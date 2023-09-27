/*     */ package com.aof.dao.business.rule.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.business.rule.RuleDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.business.rule.Rule;
/*     */ import com.aof.model.business.rule.RuleCondition;
/*     */ import com.aof.model.business.rule.RuleConsequence;
/*     */ import com.aof.model.business.rule.query.RuleQueryCondition;
/*     */ import com.aof.model.business.rule.query.RuleQueryOrder;
/*     */ import com.aof.model.metadata.ConditionType;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.type.Type;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.springframework.orm.hibernate.HibernateTemplate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RuleDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements RuleDAO
/*     */ {
/*  36 */   private Log log = LogFactory.getLog(RuleDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from Rule r";
/*     */   private static final String SQL = "from Rule r";
/*     */   
/*     */   public Rule getRule(Integer id, boolean loadLazyProperties) {
/*  42 */     if (id == null) {
/*  43 */       if (this.log.isDebugEnabled())
/*  44 */         this.log.debug("try to get Rule with null id"); 
/*  45 */       return null;
/*     */     } 
/*  47 */     HibernateTemplate template = getHibernateTemplate();
/*  48 */     Rule r = (Rule)template.get(Rule.class, id);
/*  49 */     if (r != null && loadLazyProperties) {
/*  50 */       template.initialize(r.getConditions());
/*  51 */       template.initialize(r.getConsequences());
/*     */     } 
/*  53 */     return r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rule saveRule(Rule r) {
/*  60 */     HibernateTemplate template = getHibernateTemplate();
/*  61 */     template.saveOrUpdate(r);
/*  62 */     template.flush();
/*  63 */     return r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeRule(Integer id) {
/*  70 */     Rule r = getRule(id, false);
/*  71 */     HibernateTemplate template = getHibernateTemplate();
/*  72 */     template.delete(r);
/*  73 */     template.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  81 */       { RuleQueryCondition.ID_EQ, "r.id = ?"
/*  82 */       }, { RuleQueryCondition.DESCRIPTION_LIKE, "r.description like ?", new LikeConvert()
/*  83 */       }, { RuleQueryCondition.TYPE_EQ, "r.type = ?"
/*  84 */       }, { RuleQueryCondition.ENABLED_EQ, "r.enabled = ?"
/*  85 */       }, { RuleQueryCondition.SITE_ID_EQ, "r.site.id = ?" }
/*     */     };
/*     */   
/*  88 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  89 */       { RuleQueryOrder.ID, "r.id"
/*  90 */       }, { RuleQueryOrder.DESCRIPTION, "r.description"
/*  91 */       }, { RuleQueryOrder.ENABLED, "r.enabled" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRuleListCount(Map conditions) {
/*  98 */     return getListCount(conditions, "select count(*) from Rule r", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getRuleList(Map conditions, int pageNo, int pageSize, RuleQueryOrder order, boolean descend) {
/* 105 */     return getList(conditions, pageNo, pageSize, order, descend, "from Rule r", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRuleInUse(Integer id) {
/* 112 */     return getHibernateTemplate().iterate("from FlowRule fr where fr.rule.id = ?", id, (Type)Hibernate.INTEGER).hasNext();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuleCondition getRuleCondition(Integer ruleId, ConditionType ct) {
/* 119 */     if (ruleId == null) {
/* 120 */       if (this.log.isDebugEnabled())
/* 121 */         this.log.debug("try to get RuleCondition with null rule id"); 
/* 122 */       return null;
/*     */     } 
/* 124 */     HibernateTemplate template = getHibernateTemplate();
/* 125 */     Rule r = (Rule)template.get(Rule.class, ruleId);
/* 126 */     if (r == null) return null; 
/* 127 */     return (RuleCondition)template.get(RuleCondition.class, (Serializable)new RuleCondition(r, ct));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuleCondition saveRuleCondition(RuleCondition rc) {
/* 134 */     HibernateTemplate template = getHibernateTemplate();
/* 135 */     template.save(rc);
/* 136 */     template.flush();
/* 137 */     template.evict(rc);
/* 138 */     return getRuleCondition(rc.getRule().getId(), rc.getType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuleCondition updateRuleCondition(RuleCondition rc) {
/* 145 */     HibernateTemplate template = getHibernateTemplate();
/* 146 */     template.update(rc);
/* 147 */     template.flush();
/* 148 */     template.evict(rc);
/* 149 */     return getRuleCondition(rc.getRule().getId(), rc.getType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeRuleCondition(RuleCondition rc) {
/* 156 */     HibernateTemplate template = getHibernateTemplate();
/* 157 */     template.delete(rc);
/* 158 */     template.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuleConsequence getRuleConsequence(Integer ruleId, Integer userId) {
/* 165 */     if (ruleId == null) {
/* 166 */       if (this.log.isDebugEnabled())
/* 167 */         this.log.debug("try to get RuleConsequence with null rule id"); 
/* 168 */       return null;
/*     */     } 
/* 170 */     if (userId == null) {
/* 171 */       if (this.log.isDebugEnabled())
/* 172 */         this.log.debug("try to get RuleConsequence with null user id"); 
/* 173 */       return null;
/*     */     } 
/* 175 */     HibernateTemplate template = getHibernateTemplate();
/* 176 */     Rule r = (Rule)template.get(Rule.class, ruleId);
/* 177 */     if (r == null) return null; 
/* 178 */     User u = (User)template.get(User.class, userId);
/* 179 */     if (u == null) return null; 
/* 180 */     return (RuleConsequence)template.get(RuleConsequence.class, (Serializable)new RuleConsequence(r, u));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuleConsequence saveRuleConsequence(RuleConsequence rc) {
/* 187 */     HibernateTemplate template = getHibernateTemplate();
/* 188 */     template.save(rc);
/* 189 */     template.flush();
/* 190 */     template.evict(rc);
/* 191 */     return getRuleConsequence(rc.getRule().getId(), rc.getUser().getId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RuleConsequence updateRuleConsequence(RuleConsequence rc) {
/* 198 */     HibernateTemplate template = getHibernateTemplate();
/* 199 */     template.update(rc);
/* 200 */     template.flush();
/* 201 */     template.evict(rc);
/* 202 */     return getRuleConsequence(rc.getRule().getId(), rc.getUser().getId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeRuleConsequence(RuleConsequence rc) {
/* 209 */     HibernateTemplate template = getHibernateTemplate();
/* 210 */     template.delete(rc);
/* 211 */     template.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getMaxConsequenceSeqNoForRuleId(Integer ruleId) {
/* 218 */     return getHibernateTemplate().find("select max(rc.seq) from RuleConsequence rc where rc.rule.id = ?", ruleId, (Type)Hibernate.INTEGER).get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getConditionsForRuleId(Integer ruleId) {
/* 225 */     return getHibernateTemplate().find("from RuleCondition rc where rc.rule.id = ?", ruleId, (Type)Hibernate.INTEGER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getConsequencesForRuleId(Integer ruleId) {
/* 232 */     return getHibernateTemplate().find("from RuleConsequence rc where rc.rule.id = ? order by rc.seq", ruleId, (Type)Hibernate.INTEGER);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/business/rule/hibernate/RuleDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */