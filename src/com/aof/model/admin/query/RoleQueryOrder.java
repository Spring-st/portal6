/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RoleQueryOrder
/*    */   extends Enum
/*    */ {
/* 14 */   public static final RoleQueryOrder ID = new RoleQueryOrder("id");
/* 15 */   public static final RoleQueryOrder NAME = new RoleQueryOrder("name");
/* 16 */   public static final RoleQueryOrder TYPE = new RoleQueryOrder("type");
/*    */ 
/*    */   
/*    */   protected RoleQueryOrder(String value) {
/* 20 */     super(value);
/*    */   }
/*    */   
/*    */   public static RoleQueryOrder getEnum(String value) {
/* 24 */     return (RoleQueryOrder)getEnum(RoleQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/RoleQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */