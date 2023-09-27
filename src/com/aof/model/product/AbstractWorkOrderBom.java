/*     */ package com.aof.model.product;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractWorkOrderBom
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private String workOrderId;
/*     */   private String workOrderNo;
/*     */   private String productNo;
/*     */   private String partNo;
/*     */   private String allNeedQty;
/*     */   private String workingOrder;
/*     */   private String workingPosition;
/*     */   private BigDecimal singleNeedQty;
/*     */   private String site;
/*     */   private String domain;
/*     */   private String seqNo;
/*     */   
/*     */   public String getWorkOrderId() {
/*  26 */     return this.workOrderId;
/*     */   }
/*     */   
/*     */   public void setWorkOrderId(String workOrderId) {
/*  30 */     this.workOrderId = workOrderId;
/*     */   }
/*     */   
/*     */   public String getWorkOrderNo() {
/*  34 */     return this.workOrderNo;
/*     */   }
/*     */   
/*     */   public void setWorkOrderNo(String workOrderNo) {
/*  38 */     this.workOrderNo = workOrderNo;
/*     */   }
/*     */   
/*     */   public String getProductNo() {
/*  42 */     return this.productNo;
/*     */   }
/*     */   
/*     */   public void setProductNo(String productNo) {
/*  46 */     this.productNo = productNo;
/*     */   }
/*     */   
/*     */   public String getPartNo() {
/*  50 */     return this.partNo;
/*     */   }
/*     */   
/*     */   public void setPartNo(String partNo) {
/*  54 */     this.partNo = partNo;
/*     */   }
/*     */   
/*     */   public String getAllNeedQty() {
/*  58 */     return this.allNeedQty;
/*     */   }
/*     */   
/*     */   public void setAllNeedQty(String allNeedQty) {
/*  62 */     this.allNeedQty = allNeedQty;
/*     */   }
/*     */   
/*     */   public String getWorkingOrder() {
/*  66 */     return this.workingOrder;
/*     */   }
/*     */   
/*     */   public void setWorkingOrder(String workingOrder) {
/*  70 */     this.workingOrder = workingOrder;
/*     */   }
/*     */   
/*     */   public String getWorkingPosition() {
/*  74 */     return this.workingPosition;
/*     */   }
/*     */   
/*     */   public void setWorkingPosition(String workingPosition) {
/*  78 */     this.workingPosition = workingPosition;
/*     */   }
/*     */   
/*     */   public BigDecimal getSingleNeedQty() {
/*  82 */     return this.singleNeedQty;
/*     */   }
/*     */   
/*     */   public void setSingleNeedQty(BigDecimal singleNeedQty) {
/*  86 */     this.singleNeedQty = singleNeedQty;
/*     */   }
/*     */   
/*     */   public String getSite() {
/*  90 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(String site) {
/*  94 */     this.site = site;
/*     */   }
/*     */   
/*     */   public String getDomain() {
/*  98 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomain(String domain) {
/* 102 */     this.domain = domain;
/*     */   }
/*     */   
/*     */   public String getSeqNo() {
/* 106 */     return this.seqNo;
/*     */   }
/*     */   
/*     */   public void setSeqNo(String seqNo) {
/* 110 */     this.seqNo = seqNo;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractWorkOrderBom() {}
/*     */   
/*     */   public AbstractWorkOrderBom(Integer id) {
/* 117 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 121 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 125 */     this.id = id;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/AbstractWorkOrderBom.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */