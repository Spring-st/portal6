/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class StorageLocationQueryOrder
/*    */   extends Enum {
/*  7 */   public static final StorageLocationQueryOrder ID = new StorageLocationQueryOrder("id");
/*  8 */   public static final StorageLocationQueryOrder ENGNAME = new StorageLocationQueryOrder("engName");
/*  9 */   public static final StorageLocationQueryOrder CHNNAME = new StorageLocationQueryOrder("chnName");
/* 10 */   public static final StorageLocationQueryOrder ENABLED = new StorageLocationQueryOrder("enabled");
/*    */   
/*    */   protected StorageLocationQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static StorageLocationQueryOrder getEnum(String value) {
/* 17 */     return (StorageLocationQueryOrder)getEnum(StorageLocationQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/StorageLocationQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */