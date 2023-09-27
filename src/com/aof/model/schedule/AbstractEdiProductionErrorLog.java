/*     */ package com.aof.model.schedule;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractEdiProductionErrorLog implements Serializable {
/*     */   private Integer id;
/*     */   private String asnNo;
/*     */   private String number;
/*     */   private String models;
/*     */   private String describe;
/*     */   private String productlinecode;
/*     */   private String shiftcode;
/*     */   private String staffcode;
/*     */   private Date taskDate;
/*     */   private String time;
/*     */   private Integer qty;
/*     */   private Date syncTime;
/*     */   private Integer type;
/*     */   private String unit;
/*     */   private String errorInfo;
/*     */   
/*     */   public AbstractEdiProductionErrorLog() {}
/*     */   
/*     */   public AbstractEdiProductionErrorLog(Integer id) {
/*  26 */     this.id = id;
/*     */   }
/*     */   public Integer getId() {
/*  29 */     return this.id;
/*     */   }
/*     */   public void setId(Integer id) {
/*  32 */     this.id = id;
/*     */   }
/*     */   public String getAsnNo() {
/*  35 */     return this.asnNo;
/*     */   }
/*     */   public void setAsnNo(String asnNo) {
/*  38 */     this.asnNo = asnNo;
/*     */   }
/*     */   public String getNumber() {
/*  41 */     return this.number;
/*     */   }
/*     */   public void setNumber(String number) {
/*  44 */     this.number = number;
/*     */   }
/*     */   public String getModels() {
/*  47 */     return this.models;
/*     */   }
/*     */   public void setModels(String models) {
/*  50 */     this.models = models;
/*     */   }
/*     */   public String getDescribe() {
/*  53 */     return this.describe;
/*     */   }
/*     */   public void setDescribe(String describe) {
/*  56 */     this.describe = describe;
/*     */   }
/*     */   public String getProductlinecode() {
/*  59 */     return this.productlinecode;
/*     */   }
/*     */   public void setProductlinecode(String productlinecode) {
/*  62 */     this.productlinecode = productlinecode;
/*     */   }
/*     */   public String getShiftcode() {
/*  65 */     return this.shiftcode;
/*     */   }
/*     */   public void setShiftcode(String shiftcode) {
/*  68 */     this.shiftcode = shiftcode;
/*     */   }
/*     */   public String getStaffcode() {
/*  71 */     return this.staffcode;
/*     */   }
/*     */   public void setStaffcode(String staffcode) {
/*  74 */     this.staffcode = staffcode;
/*     */   }
/*     */   public Date getTaskDate() {
/*  77 */     return this.taskDate;
/*     */   }
/*     */   public void setTaskDate(Date taskDate) {
/*  80 */     this.taskDate = taskDate;
/*     */   }
/*     */   public String getTime() {
/*  83 */     return this.time;
/*     */   }
/*     */   public void setTime(String time) {
/*  86 */     this.time = time;
/*     */   }
/*     */   public Integer getQty() {
/*  89 */     return this.qty;
/*     */   }
/*     */   public void setQty(Integer qty) {
/*  92 */     this.qty = qty;
/*     */   }
/*     */   public Date getSyncTime() {
/*  95 */     return this.syncTime;
/*     */   }
/*     */   public void setSyncTime(Date syncTime) {
/*  98 */     this.syncTime = syncTime;
/*     */   }
/*     */   public Integer getType() {
/* 101 */     return this.type;
/*     */   }
/*     */   public void setType(Integer type) {
/* 104 */     this.type = type;
/*     */   }
/*     */   public String getUnit() {
/* 107 */     return this.unit;
/*     */   }
/*     */   public void setUnit(String unit) {
/* 110 */     this.unit = unit;
/*     */   }
/*     */   public String getErrorInfo() {
/* 113 */     return this.errorInfo;
/*     */   }
/*     */   public void setErrorInfo(String errorInfo) {
/* 116 */     this.errorInfo = errorInfo;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/AbstractEdiProductionErrorLog.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */