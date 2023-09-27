package com.aof.dao.basic;

import com.aof.model.basic.BadReasons;
import com.aof.model.basic.WmsTool;
import com.aof.model.basic.query.WmsToolQueryOrder;
import java.util.List;
import java.util.Map;

public interface WmsToolDAO {
  WmsTool getWmsTool(Integer paramInteger);
  
  int getWmsToolListCount(Map paramMap);
  
  List getWmsToolList(Map paramMap, int paramInt1, int paramInt2, WmsToolQueryOrder paramWmsToolQueryOrder, boolean paramBoolean);
  
  WmsTool insertWmsTool(WmsTool paramWmsTool);
  
  WmsTool updateWmsTool(WmsTool paramWmsTool);
  
  List getEnabledWmsToolList();
  
  void deleteWmsTool(WmsTool paramWmsTool);
  
  WmsTool getWmsTool(String paramString);
  
  List<BadReasons> getBadReasons();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/WmsToolDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */