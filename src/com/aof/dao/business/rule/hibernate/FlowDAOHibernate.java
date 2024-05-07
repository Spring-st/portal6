/*     */ package com.aof.dao.business.rule.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.business.rule.FlowDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.business.rule.Flow;
/*     */ import com.aof.model.business.rule.FlowRule;
/*     */ import com.aof.model.business.rule.query.FlowQueryCondition;
/*     */ import com.aof.model.business.rule.query.FlowQueryOrder;
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
/*     */ 
/*     */ public class FlowDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements FlowDAO
/*     */ {
/*  33 */   private Log log = LogFactory.getLog(FlowDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from Flow f";
/*     */   private static final String SQL = "from Flow f";
/*     */   
/*     */   public Flow getFlow(Integer id, boolean loadLazyProperties) {
/*  39 */     if (id == null) {
/*  40 */       if (this.log.isDebugEnabled())
/*  41 */         this.log.debug("try to get Flow with null id"); 
/*  42 */       return null;
/*     */     } 
/*  44 */     HibernateTemplate template = getHibernateTemplate();
/*  45 */     Flow f = (Flow)template.get(Flow.class, id);
/*  46 */     if (f != null && loadLazyProperties) {
/*  47 */       template.initialize(f.getRules());
/*     */     }
/*  49 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Flow saveFlow(Flow f) {
/*  56 */     HibernateTemplate template = getHibernateTemplate();
/*  57 */     template.saveOrUpdate(f);
/*  58 */     template.flush();
/*  59 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeFlow(Integer id) {
/*  66 */     Flow f = getFlow(id, false);
/*  67 */     HibernateTemplate template = getHibernateTemplate();
/*  68 */     template.delete(f);
/*  69 */     template.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  77 */       { FlowQueryCondition.ID_EQ, "f.id = ?"
/*  78 */       }, { FlowQueryCondition.DESCRIPTION_LIKE, "f.description like ?", new LikeConvert()
/*  79 */       }, { FlowQueryCondition.TYPE_EQ, "f.type = ?"
/*  80 */       }, { FlowQueryCondition.ENABLED_EQ, "f.enabled = ?"
/*  81 */       }, { FlowQueryCondition.SITE_ID_EQ, "f.site.id = ?" }
/*     */     };
/*     */   
/*  84 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  85 */       { FlowQueryOrder.ID, "f.id"
/*  86 */       }, { FlowQueryOrder.DESCRIPTION, "f.description"
/*  87 */       }, { FlowQueryOrder.ENABLED, "f.enabled" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlowListCount(Map conditions) {
/*  94 */     return getListCount(conditions, "select count(*) from Flow f", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getFlowList(Map conditions, int pageNo, int pageSize, FlowQueryOrder order, boolean descend) {
/* 101 */     return getList(conditions, pageNo, pageSize, order, descend, "from Flow f", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getRulesForFlow(Integer flowId) {
/* 108 */     return getHibernateTemplate().find("from FlowRule fr where fr.flow.id = ? order by fr.seq", flowId, (Type)Hibernate.INTEGER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveFlowRule(FlowRule fr) {
/* 115 */     getHibernateTemplate().save(fr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeFlowRule(FlowRule fr) {
/* 122 */     getHibernateTemplate().delete(fr);
/*     */   }
/*     */ }
