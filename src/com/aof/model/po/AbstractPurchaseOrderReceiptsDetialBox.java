/*     */ package com.aof.model.po;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public abstract class AbstractPurchaseOrderReceiptsDetialBox
/*     */   implements Serializable
/*     */ {
/*  24 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private Box box_id;
/*     */   private PurchaseOrderInspectionPendingItem po_receipts_detial_id;
/*     */   
/*     */   public int getHashValue() {
/*  30 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/*  34 */     this.hashValue = hashValue;
/*     */   }
/*     */   
/*     */   public Box getBox_id() {
/*  38 */     return this.box_id;
/*     */   }
/*     */   
/*     */   public void setBox_id(Box box_id) {
/*  42 */     this.box_id = box_id;
/*     */   }
/*     */   
/*     */   public PurchaseOrderInspectionPendingItem getPo_receipts_detial_id() {
/*  46 */     return this.po_receipts_detial_id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPo_receipts_detial_id(PurchaseOrderInspectionPendingItem po_receipts_detial_id) {
/*  51 */     this.po_receipts_detial_id = po_receipts_detial_id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  55 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  59 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderReceiptsDetialBox() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderReceiptsDetialBox(Integer id) {
/*  74 */     setId(id);
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
/*  85 */     if (rhs == null)
/*  86 */       return false; 
/*  87 */     if (this == rhs)
/*  88 */       return true; 
/*  89 */     if (!(rhs instanceof PurchaseOrderReceiptsDetialBox))
/*  90 */       return false; 
/*  91 */     PurchaseOrderReceiptsDetialBox that = (PurchaseOrderReceiptsDetialBox)rhs;
/*  92 */     if (getId() != null)
/*  93 */       return getId().equals(that.getId()); 
/*  94 */     return (that.getId() == null);
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
/* 105 */     if (this.hashValue == 0) {
/* 106 */       int result = 17;
/* 107 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 108 */       result = result * 37 + poIdValue;
/* 109 */       this.hashValue = result;
/*     */     } 
/* 111 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPurchaseOrderReceiptsDetialBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */