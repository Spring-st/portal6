/*     */ package com.aof.dao.basic.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.basic.StorageLocationDAO;
/*     */ import com.aof.dao.convert.QuerySQLConvert;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.query.StorageLocationQueryCondition;
/*     */ import com.aof.model.basic.query.StorageLocationQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import java.util.HashMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StorageLocationDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements StorageLocationDAO
/*     */ {
/*  35 */   private Log log = LogFactory.getLog(StorageLocationDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from StorageLocation sl";
/*     */   
/*     */   private static final String SQL = "from StorageLocation sl";
/*     */ 
/*     */   
/*     */   public StorageLocation getStorageLocation(Integer id) {
/*  43 */     if (id == null) {
/*  44 */       if (this.log.isDebugEnabled())
/*  45 */         this.log.debug("try to get StorageLocation with null id"); 
/*  46 */       return null;
/*     */     } 
/*  48 */     return (StorageLocation)getHibernateTemplate().get(StorageLocation.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StorageLocation updateStorageLocation(StorageLocation sl) {
/*  57 */     Integer id = sl.getId();
/*  58 */     if (id == null) {
/*  59 */       throw new RuntimeException("cannot save a sl with null id");
/*     */     }
/*  61 */     StorageLocation oldStorageLocation = getStorageLocation(id);
/*  62 */     if (oldStorageLocation != null) {
/*     */       try {
/*  64 */         PropertyUtils.copyProperties(oldStorageLocation, sl);
/*  65 */       } catch (Exception e) {
/*  66 */         throw new RuntimeException("copy error" + e);
/*     */       } 
/*  68 */       getHibernateTemplate().update(oldStorageLocation);
/*  69 */       return oldStorageLocation;
/*     */     } 
/*  71 */     throw new RuntimeException("sl not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StorageLocation insertStorageLocation(StorageLocation sl) {
/*  81 */     getHibernateTemplate().save(sl);
/*  82 */     return sl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  90 */       { StorageLocationQueryCondition.ID_EQ, "sl.id = ?"
/*  91 */       }, { StorageLocationQueryCondition.STROMTYPE_EQ, "sl.basic_storeroom_id.type = ?"
/*  92 */       }, { StorageLocationQueryCondition.CODE_EQ, "sl.code = ?"
/*  93 */       }, { StorageLocationQueryCondition.STOREROOM_ID_EQ, "sl.basic_storeroom_id.code = ?"
/*  94 */       }, { StorageLocationQueryCondition.STROMTYPE_IN, "sl.basic_storeroom_id.type in (5,8)"
/*  95 */       }, { StorageLocationQueryCondition.FREEAE_STATUS_EQ, "sl.freeae_status=?"
/*  96 */       }, { StorageLocationQueryCondition.CODEMANY_EQ, "sl.code in", new QuerySQLConvert() {
/*     */           public Object convert(StringBuffer sql, Object parameter) {
/*  98 */             if (parameter != null && parameter instanceof Object[]) {
/*  99 */               Object[] finalParameter = (Object[])parameter;
/* 100 */               if (finalParameter.length > 0) {
/* 101 */                 sql.append("(?");
/* 102 */                 for (int i = 1; i < finalParameter.length; i++) {
/* 103 */                   sql.append(",?");
/*     */                 }
/* 105 */                 sql.append(")");
/*     */               } else {
/* 107 */                 return finalParameter;
/*     */               } 
/*     */             } else {
/* 110 */               return parameter;
/*     */             } 
/* 112 */             return parameter;
/*     */           }
/*     */         } }
/*     */     };
/*     */   
/* 117 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 118 */       { StorageLocationQueryOrder.ID, "sl.id"
/* 119 */       }, { StorageLocationQueryOrder.ENABLED, "sl.enabled" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStorageLocationListCount(Map conditions) {
/* 128 */     return getListCount(conditions, "select count(*) from StorageLocation sl", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getStorageLocationList(Map conditions, int pageNo, int pageSize, StorageLocationQueryOrder order, boolean descend) {
/* 138 */     return getList(conditions, pageNo, pageSize, order, descend, "from StorageLocation sl", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEnabledStorageLocationList() {
/* 147 */     Map<Object, Object> conds = new HashMap<Object, Object>();
/* 148 */     conds.put(StorageLocationQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED.getEnumCode());
/* 149 */     return getStorageLocationList(conds, 0, -1, StorageLocationQueryOrder.ID, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteStorageLocation(StorageLocation sl) {
/* 154 */     getHibernateTemplate().delete(sl);
/*     */   }
/*     */   
/*     */   public StorageLocation getStorageLocation(String code) {
/* 158 */     String sql = "from StorageLocation sl where sl.code = ?";
/* 159 */     List<StorageLocation> list = getHibernateTemplate().find(sql, code, (Type)Hibernate.STRING);
/* 160 */     if (list.size() > 0) {
/* 161 */       return list.get(0);
/*     */     }
/* 163 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<StorageLocation> getStorageLocationLineLibrary() {
/* 169 */     String sql = "from StorageLocation location where location.basic_storeroom_id.id in (39,40)";
/* 170 */     return getHibernateTemplate().find(sql);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<StorageLocation> getStorageLocationByWmsStockingItemLocation(Integer id) {
/* 175 */     String sql = "from StorageLocation location where location.id in (select item.location.id from WmsStockingItem item where item.stocking.id = ?)";
/*     */     
/* 177 */     return getHibernateTemplate().find(sql, id, (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public boolean validateIsScatteredLocation(String location) {
/* 181 */     boolean sign = true;
/* 182 */     if (location.length() > 2) {
/* 183 */       String code = location.substring(0, 2);
/* 184 */       String sql = "from StorageLocation sl where sl.code like '" + code + "%' ";
/* 185 */       List list = getHibernateTemplate().find(sql, code);
/* 186 */       if (list == null || list.isEmpty())
/* 187 */         sign = false; 
/*     */     } else {
/* 189 */       sign = false;
/*     */     } 
/* 191 */     return sign;
/*     */   }
/*     */   
/*     */   public StorageLocation getStorageLocationByDYK() {
/* 195 */     String sql = "from StorageLocation sl where sl.basic_storeroom_id.type = 2 ";
/* 196 */     List<StorageLocation> list = getHibernateTemplate().find(sql);
/* 197 */     if (list == null || list.isEmpty()) {
/* 198 */       return null;
/*     */     }
/* 200 */     return list.get(0);
/*     */   }
/*     */   
/*     */   public StorageLocation getProduceLineLocation() {
/* 204 */     String sql = "from StorageLocation sl where sl.basic_storeroom_id.type = 11 ";
/* 205 */     List<StorageLocation> list = getHibernateTemplate().find(sql);
/* 206 */     if (list == null || list.isEmpty()) {
/* 207 */       return null;
/*     */     }
/* 209 */     return list.get(0);
/*     */   }
/*     */   
/*     */   public StorageLocation getProduceLineLocationLine() {
/* 213 */     String sql = "from StorageLocation sl where sl.basic_storeroom_id.type = 5 ";
/* 214 */     List<StorageLocation> list = getHibernateTemplate().find(sql);
/* 215 */     if (list == null || list.isEmpty()) {
/* 216 */       return null;
/*     */     }
/* 218 */     return list.get(0);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/StorageLocationDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */