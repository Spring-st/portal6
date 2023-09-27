/*    */ package com.aof.dao.admin.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.admin.GlobalDAO;
/*    */ import com.aof.model.admin.GlobalParameter;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GlobalDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements GlobalDAO
/*    */ {
/*    */   public GlobalParameter getParameter() {
/* 19 */     List<GlobalParameter> ls = getHibernateTemplate().find("from GlobalParameter");
/* 20 */     if (ls.size() == 0) {
/* 21 */       GlobalParameter para = new GlobalParameter();
/* 22 */       getHibernateTemplate().save(para);
/* 23 */       return para;
/*    */     } 
/* 25 */     return ls.get(0);
/*    */   }
/*    */   
/*    */   public GlobalParameter saveUser(GlobalParameter para) {
/* 29 */     getHibernateTemplate().saveOrUpdate(para);
/* 30 */     return para;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/GlobalDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */