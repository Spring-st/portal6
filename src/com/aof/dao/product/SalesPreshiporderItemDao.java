package com.aof.dao.product;

import com.aof.model.product.SalesPreshiporderItem;
import com.aof.model.product.query.SalesPreshiporderItemQueryOrder;
import java.util.List;
import java.util.Map;

public interface SalesPreshiporderItemDao {
  SalesPreshiporderItem getById(Integer paramInteger);
  
  SalesPreshiporderItem insert(SalesPreshiporderItem paramSalesPreshiporderItem);
  
  void remove(SalesPreshiporderItem paramSalesPreshiporderItem);
  
  SalesPreshiporderItem update(SalesPreshiporderItem paramSalesPreshiporderItem);
  
  int getListCount(Map paramMap);
  
  List getList(Map paramMap, int paramInt1, int paramInt2, SalesPreshiporderItemQueryOrder paramSalesPreshiporderItemQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/SalesPreshiporderItemDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */