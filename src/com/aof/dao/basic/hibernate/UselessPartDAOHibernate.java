/*    */ package com.aof.dao.basic.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.basic.UselessPartDAO;
/*    */ import com.aof.model.basic.UselessPart;
/*    */ import com.aof.model.basic.query.UselessPartQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class UselessPartDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements UselessPartDAO
/*    */ {
/*    */   public UselessPart getUselessPart(Integer id) {
/* 15 */     return (UselessPart)getHibernateTemplate().get(UselessPart.class, id);
/*    */   }
/*    */   
/*    */   public int getUselessPartListCount(Map conditions) {
/* 19 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getUselessPartList(Map conditions, int pageNo, int pageSize, UselessPartQueryOrder order, boolean descend) {
/* 25 */     return null;
/*    */   }
/*    */   
/*    */   public void insertUselessPart(UselessPart part) {
/* 29 */     getHibernateTemplate().save(part);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateUselessPart(UselessPart part) {
/* 34 */     getHibernateTemplate().update(part);
/*    */   }
/*    */ 
/*    */   
/*    */   public void deleteUselessPart(UselessPart part) {
/* 39 */     getHibernateTemplate().delete(part);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/UselessPartDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */