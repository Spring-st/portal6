package com.aof.service.business;

import com.aof.model.business.Approvable;
import com.aof.model.business.BaseApproveRequest;
import com.aof.model.metadata.ApproverDelegateType;

public interface ApproveRelativeEmailManager {
  void sendApprovalEmail(Approvable paramApprovable, ApproverDelegateType paramApproverDelegateType, BaseApproveRequest paramBaseApproveRequest);
  
  void sendApprovedEmail(Approvable paramApprovable);
  
  void sendRejectedEmail(Approvable paramApprovable, BaseApproveRequest paramBaseApproveRequest);
  
  void sendRejectedEmail(Approvable paramApprovable, String paramString1, String paramString2);
  
  void deleteWithdrawEmail(Approvable paramApprovable);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/business/ApproveRelativeEmailManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */