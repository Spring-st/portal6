/*    */ package com.aof.service.dataTransfer.impl;
/*    */ 
/*    */ import com.aof.model.admin.Site;
/*    */ import com.aof.model.admin.Supplier;
/*    */ import com.aof.model.metadata.ExportStatus;
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
/*    */ public class ExportSupplierManagerImpl
/*    */   extends BaseExportManager
/*    */ {
/*    */   public String exportTextFile(Site site) throws Exception {
/* 39 */     List supplierList = this.dao.getSupplierList(site);
/* 40 */     if (supplierList.size() > 0) {
/* 41 */       File tempFile = new File(getLocalFileName("supplier", "txt"));
/* 42 */       OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(tempFile), "GB2312");
/* 43 */       for (Iterator<Supplier> itorExpense = supplierList.iterator(); itorExpense.hasNext(); ) {
/* 44 */         Supplier supplier = itorExpense.next();
/* 45 */         writeSupplier(writer, supplier);
/* 46 */         supplier.setExportStatus(ExportStatus.EXPORTED);
/* 47 */         this.dao.updateSupplier(supplier);
/*    */       } 
/* 49 */       writer.close();
/* 50 */       return tempFile.getPath();
/*    */     } 
/* 52 */     return "";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String exportXmlFile(Site site) throws Exception {
/* 58 */     return null;
/*    */   }
/*    */   
/*    */   private void writeSupplier(OutputStreamWriter writer, Supplier supplier) throws IOException {
/* 62 */     writer.write(getFormatString(getValue(supplier, "code"), 8));
/* 63 */     writer.write(getFormatString(getValue(supplier, "name"), 50));
/* 64 */     writer.write(getFormatString(getValue(supplier, "address1"), 50));
/* 65 */     writer.write(getFormatString(getValue(supplier, "address2"), 50));
/* 66 */     writer.write(getFormatString(getValue(supplier, "address3"), 50));
/* 67 */     writer.write(getFormatString(getValue(supplier, "country.shortName"), 50));
/* 68 */     writer.write(getFormatString(getValue(supplier, "city.engName"), 50));
/* 69 */     writer.write(getFormatString(getValue(supplier, "post"), 9));
/* 70 */     writer.write(getFormatString(getValue(supplier, "attention1"), 24));
/* 71 */     writer.write(getFormatString(getValue(supplier, "attention2"), 24));
/* 72 */     writer.write(getFormatString(getValue(supplier, "telephone1"), 16));
/* 73 */     writer.write(getFormatString(getValue(supplier, "ext1"), 4));
/* 74 */     writer.write(getFormatString(getValue(supplier, "telephone2"), 16));
/* 75 */     writer.write(getFormatString(getValue(supplier, "ext2"), 4));
/* 76 */     writer.write(getFormatString(getValue(supplier, "fax1"), 16));
/* 77 */     writer.write(getFormatString(getValue(supplier, "fax2"), 16));
/* 78 */     writer.write(getFormatString(getValue(supplier, "purchaseAccount"), 8));
/* 79 */     writer.write(getFormatString(getValue(supplier, "purchaseSubAccount"), 8));
/* 80 */     writer.write(getFormatString(getValue(supplier, "purchaseCostCenter"), 8));
/* 81 */     writer.write(getFormatString(getValue(supplier, "apAccount"), 8));
/* 82 */     writer.write(getFormatString(getValue(supplier, "apSubAccount"), 8));
/* 83 */     writer.write(getFormatString(getValue(supplier, "apCostCenter"), 8));
/* 84 */     writer.write(getFormatString(getValue(supplier, "bank"), 8));
/* 85 */     writer.write(getFormatString(getValue(supplier, "currency.code"), 3));
/* 86 */     writer.write(getFormatString(getValue(supplier, "creditTerms"), 8));
/* 87 */     writer.write(getFormatString(getValue(supplier, "contact"), 20));
/* 88 */     writer.write("\n");
/*    */   }
/*    */   
/*    */   public String getRemoteExportFileName() {
/* 92 */     return getRemoteFileName("supplier", "txt");
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/dataTransfer/impl/ExportSupplierManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */