package com.aof.service.basic;

import com.aof.model.basic.BasicPartLocation;
import com.aof.model.basic.query.BasicPartLocationQueryOrder;
import java.util.List;
import java.util.Map;
import jxl.Sheet;

public interface BasicPartLocationManager {
  BasicPartLocation getBasicPartLocation(Integer paramInteger);
  
  int getBasicPartLocationListCount(Map paramMap);
  
  List getBasicPartLocationList(Map paramMap, int paramInt1, int paramInt2, BasicPartLocationQueryOrder paramBasicPartLocationQueryOrder, boolean paramBoolean);
  
  BasicPartLocation insertBasicPartLocation(BasicPartLocation paramBasicPartLocation);
  
  BasicPartLocation updateBasicPartLocation(BasicPartLocation paramBasicPartLocation);
  
  List getEnabledBasicPartLocationList();
  
  void insertBasicPartLocation(Sheet[] paramArrayOfSheet);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/BasicPartLocationManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */