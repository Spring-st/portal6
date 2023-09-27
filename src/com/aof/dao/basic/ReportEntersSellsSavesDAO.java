package com.aof.dao.basic;

import com.aof.dao.DAO;
import com.aof.model.basic.ReportEntersSellsSaves;
import com.aof.model.basic.query.ReportEntersSellsSavesQueryOrder;
import java.util.List;
import java.util.Map;

public interface ReportEntersSellsSavesDAO extends DAO {
  ReportEntersSellsSaves getReportEntersSellsSaves(Integer paramInteger);
  
  int getReportEntersSellsSavesListCount(Map paramMap);
  
  List getReportEntersSellsSavesList(Map paramMap, int paramInt1, int paramInt2, ReportEntersSellsSavesQueryOrder paramReportEntersSellsSavesQueryOrder, boolean paramBoolean);
  
  ReportEntersSellsSaves insertReportEntersSellsSaves(ReportEntersSellsSaves paramReportEntersSellsSaves);
  
  ReportEntersSellsSaves updateReportEntersSellsSaves(ReportEntersSellsSaves paramReportEntersSellsSaves);
  
  List getEnabledReportEntersSellsSavesList();
  
  ReportEntersSellsSaves getReportEntersSellsSaves(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/ReportEntersSellsSavesDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */