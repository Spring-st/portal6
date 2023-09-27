package com.aof.dao.product;

import com.aof.model.product.SalesOrder;
import com.aof.model.product.query.SalesOrderQueryOrder;
import java.util.List;
import java.util.Map;

public interface SalesOrderDao {
  SalesOrder getById(Integer paramInteger);
  
  SalesOrder insert(SalesOrder paramSalesOrder);
  
  void remove(SalesOrder paramSalesOrder);
  
  SalesOrder update(SalesOrder paramSalesOrder);
  
  int getListCount(Map paramMap);
  
  List getList(Map paramMap, int paramInt1, int paramInt2, SalesOrderQueryOrder paramSalesOrderQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/SalesOrderDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */