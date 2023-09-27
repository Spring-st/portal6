package com.aof.service.Product;

import com.aof.model.product.BasicCustomer;
import com.aof.model.product.query.BasicCustomerQueryOrder;
import java.util.List;
import java.util.Map;

public interface BasicCustomerManager {
  BasicCustomer getById(Integer paramInteger);
  
  BasicCustomer insert(BasicCustomer paramBasicCustomer);
  
  void remove(BasicCustomer paramBasicCustomer);
  
  BasicCustomer update(BasicCustomer paramBasicCustomer);
  
  int getListCount(Map paramMap);
  
  List getList(Map paramMap, int paramInt1, int paramInt2, BasicCustomerQueryOrder paramBasicCustomerQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/BasicCustomerManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */