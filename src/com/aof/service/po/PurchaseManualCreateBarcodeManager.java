package com.aof.service.po;

import com.aof.model.po.PurchaseManualCreateBarcode;
import com.aof.model.po.query.PurchaseManualCreateBarcodeQueryOrder;
import java.util.List;
import java.util.Map;

public interface PurchaseManualCreateBarcodeManager {
  PurchaseManualCreateBarcode getPurchaseManualCreateBarcode(Integer paramInteger);
  
  List list(Map paramMap, int paramInt1, int paramInt2, PurchaseManualCreateBarcodeQueryOrder paramPurchaseManualCreateBarcodeQueryOrder, boolean paramBoolean);
  
  PurchaseManualCreateBarcode insert(PurchaseManualCreateBarcode paramPurchaseManualCreateBarcode);
  
  void update(PurchaseManualCreateBarcode paramPurchaseManualCreateBarcode);
  
  void delete(PurchaseManualCreateBarcode paramPurchaseManualCreateBarcode);
  
  int getListCount(Map paramMap);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/PurchaseManualCreateBarcodeManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */