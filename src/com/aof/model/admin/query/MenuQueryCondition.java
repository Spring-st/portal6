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
/*    */ public class MenuQueryCondition
/*    */   extends Enum
/*    */ {
/*    */   protected MenuQueryCondition(String value) {
/* 15 */     super(value);
/*    */   }
/*    */   
/*    */   public static MenuQueryCondition getEnum(String value) {
/* 19 */     return (MenuQueryCondition)getEnum(MenuQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/MenuQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */