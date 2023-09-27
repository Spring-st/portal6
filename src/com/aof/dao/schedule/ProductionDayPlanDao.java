package com.aof.dao.schedule;

import com.aof.model.schedule.ProductionDayPlan;
import com.aof.model.schedule.query.ProductionDayPlanQueryOrder;
import java.util.List;
import java.util.Map;

public interface ProductionDayPlanDao {
  ProductionDayPlan save(ProductionDayPlan paramProductionDayPlan);
  
  ProductionDayPlan getProductionDayPlan(Integer paramInteger);
  
  void delete(ProductionDayPlan paramProductionDayPlan);
  
  ProductionDayPlan update(ProductionDayPlan paramProductionDayPlan);
  
  Integer getListCount(Map paramMap);
  
  List<ProductionDayPlan> getList(Map paramMap, int paramInt1, int paramInt2, ProductionDayPlanQueryOrder paramProductionDayPlanQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/ProductionDayPlanDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */