package com.aof.service.basic;

import com.aof.model.basic.SycSleepTime;
import com.aof.model.basic.query.SycSleepTimeQueryOrder;
import java.util.List;
import java.util.Map;

public interface SycSleepTimeManager {
  SycSleepTime getSycSleepTime(Integer paramInteger);
  
  int getSycSleepTimeCount(Map paramMap);
  
  List getSycSleepTimeList(Map paramMap, int paramInt1, int paramInt2, SycSleepTimeQueryOrder paramSycSleepTimeQueryOrder, boolean paramBoolean);
  
  SycSleepTime insertSycSleepTime(SycSleepTime paramSycSleepTime);
  
  SycSleepTime updateSycSleepTime(SycSleepTime paramSycSleepTime);
  
  void deleteSycSleepTime(SycSleepTime paramSycSleepTime);
  
  SycSleepTime getSycSleepTime(String paramString);
  
  void init();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/SycSleepTimeManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */