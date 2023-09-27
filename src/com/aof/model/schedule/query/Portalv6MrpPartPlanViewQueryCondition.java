/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class Portalv6MrpPartPlanViewQueryCondition
/*    */   extends Enum {
/*  7 */   public static final Portalv6MrpPartPlanViewQueryCondition PARTID_EQ = new Portalv6MrpPartPlanViewQueryCondition("partId");
/*  8 */   public static final Portalv6MrpPartPlanViewQueryCondition NAME = new Portalv6MrpPartPlanViewQueryCondition("name");
/*  9 */   public static final Portalv6MrpPartPlanViewQueryCondition CURRENTQTY = new Portalv6MrpPartPlanViewQueryCondition("current_qty");
/* 10 */   public static final Portalv6MrpPartPlanViewQueryCondition B = new Portalv6MrpPartPlanViewQueryCondition("b");
/* 11 */   public static final Portalv6MrpPartPlanViewQueryCondition G = new Portalv6MrpPartPlanViewQueryCondition("g");
/* 12 */   public static final Portalv6MrpPartPlanViewQueryCondition H = new Portalv6MrpPartPlanViewQueryCondition("h");
/* 13 */   public static final Portalv6MrpPartPlanViewQueryCondition F = new Portalv6MrpPartPlanViewQueryCondition("f");
/* 14 */   public static final Portalv6MrpPartPlanViewQueryCondition E = new Portalv6MrpPartPlanViewQueryCondition("e");
/*    */ 
/*    */ 
/*    */   
/*    */   protected Portalv6MrpPartPlanViewQueryCondition(String name) {
/* 19 */     super(name);
/*    */   }
/*    */   
/*    */   public static Portalv6MrpPartPlanViewQueryCondition getEnum(String value) {
/* 23 */     return (Portalv6MrpPartPlanViewQueryCondition)getEnum(Portalv6MrpPartPlanViewQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/Portalv6MrpPartPlanViewQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */