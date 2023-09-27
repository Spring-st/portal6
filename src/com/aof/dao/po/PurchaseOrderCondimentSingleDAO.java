package com.aof.dao.po;

import com.aof.dao.DAO;
import com.aof.model.po.PurchaseOrderCondimentSingle;
import com.aof.model.po.query.PurchaseOrderCondimentSingleQueryOrder;
import java.util.List;
import java.util.Map;

public interface PurchaseOrderCondimentSingleDAO extends DAO {
  PurchaseOrderCondimentSingle getPurchaseOrderCondimentSingle(Integer paramInteger);
  
  int getPurchaseOrderCondimentSingleListCount(Map paramMap);
  
  List getPurchaseOrderCondimentSingleList(Map paramMap, int paramInt1, int paramInt2, PurchaseOrderCondimentSingleQueryOrder paramPurchaseOrderCondimentSingleQueryOrder, boolean paramBoolean);
  
  PurchaseOrderCondimentSingle updatePurchaseOrderCondimentSingle(PurchaseOrderCondimentSingle paramPurchaseOrderCondimentSingle);
  
  PurchaseOrderCondimentSingle insertPurchaseOrderCondimentSingle(PurchaseOrderCondimentSingle paramPurchaseOrderCondimentSingle);
  
  String getMaxPoReceiptsBeginWith(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/PurchaseOrderCondimentSingleDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */