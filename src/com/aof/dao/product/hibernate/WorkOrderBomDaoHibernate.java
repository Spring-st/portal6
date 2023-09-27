/*    */ package com.aof.dao.product.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.product.WorkOrderBomDao;
/*    */ import com.aof.model.product.WorkOrderBom;
/*    */ import com.aof.model.product.query.WorkOrderBomQueryCondition;
/*    */ import com.aof.model.product.query.WorkOrderBomQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class WorkOrderBomDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements WorkOrderBomDao
/*    */ {
/* 18 */   private Log log = LogFactory.getLog(WorkOrderBomDaoHibernate.class);
/*    */   
/*    */   private static final String SQL_COUNT = "select count(*) from WorkOrderBom workOrderBom";
/*    */   
/*    */   private static final String SQL = "from WorkOrderBom workOrderBom";
/*    */   
/* 24 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] { { WorkOrderBomQueryCondition.ID_EQ, "workOrderBom.id = ?" } };
/* 25 */   private static final Object[][] QUERY_ORDERS = new Object[][] { { WorkOrderBomQueryOrder.ID, "workOrderBom.id" } };
/*    */   
/*    */   public WorkOrderBom getById(Integer id) {
/* 28 */     if (id == null) {
/* 29 */       if (this.log.isDebugEnabled()) this.log.debug("try to get WorkOrderBom with null id"); 
/* 30 */       return null;
/*    */     } 
/* 32 */     return (WorkOrderBom)getHibernateTemplate().get(WorkOrderBom.class, id);
/*    */   }
/*    */   
/*    */   public WorkOrderBom insert(WorkOrderBom workOrderBom) {
/* 36 */     getHibernateTemplate().save(workOrderBom);
/* 37 */     return workOrderBom;
/*    */   }
/*    */   
/*    */   public void remove(WorkOrderBom workOrderBom) {
/* 41 */     getHibernateTemplate().delete(workOrderBom);
/*    */   }
/*    */   
/*    */   public WorkOrderBom update(WorkOrderBom workOrderBom) {
/* 45 */     if (workOrderBom.getId() == null) throw new RuntimeException("update WorkOrderBom with null id!"); 
/* 46 */     WorkOrderBom oldEntity = getById(workOrderBom.getId());
/* 47 */     if (oldEntity != null) {
/*    */       try {
/* 49 */         PropertyUtils.copyProperties(oldEntity, workOrderBom);
/* 50 */       } catch (Exception e) {
/* 51 */         throw new RuntimeException("copy error with WorkOrderBom" + e);
/*    */       } 
/* 53 */       getHibernateTemplate().update(oldEntity);
/* 54 */       return oldEntity;
/*    */     } 
/* 56 */     throw new RuntimeException("WorkOrderBom not found");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 62 */     return getListCount(conditions, "select count(*) from WorkOrderBom workOrderBom", QUERY_CONDITIONS);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNo, int pageSize, WorkOrderBomQueryOrder order, boolean descend) {
/* 67 */     return getList(conditions, pageNo, pageSize, order, descend, "from WorkOrderBom workOrderBom", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/hibernate/WorkOrderBomDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */