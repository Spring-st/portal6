package com.aof.dao.kpi;

import com.aof.model.admin.Site;
import com.aof.model.kpi.KPIExpenseCategorySummary;
import com.aof.model.kpi.KPIPurchaseCategorySummary;
import com.aof.model.kpi.KPISummary;
import com.aof.model.kpi.query.KPISummaryQueryOrder;
import java.util.List;
import java.util.Map;

public interface KPISummaryDAO {
  KPISummary getKPISummary(Integer paramInteger);
  
  KPISummary insertKPISummary(KPISummary paramKPISummary);
  
  KPISummary updateKPISummary(KPISummary paramKPISummary);
  
  void deleteKPISummary(KPISummary paramKPISummary);
  
  KPIPurchaseCategorySummary getKPIPurchaseCategorySummary(Integer paramInteger);
  
  KPIPurchaseCategorySummary insertKPIPurchaseCategorySummary(KPIPurchaseCategorySummary paramKPIPurchaseCategorySummary);
  
  KPIPurchaseCategorySummary updateKPIPurchaseCategorySummary(KPIPurchaseCategorySummary paramKPIPurchaseCategorySummary);
  
  void deleteKPIPurchaseCategorySummary(KPIPurchaseCategorySummary paramKPIPurchaseCategorySummary);
  
  KPIExpenseCategorySummary getKPIExpenseCategorySummary(Integer paramInteger);
  
  KPIExpenseCategorySummary insertKPIExpenseCategorySummary(KPIExpenseCategorySummary paramKPIExpenseCategorySummary);
  
  KPIExpenseCategorySummary updateKPIExpenseCategorySummary(KPIExpenseCategorySummary paramKPIExpenseCategorySummary);
  
  void deleteKPIExpenseCategorySummary(KPIExpenseCategorySummary paramKPIExpenseCategorySummary);
  
  List getKPISummaryList(Map paramMap, int paramInt1, int paramInt2, KPISummaryQueryOrder paramKPISummaryQueryOrder, boolean paramBoolean);
  
  List getTop5KPIExpenseCategorySummary(Site paramSite);
  
  List getTop5KPIPurchaseCategorySummary(Site paramSite);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/kpi/KPISummaryDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */