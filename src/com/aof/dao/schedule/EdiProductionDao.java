package com.aof.dao.schedule;

import com.aof.model.schedule.EdiProduction;
import com.aof.model.schedule.query.EdiProductionQueryOrder;
import java.util.List;
import java.util.Map;

public interface EdiProductionDao {
  EdiProduction save(EdiProduction paramEdiProduction);
  
  EdiProduction getEdiProduction(Integer paramInteger);
  
  void delete(EdiProduction paramEdiProduction);
  
  EdiProduction update(EdiProduction paramEdiProduction);
  
  Integer getListCount(Map paramMap);
  
  List<EdiProduction> getList(Map paramMap, int paramInt1, int paramInt2, EdiProductionQueryOrder paramEdiProductionQueryOrder, boolean paramBoolean);
  
  List<EdiProduction> getGroupList(Map paramMap, int paramInt1, int paramInt2, EdiProductionQueryOrder paramEdiProductionQueryOrder, boolean paramBoolean);
  
  Integer getListGroupCount(Map paramMap);
  
  List<EdiProduction> getListReport(Map paramMap, int paramInt1, int paramInt2, EdiProductionQueryOrder paramEdiProductionQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/EdiProductionDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */