/*    */ package com.aof.service.basic.impl;
/*    */ 
/*    */ import com.aof.dao.basic.WmsToolDAO;
/*    */ import com.aof.model.basic.BadReasons;
/*    */ import com.aof.model.basic.WmsTool;
/*    */ import com.aof.model.basic.query.WmsToolQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.basic.WmsToolManager;
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
/*    */ public class WmsToolManagerImpl
/*    */   extends BaseManager
/*    */   implements WmsToolManager
/*    */ {
/*    */   private WmsToolDAO wmsToolDAO;
/*    */   
/*    */   public void setWmsToolDAO(WmsToolDAO wmsToolDAO) {
/* 30 */     this.wmsToolDAO = wmsToolDAO;
/*    */   }
/*    */ 
/*    */   
/*    */   public WmsTool getWmsTool(Integer id) {
/* 35 */     return this.wmsToolDAO.getWmsTool(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getWmsToolListCount(Map conditions) {
/* 40 */     return this.wmsToolDAO.getWmsToolListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getWmsToolList(Map conditions, int pageNo, int pageSize, WmsToolQueryOrder order, boolean descend) {
/* 46 */     return this.wmsToolDAO.getWmsToolList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public WmsTool insertWmsTool(WmsTool partType) {
/* 51 */     return this.wmsToolDAO.insertWmsTool(partType);
/*    */   }
/*    */ 
/*    */   
/*    */   public WmsTool updateWmsTool(WmsTool partType) {
/* 56 */     return this.wmsToolDAO.updateWmsTool(partType);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getEnabledWmsToolList() {
/* 61 */     return this.wmsToolDAO.getEnabledWmsToolList();
/*    */   }
/*    */ 
/*    */   
/*    */   public void deleteWmsTool(WmsTool partType) {
/* 66 */     this.wmsToolDAO.deleteWmsTool(partType);
/*    */   }
/*    */   
/*    */   public WmsTool getWmsTool(String code) {
/* 70 */     return this.wmsToolDAO.getWmsTool(code);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<BadReasons> getBadReasons() {
/* 75 */     return this.wmsToolDAO.getBadReasons();
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/WmsToolManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */