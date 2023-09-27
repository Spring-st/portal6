package com.aof.service.admin;

import com.aof.model.admin.Country;
import com.aof.model.admin.Province;
import com.aof.model.admin.query.ProvinceQueryOrder;
import java.util.List;
import java.util.Map;

public interface ProvinceManager {
  Province getProvince(Integer paramInteger);
  
  Province insertProvince(Province paramProvince);
  
  Province updateProvince(Province paramProvince);
  
  int getProvinceListCount(Map paramMap);
  
  List getProvinceList(Map paramMap, int paramInt1, int paramInt2, ProvinceQueryOrder paramProvinceQueryOrder, boolean paramBoolean);
  
  void promoteProvince(Province paramProvince);
  
  List getEnabledProvinceOfCountry(Country paramCountry);
  
  boolean deleteProvince(Province paramProvince);
  
  Province getProvinceByChnName(Country paramCountry, String paramString);
  
  Province getProvinceByEngName(Country paramCountry, String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/ProvinceManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */