package com.aof.service.admin;

import com.aof.model.admin.Customer;
import com.aof.model.admin.query.CustomerQueryOrder;
import java.util.List;
import java.util.Map;

public interface CustomerManager {
  Customer getCustomer(String paramString);
  
  int getCustomerListCount(Map paramMap);
  
  List getCustomerList(Map paramMap, int paramInt1, int paramInt2, CustomerQueryOrder paramCustomerQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/CustomerManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */