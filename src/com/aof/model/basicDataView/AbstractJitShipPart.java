/*    */ package com.aof.model.basicDataView;
/*    */ 
/*    */ import com.aof.model.basic.WmsPart;
/*    */ import java.io.Serializable;
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public abstract class AbstractJitShipPart
/*    */   implements Serializable {
/*    */   private WmsPart part;
/*    */   private BigDecimal qty;
/*    */   private BigDecimal currentQty;
/*    */   
/*    */   public WmsPart getPart() {
/* 14 */     return this.part;
/*    */   }
/*    */   public void setPart(WmsPart part) {
/* 17 */     this.part = part;
/*    */   }
/*    */   
/*    */   public AbstractJitShipPart() {}
/*    */   
/*    */   public AbstractJitShipPart(WmsPart id) {
/* 23 */     setPart(id);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 27 */     int prime = 31;
/* 28 */     int result = 1;
/* 29 */     result = 31 * result + ((this.part == null) ? 0 : this.part.hashCode());
/*    */     
/* 31 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 42 */     if (this == obj) {
/* 43 */       return true;
/*    */     }
/* 45 */     if (obj == null) {
/* 46 */       return false;
/*    */     }
/* 48 */     if (getClass() != obj.getClass()) {
/* 49 */       return false;
/*    */     }
/* 51 */     AbstractJitShipPart other = (AbstractJitShipPart)obj;
/*    */     
/* 53 */     if (this.part == null) {
/* 54 */       if (other.part != null) {
/* 55 */         return false;
/*    */       }
/* 57 */     } else if (!this.part.equals(other.part)) {
/* 58 */       return false;
/*    */     } 
/*    */     
/* 61 */     return true;
/*    */   }
/*    */   public BigDecimal getQty() {
/* 64 */     return this.qty;
/*    */   }
/*    */   public void setQty(BigDecimal qty) {
/* 67 */     this.qty = qty;
/*    */   }
/*    */   public BigDecimal getCurrentQty() {
/* 70 */     return this.currentQty;
/*    */   }
/*    */   public void setCurrentQty(BigDecimal currentQty) {
/* 73 */     this.currentQty = currentQty;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basicDataView/AbstractJitShipPart.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */