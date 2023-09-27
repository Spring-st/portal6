/*    */ package com.aof.model.basic;
/*    */ 
/*    */ import com.aof.model.po.WmsLot;
/*    */ import java.io.Serializable;
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public class AbstractUselessPart
/*    */   implements Serializable {
/*    */   private Integer id;
/*    */   private WmsLot lot;
/*    */   private WmsPart part;
/*    */   private WmsPart fatherPart;
/*    */   private BigDecimal amount;
/*    */   private PartDecomposition partDecomposition;
/*    */   
/*    */   public Integer getId() {
/* 17 */     return this.id;
/*    */   }
/*    */   public void setId(Integer id) {
/* 20 */     this.id = id;
/*    */   }
/*    */   public WmsLot getLot() {
/* 23 */     return this.lot;
/*    */   }
/*    */   public void setLot(WmsLot lot) {
/* 26 */     this.lot = lot;
/*    */   }
/*    */   public WmsPart getPart() {
/* 29 */     return this.part;
/*    */   }
/*    */   public void setPart(WmsPart part) {
/* 32 */     this.part = part;
/*    */   }
/*    */   public WmsPart getFatherPart() {
/* 35 */     return this.fatherPart;
/*    */   }
/*    */   
/*    */   public BigDecimal getAmount() {
/* 39 */     return this.amount;
/*    */   }
/*    */   public void setAmount(BigDecimal amount) {
/* 42 */     this.amount = amount;
/*    */   }
/*    */   public void setFatherPart(WmsPart fatherPart) {
/* 45 */     this.fatherPart = fatherPart;
/*    */   }
/*    */   public PartDecomposition getPartDecomposition() {
/* 48 */     return this.partDecomposition;
/*    */   }
/*    */   public void setPartDecomposition(PartDecomposition partDecomposition) {
/* 51 */     this.partDecomposition = partDecomposition;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractUselessPart() {}
/*    */   
/*    */   public AbstractUselessPart(Integer id) {
/* 58 */     this.id = id;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractUselessPart.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */