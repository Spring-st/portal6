package com.aof.service.admin;

import com.aof.model.admin.Department;
import com.aof.model.admin.Site;
import com.aof.model.admin.query.DepartmentQueryOrder;
import java.util.List;
import java.util.Map;

public interface DepartmentManager {
  Department getDepartment(Integer paramInteger);
  
  Department saveDepartment(Department paramDepartment);
  
  void removeDepartment(Integer paramInteger);
  
  int getDepartmentListCount(Map paramMap);
  
  List getDepartmentList(Map paramMap, int paramInt1, int paramInt2, DepartmentQueryOrder paramDepartmentQueryOrder, boolean paramBoolean);
  
  String getDepartmentXmlBySite(Integer paramInteger);
  
  void fillDepartment(List paramList, boolean paramBoolean);
  
  void fillDepartment(Site paramSite, boolean paramBoolean);
  
  List getEnabledDepartmentTreeOfSite(Site paramSite);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/DepartmentManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */