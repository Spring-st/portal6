package com.aof.dao.business;

import com.aof.model.business.BaseApproveRequest;
import java.util.List;
import java.util.Map;

public interface BaseApproveRequestDAO {
  List getBaseApproveRequestList(Map paramMap);
  
  List getBaseApproveRequestListByApproveRequestId(String paramString);
  
  void updateBaseApproveRequest(BaseApproveRequest paramBaseApproveRequest);
  
  void insertBaseApproveRequest(BaseApproveRequest paramBaseApproveRequest);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/business/BaseApproveRequestDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */