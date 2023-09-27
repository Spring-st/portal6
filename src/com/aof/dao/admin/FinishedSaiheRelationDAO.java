package com.aof.dao.admin;

import com.aof.model.admin.FinishedSaiheRelation;
import com.aof.model.admin.query.FinishedSaiheRelationQueryOrder;
import java.util.List;
import java.util.Map;

public interface FinishedSaiheRelationDAO {
  FinishedSaiheRelation getFinishedSaiheRelation(Integer paramInteger);
  
  int getFinishedSaiheRelationListCount(Map paramMap);
  
  List getFinishedSaiheRelationList(Map paramMap, int paramInt1, int paramInt2, FinishedSaiheRelationQueryOrder paramFinishedSaiheRelationQueryOrder, boolean paramBoolean);
  
  FinishedSaiheRelation insertFinishedSaiheRelation(FinishedSaiheRelation paramFinishedSaiheRelation);
  
  FinishedSaiheRelation updateFinishedSaiheRelation(FinishedSaiheRelation paramFinishedSaiheRelation);
  
  void deleteFinishedSaiheRelation(FinishedSaiheRelation paramFinishedSaiheRelation);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/FinishedSaiheRelationDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */