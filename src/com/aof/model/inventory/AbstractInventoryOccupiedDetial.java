/*     */ package com.aof.model.inventory;
/*     */ 
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ 
/*     */ public abstract class AbstractInventoryOccupiedDetial
/*     */   implements Serializable {
/*  10 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private StorageLocation location;
/*     */   private WmsPart part;
/*     */   private BigDecimal number;
/*     */   
/*     */   public WmsPart getPart() {
/*  17 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  21 */     this.part = part;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  25 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  29 */     this.id = id;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/*  33 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/*  37 */     this.location = location;
/*     */   }
/*     */   
/*     */   public BigDecimal getNumber() {
/*  41 */     return this.number;
/*     */   }
/*     */   
/*     */   public void setNumber(BigDecimal number) {
/*  45 */     this.number = number;
/*     */   }
/*     */   
/*     */   public int getHashValue() {
/*  49 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/*  53 */     this.hashValue = hashValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInventoryOccupiedDetial() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInventoryOccupiedDetial(Integer id) {
/*  65 */     setId(id);
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
/*  76 */     if (rhs == null)
/*  77 */       return false; 
/*  78 */     if (this == rhs)
/*  79 */       return true; 
/*  80 */     if (!(rhs instanceof InventoryDetial))
/*  81 */       return false; 
/*  82 */     InventoryDetial that = (InventoryDetial)rhs;
/*  83 */     if (getId() != null)
/*  84 */       return getId().equals(that.getId()); 
/*  85 */     return (that.getId() == null);
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
/*  96 */     if (this.hashValue == 0) {
/*  97 */       int result = 17;
/*  98 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/*  99 */       result = result * 37 + poIdValue;
/* 100 */       this.hashValue = result;
/*     */     } 
/* 102 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/inventory/AbstractInventoryOccupiedDetial.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */