/*    */ package com.aof.service.po.impl;
/*    */ 
/*    */ import com.aof.dao.po.ProduceBuckleMaterialDAO;
/*    */ import com.aof.model.comprehensive.ProduceBuckleMaterial;
/*    */ import com.aof.model.po.query.ProduceBuckleMaterialQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.po.ProduceBuckleMaterialManager;
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
/*    */ public class ProduceBuckleMaterialManagerImpl
/*    */   extends BaseManager
/*    */   implements ProduceBuckleMaterialManager
/*    */ {
/*    */   private ProduceBuckleMaterialDAO dao;
/*    */   
/*    */   public void setDao(ProduceBuckleMaterialDAO dao) {
/* 29 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   
/*    */   public ProduceBuckleMaterial getProduceBuckleMaterial(Integer id) {
/* 34 */     return this.dao.getProduceBuckleMaterial(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getProduceBuckleMaterialListCount(Map conditions) {
/* 39 */     return this.dao.getProduceBuckleMaterialListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getProduceBuckleMaterialList(Map conditions, int pageNo, int pageSize, ProduceBuckleMaterialQueryOrder order, boolean descend) {
/* 45 */     return this.dao.getProduceBuckleMaterialList(conditions, pageNo, pageSize, 
/* 46 */         order, descend);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ProduceBuckleMaterial insertProduceBuckleMaterial(ProduceBuckleMaterial po) {
/* 52 */     return this.dao.insertProduceBuckleMaterial(po);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ProduceBuckleMaterial updateProduceBuckleMaterial(ProduceBuckleMaterial po) {
/* 58 */     return this.dao.updateProduceBuckleMaterial(po);
/*    */   }
/*    */   
/*    */   public Integer nextGrowth() {
/* 62 */     return this.dao.getnextGrowth();
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/impl/ProduceBuckleMaterialManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */