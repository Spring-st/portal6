/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExportFileStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final ExportFileStatus CREATE = new ExportFileStatus(1, "Create", MetadataType.EXPORT_FILE_STATUS);
/* 12 */   public static final ExportFileStatus READ = new ExportFileStatus(2, "Read", MetadataType.EXPORT_FILE_STATUS);
/* 13 */   public static final ExportFileStatus CLOSE = new ExportFileStatus(3, "Close", MetadataType.EXPORT_FILE_STATUS);
/* 14 */   public static final ExportFileStatus ERROR = new ExportFileStatus(4, "Error", MetadataType.EXPORT_FILE_STATUS);
/*    */ 
/*    */   
/*    */   public ExportFileStatus() {}
/*    */   
/*    */   private ExportFileStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 20 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/ExportFileStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */