/*    */ package com.aof.service.dataTransfer.impl;
/*    */ 
/*    */ import com.aof.model.admin.PurchaseType;
/*    */ import com.aof.model.admin.Site;
/*    */ import com.aof.model.metadata.EnabledDisabled;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImportPurchaseTypeManagerImpl
/*    */   extends BaseImportManager
/*    */ {
/*    */   private static final int LINE_LENGTH = 59;
/*    */   private static final int CODE_BEGIN_INDEX = 0;
/*    */   private static final int CODE_END_INDEX = 8;
/*    */   private static final int DESC_BEGIN_INDEX = 8;
/*    */   private static final int DESC_END_INDEX = 58;
/*    */   private static final int ENABLE_BEGIN_INDEX = 58;
/*    */   private static final int ENABLE_END_INDEX = 59;
/*    */   
/*    */   public void importFromTextFile(Site site, String localFileName) throws Exception {
/* 47 */     List codeList = this.dao.getPurchaseTypeCodeList(site);
/* 48 */     BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(localFileName), "GB2312"));
/*    */     try {
/*    */       String line;
/* 51 */       while ((line = reader.readLine()) != null) {
/* 52 */         if (line.length() < 59)
/* 53 */           continue;  PurchaseType purchaseType = new PurchaseType();
/* 54 */         purchaseType.setSite(site);
/* 55 */         purchaseType.setCode(getCode(line));
/* 56 */         purchaseType.setDescription(getDesc(line));
/* 57 */         purchaseType.setEnabled(getEnable(line));
/* 58 */         this.dao.updatePurchaseType(purchaseType, !codeList.contains(purchaseType.getCode()));
/*    */       } 
/*    */     } finally {
/* 61 */       reader.close();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void importFromXmlFile(Site site, String localFileName) throws Exception {}
/*    */ 
/*    */ 
/*    */   
/*    */   private String getCode(String line) {
/* 73 */     return line.substring(0, 8).trim();
/*    */   }
/*    */   
/*    */   private String getDesc(String line) {
/* 77 */     return line.substring(8, 58).trim();
/*    */   }
/*    */   
/*    */   private EnabledDisabled getEnable(String line) {
/* 81 */     return line.substring(58, 59).equals("1") ? EnabledDisabled.ENABLED : EnabledDisabled.DISABLED;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/dataTransfer/impl/ImportPurchaseTypeManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */