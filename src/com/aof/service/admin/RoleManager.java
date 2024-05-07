package com.aof.service.admin;

import com.aof.model.admin.Function;
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
  
  void saveFunctionListForRole(Role paramRole, List<Function> paramList) throws Exception;
}
