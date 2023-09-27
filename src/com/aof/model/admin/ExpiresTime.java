/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import com.shcnc.hibernate.PersistentStringEnum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExpiresTime
/*    */   extends PersistentStringEnum
/*    */ {
/* 13 */   public static final ExpiresTime NEVER = new ExpiresTime("����", "00");
/* 14 */   public static final ExpiresTime TEND = new ExpiresTime("ʮ��", "01");
/* 15 */   public static final ExpiresTime ONEM = new ExpiresTime("һ����", "02");
/* 16 */   public static final ExpiresTime TWOM = new ExpiresTime("������", "03");
/* 17 */   public static final ExpiresTime THREEM = new ExpiresTime("�����", "04");
/* 18 */   public static final ExpiresTime HALFY = new ExpiresTime("����", "05");
/* 19 */   public static final ExpiresTime ONEY = new ExpiresTime("һ��", "06");
/*    */   
/*    */   public ExpiresTime() {}
/*    */   
/*    */   private ExpiresTime(String name, String persistentValue) {
/* 24 */     super(name, persistentValue);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/ExpiresTime.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */