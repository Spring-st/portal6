/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProductBelowLineQueryCondition
/*    */   extends Enum {
/*  7 */   public static final ProductBelowLineQueryCondition ID_EQ = new ProductBelowLineQueryCondition("id_eq");
/*  8 */   public static final ProductBelowLineQueryCondition LOTSER_EQ = new ProductBelowLineQueryCondition("lotSer_eq");
/*  9 */   public static final ProductBelowLineQueryCondition NUMBER_EQ = new ProductBelowLineQueryCondition("number_eq");
/* 10 */   public static final ProductBelowLineQueryCondition POIP_ID_EQ = new ProductBelowLineQueryCondition("poipId_eq");
/* 11 */   public static final ProductBelowLineQueryCondition CHILDITEM_EQ = new ProductBelowLineQueryCondition("childItem_eq");
/* 12 */   public static final ProductBelowLineQueryCondition PORITEM_EQ = new ProductBelowLineQueryCondition("poritem_eq");
/* 13 */   public static final ProductBelowLineQueryCondition RQCITEM_EQ = new ProductBelowLineQueryCondition("rqcitem_eq");
/* 14 */   public static final ProductBelowLineQueryCondition RQCITEM_NOTNULL_EQ = new ProductBelowLineQueryCondition("rqcitem_notnull_eq");
/* 15 */   public static final ProductBelowLineQueryCondition POINBOUDITEM_EQ = new ProductBelowLineQueryCondition("poInboundItem_eq");
/* 16 */   public static final ProductBelowLineQueryCondition PORETURNSITEM_EQ = new ProductBelowLineQueryCondition("poReturnsItem_eq");
/* 17 */   public static final ProductBelowLineQueryCondition ISPICKOUTBOUNDFINISH_EQ = new ProductBelowLineQueryCondition("isPickingOutboundFinish_eq");
/* 18 */   public static final ProductBelowLineQueryCondition BLANKETMARK_EQ = new ProductBelowLineQueryCondition("blanketMark_eq");
/* 19 */   public static final ProductBelowLineQueryCondition WMSTOOL_EQ = new ProductBelowLineQueryCondition("wmstool_eq");
/*    */   
/* 21 */   public static final ProductBelowLineQueryCondition ISSCANNING_EQ = new ProductBelowLineQueryCondition("is_scanning_eq");
/* 22 */   public static final ProductBelowLineQueryCondition ISPUTINTSTORAGE_EQ = new ProductBelowLineQueryCondition("isPutIntStorage_eq");
/* 23 */   public static final ProductBelowLineQueryCondition ISENABLED_EQ = new ProductBelowLineQueryCondition("isEnabled_eq");
/* 24 */   public static final ProductBelowLineQueryCondition ISSCANNINGANDOK_EQ = new ProductBelowLineQueryCondition("is_scanningandok_eq");
/* 25 */   public static final ProductBelowLineQueryCondition ISSELECTED_EQ = new ProductBelowLineQueryCondition("is_selected_eq");
/* 26 */   public static final ProductBelowLineQueryCondition LOTSET_EQ = new ProductBelowLineQueryCondition("lotset_eq");
/* 27 */   public static final ProductBelowLineQueryCondition SUPPLIERCODE_EQ = new ProductBelowLineQueryCondition("supplier_code_eq");
/* 28 */   public static final ProductBelowLineQueryCondition SUPPLIERNAME_EQ = new ProductBelowLineQueryCondition("supplier_name_eq");
/* 29 */   public static final ProductBelowLineQueryCondition STARTTIME_EQ = new ProductBelowLineQueryCondition("starttime_eq");
/* 30 */   public static final ProductBelowLineQueryCondition ENDTIME_EQ = new ProductBelowLineQueryCondition("endtime_eq");
/*    */   
/* 32 */   public static final ProductBelowLineQueryCondition SUPPLIER_EQ = new ProductBelowLineQueryCondition("supplier_eq");
/* 33 */   public static final ProductBelowLineQueryCondition DATE_EQ = new ProductBelowLineQueryCondition("date_eq");
/* 34 */   public static final ProductBelowLineQueryCondition PARTID_EQ = new ProductBelowLineQueryCondition("partId_eq");
/* 35 */   public static final ProductBelowLineQueryCondition PARTID2_EQ = new ProductBelowLineQueryCondition("partId2_eq");
/* 36 */   public static final ProductBelowLineQueryCondition ID_NOT_IN = new ProductBelowLineQueryCondition("id_not_in");
/* 37 */   public static final ProductBelowLineQueryCondition LOCATION_EQ = new ProductBelowLineQueryCondition("location_eq");
/* 38 */   public static final ProductBelowLineQueryCondition SPECIFICATION_EQ = new ProductBelowLineQueryCondition("specifications_eq");
/* 39 */   public static final ProductBelowLineQueryCondition PARTIDSTR_EQ = new ProductBelowLineQueryCondition("partIdStr_eq");
/* 40 */   public static final ProductBelowLineQueryCondition FREEZE_EQ = new ProductBelowLineQueryCondition("freeze_eq");
/* 41 */   public static final ProductBelowLineQueryCondition THAW_EQ = new ProductBelowLineQueryCondition("thaw_eq");
/* 42 */   public static final ProductBelowLineQueryCondition TOOL_EQ = new ProductBelowLineQueryCondition("tool_eq");
/*    */   
/*    */   protected ProductBelowLineQueryCondition(String value) {
/* 45 */     super(value);
/*    */   }
/*    */   
/*    */   public static ProductBelowLineQueryCondition getEnum(String value) {
/* 49 */     return (ProductBelowLineQueryCondition)getEnum(ProductBelowLineQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/ProductBelowLineQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */