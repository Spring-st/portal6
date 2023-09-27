/*    */ package com.aof.model.comprehensive;
/*    */ 
/*    */ import com.aof.model.po.Box;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public abstract class AbstractBoxAdjustmentBox implements Serializable {
/*  7 */   private int hashValue = 0;
/*    */   private Integer id;
/*    */   private Box box_id;
/*    */   private BoxAdjustment box_adjustment_id;
/*    */   
/*    */   public Integer getId() {
/* 13 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Integer id) {
/* 17 */     this.id = id;
/*    */   }
/*    */   
/*    */   public int getHashValue() {
/* 21 */     return this.hashValue;
/*    */   }
/*    */   
/*    */   public void setHashValue(int hashValue) {
/* 25 */     this.hashValue = hashValue;
/*    */   }
/*    */   
/*    */   public Box getBox_id() {
/* 29 */     return this.box_id;
/*    */   }
/*    */   
/*    */   public void setBox_id(Box box_id) {
/* 33 */     this.box_id = box_id;
/*    */   }
/*    */   
/*    */   public BoxAdjustment getBox_adjustment_id() {
/* 37 */     return this.box_adjustment_id;
/*    */   }
/*    */   
/*    */   public void setBox_adjustment_id(BoxAdjustment box_adjustment_id) {
/* 41 */     this.box_adjustment_id = box_adjustment_id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractBoxAdjustmentBox() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractBoxAdjustmentBox(Integer id) {
/* 53 */     setId(id);
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
/* 64 */     if (rhs == null)
/* 65 */       return false; 
/* 66 */     if (this == rhs)
/* 67 */       return true; 
/* 68 */     if (!(rhs instanceof BoxAdjustmentBox))
/* 69 */       return false; 
/* 70 */     BoxAdjustmentBox that = (BoxAdjustmentBox)rhs;
/* 71 */     if (getId() != null)
/* 72 */       return getId().equals(that.getId()); 
/* 73 */     return (that.getId() == null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 84 */     if (this.hashValue == 0) {
/* 85 */       int result = 17;
/* 86 */       int poIdValue = (getId() == null) ? 0 : getId().hashCode();
/* 87 */       result = result * 37 + poIdValue;
/* 88 */       this.hashValue = result;
/*    */     } 
/* 90 */     return this.hashValue;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/comprehensive/AbstractBoxAdjustmentBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */