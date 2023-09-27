/*     */ package com.aof.model.basic;
/*     */ 
/*     */ import com.aof.model.admin.User;
/*     */ import java.io.Serializable;
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
/*     */ public abstract class AbstractScanLog
/*     */   implements Serializable
/*     */ {
/*  22 */   private int hashValue = 0;
/*     */   private Integer id;
/*     */   private String describe;
/*     */   private Date date;
/*     */   private Integer type;
/*     */   private User userId;
/*     */   
/*     */   public Integer getId() {
/*  30 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  34 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  38 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  42 */     this.date = date;
/*     */   }
/*     */   
/*     */   public Integer getType() {
/*  46 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(Integer type) {
/*  50 */     this.type = type;
/*     */   }
/*     */   
/*     */   public User getUserId() {
/*  54 */     return this.userId;
/*     */   }
/*     */   
/*     */   public void setUserId(User userId) {
/*  58 */     this.userId = userId;
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractScanLog() {}
/*     */   
/*     */   public AbstractScanLog(Integer id) {
/*  65 */     setId(id);
/*     */   }
/*     */   
/*     */   public boolean equals(Object rhs) {
/*  69 */     if (rhs == null)
/*  70 */       return false; 
/*  71 */     if (this == rhs)
/*  72 */       return true; 
/*  73 */     if (!(rhs instanceof ScanLog))
/*  74 */       return false; 
/*  75 */     ScanLog that = (ScanLog)rhs;
/*  76 */     if (getId() != null)
/*  77 */       return getId().equals(that.getId()); 
/*  78 */     return (that.getId() == null);
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
/*  89 */     if (this.hashValue == 0) {
/*  90 */       int result = 17;
/*  91 */       int cityIdValue = (getId() == null) ? 0 : getId()
/*  92 */         .hashCode();
/*  93 */       result = result * 37 + cityIdValue;
/*  94 */       this.hashValue = result;
/*     */     } 
/*  96 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public String getDescribe() {
/* 100 */     return this.describe;
/*     */   }
/*     */   
/*     */   public void setDescribe(String describe) {
/* 104 */     this.describe = describe;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractScanLog.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */