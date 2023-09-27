/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Department
/*    */   extends AbstractDepartment
/*    */   implements Serializable
/*    */ {
/*    */   private List children;
/*    */   private String indentName;
/*    */   
/*    */   public Department() {}
/*    */   
/*    */   public Department(Integer id) {
/* 34 */     super(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getChildren() {
/* 42 */     return this.children;
/*    */   }
/*    */   
/*    */   public void addChild(Department d) {
/* 46 */     if (this.children == null)
/* 47 */       this.children = new ArrayList(); 
/* 48 */     this.children.add(d);
/*    */   }
/*    */   
/*    */   public String getIndentName() {
/* 52 */     return this.indentName;
/*    */   }
/*    */   
/*    */   public void setIndentName(String indentName) {
/* 56 */     this.indentName = indentName;
/*    */   }
/*    */   
/*    */   private boolean granted = false;
/*    */   
/*    */   public boolean isGranted() {
/* 62 */     return this.granted;
/*    */   }
/*    */   
/*    */   public void setGranted(boolean granted) {
/* 66 */     this.granted = granted;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/Department.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */