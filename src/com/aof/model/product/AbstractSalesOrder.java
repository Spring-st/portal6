/*     */ package com.aof.model.product;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.metadata.PurchaseOrderStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSalesOrder
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private String soNumber;
/*     */   private Site site;
/*     */   private Date createDate;
/*     */   private BasicCustomer customer;
/*     */   private PurchaseOrderStatus status;
/*     */   private Integer createtype;
/*     */   private String remark;
/*     */   private String describe;
/*     */   private YesNo isprint;
/*     */   private YesNo issync;
/*     */   private String custCode;
/*     */   private String custAddress;
/*     */   private String custName;
/*     */   
/*     */   public BasicCustomer getCustomer() {
/*  31 */     return this.customer;
/*     */   }
/*     */   
/*     */   public void setCustomer(BasicCustomer customer) {
/*  35 */     this.customer = customer;
/*     */   }
/*     */   
/*     */   public String getCustCode() {
/*  39 */     return this.custCode;
/*     */   }
/*     */   
/*     */   public void setCustCode(String custCode) {
/*  43 */     this.custCode = custCode;
/*     */   }
/*     */   
/*     */   public String getCustAddress() {
/*  47 */     return this.custAddress;
/*     */   }
/*     */   
/*     */   public void setCustAddress(String custAddress) {
/*  51 */     this.custAddress = custAddress;
/*     */   }
/*     */   
/*     */   public String getCustName() {
/*  55 */     return this.custName;
/*     */   }
/*     */   
/*     */   public void setCustName(String custName) {
/*  59 */     this.custName = custName;
/*     */   }
/*     */   
/*     */   public String getSoNumber() {
/*  63 */     return this.soNumber;
/*     */   }
/*     */   
/*     */   public void setSoNumber(String soNumber) {
/*  67 */     this.soNumber = soNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/*  73 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/*  77 */     this.site = site;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/*  81 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/*  85 */     this.remark = remark;
/*     */   }
/*     */   
/*     */   public Date getCreateDate() {
/*  89 */     return this.createDate;
/*     */   }
/*     */   
/*     */   public void setCreateDate(Date createDate) {
/*  93 */     this.createDate = createDate;
/*     */   }
/*     */   
/*     */   public String getDescribe() {
/*  97 */     return this.describe;
/*     */   }
/*     */   
/*     */   public void setDescribe(String describe) {
/* 101 */     this.describe = describe;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PurchaseOrderStatus getStatus() {
/* 107 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(PurchaseOrderStatus status) {
/* 111 */     this.status = status;
/*     */   }
/*     */   
/*     */   public YesNo getIsprint() {
/* 115 */     return this.isprint;
/*     */   }
/*     */   
/*     */   public void setIsprint(YesNo isprint) {
/* 119 */     this.isprint = isprint;
/*     */   }
/*     */   
/*     */   public YesNo getIssync() {
/* 123 */     return this.issync;
/*     */   }
/*     */   
/*     */   public void setIssync(YesNo issync) {
/* 127 */     this.issync = issync;
/*     */   }
/*     */   
/*     */   public Integer getCreatetype() {
/* 131 */     return this.createtype;
/*     */   }
/*     */   
/*     */   public void setCreatetype(Integer createtype) {
/* 135 */     this.createtype = createtype;
/*     */   }
/*     */   
/*     */   public AbstractSalesOrder() {}
/*     */   
/*     */   public AbstractSalesOrder(Integer id) {
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
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/AbstractSalesOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */