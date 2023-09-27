package com.aof.service.schedule;

import com.aof.model.schedule.NjitNpoPlan;
import com.aof.model.schedule.query.NjitNpoPlanQueryOrder;
import java.util.List;
import java.util.Map;

public interface NjitNpoPlanManager {
  NjitNpoPlan save(NjitNpoPlan paramNjitNpoPlan);
  
  NjitNpoPlan getNjitNpoPlan(Integer paramInteger);
  
  NjitNpoPlan getNjitNpoPlanByPart(String paramString);
  
  void delete(NjitNpoPlan paramNjitNpoPlan);
  
  NjitNpoPlan update(NjitNpoPlan paramNjitNpoPlan);
  
  Integer getListCount(Map paramMap);
  
  List<NjitNpoPlan> getList(Map paramMap, int paramInt1, int paramInt2, NjitNpoPlanQueryOrder paramNjitNpoPlanQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/schedule/NjitNpoPlanManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */