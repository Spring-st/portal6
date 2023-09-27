/*    */ package com.aof.model.basic.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class StorageLocationQueryCondition
/*    */   extends Enum
/*    */ {
/*  8 */   public static final StorageLocationQueryCondition ID_EQ = new StorageLocationQueryCondition(
/*  9 */       "id_eq");
/* 10 */   public static final StorageLocationQueryCondition ID_BEGINWITH = new StorageLocationQueryCondition(
/* 11 */       "id_beginwith");
/* 12 */   public static final StorageLocationQueryCondition DESCRIBE_EQ = new StorageLocationQueryCondition(
/* 13 */       "describe_eq");
/* 14 */   public static final StorageLocationQueryCondition ADDRESS_EQ = new StorageLocationQueryCondition(
/* 15 */       "address_eq");
/* 16 */   public static final StorageLocationQueryCondition STOREROOM_ID_EQ = new StorageLocationQueryCondition(
/* 17 */       "storeroom_id_eq");
/* 18 */   public static final StorageLocationQueryCondition ENABLED_EQ = new StorageLocationQueryCondition(
/* 19 */       "enabled_eq");
/* 20 */   public static final StorageLocationQueryCondition SITE_EQ = new StorageLocationQueryCondition(
/* 21 */       "site_eq");
/* 22 */   public static final StorageLocationQueryCondition CODE_EQ = new StorageLocationQueryCondition(
/* 23 */       "code_eq");
/* 24 */   public static final StorageLocationQueryCondition CODEMANY_EQ = new StorageLocationQueryCondition(
/* 25 */       "codemany_eq");
/* 26 */   public static final StorageLocationQueryCondition STROMTYPE_EQ = new StorageLocationQueryCondition(
/* 27 */       "stromType_eq");
/* 28 */   public static final StorageLocationQueryCondition NOTCONTAIN_EQ = new StorageLocationQueryCondition(
/* 29 */       "notContain_eq");
/* 30 */   public static final StorageLocationQueryCondition STROMTYPE_IN = new StorageLocationQueryCondition(
/* 31 */       "stromType_in");
/* 32 */   public static final StorageLocationQueryCondition FREEAE_STATUS_EQ = new StorageLocationQueryCondition(
/* 33 */       "freeae_status_eq");
/*    */   
/*    */   protected StorageLocationQueryCondition(String value) {
/* 36 */     super(value);
/*    */   }
/*    */   
/*    */   public static StorageLocationQueryCondition getEnum(String value) {
/* 40 */     return (StorageLocationQueryCondition)getEnum(
/* 41 */         StorageLocationQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/query/StorageLocationQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */