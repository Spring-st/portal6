package com.aof.service.basic;

import com.aof.model.basic.UselessPart;
import com.aof.model.basic.query.UselessPartQueryOrder;
import java.util.List;
import java.util.Map;

public interface UselessPartManager {
  UselessPart getUselessPart(Integer paramInteger);
  
  int getUselessPartListCount(Map paramMap);
  
  List getUselessPartList(Map paramMap, int paramInt1, int paramInt2, UselessPartQueryOrder paramUselessPartQueryOrder, boolean paramBoolean);
  
  void insertUselessPart(UselessPart paramUselessPart);
  
  void updateUselessPart(UselessPart paramUselessPart);
  
  void deleteUselessPart(UselessPart paramUselessPart);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/UselessPartManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */