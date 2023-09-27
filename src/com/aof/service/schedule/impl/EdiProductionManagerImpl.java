/*    */ package com.aof.service.schedule.impl;
/*    */ 
/*    */ import com.aof.dao.basicDataView.EditProductionReportDAO;
/*    */ import com.aof.dao.schedule.EdiProductionDao;
/*    */ import com.aof.model.basicDataView.EditProductionReport;
/*    */ import com.aof.model.schedule.EdiProduction;
/*    */ import com.aof.model.schedule.query.EdiProductionQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.schedule.EdiProductionManager;
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
/*    */ public class EdiProductionManagerImpl
/*    */   extends BaseManager
/*    */   implements EdiProductionManager
/*    */ {
/*    */   private EdiProductionDao dao;
/*    */   private EditProductionReportDAO editProductionReportDAO;
/*    */   
/*    */   public EdiProduction save(EdiProduction entity) {
/* 35 */     return this.dao.save(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EdiProduction getEdiProduction(Integer id) {
/* 40 */     return this.dao.getEdiProduction(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void delete(EdiProduction entity) {
/* 45 */     this.dao.delete(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EdiProduction update(EdiProduction entity) {
/* 50 */     return this.dao.update(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 55 */     return this.dao.getListCount(conditions);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<EdiProduction> getList(Map conditions, int pageNum, int pageSize, EdiProductionQueryOrder order, boolean descend) {
/* 61 */     return this.dao.getList(conditions, pageNum, pageSize, order, descend);
/*    */   }
/*    */   
/*    */   public void setDao(EdiProductionDao dao) {
/* 65 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public EdiProductionDao getDao() {
/* 69 */     return this.dao;
/*    */   }
/*    */   
/*    */   public EditProductionReportDAO getEditProductionReportDAO() {
/* 73 */     return this.editProductionReportDAO;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEditProductionReportDAO(EditProductionReportDAO editProductionReportDAO) {
/* 78 */     this.editProductionReportDAO = editProductionReportDAO;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<EdiProduction> getGroupList(Map conditions, int pageNum, int pageSize, EdiProductionQueryOrder order, boolean descend) {
/* 83 */     return this.dao.getGroupList(conditions, pageNum, pageSize, order, descend);
/*    */   }
/*    */   
/*    */   public Integer getListGroupCount(Map conditions) {
/* 87 */     return this.dao.getListGroupCount(conditions);
/*    */   }
/*    */   
/*    */   public List<EditProductionReport> getListReport(Map conditions, int pageNum, int pageSize, EdiProductionQueryOrder order, boolean descend) {
/* 91 */     return this.editProductionReportDAO.list(conditions, pageNum, pageSize, order, descend);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/schedule/impl/EdiProductionManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */