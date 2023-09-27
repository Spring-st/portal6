package com.aof.dao.basic;

import com.aof.model.basic.BadReasons;
import com.aof.model.basic.query.BadReasonsQueryOrder;
import java.util.List;
import java.util.Map;

public interface BadReasonsDAO {
  BadReasons getBadReasons(Integer paramInteger);
  
  int getBadReasonsListCount(Map paramMap);
  
  List getBadReasonsList(Map paramMap, int paramInt1, int paramInt2, BadReasonsQueryOrder paramBadReasonsQueryOrder, boolean paramBoolean);
  
  BadReasons insertBadReasons(BadReasons paramBadReasons);
  
  BadReasons updateBadReasons(BadReasons paramBadReasons);
  
  List getEnabledBadReasonsList();
  
  BadReasons getBadReasons(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/BadReasonsDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */