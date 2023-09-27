/*     */ package com.aof.model.schedule;
/*     */ 
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractProjectedInventory
/*     */   implements Serializable {
/*     */   private Integer id;
/*     */   private WmsPart part;
/*     */   private String location;
/*     */   private BigDecimal currentQty;
/*     */   private BigDecimal hour2Qty;
/*     */   private BigDecimal hour4Qty;
/*     */   private BigDecimal hour6Qty;
/*     */   private BigDecimal hour8Qty;
/*     */   private BigDecimal hour10Qty;
/*     */   private BigDecimal hour12Qty;
/*     */   private BigDecimal hour14Qty;
/*     */   private BigDecimal hour16Qty;
/*     */   private BigDecimal hour18Qty;
/*     */   private BigDecimal hour20Qty;
/*     */   private BigDecimal hour22Qty;
/*     */   private BigDecimal hour24Qty;
/*     */   private Date syncDate;
/*     */   private Date createDate;
/*     */   
/*     */   public Integer getId() {
/*  30 */     return this.id;
/*     */   } public AbstractProjectedInventory() {}
/*     */   public void setId(Integer id) {
/*  33 */     this.id = id;
/*     */   }
/*     */   public AbstractProjectedInventory(Integer id) {
/*  36 */     this.id = id;
/*     */   } public WmsPart getPart() {
/*  38 */     return this.part;
/*     */   }
/*     */   public void setPart(WmsPart part) {
/*  41 */     this.part = part;
/*     */   }
/*     */   public String getLocation() {
/*  44 */     return this.location;
/*     */   }
/*     */   public void setLocation(String location) {
/*  47 */     this.location = location;
/*     */   }
/*     */   public BigDecimal getCurrentQty() {
/*  50 */     return this.currentQty;
/*     */   }
/*     */   public void setCurrentQty(BigDecimal currentQty) {
/*  53 */     this.currentQty = currentQty;
/*     */   }
/*     */   public BigDecimal getHour2Qty() {
/*  56 */     return this.hour2Qty;
/*     */   }
/*     */   public void setHour2Qty(BigDecimal hour2Qty) {
/*  59 */     this.hour2Qty = hour2Qty;
/*     */   }
/*     */   public BigDecimal getHour4Qty() {
/*  62 */     return this.hour4Qty;
/*     */   }
/*     */   public void setHour4Qty(BigDecimal hour4Qty) {
/*  65 */     this.hour4Qty = hour4Qty;
/*     */   }
/*     */   public BigDecimal getHour6Qty() {
/*  68 */     return this.hour6Qty;
/*     */   }
/*     */   public void setHour6Qty(BigDecimal hour6Qty) {
/*  71 */     this.hour6Qty = hour6Qty;
/*     */   }
/*     */   public BigDecimal getHour8Qty() {
/*  74 */     return this.hour8Qty;
/*     */   }
/*     */   public void setHour8Qty(BigDecimal hour8Qty) {
/*  77 */     this.hour8Qty = hour8Qty;
/*     */   }
/*     */   public BigDecimal getHour10Qty() {
/*  80 */     return this.hour10Qty;
/*     */   }
/*     */   public void setHour10Qty(BigDecimal hour10Qty) {
/*  83 */     this.hour10Qty = hour10Qty;
/*     */   }
/*     */   public BigDecimal getHour12Qty() {
/*  86 */     return this.hour12Qty;
/*     */   }
/*     */   public void setHour12Qty(BigDecimal hour12Qty) {
/*  89 */     this.hour12Qty = hour12Qty;
/*     */   }
/*     */   public BigDecimal getHour14Qty() {
/*  92 */     return this.hour14Qty;
/*     */   }
/*     */   public void setHour14Qty(BigDecimal hour14Qty) {
/*  95 */     this.hour14Qty = hour14Qty;
/*     */   }
/*     */   public BigDecimal getHour16Qty() {
/*  98 */     return this.hour16Qty;
/*     */   }
/*     */   public void setHour16Qty(BigDecimal hour16Qty) {
/* 101 */     this.hour16Qty = hour16Qty;
/*     */   }
/*     */   public BigDecimal getHour18Qty() {
/* 104 */     return this.hour18Qty;
/*     */   }
/*     */   public void setHour18Qty(BigDecimal hour18Qty) {
/* 107 */     this.hour18Qty = hour18Qty;
/*     */   }
/*     */   public BigDecimal getHour20Qty() {
/* 110 */     return this.hour20Qty;
/*     */   }
/*     */   public void setHour20Qty(BigDecimal hour20Qty) {
/* 113 */     this.hour20Qty = hour20Qty;
/*     */   }
/*     */   public BigDecimal getHour22Qty() {
/* 116 */     return this.hour22Qty;
/*     */   }
/*     */   public void setHour22Qty(BigDecimal hour22Qty) {
/* 119 */     this.hour22Qty = hour22Qty;
/*     */   }
/*     */   public BigDecimal getHour24Qty() {
/* 122 */     return this.hour24Qty;
/*     */   }
/*     */   public void setHour24Qty(BigDecimal hour24Qty) {
/* 125 */     this.hour24Qty = hour24Qty;
/*     */   }
/*     */   public Date getSyncDate() {
/* 128 */     return this.syncDate;
/*     */   }
/*     */   public void setSyncDate(Date syncDate) {
/* 131 */     this.syncDate = syncDate;
/*     */   }
/*     */   public Date getCreateDate() {
/* 134 */     return this.createDate;
/*     */   }
/*     */   public void setCreateDate(Date createDate) {
/* 137 */     this.createDate = createDate;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/AbstractProjectedInventory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */