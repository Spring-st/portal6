package com.aof.dao.admin;

import com.aof.model.admin.SystemLog;
import com.aof.model.admin.query.SystemLogQueryOrder;
import java.util.List;
import java.util.Map;

public interface SystemLogDAO {
  SystemLog getSystemLog(Integer paramInteger);
  
  int getSystemLogListCount(Map paramMap);
  
  List getSystemLogList(Map paramMap, int paramInt1, int paramInt2, SystemLogQueryOrder paramSystemLogQueryOrder, boolean paramBoolean);
  
  SystemLog insertSystemLog(SystemLog paramSystemLog);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/SystemLogDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */