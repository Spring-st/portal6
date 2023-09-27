package com.aof.dao.basicDataView;

import com.aof.dao.DAO;
import com.aof.model.basicDataView.PartForecastNeedReport;
import com.aof.model.basicDataView.SkPartSumNumber;
import com.aof.model.basicDataView.query.JitShipPartQueryOrder;
import com.aof.model.basicDataView.query.PartForecastNeedReportQueryOrder;
import com.aof.model.basicDataView.query.SkPartSumNumberQueryOrder;
import java.util.List;
import java.util.Map;

public interface BasicDataViewDAO extends DAO {
  SkPartSumNumber getSkPartSumNumber(String paramString);
  
  int getSkPartSumNumberListCount(Map paramMap);
  
  List getSkPartSumNumberList(Map paramMap, int paramInt1, int paramInt2, SkPartSumNumberQueryOrder paramSkPartSumNumberQueryOrder, boolean paramBoolean);
  
  int getJitShipPartListCount(Map paramMap);
  
  List getJitShipPartNumberList(Map paramMap, int paramInt1, int paramInt2, JitShipPartQueryOrder paramJitShipPartQueryOrder, boolean paramBoolean);
  
  Integer getPartForecastNeedReportListCount(Map paramMap);
  
  List<PartForecastNeedReport> getPartForecastNeedReportList(Map paramMap, int paramInt1, int paramInt2, PartForecastNeedReportQueryOrder paramPartForecastNeedReportQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basicDataView/BasicDataViewDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */