/*    */ package com.aof.service.admin.impl;
/*    */ 
/*    */ import com.aof.dao.admin.PurchaseTypeDAO;
/*    */ import com.aof.model.admin.PurchaseType;
/*    */ import com.aof.model.admin.Site;
/*    */ import com.aof.service.admin.PurchaseTypeManager;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PurchaseTypeManagerImpl
/*    */   implements PurchaseTypeManager
/*    */ {
/*    */   private PurchaseTypeDAO dao;
/*    */   
/*    */   public void setPurchaseTypeDAO(PurchaseTypeDAO dao) {
/* 20 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public PurchaseType getPurchaseType(String code) {
/* 24 */     return this.dao.getPurchaseType(code);
/*    */   }
/*    */   
/*    */   public List getEnabledPurchaseTypeList(Site site) {
/* 28 */     return this.dao.getEnabledPurchaseTypeList(site);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/PurchaseTypeManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */