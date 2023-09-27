/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.UserDepartmentDAO;
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.UserDepartment;
/*     */ import com.aof.model.admin.query.UserDepartmentQueryCondition;
/*     */ import com.aof.model.admin.query.UserDepartmentQueryOrder;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class UserDepartmentDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements UserDepartmentDAO
/*     */ {
/*  31 */   private Log log = LogFactory.getLog(UserDepartmentDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from UserDepartment ud";
/*     */   private static final String SQL = "from UserDepartment ud";
/*     */   
/*     */   public UserDepartment getUserDepartment(Integer userId, Integer departmentId) {
/*  37 */     if (userId == null) {
/*  38 */       if (this.log.isDebugEnabled())
/*  39 */         this.log.debug("try to get UserDepartment with null user id"); 
/*  40 */       return null;
/*     */     } 
/*  42 */     if (departmentId == null) {
/*  43 */       if (this.log.isDebugEnabled())
/*  44 */         this.log.debug("try to get UserDepartment with null department id"); 
/*  45 */       return null;
/*     */     } 
/*  47 */     HibernateTemplate template = getHibernateTemplate();
/*  48 */     User u = (User)template.get(User.class, userId);
/*  49 */     if (u == null)
/*  50 */       return null; 
/*  51 */     Department d = (Department)template
/*  52 */       .get(Department.class, departmentId);
/*  53 */     if (d == null)
/*  54 */       return null; 
/*  55 */     return (UserDepartment)getHibernateTemplate().get(
/*  56 */         UserDepartment.class, (Serializable)new UserDepartment(u, d));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserDepartment saveUserDepartment(UserDepartment ud) {
/*  63 */     HibernateTemplate template = getHibernateTemplate();
/*  64 */     template.save(ud);
/*  65 */     template.flush();
/*  66 */     template.evict(ud);
/*  67 */     return getUserDepartment(ud.getUser().getId(), ud.getDepartment().getId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UserDepartment updateUserDepartment(UserDepartment ud) {
/*  74 */     HibernateTemplate template = getHibernateTemplate();
/*  75 */     template.update(ud);
/*  76 */     template.flush();
/*  77 */     template.evict(ud);
/*  78 */     return getUserDepartment(ud.getUser().getId(), ud.getDepartment().getId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeUserDepartment(UserDepartment ud) {
/*  85 */     getHibernateTemplate().delete(ud);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  93 */       { UserDepartmentQueryCondition.USER_ID_EQ, "ud.user.id = ?"
/*  94 */       }, { UserDepartmentQueryCondition.SITE_ID_EQ, "ud.department.site.id = ?" }
/*     */     };
/*  96 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  97 */       { UserDepartmentQueryOrder.SITE_NAME, "ud.department.site.name"
/*  98 */       }, { UserDepartmentQueryOrder.DEPARTMENT_NAME, "ud.department.name" }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUserDepartmentListCount(Map conditions) {
/* 104 */     return getListCount(conditions, "select count(*) from UserDepartment ud", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getUserDepartmentList(Map conditions, int pageNo, int pageSize, UserDepartmentQueryOrder order, boolean descend) {
/* 111 */     return getList(conditions, pageNo, pageSize, order, descend, "from UserDepartment ud", 
/* 112 */         QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/UserDepartmentDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */