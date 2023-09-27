package com.aof.service.Product;

import com.aof.model.product.DailyProductPlan;
import com.aof.model.product.query.DailyProductPlanQueryOrder;
import java.util.List;
import java.util.Map;

public interface DailyProductPlanManager {
  DailyProductPlan getById(Integer paramInteger);
  
  DailyProductPlan insert(DailyProductPlan paramDailyProductPlan);
  
  void remove(DailyProductPlan paramDailyProductPlan);
  
  DailyProductPlan update(DailyProductPlan paramDailyProductPlan);
  
  int getListCount(Map paramMap);
  
  List getList(Map paramMap, int paramInt1, int paramInt2, DailyProductPlanQueryOrder paramDailyProductPlanQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Product/DailyProductPlanManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */