package com.aof.service.admin;

import com.aof.model.admin.City;
import com.aof.model.admin.Province;
import com.aof.model.admin.query.CityQueryOrder;
import java.util.List;
import java.util.Map;

public interface CityManager {
  City getCity(Integer paramInteger);
  
  City insertCity(City paramCity);
  
  City updateCity(City paramCity);
  
  int getCityListCount(Map paramMap);
  
  List getCityList(Map paramMap, int paramInt1, int paramInt2, CityQueryOrder paramCityQueryOrder, boolean paramBoolean);
  
  void promoteCity(City paramCity);
  
  List getEnabledCityList(Province paramProvince);
  
  boolean deleteCity(City paramCity);
  
  City getCityByChnName(Province paramProvince, String paramString);
  
  City getCityByEngName(Province paramProvince, String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/CityManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */