/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import com.aof.model.BaseAttachment;
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
/*    */ public abstract class AbstractSupplierContract
/*    */   extends BaseAttachment
/*    */   implements Serializable
/*    */ {
/*    */   private Supplier supplier;
/*    */   
/*    */   public AbstractSupplierContract() {}
/*    */   
/*    */   public AbstractSupplierContract(Integer id) {
/* 43 */     setId(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Supplier getSupplier() {
/* 50 */     return this.supplier;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSupplier(Supplier supplier) {
/* 58 */     this.supplier = supplier;
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
/* 69 */     if (rhs == null) return false; 
/* 70 */     if (this == rhs) return true; 
/* 71 */     if (!(rhs instanceof SupplierContract)) return false; 
/* 72 */     SupplierContract that = (SupplierContract)rhs;
/* 73 */     if (getId() != null) return getId().equals(that.getId()); 
/* 74 */     return (that.getId() == null);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractSupplierContract.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */