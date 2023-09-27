/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.RoleDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.Role;
/*     */ import com.aof.model.admin.RoleFunction;
/*     */ import com.aof.model.admin.query.RoleQueryCondition;
/*     */ import com.aof.model.admin.query.RoleQueryOrder;
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
/*     */ public class RoleDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements RoleDAO
/*     */ {
/*  33 */   private Log log = LogFactory.getLog(RoleDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from Role r";
/*     */   private static final String SQL = "from Role r";
/*     */   
/*     */   public Role getRole(Integer id) {
/*  39 */     if (id == null) {
/*  40 */       if (this.log.isDebugEnabled()) this.log.debug("try to get Role with null id"); 
/*  41 */       return null;
/*     */     } 
/*  43 */     return (Role)getHibernateTemplate().get(Role.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Role saveRole(Role role) {
/*  50 */     getHibernateTemplate().saveOrUpdate(role);
/*  51 */     return role;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeRole(Integer id) {
/*  58 */     Role role = getRole(id);
/*  59 */     getHibernateTemplate().delete(role);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  67 */       { RoleQueryCondition.ID_EQ, "r.id = ?"
/*  68 */       }, { RoleQueryCondition.NAME_LIKE, "r.name like ?", new LikeConvert()
/*  69 */       }, { RoleQueryCondition.TYPE_EQ, "r.type = ?" }
/*     */     };
/*     */   
/*  72 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  73 */       { RoleQueryOrder.ID, "r.id"
/*  74 */       }, { RoleQueryOrder.NAME, "r.name"
/*  75 */       }, { RoleQueryOrder.TYPE, "r.type" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRoleListCount(Map conditions) {
/*  82 */     return getListCount(conditions, "select count(*) from Role r", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getRoleList(Map conditions, int pageNo, int pageSize, RoleQueryOrder order, boolean descend) {
/*  89 */     return getList(conditions, pageNo, pageSize, order, descend, "from Role r", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getFunctionListByRole(Role role) {
/*  96 */     if (role == null) return null; 
/*  97 */     Integer roleId = role.getId();
/*  98 */     if (roleId == null) return null; 
/*  99 */     return getHibernateTemplate().find("select rf.function from RoleFunction rf where rf.role.id = ? order by rf.function.name", roleId, (Type)Hibernate.INTEGER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RoleFunction saveRoleFunction(RoleFunction roleFunction) {
/* 106 */     HibernateTemplate template = getHibernateTemplate();
/* 107 */     template.save(roleFunction);
/* 108 */     return roleFunction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeRoleFunction(RoleFunction roleFunction) {
/* 115 */     getHibernateTemplate().delete(roleFunction);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/RoleDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */