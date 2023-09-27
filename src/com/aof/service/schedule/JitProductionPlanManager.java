package com.aof.service.schedule;

import com.aof.model.basic.WmsPart;
import com.aof.model.schedule.EdiProduction;
import com.aof.model.schedule.JitProductionPlan;
import com.aof.model.schedule.query.JitProductionPlanQueryOrder;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface JitProductionPlanManager {
  JitProductionPlan save(JitProductionPlan paramJitProductionPlan);
  
  JitProductionPlan getJitProductionPlan(Integer paramInteger);
  
  void delete(JitProductionPlan paramJitProductionPlan);
  
  JitProductionPlan update(JitProductionPlan paramJitProductionPlan);
  
  Integer getListCount(Map paramMap);
  
  List<JitProductionPlan> getList(Map paramMap, int paramInt1, int paramInt2, JitProductionPlanQueryOrder paramJitProductionPlanQueryOrder, boolean paramBoolean);
  
  void DismantlingBom(EdiProduction paramEdiProduction, WmsPart paramWmsPart, BigDecimal paramBigDecimal);
  
  Map<String, BigDecimal> getPartNeedQtyByBom(EdiProduction paramEdiProduction, WmsPart paramWmsPart, BigDecimal paramBigDecimal);
  
  void combinePlan(List<JitProductionPlan> paramList);
  
  List<Map> computeCombinePlan(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/schedule/JitProductionPlanManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */