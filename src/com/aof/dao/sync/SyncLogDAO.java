package com.aof.dao.sync;

import com.aof.dao.DAO;
import com.aof.model.basic.SyncLog;
import com.aof.model.sync.query.SyncLogQueryOrder;
import java.util.List;
import java.util.Map;

public interface SyncLogDAO extends DAO {
  SyncLog getSyncLog(Integer paramInteger);
  
  int getSyncLogListCount(Map paramMap);
  
  List getSyncLogList(Map paramMap, int paramInt1, int paramInt2, SyncLogQueryOrder paramSyncLogQueryOrder, boolean paramBoolean);
  
  SyncLog insertSyncLog(SyncLog paramSyncLog);
  
  SyncLog updateSyncLog(SyncLog paramSyncLog);
  
  List getEnabledSyncLogList();
  
  void deleteSyncLog(SyncLog paramSyncLog);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/sync/SyncLogDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */