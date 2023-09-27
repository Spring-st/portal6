package com.aof.service.admin;

import com.aof.model.admin.Department;
import com.aof.model.admin.Function;
import com.aof.model.admin.Site;
import com.aof.model.admin.User;
import com.aof.model.admin.UserDepartment;
import com.aof.model.admin.UserRole;
import com.aof.model.admin.UserSite;
import com.aof.model.admin.query.UserQueryOrder;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserManager {
  User getUser(Integer paramInteger);
  
  User saveUser(User paramUser);
  
  void removeUser(Integer paramInteger);
  
  User getUserByLoginName(String paramString);
  
  int getUserListCount(Map paramMap);
  
  List getUserList(Map paramMap, int paramInt1, int paramInt2, UserQueryOrder paramUserQueryOrder, boolean paramBoolean);
  
  List getUserSiteListByUser(User paramUser);
  
  List getUserDepartmentListByUser(User paramUser);
  
  List getUserDepartmentListByUserAndSite(User paramUser, Site paramSite);
  
  UserSite getUserSite(Integer paramInteger1, Integer paramInteger2);
  
  UserSite saveUserSite(UserSite paramUserSite);
  
  UserSite updateUserSite(UserSite paramUserSite);
  
  void removeUserSite(UserSite paramUserSite);
  
  UserDepartment getUserDepartment(Integer paramInteger1, Integer paramInteger2);
  
  UserDepartment saveUserDepartment(UserDepartment paramUserDepartment);
  
  UserDepartment updateUserDepartment(UserDepartment paramUserDepartment);
  
  void removeUserDepartment(UserDepartment paramUserDepartment);
  
  List getUserRoleListByUser(User paramUser);
  
  List getUserRoleListByUserAndFunction(User paramUser, Function paramFunction);
  
  UserRole getUserRole(Integer paramInteger);
  
  UserRole saveUserRole(UserRole paramUserRole);
  
  void removeUserRole(UserRole paramUserRole);
  
  List getGrantedSiteList(User paramUser, Function paramFunction);
  
  List getEnabledUserList(Function paramFunction);
  
  boolean hasSitePower(Site paramSite, User paramUser, Function paramFunction);
  
  boolean hasDepartmentPower(Department paramDepartment, User paramUser, Function paramFunction);
  
  List getGrantedSiteDeparmentList(User paramUser, Function paramFunction);
  
  boolean hasGlobalPower(User paramUser, Function paramFunction);
  
  Set getGrantedMenuSet(User paramUser);
  
  List getEnabledSiteDepartmentListOfUser(User paramUser);
  
  List getEnabledUserSiteList(User paramUser);
  
  List getEnabledUserSiteListWithDepartmentsAndExpenseCategory(User paramUser);
  
  List getEnabledUserDepartmentList(User paramUser, Site paramSite);
  
  List getSiteOfGrantedSiteDeparmentList(User paramUser, Function paramFunction);
  
  List getGrantedDepartmentListOfSite(User paramUser, Site paramSite, Function paramFunction);
  
  List getEnabledUserListOfDepartment(Department paramDepartment);
  
  boolean hasUserPower(User paramUser1, User paramUser2, Function paramFunction);
  
  List getEnabledUserList(Function paramFunction, Site paramSite);
  
  List getEnabledUserList(Function paramFunction, Department paramDepartment);
  
  List getEnabledSiteDepartmentTreeOfUser(User paramUser);
  
  List getEnabledSiteListOfUser(User paramUser);
  
  void fillUserRole(List paramList);
  
  List getUserRoleList(Map paramMap, UserQueryOrder paramUserQueryOrder, boolean paramBoolean);
  
  boolean hasGlobalPowers(User paramUser, Function paramFunction);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/UserManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */