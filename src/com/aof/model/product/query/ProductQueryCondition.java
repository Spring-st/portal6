/*    */ package com.aof.model.product.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProductQueryCondition
/*    */   extends Enum {
/*  7 */   public static final ProductQueryCondition ID_EQ = new ProductQueryCondition("id_eq");
/*  8 */   public static final ProductQueryCondition LOTSER_EQ = new ProductQueryCondition("lotSer_eq");
/*  9 */   public static final ProductQueryCondition NUMBER_EQ = new ProductQueryCondition("number_eq");
/* 10 */   public static final ProductQueryCondition POIP_ID_EQ = new ProductQueryCondition("poipId_eq");
/* 11 */   public static final ProductQueryCondition CHILDITEM_EQ = new ProductQueryCondition("childItem_eq");
/* 12 */   public static final ProductQueryCondition PORITEM_EQ = new ProductQueryCondition("poritem_eq");
/* 13 */   public static final ProductQueryCondition RQCITEM_EQ = new ProductQueryCondition("rqcitem_eq");
/* 14 */   public static final ProductQueryCondition RQCITEM_NOTNULL_EQ = new ProductQueryCondition("rqcitem_notnull_eq");
/* 15 */   public static final ProductQueryCondition POINBOUDITEM_EQ = new ProductQueryCondition("poInboundItem_eq");
/* 16 */   public static final ProductQueryCondition PORETURNSITEM_EQ = new ProductQueryCondition("poReturnsItem_eq");
/* 17 */   public static final ProductQueryCondition ISPICKOUTBOUNDFINISH_EQ = new ProductQueryCondition("isPickingOutboundFinish_eq");
/* 18 */   public static final ProductQueryCondition BLANKETMARK_EQ = new ProductQueryCondition("blanketMark_eq");
/* 19 */   public static final ProductQueryCondition WMSTOOL_EQ = new ProductQueryCondition("wmstool_eq");
/*    */   
/* 21 */   public static final ProductQueryCondition ISSCANNING_EQ = new ProductQueryCondition("is_scanning_eq");
/* 22 */   public static final ProductQueryCondition ISPUTINTSTORAGE_EQ = new ProductQueryCondition("isPutIntStorage_eq");
/* 23 */   public static final ProductQueryCondition ISENABLED_EQ = new ProductQueryCondition("isEnabled_eq");
/* 24 */   public static final ProductQueryCondition ISSCANNINGANDOK_EQ = new ProductQueryCondition("is_scanningandok_eq");
/* 25 */   public static final ProductQueryCondition ISSELECTED_EQ = new ProductQueryCondition("is_selected_eq");
/* 26 */   public static final ProductQueryCondition LOTSET_EQ = new ProductQueryCondition("lotset_eq");
/* 27 */   public static final ProductQueryCondition SUPPLIERCODE_EQ = new ProductQueryCondition("supplier_code_eq");
/* 28 */   public static final ProductQueryCondition SUPPLIERNAME_EQ = new ProductQueryCondition("supplier_name_eq");
/* 29 */   public static final ProductQueryCondition STARTTIME_EQ = new ProductQueryCondition("starttime_eq");
/* 30 */   public static final ProductQueryCondition ENDTIME_EQ = new ProductQueryCondition("endtime_eq");
/*    */   
/* 32 */   public static final ProductQueryCondition SUPPLIER_EQ = new ProductQueryCondition("supplier_eq");
/* 33 */   public static final ProductQueryCondition DATE_EQ = new ProductQueryCondition("date_eq");
/* 34 */   public static final ProductQueryCondition PARTID_EQ = new ProductQueryCondition("partId_eq");
/* 35 */   public static final ProductQueryCondition PARTID2_EQ = new ProductQueryCondition("partId2_eq");
/* 36 */   public static final ProductQueryCondition ID_NOT_IN = new ProductQueryCondition("id_not_in");
/* 37 */   public static final ProductQueryCondition LOCATION_EQ = new ProductQueryCondition("location_eq");
/* 38 */   public static final ProductQueryCondition SPECIFICATION_EQ = new ProductQueryCondition("specifications_eq");
/* 39 */   public static final ProductQueryCondition PARTIDSTR_EQ = new ProductQueryCondition("partIdStr_eq");
/* 40 */   public static final ProductQueryCondition FREEZE_EQ = new ProductQueryCondition("freeze_eq");
/* 41 */   public static final ProductQueryCondition THAW_EQ = new ProductQueryCondition("thaw_eq");
/*    */   
/*    */   protected ProductQueryCondition(String value) {
/* 44 */     super(value);
/*    */   }
/*    */   
/*    */   public static ProductQueryCondition getEnum(String value) {
/* 48 */     return (ProductQueryCondition)getEnum(ProductQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/product/query/ProductQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */