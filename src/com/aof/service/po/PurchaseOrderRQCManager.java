package com.aof.service.po;

import com.aof.model.admin.User;
import com.aof.model.po.PurchaseOrderRqc;
import com.aof.model.po.query.PurchaseOrderRqcQueryOrder;
import java.util.List;
import java.util.Map;

public interface PurchaseOrderRQCManager {
  PurchaseOrderRqc getPurchaseOrderRqc(Integer paramInteger);
  
  String getLastPoApplicationCode();
  
  String getMaxPoApplicationIdBeginWith(String paramString);
  
  int getPurchaseOrderRqcListCount(Map paramMap);
  
  List getPurchaseOrderRqcList(Map paramMap, int paramInt1, int paramInt2, PurchaseOrderRqcQueryOrder paramPurchaseOrderRqcQueryOrder, boolean paramBoolean);
  
  PurchaseOrderRqc insertPurchaseOrderRqc(PurchaseOrderRqc paramPurchaseOrderRqc);
  
  PurchaseOrderRqc updatePurchaseOrderRqc(PurchaseOrderRqc paramPurchaseOrderRqc);
  
  List getEnabledPurchaseOrderRqcList();
  
  void deletePurchaseOrderRqc(PurchaseOrderRqc paramPurchaseOrderRqc);
  
  String scanningPurchaseOrderRQC(String paramString1, String paramString2, String paramString3, String paramString4);
  
  String systemPurchaseOrderRQC(String paramString1, String paramString2, String paramString3, User paramUser);
  
  String purchaseOrderRejectedMaterialBoxRQC(String paramString1, String paramString2, String paramString3, User paramUser);
  
  String[] scanningRqcProgress();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/PurchaseOrderRQCManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */