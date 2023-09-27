package com.aof.service.admin;

import com.aof.model.admin.Hotel;
import com.aof.model.admin.query.HotelQueryOrder;
import java.util.List;
import java.util.Map;

public interface HotelManager {
  Hotel getHotel(Integer paramInteger);
  
  Hotel insertHotel(Hotel paramHotel);
  
  Hotel updateHotel(Hotel paramHotel);
  
  int getHotelListCount(Map paramMap);
  
  List getHotelList(Map paramMap, int paramInt1, int paramInt2, HotelQueryOrder paramHotelQueryOrder, boolean paramBoolean);
  
  Hotel reponsePromote(Integer paramInteger);
  
  Hotel requestPromote(Integer paramInteger, String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/HotelManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */