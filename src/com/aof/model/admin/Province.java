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
/*    */ public class Province
/*    */   extends AbstractProvince
/*    */   implements Serializable
/*    */ {
/*    */   private List enabledCityList;
/*    */   
/*    */   public Province() {}
/*    */   
/*    */   public Province(Integer id) {
/* 33 */     super(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getEnabledCityList() {
/* 40 */     return this.enabledCityList;
/*    */   }
/*    */   
/*    */   public void setEnabledCityList(List enabledCityList) {
/* 44 */     this.enabledCityList = enabledCityList;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/Province.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */