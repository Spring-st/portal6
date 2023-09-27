package com.aof.dao.schedule;

import com.aof.model.schedule.ProductionDayPlanHistory;
import com.aof.model.schedule.query.ProductionDayPlanHistoryQueryOrder;
import java.util.List;
import java.util.Map;

public interface ProductionDayPlanHistoryDao {
  ProductionDayPlanHistory save(ProductionDayPlanHistory paramProductionDayPlanHistory);
  
  ProductionDayPlanHistory getProductionDayPlanHistory(Integer paramInteger);
  
  void delete(ProductionDayPlanHistory paramProductionDayPlanHistory);
  
  ProductionDayPlanHistory update(ProductionDayPlanHistory paramProductionDayPlanHistory);
  
  Integer getListCount(Map paramMap);
  
  List<ProductionDayPlanHistory> getList(Map paramMap, int paramInt1, int paramInt2, ProductionDayPlanHistoryQueryOrder paramProductionDayPlanHistoryQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/ProductionDayPlanHistoryDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */