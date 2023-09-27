/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractCarNumber
/*     */   implements Serializable {
/*   8 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private String car_number;
/*     */   private String address;
/*     */   private String name;
/*     */   private EnabledDisabled status;
/*     */   private String remark;
/*     */   
/*     */   public Integer getId() {
/*  17 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  21 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getHashValue() {
/*  25 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public void setHashValue(int hashValue) {
/*  29 */     this.hashValue = hashValue;
/*     */   }
/*     */   
/*     */   public String getCar_number() {
/*  33 */     return this.car_number;
/*     */   }
/*     */   
/*     */   public void setCar_number(String car_number) {
/*  37 */     this.car_number = car_number;
/*     */   }
/*     */   
/*     */   public String getAddress() {
/*  41 */     return this.address;
/*     */   }
/*     */   
/*     */   public void setAddress(String address) {
/*  45 */     this.address = address;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  49 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  53 */     this.name = name;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getStatus() {
/*  57 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(EnabledDisabled status) {
/*  61 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getRemark() {
/*  65 */     return this.remark;
/*     */   }
/*     */   
/*     */   public void setRemark(String remark) {
/*  69 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractCarNumber(Integer id) {
/*  78 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractCarNumber() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/*  93 */     if (rhs == null)
/*  94 */       return false; 
/*  95 */     if (this == rhs)
/*  96 */       return true; 
/*  97 */     if (!(rhs instanceof CarNumber))
/*  98 */       return false; 
/*  99 */     CarNumber that = (CarNumber)rhs;
/* 100 */     if (getId() != null)
/* 101 */       return getId().equals(that.getId()); 
/* 102 */     return (that.getId() == null);
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
/* 113 */     if (this.hashValue == 0) {
/* 114 */       int result = 17;
/* 115 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 116 */       result = result * 37 + poIdValue;
/* 117 */       this.hashValue = result;
/*     */     } 
/* 119 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractCarNumber.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */