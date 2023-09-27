/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExportStatus
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final ExportStatus UNEXPORTED = new ExportStatus(1, "Unexported", MetadataType.EXPORT_STATUS);
/* 12 */   public static final ExportStatus EXPORTED = new ExportStatus(2, "Exported", MetadataType.EXPORT_STATUS);
/* 13 */   public static final ExportStatus NEEDREEXPORT = new ExportStatus(3, "NeedReExport", MetadataType.EXPORT_STATUS);
/*    */ 
/*    */   
/*    */   public ExportStatus() {}
/*    */   
/*    */   private ExportStatus(int metadataId, String defaultLabel, MetadataType type) {
/* 19 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/ExportStatus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */