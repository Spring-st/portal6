/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.Clob;
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
/*     */ public abstract class AbstractEmailBody
/*     */   implements Serializable
/*     */ {
/*  24 */   private int hashValue = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */ 
/*     */   
/*     */   private Clob body;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEmailBody() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEmailBody(Integer id) {
/*  44 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  53 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
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
/*     */ 
/*     */   
/*     */   public Clob getBody() {
/*  72 */     return this.body;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBody(Clob body) {
/*  81 */     this.body = body;
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
/*  92 */     if (rhs == null)
/*  93 */       return false; 
/*  94 */     if (this == rhs)
/*  95 */       return true; 
/*  96 */     if (!(rhs instanceof Email))
/*  97 */       return false; 
/*  98 */     EmailBody that = (EmailBody)rhs;
/*  99 */     if (getId() != null)
/* 100 */       return getId().equals(that.getId()); 
/* 101 */     return (that.getId() == null);
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
/* 112 */     if (this.hashValue == 0) {
/* 113 */       int result = 17;
/* 114 */       int emailIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 115 */       result = result * 37 + emailIdValue;
/* 116 */       this.hashValue = result;
/*     */     } 
/* 118 */     return this.hashValue;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractEmailBody.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */