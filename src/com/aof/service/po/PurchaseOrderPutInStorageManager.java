package com.aof.service.po;

import com.aof.model.po.PurchaseOrderPutInStorage;
import com.aof.model.po.query.PurchaseOrderPutInStorageQueryOrder;
import java.util.List;
import java.util.Map;

public interface PurchaseOrderPutInStorageManager {
  PurchaseOrderPutInStorage getPurchaseOrderPutInStorage(Integer paramInteger);
  
  String getLastPoApplicationCode();
  
  String getMaxPoApplicationIdBeginWith(String paramString);
  
  int getPurchaseOrderPutInStorageListCount(Map paramMap);
  
  List getPurchaseOrderPutInStorageList(Map paramMap, int paramInt1, int paramInt2, PurchaseOrderPutInStorageQueryOrder paramPurchaseOrderPutInStorageQueryOrder, boolean paramBoolean);
  
  PurchaseOrderPutInStorage insertPurchaseOrderPutInStorage(PurchaseOrderPutInStorage paramPurchaseOrderPutInStorage);
  
  PurchaseOrderPutInStorage updatePurchaseOrderPutInStorage(PurchaseOrderPutInStorage paramPurchaseOrderPutInStorage);
  
  List getEnabledPurchaseOrderPutInStorageList();
  
  List getRecommendLocationList(String paramString1, String paramString2);
  
  String scanningPurchaseOrderPutInStorage(String paramString1, String paramString2, String paramString3);
  
  String scanningPurchaseOrderBLPInStorage(String paramString1, String paramString2, String paramString3);
  
  String scanningPurchaseOrderPutInStorages(String paramString1, String paramString2, String paramString3);
  
  List getRecommendLocationList();
  
  String scanningPurchaseOrderPutInStorageByRecommendLocation(String paramString);
  
  String scanningPurchaseOrderPutInStorageByOrderRecommendLocation(String paramString);
  
  String scanningPurchaseOrderBatchStorage(String paramString1, String paramString2, String paramString3);
  
  String scanningCustomerreturnInStorages(String paramString1, String paramString2, String paramString3);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/PurchaseOrderPutInStorageManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */