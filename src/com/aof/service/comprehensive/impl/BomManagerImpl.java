/*    */ package com.aof.service.comprehensive.impl;
/*    */ 
/*    */ import com.aof.dao.comprehensive.BomDAO;
/*    */ import com.aof.model.comprehensive.Bom;
/*    */ import com.aof.model.comprehensive.query.BomQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.comprehensive.BomManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class BomManagerImpl
/*    */   extends BaseManager implements BomManager {
/*    */   private BomDAO dao;
/*    */   
/*    */   public void setDao(BomDAO dao) {
/* 16 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   
/*    */   public Bom getBom(Integer id) {
/* 21 */     return this.dao.getBom(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBomListCount(Map conditions) {
/* 26 */     return this.dao.getBomListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getBomList(Map conditions, int pageNo, int pageSize, BomQueryOrder order, boolean descend) {
/* 32 */     return this.dao.getBomList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public Bom insertBom(Bom batchAdjustment) {
/* 37 */     return this.dao.insertBom(batchAdjustment);
/*    */   }
/*    */ 
/*    */   
/*    */   public Bom updateBom(Bom batchAdjustment) {
/* 42 */     return this.dao.updateBom(batchAdjustment);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/comprehensive/impl/BomManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */