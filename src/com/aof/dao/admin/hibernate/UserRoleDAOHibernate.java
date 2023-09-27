/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.UserRoleDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.dao.convert.QueryParameterConvert;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.UserRole;
/*     */ import com.aof.model.admin.query.UserQueryCondition;
/*     */ import com.aof.model.admin.query.UserQueryOrder;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public class UserRoleDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements UserRoleDAO
/*     */ {
/*     */   private static final String SQL = "select u,ur from UserRole ur right join ur.user u";
/*     */   
/*  39 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  40 */       { UserQueryCondition.LOGINNAME_LIKE, "u.loginName like ?", new LikeConvert()
/*  41 */       }, { UserQueryCondition.NAME_LIKE, "u.name like ?", new LikeConvert()
/*  42 */       }, { UserQueryCondition.ROLE_ID_EQ, "exists (select ur2.user.id from UserRole ur2 where ur2.user.id = u.id and ur2.role.id = ?)"
/*  43 */       }, { UserQueryCondition.PRIMARY_OR_SITE_ID_EQ, 
/*  44 */         "(u.primarySite.id = ? or exists (select us.user.id from UserSite us where us.user.id = u.id and us.site.id = ?))", 
/*     */         
/*  46 */         new QueryParameterConvert()
/*     */         {
/*     */           public Object convert(Object src) {
/*  49 */             return new Object[] { src, src
/*     */               };
/*     */           }
/*     */         }
/*  53 */       }, { UserQueryCondition.DEPARTMENT_ID_EQ, 
/*  54 */         "exists (select ud.user.id from UserDepartment ud where ud.user.id = u.id and ud.department.id = ?)"
/*  55 */       }, { UserQueryCondition.ENABLED_EQ, "u.enabled = ?" }
/*     */     };
/*     */   
/*  58 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  59 */       { UserQueryOrder.NAME, "u.name" }
/*     */     };
/*     */ 
/*     */   
/*     */   public List getUserRoleList(Map conditions, UserQueryOrder order, boolean descend) {
/*  64 */     List list = getList(conditions, 0, -1, order, descend, "select u,ur from UserRole ur right join ur.user u", QUERY_CONDITIONS, QUERY_ORDERS);
/*  65 */     Set<User> result = new HashSet();
/*  66 */     for (Iterator<Object[]> iter = list.iterator(); iter.hasNext(); ) {
/*  67 */       Object[] row = iter.next();
/*  68 */       User user = (User)row[0];
/*  69 */       UserRole userRole = (UserRole)row[1];
/*  70 */       if (user.getUserRoleList() == null) user.setUserRoleList(new ArrayList()); 
/*  71 */       if (userRole != null)
/*  72 */         user.getUserRoleList().add(userRole); 
/*  73 */       result.add(user);
/*     */     } 
/*  75 */     if (descend) {
/*  76 */       return BeanHelper.sortToList(result, "name desc");
/*     */     }
/*  78 */     return BeanHelper.sortToList(result, "name asc");
/*     */   }
/*     */ 
/*     */   
/*     */   public void fillUserRole(List userList) {
/*  83 */     if (userList.isEmpty())
/*  84 */       return;  String sql = "select ur from UserRole ur where ur.user.id in(" + userIdList(userList) + ")";
/*  85 */     List userRoleList = getHibernateTemplate().find(sql);
/*  86 */     Map<Object, Object> urMap = new HashMap<Object, Object>();
/*  87 */     for (Iterator<UserRole> iterator = userRoleList.iterator(); iterator.hasNext(); ) {
/*  88 */       UserRole ur = iterator.next();
/*  89 */       if (urMap.get(ur.getUser()) == null)
/*     */       {
/*  91 */         urMap.put(ur.getUser(), new ArrayList());
/*     */       }
/*  93 */       List<UserRole> urList = (List)urMap.get(ur.getUser());
/*  94 */       urList.add(ur);
/*     */     } 
/*  96 */     for (Iterator<User> iter = userList.iterator(); iter.hasNext(); ) {
/*  97 */       User user = iter.next();
/*  98 */       List urList = (List)urMap.get(user);
/*  99 */       if (urList == null) { user.setUserRoleList(Collections.EMPTY_LIST); continue; }
/* 100 */        user.setUserRoleList(urList);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String userIdList(List userList) {
/* 106 */     StringBuffer sb = new StringBuffer();
/* 107 */     for (Iterator<User> iter = userList.iterator(); iter.hasNext(); ) {
/* 108 */       User user = iter.next();
/* 109 */       sb.append(user.getId());
/* 110 */       sb.append(',');
/*     */     } 
/* 112 */     sb.deleteCharAt(sb.length() - 1);
/* 113 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/UserRoleDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */