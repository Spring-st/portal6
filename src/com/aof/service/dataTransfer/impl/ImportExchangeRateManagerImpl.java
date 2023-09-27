/*    */ package com.aof.service.dataTransfer.impl;
/*    */ 
/*    */ import com.aof.model.admin.Currency;
/*    */ import com.aof.model.admin.ExchangeRate;
/*    */ import com.aof.model.admin.Site;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.math.BigDecimal;
/*    */ import java.util.Iterator;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImportExchangeRateManagerImpl
/*    */   extends BaseImportManager
/*    */ {
/*    */   private static final int LINE_LENGTH = 70;
/*    */   private static final int CODE_BEGIN_INDEX = 0;
/*    */   private static final int CODE_END_INDEX = 8;
/*    */   private static final int RATE_BEGIN_INDEX = 58;
/*    */   private static final int RATE_END_INDEX = 70;
/*    */   
/*    */   public void importFromTextFile(Site site, String localFileName) throws Exception {
/* 50 */     List<ExchangeRate> exchangeRateList = this.dao.getExchangeRateList(site);
/* 51 */     BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(localFileName), "GB2312"));
/*    */     try {
/*    */       String line;
/* 54 */       while ((line = reader.readLine()) != null) {
/* 55 */         if (line.length() < 70)
/* 56 */           continue;  Currency currency = getCurrency(line);
/* 57 */         ExchangeRate exchangeRate = getExchangeRate(exchangeRateList, currency);
/* 58 */         boolean insert = false;
/* 59 */         if (exchangeRate == null) {
/* 60 */           insert = true;
/* 61 */           exchangeRate = new ExchangeRate();
/*    */         } 
/* 63 */         exchangeRate.setSite(site);
/* 64 */         exchangeRate.setCurrency(currency);
/* 65 */         exchangeRate.setExchangeRate(getRate(line));
/* 66 */         this.dao.updateExchangeRate(exchangeRate, insert);
/* 67 */         exchangeRateList.add(exchangeRate);
/*    */       } 
/*    */     } finally {
/* 70 */       reader.close();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void importFromXmlFile(Site site, String localFileName) throws Exception {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Currency getCurrency(String line) {
/* 85 */     return this.dao.getCurrency(line.substring(0, 8).trim());
/*    */   }
/*    */   
/*    */   private BigDecimal getRate(String line) {
/* 89 */     return new BigDecimal(line.substring(58, 70).trim());
/*    */   }
/*    */   
/*    */   private ExchangeRate getExchangeRate(List exchangeRateList, Currency currency) {
/* 93 */     for (Iterator<ExchangeRate> itor = exchangeRateList.iterator(); itor.hasNext(); ) {
/* 94 */       ExchangeRate exchangeRate = itor.next();
/* 95 */       if (exchangeRate.getCurrency().equals(currency))
/* 96 */         return exchangeRate; 
/*    */     } 
/* 98 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/dataTransfer/impl/ImportExchangeRateManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */