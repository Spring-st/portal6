package com.aof.service.basic;

import com.aof.model.basic.ScanLog;
import com.aof.model.basic.query.ScanLogQueryOrder;
import java.util.List;
import java.util.Map;

public interface ScanLogManager {
  ScanLog getScanLog(Integer paramInteger);
  
  ScanLog insertScanLog(ScanLog paramScanLog);
  
  List getScanLogList(Map paramMap, int paramInt1, int paramInt2, ScanLogQueryOrder paramScanLogQueryOrder, boolean paramBoolean);
  
  int getScanLogListCount(Map paramMap);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/ScanLogManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */