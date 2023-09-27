package com.aof.dao.basic;

import com.aof.model.basic.Customerreturns;
import com.aof.model.basic.query.CustomerreturnsQueryOrder;
import java.util.List;
import java.util.Map;

public interface CustomerreturnsDao {
  Customerreturns save(Customerreturns paramCustomerreturns);
  
  Customerreturns getCustomerreturns(Integer paramInteger);
  
  void delete(Customerreturns paramCustomerreturns);
  
  Customerreturns update(Customerreturns paramCustomerreturns);
  
  Integer getListCount(Map paramMap);
  
  List<Customerreturns> getList(Map paramMap, int paramInt1, int paramInt2, CustomerreturnsQueryOrder paramCustomerreturnsQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/CustomerreturnsDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */