/*    */ package com.aof.model.query;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class AutoArrayList
/*    */   extends ArrayList {
/*    */   private Class itemClass;
/*    */   
/*    */   public AutoArrayList(Class itemClass) {
/* 10 */     this.itemClass = itemClass;
/*    */   }
/*    */   
/*    */   public Object get(int index) {
/*    */     try {
/* 15 */       while (index >= size()) {
/* 16 */         add(this.itemClass.newInstance());
/*    */       }
/* 18 */     } catch (Exception e) {
/* 19 */       e.printStackTrace();
/*    */     } 
/* 21 */     return super.get(index);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object set(int index, Object element) {
/*    */     try {
/* 28 */       while (index >= size()) {
/* 29 */         add(this.itemClass.newInstance());
/*    */       }
/* 31 */       super.set(index, element);
/* 32 */     } catch (Exception e) {
/* 33 */       e.printStackTrace();
/*    */     } 
/* 35 */     return super.get(index);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/query/AutoArrayList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */