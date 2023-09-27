package com.aof.dao.admin;

import com.aof.model.admin.GlobalMailReminder;
import com.aof.model.admin.Hotel;
import com.aof.model.admin.Site;
import com.aof.model.admin.query.HotelQueryOrder;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HotelDAO {
  Hotel getHotel(Integer paramInteger);
  
  int getHotelListCount(Map paramMap);
  
  List getHotelList(Map paramMap, int paramInt1, int paramInt2, HotelQueryOrder paramHotelQueryOrder, boolean paramBoolean);
  
  Hotel insertHotel(Hotel paramHotel);
  
  Hotel updateHotel(Hotel paramHotel);
  
  List getHotelMaintainerNotResponsedList(Site paramSite, Date paramDate, GlobalMailReminder paramGlobalMailReminder);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/HotelDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */