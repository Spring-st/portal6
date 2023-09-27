/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.FinishedSaiheRelationDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.FinishedSaiheRelation;
/*     */ import com.aof.model.admin.query.FinishedSaiheRelationQueryCondition;
/*     */ import com.aof.model.admin.query.FinishedSaiheRelationQueryOrder;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FinishedSaiheRelationDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements FinishedSaiheRelationDAO
/*     */ {
/*  33 */   private Log log = LogFactory.getLog(FinishedSaiheRelationDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from FinishedSaiheRelation finishedSaiheRelation";
/*     */   
/*     */   private static final String SQL = "from FinishedSaiheRelation finishedSaiheRelation";
/*     */ 
/*     */   
/*     */   public FinishedSaiheRelation getFinishedSaiheRelation(Integer id) {
/*  41 */     if (id == null) {
/*  42 */       if (this.log.isDebugEnabled())
/*  43 */         this.log.debug("try to get FinishedSaiheRelation with null id"); 
/*  44 */       return null;
/*     */     } 
/*  46 */     return (FinishedSaiheRelation)getHibernateTemplate().get(FinishedSaiheRelation.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FinishedSaiheRelation updateFinishedSaiheRelation(FinishedSaiheRelation finishedSaiheRelation) {
/*  55 */     Integer id = finishedSaiheRelation.getId();
/*  56 */     if (id == null) {
/*  57 */       throw new RuntimeException("cannot save a finishedSaiheRelation with null id");
/*     */     }
/*  59 */     FinishedSaiheRelation oldFinishedSaiheRelation = getFinishedSaiheRelation(id);
/*  60 */     if (oldFinishedSaiheRelation != null) {
/*     */       try {
/*  62 */         PropertyUtils.copyProperties(oldFinishedSaiheRelation, finishedSaiheRelation);
/*  63 */       } catch (Exception e) {
/*  64 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  66 */       getHibernateTemplate().update(oldFinishedSaiheRelation);
/*  67 */       return oldFinishedSaiheRelation;
/*     */     } 
/*  69 */     throw new RuntimeException("finishedSaiheRelation not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FinishedSaiheRelation insertFinishedSaiheRelation(FinishedSaiheRelation finishedSaiheRelation) {
/*  79 */     getHibernateTemplate().save(finishedSaiheRelation);
/*  80 */     return finishedSaiheRelation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] { { FinishedSaiheRelationQueryCondition.ID_EQ, "finishedSaiheRelation.id = ?"
/*  88 */       }, { FinishedSaiheRelationQueryCondition.SAIHECODE_LIKE, "finishedSaiheRelation.saiheCode like ?", new LikeConvert()
/*  89 */       }, { FinishedSaiheRelationQueryCondition.FINISHEDCODE_LIKE, "finishedSaiheRelation.finishedCode like ?", new LikeConvert()
/*  90 */       }, { FinishedSaiheRelationQueryCondition.SAIHECODE_EQ, "finishedSaiheRelation.saiheCode = ?"
/*  91 */       }, { FinishedSaiheRelationQueryCondition.FINISHEDCODE_EQ, "finishedSaiheRelation.finishedCode = ?"
/*  92 */       }, { FinishedSaiheRelationQueryCondition.ENABLED_EQ, "finishedSaiheRelation.status = ?" } };
/*     */ 
/*     */ 
/*     */   
/*  96 */   private static final Object[][] QUERY_ORDERS = new Object[][] { { FinishedSaiheRelationQueryOrder.ID, "finishedSaiheRelation.id" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFinishedSaiheRelationListCount(Map conditions) {
/* 104 */     return getListCount(conditions, "select count(*) from FinishedSaiheRelation finishedSaiheRelation", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getFinishedSaiheRelationList(Map conditions, int pageNo, int pageSize, FinishedSaiheRelationQueryOrder order, boolean descend) {
/* 114 */     return getList(conditions, pageNo, pageSize, order, descend, "from FinishedSaiheRelation finishedSaiheRelation", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteFinishedSaiheRelation(FinishedSaiheRelation finishedSaiheRelation) {
/* 119 */     getHibernateTemplate().delete(finishedSaiheRelation);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/FinishedSaiheRelationDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */