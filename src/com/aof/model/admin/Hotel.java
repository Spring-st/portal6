/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;
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
/*    */ public class Hotel
/*    */   extends AbstractHotel
/*    */   implements Serializable
/*    */ {
/*    */   private List rooms;
/*    */   
/*    */   public Hotel() {}
/*    */   
/*    */   public Hotel(Integer id) {
/* 40 */     super(id);
/*    */   }
/*    */   
/*    */   public List getRooms() {
/* 44 */     return this.rooms;
/*    */   }
/*    */   
/*    */   public void setRooms(ArrayList list) {
/* 48 */     this.rooms = list;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void emailed(Date d) {
/* 57 */     setEmailDate(d);
/* 58 */     setEmailTimes(getEmailTimes() + 1);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/Hotel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */