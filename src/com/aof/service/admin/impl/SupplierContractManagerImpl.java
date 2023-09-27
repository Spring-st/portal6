/*    */ package com.aof.service.admin.impl;
/*    */ 
/*    */ import com.aof.dao.admin.SupplierContractDAO;
/*    */ import com.aof.model.admin.SupplierContract;
/*    */ import com.aof.model.admin.query.SupplierContractQueryOrder;
/*    */ import com.aof.service.BaseManager;
/*    */ import com.aof.service.admin.SupplierContractManager;
/*    */ import java.io.InputStream;
/*    */ import java.util.Date;
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
/*    */ public class SupplierContractManagerImpl
/*    */   extends BaseManager
/*    */   implements SupplierContractManager
/*    */ {
/*    */   private SupplierContractDAO dao;
/*    */   
/*    */   public void setSupplierContractDAO(SupplierContractDAO dao) {
/* 33 */     this.dao = dao;
/*    */   }
/*    */   
/*    */   public SupplierContract getSupplierContract(Integer id) {
/* 37 */     return this.dao.getSupplierContract(id);
/*    */   }
/*    */   
/*    */   public SupplierContract updateSupplierContract(SupplierContract function) {
/* 41 */     return this.dao.updateSupplierContract(function);
/*    */   }
/*    */   
/*    */   public SupplierContract insertSupplierContract(SupplierContract function) {
/* 45 */     return this.dao.insertSupplierContract(function);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSupplierContractListCount(Map conditions) {
/* 50 */     return this.dao.getSupplierContractListCount(conditions);
/*    */   }
/*    */   
/*    */   public List getSupplierContractList(Map conditions, int pageNo, int pageSize, SupplierContractQueryOrder order, boolean descend) {
/* 54 */     return this.dao.getSupplierContractList(conditions, pageNo, pageSize, order, descend);
/*    */   }
/*    */   
/*    */   public SupplierContract insertSupplierContract(SupplierContract supplierContract, InputStream inputStream) {
/* 58 */     supplierContract.setUploadDate(new Date());
/* 59 */     SupplierContract sc = this.dao.insertSupplierContract(supplierContract);
/* 60 */     this.dao.saveSupplierContractContent(sc.getId(), inputStream);
/* 61 */     return sc;
/*    */   }
/*    */   
/*    */   public InputStream getSupplierContractContent(Integer id) {
/* 65 */     return this.dao.getSupplierContractContent(id);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/SupplierContractManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */