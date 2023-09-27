/*     */ package com.aof.model.inventory;
/*     */ 
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ 
/*     */ public abstract class AbstractInventoryTransit
/*     */   implements Serializable {
/*  10 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private WmsPart part;
/*     */   private StorageLocation location;
/*     */   private BigDecimal qty;
/*     */   private String test1;
/*     */   private String test2;
/*     */   private String test3;
/*     */   
/*     */   public Integer getId() {
/*  20 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  24 */     this.id = id;
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/*  28 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  32 */     this.part = part;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/*  36 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/*  40 */     this.location = location;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/*  44 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/*  48 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public String getTest1() {
/*  52 */     return this.test1;
/*     */   }
/*     */   
/*     */   public void setTest1(String test1) {
/*  56 */     this.test1 = test1;
/*     */   }
/*     */   
/*     */   public String getTest2() {
/*  60 */     return this.test2;
/*     */   }
/*     */   
/*     */   public void setTest2(String test2) {
/*  64 */     this.test2 = test2;
/*     */   }
/*     */   
/*     */   public String getTest3() {
/*  68 */     return this.test3;
/*     */   }
/*     */   
/*     */   public void setTest3(String test3) {
/*  72 */     this.test3 = test3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInventoryTransit() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInventoryTransit(Integer id) {
/*  89 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 100 */     if (rhs == null)
/* 101 */       return false; 
/* 102 */     if (this == rhs)
/* 103 */       return true; 
/* 104 */     if (!(rhs instanceof InventoryTransit))
/* 105 */       return false; 
/* 106 */     InventoryTransit that = (InventoryTransit)rhs;
/* 107 */     if (getId() != null)
/* 108 */       return getId().equals(that.getId()); 
/* 109 */     return (that.getId() == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 120 */     if (this.hashValue == 0) {
/* 121 */       int result = 17;
/* 122 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 123 */       result = result * 37 + poIdValue;
/* 124 */       this.hashValue = result;
/*     */     } 
/* 126 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/inventory/AbstractInventoryTransit.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */