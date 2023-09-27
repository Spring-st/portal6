package com.aof.service.po;

import com.aof.model.po.PoHighLineTwo;
import com.aof.model.po.query.PoHighLineTwoQueryOrder;
import java.util.List;
import java.util.Map;

public interface PoHighLineManager {
  PoHighLineTwo getPoHighLineTwo(Integer paramInteger);
  
  int getPoHighLineOneListCount(Map paramMap);
  
  int getPoHighLineTwoListCount(Map paramMap);
  
  List getPoHighLineOneList(Map paramMap, int paramInt1, int paramInt2, PoHighLineTwoQueryOrder paramPoHighLineTwoQueryOrder, boolean paramBoolean);
  
  List getPoHighLineTwoList(Map paramMap, int paramInt1, int paramInt2, PoHighLineTwoQueryOrder paramPoHighLineTwoQueryOrder, boolean paramBoolean);
  
  PoHighLineTwo insertPoHighLineTwo(PoHighLineTwo paramPoHighLineTwo);
  
  PoHighLineTwo updatePoHighLineTwo(PoHighLineTwo paramPoHighLineTwo);
  
  int getPoHighLineBelowListCount(Map paramMap);
  
  List getPoHighLineBelowList(Map paramMap, int paramInt1, int paramInt2, PoHighLineTwoQueryOrder paramPoHighLineTwoQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/PoHighLineManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */