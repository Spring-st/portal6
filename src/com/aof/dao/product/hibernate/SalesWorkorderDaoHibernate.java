/*     */ package com.aof.dao.product.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.convert.QuerySQLConvert;
/*     */ import com.aof.dao.product.SalesWorkorderDao;
/*     */ import com.aof.model.metadata.SalesPreshiporderBatchStatus;
/*     */ import com.aof.model.product.SalesPreshiporder;
/*     */ import com.aof.model.product.SalesWorkorder;
/*     */ import com.aof.model.product.query.SalesWorkorderQueryCondition;
/*     */ import com.aof.model.product.query.SalesWorkorderQueryOrder;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.type.Type;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SalesWorkorderDaoHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements SalesWorkorderDao
/*     */ {
/*  26 */   private Log log = LogFactory.getLog(SalesWorkorderDaoHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from SalesWorkorder salesWorkorder";
/*     */   
/*     */   private static final String SQL = "from SalesWorkorder salesWorkorder";
/*     */   
/*  32 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  33 */       { SalesWorkorderQueryCondition.ID_EQ, "salesWorkorder.id = ?"
/*  34 */       }, { SalesWorkorderQueryCondition.SHIP_ORDER_ID_EQ, "salesWorkorder.shipId.id = ?"
/*  35 */       }, { SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_EQ, "salesWorkorder.status1 = ?"
/*  36 */       }, { SalesWorkorderQueryCondition.SHIPWORKORDER_STATUS_GE, "salesWorkorder.status1 >= ?"
/*  37 */       }, { SalesWorkorderQueryCondition.CUSTOMERCODE_EQ, "salesWorkorder.shipId.customerCode = ?"
/*  38 */       }, { SalesWorkorderQueryCondition.SHIPITEM_ID_EQ, "salesWorkorder.shipItemId.id = ?"
/*  39 */       }, { SalesWorkorderQueryCondition.ID_IN, "salesWorkorder.id in ", new QuerySQLConvert() {
/*     */           public Object convert(StringBuffer sql, Object parameter) {
/*  41 */             if (parameter != null && parameter instanceof Object[]) {
/*  42 */               Object[] finalParameter = (Object[])parameter;
/*  43 */               if (finalParameter.length > 0) {
/*  44 */                 sql.append("(?");
/*  45 */                 for (int i = 1; i < finalParameter.length; i++) {
/*  46 */                   sql.append(",?");
/*     */                 }
/*  48 */                 sql.append(")");
/*     */               } else {
/*  50 */                 return finalParameter;
/*     */               } 
/*     */             } else {
/*  53 */               return parameter;
/*     */             } 
/*  55 */             return parameter;
/*     */           }
/*     */         } } };
/*     */   
/*  59 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  60 */       { SalesWorkorderQueryOrder.ID, "salesWorkorder.id"
/*  61 */       }, { SalesWorkorderQueryOrder.LOTSER_ID, "salesWorkorder.lotSer.id"
/*  62 */       }, { SalesWorkorderQueryOrder.SHIP_ID, "salesWorkorder.shipId.code"
/*  63 */       }, { SalesWorkorderQueryOrder.OUT_DATE, "salesWorkorder.outDate" }
/*     */     };
/*     */   
/*     */   public SalesWorkorder getById(Integer id) {
/*  67 */     if (id == null) {
/*  68 */       if (this.log.isDebugEnabled()) this.log.debug("try to get SalesWorkorder with null id"); 
/*  69 */       return null;
/*     */     } 
/*  71 */     return (SalesWorkorder)getHibernateTemplate().get(SalesWorkorder.class, id);
/*     */   }
/*     */   
/*     */   public SalesWorkorder insert(SalesWorkorder salesWorkorder) {
/*  75 */     getHibernateTemplate().save(salesWorkorder);
/*  76 */     return salesWorkorder;
/*     */   }
/*     */   
/*     */   public void remove(SalesWorkorder salesWorkorder) {
/*  80 */     getHibernateTemplate().delete(salesWorkorder);
/*     */   }
/*     */   
/*     */   public SalesWorkorder update(SalesWorkorder salesWorkorder) {
/*  84 */     if (salesWorkorder.getId() == null) throw new RuntimeException("update SalesWorkorder with null id!"); 
/*  85 */     SalesWorkorder oldEntity = getById(salesWorkorder.getId());
/*  86 */     if (oldEntity != null) {
/*     */       try {
/*  88 */         PropertyUtils.copyProperties(oldEntity, salesWorkorder);
/*  89 */       } catch (Exception e) {
/*  90 */         throw new RuntimeException("copy error with SalesWorkorder" + e);
/*     */       } 
/*  92 */       getHibernateTemplate().update(oldEntity);
/*  93 */       return oldEntity;
/*     */     } 
/*  95 */     throw new RuntimeException("SalesWorkorder not found");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getListCount(Map conditions) {
/* 101 */     return getListCount(conditions, "select count(*) from SalesWorkorder salesWorkorder", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getList(Map conditions, int pageNo, int pageSize, SalesWorkorderQueryOrder order, boolean descend) {
/* 106 */     return getList(conditions, pageNo, pageSize, order, descend, "from SalesWorkorder salesWorkorder", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */   
/*     */   public List<SalesWorkorder> getSalesWorkorder(String order) {
/* 110 */     String sql = "from SalesWorkorder salesWorkorder where salesWorkorder.shipId.code = ?";
/* 111 */     List<SalesWorkorder> list = getHibernateTemplate().find(sql, order, (Type)Hibernate.STRING);
/* 112 */     if (list == null || list.isEmpty()) {
/* 113 */       return null;
/*     */     }
/* 115 */     return list;
/*     */   }
/*     */   
/*     */   public String getSalesWorkorderByLotStatus(String order, String lotSer) {
/* 119 */     String sql = "from SalesWorkorder salesWorkorder where salesWorkorder.shipId.code = ? and salesWorkorder.lotSer.id=?";
/* 120 */     List<SalesWorkorder> list = getHibernateTemplate().find(sql, new Object[] { order, lotSer });
/* 121 */     if (list.size() > 0) {
/* 122 */       SalesPreshiporderBatchStatus status = ((SalesWorkorder)list.get(0)).getStatus1();
/* 123 */       if (status == SalesPreshiporderBatchStatus.IN_DELIVERYBATCH)
/*     */       {
/* 125 */         return "deliverybatch";
/*     */       }
/*     */       
/* 128 */       return "yes";
/*     */     } 
/*     */ 
/*     */     
/* 132 */     return "no";
/*     */   }
/*     */ 
/*     */   
/*     */   public SalesWorkorder getSalesWorkorder(String order, String lotSer) {
/* 137 */     String sql = "from SalesWorkorder salesWorkorder where salesWorkorder.shipId.code = ? and salesWorkorder.lotSer.id=?";
/* 138 */     List<SalesWorkorder> list = getHibernateTemplate().find(sql, new Object[] { order, lotSer });
/* 139 */     return list.get(0);
/*     */   }
/*     */   
/*     */   public SalesPreshiporder getSalesPreshiporder(String code) {
/* 143 */     String sql = "from SalesPreshiporder salesPreshiporder where salesPreshiporder.code = ?";
/* 144 */     List<SalesPreshiporder> list = getHibernateTemplate().find(sql, code, (Type)Hibernate.STRING);
/* 145 */     if (list == null || list.isEmpty()) {
/* 146 */       return null;
/*     */     }
/* 148 */     return list.get(0);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/product/hibernate/SalesWorkorderDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */