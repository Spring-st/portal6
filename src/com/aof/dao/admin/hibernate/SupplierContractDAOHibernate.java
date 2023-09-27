/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.SupplierContractDAO;
/*     */ import com.aof.model.admin.SupplierContract;
/*     */ import com.aof.model.admin.SupplierContractContent;
/*     */ import com.aof.model.admin.query.SupplierContractQueryCondition;
/*     */ import com.aof.model.admin.query.SupplierContractQueryOrder;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.sql.Blob;
/*     */ import java.sql.SQLException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.HibernateException;
/*     */ import net.sf.hibernate.Session;
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
/*     */ public class SupplierContractDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements SupplierContractDAO
/*     */ {
/*  40 */   private Log log = LogFactory.getLog(SupplierContractDAOHibernate.class);
/*     */   private static final String SQL_COUNT = "select count(*) from SupplierContract supplierContract";
/*     */   
/*     */   public SupplierContract getSupplierContract(Integer id) {
/*  44 */     if (id == null) {
/*  45 */       if (this.log.isDebugEnabled()) this.log.debug("try to get SupplierContract with null id"); 
/*  46 */       return null;
/*     */     } 
/*  48 */     return (SupplierContract)getHibernateTemplate().get(SupplierContract.class, id);
/*     */   }
/*     */   private static final String SQL = "from SupplierContract supplierContract";
/*     */   public SupplierContract updateSupplierContract(SupplierContract supplierContract) {
/*  52 */     Integer id = supplierContract.getId();
/*  53 */     if (id == null) {
/*  54 */       throw new RuntimeException("cannot save a supplierContract with null id");
/*     */     }
/*  56 */     SupplierContract oldSupplierContract = getSupplierContract(id);
/*  57 */     if (oldSupplierContract != null) {
/*     */       try {
/*  59 */         PropertyUtils.copyProperties(oldSupplierContract, supplierContract);
/*     */       }
/*  61 */       catch (Exception e) {
/*     */         
/*  63 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  65 */       getHibernateTemplate().update(oldSupplierContract);
/*  66 */       return oldSupplierContract;
/*     */     } 
/*     */ 
/*     */     
/*  70 */     throw new RuntimeException("supplierContract not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public SupplierContract insertSupplierContract(SupplierContract supplierContract) {
/*  75 */     getHibernateTemplate().save(supplierContract);
/*  76 */     return supplierContract;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*  84 */       { SupplierContractQueryCondition.ID_EQ, "supplierContract.id = ?"
/*  85 */       }, { SupplierContractQueryCondition.SUPPLIER_ID_EQ, "supplierContract.Supplier.id = ?" }
/*     */     };
/*     */   
/*  88 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*  89 */       { SupplierContractQueryOrder.ID, "supplierContract.id"
/*  90 */       }, { SupplierContractQueryOrder.FILESIZE, "supplierContract.fileSize"
/*  91 */       }, { SupplierContractQueryOrder.FILENAME, "supplierContract.fileName"
/*  92 */       }, { SupplierContractQueryOrder.DESCRIPTION, "supplierContract.description"
/*  93 */       }, { SupplierContractQueryOrder.UPLOADDATE, "supplierContract.uploadDate" }
/*     */     };
/*     */   
/*     */   public int getSupplierContractListCount(Map conditions) {
/*  97 */     return getListCount(conditions, "select count(*) from SupplierContract supplierContract", QUERY_CONDITIONS);
/*     */   }
/*     */   
/*     */   public List getSupplierContractList(Map conditions, int pageNo, int pageSize, SupplierContractQueryOrder order, boolean descend) {
/* 101 */     return getList(conditions, pageNo, pageSize, order, descend, "from SupplierContract supplierContract", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveSupplierContractContent(final Integer id, final InputStream inputStream) {
/* 106 */     getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 109 */             SupplierContractContent scc = (SupplierContractContent)session.get(SupplierContractContent.class, id);
/* 110 */             if (scc == null) return null; 
/*     */             try {
/* 112 */               Blob content = Hibernate.createBlob(inputStream);
/* 113 */               scc.setFileContent(content);
/* 114 */               session.update(scc);
/* 115 */             } catch (IOException e) {
/* 116 */               throw new HibernateException(e);
/*     */             } finally {
/* 118 */               if (session != null && session.isOpen()) {
/* 119 */                 session.close();
/*     */               }
/*     */             } 
/* 122 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream getSupplierContractContent(final Integer id) {
/* 129 */     return (InputStream)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 132 */             SupplierContractContent scc = (SupplierContractContent)session.get(SupplierContractContent.class, id);
/* 133 */             if (scc == null) return null; 
/* 134 */             Blob fileContent = scc.getFileContent();
/* 135 */             if (fileContent == null) return null; 
/*     */             try {
/* 137 */               return SupplierContractDAOHibernate.preRead(fileContent.getBinaryStream());
/* 138 */             } catch (IOException e) {
/* 139 */               throw new HibernateException(e);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/SupplierContractDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */