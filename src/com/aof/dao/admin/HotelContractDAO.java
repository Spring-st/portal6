package com.aof.dao.admin;

import com.aof.model.admin.HotelContract;
import com.aof.model.admin.query.HotelContractQueryOrder;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface HotelContractDAO {
  HotelContract getHotelContract(Integer paramInteger);
  
  int getHotelContractListCount(Map paramMap);
  
  List getHotelContractList(Map paramMap, int paramInt1, int paramInt2, HotelContractQueryOrder paramHotelContractQueryOrder, boolean paramBoolean);
  
  HotelContract insertHotelContract(HotelContract paramHotelContract);
  
  HotelContract updateHotelContract(HotelContract paramHotelContract);
  
  void saveHotelContractContent(Integer paramInteger, InputStream paramInputStream);
  
  InputStream getHotelContractContent(Integer paramInteger);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/HotelContractDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */