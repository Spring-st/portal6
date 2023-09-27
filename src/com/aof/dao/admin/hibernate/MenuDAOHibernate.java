/*    */ package com.aof.dao.admin.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.admin.MenuDAO;
/*    */ import com.aof.model.admin.Menu;
/*    */ import com.aof.model.admin.query.MenuQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MenuDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements MenuDAO
/*    */ {
/* 27 */   private Log log = LogFactory.getLog(MenuDAOHibernate.class);
/*    */   
/*    */   private static final String SQL_COUNT = "select count(*) from Menu m";
/*    */   private static final String SQL = "from Menu m";
/*    */   
/*    */   public Menu getMenu(Integer id) {
/* 33 */     if (id == null) {
/* 34 */       if (this.log.isDebugEnabled()) this.log.debug("try to get Menu with null id"); 
/* 35 */       return null;
/*    */     } 
/* 37 */     return (Menu)getHibernateTemplate().get(Menu.class, id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Menu saveMenu(Menu menu) {
/* 44 */     getHibernateTemplate().saveOrUpdate(menu);
/* 45 */     return menu;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void removeMenu(Integer id) {
/* 52 */     Menu menu = getMenu(id);
/* 53 */     getHibernateTemplate().delete(menu);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   private static final Object[][] QUERY_CONDITIONS = new Object[0][];
/*    */ 
/*    */   
/* 63 */   private static final Object[][] QUERY_ORDERS = new Object[0][];
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMenuListCount(Map conditions) {
/* 70 */     return getListCount(conditions, "select count(*) from Menu m", QUERY_CONDITIONS);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getMenuList(Map conditions, int pageNo, int pageSize, MenuQueryOrder order, boolean descend) {
/* 77 */     return getList(conditions, pageNo, pageSize, order, descend, "from Menu m", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/MenuDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */