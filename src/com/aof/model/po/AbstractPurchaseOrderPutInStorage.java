/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.PurchaseOrderPutInStorageStatus;
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
/*     */ public abstract class AbstractPurchaseOrderPutInStorage
/*     */   implements Serializable
/*     */ {
/*  31 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   private PurchaseOrderInspectionPendingItem poipItem;
/*     */   private PurchaseOrderCondimentSingle single;
/*     */   private StorageLocation location;
/*     */   private Date date;
/*     */   private BigDecimal receipts_qty;
/*     */   private BigDecimal qty;
/*     */   private YesNo is_sync;
/*     */   private Date is_sync_date;
/*     */   private PurchaseOrderPutInStorageStatus status;
/*     */   private WmsPart part;
/*     */   private Supplier supper;
/*     */   private Date po_date;
/*     */   private String line;
/*     */   private String po_number;
/*     */   private BigDecimal po_qty;
/*     */   private WmsLot lotSer;
/*     */   
/*     */   public WmsLot getLotSer() {
/*  52 */     return this.lotSer;
/*     */   }
/*     */   
/*     */   public void setLotSer(WmsLot lotSer) {
/*  56 */     this.lotSer = lotSer;
/*     */   }
/*     */   
/*     */   public Date getIs_sync_date() {
/*  60 */     return this.is_sync_date;
/*     */   }
/*     */   
/*     */   public void setIs_sync_date(Date is_sync_date) {
/*  64 */     this.is_sync_date = is_sync_date;
/*     */   }
/*     */   
/*     */   public PurchaseOrderCondimentSingle getSingle() {
/*  68 */     return this.single;
/*     */   }
/*     */   
/*     */   public void setSingle(PurchaseOrderCondimentSingle single) {
/*  72 */     this.single = single;
/*     */   }
/*     */   
/*     */   public Supplier getSupper() {
/*  76 */     return this.supper;
/*     */   }
/*     */   
/*     */   public void setSupper(Supplier supper) {
/*  80 */     this.supper = supper;
/*     */   }
/*     */   
/*     */   public Date getPo_date() {
/*  84 */     return this.po_date;
/*     */   }
/*     */   
/*     */   public void setPo_date(Date po_date) {
/*  88 */     this.po_date = po_date;
/*     */   }
/*     */   
/*     */   public String getLine() {
/*  92 */     return this.line;
/*     */   }
/*     */   
/*     */   public void setLine(String line) {
/*  96 */     this.line = line;
/*     */   }
/*     */   
/*     */   public String getPo_number() {
/* 100 */     return this.po_number;
/*     */   }
/*     */   
/*     */   public void setPo_number(String po_number) {
/* 104 */     this.po_number = po_number;
/*     */   }
/*     */   
/*     */   public BigDecimal getPo_qty() {
/* 108 */     return this.po_qty;
/*     */   }
/*     */   
/*     */   public void setPo_qty(BigDecimal po_qty) {
/* 112 */     this.po_qty = po_qty;
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/* 116 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/* 120 */     this.part = part;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 124 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 128 */     this.id = id;
/*     */   }
/*     */   
/*     */   public PurchaseOrderInspectionPendingItem getPoipItem() {
/* 132 */     return this.poipItem;
/*     */   }
/*     */   
/*     */   public void setPoipItem(PurchaseOrderInspectionPendingItem poipItem) {
/* 136 */     this.poipItem = poipItem;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/* 140 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/* 144 */     this.location = location;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/* 148 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/* 152 */     this.date = date;
/*     */   }
/*     */   
/*     */   public BigDecimal getReceipts_qty() {
/* 156 */     return this.receipts_qty;
/*     */   }
/*     */   
/*     */   public void setReceipts_qty(BigDecimal receipts_qty) {
/* 160 */     this.receipts_qty = receipts_qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/* 164 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/* 168 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public YesNo getIs_sync() {
/* 172 */     return this.is_sync;
/*     */   }
/*     */   
/*     */   public void setIs_sync(YesNo is_sync) {
/* 176 */     this.is_sync = is_sync;
/*     */   }
/*     */   
/*     */   public PurchaseOrderPutInStorageStatus getStatus() {
/* 180 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(PurchaseOrderPutInStorageStatus status) {
/* 184 */     this.status = status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderPutInStorage() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderPutInStorage(Integer id) {
/* 199 */     setId(id);
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
/* 210 */     if (rhs == null)
/* 211 */       return false; 
/* 212 */     if (this == rhs)
/* 213 */       return true; 
/* 214 */     if (!(rhs instanceof PurchaseOrderPutInStorage))
/* 215 */       return false; 
/* 216 */     PurchaseOrderPutInStorage that = (PurchaseOrderPutInStorage)rhs;
/* 217 */     if (getId() != null)
/* 218 */       return getId().equals(that.getId()); 
/* 219 */     return (that.getId() == null);
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
/* 230 */     if (this.hashValue == 0) {
/* 231 */       int result = 17;
/* 232 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 233 */       result = result * 37 + poIdValue;
/* 234 */       this.hashValue = result;
/*     */     } 
/* 236 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPurchaseOrderPutInStorage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */