/*    */ package com.aof.model.basicDataView;
/*    */ 
/*    */ import com.aof.model.basic.StorageLocation;
/*    */ import com.aof.model.basic.WmsPart;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LocationPartNumber
/*    */   extends AbstractLocationPartNumber
/*    */   implements Serializable
/*    */ {
/*    */   public LocationPartNumber() {}
/*    */   
/*    */   public LocationPartNumber(StorageLocation id, WmsPart part) {
/* 18 */     super(id, part);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basicDataView/LocationPartNumber.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */