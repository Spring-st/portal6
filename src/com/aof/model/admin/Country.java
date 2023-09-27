/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public class Country
/*    */   extends AbstractCountry
/*    */   implements Serializable
/*    */ {
/*    */   private List enabledProvinceList;
/*    */   private List enabledCityList;
/*    */   
/*    */   public Country() {}
/*    */   
/*    */   public Country(Integer id) {
/* 44 */     super(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public List getEnabledProvinceList() {
/* 49 */     return this.enabledProvinceList;
/*    */   }
/*    */   
/*    */   public void setEnabledProvinceList(List enabledProvinceList) {
/* 53 */     this.enabledProvinceList = enabledProvinceList;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getEnabledCityList() {
/* 62 */     return this.enabledCityList;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setEnabledCityList(List enabledCityList) {
/* 69 */     this.enabledCityList = enabledCityList;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/Country.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */