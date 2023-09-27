/*    */ package com.aof.model.po;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.math.BigDecimal;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PurchaseOrderInspectionPendingItem
/*    */   extends AbstractPurchaseOrderInspectionPendingItem
/*    */   implements Serializable
/*    */ {
/*    */   private BigDecimal waitQuantityTemp;
/*    */   
/*    */   public PurchaseOrderInspectionPendingItem() {}
/*    */   
/*    */   public PurchaseOrderInspectionPendingItem(Integer id) {
/* 34 */     super(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BigDecimal getWaitQuantityTemp() {
/* 41 */     return this.waitQuantityTemp;
/*    */   }
/*    */   
/*    */   public void setWaitQuantityTemp(BigDecimal waitQuantityTemp) {
/* 45 */     this.waitQuantityTemp = waitQuantityTemp;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/PurchaseOrderInspectionPendingItem.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */