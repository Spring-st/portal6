/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.basic.WmsTool;
/*     */ import com.aof.model.metadata.BoxStatus;
/*     */ import com.aof.model.metadata.BoxStatusRqc;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import java.util.List;
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
/*     */ public abstract class AbstractBox
/*     */   implements Serializable
/*     */ {
/*  34 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   
/*     */   private WmsLot lot;
/*     */   
/*     */   private StorageLocation location;
/*     */   private WmsPart part;
/*     */   private BigDecimal number;
/*     */   private Date in_date;
/*     */   private Date out_date;
/*     */   private Integer print_number;
/*     */   private BoxStatus status;
/*     */   private BoxStatusRqc status_rqc;
/*     */   private YesNo status_print;
/*     */   private YesNo status_freeze;
/*     */   private EnabledDisabled enabled;
/*     */   private PortalShipOrderItem psoItem;
/*     */   private YesNo isReceipt;
/*     */   private YesNo isPrint;
/*     */   private BigDecimal receivedNumber;
/*     */   private BigDecimal vetoReceivedNumber;
/*     */   private BigDecimal inStorageNumber;
/*     */   private BigDecimal vetoQCnumber;
/*     */   private BigDecimal returnsNumber;
/*     */   private String supplierBatch;
/*     */   private YesNo isSync;
/*     */   private Date date;
/*     */   private String supplierCode;
/*     */   private YesNo type;
/*     */   private WmsTool tool;
/*     */   private PurchaseOrderCondimentSingle single;
/*     */   private String po_number;
/*     */   private String po_line;
/*     */   private Date po_date;
/*     */   private String po_supp;
/*     */   private String po_supp_name;
/*     */   private Date in_date_line;
/*     */   private List<PurchaseOrderRqcUnqualified> unqualifiedList;
/*     */   private String remark;
/*     */   private String huCodeOrderNumber;
/*     */   private YesNo Is_scan;
/*     */   
/*     */   public Date getIn_date_line() {
/*  78 */     return this.in_date_line;
/*     */   }
/*     */   
/*     */   public void setIn_date_line(Date in_date_line) {
/*  82 */     this.in_date_line = in_date_line;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public YesNo getIs_scan() {
/*  88 */     return this.Is_scan;
/*     */   }
/*     */   
/*     */   public void setIs_scan(YesNo is_scan) {
/*  92 */     this.Is_scan = is_scan;
/*     */   }
/*     */   
/*     */   public String getPo_supp_name() {
/*  96 */     return this.po_supp_name;
/*     */   }
/*     */   
/*     */   public void setPo_supp_name(String po_supp_name) {
/* 100 */     this.po_supp_name = po_supp_name;
/*     */   }
/*     */   
/*     */   public String getPo_supp() {
/* 104 */     return this.po_supp;
/*     */   }
/*     */   
/*     */   public void setPo_supp(String po_supp) {
/* 108 */     this.po_supp = po_supp;
/*     */   }
/*     */   
/*     */   public String getPo_number() {
/* 112 */     return this.po_number;
/*     */   }
/*     */   
/*     */   public void setPo_number(String po_number) {
/* 116 */     this.po_number = po_number;
/*     */   }
/*     */   
/*     */   public String getPo_line() {
/* 120 */     return this.po_line;
/*     */   }
/*     */   
/*     */   public void setPo_line(String po_line) {
/* 124 */     this.po_line = po_line;
/*     */   }
/*     */   
/*     */   public Date getPo_date() {
/* 128 */     return this.po_date;
/*     */   }
/*     */   
/*     */   public void setPo_date(Date po_date) {
/* 132 */     this.po_date = po_date;
/*     */   }
/*     */   
/*     */   public PurchaseOrderCondimentSingle getSingle() {
/* 136 */     return this.single;
/*     */   }
/*     */   
/*     */   public void setSingle(PurchaseOrderCondimentSingle single) {
/* 140 */     this.single = single;
/*     */   }
/*     */   
/*     */   public WmsTool getTool() {
/* 144 */     return this.tool;
/*     */   }
/*     */   
/*     */   public void setTool(WmsTool tool) {
/* 148 */     this.tool = tool;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/* 152 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/* 156 */     this.enabled = enabled;
/*     */   }
/*     */   
/*     */   public YesNo getType() {
/* 160 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(YesNo type) {
/* 164 */     this.type = type;
/*     */   }
/*     */   
/*     */   public String getSupplierCode() {
/* 168 */     return this.supplierCode;
/*     */   }
/*     */   
/*     */   public void setSupplierCode(String supplierCode) {
/* 172 */     this.supplierCode = supplierCode;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/* 176 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/* 180 */     this.date = date;
/*     */   }
/*     */   
/*     */   public PortalShipOrderItem getPsoItem() {
/* 184 */     return this.psoItem;
/*     */   }
/*     */   
/*     */   public void setPsoItem(PortalShipOrderItem psoItem) {
/* 188 */     this.psoItem = psoItem;
/*     */   }
/*     */   
/*     */   public YesNo getIsReceipt() {
/* 192 */     return this.isReceipt;
/*     */   }
/*     */   
/*     */   public void setIsReceipt(YesNo isReceipt) {
/* 196 */     this.isReceipt = isReceipt;
/*     */   }
/*     */   
/*     */   public YesNo getIsPrint() {
/* 200 */     return this.isPrint;
/*     */   }
/*     */   
/*     */   public void setIsPrint(YesNo isPrint) {
/* 204 */     this.isPrint = isPrint;
/*     */   }
/*     */   
/*     */   public BigDecimal getReceivedNumber() {
/* 208 */     return this.receivedNumber;
/*     */   }
/*     */   
/*     */   public void setReceivedNumber(BigDecimal receivedNumber) {
/* 212 */     this.receivedNumber = receivedNumber;
/*     */   }
/*     */   
/*     */   public BigDecimal getVetoReceivedNumber() {
/* 216 */     return this.vetoReceivedNumber;
/*     */   }
/*     */   
/*     */   public void setVetoReceivedNumber(BigDecimal vetoReceivedNumber) {
/* 220 */     this.vetoReceivedNumber = vetoReceivedNumber;
/*     */   }
/*     */   
/*     */   public BigDecimal getInStorageNumber() {
/* 224 */     return this.inStorageNumber;
/*     */   }
/*     */   
/*     */   public void setInStorageNumber(BigDecimal inStorageNumber) {
/* 228 */     this.inStorageNumber = inStorageNumber;
/*     */   }
/*     */   
/*     */   public BigDecimal getVetoQCnumber() {
/* 232 */     return this.vetoQCnumber;
/*     */   }
/*     */   
/*     */   public void setVetoQCnumber(BigDecimal vetoQCnumber) {
/* 236 */     this.vetoQCnumber = vetoQCnumber;
/*     */   }
/*     */   
/*     */   public BigDecimal getReturnsNumber() {
/* 240 */     return this.returnsNumber;
/*     */   }
/*     */   
/*     */   public void setReturnsNumber(BigDecimal returnsNumber) {
/* 244 */     this.returnsNumber = returnsNumber;
/*     */   }
/*     */   
/*     */   public String getSupplierBatch() {
/* 248 */     return this.supplierBatch;
/*     */   }
/*     */   
/*     */   public void setSupplierBatch(String supplierBatch) {
/* 252 */     this.supplierBatch = supplierBatch;
/*     */   }
/*     */   
/*     */   public YesNo getIsSync() {
/* 256 */     return this.isSync;
/*     */   }
/*     */   
/*     */   public void setIsSync(YesNo isSync) {
/* 260 */     this.isSync = isSync;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 264 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 268 */     this.id = id;
/*     */   }
/*     */   
/*     */   public WmsLot getLot() {
/* 272 */     return this.lot;
/*     */   }
/*     */   
/*     */   public void setLot(WmsLot lot) {
/* 276 */     this.lot = lot;
/*     */   }
/*     */   
/*     */   public StorageLocation getLocation() {
/* 280 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(StorageLocation location) {
/* 284 */     this.location = location;
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/* 288 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/* 292 */     this.part = part;
/*     */   }
/*     */   
/*     */   public BigDecimal getNumber() {
/* 296 */     return this.number;
/*     */   }
/*     */   
/*     */   public void setNumber(BigDecimal number) {
/* 300 */     this.number = number;
/*     */   }
/*     */   
/*     */   public Date getIn_date() {
/* 304 */     return this.in_date;
/*     */   }
/*     */   
/*     */   public void setIn_date(Date in_date) {
/* 308 */     this.in_date = in_date;
/*     */   }
/*     */   
/*     */   public Date getOut_date() {
/* 312 */     return this.out_date;
/*     */   }
/*     */   
/*     */   public void setOut_date(Date out_date) {
/* 316 */     this.out_date = out_date;
/*     */   }
/*     */   
/*     */   public Integer getPrint_number() {
/* 320 */     return this.print_number;
/*     */   }
/*     */   
/*     */   public void setPrint_number(Integer print_number) {
/* 324 */     this.print_number = print_number;
/*     */   }
/*     */   
/*     */   public BoxStatus getStatus() {
/* 328 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(BoxStatus status) {
/* 332 */     this.status = status;
/*     */   }
/*     */   
/*     */   public BoxStatusRqc getStatus_rqc() {
/* 336 */     return this.status_rqc;
/*     */   }
/*     */   
/*     */   public void setStatus_rqc(BoxStatusRqc status_rqc) {
/* 340 */     this.status_rqc = status_rqc;
/*     */   }
/*     */   
/*     */   public YesNo getStatus_print() {
/* 344 */     return this.status_print;
/*     */   }
/*     */   
/*     */   public void setStatus_print(YesNo status_print) {
/* 348 */     this.status_print = status_print;
/*     */   }
/*     */   
/*     */   public YesNo getStatus_freeze() {
/* 352 */     return this.status_freeze;
/*     */   }
/*     */   
/*     */   public void setStatus_freeze(YesNo status_freeze) {
/* 356 */     this.status_freeze = status_freeze;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractBox() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractBox(Integer id) {
/* 372 */     setId(id);
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
/* 383 */     if (rhs == null)
/* 384 */       return false; 
/* 385 */     if (this == rhs)
/* 386 */       return true; 
/* 387 */     if (!(rhs instanceof Box))
/* 388 */       return false; 
/* 389 */     Box that = (Box)rhs;
/* 390 */     if (getId() != null)
/* 391 */       return getId().equals(that.getId()); 
/* 392 */     return (that.getId() == null);
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
/* 403 */     if (this.hashValue == 0) {
/* 404 */       int result = 17;
/* 405 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 406 */       result = result * 37 + poIdValue;
/* 407 */       this.hashValue = result;
/*     */     } 
/* 409 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public List<PurchaseOrderRqcUnqualified> getUnqualifiedList() {
/* 413 */     return this.unqualifiedList;
/*     */   }
/*     */   
/*     */   public void setUnqualifiedList(List<PurchaseOrderRqcUnqualified> unqualifiedList) {
/* 417 */     this.unqualifiedList = unqualifiedList;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 421 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 425 */     this.remark = remark;
/*     */   }
/*     */   
/*     */   public int getHashValue() {
/* 429 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/* 433 */     this.hashValue = hashValue;
/*     */   }
/*     */   
/*     */   public String getHuCodeOrderNumber() {
/* 437 */     return this.huCodeOrderNumber;
/*     */   }
/*     */   
/*     */   public void setHuCodeOrderNumber(String huCodeOrderNumber) {
/* 441 */     this.huCodeOrderNumber = huCodeOrderNumber;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */