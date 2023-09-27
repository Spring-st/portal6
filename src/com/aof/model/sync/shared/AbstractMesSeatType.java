/*    */ package com.aof.model.sync.shared;
/*    */ 
/*    */ 
/*    */ public class AbstractMesSeatType
/*    */ {
/*    */   private Integer id;
/*    */   private String alc;
/*    */   private String des;
/*    */   private String line;
/*    */   
/*    */   public AbstractMesSeatType() {}
/*    */   
/*    */   public AbstractMesSeatType(Integer id) {
/* 14 */     this.id = id;
/*    */   }
/*    */   public Integer getId() {
/* 17 */     return this.id;
/*    */   }
/*    */   public void setId(Integer id) {
/* 20 */     this.id = id;
/*    */   }
/*    */   public String getAlc() {
/* 23 */     return this.alc;
/*    */   }
/*    */   public void setAlc(String alc) {
/* 26 */     this.alc = alc;
/*    */   }
/*    */   public String getDes() {
/* 29 */     return this.des;
/*    */   }
/*    */   public void setDes(String des) {
/* 32 */     this.des = des;
/*    */   }
/*    */   public String getLine() {
/* 35 */     return this.line;
/*    */   }
/*    */   public void setLine(String line) {
/* 38 */     this.line = line;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/sync/shared/AbstractMesSeatType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */