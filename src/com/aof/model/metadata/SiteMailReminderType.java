/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SiteMailReminderType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 12 */   public static final SiteMailReminderType APPROVER_NOT_RESPOND_PURCHASE_REQUEST = new SiteMailReminderType(1, "Approver not respond the Submitted Purchase Request", MetadataType.SITE_MAIL_REMINDER_TYPE);
/* 13 */   public static final SiteMailReminderType PURCHASER_NOT_RESPOND_PURCHASE_REQUEST = new SiteMailReminderType(2, "Purchaser not respond the Approved Purchase Request", MetadataType.SITE_MAIL_REMINDER_TYPE);
/* 14 */   public static final SiteMailReminderType APPROVER_NOT_RESPOND_PURCHASE_ORDER = new SiteMailReminderType(3, "Approver not respond the Submitted Purchase Order", MetadataType.SITE_MAIL_REMINDER_TYPE);
/* 15 */   public static final SiteMailReminderType FINANCE_NOT_RESPOND_PURCHASE_ORDER = new SiteMailReminderType(4, "Finance not respond the Approved Purchase Order", MetadataType.SITE_MAIL_REMINDER_TYPE);
/* 16 */   public static final SiteMailReminderType APPROVER_NOT_RESPOND_EXPENSE_REQUEST = new SiteMailReminderType(5, "Approver not respond the Submitted Expense Request", MetadataType.SITE_MAIL_REMINDER_TYPE);
/* 17 */   public static final SiteMailReminderType FINANCE_NOT_RESPOND_EXPENSE_REQUEST = new SiteMailReminderType(6, "Finance not respond the Approved Expense Request", MetadataType.SITE_MAIL_REMINDER_TYPE);
/* 18 */   public static final SiteMailReminderType APPROVER_NOT_RESPOND_TRAVELING_APPLICATION = new SiteMailReminderType(7, "Approver not respond the Submitted Traveling Application", MetadataType.SITE_MAIL_REMINDER_TYPE);
/* 19 */   public static final SiteMailReminderType RECEPTION_NOT_RESPOND_TRAVELING_APPLICATION = new SiteMailReminderType(8, "Reception not respond the Approved Traveling Application", MetadataType.SITE_MAIL_REMINDER_TYPE);
/*    */ 
/*    */ 
/*    */   
/*    */   public SiteMailReminderType() {}
/*    */ 
/*    */   
/*    */   private SiteMailReminderType(int metadataId, String defaultLabel, MetadataType type) {
/* 27 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/SiteMailReminderType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */