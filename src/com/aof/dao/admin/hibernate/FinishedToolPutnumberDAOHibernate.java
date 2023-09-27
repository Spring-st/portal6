/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.FinishedToolPutnumberDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.FinishedToolPutnumber;
/*     */ import com.aof.model.admin.query.FinishedToolPutnumberQueryCondition;
/*     */ import com.aof.model.admin.query.FinishedToolPutnumberQueryOrder;
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
/*     */ public class FinishedToolPutnumberDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements FinishedToolPutnumberDAO
/*     */ {
/*  33 */   private Log log = LogFactory.getLog(FinishedToolPutnumberDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from FinishedToolPutnumber finishedToolPutnumber";
/*     */   
/*     */   private static final String SQL = "from FinishedToolPutnumber finishedToolPutnumber";
/*     */ 
/*     */   
/*     */   public FinishedToolPutnumber getFinishedToolPutnumber(Integer id) {
/*  41 */     if (id == null) {
/*  42 */       if (this.log.isDebugEnabled())
/*  43 */         this.log.debug("try to get FinishedToolPutnumber with null id"); 
/*  44 */       return null;
/*     */     } 
/*  46 */     return (FinishedToolPutnumber)getHibernateTemplate().get(FinishedToolPutnumber.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FinishedToolPutnumber updateFinishedToolPutnumber(FinishedToolPutnumber finishedToolPutnumber) {
/*  55 */     Integer id = finishedToolPutnumber.getId();
/*  56 */     if (id == null) {
/*  57 */       throw new RuntimeException("cannot save a finishedToolPutnumber with null id");
/*     */     }
/*  59 */     FinishedToolPutnumber oldFinishedToolPutnumber = getFinishedToolPutnumber(id);
/*  60 */     if (oldFinishedToolPutnumber != null) {
/*     */       try {
/*  62 */         PropertyUtils.copyProperties(oldFinishedToolPutnumber, finishedToolPutnumber);
/*  63 */       } catch (Exception e) {
/*  64 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  66 */       getHibernateTemplate().update(oldFinishedToolPutnumber);
/*  67 */       return oldFinishedToolPutnumber;
/*     */     } 
/*  69 */     throw new RuntimeException("finishedToolPutnumber not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FinishedToolPutnumber insertFinishedToolPutnumber(FinishedToolPutnumber finishedToolPutnumber) {
/*  79 */     getHibernateTemplate().save(finishedToolPutnumber);
/*  80 */     return finishedToolPutnumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] { { FinishedToolPutnumberQueryCondition.ID_EQ, "finishedToolPutnumber.id = ?"
/*  88 */       }, { FinishedToolPutnumberQueryCondition.TOOLCODE_LIKE, "finishedToolPutnumber.toolCode like ?", new LikeConvert()
/*  89 */       }, { FinishedToolPutnumberQueryCondition.FINISHEDCODE_LIKE, "finishedToolPutnumber.finishedCode like ?", new LikeConvert()
/*  90 */       }, { FinishedToolPutnumberQueryCondition.FINISHEDCODE_EQ, "finishedToolPutnumber.finishedCode = ?"
/*  91 */       }, { FinishedToolPutnumberQueryCondition.ENABLED_EQ, "finishedToolPutnumber.status = ?" } };
/*     */ 
/*     */ 
/*     */   
/*  95 */   private static final Object[][] QUERY_ORDERS = new Object[][] { { FinishedToolPutnumberQueryOrder.ID, "finishedToolPutnumber.id" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFinishedToolPutnumberListCount(Map conditions) {
/* 103 */     return getListCount(conditions, "select count(*) from FinishedToolPutnumber finishedToolPutnumber", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getFinishedToolPutnumberList(Map conditions, int pageNo, int pageSize, FinishedToolPutnumberQueryOrder order, boolean descend) {
/* 113 */     return getList(conditions, pageNo, pageSize, order, descend, "from FinishedToolPutnumber finishedToolPutnumber", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteFinishedToolPutnumber(FinishedToolPutnumber finishedToolPutnumber) {
/* 118 */     getHibernateTemplate().delete(finishedToolPutnumber);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/FinishedToolPutnumberDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */