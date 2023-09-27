/*    */ package com.aof.dao.schedule.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.schedule.Portalv6MrpPartPlanViewDao;
/*    */ import com.aof.model.schedule.Portalv6MrpPartPlanView;
/*    */ import com.aof.model.schedule.query.Portalv6MrpPartPlanViewQueryCondition;
/*    */ import com.aof.model.schedule.query.Portalv6MrpPartPlanViewQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class Portalv6MrpPartPlanViewDaoImpl
/*    */   extends BaseDAOHibernate
/*    */   implements Portalv6MrpPartPlanViewDao {
/*    */   private static final String SQL_COUNT = "select count(*) from Portalv6MrpPartPlanView entity";
/*    */   private static final String SQL = "from Portalv6MrpPartPlanView entity";
/* 16 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 17 */       { Portalv6MrpPartPlanViewQueryCondition.PARTID_EQ, "entity.partId = ?" }
/*    */     };
/* 19 */   private static final Object[][] QUERY_ORDERS = new Object[0][];
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Portalv6MrpPartPlanView> getList(Map conditions, int pageNum, int pageSize, Portalv6MrpPartPlanViewQueryOrder order, boolean descend) {
/* 26 */     return getList(conditions, pageNum, pageSize, order, descend, "from Portalv6MrpPartPlanView entity", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 31 */     return Integer.valueOf(getListCount(conditions, "select count(*) from Portalv6MrpPartPlanView entity", QUERY_CONDITIONS));
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/hibernate/Portalv6MrpPartPlanViewDaoImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */