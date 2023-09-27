package com.aof.service.basic;

import com.aof.model.basic.BadReasons;
import com.aof.model.basic.query.BadReasonsQueryOrder;
import java.util.List;
import java.util.Map;

public interface BadReasonsManager {
  BadReasons getBadReasons(Integer paramInteger);
  
  BadReasons getBadReasons(String paramString);
  
  int getBadReasonsListCount(Map paramMap);
  
  List getBadReasonsList(Map paramMap, int paramInt1, int paramInt2, BadReasonsQueryOrder paramBadReasonsQueryOrder, boolean paramBoolean);
  
  BadReasons insertBadReasons(BadReasons paramBadReasons);
  
  BadReasons updateBadReasons(BadReasons paramBadReasons);
  
  List getEnabledBadReasonsList();
  
  String[] scanningUnqualifiedReason();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/BadReasonsManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */