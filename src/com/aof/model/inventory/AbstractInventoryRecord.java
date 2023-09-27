/*     */ package com.aof.model.inventory;
/*     */ 
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.metadata.InventoryRecordType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.WmsLot;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractInventoryRecord
/*     */   implements Serializable {
/*  13 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private InventoryDetial inventory_detial_id;
/*     */   private StorageLocation location;
/*     */   private WmsLot lot;
/*     */   private BigDecimal qty;
/*     */   private Date date;
/*     */   private InventoryRecordType type;
/*     */   private YesNo status;
/*     */   private BigDecimal location_init_number;
/*     */   private BigDecimal part_init_number;
/*     */   private String part;
/*     */   
/*     */   public String getPart() {
/*  27 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(String part) {
/*  31 */     this.part = part;
/*     */   }
/*     */   
/*     */   public WmsLot getLot() {
/*  35 */     return this.lot;
/*     */   }
/*     */   
/*     */   public void setLot(WmsLot lot) {
/*  39 */     this.lot = lot;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  43 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  47 */     this.id = id;
/*     */   }
/*     */   
/*     */   public InventoryDetial getInventory_detial_id() {
/*  51 */     return this.inventory_detial_id;
/*     */   }
/*     */   
/*     */   public void setInventory_detial_id(InventoryDetial inventory_detial_id) {
/*  55 */     this.inventory_detial_id = inventory_detial_id;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/*  59 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/*  63 */     this.location = location;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/*  67 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/*  71 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  75 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  79 */     this.date = date;
/*     */   }
/*     */   
/*     */   public InventoryRecordType getType() {
/*  83 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(InventoryRecordType type) {
/*  87 */     this.type = type;
/*     */   }
/*     */   
/*     */   public YesNo getStatus() {
/*  91 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(YesNo status) {
/*  95 */     this.status = status;
/*     */   }
/*     */   
/*     */   public BigDecimal getLocation_init_number() {
/*  99 */     return this.location_init_number;
/*     */   }
/*     */   
/*     */   public void setLocation_init_number(BigDecimal location_init_number) {
/* 103 */     this.location_init_number = location_init_number;
/*     */   }
/*     */   
/*     */   public BigDecimal getPart_init_number() {
/* 107 */     return this.part_init_number;
/*     */   }
/*     */   
/*     */   public void setPart_init_number(BigDecimal part_init_number) {
/* 111 */     this.part_init_number = part_init_number;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInventoryRecord() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractInventoryRecord(Integer id) {
/* 123 */     setId(id);
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
/* 134 */     if (rhs == null)
/* 135 */       return false; 
/* 136 */     if (this == rhs)
/* 137 */       return true; 
/* 138 */     if (!(rhs instanceof InventoryRecord))
/* 139 */       return false; 
/* 140 */     InventoryRecord that = (InventoryRecord)rhs;
/* 141 */     if (getId() != null)
/* 142 */       return getId().equals(that.getId()); 
/* 143 */     return (that.getId() == null);
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
/* 154 */     if (this.hashValue == 0) {
/* 155 */       int result = 17;
/* 156 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 157 */       result = result * 37 + poIdValue;
/* 158 */       this.hashValue = result;
/*     */     } 
/* 160 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/inventory/AbstractInventoryRecord.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */