package com.aof.dao.po;

import com.aof.dao.DAO;
import com.aof.model.po.PurchaseOrder;
import com.aof.model.po.PurchaseOrderDetial;
import com.aof.model.po.query.PurchaseOrderQueryOrder;
import java.util.List;
import java.util.Map;

public interface PurchaseOrderDAO extends DAO {
  PurchaseOrder getPurchaseOrder(String paramString);
  
  String getLastPoApplicationCode();
  
  String getMaxPoApplicationIdBeginWith(String paramString);
  
  int getPurchaseOrderListCount(Map paramMap);
  
  List getPurchaseOrderList(Map paramMap, int paramInt1, int paramInt2, PurchaseOrderQueryOrder paramPurchaseOrderQueryOrder, boolean paramBoolean);
  
  PurchaseOrder insertPurchaseOrder(PurchaseOrder paramPurchaseOrder);
  
  PurchaseOrder updatePurchaseOrder(PurchaseOrder paramPurchaseOrder);
  
  List getEnabledPurchaseOrderList();
  
  void deletePurchaseOrder(PurchaseOrder paramPurchaseOrder);
  
  List<PurchaseOrderDetial> getPurchaseOrderItemListByOrderId(String paramString);
  
  List getPurchaseOrderInspectionPendingItemListByPoipId(String paramString1, String paramString2);
  
  PurchaseOrderDetial getPurchaseOrderDetial(Integer paramInteger);
  
  PurchaseOrderDetial updatePurchaseOrderDetial(PurchaseOrderDetial paramPurchaseOrderDetial);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/PurchaseOrderDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */