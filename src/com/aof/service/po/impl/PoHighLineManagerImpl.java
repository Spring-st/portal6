/*    */ package com.aof.service.po.impl;
/*    */ 
/*    */ import com.aof.dao.po.PoHighLineDAO;
/*    */ import com.aof.model.po.PoHighLineTwo;
/*    */ import com.aof.model.po.query.PoHighLineTwoQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.po.PoHighLineManager;
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
/*    */ public class PoHighLineManagerImpl
/*    */   extends BaseManager
/*    */   implements PoHighLineManager
/*    */ {
/*    */   private PoHighLineDAO dao;
/*    */   
/*    */   public void setDao(PoHighLineDAO dao) {
/* 29 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   
/*    */   public PoHighLineTwo getPoHighLineTwo(Integer id) {
/* 34 */     return this.dao.getPoHighLineTwo(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPoHighLineTwoListCount(Map conditions) {
/* 39 */     return this.dao.getPoHighLineTwoListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getPoHighLineTwoList(Map conditions, int pageNo, int pageSize, PoHighLineTwoQueryOrder order, boolean descend) {
/* 45 */     return this.dao.getPoHighLineTwoList(conditions, pageNo, pageSize, order, 
/* 46 */         descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public PoHighLineTwo insertPoHighLineTwo(PoHighLineTwo po) {
/* 51 */     return this.dao.insertPoHighLineTwo(po);
/*    */   }
/*    */ 
/*    */   
/*    */   public PoHighLineTwo updatePoHighLineTwo(PoHighLineTwo po) {
/* 56 */     return this.dao.updatePoHighLineTwo(po);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPoHighLineOneListCount(Map conditions) {
/* 61 */     return this.dao.getPoHighLineOneListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getPoHighLineOneList(Map conditions, int pageNo, int pageSize, PoHighLineTwoQueryOrder order, boolean descend) {
/* 67 */     return this.dao.getPoHighLineOneList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPoHighLineBelowListCount(Map conditions) {
/* 72 */     return this.dao.getPoHighLineBelowListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getPoHighLineBelowList(Map conditions, int pageNo, int pageSize, PoHighLineTwoQueryOrder order, boolean descend) {
/* 78 */     return this.dao.getPoHighLineBelowList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/impl/PoHighLineManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */