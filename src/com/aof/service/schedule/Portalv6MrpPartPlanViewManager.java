package com.aof.service.schedule;

import com.aof.model.schedule.Portalv6MrpPartPlanView;
import com.aof.model.schedule.query.Portalv6MrpPartPlanViewQueryOrder;
import java.util.List;
import java.util.Map;

public interface Portalv6MrpPartPlanViewManager {
  List<Portalv6MrpPartPlanView> getList(Map paramMap, int paramInt1, int paramInt2, Portalv6MrpPartPlanViewQueryOrder paramPortalv6MrpPartPlanViewQueryOrder, boolean paramBoolean);
  
  Integer getListCount(Map paramMap);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/schedule/Portalv6MrpPartPlanViewManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */