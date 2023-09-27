/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.UserDAO;
/*     */ import com.aof.dao.admin.UserDepartmentDAO;
/*     */ import com.aof.dao.admin.UserRoleDAO;
/*     */ import com.aof.dao.admin.UserSiteDAO;
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.Function;
/*     */ import com.aof.model.admin.Menu;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.UserDepartment;
/*     */ import com.aof.model.admin.UserRole;
/*     */ import com.aof.model.admin.UserSite;
/*     */ import com.aof.model.admin.query.UserDepartmentQueryCondition;
/*     */ import com.aof.model.admin.query.UserDepartmentQueryOrder;
/*     */ import com.aof.model.admin.query.UserQueryCondition;
/*     */ import com.aof.model.admin.query.UserQueryOrder;
/*     */ import com.aof.model.admin.query.UserSiteQueryCondition;
/*     */ import com.aof.model.admin.query.UserSiteQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.DepartmentManager;
/*     */ import com.aof.service.admin.ExpenseCategoryManager;
/*     */ import com.aof.service.admin.SiteManager;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UserManagerImpl
/*     */   extends BaseManager
/*     */   implements UserManager
/*     */ {
/*     */   private UserDAO dao;
/*     */   private UserSiteDAO userSiteDAO;
/*     */   private UserDepartmentDAO userDepartmentDAO;
/*     */   private DepartmentManager departmentManager;
/*     */   private SiteManager siteManager;
/*     */   private ExpenseCategoryManager expenseCategoryManager;
/*     */   private UserRoleDAO userRoleDAO;
/*     */   
/*     */   public void setExpenseCategoryManager(ExpenseCategoryManager expenseCategoryManager) {
/*  74 */     this.expenseCategoryManager = expenseCategoryManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDepartmentManager(DepartmentManager departmentManager) {
/*  85 */     this.departmentManager = departmentManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserDAO(UserDAO dao) {
/*  95 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserDepartmentDAO(UserDepartmentDAO userDepartmentDAO) {
/* 105 */     this.userDepartmentDAO = userDepartmentDAO;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserSiteDAO(UserSiteDAO userSiteDAO) {
/* 115 */     this.userSiteDAO = userSiteDAO;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSiteManager(SiteManager siteManager) {
/* 125 */     this.siteManager = siteManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public User getUser(Integer id) {
/* 134 */     return this.dao.getUser(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public User saveUser(User user) {
/* 143 */     return this.dao.saveUser(user);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeUser(Integer id) {
/* 152 */     this.dao.removeUser(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public User getUserByLoginName(String loginName) {
/* 161 */     if (loginName == null)
/* 162 */       return null; 
/* 163 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 164 */     conditions.put(UserQueryCondition.LOGINNAME_EQ, loginName);
/* 165 */     List<User> l = this.dao.getUserList(conditions, 0, -1, null, false);
/* 166 */     if (l.size() == 0)
/* 167 */       return null; 
/* 168 */     return l.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUserListCount(Map conditions) {
/* 177 */     return this.dao.getUserListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getUserList(Map conditions, int pageNo, int pageSize, UserQueryOrder order, boolean descend) {
/* 187 */     return this.dao.getUserList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getUserSiteListByUser(User user) {
/* 196 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 197 */     conditions.put(UserSiteQueryCondition.USER_ID_EQ, user.getId());
/* 198 */     return this.userSiteDAO.getUserSiteList(conditions, 0, -1, UserSiteQueryOrder.SITE_NAME, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserSite getUserSite(Integer userId, Integer siteId) {
/* 208 */     return this.userSiteDAO.getUserSite(userId, siteId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserSite saveUserSite(UserSite us) {
/* 217 */     return this.userSiteDAO.saveUserSite(us);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserSite updateUserSite(UserSite us) {
/* 226 */     return this.userSiteDAO.updateUserSite(us);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeUserSite(UserSite us) {
/* 235 */     for (Iterator<UserDepartment> itor = getUserDepartmentListByUserAndSite(us.getUser(), us.getSite()).iterator(); itor.hasNext();) {
/* 236 */       this.userDepartmentDAO.removeUserDepartment(itor.next());
/*     */     }
/* 238 */     this.userSiteDAO.removeUserSite(us);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getUserDepartmentListByUser(User user) {
/* 247 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 248 */     conditions.put(UserDepartmentQueryCondition.USER_ID_EQ, user.getId());
/* 249 */     return this.userDepartmentDAO.getUserDepartmentList(conditions, 0, -1, UserDepartmentQueryOrder.SITE_NAME, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getUserDepartmentListByUserAndSite(User user, Site site) {
/* 259 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 260 */     conditions.put(UserDepartmentQueryCondition.USER_ID_EQ, user.getId());
/* 261 */     conditions.put(UserDepartmentQueryCondition.SITE_ID_EQ, site.getId());
/* 262 */     return this.userDepartmentDAO.getUserDepartmentList(conditions, 0, -1, UserDepartmentQueryOrder.DEPARTMENT_NAME, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserDepartment getUserDepartment(Integer userId, Integer departmentId) {
/* 272 */     return this.userDepartmentDAO.getUserDepartment(userId, departmentId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserDepartment saveUserDepartment(UserDepartment ud) {
/* 281 */     return this.userDepartmentDAO.saveUserDepartment(ud);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserDepartment updateUserDepartment(UserDepartment ud) {
/* 290 */     return this.userDepartmentDAO.updateUserDepartment(ud);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeUserDepartment(UserDepartment ud) {
/* 299 */     this.userDepartmentDAO.removeUserDepartment(ud);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getUserRoleListByUser(User user) {
/* 308 */     return this.dao.getUserRoleListByUser(user);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getUserRoleListByUserAndFunction(User user, Function function) {
/* 318 */     return this.dao.getUserRoleListByUserAndFunction(user, function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserRole getUserRole(Integer id) {
/* 327 */     return this.dao.getUserRole(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserRole saveUserRole(UserRole ur) {
/* 336 */     return this.dao.saveUserRole(ur);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeUserRole(UserRole ur) {
/* 345 */     this.dao.removeUserRole(ur);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getGrantedSiteList(User user, Function function) {
/* 355 */     if (!function.isDepartment()) {
/* 356 */       return this.dao.getGrantedSiteList(user, function);
/*     */     }
/* 358 */     throw new RuntimeException("not with department level function");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledUserList(Function function) {
/* 364 */     return this.dao.getEnabledUserList(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSitePower(Site site, User user, Function function) {
/* 374 */     return this.dao.hasSitePower(site, user, function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasDepartmentPower(Department department, User user, Function function) {
/* 384 */     return this.dao.hasDepartmentPower(department, user, function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSiteOfGrantedSiteDeparmentList(User user, Function function) {
/* 394 */     List siteList = null;
/* 395 */     if (hasGlobalPower(user, function)) {
/* 396 */       siteList = this.siteManager.getAllEnabledSiteList();
/*     */     } else {
/* 398 */       siteList = this.dao.getSiteOfGrantedSiteDeparmentList(user, function);
/*     */     } 
/*     */     
/* 401 */     return siteList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getGrantedSiteDeparmentList(User user, Function function) {
/* 411 */     List<Site> siteList = null;
/* 412 */     if (hasGlobalPower(user, function)) {
/* 413 */       siteList = this.siteManager.getAllEnabledSiteList();
/* 414 */       this.departmentManager.fillDepartment(siteList, true);
/* 415 */       setGranted(siteList);
/*     */     } else {
/* 417 */       siteList = this.dao.getGrantedSiteList(user, function);
/* 418 */       this.departmentManager.fillDepartment(siteList, true);
/* 419 */       setGranted(siteList);
/*     */       
/* 421 */       List departmentSiteList = this.dao.getGrantedSiteDeparmentList(user, function);
/* 422 */       for (Iterator<Site> iter = departmentSiteList.iterator(); iter.hasNext(); ) {
/* 423 */         Site site = iter.next();
/* 424 */         if (!siteList.contains(site)) {
/* 425 */           siteList.add(site);
/* 426 */           buildDepartmentList(site);
/*     */         } 
/*     */       } 
/*     */     } 
/* 430 */     return siteList;
/*     */   }
/*     */   
/*     */   private void setGranted(Site site) {
/* 434 */     List<Site> list = new ArrayList();
/* 435 */     list.add(site);
/* 436 */     setGranted(list);
/*     */   }
/*     */   
/*     */   private void setGranted(List siteList) {
/* 440 */     for (Iterator<Site> iter = siteList.iterator(); iter.hasNext(); ) {
/* 441 */       Site site = iter.next();
/* 442 */       for (Iterator<Department> iterator = site.getDepartments().iterator(); iterator.hasNext(); ) {
/* 443 */         Department d = iterator.next();
/* 444 */         d.setGranted(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void buildDepartmentList(Site site) {
/* 452 */     List dList = site.getDepartments();
/* 453 */     Set rootSet = new HashSet();
/* 454 */     Map<Object, Object> departmentItems = new HashMap<Object, Object>();
/* 455 */     for (Iterator<Department> iter = dList.iterator(); iter.hasNext(); ) {
/* 456 */       Department d = iter.next();
/* 457 */       d.setGranted(true);
/* 458 */       visiteAncestor(d, rootSet, departmentItems);
/*     */     } 
/* 460 */     List resultList = new ArrayList();
/* 461 */     List rootList = new ArrayList();
/* 462 */     rootList.addAll(rootSet);
/* 463 */     buildList(resultList, rootList, departmentItems, 0);
/* 464 */     site.setDepartments(resultList);
/*     */   }
/*     */   
/*     */   private void buildList(List<Department> resultList, List<Department> rootList, Map departmentItems, int indent) {
/* 468 */     if (rootList == null)
/*     */       return; 
/* 470 */     String strIndent = getIndenetString(indent);
/* 471 */     for (int i = 0; i < rootList.size(); i++) {
/* 472 */       Department d = rootList.get(i);
/* 473 */       d.setIndentName(String.valueOf(strIndent) + d.getName());
/* 474 */       resultList.add(d);
/* 475 */       List l = (List)departmentItems.get(d);
/* 476 */       buildList(resultList, l, departmentItems, indent + 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getIndenetString(int indent) {
/* 481 */     StringBuffer space = new StringBuffer();
/* 482 */     while (indent-- > 0)
/* 483 */       space.append("����"); 
/* 484 */     return space.toString();
/*     */   }
/*     */   
/*     */   private void visiteAncestor(Department d, Set<Department> rootSet, Map departmentItems) {
/* 488 */     Department item = d; while (true) {
/* 489 */       if (item.getParentDepartment() == null) {
/* 490 */         rootSet.add(item);
/*     */         return;
/*     */       } 
/* 493 */       Department parent = item.getParentDepartment();
/* 494 */       if (departmentItems.get(parent) == null) {
/* 495 */         departmentItems.put(parent, new ArrayList());
/*     */       }
/* 497 */       List<Department> childList = (List)departmentItems.get(parent);
/* 498 */       childList.add(item);
/* 499 */       item = parent;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasGlobalPower(User user, Function function) {
/* 512 */     return this.dao.hasGlobalPower(user, function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getGrantedMenuSet(User user) {
/* 521 */     Set<Menu> resultSet = new TreeSet(Menu.MENU_COMPARATOR);
/* 522 */     for (Iterator<Menu> itor = this.dao.getGrantedMenuList(user).iterator(); itor.hasNext(); ) {
/* 523 */       Menu m = itor.next();
/*     */       while (true) {
/* 525 */         Menu pm = m.getParentMenu();
/* 526 */         if (pm == null) {
/* 527 */           resultSet.add(m);
/*     */           break;
/*     */         } 
/* 530 */         pm.addChild(m);
/*     */         
/* 532 */         m = pm;
/*     */       } 
/*     */     } 
/*     */     
/* 536 */     return resultSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledSiteDepartmentListOfUser(User user) {
/* 545 */     List userSiteList = getEnabledUserSiteList(user);
/* 546 */     List userDepartmentList = getUserDepartmentListByUser(user);
/* 547 */     List<Site> retVal = new ArrayList();
/* 548 */     for (Iterator<UserSite> iterator = userSiteList.iterator(); iterator.hasNext(); ) {
/* 549 */       UserSite userSite = iterator.next();
/* 550 */       Site site = userSite.getSite();
/* 551 */       if (site.getEnabled().equals(EnabledDisabled.ENABLED)) {
/* 552 */         retVal.add(site);
/* 553 */         site.setDepartments(new ArrayList());
/*     */       } 
/*     */     } 
/* 556 */     for (Iterator<UserDepartment> iter = userDepartmentList.iterator(); iter.hasNext(); ) {
/* 557 */       UserDepartment ud = iter.next();
/* 558 */       Department d = ud.getDepartment();
/* 559 */       if (d.getEnabled().equals(EnabledDisabled.ENABLED)) {
/* 560 */         List<Department> dList = d.getSite().getDepartments();
/* 561 */         if (dList != null) {
/* 562 */           dList.add(d);
/*     */         }
/*     */       } 
/*     */     } 
/* 566 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledUserSiteList(User user) {
/* 575 */     List userSiteList = getUserSiteListByUser(user);
/* 576 */     List<UserSite> retVal = new ArrayList();
/* 577 */     for (Iterator<UserSite> iter = userSiteList.iterator(); iter.hasNext(); ) {
/* 578 */       UserSite userSite = iter.next();
/* 579 */       Site site = userSite.getSite();
/* 580 */       if (site.getEnabled().equals(EnabledDisabled.ENABLED)) {
/* 581 */         retVal.add(userSite);
/*     */       }
/*     */     } 
/* 584 */     return userSiteList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledUserSiteListWithDepartmentsAndExpenseCategory(User user) {
/* 592 */     List userSiteList = getUserSiteListByUser(user);
/* 593 */     List<UserSite> retVal = new ArrayList();
/* 594 */     for (Iterator<UserSite> iter = userSiteList.iterator(); iter.hasNext(); ) {
/* 595 */       UserSite userSite = iter.next();
/* 596 */       Site site = userSite.getSite();
/* 597 */       if (site.getEnabled().equals(EnabledDisabled.ENABLED)) {
/* 598 */         retVal.add(userSite);
/* 599 */         userSite.setEnabledUserDepartmentList(getEnabledUserDepartmentList(user, site));
/* 600 */         site.setEnabledTravelExpenseCategory(this.expenseCategoryManager.getEnabledTravelExpenseCategoryOfSite(site.getId().intValue()));
/* 601 */         site.setEnabledNotTravelExpenseCategoryList(this.expenseCategoryManager.getEnabledNotTravelExpenseCategoryListOfSite(site.getId().intValue()));
/*     */       } 
/*     */     } 
/* 604 */     return userSiteList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledUserDepartmentList(User user, Site site) {
/* 614 */     List udList = getUserDepartmentListByUserAndSite(user, site);
/* 615 */     List<UserDepartment> retVal = new ArrayList();
/* 616 */     for (Iterator<UserDepartment> iter = udList.iterator(); iter.hasNext(); ) {
/* 617 */       UserDepartment ud = iter.next();
/* 618 */       Department d = ud.getDepartment();
/* 619 */       if (d.getEnabled().equals(EnabledDisabled.ENABLED)) {
/* 620 */         retVal.add(ud);
/*     */       }
/*     */     } 
/* 623 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getGrantedDepartmentListOfSite(User user, Site site, Function function) {
/* 633 */     if (hasSitePower(site, user, function)) {
/* 634 */       this.departmentManager.fillDepartment(site, true);
/* 635 */       setGranted(site);
/* 636 */       return site.getDepartments();
/*     */     } 
/* 638 */     List departmentList = this.dao.getGrantedDeparmentListOfSite(user, site, function);
/* 639 */     site.setDepartments(departmentList);
/* 640 */     buildDepartmentList(site);
/* 641 */     return site.getDepartments();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledUserListOfDepartment(Department department) {
/* 651 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 652 */     conds.put(UserQueryCondition.DEPARTMENT_ID_EQ, department.getId());
/* 653 */     conds.put(UserQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 654 */     return this.dao.getUserList(conds, 0, -1, UserQueryOrder.NAME, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasUserPower(User user, User operator, Function function) {
/* 661 */     return this.dao.hasUserPower(user, operator, function);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledUserList(Function f, Site site) {
/* 666 */     return this.dao.getEnabledUserList(f, site);
/*     */   }
/*     */   
/*     */   public List getEnabledUserList(Function f, Department dep) {
/* 670 */     return this.dao.getEnabledUserList(f, dep);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledSiteDepartmentTreeOfUser(User user) {
/* 675 */     List siteList = getEnabledSiteDepartmentListOfUser(user);
/* 676 */     for (Iterator<Site> iter = siteList.iterator(); iter.hasNext(); ) {
/* 677 */       Site site = iter.next();
/* 678 */       buildDepartmentList(site);
/*     */     } 
/* 680 */     return siteList;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledSiteListOfUser(User currentUser) {
/* 685 */     List userSiteList = getEnabledUserSiteList(currentUser);
/* 686 */     List<Site> retVal = new ArrayList();
/* 687 */     for (Iterator<UserSite> iter = userSiteList.iterator(); iter.hasNext(); ) {
/* 688 */       UserSite userSite = iter.next();
/* 689 */       retVal.add(userSite.getSite());
/*     */     } 
/* 691 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
/* 697 */     this.userRoleDAO = userRoleDAO;
/*     */   }
/*     */ 
/*     */   
/*     */   public void fillUserRole(List userList) {
/* 702 */     this.userRoleDAO.fillUserRole(userList);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getUserRoleList(Map conditions, UserQueryOrder order, boolean isDesc) {
/* 707 */     return this.userRoleDAO.getUserRoleList(conditions, order, isDesc);
/*     */   }
/*     */   
/*     */   public boolean hasGlobalPowers(User user, Function function) {
/* 711 */     return this.dao.hasGlobalPowers(user, function);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/UserManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */