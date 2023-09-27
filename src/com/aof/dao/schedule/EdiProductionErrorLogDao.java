package com.aof.dao.schedule;

import com.aof.model.schedule.EdiProductionErrorLog;
import com.aof.model.schedule.query.EdiProductionErrorLogQueryOrder;
import java.util.List;
import java.util.Map;

public interface EdiProductionErrorLogDao {
  EdiProductionErrorLog save(EdiProductionErrorLog paramEdiProductionErrorLog);
  
  EdiProductionErrorLog getEdiProductionErrorLog(Integer paramInteger);
  
  void delete(EdiProductionErrorLog paramEdiProductionErrorLog);
  
  EdiProductionErrorLog update(EdiProductionErrorLog paramEdiProductionErrorLog);
  
  Integer getListCount(Map paramMap);
  
  List<EdiProductionErrorLog> getList(Map paramMap, int paramInt1, int paramInt2, EdiProductionErrorLogQueryOrder paramEdiProductionErrorLogQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/EdiProductionErrorLogDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */