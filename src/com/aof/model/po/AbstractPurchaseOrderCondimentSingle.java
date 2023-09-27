/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.PurchaseOrderCondimentSingleStatus;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractPurchaseOrderCondimentSingle
/*     */   implements Serializable
/*     */ {
/*  30 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   
/*     */   private PurchaseOrderInspectionPendingItem po_detial_id;
/*     */   
/*     */   private WmsPart part;
/*     */   
/*     */   private Supplier supplier;
/*     */   
/*     */   private BigDecimal number;
/*     */   
/*     */   private Date date;
/*     */   private String supplier_part;
/*     */   
/*     */   public BigDecimal getPutIn_qty() {
/*  46 */     return this.putIn_qty;
/*     */   }
/*     */   private String line; private PurchaseOrderCondimentSingleStatus status; private String remark; private String code; private BigDecimal delivery_qty; private BigDecimal putIn_qty; private Integer isPrint;
/*     */   public void setPutIn_qty(BigDecimal putIn_qty) {
/*  50 */     this.putIn_qty = putIn_qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getDelivery_qty() {
/*  54 */     return this.delivery_qty;
/*     */   }
/*     */   
/*     */   public void setDelivery_qty(BigDecimal delivery_qty) {
/*  58 */     this.delivery_qty = delivery_qty;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  62 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/*  66 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/*  70 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/*  74 */     this.remark = remark;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  78 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  82 */     this.id = id;
/*     */   }
/*     */   
/*     */   public PurchaseOrderInspectionPendingItem getPo_detial_id() {
/*  86 */     return this.po_detial_id;
/*     */   }
/*     */   
/*     */   public void setPo_detial_id(PurchaseOrderInspectionPendingItem po_detial_id) {
/*  90 */     this.po_detial_id = po_detial_id;
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/*  94 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  98 */     this.part = part;
/*     */   }
/*     */   
/*     */   public Supplier getSupplier() {
/* 102 */     return this.supplier;
/*     */   }
/*     */   
/*     */   public void setSupplier(Supplier supplier) {
/* 106 */     this.supplier = supplier;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/* 110 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/* 114 */     this.date = date;
/*     */   }
/*     */   
/*     */   public BigDecimal getNumber() {
/* 118 */     return this.number;
/*     */   }
/*     */   
/*     */   public void setNumber(BigDecimal number) {
/* 122 */     this.number = number;
/*     */   }
/*     */   
/*     */   public String getSupplier_part() {
/* 126 */     return this.supplier_part;
/*     */   }
/*     */   
/*     */   public void setSupplier_part(String supplier_part) {
/* 130 */     this.supplier_part = supplier_part;
/*     */   }
/*     */   
/*     */   public String getLine() {
/* 134 */     return this.line;
/*     */   }
/*     */   
/*     */   public void setLine(String line) {
/* 138 */     this.line = line;
/*     */   }
/*     */   
/*     */   public PurchaseOrderCondimentSingleStatus getStatus() {
/* 142 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(PurchaseOrderCondimentSingleStatus status) {
/* 146 */     this.status = status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderCondimentSingle() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderCondimentSingle(Integer id) {
/* 158 */     setId(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer getIsPrint() {
/* 163 */     return this.isPrint;
/*     */   }
/*     */   
/*     */   public void setIsPrint(Integer isPrint) {
/* 167 */     this.isPrint = isPrint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 178 */     if (rhs == null)
/* 179 */       return false; 
/* 180 */     if (this == rhs)
/* 181 */       return true; 
/* 182 */     if (!(rhs instanceof PurchaseOrderCondimentSingle))
/* 183 */       return false; 
/* 184 */     PurchaseOrderCondimentSingle that = (PurchaseOrderCondimentSingle)rhs;
/* 185 */     if (getId() != null)
/* 186 */       return getId().equals(that.getId()); 
/* 187 */     return (that.getId() == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 198 */     if (this.hashValue == 0) {
/* 199 */       int result = 17;
/* 200 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 201 */       result = result * 37 + poIdValue;
/* 202 */       this.hashValue = result;
/*     */     } 
/* 204 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPurchaseOrderCondimentSingle.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */