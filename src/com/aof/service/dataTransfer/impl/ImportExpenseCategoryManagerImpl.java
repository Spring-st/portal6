/*     */ package com.aof.service.dataTransfer.impl;
/*     */ 
/*     */ import com.aof.model.admin.ExpenseCategory;
/*     */ import com.aof.model.admin.ExpenseSubCategory;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.ExpenseType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public class ImportExpenseCategoryManagerImpl
/*     */   extends BaseImportManager
/*     */ {
/*     */   private static final int EXPENSE_LINE_LENGTH = 60;
/*     */   private static final int EXPENSE_SUB_LINE_LENGTH = 68;
/*     */   private static final int TYPE_BEGIN_INDEX = 0;
/*     */   private static final int TYPE_END_INDEX = 1;
/*     */   private static final int EXPENSE_CODE_BEGIN_INDEX = 1;
/*     */   private static final int EXPENSE_CODE_END_INDEX = 9;
/*     */   private static final int EXPENSE_DESC_BEGIN_INDEX = 9;
/*     */   private static final int EXPENSE_DESC_END_INDEX = 59;
/*     */   private static final int EXPENSE_ENABLE_BEGIN_INDEX = 59;
/*     */   private static final int EXPENSE_ENABLE_END_INDEX = 60;
/*     */   private static final int EXPENSE_SUB_CODE_BEGIN_INDEX = 1;
/*     */   private static final int EXPENSE_SUB_CODE_END_INDEX = 9;
/*     */   private static final int EXPENSE_SUB_SUBCODE_BEGIN_INDEX = 9;
/*     */   private static final int EXPENSE_SUB_SUBCODE_END_INDEX = 17;
/*     */   private static final int EXPENSE_SUB_DESC_BEGIN_INDEX = 17;
/*     */   private static final int EXPENSE_SUB_DESC_END_INDEX = 67;
/*     */   private static final int EXPENSE_SUB_ENABLE_BEGIN_INDEX = 67;
/*     */   private static final int EXPENSE_SUB_ENABLE_END_INDEX = 68;
/*     */   private static final String TYPE_EXPENSE = "1";
/*     */   private static final String TYPE_EXPENSE_SUB = "2";
/*  57 */   private static final ExpenseType DEFAULT_EXPENSE_TYPE = ExpenseType.OTHER;
/*  58 */   private static final YesNo DEFAULT_EXPENSE_SUB_IS_HOTEL = YesNo.NO;
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
/*     */   public void importFromTextFile(Site site, String localFileName) throws Exception {
/*  73 */     List<ExpenseCategory> expenseCategoryList = this.dao.getExpenseCategoryList(site);
/*  74 */     List<ExpenseSubCategory> expenseSubCategoryList = this.dao.getExpenseSubCategoryList(site);
/*  75 */     BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(localFileName), "GB2312"));
/*     */     try {
/*     */       String line;
/*  78 */       while ((line = reader.readLine()) != null) {
/*  79 */         if (line.length() < 1)
/*  80 */           continue;  if (getType(line).equals("1")) {
/*  81 */           if (line.length() < 60)
/*  82 */             continue;  String str = getExpenseCode(line);
/*  83 */           ExpenseCategory expenseCategory = getExpenseCategory(expenseCategoryList, str);
/*  84 */           boolean bool = false;
/*  85 */           if (expenseCategory == null) {
/*  86 */             bool = true;
/*  87 */             expenseCategory = new ExpenseCategory();
/*  88 */             expenseCategory.setType(DEFAULT_EXPENSE_TYPE);
/*     */           } 
/*  90 */           expenseCategory.setSite(site);
/*  91 */           expenseCategory.setReferenceErpId(str);
/*  92 */           expenseCategory.setEnabled(getExpenseEnable(line));
/*  93 */           expenseCategory.setDescription(getExpenseDesc(line));
/*  94 */           this.dao.updateExpenseCategory(expenseCategory, bool);
/*  95 */           if (bool) expenseCategoryList.add(expenseCategory);  continue;
/*     */         } 
/*  97 */         if (line.length() < 68)
/*  98 */           continue;  String erpId = getExpenseSubCode(line);
/*  99 */         ExpenseSubCategory expenseSubCategory = getExpenseSubCategory(expenseSubCategoryList, erpId);
/* 100 */         boolean insert = false;
/* 101 */         if (expenseSubCategory == null) {
/* 102 */           insert = true;
/* 103 */           expenseSubCategory = new ExpenseSubCategory();
/*     */         } 
/* 105 */         if (insert) {
/* 106 */           ExpenseCategory expenseCategory = getExpenseCategory(site, line);
/* 107 */           expenseSubCategory.setExpenseCategory(expenseCategory);
/* 108 */           expenseSubCategory.setIsHotel(DEFAULT_EXPENSE_SUB_IS_HOTEL);
/*     */         } 
/* 110 */         expenseSubCategory.setDescription(getExpenseSubDesc(line));
/* 111 */         expenseSubCategory.setEnabled(getExpenseSubEnable(line));
/* 112 */         expenseSubCategory.setReferenceErpId(erpId);
/* 113 */         this.dao.updateExpenseSubCategory(expenseSubCategory, insert);
/* 114 */         if (insert) expenseSubCategoryList.add(expenseSubCategory);
/*     */       
/*     */       } 
/*     */     } finally {
/* 118 */       reader.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ExpenseSubCategory getExpenseSubCategory(List expenseSubCategoryList, String erpId) {
/* 125 */     for (Iterator<ExpenseSubCategory> itor = expenseSubCategoryList.iterator(); itor.hasNext(); ) {
/* 126 */       ExpenseSubCategory expenseSubCategory = itor.next();
/* 127 */       if (expenseSubCategory.getReferenceErpId() != null && 
/* 128 */         expenseSubCategory.getReferenceErpId().equals(erpId)) {
/* 129 */         return expenseSubCategory;
/*     */       }
/*     */     } 
/* 132 */     return null;
/*     */   }
/*     */   
/*     */   private ExpenseCategory getExpenseCategory(List expenseCategoryList, String erpId) {
/* 136 */     for (Iterator<ExpenseCategory> itor = expenseCategoryList.iterator(); itor.hasNext(); ) {
/* 137 */       ExpenseCategory expenseCategory = itor.next();
/* 138 */       if (expenseCategory.getReferenceErpId() != null && 
/* 139 */         expenseCategory.getReferenceErpId().equals(erpId)) {
/* 140 */         return expenseCategory;
/*     */       }
/*     */     } 
/* 143 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void importFromXmlFile(Site site, String localFileName) throws Exception {}
/*     */ 
/*     */ 
/*     */   
/*     */   private String getType(String line) {
/* 154 */     return line.substring(0, 1).equals("1") ? "1" : "2";
/*     */   }
/*     */   
/*     */   private String getExpenseCode(String line) {
/* 158 */     return line.substring(1, 9).trim();
/*     */   }
/*     */   
/*     */   private String getExpenseSubCode(String line) {
/* 162 */     return line.substring(9, 17).trim();
/*     */   }
/*     */   
/*     */   private EnabledDisabled getExpenseEnable(String line) {
/* 166 */     return line.substring(59, 60).equals("1") ? EnabledDisabled.ENABLED : EnabledDisabled.DISABLED;
/*     */   }
/*     */   
/*     */   private EnabledDisabled getExpenseSubEnable(String line) {
/* 170 */     return line.substring(67, 68).equals("1") ? EnabledDisabled.ENABLED : EnabledDisabled.DISABLED;
/*     */   }
/*     */   
/*     */   private String getExpenseDesc(String line) {
/* 174 */     return line.substring(9, 59).trim();
/*     */   }
/*     */   
/*     */   private String getExpenseSubDesc(String line) {
/* 178 */     return line.substring(17, 67).trim();
/*     */   }
/*     */   
/*     */   private ExpenseCategory getExpenseCategory(Site site, String line) {
/* 182 */     return this.dao.getExpenseCategory(site, line.substring(1, 9).trim());
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/dataTransfer/impl/ImportExpenseCategoryManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */