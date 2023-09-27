package com.aof.dao.admin;

import com.aof.dao.DAO;
import com.aof.model.admin.Customer;
import com.aof.model.admin.query.CustomerQueryOrder;
import java.util.List;
import java.util.Map;

public interface CustomerDAO extends DAO {
  Customer getCustomer(String paramString);
  
  int getCustomerListCount(Map paramMap);
  
  List getCustomerList(Map paramMap, int paramInt1, int paramInt2, CustomerQueryOrder paramCustomerQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/CustomerDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */