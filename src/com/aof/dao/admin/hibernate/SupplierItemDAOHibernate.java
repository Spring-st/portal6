/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.SupplierItemDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.PurchaseSubCategory;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.admin.SupplierItem;
/*     */ import com.aof.model.admin.query.SupplierItemQueryCondition;
/*     */ import com.aof.model.admin.query.SupplierItemQueryOrder;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
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
/*     */ public class SupplierItemDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements SupplierItemDAO
/*     */ {
/*  36 */   private Log log = LogFactory.getLog(SupplierItemDAOHibernate.class);
/*     */   private static final String SQL_COUNT = "select count(*) from SupplierItem supplierItem";
/*     */   
/*     */   public SupplierItem getSupplierItem(Integer id) {
/*  40 */     if (id == null) {
/*  41 */       if (this.log.isDebugEnabled()) this.log.debug("try to get SupplierItem with null id"); 
/*  42 */       return null;
/*     */     } 
/*  44 */     return (SupplierItem)getHibernateTemplate().get(SupplierItem.class, id);
/*     */   }
/*     */   private static final String SQL = "from SupplierItem supplierItem";
/*     */   public SupplierItem updateSupplierItem(SupplierItem supplierItem) {
/*  48 */     Integer id = supplierItem.getId();
/*  49 */     if (id == null) {
/*  50 */       throw new RuntimeException("cannot save a supplierItem with null id");
/*     */     }
/*  52 */     SupplierItem oldSupplierItem = getSupplierItem(id);
/*  53 */     if (oldSupplierItem != null) {
/*     */       try {
/*  55 */         PropertyUtils.copyProperties(oldSupplierItem, supplierItem);
/*     */       }
/*  57 */       catch (Exception e) {
/*     */         
/*  59 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  61 */       getHibernateTemplate().update(oldSupplierItem);
/*  62 */       getHibernateTemplate().flush();
/*  63 */       getHibernateTemplate().refresh(oldSupplierItem);
/*  64 */       return oldSupplierItem;
/*     */     } 
/*     */ 
/*     */     
/*  68 */     throw new RuntimeException("supplierItem not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public SupplierItem insertSupplierItem(SupplierItem supplierItem) {
/*  73 */     getHibernateTemplate().save(supplierItem);
/*  74 */     getHibernateTemplate().flush();
/*  75 */     getHibernateTemplate().refresh(supplierItem);
/*  76 */     return supplierItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  84 */       { SupplierItemQueryCondition.ID_EQ, "supplierItem.id = ?"
/*  85 */       }, { SupplierItemQueryCondition.CURRENCY_CODE_EQ, "supplierItem.currency.code = ?"
/*  86 */       }, { SupplierItemQueryCondition.PURCHASESUBCATEGORY_ID_EQ, "supplierItem.purchaseSubCategory.id = ?"
/*  87 */       }, { SupplierItemQueryCondition.PURCHASECATEGORY_ID_EQ, "supplierItem.purchaseSubCategory.purchaseCategory.id = ?"
/*  88 */       }, { SupplierItemQueryCondition.SUPPLIER_ID_EQ, "supplierItem.supplier.id = ?"
/*  89 */       }, { SupplierItemQueryCondition.SEPC_LIKE, "supplierItem.sepc like ?", new LikeConvert()
/*  90 */       }, { SupplierItemQueryCondition.UNITPRICE_EQ, "supplierItem.unitPrice = ?"
/*  91 */       }, { SupplierItemQueryCondition.ENABLED_EQ, "supplierItem.enabled = ?"
/*  92 */       }, { SupplierItemQueryCondition.ERPNO_LIKE, "supplierItem.erpNo like ?", new LikeConvert() }
/*     */     };
/*     */ 
/*     */   
/*  96 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  97 */       { SupplierItemQueryOrder.ID, "supplierItem.id"
/*  98 */       }, { SupplierItemQueryOrder.SEPC, "supplierItem.sepc"
/*  99 */       }, { SupplierItemQueryOrder.UNITPRICE, "supplierItem.unitPrice"
/* 100 */       }, { SupplierItemQueryOrder.ENABLED, "supplierItem.enabled"
/* 101 */       }, { SupplierItemQueryOrder.ERPNO, "supplierItem.erpNo"
/* 102 */       }, { SupplierItemQueryOrder.CATEGORY, "supplierItem.purchaseSubCategory.purchaseCategory.description"
/* 103 */       }, { SupplierItemQueryOrder.SUBCATEGORY, "supplierItem.purchaseSubCategory.description" }
/*     */     };
/*     */   
/*     */   public int getSupplierItemListCount(Map conditions) {
/* 107 */     return getListCount(conditions, "select count(*) from SupplierItem supplierItem", QUERY_CONDITIONS);
/*     */   }
/*     */   
/*     */   public List getSupplierItemList(Map conditions, int pageNo, int pageSize, SupplierItemQueryOrder order, boolean descend) {
/* 111 */     return getList(conditions, pageNo, pageSize, order, descend, "from SupplierItem supplierItem", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getSupplierItemListBySupplier(Supplier supplier) {
/* 116 */     return getHibernateTemplate().find("from SupplierItem si where si.supplier.id=? order by si.purchaseSubCategory.purchaseCategory.id,si.purchaseSubCategory.id,si.sepc", supplier.getId(), (Type)Hibernate.INTEGER);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeSupplierItem(Integer id) {
/* 121 */     SupplierItem supplierItem = getSupplierItem(id);
/* 122 */     getHibernateTemplate().delete(supplierItem);
/*     */   }
/*     */   
/*     */   public List getSupplierItemListConflictWithPromoteBySupplier(Supplier supplier) {
/* 126 */     return getHibernateTemplate().find("from SupplierItem si where si.supplier.id=? and si.purchaseSubCategory.purchaseCategory.site.id is not null order by si.purchaseSubCategory.purchaseCategory.id,si.purchaseSubCategory.id,si.sepc", supplier.getId(), (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public void changePurchaseSubCategoryBySupplierAndPurchaseSubCategory(Integer supplierId, Integer purchaseSubCategoryId, PurchaseSubCategory destPurchaseSubCategory) {
/* 130 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 131 */     conditions.put(SupplierItemQueryCondition.SUPPLIER_ID_EQ, supplierId);
/* 132 */     conditions.put(SupplierItemQueryCondition.PURCHASESUBCATEGORY_ID_EQ, purchaseSubCategoryId);
/* 133 */     List result = getSupplierItemList(conditions, 0, -1, (SupplierItemQueryOrder)null, false);
/* 134 */     for (Iterator<SupplierItem> itor = result.iterator(); itor.hasNext(); ) {
/* 135 */       SupplierItem item = itor.next();
/* 136 */       item.setPurchaseSubCategory(destPurchaseSubCategory);
/* 137 */       getHibernateTemplate().update(item);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/SupplierItemDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */