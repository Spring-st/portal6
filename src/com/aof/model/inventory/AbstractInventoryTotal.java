/*    */ package com.aof.model.inventory;
/*    */ 
/*    */ import com.aof.model.basic.WmsPart;
/*    */ import java.io.Serializable;
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public abstract class AbstractInventoryTotal
/*    */   implements Serializable {
/*  9 */   private int hashValue = 0;
/*    */   private Integer id;
/*    */   private WmsPart part;
/*    */   private BigDecimal number;
/*    */   
/*    */   public Integer getId() {
/* 15 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Integer id) {
/* 19 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getHashValue() {
/* 23 */     return this.hashValue;
/*    */   }
/*    */   
/*    */   public void setHashValue(int hashValue) {
/* 27 */     this.hashValue = hashValue;
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
/*    */   public BigDecimal getNumber() {
/* 39 */     return this.number;
/*    */   }
/*    */   
/*    */   public void setNumber(BigDecimal number) {
/* 43 */     this.number = number;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractInventoryTotal() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractInventoryTotal(Integer id) {
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
/* 70 */     if (!(rhs instanceof InventoryTotal))
/* 71 */       return false; 
/* 72 */     InventoryTotal that = (InventoryTotal)rhs;
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


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/inventory/AbstractInventoryTotal.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */