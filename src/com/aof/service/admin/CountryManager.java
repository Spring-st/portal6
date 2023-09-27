package com.aof.service.admin;

import com.aof.model.admin.Country;
import com.aof.model.admin.query.CountryQueryOrder;
import java.util.List;
import java.util.Map;

public interface CountryManager {
  Country getCountry(Integer paramInteger);
  
  Country insertCountry(Country paramCountry);
  
  Country updateCountry(Country paramCountry);
  
  int getCountryListCount(Map paramMap);
  
  List getCountryList(Map paramMap, int paramInt1, int paramInt2, CountryQueryOrder paramCountryQueryOrder, boolean paramBoolean);
  
  List listEnabledCountryProvinceCity();
  
  List listEnabledCountryCity();
  
  void promoteCountry(Country paramCountry);
  
  List getEnabledCountryList();
  
  boolean deleteCountry(Country paramCountry);
  
  Country getCountryByChnName(String paramString);
  
  Country getCountryByEngName(String paramString);
  
  Country getCountryByShortName(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/CountryManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */