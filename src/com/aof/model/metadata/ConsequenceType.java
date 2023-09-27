/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConsequenceType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final ConsequenceType APPROVER = new ConsequenceType(1, "Approver", MetadataType.CONSEQUENCE_TYPE);
/* 13 */   public static final ConsequenceType PURCHASER = new ConsequenceType(2, "Purchaser", MetadataType.CONSEQUENCE_TYPE);
/* 14 */   public static final ConsequenceType ACCEPTABLE = new ConsequenceType(3, "Accpetable", MetadataType.CONSEQUENCE_TYPE);
/* 15 */   public static final ConsequenceType NOTIFIER = new ConsequenceType(4, "Notification", MetadataType.CONSEQUENCE_TYPE);
/*    */   
/*    */   static {
/* 18 */     APPROVER.prefixUrl = "Approver";
/* 19 */     PURCHASER.prefixUrl = "Purchaser";
/* 20 */     ACCEPTABLE.prefixUrl = "Acceptable";
/* 21 */     NOTIFIER.prefixUrl = "Notifier";
/*    */   }
/*    */   
/*    */   private String prefixUrl;
/*    */   
/*    */   public ConsequenceType() {}
/*    */   
/*    */   private ConsequenceType(int metadataId, String defaultLabel, MetadataType type) {
/* 29 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getPrefixUrl() {
/* 38 */     return this.prefixUrl;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/ConsequenceType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */