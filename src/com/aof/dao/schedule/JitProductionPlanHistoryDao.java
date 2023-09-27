package com.aof.dao.schedule;

import com.aof.dao.DAO;
import com.aof.model.schedule.JitProductionPlanHistory;
import com.aof.model.schedule.query.JitProductionPlanHistoryQueryOrder;
import java.util.List;
import java.util.Map;

public interface JitProductionPlanHistoryDao extends DAO {
  JitProductionPlanHistory save(JitProductionPlanHistory paramJitProductionPlanHistory);
  
  JitProductionPlanHistory getJitProductionPlanHistory(Integer paramInteger);
  
  void delete(JitProductionPlanHistory paramJitProductionPlanHistory);
  
  JitProductionPlanHistory update(JitProductionPlanHistory paramJitProductionPlanHistory);
  
  Integer getListCount(Map paramMap);
  
  List<JitProductionPlanHistory> getList(Map paramMap, int paramInt1, int paramInt2, JitProductionPlanHistoryQueryOrder paramJitProductionPlanHistoryQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/JitProductionPlanHistoryDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */