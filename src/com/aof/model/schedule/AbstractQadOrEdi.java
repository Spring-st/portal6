/*    */ package com.aof.model.schedule;
/*    */ 
/*    */ import com.aof.model.basic.WmsPart;
/*    */ import java.io.Serializable;
/*    */ import java.math.BigDecimal;
/*    */ import java.util.Date;
/*    */ 
/*    */ public abstract class AbstractQadOrEdi implements Serializable {
/*    */   private Integer id;
/*    */   private String models;
/*    */   private WmsPart qadPart;
/*    */   private Date createDate;
/*    */   private BigDecimal qty;
/*    */   private String des;
/*    */   
/*    */   public Integer getId() {
/* 17 */     return this.id;
/*    */   } public AbstractQadOrEdi() {}
/*    */   public void setId(Integer id) {
/* 20 */     this.id = id;
/*    */   }
/*    */   public AbstractQadOrEdi(Integer id) {
/* 23 */     this.id = id;
/*    */   } public String getModels() {
/* 25 */     return this.models;
/*    */   }
/*    */   public void setModels(String models) {
/* 28 */     this.models = models;
/*    */   }
/*    */   public WmsPart getQadPart() {
/* 31 */     return this.qadPart;
/*    */   }
/*    */   public void setQadPart(WmsPart qadPart) {
/* 34 */     this.qadPart = qadPart;
/*    */   }
/*    */   public Date getCreateDate() {
/* 37 */     return this.createDate;
/*    */   }
/*    */   public void setCreateDate(Date createDate) {
/* 40 */     this.createDate = createDate;
/*    */   }
/*    */   public BigDecimal getQty() {
/* 43 */     return this.qty;
/*    */   }
/*    */   public void setQty(BigDecimal qty) {
/* 46 */     this.qty = qty;
/*    */   }
/*    */   public String getDes() {
/* 49 */     return this.des;
/*    */   }
/*    */   public void setDes(String des) {
/* 52 */     this.des = des;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/AbstractQadOrEdi.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */