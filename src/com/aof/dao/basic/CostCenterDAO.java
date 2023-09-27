package com.aof.dao.basic;

import com.aof.dao.DAO;
import com.aof.model.basic.CostCenter;
import com.aof.model.basic.query.CostCenterQueryOrder;
import java.util.List;
import java.util.Map;

public interface CostCenterDAO extends DAO {
  CostCenter getCostCenter(Integer paramInteger);
  
  int getCostCenterListCount(Map paramMap);
  
  List getCostCenterList(Map paramMap, int paramInt1, int paramInt2, CostCenterQueryOrder paramCostCenterQueryOrder, boolean paramBoolean);
  
  CostCenter insertCostCenter(CostCenter paramCostCenter);
  
  CostCenter updateCostCenter(CostCenter paramCostCenter);
  
  List getEnabledCostCenterList();
  
  void deleteCostCenter(CostCenter paramCostCenter);
  
  String getMaxCostCenterCodeBeginWith(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/CostCenterDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */