/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.InventoryMovingDAO;
/*     */ import com.aof.model.admin.query.InventoryMovingQueryCondition;
/*     */ import com.aof.model.admin.query.InventoryMovingQueryOrder;
/*     */ import com.aof.model.inventory.InventoryMoving;
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
/*     */ public class InventoryMovingDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements InventoryMovingDAO
/*     */ {
/*  30 */   private Log log = LogFactory.getLog(InventoryMovingDAOHibernate.class);
/*     */ 
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from InventoryMoving iv";
/*     */ 
/*     */   
/*     */   private static final String SQL = "from InventoryMoving iv";
/*     */ 
/*     */   
/*     */   public InventoryMoving getInventoryMoving(Integer id) {
/*  40 */     if (id == null) {
/*  41 */       if (this.log.isDebugEnabled())
/*  42 */         this.log.debug("try to get InventoryMoving with null id"); 
/*  43 */       return null;
/*     */     } 
/*  45 */     return (InventoryMoving)getHibernateTemplate().get(
/*  46 */         InventoryMoving.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InventoryMoving updateInventoryMoving(InventoryMoving inventoryMoving) {
/*  57 */     Integer id = inventoryMoving.getId();
/*  58 */     if (id == null) {
/*  59 */       throw new RuntimeException(
/*  60 */           "cannot save a InventoryMoving with null id");
/*     */     }
/*  62 */     InventoryMoving oldInventoryMoving = getInventoryMoving(id);
/*  63 */     if (oldInventoryMoving != null) {
/*     */       try {
/*  65 */         PropertyUtils.copyProperties(oldInventoryMoving, 
/*  66 */             inventoryMoving);
/*  67 */       } catch (Exception e) {
/*  68 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  70 */       getHibernateTemplate().update(oldInventoryMoving);
/*  71 */       return oldInventoryMoving;
/*     */     } 
/*  73 */     throw new RuntimeException("InventoryMoving not found");
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
/*     */   public InventoryMoving insertInventoryMoving(InventoryMoving inventoryMoving) {
/*  85 */     getHibernateTemplate().save(inventoryMoving);
/*  86 */     return inventoryMoving;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  94 */       { InventoryMovingQueryCondition.ID_EQ, "iv.id = ?" }
/*     */     };
/*     */   
/*  97 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  98 */       { InventoryMovingQueryOrder.ID, "iv.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryMovingListCount(Map conditions) {
/* 110 */     return getListCount(conditions, "select count(*) from InventoryMoving iv", QUERY_CONDITIONS);
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
/*     */   public List getInventoryMovingList(Map conditions, int pageNo, int pageSize, InventoryMovingQueryOrder order, boolean descend) {
/* 125 */     return getList(conditions, pageNo, pageSize, order, descend, "from InventoryMoving iv", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/InventoryMovingDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */