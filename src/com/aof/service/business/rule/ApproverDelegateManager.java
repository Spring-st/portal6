package com.aof.service.business.rule;

import com.aof.model.business.rule.ApproverDelegate;
import com.aof.model.business.rule.query.ApproverDelegateQueryOrder;
import com.aof.model.business.rule.query.ApproverQueryCondition;
import com.aof.model.metadata.ApproverDelegateType;
import java.util.List;
import java.util.Map;

public interface ApproverDelegateManager {
  ApproverDelegate getApproverDelegate(Integer paramInteger) throws Exception;
  
  ApproverDelegate insertApproverDelegate(ApproverDelegate paramApproverDelegate) throws Exception;
  
  ApproverDelegate updateApproverDelegate(ApproverDelegate paramApproverDelegate) throws Exception;
  
  int getApproverDelegateListCount(Map paramMap) throws Exception;
  
  List getApproverDelegateList(Map paramMap, int paramInt1, int paramInt2, ApproverDelegateQueryOrder paramApproverDelegateQueryOrder, boolean paramBoolean) throws Exception;
  
  List getApproverList(ApproverQueryCondition paramApproverQueryCondition, int paramInt1, int paramInt2, ApproverDelegateQueryOrder paramApproverDelegateQueryOrder, boolean paramBoolean) throws Exception;
  
  int getApproverListCount(ApproverQueryCondition paramApproverQueryCondition) throws Exception;
  
  boolean isDelegateApprover(ApproverDelegateType paramApproverDelegateType, Integer paramInteger1, Integer paramInteger2);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/business/rule/ApproverDelegateManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */