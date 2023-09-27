package com.aof.dao.admin;

import com.aof.dao.DAO;
import com.aof.model.admin.Role;
import com.aof.model.admin.RoleFunction;
import com.aof.model.admin.query.RoleQueryOrder;
import java.util.List;
import java.util.Map;

public interface RoleDAO extends DAO {
  Role getRole(Integer paramInteger);
  
  Role saveRole(Role paramRole);
  
  void removeRole(Integer paramInteger);
  
  int getRoleListCount(Map paramMap);
  
  List getRoleList(Map paramMap, int paramInt1, int paramInt2, RoleQueryOrder paramRoleQueryOrder, boolean paramBoolean);
  
  List getFunctionListByRole(Role paramRole);
  
  RoleFunction saveRoleFunction(RoleFunction paramRoleFunction);
  
  void removeRoleFunction(RoleFunction paramRoleFunction);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/RoleDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */