/*    */ package com.aof.dao.inventory.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.inventory.InventoryTransitDAO;
/*    */ import com.aof.model.inventory.InventoryTransit;
/*    */ import com.aof.model.inventory.query.InventoryQueryOrder;
/*    */ import com.aof.model.inventory.query.InventoryTransitQueryCondition;
/*    */ import com.aof.model.inventory.query.InventoryTransitQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InventoryTransitDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements InventoryTransitDAO
/*    */ {
/* 30 */   private Log log = LogFactory.getLog(InventoryTransitDAOHibernate.class);
/*    */   
/*    */   private static final String SQL_COUNT = "select count(*) from InventoryTransit it";
/*    */   
/*    */   private static final String SQL = "from InventoryTransit it";
/*    */   
/* 36 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 37 */       { InventoryTransitQueryCondition.ID_EQ, "it.id = ?"
/* 38 */       }, { InventoryTransitQueryCondition.PART_CODE_EQ, "it.part.id = ?" }
/*    */     };
/*    */   
/* 41 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 42 */       { InventoryQueryOrder.ID, "it.id" }
/*    */     };
/*    */   
/*    */   public InventoryTransit getInventoryTransit(Integer id) {
/* 46 */     if (id == null) {
/* 47 */       if (this.log.isDebugEnabled())
/* 48 */         this.log.debug("try to get inventoryTransit with null id"); 
/* 49 */       return null;
/*    */     } 
/* 51 */     return (InventoryTransit)getHibernateTemplate().get(InventoryTransit.class, id);
/*    */   }
/*    */   
/*    */   public int getInventoryTransitListCount(Map conditions) {
/* 55 */     return getListCount(conditions, "select count(*) from InventoryTransit it", QUERY_CONDITIONS);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getInventoryTransitList(Map conditions, int pageNo, int pageSize, InventoryTransitQueryOrder order, boolean descend) {
/* 60 */     return getList(conditions, pageNo, pageSize, order, descend, "from InventoryTransit it", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */   
/*    */   public InventoryTransit insertInventoryTransit(InventoryTransit transit) {
/* 64 */     getHibernateTemplate().save(transit);
/* 65 */     return transit;
/*    */   }
/*    */   
/*    */   public InventoryTransit updateInventoryTransit(InventoryTransit inventory) {
/* 69 */     Integer id = inventory.getId();
/* 70 */     if (id == null) {
/* 71 */       throw new RuntimeException("cannot save a inventory with null id");
/*    */     }
/* 73 */     InventoryTransit oldInventory = getInventoryTransit(id);
/* 74 */     if (oldInventory != null) {
/*    */       try {
/* 76 */         PropertyUtils.copyProperties(oldInventory, inventory);
/* 77 */       } catch (Exception e) {
/* 78 */         throw new RuntimeException("copy error" + e);
/*    */       } 
/* 80 */       getHibernateTemplate().update(oldInventory);
/* 81 */       return oldInventory;
/*    */     } 
/* 83 */     throw new RuntimeException("srt not found");
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/inventory/hibernate/InventoryTransitDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */