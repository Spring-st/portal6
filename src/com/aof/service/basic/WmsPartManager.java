package com.aof.service.basic;

import com.aof.model.admin.Supplier;
import com.aof.model.basic.WmsPart;
import com.aof.model.basic.query.WmsPartQueryOrder;
import java.util.List;
import java.util.Map;

public interface WmsPartManager {
  WmsPart getWmsPart(String paramString);
  
  int getWmsPartListCount(Map paramMap);
  
  List getWmsPartList(Map paramMap, int paramInt1, int paramInt2, WmsPartQueryOrder paramWmsPartQueryOrder, boolean paramBoolean);
  
  WmsPart insertWmsPart(WmsPart paramWmsPart);
  
  WmsPart updateWmsPart(WmsPart paramWmsPart);
  
  List getEnabledWmsPartList();
  
  void deleteWmsPart(WmsPart paramWmsPart);
  
  boolean isExits(String paramString1, String paramString2);
  
  List<WmsPart> getWmsPartListBySupplierPartCode(List<WmsPart> paramList, Supplier paramSupplier);
  
  List getWmsPartListEnabledAll();
  
  boolean validatePartIsFreeze(String paramString);
  
  void init();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/WmsPartManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */