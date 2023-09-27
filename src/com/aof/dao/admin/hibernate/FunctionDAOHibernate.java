/*    */ package com.aof.dao.admin.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.admin.FunctionDAO;
/*    */ import com.aof.dao.convert.LikeConvert;
/*    */ import com.aof.model.admin.Function;
/*    */ import com.aof.model.admin.query.FunctionQueryCondition;
/*    */ import com.aof.model.admin.query.FunctionQueryOrder;
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
/*    */ public class FunctionDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements FunctionDAO
/*    */ {
/* 29 */   private Log log = LogFactory.getLog(FunctionDAOHibernate.class);
/*    */   
/*    */   private static final String SQL_COUNT = "select count(*) from Function f";
/*    */   private static final String SQL = "from Function f";
/*    */   
/*    */   public Function getFunction(Integer id) {
/* 35 */     if (id == null) {
/* 36 */       if (this.log.isDebugEnabled()) this.log.debug("try to get Function with null id"); 
/* 37 */       return null;
/*    */     } 
/* 39 */     return (Function)getHibernateTemplate().get(Function.class, id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Function saveFunction(Function function) {
/* 46 */     Integer id = function.getId();
/* 47 */     if (id == null) {
/* 48 */       throw new RuntimeException("cannot save a function with null id");
/*    */     }
/* 50 */     Function f = getFunction(id);
/* 51 */     if (f == null) {
/* 52 */       getHibernateTemplate().save(function);
/* 53 */       return function;
/*    */     } 
/* 55 */     f.setName(function.getName());
/* 56 */     f.setDescription(function.getDescription());
/* 57 */     getHibernateTemplate().update(f);
/* 58 */     return f;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 65 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 66 */       { FunctionQueryCondition.ID_EQ, "f.id = ?"
/* 67 */       }, { FunctionQueryCondition.NAME_LIKE, "f.name like ?", new LikeConvert() }
/*    */     };
/*    */   
/* 70 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 71 */       { FunctionQueryOrder.ID, "f.id"
/* 72 */       }, { FunctionQueryOrder.NAME, "f.name"
/* 73 */       }, { FunctionQueryOrder.DESCRIPTION, "f.description" }
/*    */     };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFunctionListCount(Map conditions) {
/* 80 */     return getListCount(conditions, "select count(*) from Function f", QUERY_CONDITIONS);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getFunctionList(Map conditions, int pageNo, int pageSize, FunctionQueryOrder order, boolean descend) {
/* 87 */     return getList(conditions, pageNo, pageSize, order, descend, "from Function f", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/FunctionDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */