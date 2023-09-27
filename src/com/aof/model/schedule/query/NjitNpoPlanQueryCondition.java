/*    */ package com.aof.model.schedule.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class NjitNpoPlanQueryCondition
/*    */   extends Enum {
/*  7 */   public static final NjitNpoPlanQueryCondition ID_EQ = new NjitNpoPlanQueryCondition("id_eq");
/*  8 */   public static final NjitNpoPlanQueryCondition PARTID_VEND_EQ = new NjitNpoPlanQueryCondition("partid_vend_eq");
/*    */   
/*    */   protected NjitNpoPlanQueryCondition(String name) {
/* 11 */     super(name);
/*    */   }
/*    */   
/*    */   public static NjitNpoPlanQueryCondition getEnum(String value) {
/* 15 */     return (NjitNpoPlanQueryCondition)getEnum(NjitNpoPlanQueryCondition.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/schedule/query/NjitNpoPlanQueryCondition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */