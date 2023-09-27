/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public class UserDepartment
/*    */   extends AbstractUserDepartment
/*    */   implements Serializable
/*    */ {
/*    */   public UserDepartment() {}
/*    */   
/*    */   public UserDepartment(User user, Department department) {
/* 33 */     super(user, department);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/UserDepartment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */