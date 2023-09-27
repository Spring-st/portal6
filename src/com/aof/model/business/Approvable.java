package com.aof.model.business;

import com.aof.model.admin.Site;
import com.aof.model.admin.User;
import java.util.Map;

public interface Approvable {
  BaseApproveRequest createNewApproveRequestObj();
  
  User getRequestor();
  
  String getApproveFlowName();
  
  String getApprovalBatchEmailTemplateName();
  
  String getApprovalNotifyEmailTemplateName();
  
  Map getApprovalNotifyEmailContext();
  
  String getApprovedNotifyEmailTemplateName();
  
  Map getApprovedNotifyEmailContext();
  
  String getRejectedNotifyEmailTemplateName();
  
  Map getRejectedNotifyEmailContext();
  
  Site getLogSite();
  
  String getApproveRequestId();
  
  String getRefNo();
  
  User getCreator();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/Approvable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */