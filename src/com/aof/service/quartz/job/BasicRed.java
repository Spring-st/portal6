/*      */ package com.aof.service.quartz.job;
/*      */ 
/*      */ import com.aof.dao.admin.SynBaseDAO;
/*      */ import com.aof.dao.sync.SyncDAO;
/*      */ import com.aof.model.admin.City;
/*      */ import com.aof.model.admin.Country;
/*      */ import com.aof.model.admin.Currency;
/*      */ import com.aof.model.admin.FinishedSaiheRelation;
/*      */ import com.aof.model.admin.Role;
/*      */ import com.aof.model.admin.Site;
/*      */ import com.aof.model.admin.Supplier;
/*      */ import com.aof.model.admin.User;
/*      */ import com.aof.model.admin.UserRole;
/*      */ import com.aof.model.basic.BasicPartPrice;
/*      */ import com.aof.model.basic.StorageLocation;
/*      */ import com.aof.model.basic.SupplierPart;
/*      */ import com.aof.model.basic.WmsPart;
/*      */ import com.aof.model.comprehensive.Bom;
/*      */ import com.aof.model.inventory.InventoryDetial;
/*      */ import com.aof.model.metadata.CurrencyType;
/*      */ import com.aof.model.metadata.EnabledDisabled;
/*      */ import com.aof.model.metadata.ExportStatus;
/*      */ import com.aof.model.metadata.Gender;
/*      */ import com.aof.model.metadata.PurchaseOrderStatus;
/*      */ import com.aof.model.metadata.SupplierConfirmStatus;
/*      */ import com.aof.model.metadata.SupplierPromoteStatus;
/*      */ import com.aof.model.metadata.YesNo;
/*      */ import com.aof.model.po.PurchaseOrderInspectionPending;
/*      */ import com.aof.model.po.PurchaseOrderInspectionPendingItem;
/*      */ import com.aof.model.product.BasicCustomer;
/*      */ import com.aof.model.product.DailyProductPlan;
/*      */ import com.aof.model.product.ProductGoline;
/*      */ import com.aof.model.product.SalesOrder;
/*      */ import com.aof.model.product.SalesOrderItem;
/*      */ import com.aof.model.product.WorkOrderBom;
/*      */ import com.aof.model.schedule.EdiProduction;
/*      */ import com.aof.model.schedule.NjitNpoPlan;
/*      */ import com.aof.model.schedule.Production72HourPlan;
/*      */ import com.aof.model.schedule.ProductionDayPlan;
/*      */ import com.aof.model.schedule.ProjectedInventory;
/*      */ import com.aof.model.sync.shared.ProductOutGoline;
/*      */ import com.aof.model.sync.shared.QADAdMstr;
/*      */ import com.aof.model.sync.shared.QADBom;
/*      */ import com.aof.model.sync.shared.QADBomInfo;
/*      */ import com.aof.model.sync.shared.QADCustomerWmsPart;
/*      */ import com.aof.model.sync.shared.QADDailyProductionSchedule;
/*      */ import com.aof.model.sync.shared.QADPcMstr;
/*      */ import com.aof.model.sync.shared.QADPurchaseOrder;
/*      */ import com.aof.model.sync.shared.QADStorage;
/*      */ import com.aof.model.sync.shared.QADSupplier;
/*      */ import com.aof.model.sync.shared.QADSupplierWmsPart;
/*      */ import com.aof.model.sync.shared.QADWmsPart;
/*      */ import com.aof.model.sync.shared.XbmwoDet;
/*      */ import com.aof.model.sync.shared.XxqadDailyPlanDet;
/*      */ import com.aof.model.sync.shared.XxqadHourPlanDet;
/*      */ import com.aof.model.sync.shared.XxqadMrpDet;
/*      */ import com.shcnc.utils.MD5;
/*      */ import java.math.BigDecimal;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Date;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class BasicRed
/*      */ {
/*      */   protected void insertPart(QADWmsPart shared, SynBaseDAO dao, SyncDAO daoShared) {
/*   78 */     SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
/*   79 */     String xxqadPtPart = shared.getXxqadPtPart();
/*   80 */     String xxqadPtUm = shared.getXxqadPtUm();
/*   81 */     String xxqadPtDesc1 = shared.getXxqadPtDesc1();
/*   82 */     String xxqadPtDesc2 = shared.getXxqadPtDesc2();
/*   83 */     String xxqadPtPartType = shared.getXxqadPtPartType();
/*   84 */     String xxqadPtProdLine = shared.getXxqadPtProdLine();
/*   85 */     String xxqadPtStatus = shared.getXxqadPtStatus();
/*   86 */     Integer xxqadPtSize = shared.getXxqadPtSize();
/*   87 */     String xxqadPtGroup = shared.getXxqadPtGroup();
/*   88 */     Integer xxqadPtShipWt = shared.getXxqadPtShipWt();
/*   89 */     Integer xxqadPtNetWt = shared.getXxqadPtNetWt();
/*   90 */     String xxqadPtVend = shared.getXxqadPtVend();
/*   91 */     String xxqadPtSite = shared.getXxqadPtSite();
/*   92 */     String xxqadPtDomain = shared.getXxqadPtDomain();
/*   93 */     String xxqadPtDraw = shared.getXxqadPtDraw();
/*   94 */     BigDecimal xxqadPtFrClass = shared.getXxqadPtFrClass();
/*   95 */     String xxqadDrwgLoc = shared.getXxqad_pt_drwg_loc();
/*      */ 
/*      */ 
/*      */     
/*   99 */     WmsPart oldPart = (WmsPart)dao.getObject(WmsPart.class, xxqadPtPart);
/*  100 */     if (oldPart == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  105 */       WmsPart part = new WmsPart();
/*  106 */       part.setId(xxqadPtPart);
/*  107 */       part.setUnit(xxqadPtUm);
/*  108 */       part.setDescribe1(xxqadPtDesc1);
/*  109 */       part.setDescribe2(xxqadPtDesc2);
/*  110 */       part.setName(xxqadPtDesc1);
/*  111 */       part.setPartType(xxqadPtPartType);
/*  112 */       part.setEnabled(EnabledDisabled.ENABLED);
/*  113 */       part.setProductLine(xxqadPtProdLine);
/*  114 */       if (xxqadPtStatus.equals("AC")) {
/*  115 */         part.setFreeze_status(YesNo.NO);
/*  116 */       } else if (xxqadPtStatus.equals("HOLD")) {
/*  117 */         part.setFreeze_status(YesNo.YES);
/*      */       } 
/*  119 */       part.setOrd_mult(new BigDecimal(xxqadPtSize.intValue()));
/*  120 */       part.setProductClass(xxqadPtGroup);
/*  121 */       part.setLowQty(new BigDecimal(xxqadPtShipWt.intValue()));
/*  122 */       part.setHighQty(new BigDecimal(xxqadPtNetWt.intValue()));
/*  123 */       part.setVend(xxqadPtVend);
/*  124 */       part.setProductSpecifications(xxqadPtDraw);
/*      */       
/*  126 */       part.setDomain(xxqadPtDomain);
/*  127 */       part.setSecurityQty(xxqadPtFrClass);
/*  128 */       part.setDrwgLoc(xxqadDrwgLoc);
/*  129 */       dao.saveObject(part);
/*      */     } else {
/*      */       
/*  132 */       oldPart.setUnit(xxqadPtUm);
/*  133 */       oldPart.setDescribe1(xxqadPtDesc1);
/*  134 */       oldPart.setDescribe2(xxqadPtDesc2);
/*  135 */       oldPart.setName(xxqadPtDesc1);
/*  136 */       oldPart.setPartType(xxqadPtPartType);
/*  137 */       oldPart.setEnabled(EnabledDisabled.ENABLED);
/*  138 */       oldPart.setProductLine(xxqadPtProdLine);
/*  139 */       if (xxqadPtStatus.equals("AC")) {
/*  140 */         oldPart.setFreeze_status(YesNo.NO);
/*  141 */       } else if (xxqadPtStatus.equals("HOLD")) {
/*  142 */         oldPart.setFreeze_status(YesNo.YES);
/*      */       } 
/*  144 */       oldPart.setOrd_mult(new BigDecimal(xxqadPtSize.intValue()));
/*  145 */       oldPart.setProductClass(xxqadPtGroup);
/*  146 */       oldPart.setLowQty(new BigDecimal(xxqadPtShipWt.intValue()));
/*  147 */       oldPart.setHighQty(new BigDecimal(xxqadPtNetWt.intValue()));
/*  148 */       oldPart.setVend(xxqadPtVend);
/*  149 */       oldPart.setDomain(xxqadPtDomain);
/*  150 */       oldPart.setSecurityQty(xxqadPtFrClass);
/*  151 */       oldPart.setProductSpecifications(xxqadPtDraw);
/*  152 */       oldPart.setDrwgLoc(xxqadDrwgLoc);
/*  153 */       dao.updateObject(oldPart);
/*      */     } 
/*      */ 
/*      */     
/*  157 */     shared.setXxqadPtPortalread("1");
/*  158 */     daoShared.updateObject(shared);
/*  159 */     dao.commit();
/*  160 */     daoShared.commit();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void insertSupp(QADSupplier shared, SynBaseDAO dao, SyncDAO daoShared) {
/*  170 */     Boolean isInsertSupplier = Boolean.valueOf(false);
/*      */     
/*  172 */     String code = shared.getXxqad_vd_addr();
/*  173 */     String name = shared.getXxqad_vd_name();
/*  174 */     String address1 = shared.getXxqad_vd_line1();
/*  175 */     String address2 = shared.getXxqad_vd_line2();
/*  176 */     String address3 = shared.getXxqad_vd_line3();
/*  177 */     String country = shared.getXxqad_vd_country();
/*  178 */     String city = shared.getXxqad_vd_city();
/*  179 */     String phone = shared.getXxqad_vd_phone();
/*  180 */     String vd_fax = shared.getXxqad_vd_fax();
/*  181 */     String vd_pst = shared.getXxqad_vd_pst_id();
/*  182 */     String vd_attn = shared.getXxqad_vd_attn();
/*  183 */     String vd_curr = shared.getXxqad_vd_curr();
/*  184 */     String vd_promo = shared.getXxqad_vd_promo();
/*  185 */     String remark = shared.getXxqad_vd_rmks();
/*      */ 
/*      */ 
/*      */     
/*  189 */     Country coun = null;
/*      */     
/*  191 */     if (country != null) {
/*  192 */       List<Country> listcountry = dao
/*  193 */         .getObjectList("from Country cou where cou.engName='" + 
/*  194 */           country + "' ");
/*  195 */       if (listcountry.size() > 0) {
/*  196 */         coun = listcountry.get(0);
/*      */       } else {
/*  198 */         List<Country> countries = dao
/*  199 */           .getObjectList("from Country cou where cou.engName='China' ");
/*  200 */         coun = countries.get(0);
/*      */       } 
/*      */     } else {
/*  203 */       List<Country> countries = dao
/*  204 */         .getObjectList("from Country cou where cou.engName='China' ");
/*  205 */       coun = countries.get(0);
/*      */     } 
/*      */     
/*  208 */     Currency currency = (Currency)dao.getObject(Currency.class, vd_curr);
/*  209 */     if (currency == null) {
/*  210 */       currency = new Currency();
/*  211 */       currency.setCode(vd_curr);
/*  212 */       currency.setName(vd_curr);
/*  213 */       currency.setEnabled(EnabledDisabled.ENABLED);
/*  214 */       dao.saveObject(currency);
/*  215 */       dao.commit();
/*      */     } 
/*      */ 
/*      */     
/*  219 */     City city_new = null;
/*  220 */     List<City> cityList = dao
/*  221 */       .getObjectList("select city from City city where city.chnName ='" + 
/*  222 */         city + "' or city.engName ='" + city + "' ");
/*  223 */     if (cityList.size() > 0) {
/*  224 */       city_new = cityList.get(0);
/*      */     } else {
/*  226 */       List<City> cities = dao
/*  227 */         .getObjectList("from City city where city.chnName='北京' ");
/*  228 */       city_new = cities.get(0);
/*      */     } 
/*      */     
/*  231 */     Supplier supplier = null;
/*  232 */     List<Supplier> supplierList = dao
/*  233 */       .getObjectList("from Supplier supplier where supplier.code='" + 
/*  234 */         code + "'");
/*      */     
/*  236 */     if (supplierList.size() > 0) {
/*  237 */       supplier = supplierList.get(0);
/*  238 */       supplier.setAddress1(address1);
/*  239 */       supplier.setAddress2(address2);
/*  240 */       supplier.setAddress3(address3);
/*  241 */       supplier.setTelephone1(phone);
/*  242 */       supplier.setFax1(vd_fax);
/*  243 */       supplier.setPost(vd_pst);
/*  244 */       supplier.setContact(vd_attn);
/*  245 */       supplier.setCode(code);
/*  246 */       supplier.setName(name);
/*  247 */       supplier.setEnabled(EnabledDisabled.ENABLED);
/*  248 */       supplier.setConfirmed(YesNo.YES);
/*  249 */       supplier.setPromoteStatus(SupplierPromoteStatus.GLOBAL);
/*  250 */       supplier.setConfirmStatus(SupplierConfirmStatus.NEW);
/*      */       
/*  252 */       supplier.setExportStatus(ExportStatus.EXPORTED);
/*  253 */       supplier.setEmailTimes(0);
/*  254 */       supplier.setContractStartDate(new Date());
/*  255 */       supplier.setAirTicket(YesNo.YES);
/*      */       
/*  257 */       dao.updateObject(supplier);
/*      */     } else {
/*      */       
/*  260 */       supplier = new Supplier();
/*  261 */       supplier.setAddress1(address1);
/*  262 */       supplier.setAddress2(address2);
/*  263 */       supplier.setAddress3(address3);
/*  264 */       supplier.setTelephone1(phone);
/*  265 */       supplier.setFax1(vd_fax);
/*  266 */       supplier.setPost(vd_pst);
/*  267 */       supplier.setContact(vd_attn);
/*  268 */       supplier.setCode(code);
/*  269 */       supplier.setName(name);
/*  270 */       supplier.setCountry(coun);
/*  271 */       supplier.setEnabled(EnabledDisabled.ENABLED);
/*  272 */       supplier.setConfirmed(YesNo.YES);
/*  273 */       supplier.setPromoteStatus(SupplierPromoteStatus.GLOBAL);
/*  274 */       supplier.setConfirmStatus(SupplierConfirmStatus.NEW);
/*      */       
/*  276 */       supplier.setExportStatus(ExportStatus.EXPORTED);
/*  277 */       supplier.setEmailTimes(0);
/*  278 */       supplier.setContractStartDate(new Date());
/*  279 */       supplier.setAirTicket(YesNo.YES);
/*      */       
/*  281 */       dao.saveObject(supplier);
/*  282 */       dao.commit();
/*  283 */       isInsertSupplier = Boolean.valueOf(true);
/*      */     } 
/*  285 */     isInsertSupplier = Boolean.valueOf(true);
/*  286 */     List<Site> listSites = dao
/*  287 */       .getObjectList("from Site site where site.name='" + code + "'");
/*  288 */     if (isInsertSupplier.booleanValue())
/*      */     {
/*  290 */       if (listSites.size() == 0) {
/*  291 */         Site site = new Site();
/*  292 */         site.setName(code);
/*  293 */         site.setActivity(name);
/*  294 */         site.setCanRecharge(YesNo.YES);
/*  295 */         site.setEnabled(EnabledDisabled.ENABLED);
/*  296 */         site.setSupplier(supplier);
/*  297 */         site.setBaseCurrency(currency);
/*  298 */         site.setCity(city_new);
/*  299 */         dao.saveObject(site);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  304 */         List<User> users = dao
/*  305 */           .getObjectList("from User user where user.loginName='" + 
/*  306 */             code + "' ");
/*  307 */         if (users.size() == 0) {
/*  308 */           User user = new User();
/*  309 */           user.setName(name);
/*      */           try {
/*  311 */             user.setPassword(MD5.getDigestString(code));
/*  312 */           } catch (Exception e) {
/*  313 */             e.printStackTrace();
/*      */           } 
/*      */           
/*  316 */           user.setLoginName(code);
/*  317 */           user.setGender(Gender.MALE);
/*  318 */           user.setEmail("");
/*  319 */           user.setPrimarySite(site);
/*  320 */           user.setLoginFailedCount(0);
/*  321 */           user.setLocale("zh");
/*  322 */           user.setEnabled(EnabledDisabled.ENABLED);
/*  323 */           user.setLastLoginTime(new Date());
/*  324 */           user.setType(YesNo.YES);
/*  325 */           dao.saveObject(user);
/*      */           
/*  327 */           UserRole userRole = new UserRole();
/*  328 */           userRole.setRole(new Role(Integer.valueOf(242)));
/*  329 */           userRole.setUser(user);
/*  330 */           userRole.setGrantedSite(site);
/*  331 */           dao.saveObject(userRole);
/*  332 */           dao.commit();
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  338 */     shared.setXxqad_vd_portalread("1");
/*  339 */     daoShared.updateObject(shared);
/*  340 */     daoShared.commit();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean insertSuppPart(QADSupplierWmsPart shared, SynBaseDAO dao) {
/*  393 */     String suppCode = shared.getXxqad_vp_addr();
/*  394 */     String part = shared.getXxqad_vp_part();
/*  395 */     BigDecimal capacity = shared.getXxqad_vp_comment();
/*      */     
/*  397 */     String sql = "from SupplierPart sp where sp.partId.id='" + part + 
/*  398 */       "' and sp.supplierId.code='" + suppCode + "' ";
/*  399 */     List<SupplierPart> list = dao.getObjectList(sql);
/*      */     
/*  401 */     if (list.size() > 0) {
/*  402 */       SupplierPart supplierPart1 = list.get(0);
/*  403 */       supplierPart1.setSampling_rate(new BigDecimal(0));
/*  404 */       supplierPart1.setCurrencyType(CurrencyType.YUAN);
/*  405 */       supplierPart1.setCapacity(capacity);
/*      */       
/*  407 */       dao.updateObject(supplierPart1);
/*  408 */       dao.commit();
/*  409 */       return true;
/*      */     } 
/*  411 */     List<Supplier> listSupp = dao
/*  412 */       .getObjectList("from Supplier supp where supp.code='" + 
/*  413 */         suppCode + "'");
/*  414 */     List<WmsPart> listPart = dao
/*  415 */       .getObjectList("from WmsPart part where part.id='" + part + 
/*  416 */         "' ");
/*      */     
/*  418 */     Supplier supplier = null;
/*  419 */     WmsPart wmsPart = null;
/*  420 */     if (listSupp.size() > 0) {
/*  421 */       supplier = listSupp.get(0);
/*      */     } else {
/*  423 */       return false;
/*      */     } 
/*  425 */     if (listPart.size() > 0) {
/*  426 */       wmsPart = listPart.get(0);
/*      */     } else {
/*  428 */       return false;
/*      */     } 
/*      */     
/*  431 */     SupplierPart supplierPart = new SupplierPart();
/*  432 */     supplierPart.setSupplierId(supplier);
/*  433 */     supplierPart.setPartId(wmsPart);
/*  434 */     supplierPart.setSampling_rate(new BigDecimal(0));
/*  435 */     supplierPart.setCurrencyType(CurrencyType.YUAN);
/*  436 */     supplierPart.setCapacity(capacity);
/*      */     
/*  438 */     dao.saveObject(supplierPart);
/*  439 */     dao.commit();
/*  440 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void insertLocation(QADStorage shared, SynBaseDAO dao) {
/*  450 */     String code = shared.getXxqad_loc_loc();
/*  451 */     String desc = shared.getXxqad_loc_desc();
/*  452 */     String domain = shared.getXxqad_loc_domain();
/*  453 */     String remark = shared.getXxqad_loc_rmks();
/*  454 */     Date date = shared.getXxqad_loc_createdt();
/*  455 */     String siteName = shared.getXxqad_loc_site();
/*      */ 
/*      */ 
/*      */     
/*  459 */     String sql = "from StorageLocation sl where sl.code = '" + code + "'";
/*  460 */     List<StorageLocation> list = dao.getObjectList(sql);
/*  461 */     dao.getObjectList("from Site site where site.id = 2");
/*      */     
/*  463 */     if (list.size() > 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  472 */       StorageLocation sl = list.get(0);
/*  473 */       sl.setCode(code);
/*  474 */       sl.setDescription(desc);
/*  475 */       sl.setRemark(remark);
/*  476 */       sl.setEnabled(EnabledDisabled.ENABLED);
/*  477 */       sl.setFreeae_status(YesNo.NO);
/*  478 */       sl.setRecommend_status(YesNo.YES);
/*  479 */       sl.setF_in_f_out_status(YesNo.YES);
/*  480 */       dao.updateObject(sl);
/*      */     } else {
/*  482 */       Site site = (Site) dao.getObjectList(
/*  483 */           "from Site site where site.name='" + siteName + "' ")
/*  484 */         .get(0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  495 */       StorageLocation sl = new StorageLocation();
/*  496 */       sl.setCode(code);
/*  497 */       sl.setDescription(desc);
/*  498 */       sl.setRemark(remark);
/*  499 */       sl.setEnabled(EnabledDisabled.ENABLED);
/*  500 */       sl.setFreeae_status(YesNo.NO);
/*  501 */       sl.setRecommend_status(YesNo.YES);
/*  502 */       sl.setF_in_f_out_status(YesNo.YES);
/*  503 */       sl.setSite(site);
/*  504 */       dao.saveObject(sl);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean insertBom(QADBomInfo shared, SynBaseDAO dao, SyncDAO daoShared) {
/*  515 */     Boolean isRead = Boolean.valueOf(false);
/*  516 */     String domain = shared.getXxqad_ps_domain();
/*  517 */     BigDecimal qty = new BigDecimal(shared.getXxqad_ps_qty_per().intValue());
/*  518 */     String fa_part = shared.getXxqad_ps_par();
/*  519 */     String cl_part = shared.getXxqad_ps_comp();
/*  520 */     String type = shared.getXxqad_ps_ps_code();
/*  521 */     Date dateStart = shared.getXxqad_ps_start();
/*  522 */     Date dateEnd = shared.getXxqad_ps_end();
/*  523 */     String rmks = shared.getXxqad_ps_rmks();
/*  524 */     String station = shared.getXxqad_ps_ref();
/*  525 */     Integer process = shared.getXxqad_ps_op();
/*  526 */     String sql = "from Bom bom where bom.father_part.id = '" + fa_part + 
/*  527 */       "' and bom.child_part.id='" + cl_part + "' ";
/*  528 */     List<Bom> list = dao.getObjectList(sql);
/*      */     
/*  530 */     if (list.size() > 0) {
/*  531 */       Bom bom = list.get(0);
/*  532 */       bom.setUnit_qty(qty);
/*  533 */       bom.setStation(station);
/*  534 */       bom.setProcess((process == null) ? "" : process.toString());
/*  535 */       bom.setDomain(domain);
/*  536 */       bom.setStart_date(dateStart);
/*  537 */       bom.setEnd_date(dateEnd);
/*  538 */       bom.setType(type);
/*  539 */       bom.setRemark(rmks);
/*  540 */       dao.updateObject(bom);
/*      */       
/*  542 */       shared.setXxqad_ps_portalread("1");
/*  543 */       daoShared.updateObject(shared);
/*  544 */       dao.commit();
/*  545 */       daoShared.commit();
/*      */       
/*  547 */       isRead = Boolean.valueOf(true);
/*      */     } else {
/*  549 */       WmsPart part1 = (WmsPart)dao.getObject(WmsPart.class, fa_part);
/*  550 */       WmsPart part2 = (WmsPart)dao.getObject(WmsPart.class, cl_part);
/*      */       
/*  552 */       if (part1 != null && part2 != null) {
/*  553 */         Bom bom = new Bom();
/*  554 */         bom.setFather_part(part1);
/*  555 */         bom.setChild_part(part2);
/*  556 */         bom.setProduct_no(part1);
/*  557 */         bom.setStation(station);
/*  558 */         bom.setProcess((process == null) ? "" : process.toString());
/*  559 */         bom.setUnit_qty(qty);
/*  560 */         bom.setDomain(domain);
/*  561 */         bom.setRemark(rmks);
/*  562 */         bom.setStart_date(dateStart);
/*  563 */         bom.setEnd_date(dateEnd);
/*  564 */         bom.setType(type);
/*      */         
/*  566 */         dao.saveObject(bom);
/*      */         
/*  568 */         shared.setXxqad_ps_portalread("1");
/*  569 */         daoShared.updateObject(shared);
/*  570 */         dao.commit();
/*  571 */         daoShared.commit();
/*      */         
/*  573 */         isRead = Boolean.valueOf(true);
/*      */       } 
/*      */     } 
/*  576 */     return isRead.booleanValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void insertProductOutGoline(ProductOutGoline shared, SynBaseDAO dao) {
/*  587 */     String code = shared.getXxsh_worc_item().trim();
/*  588 */     int qty = Integer.parseInt(shared.getXxsh_worc_qty().trim());
/*  589 */     String location_code = shared.getXxsh_worc_loc().trim();
/*  590 */     Date storage_date = shared.getXxsh_worc_date();
/*      */     
/*  592 */     String sql = "from ProductGoline pg where pg.shCode = '" + code + "'";
/*  593 */     List<ProductGoline> list = dao.getObjectList(sql);
/*      */     
/*  595 */     List<StorageLocation> slList = dao
/*  596 */       .getObjectList("from StorageLocation sl where sl.id='" + 
/*  597 */         location_code + "'");
/*  598 */     if (list.size() == 0) {
/*  599 */       ProductGoline pg = new ProductGoline();
/*  600 */       pg.setShCode(code);
/*  601 */       pg.setQty(Integer.valueOf(qty));
/*  602 */       pg.setLocationCode(slList.get(0));
/*  603 */       pg.setStorageDate(storage_date);
/*      */       
/*  605 */       String totalNumber = code.substring(23);
/*  606 */       pg.setTotalNumber(totalNumber);
/*  607 */       List<FinishedSaiheRelation> fslist = dao
/*  608 */         .getObjectList("from FinishedSaiheRelation finishedSaiheRelation where finishedSaiheRelation.saiheCode ='" + 
/*  609 */           totalNumber + "'");
/*      */       
/*  611 */       String hncCode = ((FinishedSaiheRelation)fslist.get(0)).getFinishedCode();
/*  612 */       pg.setHncCode(hncCode);
/*      */       
/*  614 */       List<Bom> bl = dao
/*  615 */         .getObjectList("from Bom ba where ba.product_no.id = '" + 
/*  616 */           hncCode + "'");
/*      */       
/*  618 */       pg.setHncDesc(((Bom)bl.get(0)).getProduct_no().getDescribe1());
/*  619 */       for (Bom bom : bl) {
/*  620 */         if (bom.getChild_part().getId().indexOf("CL1") >= 0) {
/*      */           
/*  622 */           pg.setPartTireCode(bom.getChild_part().getId());
/*  623 */           pg.setTireDesc(bom.getChild_part().getDescribe1()); continue;
/*      */         } 
/*  625 */         if (bom.getChild_part().getId().indexOf("CL3") >= 0) {
/*      */           
/*  627 */           pg.setPartHubCode(bom.getChild_part().getId());
/*  628 */           pg.setHubDesc(bom.getChild_part().getDescribe1()); continue;
/*      */         } 
/*  630 */         if (bom.getChild_part().getId().indexOf("CL4") >= 0) {
/*      */           
/*  632 */           pg.setPartValvestemCode(bom.getChild_part().getId());
/*  633 */           pg.setValvestemDesc(bom.getChild_part().getDescribe1());
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  638 */       insertInventoryDetial(((Bom)bl.get(0)).getProduct_no(), 
/*  639 */           slList.get(0), new BigDecimal(1), dao);
/*  640 */       pg.setStatus(Integer.valueOf(1));
/*  641 */       dao.saveObject(pg);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void insertInventoryDetial(WmsPart part, StorageLocation sl, BigDecimal qty, SynBaseDAO dao) {
/*  652 */     List<InventoryDetial> partQtyList = dao
/*  653 */       .getObjectList("from InventoryDetial ind  where ind.part.id ='" + 
/*  654 */         part.getId() + "'");
/*  655 */     BigDecimal oldQty = new BigDecimal(0);
/*  656 */     Iterator<InventoryDetial> iterator = partQtyList.iterator(); if (iterator.hasNext()) { InventoryDetial it = iterator.next();
/*  657 */       oldQty = oldQty.add(it.getPart_qty()); }
/*      */ 
/*      */     
/*  660 */     List<InventoryDetial> InventoryQtyList = dao
/*  661 */       .getObjectList("from InventoryDetial ind  where ind.part.id ='" + 
/*  662 */         part.getId() + "' and ind.location.id ='" + 
/*  663 */         sl.getId() + "'");
/*      */     
/*  665 */     if (InventoryQtyList.size() < 1) {
/*  666 */       InventoryDetial detial = new InventoryDetial();
/*  667 */       detial.setLocation(sl);
/*  668 */       detial.setNumber(new BigDecimal(0));
/*  669 */       detial.setPart(part);
/*  670 */       detial.setPart_qty(new BigDecimal(0));
/*  671 */       dao.saveObject(detial);
/*      */     } 
/*      */ 
/*      */     
/*  675 */     InventoryDetial indl = (InventoryDetial) dao.getObjectList(
/*  676 */         "from InventoryDetial ind  where ind.part.id ='" + part.getId() + 
/*  677 */         "' and ind.location.id ='" + sl.getId() + "'").get(0);
/*      */     
/*  679 */     indl.setNumber(indl.getNumber().add(qty));
/*  680 */     dao.updateObject(indl);
/*      */     
/*  682 */     List<InventoryDetial> detialList = dao
/*  683 */       .getObjectList("from InventoryDetial ind  where ind.part.id ='" + 
/*  684 */         part.getId() + "'");
/*      */     
/*  686 */     for (InventoryDetial inventoryDetial : detialList) {
/*  687 */       inventoryDetial.setPart_qty(oldQty.add(qty));
/*  688 */       dao.updateObject(inventoryDetial);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void insertPo(QADPurchaseOrder po, SynBaseDAO dao, SyncDAO daoShared) {
/*  699 */     String xxqad_pod_nbr = po.getXxqad_pod_nbr();
/*  700 */     Integer xxqad_pod_line = po.getXxqad_pod_line();
/*  701 */     String xxqad_pod_part = po.getXxqad_pod_part();
/*  702 */     String xxqad_pod_desc = po.getXxqad_pod_desc();
/*  703 */     BigDecimal xxqad_pod_qty_ord = po.getXxqad_pod_qty_ord();
/*  704 */     BigDecimal xxqad_pod_qty_std = po.getXxqad_pod_qty_std();
/*  705 */     Date xxqad_pod_due_date = po.getXxqad_pod_due_date();
/*  706 */     String xxqad_pod_loc_um = po.getXxqad_pod_loc_um();
/*  707 */     String xxqad_pod_um = po.getXxqad_pod_um();
/*  708 */     BigDecimal xxqad_pod_um_conv = po.getXxqad_pod_um_conv();
/*  709 */     String xxqad_pod_vend = po.getXxqad_pod_vend();
/*  710 */     String xxqad_pod_name = po.getXxqad_pod_name();
/*  711 */     String xxqad_pod_attn = po.getXxqad_pod_attn();
/*  712 */     String xxqad_pod_ship = po.getXxqad_pod_ship();
/*  713 */     String xxqad_pod_made = po.getXxqad_pod_made();
/*  714 */     String xxqad_pod_site = po.getXxqad_pod_site();
/*  715 */     String xxqad_pod_confirm = po.getXxqad_pod_confirm();
/*  716 */     String xxqad_pod_domain = po.getXxqad_pod_domain();
/*  717 */     String xxqad_pod_status = po.getXxqad_pod_status();
/*      */     
/*  719 */     String xxqad_pod_buyer = po.getXxqad_pod_buyer();
/*  720 */     String xxqad_pod_buyer_phone = po.getXxqad_pod_buyer_phone();
/*  721 */     List<Supplier> supplierList = dao
/*  722 */       .getObjectList("from Supplier supplier where supplier.code='" + 
/*  723 */         xxqad_pod_vend + "'");
/*  724 */     if (supplierList == null || supplierList.size() != 1)
/*      */     {
/*  726 */       po.setXxqad_pod_rmks("supplier.inexistence:" + xxqad_pod_vend);
/*      */     }
/*      */     
/*  729 */     WmsPart wmsPart = (WmsPart)dao
/*  730 */       .getObject(WmsPart.class, xxqad_pod_part);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  739 */     if (wmsPart != null) {
/*  740 */       String sql = "from PurchaseOrderInspectionPending po where po.po_number='" + 
/*  741 */         xxqad_pod_nbr + "' ";
/*  742 */       List<PurchaseOrderInspectionPending> list = dao.getObjectList(sql);
/*  743 */       PurchaseOrderInspectionPending pending = null;
/*  744 */       if (list.size() == 0) {
/*  745 */         pending = new PurchaseOrderInspectionPending();
/*  746 */         pending.setPoip_number(xxqad_pod_nbr);
/*  747 */         pending.setCreateDate(new Date());
/*  748 */         pending.setCreateType(Integer.valueOf(2));
/*  749 */         pending.setIsPrint(YesNo.YES);
/*  750 */         pending.setPo_number(xxqad_pod_nbr);
/*  751 */         pending.setSite(new Site(Integer.valueOf(2)));
/*  752 */         pending.setSupplier(supplierList.get(0));
/*  753 */         pending.setBuyer(xxqad_pod_buyer);
/*  754 */         pending.setBuyer_phone(xxqad_pod_buyer_phone);
/*      */         
/*  756 */         if (xxqad_pod_status.equals("1")) {
/*  757 */           pending.setStatus(PurchaseOrderStatus.CLOSE);
/*  758 */         } else if (xxqad_pod_status.equals("0")) {
/*  759 */           pending.setStatus(PurchaseOrderStatus.WAIT);
/*      */         } 
/*      */         
/*  762 */         dao.saveObject(pending);
/*      */       } else {
/*  764 */         pending = list.get(0);
/*      */       } 
/*      */ 
/*      */       
/*  768 */       List<PurchaseOrderInspectionPendingItem> itemList = dao
/*  769 */         .getObjectList("from PurchaseOrderInspectionPendingItem item where item.line='" + 
/*  770 */           xxqad_pod_line + 
/*  771 */           "' and item.itemNumber.id='" + 
/*  772 */           xxqad_pod_part + 
/*  773 */           "' and item.poip_number.id='" + 
/*  774 */           pending.getId() + "'");
/*      */ 
/*      */       
/*  777 */       if (itemList.size() == 0) {
/*  778 */         PurchaseOrderInspectionPendingItem item = new PurchaseOrderInspectionPendingItem();
/*  779 */         item.setPoip_number(pending);
/*  780 */         item.setLine(xxqad_pod_line.toString());
/*  781 */         item.setItemNumber(wmsPart);
/*  782 */         item.setQty(xxqad_pod_qty_ord);
/*  783 */         item.setQtyOpen(xxqad_pod_qty_ord);
/*  784 */         item.setDueDate(xxqad_pod_due_date);
/*  785 */         item.setUm(xxqad_pod_loc_um);
/*  786 */         item.setRum(xxqad_pod_um);
/*  787 */         item.setConversion_ratio(xxqad_pod_um_conv);
/*  788 */         item.setAd_attn(xxqad_pod_attn);
/*  789 */         item.setPo_ship(xxqad_pod_ship);
/*  790 */         item.setVd_promo(xxqad_pod_made);
/*  791 */         item.setFactory(xxqad_pod_site);
/*  792 */         if (xxqad_pod_confirm != null && xxqad_pod_confirm.equals("")) {
/*  793 */           item.setPo_confirm(Integer.getInteger(xxqad_pod_confirm));
/*      */         }
/*  795 */         item.setPo_domain(xxqad_pod_domain);
/*  796 */         item.setReceiptQty(new BigDecimal(0));
/*  797 */         item.setQty_std(xxqad_pod_qty_std);
/*  798 */         item.setCapacity(xxqad_pod_qty_std);
/*  799 */         item.setIsViewItem(YesNo.NO);
/*  800 */         item.setIsPrintLabels(YesNo.NO);
/*  801 */         item.setIsReceiving(YesNo.NO);
/*  802 */         item.setIsIqc(YesNo.NO);
/*  803 */         item.setReceiptQty(new BigDecimal(0));
/*  804 */         item.setInventoryNumber(new BigDecimal(0));
/*      */         
/*  806 */         dao.saveObject(item);
/*      */       }
/*  808 */       else if (xxqad_pod_status.equals("1")) {
/*  809 */         PurchaseOrderInspectionPendingItem item = itemList.get(0);
/*  810 */         if (item.getQtyOpen().compareTo(item.getQty()) == 0) {
/*  811 */           dao.removeObject(item);
/*      */         }
/*      */       } else {
/*  814 */         PurchaseOrderInspectionPendingItem item = itemList.get(0);
/*  815 */         Boolean isupdate = Boolean.valueOf(true);
/*  816 */         if (xxqad_pod_qty_ord.doubleValue() < item.getQty()
/*  817 */           .doubleValue()) {
/*  818 */           if (item.getQty().doubleValue() - xxqad_pod_qty_ord
/*  819 */             .doubleValue() < item.getQty().doubleValue() - item
/*  820 */             .getQtyOpen().doubleValue())
/*      */           {
/*  822 */             isupdate = Boolean.valueOf(false);
/*      */           }
/*      */         }
/*  825 */         if (isupdate.booleanValue()) {
/*      */           
/*  827 */           BigDecimal overQty = item.getQty().subtract(
/*  828 */               item.getQtyOpen());
/*  829 */           item.setQty(xxqad_pod_qty_ord);
/*  830 */           if (xxqad_pod_qty_ord.compareTo(item.getQty()) == 1) {
/*  831 */             item.setQtyOpen(xxqad_pod_qty_ord.subtract(overQty));
/*      */           } else {
/*  833 */             item.setQtyOpen(xxqad_pod_qty_ord.subtract(overQty));
/*      */           } 
/*  835 */           item.setConversion_ratio(xxqad_pod_um_conv);
/*  836 */           item.setQty_std(xxqad_pod_qty_std);
/*  837 */           dao.updateObject(item);
/*  838 */           item.getPoip_number().setStatus(
/*  839 */               PurchaseOrderStatus.WAIT);
/*  840 */           dao.updateObject(item.getPoip_number());
/*      */         } 
/*      */       } 
/*      */       
/*  844 */       po.setXxqad_pod_portalread("1");
/*  845 */       daoShared.updateObject(po);
/*  846 */       dao.commit();
/*  847 */       daoShared.commit();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean insertXxqadidddetListMap(String partId, Map<String, Object> mapo, SynBaseDAO dao) {
/*  852 */     boolean sign = false;
/*  853 */     List<ProjectedInventory> inventoryList = dao.getObjectList("from ProjectedInventory pji  where pji.part.id ='" + partId + "'");
/*  854 */     ProjectedInventory projectedInventory = null;
/*  855 */     if (inventoryList != null && inventoryList.size() > 0) {
/*  856 */       projectedInventory = inventoryList.get(0);
/*  857 */       projectedInventory.setCreateDate((Date)mapo.get("createDate"));
/*  858 */       projectedInventory.setCurrentQty(new BigDecimal(((Integer)mapo.get("qty")).intValue()));
/*  859 */       projectedInventory.setSyncDate(new Date());
/*  860 */       dao.updateObject(projectedInventory);
/*  861 */       sign = true;
/*      */     } else {
/*  863 */       projectedInventory = new ProjectedInventory();
/*  864 */       WmsPart part = (WmsPart)dao.getObject(WmsPart.class, partId);
/*  865 */       if (part != null) {
/*  866 */         projectedInventory.setPart(part);
/*  867 */         projectedInventory.setCreateDate((Date)mapo.get("createDate"));
/*  868 */         projectedInventory.setCurrentQty(new BigDecimal(((Integer)mapo.get("qty")).intValue()));
/*  869 */         projectedInventory.setSyncDate(new Date());
/*  870 */         dao.saveObject(projectedInventory);
/*  871 */         sign = true;
/*      */       } else {
/*  873 */         sign = false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  879 */     return sign;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean insertSalesOrder(QADCustomerWmsPart shared, SynBaseDAO dao) {
/*  888 */     String customerCode = shared.getXxqad_sche_cust();
/*  889 */     String customerName = shared.getXxqad_sche_name();
/*  890 */     Integer lineNo = shared.getXxqad_sche_line();
/*  891 */     String productNo = shared.getXxqad_sche_part();
/*      */     
/*  893 */     String ramek = shared.getXxqad_sche_rmks();
/*  894 */     Date date = shared.getXxqad_sche_createdt();
/*  895 */     String addressCode = shared.getXxqad_sche_shipto();
/*  896 */     String addressDesc = shared.getXxqad_sche_desc();
/*  897 */     String site = shared.getXxqad_sche_site();
/*  898 */     BigDecimal outerPackingQty = shared.getXxqad_sche_comment();
/*  899 */     String domain = shared.getXxqad_sche_domain();
/*  900 */     String seqNo = shared.getXxqad_sche_seq();
/*  901 */     String nbr = shared.getXxqad_sche_nbr();
/*  902 */     BigDecimal qty = shared.getXxqad_sche_qty();
/*      */     
/*  904 */     boolean sign = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  912 */     WmsPart wmsPart = (WmsPart)dao.getObject(WmsPart.class, productNo);
/*  913 */     if (wmsPart == null) {
/*  914 */       sign = false;
/*  915 */       shared.setXxqad_sche_portalread("2");
/*  916 */       shared.setXxqad_sche_rmks("part.inexistence：" + productNo + "||ID=" + 
/*  917 */           shared.getXxqad_sche_id());
/*      */     } 
/*      */     
/*  920 */     if (sign) {
/*  921 */       String sql = "from SalesOrder so where so.soNumber = '" + nbr + "'";
/*  922 */       List<SalesOrder> salesOrderlist = dao.getObjectList(sql);
/*  923 */       SalesOrder salesOrder = null;
/*  924 */       if (salesOrderlist.size() == 0) {
/*  925 */         salesOrder = new SalesOrder();
/*  926 */         salesOrder.setCreateDate(date);
/*  927 */         salesOrder.setCreatetype(Integer.valueOf(1));
/*  928 */         salesOrder.setIsprint(YesNo.NO);
/*  929 */         salesOrder.setSite(new Site(Integer.valueOf(2)));
/*  930 */         salesOrder.setRemark(ramek);
/*  931 */         salesOrder.setCustCode(customerCode);
/*  932 */         salesOrder.setCustAddress(addressCode);
/*  933 */         salesOrder.setCustName(customerName);
/*  934 */         salesOrder.setIssync(YesNo.YES);
/*  935 */         salesOrder.setStatus(PurchaseOrderStatus.WAIT);
/*  936 */         salesOrder.setSoNumber(nbr);
/*  937 */         salesOrder.setCustAddress(addressDesc);
/*      */         
/*  939 */         dao.saveObject(salesOrder);
/*      */       } else {
/*  941 */         salesOrder = salesOrderlist.get(0);
/*  942 */         salesOrder.setCreateDate(date);
/*  943 */         salesOrder.setIsprint(YesNo.NO);
/*  944 */         salesOrder.setRemark(ramek);
/*  945 */         salesOrder.setCustCode(customerCode);
/*  946 */         salesOrder.setCustAddress(addressCode);
/*  947 */         salesOrder.setCustName(customerName);
/*  948 */         salesOrder.setCustAddress(addressDesc);
/*      */       } 
/*      */ 
/*      */       
/*  952 */       String sql3 = "from SalesOrderItem item where item.line='" + 
/*  953 */         lineNo + "'  and item.itemNumber.id='" + 
/*  954 */         productNo + "'  and item.soId.id='" + salesOrder.getId() + "'";
/*  955 */       List<SalesOrderItem> itemList = dao.getObjectList(sql3);
/*      */ 
/*      */       
/*  958 */       if (itemList.size() == 0) {
/*  959 */         SalesOrderItem item = new SalesOrderItem();
/*  960 */         item.setSoId(salesOrder);
/*  961 */         item.setBoxcount(outerPackingQty);
/*  962 */         item.setSite(new Site(Integer.valueOf(2)));
/*  963 */         item.setSoipNumber(salesOrder.getSoNumber());
/*  964 */         item.setLine(lineNo.toString());
/*  965 */         item.setItemNumber(wmsPart);
/*  966 */         item.setQty(qty);
/*  967 */         item.setUm(wmsPart.getUnit());
/*  968 */         item.setQtyopen(qty);
/*  969 */         item.setFactory(site);
/*  970 */         item.setPoDomain(domain);
/*  971 */         item.setReceiptQty(new BigDecimal(0));
/*      */         
/*  973 */         dao.saveObject(item);
/*      */       } else {
/*  975 */         SalesOrderItem item = itemList.get(0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1002 */         item.setBoxcount(outerPackingQty);
/* 1003 */         item.setQty(qty);
/* 1004 */         item.setQtyopen(item.getQty().subtract(item.getReceiptQty()));
/* 1005 */         dao.updateObject(item);
/* 1006 */         if (item.getQtyopen().compareTo(new BigDecimal(0)) == 1) {
/* 1007 */           item.getSoId().setStatus(PurchaseOrderStatus.WAIT);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1012 */         dao.updateObject(item.getSoId());
/*      */       } 
/*      */     } 
/* 1015 */     return sign;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void insertDailyProductPlan(QADDailyProductionSchedule shared, SynBaseDAO dao) {
/* 1079 */     String workOrderNo = shared.getXxqad_wo_nbr();
/* 1080 */     String workOrderId = shared.getXxqad_wo_lot();
/* 1081 */     String part = shared.getXxqad_wo_part();
/* 1082 */     String orderType = shared.getXxqad_wo_type();
/* 1083 */     String orderAttribute = shared.getXxqad_wo_property();
/* 1084 */     String site = shared.getXxqad_wo_site();
/* 1085 */     String lineNo = shared.getXxqad_wo_line();
/* 1086 */     BigDecimal qty = shared.getXxqad_wo_qty_ord();
/* 1087 */     Date golineDate = shared.getXxqad_wo_rel_date();
/* 1088 */     Date offlineDate = shared.getXxqad_wo_due_date();
/* 1089 */     String procedureCode = shared.getXxqad_wo_routing();
/* 1090 */     String bomCode = shared.getXxqad_wo_bom_code();
/* 1091 */     String shift = shared.getXxqad_wo_shift();
/* 1092 */     String bomOutFinish = shared.getXxqad_wo_note();
/* 1093 */     String domain = shared.getXxqad_wo_domain();
/* 1094 */     String seqNo = shared.getXxqad_wo_seq();
/*      */     
/* 1096 */     String sql = "from DailyProductPlan where workOrderId = " + workOrderId;
/* 1097 */     List<DailyProductPlan> list = dao.getObjectList(sql);
/* 1098 */     DailyProductPlan dailyProductPlan = new DailyProductPlan();
/* 1099 */     if (list.size() == 0) {
/* 1100 */       dailyProductPlan.setWorkOrderId(workOrderId);
/* 1101 */       dailyProductPlan.setWorkOrderNo(workOrderNo);
/* 1102 */       dailyProductPlan.setPart(part);
/* 1103 */       dailyProductPlan.setOrderType(orderType);
/* 1104 */       dailyProductPlan.setOrderAttribute(orderAttribute);
/* 1105 */       dailyProductPlan.setSite(site);
/* 1106 */       dailyProductPlan.setLineNo(lineNo);
/* 1107 */       dailyProductPlan.setQty(qty);
/* 1108 */       dailyProductPlan.setGolineDate(golineDate);
/* 1109 */       dailyProductPlan.setOfflineDate(offlineDate);
/* 1110 */       dailyProductPlan.setProcedureCode(procedureCode);
/* 1111 */       dailyProductPlan.setBomCode(bomCode);
/* 1112 */       dailyProductPlan.setShift(shift);
/* 1113 */       dailyProductPlan.setBomOutFinish(bomOutFinish);
/* 1114 */       dailyProductPlan.setDomain(domain);
/* 1115 */       dailyProductPlan.setSeqNo(seqNo);
/* 1116 */       dao.saveObject(dailyProductPlan);
/*      */     } else {
/* 1118 */       dailyProductPlan = list.get(0);
/* 1119 */       dailyProductPlan.setWorkOrderId(workOrderId);
/* 1120 */       dailyProductPlan.setWorkOrderNo(workOrderNo);
/* 1121 */       dailyProductPlan.setPart(part);
/* 1122 */       dailyProductPlan.setOrderType(orderType);
/* 1123 */       dailyProductPlan.setOrderAttribute(orderAttribute);
/* 1124 */       dailyProductPlan.setSite(site);
/* 1125 */       dailyProductPlan.setLineNo(lineNo);
/* 1126 */       dailyProductPlan.setQty(qty);
/* 1127 */       dailyProductPlan.setGolineDate(golineDate);
/* 1128 */       dailyProductPlan.setOfflineDate(offlineDate);
/* 1129 */       dailyProductPlan.setProcedureCode(procedureCode);
/* 1130 */       dailyProductPlan.setBomCode(bomCode);
/* 1131 */       dailyProductPlan.setShift(shift);
/* 1132 */       dailyProductPlan.setBomOutFinish(bomOutFinish);
/* 1133 */       dailyProductPlan.setDomain(domain);
/* 1134 */       dailyProductPlan.setSeqNo(seqNo);
/* 1135 */       dao.updateObject(dailyProductPlan);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void insertWorkOrderBom(QADBom shared, SynBaseDAO dao) {
/* 1141 */     String workOrderId = shared.getXxqad_wod_lot();
/* 1142 */     String workOrderNo = shared.getXxqad_wod_nbr();
/* 1143 */     String productNo = shared.getXxqad_wod_item();
/* 1144 */     String partNo = shared.getXxqad_wod_part();
/* 1145 */     String allNeedQty = shared.getXxqad_wod_qty_req();
/* 1146 */     String workingOrder = shared.getXxqad_wod_op();
/* 1147 */     String workingPosition = shared.getXxqad_wod_gw();
/* 1148 */     BigDecimal singleNeedQty = shared.getXxqad_wod_bom_qty();
/* 1149 */     String site = shared.getXxqad_wod_site();
/* 1150 */     String domain = shared.getXxqad_wod_domain();
/* 1151 */     String seqNo = shared.getXxqad_wod_seq();
/*      */     
/* 1153 */     String sql = "from WorkOrderBom from workOrderId = " + workOrderId;
/* 1154 */     List<WorkOrderBom> list = dao.getObjectList(sql);
/* 1155 */     WorkOrderBom workOrderBom = new WorkOrderBom();
/* 1156 */     if (list.size() == 0) {
/* 1157 */       workOrderBom.setWorkOrderId(workOrderId);
/* 1158 */       workOrderBom.setWorkOrderNo(workOrderNo);
/* 1159 */       workOrderBom.setProductNo(productNo);
/* 1160 */       workOrderBom.setPartNo(partNo);
/* 1161 */       workOrderBom.setAllNeedQty(allNeedQty);
/* 1162 */       workOrderBom.setWorkingOrder(workingOrder);
/* 1163 */       workOrderBom.setWorkingPosition(workingPosition);
/* 1164 */       workOrderBom.setSingleNeedQty(singleNeedQty);
/* 1165 */       workOrderBom.setSite(site);
/* 1166 */       workOrderBom.setDomain(domain);
/* 1167 */       workOrderBom.setSeqNo(seqNo);
/* 1168 */       dao.saveObject(workOrderBom);
/*      */     } else {
/* 1170 */       workOrderBom = list.get(0);
/* 1171 */       workOrderBom.setWorkOrderId(workOrderId);
/* 1172 */       workOrderBom.setWorkOrderNo(workOrderNo);
/* 1173 */       workOrderBom.setProductNo(productNo);
/* 1174 */       workOrderBom.setPartNo(partNo);
/* 1175 */       workOrderBom.setAllNeedQty(allNeedQty);
/* 1176 */       workOrderBom.setWorkingOrder(workingOrder);
/* 1177 */       workOrderBom.setWorkingPosition(workingPosition);
/* 1178 */       workOrderBom.setSingleNeedQty(singleNeedQty);
/* 1179 */       workOrderBom.setSite(site);
/* 1180 */       workOrderBom.setDomain(domain);
/* 1181 */       workOrderBom.setSeqNo(seqNo);
/* 1182 */       dao.updateObject(workOrderBom);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void insertBasicCustomer(QADAdMstr shared, SynBaseDAO dao) {
/* 1188 */     String code1 = shared.getXxqad_ad_addr();
/* 1189 */     String name = shared.getXxqadAdName();
/* 1190 */     String address1 = shared.getXxqadAdLine1();
/* 1191 */     String address2 = shared.getXxqadAdLine2();
/* 1192 */     String address3 = shared.getXxqadAdLine3();
/* 1193 */     String country = shared.getXxqadAdCountry();
/* 1194 */     String city = shared.getXxqadAdCity();
/* 1195 */     String phone = shared.getXxqadAdPhone();
/* 1196 */     String fax = shared.getXxqadAdFax();
/* 1197 */     String postId = shared.getXxqadAdPstId();
/* 1198 */     String contacts = shared.getXxqadAdAttn();
/* 1199 */     String currencyType = shared.getXxqadAdCurr();
/* 1200 */     String enable = shared.getXxqadAdActive();
/* 1201 */     String domain = shared.getXxqadAdDomain();
/* 1202 */     String site = shared.getXxqadAdSite();
/* 1203 */     String seqNo = shared.getXxqadAdSeq();
/* 1204 */     String ramks = shared.getXxqadRamks();
/*      */     
/* 1206 */     String sql = "from BasicCustomer where code = '" + code1 + "'";
/* 1207 */     List<BasicCustomer> list = dao.getObjectList(sql);
/* 1208 */     BasicCustomer customer = new BasicCustomer();
/* 1209 */     if (list.size() == 0) {
/* 1210 */       customer.setCode(code1);
/* 1211 */       customer.setName1(name);
/* 1212 */       customer.setName2(name);
/* 1213 */       customer.setAddress(address1);
/* 1214 */       customer.setAddress2(address2);
/* 1215 */       customer.setAddress3(address3);
/* 1216 */       customer.setCountry(country);
/* 1217 */       customer.setCity(city);
/* 1218 */       customer.setPhone(phone);
/* 1219 */       customer.setFax(fax);
/* 1220 */       customer.setPostId(postId);
/* 1221 */       customer.setContacts(contacts);
/* 1222 */       customer.setCurrencyType(currencyType);
/* 1223 */       customer.setEnabled(EnabledDisabled.ENABLED);
/* 1224 */       customer.setDomain(domain);
/* 1225 */       customer.setSite(site);
/* 1226 */       customer.setRemarks(ramks);
/* 1227 */       dao.saveObject(customer);
/*      */     } else {
/* 1229 */       customer = list.get(0);
/*      */       
/* 1231 */       customer.setName1(name);
/* 1232 */       customer.setName2(name);
/* 1233 */       customer.setAddress(address1);
/* 1234 */       customer.setAddress2(address2);
/* 1235 */       customer.setAddress3(address3);
/* 1236 */       customer.setCountry(country);
/* 1237 */       customer.setCity(city);
/* 1238 */       customer.setPhone(phone);
/* 1239 */       customer.setFax(fax);
/* 1240 */       customer.setPostId(postId);
/* 1241 */       customer.setContacts(contacts);
/* 1242 */       customer.setCurrencyType(currencyType);
/* 1243 */       customer.setEnabled(EnabledDisabled.ENABLED);
/* 1244 */       customer.setDomain(domain);
/* 1245 */       customer.setSite(site);
/* 1246 */       customer.setRemarks(ramks);
/* 1247 */       dao.updateObject(customer);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void insertBasicPartPrice(QADPcMstr shared, SynBaseDAO dao) {
/* 1253 */     String customer = shared.getXxqadPcShipto();
/* 1254 */     String sotaxc = shared.getXxqadPcSotaxc();
/* 1255 */     String partId = shared.getXxqadPcSopart();
/* 1256 */     String solist = shared.getXxqadPcSolist();
/* 1257 */     String pcDesc = shared.getXxqadPcDesc();
/* 1258 */     String curr = shared.getXxqadPcCurr();
/* 1259 */     String pcUm = shared.getXxqadPcUm();
/* 1260 */     Date startDate = shared.getXxqadPcStart();
/* 1261 */     Date expireDate = shared.getXxqadPcExpire();
/* 1262 */     String amtType = shared.getXxqadPcAmtType();
/* 1263 */     BigDecimal amt = shared.getXxqadPcAmt();
/* 1264 */     String domain = shared.getXxqadPcDomain();
/* 1265 */     String site = shared.getXxqadPcSite();
/* 1266 */     String rmks = shared.getXxqadPcRmks();
/* 1267 */     Date createDate = shared.getXxqadPcCreatedt();
/* 1268 */     String createUser = shared.getXxqadPcCreateur();
/* 1269 */     Date updateDate = shared.getXxqadPcUpdatedt();
/* 1270 */     String updateUser = shared.getXxqadPcUpdateur();
/* 1271 */     String seq = shared.getXxqadPcSeq();
/*      */ 
/*      */     
/* 1274 */     String sql = "from BasicPartPrice bpp where bpp.customer = '" + customer + "' and bpp.partId = '" + partId + "' and bpp.curr='" + curr + "' and bpp.pcUm='" + pcUm + "'";
/* 1275 */     List<BasicPartPrice> list = dao.getObjectList(sql);
/* 1276 */     BasicPartPrice partPrice = new BasicPartPrice();
/* 1277 */     if (list.size() == 0) {
/* 1278 */       partPrice.setCustomer(customer);
/* 1279 */       partPrice.setSotaxc(sotaxc);
/* 1280 */       partPrice.setPartId(partId);
/* 1281 */       partPrice.setSolist(solist);
/* 1282 */       partPrice.setPcDesc(pcDesc);
/* 1283 */       partPrice.setCurr(curr);
/* 1284 */       partPrice.setPcUm(pcUm);
/* 1285 */       partPrice.setStartDate(startDate);
/* 1286 */       partPrice.setExpireDate(expireDate);
/* 1287 */       partPrice.setAmtType(amtType);
/* 1288 */       partPrice.setAmt(amt);
/* 1289 */       partPrice.setDomain(domain);
/* 1290 */       partPrice.setSite(site);
/* 1291 */       partPrice.setRmks(rmks);
/* 1292 */       partPrice.setCreateDate(createDate);
/* 1293 */       partPrice.setCreateUser(createUser);
/* 1294 */       partPrice.setUpdateDate(updateDate);
/* 1295 */       partPrice.setUpdateUser(updateUser);
/* 1296 */       partPrice.setSeq(seq);
/* 1297 */       partPrice.setMtType("QAD");
/* 1298 */       dao.saveObject(partPrice);
/*      */     } else {
/* 1300 */       partPrice = list.get(0);
/* 1301 */       partPrice.setCustomer(customer);
/* 1302 */       partPrice.setSotaxc(sotaxc);
/* 1303 */       partPrice.setPartId(partId);
/* 1304 */       partPrice.setSolist(solist);
/* 1305 */       partPrice.setPcDesc(pcDesc);
/* 1306 */       partPrice.setCurr(curr);
/* 1307 */       partPrice.setPcUm(pcUm);
/* 1308 */       partPrice.setStartDate(startDate);
/* 1309 */       partPrice.setExpireDate(expireDate);
/* 1310 */       partPrice.setAmtType(amtType);
/* 1311 */       partPrice.setAmt(amt);
/* 1312 */       partPrice.setDomain(domain);
/* 1313 */       partPrice.setSite(site);
/* 1314 */       partPrice.setRmks(rmks);
/* 1315 */       partPrice.setCreateDate(createDate);
/* 1316 */       partPrice.setCreateUser(createUser);
/* 1317 */       partPrice.setUpdateDate(updateDate);
/* 1318 */       partPrice.setUpdateUser(updateUser);
/* 1319 */       partPrice.setSeq(seq);
/* 1320 */       partPrice.setMtType("QAD");
/* 1321 */       dao.updateObject(partPrice);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void insertXbmwoDet(XbmwoDet shared, SynBaseDAO dao, SyncDAO daoShared) throws Exception {
/* 1326 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 1327 */     SimpleDateFormat sdfHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
/* 1328 */     SimpleDateFormat sdf1 = new SimpleDateFormat("HHmmss");
/* 1329 */     String xbmwo_hd_code = shared.getXbmwo_hd_code();
/* 1330 */     String xbmwo_type = shared.getXbmwo_type();
/* 1331 */     String xbmwo_line = shared.getXbmwo_line();
/* 1332 */     String xbmwo_shift = shared.getXbmwo_shift();
/* 1333 */     String xbmwo_emp = shared.getXbmwo_emp();
/* 1334 */     String xbmwo_seq = shared.getXbmwo_seq();
/* 1335 */     String xbmwo_date = shared.getXbmwo_date();
/* 1336 */     Integer xbmwo_qty = shared.getXbmwo_qty();
/*      */     
/* 1338 */     EdiProduction ediProduction = new EdiProduction();
/* 1339 */     ediProduction.setModels(xbmwo_hd_code);
/* 1340 */     ediProduction.setAsnNo(xbmwo_type);
/* 1341 */     ediProduction.setNumber((xbmwo_seq == null) ? "" : xbmwo_seq);
/* 1342 */     ediProduction.setQty(xbmwo_qty);
/* 1343 */     ediProduction.setType(Integer.valueOf(1));
/* 1344 */     ediProduction.setProductlinecode(xbmwo_line);
/* 1345 */     ediProduction.setShiftcode(xbmwo_shift);
/* 1346 */     ediProduction.setStaffcode(xbmwo_emp);
/* 1347 */     ediProduction.setTaskDate(sdfHHmmss.parse(xbmwo_date));
/* 1348 */     ediProduction.setTime(sdf1.format(sdfHHmmss.parse(xbmwo_date)));
/* 1349 */     ediProduction.setStatus(Integer.valueOf(0));
/* 1350 */     ediProduction.setTaskTime(sdfHHmmss.parse(xbmwo_date));
/* 1351 */     ediProduction.setSyncTime(new Date());
/* 1352 */     ediProduction.setHandStatus(Integer.valueOf(0));
/* 1353 */     ediProduction.setEnabled(Integer.valueOf(0));
/* 1354 */     dao.saveObject(ediProduction);
/*      */     
/* 1356 */     shared.setXbmwo_portalread("1");
/* 1357 */     daoShared.updateObject(shared);
/*      */     
/* 1359 */     dao.commit();
/* 1360 */     daoShared.commit();
/*      */   }
/*      */ 
/*      */   
/*      */   protected int insertXxqadMrpDet(XxqadMrpDet shared, SynBaseDAO dao, SyncDAO daoShared) throws Exception {
/* 1365 */     int i = 0;
/* 1366 */     String part = shared.getXxqadMrpPart();
/* 1367 */     WmsPart wmspart = (WmsPart)dao.getObject(WmsPart.class, part);
/* 1368 */     NjitNpoPlan njitNpoPlan = null;
/* 1369 */     if (wmspart != null) {
/* 1370 */       njitNpoPlan = new NjitNpoPlan();
/* 1371 */       njitNpoPlan.setPartId(wmspart);
/* 1372 */       njitNpoPlan.setCreateDate(new Date());
/* 1373 */       njitNpoPlan.setDataset(shared.getXxqadMrpDataset());
/* 1374 */       njitNpoPlan.setDetail(shared.getXxqadMrpDetail());
/* 1375 */       njitNpoPlan.setIsEnabled(EnabledDisabled.ENABLED);
/* 1376 */       njitNpoPlan.setLine(shared.getXxqadMrpLine());
/* 1377 */       njitNpoPlan.setNbr(shared.getXxqadMrpNbr());
/* 1378 */       njitNpoPlan.setNeedDate(shared.getXxqadMrpDueDate());
/* 1379 */       njitNpoPlan.setQty(shared.getXxqadMrpQty());
/* 1380 */       njitNpoPlan.setRelDate(shared.getXxqadMrpRelDate());
/* 1381 */       njitNpoPlan.setTime(shared.getXxqadMrpTime());
/* 1382 */       njitNpoPlan.setType(shared.getXxqadMrpType());
/* 1383 */       njitNpoPlan.setVersion(shared.getXxqadMrpRev());
/* 1384 */       dao.saveObject(njitNpoPlan);
/*      */       
/* 1386 */       shared.setXxqadMrpPortalread("1");
/* 1387 */       daoShared.updateObject(shared);
/*      */       
/* 1389 */       i++;
/*      */     } 
/* 1391 */     return i;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void insertXxqadDailyPlanDet(XxqadDailyPlanDet plan, SynBaseDAO dao, SyncDAO daoShared) {
/*      */     try {
/* 1397 */       ProductionDayPlan day = new ProductionDayPlan();
/* 1398 */       day.setAsnNo(plan.getXxqad_dailyplan_asn());
/* 1399 */       day.setVersion(plan.getXxqad_dailyplan_rev());
/* 1400 */       day.setCreatDate(plan.getXxqad_dailyplan_creatdate());
/* 1401 */       day.setCreatTime(plan.getXxqad_dailyplan_creattime());
/* 1402 */       day.setDailyDate(plan.getXxqad_dailydate());
/* 1403 */       day.setCarkind(plan.getXxqad_dailyplan_carkind());
/* 1404 */       day.setPlanRem(plan.getXxqad_dailyplan_rem());
/* 1405 */       day.setPlanSum(plan.getXxqad_dailyplan_sum());
/* 1406 */       day.setPlanWeitou(plan.getXxqad_dailyplan_weitou());
/* 1407 */       day.setPlanLco(plan.getXxqad_dailyplan_lco());
/* 1408 */       day.setPlanLcx(plan.getXxqad_dailyplan_lcx());
/* 1409 */       day.setPlanAll(plan.getXxqad_dailyplan_all());
/* 1410 */       day.setPlanM1(plan.getXxqad_dailyplan_M1());
/* 1411 */       day.setPlanMouth(plan.getXxqad_dailyplan_mouth());
/* 1412 */       day.setPlanD1(plan.getXxqad_dailyplan_D1());
/* 1413 */       day.setPlanPbs(plan.getXxqad_dailyplan_pbs());
/* 1414 */       day.setPlanPrj(plan.getXxqad_dailyplan_prj());
/* 1415 */       day.setPlanWbs(plan.getXxqad_dailyplan_wbs());
/* 1416 */       day.setPlanSum1(plan.getXxqad_dailyplan_sum1());
/* 1417 */       day.setDay1(plan.getXxqad_dailyplan_day1());
/* 1418 */       day.setDay2(plan.getXxqad_dailyplan_day2());
/* 1419 */       day.setDay3(plan.getXxqad_dailyplan_day3());
/* 1420 */       day.setDay4(plan.getXxqad_dailyplan_day4());
/* 1421 */       day.setDay5(plan.getXxqad_dailyplan_day5());
/* 1422 */       day.setDay6(plan.getXxqad_dailyplan_day6());
/* 1423 */       day.setDay7(plan.getXxqad_dailyplan_day7());
/* 1424 */       day.setDay8(plan.getXxqad_dailyplan_day8());
/* 1425 */       day.setDay9(plan.getXxqad_dailyplan_day9());
/* 1426 */       day.setDay10(plan.getXxqad_dailyplan_day10());
/* 1427 */       day.setDay11(plan.getXxqad_dailyplan_day11());
/* 1428 */       day.setDay12(plan.getXxqad_dailyplan_day12());
/* 1429 */       day.setDay13(plan.getXxqad_dailyplan_day13());
/* 1430 */       day.setDay14(plan.getXxqad_dailyplan_day14());
/* 1431 */       day.setDay15(plan.getXxqad_dailyplan_day15());
/* 1432 */       day.setDay16(plan.getXxqad_dailyplan_day16());
/* 1433 */       day.setDay17(plan.getXxqad_dailyplan_day17());
/* 1434 */       day.setDay18(plan.getXxqad_dailyplan_day18());
/* 1435 */       day.setDay19(plan.getXxqad_dailyplan_day19());
/* 1436 */       day.setDay20(plan.getXxqad_dailyplan_day20());
/* 1437 */       day.setDay21(plan.getXxqad_dailyplan_day21());
/* 1438 */       day.setDay22(plan.getXxqad_dailyplan_day22());
/* 1439 */       day.setDay23(plan.getXxqad_dailyplan_day23());
/* 1440 */       day.setDay24(plan.getXxqad_dailyplan_day24());
/* 1441 */       day.setDay25(plan.getXxqad_dailyplan_day25());
/* 1442 */       day.setDay26(plan.getXxqad_dailyplan_day26());
/* 1443 */       day.setDay27(plan.getXxqad_dailyplan_day27());
/* 1444 */       day.setDay28(plan.getXxqad_dailyplan_day28());
/* 1445 */       day.setDay29(plan.getXxqad_dailyplan_day29());
/* 1446 */       day.setDay30(plan.getXxqad_dailyplan_day30());
/* 1447 */       day.setDay31(plan.getXxqad_dailyplan_day31());
/* 1448 */       day.setDay32(plan.getXxqad_dailyplan_day32());
/* 1449 */       day.setDay33(plan.getXxqad_dailyplan_day33());
/* 1450 */       day.setDay34(plan.getXxqad_dailyplan_day34());
/* 1451 */       day.setDay35(plan.getXxqad_dailyplan_day35());
/* 1452 */       day.setDay36(plan.getXxqad_dailyplan_day36());
/* 1453 */       day.setDay37(plan.getXxqad_dailyplan_day37());
/* 1454 */       day.setDay38(plan.getXxqad_dailyplan_day38());
/* 1455 */       day.setDay39(plan.getXxqad_dailyplan_day39());
/* 1456 */       day.setDay40(plan.getXxqad_dailyplan_day40());
/* 1457 */       day.setDay41(plan.getXxqad_dailyplan_day41());
/* 1458 */       day.setDay42(plan.getXxqad_dailyplan_day42());
/* 1459 */       day.setDay43(plan.getXxqad_dailyplan_day43());
/* 1460 */       day.setDay44(plan.getXxqad_dailyplan_day44());
/* 1461 */       day.setDay45(plan.getXxqad_dailyplan_day45());
/* 1462 */       dao.saveObject(day);
/* 1463 */       plan.setXxqad_dailyplan_portalread("1");
/* 1464 */       daoShared.updateObject(plan);
/* 1465 */       dao.commit();
/* 1466 */       daoShared.commit();
/* 1467 */     } catch (Exception e) {
/*      */       
/* 1469 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void insertXxqad72HourPlanDet(XxqadHourPlanDet hourPlan, SynBaseDAO dao, SyncDAO daoShared) {
/*      */     try {
/* 1476 */       Production72HourPlan hour = new Production72HourPlan();
/* 1477 */       hour.setVersion(hourPlan.getXxqad_hourplan_rev());
/* 1478 */       hour.setCreatdate(hourPlan.getXxqad_hourplan_creatdate());
/* 1479 */       hour.setCreattime(hourPlan.getXxqad_hourplan_creattime());
/* 1480 */       hour.setCarkind(hourPlan.getXxqad_hourplan_carkind());
/* 1481 */       hour.setAsn(hourPlan.getXxqad_hourplan_asn());
/* 1482 */       hour.setM1(hourPlan.getXxqad_hourplan_M1());
/* 1483 */       hour.setMouth(hourPlan.getXxqad_hourplan_mouth());
/* 1484 */       hour.setD1(hourPlan.getXxqad_hourplan_D1());
/* 1485 */       hour.setPbs(hourPlan.getXxqad_hourplan_pbs());
/* 1486 */       hour.setPrj(hourPlan.getXxqad_hourplan_prj());
/* 1487 */       hour.setWbs(hourPlan.getXxqad_hourplan_wbs());
/* 1488 */       hour.setSum1(hourPlan.getXxqad_hourplan_sum1());
/* 1489 */       hour.setHourplan_day1(hourPlan.getXxqad_hourplan_day1());
/* 1490 */       hour.setHourplan_day2(hourPlan.getXxqad_hourplan_day2());
/* 1491 */       hour.setHourplan_day3(hourPlan.getXxqad_hourplan_day3());
/* 1492 */       hour.setHourplan_day4(hourPlan.getXxqad_hourplan_day4());
/* 1493 */       hour.setHourplan_day5(hourPlan.getXxqad_hourplan_day5());
/* 1494 */       hour.setHourplan_day6(hourPlan.getXxqad_hourplan_day6());
/* 1495 */       hour.setHourplan_day7(hourPlan.getXxqad_hourplan_day7());
/* 1496 */       hour.setHourplan_day8(hourPlan.getXxqad_hourplan_day8());
/* 1497 */       hour.setHourplan_day9(hourPlan.getXxqad_hourplan_day9());
/* 1498 */       hour.setHourplan_day10(hourPlan.getXxqad_hourplan_day10());
/* 1499 */       hour.setHourplan_day11(hourPlan.getXxqad_hourplan_day11());
/* 1500 */       hour.setHourplan_day12(hourPlan.getXxqad_hourplan_day12());
/* 1501 */       hour.setHourplan_day13(hourPlan.getXxqad_hourplan_day13());
/* 1502 */       hour.setHourplan_day14(hourPlan.getXxqad_hourplan_day14());
/* 1503 */       hour.setHourplan_day15(hourPlan.getXxqad_hourplan_day15());
/* 1504 */       hour.setHourplan_day16(hourPlan.getXxqad_hourplan_day16());
/* 1505 */       hour.setHourplan_day17(hourPlan.getXxqad_hourplan_day17());
/* 1506 */       hour.setHourplan_day18(hourPlan.getXxqad_hourplan_day18());
/* 1507 */       hour.setHourplan_day19(hourPlan.getXxqad_hourplan_day19());
/* 1508 */       hour.setHourplan_day20(hourPlan.getXxqad_hourplan_day20());
/* 1509 */       hour.setHourplan_day21(hourPlan.getXxqad_hourplan_day21());
/* 1510 */       hour.setHourplan_day22(hourPlan.getXxqad_hourplan_day22());
/* 1511 */       hour.setHourplan_day23(hourPlan.getXxqad_hourplan_day23());
/* 1512 */       hour.setHourplan_day24(hourPlan.getXxqad_hourplan_day24());
/* 1513 */       hour.setHourplan_day25(hourPlan.getXxqad_hourplan_day25());
/* 1514 */       hour.setHourplan_day26(hourPlan.getXxqad_hourplan_day26());
/* 1515 */       hour.setHourplan_day27(hourPlan.getXxqad_hourplan_day27());
/* 1516 */       hour.setHourplan_day28(hourPlan.getXxqad_hourplan_day28());
/* 1517 */       hour.setHourplan_day29(hourPlan.getXxqad_hourplan_day29());
/* 1518 */       hour.setHourplan_day30(hourPlan.getXxqad_hourplan_day30());
/* 1519 */       hour.setHourplan_day31(hourPlan.getXxqad_hourplan_day31());
/* 1520 */       hour.setHourplan_day32(hourPlan.getXxqad_hourplan_day32());
/* 1521 */       hour.setHourplan_day33(hourPlan.getXxqad_hourplan_day33());
/* 1522 */       hour.setHourplan_day34(hourPlan.getXxqad_hourplan_day34());
/* 1523 */       hour.setHourplan_day35(hourPlan.getXxqad_hourplan_day35());
/* 1524 */       hour.setHourplan_day36(hourPlan.getXxqad_hourplan_day36());
/* 1525 */       hour.setHourplan_day37(hourPlan.getXxqad_hourplan_day37());
/* 1526 */       hour.setHourplan_day38(hourPlan.getXxqad_hourplan_day38());
/* 1527 */       hour.setHourplan_day39(hourPlan.getXxqad_hourplan_day39());
/* 1528 */       hour.setHourplan_day40(hourPlan.getXxqad_hourplan_day40());
/* 1529 */       hour.setHourplan_day41(hourPlan.getXxqad_hourplan_day41());
/* 1530 */       hour.setHourplan_day42(hourPlan.getXxqad_hourplan_day42());
/* 1531 */       hour.setHourplan_day43(hourPlan.getXxqad_hourplan_day43());
/* 1532 */       hour.setHourplan_day44(hourPlan.getXxqad_hourplan_day44());
/* 1533 */       hour.setHourplan_day45(hourPlan.getXxqad_hourplan_day45());
/* 1534 */       hour.setHourplan_day46(hourPlan.getXxqad_hourplan_day46());
/* 1535 */       hour.setHourplan_day47(hourPlan.getXxqad_hourplan_day47());
/* 1536 */       hour.setHourplan_day48(hourPlan.getXxqad_hourplan_day48());
/* 1537 */       hour.setHourplan_day49(hourPlan.getXxqad_hourplan_day49());
/* 1538 */       hour.setHourplan_day50(hourPlan.getXxqad_hourplan_day50());
/* 1539 */       hour.setHourplan_day51(hourPlan.getXxqad_hourplan_day51());
/* 1540 */       hour.setHourplan_day52(hourPlan.getXxqad_hourplan_day52());
/* 1541 */       hour.setHourplan_day53(hourPlan.getXxqad_hourplan_day53());
/* 1542 */       hour.setHourplan_day54(hourPlan.getXxqad_hourplan_day54());
/* 1543 */       hour.setHourplan_day55(hourPlan.getXxqad_hourplan_day55());
/* 1544 */       hour.setHourplan_day56(hourPlan.getXxqad_hourplan_day56());
/* 1545 */       hour.setHourplan_day57(hourPlan.getXxqad_hourplan_day57());
/* 1546 */       hour.setHourplan_day58(hourPlan.getXxqad_hourplan_day58());
/* 1547 */       hour.setHourplan_day59(hourPlan.getXxqad_hourplan_day59());
/* 1548 */       hour.setHourplan_day60(hourPlan.getXxqad_hourplan_day60());
/* 1549 */       hour.setHourplan_day61(hourPlan.getXxqad_hourplan_day61());
/* 1550 */       hour.setHourplan_day62(hourPlan.getXxqad_hourplan_day62());
/* 1551 */       hour.setHourplan_day63(hourPlan.getXxqad_hourplan_day63());
/* 1552 */       hour.setHourplan_day64(hourPlan.getXxqad_hourplan_day64());
/* 1553 */       hour.setHourplan_day65(hourPlan.getXxqad_hourplan_day65());
/* 1554 */       hour.setRem(hourPlan.getXxqad_hourplan_rem());
/* 1555 */       hour.setSum(hourPlan.getXxqad_hourplan_sum());
/* 1556 */       hour.setWeitou(hourPlan.getXxqad_hourplan_weitou());
/* 1557 */       hour.setLco(hourPlan.getXxqad_hourplan_lco());
/* 1558 */       hour.setLcx(hourPlan.getXxqad_hourplan_lcx());
/* 1559 */       hour.setHourAll(hourPlan.getXxqad_hourplan_all());
/* 1560 */       hour.setXxqad_hourdate(hourPlan.getXxqad_hourdate());
/* 1561 */       hour.setRmks(hourPlan.getXxqad_hourplan_rmks());
/* 1562 */       hour.setCreatedt(hourPlan.getXxqad_hourplan_createdt());
/* 1563 */       hour.setCreateur(hourPlan.getXxqad_hourplan_createur());
/* 1564 */       hour.setUpdatedt(hourPlan.getXxqad_hourplan_updatedt());
/* 1565 */       hour.setUpdateur(hourPlan.getXxqad_hourplan_updateur());
/* 1566 */       dao.saveObject(hour);
/* 1567 */       hourPlan.setXxqad_hourplan_portalread("1");
/* 1568 */       daoShared.updateObject(hourPlan);
/* 1569 */       dao.commit();
/* 1570 */       daoShared.commit();
/* 1571 */     } catch (Exception e) {
/*      */       
/* 1573 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/quartz/job/BasicRed.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */