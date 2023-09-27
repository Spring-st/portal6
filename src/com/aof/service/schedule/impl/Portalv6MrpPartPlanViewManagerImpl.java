/*    */ package com.aof.service.schedule.impl;
/*    */ 
/*    */ import com.aof.dao.schedule.Portalv6MrpPartPlanViewDao;
/*    */ import com.aof.model.schedule.Portalv6MrpPartPlanView;
/*    */ import com.aof.model.schedule.query.Portalv6MrpPartPlanViewQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.schedule.Portalv6MrpPartPlanViewManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class Portalv6MrpPartPlanViewManagerImpl
/*    */   extends BaseManager
/*    */   implements Portalv6MrpPartPlanViewManager {
/*    */   public Portalv6MrpPartPlanViewDao portalv6MrpPartPlanViewDao;
/*    */   
/*    */   public Portalv6MrpPartPlanViewDao getPortalv6MrpPartPlanViewDao() {
/* 17 */     return this.portalv6MrpPartPlanViewDao;
/*    */   }
/*    */   public void setPortalv6MrpPartPlanViewDao(Portalv6MrpPartPlanViewDao portalv6MrpPartPlanViewDao) {
/* 20 */     this.portalv6MrpPartPlanViewDao = portalv6MrpPartPlanViewDao;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Portalv6MrpPartPlanView> getList(Map conditions, int pageNum, int pageSize, Portalv6MrpPartPlanViewQueryOrder order, boolean descend) {
/* 26 */     return this.portalv6MrpPartPlanViewDao.getList(conditions, pageNum, pageSize, order, descend);
/*    */   }
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 30 */     return this.portalv6MrpPartPlanViewDao.getListCount(conditions);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/schedule/impl/Portalv6MrpPartPlanViewManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */