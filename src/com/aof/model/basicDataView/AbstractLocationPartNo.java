/*    */ package com.aof.model.basicDataView;
/*    */ 
/*    */ import com.aof.model.basic.StorageLocation;
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
/*    */ public abstract class AbstractLocationPartNo
/*    */   implements Serializable
/*    */ {
/*    */   private StorageLocation location;
/*    */   private WmsPart part;
/*    */   private Integer count;
/*    */   private BigDecimal sumNumber;
/*    */   
/*    */   public BigDecimal getSumNumber() {
/* 25 */     return this.sumNumber;
/*    */   }
/*    */   public void setSumNumber(BigDecimal sumNumber) {
/* 28 */     this.sumNumber = sumNumber;
/*    */   }
/*    */   public Integer getCount() {
/* 31 */     return this.count;
/*    */   }
/*    */   public void setCount(Integer count) {
/* 34 */     this.count = count;
/*    */   }
/*    */   
/*    */   public StorageLocation getLocation() {
/* 38 */     return this.location;
/*    */   }
/*    */   public void setLocation(StorageLocation location) {
/* 41 */     this.location = location;
/*    */   }
/*    */   public WmsPart getPart() {
/* 44 */     return this.part;
/*    */   }
/*    */   public void setPart(WmsPart part) {
/* 47 */     this.part = part;
/*    */   }
/*    */   
/*    */   public AbstractLocationPartNo() {}
/*    */   
/*    */   public AbstractLocationPartNo(StorageLocation id) {
/* 53 */     setLocation(id);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 57 */     return this.location.hashCode();
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
/* 69 */     return this.location.equals(obj);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basicDataView/AbstractLocationPartNo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */