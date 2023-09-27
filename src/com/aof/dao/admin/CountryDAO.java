package com.aof.dao.admin;

import com.aof.model.admin.Country;
import com.aof.model.admin.query.CountryQueryOrder;
import java.util.List;
import java.util.Map;

public interface CountryDAO {
  Country getCountry(Integer paramInteger);
  
  int getCountryListCount(Map paramMap);
  
  List getCountryList(Map paramMap, int paramInt1, int paramInt2, CountryQueryOrder paramCountryQueryOrder, boolean paramBoolean);
  
  Country insertCountry(Country paramCountry);
  
  Country updateCountry(Country paramCountry);
  
  List listEnabledCountry();
  
  void deleteCountry(Country paramCountry);
  
  Country getCountryByChnName(String paramString);
  
  Country getCountryByEngName(String paramString);
  
  Country getCountryByShortName(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/CountryDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */