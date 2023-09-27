package com.aof.service.po;

import com.aof.model.metadata.YesNo;
import com.aof.model.po.Box;
import com.aof.model.po.ProduceRejectedMaterial;
import com.aof.model.po.PurchaseOrderInspectionPending;
import com.aof.model.po.PurchaseOrderInspectionPendingItem;
import com.aof.model.po.query.PurchaseOrderInspectionPendingQueryOrder;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PurchaseOrderInspectionPendingManager {
  PurchaseOrderInspectionPending getPurchaseOrderInspectionPending(String paramString);
  
  PurchaseOrderInspectionPending insertPurchaseOrderInspectionPending(PurchaseOrderInspectionPending paramPurchaseOrderInspectionPending);
  
  PurchaseOrderInspectionPending insertPurchaseOrderInspectionPendingPONumber(PurchaseOrderInspectionPending paramPurchaseOrderInspectionPending);
  
  PurchaseOrderInspectionPending insertPurchaseOrderInspectionPending(PurchaseOrderInspectionPending paramPurchaseOrderInspectionPending, Date paramDate);
  
  PurchaseOrderInspectionPending updatePurchaseOrderInspectionPending(PurchaseOrderInspectionPending paramPurchaseOrderInspectionPending);
  
  int getPurchaseOrderInspectionPendingListCount(Map paramMap);
  
  List getPurchaseOrderInspectionPendingList(Map paramMap, int paramInt1, int paramInt2, PurchaseOrderInspectionPendingQueryOrder paramPurchaseOrderInspectionPendingQueryOrder, boolean paramBoolean);
  
  void promotePurchaseOrderInspectionPending(PurchaseOrderInspectionPending paramPurchaseOrderInspectionPending);
  
  boolean deletePurchaseOrderInspectionPending(PurchaseOrderInspectionPending paramPurchaseOrderInspectionPending);
  
  List getPurchaseOrderInspectionPendingListEnabledAll();
  
  void insertPurchaseOrderInspectionPendingItem(PurchaseOrderInspectionPendingItem paramPurchaseOrderInspectionPendingItem);
  
  void updatePurchaseOrderInspectionPendingItem(Object paramObject);
  
  PurchaseOrderInspectionPendingItem updatePurchaseOrderInspectionPendingItem2(PurchaseOrderInspectionPendingItem paramPurchaseOrderInspectionPendingItem);
  
  PurchaseOrderInspectionPendingItem updatePurchaseOrderInspectionPendingItem3(PurchaseOrderInspectionPendingItem paramPurchaseOrderInspectionPendingItem);
  
  Boolean isclosePurchaseOrderInspectionPendingByItem(PurchaseOrderInspectionPending paramPurchaseOrderInspectionPending);
  
  Boolean isconfirmPurchaseOrderInspectionPendingByItem(PurchaseOrderInspectionPending paramPurchaseOrderInspectionPending);
  
  void deletePurchaseOrderInspectionPendingItem(PurchaseOrderInspectionPendingItem paramPurchaseOrderInspectionPendingItem);
  
  List getPurchaseOrderInspectionPendingItemList(String paramString);
  
  List<PurchaseOrderInspectionPendingItem> getPurchaseOrderInspectionPendingItemListByReceiving(String paramString);
  
  List getPurchaseOrderInspectionPendingItemListByPoipId(String paramString1, String paramString2);
  
  PurchaseOrderInspectionPendingItem getPurchaseOrderInspectionPendingItem(Integer paramInteger);
  
  int getPurchaseOrderInspectionPendingItemListCount(Map paramMap);
  
  List getPurchaseOrderInspectionPendingItemList(Map paramMap, int paramInt1, int paramInt2, PurchaseOrderInspectionPendingQueryOrder paramPurchaseOrderInspectionPendingQueryOrder, boolean paramBoolean);
  
  void insertBox(Box paramBox);
  
  void insertBox(String paramString, Date paramDate, Integer paramInteger1, Integer paramInteger2);
  
  void updateBox(Box paramBox);
  
  void deleteBox(Box paramBox);
  
  List getBoxList(String paramString);
  
  List getBoxListByReceipt(String paramString);
  
  List getBoxListByRQC(String paramString);
  
  List getBoxListByRQC2(String paramString);
  
  List getBoxListByRQCNotQC(String paramString);
  
  List getBoxListByInbound(int paramInt);
  
  List getBoxListByInbound2(int paramInt);
  
  List getBoxListByInbound2(String paramString);
  
  List getBoxListByReturn(String paramString);
  
  List getBoxListByReturnItem(int paramInt);
  
  List getBoxList(String paramString1, String paramString2, YesNo paramYesNo);
  
  List getBoxList(Integer paramInteger, String paramString);
  
  Box getBox(Integer paramInteger);
  
  void createPurchaseOrderReceiptsItemList(List<PurchaseOrderInspectionPendingItem> paramList);
  
  List<BigDecimal> getBasicPOPartAmount(List<String> paramList, Integer paramInteger);
  
  List<BigDecimal> getBasicPOPartAmount(List<String> paramList);
  
  List<PurchaseOrderInspectionPendingItem> getPurchaseOrderInspectionPendingItemReport(List<PurchaseOrderInspectionPendingItem> paramList);
  
  List<Map> getPurchaseOrderInspectionPendingItemAndSuppliers(String paramString);
  
  void insertPurchaseOrderSupplier(String paramString);
  
  void updatePurchaseOrderInspectionPendingItemStatus(String paramString);
  
  List updatePurchaseOrderInspectionPendingItem(List<PurchaseOrderInspectionPendingItem> paramList);
  
  List updatePurchaseOrderInspectionPending(List<PurchaseOrderInspectionPending> paramList);
  
  List<ProduceRejectedMaterial> getPurchaseOrderInspectionPendingItemByPrintReport(List<ProduceRejectedMaterial> paramList);
  
  PurchaseOrderInspectionPendingItem getPurchaseOrderInspectionPendingItem(PurchaseOrderInspectionPendingItem paramPurchaseOrderInspectionPendingItem);
  
  String getMaxPONumber();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/PurchaseOrderInspectionPendingManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */