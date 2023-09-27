/*     */ package com.aof.model.admin;
/*     */ 
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
/*     */ public abstract class AbstractExchangeRate
/*     */   implements Serializable
/*     */ {
/*  24 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */   
/*     */   private Currency currency;
/*     */ 
/*     */ 
/*     */   
/*     */   private Site site;
/*     */ 
/*     */   
/*     */   private BigDecimal exchangeRate;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractExchangeRate() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractExchangeRate(Integer id) {
/*  48 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  55 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  62 */     this.hashValue = 0;
/*  63 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Currency getCurrency() {
/*  70 */     return this.currency;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrency(Currency currency) {
/*  78 */     this.currency = currency;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/*  85 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(Site site) {
/*  93 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BigDecimal getExchangeRate() {
/* 102 */     return this.exchangeRate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExchangeRate(BigDecimal exchangeRate) {
/* 111 */     this.exchangeRate = exchangeRate;
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
/* 122 */     if (rhs == null)
/* 123 */       return false; 
/* 124 */     if (this == rhs)
/* 125 */       return true; 
/* 126 */     if (!(rhs instanceof ExchangeRate))
/* 127 */       return false; 
/* 128 */     ExchangeRate that = (ExchangeRate)rhs;
/* 129 */     if (getId() != null) {
/* 130 */       return getId().equals(that.getId());
/*     */     }
/* 132 */     return (that.getId() == null);
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
/* 143 */     if (this.hashValue == 0) {
/* 144 */       int result = 17;
/* 145 */       int currencyValue = (getCurrency() == null) ? 0 : getCurrency().hashCode();
/* 146 */       result = result * 37 + currencyValue;
/* 147 */       int tSiteValue = (getSite() == null) ? 0 : getSite().hashCode();
/* 148 */       result = result * 37 + tSiteValue;
/* 149 */       this.hashValue = result;
/*     */     } 
/* 151 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractExchangeRate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */