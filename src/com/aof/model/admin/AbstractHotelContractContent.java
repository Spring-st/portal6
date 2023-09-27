/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import com.aof.model.BaseAttachmentContent;
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
/*    */ public abstract class AbstractHotelContractContent
/*    */   extends BaseAttachmentContent
/*    */   implements Serializable
/*    */ {
/*    */   public AbstractHotelContractContent() {}
/*    */   
/*    */   public AbstractHotelContractContent(Integer id) {
/* 41 */     setId(id);
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
/* 52 */     if (rhs == null) return false; 
/* 53 */     if (this == rhs) return true; 
/* 54 */     if (!(rhs instanceof HotelContractContent)) return false; 
/* 55 */     HotelContractContent that = (HotelContractContent)rhs;
/* 56 */     if (getId() != null) return getId().equals(that.getId()); 
/* 57 */     return (that.getId() == null);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/AbstractHotelContractContent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */