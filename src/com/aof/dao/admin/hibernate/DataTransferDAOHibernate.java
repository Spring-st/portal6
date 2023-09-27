/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.DataTransferDAO;
/*     */ import com.aof.model.admin.Currency;
/*     */ import com.aof.model.admin.Customer;
/*     */ import com.aof.model.admin.ExchangeRate;
/*     */ import com.aof.model.admin.ExpenseCategory;
/*     */ import com.aof.model.admin.ExpenseSubCategory;
/*     */ import com.aof.model.admin.PurchaseType;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.ExpenseStatus;
/*     */ import com.aof.model.metadata.ExpenseType;
/*     */ import com.aof.model.metadata.ExportStatus;
/*     */ import com.aof.model.metadata.PurchaseOrderStatus;
/*     */ import com.aof.model.metadata.SupplierPromoteStatus;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.type.Type;
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
/*     */ public class DataTransferDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements DataTransferDAO
/*     */ {
/*     */   public List getDataTransferParameterList() {
/*  45 */     return getHibernateTemplate().find("from DataTransferParameter dtp where dtp.startTime is not null and dtp.timePerDay is not null and dtp.intervalMin is not null");
/*     */   }
/*     */   
/*     */   public List getPOList(Site site) {
/*  49 */     return getHibernateTemplate().find("from PurchaseOrder po where (po.exportStatus=? or po.exportStatus=?) and po.site.id=? and (po.status=? or po.status=?) order by po.id", 
/*  50 */         new Object[] { ExportStatus.UNEXPORTED.getEnumCode().toString(), ExportStatus.NEEDREEXPORT.getEnumCode().toString(), site.getId(), PurchaseOrderStatus.WAIT.getEnumCode().toString(), PurchaseOrderStatus.WAIT.getEnumCode().toString()
/*  51 */         }, new Type[] { (Type)Hibernate.STRING, (Type)Hibernate.STRING, (Type)Hibernate.INTEGER, (Type)Hibernate.STRING, (Type)Hibernate.STRING });
/*     */   }
/*     */ 
/*     */   
/*     */   public List getPOItemReceiptList(Site site) {
/*  56 */     return getHibernateTemplate().find("from PurchaseOrderItemReceipt poir where poir.exportStatus=? and poir.purchaseOrderItem.purchaseOrder.site.id=? and poir.receiveQty1=poir.receiveQty2", 
/*  57 */         new Object[] { ExportStatus.UNEXPORTED.getEnumCode().toString(), site.getId()
/*  58 */         }, new Type[] { (Type)Hibernate.STRING, (Type)Hibernate.INTEGER });
/*     */   }
/*     */   
/*     */   public List getExpenseList(Site site) {
/*  62 */     return getHibernateTemplate().find("from Expense ex where ex.exportStatus=? and ex.department.site.id=? and ex.status=? order by ex.id", 
/*  63 */         new Object[] { ExportStatus.UNEXPORTED.getEnumCode().toString(), site.getId(), ExpenseStatus.CLAIMED.getEnumCode().toString()
/*  64 */         }, new Type[] { (Type)Hibernate.STRING, (Type)Hibernate.INTEGER, (Type)Hibernate.STRING });
/*     */   }
/*     */ 
/*     */   
/*     */   private BigDecimal getSumEnterAmount(ExpenseSubCategory expenseSubCategory, List sumCellList) {
/*  69 */     for (Iterator<Object[]> itor = sumCellList.iterator(); itor.hasNext(); ) {
/*  70 */       Object[] content = itor.next();
/*  71 */       if (((Integer)content[1]).equals(expenseSubCategory.getId())) {
/*  72 */         return (BigDecimal)content[0];
/*     */       }
/*     */     } 
/*  75 */     return new BigDecimal(0.0D);
/*     */   }
/*     */   
/*     */   public List getSupplierList(Site site) {
/*  79 */     return getHibernateTemplate().find("from Supplier sp where sp.exportStatus=? and sp.site.id=? and sp.promoteStatus=? order by sp.id", 
/*  80 */         new Object[] { ExportStatus.UNEXPORTED.getEnumCode().toString(), site.getId(), SupplierPromoteStatus.SITE.getEnumCode().toString()
/*  81 */         }, new Type[] { (Type)Hibernate.STRING, (Type)Hibernate.INTEGER, (Type)Hibernate.STRING });
/*     */   }
/*     */   
/*     */   public List getDepartmentList(Site site) {
/*  85 */     return getHibernateTemplate().find("from Department dp where dp.site.id=? order by dp.id", site.getId(), (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public List getCountryList(Site site) {
/*  89 */     return getHibernateTemplate().find("from Country country where country.site.id=? or country.site.id is null order by country.id", site.getId(), (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public List getPurchaseTypeCodeList(Site site) {
/*  93 */     return getHibernateTemplate().find("select pt.code from PurchaseType pt where pt.site.id=? order by pt.code", site.getId(), (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public List getCustomerList(Site site) {
/*  97 */     return getHibernateTemplate().find("from Customer c where c.site.id=? order by c.code", site.getId(), (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public List getExchangeRateList(Site site) {
/* 101 */     return getHibernateTemplate().find("from ExchangeRate ex where ex.site.id=? order by ex.currency.code", site.getId(), (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public Currency getCurrency(String code) {
/* 105 */     if (code == null) return null; 
/* 106 */     List<Currency> cList = getHibernateTemplate().find("from Currency c where ucase(c.code)=?", code.toUpperCase(), (Type)Hibernate.STRING);
/* 107 */     Currency currency = null;
/* 108 */     if (cList.size() > 0)
/* 109 */       currency = cList.get(0); 
/* 110 */     if (currency == null) {
/* 111 */       currency = new Currency();
/* 112 */       currency.setCode(code);
/* 113 */       currency.setName(code);
/* 114 */       currency.setEnabled(EnabledDisabled.ENABLED);
/* 115 */       getHibernateTemplate().save(currency);
/* 116 */       getHibernateTemplate().flush();
/*     */     } 
/* 118 */     return currency;
/*     */   }
/*     */   
/*     */   public ExpenseCategory getExpenseCategory(Site site, String code) {
/* 122 */     List<ExpenseCategory> result = getHibernateTemplate().find("from ExpenseCategory ex where ex.site.id=? and ex.referenceErpId=?", 
/* 123 */         new Object[] { site.getId(), code
/* 124 */         }, new Type[] { (Type)Hibernate.INTEGER, (Type)Hibernate.STRING });
/* 125 */     if (result.size() > 0) {
/* 126 */       return result.get(0);
/*     */     }
/* 128 */     ExpenseCategory ex = new ExpenseCategory();
/* 129 */     ex.setSite(site);
/* 130 */     ex.setType(ExpenseType.OTHER);
/* 131 */     ex.setDescription(code);
/* 132 */     ex.setEnabled(EnabledDisabled.ENABLED);
/* 133 */     ex.setReferenceErpId(code);
/* 134 */     getHibernateTemplate().save(ex);
/* 135 */     getHibernateTemplate().flush();
/* 136 */     return ex;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getExpenseCategoryList(Site site) {
/* 141 */     return getHibernateTemplate().find("from ExpenseCategory ex where ex.site.id=?", site.getId(), (Type)Hibernate.INTEGER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpenseSubCategory getExpenseSubCategory(ExpenseCategory expenseCategory, String code) {
/* 149 */     return null;
/*     */   }
/*     */   
/*     */   public List getExpenseSubCategoryList(Site site) {
/* 153 */     return getHibernateTemplate().find("from ExpenseSubCategory ex where ex.expenseCategory.site.id=?", site.getId(), (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public void updateSupplier(Supplier supplier) {
/* 157 */     getHibernateTemplate().update(supplier);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePurchaseType(PurchaseType purchaseType, boolean insert) {
/* 162 */     if (insert) {
/* 163 */       getHibernateTemplate().save(purchaseType);
/*     */     } else {
/* 165 */       getHibernateTemplate().update(purchaseType);
/* 166 */     }  getHibernateTemplate().flush();
/*     */   }
/*     */   
/*     */   public void updateExchangeRate(ExchangeRate exchangeRate, boolean insert) {
/* 170 */     if (insert) {
/* 171 */       getHibernateTemplate().save(exchangeRate);
/*     */     } else {
/* 173 */       getHibernateTemplate().update(exchangeRate);
/* 174 */     }  getHibernateTemplate().flush();
/*     */   }
/*     */   
/*     */   public void updateExpenseCategory(ExpenseCategory expenseCategory, boolean insert) {
/* 178 */     if (insert) {
/* 179 */       getHibernateTemplate().save(expenseCategory);
/*     */     } else {
/* 181 */       getHibernateTemplate().update(expenseCategory);
/* 182 */     }  getHibernateTemplate().flush();
/*     */   }
/*     */   
/*     */   public void updateExpenseSubCategory(ExpenseSubCategory expenseSubCategory, boolean insert) {
/* 186 */     if (insert) {
/* 187 */       getHibernateTemplate().save(expenseSubCategory);
/*     */     } else {
/* 189 */       getHibernateTemplate().update(expenseSubCategory);
/* 190 */     }  getHibernateTemplate().flush();
/*     */   }
/*     */   
/*     */   public void updateCustomer(Customer customer, boolean insert) {
/* 194 */     if (insert) {
/* 195 */       getHibernateTemplate().save(customer);
/*     */     } else {
/* 197 */       getHibernateTemplate().update(customer);
/* 198 */     }  getHibernateTemplate().flush();
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/DataTransferDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */