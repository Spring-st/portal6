package com.aof.dao.product;

import com.aof.dao.DAO;
import com.aof.model.product.CustomerPlan;
import com.aof.model.product.query.CustomerPlanQueryOrder;
import java.util.List;
import java.util.Map;

public interface CustomerPlanDao extends DAO {
  CustomerPlan getById(Integer paramInteger);
  
  CustomerPlan insert(CustomerPlan paramCustomerPlan);
  
  void remove(CustomerPlan paramCustomerPlan);
  
  CustomerPlan update(CustomerPlan paramCustomerPlan);
  
  int getListCount(Map paramMap);
  
  List getList(Map paramMap, int paramInt1, int paramInt2, CustomerPlanQueryOrder paramCustomerPlanQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/CustomerPlanDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */