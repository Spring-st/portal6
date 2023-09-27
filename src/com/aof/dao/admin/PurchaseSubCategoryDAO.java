package com.aof.dao.admin;

import com.aof.model.admin.PurchaseSubCategory;
import com.aof.model.admin.query.PurchaseSubCategoryQueryOrder;
import java.util.List;
import java.util.Map;

public interface PurchaseSubCategoryDAO {
  PurchaseSubCategory getPurchaseSubCategory(Integer paramInteger);
  
  int getPurchaseSubCategoryListCount(Map paramMap);
  
  List getPurchaseSubCategoryList(Map paramMap, int paramInt1, int paramInt2, PurchaseSubCategoryQueryOrder paramPurchaseSubCategoryQueryOrder, boolean paramBoolean);
  
  PurchaseSubCategory insertPurchaseSubCategory(PurchaseSubCategory paramPurchaseSubCategory);
  
  PurchaseSubCategory updatePurchaseSubCategory(PurchaseSubCategory paramPurchaseSubCategory);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/PurchaseSubCategoryDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */