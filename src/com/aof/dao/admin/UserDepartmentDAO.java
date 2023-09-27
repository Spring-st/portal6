package com.aof.dao.admin;

import com.aof.dao.DAO;
import com.aof.model.admin.UserDepartment;
import com.aof.model.admin.query.UserDepartmentQueryOrder;
import java.util.List;
import java.util.Map;

public interface UserDepartmentDAO extends DAO {
  UserDepartment getUserDepartment(Integer paramInteger1, Integer paramInteger2);
  
  UserDepartment saveUserDepartment(UserDepartment paramUserDepartment);
  
  UserDepartment updateUserDepartment(UserDepartment paramUserDepartment);
  
  void removeUserDepartment(UserDepartment paramUserDepartment);
  
  int getUserDepartmentListCount(Map paramMap);
  
  List getUserDepartmentList(Map paramMap, int paramInt1, int paramInt2, UserDepartmentQueryOrder paramUserDepartmentQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/UserDepartmentDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */