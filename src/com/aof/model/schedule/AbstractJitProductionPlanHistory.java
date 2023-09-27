/*     */ package com.aof.model.schedule;
/*     */ 
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractJitProductionPlanHistory implements Serializable {
/*     */   private Integer id;
/*     */   private EdiProduction productionId;
/*     */   private JitProductionPlan production_plan_id;
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
/*     */   } public AbstractJitProductionPlanHistory() {}
/*     */   public void setId(Integer id) {
/*  36 */     this.id = id;
/*     */   }
/*     */   public AbstractJitProductionPlanHistory(Integer id) {
/*  39 */     this.id = id;
/*     */   } public EdiProduction getProductionId() {
/*  41 */     return this.productionId;
/*     */   }
/*     */   public void setProductionId(EdiProduction productionId) {
/*  44 */     this.productionId = productionId;
/*     */   }
/*     */   public JitProductionPlan getProduction_plan_id() {
/*  47 */     return this.production_plan_id;
/*     */   }
/*     */   public void setProduction_plan_id(JitProductionPlan production_plan_id) {
/*  50 */     this.production_plan_id = production_plan_id;
/*     */   }
/*     */   public WmsPart getFatherPart() {
/*  53 */     return this.fatherPart;
/*     */   }
/*     */   public void setFatherPart(WmsPart fatherPart) {
/*  56 */     this.fatherPart = fatherPart;
/*     */   }
/*     */   public WmsPart getChildPart() {
/*  59 */     return this.childPart;
/*     */   }
/*     */   public void setChildPart(WmsPart childPart) {
/*  62 */     this.childPart = childPart;
/*     */   }
/*     */   public BigDecimal getQty() {
/*  65 */     return this.qty;
/*     */   }
/*     */   public void setQty(BigDecimal qty) {
/*  68 */     this.qty = qty;
/*     */   }
/*     */   public Integer getType() {
/*  71 */     return this.type;
/*     */   }
/*     */   public void setType(Integer type) {
/*  74 */     this.type = type;
/*     */   }
/*     */   public Integer getStatus() {
/*  77 */     return this.status;
/*     */   }
/*     */   public void setStatus(Integer status) {
/*  80 */     this.status = status;
/*     */   }
/*     */   public BigDecimal getCurrentQty() {
/*  83 */     return this.currentQty;
/*     */   }
/*     */   public void setCurrentQty(BigDecimal currentQty) {
/*  86 */     this.currentQty = currentQty;
/*     */   }
/*     */   public BigDecimal getHour2DemandQty() {
/*  89 */     return this.hour2DemandQty;
/*     */   }
/*     */   public void setHour2DemandQty(BigDecimal hour2DemandQty) {
/*  92 */     this.hour2DemandQty = hour2DemandQty;
/*     */   }
/*     */   public BigDecimal getHour4DemandQty() {
/*  95 */     return this.hour4DemandQty;
/*     */   }
/*     */   public void setHour4DemandQty(BigDecimal hour4DemandQty) {
/*  98 */     this.hour4DemandQty = hour4DemandQty;
/*     */   }
/*     */   public BigDecimal getHour6DemandQty() {
/* 101 */     return this.hour6DemandQty;
/*     */   }
/*     */   public void setHour6DemandQty(BigDecimal hour6DemandQty) {
/* 104 */     this.hour6DemandQty = hour6DemandQty;
/*     */   }
/*     */   public BigDecimal getHour8DemandQty() {
/* 107 */     return this.hour8DemandQty;
/*     */   }
/*     */   public void setHour8DemandQty(BigDecimal hour8DemandQty) {
/* 110 */     this.hour8DemandQty = hour8DemandQty;
/*     */   }
/*     */   public BigDecimal getHour10DemandQty() {
/* 113 */     return this.hour10DemandQty;
/*     */   }
/*     */   public void setHour10DemandQty(BigDecimal hour10DemandQty) {
/* 116 */     this.hour10DemandQty = hour10DemandQty;
/*     */   }
/*     */   public BigDecimal getHour12DemandQty() {
/* 119 */     return this.hour12DemandQty;
/*     */   }
/*     */   public void setHour12DemandQty(BigDecimal hour12DemandQty) {
/* 122 */     this.hour12DemandQty = hour12DemandQty;
/*     */   }
/*     */   public BigDecimal getHour14DemandQty() {
/* 125 */     return this.hour14DemandQty;
/*     */   }
/*     */   public void setHour14DemandQty(BigDecimal hour14DemandQty) {
/* 128 */     this.hour14DemandQty = hour14DemandQty;
/*     */   }
/*     */   public BigDecimal getHour16DemandQty() {
/* 131 */     return this.hour16DemandQty;
/*     */   }
/*     */   public void setHour16DemandQty(BigDecimal hour16DemandQty) {
/* 134 */     this.hour16DemandQty = hour16DemandQty;
/*     */   }
/*     */   public BigDecimal getHour18DemandQty() {
/* 137 */     return this.hour18DemandQty;
/*     */   }
/*     */   public void setHour18DemandQty(BigDecimal hour18DemandQty) {
/* 140 */     this.hour18DemandQty = hour18DemandQty;
/*     */   }
/*     */   public BigDecimal getHour20DemandQty() {
/* 143 */     return this.hour20DemandQty;
/*     */   }
/*     */   public void setHour20DemandQty(BigDecimal hour20DemandQty) {
/* 146 */     this.hour20DemandQty = hour20DemandQty;
/*     */   }
/*     */   public BigDecimal getHour22DemandQty() {
/* 149 */     return this.hour22DemandQty;
/*     */   }
/*     */   public void setHour22DemandQty(BigDecimal hour22DemandQty) {
/* 152 */     this.hour22DemandQty = hour22DemandQty;
/*     */   }
/*     */   public BigDecimal getHour24DemandQty() {
/* 155 */     return this.hour24DemandQty;
/*     */   }
/*     */   public void setHour24DemandQty(BigDecimal hour24DemandQty) {
/* 158 */     this.hour24DemandQty = hour24DemandQty;
/*     */   }
/*     */   public Date getDate() {
/* 161 */     return this.date;
/*     */   }
/*     */   public void setDate(Date date) {
/* 164 */     this.date = date;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/AbstractJitProductionPlanHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */