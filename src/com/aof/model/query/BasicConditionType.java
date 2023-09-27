/*     */ package com.aof.model.query;
/*     */ 
/*     */ import com.aof.dao.convert.QueryParameterConvert;
/*     */ import com.aof.dao.convert.QuerySQLConvert;
/*     */ import org.apache.commons.lang.enums.Enum;
/*     */ 
/*     */ public class BasicConditionType
/*     */   extends Enum
/*     */ {
/*  10 */   public static final BasicConditionType EQ = new BasicConditionType(
/*  11 */       "eq");
/*  12 */   public static final BasicConditionType NOT_EQ = new BasicConditionType(
/*  13 */       "not_eq");
/*  14 */   public static final BasicConditionType BEGIN_LIKE = new BasicConditionType(
/*  15 */       "begin_like");
/*  16 */   public static final BasicConditionType END_LIKE = new BasicConditionType(
/*  17 */       "end_like");
/*  18 */   public static final BasicConditionType INCLUDE_LIKE = new BasicConditionType(
/*  19 */       "include_like");
/*  20 */   public static final BasicConditionType NOT_INCLUDE_LIKE = new BasicConditionType(
/*  21 */       "not_include_like");
/*  22 */   public static final BasicConditionType IN = new BasicConditionType(
/*  23 */       "in");
/*  24 */   public static final BasicConditionType NOT_IN = new BasicConditionType(
/*  25 */       "not_in");
/*     */   
/*  27 */   public static final BasicConditionType GT = new BasicConditionType(
/*  28 */       "gt");
/*  29 */   public static final BasicConditionType LT = new BasicConditionType(
/*  30 */       "lt");
/*  31 */   public static final BasicConditionType GTE = new BasicConditionType(
/*  32 */       "gte"); private String typeSql;
/*  33 */   public static final BasicConditionType LTE = new BasicConditionType(
/*  34 */       "lte"); private Object convert;
/*     */   protected BasicConditionType(String name) {
/*  36 */     super(name);
/*     */   }
/*     */   public static BasicConditionType getEnum(String value) {
/*  39 */     return (BasicConditionType)getEnum(BasicConditionType.class, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTypeSql() {
/*  44 */     return this.typeSql;
/*     */   }
/*     */   
/*     */   public void setTypeSql(String typeSql) {
/*  48 */     this.typeSql = typeSql;
/*     */   }
/*     */   
/*     */   public Object getConvert() {
/*  52 */     return this.convert;
/*     */   }
/*     */   public void setConvert(Object convert) {
/*  55 */     this.convert = convert;
/*     */   }
/*     */   
/*     */   static {
/*  59 */     EQ.typeSql = "=?";
/*  60 */     NOT_EQ.typeSql = "!=?";
/*  61 */     BEGIN_LIKE.typeSql = ">= ?";
/*  62 */     END_LIKE.typeSql = "<= ?";
/*  63 */     INCLUDE_LIKE.typeSql = "like ?";
/*  64 */     NOT_INCLUDE_LIKE.typeSql = "not like ?";
/*  65 */     IN.typeSql = "in ";
/*  66 */     NOT_IN.typeSql = "not in ";
/*  67 */     GT.typeSql = ">?";
/*  68 */     GTE.typeSql = ">=?";
/*  69 */     LT.typeSql = "<?";
/*  70 */     LTE.typeSql = "<=?";
/*     */ 
/*     */ 
/*     */     
/*  74 */     INCLUDE_LIKE.convert = new QueryParameterConvert() { public Object convert(Object src) { return "%" + src + "%"; } };
/*  75 */     NOT_INCLUDE_LIKE.convert = new QueryParameterConvert() { public Object convert(Object src) { return "%" + src + "%"; } };
/*  76 */     IN.convert = new QuerySQLConvert() {
/*     */         public Object convert(StringBuffer sql, Object parameter) {
/*  78 */           if (parameter != null && parameter instanceof Object[]) {
/*  79 */             Object[] finalParameter = (Object[])parameter;
/*  80 */             if (finalParameter.length > 0) {
/*  81 */               sql.append("(?");
/*  82 */               for (int i = 1; i < finalParameter.length; i++) {
/*  83 */                 sql.append(",?");
/*     */               }
/*  85 */               sql.append(")");
/*     */             } else {
/*  87 */               return finalParameter;
/*     */             } 
/*     */           } else {
/*  90 */             return parameter;
/*     */           } 
/*  92 */           return parameter;
/*     */         }
/*     */       };
/*  95 */     NOT_IN.convert = new QuerySQLConvert() {
/*     */         public Object convert(StringBuffer sql, Object parameter) {
/*  97 */           if (parameter != null && parameter instanceof Object[]) {
/*  98 */             Object[] finalParameter = (Object[])parameter;
/*  99 */             if (finalParameter.length > 0) {
/* 100 */               sql.append("(?");
/* 101 */               for (int i = 1; i < finalParameter.length; i++) {
/* 102 */                 sql.append(",?");
/*     */               }
/* 104 */               sql.append(")");
/*     */             } else {
/* 106 */               return finalParameter;
/*     */             } 
/*     */           } else {
/* 109 */             return parameter;
/*     */           } 
/* 111 */           return parameter;
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/query/BasicConditionType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */