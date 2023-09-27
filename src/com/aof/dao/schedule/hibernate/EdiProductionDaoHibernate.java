/*     */ package com.aof.dao.schedule.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.schedule.EdiProductionDao;
/*     */ import com.aof.model.schedule.EdiProduction;
/*     */ import com.aof.model.schedule.query.EdiProductionQueryCondition;
/*     */ import com.aof.model.schedule.query.EdiProductionQueryOrder;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EdiProductionDaoHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements EdiProductionDao
/*     */ {
/*  20 */   private Log log = LogFactory.getLog(EdiProductionDaoHibernate.class); private static final String SQL_COUNT = "select count(*) from EdiProduction entity";
/*     */   public EdiProduction save(EdiProduction entity) {
/*  22 */     getHibernateTemplate().save(entity);
/*  23 */     return entity;
/*     */   }
/*     */   private static final String SQL = "from EdiProduction entity"; private static final String SQL_GROUP = "from EdiProduction entity";
/*     */   public EdiProduction getEdiProduction(Integer id) {
/*  27 */     if (id == null && 
/*  28 */       this.log.isDebugEnabled()) {
/*  29 */       this.log.debug("get EdiProduction with null id!");
/*  30 */       return null;
/*     */     } 
/*     */     
/*  33 */     return (EdiProduction)getHibernateTemplate().get(EdiProduction.class, id);
/*     */   }
/*     */   
/*     */   public void delete(EdiProduction entity) {
/*  37 */     getHibernateTemplate().delete(entity);
/*     */   }
/*     */   
/*     */   public EdiProduction update(EdiProduction entity) {
/*  41 */     if (entity.getId() == null) {
/*  42 */       throw new RuntimeException("update EdiProduction with null id!");
/*     */     }
/*  44 */     EdiProduction oldEntity = getEdiProduction(entity.getId());
/*  45 */     if (oldEntity != null) {
/*     */       try {
/*  47 */         PropertyUtils.copyProperties(oldEntity, entity);
/*  48 */       } catch (Exception e) {
/*  49 */         throw new RuntimeException("copy error with MappingCsutomer" + e);
/*     */       } 
/*  51 */       getHibernateTemplate().update(oldEntity);
/*  52 */       return oldEntity;
/*     */     } 
/*  54 */     throw new RuntimeException("MappinCustomer not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  65 */       { EdiProductionQueryCondition.ID_EQ, "entity.id = ?"
/*  66 */       }, { EdiProductionQueryCondition.TYPE_EQ, "entity.type = ?"
/*  67 */       }, { EdiProductionQueryCondition.STATUS_NOT_EQ, "entity.status != ?"
/*  68 */       }, { EdiProductionQueryCondition.SUPPLIER_EQ, "entity.supplier = ?"
/*  69 */       }, { EdiProductionQueryCondition.UPLOADER_EQ, "entity.uploader = ?"
/*  70 */       }, { EdiProductionQueryCondition.UPLOADFILENAME_EQ, "entity.uploadFileName = ?"
/*  71 */       }, { EdiProductionQueryCondition.UPLOADTIME_EQ, "entity.uploadTime = ?"
/*  72 */       }, { EdiProductionQueryCondition.HANDSTATUS_EQ, "entity.handStatus = ?"
/*  73 */       }, { EdiProductionQueryCondition.ENABLED_EQ, "entity.enabled = ?"
/*  74 */       }, { EdiProductionQueryCondition.ENABLED_NE, "entity.enabled <> ?" }
/*     */     };
/*  76 */   private static final Object[][] QUERY_CONDITIONS_GROUP = new Object[][] {
/*  77 */       { EdiProductionQueryCondition.ID_EQ, "entity.id = ?"
/*  78 */       }, { EdiProductionQueryCondition.TYPE_EQ, "entity.type = ?"
/*  79 */       }, { EdiProductionQueryCondition.STATUS_NOT_EQ, "entity.status != ?"
/*     */       },
/*  81 */       { EdiProductionQueryCondition.ID_IN, "entity.id in ( select min(a.id) from EdiProduction a group by a.supplier,a.uploader, a.uploadTime,a.uploadFileName )" }
/*     */     };
/*     */   
/*  84 */   private static final Object[][] QUERY_CONDITIONS_GROUP_REPORT = new Object[][] {
/*  85 */       { EdiProductionQueryCondition.ID_EQ, "entity.id = ?"
/*  86 */       }, { EdiProductionQueryCondition.TYPE_EQ, "entity.type = ?"
/*  87 */       }, { EdiProductionQueryCondition.STATUS_NOT_EQ, "entity.status != ?"
/*     */       },
/*  89 */       { EdiProductionQueryCondition.ID_IN, "entity.id in ( select min(a.id) from EdiProduction a group by a.asnNo )" }
/*     */     };
/*     */   
/*  92 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  93 */       { EdiProductionQueryOrder.ID, "entity.id"
/*  94 */       }, { EdiProductionQueryOrder.SYNCTIME, "entity.syncTime" }
/*     */     };
/*     */   
/*     */   public Integer getListCount(Map conditions) {
/*  98 */     return Integer.valueOf(getListCount(conditions, "select count(*) from EdiProduction entity", QUERY_CONDITIONS));
/*     */   }
/*     */ 
/*     */   
/*     */   public List<EdiProduction> getList(Map conditions, int pageNum, int pageSize, EdiProductionQueryOrder order, boolean descend) {
/* 103 */     return getList(conditions, pageNum, pageSize, order, descend, "from EdiProduction entity", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<EdiProduction> getGroupList(Map conditions, int pageNum, int pageSize, EdiProductionQueryOrder order, boolean descend) {
/* 108 */     return getList(conditions, pageNum, pageSize, order, descend, "from EdiProduction entity", QUERY_CONDITIONS_GROUP, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer getListGroupCount(Map conditions) {
/* 113 */     return Integer.valueOf(getListCount(conditions, "select count(*) from EdiProduction entity", QUERY_CONDITIONS_GROUP));
/*     */   }
/*     */ 
/*     */   
/*     */   public List<EdiProduction> getListReport(Map conditions, int pageNum, int pageSize, EdiProductionQueryOrder order, boolean descend) {
/* 118 */     return getList(conditions, pageNum, pageSize, order, descend, "from EdiProduction entity", QUERY_CONDITIONS_GROUP_REPORT, QUERY_ORDERS);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/hibernate/EdiProductionDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */