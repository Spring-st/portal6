/*     */ package com.aof.model.plantWarehouse;
/*     */ 
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class AbstractWmsPlanToGoOutSub
/*     */ {
/*  11 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private WmsPlanToGoOutItem unplanned_outbound_detial_id;
/*     */   private StorageLocation location;
/*     */   private Box box_id;
/*     */   private BigDecimal qty;
/*     */   private YesNo is_sync;
/*     */   private Date is_sync_date;
/*     */   
/*     */   public int getHashValue() {
/*  21 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/*  25 */     this.hashValue = hashValue;
/*     */   }
/*     */   
/*     */   public WmsPlanToGoOutItem getUnplanned_outbound_detial_id() {
/*  29 */     return this.unplanned_outbound_detial_id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUnplanned_outbound_detial_id(WmsPlanToGoOutItem unplanned_outbound_detial_id) {
/*  34 */     this.unplanned_outbound_detial_id = unplanned_outbound_detial_id;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/*  38 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/*  42 */     this.location = location;
/*     */   }
/*     */   
/*     */   public Box getBox_id() {
/*  46 */     return this.box_id;
/*     */   }
/*     */   
/*     */   public void setBox_id(Box box_id) {
/*  50 */     this.box_id = box_id;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/*  54 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/*  58 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public YesNo getIs_sync() {
/*  62 */     return this.is_sync;
/*     */   }
/*     */   
/*     */   public void setIs_sync(YesNo is_sync) {
/*  66 */     this.is_sync = is_sync;
/*     */   }
/*     */   
/*     */   public Date getIs_sync_date() {
/*  70 */     return this.is_sync_date;
/*     */   }
/*     */   
/*     */   public void setIs_sync_date(Date is_sync_date) {
/*  74 */     this.is_sync_date = is_sync_date;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractWmsPlanToGoOutSub() {}
/*     */   
/*     */   public AbstractWmsPlanToGoOutSub(Integer id) {
/*  81 */     setId(id);
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  85 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  89 */     this.id = id;
/*     */   }
/*     */   
/*     */   public boolean equals(Object rhs) {
/*  93 */     if (rhs == null)
/*  94 */       return false; 
/*  95 */     if (this == rhs)
/*  96 */       return true; 
/*  97 */     if (!(rhs instanceof WmsPlanToGoOutSub))
/*  98 */       return false; 
/*  99 */     WmsPlanToGoOutSub that = (WmsPlanToGoOutSub)rhs;
/* 100 */     if (getId() != null)
/* 101 */       return getId().equals(that.getId()); 
/* 102 */     return (that.getId() == null);
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
/* 113 */     if (this.hashValue == 0) {
/* 114 */       int result = 17;
/* 115 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 116 */       result = result * 37 + poIdValue;
/* 117 */       this.hashValue = result;
/*     */     } 
/* 119 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/plantWarehouse/AbstractWmsPlanToGoOutSub.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */