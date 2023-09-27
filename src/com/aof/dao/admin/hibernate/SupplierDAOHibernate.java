/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.SupplierDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.dao.convert.QueryParameterConvert;
/*     */ import com.aof.model.admin.Currency;
/*     */ import com.aof.model.admin.ExchangeRate;
/*     */ import com.aof.model.admin.GlobalMailReminder;
/*     */ import com.aof.model.admin.PurchaseSubCategory;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.admin.SupplierItem;
/*     */ import com.aof.model.admin.query.SupplierQueryCondition;
/*     */ import com.aof.model.admin.query.SupplierQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.SupplierConfirmStatus;
/*     */ import com.aof.model.metadata.SupplierPromoteStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.shcnc.hibernate.CompositeQuery;
/*     */ import com.shcnc.hibernate.QueryCondition;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.HibernateException;
/*     */ import net.sf.hibernate.Session;
/*     */ import net.sf.hibernate.type.Type;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.springframework.orm.hibernate.HibernateCallback;
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
/*     */ public class SupplierDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements SupplierDAO
/*     */ {
/*  54 */   private Log log = LogFactory.getLog(SupplierDAOHibernate.class);
/*     */   private static final String SQL_COUNT = "select count(*) from Supplier supplier";
/*     */   
/*     */   public Supplier getSupplier(Integer id) {
/*  58 */     if (id == null) {
/*  59 */       if (this.log.isDebugEnabled()) this.log.debug("try to get Supplier with null id"); 
/*  60 */       return null;
/*     */     } 
/*  62 */     return (Supplier)getHibernateTemplate().get(Supplier.class, id);
/*     */   }
/*     */   private static final String SQL = "from Supplier supplier";
/*     */   public Supplier updateSupplier(Supplier supplier) {
/*  66 */     Integer id = supplier.getId();
/*  67 */     if (id == null) {
/*  68 */       throw new RuntimeException("cannot save a supplier with null id");
/*     */     }
/*  70 */     Supplier oldSupplier = getSupplier(id);
/*  71 */     if (oldSupplier != null) {
/*     */       try {
/*  73 */         PropertyUtils.copyProperties(oldSupplier, supplier);
/*     */       }
/*  75 */       catch (Exception e) {
/*     */         
/*  77 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  79 */       getHibernateTemplate().update(oldSupplier);
/*  80 */       return oldSupplier;
/*     */     } 
/*     */ 
/*     */     
/*  84 */     throw new RuntimeException("supplier not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public Supplier insertSupplier(Supplier supplier) {
/*  89 */     getHibernateTemplate().save(supplier);
/*  90 */     return supplier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] { 
/*  98 */       { SupplierQueryCondition.CONTRACT_NOT_ACTIVE, "(supplier.contractStartDate is not null and supplier.contractStartDate>?)"
/*  99 */       }, { SupplierQueryCondition.CONTRACT_EXPIRED, "(supplier.contractExpireDate is not null and supplier.contractExpireDate<?)"
/* 100 */       }, { SupplierQueryCondition.CONTRACT_ACTIVE, " ((supplier.contractStartDate is null and supplier.contractExpireDate is null)  or (supplier.contractStartDate is null and supplier.contractExpireDate is not null and supplier.contractExpireDate>?)  or (supplier.contractStartDate is not null and supplier.contractExpireDate is null and supplier.contractStartDate<?)  or (supplier.contractStartDate is not null and supplier.contractExpireDate is not null and supplier.contractStartDate<? and supplier.contractExpireDate>?) )"
/*     */       
/*     */       },
/*     */       {
/* 104 */         SupplierQueryCondition.ID_EQ, "supplier.id = ?"
/* 105 */       }, { SupplierQueryCondition.CODE_LIKE, "supplier.code like ?", new LikeConvert()
/* 106 */       }, { SupplierQueryCondition.NAME_LIKE, "supplier.name like ?", new LikeConvert()
/* 107 */       }, { SupplierQueryCondition.SITE_ID_EQ, "supplier.site.id = ?"
/* 108 */       }, { SupplierQueryCondition.GLOBAL_OR_SITE_ID_EQ, "supplier.promoteStatus=? or supplier.site.id = ?", new QueryParameterConvert()
/*     */         {
/*     */           public Object convert(Object src) {
/* 111 */             return new Object[] { SupplierPromoteStatus.GLOBAL, src
/*     */               };
/*     */           }
/*     */         }
/* 115 */       }, { SupplierQueryCondition.COUNTRY_ID_EQ, "supplier.country.id = ?"
/* 116 */       }, { SupplierQueryCondition.CITY_ID_EQ, "supplier.city.id = ?" }, 
/* 117 */       { SupplierQueryCondition.ENABLED_EQ, "supplier.enabled = ?"
/* 118 */       }, { SupplierQueryCondition.DRAFT_EQ, "supplier.draft = ?"
/* 119 */       }, { SupplierQueryCondition.CONFIRM_EQ, "supplier.confirmed = ?"
/* 120 */       }, { SupplierQueryCondition.PROMOTE_STATUS_EQ, "supplier.promoteStatus = ?"
/* 121 */       }, { SupplierQueryCondition.PROMOTE_STATUS_NEQ, "supplier.promoteStatus <> ?"
/* 122 */       }, { SupplierQueryCondition.CONFIRM_TYPE_EQ, "supplier.confirmStatus = ? "
/* 123 */       }, { SupplierQueryCondition.FOR_SITE_ID_EQ, "((supplier.site) is null or (supplier.site.id = ?))"
/* 124 */       }, { SupplierQueryCondition.IS_AIRTICKET, "supplier.airTicket = ?" } };
/*     */ 
/*     */ 
/*     */   
/* 128 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 129 */       { SupplierQueryOrder.ID, "supplier.id"
/* 130 */       }, { SupplierQueryOrder.CODE, "supplier.code"
/* 131 */       }, { SupplierQueryOrder.CITYENGNAME, "supplier.city.engName"
/* 132 */       }, { SupplierQueryOrder.CITYCHNNAME, "supplier.city.chnName"
/* 133 */       }, { SupplierQueryOrder.NAME, "supplier.name"
/* 134 */       }, { SupplierQueryOrder.PROMOTE_STATUS, "supplier.promoteStatus"
/* 135 */       }, { SupplierQueryOrder.ENABLED, "supplier.enabled"
/* 136 */       }, { SupplierQueryOrder.DRAFT, "supplier.draft" } }; private static final String SQL_COUNT_SamplingRatio = "select count(*) from SupplierPartSamplingRatio sr";
/*     */   private static final String SQL_SamplingRatio = "from SupplierPartSamplingRatio sr";
/*     */   
/*     */   public int getSupplierListCount(Map conditions) {
/* 140 */     return getListCount(conditions, "select count(*) from Supplier supplier", QUERY_CONDITIONS);
/*     */   }
/*     */   
/*     */   public List getSupplierList(Map conditions, int pageNo, int pageSize, SupplierQueryOrder order, boolean descend) {
/* 144 */     return getList(conditions, pageNo, pageSize, order, descend, "from Supplier supplier", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */   
/*     */   public String getLastSupplierCode() {
/* 148 */     String result = getHibernateTemplate().find(
/* 149 */         "select max(supplier.code) from Supplier supplier where supplier.code like 'SP%'").get(0);
/* 150 */     return ((result == null) ? "SP000000" : (String.valueOf(result) + "000000")).substring(0, 8);
/*     */   }
/*     */   
/*     */   public boolean isCodeUsed(String code, Site site) {
/* 154 */     if (site != null) {
/* 155 */       return getHibernateTemplate().iterate("select s.code from Supplier s where s.code = ? and s.site.id=?", new Object[] { code, site.getId() }, new Type[] { (Type)Hibernate.STRING, (Type)Hibernate.INTEGER }).hasNext();
/*     */     }
/* 157 */     return getHibernateTemplate().iterate("select s.code from Supplier s where s.code = ? and s.site.id is null", code, (Type)Hibernate.STRING).hasNext();
/*     */   }
/*     */   
/*     */   public List getSuitableSupplierListForPurchase(final Site site, final PurchaseSubCategory psc, final List exchangeRateList) {
/* 161 */     return getHibernateTemplate().executeFind(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 164 */             CompositeQuery query = new CompositeQuery("select si from SupplierItem si join si.supplier s", "s.name, si.sepc", session);
/* 165 */             QueryCondition qc = query.createQueryCondition("s.promoteStatus = ? or s.site.id = ?");
/* 166 */             qc.appendParameter(SupplierPromoteStatus.GLOBAL);
/* 167 */             qc.appendParameter(site.getId());
/* 168 */             query.createQueryCondition("si.enabled = ?").appendParameter(EnabledDisabled.ENABLED);
/* 169 */             query.createQueryCondition("s.enabled = ?").appendParameter(EnabledDisabled.ENABLED);
/* 170 */             query.createQueryCondition("si.purchaseSubCategory.id = ?").appendParameter(psc.getId());
/* 171 */             qc = query.createQueryCondition("(s.confirmStatus = ? or s.confirmed = ?)");
/* 172 */             qc.appendParameter(SupplierConfirmStatus.MODIFY);
/* 173 */             qc.appendParameter(YesNo.YES);
/* 174 */             List supplierItemList = query.list();
/* 175 */             List<Currency> currencyList = null;
/* 176 */             if (exchangeRateList != null) {
/* 177 */               currencyList = new ArrayList();
/* 178 */               for (Iterator<ExchangeRate> iterator = exchangeRateList.iterator(); iterator.hasNext(); ) {
/* 179 */                 ExchangeRate er = iterator.next();
/* 180 */                 currencyList.add(er.getCurrency());
/*     */               } 
/*     */             } 
/* 183 */             List<Supplier> result = new ArrayList();
/* 184 */             for (Iterator<SupplierItem> itor = supplierItemList.iterator(); itor.hasNext(); ) {
/* 185 */               SupplierItem si = itor.next();
/* 186 */               if (exchangeRateList == null || currencyList.contains(si.getCurrency())) {
/* 187 */                 Supplier s = si.getSupplier();
/* 188 */                 s.addItem(si);
/* 189 */                 if (!result.contains(s)) {
/* 190 */                   result.add(s);
/*     */                 }
/*     */               } 
/*     */             } 
/* 194 */             return result;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public List getSuitableSupplierItemListForPurchase(final Supplier supplier, final PurchaseSubCategory psc, final Currency currency) {
/* 201 */     return getHibernateTemplate().executeFind(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 204 */             CompositeQuery query = new CompositeQuery(
/* 205 */                 "select si from SupplierItem si", "si.sepc", session);
/* 206 */             query.createQueryCondition("si.enabled = ?").appendParameter(EnabledDisabled.ENABLED.getEnumCode());
/* 207 */             query.createQueryCondition("si.supplier.id = ?").appendParameter(supplier.getId());
/* 208 */             query.createQueryCondition("si.purchaseSubCategory.id = ?").appendParameter(psc.getId());
/* 209 */             query.createQueryCondition("si.currency.code = ?").appendParameter(currency.getCode());
/* 210 */             return query.list();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public List getSupplierMaintainerNotResponsedList(Site site, Date time, GlobalMailReminder gmr) {
/* 217 */     return getHibernateTemplate().find("from Supplier s where s.site.id=? and s.promoteStatus=? and s.promoteDate<=? and s.emailTimes<? and (s.emailDate is null or s.emailDate<=?) and s.enabled=?", 
/*     */         
/* 219 */         new Object[] { site.getId(), SupplierPromoteStatus.REQUEST.getEnumCode(), 
/* 220 */           gmr.getResponseDate(time), new Integer(gmr.getMaxTime()), gmr.getEmailDate(time), 
/* 221 */           EnabledDisabled.ENABLED.getEnumCode()
/* 222 */         }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.INTEGER, (Type)Hibernate.TIMESTAMP, (Type)Hibernate.INTEGER, (Type)Hibernate.TIMESTAMP, (Type)Hibernate.INTEGER });
/*     */   }
/*     */   
/*     */   public Supplier getSupplierByCode(String code) {
/* 226 */     String sql = "from Supplier sp where sp.code = ?";
/* 227 */     List<Supplier> list = getHibernateTemplate().find(sql, code, (Type)Hibernate.STRING);
/* 228 */     if (list == null || list.isEmpty()) {
/* 229 */       return null;
/*     */     }
/* 231 */     return list.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 238 */   private static final Object[][] QUERY_CONDITIONS_SamplingRatio = new Object[0][];
/*     */ 
/*     */ 
/*     */   
/* 242 */   private static final Object[][] QUERY_ORDERS_SamplingRatio = new Object[0][];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSupplierPartSamplingRatioList(Map conditions, int pageNo, int pageSize, SupplierQueryOrder order, boolean descend) {
/* 249 */     return getList(conditions, pageNo, pageSize, order, descend, "from SupplierPartSamplingRatio sr", QUERY_CONDITIONS_SamplingRatio, QUERY_ORDERS_SamplingRatio);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSupplierPartSamplingRatioCount(Map condtions) {
/* 254 */     return getListCount(condtions, "select count(*) from SupplierPartSamplingRatio sr", QUERY_CONDITIONS_SamplingRatio);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/SupplierDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */