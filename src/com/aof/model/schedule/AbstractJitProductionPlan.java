/*     */ package com.aof.model.schedule;
/*     */ 
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractJitProductionPlan
/*     */   implements Serializable {
/*     */   private Integer id;
/*     */   private EdiProduction productionId;
/*     */   private WmsPart fatherPart;
/*     */   private WmsPart childPart;
/*     */   private BigDecimal qty;
/*     */   private Integer type;
/*     */   private Integer status;
/*     */   private BigDecimal currentQty;
/*     */   private BigDecimal hour2DemandQty;
/*     */   private BigDecimal hour4DemandQty;
/*     */   private BigDecimal hour6DemandQty;
/*     */   private BigDecimal hour8DemandQty;
/*     */   private BigDecimal hour10DemandQty;
/*     */   private BigDecimal hour12DemandQty;
/*     */   private BigDecimal hour14DemandQty;
/*     */   private BigDecimal hour16DemandQty;
/*     */   private BigDecimal hour18DemandQty;
/*     */   private BigDecimal hour20DemandQty;
/*     */   private BigDecimal hour22DemandQty;
/*     */   private BigDecimal hour24DemandQty;
/*     */   private Date date;
/*     */   
/*     */   public Integer getId() {
/*  33 */     return this.id;
/*     */   } public AbstractJitProductionPlan() {}
/*     */   public void setId(Integer id) {
/*  36 */     this.id = id;
/*     */   }
/*     */   public AbstractJitProductionPlan(Integer id) {
/*  39 */     this.id = id;
/*     */   } public EdiProduction getProductionId() {
/*  41 */     return this.productionId;
/*     */   }
/*     */   public void setProductionId(EdiProduction productionId) {
/*  44 */     this.productionId = productionId;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/*  48 */     return this.qty;
/*     */   }
/*     */   public void setQty(BigDecimal qty) {
/*  51 */     this.qty = qty;
/*     */   }
/*     */   public Integer getType() {
/*  54 */     return this.type;
/*     */   }
/*     */   public void setType(Integer type) {
/*  57 */     this.type = type;
/*     */   }
/*     */   public WmsPart getChildPart() {
/*  60 */     return this.childPart;
/*     */   }
/*     */   public void setChildPart(WmsPart childPart) {
/*  63 */     this.childPart = childPart;
/*     */   }
/*     */   public WmsPart getFatherPart() {
/*  66 */     return this.fatherPart;
/*     */   }
/*     */   public void setFatherPart(WmsPart fatherPart) {
/*  69 */     this.fatherPart = fatherPart;
/*     */   }
/*     */   public Integer getStatus() {
/*  72 */     return this.status;
/*     */   }
/*     */   public void setStatus(Integer status) {
/*  75 */     this.status = status;
/*     */   }
/*     */   public BigDecimal getCurrentQty() {
/*  78 */     return this.currentQty;
/*     */   }
/*     */   public void setCurrentQty(BigDecimal currentQty) {
/*  81 */     this.currentQty = currentQty;
/*     */   }
/*     */   public BigDecimal getHour2DemandQty() {
/*  84 */     return this.hour2DemandQty;
/*     */   }
/*     */   public void setHour2DemandQty(BigDecimal hour2DemandQty) {
/*  87 */     this.hour2DemandQty = hour2DemandQty;
/*     */   }
/*     */   public BigDecimal getHour4DemandQty() {
/*  90 */     return this.hour4DemandQty;
/*     */   }
/*     */   public void setHour4DemandQty(BigDecimal hour4DemandQty) {
/*  93 */     this.hour4DemandQty = hour4DemandQty;
/*     */   }
/*     */   public BigDecimal getHour6DemandQty() {
/*  96 */     return this.hour6DemandQty;
/*     */   }
/*     */   public void setHour6DemandQty(BigDecimal hour6DemandQty) {
/*  99 */     this.hour6DemandQty = hour6DemandQty;
/*     */   }
/*     */   public BigDecimal getHour8DemandQty() {
/* 102 */     return this.hour8DemandQty;
/*     */   }
/*     */   public void setHour8DemandQty(BigDecimal hour8DemandQty) {
/* 105 */     this.hour8DemandQty = hour8DemandQty;
/*     */   }
/*     */   public BigDecimal getHour10DemandQty() {
/* 108 */     return this.hour10DemandQty;
/*     */   }
/*     */   public void setHour10DemandQty(BigDecimal hour10DemandQty) {
/* 111 */     this.hour10DemandQty = hour10DemandQty;
/*     */   }
/*     */   public BigDecimal getHour12DemandQty() {
/* 114 */     return this.hour12DemandQty;
/*     */   }
/*     */   public void setHour12DemandQty(BigDecimal hour12DemandQty) {
/* 117 */     this.hour12DemandQty = hour12DemandQty;
/*     */   }
/*     */   public BigDecimal getHour14DemandQty() {
/* 120 */     return this.hour14DemandQty;
/*     */   }
/*     */   public void setHour14DemandQty(BigDecimal hour14DemandQty) {
/* 123 */     this.hour14DemandQty = hour14DemandQty;
/*     */   }
/*     */   public BigDecimal getHour16DemandQty() {
/* 126 */     return this.hour16DemandQty;
/*     */   }
/*     */   public void setHour16DemandQty(BigDecimal hour16DemandQty) {
/* 129 */     this.hour16DemandQty = hour16DemandQty;
/*     */   }
/*     */   public BigDecimal getHour18DemandQty() {
/* 132 */     return this.hour18DemandQty;
/*     */   }
/*     */   public void setHour18DemandQty(BigDecimal hour18DemandQty) {
/* 135 */     this.hour18DemandQty = hour18DemandQty;
/*     */   }
/*     */   public BigDecimal getHour20DemandQty() {
/* 138 */     return this.hour20DemandQty;
/*     */   }
/*     */   public void setHour20DemandQty(BigDecimal hour20DemandQty) {
/* 141 */     this.hour20DemandQty = hour20DemandQty;
/*     */   }
/*     */   public BigDecimal getHour22DemandQty() {
/* 144 */     return this.hour22DemandQty;
/*     */   }
/*     */   public void setHour22DemandQty(BigDecimal hour22DemandQty) {
/* 147 */     this.hour22DemandQty = hour22DemandQty;
/*     */   }
/*     */   public BigDecimal getHour24DemandQty() {
/* 150 */     return this.hour24DemandQty;
/*     */   }
/*     */   public void setHour24DemandQty(BigDecimal hour24DemandQty) {
/* 153 */     this.hour24DemandQty = hour24DemandQty;
/*     */   }
/*     */   public Date getDate() {
/* 156 */     return this.date;
/*     */   }
/*     */   public void setDate(Date date) {
/* 159 */     this.date = date;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/AbstractJitProductionPlan.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */