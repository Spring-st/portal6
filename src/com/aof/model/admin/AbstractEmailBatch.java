/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.YesNo;
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
/*     */ public abstract class AbstractEmailBatch
/*     */   implements Serializable
/*     */ {
/*  25 */   private int hashValue = 0;
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */   
/*     */   private User mailToUser;
/*     */ 
/*     */   
/*     */   private Site site;
/*     */ 
/*     */   
/*     */   private String templateName;
/*     */ 
/*     */   
/*     */   private String refNo;
/*     */ 
/*     */   
/*     */   private YesNo isSend;
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEmailBatch() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractEmailBatch(Integer id) {
/*  52 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  61 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  70 */     this.hashValue = 0;
/*  71 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public User getMailToUser() {
/*  78 */     return this.mailToUser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMailToUser(User mailToUser) {
/*  85 */     this.mailToUser = mailToUser;
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
/*  96 */     if (rhs == null)
/*  97 */       return false; 
/*  98 */     if (this == rhs)
/*  99 */       return true; 
/* 100 */     if (!(rhs instanceof Email))
/* 101 */       return false; 
/* 102 */     Email that = (Email)rhs;
/* 103 */     if (getId() != null)
/* 104 */       return getId().equals(that.getId()); 
/* 105 */     return (that.getId() == null);
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
/* 116 */     if (this.hashValue == 0) {
/* 117 */       int result = 17;
/* 118 */       int emailIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 119 */       result = result * 37 + emailIdValue;
/* 120 */       this.hashValue = result;
/*     */     } 
/* 122 */     return this.hashValue;
/*     */   }
/*     */   
/*     */   public Site getSite() {
/* 126 */     return this.site;
/*     */   }
/*     */   
/*     */   public void setSite(Site site) {
/* 130 */     this.site = site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTemplateName() {
/* 137 */     return this.templateName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTemplateName(String templateName) {
/* 144 */     this.templateName = templateName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public YesNo getIsSend() {
/* 151 */     return this.isSend;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsSend(YesNo isSend) {
/* 158 */     this.isSend = isSend;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRefNo() {
/* 165 */     return this.refNo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRefNo(String refNo) {
/* 172 */     this.refNo = refNo;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractEmailBatch.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */