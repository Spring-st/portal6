/*    */ package com.aof.model.sync.shared;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbstractProductOutGoline
/*    */   implements Serializable
/*    */ {
/*    */   private Integer xxsh_worc_number;
/*    */   private String xxsh_worc_item;
/*    */   private String xxsh_worc_qty;
/*    */   private Date xxsh_worc_date;
/*    */   private String xxsh_worc_loc;
/*    */   private Date xxsh_worc_cr_date;
/*    */   private Date xxsh_worc_up_date;
/*    */   private String xxsh_worc_status;
/*    */   private String xxsh_worc_rmks;
/*    */   private String xxsh_worc_rmks1;
/*    */   private String xxsh_worc_rmks2;
/*    */   
/*    */   public AbstractProductOutGoline() {}
/*    */   
/*    */   public AbstractProductOutGoline(Integer id) {
/* 27 */     setXxsh_worc_number(id);
/*    */   }
/*    */   
/*    */   public Integer getXxsh_worc_number() {
/* 31 */     return this.xxsh_worc_number;
/*    */   }
/*    */   public void setXxsh_worc_number(Integer xxsh_worc_number) {
/* 34 */     this.xxsh_worc_number = xxsh_worc_number;
/*    */   }
/*    */   public String getXxsh_worc_item() {
/* 37 */     return this.xxsh_worc_item;
/*    */   }
/*    */   public void setXxsh_worc_item(String xxsh_worc_item) {
/* 40 */     this.xxsh_worc_item = xxsh_worc_item;
/*    */   }
/*    */   public String getXxsh_worc_qty() {
/* 43 */     return this.xxsh_worc_qty;
/*    */   }
/*    */   public void setXxsh_worc_qty(String xxsh_worc_qty) {
/* 46 */     this.xxsh_worc_qty = xxsh_worc_qty;
/*    */   }
/*    */   public Date getXxsh_worc_date() {
/* 49 */     return this.xxsh_worc_date;
/*    */   }
/*    */   public void setXxsh_worc_date(Date xxsh_worc_date) {
/* 52 */     this.xxsh_worc_date = xxsh_worc_date;
/*    */   }
/*    */   public String getXxsh_worc_loc() {
/* 55 */     return this.xxsh_worc_loc;
/*    */   }
/*    */   public void setXxsh_worc_loc(String xxsh_worc_loc) {
/* 58 */     this.xxsh_worc_loc = xxsh_worc_loc;
/*    */   }
/*    */   public Date getXxsh_worc_cr_date() {
/* 61 */     return this.xxsh_worc_cr_date;
/*    */   }
/*    */   public void setXxsh_worc_cr_date(Date xxsh_worc_cr_date) {
/* 64 */     this.xxsh_worc_cr_date = xxsh_worc_cr_date;
/*    */   }
/*    */   public Date getXxsh_worc_up_date() {
/* 67 */     return this.xxsh_worc_up_date;
/*    */   }
/*    */   public void setXxsh_worc_up_date(Date xxsh_worc_up_date) {
/* 70 */     this.xxsh_worc_up_date = xxsh_worc_up_date;
/*    */   }
/*    */   public String getXxsh_worc_status() {
/* 73 */     return this.xxsh_worc_status;
/*    */   }
/*    */   public void setXxsh_worc_status(String xxsh_worc_status) {
/* 76 */     this.xxsh_worc_status = xxsh_worc_status;
/*    */   }
/*    */   public String getXxsh_worc_rmks() {
/* 79 */     return this.xxsh_worc_rmks;
/*    */   }
/*    */   public void setXxsh_worc_rmks(String xxsh_worc_rmks) {
/* 82 */     this.xxsh_worc_rmks = xxsh_worc_rmks;
/*    */   }
/*    */   public String getXxsh_worc_rmks1() {
/* 85 */     return this.xxsh_worc_rmks1;
/*    */   }
/*    */   public void setXxsh_worc_rmks1(String xxsh_worc_rmks1) {
/* 88 */     this.xxsh_worc_rmks1 = xxsh_worc_rmks1;
/*    */   }
/*    */   public String getXxsh_worc_rmks2() {
/* 91 */     return this.xxsh_worc_rmks2;
/*    */   }
/*    */   public void setXxsh_worc_rmks2(String xxsh_worc_rmks2) {
/* 94 */     this.xxsh_worc_rmks2 = xxsh_worc_rmks2;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractProductOutGoline.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */