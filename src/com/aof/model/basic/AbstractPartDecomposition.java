/*    */ package com.aof.model.basic;
/*    */ 
/*    */ import com.aof.model.admin.Site;
/*    */ import com.aof.model.admin.User;
/*    */ import com.aof.model.metadata.YesNo;
/*    */ import com.aof.model.po.Box;
/*    */ import java.io.Serializable;
/*    */ import java.math.BigDecimal;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class AbstractPartDecomposition
/*    */   implements Serializable {
/*    */   private Integer id;
/*    */   private Box box;
/*    */   private Date date;
/*    */   private String domain;
/*    */   private YesNo isPrint;
/*    */   private User operation;
/*    */   private WmsPart part;
/*    */   private BigDecimal qty;
/*    */   private Site site;
/*    */   private YesNo type;
/*    */   
/*    */   public Integer getId() {
/* 25 */     return this.id;
/*    */   }
/*    */   public void setId(Integer id) {
/* 28 */     this.id = id;
/*    */   }
/*    */   public Box getBox() {
/* 31 */     return this.box;
/*    */   }
/*    */   public void setBox(Box box) {
/* 34 */     this.box = box;
/*    */   }
/*    */   public Date getDate() {
/* 37 */     return this.date;
/*    */   }
/*    */   public void setDate(Date date) {
/* 40 */     this.date = date;
/*    */   }
/*    */   public String getDomain() {
/* 43 */     return this.domain;
/*    */   }
/*    */   public void setDomain(String domain) {
/* 46 */     this.domain = domain;
/*    */   }
/*    */   public YesNo getIsPrint() {
/* 49 */     return this.isPrint;
/*    */   }
/*    */   public void setIsPrint(YesNo isPrint) {
/* 52 */     this.isPrint = isPrint;
/*    */   }
/*    */   public User getOperation() {
/* 55 */     return this.operation;
/*    */   }
/*    */   public void setOperation(User operation) {
/* 58 */     this.operation = operation;
/*    */   }
/*    */   public WmsPart getPart() {
/* 61 */     return this.part;
/*    */   }
/*    */   public void setPart(WmsPart part) {
/* 64 */     this.part = part;
/*    */   }
/*    */   public BigDecimal getQty() {
/* 67 */     return this.qty;
/*    */   }
/*    */   public void setQty(BigDecimal qty) {
/* 70 */     this.qty = qty;
/*    */   }
/*    */   public Site getSite() {
/* 73 */     return this.site;
/*    */   }
/*    */   public void setSite(Site site) {
/* 76 */     this.site = site;
/*    */   }
/*    */   public YesNo getType() {
/* 79 */     return this.type;
/*    */   }
/*    */   public void setType(YesNo type) {
/* 82 */     this.type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractPartDecomposition() {}
/*    */   
/*    */   public AbstractPartDecomposition(Integer id) {
/* 89 */     this.id = id;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractPartDecomposition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */