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
/*    */ public class UserSite
/*    */   extends AbstractUserSite
/*    */   implements Serializable
/*    */ {
/*    */   private List enabledUserDepartmentList;
/*    */   
/*    */   public UserSite() {}
/*    */   
/*    */   public UserSite(User user, Site site) {
/* 34 */     super(user, site);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getEnabledUserDepartmentList() {
/* 45 */     return this.enabledUserDepartmentList;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setEnabledUserDepartmentList(List enabledUserDepartmentList) {
/* 52 */     this.enabledUserDepartmentList = enabledUserDepartmentList;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/UserSite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */