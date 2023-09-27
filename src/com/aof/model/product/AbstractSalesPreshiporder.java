/*     */ package com.aof.model.product;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.SalesPreshiporderStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSalesPreshiporder
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private Site site;
/*     */   private String code;
/*     */   private Date createDate;
/*     */   private String remark;
/*     */   private User requestorId;
/*     */   private YesNo isPrint;
/*     */   private SalesPreshiporderStatus status;
/*     */   private Date arrivaldate;
/*     */   private EnabledDisabled isEnabled;
/*     */   private String customerName;
/*     */   private String customerCode;
/*     */   private YesNo shPrint;
/*     */   private Integer type;
/*     */   private Date shPrintDate;
/*     */   private YesNo matchStatus;
/*     */   
/*     */   public Integer getType() {
/*  34 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(Integer type) {
/*  38 */     this.type = type;
/*     */   }
/*     */   
/*     */   public YesNo getShPrint() {
/*  42 */     return this.shPrint;
/*     */   }
/*     */   
/*     */   public void setShPrint(YesNo shPrint) {
/*  46 */     this.shPrint = shPrint;
/*     */   }
/*     */   
/*     */   public String getCustomerName() {
/*  50 */     return this.customerName;
/*     */   }
/*     */   
/*     */   public void setCustomerName(String customerName) {
/*  54 */     this.customerName = customerName;
/*     */   }
/*     */   
/*     */   public String getCustomerCode() {
/*  58 */     return this.customerCode;
/*     */   }
/*     */   
/*     */   public void setCustomerCode(String customerCode) {
/*  62 */     this.customerCode = customerCode;
/*     */   }
/*     */   
/*     */   public SalesPreshiporderStatus getStatus() {
/*  66 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(SalesPreshiporderStatus status) {
/*  70 */     this.status = status;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/*  74 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/*  78 */     this.site = site;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  82 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  86 */     this.code = code;
/*     */   }
/*     */   
/*     */   public Date getCreateDate() {
/*  90 */     return this.createDate;
/*     */   }
/*     */   
/*     */   public void setCreateDate(Date createDate) {
/*  94 */     this.createDate = createDate;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/*  98 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 102 */     this.remark = remark;
/*     */   }
/*     */   
/*     */   public User getRequestorId() {
/* 106 */     return this.requestorId;
/*     */   }
/*     */   
/*     */   public void setRequestorId(User requestorId) {
/* 110 */     this.requestorId = requestorId;
/*     */   }
/*     */   
/*     */   public YesNo getIsPrint() {
/* 114 */     return this.isPrint;
/*     */   }
/*     */   
/*     */   public void setIsPrint(YesNo isPrint) {
/* 118 */     this.isPrint = isPrint;
/*     */   }
/*     */   
/*     */   public Date getArrivaldate() {
/* 122 */     return this.arrivaldate;
/*     */   }
/*     */   
/*     */   public void setArrivaldate(Date arrivaldate) {
/* 126 */     this.arrivaldate = arrivaldate;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getIsEnabled() {
/* 130 */     return this.isEnabled;
/*     */   }
/*     */   
/*     */   public void setIsEnabled(EnabledDisabled isEnabled) {
/* 134 */     this.isEnabled = isEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractSalesPreshiporder() {}
/*     */   
/*     */   public AbstractSalesPreshiporder(Integer id) {
/* 141 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 145 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 149 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Date getShPrintDate() {
/* 153 */     return this.shPrintDate;
/*     */   }
/*     */   
/*     */   public void setShPrintDate(Date shPrintDate) {
/* 157 */     this.shPrintDate = shPrintDate;
/*     */   }
/*     */   
/*     */   public YesNo getMatchStatus() {
/* 161 */     return this.matchStatus;
/*     */   }
/*     */   
/*     */   public void setMatchStatus(YesNo matchStatus) {
/* 165 */     this.matchStatus = matchStatus;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/AbstractSalesPreshiporder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */