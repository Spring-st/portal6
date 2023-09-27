/*     */ package com.aof.service.basic.impl;
/*     */ 
/*     */ import com.aof.dao.basic.FPSMaterialPriceDAO;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.basic.FPSMaterialPrice;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.basic.query.FPSMaterialPriceQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.SupplierManager;
/*     */ import com.aof.service.basic.FPSMaterialPriceManager;
/*     */ import com.aof.service.basic.WmsPartManager;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import jxl.Cell;
/*     */ import jxl.Sheet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FPSMaterialPriceManagerImpl
/*     */   extends BaseManager
/*     */   implements FPSMaterialPriceManager
/*     */ {
/*     */   private FPSMaterialPriceDAO fpsMaterialPriceDAO;
/*     */   private WmsPartManager wmsPartManager;
/*     */   private SupplierManager supplierManager;
/*     */   
/*     */   public void setSupplierManager(SupplierManager supplierManager) {
/*  33 */     this.supplierManager = supplierManager;
/*     */   }
/*     */   
/*     */   public void setWmsPartManager(WmsPartManager wmsPartManager) {
/*  37 */     this.wmsPartManager = wmsPartManager;
/*     */   }
/*     */   
/*     */   public void setFpsMaterialPriceDAO(FPSMaterialPriceDAO fpsMaterialPriceDAO) {
/*  41 */     this.fpsMaterialPriceDAO = fpsMaterialPriceDAO;
/*     */   }
/*     */ 
/*     */   
/*     */   public FPSMaterialPrice getFPSMaterialPrice(Integer id) {
/*  46 */     return this.fpsMaterialPriceDAO.getFPSMaterialPrice(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFPSMaterialPriceListCount(Map conditions) {
/*  51 */     return this.fpsMaterialPriceDAO.getFPSMaterialPriceListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getFPSMaterialPriceList(Map conditions, int pageNo, int pageSize, FPSMaterialPriceQueryOrder order, boolean descend) {
/*  57 */     return this.fpsMaterialPriceDAO.getFPSMaterialPriceList(conditions, pageNo, 
/*  58 */         pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FPSMaterialPrice insertFPSMaterialPrice(FPSMaterialPrice fPSMaterialPrice) {
/*  64 */     return this.fpsMaterialPriceDAO.insertFPSMaterialPrice(fPSMaterialPrice);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FPSMaterialPrice updateFPSMaterialPrice(FPSMaterialPrice fPSMaterialPrice) {
/*  70 */     return this.fpsMaterialPriceDAO.updateFPSMaterialPrice(fPSMaterialPrice);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledFPSMaterialPriceList() {
/*  75 */     return this.fpsMaterialPriceDAO.getEnabledFPSMaterialPriceList();
/*     */   }
/*     */   
/*     */   public void deleteFPSMaterialPrice(FPSMaterialPrice fPSMaterialPrice) {
/*  79 */     this.fpsMaterialPriceDAO.deleteFPSMaterialPrice(fPSMaterialPrice);
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertFPSMaterialPrice(Sheet[] sheet) {
/*  84 */     this.fpsMaterialPriceDAO.updateFPSMaterialPriceAllEanble();
/*     */     
/*  86 */     int rowNum = 0; byte b; int i; Sheet[] arrayOfSheet;
/*  87 */     for (i = (arrayOfSheet = sheet).length, b = 0; b < i; ) { Sheet sh = arrayOfSheet[b];
/*  88 */       int k = 0;
/*  89 */       rowNum = sheet[k].getRows();
/*  90 */       for (int j = 1; j < rowNum; j++) {
/*  91 */         Cell[] cells = sh.getRow(j);
/*  92 */         if (cells.length > 1) {
/*  93 */           String suppCode = cells[0].getContents();
/*  94 */           String part = cells[1].getContents();
/*  95 */           String unitPrice = cells[2].getContents();
/*     */           
/*  97 */           if (suppCode != null && suppCode.equals("")) {
/*  98 */             Supplier supplier = this.supplierManager.getSupplierByCode(suppCode);
/*     */             
/*     */             try {
/* 101 */               if (unitPrice.equals("-") || unitPrice.equals("���޵���") || unitPrice.equals("")) {
/* 102 */                 unitPrice = "0";
/*     */               }
/* 104 */               if (!part.equals("")) {
/*     */                 
/* 106 */                 String sql = "from FPSMaterialPrice fmp where fmp.wmsPart.id= '" + part + "' and fmp.supplier.id=" + supplier.getId() + " ";
/* 107 */                 List<FPSMaterialPrice> materialPrices = this.fpsMaterialPriceDAO.getObjectList(sql);
/* 108 */                 if (materialPrices.size() > 0) {
/* 109 */                   FPSMaterialPrice price = materialPrices.get(0);
/* 110 */                   price.setUnitPrice(new BigDecimal(unitPrice));
/* 111 */                   price.setStartDate(new Date());
/* 112 */                   price.setEnabled(EnabledDisabled.ENABLED);
/*     */                   
/* 114 */                   this.fpsMaterialPriceDAO.updateFPSMaterialPrice(price);
/*     */                 } else {
/*     */                   
/* 117 */                   WmsPart wmsPart = this.wmsPartManager.getWmsPart(part);
/* 118 */                   if (wmsPart != null) {
/* 119 */                     FPSMaterialPrice price = new FPSMaterialPrice();
/* 120 */                     price.setDescription1(wmsPart.getDescribe1());
/* 121 */                     price.setDescription2(wmsPart.getDescribe2());
/* 122 */                     price.setWmsPart(wmsPart);
/* 123 */                     price.setUnitPrice(new BigDecimal(unitPrice));
/* 124 */                     price.setEnabled(EnabledDisabled.ENABLED);
/* 125 */                     price.setSupplier(supplier);
/* 126 */                     price.setStartDate(new Date());
/*     */                     
/* 128 */                     this.fpsMaterialPriceDAO.insertFPSMaterialPrice(price);
/*     */                   } else {
/* 130 */                     throw new ActionException("���ϺŲ��棬��ά����ϵͳ���ڵ�����ݣ�part=" + wmsPart + ",row=" + (j + 1));
/*     */                   } 
/*     */                 } 
/*     */               } 
/* 134 */             } catch (Exception e) {
/* 135 */               e.printStackTrace();
/*     */             } 
/*     */           } else {
/* 138 */             throw new ActionException("��Ӧ�̱�ű�����д����ά����ϵͳ���ڵ�����ݣ�");
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       b++; }
/*     */   
/*     */   }
/*     */   
/*     */   public FPSMaterialPrice getFPSMaterialPriceByPart(String partId) {
/* 147 */     return this.fpsMaterialPriceDAO.getFPSMaterialPriceByPart(partId);
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal getFPSMaterialPriceBydescribe2(String describe2) {
/* 152 */     return this.fpsMaterialPriceDAO.getFPSMaterialPriceBydescribe2(describe2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FPSMaterialPrice getFPSMaterialPriceByPartAndSupp(String part, Integer id) {
/* 158 */     return this.fpsMaterialPriceDAO.getFPSMaterialPriceByPartAndSupp(part, id);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/FPSMaterialPriceManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */