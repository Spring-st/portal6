/*    */ package com.aof.dao.business.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.business.BaseApproveRequestDAO;
/*    */ import com.aof.model.business.BaseApproveRequest;
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
/*    */ public abstract class BaseApproveRequestDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements BaseApproveRequestDAO
/*    */ {
/*    */   public void updateBaseApproveRequest(BaseApproveRequest bar) {
/* 22 */     getHibernateTemplate().update(bar);
/*    */   }
/*    */   
/*    */   public void insertBaseApproveRequest(BaseApproveRequest bar) {
/* 26 */     getHibernateTemplate().save(bar);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/business/hibernate/BaseApproveRequestDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */