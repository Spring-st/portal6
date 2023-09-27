
/*    */ package com.aof.model.basic;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WmsPart
/*    */   extends AbstractWmsPart
/*    */   implements Serializable
/*    */ {
/*    */   private String supplierPartCode;
/*    */   
/*    */   public WmsPart() {}
/*    */   
/*    */   public WmsPart(String id) {
/* 18 */     super(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSupplierPartCode() {
/* 28 */     return this.supplierPartCode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSupplierPartCode(String supplierPartCode) {
/* 36 */     this.supplierPartCode = supplierPartCode;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/WmsPart.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */