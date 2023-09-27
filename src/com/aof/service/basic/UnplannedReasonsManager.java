package com.aof.service.basic;

import com.aof.model.basic.UnplannedReasons;
import com.aof.model.basic.query.UnplannedReasonsQueryOrder;
import java.util.List;
import java.util.Map;

public interface UnplannedReasonsManager {
  UnplannedReasons getUnplannedReasons(Integer paramInteger);
  
  int getUnplannedReasonsListCount(Map paramMap);
  
  List getUnplannedReasonsList(Map paramMap, int paramInt1, int paramInt2, UnplannedReasonsQueryOrder paramUnplannedReasonsQueryOrder, boolean paramBoolean);
  
  UnplannedReasons insertUnplannedReasons(UnplannedReasons paramUnplannedReasons);
  
  UnplannedReasons updateUnplannedReasons(UnplannedReasons paramUnplannedReasons);
  
  List getEnabledUnplannedReasonsList();
  
  UnplannedReasons getUnplannedReasons(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/UnplannedReasonsManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */