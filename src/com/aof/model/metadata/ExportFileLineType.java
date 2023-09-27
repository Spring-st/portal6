/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExportFileLineType
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final ExportFileLineType MASTER_DATA = new ExportFileLineType(1, "Master Data", MetadataType.EXPORT_FILE_LINE_TYPE);
/* 12 */   public static final ExportFileLineType DETAIL_DATA = new ExportFileLineType(2, "Detail Data", MetadataType.EXPORT_FILE_LINE_TYPE);
/* 13 */   public static final ExportFileLineType RECHARGE_DATA = new ExportFileLineType(3, "Recharge Data", MetadataType.EXPORT_FILE_LINE_TYPE);
/*    */ 
/*    */   
/*    */   public ExportFileLineType() {}
/*    */   
/*    */   private ExportFileLineType(int metadataId, String defaultLabel, MetadataType type) {
/* 19 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/ExportFileLineType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */