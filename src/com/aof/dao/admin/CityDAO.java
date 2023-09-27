package com.aof.dao.admin;

import com.aof.model.admin.City;
import com.aof.model.admin.Province;
import com.aof.model.admin.query.CityQueryOrder;
import java.util.List;
import java.util.Map;

public interface CityDAO {
  City getCity(Integer paramInteger);
  
  int getCityListCount(Map paramMap);
  
  List getCityList(Map paramMap, int paramInt1, int paramInt2, CityQueryOrder paramCityQueryOrder, boolean paramBoolean);
  
  City insertCity(City paramCity);
  
  City updateCity(City paramCity);
  
  List getEnabledCityList();
  
  List getEnabledCityList(Province paramProvince);
  
  void deleteCity(City paramCity);
  
  City getCityByChnName(Province paramProvince, String paramString);
  
  City getCityByEngName(Province paramProvince, String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/CityDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */