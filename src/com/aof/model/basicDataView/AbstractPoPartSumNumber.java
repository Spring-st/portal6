/*    */ package com.aof.model.basicDataView;
/*    */ 
/*    */ import com.aof.model.basic.WmsPart;
/*    */ import java.io.Serializable;
/*    */ import java.math.BigDecimal;
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
/*    */ public abstract class AbstractPoPartSumNumber
/*    */   implements Serializable
/*    */ {
/*    */   private WmsPart part;
/*    */   private BigDecimal sumNumber;
/*    */   
/*    */   public BigDecimal getSumNumber() {
/* 23 */     return this.sumNumber;
/*    */   }
/*    */   public void setSumNumber(BigDecimal sumNumber) {
/* 26 */     this.sumNumber = sumNumber;
/*    */   }
/*    */   
/*    */   public WmsPart getPart() {
/* 30 */     return this.part;
/*    */   }
/*    */   public void setPart(WmsPart part) {
/* 33 */     this.part = part;
/*    */   }
/*    */   
/*    */   public AbstractPoPartSumNumber() {}
/*    */   
/*    */   public AbstractPoPartSumNumber(WmsPart id) {
/* 39 */     setPart(id);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 43 */     return this.part.hashCode();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 55 */     return this.part.equals(obj);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basicDataView/AbstractPoPartSumNumber.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */