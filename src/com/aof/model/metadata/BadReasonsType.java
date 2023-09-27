/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BadReasonsType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final BadReasonsType WHEELHUB = new BadReasonsType(1, "Wheel hub", MetadataType.BADREASONSTYPE);
/* 12 */   public static final BadReasonsType TIRE = new BadReasonsType(2, "tire", MetadataType.BADREASONSTYPE);
/* 13 */   public static final BadReasonsType BALANCEWEIGHT = new BadReasonsType(3, "Balance weight", MetadataType.BADREASONSTYPE);
/* 14 */   public static final BadReasonsType VALVE = new BadReasonsType(4, "valve", MetadataType.BADREASONSTYPE);
/* 15 */   public static final BadReasonsType PRESSURESENSOR = new BadReasonsType(5, "pressure sensor", MetadataType.BADREASONSTYPE);
/*    */ 
/*    */   
/*    */   public BadReasonsType() {}
/*    */   
/*    */   private BadReasonsType(int metadataId, String defaultLabel, MetadataType type) {
/* 21 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/BadReasonsType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */