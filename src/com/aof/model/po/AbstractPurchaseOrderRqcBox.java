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
/*     */ public abstract class AbstractPurchaseOrderRqcBox
/*     */   implements Serializable
/*     */ {
/*  23 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private Box box_id;
/*     */   private PurchaseOrderRqc rqc_id;
/*     */   
/*     */   public int getHashValue() {
/*  29 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/*  33 */     this.hashValue = hashValue;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  37 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  41 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Box getBox_id() {
/*  45 */     return this.box_id;
/*     */   }
/*     */   
/*     */   public void setBox_id(Box box_id) {
/*  49 */     this.box_id = box_id;
/*     */   }
/*     */   
/*     */   public PurchaseOrderRqc getRqc_id() {
/*  53 */     return this.rqc_id;
/*     */   }
/*     */   
/*     */   public void setRqc_id(PurchaseOrderRqc rqc_id) {
/*  57 */     this.rqc_id = rqc_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderRqcBox() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderRqcBox(Integer id) {
/*  72 */     setId(id);
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
/*  83 */     if (rhs == null)
/*  84 */       return false; 
/*  85 */     if (this == rhs)
/*  86 */       return true; 
/*  87 */     if (!(rhs instanceof PurchaseOrderRqcBox))
/*  88 */       return false; 
/*  89 */     PurchaseOrderRqcBox that = (PurchaseOrderRqcBox)rhs;
/*  90 */     if (getId() != null)
/*  91 */       return getId().equals(that.getId()); 
/*  92 */     return (that.getId() == null);
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
/* 103 */     if (this.hashValue == 0) {
/* 104 */       int result = 17;
/* 105 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 106 */       result = result * 37 + poIdValue;
/* 107 */       this.hashValue = result;
/*     */     } 
/* 109 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPurchaseOrderRqcBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */