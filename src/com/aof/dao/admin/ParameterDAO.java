package com.aof.dao.admin;

import com.aof.model.admin.DataTransferParameter;
import com.aof.model.admin.GlobalMailReminder;
import com.aof.model.admin.GlobalParameter;
import com.aof.model.admin.Site;
import com.aof.model.metadata.GlobalMailReminderType;
import java.util.List;
import java.util.Set;

public interface ParameterDAO {
  GlobalParameter getGlobalParameter();
  
  GlobalParameter updateGlobalParameter(GlobalParameter paramGlobalParameter);
  
  List getGlobalMailReminderList();
  
  void updateGlobalMailReminder(List paramList);
  
  DataTransferParameter getDataTransferParameter(Site paramSite);
  
  DataTransferParameter updateDataTransferParameter(DataTransferParameter paramDataTransferParameter);
  
  void updateSiteMailReminder(Site paramSite, List paramList);
  
  List getSiteMailReminderList(Site paramSite);
  
  Set getEnabledSiteSetWithMailReminders();
  
  GlobalMailReminder getGlobalMailReminder(GlobalMailReminderType paramGlobalMailReminderType);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/ParameterDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */