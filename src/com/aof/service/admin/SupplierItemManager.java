package com.aof.service.admin;

import com.aof.model.admin.PurchaseSubCategory;
import com.aof.model.admin.Supplier;
import com.aof.model.admin.SupplierItem;
import com.aof.model.admin.query.SupplierItemQueryOrder;
import java.util.List;
import java.util.Map;

public interface SupplierItemManager {
  SupplierItem getSupplierItem(Integer paramInteger);
  
  SupplierItem insertSupplierItem(SupplierItem paramSupplierItem);
  
  SupplierItem updateSupplierItem(SupplierItem paramSupplierItem);
  
  int getSupplierItemListCount(Map paramMap);
  
  List getSupplierItemList(Map paramMap, int paramInt1, int paramInt2, SupplierItemQueryOrder paramSupplierItemQueryOrder, boolean paramBoolean);
  
  List getSupplierItemListBySupplier(Supplier paramSupplier);
  
  List getSupplierItemListGroupByPurchaseSubCategory(Supplier paramSupplier);
  
  void removeSupplierItem(Integer paramInteger);
  
  List getSupplierItemListConflictWithPromoteBySupplierGroupByPurchaseSubCategory(Supplier paramSupplier);
  
  List getSupplierItemListBySupplierPurchaseSubCategoryId(Integer paramInteger1, Integer paramInteger2);
  
  void changePurchaseSubCategoryBySupplierAndPurchaseSubCategory(Integer paramInteger1, Integer paramInteger2, PurchaseSubCategory paramPurchaseSubCategory);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/SupplierItemManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */