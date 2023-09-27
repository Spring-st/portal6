package com.aof.dao.admin;

import com.aof.dao.DAO;
import com.aof.model.admin.Department;
import com.aof.model.admin.query.DepartmentQueryOrder;
import java.util.List;
import java.util.Map;

public interface DepartmentDAO extends DAO {
  Department getDepartment(Integer paramInteger);
  
  Department saveDepartment(Department paramDepartment);
  
  void removeDepartment(Integer paramInteger);
  
  int getDepartmentListCount(Map paramMap);
  
  List getDepartmentList(Map paramMap, int paramInt1, int paramInt2, DepartmentQueryOrder paramDepartmentQueryOrder, boolean paramBoolean);
  
  void fillSiteDepartment(List paramList, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/DepartmentDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */