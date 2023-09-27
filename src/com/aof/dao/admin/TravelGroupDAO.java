package com.aof.dao.admin;

import com.aof.model.admin.TravelGroup;
import com.aof.model.admin.query.TravelGroupQueryOrder;
import java.util.List;
import java.util.Map;

public interface TravelGroupDAO {
  TravelGroup getTravelGroup(Integer paramInteger);
  
  int getTravelGroupListCount(Map paramMap);
  
  List getTravelGroupList(Map paramMap, int paramInt1, int paramInt2, TravelGroupQueryOrder paramTravelGroupQueryOrder, boolean paramBoolean);
  
  TravelGroup insertTravelGroup(TravelGroup paramTravelGroup);
  
  TravelGroup updateTravelGroup(TravelGroup paramTravelGroup);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/TravelGroupDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */