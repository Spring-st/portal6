/*    */ package com.aof.service;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Properties
/*    */ {
/*    */   public static String getPropertiesValye(String key) {
/*    */     try {
/* 12 */       InputStream path = Properties.class.getClassLoader().getResourceAsStream("com/aof/web/struts/ApplicationResources_zh.properties");
/* 13 */       java.util.Properties p = new java.util.Properties();
/* 14 */       p.load(path);
/* 15 */       Object value = p.get(key);
/* 16 */       if (value != null) {
/* 17 */         return value.toString();
/*    */       }
/* 19 */       return "";
/*    */     
/*    */     }
/* 22 */     catch (Exception e) {
/* 23 */       e.printStackTrace();
/*    */       
/* 25 */       return "";
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/Properties.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */