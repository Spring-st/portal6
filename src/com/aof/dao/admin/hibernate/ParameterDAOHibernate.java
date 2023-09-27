/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.ParameterDAO;
/*     */ import com.aof.model.admin.DataTransferParameter;
/*     */ import com.aof.model.admin.GlobalMailReminder;
/*     */ import com.aof.model.admin.GlobalParameter;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.SiteMailReminder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.ExportFileType;
/*     */ import com.aof.model.metadata.GlobalMailReminderType;
/*     */ import java.io.Serializable;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.sf.hibernate.Hibernate;
/*     */ import net.sf.hibernate.HibernateException;
/*     */ import net.sf.hibernate.Query;
/*     */ import net.sf.hibernate.Session;
/*     */ import net.sf.hibernate.type.Type;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
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
/*     */ public class ParameterDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements ParameterDAO
/*     */ {
/*     */   public GlobalParameter getGlobalParameter() {
/*  44 */     List<GlobalParameter> ls = getHibernateTemplate().find("from GlobalParameter");
/*  45 */     if (ls.size() == 0) {
/*  46 */       GlobalParameter para = new GlobalParameter();
/*  47 */       getHibernateTemplate().save(para);
/*  48 */       return para;
/*     */     } 
/*  50 */     return ls.get(0);
/*     */   }
/*     */   
/*     */   public GlobalParameter updateGlobalParameter(GlobalParameter globalParameter) {
/*  54 */     Integer id = globalParameter.getId();
/*  55 */     if (id == null) {
/*  56 */       throw new RuntimeException("cannot save a globalParameter with null id");
/*     */     }
/*  58 */     GlobalParameter oldGlobalParameter = getGlobalParameter();
/*  59 */     if (oldGlobalParameter != null) {
/*     */       try {
/*  61 */         PropertyUtils.copyProperties(oldGlobalParameter, globalParameter);
/*     */       }
/*  63 */       catch (Exception e) {
/*     */         
/*  65 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/*  67 */       getHibernateTemplate().update(oldGlobalParameter);
/*  68 */       return oldGlobalParameter;
/*     */     } 
/*     */ 
/*     */     
/*  72 */     throw new RuntimeException("globalParameter not found");
/*     */   }
/*     */ 
/*     */   
/*     */   public GlobalMailReminder getGlobalMailReminder(GlobalMailReminderType type) {
/*  77 */     if (type == null) {
/*  78 */       return null;
/*     */     }
/*  80 */     return (GlobalMailReminder)getHibernateTemplate().get(GlobalMailReminder.class, (Serializable)type);
/*     */   }
/*     */   
/*     */   public List getGlobalMailReminderList() {
/*  84 */     return getHibernateTemplate().find("from GlobalMailReminder gmr order by gmr.type");
/*     */   }
/*     */   
/*     */   private void deleteGlobalMailReminderList() {
/*  88 */     getHibernateTemplate().delete("from GlobalMailReminder");
/*     */   }
/*     */   
/*     */   public void updateGlobalMailReminder(List reminderList) {
/*  92 */     deleteGlobalMailReminderList();
/*  93 */     for (Iterator<GlobalMailReminder> itor = reminderList.iterator(); itor.hasNext(); ) {
/*  94 */       GlobalMailReminder reminder = itor.next();
/*  95 */       getHibernateTemplate().save(reminder);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public DataTransferParameter getDataTransferParameter(Site site) {
/* 101 */     List<DataTransferParameter> result = getHibernateTemplate().find("from DataTransferParameter dtp where dtp.site.id=?", site.getId(), (Type)Hibernate.INTEGER);
/* 102 */     if (result.size() == 0) {
/* 103 */       DataTransferParameter dataTransferParameter = new DataTransferParameter();
/* 104 */       dataTransferParameter.setSite(site);
/* 105 */       dataTransferParameter.setExportFileType(ExportFileType.TEXT);
/* 106 */       getHibernateTemplate().save(dataTransferParameter);
/* 107 */       return dataTransferParameter;
/*     */     } 
/* 109 */     return result.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DataTransferParameter updateDataTransferParameter(DataTransferParameter dataTransferParameter) {
/* 115 */     Integer id = dataTransferParameter.getSite().getId();
/* 116 */     if (id == null) {
/* 117 */       throw new RuntimeException("cannot save a dataTransferParameter with null site id");
/*     */     }
/* 119 */     DataTransferParameter oldDataTransferParameter = getDataTransferParameter(dataTransferParameter.getSite());
/* 120 */     if (oldDataTransferParameter != null) {
/*     */       try {
/* 122 */         PropertyUtils.copyProperties(oldDataTransferParameter, dataTransferParameter);
/*     */       }
/* 124 */       catch (Exception e) {
/*     */         
/* 126 */         throw new RuntimeException("copy error��" + e);
/*     */       } 
/* 128 */       getHibernateTemplate().update(oldDataTransferParameter);
/* 129 */       getHibernateTemplate().flush();
/* 130 */       return oldDataTransferParameter;
/*     */     } 
/*     */ 
/*     */     
/* 134 */     throw new RuntimeException("oldDataTransferParameter not found");
/*     */   }
/*     */ 
/*     */   
/*     */   private void deleteSiteMailReminderList(Site site) {
/* 139 */     getHibernateTemplate().delete("from SiteMailReminder smr where smr.site.id=?", site.getId(), (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public void updateSiteMailReminder(Site site, List reminderList) {
/* 143 */     deleteSiteMailReminderList(site);
/* 144 */     for (Iterator<SiteMailReminder> itor = reminderList.iterator(); itor.hasNext(); ) {
/* 145 */       SiteMailReminder reminder = itor.next();
/* 146 */       getHibernateTemplate().save(reminder);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List getSiteMailReminderList(Site site) {
/* 152 */     return getHibernateTemplate().find("from SiteMailReminder smr where smr.site.id=? order by smr.type", site.getId(), (Type)Hibernate.INTEGER);
/*     */   }
/*     */   
/*     */   public Set getEnabledSiteSetWithMailReminders() {
/* 156 */     List smrList = (List)getHibernateTemplate().execute(new HibernateCallback()
/*     */         {
/*     */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 159 */             Query q = session.createQuery("from SiteMailReminder smr where smr.site.enabled=?");
/*     */             
/*     */             try {
/* 162 */               q.setParameter(0, EnabledDisabled.ENABLED.getEnumCode());
/*     */             }
/* 164 */             catch (Exception e) {
/* 165 */               e.getStackTrace();
/*     */             } finally {
/* 167 */               if (session != null && session.isOpen()) {
/* 168 */                 session.close();
/*     */               }
/*     */             } 
/* 171 */             return q.list();
/*     */           }
/*     */         });
/*     */     
/* 175 */     Set<Site> retVal = new HashSet();
/* 176 */     for (Iterator<SiteMailReminder> iter = smrList.iterator(); iter.hasNext(); ) {
/* 177 */       SiteMailReminder smr = iter.next();
/* 178 */       Site site = smr.getSite();
/* 179 */       if (site.getMailReminders() == null) site.setMailReminders(new HashMap<Object, Object>()); 
/* 180 */       site.getMailReminders().put(smr.getType(), smr);
/* 181 */       retVal.add(site);
/*     */     } 
/* 183 */     return retVal;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/ParameterDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */