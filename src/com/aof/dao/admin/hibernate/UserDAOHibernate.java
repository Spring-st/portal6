/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.UserDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.dao.convert.QueryParameterConvert;
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.Function;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.UserRole;
/*     */ import com.aof.model.admin.query.UserQueryCondition;
/*     */ import com.aof.model.admin.query.UserQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.type.Type;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.springframework.orm.hibernate.HibernateTemplate;
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
/*     */ public class UserDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements UserDAO
/*     */ {
/*  42 */   private Log log = LogFactory.getLog(UserDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from User u";
/*     */   private static final String SQL = "from User u";
/*     */   
/*     */   public User getUser(Integer id) {
/*  48 */     if (id == null) {
/*  49 */       if (this.log.isDebugEnabled())
/*  50 */         this.log.debug("try to get User with null id"); 
/*  51 */       return null;
/*     */     } 
/*  53 */     return (User)getHibernateTemplate().get(User.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public User saveUser(User user) {
/*  60 */     getHibernateTemplate().saveOrUpdate(user);
/*  61 */     return user;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeUser(Integer id) {
/*  68 */     User user = getUser(id);
/*  69 */     getHibernateTemplate().delete(user);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  77 */       { UserQueryCondition.LOGINNAME_EQ, "u.loginName = ?"
/*  78 */       }, { UserQueryCondition.LOGINNAME_LIKE, "u.loginName like ?", new LikeConvert()
/*  79 */       }, { UserQueryCondition.NAME_LIKE, "u.name like ?", new LikeConvert()
/*  80 */       }, { UserQueryCondition.ROLE_ID_EQ, "exists (select ur.user.id from UserRole ur where ur.user.id = u.id and ur.role.id = ?)"
/*  81 */       }, { UserQueryCondition.SITE_ID_EQ, "exists (select us.user.id from UserSite us where us.user.id = u.id and us.site.id = ?)"
/*  82 */       }, { UserQueryCondition.PRIMARY_OR_SITE_ID_EQ, "(u.primarySite.id = ? or exists (select us.user.id from UserSite us where us.user.id = u.id and us.site.id = ?))", 
/*  83 */         new QueryParameterConvert()
/*     */         {
/*     */           public Object convert(Object src) {
/*  86 */             return new Object[] { src, src
/*     */               };
/*     */           }
/*     */         }
/*  90 */       }, { UserQueryCondition.DEPARTMENT_ID_EQ, "exists (select ud.user.id from UserDepartment ud where ud.user.id = u.id and ud.department.id = ?)"
/*  91 */       }, { UserQueryCondition.ENABLED_EQ, "u.enabled = ?"
/*  92 */       }, { UserQueryCondition.FUNCTION_ID_EQ, 
/*  93 */         "exists (select ur.user.id from UserRole ur, RoleFunction rf where ur.role.id = rf.role.id and ur.user.id = u.id and rf.function.id = ?)" }
/*     */     };
/*     */   
/*  96 */   private static final Object[][] QUERY_ORDERS = new Object[][] { { UserQueryOrder.LOGINNAME, "u.loginName" }, { UserQueryOrder.NAME, "u.name"
/*  97 */       }, { UserQueryOrder.EMAIL, "u.email" }, { UserQueryOrder.TELEPHONE, "u.telephone" } };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUserListCount(Map conditions) {
/* 103 */     return getListCount(conditions, "select count(*) from User u", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getUserList(Map conditions, int pageNo, int pageSize, UserQueryOrder order, boolean descend) {
/* 110 */     return getList(conditions, pageNo, pageSize, order, descend, "from User u", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getUserRoleListByUser(User user) {
/* 117 */     if (user == null)
/* 118 */       return null; 
/* 119 */     Integer userId = user.getId();
/* 120 */     if (userId == null)
/* 121 */       return null; 
/* 122 */     return getHibernateTemplate().find("from UserRole ur where ur.user.id = ? order by ur.role.name", userId, (Type)Hibernate.INTEGER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getUserRoleListByUserAndFunction(User user, Function function) {
/* 129 */     if (user == null || function == null)
/* 130 */       return null; 
/* 131 */     Integer userId = user.getId();
/* 132 */     if (userId == null)
/* 133 */       return null; 
/* 134 */     Integer functionId = function.getId();
/* 135 */     if (functionId == null)
/* 136 */       return null; 
/* 137 */     return getHibernateTemplate().find(
/* 138 */         "from UserRole ur where ur.user.id = ? and ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?)", 
/* 139 */         new Object[] { userId, functionId }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserRole getUserRole(Integer id) {
/* 146 */     if (id == null) {
/* 147 */       if (this.log.isDebugEnabled())
/* 148 */         this.log.debug("try to get UserRole with null id"); 
/* 149 */       return null;
/*     */     } 
/* 151 */     return (UserRole)getHibernateTemplate().get(UserRole.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserRole saveUserRole(UserRole ur) {
/* 158 */     HibernateTemplate template = getHibernateTemplate();
/* 159 */     template.saveOrUpdate(ur);
/* 160 */     template.flush();
/* 161 */     template.evict(ur);
/* 162 */     return getUserRole(ur.getId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeUserRole(UserRole ur) {
/* 169 */     getHibernateTemplate().delete(ur);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getGrantedSiteList(User user, Function function) {
/* 177 */     String sql = "select distinct ur.grantedSite from UserRole ur  where (ur.user.id = ?) and (ur.grantedSite.enabled=?) and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and (ur.grantedSite is not null)  and (ur.grantedDepartment is null) order by ur.grantedSite.id ";
/*     */ 
/*     */ 
/*     */     
/* 181 */     return getHibernateTemplate().find("select distinct ur.grantedSite from UserRole ur  where (ur.user.id = ?) and (ur.grantedSite.enabled=?) and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and (ur.grantedSite is not null)  and (ur.grantedDepartment is null) order by ur.grantedSite.id ", new Object[] { user.getId(), EnabledDisabled.ENABLED.getEnumCode(), function.getId()
/* 182 */         }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSitePower(Site site, User user, Function function) {
/* 191 */     String sql = "select ur.grantedSite.id from UserRole ur  where (ur.user.id = ?)  and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and (ur.grantedSite is null or ur.grantedSite.id=?)  and (ur.grantedDepartment is null) ";
/*     */ 
/*     */ 
/*     */     
/* 195 */     return getHibernateTemplate().iterate(sql, new Object[] { user.getId(), function.getId(), site.getId()
/* 196 */         }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER }).hasNext();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getGrantedSiteDeparmentList(User user, Function function) {
/* 204 */     String sql = "select distinct ur.grantedDepartment from UserRole ur  where (ur.user.id = ?) and (ur.grantedSite.enabled=?) and (ur.grantedDepartment.enabled=?) and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and (ur.grantedSite is not null)  and (ur.grantedDepartment is not null)";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 209 */     List departList = getHibernateTemplate().find("select distinct ur.grantedDepartment from UserRole ur  where (ur.user.id = ?) and (ur.grantedSite.enabled=?) and (ur.grantedDepartment.enabled=?) and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and (ur.grantedSite is not null)  and (ur.grantedDepartment is not null)", 
/* 210 */         new Object[] { user.getId(), EnabledDisabled.ENABLED.getEnumCode(), EnabledDisabled.ENABLED.getEnumCode(), function.getId()
/* 211 */         }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER });
/* 212 */     List<Site> siteList = new ArrayList();
/*     */     
/* 214 */     for (Iterator<Department> iter = departList.iterator(); iter.hasNext(); ) {
/* 215 */       Department d = iter.next();
/* 216 */       if (!siteList.contains(d.getSite())) {
/* 217 */         d.getSite().setDepartments(new ArrayList());
/* 218 */         siteList.add(d.getSite());
/* 219 */         d.setGranted(true);
/*     */       } 
/* 221 */       d.getSite().getDepartments().add(d);
/*     */     } 
/* 223 */     return siteList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasDepartmentPower(Department department, User user, Function function) {
/* 230 */     String sql = "select ur.grantedDepartment.id from UserRole ur  where (ur.user.id = ?)  and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and ( (ur.grantedSite is null)  or (ur.grantedSite.id=? and (ur.grantedDepartment is null or ur.grantedDepartment.id=?) ) )";
/*     */ 
/*     */ 
/*     */     
/* 234 */     return getHibernateTemplate().iterate("select ur.grantedDepartment.id from UserRole ur  where (ur.user.id = ?)  and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and ( (ur.grantedSite is null)  or (ur.grantedSite.id=? and (ur.grantedDepartment is null or ur.grantedDepartment.id=?) ) )", new Object[] { user.getId(), function.getId(), department.getSite().getId(), department.getId()
/* 235 */         }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER }).hasNext();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasGlobalPower(User user, Function function) {
/* 242 */     String sql = "select ur.grantedSite.id from UserRole ur  where (ur.user.id = ?)  and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and (ur.grantedSite is null )  and (ur.grantedDepartment is null) ";
/*     */ 
/*     */ 
/*     */     
/* 246 */     return getHibernateTemplate().iterate(sql, new Object[] { user.getId(), function.getId() }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER
/* 247 */         }).hasNext();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getGrantedMenuList(User user) {
/* 254 */     return getHibernateTemplate()
/* 255 */       .find(
/* 256 */         "from Menu m where m.function.id in (select rf.function.id from RoleFunction rf where rf.role.id in (select ur.role.id from UserRole ur where ur.user.id = ?))", 
/* 257 */         user.getId(), (Type)Hibernate.INTEGER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSiteOfGrantedSiteDeparmentList(User user, Function function) {
/* 264 */     String sql = "select distinct ur.grantedSite from UserRole ur  join ur.grantedSite left join ur.grantedDepartment where (ur.user.id = ?) and (ur.grantedSite.enabled=?)  and (ur.grantedDepartment is null or ur.grantedDepartment.enabled=?) and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and (ur.grantedSite is not null) order by ur.grantedSite.id";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 269 */     return getHibernateTemplate().find("select distinct ur.grantedSite from UserRole ur  join ur.grantedSite left join ur.grantedDepartment where (ur.user.id = ?) and (ur.grantedSite.enabled=?)  and (ur.grantedDepartment is null or ur.grantedDepartment.enabled=?) and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and (ur.grantedSite is not null) order by ur.grantedSite.id", 
/* 270 */         new Object[] { user.getId(), EnabledDisabled.ENABLED.getEnumCode(), EnabledDisabled.ENABLED.getEnumCode(), function.getId()
/* 271 */         }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getGrantedDeparmentListOfSite(User user, Site site, Function function) {
/* 278 */     String sql = "select distinct ur.grantedDepartment from UserRole ur  where (ur.user.id = ?) and (ur.grantedSite.enabled=?) and (ur.grantedDepartment.enabled=?) and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and (ur.grantedSite.id=?)  and (ur.grantedSite is not null)  and (ur.grantedDepartment is not null) order by ur.grantedDepartment.id";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 283 */     return getHibernateTemplate().find("select distinct ur.grantedDepartment from UserRole ur  where (ur.user.id = ?) and (ur.grantedSite.enabled=?) and (ur.grantedDepartment.enabled=?) and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and (ur.grantedSite.id=?)  and (ur.grantedSite is not null)  and (ur.grantedDepartment is not null) order by ur.grantedDepartment.id", 
/* 284 */         new Object[] { user.getId(), EnabledDisabled.ENABLED.getEnumCode(), EnabledDisabled.ENABLED.getEnumCode(), function.getId(), site.getId()
/* 285 */         }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasUserPower(User user, User operator, Function function) {
/* 294 */     if (hasGlobalPower(operator, function)) {
/* 295 */       return true;
/*     */     }
/*     */     
/* 298 */     String sql = "select ur.grantedSite.id from UserRole ur  where (ur.user.id = ?)  and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and (ur.grantedSite.id in (select us.site.id from UserSite us where us.user.id=?)) and ur.grantedDepartment is null";
/*     */ 
/*     */ 
/*     */     
/* 302 */     if (getHibernateTemplate().iterate(sql, new Object[] { operator.getId(), function.getId(), user.getId()
/* 303 */         }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER }).hasNext()) {
/* 304 */       return true;
/*     */     }
/* 306 */     sql = "select ur.grantedDepartment.id from UserRole ur  where (ur.user.id = ?)  and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and (ur.grantedDepartment.id in (select ud.department.id from UserDepartment ud where ud.user.id=?))";
/*     */ 
/*     */ 
/*     */     
/* 310 */     if (getHibernateTemplate().iterate(sql, new Object[] { operator.getId(), function.getId(), user.getId()
/* 311 */         }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER }).hasNext()) {
/* 312 */       return true;
/*     */     }
/* 314 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledUserList(Function f, Site site) {
/* 319 */     String sql = "select distinct ur.user from UserRole ur where  (ur.grantedSite is null or ur.grantedSite.id=?)  and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and (ur.grantedDepartment is null)  and ur.user.enabled=? order by ur.user.name";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 325 */     return getHibernateTemplate().find("select distinct ur.user from UserRole ur where  (ur.grantedSite is null or ur.grantedSite.id=?)  and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and (ur.grantedDepartment is null)  and ur.user.enabled=? order by ur.user.name", 
/* 326 */         new Object[] { site.getId(), f.getId(), EnabledDisabled.ENABLED.getEnumCode()
/* 327 */         }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER });
/*     */   }
/*     */ 
/*     */   
/*     */   public List getEnabledUserList(Function f, Department dep) {
/* 332 */     String sql = "select distinct ur.user from UserRole ur where  (ur.grantedSite is null or ur.grantedDepartment is null or ur.grantedDepartment.id=? )  and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and ur.user.enabled=? order by ur.user.name";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 337 */     return getHibernateTemplate().find("select distinct ur.user from UserRole ur where  (ur.grantedSite is null or ur.grantedDepartment is null or ur.grantedDepartment.id=? )  and (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and ur.user.enabled=? order by ur.user.name", 
/* 338 */         new Object[] { dep.getId(), f.getId(), EnabledDisabled.ENABLED.getEnumCode()
/* 339 */         }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledUserList(Function function) {
/* 345 */     String sql = "select distinct ur.user from UserRole ur  where  (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and (ur.grantedSite is null)  and (ur.grantedDepartment is null) and ur.user.enabled=? order by ur.user.id ";
/*     */ 
/*     */ 
/*     */     
/* 349 */     return getHibernateTemplate().find("select distinct ur.user from UserRole ur  where  (ur.role in (select rf.role from RoleFunction rf where rf.function.id = ?))  and (ur.grantedSite is null)  and (ur.grantedDepartment is null) and ur.user.enabled=? order by ur.user.id ", new Object[] { function.getId(), EnabledDisabled.ENABLED.getEnumCode() }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER });
/*     */   }
/*     */   
/*     */   public boolean hasGlobalPowers(User user, Function function) {
/* 353 */     String sql = "from RoleFunction rfc where " + function.getId() + " in(" + 
/* 354 */       "select rf.function.id from RoleFunction rf where rf.role.id in(select ur.role.id from UserRole ur where ur.user.id = " + user.getId() + ")) ";
/* 355 */     List list = getHibernateTemplate().find(sql);
/* 356 */     if (list == null || list.isEmpty()) {
/* 357 */       return false;
/*     */     }
/*     */     
/* 360 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/UserDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */