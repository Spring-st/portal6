package com.aof.dao.schedule;

import com.aof.model.schedule.NjitNpoPlan;
import com.aof.model.schedule.query.NjitNpoPlanQueryOrder;
import java.util.List;
import java.util.Map;

public interface NjitNpoPlanDao {
  NjitNpoPlan save(NjitNpoPlan paramNjitNpoPlan);
  
  NjitNpoPlan getNjitNpoPlan(Integer paramInteger);
  
  void delete(NjitNpoPlan paramNjitNpoPlan);
  
  NjitNpoPlan update(NjitNpoPlan paramNjitNpoPlan);
  
  Integer getListCount(Map paramMap);
  
  List<NjitNpoPlan> getList(Map paramMap, int paramInt1, int paramInt2, NjitNpoPlanQueryOrder paramNjitNpoPlanQueryOrder, boolean paramBoolean);
  
  NjitNpoPlan getNjitNpoPlanByPart(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/NjitNpoPlanDao.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */