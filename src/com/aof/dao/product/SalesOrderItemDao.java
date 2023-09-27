package com.aof.dao.product;

import com.aof.dao.DAO;
import com.aof.model.product.SalesOrderItem;
import com.aof.model.product.query.SalesOrderItemQueryOrder;
import java.util.List;
import java.util.Map;

public interface SalesOrderItemDao extends DAO {
  SalesOrderItem getById(Integer paramInteger);
  
  SalesOrderItem insert(SalesOrderItem paramSalesOrderItem);
  
  void remove(SalesOrderItem paramSalesOrderItem);
  
  SalesOrderItem update(SalesOrderItem paramSalesOrderItem);
  
  int getListCount(Map paramMap);
  
  List getList(Map paramMap, int paramInt1, int paramInt2, SalesOrderItemQueryOrder paramSalesOrderItemQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/SalesOrderItemDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */