package com.aof.service.admin;

import com.aof.model.admin.PurchaseCategory;
import com.aof.model.admin.PurchaseSubCategory;
import com.aof.model.admin.query.PurchaseSubCategoryQueryOrder;
import java.util.List;
import java.util.Map;

public interface PurchaseSubCategoryManager {
  PurchaseSubCategory getPurchaseSubCategory(Integer paramInteger);
  
  PurchaseSubCategory insertPurchaseSubCategory(PurchaseSubCategory paramPurchaseSubCategory);
  
  PurchaseSubCategory updatePurchaseSubCategory(PurchaseSubCategory paramPurchaseSubCategory);
  
  int getPurchaseSubCategoryListCount(Map paramMap);
  
  List getPurchaseSubCategoryList(Map paramMap, int paramInt1, int paramInt2, PurchaseSubCategoryQueryOrder paramPurchaseSubCategoryQueryOrder, boolean paramBoolean);
  
  List getEnabledPurchaseSubCategoryOfPurchaseCategory(PurchaseCategory paramPurchaseCategory);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/PurchaseSubCategoryManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */