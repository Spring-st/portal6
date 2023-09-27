/*     */ package com.aof.model.inventory;
/*     */ 
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ 
/*     */ public abstract class AbstractInventoryDetial
/*     */   implements Serializable {
/*  10 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private InventoryTotal inventory_total_id;
/*     */   private StorageLocation location;
/*     */   private WmsPart part;
/*     */   private BigDecimal number;
/*     */   private BigDecimal part_qty;
/*     */   
/*     */   public WmsPart getPart() {
/*  19 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  23 */     this.part = part;
/*     */   }
/*     */   
/*     */   public BigDecimal getPart_qty() {
/*  27 */     return this.part_qty;
/*     */   }
/*     */   
/*     */   public void setPart_qty(BigDecimal part_qty) {
/*  31 */     this.part_qty = part_qty;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  35 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  39 */     this.id = id;
/*     */   }
/*     */   
/*     */   public InventoryTotal getInventory_total_id() {
/*  43 */     return this.inventory_total_id;
/*     */   }
/*     */   
/*     */   public void setInventory_total_id(InventoryTotal inventory_total_id) {
/*  47 */     this.inventory_total_id = inventory_total_id;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/*  51 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/*  55 */     this.location = location;
/*     */   }
/*     */   
/*     */   public BigDecimal getNumber() {
/*  59 */     return this.number;
/*     */   }
/*     */   
/*     */   public void setNumber(BigDecimal number) {
/*  63 */     this.number = number;
/*     */   }
/*     */   
/*     */   public int getHashValue() {
/*  67 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/*  71 */     this.hashValue = hashValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInventoryDetial() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInventoryDetial(Integer id) {
/*  83 */     setId(id);
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
/*  94 */     if (rhs == null)
/*  95 */       return false; 
/*  96 */     if (this == rhs)
/*  97 */       return true; 
/*  98 */     if (!(rhs instanceof InventoryDetial))
/*  99 */       return false; 
/* 100 */     InventoryDetial that = (InventoryDetial)rhs;
/* 101 */     if (getId() != null)
/* 102 */       return getId().equals(that.getId()); 
/* 103 */     return (that.getId() == null);
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
/* 114 */     if (this.hashValue == 0) {
/* 115 */       int result = 17;
/* 116 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 117 */       result = result * 37 + poIdValue;
/* 118 */       this.hashValue = result;
/*     */     } 
/* 120 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/inventory/AbstractInventoryDetial.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */