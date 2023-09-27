package com.aof.service.admin;

import com.aof.model.admin.ExpenseSubCategory;
import com.aof.model.admin.TravelGroup;
import com.aof.model.admin.TravelGroupDetail;
import com.aof.model.admin.query.TravelGroupQueryOrder;
import java.util.List;
import java.util.Map;

public interface TravelGroupManager {
  TravelGroup getTravelGroup(Integer paramInteger);
  
  TravelGroup insertTravelGroup(TravelGroup paramTravelGroup);
  
  TravelGroup updateTravelGroup(TravelGroup paramTravelGroup);
  
  int getTravelGroupListCount(Map paramMap);
  
  List getTravelGroupList(Map paramMap, int paramInt1, int paramInt2, TravelGroupQueryOrder paramTravelGroupQueryOrder, boolean paramBoolean);
  
  void insertTravelGroup(TravelGroup paramTravelGroup, List paramList);
  
  List getDetailOf(TravelGroup paramTravelGroup);
  
  void updateTravelGroup(TravelGroup paramTravelGroup, List paramList);
  
  List getAllEnabledTravelGroupListBySiteId(Integer paramInteger);
  
  List getAllEnabledTravelGroupListBySiteIdAndIncludeThis(Integer paramInteger1, Integer paramInteger2);
  
  TravelGroupDetail getHotelTravelGroupDetail(TravelGroup paramTravelGroup);
  
  TravelGroupDetail getTravelGroupDetail(TravelGroup paramTravelGroup, ExpenseSubCategory paramExpenseSubCategory);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/TravelGroupManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */