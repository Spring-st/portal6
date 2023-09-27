package com.aof.service.basic;

import com.aof.model.basic.StoreRoom;
import com.aof.model.basic.query.StoreRoomQueryOrder;
import java.util.List;
import java.util.Map;

public interface StoreRoomManager {
  StoreRoom getStoreRoom(Integer paramInteger);
  
  int getStoreRoomListCount(Map paramMap);
  
  List getStoreRoomList(Map paramMap, int paramInt1, int paramInt2, StoreRoomQueryOrder paramStoreRoomQueryOrder, boolean paramBoolean);
  
  StoreRoom insertStoreRoom(StoreRoom paramStoreRoom);
  
  StoreRoom updateStoreRoom(StoreRoom paramStoreRoom);
  
  List getEnabledStoreRoomList();
  
  void deleteStoreRoom(StoreRoom paramStoreRoom);
  
  List getStoreRoomListEnabledAll();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/StoreRoomManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */