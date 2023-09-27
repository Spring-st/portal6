package com.aof.dao.po;

import com.aof.dao.DAO;
import com.aof.model.po.PurchaseOrderInspectionPending;
import com.aof.model.po.PurchaseOrderInspectionPendingItem;
import com.aof.model.po.query.PurchaseOrderInspectionPendingQueryOrder;
import java.util.List;
import java.util.Map;

public interface PurchaseOrderInspectionPendingDAO extends DAO {
  PurchaseOrderInspectionPending getPurchaseOrderInspectionPending(String paramString);
  
  String getLastPoApplicationCode();
  
  String getMaxPoApplicationIdBeginWith(String paramString);
  
  String getMaxPoApplicationIdBeginWithReceipts(String paramString);
  
  int getPurchaseOrderInspectionPendingListCount(Map paramMap);
  
  List getPurchaseOrderInspectionPendingList(Map paramMap, int paramInt1, int paramInt2, PurchaseOrderInspectionPendingQueryOrder paramPurchaseOrderInspectionPendingQueryOrder, boolean paramBoolean);
  
  PurchaseOrderInspectionPending insertPurchaseOrderInspectionPending(PurchaseOrderInspectionPending paramPurchaseOrderInspectionPending);
  
  PurchaseOrderInspectionPending updatePurchaseOrderInspectionPending(PurchaseOrderInspectionPending paramPurchaseOrderInspectionPending);
  
  List getEnabledPurchaseOrderInspectionPendingList();
  
  void deletePurchaseOrderInspectionPending(PurchaseOrderInspectionPending paramPurchaseOrderInspectionPending);
  
  int getPurchaseOrderInspectionPendingItemListCount(Map paramMap);
  
  List getPurchaseOrderInspectionPendingItemList(Map paramMap, int paramInt1, int paramInt2, PurchaseOrderInspectionPendingQueryOrder paramPurchaseOrderInspectionPendingQueryOrder, boolean paramBoolean);
  
  List<PurchaseOrderInspectionPendingItem> getPurchaseOrderInspectionPendingItemListByReceiving(String paramString);
  
  List getBoxList(Integer paramInteger, String paramString);
  
  List getPurchaseOrderInspectionPendingItemListByPoipId(String paramString1, String paramString2);
  
  PurchaseOrderInspectionPendingItem updatePurchaseOrderInspectionPendingItem2(PurchaseOrderInspectionPendingItem paramPurchaseOrderInspectionPendingItem);
  
  PurchaseOrderInspectionPendingItem updatePurchaseOrderInspectionPendingItem3(PurchaseOrderInspectionPendingItem paramPurchaseOrderInspectionPendingItem);
  
  String getMaxPONumber();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/PurchaseOrderInspectionPendingDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */