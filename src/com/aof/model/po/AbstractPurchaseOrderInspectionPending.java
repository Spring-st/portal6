/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.metadata.PurchaseOrderStatus;
/*     */ import com.aof.model.metadata.YesNo;
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
/*     */ public abstract class AbstractPurchaseOrderInspectionPending
/*     */   implements Serializable
/*     */ {
/*  32 */   private int hashValue = 0;
/*     */   
/*     */   private String poip_number;
/*     */   
/*     */   private String po_number;
/*     */   private Site site;
/*     */   private Department department;
/*     */   private Date createDate;
/*     */   private Supplier supplier;
/*     */   private PurchaseOrderStatus status;
/*     */   private Integer createType;
/*     */   private String remark;
/*     */   private String describe;
/*     */   private String pdfmd5;
/*     */   private YesNo isPrint;
/*     */   private String contacter;
/*     */   private String shipmentTo;
/*     */   private YesNo ifConfirmPO;
/*     */   private String buyer;
/*     */   private String buyer_phone;
/*     */   private BigDecimal qty;
/*     */   private BigDecimal receiptQty;
/*     */   private BigDecimal inventoryNumber;
/*     */   
/*     */   public BigDecimal getQty() {
/*  57 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/*  61 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getReceiptQty() {
/*  65 */     return this.receiptQty;
/*     */   }
/*     */   
/*     */   public void setReceiptQty(BigDecimal receiptQty) {
/*  69 */     this.receiptQty = receiptQty;
/*     */   }
/*     */   
/*     */   public BigDecimal getInventoryNumber() {
/*  73 */     return this.inventoryNumber;
/*     */   }
/*     */   
/*     */   public void setInventoryNumber(BigDecimal inventoryNumber) {
/*  77 */     this.inventoryNumber = inventoryNumber;
/*     */   }
/*     */   
/*     */   public String getContacter() {
/*  81 */     return this.contacter;
/*     */   }
/*     */   
/*     */   public void setContacter(String contacter) {
/*  85 */     this.contacter = contacter;
/*     */   }
/*     */   
/*     */   public String getShipmentTo() {
/*  89 */     return this.shipmentTo;
/*     */   }
/*     */   
/*     */   public void setShipmentTo(String shipmentTo) {
/*  93 */     this.shipmentTo = shipmentTo;
/*     */   }
/*     */   
/*     */   public YesNo getIfConfirmPO() {
/*  97 */     return this.ifConfirmPO;
/*     */   }
/*     */   
/*     */   public void setIfConfirmPO(YesNo ifConfirmPO) {
/* 101 */     this.ifConfirmPO = ifConfirmPO;
/*     */   }
/*     */   
/*     */   public YesNo getIsPrint() {
/* 105 */     return this.isPrint;
/*     */   }
/*     */   
/*     */   public void setIsPrint(YesNo isPrint) {
/* 109 */     this.isPrint = isPrint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescribe() {
/* 116 */     return this.describe;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescribe(String describe) {
/* 124 */     this.describe = describe;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderInspectionPending() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderInspectionPending(String id) {
/* 139 */     setId(id);
/*     */   }
/*     */   
/*     */   public Site getSite() {
/* 143 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/* 147 */     this.site = site;
/*     */   }
/*     */   
/*     */   public Department getDepartment() {
/* 151 */     return this.department;
/*     */   }
/*     */   
/*     */   public void setDepartment(Department department) {
/* 155 */     this.department = department;
/*     */   }
/*     */   
/*     */   public Date getCreateDate() {
/* 159 */     return this.createDate;
/*     */   }
/*     */   
/*     */   public void setCreateDate(Date createDate) {
/* 163 */     this.createDate = createDate;
/*     */   }
/*     */   
/*     */   public Supplier getSupplier() {
/* 167 */     return this.supplier;
/*     */   }
/*     */   
/*     */   public void setSupplier(Supplier supplier) {
/* 171 */     this.supplier = supplier;
/*     */   }
/*     */   
/*     */   public PurchaseOrderStatus getStatus() {
/* 175 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(PurchaseOrderStatus status) {
/* 179 */     this.status = status;
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
/* 194 */     if (!(rhs instanceof PurchaseOrderInspectionPending))
/* 195 */       return false; 
/* 196 */     PurchaseOrderInspectionPending that = (PurchaseOrderInspectionPending)rhs;
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
/*     */   
/*     */   public String getId() {
/* 220 */     return getPoip_number();
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/* 224 */     setPoip_number(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getCreateType() {
/* 231 */     return this.createType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreateType(Integer createType) {
/* 239 */     this.createType = createType;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 243 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 247 */     this.remark = remark;
/*     */   }
/*     */   
/*     */   public String getPo_number() {
/* 251 */     return this.po_number;
/*     */   }
/*     */   
/*     */   public void setPo_number(String po_number) {
/* 255 */     this.po_number = po_number;
/*     */   }
/*     */   
/*     */   public String getPoip_number() {
/* 259 */     return this.poip_number;
/*     */   }
/*     */   
/*     */   public void setPoip_number(String poipNumber) {
/* 263 */     this.poip_number = poipNumber;
/*     */   }
/*     */   
/*     */   public String getPdfmd5() {
/* 267 */     return this.pdfmd5;
/*     */   }
/*     */   
/*     */   public void setPdfmd5(String pdfmd5) {
/* 271 */     this.pdfmd5 = pdfmd5;
/*     */   }
/*     */   
/*     */   public String getBuyer() {
/* 275 */     return this.buyer;
/*     */   }
/*     */   
/*     */   public void setBuyer(String buyer) {
/* 279 */     this.buyer = buyer;
/*     */   }
/*     */   
/*     */   public String getBuyer_phone() {
/* 283 */     return this.buyer_phone;
/*     */   }
/*     */   
/*     */   public void setBuyer_phone(String buyerPhone) {
/* 287 */     this.buyer_phone = buyerPhone;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPurchaseOrderInspectionPending.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */