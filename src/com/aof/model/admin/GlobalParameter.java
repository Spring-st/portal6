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
/*    */ public class GlobalParameter
/*    */   extends AbstractGlobalParameter
/*    */   implements Serializable
/*    */ {
/*    */   public GlobalParameter() {
/* 24 */     setSmtpAddress("");
/* 25 */     setSmtpUsername("");
/* 26 */     setSmtpPassword("");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GlobalParameter(Integer id) {
/* 35 */     super(id);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/GlobalParameter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */