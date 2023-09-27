/*     */ package com.aof.web.struts.form.admin;
/*     */ 
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PriceQueryForm
/*     */   extends BaseSessionQueryForm
/*     */ {
/*     */   private String id;
/*     */   private String hotel_id;
/*     */   private String room;
/*     */   private String price;
/*     */   private String discount;
/*     */   private String description;
/*     */   private String enabled;
/*     */   
/*     */   public String getId() {
/*  27 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setId(String id) {
/*  35 */     this.id = id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHotel_id() {
/*  49 */     return this.hotel_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHotel_id(String hotel_id) {
/*  57 */     this.hotel_id = hotel_id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  75 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDescription(String description) {
/*  83 */     this.description = description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDiscount() {
/*  90 */     return this.discount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDiscount(String discount) {
/*  98 */     this.discount = discount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEnabled() {
/* 105 */     return this.enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEnabled(String enabled) {
/* 113 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrice() {
/* 120 */     return this.price;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrice(String price) {
/* 128 */     this.price = price;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRoom() {
/* 135 */     return this.room;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRoom(String room) {
/* 143 */     this.room = room;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/admin/PriceQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */