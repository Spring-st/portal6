/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.CityDAO;
/*     */ import com.aof.dao.admin.CountryDAO;
/*     */ import com.aof.dao.admin.ProvinceDAO;
/*     */ import com.aof.model.admin.City;
/*     */ import com.aof.model.admin.Country;
/*     */ import com.aof.model.admin.Province;
/*     */ import com.aof.model.admin.query.CountryQueryOrder;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.CountryManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
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
/*     */ public class CountryManagerImpl
/*     */   extends BaseManager
/*     */   implements CountryManager
/*     */ {
/*     */   private CountryDAO dao;
/*     */   private ProvinceDAO provinceDAO;
/*     */   private CityDAO cityDAO;
/*     */   
/*     */   public void setCountryDAO(CountryDAO dao) {
/*  41 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Country getCountry(Integer id) {
/*  48 */     return this.dao.getCountry(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Country updateCountry(Country function) {
/*  55 */     return this.dao.updateCountry(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Country insertCountry(Country function) {
/*  62 */     return this.dao.insertCountry(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCountryListCount(Map conditions) {
/*  70 */     return this.dao.getCountryListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getCountryList(Map conditions, int pageNo, int pageSize, CountryQueryOrder order, boolean descend) {
/*  77 */     return this.dao.getCountryList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCityDAO(CityDAO cityDAO) {
/*  84 */     this.cityDAO = cityDAO;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProvinceDAO(ProvinceDAO provinceDAO) {
/*  91 */     this.provinceDAO = provinceDAO;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List listEnabledCountryProvinceCity() {
/*  98 */     List countryList = this.dao.listEnabledCountry();
/*  99 */     List cityList = this.cityDAO.getEnabledCityList();
/* 100 */     List provinceList = this.provinceDAO.getEnabledProvinceList();
/*     */     
/* 102 */     for (Iterator<Country> iterator = countryList.iterator(); iterator.hasNext(); ) {
/* 103 */       Country c = iterator.next();
/* 104 */       c.setEnabledProvinceList(new ArrayList());
/*     */     } 
/*     */     
/* 107 */     for (Iterator<Province> iterator2 = provinceList.iterator(); iterator2.hasNext(); ) {
/* 108 */       Province p = iterator2.next();
/* 109 */       p.setEnabledCityList(new ArrayList());
/*     */     } 
/*     */     
/* 112 */     for (Iterator<Province> iterator1 = provinceList.iterator(); iterator1.hasNext(); ) {
/* 113 */       Province p = iterator1.next();
/* 114 */       if (p.getCountry().getEnabledProvinceList() != null) {
/* 115 */         p.getCountry().getEnabledProvinceList().add(p);
/*     */       }
/*     */     } 
/* 118 */     for (Iterator<City> iter = cityList.iterator(); iter.hasNext(); ) {
/* 119 */       City c = iter.next();
/* 120 */       if (c.getProvince().getEnabledCityList() != null) {
/* 121 */         c.getProvince().getEnabledCityList().add(c);
/*     */       }
/*     */     } 
/* 124 */     return countryList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List listEnabledCountryCity() {
/* 131 */     List countryList = this.dao.listEnabledCountry();
/* 132 */     List cityList = this.cityDAO.getEnabledCityList();
/* 133 */     for (Iterator<Country> iterator = countryList.iterator(); iterator.hasNext(); ) {
/* 134 */       Country c = iterator.next();
/* 135 */       c.setEnabledCityList(new ArrayList());
/*     */     } 
/*     */     
/* 138 */     for (Iterator<City> iter = cityList.iterator(); iter.hasNext(); ) {
/* 139 */       City c = iter.next();
/* 140 */       c.getProvince().getCountry().getEnabledCityList().add(c);
/*     */     } 
/*     */     
/* 143 */     return countryList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void promoteCountry(Country country) {
/* 150 */     country.setSite(null);
/* 151 */     updateCountry(country);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledCountryList() {
/* 159 */     return this.dao.listEnabledCountry();
/*     */   }
/*     */   
/*     */   public boolean deleteCountry(Country country) {
/* 163 */     this.dao.deleteCountry(country);
/* 164 */     return true;
/*     */   }
/*     */   
/*     */   public Country getCountryByChnName(String chnName) {
/* 168 */     return this.dao.getCountryByChnName(chnName);
/*     */   }
/*     */   
/*     */   public Country getCountryByEngName(String engName) {
/* 172 */     return this.dao.getCountryByEngName(engName);
/*     */   }
/*     */   
/*     */   public Country getCountryByShortName(String shortName) {
/* 176 */     return this.dao.getCountryByShortName(shortName);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/CountryManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */