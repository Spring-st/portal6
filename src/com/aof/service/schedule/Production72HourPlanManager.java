package com.aof.service.schedule;

import com.aof.model.schedule.Production72HourPlan;
import com.aof.model.schedule.query.Production72HourPlanQueryOrder;
import java.util.List;
import java.util.Map;

public interface Production72HourPlanManager {
  Production72HourPlan save(Production72HourPlan paramProduction72HourPlan);
  
  Production72HourPlan getProduction72HourPlan(Integer paramInteger);
  
  void delete(Production72HourPlan paramProduction72HourPlan);
  
  Production72HourPlan update(Production72HourPlan paramProduction72HourPlan);
  
  Integer getListCount(Map paramMap);
  
  List<Production72HourPlan> getList(Map paramMap, int paramInt1, int paramInt2, Production72HourPlanQueryOrder paramProduction72HourPlanQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/schedule/Production72HourPlanManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */