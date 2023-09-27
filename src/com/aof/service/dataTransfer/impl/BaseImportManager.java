/*     */ package com.aof.service.dataTransfer.impl;
/*     */ 
/*     */ import com.aof.model.admin.DataTransferParameter;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.metadata.ExportFileType;
/*     */ import com.aof.service.dataTransfer.ImportManager;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Date;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BaseImportManager
/*     */   extends BaseDataTransferManager
/*     */   implements ImportManager
/*     */ {
/*     */   protected String importClassName;
/*     */   protected String remoteFileName;
/*     */   
/*     */   public String getImportClassName() {
/*  42 */     return this.importClassName;
/*     */   }
/*     */   
/*     */   protected String getLocalFileName() {
/*  46 */     String prefix = this.remoteFileName.substring(0, this.remoteFileName.indexOf("."));
/*  47 */     String postfix = this.remoteFileName.substring(this.remoteFileName.indexOf(".") + 1);
/*  48 */     return String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator + prefix + "_" + fileFormatter.format(new Date()) + "." + postfix;
/*     */   }
/*     */   public void importFile(DataTransferParameter para) throws Exception {
/*     */     String localFileName;
/*  52 */     this.log.info("Begin importing " + this.importClassName + "!");
/*     */     
/*     */     try {
/*  55 */       localFileName = transferFileFromServer(para, this.remoteFileName);
/*  56 */     } catch (Exception e) {
/*  57 */       this.log.info("Fail to transfer file " + this.remoteFileName + " from FTP Server.");
/*  58 */       throw e;
/*     */     } 
/*  60 */     if (localFileName.equals("")) {
/*  61 */       this.log.info("Find no file to import!");
/*     */       return;
/*     */     } 
/*  64 */     this.log.info("Local file generated!FilePath:" + localFileName);
/*     */     try {
/*  66 */       if (para.getExportFileType().equals(ExportFileType.TEXT)) {
/*  67 */         importFromTextFile(para.getSite(), localFileName);
/*     */       } else {
/*  69 */         importFromXmlFile(para.getSite(), localFileName);
/*     */       } 
/*     */     } finally {
/*     */       
/*  73 */       deleteLocalFile(localFileName);
/*     */     } 
/*  75 */     deleteFileFromServer(para, this.remoteFileName);
/*  76 */     this.log.info("Finish imorting " + this.importClassName + "!");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void deleteFileFromServer(DataTransferParameter para, String remoteFileName) {
/*  82 */     FTPClient ftp = new FTPClient();
/*  83 */     String server = para.getServerAddress();
/*  84 */     int port = (para.getServerPort() == null) ? 21 : para.getServerPort().intValue();
/*  85 */     String userName = para.getServerUserName();
/*  86 */     String password = para.getServerPassword();
/*  87 */     if (userName == null) {
/*  88 */       userName = "anonymous";
/*  89 */       password = "anonymous@anonymous.com";
/*     */     } 
/*  91 */     String ftpDir = getFtpDir(para);
/*  92 */     if (!connectToFTP(ftp, server, port, this.log)) {
/*  93 */       this.log.info("Delete remote file error!-->Could not connect to server");
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/*  98 */       this.log.info("FTP login user:" + userName + " password:******");
/*  99 */       if (!ftp.login(userName, password)) {
/*     */         
/* 101 */         ftp.logout();
/* 102 */         this.log.info("Delete remote file error!-->Error user name or password");
/*     */         return;
/*     */       } 
/* 105 */       this.log.info("Remote system is " + ftp.getSystemName());
/* 106 */       if (ftpDir != null) {
/* 107 */         this.log.info("Change remote directory to " + ftpDir);
/* 108 */         if (!ftp.changeWorkingDirectory(ftpDir)) {
/* 109 */           this.log.info("Delete remote file error!-->FTP change dir error.No such directory:" + ftpDir);
/*     */           return;
/*     */         } 
/*     */       } 
/* 113 */       ftp.deleteFile(remoteFileName);
/* 114 */       ftp.logout();
/*     */       
/*     */       return;
/* 117 */     } catch (FTPConnectionClosedException e) {
/*     */       
/* 119 */       this.log.info("Delete remote file error!-->Server closed connection");
/*     */       
/*     */       return;
/* 122 */     } catch (IOException e) {
/*     */       
/* 124 */       e.printStackTrace();
/* 125 */       this.log.info("Delete remote file error!");
/*     */     }
/*     */     finally {
/*     */       
/* 129 */       if (ftp.isConnected()) {
/*     */         
/*     */         try {
/* 132 */           ftp.disconnect();
/* 133 */         } catch (IOException iOException) {}
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private String transferFileFromServer(DataTransferParameter para, String remoteFileName) throws Exception {
/* 139 */     FTPClient ftp = new FTPClient();
/* 140 */     String server = para.getServerAddress();
/* 141 */     int port = (para.getServerPort() == null) ? 21 : para.getServerPort().intValue();
/* 142 */     String userName = para.getServerUserName();
/* 143 */     String password = para.getServerPassword();
/* 144 */     if (userName == null) {
/* 145 */       userName = "anonymous";
/* 146 */       password = "anonymous@anonymous.com";
/*     */     } 
/* 148 */     String ftpDir = getFtpDir(para);
/* 149 */     if (!connectToFTP(ftp, server, port, this.log)) {
/* 150 */       throw new Exception("Could not connect to server.");
/*     */     }
/*     */     
/*     */     try {
/* 154 */       this.log.info("FTP login user:" + userName + " password:******");
/* 155 */       if (!ftp.login(userName, password)) {
/*     */         
/* 157 */         ftp.logout();
/* 158 */         throw new Exception("Error user name or password.");
/*     */       } 
/* 160 */       this.log.info("Remote system is " + ftp.getSystemName());
/* 161 */       if (ftpDir != null) {
/* 162 */         this.log.info("Change remote directory to " + ftpDir);
/* 163 */         if (!ftp.changeWorkingDirectory(ftpDir)) {
/* 164 */           this.log.info("FTP change dir error.No such directory:" + ftpDir + ".Try to create it!");
/* 165 */           if (!ftp.makeDirectory(ftpDir)) {
/* 166 */             ftp.logout();
/* 167 */             throw new Exception("Create directory error,logout!");
/*     */           } 
/* 169 */           this.log.info("Directory-->" + ftpDir + " has been created!");
/*     */           
/* 171 */           ftp.changeWorkingDirectory(ftpDir);
/*     */         } 
/*     */       } 
/*     */       
/* 175 */       ftp.setFileType(2);
/* 176 */       ftp.enterLocalPassiveMode();
/*     */       
/* 178 */       String localFileName = getLocalFileName();
/* 179 */       OutputStream output = new FileOutputStream(localFileName);
/*     */       
/* 181 */       if (!ftp.retrieveFile(remoteFileName, output)) {
/* 182 */         int reply = ftp.getReplyCode();
/* 183 */         output.close();
/* 184 */         ftp.logout();
/* 185 */         deleteLocalFile(localFileName);
/* 186 */         if (reply == 550) {
/* 187 */           this.log.info("Does't find remote file " + remoteFileName + "!");
/* 188 */           return "";
/*     */         } 
/* 190 */         this.log.info("File transfer:" + remoteFileName + "-->" + localFileName + ">>>error!");
/* 191 */         throw new Exception("Error receiving file.");
/*     */       } 
/*     */       
/* 194 */       this.log.info("File transfer:" + remoteFileName + "-->" + localFileName + ">>>ok!");
/*     */ 
/*     */       
/* 197 */       output.close();
/*     */       
/* 199 */       ftp.logout();
/*     */       
/* 201 */       return localFileName;
/*     */     }
/* 203 */     catch (FTPConnectionClosedException e) {
/*     */       String localFileName;
/* 205 */       throw new Exception("Server closed connection.");
/*     */     }
/* 207 */     catch (IOException e) {
/*     */       
/* 209 */       e.printStackTrace();
/* 210 */       throw new Exception("File transfer error.");
/*     */     }
/*     */     finally {
/*     */       
/* 214 */       if (ftp.isConnected())
/*     */         
/*     */         try {
/* 217 */           ftp.disconnect();
/* 218 */         } catch (IOException iOException) {} 
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract void importFromTextFile(Site paramSite, String paramString) throws Exception;
/*     */   
/*     */   public abstract void importFromXmlFile(Site paramSite, String paramString) throws Exception;
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/dataTransfer/impl/BaseImportManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */