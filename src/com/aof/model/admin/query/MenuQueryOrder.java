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
/*    */ public class MenuQueryOrder
/*    */   extends Enum
/*    */ {
/*    */   protected MenuQueryOrder(String value) {
/* 15 */     super(value);
/*    */   }
/*    */   
/*    */   public static MenuQueryOrder getEnum(String value) {
/* 19 */     return (MenuQueryOrder)getEnum(MenuQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/query/MenuQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */