/*     */ package com.aof.model.schedule;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractEdiProduction implements Serializable {
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
/*     */   private Integer status;
/*     */   private Integer type;
/*     */   private String unit;
/*     */   private String supplier;
/*     */   private String uploader;
/*     */   private Date uploadTime;
/*     */   private String uploadFileName;
/*     */   private Integer handStatus;
/*     */   private Integer enabled;
/*     */   private Date taskTime;
/*     */   
/*     */   public Integer getId() {
/*  31 */     return this.id;
/*     */   }
/*     */   public void setId(Integer id) {
/*  34 */     this.id = id;
/*     */   }
/*     */   
/*     */   public AbstractEdiProduction() {}
/*     */   
/*     */   public AbstractEdiProduction(Integer id) {
/*  40 */     this.id = id;
/*     */   }
/*     */   public String getAsnNo() {
/*  43 */     return this.asnNo;
/*     */   }
/*     */   public void setAsnNo(String asnNo) {
/*  46 */     this.asnNo = asnNo;
/*     */   }
/*     */   public String getNumber() {
/*  49 */     return this.number;
/*     */   }
/*     */   public void setNumber(String number) {
/*  52 */     this.number = number;
/*     */   }
/*     */   public String getModels() {
/*  55 */     return this.models;
/*     */   }
/*     */   public void setModels(String models) {
/*  58 */     this.models = models;
/*     */   }
/*     */   public String getDescribe() {
/*  61 */     return this.describe;
/*     */   }
/*     */   public void setDescribe(String describe) {
/*  64 */     this.describe = describe;
/*     */   }
/*     */   public String getProductlinecode() {
/*  67 */     return this.productlinecode;
/*     */   }
/*     */   public void setProductlinecode(String productlinecode) {
/*  70 */     this.productlinecode = productlinecode;
/*     */   }
/*     */   public String getShiftcode() {
/*  73 */     return this.shiftcode;
/*     */   }
/*     */   public void setShiftcode(String shiftcode) {
/*  76 */     this.shiftcode = shiftcode;
/*     */   }
/*     */   public String getStaffcode() {
/*  79 */     return this.staffcode;
/*     */   }
/*     */   public void setStaffcode(String staffcode) {
/*  82 */     this.staffcode = staffcode;
/*     */   }
/*     */   public Date getTaskDate() {
/*  85 */     return this.taskDate;
/*     */   }
/*     */   public void setTaskDate(Date taskDate) {
/*  88 */     this.taskDate = taskDate;
/*     */   }
/*     */   public String getTime() {
/*  91 */     return this.time;
/*     */   }
/*     */   public void setTime(String time) {
/*  94 */     this.time = time;
/*     */   }
/*     */   public Integer getQty() {
/*  97 */     return this.qty;
/*     */   }
/*     */   public void setQty(Integer qty) {
/* 100 */     this.qty = qty;
/*     */   }
/*     */   public Date getSyncTime() {
/* 103 */     return this.syncTime;
/*     */   }
/*     */   public void setSyncTime(Date syncTime) {
/* 106 */     this.syncTime = syncTime;
/*     */   }
/*     */   public Integer getStatus() {
/* 109 */     return this.status;
/*     */   }
/*     */   public void setStatus(Integer status) {
/* 112 */     this.status = status;
/*     */   }
/*     */   public Integer getType() {
/* 115 */     return this.type;
/*     */   }
/*     */   public void setType(Integer type) {
/* 118 */     this.type = type;
/*     */   }
/*     */   public String getUnit() {
/* 121 */     return this.unit;
/*     */   }
/*     */   public void setUnit(String unit) {
/* 124 */     this.unit = unit;
/*     */   }
/*     */   public String getSupplier() {
/* 127 */     return this.supplier;
/*     */   }
/*     */   public void setSupplier(String supplier) {
/* 130 */     this.supplier = supplier;
/*     */   }
/*     */   public String getUploader() {
/* 133 */     return this.uploader;
/*     */   }
/*     */   public void setUploader(String uploader) {
/* 136 */     this.uploader = uploader;
/*     */   }
/*     */   public Date getUploadTime() {
/* 139 */     return this.uploadTime;
/*     */   }
/*     */   public void setUploadTime(Date uploadTime) {
/* 142 */     this.uploadTime = uploadTime;
/*     */   }
/*     */   public String getUploadFileName() {
/* 145 */     return this.uploadFileName;
/*     */   }
/*     */   public void setUploadFileName(String uploadFileName) {
/* 148 */     this.uploadFileName = uploadFileName;
/*     */   }
/*     */   public Integer getHandStatus() {
/* 151 */     return this.handStatus;
/*     */   }
/*     */   public void setHandStatus(Integer handStatus) {
/* 154 */     this.handStatus = handStatus;
/*     */   }
/*     */   public Integer getEnabled() {
/* 157 */     return this.enabled;
/*     */   }
/*     */   public void setEnabled(Integer enabled) {
/* 160 */     this.enabled = enabled;
/*     */   }
/*     */   public Date getTaskTime() {
/* 163 */     return this.taskTime;
/*     */   }
/*     */   public void setTaskTime(Date taskTime) {
/* 166 */     this.taskTime = taskTime;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/AbstractEdiProduction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */