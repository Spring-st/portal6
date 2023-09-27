/*     */ package com.aof.model.comprehensive;
/*     */ 
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
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
/*     */ public abstract class AbstractStockingDetial
/*     */   implements Serializable
/*     */ {
/*  22 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   private Stocking stocking;
/*     */   private StorageLocation location;
/*     */   private WmsPart part;
/*     */   private BigDecimal plan_sum_qty;
/*     */   private BigDecimal actual_sum_qty;
/*     */   private Integer plan_num;
/*     */   private Integer actual_num;
/*     */   
/*     */   public int getHashValue() {
/*  34 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/*  38 */     this.hashValue = hashValue;
/*     */   }
/*     */   
/*     */   public Stocking getStocking() {
/*  42 */     return this.stocking;
/*     */   }
/*     */   
/*     */   public void setStocking(Stocking stocking) {
/*  46 */     this.stocking = stocking;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/*  50 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/*  54 */     this.location = location;
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/*  58 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  62 */     this.part = part;
/*     */   }
/*     */   
/*     */   public BigDecimal getPlan_sum_qty() {
/*  66 */     return this.plan_sum_qty;
/*     */   }
/*     */   
/*     */   public void setPlan_sum_qty(BigDecimal plan_sum_qty) {
/*  70 */     this.plan_sum_qty = plan_sum_qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getActual_sum_qty() {
/*  74 */     return this.actual_sum_qty;
/*     */   }
/*     */   
/*     */   public void setActual_sum_qty(BigDecimal actual_sum_qty) {
/*  78 */     this.actual_sum_qty = actual_sum_qty;
/*     */   }
/*     */   
/*     */   public Integer getPlan_num() {
/*  82 */     return this.plan_num;
/*     */   }
/*     */   
/*     */   public void setPlan_num(Integer plan_num) {
/*  86 */     this.plan_num = plan_num;
/*     */   }
/*     */   
/*     */   public Integer getActual_num() {
/*  90 */     return this.actual_num;
/*     */   }
/*     */   
/*     */   public void setActual_num(Integer actual_num) {
/*  94 */     this.actual_num = actual_num;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractStockingDetial() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractStockingDetial(Integer id) {
/* 109 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/* 118 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/* 127 */     this.hashValue = 0;
/* 128 */     this.id = id;
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
/* 139 */     if (rhs == null)
/* 140 */       return false; 
/* 141 */     if (this == rhs)
/* 142 */       return true; 
/* 143 */     if (!(rhs instanceof StockingDetial))
/* 144 */       return false; 
/* 145 */     StockingDetial that = (StockingDetial)rhs;
/* 146 */     if (getId() != null)
/* 147 */       return getId().equals(that.getId()); 
/* 148 */     return (that.getId() == null);
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
/* 159 */     if (this.hashValue == 0) {
/* 160 */       int result = 17;
/* 161 */       int cityIdValue = (getId() == null) ? 0 : getId()
/* 162 */         .hashCode();
/* 163 */       result = result * 37 + cityIdValue;
/* 164 */       this.hashValue = result;
/*     */     } 
/* 166 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/AbstractStockingDetial.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */