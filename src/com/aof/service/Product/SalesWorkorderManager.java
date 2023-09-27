package com.aof.service.Product;

import com.aof.model.product.SalesWorkorder;
import com.aof.model.product.query.SalesWorkorderQueryOrder;
import java.util.List;
import java.util.Map;

public interface SalesWorkorderManager {
  SalesWorkorder getById(Integer paramInteger);
  
  SalesWorkorder insert(SalesWorkorder paramSalesWorkorder);
  
  void remove(SalesWorkorder paramSalesWorkorder);
  
  SalesWorkorder update(SalesWorkorder paramSalesWorkorder);
  
  int getListCount(Map paramMap);
  
  List getList(Map paramMap, int paramInt1, int paramInt2, SalesWorkorderQueryOrder paramSalesWorkorderQueryOrder, boolean paramBoolean);
  
  String scanningSalesDelivery(String paramString1, String paramString2, String paramString3);
  
  String scanningSalesLoadingWithdraw(String paramString1, String paramString2, String paramString3);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/SalesWorkorderManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */