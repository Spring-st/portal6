package com.aof.dao.business.rule;

import com.aof.model.business.rule.ApproverDelegate;
import com.aof.model.business.rule.query.ApproverDelegateQueryOrder;
import com.aof.model.metadata.ApproverDelegateType;
import java.util.List;
import java.util.Map;

public interface ApproverDelegateDAO {
  ApproverDelegate getApproverDelegate(Integer paramInteger);
  
  int getApproverDelegateListCount(Map paramMap);
  
  List getApproverDelegateList(Map paramMap, int paramInt1, int paramInt2, ApproverDelegateQueryOrder paramApproverDelegateQueryOrder, boolean paramBoolean);
  
  ApproverDelegate insertApproverDelegate(ApproverDelegate paramApproverDelegate);
  
  ApproverDelegate updateApproverDelegate(ApproverDelegate paramApproverDelegate);
  
  boolean isDelegateApprover(ApproverDelegateType paramApproverDelegateType, Integer paramInteger1, Integer paramInteger2);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/business/rule/ApproverDelegateDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */