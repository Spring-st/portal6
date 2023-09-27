/*    */ package com.aof.dao.product.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.product.SalesPreshiporderBatchDao;
/*    */ import com.aof.model.product.SalesPreshiporderBatch;
/*    */ import com.aof.model.product.query.SalesPreshiporderBatchQueryCondition;
/*    */ import com.aof.model.product.query.SalesPreshiporderBatchQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.springframework.orm.hibernate.HibernateTemplate;
/*    */ 
/*    */ 
/*    */ public class SalesPreshiporderBatchDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements SalesPreshiporderBatchDao
/*    */ {
/* 19 */   private Log log = LogFactory.getLog(SalesPreshiporderBatchDaoHibernate.class);
/*    */   
/*    */   private static final String SQL_COUNT = "select count(*) from SalesPreshiporderBatch salesPreshiporderBatch";
/*    */   
/*    */   private static final String SQL = "from SalesPreshiporderBatch salesPreshiporderBatch";
/*    */   
/* 25 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 26 */       { SalesPreshiporderBatchQueryCondition.ID_EQ, "salesPreshiporderBatch.id = ?"
/* 27 */       }, { SalesPreshiporderBatchQueryCondition.SPSOITEM_SHIPORDER_ID_EQ, "salesPreshiporderBatch.spsoitemId.spsoId.id = ?" }
/*    */     };
/* 29 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 30 */       { SalesPreshiporderBatchQueryOrder.ID, "salesPreshiporderBatch.id"
/* 31 */       }, { SalesPreshiporderBatchQueryOrder.LOCATIONID, "salesPreshiporderBatch.box.location.id"
/* 32 */       }, { SalesPreshiporderBatchQueryOrder.PARTID, "salesPreshiporderBatch.box.part.id" }
/*    */     };
/*    */   
/*    */   public SalesPreshiporderBatch getById(Integer id) {
/* 36 */     if (id == null) {
/* 37 */       if (this.log.isDebugEnabled()) this.log.debug("try to get SalesPreshiporderBatch with null id"); 
/* 38 */       return null;
/*    */     } 
/* 40 */     return (SalesPreshiporderBatch)getHibernateTemplate().get(SalesPreshiporderBatch.class, id);
/*    */   }
/*    */   
/*    */   public SalesPreshiporderBatch insert(SalesPreshiporderBatch salesPreshiporderBatch) {
/* 44 */     getHibernateTemplate().save(salesPreshiporderBatch);
/* 45 */     return salesPreshiporderBatch;
/*    */   }
/*    */   
/*    */   public void remove(SalesPreshiporderBatch salesPreshiporderBatch) {
/* 49 */     getHibernateTemplate().delete(salesPreshiporderBatch);
/*    */   }
/*    */   
/*    */   public SalesPreshiporderBatch update(SalesPreshiporderBatch salesPreshiporderBatch) {
/* 53 */     HibernateTemplate template = getHibernateTemplate();
/* 54 */     template.update(salesPreshiporderBatch);
/* 55 */     template.flush();
/* 56 */     return getById(salesPreshiporderBatch.getId());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 61 */     return getListCount(conditions, "select count(*) from SalesPreshiporderBatch salesPreshiporderBatch", QUERY_CONDITIONS);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getList(Map conditions, int pageNo, int pageSize, SalesPreshiporderBatchQueryOrder order, boolean descend) {
/* 66 */     return getList(conditions, pageNo, pageSize, order, descend, "from SalesPreshiporderBatch salesPreshiporderBatch", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */   
/*    */   public List getNotWorkList(Integer salesPreshiporderId) {
/* 70 */     String sql = " from SalesPreshiporderBatch salesPreshiporderBatch where salesPreshiporderBatch.spsoitemId.spsoId.id='" + salesPreshiporderId + "' and salesPreshiporderBatch.box.lot.id not in(" + 
/* 71 */       " select salesWorkorder.lotSer.id from SalesWorkorder salesWorkorder where salesWorkorder.shipId.id='" + salesPreshiporderId + "')";
/* 72 */     return getHibernateTemplate().find(sql);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/hibernate/SalesPreshiporderBatchDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */