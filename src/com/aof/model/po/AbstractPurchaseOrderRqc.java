/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.PurchaseOrderRqcStatus;
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
/*     */ public abstract class AbstractPurchaseOrderRqc
/*     */   implements Serializable
/*     */ {
/*  31 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   
/*     */   private Site site;
/*     */   
/*     */   private PurchaseOrderInspectionPendingItem poipItem;
/*     */   private PurchaseOrderCondimentSingle single;
/*     */   private PurchaseOrderRqcStatus status;
/*     */   private Date create_date;
/*     */   private BigDecimal qty;
/*     */   private BigDecimal need_qty_rqc;
/*     */   private BigDecimal actual_qty_rqc;
/*     */   private BigDecimal qualified_qty;
/*     */   private BigDecimal unqualified_qty;
/*     */   private BigDecimal rqc_type;
/*     */   private String remark;
/*     */   private WmsPart part;
/*     */   private Supplier supper;
/*     */   private Date po_date;
/*     */   private String line;
/*     */   private String po_number;
/*     */   private BigDecimal po_qty;
/*     */   private Box boxId;
/*     */   
/*     */   public Box getBoxId() {
/*  57 */     return this.boxId;
/*     */   }
/*     */   
/*     */   public void setBoxId(Box boxId) {
/*  61 */     this.boxId = boxId;
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/*  65 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  69 */     this.part = part;
/*     */   }
/*     */   
/*     */   public Supplier getSupper() {
/*  73 */     return this.supper;
/*     */   }
/*     */   
/*     */   public void setSupper(Supplier supper) {
/*  77 */     this.supper = supper;
/*     */   }
/*     */   
/*     */   public Date getPo_date() {
/*  81 */     return this.po_date;
/*     */   }
/*     */   
/*     */   public void setPo_date(Date po_date) {
/*  85 */     this.po_date = po_date;
/*     */   }
/*     */   
/*     */   public String getLine() {
/*  89 */     return this.line;
/*     */   }
/*     */   
/*     */   public void setLine(String line) {
/*  93 */     this.line = line;
/*     */   }
/*     */   
/*     */   public String getPo_number() {
/*  97 */     return this.po_number;
/*     */   }
/*     */   
/*     */   public void setPo_number(String po_number) {
/* 101 */     this.po_number = po_number;
/*     */   }
/*     */   
/*     */   public BigDecimal getPo_qty() {
/* 105 */     return this.po_qty;
/*     */   }
/*     */   
/*     */   public void setPo_qty(BigDecimal po_qty) {
/* 109 */     this.po_qty = po_qty;
/*     */   }
/*     */   
/*     */   public PurchaseOrderCondimentSingle getSingle() {
/* 113 */     return this.single;
/*     */   }
/*     */   
/*     */   public void setSingle(PurchaseOrderCondimentSingle single) {
/* 117 */     this.single = single;
/*     */   }
/*     */   
/*     */   public BigDecimal getNeed_qty_rqc() {
/* 121 */     return this.need_qty_rqc;
/*     */   }
/*     */   
/*     */   public void setNeed_qty_rqc(BigDecimal need_qty_rqc) {
/* 125 */     this.need_qty_rqc = need_qty_rqc;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 129 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 133 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/* 137 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/* 141 */     this.site = site;
/*     */   }
/*     */   
/*     */   public PurchaseOrderInspectionPendingItem getPoipItem() {
/* 145 */     return this.poipItem;
/*     */   }
/*     */   
/*     */   public void setPoipItem(PurchaseOrderInspectionPendingItem poipItem) {
/* 149 */     this.poipItem = poipItem;
/*     */   }
/*     */   
/*     */   public PurchaseOrderRqcStatus getStatus() {
/* 153 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(PurchaseOrderRqcStatus status) {
/* 157 */     this.status = status;
/*     */   }
/*     */   
/*     */   public Date getCreate_date() {
/* 161 */     return this.create_date;
/*     */   }
/*     */   
/*     */   public void setCreate_date(Date create_date) {
/* 165 */     this.create_date = create_date;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/* 169 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/* 173 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getActual_qty_rqc() {
/* 177 */     return this.actual_qty_rqc;
/*     */   }
/*     */   
/*     */   public void setActual_qty_rqc(BigDecimal actual_qty_rqc) {
/* 181 */     this.actual_qty_rqc = actual_qty_rqc;
/*     */   }
/*     */   
/*     */   public BigDecimal getRqc_type() {
/* 185 */     return this.rqc_type;
/*     */   }
/*     */   
/*     */   public void setRqc_type(BigDecimal rqc_type) {
/* 189 */     this.rqc_type = rqc_type;
/*     */   }
/*     */   
/*     */   public BigDecimal getQualified_qty() {
/* 193 */     return this.qualified_qty;
/*     */   }
/*     */   
/*     */   public void setQualified_qty(BigDecimal qualified_qty) {
/* 197 */     this.qualified_qty = qualified_qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getUnqualified_qty() {
/* 201 */     return this.unqualified_qty;
/*     */   }
/*     */   
/*     */   public void setUnqualified_qty(BigDecimal unqualified_qty) {
/* 205 */     this.unqualified_qty = unqualified_qty;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 209 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 213 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderRqc() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderRqc(Integer id) {
/* 228 */     setId(id);
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
/* 239 */     if (rhs == null)
/* 240 */       return false; 
/* 241 */     if (this == rhs)
/* 242 */       return true; 
/* 243 */     if (!(rhs instanceof PurchaseOrderRqc))
/* 244 */       return false; 
/* 245 */     PurchaseOrderRqc that = (PurchaseOrderRqc)rhs;
/* 246 */     if (getId() != null)
/* 247 */       return getId().equals(that.getId()); 
/* 248 */     return (that.getId() == null);
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
/* 259 */     if (this.hashValue == 0) {
/* 260 */       int result = 17;
/* 261 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 262 */       result = result * 37 + poIdValue;
/* 263 */       this.hashValue = result;
/*     */     } 
/* 265 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPurchaseOrderRqc.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */