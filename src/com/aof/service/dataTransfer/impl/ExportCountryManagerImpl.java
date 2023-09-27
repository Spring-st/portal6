/*    */ package com.aof.service.dataTransfer.impl;
/*    */ 
/*    */ import com.aof.model.admin.Country;
/*    */ import com.aof.model.admin.Site;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStreamWriter;
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
/*    */ public class ExportCountryManagerImpl
/*    */   extends BaseExportManager
/*    */ {
/*    */   public String exportTextFile(Site site) throws Exception {
/* 38 */     List countryList = this.dao.getCountryList(site);
/* 39 */     if (countryList.size() > 0) {
/* 40 */       File tempFile = new File(getLocalFileName("country", "txt"));
/* 41 */       OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(tempFile), "GB2312");
/* 42 */       for (Iterator<Country> itorExpense = countryList.iterator(); itorExpense.hasNext(); ) {
/* 43 */         Country country = itorExpense.next();
/* 44 */         writeCountry(writer, country);
/*    */       } 
/* 46 */       writer.close();
/* 47 */       return tempFile.getPath();
/*    */     } 
/* 49 */     return "";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String exportXmlFile(Site site) throws Exception {
/* 55 */     return null;
/*    */   }
/*    */   
/*    */   private void writeCountry(OutputStreamWriter writer, Country country) throws IOException {
/* 59 */     writer.write(getFormatString(getValue(country, "id"), 8));
/* 60 */     writer.write(getFormatString(getValue(country, "engName"), 28));
/* 61 */     writer.write(getFormatString(getValue(country, "shortName"), 3));
/* 62 */     writer.write("\n");
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRemoteExportFileName() {
/* 67 */     return "country.txt";
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/dataTransfer/impl/ExportCountryManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */