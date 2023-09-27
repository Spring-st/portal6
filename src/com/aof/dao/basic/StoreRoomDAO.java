package com.aof.dao.basic;

import com.aof.model.basic.StoreRoom;
import com.aof.model.basic.query.StoreRoomQueryOrder;
import java.util.List;
import java.util.Map;

public interface StoreRoomDAO {
  StoreRoom getStoreRoom(Integer paramInteger);
  
  int getStoreRoomListCount(Map paramMap);
  
  List getStoreRoomList(Map paramMap, int paramInt1, int paramInt2, StoreRoomQueryOrder paramStoreRoomQueryOrder, boolean paramBoolean);
  
  StoreRoom insertStoreRoom(StoreRoom paramStoreRoom);
  
  StoreRoom updateStoreRoom(StoreRoom paramStoreRoom);
  
  List getEnabledStoreRoomList();
  
  void deleteStoreRoom(StoreRoom paramStoreRoom);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/StoreRoomDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */