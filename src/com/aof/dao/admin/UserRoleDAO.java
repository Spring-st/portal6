package com.aof.dao.admin;

import com.aof.dao.DAO;
import com.aof.model.admin.query.UserQueryOrder;
import java.util.List;
import java.util.Map;

public interface UserRoleDAO extends DAO {
  void fillUserRole(List paramList);
  
  List getUserRoleList(Map paramMap, UserQueryOrder paramUserQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/UserRoleDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */