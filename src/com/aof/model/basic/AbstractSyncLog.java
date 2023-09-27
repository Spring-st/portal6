/*    */ package com.aof.model.basic;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public abstract class AbstractSyncLog implements Serializable {
/*  7 */   private int hashValue = 0;
/*    */   private Integer id;
/*    */   private Date sync_date;
/*    */   private String sync_object;
/*    */   private String sync_content;
/*    */   private String sync_results;
/*    */   private String sync_describe;
/*    */   
/*    */   public Integer getId() {
/* 16 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Integer id) {
/* 20 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractSyncLog() {}
/*    */   
/*    */   public AbstractSyncLog(Integer id) {
/* 27 */     setId(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object rhs) {
/* 32 */     if (rhs == null)
/* 33 */       return false; 
/* 34 */     if (this == rhs)
/* 35 */       return true; 
/* 36 */     if (!(rhs instanceof SyncLog))
/* 37 */       return false; 
/* 38 */     SyncLog that = (SyncLog)rhs;
/* 39 */     if (getId() != null)
/* 40 */       return getId().equals(that.getId()); 
/* 41 */     return (that.getId() == null);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 46 */     if (this.hashValue == 0) {
/* 47 */       int result = 17;
/* 48 */       int purCateIdValue = (getId() == null) ? 0 : getId()
/* 49 */         .hashCode();
/* 50 */       result = result * 37 + purCateIdValue;
/* 51 */       this.hashValue = result;
/*    */     } 
/* 53 */     return this.hashValue;
/*    */   }
/*    */   
/*    */   public Date getSync_date() {
/* 57 */     return this.sync_date;
/*    */   }
/*    */   
/*    */   public void setSync_date(Date syncDate) {
/* 61 */     this.sync_date = syncDate;
/*    */   }
/*    */   
/*    */   public String getSync_object() {
/* 65 */     return this.sync_object;
/*    */   }
/*    */   
/*    */   public void setSync_object(String syncObject) {
/* 69 */     this.sync_object = syncObject;
/*    */   }
/*    */   
/*    */   public String getSync_content() {
/* 73 */     return this.sync_content;
/*    */   }
/*    */   
/*    */   public void setSync_content(String syncContent) {
/* 77 */     this.sync_content = syncContent;
/*    */   }
/*    */   
/*    */   public String getSync_results() {
/* 81 */     return this.sync_results;
/*    */   }
/*    */   
/*    */   public void setSync_results(String syncResults) {
/* 85 */     this.sync_results = syncResults;
/*    */   }
/*    */   
/*    */   public String getSync_describe() {
/* 89 */     return this.sync_describe;
/*    */   }
/*    */   
/*    */   public void setSync_describe(String syncDescribe) {
/* 93 */     this.sync_describe = syncDescribe;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/basic/AbstractSyncLog.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */