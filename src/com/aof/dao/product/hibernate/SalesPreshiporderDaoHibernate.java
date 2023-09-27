/*     */ package com.aof.dao.product.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.product.SalesPreshiporderDao;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.product.SalesPreshiporder;
/*     */ import com.aof.model.product.SalesPreshiporderBatch;
/*     */ import com.aof.model.product.SalesPreshiporderItem;
/*     */ import com.aof.model.product.query.SalesPreshiporderQueryCondition;
/*     */ import com.aof.model.product.query.SalesPreshiporderQueryOrder;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.type.Type;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SalesPreshiporderDaoHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements SalesPreshiporderDao
/*     */ {
/*  27 */   private Log log = LogFactory.getLog(SalesPreshiporderDaoHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from SalesPreshiporder salesPreshiporder";
/*     */   
/*     */   private static final String SQL = "from SalesPreshiporder salesPreshiporder";
/*     */   
/*  33 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  34 */       { SalesPreshiporderQueryCondition.ID_EQ, "salesPreshiporder.id = ?"
/*  35 */       }, { SalesPreshiporderQueryCondition.TYPE_EQ, "salesPreshiporder.type = ?" }
/*     */     };
/*  37 */   private static final Object[][] QUERY_ORDERS = new Object[][] { { SalesPreshiporderQueryOrder.ID, "salesPreshiporder.id" } };
/*     */   
/*     */   public SalesPreshiporder getById(Integer id) {
/*  40 */     if (id == null) {
/*  41 */       if (this.log.isDebugEnabled()) this.log.debug("try to get SalesPreshiporder with null id"); 
/*  42 */       return null;
/*     */     } 
/*  44 */     return (SalesPreshiporder)getHibernateTemplate().get(SalesPreshiporder.class, id);
/*     */   }
/*     */   
/*     */   public SalesPreshiporder insert(SalesPreshiporder salesPreshiporder) {
/*  48 */     getHibernateTemplate().save(salesPreshiporder);
/*  49 */     return salesPreshiporder;
/*     */   }
/*     */   
/*     */   public void remove(SalesPreshiporder salesPreshiporder) {
/*  53 */     getHibernateTemplate().delete(salesPreshiporder);
/*     */   }
/*     */   
/*     */   public SalesPreshiporder update(SalesPreshiporder salesPreshiporder) {
/*  57 */     if (salesPreshiporder.getId() == null) throw new RuntimeException("update SalesPreshiporder with null id!"); 
/*  58 */     SalesPreshiporder oldEntity = getById(salesPreshiporder.getId());
/*  59 */     if (oldEntity != null) {
/*     */       try {
/*  61 */         PropertyUtils.copyProperties(oldEntity, salesPreshiporder);
/*  62 */       } catch (Exception e) {
/*  63 */         throw new RuntimeException("copy error with SalesPreshiporder" + e);
/*     */       } 
/*  65 */       getHibernateTemplate().update(oldEntity);
/*  66 */       return oldEntity;
/*     */     } 
/*  68 */     throw new RuntimeException("SalesPreshiporder not found");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getListCount(Map conditions) {
/*  74 */     return getListCount(conditions, "select count(*) from SalesPreshiporder salesPreshiporder", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getList(Map conditions, int pageNo, int pageSize, SalesPreshiporderQueryOrder order, boolean descend) {
/*  79 */     return getList(conditions, pageNo, pageSize, order, descend, "from SalesPreshiporder salesPreshiporder", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<SalesPreshiporderItem> getSalesPreshiporderListByOrderId(Integer id) {
/*  84 */     String hql = "from SalesPreshiporderItem item where item.spsoId.id=? ";
/*  85 */     return getHibernateTemplate().find(hql, id);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Box> getBoxByBatchList(String partId) {
/*  90 */     return null;
/*     */   }
/*     */   
/*     */   public SalesPreshiporder getSalesPreshiporder(String code) {
/*  94 */     String sql = "from SalesPreshiporder salesPreshiporder where salesPreshiporder.code = ?";
/*  95 */     List<SalesPreshiporder> list = getHibernateTemplate().find(sql, code, (Type)Hibernate.STRING);
/*  96 */     if (list == null || list.isEmpty()) {
/*  97 */       return null;
/*     */     }
/*  99 */     return list.get(0);
/*     */   }
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
/*     */   public String getSalesPreshiporderBacthByLotStatus(String shipOrder, String lotSer) {
/* 159 */     String sql = "from SalesPreshiporderBatch batch where batch.spsoitemId.spsoId.code=? and batch.box.lot.id=? ";
/* 160 */     List<SalesPreshiporderBatch> list = getHibernateTemplate().find(sql, new Object[] { shipOrder, lotSer });
/* 161 */     if (list.size() > 0) {
/* 162 */       EnabledDisabled enabled = ((SalesPreshiporderBatch)list.get(0)).getEnabled();
/* 163 */       if (enabled == EnabledDisabled.DISABLED)
/*     */       {
/* 165 */         return "disabled";
/*     */       }
/*     */       
/* 168 */       return "yes";
/*     */     } 
/*     */     
/* 171 */     return "not";
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/hibernate/SalesPreshiporderDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */