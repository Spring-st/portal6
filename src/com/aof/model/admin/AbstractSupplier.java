/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.ExportStatus;
/*     */ import com.aof.model.metadata.SupplierConfirmStatus;
/*     */ import com.aof.model.metadata.YesNo;
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
/*     */ public abstract class AbstractSupplier
/*     */   extends BaseSupplier
/*     */   implements Serializable
/*     */ {
/*     */   private ExportStatus exportStatus;
/*     */   private String lastExportFile;
/*     */   private SupplierHistory history;
/*     */   private YesNo confirmed;
/*     */   private String promoteMessage;
/*     */   private SupplierConfirmStatus confirmStatus;
/*     */   private Date emailDate;
/*  46 */   private int emailTimes = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Date lastModifyDate;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSupplier() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSupplier(Integer id) {
/*  62 */     setId(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getEmailDate() {
/*  71 */     return this.emailDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEmailDate(Date emailDate) {
/*  78 */     this.emailDate = emailDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEmailTimes() {
/*  85 */     return this.emailTimes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEmailTimes(int emailTimes) {
/*  92 */     this.emailTimes = emailTimes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public YesNo getConfirmed() {
/*  99 */     return this.confirmed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConfirmed(YesNo confirmed) {
/* 106 */     this.confirmed = confirmed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExportStatus getExportStatus() {
/* 113 */     return this.exportStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExportStatus(ExportStatus exportStatus) {
/* 120 */     this.exportStatus = exportStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SupplierHistory getHistory() {
/* 127 */     return this.history;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHistory(SupplierHistory history) {
/* 134 */     this.history = history;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLastExportFile() {
/* 141 */     return this.lastExportFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLastExportFile(String lastExportFile) {
/* 148 */     this.lastExportFile = lastExportFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPromoteMessage() {
/* 155 */     return this.promoteMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SupplierConfirmStatus getConfirmStatus() {
/* 164 */     return this.confirmStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConfirmStatus(SupplierConfirmStatus confirmStatus) {
/* 171 */     this.confirmStatus = confirmStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPromoteMessage(String promoteMessage) {
/* 178 */     this.promoteMessage = promoteMessage;
/*     */   }
/*     */   
/*     */   public Date getLastModifyDate() {
/* 182 */     return this.lastModifyDate;
/*     */   }
/*     */   
/*     */   public void setLastModifyDate(Date lastModifyDate) {
/* 186 */     this.lastModifyDate = lastModifyDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 195 */     if (rhs == null)
/* 196 */       return false; 
/* 197 */     if (this == rhs)
/* 198 */       return true; 
/* 199 */     if (!(rhs instanceof Supplier))
/* 200 */       return false; 
/* 201 */     Supplier that = (Supplier)rhs;
/* 202 */     if (getId() != null)
/* 203 */       return getId().equals(that.getId()); 
/* 204 */     return (that.getId() == null);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractSupplier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */