package com.aof.dao.po;

import com.aof.dao.DAO;
import com.aof.model.po.PurchaseOrderRqc;
import com.aof.model.po.query.PurchaseOrderRqcQueryOrder;
import java.util.List;
import java.util.Map;

public interface PurchaseOrderRQCDAO extends DAO {
  PurchaseOrderRqc getPurchaseOrderRqc(Integer paramInteger);
  
  String getLastPoApplicationCode();
  
  String getMaxPoApplicationIdBeginWith(String paramString);
  
  int getPurchaseOrderRqcListCount(Map paramMap);
  
  List getPurchaseOrderRqcList(Map paramMap, int paramInt1, int paramInt2, PurchaseOrderRqcQueryOrder paramPurchaseOrderRqcQueryOrder, boolean paramBoolean);
  
  PurchaseOrderRqc insertPurchaseOrderRqc(PurchaseOrderRqc paramPurchaseOrderRqc);
  
  PurchaseOrderRqc updatePurchaseOrderRqc(PurchaseOrderRqc paramPurchaseOrderRqc);
  
  List getEnabledPurchaseOrderRqcList();
  
  void deletePurchaseOrderRqc(PurchaseOrderRqc paramPurchaseOrderRqc);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/PurchaseOrderRQCDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */