/*     */ package com.aof.dao.schedule.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.schedule.ProjectedInventoryDao;
/*     */ import com.aof.model.schedule.ProjectedInventory;
/*     */ import com.aof.model.schedule.query.ProjectedInventoryQueryCondition;
/*     */ import com.aof.model.schedule.query.ProjectedInventoryQueryOrder;
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
/*     */ public class ProjectedInventoryDaoHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements ProjectedInventoryDao
/*     */ {
/*  27 */   private Log log = LogFactory.getLog(ProjectedInventoryDaoHibernate.class); private static final String SQL_COUNT = "select count(*) from ProjectedInventory entity";
/*     */   public ProjectedInventory save(ProjectedInventory entity) {
/*  29 */     getHibernateTemplate().save(entity);
/*  30 */     return entity;
/*     */   }
/*     */   private static final String SQL = "from ProjectedInventory entity";
/*     */   public ProjectedInventory getProjectedInventory(Integer id) {
/*  34 */     if (id == null && 
/*  35 */       this.log.isDebugEnabled()) {
/*  36 */       this.log.debug("get ProjectedInventory with null id!");
/*  37 */       return null;
/*     */     } 
/*     */     
/*  40 */     return (ProjectedInventory)getHibernateTemplate().get(ProjectedInventory.class, id);
/*     */   }
/*     */   
/*     */   public void delete(ProjectedInventory entity) {
/*  44 */     getHibernateTemplate().delete(entity);
/*     */   }
/*     */   
/*     */   public ProjectedInventory update(ProjectedInventory entity) {
/*  48 */     if (entity.getId() == null) {
/*  49 */       throw new RuntimeException("update ProjectedInventory with null id!");
/*     */     }
/*  51 */     ProjectedInventory oldEntity = getProjectedInventory(entity.getId());
/*  52 */     if (oldEntity != null) {
/*     */       try {
/*  54 */         PropertyUtils.copyProperties(oldEntity, entity);
/*  55 */       } catch (Exception e) {
/*  56 */         throw new RuntimeException("copy error with MappingCsutomer" + e);
/*     */       } 
/*  58 */       getHibernateTemplate().update(oldEntity);
/*  59 */       return oldEntity;
/*     */     } 
/*  61 */     throw new RuntimeException("MappinCustomer not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  70 */       { ProjectedInventoryQueryCondition.ID_EQ, "entity.id = ?"
/*  71 */       }, { ProjectedInventoryQueryCondition.PART_ID_EQ, "entity.part.id = ?"
/*  72 */       }, { ProjectedInventoryQueryCondition.Part_VEND_EQ, "entity.part.vend = ?" }
/*     */     };
/*     */   
/*  75 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  76 */       { ProjectedInventoryQueryOrder.ID, "entity.id" } };
/*     */   private static final String SQL_JitShipPart = "from ProjectedInventory entity";
/*     */   
/*     */   public Integer getListCount(Map conditions) {
/*  80 */     return Integer.valueOf(getListCount(conditions, "select count(*) from ProjectedInventory entity", QUERY_CONDITIONS));
/*     */   }
/*     */ 
/*     */   
/*     */   public List<ProjectedInventory> getList(Map conditions, int pageNum, int pageSize, ProjectedInventoryQueryOrder order, boolean descend) {
/*  85 */     return getList(conditions, pageNum, pageSize, order, descend, "from ProjectedInventory entity", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ProjectedInventory> getJitShipPartNumberList(Map conditions, int pageNo, int pageSize, ProjectedInventoryQueryOrder order, boolean descend) {
/*  96 */     String HQL = "from ProjectedInventory entity";
/*  97 */     Map map = conditions;
/*  98 */     Object a = map.get("a");
/*  99 */     Object e = map.get("e");
/* 100 */     Object b = map.get("b");
/* 101 */     if (a != null) {
/* 102 */       HQL = String.valueOf(HQL) + " where " + a + " " + e + " " + b + " and entity.part = entity.part.id";
/*     */     }
/* 104 */     String s = HQL;
/* 105 */     return getList(null, pageNo, pageSize, order, descend, s, QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/hibernate/ProjectedInventoryDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */