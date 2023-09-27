package com.aof.service.kpi;

import com.aof.model.admin.Site;
import com.aof.model.kpi.query.KPISummaryQueryOrder;
import java.util.List;
import java.util.Map;

public interface KPIManager {
  void doSummary();
  
  List getKPISummaryList(Map paramMap, int paramInt1, int paramInt2, KPISummaryQueryOrder paramKPISummaryQueryOrder, boolean paramBoolean);
  
  List getTop5KPIExpenseCategorySummary(Site paramSite);
  
  List getTop5KPIPurchaseCategorySummary(Site paramSite);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/kpi/KPIManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */