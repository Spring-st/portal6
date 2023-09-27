/*    */ package com.aof.model.basicDataView;
/*    */ 
/*    */ import com.aof.model.basic.WmsPart;
/*    */ import java.io.Serializable;
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public abstract class AbstractSkPartSumNumber
/*    */   implements Serializable {
/*    */   private WmsPart part;
/*    */   private BigDecimal sumNumber;
/*    */   private Integer count;
/*    */   
/*    */   public BigDecimal getSumNumber() {
/* 14 */     return this.sumNumber;
/*    */   }
/*    */   public void setSumNumber(BigDecimal sumNumber) {
/* 17 */     this.sumNumber = sumNumber;
/*    */   }
/*    */   
/*    */   public WmsPart getPart() {
/* 21 */     return this.part;
/*    */   }
/*    */   public void setPart(WmsPart part) {
/* 24 */     this.part = part;
/*    */   }
/*    */   
/*    */   public AbstractSkPartSumNumber() {}
/*    */   
/*    */   public AbstractSkPartSumNumber(WmsPart id) {
/* 30 */     setPart(id);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 34 */     int prime = 31;
/* 35 */     int result = 1;
/* 36 */     result = 31 * result + ((this.part == null) ? 0 : this.part.hashCode());
/*    */     
/* 38 */     return result;
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
/* 49 */     if (this == obj) {
/* 50 */       return true;
/*    */     }
/* 52 */     if (obj == null) {
/* 53 */       return false;
/*    */     }
/* 55 */     if (getClass() != obj.getClass()) {
/* 56 */       return false;
/*    */     }
/* 58 */     AbstractSkPartSumNumber other = (AbstractSkPartSumNumber)obj;
/*    */     
/* 60 */     if (this.part == null) {
/* 61 */       if (other.part != null) {
/* 62 */         return false;
/*    */       }
/* 64 */     } else if (!this.part.equals(other.part)) {
/* 65 */       return false;
/*    */     } 
/*    */     
/* 68 */     return true;
/*    */   }
/*    */   public Integer getCount() {
/* 71 */     return this.count;
/*    */   }
/*    */   public void setCount(Integer count) {
/* 74 */     this.count = count;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basicDataView/AbstractSkPartSumNumber.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */