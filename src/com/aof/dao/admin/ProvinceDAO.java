package com.aof.dao.admin;

import com.aof.model.admin.Country;
import com.aof.model.admin.Province;
import com.aof.model.admin.query.ProvinceQueryOrder;
import java.util.List;
import java.util.Map;

public interface ProvinceDAO {
  Province getProvince(Integer paramInteger);
  
  int getProvinceListCount(Map paramMap);
  
  List getProvinceList(Map paramMap, int paramInt1, int paramInt2, ProvinceQueryOrder paramProvinceQueryOrder, boolean paramBoolean);
  
  Province insertProvince(Province paramProvince);
  
  Province updateProvince(Province paramProvince);
  
  List getEnabledProvinceList();
  
  List getEnabledProvinceList(Country paramCountry);
  
  void deleteProvince(Province paramProvince);
  
  Province getProvinceByChnName(Country paramCountry, String paramString);
  
  Province getProvinceByEngName(Country paramCountry, String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/ProvinceDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */