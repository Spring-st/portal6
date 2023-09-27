/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.CurrencyType;
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
/*     */ public abstract class AbstractPurchaseOrderInspectionPendingItem
/*     */   implements Serializable
/*     */ {
/*  30 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private PurchaseOrderInspectionPending poip_number;
/*     */   private PurchaseOrderDetial poi;
/*     */   private String line;
/*     */   private WmsPart itemNumber;
/*     */   private String supplierItemNumber;
/*     */   private String um;
/*     */   private String rum;
/*     */   private BigDecimal qty;
/*     */   private Date dueDate;
/*     */   private BigDecimal qtyOpen;
/*     */   private BigDecimal ReturnQtySum;
/*     */   private String describe;
/*     */   private Date receivingDate;
/*     */   private YesNo isReceiving;
/*     */   private YesNo isIqc;
/*     */   private YesNo isPrintLabels;
/*     */   private Integer boxCount;
/*     */   private YesNo isViewItem;
/*     */   private BigDecimal conversion_ratio;
/*     */   private String part_type;
/*     */   private String factory;
/*     */   private Date arrivalDate;
/*  54 */   private String checked = "";
/*     */   private String ad_attn;
/*     */   private String po_ship;
/*     */   private String vd_promo;
/*     */   private Integer po_confirm;
/*     */   private String po_domain;
/*     */   private BigDecimal qty_std;
/*     */   private BigDecimal receiptQty;
/*     */   private BigDecimal inventoryNumber;
/*     */   private BigDecimal returnNumber;
/*     */   private YesNo isTxt;
/*     */   private BigDecimal peric;
/*     */   private CurrencyType currencyType;
/*     */   private BigDecimal capacity;
/*     */   private BigDecimal transitQty;
/*     */   private BigDecimal currentQty;
/*     */   private Integer needRQCnumber;
/*     */   
/*     */   public BigDecimal getCapacity() {
/*  73 */     return this.capacity;
/*     */   }
/*     */   
/*     */   public void setCapacity(BigDecimal capacity) {
/*  77 */     this.capacity = capacity;
/*     */   }
/*     */   
/*     */   public CurrencyType getCurrencyType() {
/*  81 */     return this.currencyType;
/*     */   }
/*     */   
/*     */   public void setCurrencyType(CurrencyType currencyType) {
/*  85 */     this.currencyType = currencyType;
/*     */   }
/*     */   
/*     */   public BigDecimal getPeric() {
/*  89 */     return this.peric;
/*     */   }
/*     */   
/*     */   public void setPeric(BigDecimal peric) {
/*  93 */     this.peric = peric;
/*     */   }
/*     */   
/*     */   public YesNo getIsTxt() {
/*  97 */     return this.isTxt;
/*     */   }
/*     */   
/*     */   public void setIsTxt(YesNo isTxt) {
/* 101 */     this.isTxt = isTxt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getChecked() {
/* 108 */     return this.checked;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChecked(String checked) {
/* 116 */     this.checked = checked;
/*     */   }
/*     */   
/*     */   public YesNo getIsViewItem() {
/* 120 */     return this.isViewItem;
/*     */   }
/*     */   
/*     */   public void setIsViewItem(YesNo isViewItem) {
/* 124 */     this.isViewItem = isViewItem;
/*     */   }
/*     */   
/*     */   public BigDecimal getReturnQtySum() {
/* 128 */     return this.ReturnQtySum;
/*     */   }
/*     */   
/*     */   public void setReturnQtySum(BigDecimal returnQtySum) {
/* 132 */     this.ReturnQtySum = returnQtySum;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescribe() {
/* 139 */     return this.describe;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescribe(String describe) {
/* 147 */     this.describe = describe;
/*     */   }
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
/*     */   public Integer getNeedRQCnumber() {
/* 160 */     return this.needRQCnumber;
/*     */   }
/*     */   
/*     */   public void setNeedRQCnumber(Integer needRQCnumber) {
/* 164 */     this.needRQCnumber = needRQCnumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal getInventoryNumber() {
/* 176 */     return this.inventoryNumber;
/*     */   }
/*     */   
/*     */   public void setInventoryNumber(BigDecimal inventoryNumber) {
/* 180 */     this.inventoryNumber = inventoryNumber;
/*     */   }
/*     */   
/*     */   public BigDecimal getReturnNumber() {
/* 184 */     return this.returnNumber;
/*     */   }
/*     */   
/*     */   public void setReturnNumber(BigDecimal returnNumber) {
/* 188 */     this.returnNumber = returnNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getBoxCount() {
/* 195 */     return this.boxCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBoxCount(Integer boxCount) {
/* 203 */     this.boxCount = boxCount;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 207 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 211 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getLine() {
/* 215 */     return this.line;
/*     */   }
/*     */   
/*     */   public void setLine(String line) {
/* 219 */     this.line = line;
/*     */   }
/*     */   
/*     */   public String getUm() {
/* 223 */     return this.um;
/*     */   }
/*     */   
/*     */   public void setUm(String um) {
/* 227 */     this.um = um;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/* 231 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/* 235 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public Date getDueDate() {
/* 239 */     return this.dueDate;
/*     */   }
/*     */   
/*     */   public void setDueDate(Date dueDate) {
/* 243 */     this.dueDate = dueDate;
/*     */   }
/*     */   
/*     */   public BigDecimal getReceiptQty() {
/* 247 */     return this.receiptQty;
/*     */   }
/*     */   
/*     */   public void setReceiptQty(BigDecimal receiptQty) {
/* 251 */     this.receiptQty = receiptQty;
/*     */   }
/*     */   
/*     */   public BigDecimal getQtyOpen() {
/* 255 */     return this.qtyOpen;
/*     */   }
/*     */   
/*     */   public void setQtyOpen(BigDecimal qtyOpen) {
/* 259 */     this.qtyOpen = qtyOpen;
/*     */   }
/*     */   
/*     */   public Date getReceivingDate() {
/* 263 */     return this.receivingDate;
/*     */   }
/*     */   
/*     */   public void setReceivingDate(Date receivingDate) {
/* 267 */     this.receivingDate = receivingDate;
/*     */   }
/*     */   
/*     */   public YesNo getIsReceiving() {
/* 271 */     return this.isReceiving;
/*     */   }
/*     */   
/*     */   public void setIsReceiving(YesNo isReceiving) {
/* 275 */     this.isReceiving = isReceiving;
/*     */   }
/*     */   
/*     */   public YesNo getIsIqc() {
/* 279 */     return this.isIqc;
/*     */   }
/*     */   
/*     */   public void setIsIqc(YesNo isIqc) {
/* 283 */     this.isIqc = isIqc;
/*     */   }
/*     */   
/*     */   public YesNo getIsPrintLabels() {
/* 287 */     return this.isPrintLabels;
/*     */   }
/*     */   
/*     */   public void setIsPrintLabels(YesNo isPrintLabels) {
/* 291 */     this.isPrintLabels = isPrintLabels;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderInspectionPendingItem() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderInspectionPendingItem(Integer id) {
/* 306 */     setId(id);
/*     */   }
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 310 */     if (rhs == null)
/* 311 */       return false; 
/* 312 */     if (this == rhs)
/* 313 */       return true; 
/* 314 */     if (!(rhs instanceof PurchaseOrderInspectionPendingItem))
/* 315 */       return false; 
/* 316 */     PurchaseOrderInspectionPendingItem that = (PurchaseOrderInspectionPendingItem)rhs;
/* 317 */     if (getId() != null)
/* 318 */       return getId().equals(that.getId()); 
/* 319 */     return (that.getId() == null);
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
/* 330 */     if (this.hashValue == 0) {
/* 331 */       int result = 17;
/* 332 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 333 */       result = result * 37 + poIdValue;
/* 334 */       this.hashValue = result;
/*     */     } 
/* 336 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public PurchaseOrderInspectionPending getPoip_number() {
/* 340 */     return this.poip_number;
/*     */   }
/*     */   
/*     */   public void setPoip_number(PurchaseOrderInspectionPending poipNumber) {
/* 344 */     this.poip_number = poipNumber;
/*     */   }
/*     */   
/*     */   public PurchaseOrderDetial getPoi() {
/* 348 */     return this.poi;
/*     */   }
/*     */   
/*     */   public void setPoi(PurchaseOrderDetial poi) {
/* 352 */     this.poi = poi;
/*     */   }
/*     */   
/*     */   public WmsPart getItemNumber() {
/* 356 */     return this.itemNumber;
/*     */   }
/*     */   
/*     */   public void setItemNumber(WmsPart itemNumber) {
/* 360 */     this.itemNumber = itemNumber;
/*     */   }
/*     */   
/*     */   public String getSupplierItemNumber() {
/* 364 */     return this.supplierItemNumber;
/*     */   }
/*     */   
/*     */   public void setSupplierItemNumber(String supplierItemNumber) {
/* 368 */     this.supplierItemNumber = supplierItemNumber;
/*     */   }
/*     */   
/*     */   public String getRum() {
/* 372 */     return this.rum;
/*     */   }
/*     */   
/*     */   public void setRum(String rum) {
/* 376 */     this.rum = rum;
/*     */   }
/*     */   
/*     */   public BigDecimal getConversion_ratio() {
/* 380 */     return this.conversion_ratio;
/*     */   }
/*     */   
/*     */   public void setConversion_ratio(BigDecimal conversionRatio) {
/* 384 */     this.conversion_ratio = conversionRatio;
/*     */   }
/*     */   
/*     */   public String getPart_type() {
/* 388 */     return this.part_type;
/*     */   }
/*     */   
/*     */   public void setPart_type(String partType) {
/* 392 */     this.part_type = partType;
/*     */   }
/*     */   
/*     */   public String getFactory() {
/* 396 */     return this.factory;
/*     */   }
/*     */   
/*     */   public void setFactory(String factory) {
/* 400 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public Date getArrivalDate() {
/* 404 */     return this.arrivalDate;
/*     */   }
/*     */   
/*     */   public void setArrivalDate(Date arrivalDate) {
/* 408 */     this.arrivalDate = arrivalDate;
/*     */   }
/*     */   
/*     */   public String getAd_attn() {
/* 412 */     return this.ad_attn;
/*     */   }
/*     */   
/*     */   public void setAd_attn(String adAttn) {
/* 416 */     this.ad_attn = adAttn;
/*     */   }
/*     */   
/*     */   public String getPo_ship() {
/* 420 */     return this.po_ship;
/*     */   }
/*     */   
/*     */   public void setPo_ship(String poShip) {
/* 424 */     this.po_ship = poShip;
/*     */   }
/*     */   
/*     */   public String getVd_promo() {
/* 428 */     return this.vd_promo;
/*     */   }
/*     */   
/*     */   public void setVd_promo(String vdPromo) {
/* 432 */     this.vd_promo = vdPromo;
/*     */   }
/*     */   
/*     */   public Integer getPo_confirm() {
/* 436 */     return this.po_confirm;
/*     */   }
/*     */   
/*     */   public void setPo_confirm(Integer poConfirm) {
/* 440 */     this.po_confirm = poConfirm;
/*     */   }
/*     */   
/*     */   public String getPo_domain() {
/* 444 */     return this.po_domain;
/*     */   }
/*     */   
/*     */   public void setPo_domain(String poDomain) {
/* 448 */     this.po_domain = poDomain;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty_std() {
/* 452 */     return this.qty_std;
/*     */   }
/*     */   
/*     */   public void setQty_std(BigDecimal qtyStd) {
/* 456 */     this.qty_std = qtyStd;
/*     */   }
/*     */   
/*     */   public BigDecimal getTransitQty() {
/* 460 */     return this.transitQty;
/*     */   }
/*     */   
/*     */   public void setTransitQty(BigDecimal transitQty) {
/* 464 */     this.transitQty = transitQty;
/*     */   }
/*     */   
/*     */   public BigDecimal getCurrentQty() {
/* 468 */     return this.currentQty;
/*     */   }
/*     */   
/*     */   public void setCurrentQty(BigDecimal currentQty) {
/* 472 */     this.currentQty = currentQty;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPurchaseOrderInspectionPendingItem.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */