/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.DepartmentDAO;
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.query.DepartmentQueryCondition;
/*     */ import com.aof.model.admin.query.DepartmentQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.shcnc.hibernate.CompositeQuery;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.HibernateException;
/*     */ import net.sf.hibernate.Session;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.springframework.orm.hibernate.HibernateCallback;
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
/*     */ public class DepartmentDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements DepartmentDAO
/*     */ {
/*  38 */   private Log log = LogFactory.getLog(DepartmentDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from Department d";
/*     */   private static final String SQL = "from Department d";
/*     */   
/*     */   public Department getDepartment(Integer id) {
/*  44 */     if (id == null) {
/*  45 */       if (this.log.isDebugEnabled()) this.log.debug("try to get Department with null id"); 
/*  46 */       return null;
/*     */     } 
/*  48 */     return (Department)getHibernateTemplate().get(Department.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Department saveDepartment(Department department) {
/*  55 */     getHibernateTemplate().saveOrUpdate(department);
/*  56 */     return department;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeDepartment(Integer id) {
/*  63 */     Department department = getDepartment(id);
/*  64 */     getHibernateTemplate().delete(department);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  72 */       { DepartmentQueryCondition.SITE_EQ, "d.site.id = ?"
/*  73 */       }, { DepartmentQueryCondition.ENABLED_EQ, "d.enabled = ?" }
/*     */     };
/*     */   
/*  76 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  77 */       { DepartmentQueryOrder.NAME, "d.name" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDepartmentListCount(Map conditions) {
/*  84 */     return getListCount(conditions, "select count(*) from Department d", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getDepartmentList(Map conditions, int pageNo, int pageSize, DepartmentQueryOrder order, boolean descend) {
/*  91 */     return getList(conditions, pageNo, pageSize, order, descend, "from Department d", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillSiteDepartment(final List<Site> siteList, final boolean onlyEnabled) {
/*  98 */     if (siteList.isEmpty())
/*     */       return; 
/* 100 */     List departmentList = (List)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 103 */             CompositeQuery query = new CompositeQuery("from Department d", "", session);
/* 104 */             if (onlyEnabled) {
/* 105 */               DepartmentDAOHibernate.makeSimpleCondition(query, "d.enabled = ?", EnabledDisabled.ENABLED.getEnumCode());
/* 106 */               if (siteList.size() < 20) {
/* 107 */                 query.createQueryCondition("d.site.id in " + DepartmentDAOHibernate.this.getIdRange(siteList));
/*     */               }
/*     */             } 
/* 110 */             return query.list();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 115 */     for (Iterator<Site> iterator = siteList.iterator(); iterator.hasNext(); ) {
/* 116 */       Site site = iterator.next();
/* 117 */       site.setDepartments(new ArrayList());
/*     */     } 
/* 119 */     for (Iterator<Department> iter = departmentList.iterator(); iter.hasNext(); ) {
/* 120 */       Department d = iter.next();
/* 121 */       int i = siteList.indexOf(d.getSite());
/* 122 */       if (i != -1) {
/* 123 */         ((Site)siteList.get(i)).getDepartments().add(d);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String getIdRange(List siteList) {
/* 130 */     StringBuffer sb = new StringBuffer();
/* 131 */     sb.append('(');
/* 132 */     for (Iterator<Site> iter = siteList.iterator(); iter.hasNext(); ) {
/* 133 */       Site site = iter.next();
/* 134 */       sb.append(site.getId());
/* 135 */       sb.append(',');
/*     */     } 
/* 137 */     sb.deleteCharAt(sb.length() - 1);
/* 138 */     sb.append(')');
/* 139 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/DepartmentDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */