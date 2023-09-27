package com.aof.service.admin;

import com.aof.model.Loggable;
import com.aof.model.admin.SystemLog;
import com.aof.model.admin.User;
import com.aof.model.admin.query.SystemLogQueryOrder;
import java.util.List;
import java.util.Map;

public interface SystemLogManager {
  SystemLog getSystemLog(Integer paramInteger);
  
  SystemLog insertSystemLog(SystemLog paramSystemLog);
  
  int getSystemLogListCount(Map paramMap);
  
  List getSystemLogList(Map paramMap, int paramInt1, int paramInt2, SystemLogQueryOrder paramSystemLogQueryOrder, boolean paramBoolean);
  
  void generateLog(Loggable paramLoggable1, Loggable paramLoggable2, String paramString, User paramUser);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/SystemLogManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */