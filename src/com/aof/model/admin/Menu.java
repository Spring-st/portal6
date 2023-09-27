/*    */ package com.aof.model.admin;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Comparator;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Menu
/*    */   extends AbstractMenu
/*    */   implements Serializable
/*    */ {
/*    */   public Menu() {}
/*    */   
/*    */   public Menu(Integer id) {
/* 36 */     super(id);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public static final Comparator MENU_COMPARATOR = new Comparator()
/*    */     {
/*    */       public int compare(Object o1, Object o2) {
/* 47 */         if (!(o1 instanceof Menu) || !(o2 instanceof Menu)) return 0; 
/* 48 */         Menu m1 = (Menu)o1;
/* 49 */         Menu m2 = (Menu)o2;
/* 50 */         if (m1 == null) return -1; 
/* 51 */         if (m2 == null) return 1; 
/* 52 */         return m1.getId().compareTo(m2.getId());
/*    */       }
/*    */     };
/*    */ 
/*    */   
/*    */   private Set children;
/*    */   
/*    */   public Set getChildren() {
/* 60 */     return this.children;
/*    */   }
/*    */   
/*    */   public void addChild(Menu m) {
/* 64 */     if (this.children == null) this.children = new TreeSet(MENU_COMPARATOR); 
/* 65 */     this.children.add(m);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/admin/Menu.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */