package com.aof.service.Product;

import com.aof.model.product.SalesOrder;
import com.aof.model.product.SalesOrderItem;
import com.aof.model.product.query.SalesOrderItemQueryOrder;
import java.util.List;
import java.util.Map;

public interface SalesOrderItemManager {
  SalesOrderItem getById(Integer paramInteger);
  
  SalesOrderItem insert(SalesOrderItem paramSalesOrderItem);
  
  void remove(SalesOrderItem paramSalesOrderItem);
  
  SalesOrderItem update(SalesOrderItem paramSalesOrderItem);
  
  int getListCount(Map paramMap);
  
  List getList(Map paramMap, int paramInt1, int paramInt2, SalesOrderItemQueryOrder paramSalesOrderItemQueryOrder, boolean paramBoolean);
  
  Boolean iscloseSalesOrderItem(SalesOrder paramSalesOrder);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/SalesOrderItemManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */