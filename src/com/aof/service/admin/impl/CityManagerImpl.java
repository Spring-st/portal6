/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.CityDAO;
/*     */ import com.aof.model.admin.City;
/*     */ import com.aof.model.admin.Province;
/*     */ import com.aof.model.admin.query.CityQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.CityManager;
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
/*     */ public class CityManagerImpl
/*     */   extends BaseManager
/*     */   implements CityManager
/*     */ {
/*     */   private CityDAO dao;
/*     */   
/*     */   public void setCityDAO(CityDAO dao) {
/*  34 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public City getCity(Integer id) {
/*  41 */     return this.dao.getCity(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public City updateCity(City function) {
/*  48 */     return this.dao.updateCity(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public City insertCity(City function) {
/*  55 */     return this.dao.insertCity(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCityListCount(Map conditions) {
/*  63 */     return this.dao.getCityListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getCityList(Map conditions, int pageNo, int pageSize, CityQueryOrder order, boolean descend) {
/*  70 */     return this.dao.getCityList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void promoteCity(City city) {
/*  77 */     city.setSite(null);
/*  78 */     updateCity(city);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledCityList(Province p) {
/*  86 */     return this.dao.getEnabledCityList(p);
/*     */   }
/*     */   
/*     */   public boolean deleteCity(City city) {
/*     */     try {
/*  91 */       this.dao.deleteCity(city);
/*  92 */       return true;
/*     */     }
/*  94 */     catch (Throwable t) {
/*     */       
/*  96 */       return false;
/*     */     } 
/*     */   }
/*     */   public City getCityByChnName(Province p, String chnName) {
/* 100 */     return this.dao.getCityByChnName(p, chnName);
/*     */   }
/*     */   
/*     */   public City getCityByEngName(Province p, String engName) {
/* 104 */     return this.dao.getCityByEngName(p, engName);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/CityManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */