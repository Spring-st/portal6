package com.aof.dao.basic;

import com.aof.model.basic.BasicPartLocation;
import com.aof.model.basic.query.BasicPartLocationQueryOrder;
import java.util.List;
import java.util.Map;

public interface BasicPartLocationDAO {
  BasicPartLocation getBasicPartLocation(Integer paramInteger);
  
  int getBasicPartLocationListCount(Map paramMap);
  
  List getBasicPartLocationList(Map paramMap, int paramInt1, int paramInt2, BasicPartLocationQueryOrder paramBasicPartLocationQueryOrder, boolean paramBoolean);
  
  BasicPartLocation insertBasicPartLocation(BasicPartLocation paramBasicPartLocation);
  
  BasicPartLocation updateBasicPartLocation(BasicPartLocation paramBasicPartLocation);
  
  List getEnabledBasicPartLocationList();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/BasicPartLocationDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */