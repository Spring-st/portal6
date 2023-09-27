/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.SynBaseDAO;
/*     */ import com.aof.dao.sync.SyncDAO;
/*     */ import com.aof.model.admin.City;
/*     */ import com.aof.model.admin.Country;
/*     */ import com.aof.model.admin.Currency;
/*     */ import com.aof.model.admin.Role;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.UserRole;
/*     */ import com.aof.model.basic.SycSleepTime;
/*     */ import com.aof.model.basic.SyncLog;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.ExportStatus;
/*     */ import com.aof.model.metadata.Gender;
/*     */ import com.aof.model.metadata.SupplierConfirmStatus;
/*     */ import com.aof.model.metadata.SupplierPromoteStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.sync.shared.QADCtrl;
/*     */ import com.aof.model.sync.shared.QADSupplier;
/*     */ import com.aof.service.basic.SycSleepTimeManager;
/*     */ import com.aof.service.basic.impl.SycSleepTimeManagerImpl;
/*     */ import com.shcnc.utils.MD5;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.List;
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
/*     */ public class SupplierThread
/*     */   extends Thread
/*     */ {
/*  47 */   private Log log = LogFactory.getLog(SupplierThread.class);
/*     */   
/*     */   private SynBaseDAO dao;
/*     */   
/*     */   private SyncDAO daoShared;
/*     */   
/*     */   private String time;
/*     */   private SycSleepTimeManager sycSleepTimeManager;
/*  55 */   SycSleepTimeManager manager = (SycSleepTimeManager)new SycSleepTimeManagerImpl();
/*     */ 
/*     */ 
/*     */   
/*     */   public SupplierThread(SycSleepTimeManager sycSleepTimeManager, SynBaseDAO dao, SyncDAO daoShared, String time) {
/*  60 */     this.sycSleepTimeManager = sycSleepTimeManager;
/*  61 */     this.dao = dao;
/*  62 */     this.time = time;
/*  63 */     this.daoShared = daoShared;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     while (true) {
/*     */       try {
/*  70 */         SycSleepTime sycSleepTime = new SycSleepTime();
/*  71 */         sycSleepTime = this.sycSleepTimeManager.getSycSleepTime("供应商信息");
/*  72 */         if (sycSleepTime != null) {
/*  73 */           this.time = String.valueOf(Integer.valueOf(sycSleepTime
/*  74 */                 .getSleepTime()).intValue() * 1000 * 60);
/*     */         }
/*  76 */         sleep(Long.parseLong(this.time));
/*  77 */         Date date = new Date();
/*  78 */         SimpleDateFormat format = new SimpleDateFormat(
/*  79 */             "yyyy/MM/dd hh:mm:ss");
/*  80 */         System.out.println("供应商信息同步-------------------------1-" + 
/*  81 */             format.format(date));
/*     */         
/*  83 */         String beginSql = "select ctrl.xxqad_seq from QADCtrl ctrl  where ctrl.xxqad_portal=0 and ctrl.xxqad_table_qty > 0";
/*     */ 
/*     */         
/*  86 */         List<String> suppList = this.daoShared
/*  87 */           .getObjectList(String.valueOf(beginSql) + 
/*  88 */             " and  ctrl.xxqad_table='xxqad_vd_mstr' group by ctrl.xxqad_seq");
/*  89 */         supplierSyncRead(suppList);
/*  90 */         System.out.println("--------------------供应商信息同步完成");
/*  91 */       } catch (InterruptedException e) {
/*  92 */         e.printStackTrace(); continue;
/*     */       } finally {
/*  94 */         System.gc();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void supplierSyncRead(List<String> sqlList) {
/*     */     try {
/* 104 */       for (String sql : sqlList) {
/* 105 */         List<QADSupplier> syncSupplierList = this.daoShared
/* 106 */           .getObjectList("from QADSupplier supplier where supplier.xxqad_vd_seq='" + 
/* 107 */             sql + 
/* 108 */             "' and supplier.xxqad_vd_portalread = 0");
/* 109 */         Boolean issyncok = Boolean.valueOf(true);
/* 110 */         for (QADSupplier shared : syncSupplierList) {
/*     */           try {
/* 112 */             insertSupp(shared, this.dao, this.daoShared);
/* 113 */           } catch (Exception e) {
/* 114 */             issyncok = Boolean.valueOf(false);
/* 115 */             e.printStackTrace();
/* 116 */             insertSystemLog("RedMinuteSyncJob", 
/* 117 */                 "supplierSyncRead1", e.getMessage(), "1");
/*     */           } 
/*     */         } 
/* 120 */         if (issyncok.booleanValue()) {
/*     */           
/* 122 */           List<QADCtrl> ctrlList = this.daoShared
/* 123 */             .getObjectList("from QADCtrl ctrl where ctrl.xxqad_seq='" + 
/* 124 */               sql + "' and ctrl.xxqad_portal=0");
/* 125 */           for (QADCtrl ctrl : ctrlList) {
/* 126 */             ctrl.setXxqad_portal("1");
/* 127 */             this.daoShared.updateObject(ctrl);
/* 128 */             this.daoShared.commit();
/*     */           } 
/*     */         } 
/*     */       } 
/* 132 */     } catch (Exception e) {
/*     */       
/* 134 */       insertSystemLog("RedMinuteSyncJob", "supplierSyncRead", 
/* 135 */           e.getMessage(), "1");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void insertSupp(QADSupplier shared, SynBaseDAO dao, SyncDAO daoShared) {
/* 147 */     Boolean isInsertSupplier = Boolean.valueOf(false);
/*     */     
/* 149 */     String code = shared.getXxqad_vd_addr();
/* 150 */     String name = shared.getXxqad_vd_name();
/* 151 */     String address1 = shared.getXxqad_vd_line1();
/* 152 */     String address2 = shared.getXxqad_vd_line2();
/* 153 */     String address3 = shared.getXxqad_vd_line3();
/* 154 */     String country = shared.getXxqad_vd_country();
/* 155 */     String city = shared.getXxqad_vd_city();
/* 156 */     String phone = shared.getXxqad_vd_phone();
/* 157 */     String vd_fax = shared.getXxqad_vd_fax();
/* 158 */     String vd_pst = shared.getXxqad_vd_pst_id();
/* 159 */     String vd_attn = shared.getXxqad_vd_attn();
/* 160 */     String vd_curr = shared.getXxqad_vd_curr();
/* 161 */     String vd_promo = shared.getXxqad_vd_promo();
/* 162 */     String remark = shared.getXxqad_vd_rmks();
/*     */ 
/*     */ 
/*     */     
/* 166 */     Country coun = null;
/*     */     
/* 168 */     if (country != null) {
/* 169 */       List<Country> listcountry = dao
/* 170 */         .getObjectList("from Country cou where cou.engName='" + 
/* 171 */           country + "' ");
/* 172 */       if (listcountry.size() > 0) {
/* 173 */         coun = listcountry.get(0);
/*     */       } else {
/* 175 */         List<Country> countries = dao
/* 176 */           .getObjectList("from Country cou where cou.engName='China' ");
/* 177 */         coun = countries.get(0);
/*     */       } 
/*     */     } else {
/* 180 */       List<Country> countries = dao
/* 181 */         .getObjectList("from Country cou where cou.engName='China' ");
/* 182 */       coun = countries.get(0);
/*     */     } 
/*     */     
/* 185 */     Currency currency = (Currency)dao.getObject(Currency.class, vd_curr);
/* 186 */     if (currency == null) {
/* 187 */       currency = new Currency();
/* 188 */       currency.setCode(vd_curr);
/* 189 */       currency.setName(vd_curr);
/* 190 */       currency.setEnabled(EnabledDisabled.ENABLED);
/* 191 */       dao.saveObject(currency);
/* 192 */       dao.commit();
/*     */     } 
/*     */ 
/*     */     
/* 196 */     City city_new = null;
/* 197 */     List<City> cityList = dao
/* 198 */       .getObjectList("select city from City city where city.chnName ='" + 
/* 199 */         city + "' or city.engName ='" + city + "' ");
/* 200 */     if (cityList.size() > 0) {
/* 201 */       city_new = cityList.get(0);
/*     */     } else {
/* 203 */       List<City> cities = dao
/* 204 */         .getObjectList("from City city where city.chnName='北京' ");
/* 205 */       city_new = cities.get(0);
/*     */     } 
/*     */     
/* 208 */     Supplier supplier = null;
/* 209 */     List<Supplier> supplierList = dao
/* 210 */       .getObjectList("from Supplier supplier where supplier.code='" + 
/* 211 */         code + "'");
/*     */     
/* 213 */     if (supplierList.size() > 0) {
/* 214 */       supplier = supplierList.get(0);
/* 215 */       supplier.setAddress1(address1);
/* 216 */       supplier.setAddress2(address2);
/* 217 */       supplier.setAddress3(address3);
/* 218 */       supplier.setTelephone1(phone);
/* 219 */       supplier.setFax1(vd_fax);
/* 220 */       supplier.setPost(vd_pst);
/* 221 */       supplier.setContact(vd_attn);
/* 222 */       supplier.setCode(code);
/* 223 */       supplier.setName(name);
/* 224 */       supplier.setEnabled(EnabledDisabled.ENABLED);
/* 225 */       supplier.setConfirmed(YesNo.YES);
/* 226 */       supplier.setPromoteStatus(SupplierPromoteStatus.GLOBAL);
/* 227 */       supplier.setConfirmStatus(SupplierConfirmStatus.NEW);
/*     */       
/* 229 */       supplier.setExportStatus(ExportStatus.EXPORTED);
/* 230 */       supplier.setEmailTimes(0);
/* 231 */       supplier.setContractStartDate(new Date());
/* 232 */       supplier.setAirTicket(YesNo.YES);
/*     */       
/* 234 */       dao.updateObject(supplier);
/*     */     } else {
/*     */       
/* 237 */       supplier = new Supplier();
/* 238 */       supplier.setAddress1(address1);
/* 239 */       supplier.setAddress2(address2);
/* 240 */       supplier.setAddress3(address3);
/* 241 */       supplier.setTelephone1(phone);
/* 242 */       supplier.setFax1(vd_fax);
/* 243 */       supplier.setPost(vd_pst);
/* 244 */       supplier.setContact(vd_attn);
/* 245 */       supplier.setCode(code);
/* 246 */       supplier.setName(name);
/* 247 */       supplier.setCountry(coun);
/* 248 */       supplier.setEnabled(EnabledDisabled.ENABLED);
/* 249 */       supplier.setConfirmed(YesNo.YES);
/* 250 */       supplier.setPromoteStatus(SupplierPromoteStatus.GLOBAL);
/* 251 */       supplier.setConfirmStatus(SupplierConfirmStatus.NEW);
/*     */       
/* 253 */       supplier.setExportStatus(ExportStatus.EXPORTED);
/* 254 */       supplier.setEmailTimes(0);
/* 255 */       supplier.setContractStartDate(new Date());
/* 256 */       supplier.setAirTicket(YesNo.YES);
/*     */       
/* 258 */       dao.saveObject(supplier);
/* 259 */       dao.commit();
/* 260 */       isInsertSupplier = Boolean.valueOf(true);
/*     */     } 
/* 262 */     isInsertSupplier = Boolean.valueOf(true);
/* 263 */     List<Site> listSites = dao
/* 264 */       .getObjectList("from Site site where site.name='" + code + "'");
/* 265 */     if (isInsertSupplier.booleanValue())
/*     */     {
/* 267 */       if (listSites.size() == 0) {
/* 268 */         Site site = new Site();
/* 269 */         site.setName(code);
/* 270 */         site.setActivity(name);
/* 271 */         site.setCanRecharge(YesNo.YES);
/* 272 */         site.setEnabled(EnabledDisabled.ENABLED);
/* 273 */         site.setSupplier(supplier);
/* 274 */         site.setBaseCurrency(currency);
/* 275 */         site.setCity(city_new);
/* 276 */         dao.saveObject(site);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 281 */         List<User> users = dao
/* 282 */           .getObjectList("from User user where user.loginName='" + 
/* 283 */             code + "' ");
/* 284 */         if (users.size() == 0) {
/* 285 */           User user = new User();
/* 286 */           user.setName(name);
/*     */           try {
/* 288 */             user.setPassword(MD5.getDigestString(code));
/* 289 */           } catch (Exception e) {
/* 290 */             e.printStackTrace();
/*     */           } 
/*     */           
/* 293 */           user.setLoginName(code);
/* 294 */           user.setGender(Gender.MALE);
/* 295 */           user.setEmail("");
/* 296 */           user.setPrimarySite(site);
/* 297 */           user.setLoginFailedCount(0);
/* 298 */           user.setLocale("zh");
/* 299 */           user.setEnabled(EnabledDisabled.ENABLED);
/* 300 */           user.setLastLoginTime(new Date());
/* 301 */           user.setType(YesNo.YES);
/* 302 */           dao.saveObject(user);
/*     */           
/* 304 */           UserRole userRole = new UserRole();
/* 305 */           userRole.setRole(new Role(Integer.valueOf(242)));
/* 306 */           userRole.setUser(user);
/* 307 */           userRole.setGrantedSite(site);
/* 308 */           dao.saveObject(userRole);
/* 309 */           dao.commit();
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 315 */     shared.setXxqad_vd_portalread("1");
/* 316 */     daoShared.updateObject(shared);
/* 317 */     daoShared.commit();
/*     */   }
/*     */ 
/*     */   
/*     */   private void insertSystemLog(String action, String content, String sync_describe, String syncResults) {
/* 322 */     SyncLog log = new SyncLog();
/* 323 */     log.setSync_date(new Date());
/* 324 */     log.setSync_content(content);
/* 325 */     log.setSync_describe(sync_describe);
/* 326 */     log.setSync_object(action);
/* 327 */     log.setSync_results(syncResults);
/* 328 */     this.dao.saveObject(log);
/*     */   }
/*     */   
/*     */   public SynBaseDAO getDao() {
/* 332 */     return this.dao;
/*     */   }
/*     */   
/*     */   public void setDao(SynBaseDAO dao) {
/* 336 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public SyncDAO getDaoShared() {
/* 340 */     return this.daoShared;
/*     */   }
/*     */   
/*     */   public void setDaoShared(SyncDAO daoShared) {
/* 344 */     this.daoShared = daoShared;
/*     */   }
/*     */   
/*     */   public String getTime() {
/* 348 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(String time) {
/* 352 */     this.time = time;
/*     */   }
/*     */   
/*     */   public SycSleepTimeManager getSycSleepTimeManager() {
/* 356 */     return this.sycSleepTimeManager;
/*     */   }
/*     */   
/*     */   public void setSycSleepTimeManager(SycSleepTimeManager sycSleepTimeManager) {
/* 360 */     this.sycSleepTimeManager = sycSleepTimeManager;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/SupplierThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */