/*     */ package com.aof.model.admin;
/*     */ 
/*     */ import com.aof.model.metadata.ExportFileType;
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
/*     */ public abstract class AbstractDataTransferParameter
/*     */   implements Serializable
/*     */ {
/*  27 */   private int hashValue = 0;
/*     */ 
/*     */   
/*     */   private Integer id;
/*     */ 
/*     */   
/*     */   private Site site;
/*     */ 
/*     */   
/*     */   private Date startTime;
/*     */ 
/*     */   
/*     */   private Integer timePerDay;
/*     */ 
/*     */   
/*     */   private Integer intervalMin;
/*     */ 
/*     */   
/*     */   private String succEmail;
/*     */ 
/*     */   
/*     */   private String failEmail;
/*     */ 
/*     */   
/*     */   private ExportFileType exportFileType;
/*     */ 
/*     */   
/*     */   private String serverAddress;
/*     */ 
/*     */   
/*     */   private Integer serverPort;
/*     */ 
/*     */   
/*     */   private String serverUserName;
/*     */   
/*     */   private String serverPassword;
/*     */   
/*     */   private String serverDir;
/*     */ 
/*     */   
/*     */   public AbstractDataTransferParameter() {}
/*     */ 
/*     */   
/*     */   public AbstractDataTransferParameter(Site site) {
/*  71 */     setSite(site);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getId() {
/*  78 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(Integer id) {
/*  86 */     this.hashValue = 0;
/*  87 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Site getSite() {
/*  96 */     return this.site;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSite(Site site) {
/* 105 */     this.site = site;
/* 106 */     if (site == null) {
/* 107 */       setId(null);
/*     */     } else {
/* 109 */       setId(site.getId());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFailEmail() {
/* 117 */     return this.failEmail;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFailEmail(String failEmail) {
/* 125 */     this.failEmail = failEmail;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getIntervalMin() {
/* 132 */     return this.intervalMin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIntervalMin(Integer interval) {
/* 140 */     this.intervalMin = interval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date getStartTime() {
/* 147 */     return this.startTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartTime(Date startTime) {
/* 155 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSuccEmail() {
/* 162 */     return this.succEmail;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSuccEmail(String succEmail) {
/* 170 */     this.succEmail = succEmail;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getTimePerDay() {
/* 177 */     return this.timePerDay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimePerDay(Integer timePerDay) {
/* 185 */     this.timePerDay = timePerDay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExportFileType getExportFileType() {
/* 192 */     return this.exportFileType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExportFileType(ExportFileType exportFileType) {
/* 199 */     this.exportFileType = exportFileType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getServerAddress() {
/* 208 */     return this.serverAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServerAddress(String serverAddress) {
/* 215 */     this.serverAddress = serverAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getServerDir() {
/* 222 */     return this.serverDir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServerDir(String serverDir) {
/* 229 */     this.serverDir = serverDir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getServerPassword() {
/* 236 */     return this.serverPassword;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServerPassword(String serverPassword) {
/* 243 */     this.serverPassword = serverPassword;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Integer getServerPort() {
/* 252 */     return this.serverPort;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServerPort(Integer serverPort) {
/* 259 */     this.serverPort = serverPort;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getServerUserName() {
/* 266 */     return this.serverUserName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServerUserName(String serverUserName) {
/* 273 */     this.serverUserName = serverUserName;
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
/* 284 */     if (this.hashValue == 0) {
/* 285 */       int result = 17;
/* 286 */       int idValue = (getId() == null) ? 0 : getId().hashCode();
/* 287 */       result = result * 37 + idValue;
/* 288 */       this.hashValue = result;
/*     */     } 
/* 290 */     return this.hashValue;
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
/* 301 */     if (rhs == null)
/* 302 */       return false; 
/* 303 */     if (this == rhs)
/* 304 */       return true; 
/* 305 */     if (!(rhs instanceof DataTransferParameter))
/* 306 */       return false; 
/* 307 */     DataTransferParameter that = (DataTransferParameter)rhs;
/* 308 */     if (getId() != null)
/* 309 */       return getId().equals(that.getId()); 
/* 310 */     return (that.getId() == null);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractDataTransferParameter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */