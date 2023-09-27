/*     */ package com.aof.model.product;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractDailyProductPlan
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private String workOrderNo;
/*     */   private String workOrderId;
/*     */   private String part;
/*     */   private String orderType;
/*     */   private String orderAttribute;
/*     */   private String site;
/*     */   private String lineNo;
/*     */   private BigDecimal qty;
/*     */   private Date golineDate;
/*     */   private Date offlineDate;
/*     */   private String procedureCode;
/*     */   private String bomCode;
/*     */   private String shift;
/*     */   private String bomOutFinish;
/*     */   private String domain;
/*     */   private String seqNo;
/*     */   
/*     */   public String getWorkOrderNo() {
/*  32 */     return this.workOrderNo;
/*     */   }
/*     */   
/*     */   public void setWorkOrderNo(String workOrderNo) {
/*  36 */     this.workOrderNo = workOrderNo;
/*     */   }
/*     */   
/*     */   public String getWorkOrderId() {
/*  40 */     return this.workOrderId;
/*     */   }
/*     */   
/*     */   public void setWorkOrderId(String workOrderId) {
/*  44 */     this.workOrderId = workOrderId;
/*     */   }
/*     */   
/*     */   public String getPart() {
/*  48 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(String part) {
/*  52 */     this.part = part;
/*     */   }
/*     */   
/*     */   public String getOrderType() {
/*  56 */     return this.orderType;
/*     */   }
/*     */   
/*     */   public void setOrderType(String orderType) {
/*  60 */     this.orderType = orderType;
/*     */   }
/*     */   
/*     */   public String getOrderAttribute() {
/*  64 */     return this.orderAttribute;
/*     */   }
/*     */   
/*     */   public void setOrderAttribute(String orderAttribute) {
/*  68 */     this.orderAttribute = orderAttribute;
/*     */   }
/*     */   
/*     */   public String getSite() {
/*  72 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(String site) {
/*  76 */     this.site = site;
/*     */   }
/*     */   
/*     */   public String getLineNo() {
/*  80 */     return this.lineNo;
/*     */   }
/*     */   
/*     */   public void setLineNo(String lineNo) {
/*  84 */     this.lineNo = lineNo;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/*  88 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/*  92 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public Date getGolineDate() {
/*  96 */     return this.golineDate;
/*     */   }
/*     */   
/*     */   public void setGolineDate(Date golineDate) {
/* 100 */     this.golineDate = golineDate;
/*     */   }
/*     */   
/*     */   public Date getOfflineDate() {
/* 104 */     return this.offlineDate;
/*     */   }
/*     */   
/*     */   public void setOfflineDate(Date offlineDate) {
/* 108 */     this.offlineDate = offlineDate;
/*     */   }
/*     */   
/*     */   public String getProcedureCode() {
/* 112 */     return this.procedureCode;
/*     */   }
/*     */   
/*     */   public void setProcedureCode(String procedureCode) {
/* 116 */     this.procedureCode = procedureCode;
/*     */   }
/*     */   
/*     */   public String getBomCode() {
/* 120 */     return this.bomCode;
/*     */   }
/*     */   
/*     */   public void setBomCode(String bomCode) {
/* 124 */     this.bomCode = bomCode;
/*     */   }
/*     */   
/*     */   public String getShift() {
/* 128 */     return this.shift;
/*     */   }
/*     */   
/*     */   public void setShift(String shift) {
/* 132 */     this.shift = shift;
/*     */   }
/*     */   
/*     */   public String getBomOutFinish() {
/* 136 */     return this.bomOutFinish;
/*     */   }
/*     */   
/*     */   public void setBomOutFinish(String bomOutFinish) {
/* 140 */     this.bomOutFinish = bomOutFinish;
/*     */   }
/*     */   
/*     */   public String getDomain() {
/* 144 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomain(String domain) {
/* 148 */     this.domain = domain;
/*     */   }
/*     */   
/*     */   public String getSeqNo() {
/* 152 */     return this.seqNo;
/*     */   }
/*     */   
/*     */   public void setSeqNo(String seqNo) {
/* 156 */     this.seqNo = seqNo;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractDailyProductPlan() {}
/*     */   
/*     */   public AbstractDailyProductPlan(Integer id) {
/* 163 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 167 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 171 */     this.id = id;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/AbstractDailyProductPlan.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */