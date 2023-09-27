/*    */ package com.aof.model.po.query;
/*    */ 
/*    */ import org.apache.commons.lang.enums.Enum;
/*    */ 
/*    */ public class ProduceRejectedMaterialQueryOrder
/*    */   extends Enum {
/*  7 */   public static final ProduceRejectedMaterialQueryOrder ID = new ProduceRejectedMaterialQueryOrder("id");
/*  8 */   public static final ProduceRejectedMaterialQueryOrder ENGNAME = new ProduceRejectedMaterialQueryOrder("engName");
/*  9 */   public static final ProduceRejectedMaterialQueryOrder CHNNAME = new ProduceRejectedMaterialQueryOrder("chnName");
/* 10 */   public static final ProduceRejectedMaterialQueryOrder ENABLED = new ProduceRejectedMaterialQueryOrder("enabled");
/*    */   
/*    */   protected ProduceRejectedMaterialQueryOrder(String value) {
/* 13 */     super(value);
/*    */   }
/*    */   
/*    */   public static ProduceRejectedMaterialQueryOrder getEnum(String value) {
/* 17 */     return (ProduceRejectedMaterialQueryOrder)getEnum(ProduceRejectedMaterialQueryOrder.class, value);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/po/query/ProduceRejectedMaterialQueryOrder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */