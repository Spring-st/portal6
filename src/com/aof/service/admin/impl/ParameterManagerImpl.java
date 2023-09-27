/*    */ package com.aof.service.admin.impl;
/*    */ 
/*    */ import com.aof.dao.admin.ParameterDAO;
/*    */ import com.aof.model.admin.DataTransferParameter;
/*    */ import com.aof.model.admin.GlobalParameter;
/*    */ import com.aof.model.admin.Site;
/*    */ import com.aof.model.admin.SiteMailReminder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.admin.DataTransferManager;
/*    */ import com.aof.service.admin.ParameterManager;
/*    */ import java.util.List;
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
/*    */ public class ParameterManagerImpl
/*    */   extends BaseManager
/*    */   implements ParameterManager
/*    */ {
/*    */   private ParameterDAO dao;
/*    */   private DataTransferManager dataTransferManager;
/*    */   
/*    */   public void setDataTransferManager(DataTransferManager dataTransferManager) {
/* 37 */     this.dataTransferManager = dataTransferManager;
/*    */   }
/*    */   
/*    */   public void setParameterDAO(ParameterDAO dao) {
/* 41 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public GlobalParameter getGlobalParameter() {
/* 45 */     return this.dao.getGlobalParameter();
/*    */   }
/*    */   
/*    */   public void updateGlobalParameter(GlobalParameter globalParameter, List reminderList) {
/* 49 */     this.dao.updateGlobalParameter(globalParameter);
/* 50 */     this.dao.updateGlobalMailReminder(reminderList);
/*    */   }
/*    */   
/*    */   public List getGlobalMailReminderList() {
/* 54 */     return this.dao.getGlobalMailReminderList();
/*    */   }
/*    */ 
/*    */   
/*    */   public DataTransferParameter getDataTransferParameter(Site site) {
/* 59 */     return this.dao.getDataTransferParameter(site);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateSiteParameter(DataTransferParameter para, List<SiteMailReminder> reminderList) {
/* 64 */     boolean needReset = false;
/* 65 */     boolean needRemove = false;
/* 66 */     DataTransferParameter oldPara = this.dao.getDataTransferParameter(para.getSite());
/* 67 */     if (para.getStartTime() == null || para.getIntervalMin() == null || para.getTimePerDay() == null) needRemove = true; 
/* 68 */     if (!oldPara.schemeEqual(para)) needReset = true; 
/* 69 */     this.dao.updateDataTransferParameter(para);
/* 70 */     this.dao.updateSiteMailReminder(((SiteMailReminder)reminderList.get(0)).getSite(), reminderList);
/* 71 */     this.dataTransferManager.updateJobParameter(para);
/* 72 */     if (needRemove) {
/* 73 */       this.dataTransferManager.removeJob(para);
/*    */     }
/* 75 */     else if (needReset) {
/* 76 */       this.dataTransferManager.resetJob(para);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List getSiteMailReminderList(Site site) {
/* 82 */     return this.dao.getSiteMailReminderList(site);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/ParameterManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */