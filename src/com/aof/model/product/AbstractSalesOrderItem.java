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
/*     */ public abstract class AbstractSalesOrderItem
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private SalesOrder soId;
/*     */   private String soipNumber;
/*     */   private String line;
/*     */   private WmsPart itemNumber;
/*     */   private String supplierItemNumber;
/*     */   private String um;
/*     */   private String rum;
/*     */   private BigDecimal qty;
/*     */   private Integer status;
/*     */   private Date dueDate;
/*     */   private BigDecimal receiptQty;
/*     */   private Integer isPrintLabels;
/*     */   private BigDecimal boxcount;
/*     */   private String describe;
/*  29 */   private String checked = "";
/*     */   
/*     */   private BigDecimal returnQtySum;
/*     */   
/*     */   private BigDecimal conversionRatio;
/*     */   private String partType;
/*     */   private String factory;
/*     */   private String poDomain;
/*     */   private Site site;
/*     */   private YesNo issync;
/*     */   private YesNo istxt;
/*     */   private BigDecimal qtyopen;
/*     */   private Integer sotype;
/*     */   
/*     */   public Integer getSotype() {
/*  44 */     return this.sotype;
/*     */   }
/*     */   
/*     */   public void setSotype(Integer sotype) {
/*  48 */     this.sotype = sotype;
/*     */   }
/*     */   
/*     */   public String getChecked() {
/*  52 */     return this.checked;
/*     */   }
/*     */   
/*     */   public void setChecked(String checked) {
/*  56 */     this.checked = checked;
/*     */   }
/*     */   
/*     */   public BigDecimal getQtyopen() {
/*  60 */     return this.qtyopen;
/*     */   }
/*     */   
/*     */   public void setQtyopen(BigDecimal qtyopen) {
/*  64 */     this.qtyopen = qtyopen;
/*     */   }
/*     */   
/*     */   public WmsPart getItemNumber() {
/*  68 */     return this.itemNumber;
/*     */   }
/*     */   
/*     */   public void setItemNumber(WmsPart itemNumber) {
/*  72 */     this.itemNumber = itemNumber;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/*  76 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/*  80 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SalesOrder getSoId() {
/*  86 */     return this.soId;
/*     */   }
/*     */   
/*     */   public void setSoId(SalesOrder soId) {
/*  90 */     this.soId = soId;
/*     */   }
/*     */   
/*     */   public String getSoipNumber() {
/*  94 */     return this.soipNumber;
/*     */   }
/*     */   
/*     */   public void setSoipNumber(String soipNumber) {
/*  98 */     this.soipNumber = soipNumber;
/*     */   }
/*     */   
/*     */   public String getLine() {
/* 102 */     return this.line;
/*     */   }
/*     */   
/*     */   public void setLine(String line) {
/* 106 */     this.line = line;
/*     */   }
/*     */   
/*     */   public String getSupplierItemNumber() {
/* 110 */     return this.supplierItemNumber;
/*     */   }
/*     */   
/*     */   public void setSupplierItemNumber(String supplierItemNumber) {
/* 114 */     this.supplierItemNumber = supplierItemNumber;
/*     */   }
/*     */   
/*     */   public String getUm() {
/* 118 */     return this.um;
/*     */   }
/*     */   
/*     */   public void setUm(String um) {
/* 122 */     this.um = um;
/*     */   }
/*     */   
/*     */   public String getRum() {
/* 126 */     return this.rum;
/*     */   }
/*     */   
/*     */   public void setRum(String rum) {
/* 130 */     this.rum = rum;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/* 134 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/* 138 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public Integer getStatus() {
/* 142 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(Integer status) {
/* 146 */     this.status = status;
/*     */   }
/*     */   
/*     */   public Date getDueDate() {
/* 150 */     return this.dueDate;
/*     */   }
/*     */   
/*     */   public void setDueDate(Date dueDate) {
/* 154 */     this.dueDate = dueDate;
/*     */   }
/*     */   
/*     */   public BigDecimal getReceiptQty() {
/* 158 */     return this.receiptQty;
/*     */   }
/*     */   
/*     */   public void setReceiptQty(BigDecimal receiptQty) {
/* 162 */     this.receiptQty = receiptQty;
/*     */   }
/*     */   
/*     */   public Integer getIsPrintLabels() {
/* 166 */     return this.isPrintLabels;
/*     */   }
/*     */   
/*     */   public void setIsPrintLabels(Integer isPrintLabels) {
/* 170 */     this.isPrintLabels = isPrintLabels;
/*     */   }
/*     */   
/*     */   public BigDecimal getBoxcount() {
/* 174 */     return this.boxcount;
/*     */   }
/*     */   
/*     */   public void setBoxcount(BigDecimal boxcount) {
/* 178 */     this.boxcount = boxcount;
/*     */   }
/*     */   
/*     */   public String getDescribe() {
/* 182 */     return this.describe;
/*     */   }
/*     */   
/*     */   public void setDescribe(String describe) {
/* 186 */     this.describe = describe;
/*     */   }
/*     */   
/*     */   public BigDecimal getReturnQtySum() {
/* 190 */     return this.returnQtySum;
/*     */   }
/*     */   
/*     */   public void setReturnQtySum(BigDecimal returnQtySum) {
/* 194 */     this.returnQtySum = returnQtySum;
/*     */   }
/*     */   
/*     */   public BigDecimal getConversionRatio() {
/* 198 */     return this.conversionRatio;
/*     */   }
/*     */   
/*     */   public void setConversionRatio(BigDecimal conversionRatio) {
/* 202 */     this.conversionRatio = conversionRatio;
/*     */   }
/*     */   
/*     */   public String getPartType() {
/* 206 */     return this.partType;
/*     */   }
/*     */   
/*     */   public void setPartType(String partType) {
/* 210 */     this.partType = partType;
/*     */   }
/*     */   
/*     */   public String getFactory() {
/* 214 */     return this.factory;
/*     */   }
/*     */   
/*     */   public void setFactory(String factory) {
/* 218 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public String getPoDomain() {
/* 222 */     return this.poDomain;
/*     */   }
/*     */   
/*     */   public void setPoDomain(String poDomain) {
/* 226 */     this.poDomain = poDomain;
/*     */   }
/*     */   
/*     */   public YesNo getIssync() {
/* 230 */     return this.issync;
/*     */   }
/*     */   
/*     */   public void setIssync(YesNo issync) {
/* 234 */     this.issync = issync;
/*     */   }
/*     */   
/*     */   public YesNo getIstxt() {
/* 238 */     return this.istxt;
/*     */   }
/*     */   
/*     */   public void setIstxt(YesNo istxt) {
/* 242 */     this.istxt = istxt;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractSalesOrderItem() {}
/*     */   
/*     */   public AbstractSalesOrderItem(Integer id) {
/* 249 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 253 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 257 */     this.id = id;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/AbstractSalesOrderItem.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */