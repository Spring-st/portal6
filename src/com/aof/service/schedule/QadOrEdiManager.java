package com.aof.service.schedule;

import com.aof.model.schedule.QadOrEdi;
import com.aof.model.schedule.query.QadOrEdiQueryOrder;
import java.util.List;
import java.util.Map;

public interface QadOrEdiManager {
  QadOrEdi save(QadOrEdi paramQadOrEdi);
  
  QadOrEdi getQadOrEdi(Integer paramInteger);
  
  void delete(QadOrEdi paramQadOrEdi);
  
  QadOrEdi update(QadOrEdi paramQadOrEdi);
  
  Integer getListCount(Map paramMap);
  
  List<QadOrEdi> getList(Map paramMap, int paramInt1, int paramInt2, QadOrEdiQueryOrder paramQadOrEdiQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/schedule/QadOrEdiManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */