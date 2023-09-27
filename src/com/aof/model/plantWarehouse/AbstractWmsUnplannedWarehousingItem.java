/*     */ package com.aof.model.plantWarehouse;
/*     */ 
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class AbstractWmsUnplannedWarehousingItem
/*     */ {
/*  11 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private WmsUnplannedWarehousing unplanned_warehousing_id;
/*     */   private StorageLocation location;
/*     */   private Box box_id;
/*     */   private BigDecimal qty;
/*     */   private BigDecimal actual_qty;
/*     */   private YesNo status;
/*     */   private YesNo is_sync;
/*     */   private Date is_sync_date;
/*     */   private String remark;
/*     */   
/*     */   public int getHashValue() {
/*  24 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/*  28 */     this.hashValue = hashValue;
/*     */   }
/*     */   
/*     */   public WmsUnplannedWarehousing getUnplanned_warehousing_id() {
/*  32 */     return this.unplanned_warehousing_id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUnplanned_warehousing_id(WmsUnplannedWarehousing unplanned_warehousing_id) {
/*  37 */     this.unplanned_warehousing_id = unplanned_warehousing_id;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/*  41 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/*  45 */     this.location = location;
/*     */   }
/*     */   
/*     */   public Box getBox_id() {
/*  49 */     return this.box_id;
/*     */   }
/*     */   
/*     */   public void setBox_id(Box box_id) {
/*  53 */     this.box_id = box_id;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/*  57 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/*  61 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getActual_qty() {
/*  65 */     return this.actual_qty;
/*     */   }
/*     */   
/*     */   public void setActual_qty(BigDecimal actual_qty) {
/*  69 */     this.actual_qty = actual_qty;
/*     */   }
/*     */   
/*     */   public YesNo getStatus() {
/*  73 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(YesNo status) {
/*  77 */     this.status = status;
/*     */   }
/*     */   
/*     */   public YesNo getIs_sync() {
/*  81 */     return this.is_sync;
/*     */   }
/*     */   
/*     */   public void setIs_sync(YesNo is_sync) {
/*  85 */     this.is_sync = is_sync;
/*     */   }
/*     */   
/*     */   public Date getIs_sync_date() {
/*  89 */     return this.is_sync_date;
/*     */   }
/*     */   
/*     */   public void setIs_sync_date(Date is_sync_date) {
/*  93 */     this.is_sync_date = is_sync_date;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/*  97 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 101 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractWmsUnplannedWarehousingItem() {}
/*     */   
/*     */   public AbstractWmsUnplannedWarehousingItem(Integer id) {
/* 108 */     setId(id);
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 112 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 116 */     this.id = id;
/*     */   }
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 120 */     if (rhs == null)
/* 121 */       return false; 
/* 122 */     if (this == rhs)
/* 123 */       return true; 
/* 124 */     if (!(rhs instanceof WmsUnplannedWarehousingItem))
/* 125 */       return false; 
/* 126 */     WmsUnplannedWarehousingItem that = (WmsUnplannedWarehousingItem)rhs;
/* 127 */     if (getId() != null)
/* 128 */       return getId().equals(that.getId()); 
/* 129 */     return (that.getId() == null);
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
/* 140 */     if (this.hashValue == 0) {
/* 141 */       int result = 17;
/* 142 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 143 */       result = result * 37 + poIdValue;
/* 144 */       this.hashValue = result;
/*     */     } 
/* 146 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/plantWarehouse/AbstractWmsUnplannedWarehousingItem.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */