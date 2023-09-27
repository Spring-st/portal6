/*    */ package com.aof.dao.basicDataView.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.basicDataView.EditProductionReportDAO;
/*    */ import com.aof.model.basicDataView.EditProductionReport;
/*    */ import com.aof.model.schedule.query.EdiProductionQueryOrder;
/*    */ import com.aof.model.schedule.query.JitProductionPlanQueryCondition;
/*    */ import java.util.List;
/*    */ import java.util.Map;
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
/*    */ public class EditProductionReportDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements EditProductionReportDAO
/*    */ {
/*    */   private static final String SQL = "from EditProductionReport epr";
/* 38 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 39 */       { JitProductionPlanQueryCondition.DATE_GE, " epr.task_date >= ?" }
/*    */     };
/*    */   
/* 42 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 43 */       { EdiProductionQueryOrder.TASKDATE, "epr.task_date" }
/*    */     };
/*    */ 
/*    */ 
/*    */   
/*    */   public List<EditProductionReport> list(Map conditions, int pageNum, int pageSize, EdiProductionQueryOrder order, boolean descend) {
/* 49 */     return getList(conditions, pageNum, pageSize, order, descend, "from EditProductionReport epr", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basicDataView/hibernate/EditProductionReportDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */