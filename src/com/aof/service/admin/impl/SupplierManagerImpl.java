/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.SupplierDAO;
/*     */ import com.aof.dao.admin.SupplierHistoryDAO;
/*     */ import com.aof.dao.basic.SupplierPartDAO;
/*     */ import com.aof.model.admin.BaseSupplier;
/*     */ import com.aof.model.admin.Currency;
/*     */ import com.aof.model.admin.PurchaseSubCategory;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.admin.SupplierHistory;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.query.SupplierQueryCondition;
/*     */ import com.aof.model.admin.query.SupplierQueryOrder;
/*     */ import com.aof.model.basic.FPSMaterialPrice;
/*     */ import com.aof.model.basic.SupplierPart;
/*     */ import com.aof.model.basic.SupplierPartSamplingRatio;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.metadata.CurrencyType;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.SupplierConfirmStatus;
/*     */ import com.aof.model.metadata.SupplierPromoteStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.SupplierManager;
/*     */ import com.aof.service.basic.WmsPartManager;
/*     */ import com.aof.web.struts.action.ActionUtils2;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import jxl.Cell;
/*     */ import jxl.Sheet;
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
/*     */ public class SupplierManagerImpl
/*     */   extends BaseManager
/*     */   implements SupplierManager
/*     */ {
/*     */   private SupplierDAO dao;
/*     */   private SupplierPartDAO supplierPartDAO;
/*     */   private SupplierHistoryDAO supplierHistoryDao;
/*     */   private WmsPartManager wmsPartManager;
/*     */   
/*     */   public void setWmsPartManager(WmsPartManager wmsPartManager) {
/*  58 */     this.wmsPartManager = wmsPartManager;
/*     */   }
/*     */   
/*     */   public void setSupplierPartDAO(SupplierPartDAO supplierPartDAO) {
/*  62 */     this.supplierPartDAO = supplierPartDAO;
/*     */   }
/*     */   
/*  65 */   private static String lastCode = null;
/*     */   
/*     */   private String getIncCode(String lastCode) {
/*  68 */     String tpStr = "000000" + ((new Integer(lastCode.substring(2, 8))).intValue() + 1);
/*  69 */     return "SP" + tpStr.substring(tpStr.length() - 6);
/*     */   }
/*     */   
/*     */   private synchronized String getLastCode(Site site) {
/*  73 */     if (lastCode == null) {
/*  74 */       lastCode = this.dao.getLastSupplierCode();
/*     */     }
/*  76 */     lastCode = getIncCode(lastCode);
/*  77 */     for (int repeatTimes = 0; repeatTimes < 10; repeatTimes++) {
/*  78 */       if (!this.dao.isCodeUsed(lastCode, site))
/*  79 */         return lastCode; 
/*  80 */       lastCode = getIncCode(this.dao.getLastSupplierCode());
/*     */     } 
/*  82 */     lastCode = null;
/*  83 */     return lastCode;
/*     */   }
/*     */   
/*     */   public void setSupplierDAO(SupplierDAO dao) {
/*  87 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public void setSupplierHistoryDAO(SupplierHistoryDAO supplierHistoryDao) {
/*  91 */     this.supplierHistoryDao = supplierHistoryDao;
/*     */   }
/*     */   
/*     */   public Supplier getSupplier(Integer id) {
/*  95 */     return this.dao.getSupplier(id);
/*     */   }
/*     */   
/*     */   public Supplier updateSupplier(Supplier supplier) {
/*  99 */     setContractDate(supplier);
/* 100 */     if (supplier.getConfirmed() == YesNo.YES) {
/* 101 */       Supplier oldSupplier = this.dao.getSupplier(supplier.getId());
/* 102 */       if (!supplier.contentEqual(oldSupplier)) {
/* 103 */         supplier.setConfirmed(YesNo.NO);
/* 104 */         supplier.setConfirmStatus(SupplierConfirmStatus.MODIFY);
/*     */       } 
/*     */     } 
/* 107 */     return this.dao.updateSupplier(supplier);
/*     */   }
/*     */   
/*     */   public Supplier insertSupplier(Supplier supplier) {
/* 111 */     if (supplier.getSite() == null) {
/* 112 */       supplier.setPromoteStatus(SupplierPromoteStatus.GLOBAL);
/*     */     } else {
/* 114 */       supplier.setPromoteStatus(SupplierPromoteStatus.SITE);
/* 115 */     }  setContractDate(supplier);
/* 116 */     supplier.setConfirmed(YesNo.NO);
/* 117 */     supplier.setConfirmStatus(SupplierConfirmStatus.NEW);
/* 118 */     String supplierCode = supplier.getCode();
/* 119 */     if (supplierCode == null) {
/* 120 */       supplierCode = getLastCode(supplier.getSite());
/* 121 */       if (supplierCode == null)
/* 122 */         throw new RuntimeException("error occurs when supplier get last code"); 
/*     */     } 
/* 124 */     supplier.setCode(supplierCode);
/* 125 */     return this.dao.insertSupplier(supplier);
/*     */   }
/*     */   
/*     */   public int getSupplierListCount(Map conditions) {
/* 129 */     return this.dao.getSupplierListCount(conditions);
/*     */   }
/*     */   
/*     */   public List getSupplierList(Map conditions, int pageNo, int pageSize, SupplierQueryOrder order, boolean descend) {
/* 133 */     return this.dao.getSupplierList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */   
/*     */   private void setContractDate(Supplier supplier) {
/* 137 */     if (supplier.getContractStartDate() != null)
/* 138 */       supplier.setContractStartDate(ActionUtils2.getStartDate(supplier.getContractStartDate()));
/* 139 */     if (supplier.getContractExpireDate() != null)
/* 140 */       supplier.setContractExpireDate(ActionUtils2.getExpireDate(supplier.getContractExpireDate()));
/*     */   }
/*     */   
/*     */   public void cancelSupplier(Supplier supplier) {
/* 144 */     if (supplier.getConfirmStatus() == SupplierConfirmStatus.NEW) {
/* 145 */       supplier.setEnabled(EnabledDisabled.DISABLED);
/*     */     }
/* 147 */     if (supplier.getConfirmStatus() == SupplierConfirmStatus.MODIFY) {
/* 148 */       SupplierHistory supplierHistory = this.supplierHistoryDao.getSupplierHistory(supplier.getId());
/* 149 */       supplier.copySupplier((BaseSupplier)supplierHistory);
/* 150 */       supplier.setConfirmed(YesNo.YES);
/*     */     } 
/* 152 */     this.dao.updateSupplier(supplier);
/*     */   }
/*     */   
/*     */   public void confirmSupplier(Supplier supplier) {
/* 156 */     Supplier oldSupplier = this.dao.getSupplier(supplier.getId());
/* 157 */     oldSupplier.setConfirmed(YesNo.YES);
/* 158 */     oldSupplier.setConfirmDate(new Date());
/* 159 */     this.supplierHistoryDao.copySupplier(oldSupplier);
/* 160 */     setContractDate(supplier);
/* 161 */     supplier.setConfirmed(YesNo.YES);
/* 162 */     supplier.setConfirmDate(new Date());
/* 163 */     this.dao.updateSupplier(supplier);
/*     */   }
/*     */   
/*     */   public Supplier requestPromote(Integer id, String promoteMessage) {
/* 167 */     Supplier supplier = getSupplier(id);
/* 168 */     if (!supplier.getPromoteStatus().equals(SupplierPromoteStatus.SITE)) {
/* 169 */       throw new RuntimeException("Promote Status error");
/*     */     }
/* 171 */     supplier.setPromoteMessage(promoteMessage);
/* 172 */     supplier.setPromoteStatus(SupplierPromoteStatus.REQUEST);
/* 173 */     supplier.setPromoteDate(new Date());
/* 174 */     updateSupplier(supplier);
/* 175 */     return supplier;
/*     */   }
/*     */   
/*     */   public Supplier responsePromote(Integer id) {
/* 179 */     Supplier supplier = getSupplier(id);
/* 180 */     if (!supplier.getPromoteStatus().equals(SupplierPromoteStatus.REQUEST)) {
/* 181 */       throw new RuntimeException("Promote Status error");
/*     */     }
/*     */ 
/*     */     
/* 185 */     supplier.setPromoteStatus(SupplierPromoteStatus.GLOBAL);
/* 186 */     updateSupplier(supplier);
/* 187 */     return supplier;
/*     */   }
/*     */   
/*     */   public void promoteCreate(Supplier supplier) {
/* 191 */     supplier.setPromoteStatus(SupplierPromoteStatus.GLOBAL);
/* 192 */     if (this.supplierHistoryDao.getSupplierHistory(supplier.getId()) != null) {
/* 193 */       supplier.setConfirmStatus(SupplierConfirmStatus.MODIFY);
/*     */     } else {
/* 195 */       supplier.setConfirmStatus(SupplierConfirmStatus.NEW);
/* 196 */     }  supplier.setConfirmed(YesNo.NO);
/* 197 */     setContractDate(supplier);
/* 198 */     this.dao.updateSupplier(supplier);
/*     */   }
/*     */   
/*     */   public void promoteDelete(Supplier supplier) {
/* 202 */     supplier.setPromoteStatus(SupplierPromoteStatus.SITE);
/* 203 */     this.dao.updateSupplier(supplier);
/*     */   }
/*     */   
/*     */   public List getEnabledAirTicketSuppliersForSiteAndIncludeGlobal(Site site) {
/* 207 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 208 */     conds.put(SupplierQueryCondition.GLOBAL_OR_SITE_ID_EQ, site.getId());
/* 209 */     conds.put(SupplierQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 210 */     conds.put(SupplierQueryCondition.IS_AIRTICKET, YesNo.YES.getEnumCode());
/* 211 */     return getSupplierList(conds, 0, -1, SupplierQueryOrder.NAME, false);
/*     */   }
/*     */   
/*     */   public List getEnabledNonAirTicketSuppliersForSiteAndIncludeGlobal(Site site) {
/* 215 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 216 */     conds.put(SupplierQueryCondition.GLOBAL_OR_SITE_ID_EQ, site.getId());
/* 217 */     conds.put(SupplierQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 218 */     conds.put(SupplierQueryCondition.IS_AIRTICKET, YesNo.NO.getEnumCode());
/* 219 */     return getSupplierList(conds, 0, -1, SupplierQueryOrder.NAME, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCodeUsed(String code, Site site) {
/* 229 */     return this.dao.isCodeUsed(code, site);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSuitableSupplierListForPurchase(Site site, PurchaseSubCategory psc, List exchangeRateList) {
/* 236 */     return this.dao.getSuitableSupplierListForPurchase(site, psc, exchangeRateList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSuitableSupplierItemListForPurchase(Supplier supplier, PurchaseSubCategory psc, Currency currency) {
/* 247 */     return this.dao.getSuitableSupplierItemListForPurchase(supplier, psc, currency);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledSupplierPartList(Map conditions, int pageNo, int pageSize, SupplierQueryOrder order, boolean descend) {
/* 253 */     return this.supplierPartDAO.getSupplierPartList(conditions, 0, -1, null, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSupplierPartListCount(Map condtions) {
/* 258 */     return this.supplierPartDAO.getSupplierPartListCount(condtions);
/*     */   }
/*     */   
/*     */   public List updateSupplierProportion(List<SupplierPart> list) {
/* 262 */     List<SupplierPart> newList = new ArrayList<SupplierPart>();
/* 263 */     for (SupplierPart supplierPart : list) {
/*     */       
/* 265 */       String sql = "from FPSMaterialPrice fsp where fsp.wmsPart.id = '" + supplierPart.getPartId().getId() + "' " + 
/* 266 */         " and fsp.supplier.code = '" + supplierPart.getSupplierId().getCode() + "' ";
/* 267 */       List<FPSMaterialPrice> listObj = this.supplierPartDAO.getObjectList(sql);
/*     */       
/* 269 */       if (listObj.size() > 0) {
/* 270 */         FPSMaterialPrice peric = listObj.get(0);
/* 271 */         supplierPart.setPrice(peric.getUnitPrice());
/* 272 */         supplierPart.setCurrencyType(peric.getCurrencyType());
/*     */       } else {
/* 274 */         supplierPart.setPrice(new BigDecimal(0));
/* 275 */         supplierPart.setCurrencyType(CurrencyType.YUAN);
/*     */       } 
/*     */       
/* 278 */       newList.add(supplierPart);
/*     */     } 
/*     */     
/* 281 */     return newList;
/*     */   }
/*     */ 
/*     */   
/*     */   public Supplier getSupplierByCode(String code) {
/* 286 */     return this.dao.getSupplierByCode(code);
/*     */   }
/*     */   
/*     */   public boolean validateSupplierPartProportion(Supplier supplier, String part) {
/* 290 */     boolean sign = false;
/* 291 */     WmsPart wmsPart = this.wmsPartManager.getWmsPart(part);
/* 292 */     String sql = "from SupplierPart sp where sp.supplierId.id = '" + supplier.getId() + "' and sp.partId.id = '" + wmsPart.getId() + "' ";
/* 293 */     List<SupplierPart> list = this.supplierPartDAO.getObjectList(sql);
/* 294 */     if (list.size() > 0) {
/* 295 */       sign = true;
/*     */     }
/*     */     
/* 298 */     return sign;
/*     */   }
/*     */   
/*     */   public BigDecimal getSupplierPartProportion(Supplier supplier, String part) {
/* 302 */     WmsPart wmsPart = this.wmsPartManager.getWmsPart(part);
/* 303 */     String sql = "from SupplierPart sp where sp.supplierId.id <> '" + supplier.getId() + "' and sp.partId.id = '" + wmsPart.getId() + "' ";
/* 304 */     List<SupplierPart> list = this.supplierPartDAO.getObjectList(sql);
/* 305 */     BigDecimal sumAmount = new BigDecimal(0);
/* 306 */     if (list.size() > 0) {
/* 307 */       for (SupplierPart supplierPart : list) {
/* 308 */         sumAmount = sumAmount.add(supplierPart.getProportion());
/*     */       }
/*     */     }
/*     */     
/* 312 */     return sumAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public SupplierPart insertSupplierPart(SupplierPart supplierPart) {
/* 317 */     return this.supplierPartDAO.saveSupplierPart(supplierPart);
/*     */   }
/*     */   
/*     */   public SupplierPart getSupplierPart(Integer id) {
/* 321 */     return this.supplierPartDAO.getSupplierPart(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public SupplierPart updateSupplierPart(SupplierPart supplierPart) {
/* 326 */     return this.supplierPartDAO.updateSupplierPart(supplierPart);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteSupplierPart(SupplierPart ur) {
/* 331 */     this.supplierPartDAO.removeSupplierPart(ur);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<SupplierPart> getSupplierPart(String partId) {
/* 336 */     return this.supplierPartDAO.getSupplierPart(partId);
/*     */   }
/*     */   
/*     */   public void insertSupplierPartSamplingRatio(Sheet[] sheet, User user) {
/* 340 */     int rowNum = 0; byte b; int i; Sheet[] arrayOfSheet;
/* 341 */     for (i = (arrayOfSheet = sheet).length, b = 0; b < i; ) { Sheet sh = arrayOfSheet[b];
/* 342 */       int k = 0;
/* 343 */       rowNum = sheet[k].getRows();
/* 344 */       for (int j = 1; j < rowNum; j++) {
/* 345 */         Cell[] cells = sh.getRow(j);
/* 346 */         String suppCode = cells[0].getContents();
/* 347 */         String part = cells[1].getContents();
/* 348 */         String qty = cells[2].getContents();
/*     */         
/* 350 */         Supplier supplier = this.dao.getSupplierByCode(suppCode);
/* 351 */         WmsPart wmsPart = this.wmsPartManager.getWmsPart(part);
/*     */         
/* 353 */         String sql = "from SupplierPartSamplingRatio sr where sr.supplierId.code = '" + supplier.getCode() + "' and sr.part.id='" + part + "' ";
/* 354 */         List<SupplierPartSamplingRatio> list = this.dao.getObjectList(sql);
/* 355 */         if (list.size() > 0) {
/* 356 */           SupplierPartSamplingRatio ratio = list.get(0);
/* 357 */           ratio.setQty(new BigDecimal(qty));
/* 358 */           ratio.setDate(new Date());
/* 359 */           this.dao.updateObject(ratio);
/*     */         } else {
/* 361 */           SupplierPartSamplingRatio ratio = new SupplierPartSamplingRatio();
/* 362 */           ratio.setPart(wmsPart);
/* 363 */           ratio.setDate(new Date());
/* 364 */           ratio.setUser_id(user);
/* 365 */           ratio.setSupplierId(supplier);
/* 366 */           ratio.setQty(new BigDecimal(qty));
/* 367 */           ratio.setEnabled(EnabledDisabled.ENABLED);
/* 368 */           this.dao.saveObject(ratio);
/*     */         } 
/*     */       } 
/*     */       b++; }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public List getSupplierPartSamplingRatioList(Map conditions, int pageNo, int pageSize, SupplierQueryOrder order, boolean descend) {
/* 377 */     return this.dao.getSupplierPartSamplingRatioList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSupplierPartSamplingRatioCount(Map condtions) {
/* 382 */     return this.dao.getSupplierPartSamplingRatioCount(condtions);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/SupplierManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */