/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.metadata.PurchaseOrderReceiptsType;
/*     */ import com.aof.model.metadata.PurchaseOrderStatus;
/*     */ import com.aof.model.metadata.PurchaseOrderType;
/*     */ import java.io.Serializable;
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
/*     */ public abstract class AbstractPurchaseOrder
/*     */   implements Serializable
/*     */ {
/*  31 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private String po_number;
/*     */   private Site site;
/*     */   private Department department;
/*     */   private Supplier supplier;
/*     */   private String domain;
/*     */   private Date po_date;
/*     */   private String po_order;
/*     */   private PurchaseOrderStatus status;
/*     */   private PurchaseOrderReceiptsType receipts_type;
/*     */   private PurchaseOrderType type;
/*     */   private String address;
/*     */   private String describe;
/*     */   private String remark;
/*     */   
/*     */   public Integer getId() {
/*  48 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  52 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getHashValue() {
/*  56 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/*  60 */     this.hashValue = hashValue;
/*     */   }
/*     */   
/*     */   public String getPo_number() {
/*  64 */     return this.po_number;
/*     */   }
/*     */   
/*     */   public void setPo_number(String po_number) {
/*  68 */     this.po_number = po_number;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/*  72 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/*  76 */     this.site = site;
/*     */   }
/*     */   
/*     */   public Department getDepartment() {
/*  80 */     return this.department;
/*     */   }
/*     */   
/*     */   public void setDepartment(Department department) {
/*  84 */     this.department = department;
/*     */   }
/*     */   
/*     */   public Supplier getSupplier() {
/*  88 */     return this.supplier;
/*     */   }
/*     */   
/*     */   public void setSupplier(Supplier supplier) {
/*  92 */     this.supplier = supplier;
/*     */   }
/*     */   
/*     */   public String getDomain() {
/*  96 */     return this.domain;
/*     */   }
/*     */   
/*     */   public void setDomain(String domain) {
/* 100 */     this.domain = domain;
/*     */   }
/*     */   
/*     */   public Date getPo_date() {
/* 104 */     return this.po_date;
/*     */   }
/*     */   
/*     */   public void setPo_date(Date po_date) {
/* 108 */     this.po_date = po_date;
/*     */   }
/*     */   
/*     */   public String getPo_order() {
/* 112 */     return this.po_order;
/*     */   }
/*     */   
/*     */   public void setPo_order(String po_order) {
/* 116 */     this.po_order = po_order;
/*     */   }
/*     */   
/*     */   public PurchaseOrderStatus getStatus() {
/* 120 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(PurchaseOrderStatus status) {
/* 124 */     this.status = status;
/*     */   }
/*     */   
/*     */   public PurchaseOrderReceiptsType getReceipts_type() {
/* 128 */     return this.receipts_type;
/*     */   }
/*     */   
/*     */   public void setReceipts_type(PurchaseOrderReceiptsType receipts_type) {
/* 132 */     this.receipts_type = receipts_type;
/*     */   }
/*     */   
/*     */   public PurchaseOrderType getType() {
/* 136 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(PurchaseOrderType type) {
/* 140 */     this.type = type;
/*     */   }
/*     */   
/*     */   public String getAddress() {
/* 144 */     return this.address;
/*     */   }
/*     */   
/*     */   public void setAddress(String address) {
/* 148 */     this.address = address;
/*     */   }
/*     */   
/*     */   public String getDescribe() {
/* 152 */     return this.describe;
/*     */   }
/*     */   
/*     */   public void setDescribe(String describe) {
/* 156 */     this.describe = describe;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 160 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 164 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrder() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrder(Integer id) {
/* 179 */     setId(id);
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
/* 190 */     if (rhs == null)
/* 191 */       return false; 
/* 192 */     if (this == rhs)
/* 193 */       return true; 
/* 194 */     if (!(rhs instanceof PurchaseOrder))
/* 195 */       return false; 
/* 196 */     PurchaseOrder that = (PurchaseOrder)rhs;
/* 197 */     if (getId() != null)
/* 198 */       return getId().equals(that.getId()); 
/* 199 */     return (that.getId() == null);
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
/* 210 */     if (this.hashValue == 0) {
/* 211 */       int result = 17;
/* 212 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 213 */       result = result * 37 + poIdValue;
/* 214 */       this.hashValue = result;
/*     */     } 
/* 216 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPurchaseOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */