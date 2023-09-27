package com.aof.dao.po;

import com.aof.dao.DAO;
import com.aof.model.po.PurchaseOrderPutInStorage;
import com.aof.model.po.query.PurchaseOrderPutInStorageQueryOrder;
import java.util.List;
import java.util.Map;

public interface PurchaseOrderPutInStorageDAO extends DAO {
  PurchaseOrderPutInStorage getPurchaseOrderPutInStorage(Integer paramInteger);
  
  String getLastPoApplicationCode();
  
  String getMaxPoApplicationIdBeginWith(String paramString);
  
  int getPurchaseOrderPutInStorageListCount(Map paramMap);
  
  List getPurchaseOrderPutInStorageList(Map paramMap, int paramInt1, int paramInt2, PurchaseOrderPutInStorageQueryOrder paramPurchaseOrderPutInStorageQueryOrder, boolean paramBoolean);
  
  PurchaseOrderPutInStorage insertPurchaseOrderPutInStorage(PurchaseOrderPutInStorage paramPurchaseOrderPutInStorage);
  
  PurchaseOrderPutInStorage updatePurchaseOrderPutInStorage(PurchaseOrderPutInStorage paramPurchaseOrderPutInStorage);
  
  List getEnabledPurchaseOrderPutInStorageList();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/PurchaseOrderPutInStorageDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */