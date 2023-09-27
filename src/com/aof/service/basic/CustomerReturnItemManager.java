package com.aof.service.basic;

import com.aof.model.basic.CustomerReturnItem;
import com.aof.model.basic.query.CustomerReturnItemQueryOrder;
import java.util.List;
import java.util.Map;

public interface CustomerReturnItemManager {
  CustomerReturnItem save(CustomerReturnItem paramCustomerReturnItem);
  
  CustomerReturnItem getCustomerReturnsItem(Integer paramInteger);
  
  void delete(CustomerReturnItem paramCustomerReturnItem);
  
  CustomerReturnItem update(CustomerReturnItem paramCustomerReturnItem);
  
  Integer getListCount(Map paramMap);
  
  List getList(Map paramMap, int paramInt1, int paramInt2, CustomerReturnItemQueryOrder paramCustomerReturnItemQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/CustomerReturnItemManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */