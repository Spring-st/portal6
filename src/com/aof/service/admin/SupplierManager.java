package com.aof.service.admin;

import com.aof.model.admin.Currency;
import com.aof.model.admin.PurchaseSubCategory;
import com.aof.model.admin.Site;
import com.aof.model.admin.Supplier;
import com.aof.model.admin.User;
import com.aof.model.admin.query.SupplierQueryOrder;
import com.aof.model.basic.SupplierPart;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import jxl.Sheet;

public interface SupplierManager {
  Supplier getSupplier(Integer paramInteger);
  
  Supplier getSupplierByCode(String paramString);
  
  Supplier insertSupplier(Supplier paramSupplier);
  
  Supplier updateSupplier(Supplier paramSupplier);
  
  int getSupplierListCount(Map paramMap);
  
  List getSupplierList(Map paramMap, int paramInt1, int paramInt2, SupplierQueryOrder paramSupplierQueryOrder, boolean paramBoolean);
  
  void confirmSupplier(Supplier paramSupplier);
  
  void cancelSupplier(Supplier paramSupplier);
  
  Supplier requestPromote(Integer paramInteger, String paramString);
  
  Supplier responsePromote(Integer paramInteger);
  
  void promoteCreate(Supplier paramSupplier);
  
  void promoteDelete(Supplier paramSupplier);
  
  List getEnabledAirTicketSuppliersForSiteAndIncludeGlobal(Site paramSite);
  
  List getEnabledNonAirTicketSuppliersForSiteAndIncludeGlobal(Site paramSite);
  
  boolean isCodeUsed(String paramString, Site paramSite);
  
  List getSuitableSupplierListForPurchase(Site paramSite, PurchaseSubCategory paramPurchaseSubCategory, List paramList);
  
  List getSuitableSupplierItemListForPurchase(Supplier paramSupplier, PurchaseSubCategory paramPurchaseSubCategory, Currency paramCurrency);
  
  List getEnabledSupplierPartList(Map paramMap, int paramInt1, int paramInt2, SupplierQueryOrder paramSupplierQueryOrder, boolean paramBoolean);
  
  int getSupplierPartListCount(Map paramMap);
  
  List updateSupplierProportion(List<SupplierPart> paramList);
  
  boolean validateSupplierPartProportion(Supplier paramSupplier, String paramString);
  
  BigDecimal getSupplierPartProportion(Supplier paramSupplier, String paramString);
  
  SupplierPart insertSupplierPart(SupplierPart paramSupplierPart);
  
  SupplierPart getSupplierPart(Integer paramInteger);
  
  SupplierPart updateSupplierPart(SupplierPart paramSupplierPart);
  
  void deleteSupplierPart(SupplierPart paramSupplierPart);
  
  List<SupplierPart> getSupplierPart(String paramString);
  
  void insertSupplierPartSamplingRatio(Sheet[] paramArrayOfSheet, User paramUser);
  
  List getSupplierPartSamplingRatioList(Map paramMap, int paramInt1, int paramInt2, SupplierQueryOrder paramSupplierQueryOrder, boolean paramBoolean);
  
  int getSupplierPartSamplingRatioCount(Map paramMap);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/SupplierManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */