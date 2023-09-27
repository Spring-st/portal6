/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
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
/*     */ public abstract class AbstractFinishedToolPutnumber
/*     */   implements Serializable
/*     */ {
/*  23 */   private int hashValue = 0;
/*     */   
/*     */   private Integer id;
/*     */   
/*     */   private String toolCode;
/*     */   
/*     */   private String finishedCode;
/*     */   
/*     */   private BigDecimal putNumber;
/*     */   
/*     */   private EnabledDisabled status;
/*     */   
/*     */   public Integer getId() {
/*  36 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  40 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getToolCode() {
/*  44 */     return this.toolCode;
/*     */   }
/*     */   
/*     */   public void setToolCode(String toolCode) {
/*  48 */     this.toolCode = toolCode;
/*     */   }
/*     */   
/*     */   public BigDecimal getPutNumber() {
/*  52 */     return this.putNumber;
/*     */   }
/*     */   
/*     */   public void setPutNumber(BigDecimal putNumber) {
/*  56 */     this.putNumber = putNumber;
/*     */   }
/*     */   
/*     */   public String getFinishedCode() {
/*  60 */     return this.finishedCode;
/*     */   }
/*     */   
/*     */   public void setFinishedCode(String finishedCode) {
/*  64 */     this.finishedCode = finishedCode;
/*     */   }
/*     */   
/*     */   public EnabledDisabled getStatus() {
/*  68 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(EnabledDisabled status) {
/*  72 */     this.status = status;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractFinishedToolPutnumber() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractFinishedToolPutnumber(Integer id) {
/*  82 */     this.id = id;
/*     */   }
/*     */   
/*     */   public boolean equals(Object rhs) {
/*  86 */     if (rhs == null)
/*  87 */       return false; 
/*  88 */     if (this == rhs)
/*  89 */       return true; 
/*  90 */     if (!(rhs instanceof City))
/*  91 */       return false; 
/*  92 */     City that = (City)rhs;
/*  93 */     if (getId() != null)
/*  94 */       return getId().equals(that.getId()); 
/*  95 */     return (that.getId() == null);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  99 */     if (this.hashValue == 0) {
/* 100 */       int result = 17;
/* 101 */       int cityIdValue = (getId() == null) ? 0 : getId()
/* 102 */         .hashCode();
/* 103 */       result = result * 37 + cityIdValue;
/* 104 */       this.hashValue = result;
/*     */     } 
/* 106 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractFinishedToolPutnumber.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */