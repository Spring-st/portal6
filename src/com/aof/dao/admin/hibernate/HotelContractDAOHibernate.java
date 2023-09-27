/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.HotelContractDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.HotelContract;
/*     */ import com.aof.model.admin.HotelContractContent;
/*     */ import com.aof.model.admin.query.HotelContractQueryCondition;
/*     */ import com.aof.model.admin.query.HotelContractQueryOrder;
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
/*     */ public class HotelContractDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements HotelContractDAO
/*     */ {
/*  40 */   private Log log = LogFactory.getLog(HotelContractDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from HotelContract hotelContract";
/*     */   private static final String SQL = "from HotelContract hotelContract";
/*     */   
/*     */   public HotelContract getHotelContract(Integer id) {
/*  46 */     if (id == null) {
/*  47 */       if (this.log.isDebugEnabled())
/*  48 */         this.log.debug("try to get HotelContract with null id"); 
/*  49 */       return null;
/*     */     } 
/*  51 */     return (HotelContract)getHibernateTemplate().get(HotelContract.class, 
/*  52 */         id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HotelContract updateHotelContract(HotelContract hotelContract) {
/*  59 */     Integer id = hotelContract.getId();
/*  60 */     if (id == null) {
/*  61 */       throw new RuntimeException(
/*  62 */           "cannot save a hotelContract with null id");
/*     */     }
/*  64 */     HotelContract oldHotelContract = getHotelContract(id);
/*  65 */     if (oldHotelContract != null) {
/*     */       try {
/*  67 */         PropertyUtils.copyProperties(oldHotelContract, hotelContract);
/*  68 */       } catch (Exception e) {
/*  69 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  71 */       getHibernateTemplate().update(oldHotelContract);
/*  72 */       return oldHotelContract;
/*     */     } 
/*  74 */     throw new RuntimeException("hotelContract not found");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HotelContract insertHotelContract(HotelContract hotelContract) {
/*  82 */     getHibernateTemplate().save(hotelContract);
/*  83 */     return hotelContract;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*     */       {
/*  92 */         HotelContractQueryCondition.ID_EQ, "hotelContract.id = ?"
/*     */ 
/*     */       
/*     */       },
/*     */       
/*     */       {
/*     */         
/*  99 */         HotelContractQueryCondition.HOTEL_ID_EQ, 
/* 100 */         "hotelContract.hotel.id = ?"
/*     */       
/*     */       },
/* 103 */       { HotelContractQueryCondition.FILENAME_LIKE, 
/* 104 */         "hotelContract.fileName like ?", new LikeConvert()
/* 105 */       }, { HotelContractQueryCondition.DESCRIPTION_LIKE, 
/* 106 */         "hotelContract.description like ?", new LikeConvert()
/* 107 */       }, { HotelContractQueryCondition.FILECONTENT_EQ, 
/* 108 */         "hotelContract.fileContent = ?"
/* 109 */       }, { HotelContractQueryCondition.UPLOADDATE_EQ, 
/* 110 */         "hotelContract.uploadDate = ?" }
/*     */     };
/* 112 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*     */       {
/* 114 */         HotelContractQueryOrder.ID, "hotelContract.id"
/*     */       
/*     */       },
/* 117 */       { HotelContractQueryOrder.FILENAME, "hotelContract.fileName"
/* 118 */       }, { HotelContractQueryOrder.DESCRIPTION, "hotelContract.description"
/* 119 */       }, { HotelContractQueryOrder.FILECONTENT, "hotelContract.fileContent"
/* 120 */       }, { HotelContractQueryOrder.UPLOADDATE, "hotelContract.uploadDate" }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHotelContractListCount(Map conditions) {
/* 126 */     return getListCount(conditions, "select count(*) from HotelContract hotelContract", QUERY_CONDITIONS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getHotelContractList(Map conditions, int pageNo, int pageSize, HotelContractQueryOrder order, boolean descend) {
/* 135 */     return getList(conditions, pageNo, pageSize, order, descend, "from HotelContract hotelContract", 
/* 136 */         QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveHotelContractContent(final Integer id, final InputStream inputStream) {
/* 143 */     getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 146 */             HotelContractContent hcc = (HotelContractContent)session.get(HotelContractContent.class, id);
/* 147 */             if (hcc == null) return null; 
/*     */             try {
/* 149 */               Blob content = Hibernate.createBlob(inputStream);
/* 150 */               hcc.setFileContent(content);
/* 151 */               session.update(hcc);
/* 152 */             } catch (IOException e) {
/* 153 */               throw new HibernateException(e);
/*     */             } 
/* 155 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getHotelContractContent(final Integer id) {
/* 165 */     return (InputStream)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 168 */             HotelContractContent hcc = (HotelContractContent)session.get(HotelContractContent.class, id);
/* 169 */             if (hcc == null) return null; 
/* 170 */             Blob fileContent = hcc.getFileContent();
/* 171 */             if (fileContent == null) return null; 
/*     */             try {
/* 173 */               return HotelContractDAOHibernate.preRead(fileContent.getBinaryStream());
/* 174 */             } catch (IOException e) {
/* 175 */               throw new HibernateException(e);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/HotelContractDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */