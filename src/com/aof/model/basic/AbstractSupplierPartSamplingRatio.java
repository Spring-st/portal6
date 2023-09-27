/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class AbstractSupplierPartSamplingRatio
/*     */   implements Serializable {
/*  12 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   private Supplier supplierId;
/*     */   private WmsPart part;
/*     */   private User user_id;
/*     */   private BigDecimal qty;
/*     */   private EnabledDisabled enabled;
/*     */   private Date date;
/*     */   private String test1;
/*     */   private String test2;
/*     */   
/*     */   public Integer getId() {
/*  25 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  29 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Supplier getSupplierId() {
/*  33 */     return this.supplierId;
/*     */   }
/*     */   
/*     */   public void setSupplierId(Supplier supplierId) {
/*  37 */     this.supplierId = supplierId;
/*     */   }
/*     */   
/*     */   public WmsPart getPart() {
/*  41 */     return this.part;
/*     */   }
/*     */   
/*     */   public void setPart(WmsPart part) {
/*  45 */     this.part = part;
/*     */   }
/*     */   
/*     */   public User getUser_id() {
/*  49 */     return this.user_id;
/*     */   }
/*     */   
/*     */   public void setUser_id(User user_id) {
/*  53 */     this.user_id = user_id;
/*     */   }
/*     */   
/*     */   public BigDecimal getQty() {
/*  57 */     return this.qty;
/*     */   }
/*     */   
/*     */   public void setQty(BigDecimal qty) {
/*  61 */     this.qty = qty;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getEnabled() {
/*  65 */     return this.enabled;
/*     */   }
/*     */   
/*     */   public void setEnabled(EnabledDisabled enabled) {
/*  69 */     this.enabled = enabled;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  73 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  77 */     this.date = date;
/*     */   }
/*     */   
/*     */   public String getTest1() {
/*  81 */     return this.test1;
/*     */   }
/*     */   
/*     */   public void setTest1(String test1) {
/*  85 */     this.test1 = test1;
/*     */   }
/*     */   
/*     */   public String getTest2() {
/*  89 */     return this.test2;
/*     */   }
/*     */   
/*     */   public void setTest2(String test2) {
/*  93 */     this.test2 = test2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSupplierPartSamplingRatio() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSupplierPartSamplingRatio(Integer id) {
/* 105 */     setId(id);
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
/* 116 */     if (rhs == null)
/* 117 */       return false; 
/* 118 */     if (this == rhs)
/* 119 */       return true; 
/* 120 */     if (!(rhs instanceof SupplierPartSamplingRatio))
/* 121 */       return false; 
/* 122 */     SupplierPartSamplingRatio that = (SupplierPartSamplingRatio)rhs;
/* 123 */     if (getId() != null)
/* 124 */       return getId().equals(that.getId()); 
/* 125 */     return (that.getId() == null);
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
/* 136 */     if (this.hashValue == 0) {
/* 137 */       int result = 17;
/* 138 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 139 */       result = result * 37 + poIdValue;
/* 140 */       this.hashValue = result;
/*     */     } 
/* 142 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractSupplierPartSamplingRatio.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */