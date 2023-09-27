/*    */ package com.aof.dao.po.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.convert.QuerySQLConvert;
/*    */ import com.aof.dao.po.PurchaseManualCreateBarcodeDao;
/*    */ import com.aof.model.po.PurchaseManualCreateBarcode;
/*    */ import com.aof.model.po.query.PurchaseManualCreateBarcodeQueryCondition;
/*    */ import com.aof.model.po.query.PurchaseManualCreateBarcodeQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class PurchaseManualCreateBarcodeDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements PurchaseManualCreateBarcodeDao {
/* 17 */   private Log log = LogFactory.getLog(PurchaseManualCreateBarcodeDAOHibernate.class);
/*    */   private static final String SQL_COUNT = "select count(*) from PurchaseManualCreateBarcode entity";
/*    */   private static final String SQL = "from PurchaseManualCreateBarcode entity";
/*    */   
/* 21 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 22 */       { PurchaseManualCreateBarcodeQueryCondition.ID_IN, " entity.id  in  ", new QuerySQLConvert() {
/*    */           public Object convert(StringBuffer sql, Object parameter) {
/* 24 */             if (parameter != null && parameter instanceof Object[]) {
/* 25 */               Object[] finalParameter = (Object[])parameter;
/* 26 */               if (finalParameter.length > 0) {
/* 27 */                 sql.append("(?");
/* 28 */                 for (int i = 1; i < finalParameter.length; i++) {
/* 29 */                   sql.append(",?");
/*    */                 }
/* 31 */                 sql.append(")");
/*    */               } else {
/* 33 */                 return finalParameter;
/*    */               } 
/*    */             } else {
/* 36 */               return parameter;
/*    */             } 
/* 38 */             return parameter;
/*    */           }
/*    */         } }
/*    */     };
/*    */   
/* 43 */   private static final Object[][] QUERY_ORDERS = new Object[0][];
/*    */ 
/*    */ 
/*    */   
/*    */   public PurchaseManualCreateBarcode getPurchaseManualCreateBarcode(Integer id) {
/* 48 */     if (id == null) {
/* 49 */       if (this.log.isDebugEnabled())
/* 50 */         this.log.debug("try to get Purchasemanualcreatebarcode with null id"); 
/* 51 */       return null;
/*    */     } 
/* 53 */     return (PurchaseManualCreateBarcode)getHibernateTemplate().get(PurchaseManualCreateBarcode.class, id);
/*    */   }
/*    */   
/*    */   public List list(Map condtions, int pageNo, int pageSize, PurchaseManualCreateBarcodeQueryOrder order, boolean descend) {
/* 57 */     return getList(condtions, pageNo, pageSize, order, descend, "from PurchaseManualCreateBarcode entity", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */   
/*    */   public void update(PurchaseManualCreateBarcode u) {
/* 61 */     getHibernateTemplate().update(u);
/*    */   }
/*    */   
/*    */   public void delete(PurchaseManualCreateBarcode u) {
/* 65 */     getHibernateTemplate().delete(u);
/*    */   }
/*    */   
/*    */   public PurchaseManualCreateBarcode insert(PurchaseManualCreateBarcode u) {
/* 69 */     getHibernateTemplate().save(u);
/* 70 */     return u;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getListCount(Map conditions) {
/* 75 */     return getListCount(conditions, "select count(*) from PurchaseManualCreateBarcode entity", QUERY_CONDITIONS);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/hibernate/PurchaseManualCreateBarcodeDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */