/*     */ package com.aof.model.comprehensive;
/*     */ 
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractStockingRecord
/*     */   implements Serializable
/*     */ {
/*  29 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   
/*     */   private Stocking stocking_id;
/*     */   
/*     */   private StorageLocation location;
/*     */   private BigDecimal inventory_qty;
/*     */   private BigDecimal stocking_qty;
/*     */   private BigDecimal differences;
/*     */   private Box box;
/*     */   private Date createDate;
/*     */   private YesNo is_sync;
/*     */   private Date is_sync_date;
/*     */   private Integer type;
/*     */   private Integer status;
/*     */   
/*     */   public Integer getStatus() {
/*  47 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(Integer status) {
/*  51 */     this.status = status;
/*     */   }
/*     */   
/*     */   public Integer getType() {
/*  55 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(Integer type) {
/*  59 */     this.type = type;
/*     */   }
/*     */   
/*     */   public YesNo getIs_sync() {
/*  63 */     return this.is_sync;
/*     */   }
/*     */   
/*     */   public void setIs_sync(YesNo is_sync) {
/*  67 */     this.is_sync = is_sync;
/*     */   }
/*     */   
/*     */   public Date getIs_sync_date() {
/*  71 */     return this.is_sync_date;
/*     */   }
/*     */   
/*     */   public void setIs_sync_date(Date is_sync_date) {
/*  75 */     this.is_sync_date = is_sync_date;
/*     */   }
/*     */   
/*     */   public Date getCreateDate() {
/*  79 */     return this.createDate;
/*     */   }
/*     */   
/*     */   public void setCreateDate(Date createDate) {
/*  83 */     this.createDate = createDate;
/*     */   }
/*     */   
/*     */   public Stocking getStocking_id() {
/*  87 */     return this.stocking_id;
/*     */   }
/*     */   
/*     */   public void setStocking_id(Stocking stocking_id) {
/*  91 */     this.stocking_id = stocking_id;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/*  95 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/*  99 */     this.location = location;
/*     */   }
/*     */   
/*     */   public BigDecimal getInventory_qty() {
/* 103 */     return this.inventory_qty;
/*     */   }
/*     */   
/*     */   public void setInventory_qty(BigDecimal inventory_qty) {
/* 107 */     this.inventory_qty = inventory_qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getStocking_qty() {
/* 111 */     return this.stocking_qty;
/*     */   }
/*     */   
/*     */   public void setStocking_qty(BigDecimal stocking_qty) {
/* 115 */     this.stocking_qty = stocking_qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getDifferences() {
/* 119 */     return this.differences;
/*     */   }
/*     */   
/*     */   public void setDifferences(BigDecimal differences) {
/* 123 */     this.differences = differences;
/*     */   }
/*     */   
/*     */   public Box getBox() {
/* 127 */     return this.box;
/*     */   }
/*     */   
/*     */   public void setBox(Box box) {
/* 131 */     this.box = box;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractStockingRecord() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractStockingRecord(Integer id) {
/* 146 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/* 155 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/* 164 */     this.hashValue = 0;
/* 165 */     this.id = id;
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
/* 176 */     if (rhs == null)
/* 177 */       return false; 
/* 178 */     if (this == rhs)
/* 179 */       return true; 
/* 180 */     if (!(rhs instanceof StockingRecord))
/* 181 */       return false; 
/* 182 */     StockingRecord that = (StockingRecord)rhs;
/* 183 */     if (getId() != null)
/* 184 */       return getId().equals(that.getId()); 
/* 185 */     return (that.getId() == null);
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
/* 196 */     if (this.hashValue == 0) {
/* 197 */       int result = 17;
/* 198 */       int cityIdValue = (getId() == null) ? 0 : getId()
/* 199 */         .hashCode();
/* 200 */       result = result * 37 + cityIdValue;
/* 201 */       this.hashValue = result;
/*     */     } 
/* 203 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/AbstractStockingRecord.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */