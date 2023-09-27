/*     */ package com.aof.model.po;
/*     */ 
/*     */ import com.aof.model.basic.BadReasons;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
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
/*     */ public abstract class AbstractPurchaseOrderRqcUnqualified
/*     */   implements Serializable
/*     */ {
/*  27 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private BadReasons reasons;
/*     */   private Box rqc_box_id;
/*     */   private BigDecimal qty;
/*     */   private String test1;
/*     */   private String test2;
/*     */   private String test3;
/*     */   
/*     */   public Integer getId() {
/*  37 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  41 */     this.id = id;
/*     */   }
/*     */   
/*     */   public BadReasons getReasons() {
/*  45 */     return this.reasons;
/*     */   }
/*     */   
/*     */   public void setReasons(BadReasons reasons) {
/*  49 */     this.reasons = reasons;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/*  53 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/*  57 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public String getTest1() {
/*  61 */     return this.test1;
/*     */   }
/*     */   
/*     */   public void setTest1(String test1) {
/*  65 */     this.test1 = test1;
/*     */   }
/*     */   
/*     */   public String getTest2() {
/*  69 */     return this.test2;
/*     */   }
/*     */   
/*     */   public void setTest2(String test2) {
/*  73 */     this.test2 = test2;
/*     */   }
/*     */   
/*     */   public String getTest3() {
/*  77 */     return this.test3;
/*     */   }
/*     */   
/*     */   public void setTest3(String test3) {
/*  81 */     this.test3 = test3;
/*     */   }
/*     */   
/*     */   public Box getRqc_box_id() {
/*  85 */     return this.rqc_box_id;
/*     */   }
/*     */   
/*     */   public void setRqc_box_id(Box rqc_box_id) {
/*  89 */     this.rqc_box_id = rqc_box_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderRqcUnqualified() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPurchaseOrderRqcUnqualified(Integer id) {
/* 104 */     setId(id);
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
/* 115 */     if (rhs == null)
/* 116 */       return false; 
/* 117 */     if (this == rhs)
/* 118 */       return true; 
/* 119 */     if (!(rhs instanceof PurchaseOrderRqcUnqualified))
/* 120 */       return false; 
/* 121 */     PurchaseOrderRqcUnqualified that = (PurchaseOrderRqcUnqualified)rhs;
/* 122 */     if (getId() != null)
/* 123 */       return getId().equals(that.getId()); 
/* 124 */     return (that.getId() == null);
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
/* 135 */     if (this.hashValue == 0) {
/* 136 */       int result = 17;
/* 137 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 138 */       result = result * 37 + poIdValue;
/* 139 */       this.hashValue = result;
/*     */     } 
/* 141 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/AbstractPurchaseOrderRqcUnqualified.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */