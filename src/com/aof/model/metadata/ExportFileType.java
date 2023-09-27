/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExportFileType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final ExportFileType TEXT = new ExportFileType(1, "Text", MetadataType.EXPORT_FILE_TYPE);
/* 12 */   public static final ExportFileType XML = new ExportFileType(2, "Xml", MetadataType.EXPORT_FILE_TYPE);
/*    */ 
/*    */   
/*    */   public ExportFileType() {}
/*    */   
/*    */   private ExportFileType(int metadataId, String defaultLabel, MetadataType type) {
/* 18 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/ExportFileType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */