/*    */ package com.aof.model.basic;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public abstract class AbstractBasicPartLocation implements Serializable {
/*  7 */   private int hashValue = 0;
/*    */   
/*    */   private Integer id;
/*    */   private WmsPart part;
/*    */   private StorageLocation location;
/*    */   private BigDecimal amount;
/*    */   
/*    */   public BigDecimal getAmount() {
/* 15 */     return this.amount;
/*    */   }
/*    */   
/*    */   public void setAmount(BigDecimal amount) {
/* 19 */     this.amount = amount;
/*    */   }
/*    */   
/*    */   public Integer getId() {
/* 23 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Integer id) {
/* 27 */     this.id = id;
/*    */   }
/*    */   
/*    */   public WmsPart getPart() {
/* 31 */     return this.part;
/*    */   }
/*    */   
/*    */   public void setPart(WmsPart part) {
/* 35 */     this.part = part;
/*    */   }
/*    */   
/*    */   public StorageLocation getLocation() {
/* 39 */     return this.location;
/*    */   }
/*    */   
/*    */   public void setLocation(StorageLocation location) {
/* 43 */     this.location = location;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractBasicPartLocation() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractBasicPartLocation(Integer id) {
/* 55 */     setId(id);
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
/* 66 */     if (rhs == null)
/* 67 */       return false; 
/* 68 */     if (this == rhs)
/* 69 */       return true; 
/* 70 */     if (!(rhs instanceof BasicPartLocation))
/* 71 */       return false; 
/* 72 */     BasicPartLocation that = (BasicPartLocation)rhs;
/* 73 */     if (getId() != null)
/* 74 */       return getId().equals(that.getId()); 
/* 75 */     return (that.getId() == null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 86 */     if (this.hashValue == 0) {
/* 87 */       int result = 17;
/* 88 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 89 */       result = result * 37 + poIdValue;
/* 90 */       this.hashValue = result;
/*    */     } 
/* 92 */     return this.hashValue;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractBasicPartLocation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */