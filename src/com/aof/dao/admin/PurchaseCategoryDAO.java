package com.aof.dao.admin;

import com.aof.model.admin.PurchaseCategory;
import com.aof.model.admin.query.PurchaseCategoryQueryOrder;
import java.util.List;
import java.util.Map;

public interface PurchaseCategoryDAO {
  PurchaseCategory getPurchaseCategory(Integer paramInteger);
  
  int getPurchaseCategoryListCount(Map paramMap);
  
  List getPurchaseCategoryList(Map paramMap, int paramInt1, int paramInt2, PurchaseCategoryQueryOrder paramPurchaseCategoryQueryOrder, boolean paramBoolean);
  
  PurchaseCategory insertPurchaseCategory(PurchaseCategory paramPurchaseCategory);
  
  PurchaseCategory updatePurchaseCategory(PurchaseCategory paramPurchaseCategory);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/PurchaseCategoryDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */