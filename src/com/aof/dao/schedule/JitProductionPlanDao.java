package com.aof.dao.schedule;

import com.aof.dao.DAO;
import com.aof.model.basic.WmsPart;
import com.aof.model.schedule.EdiProduction;
import com.aof.model.schedule.JitProductionPlan;
import com.aof.model.schedule.query.JitProductionPlanQueryOrder;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface JitProductionPlanDao extends DAO {
  JitProductionPlan save(JitProductionPlan paramJitProductionPlan);
  
  JitProductionPlan getJitProductionPlan(Integer paramInteger);
  
  void delete(JitProductionPlan paramJitProductionPlan);
  
  JitProductionPlan update(JitProductionPlan paramJitProductionPlan);
  
  Integer getListCount(Map paramMap);
  
  List<JitProductionPlan> getList(Map paramMap, int paramInt1, int paramInt2, JitProductionPlanQueryOrder paramJitProductionPlanQueryOrder, boolean paramBoolean);
  
  List<JitProductionPlan> DismantlingBom(EdiProduction paramEdiProduction, WmsPart paramWmsPart, BigDecimal paramBigDecimal);
  
  List<Map> computeCombinePlan(String paramString);
  
  Map<String, BigDecimal> getPartNeedQtyByBom(EdiProduction paramEdiProduction, WmsPart paramWmsPart, BigDecimal paramBigDecimal);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/JitProductionPlanDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */