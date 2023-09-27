/*     */ package com.aof.model.po;
/*     */ 
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
/*     */ public abstract class AbstractPurchaseOrderReceiptsDetial
/*     */   implements Serializable
/*     */ {
/*  28 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private PurchaseOrderReceipts po_receipts_id;
/*     */   private PurchaseOrderInspectionPendingItem po_detial_id;
/*     */   private BigDecimal plan_number;
/*     */   private BigDecimal actual_number;
/*     */   private YesNo status;
/*     */   private String line;
/*     */   private String po_order;
/*     */   private String po_part;
/*     */   private Date date;
/*     */   private String remark;
/*     */   private String test1;
/*     */   private String test2;
/*     */   private String test3;
/*     */   
/*     */   public String getTest1() {
/*  45 */     return this.test1;
/*     */   }
/*     */   
/*     */   public void setTest1(String test1) {
/*  49 */     this.test1 = test1;
/*     */   }
/*     */   
/*     */   public String getTest2() {
/*  53 */     return this.test2;
/*     */   }
/*     */   
/*     */   public void setTest2(String test2) {
/*  57 */     this.test2 = test2;
/*     */   }
/*     */   
/*     */   public String getTest3() {
/*  61 */     return this.test3;
/*     */   }
/*     */   
/*     */   public void setTest3(String test3) {
/*  65 */     this.test3 = test3;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  69 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  73 */     this.date = date;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  77 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  81 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getHashValue() {
/*  85 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/*  89 */     this.hashValue = hashValue;
/*     */   }
/*     */   
/*     */   public PurchaseOrderReceipts getPo_receipts_id() {
/*  93 */     return this.po_receipts_id;
/*     */   }
/*     */   
/*     */   public void setPo_receipts_id(PurchaseOrderReceipts po_receipts_id) {
/*  97 */     this.po_receipts_id = po_receipts_id;
/*     */   }
/*     */   
/*     */   public PurchaseOrderInspectionPendingItem getPo_detial_id() {
/* 101 */     return this.po_detial_id;
/*     */   }
/*     */   
/*     */   public void setPo_detial_id(PurchaseOrderInspectionPendingItem po_detial_id) {
/* 105 */     this.po_detial_id = po_detial_id;
/*     */   }
/*     */   
/*     */   public BigDecimal getPlan_number() {
/* 109 */     return this.plan_number;
/*     */   }
/*     */   
/*     */   public void setPlan_number(BigDecimal plan_number) {
/* 113 */     this.plan_number = plan_number;
/*     */   }
/*     */   
/*     */   public BigDecimal getActual_number() {
/* 117 */     return this.actual_number;
/*     */   }
/*     */   
/*     */   public void setActual_number(BigDecimal actual_number) {
/* 121 */     this.actual_number = actual_number;
/*     */   }
/*     */   
/*     */   public YesNo getStatus() {
/* 125 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(YesNo status) {
/* 129 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getLine() {
/* 133 */     return this.line;
/*     */   }
/*     */   
/*     */   public void setLine(String line) {
/* 137 */     this.line = line;
/*     */   }
/*     */   
/*     */   public String getPo_order() {
/* 141 */     return this.po_order;
/*     */   }
/*     */   
/*     */   public void setPo_order(String po_order) {
/* 145 */     this.po_order = po_order;
/*     */   }
/*     */   
/*     */   public String getPo_part() {
/* 149 */     return this.po_part;
/*     */   }
/*     */   
/*     */   public void setPo_part(String po_part) {
/* 153 */     this.po_part = po_part;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/* 157 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/* 161 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderReceiptsDetial() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderReceiptsDetial(Integer id) {
/* 176 */     setId(id);
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
/* 187 */     if (rhs == null)
/* 188 */       return false; 
/* 189 */     if (this == rhs)
/* 190 */       return true; 
/* 191 */     if (!(rhs instanceof PurchaseOrderReceiptsDetial))
/* 192 */       return false; 
/* 193 */     PurchaseOrderReceiptsDetial that = (PurchaseOrderReceiptsDetial)rhs;
/* 194 */     if (getId() != null)
/* 195 */       return getId().equals(that.getId()); 
/* 196 */     return (that.getId() == null);
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
/* 207 */     if (this.hashValue == 0) {
/* 208 */       int result = 17;
/* 209 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 210 */       result = result * 37 + poIdValue;
/* 211 */       this.hashValue = result;
/*     */     } 
/* 213 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPurchaseOrderReceiptsDetial.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */