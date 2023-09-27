/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.Blob;
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
/*     */ public abstract class AbstractSiteLogo
/*     */   implements Serializable
/*     */ {
/*  30 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */   
/*     */   private Blob logo;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSiteLogo() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSiteLogo(Integer id) {
/*  49 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  58 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  67 */     this.hashValue = 0;
/*  68 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Blob getLogo() {
/*  72 */     return this.logo;
/*     */   }
/*     */   
/*     */   public void setLogo(Blob logo) {
/*  76 */     this.logo = logo;
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
/*  87 */     if (rhs == null)
/*  88 */       return false; 
/*  89 */     if (this == rhs)
/*  90 */       return true; 
/*  91 */     if (!(rhs instanceof SiteLogo))
/*  92 */       return false; 
/*  93 */     SiteLogo that = (SiteLogo)rhs;
/*  94 */     if (getId() != null)
/*  95 */       return getId().equals(that.getId()); 
/*  96 */     return (that.getId() == null);
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
/* 107 */     if (this.hashValue == 0) {
/* 108 */       int result = 17;
/* 109 */       int siteIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 110 */       result = result * 37 + siteIdValue;
/* 111 */       this.hashValue = result;
/*     */     } 
/* 113 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractSiteLogo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */