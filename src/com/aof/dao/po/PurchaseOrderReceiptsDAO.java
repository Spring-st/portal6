package com.aof.dao.po;

import com.aof.dao.DAO;
import com.aof.model.po.Box;
import com.aof.model.po.PurchaseOrderReceipts;
import com.aof.model.po.PurchaseOrderReceiptsDetial;
import com.aof.model.po.query.PurchaseOrderReceiptsQueryOrder;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PurchaseOrderReceiptsDAO extends DAO {
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
  
  String getMaxPoReceiptsBeginWith(String paramString);
  
  List<Box> getBoxList(Integer paramInteger);
  
  boolean validateReceivingEndByCondimentSingle(Integer paramInteger, BigDecimal paramBigDecimal);
  
  boolean validateReceivingEndByPoip(Integer paramInteger, BigDecimal paramBigDecimal);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/PurchaseOrderReceiptsDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */