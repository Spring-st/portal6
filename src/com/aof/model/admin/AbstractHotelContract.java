/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import com.aof.model.BaseAttachment;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractHotelContract
/*    */   extends BaseAttachment
/*    */   implements Serializable
/*    */ {
/*    */   private Hotel hotel;
/*    */   
/*    */   public AbstractHotelContract() {}
/*    */   
/*    */   public AbstractHotelContract(Integer id) {
/* 44 */     setId(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Hotel getHotel() {
/* 51 */     return this.hotel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setHotel(Hotel hotel) {
/* 59 */     this.hotel = hotel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object rhs) {
/* 70 */     if (rhs == null) return false; 
/* 71 */     if (this == rhs) return true; 
/* 72 */     if (!(rhs instanceof HotelContract)) return false; 
/* 73 */     HotelContract that = (HotelContract)rhs;
/* 74 */     if (getId() != null) return getId().equals(that.getId()); 
/* 75 */     return (that.getId() == null);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractHotelContract.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */