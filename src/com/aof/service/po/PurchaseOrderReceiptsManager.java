package com.aof.service.po;

import com.aof.model.admin.User;
import com.aof.model.po.PurchaseOrderReceipts;
import com.aof.model.po.PurchaseOrderReceiptsDetial;
import com.aof.model.po.query.PurchaseOrderReceiptsQueryOrder;
import java.util.List;
import java.util.Map;

public interface PurchaseOrderReceiptsManager {
  PurchaseOrderReceipts getPurchaseOrderReceipts(Integer paramInteger);
  
  String getLastPoApplicationCode();
  
  int getPurchaseOrderReceiptsListCount(Map paramMap);
  
  List getPurchaseOrderReceiptsList(Map paramMap, int paramInt1, int paramInt2, PurchaseOrderReceiptsQueryOrder paramPurchaseOrderReceiptsQueryOrder, boolean paramBoolean);
  
  PurchaseOrderReceipts insertPurchaseOrderReceipts(PurchaseOrderReceipts paramPurchaseOrderReceipts);
  
  PurchaseOrderReceipts updatePurchaseOrderReceipts(PurchaseOrderReceipts paramPurchaseOrderReceipts);
  
  List getEnabledPurchaseOrderReceiptsList();
  
  void deletePurchaseOrderReceipts(PurchaseOrderReceipts paramPurchaseOrderReceipts);
  
  List getPurchaseOrderReceiptsItemList(Map paramMap, int paramInt1, int paramInt2, PurchaseOrderReceiptsQueryOrder paramPurchaseOrderReceiptsQueryOrder, boolean paramBoolean);
  
  int getPurchaseOrderReceiptsItemListCount(Map paramMap);
  
  PurchaseOrderReceipts getPurchaseOrderReceiptsByPoip(String paramString);
  
  PurchaseOrderReceiptsDetial getPurchaseOrderReceiptsDetial(Integer paramInteger);
  
  PurchaseOrderReceiptsDetial insertPurchaseOrderReceiptsDetial(PurchaseOrderReceiptsDetial paramPurchaseOrderReceiptsDetial);
  
  PurchaseOrderReceiptsDetial updatePurchaseOrderReceiptsDetial(PurchaseOrderReceiptsDetial paramPurchaseOrderReceiptsDetial);
  
  boolean insertPurchaseOrderReceiptsDetial(String paramString);
  
  List getBoxList(String paramString);
  
  String scanningPurchaseOrderReceipts(String paramString1, String paramString2);
  
  String scanningPurchaseOrderReceiptsByShipOrder(String paramString1, String paramString2);
  
  String systemPurchaseOrderReceipts(String paramString1, String paramString2, User paramUser);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/PurchaseOrderReceiptsManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */