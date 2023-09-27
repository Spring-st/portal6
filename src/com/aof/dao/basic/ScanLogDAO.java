package com.aof.dao.basic;

import com.aof.dao.DAO;
import com.aof.model.basic.ScanLog;
import com.aof.model.basic.query.ScanLogQueryOrder;
import java.util.List;
import java.util.Map;

public interface ScanLogDAO extends DAO {
  ScanLog getScanLog(Integer paramInteger);
  
  ScanLog insertScanLog(ScanLog paramScanLog);
  
  List getScanLogList(Map paramMap, int paramInt1, int paramInt2, ScanLogQueryOrder paramScanLogQueryOrder, boolean paramBoolean);
  
  int getScanLogListCount(Map paramMap);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/ScanLogDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */