package com.aof.service.Product;

import com.aof.model.admin.Site;
import com.aof.model.admin.User;
import com.aof.model.po.Box;
import com.aof.model.product.SalesPreshiporder;
import com.aof.model.product.SalesPreshiporderBatch;
import com.aof.model.product.query.SalesPreshiporderQueryOrder;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SalesPreshiporderManager {
  SalesPreshiporder getById(Integer paramInteger);
  
  SalesPreshiporder insert(SalesPreshiporder paramSalesPreshiporder);
  
  void remove(SalesPreshiporder paramSalesPreshiporder);
  
  SalesPreshiporder update(SalesPreshiporder paramSalesPreshiporder);
  
  int getListCount(Map paramMap);
  
  List getList(Map paramMap, int paramInt1, int paramInt2, SalesPreshiporderQueryOrder paramSalesPreshiporderQueryOrder, boolean paramBoolean);
  
  SalesPreshiporder insertSalesPreshiporde(SalesPreshiporder paramSalesPreshiporder, Site paramSite, User paramUser, Date paramDate);
  
  void insertSalesPreshiporderIP(SalesPreshiporder paramSalesPreshiporder);
  
  Map<String, Object> getinvenDetialByPartBycount(String paramString1, String paramString2);
  
  void insertSalesPreshiporderByItem(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String paramString, User paramUser);
  
  String scanningSalesOutStock(String paramString1, String paramString2, String paramString3);
  
  Map<String, Object> insertSalesPreshiporderConfirm(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String paramString, User paramUser);
  
  Map<String, Object> insertCustomerPlanPreshiporderConfirm(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String paramString, User paramUser);
  
  void insertSalesPickingOrderReplaceBatchByItem(SalesPreshiporderBatch paramSalesPreshiporderBatch);
  
  void updateInventoryOccupiedDetial(Box paramBox);
  
  void insertInventoryOccupiedDetial(Box paramBox);
  
  void updateIsMatchStatus(String paramString);
  
  String scanningSalesStockToWithdraw(String paramString1, String paramString2, String paramString3, String paramString4);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/SalesPreshiporderManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */