package com.aof.model.business;

import com.aof.model.admin.Site;
import java.util.Map;

public interface Notifiable {
  String getNotifyFlowName();
  
  String getNotifyEmailTemplateName();
  
  Map getNotifyEmailContext();
  
  Site getLogSite();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/Notifiable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */