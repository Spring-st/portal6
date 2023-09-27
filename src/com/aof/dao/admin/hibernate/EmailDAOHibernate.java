/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.EmailDAO;
/*     */ import com.aof.dao.convert.LikeConvert;
/*     */ import com.aof.model.admin.Email;
/*     */ import com.aof.model.admin.EmailBatch;
/*     */ import com.aof.model.admin.EmailBatchBody;
/*     */ import com.aof.model.admin.EmailBody;
/*     */ import com.aof.model.admin.query.EmailQueryCondition;
/*     */ import com.aof.model.admin.query.EmailQueryOrder;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.sql.Clob;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Date;
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
/*     */ import org.springframework.orm.hibernate.HibernateTemplate;
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
/*     */ public class EmailDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements EmailDAO
/*     */ {
/*  45 */   private Log log = LogFactory.getLog(EmailDAOHibernate.class); private static final String SQL_COUNT = "select count(*) from Email email";
/*     */   private static final String SQL = "from Email email";
/*     */   
/*     */   public Email getEmail(Integer id) {
/*  49 */     if (id == null) {
/*  50 */       if (this.log.isDebugEnabled()) this.log.debug("try to get Email with null id"); 
/*  51 */       return null;
/*     */     } 
/*  53 */     return (Email)getHibernateTemplate().get(Email.class, id);
/*     */   }
/*     */   
/*     */   public EmailBatch getEmailBatch(Integer id) {
/*  57 */     if (id == null) {
/*  58 */       if (this.log.isDebugEnabled()) this.log.debug("try to get EmailBatch with null id"); 
/*  59 */       return null;
/*     */     } 
/*  61 */     return (EmailBatch)getHibernateTemplate().get(EmailBatch.class, id);
/*     */   }
/*     */   
/*     */   public String getEmailBody(final Integer id) {
/*  65 */     if (id == null) {
/*  66 */       if (this.log.isDebugEnabled()) this.log.debug("try to get EmailBody with null id"); 
/*  67 */       return null;
/*     */     } 
/*  69 */     return (String)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/*  72 */             EmailBody eb = (EmailBody)session.get(EmailBody.class, id);
/*  73 */             if (eb == null) {
/*  74 */               return null;
/*     */             }
/*  76 */             char[] buffer = new char[1024];
/*  77 */             StringBuffer result = new StringBuffer();
/*  78 */             Reader rd = eb.getBody().getCharacterStream();
/*     */             try {
/*     */               while (true) {
/*  81 */                 int i = rd.read(buffer);
/*  82 */                 if (i > 0) {
/*  83 */                   result.append(buffer, 0, i);
/*     */                   continue;
/*     */                 } 
/*     */                 break;
/*     */               } 
/*  88 */               rd.close();
/*  89 */             } catch (IOException e) {
/*  90 */               throw new HibernateException(e);
/*     */             } 
/*  92 */             return result.toString();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public List getAllUnSendEmailBatch() {
/*  98 */     return getHibernateTemplate().find(
/*  99 */         "from EmailBatch as eb where eb.isSend = " + YesNo.NO + " order by eb.mailToUser, eb.site, eb.templateName");
/*     */   }
/*     */   
/*     */   public String getEmailBatchBody(final Integer id) {
/* 103 */     if (id == null) {
/* 104 */       if (this.log.isDebugEnabled()) this.log.debug("try to get EmailBatchBody with null id"); 
/* 105 */       return null;
/*     */     } 
/* 107 */     return (String)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 110 */             EmailBatchBody eb = (EmailBatchBody)session.get(EmailBatchBody.class, id);
/* 111 */             if (eb == null) {
/* 112 */               return null;
/*     */             }
/* 114 */             char[] buffer = new char[1024];
/* 115 */             StringBuffer result = new StringBuffer();
/* 116 */             Reader rd = eb.getBody().getCharacterStream();
/*     */             try {
/*     */               while (true) {
/* 119 */                 int i = rd.read(buffer);
/* 120 */                 if (i > 0) {
/* 121 */                   result.append(buffer, 0, i);
/*     */                   continue;
/*     */                 } 
/*     */                 break;
/*     */               } 
/* 126 */               rd.close();
/* 127 */             } catch (IOException e) {
/* 128 */               throw new HibernateException(e);
/*     */             } 
/* 130 */             return result.toString();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public Email updateEmail(Email email) {
/* 136 */     Integer id = email.getId();
/* 137 */     if (id == null) {
/* 138 */       throw new RuntimeException("cannot save a email with null id");
/*     */     }
/* 140 */     Email oldEmail = getEmail(id);
/* 141 */     if (oldEmail != null) {
/*     */       try {
/* 143 */         PropertyUtils.copyProperties(oldEmail, email);
/*     */       }
/* 145 */       catch (Exception e) {
/*     */         
/* 147 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/* 149 */       getHibernateTemplate().update(oldEmail);
/* 150 */       return oldEmail;
/*     */     } 
/*     */ 
/*     */     
/* 154 */     throw new RuntimeException("email not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public EmailBatch updateEmailBatch(EmailBatch emailBatch) {
/* 159 */     Integer id = emailBatch.getId();
/* 160 */     if (id == null) {
/* 161 */       throw new RuntimeException("cannot save a EmailBatch with null id");
/*     */     }
/* 163 */     EmailBatch oldEmailBatch = getEmailBatch(id);
/* 164 */     if (oldEmailBatch != null) {
/*     */       try {
/* 166 */         PropertyUtils.copyProperties(oldEmailBatch, emailBatch);
/*     */       }
/* 168 */       catch (Exception e) {
/*     */         
/* 170 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/* 172 */       getHibernateTemplate().update(oldEmailBatch);
/* 173 */       return oldEmailBatch;
/*     */     } 
/*     */ 
/*     */     
/* 177 */     throw new RuntimeException("EmailBatch not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public Email insertEmail(Email email) {
/* 182 */     HibernateTemplate template = getHibernateTemplate();
/* 183 */     email.setCreateTime(new Date());
/* 184 */     email.setWaitToSend(YesNo.YES);
/* 185 */     email.setFailCount(new Integer(0));
/* 186 */     template.save(email);
/* 187 */     return email;
/*     */   }
/*     */   
/*     */   public EmailBatch insertEmailBatch(EmailBatch emailBatch) {
/* 191 */     HibernateTemplate template = getHibernateTemplate();
/* 192 */     template.save(emailBatch);
/* 193 */     return emailBatch;
/*     */   }
/*     */   
/*     */   public void saveEmailBody(final Integer id, final String body) {
/* 197 */     getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/*     */             
/* 201 */             try { EmailBody eb = (EmailBody)session.get(EmailBody.class, id);
/* 202 */               if (eb == null) return null; 
/* 203 */               Clob content = Hibernate.createClob(body);
/* 204 */               eb.setBody(content);
/* 205 */               session.update(eb); }
/*     */             
/* 207 */             catch (Exception e)
/* 208 */             { EmailBody eb; eb.printStackTrace(); }
/*     */             finally
/* 210 */             { if (session != null && session.isOpen())
/* 211 */                 session.close();  }  if (session != null && session.isOpen()) session.close();
/*     */ 
/*     */             
/* 214 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveEmailBatchBody(final Integer id, final String body) {
/* 221 */     getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/*     */             
/* 225 */             try { EmailBatchBody eb = (EmailBatchBody)session.get(EmailBatchBody.class, id);
/* 226 */               if (eb == null) return null; 
/* 227 */               Clob content = Hibernate.createClob(body);
/* 228 */               eb.setBody(content);
/* 229 */               session.update(eb); }
/*     */             
/* 231 */             catch (Exception e)
/* 232 */             { EmailBatchBody eb; eb.printStackTrace(); }
/*     */             finally
/* 234 */             { if (session != null && session.isOpen())
/* 235 */                 session.close();  }  if (session != null && session.isOpen()) session.close();
/*     */ 
/*     */             
/* 238 */             return null;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 248 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/*     */       {
/* 250 */         EmailQueryCondition.ID_EQ, "email.id = ?"
/*     */ 
/*     */ 
/*     */       
/*     */       },
/*     */ 
/*     */       
/*     */       {
/*     */         
/* 259 */         EmailQueryCondition.MAILFROM_LIKE, "email.mailFrom like ?", new LikeConvert()
/* 260 */       }, { EmailQueryCondition.MAILTO_LIKE, "email.mailTo like ?", new LikeConvert()
/* 261 */       }, { EmailQueryCondition.SUBJECT_LIKE, "email.subject like ?", new LikeConvert()
/* 262 */       }, { EmailQueryCondition.BODY_LIKE, "email.body like ?", new LikeConvert()
/* 263 */       }, { EmailQueryCondition.CREATETIME_BT, "email.createTime between ? and ?"
/* 264 */       }, { EmailQueryCondition.SENTTIME_BT, "email.sentTime between ? and ?"
/* 265 */       }, { EmailQueryCondition.FAILCOUNT_GE, "email.failCount >= ?"
/* 266 */       }, { EmailQueryCondition.WAITTOSEND_EQ, "email.waitToSend = ?" }
/*     */     };
/*     */   
/* 269 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/*     */       {
/* 271 */         EmailQueryOrder.ID, "email.id"
/*     */       
/*     */       },
/* 274 */       { EmailQueryOrder.MAILFROM, "email.mailFrom"
/* 275 */       }, { EmailQueryOrder.MAILTO, "email.mailTo"
/* 276 */       }, { EmailQueryOrder.SUBJECT, "email.subject"
/* 277 */       }, { EmailQueryOrder.BODY, "email.body"
/* 278 */       }, { EmailQueryOrder.CREATETIME, "email.createTime"
/* 279 */       }, { EmailQueryOrder.SENTTIME, "email.sentTime"
/* 280 */       }, { EmailQueryOrder.FAILCOUNT, "email.failCount"
/* 281 */       }, { EmailQueryOrder.WAITTOSEND, "email.waitToSend" }
/*     */     };
/*     */   
/*     */   public int getEmailListCount(Map conditions) {
/* 285 */     return getListCount(conditions, "select count(*) from Email email", QUERY_CONDITIONS);
/*     */   }
/*     */   
/*     */   public List getEmailList(Map conditions, int pageNo, int pageSize, EmailQueryOrder order, boolean descend) {
/* 289 */     return getList(conditions, pageNo, pageSize, order, descend, "from Email email", QUERY_CONDITIONS, QUERY_ORDERS);
/*     */   }
/*     */   
/*     */   public List getWaitToSendEmailList() {
/* 293 */     return getHibernateTemplate().find(
/* 294 */         "from Email email where email.waitToSend = ? order by email.id ", 
/* 295 */         new Integer(YesNo.YES.getEnumCode().toString()), (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public void deleteEmailBatch(String refNo) {
/* 299 */     String hql = "from EmailBatch as eb where eb.refNo = '" + refNo + "' and eb.isSend = " + YesNo.NO;
/* 300 */     getHibernateTemplate().delete(hql);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/EmailDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */