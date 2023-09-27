package com.aof.service.admin;

import com.aof.model.admin.PurchaseCategory;
import com.aof.model.admin.Site;
import com.aof.model.admin.query.PurchaseCategoryQueryOrder;
import java.util.List;
import java.util.Map;

public interface PurchaseCategoryManager {
  PurchaseCategory getPurchaseCategory(Integer paramInteger);
  
  PurchaseCategory insertPurchaseCategory(PurchaseCategory paramPurchaseCategory);
  
  PurchaseCategory updatePurchaseCategory(PurchaseCategory paramPurchaseCategory);
  
  int getPurchaseCategoryListCount(Map paramMap);
  
  List getPurchaseCategoryList(Map paramMap, int paramInt1, int paramInt2, PurchaseCategoryQueryOrder paramPurchaseCategoryQueryOrder, boolean paramBoolean);
  
  List getEnabledPurchaseCategoryOfSite(Site paramSite);
  
  List getEnabledPurchaseCategoryOfSiteIncludeGlobal(Site paramSite);
  
  List getEnabledPurchaseCategorySubCategoryOfSite(Site paramSite);
  
  List getEnabledPurchaseCategorySubCategoryOfSiteIncludeGlobal(Site paramSite);
  
  List getEnabledPurchaseCategorySubCategoryOfGlobal();
  
  void fillEnabledPurchaseCategorySubCategoryForSiteList(List paramList);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/PurchaseCategoryManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */