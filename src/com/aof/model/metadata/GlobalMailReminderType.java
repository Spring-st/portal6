/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GlobalMailReminderType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final GlobalMailReminderType SUPPLIER_MAINTAINER_NOT_RESPOND = new GlobalMailReminderType(1, "Site supplier maintainer not respond the supplier promote call", MetadataType.GLOBAL_MAIL_REMINDER_TYPE);
/* 13 */   public static final GlobalMailReminderType HOTEL_MAINTAINER_NOT_RESPOND = new GlobalMailReminderType(2, "Site Hotel maintainer not respond the hotel promote call", MetadataType.GLOBAL_MAIL_REMINDER_TYPE);
/*    */ 
/*    */   
/*    */   public GlobalMailReminderType() {}
/*    */ 
/*    */   
/*    */   private GlobalMailReminderType(int metadataId, String defaultLabel, MetadataType type) {
/* 20 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/GlobalMailReminderType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */