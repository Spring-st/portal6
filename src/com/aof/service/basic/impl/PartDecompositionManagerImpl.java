/*    */ package com.aof.service.basic.impl;
/*    */ 
/*    */ import com.aof.dao.basic.PartDecompositionDAO;
/*    */ import com.aof.model.basic.PartDecomposition;
/*    */ import com.aof.model.basic.query.PartDecompositionQueryOrder;
/*    */ import com.aof.model.po.Box;
/*    */ import com.aof.service.basic.PartDecompositionManager;
/*    */ import java.math.BigDecimal;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class PartDecompositionManagerImpl
/*    */   implements PartDecompositionManager
/*    */ {
/*    */   private PartDecompositionDAO partDecompositionDAO;
/*    */   
/*    */   public PartDecompositionDAO getPartDecompositionDAO() {
/* 19 */     return this.partDecompositionDAO;
/*    */   }
/*    */   
/*    */   public void setPartDecompositionDAO(PartDecompositionDAO partDecompositionDAO) {
/* 23 */     this.partDecompositionDAO = partDecompositionDAO;
/*    */   }
/*    */ 
/*    */   
/*    */   public PartDecomposition getPartDecomposition(Integer id) {
/* 28 */     return this.partDecompositionDAO.getPartDecomposition(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<PartDecomposition> getPartDecompositionByBox(Box box) {
/* 33 */     return this.partDecompositionDAO.getPartDecompositionByBox(box);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPartDecompositionCount(Map conditions) {
/* 38 */     return this.partDecompositionDAO.getPartDecompositionCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getPartDecompositionList(Map conditions, int pageNo, int pageSize, PartDecompositionQueryOrder order, boolean descend) {
/* 44 */     return this.partDecompositionDAO.getPartDecompositionList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */ 
/*    */   
/*    */   public void insertPartDecomposition(PartDecomposition partDecomposition) {
/* 49 */     this.partDecompositionDAO.insertPartDecomposition(partDecomposition);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updatePartDecomposition(PartDecomposition partDecomposition) {
/* 54 */     this.partDecompositionDAO.updatePartDecomposition(partDecomposition);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void deletePartDecomposition(PartDecomposition partDecomposition) {
/* 60 */     this.partDecompositionDAO.deletePartDecomposition(partDecomposition);
/*    */   }
/*    */   
/*    */   public BigDecimal getLotPartCount(Box box, String partId) {
/* 64 */     return this.partDecompositionDAO.getLotPartCount(box, partId);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/impl/PartDecompositionManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */