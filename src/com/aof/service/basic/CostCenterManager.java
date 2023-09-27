package com.aof.service.basic;

import com.aof.model.basic.CostCenter;
import com.aof.model.basic.query.CostCenterQueryOrder;
import java.util.List;
import java.util.Map;

public interface CostCenterManager {
  CostCenter getCostCenter(Integer paramInteger);
  
  CostCenter insertCostCenter(CostCenter paramCostCenter);
  
  CostCenter updateCostCenter(CostCenter paramCostCenter);
  
  int getCostCenterListCount(Map paramMap);
  
  List getCostCenterList(Map paramMap, int paramInt1, int paramInt2, CostCenterQueryOrder paramCostCenterQueryOrder, boolean paramBoolean);
  
  void promoteCostCenter(CostCenter paramCostCenter);
  
  boolean deleteCostCenter(CostCenter paramCostCenter);
  
  List getCostCenterListEnabledAll();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/CostCenterManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */