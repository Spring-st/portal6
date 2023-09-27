/*    */ package com.aof.model.metadata;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HotelLevel
/*    */   extends MetadataDetailEnum
/*    */ {
/* 11 */   public static final HotelLevel UNKNOW = new HotelLevel(0, "Unknow", MetadataType.HOTEL_LEVEL);
/* 12 */   public static final HotelLevel STAR_1 = new HotelLevel(1, "1 Star", MetadataType.HOTEL_LEVEL);
/* 13 */   public static final HotelLevel STAR_2 = new HotelLevel(2, "2 Star", MetadataType.HOTEL_LEVEL);
/* 14 */   public static final HotelLevel STAR_3 = new HotelLevel(3, "3 Star", MetadataType.HOTEL_LEVEL);
/* 15 */   public static final HotelLevel STAR_4 = new HotelLevel(4, "4 Star", MetadataType.HOTEL_LEVEL);
/* 16 */   public static final HotelLevel STAR_5 = new HotelLevel(5, "5 Star", MetadataType.HOTEL_LEVEL);
/* 17 */   public static final HotelLevel STAR_6 = new HotelLevel(6, "6 Star", MetadataType.HOTEL_LEVEL);
/* 18 */   public static final HotelLevel STAR_7 = new HotelLevel(7, "7 Star", MetadataType.HOTEL_LEVEL);
/*    */ 
/*    */   
/*    */   public HotelLevel() {}
/*    */   
/*    */   private HotelLevel(int metadataId, String defaultLabel, MetadataType type) {
/* 24 */     super(metadataId, defaultLabel, type);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/HotelLevel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */