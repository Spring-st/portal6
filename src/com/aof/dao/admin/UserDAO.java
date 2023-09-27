package com.aof.dao.admin;

import com.aof.dao.DAO;
import com.aof.model.admin.Department;
import com.aof.model.admin.Function;
import com.aof.model.admin.Site;
import com.aof.model.admin.User;
import com.aof.model.admin.UserRole;
import com.aof.model.admin.query.UserQueryOrder;
import java.util.List;
import java.util.Map;

public interface UserDAO extends DAO {
  User getUser(Integer paramInteger);
  
  User saveUser(User paramUser);
  
  void removeUser(Integer paramInteger);
  
  int getUserListCount(Map paramMap);
  
  List getUserList(Map paramMap, int paramInt1, int paramInt2, UserQueryOrder paramUserQueryOrder, boolean paramBoolean);
  
  List getUserRoleListByUser(User paramUser);
  
  List getUserRoleListByUserAndFunction(User paramUser, Function paramFunction);
  
  UserRole getUserRole(Integer paramInteger);
  
  UserRole saveUserRole(UserRole paramUserRole);
  
  void removeUserRole(UserRole paramUserRole);
  
  List getGrantedSiteList(User paramUser, Function paramFunction);
  
  boolean hasSitePower(Site paramSite, User paramUser, Function paramFunction);
  
  List getGrantedSiteDeparmentList(User paramUser, Function paramFunction);
  
  boolean hasDepartmentPower(Department paramDepartment, User paramUser, Function paramFunction);
  
  boolean hasGlobalPower(User paramUser, Function paramFunction);
  
  List getGrantedMenuList(User paramUser);
  
  List getSiteOfGrantedSiteDeparmentList(User paramUser, Function paramFunction);
  
  List getGrantedDeparmentListOfSite(User paramUser, Site paramSite, Function paramFunction);
  
  boolean hasUserPower(User paramUser1, User paramUser2, Function paramFunction);
  
  List getEnabledUserList(Function paramFunction, Site paramSite);
  
  List getEnabledUserList(Function paramFunction, Department paramDepartment);
  
  List getEnabledUserList(Function paramFunction);
  
  boolean hasGlobalPowers(User paramUser, Function paramFunction);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/UserDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */