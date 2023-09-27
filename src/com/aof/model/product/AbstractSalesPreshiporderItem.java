/*     */ package com.aof.model.product;
/*     */ 
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSalesPreshiporderItem
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private SalesPreshiporder spsoId;
/*     */   private SalesOrderItem soipitemId;
/*     */   private BigDecimal deliverynumber;
/*     */   private YesNo status;
/*     */   private YesNo statusConfirm;
/*     */   private BigDecimal qtyStd;
/*     */   private String soRemark;
/*     */   private BigDecimal stockQty;
/*     */   private BigDecimal shipQty;
/*     */   private CustomerPlan customerPlanId;
/*  27 */   private BigDecimal price = new BigDecimal(0);
/*     */   private String curr;
/*     */   private String sotaxc;
/*     */   private List<SalesWorkorder> salesWorkorderList;
/*     */   
/*     */   public CustomerPlan getCustomerPlanId() {
/*  33 */     return this.customerPlanId;
/*     */   }
/*     */   
/*     */   public void setCustomerPlanId(CustomerPlan customerPlanId) {
/*  37 */     this.customerPlanId = customerPlanId;
/*     */   }
/*     */   
/*     */   public BigDecimal getStockQty() {
/*  41 */     return this.stockQty;
/*     */   }
/*     */   
/*     */   public void setStockQty(BigDecimal stockQty) {
/*  45 */     this.stockQty = stockQty;
/*     */   }
/*     */   
/*     */   public BigDecimal getShipQty() {
/*  49 */     return this.shipQty;
/*     */   }
/*     */   
/*     */   public void setShipQty(BigDecimal shipQty) {
/*  53 */     this.shipQty = shipQty;
/*     */   }
/*     */   
/*     */   public String getSoRemark() {
/*  57 */     return this.soRemark;
/*     */   }
/*     */   
/*     */   public void setSoRemark(String soRemark) {
/*  61 */     this.soRemark = soRemark;
/*     */   }
/*     */   
/*     */   public SalesPreshiporder getSpsoId() {
/*  65 */     return this.spsoId;
/*     */   }
/*     */   
/*     */   public void setSpsoId(SalesPreshiporder spsoId) {
/*  69 */     this.spsoId = spsoId;
/*     */   }
/*     */   
/*     */   public SalesOrderItem getSoipitemId() {
/*  73 */     return this.soipitemId;
/*     */   }
/*     */   
/*     */   public void setSoipitemId(SalesOrderItem soipitemId) {
/*  77 */     this.soipitemId = soipitemId;
/*     */   }
/*     */   
/*     */   public BigDecimal getDeliverynumber() {
/*  81 */     return this.deliverynumber;
/*     */   }
/*     */   
/*     */   public void setDeliverynumber(BigDecimal deliverynumber) {
/*  85 */     this.deliverynumber = deliverynumber;
/*     */   }
/*     */   
/*     */   public YesNo getStatus() {
/*  89 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(YesNo status) {
/*  93 */     this.status = status;
/*     */   }
/*     */   
/*     */   public YesNo getStatusConfirm() {
/*  97 */     return this.statusConfirm;
/*     */   }
/*     */   
/*     */   public void setStatusConfirm(YesNo statusConfirm) {
/* 101 */     this.statusConfirm = statusConfirm;
/*     */   }
/*     */   
/*     */   public BigDecimal getQtyStd() {
/* 105 */     return this.qtyStd;
/*     */   }
/*     */   
/*     */   public void setQtyStd(BigDecimal qtyStd) {
/* 109 */     this.qtyStd = qtyStd;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractSalesPreshiporderItem() {}
/*     */   
/*     */   public AbstractSalesPreshiporderItem(Integer id) {
/* 116 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 120 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 124 */     this.id = id;
/*     */   }
/*     */   
/*     */   public BigDecimal getPrice() {
/* 128 */     return this.price;
/*     */   }
/*     */   
/*     */   public void setPrice(BigDecimal price) {
/* 132 */     this.price = price;
/*     */   }
/*     */   
/*     */   public String getCurr() {
/* 136 */     return this.curr;
/*     */   }
/*     */   
/*     */   public void setCurr(String curr) {
/* 140 */     this.curr = curr;
/*     */   }
/*     */   
/*     */   public String getSotaxc() {
/* 144 */     return this.sotaxc;
/*     */   }
/*     */   
/*     */   public void setSotaxc(String sotaxc) {
/* 148 */     this.sotaxc = sotaxc;
/*     */   }
/*     */   
/*     */   public List<SalesWorkorder> getSalesWorkorderList() {
/* 152 */     return this.salesWorkorderList;
/*     */   }
/*     */   
/*     */   public void setSalesWorkorderList(List<SalesWorkorder> salesWorkorderList) {
/* 156 */     this.salesWorkorderList = salesWorkorderList;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/AbstractSalesPreshiporderItem.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */