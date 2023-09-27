/*     */ package com.aof.service.dataTransfer.impl;
/*     */ 
/*     */ import com.aof.model.admin.Customer;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.metadata.CustomerType;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
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
/*     */ 
/*     */ 
/*     */ public class ImportCustomerManagerImpl
/*     */   extends BaseImportManager
/*     */ {
/*     */   private static final int LINE_LENGTH = 69;
/*     */   private static final int CODE_BEGIN_INDEX = 0;
/*     */   private static final int CODE_END_INDEX = 17;
/*     */   private static final int DESC_BEGIN_INDEX = 17;
/*     */   private static final int DESC_END_INDEX = 67;
/*     */   private static final int TYPE_BEGIN_INDEX = 67;
/*     */   private static final int TYPE_END_INDEX = 68;
/*     */   private static final int ENABLE_BEGIN_INDEX = 68;
/*     */   private static final int ENABLE_END_INDEX = 69;
/*     */   private static final String CUSTOMER = "1";
/*     */   
/*     */   public void importFromTextFile(Site site, String localFileName) throws Exception {
/*  55 */     Map<Object, Object> customerMap = new HashMap<Object, Object>();
/*  56 */     for (Iterator<Customer> itor = this.dao.getCustomerList(site).iterator(); itor.hasNext(); ) {
/*  57 */       Customer customer = itor.next();
/*  58 */       customerMap.put(customer.getCode(), customer);
/*     */     } 
/*  60 */     BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(localFileName), "GB2312"));
/*     */     try {
/*     */       String line;
/*  63 */       while ((line = reader.readLine()) != null) {
/*  64 */         if (line.length() < 69)
/*  65 */           continue;  String code = getCode(line);
/*  66 */         boolean insert = false;
/*  67 */         Customer customer = (Customer)customerMap.remove(code);
/*  68 */         if (customer == null) {
/*  69 */           insert = true;
/*  70 */           customer = new Customer();
/*  71 */           customer.setCode(code);
/*     */         } 
/*  73 */         customer.setSite(site);
/*  74 */         customer.setDescription(getDesc(line));
/*  75 */         customer.setType(getType(line));
/*  76 */         customer.setEnabled(getEnable(line));
/*  77 */         this.dao.updateCustomer(customer, insert);
/*     */       } 
/*  79 */       for (Iterator<Customer> iterator = customerMap.values().iterator(); iterator.hasNext(); ) {
/*  80 */         Customer customer = iterator.next();
/*  81 */         if (EnabledDisabled.ENABLED.equals(customer.getEnabled())) {
/*  82 */           customer.setEnabled(EnabledDisabled.DISABLED);
/*  83 */           this.dao.updateCustomer(customer, false);
/*     */         } 
/*     */       } 
/*     */     } finally {
/*  87 */       reader.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void importFromXmlFile(Site site, String localFileName) throws Exception {}
/*     */ 
/*     */ 
/*     */   
/*     */   private String getCode(String line) {
/*  99 */     return line.substring(0, 17).trim();
/*     */   }
/*     */   
/*     */   private String getDesc(String line) {
/* 103 */     return line.substring(17, 67).trim();
/*     */   }
/*     */   
/*     */   private EnabledDisabled getEnable(String line) {
/* 107 */     return line.substring(68, 69).equals("1") ? EnabledDisabled.ENABLED : EnabledDisabled.DISABLED;
/*     */   }
/*     */   
/*     */   private CustomerType getType(String line) {
/* 111 */     return line.substring(67, 68).equals("1") ? CustomerType.CUSTOMER : CustomerType.ENTITY;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/dataTransfer/impl/ImportCustomerManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */