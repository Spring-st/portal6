package com.aof.dao.basicDataView;

import com.aof.model.basicDataView.EditProductionReport;
import com.aof.model.schedule.query.EdiProductionQueryOrder;
import java.util.List;
import java.util.Map;

public interface EditProductionReportDAO {
  List<EditProductionReport> list(Map paramMap, int paramInt1, int paramInt2, EdiProductionQueryOrder paramEdiProductionQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basicDataView/EditProductionReportDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */