package com.aof.service.admin;

import com.aof.model.admin.Role;
import com.aof.model.admin.query.RoleQueryOrder;
import java.util.List;
import java.util.Map;

public interface RoleManager {
  Role getRole(Integer paramInteger) throws Exception;
  
  Role saveRole(Role paramRole) throws Exception;
  
  void removeRole(Integer paramInteger) throws Exception;
  
  int getRoleListCount(Map paramMap) throws Exception;
  
  List getRoleList(Map paramMap, int paramInt1, int paramInt2, RoleQueryOrder paramRoleQueryOrder, boolean paramBoolean) throws Exception;
  
  List getAllRoleList() throws Exception;
  
  List getFunctionListByRole(Role paramRole) throws Exception;
  
  void saveFunctionListForRole(Role paramRole, List paramList) throws Exception;
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/RoleManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */