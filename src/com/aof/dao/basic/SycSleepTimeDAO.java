package com.aof.dao.basic;

import com.aof.model.basic.SycSleepTime;
import com.aof.model.basic.query.SycSleepTimeQueryOrder;
import java.util.List;
import java.util.Map;

public interface SycSleepTimeDAO {
  SycSleepTime getSycSleepTime(Integer paramInteger);
  
  int getSycSleepTimeCount(Map paramMap);
  
  List getSycSleepTimeList(Map paramMap, int paramInt1, int paramInt2, SycSleepTimeQueryOrder paramSycSleepTimeQueryOrder, boolean paramBoolean);
  
  SycSleepTime insertSycSleepTime(SycSleepTime paramSycSleepTime);
  
  SycSleepTime updateSycSleepTime(SycSleepTime paramSycSleepTime);
  
  void deleteSycSleepTime(SycSleepTime paramSycSleepTime);
  
  SycSleepTime getSycSleepTime(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/SycSleepTimeDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */