package com.aof.dao.admin;

import com.aof.dao.DAO;
import com.aof.model.admin.Currency;
import com.aof.model.admin.GlobalMailReminder;
import com.aof.model.admin.PurchaseSubCategory;
import com.aof.model.admin.Site;
import com.aof.model.admin.Supplier;
import com.aof.model.admin.query.SupplierQueryOrder;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SupplierDAO extends DAO {
  Supplier getSupplier(Integer paramInteger);
  
  Supplier getSupplierByCode(String paramString);
  
  int getSupplierListCount(Map paramMap);
  
  List getSupplierList(Map paramMap, int paramInt1, int paramInt2, SupplierQueryOrder paramSupplierQueryOrder, boolean paramBoolean);
  
  Supplier insertSupplier(Supplier paramSupplier);
  
  Supplier updateSupplier(Supplier paramSupplier);
  
  String getLastSupplierCode();
  
  boolean isCodeUsed(String paramString, Site paramSite);
  
  List getSuitableSupplierListForPurchase(Site paramSite, PurchaseSubCategory paramPurchaseSubCategory, List paramList);
  
  List getSuitableSupplierItemListForPurchase(Supplier paramSupplier, PurchaseSubCategory paramPurchaseSubCategory, Currency paramCurrency);
  
  List getSupplierMaintainerNotResponsedList(Site paramSite, Date paramDate, GlobalMailReminder paramGlobalMailReminder);
  
  List getSupplierPartSamplingRatioList(Map paramMap, int paramInt1, int paramInt2, SupplierQueryOrder paramSupplierQueryOrder, boolean paramBoolean);
  
  int getSupplierPartSamplingRatioCount(Map paramMap);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/SupplierDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */