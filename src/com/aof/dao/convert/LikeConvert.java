/*    */ package com.aof.dao.convert;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LikeConvert
/*    */   implements QueryParameterConvert
/*    */ {
/*    */   public Object convert(Object src) {
/* 13 */     return String.valueOf('%') + src.toString() + '%';
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/convert/LikeConvert.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */