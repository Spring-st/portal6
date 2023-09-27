/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Comparator;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import org.apache.struts.action.ActionMapping;
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
/*    */ public class Function
/*    */   extends AbstractFunction
/*    */   implements Serializable
/*    */ {
/*    */   public Function() {}
/*    */   
/*    */   public Function(Integer id) {
/* 29 */     super(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public static final Comparator ACTIONMAPPING_COMPARATOR = new Comparator()
/*    */     {
/*    */       public int compare(Object o1, Object o2) {
/* 40 */         if (!(o1 instanceof ActionMapping) || !(o2 instanceof ActionMapping)) return 0; 
/* 41 */         ActionMapping a1 = (ActionMapping)o1;
/* 42 */         ActionMapping a2 = (ActionMapping)o2;
/* 43 */         if (a1 == null) return -1; 
/* 44 */         if (a2 == null) return 1; 
/* 45 */         return a1.getPath().compareTo(a2.getPath());
/*    */       }
/*    */     };
/*    */   
/*    */   private Set actionMappings;
/*    */   private String level;
/*    */   
/*    */   public Set getActionMappings() {
/* 53 */     return this.actionMappings;
/*    */   }
/*    */   private static final String GLOBAL = "g"; private static final String SITE = "s"; private static final String DEPARTMENT = "d";
/*    */   public void setActionMappings(Set actionMappings) {
/* 57 */     this.actionMappings = actionMappings;
/*    */   }
/*    */   
/*    */   public void addActionMapping(ActionMapping mapping) {
/* 61 */     if (this.actionMappings == null) this.actionMappings = new TreeSet(ACTIONMAPPING_COMPARATOR); 
/* 62 */     this.actionMappings.add(mapping);
/*    */   }
/*    */   
/*    */   public String getLevel() {
/* 66 */     return this.level;
/*    */   }
/*    */   
/*    */   public void setLevel(String level) {
/* 70 */     this.level = level;
/*    */   }
/*    */   
/*    */   public boolean isGlobal() {
/* 74 */     return hasOnlyLevel("g");
/*    */   }
/*    */   
/*    */   public boolean isSite() {
/* 78 */     return hasOnlyLevel("s");
/*    */   }
/*    */   
/*    */   public boolean isDepartment() {
/* 82 */     return hasOnlyLevel("d");
/*    */   }
/*    */   
/*    */   private boolean hasOnlyLevel(String s) {
/* 86 */     return getLevel().equals(s);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/Function.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */