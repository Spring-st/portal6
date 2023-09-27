/*     */ package com.aof.service.dataTransfer.impl;
/*     */ 
/*     */ import com.aof.model.admin.DataTransferParameter;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.metadata.ExportFileType;
/*     */ import com.aof.service.dataTransfer.ExportManager;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.net.ftp.FTPClient;
/*     */ import org.apache.commons.net.ftp.FTPConnectionClosedException;
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
/*     */ public abstract class BaseExportManager
/*     */   extends BaseDataTransferManager
/*     */   implements ExportManager
/*     */ {
/*     */   protected String exportClassName;
/*     */   
/*     */   public String getExportClassName() {
/*  34 */     return this.exportClassName;
/*     */   }
/*     */   public void exportFile(DataTransferParameter para) throws Exception {
/*     */     String localFileName;
/*  38 */     this.log.info("Begin exporting " + this.exportClassName + "!");
/*     */     
/*  40 */     if (para.getExportFileType().equals(ExportFileType.TEXT)) {
/*  41 */       localFileName = exportTextFile(para.getSite());
/*     */     } else {
/*  43 */       localFileName = exportXmlFile(para.getSite());
/*     */     } 
/*  45 */     if (localFileName.equals("")) {
/*  46 */       this.log.info("Find no " + this.exportClassName + " to export");
/*     */       return;
/*     */     } 
/*  49 */     this.log.info("Local file generated!FilePath:" + localFileName);
/*  50 */     String remoteFileName = getRemoteExportFileName();
/*     */     try {
/*  52 */       transferFileToServer(para, localFileName, remoteFileName);
/*  53 */     } catch (Exception e) {
/*  54 */       this.log.info("Fail to transfer local file to FTP Server.");
/*  55 */       throw e;
/*     */     } 
/*  57 */     this.log.info("Finish exporting " + this.exportClassName + "!");
/*  58 */     deleteLocalFile(localFileName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void transferFileToServer(DataTransferParameter para, String localFileName, String remoteFileName) throws Exception {
/*  64 */     FTPClient ftp = new FTPClient();
/*  65 */     String server = para.getServerAddress();
/*  66 */     int port = (para.getServerPort() == null) ? 21 : para.getServerPort().intValue();
/*  67 */     String userName = para.getServerUserName();
/*  68 */     String password = para.getServerPassword();
/*  69 */     if (userName == null) {
/*  70 */       userName = "anonymous";
/*  71 */       password = "anonymous@anonymous.com";
/*     */     } 
/*  73 */     String ftpDir = getFtpDir(para);
/*  74 */     if (!connectToFTP(ftp, server, port, this.log)) {
/*  75 */       throw new Exception("Could not connect to server.");
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  80 */       this.log.info("FTP login user:" + userName + " password:******");
/*  81 */       if (!ftp.login(userName, password)) {
/*     */         
/*  83 */         ftp.logout();
/*  84 */         throw new Exception("Error user name or password.");
/*     */       } 
/*  86 */       this.log.info("Remote system is " + ftp.getSystemName());
/*  87 */       if (ftpDir != null) {
/*  88 */         this.log.info("Change remote directory to " + ftpDir);
/*  89 */         if (!ftp.changeWorkingDirectory(ftpDir)) {
/*  90 */           this.log.info("FTP change dir error.No such directory:" + ftpDir + ".Try to create it!");
/*  91 */           if (!ftp.makeDirectory(ftpDir)) {
/*  92 */             ftp.logout();
/*  93 */             throw new Exception("Create directory error,logout!");
/*     */           } 
/*  95 */           this.log.info("Directory-->" + ftpDir + " has been created!");
/*     */           
/*  97 */           ftp.changeWorkingDirectory(ftpDir);
/*     */         } 
/*     */       } 
/*     */       
/* 101 */       ftp.setFileType(2);
/* 102 */       ftp.enterLocalPassiveMode();
/* 103 */       InputStream input = new FileInputStream(localFileName);
/*     */       
/* 105 */       if (!ftp.storeFile(remoteFileName, input)) {
/* 106 */         input.close();
/* 107 */         ftp.logout();
/* 108 */         this.log.info("File transfer:" + localFileName + "-->" + remoteFileName + ">>>error!");
/* 109 */         throw new Exception("Error sending file.");
/*     */       } 
/* 111 */       this.log.info("File transfer:" + localFileName + "-->" + remoteFileName + ">>>ok!");
/*     */       
/* 113 */       input.close();
/* 114 */       ftp.logout();
/*     */     }
/* 116 */     catch (FTPConnectionClosedException e) {
/*     */       
/* 118 */       throw new Exception("Server closed connection.");
/*     */     }
/* 120 */     catch (IOException e) {
/*     */       
/* 122 */       e.printStackTrace();
/* 123 */       throw new Exception("File transfer error.");
/*     */     }
/*     */     finally {
/*     */       
/* 127 */       if (ftp.isConnected())
/*     */         
/*     */         try {
/* 130 */           ftp.disconnect();
/* 131 */         } catch (IOException iOException) {} 
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract String exportTextFile(Site paramSite) throws Exception;
/*     */   
/*     */   public abstract String exportXmlFile(Site paramSite) throws Exception;
/*     */   
/*     */   public abstract String getRemoteExportFileName();
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/dataTransfer/impl/BaseExportManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */