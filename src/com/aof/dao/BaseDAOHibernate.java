/*     */ package com.aof.dao;
/*     */ 
/*     */ import com.aof.dao.convert.QueryParameterConvert;
/*     */ import com.aof.dao.convert.QuerySQLConvert;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.query.BasicConditionModel;
/*     */ import com.aof.model.query.BasicConditionType;
/*     */ import com.aof.model.query.BasicQueryCondition;
/*     */ import com.shcnc.hibernate.CompositeQuery;
/*     */ import com.shcnc.hibernate.QueryCondition;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.HibernateException;
/*     */ import net.sf.hibernate.Query;
/*     */ import net.sf.hibernate.Session;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.springframework.dao.DataAccessResourceFailureException;
/*     */ import org.springframework.orm.hibernate.HibernateCallback;
/*     */ import org.springframework.orm.hibernate.support.HibernateDaoSupport;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BaseDAOHibernate
/*     */   extends HibernateDaoSupport
/*     */   implements DAO
/*     */ {
/*  51 */   private Log log = LogFactory.getLog(BaseDAOHibernate.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object refreshObject(Object o) {
/*  57 */     if (o == null) {
/*  58 */       this.log.info("Try to refresh a null object");
/*     */     }
/*  60 */     getHibernateTemplate().refresh(o);
/*  61 */     return o;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveObject(Object o) {
/*  68 */     getHibernateTemplate().save(o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateObject(Object o) {
/*  77 */     getHibernateTemplate().update(o);
/*  78 */     getHibernateTemplate().flush();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeObject(Object o) {
/*  84 */     getHibernateTemplate().delete(o);
/*  85 */     getHibernateTemplate().flush();
/*     */   }
/*     */   
/*     */   protected int getListCount(final Map conditions, final String sql, final Object[][] queryConditions) {
/*  89 */     Integer result = (Integer)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/*  92 */             CompositeQuery query = new CompositeQuery(sql, "", BaseDAOHibernate.this.getSession());
/*  93 */             if (conditions != null) BaseDAOHibernate.appendConditions(query, conditions, queryConditions); 
/*  94 */             List<Integer> result = query.list();
/*  95 */             if (!result.isEmpty()) {
/*  96 */               Integer count = result.get(0);
/*  97 */               if (count != null) return count; 
/*     */             } 
/*  99 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 103 */     if (result == null) return 0; 
/* 104 */     return result.intValue();
/*     */   }
/*     */   
/*     */   protected BigDecimal getListSumByBigDecimal(final Map conditions, final String sql, final Object[][] queryConditions) {
/* 108 */     BigDecimal result = (BigDecimal)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 111 */             CompositeQuery query = new CompositeQuery(sql, "", BaseDAOHibernate.this.getSession());
/* 112 */             if (conditions != null) BaseDAOHibernate.appendConditions(query, conditions, queryConditions); 
/* 113 */             List<BigDecimal> result = query.list();
/* 114 */             if (!result.isEmpty()) {
/* 115 */               BigDecimal count = result.get(0);
/* 116 */               if (count != null) return count; 
/*     */             } 
/* 118 */             return null;
/*     */           }
/*     */         });
/*     */     
/* 122 */     if (result == null) return new BigDecimal(0); 
/* 123 */     return result;
/*     */   }
/*     */   
/*     */   protected int getListCount(final String sql, final String parma) {
/* 127 */     Integer result = (Integer)getHibernateTemplate().execute(new HibernateCallback() {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 129 */             Query query = session.createQuery(sql);
/* 130 */             query.setString(0, parma);
/* 131 */             List<Integer> result = query.list();
/*     */             try {
/* 133 */               if (!result.isEmpty()) {
/* 134 */                 Integer count = result.get(0);
/* 135 */                 if (count != null)
/* 136 */                   return count; 
/*     */               } 
/* 138 */             } catch (Exception e) {
/* 139 */               e.getStackTrace();
/*     */             } finally {
/* 141 */               if (session != null && session.isOpen()) {
/* 142 */                 session.close();
/*     */               }
/*     */             } 
/* 145 */             return null;
/*     */           }
/*     */         });
/* 148 */     if (result == null)
/* 149 */       return 0; 
/* 150 */     return result.intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected List getList(final Map conditions, final int pageNo, final int pageSize, final Object order, final boolean descend, final String sql, final Object[][] queryConditions, final Object[][] queryOrders) {
/* 155 */     return (List)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 158 */             CompositeQuery query = new CompositeQuery(sql, BaseDAOHibernate.getOrder(order, queryOrders, descend), session);
/* 159 */             if (conditions != null) BaseDAOHibernate.appendConditions(query, conditions, queryConditions); 
/* 160 */             if (pageSize == -1) return query.list(); 
/* 161 */             return query.list(pageNo * pageSize, pageSize);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   protected static String getOrder(Object order, Object[][] orders, boolean descend) {
/* 167 */     if (order != null) {
/* 168 */       for (int i = 0; i < orders.length; i++) {
/* 169 */         if (orders[i][0].equals(order)) {
/* 170 */           if (descend) {
/* 171 */             return String.valueOf(orders[i][1].toString()) + " desc";
/*     */           }
/* 173 */           return orders[i][1].toString();
/*     */         } 
/*     */       } 
/*     */       try {
/* 177 */         String cName = order.toString();
/* 178 */         String name = cName.substring(cName.indexOf("[") + 1, cName.indexOf("]"));
/* 179 */         if (name.indexOf("basic_") != -1) {
/* 180 */           if (descend) {
/* 181 */             return String.valueOf(name.replaceAll("basic_", "")) + " desc";
/*     */           }
/* 183 */           return name.replaceAll("basic_", "");
/*     */         } 
/* 185 */       } catch (Exception e) {
/* 186 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/*     */     
/* 190 */     return "";
/*     */   }
/*     */   
/*     */   protected static void appendConditions(CompositeQuery query, Map condition, Object[][] conditions) {
/* 194 */     for (int i = 0; i < conditions.length; i++) {
/* 195 */       if (condition.containsKey(conditions[i][0])) {
/* 196 */         Object value = condition.get(conditions[i][0]);
/* 197 */         if (conditions[i][2] instanceof QueryParameterConvert) {
/* 198 */           makeSimpleCondition(query, conditions[i][1].toString(), value, (QueryParameterConvert)conditions[i][2]);
/* 199 */         } else if (conditions[i][2] instanceof QuerySQLConvert) {
/* 200 */           makeSimpleCondition(query, conditions[i][1].toString(), value, (QuerySQLConvert)conditions[i][2]);
/*     */         } else {
/* 202 */           makeSimpleCondition(query, conditions[i][1].toString(), value);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 208 */     if (condition.get(BasicQueryCondition.BASIC_QUERY_CONDITION) != null) {
/*     */       
/* 210 */       List<BasicConditionModel> modelList = (List<BasicConditionModel>)condition.get(BasicQueryCondition.BASIC_QUERY_CONDITION);
/* 211 */       for (BasicConditionModel model : modelList) {
/* 212 */         if (model.getType().equals(BasicConditionType.IN) || model.getType().equals(BasicConditionType.NOT_IN)) {
/* 213 */           String[] ids = null;
/* 214 */           if (model.getValue().toString().indexOf(",") != -1) {
/* 215 */             ids = model.getValue().toString().split(",");
/* 216 */           } else if (model.getValue().toString().indexOf("��") != -1) {
/* 217 */             ids = model.getValue().toString().split("��");
/*     */           } 
/* 219 */           if (ids != null)
/* 220 */             makeSimpleCondition(query, String.valueOf(model.getField()) + " " + model.getType().getTypeSql(), ids, (QuerySQLConvert)model.getType().getConvert()); 
/*     */           continue;
/*     */         } 
/* 223 */         String sql = String.valueOf(model.getField()) + " " + model.getType().getTypeSql();
/* 224 */         if (model.getType().getConvert() != null) {
/* 225 */           makeSimpleCondition(query, sql, model.getValue(), (QueryParameterConvert)model.getType().getConvert());
/*     */           continue;
/*     */         } 
/* 228 */         makeSimpleCondition(query, sql, model.getValue());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void makeSimpleCondition(CompositeQuery query, String sql, Object parameter) {
/* 243 */     QueryCondition qc = query.createQueryCondition(sql);
/*     */     
/* 245 */     if (parameter != null) {
/* 246 */       if (parameter instanceof Object[]) {
/* 247 */         Object[] parameters = (Object[])parameter;
/* 248 */         for (int i = 0; i < parameters.length; i++) {
/* 249 */           qc.appendParameter(parameters[i]);
/*     */         }
/*     */       } else {
/* 252 */         qc.appendParameter(parameter);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected static void makeSimpleCondition(CompositeQuery query, String sql, Object parameter, QueryParameterConvert converter) {
/* 258 */     QueryCondition qc = query.createQueryCondition(sql);
/* 259 */     Object finalParameter = (converter == null) ? parameter : converter.convert(parameter);
/* 260 */     if (finalParameter != null) {
/* 261 */       if (finalParameter instanceof Object[]) {
/* 262 */         Object[] parameters = (Object[])finalParameter;
/* 263 */         for (int i = 0; i < parameters.length; i++) {
/* 264 */           qc.appendParameter(parameters[i]);
/*     */         }
/*     */       } else {
/* 267 */         qc.appendParameter(finalParameter);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected static void makeSimpleCondition(CompositeQuery query, String sql, Object parameter, QuerySQLConvert converter) {
/* 273 */     StringBuffer sb = new StringBuffer(sql);
/* 274 */     Object finalParameter = (converter == null) ? parameter : converter.convert(sb, parameter);
/* 275 */     QueryCondition qc = query.createQueryCondition(sb.toString());
/* 276 */     if (finalParameter != null) {
/* 277 */       if (finalParameter instanceof Object[]) {
/* 278 */         Object[] parameters = (Object[])finalParameter;
/* 279 */         for (int i = 0; i < parameters.length; i++) {
/* 280 */           qc.appendParameter(parameters[i]);
/*     */         }
/*     */       } else {
/* 283 */         qc.appendParameter(finalParameter);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected static InputStream preRead(InputStream is) throws IOException {
/* 289 */     if (is instanceof ByteArrayInputStream) {
/* 290 */       return is;
/*     */     }
/* 292 */     byte[] buffer = new byte[1024];
/* 293 */     ByteArrayOutputStream os = new ByteArrayOutputStream();
/*     */     while (true) {
/* 295 */       int s = is.read(buffer);
/* 296 */       if (s > 0) {
/* 297 */         os.write(buffer, 0, s);
/*     */         continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 302 */     is.close();
/* 303 */     return new ByteArrayInputStream(os.toByteArray());
/*     */   }
/*     */   
/*     */   protected long getTodayTimeMillis() {
/* 307 */     Calendar c1 = Calendar.getInstance();
/* 308 */     Calendar c2 = Calendar.getInstance();
/* 309 */     c1.clear();
/* 310 */     c1.set(1, c2.get(1));
/* 311 */     c1.set(2, c2.get(2));
/* 312 */     c1.set(5, c2.get(5));
/* 313 */     return c1.getTimeInMillis();
/*     */   }
/*     */   public List getObjectList(String sql) {
/* 316 */     return getHibernateTemplate().find(sql);
/*     */   }
/*     */   public List<Box> getObjectListBox(String sql) {
/* 319 */     return getHibernateTemplate().find(sql);
/*     */   }
/*     */   public Object getObject(Class object, Serializable id) {
/* 322 */     return getHibernateTemplate().get(object, id);
/*     */   }
/*     */ 
/*     */   
/*     */   public void businessCommit() {
/*     */     try {
/* 328 */       getSession().beginTransaction().commit();
/* 329 */     } catch (HibernateException e) {
/*     */       
/* 331 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void commit() {
/*     */     try {
/* 338 */       getSession().connection().commit();
/* 339 */     } catch (Exception e) {
/* 340 */       e.fillInStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/BaseDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */