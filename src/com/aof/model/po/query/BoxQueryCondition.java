/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class BoxQueryCondition
/*    */   extends Enum {
/*  7 */   public static final BoxQueryCondition ID_EQ = new BoxQueryCondition("id_eq");
/*  8 */   public static final BoxQueryCondition LOTSER_EQ = new BoxQueryCondition("lotSer_eq");
/*  9 */   public static final BoxQueryCondition NUMBER_EQ = new BoxQueryCondition("number_eq");
/* 10 */   public static final BoxQueryCondition POIP_ID_EQ = new BoxQueryCondition("poipId_eq");
/* 11 */   public static final BoxQueryCondition CHILDITEM_EQ = new BoxQueryCondition("childItem_eq");
/* 12 */   public static final BoxQueryCondition PORITEM_EQ = new BoxQueryCondition("poritem_eq");
/* 13 */   public static final BoxQueryCondition RQCITEM_EQ = new BoxQueryCondition("rqcitem_eq");
/* 14 */   public static final BoxQueryCondition RQCITEM_NOTNULL_EQ = new BoxQueryCondition("rqcitem_notnull_eq");
/* 15 */   public static final BoxQueryCondition POINBOUDITEM_EQ = new BoxQueryCondition("poInboundItem_eq");
/* 16 */   public static final BoxQueryCondition PORETURNSITEM_EQ = new BoxQueryCondition("poReturnsItem_eq");
/* 17 */   public static final BoxQueryCondition ISPICKOUTBOUNDFINISH_EQ = new BoxQueryCondition("isPickingOutboundFinish_eq");
/* 18 */   public static final BoxQueryCondition BLANKETMARK_EQ = new BoxQueryCondition("blanketMark_eq");
/* 19 */   public static final BoxQueryCondition WMSTOOL_EQ = new BoxQueryCondition("wmstool_eq");
/* 20 */   public static final BoxQueryCondition SINGLE_EQ = new BoxQueryCondition("single_eq");
/*    */   
/* 22 */   public static final BoxQueryCondition ISSCANNING_EQ = new BoxQueryCondition("is_scanning_eq");
/* 23 */   public static final BoxQueryCondition ISPUTINTSTORAGE_EQ = new BoxQueryCondition("isPutIntStorage_eq");
/* 24 */   public static final BoxQueryCondition ISENABLED_EQ = new BoxQueryCondition("isEnabled_eq");
/* 25 */   public static final BoxQueryCondition ISSCANNINGANDOK_EQ = new BoxQueryCondition("is_scanningandok_eq");
/* 26 */   public static final BoxQueryCondition ISSELECTED_EQ = new BoxQueryCondition("is_selected_eq");
/* 27 */   public static final BoxQueryCondition LOTSET_EQ = new BoxQueryCondition("lotset_eq");
/* 28 */   public static final BoxQueryCondition SUPPLIERCODE_EQ = new BoxQueryCondition("supplier_code_eq");
/* 29 */   public static final BoxQueryCondition SUPPLIERNAME_EQ = new BoxQueryCondition("supplier_name_eq");
/* 30 */   public static final BoxQueryCondition STARTTIME_EQ = new BoxQueryCondition("starttime_eq");
/* 31 */   public static final BoxQueryCondition ENDTIME_EQ = new BoxQueryCondition("endtime_eq");
/*    */   
/* 33 */   public static final BoxQueryCondition SUPPLIER_EQ = new BoxQueryCondition("supplier_eq");
/* 34 */   public static final BoxQueryCondition DATE_EQ = new BoxQueryCondition("date_eq");
/* 35 */   public static final BoxQueryCondition PARTID_EQ = new BoxQueryCondition("partId_eq");
/* 36 */   public static final BoxQueryCondition PARTID2_EQ = new BoxQueryCondition("partId2_eq");
/* 37 */   public static final BoxQueryCondition ID_NOT_IN = new BoxQueryCondition("id_not_in");
/* 38 */   public static final BoxQueryCondition LOCATION_EQ = new BoxQueryCondition("location_eq");
/* 39 */   public static final BoxQueryCondition SPECIFICATION_EQ = new BoxQueryCondition("specifications_eq");
/* 40 */   public static final BoxQueryCondition PARTIDSTR_EQ = new BoxQueryCondition("partIdStr_eq");
/* 41 */   public static final BoxQueryCondition FREEZE_EQ = new BoxQueryCondition("freeze_eq");
/* 42 */   public static final BoxQueryCondition THAW_EQ = new BoxQueryCondition("thaw_eq");
/* 43 */   public static final BoxQueryCondition TYPE_EQ = new BoxQueryCondition("type_eq");
/* 44 */   public static final BoxQueryCondition STATUS_EQ = new BoxQueryCondition("status_eq");
/* 45 */   public static final BoxQueryCondition STATUS_RQC_EQ = new BoxQueryCondition("status_rqc_eq");
/* 46 */   public static final BoxQueryCondition STATUS_NOT_EQ = new BoxQueryCondition("status_not_eq");
/* 47 */   public static final BoxQueryCondition PART_TYPE_EQ = new BoxQueryCondition("part_type_eq");
/* 48 */   public static final BoxQueryCondition STA_GT = new BoxQueryCondition("status_gt");
/* 49 */   public static final BoxQueryCondition PART_ID_EQ = new BoxQueryCondition("part_id_eq");
/* 50 */   public static final BoxQueryCondition LOCATION_NOT_EQ = new BoxQueryCondition("location_not_eq");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   public static final BoxQueryCondition STATUS_RQC_NOT_EQ = new BoxQueryCondition("statusrqc_not_eq");
/*    */   
/* 57 */   public static final BoxQueryCondition ISENABLED_NOT_EQ = new BoxQueryCondition("isEnabled_not_eq");
/* 58 */   public static final BoxQueryCondition ISNOTZERO = new BoxQueryCondition("isnot_zero");
/* 59 */   public static final BoxQueryCondition LOCATION_NOTNULL = new BoxQueryCondition("location_notnull");
/* 60 */   public static final BoxQueryCondition LOCATION_STORE_ROOM_TYPE_EQ = new BoxQueryCondition("location_store_room_type_eq");
/*    */ 
/*    */ 
/*    */   
/* 64 */   public static final BoxQueryCondition NUMBER_GT = new BoxQueryCondition("number_gt");
/* 65 */   public static final BoxQueryCondition LOCATION_ID_EQ = new BoxQueryCondition("location_id_eq");
/* 66 */   public static final BoxQueryCondition LOCATION_TYPE_OR_RQC_EQ = new BoxQueryCondition("location_type_or_rqc_eq");
/* 67 */   public static final BoxQueryCondition PART_ID_IN = new BoxQueryCondition("part_id_in");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 72 */   public static final BoxQueryCondition SYNC_STATUS_EQ = new BoxQueryCondition("sync_status_eq");
/* 73 */   public static final BoxQueryCondition SHIPORDER_ITEM_ID_EQ = new BoxQueryCondition("shipOrder_item_id_eq");
/*    */   protected BoxQueryCondition(String value) {
/* 75 */     super(value);
/*    */   }
/*    */   
/*    */   public static BoxQueryCondition getEnum(String value) {
/* 79 */     return (BoxQueryCondition)getEnum(BoxQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/BoxQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */