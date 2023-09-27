/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractSupplierHistory
/*    */   extends BaseSupplier
/*    */   implements Serializable
/*    */ {
/*    */   private Supplier supplier;
/*    */   
/*    */   public AbstractSupplierHistory() {}
/*    */   
/*    */   public AbstractSupplierHistory(Supplier supplier) {
/* 42 */     setSupplier(supplier);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Supplier getSupplier() {
/* 51 */     return this.supplier;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSupplier(Supplier supplier) {
/* 60 */     this.supplier = supplier;
/* 61 */     if (supplier == null) {
/* 62 */       setId(null);
/*    */     } else {
/* 64 */       setId(supplier.getId());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object rhs) {
/* 76 */     if (rhs == null) return false; 
/* 77 */     if (this == rhs) return true; 
/* 78 */     if (!(rhs instanceof SupplierHistory)) return false; 
/* 79 */     SupplierHistory that = (SupplierHistory)rhs;
/* 80 */     if (getId() != null) return getId().equals(that.getId()); 
/* 81 */     return (that.getId() == null);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractSupplierHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */