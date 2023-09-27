package com.aof.dao.admin;

import com.aof.model.admin.TravelGroupDetail;
import com.aof.model.admin.query.TravelGroupDetailQueryOrder;
import java.util.List;
import java.util.Map;

public interface TravelGroupDetailDAO {
  TravelGroupDetail getTravelGroupDetail(Integer paramInteger1, Integer paramInteger2);
  
  int getTravelGroupDetailListCount(Map paramMap);
  
  List getTravelGroupDetailList(Map paramMap, int paramInt1, int paramInt2, TravelGroupDetailQueryOrder paramTravelGroupDetailQueryOrder, boolean paramBoolean);
  
  TravelGroupDetail insertTravelGroupDetail(TravelGroupDetail paramTravelGroupDetail);
  
  TravelGroupDetail updateTravelGroupDetail(TravelGroupDetail paramTravelGroupDetail);
  
  TravelGroupDetail insertOrUpdateTravelGroupDetail(TravelGroupDetail paramTravelGroupDetail);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/TravelGroupDetailDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */