/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CurrencyType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final CurrencyType YUAN = new CurrencyType(1, "yuan", 
/* 13 */       MetadataType.CURRENCY_TYPE);
/* 14 */   public static final CurrencyType DOLLAR = new CurrencyType(2, "dollar", 
/* 15 */       MetadataType.CURRENCY_TYPE);
/* 16 */   public static final CurrencyType EURO = new CurrencyType(3, "euro", 
/* 17 */       MetadataType.CURRENCY_TYPE);
/*    */ 
/*    */   
/*    */   public CurrencyType() {}
/*    */ 
/*    */   
/*    */   private CurrencyType(int metadataId, String defaultLabel, MetadataType type) {
/* 24 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/CurrencyType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */