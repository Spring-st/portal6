/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import com.shcnc.hibernate.PersistentStringEnum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LockAccountTimes
/*    */   extends PersistentStringEnum
/*    */ {
/* 13 */   public static final LockAccountTimes NEVER = new LockAccountTimes("����", "00");
/* 14 */   public static final LockAccountTimes THREETIMES = new LockAccountTimes("���", "01");
/* 15 */   public static final LockAccountTimes FIVETIMES = new LockAccountTimes("���", "02");
/* 16 */   public static final LockAccountTimes TENTIMES = new LockAccountTimes("ʮ��", "03");
/*    */ 
/*    */   
/*    */   public LockAccountTimes() {}
/*    */   
/*    */   private LockAccountTimes(String name, String persistentValue) {
/* 22 */     super(name, persistentValue);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/LockAccountTimes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */