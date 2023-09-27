/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.ProvinceDAO;
/*     */ import com.aof.model.admin.Country;
/*     */ import com.aof.model.admin.Province;
/*     */ import com.aof.model.admin.query.ProvinceQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.ProvinceManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProvinceManagerImpl
/*     */   extends BaseManager
/*     */   implements ProvinceManager
/*     */ {
/*     */   private ProvinceDAO dao;
/*     */   
/*     */   public void setProvinceDAO(ProvinceDAO dao) {
/*  34 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Province getProvince(Integer id) {
/*  41 */     return this.dao.getProvince(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Province updateProvince(Province function) {
/*  48 */     return this.dao.updateProvince(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Province insertProvince(Province function) {
/*  55 */     return this.dao.insertProvince(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProvinceListCount(Map conditions) {
/*  63 */     return this.dao.getProvinceListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getProvinceList(Map conditions, int pageNo, int pageSize, ProvinceQueryOrder order, boolean descend) {
/*  70 */     return this.dao.getProvinceList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void promoteProvince(Province province) {
/*  77 */     province.setSite(null);
/*  78 */     updateProvince(province);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledProvinceOfCountry(Country c) {
/*  86 */     return this.dao.getEnabledProvinceList(c);
/*     */   }
/*     */   
/*     */   public boolean deleteProvince(Province p) {
/*     */     try {
/*  91 */       this.dao.deleteProvince(p);
/*  92 */       return true;
/*     */     }
/*  94 */     catch (Throwable t) {
/*     */       
/*  96 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Province getProvinceByChnName(Country country, String chnName) {
/* 101 */     return this.dao.getProvinceByChnName(country, chnName);
/*     */   }
/*     */   
/*     */   public Province getProvinceByEngName(Country country, String engName) {
/* 105 */     return this.dao.getProvinceByEngName(country, engName);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/ProvinceManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */