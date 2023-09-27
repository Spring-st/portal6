/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.basic.WmsPart;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractPurchaseOrderDetial
/*     */   implements Serializable
/*     */ {
/*  35 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private PurchaseOrder po_id;
/*     */   private WmsPart part;
/*     */   private Integer line;
/*     */   private String supp_part;
/*     */   private String unit;
/*     */   private Date due_date;
/*     */   private BigDecimal po_qty;
/*     */   private BigDecimal received_qty;
/*     */   private BigDecimal arrival_qty;
/*     */   private YesNo status;
/*     */   private String address;
/*     */   private BigDecimal capacity;
/*     */   private String remark;
/*     */   
/*     */   public PurchaseOrder getPo_id() {
/*  52 */     return this.po_id;
/*     */   }
/*     */   
/*     */   public void setPo_id(PurchaseOrder po_id) {
/*  56 */     this.po_id = po_id;
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/*  60 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  64 */     this.part = part;
/*     */   }
/*     */   
/*     */   public Integer getLine() {
/*  68 */     return this.line;
/*     */   }
/*     */   
/*     */   public void setLine(Integer line) {
/*  72 */     this.line = line;
/*     */   }
/*     */   
/*     */   public String getSupp_part() {
/*  76 */     return this.supp_part;
/*     */   }
/*     */   
/*     */   public void setSupp_part(String supp_part) {
/*  80 */     this.supp_part = supp_part;
/*     */   }
/*     */   
/*     */   public String getUnit() {
/*  84 */     return this.unit;
/*     */   }
/*     */   
/*     */   public void setUnit(String unit) {
/*  88 */     this.unit = unit;
/*     */   }
/*     */   
/*     */   public Date getDue_date() {
/*  92 */     return this.due_date;
/*     */   }
/*     */   
/*     */   public void setDue_date(Date due_date) {
/*  96 */     this.due_date = due_date;
/*     */   }
/*     */   
/*     */   public BigDecimal getPo_qty() {
/* 100 */     return this.po_qty;
/*     */   }
/*     */   
/*     */   public void setPo_qty(BigDecimal po_qty) {
/* 104 */     this.po_qty = po_qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getReceived_qty() {
/* 108 */     return this.received_qty;
/*     */   }
/*     */   
/*     */   public void setReceived_qty(BigDecimal received_qty) {
/* 112 */     this.received_qty = received_qty;
/*     */   }
/*     */   
/*     */   public BigDecimal getArrival_qty() {
/* 116 */     return this.arrival_qty;
/*     */   }
/*     */   
/*     */   public void setArrival_qty(BigDecimal arrival_qty) {
/* 120 */     this.arrival_qty = arrival_qty;
/*     */   }
/*     */   
/*     */   public YesNo getStatus() {
/* 124 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(YesNo status) {
/* 128 */     this.status = status;
/*     */   }
/*     */   
/*     */   public BigDecimal getCapacity() {
/* 132 */     return this.capacity;
/*     */   }
/*     */   
/*     */   public void setCapacity(BigDecimal capacity) {
/* 136 */     this.capacity = capacity;
/*     */   }
/*     */   
/*     */   public int getHashValue() {
/* 140 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 144 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 148 */     this.id = id;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/* 152 */     this.hashValue = hashValue;
/*     */   }
/*     */   
/*     */   public String getAddress() {
/* 156 */     return this.address;
/*     */   }
/*     */   
/*     */   public void setAddress(String address) {
/* 160 */     this.address = address;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderDetial() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderDetial(Integer id) {
/* 175 */     setId(id);
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
/* 186 */     if (rhs == null)
/* 187 */       return false; 
/* 188 */     if (this == rhs)
/* 189 */       return true; 
/* 190 */     if (!(rhs instanceof PurchaseOrderDetial))
/* 191 */       return false; 
/* 192 */     PurchaseOrderDetial that = (PurchaseOrderDetial)rhs;
/* 193 */     if (getId() != null)
/* 194 */       return getId().equals(that.getId()); 
/* 195 */     return (that.getId() == null);
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
/* 206 */     if (this.hashValue == 0) {
/* 207 */       int result = 17;
/* 208 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 209 */       result = result * 37 + poIdValue;
/* 210 */       this.hashValue = result;
/*     */     } 
/* 212 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 216 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 220 */     this.remark = remark;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPurchaseOrderDetial.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */