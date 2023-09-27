/*    */ package com.aof.model.sync.shared;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.math.BigDecimal;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class AbstractXbqtyDet implements Serializable {
/*    */   private Integer xbqty_id;
/*    */   private String xbqty_nbr;
/*    */   private String xbqty_part;
/*    */   private BigDecimal xbqty_qty_real;
/*    */   private BigDecimal xbqty_qty_arr;
/*    */   private String xbqty_rev01;
/*    */   private String xbqty_rev02;
/*    */   private Date xbqty_date_real;
/*    */   private Date xbqty_date_arr;
/*    */   
/*    */   public AbstractXbqtyDet() {}
/*    */   
/*    */   public AbstractXbqtyDet(Integer id) {
/* 21 */     this.xbqty_id = id;
/*    */   }
/*    */   public Integer getXbqty_id() {
/* 24 */     return this.xbqty_id;
/*    */   }
/*    */   public void setXbqty_id(Integer xbqty_id) {
/* 27 */     this.xbqty_id = xbqty_id;
/*    */   }
/*    */   public String getXbqty_nbr() {
/* 30 */     return this.xbqty_nbr;
/*    */   }
/*    */   public void setXbqty_nbr(String xbqty_nbr) {
/* 33 */     this.xbqty_nbr = xbqty_nbr;
/*    */   }
/*    */   public String getXbqty_part() {
/* 36 */     return this.xbqty_part;
/*    */   }
/*    */   public void setXbqty_part(String xbqty_part) {
/* 39 */     this.xbqty_part = xbqty_part;
/*    */   }
/*    */   public BigDecimal getXbqty_qty_real() {
/* 42 */     return this.xbqty_qty_real;
/*    */   }
/*    */   public void setXbqty_qty_real(BigDecimal xbqty_qty_real) {
/* 45 */     this.xbqty_qty_real = xbqty_qty_real;
/*    */   }
/*    */   public BigDecimal getXbqty_qty_arr() {
/* 48 */     return this.xbqty_qty_arr;
/*    */   }
/*    */   public void setXbqty_qty_arr(BigDecimal xbqty_qty_arr) {
/* 51 */     this.xbqty_qty_arr = xbqty_qty_arr;
/*    */   }
/*    */   public String getXbqty_rev01() {
/* 54 */     return this.xbqty_rev01;
/*    */   }
/*    */   public void setXbqty_rev01(String xbqty_rev01) {
/* 57 */     this.xbqty_rev01 = xbqty_rev01;
/*    */   }
/*    */   public String getXbqty_rev02() {
/* 60 */     return this.xbqty_rev02;
/*    */   }
/*    */   public void setXbqty_rev02(String xbqty_rev02) {
/* 63 */     this.xbqty_rev02 = xbqty_rev02;
/*    */   }
/*    */   public Date getXbqty_date_real() {
/* 66 */     return this.xbqty_date_real;
/*    */   }
/*    */   public void setXbqty_date_real(Date xbqty_date_real) {
/* 69 */     this.xbqty_date_real = xbqty_date_real;
/*    */   }
/*    */   public Date getXbqty_date_arr() {
/* 72 */     return this.xbqty_date_arr;
/*    */   }
/*    */   public void setXbqty_date_arr(Date xbqty_date_arr) {
/* 75 */     this.xbqty_date_arr = xbqty_date_arr;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractXbqtyDet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */