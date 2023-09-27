/*    */ package com.aof.model.admin.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RoleQueryCondition
/*    */   extends Enum
/*    */ {
/* 13 */   public static final RoleQueryCondition ID_EQ = new RoleQueryCondition("id_eq");
/* 14 */   public static final RoleQueryCondition NAME_LIKE = new RoleQueryCondition("name_like");
/* 15 */   public static final RoleQueryCondition TYPE_EQ = new RoleQueryCondition("type_eq");
/*    */   
/*    */   protected RoleQueryCondition(String value) {
/* 18 */     super(value);
/*    */   }
/*    */   
/*    */   public static RoleQueryCondition getEnum(String value) {
/* 22 */     return (RoleQueryCondition)getEnum(RoleQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/RoleQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */