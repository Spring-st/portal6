package com.aof.service.admin;

import com.aof.model.admin.DataTransferParameter;
import com.aof.model.admin.GlobalParameter;
import com.aof.model.admin.Site;
import java.util.List;

public interface ParameterManager {
  GlobalParameter getGlobalParameter();
  
  void updateGlobalParameter(GlobalParameter paramGlobalParameter, List paramList);
  
  List getGlobalMailReminderList();
  
  DataTransferParameter getDataTransferParameter(Site paramSite);
  
  void updateSiteParameter(DataTransferParameter paramDataTransferParameter, List paramList);
  
  List getSiteMailReminderList(Site paramSite);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/ParameterManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */