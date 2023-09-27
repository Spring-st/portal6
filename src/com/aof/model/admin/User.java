/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Collections;
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
/*    */ public class User
/*    */   extends AbstractUser
/*    */   implements Serializable
/*    */ {
/*    */   private List userRoleList;
/*    */   
/*    */   public User() {}
/*    */   
/*    */   public User(Integer id) {
/* 34 */     super(id);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List getUserRoleList() {
/* 40 */     return this.userRoleList;
/*    */   }
/*    */   
/*    */   public void setUserRoleList(List userRoleList) {
/* 44 */     this.userRoleList = userRoleList;
/*    */   }
/*    */   
/*    */   public int getUserRoleListSize() {
/* 48 */     return this.userRoleList.size();
/*    */   }
/*    */   
/*    */   public UserRole getFirstUserRole() {
/* 52 */     if (this.userRoleList.isEmpty()) {
/* 53 */       return null;
/*    */     }
/* 55 */     return this.userRoleList.get(0);
/*    */   }
/*    */   
/*    */   public List getRemainUserRoles() {
/* 59 */     if (this.userRoleList.isEmpty()) {
/* 60 */       return Collections.EMPTY_LIST;
/*    */     }
/* 62 */     return this.userRoleList.subList(1, this.userRoleList.size());
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/User.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */