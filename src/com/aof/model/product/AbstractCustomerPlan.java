/*     */ package com.aof.model.product;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractCustomerPlan
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private String planNumbers;
/*     */   private String line;
/*     */   private BasicCustomer customer;
/*     */   private WmsPart wmspart;
/*     */   private String um;
/*     */   private BigDecimal qty;
/*     */   private BigDecimal receiptQty;
/*     */   private BigDecimal qtyOpen;
/*     */   private BigDecimal returnQtySum;
/*     */   private Date shipmentDate;
/*     */   private Date entryDate;
/*     */   private String describe;
/*     */   private String poDomain;
/*     */   private Site site;
/*     */   private String factory;
/*     */   private YesNo isprint;
/*     */   private String customerAddress;
/*     */   private BigDecimal unitPrice;
/*     */   private String sotaxc;
/*     */   private String operation;
/*     */   private String curr;
/*     */   private String checked;
/*     */   private Integer status;
/*     */   
/*     */   public String getChecked() {
/*  43 */     return this.checked;
/*     */   }
/*     */   
/*     */   public void setChecked(String checked) {
/*  47 */     this.checked = checked;
/*     */   }
/*     */   
/*     */   public String getCustomerAddress() {
/*  51 */     return this.customerAddress;
/*     */   }
/*     */   
/*     */   public void setCustomerAddress(String customerAddress) {
/*  55 */     this.customerAddress = customerAddress;
/*     */   }
/*     */   
/*     */   public String getPlanNumbers() {
/*  59 */     return this.planNumbers;
/*     */   }
/*     */   
/*     */   public void setPlanNumbers(String planNumbers) {
/*  63 */     this.planNumbers = planNumbers;
/*     */   }
/*     */   
/*     */   public String getLine() {
/*  67 */     return this.line;
/*     */   }
/*     */   
/*     */   public void setLine(String line) {
/*  71 */     this.line = line;
/*     */   }
/*     */   
/*     */   public BasicCustomer getCustomer() {
/*  75 */     return this.customer;
/*     */   }
/*     */   
/*     */   public void setCustomer(BasicCustomer customer) {
/*  79 */     this.customer = customer;
/*     */   }
/*     */   
/*     */   public WmsPart getWmspart() {
/*  83 */     return this.wmspart;
/*     */   }
/*     */   
/*     */   public void setWmspart(WmsPart wmspart) {
/*  87 */     this.wmspart = wmspart;
/*     */   }
/*     */   
/*     */   public String getUm() {
/*  91 */     return this.um;
/*     */   }
/*     */   
/*     */   public void setUm(String um) {
/*  95 */     this.um = um;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/*  99 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/* 103 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getReceiptQty() {
/* 107 */     return this.receiptQty;
/*     */   }
/*     */   
/*     */   public void setReceiptQty(BigDecimal receiptQty) {
/* 111 */     this.receiptQty = receiptQty;
/*     */   }
/*     */   
/*     */   public BigDecimal getQtyOpen() {
/* 115 */     return this.qtyOpen;
/*     */   }
/*     */   
/*     */   public void setQtyOpen(BigDecimal qtyOpen) {
/* 119 */     this.qtyOpen = qtyOpen;
/*     */   }
/*     */   
/*     */   public BigDecimal getReturnQtySum() {
/* 123 */     return this.returnQtySum;
/*     */   }
/*     */   
/*     */   public void setReturnQtySum(BigDecimal returnQtySum) {
/* 127 */     this.returnQtySum = returnQtySum;
/*     */   }
/*     */   
/*     */   public Date getShipmentDate() {
/* 131 */     return this.shipmentDate;
/*     */   }
/*     */   
/*     */   public void setShipmentDate(Date shipmentDate) {
/* 135 */     this.shipmentDate = shipmentDate;
/*     */   }
/*     */   
/*     */   public Date getEntryDate() {
/* 139 */     return this.entryDate;
/*     */   }
/*     */   
/*     */   public void setEntryDate(Date entryDate) {
/* 143 */     this.entryDate = entryDate;
/*     */   }
/*     */   
/*     */   public String getDescribe() {
/* 147 */     return this.describe;
/*     */   }
/*     */   
/*     */   public void setDescribe(String describe) {
/* 151 */     this.describe = describe;
/*     */   }
/*     */   
/*     */   public String getPoDomain() {
/* 155 */     return this.poDomain;
/*     */   }
/*     */   
/*     */   public void setPoDomain(String poDomain) {
/* 159 */     this.poDomain = poDomain;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/* 163 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/* 167 */     this.site = site;
/*     */   }
/*     */   
/*     */   public String getFactory() {
/* 171 */     return this.factory;
/*     */   }
/*     */   
/*     */   public void setFactory(String factory) {
/* 175 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public YesNo getIsprint() {
/* 179 */     return this.isprint;
/*     */   }
/*     */   
/*     */   public void setIsprint(YesNo isprint) {
/* 183 */     this.isprint = isprint;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractCustomerPlan() {}
/*     */   
/*     */   public AbstractCustomerPlan(Integer id) {
/* 190 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 194 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 198 */     this.id = id;
/*     */   }
/*     */   
/*     */   public BigDecimal getUnitPrice() {
/* 202 */     return this.unitPrice;
/*     */   }
/*     */   
/*     */   public void setUnitPrice(BigDecimal unitPrice) {
/* 206 */     this.unitPrice = unitPrice;
/*     */   }
/*     */   
/*     */   public String getSotaxc() {
/* 210 */     return this.sotaxc;
/*     */   }
/*     */   
/*     */   public void setSotaxc(String sotaxc) {
/* 214 */     this.sotaxc = sotaxc;
/*     */   }
/*     */   
/*     */   public String getOperation() {
/* 218 */     return this.operation;
/*     */   }
/*     */   
/*     */   public void setOperation(String operation) {
/* 222 */     this.operation = operation;
/*     */   }
/*     */   
/*     */   public String getCurr() {
/* 226 */     return this.curr;
/*     */   }
/*     */   
/*     */   public void setCurr(String curr) {
/* 230 */     this.curr = curr;
/*     */   }
/*     */   
/*     */   public Integer getStatus() {
/* 234 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(Integer status) {
/* 238 */     this.status = status;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/AbstractCustomerPlan.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */