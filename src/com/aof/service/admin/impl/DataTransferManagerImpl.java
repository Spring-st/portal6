/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.DataTransferDAO;
/*     */ import com.aof.model.admin.DataTransferParameter;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.DataTransferManager;
/*     */ import com.aof.service.admin.EmailManager;
/*     */ import com.aof.service.dataTransfer.ExportManager;
/*     */ import com.aof.service.dataTransfer.ImportManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class DataTransferManagerImpl
/*     */   extends BaseManager
/*     */   implements DataTransferManager
/*     */ {
/*     */   private DataTransferDAO dao;
/*     */   private Map map;
/*     */   private EmailManager emailManager;
/*     */   private ExportManager poExportManager;
/*     */   private ExportManager expenseExportManager;
/*     */   private ExportManager supplierExportManager;
/*     */   private ExportManager departmentExportManager;
/*     */   private ExportManager countryExportManager;
/*     */   private ExportManager poReceiptExportManager;
/*     */   private ImportManager purchaseTypeImportManager;
/*     */   private ImportManager expenseCategoryImportManager;
/*     */   private ImportManager customerImportManager;
/*     */   private ImportManager exchangeRateImportManager;
/*     */   
/*     */   public void setEmailManager(EmailManager emailManager) {
/*  58 */     this.emailManager = emailManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPoReceiptExportManager(ExportManager poReceiptExportManager) {
/*  66 */     this.poReceiptExportManager = poReceiptExportManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPoExportManager(ExportManager poExportManager) {
/*  74 */     this.poExportManager = poExportManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpenseExportManager(ExportManager expenseExportManager) {
/*  82 */     this.expenseExportManager = expenseExportManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupplierExportManager(ExportManager supplierExportManager) {
/*  91 */     this.supplierExportManager = supplierExportManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCountryExportManager(ExportManager countryExportManager) {
/* 100 */     this.countryExportManager = countryExportManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDepartmentExportManager(ExportManager departmentExportManager) {
/* 108 */     this.departmentExportManager = departmentExportManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCustomerImportManager(ImportManager customerImportManager) {
/* 118 */     this.customerImportManager = customerImportManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExchangeRateImportManager(ImportManager exchangeRateImportManager) {
/* 126 */     this.exchangeRateImportManager = exchangeRateImportManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpenseCategoryImportManager(ImportManager expenseCategoryImportManager) {
/* 134 */     this.expenseCategoryImportManager = expenseCategoryImportManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPurchaseTypeImportManager(ImportManager purchaseTypeImportManager) {
/* 142 */     this.purchaseTypeImportManager = purchaseTypeImportManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDataTransferDAO(DataTransferDAO dao) {
/* 150 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/* 158 */     this.map = new HashMap<Object, Object>();
/* 159 */     List paraList = this.dao.getDataTransferParameterList();
/* 160 */     for (Iterator<DataTransferParameter> itor = paraList.iterator(); itor.hasNext(); ) {
/* 161 */       DataTransferParameter para = itor.next();
/* 162 */       DataTransferThread dtThread = new DataTransferThread(this.emailManager, this, para);
/* 163 */       this.map.put(para, dtThread);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeJob(DataTransferParameter para) {
/* 169 */     DataTransferThread dtThread = (DataTransferThread)this.map.get(para);
/* 170 */     if (dtThread != null) {
/* 171 */       dtThread.setJobFinish(true);
/* 172 */       dtThread.interrupt();
/* 173 */       this.map.remove(para);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void startJob(DataTransferParameter para) {
/* 178 */     DataTransferThread dtThread = (DataTransferThread)this.map.get(para);
/* 179 */     if (dtThread != null) {
/* 180 */       dtThread.setManualJob(true);
/* 181 */       dtThread.interrupt();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void resetJob(DataTransferParameter para) {
/* 186 */     DataTransferThread dtThread = (DataTransferThread)this.map.get(para);
/* 187 */     if (dtThread == null) {
/* 188 */       dtThread = new DataTransferThread(this.emailManager, this, para);
/* 189 */       this.map.put(para, dtThread);
/*     */     } else {
/*     */       
/* 192 */       dtThread.setPara(para);
/* 193 */       dtThread.setResetJob(true);
/* 194 */       dtThread.interrupt();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateJobParameter(DataTransferParameter para) {
/* 199 */     DataTransferThread dtThread = (DataTransferThread)this.map.get(para);
/* 200 */     if (dtThread != null) {
/* 201 */       dtThread.setPara(para);
/*     */     }
/*     */   }
/*     */   
/*     */   public void destroy() {
/* 206 */     for (Iterator itor = this.map.keySet().iterator(); itor.hasNext(); ) {
/* 207 */       DataTransferThread dtThread = (DataTransferThread)this.map.get(itor.next());
/* 208 */       dtThread.setJobFinish(true);
/* 209 */       dtThread.interrupt();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void exportPurchaseOrder(DataTransferParameter para) throws Exception {
/* 215 */     this.poExportManager.exportFile(para);
/*     */   }
/*     */   
/*     */   public void exportExpense(DataTransferParameter para) throws Exception {
/* 219 */     this.expenseExportManager.exportFile(para);
/*     */   }
/*     */   
/*     */   public void exportSupplier(DataTransferParameter para) throws Exception {
/* 223 */     this.supplierExportManager.exportFile(para);
/*     */   }
/*     */   
/*     */   public void exportDepartment(DataTransferParameter para) throws Exception {
/* 227 */     this.departmentExportManager.exportFile(para);
/*     */   }
/*     */   
/*     */   public void exportCountry(DataTransferParameter para) throws Exception {
/* 231 */     this.countryExportManager.exportFile(para);
/*     */   }
/*     */   
/*     */   public void exportPurchaseOrderReceipt(DataTransferParameter para) throws Exception {
/* 235 */     this.poReceiptExportManager.exportFile(para);
/*     */   }
/*     */   
/*     */   public void importCustomer(DataTransferParameter para) throws Exception {
/* 239 */     this.customerImportManager.importFile(para);
/*     */   }
/*     */ 
/*     */   
/*     */   public void importExchangeRate(DataTransferParameter para) throws Exception {
/* 244 */     this.exchangeRateImportManager.importFile(para);
/*     */   }
/*     */ 
/*     */   
/*     */   public void importExpenseCategory(DataTransferParameter para) throws Exception {
/* 249 */     this.expenseCategoryImportManager.importFile(para);
/*     */   }
/*     */ 
/*     */   
/*     */   public void importPurchaseType(DataTransferParameter para) throws Exception {
/* 254 */     this.purchaseTypeImportManager.importFile(para);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/DataTransferManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */