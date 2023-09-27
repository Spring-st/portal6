/*     */ package com.aof.service.dataTransfer.impl;
/*     */ 
/*     */ import com.aof.dao.admin.DataTransferDAO;
/*     */ import com.aof.model.admin.DataTransferParameter;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.shcnc.struts.form.DateFormatter;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.net.ftp.FTPClient;
/*     */ import org.apache.commons.net.ftp.FTPReply;
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
/*     */ public class BaseDataTransferManager
/*     */   extends BaseManager
/*     */ {
/*     */   protected Log log;
/*     */   protected String folder;
/*     */   protected static final String FILE_ENCODE = "GB2312";
/*  39 */   protected static final DateFormatter fileFormatter = new DateFormatter(
/*  40 */       Date.class, "yyyyMMddHHmmssSSS");
/*     */   
/*  42 */   protected static final DecimalFormat decimalFormat = new DecimalFormat("##0.00");
/*     */   
/*  44 */   protected static final DateFormatter dateFormat = new DateFormatter(Date.class, "yyyyMMdd");
/*     */   
/*     */   protected static final String EOL = "\n";
/*     */   
/*  48 */   protected static final String EMPTY_STRING = null;
/*     */   
/*  50 */   protected static final String EMPTY_DATE = getSpace(8);
/*     */   
/*     */   protected static final String RECHARGE_NO = "0";
/*     */   
/*     */   protected static final String RECHARGE_YES = "1";
/*     */   
/*     */   protected static final String RECHARGE_TYPE_CUSTOMER = "1";
/*     */   protected static final String RECHARGE_TYPE_ENTITY = "2";
/*     */   protected static final String RECHARGE_TYPE_DEPARTMENT = "3";
/*     */   protected static final String RECHARGE_TYPE_PERSON = "4";
/*  60 */   protected static final String RECHARGE_EMPTY_CUSTOMER = null;
/*  61 */   protected static final String RECHARGE_EMPTY_ENTITY = null;
/*  62 */   protected static final String RECHARGE_EMPTY_PERSON = null;
/*  63 */   protected static final String RECHARGE_EMPTY_DEPARTMENT = null;
/*     */   
/*     */   protected static final String DISABLED = "0";
/*     */   
/*     */   protected static final String ENABLED = "1";
/*     */   
/*     */   protected static final int FTP_DEFAULT_PORT = 21;
/*     */   
/*     */   protected static final String FTP_DEFAULT_USERNAME = "anonymous";
/*     */   
/*     */   protected static final String FTP_DEFAULT_PASSWORD = "anonymous@anonymous.com";
/*     */   
/*     */   protected DataTransferDAO dao;
/*     */   
/*     */   public void setDataTransferDAO(DataTransferDAO dao) {
/*  78 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   protected String getFormatString(int intSrc, int length) {
/*  82 */     return getFormatString((new Integer(intSrc)).toString(), length);
/*     */   }
/*     */   
/*     */   protected String getFormatString(Object objSrc, int length) {
/*  86 */     return getFormatString((objSrc != null) ? objSrc.toString() : EMPTY_STRING, length);
/*     */   }
/*     */   
/*     */   protected String getFormatString(Integer integerSrc, int length) {
/*  90 */     return getFormatString((integerSrc != null) ? integerSrc.toString() : "0", length);
/*     */   }
/*     */   
/*     */   protected String getFormatString(String strSrc, int length) {
/*  94 */     String result = strSrc;
/*  95 */     if (result == null) return getSpace(length); 
/*  96 */     result.replace('\n', ' ');
/*     */     
/*  98 */     int count = 0;
/*  99 */     int index = 0;
/* 100 */     StringBuffer sb = new StringBuffer("");
/* 101 */     while (count < length && 
/* 102 */       index < result.length()) {
/* 103 */       char contentChar = result.charAt(index++);
/* 104 */       if (contentChar > 'ÿ') {
/* 105 */         count += 2;
/*     */       } else {
/* 107 */         count++;
/*     */       } 
/* 109 */       if (count <= length) {
/* 110 */         sb.append(contentChar);
/*     */       }
/*     */     } 
/* 113 */     if (count != length) {
/* 114 */       if (count < length) {
/* 115 */         sb.append(getSpace(length - count));
/*     */       } else {
/* 117 */         sb.append(getSpace(1));
/*     */       } 
/*     */     }
/* 120 */     return sb.toString();
/*     */   }
/*     */   
/*     */   protected static String getSpace(int length) {
/* 124 */     StringBuffer sb = new StringBuffer("");
/* 125 */     for (int i = 0; i < length; i++)
/* 126 */       sb.append(" "); 
/* 127 */     return sb.toString();
/*     */   }
/*     */   
/*     */   protected String getLocalFileName(String prefix, String postfix) {
/* 131 */     return String.valueOf(System.getProperty("java.io.tmpdir")) + File.separator + prefix + "_" + fileFormatter.format(new Date()) + "." + postfix;
/*     */   }
/*     */   
/*     */   protected String getRemoteFileName(String prefix, String postfix) {
/* 135 */     return String.valueOf(prefix) + "_" + fileFormatter.format(new Date()) + "." + postfix;
/*     */   }
/*     */   
/*     */   protected Object getValue(Object object, String name) {
/* 139 */     return BeanHelper.getBeanPropertyValue(object, name);
/*     */   }
/*     */   
/*     */   protected String getDateValue(Object object, String name, DateFormatter dateFormat) {
/* 143 */     Object value = getValue(object, name);
/* 144 */     if (value != null) {
/* 145 */       return dateFormat.format(value);
/*     */     }
/* 147 */     return EMPTY_DATE;
/*     */   }
/*     */   
/*     */   protected String getDecimalValue(Object object, String name, DecimalFormat decimalFormat) {
/* 151 */     Object value = getValue(object, name);
/* 152 */     if (value != null) {
/* 153 */       return decimalFormat.format(((BigDecimal)value).doubleValue());
/*     */     }
/* 155 */     return decimalFormat.format(0L);
/*     */   }
/*     */   
/*     */   protected void deleteLocalFile(String filePath) {
/* 159 */     File tempFile = new File(filePath);
/* 160 */     tempFile.delete();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean connectToFTP(FTPClient ftp, String server, int port, Log log) {
/*     */     try {
/* 166 */       ftp.setDefaultTimeout(30000);
/* 167 */       ftp.connect(server, port);
/* 168 */       log.info("Connected to " + server + ".");
/*     */ 
/*     */ 
/*     */       
/* 172 */       int reply = ftp.getReplyCode();
/*     */       
/* 174 */       if (!FTPReply.isPositiveCompletion(reply)) {
/*     */         
/* 176 */         ftp.disconnect();
/* 177 */         log.info("FTP server refused connection.");
/* 178 */         return false;
/*     */       } 
/* 180 */       ftp.setSoTimeout(60000);
/* 181 */       ftp.setDataTimeout(60000);
/* 182 */       return true;
/*     */     }
/* 184 */     catch (IOException e) {
/*     */       
/* 186 */       if (ftp.isConnected()) {
/*     */         
/*     */         try {
/* 189 */           ftp.disconnect();
/* 190 */         } catch (IOException iOException) {}
/*     */       }
/* 192 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String getFtpDir(DataTransferParameter para) {
/* 197 */     String ftpDir = para.getServerDir();
/* 198 */     if (this.folder.trim().length() != 0) {
/* 199 */       if (ftpDir.substring(ftpDir.length() - 1, ftpDir.length()).equals("/")) {
/* 200 */         ftpDir = String.valueOf(ftpDir) + this.folder;
/*     */       } else {
/* 202 */         ftpDir = String.valueOf(ftpDir) + "/" + this.folder;
/*     */       } 
/*     */     }
/* 205 */     return ftpDir;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/dataTransfer/impl/BaseDataTransferManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */