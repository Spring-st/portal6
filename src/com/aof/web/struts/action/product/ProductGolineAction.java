/*     */ package com.aof.web.struts.action.product;
/*     */ 
/*     */ import com.aof.model.admin.FinishedSaiheRelation;
/*     */ import com.aof.model.admin.FinishedToolPutnumber;
/*     */ import com.aof.model.admin.query.FinishedSaiheRelationQueryCondition;
/*     */ import com.aof.model.admin.query.FinishedToolPutnumberQueryCondition;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.comprehensive.Bom;
/*     */ import com.aof.model.comprehensive.ProduceBuckleMaterial;
/*     */ import com.aof.model.comprehensive.query.BomQueryCondition;
/*     */ import com.aof.model.inventory.InventoryDetial;
/*     */ import com.aof.model.inventory.InventoryMoving;
/*     */ import com.aof.model.inventory.query.InventoryQueryCondition;
/*     */ import com.aof.model.metadata.StoreRoomType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.po.query.BoxQueryCondition;
/*     */ import com.aof.model.po.query.BoxQueryOrder;
/*     */ import com.aof.model.product.ProductGoline;
/*     */ import com.aof.model.product.query.ProductGolineQueryCondition;
/*     */ import com.aof.model.product.query.ProductGolineQueryOrder;
/*     */ import com.aof.model.sync.query.ProductOutGolineQueryCondition;
/*     */ import com.aof.model.sync.query.ProductOutGolineQueryOrder;
/*     */ import com.aof.service.Product.ProductGolineManager;
/*     */ import com.aof.service.Product.ProductOutGolineManager;
/*     */ import com.aof.service.admin.FinishedSaiheRelationManager;
/*     */ import com.aof.service.admin.FinishedToolPutnumberManager;
/*     */ import com.aof.service.admin.InventoryMovingManager;
/*     */ import com.aof.service.basic.StorageLocationManager;
/*     */ import com.aof.service.basic.WmsPartManager;
/*     */ import com.aof.service.comprehensive.BomManager;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import com.aof.service.po.ProduceBuckleMaterialManager;
/*     */ import com.aof.service.quartz.job.RedMinuteSyncJob;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.po.ProductGoLineQueryForm;
/*     */ import com.aof.web.struts.form.po.ProductOutGolineQueryForm;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.net.URLEncoder;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ import net.sf.json.JsonConfig;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
/*     */ import org.apache.struts.util.MessageResources;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProductGolineAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward productGolineNotQAD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  80 */     ProductOutGolineManager manager = ServiceLocator.getProductOutGolineManager(request);
/*  81 */     ProductOutGolineQueryForm queryForm = (ProductOutGolineQueryForm)form;
/*  82 */     Map<ProductOutGolineQueryCondition, Integer> conditions = constructQueryMap(queryForm);
/*  83 */     conditions.put(ProductOutGolineQueryCondition.XXSH_WORC_STATUS_EQ, Integer.valueOf(2));
/*  84 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  85 */     if (queryForm.isFirstInit()) {
/*  86 */       queryForm.init(manager.getProductOutGolineListCount(conditions));
/*     */     } else {
/*  88 */       queryForm.init();
/*     */     } 
/*  90 */     List result = manager.getProductOutGolineList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), ProductOutGolineQueryOrder.ID, queryForm.isDescend());
/*  91 */     request.setAttribute("X_RESULTLIST", result);
/*  92 */     request.setAttribute("x_selType", Integer.valueOf(111));
/*  93 */     return mapping.findForward("page");
/*     */   }
/*     */   private Map constructQueryMap(ProductOutGolineQueryForm queryForm) {
/*  96 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  97 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/*  98 */     if (id != null && !id.equals("")) {
/*  99 */       conditions.put(ProductOutGolineQueryCondition.ID_EQ, id);
/*     */     }
/* 101 */     String date1 = queryForm.getDate1();
/* 102 */     if (date1 != null && !date1.equals("")) {
/* 103 */       conditions.put(ProductOutGolineQueryCondition.DATE_GT, date1);
/*     */     }
/* 105 */     String date2 = queryForm.getDate2();
/* 106 */     if (date2 != null && !date2.equals("")) {
/* 107 */       conditions.put(ProductOutGolineQueryCondition.DATE_LT, date2);
/*     */     }
/* 109 */     String item = queryForm.getItem();
/* 110 */     if (item != null && !item.equals("")) {
/* 111 */       conditions.put(ProductOutGolineQueryCondition.XXSH_WORC_ITEM_LIKE, item);
/*     */     }
/* 113 */     return conditions;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward productGolineQAD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 118 */     RedMinuteSyncJob sss = 
/* 119 */       ServiceLocator.getRedMinuteSyncJobManager(request);
/* 120 */     sss.startProductOutGolineSyn();
/*     */     
/* 122 */     return new ActionForward("listProductGoline.do", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insertProductGoline(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 127 */     ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
/* 128 */     FinishedSaiheRelationManager fmanager = ServiceLocator.getFinishedSaiheRelationManager(request);
/* 129 */     BomManager bmanager = ServiceLocator.getBomManager(request);
/* 130 */     StorageLocationManager slManager = ServiceLocator.getStorageLocationManager(request);
/* 131 */     InventoryManager itManager = ServiceLocator.getInventoryManager(request);
/* 132 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 133 */     ProductGoline proGoline = new ProductGoline();
/*     */     
/* 135 */     String shCode = request.getParameter("shCode");
/* 136 */     proGoline.setShCode(shCode);
/* 137 */     conditions.put(ProductGolineQueryCondition.SH_CODE_EQ, shCode);
/* 138 */     if (manager.getProductGolineListCount(conditions) > 0) {
/* 139 */       postGlobalMessage("productGoline.shCode.exists", shCode, request.getSession());
/* 140 */       return new ActionForward("listProductGoline.do", true);
/*     */     } 
/*     */     
/* 143 */     String totalNumber = shCode.substring(23);
/* 144 */     proGoline.setTotalNumber(totalNumber);
/*     */     
/* 146 */     conditions.clear();
/* 147 */     conditions.put(FinishedSaiheRelationQueryCondition.SAIHECODE_EQ, totalNumber);
/* 148 */     List<FinishedSaiheRelation> list = fmanager.getFinishedSaiheRelationList(conditions, 0, -1, null, false);
/* 149 */     if (list.size() <= 0) {
/* 150 */       postGlobalMessage("productGoline.hncCode.notFound", totalNumber, request.getSession());
/* 151 */       return new ActionForward("listProductGoline.do", true);
/*     */     } 
/* 153 */     String hncCode = ((FinishedSaiheRelation)list.get(0)).getFinishedCode();
/* 154 */     proGoline.setHncCode(hncCode);
/*     */     
/* 156 */     conditions.clear();
/* 157 */     conditions.put(BomQueryCondition.PRODUCT_NO_EQ, hncCode);
/* 158 */     List<Bom> bl = bmanager.getBomList(conditions, 0, -1, null, false);
/* 159 */     if (bl.size() <= 0) {
/* 160 */       postGlobalMessage("productGoline.partChild.notFound", hncCode, request.getSession());
/* 161 */       return new ActionForward("listProductGoline.do", true);
/*     */     } 
/*     */     
/* 164 */     proGoline.setHncDesc(((Bom)bl.get(0)).getProduct_no().getDescribe1());
/* 165 */     for (Bom bom : bl) {
/* 166 */       if (bom.getChild_part().getId().indexOf("CL1") >= 0) {
/*     */         
/* 168 */         proGoline.setPartTireCode(bom.getChild_part().getId());
/* 169 */         proGoline.setTireDesc(bom.getChild_part().getDescribe1()); continue;
/*     */       } 
/* 171 */       if (bom.getChild_part().getId().indexOf("CL3") >= 0) {
/*     */         
/* 173 */         proGoline.setPartHubCode(bom.getChild_part().getId());
/* 174 */         proGoline.setHubDesc(bom.getChild_part().getDescribe1()); continue;
/*     */       } 
/* 176 */       if (bom.getChild_part().getId().indexOf("CL4") >= 0) {
/*     */         
/* 178 */         proGoline.setPartValvestemCode(bom.getChild_part().getId());
/* 179 */         proGoline.setValvestemDesc(bom.getChild_part().getDescribe1());
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 184 */     if (proGoline.getPartTireCode() == null || proGoline.getPartTireCode().length() == 0) {
/* 185 */       postGlobalMessage("productGoline.partTireCode.notFound", hncCode, request.getSession());
/* 186 */       return new ActionForward("listProductGoline.do", true);
/*     */     } 
/* 188 */     if (proGoline.getPartHubCode() == null || proGoline.getPartHubCode().length() == 0) {
/* 189 */       postGlobalMessage("productGoline.partHubCode.notFound", hncCode, request.getSession());
/* 190 */       return new ActionForward("listProductGoline.do", true);
/*     */     } 
/* 192 */     if (proGoline.getPartValvestemCode() == null || proGoline.getPartValvestemCode().length() == 0) {
/* 193 */       postGlobalMessage("productGoline.partValvestemCode.notFound", hncCode, request.getSession());
/* 194 */       return new ActionForward("listProductGoline.do", true);
/*     */     } 
/* 196 */     proGoline.setQty(Integer.valueOf(1));
/* 197 */     proGoline.setStatus(Integer.valueOf(1));
/* 198 */     proGoline.setStorageDate(new Date());
/* 199 */     StorageLocation sl = slManager.getStorageLocation("XBK001");
/* 200 */     proGoline.setLocationCode(sl);
/*     */ 
/*     */     
/* 203 */     proGoline = manager.insertProductGoline(proGoline);
/*     */     
/* 205 */     return new ActionForward("listProductGoline.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward listProductGoline(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 211 */     ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
/* 212 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 213 */     List<ProductGoline> resultList = new ArrayList();
/*     */     
/* 215 */     List totalNumberList = manager.getTotalNumberList();
/* 216 */     for (Iterator<String> iterator = totalNumberList.iterator(); iterator.hasNext(); ) {
/*     */       
/* 218 */       String totalNumber = iterator.next();
/* 219 */       conditions.put(ProductGolineQueryCondition.TOTAL_NUMBER_EQ, totalNumber);
/* 220 */       conditions.put(ProductGolineQueryCondition.STATUS_EQ, Integer.valueOf(1));
/* 221 */       List<ProductGoline> list = manager.getProductGolineList(conditions, 0, 1, null, false);
/* 222 */       if (list.size() > 0) {
/*     */         
/* 224 */         int qty = manager.getProductGolineListCount(conditions);
/* 225 */         ProductGoline pg = list.get(0);
/* 226 */         pg.setQty(Integer.valueOf(qty));
/* 227 */         resultList.add(pg);
/*     */       } 
/*     */     } 
/* 230 */     request.setAttribute("X_RESULTLIST", resultList);
/* 231 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward connectSys(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 237 */     JSONObject jo = new JSONObject();
/* 238 */     Date date = new Date();
/* 239 */     SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 240 */     String refreshTime = sf.format(date);
/* 241 */     jo.put("refreshTime", refreshTime);
/* 242 */     response.getWriter().print(jo);
/* 243 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward materialReduction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 249 */     ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
/* 250 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 251 */     InventoryMovingManager movingManager = ServiceLocator.getInventoryMovingManager(request);
/* 252 */     ProduceBuckleMaterialManager pbmManager = ServiceLocator.getProduceBuckleMaterialManager(request);
/* 253 */     WmsPartManager wpManager = ServiceLocator.getWmsPartManager(request);
/* 254 */     StorageLocationManager slManager = ServiceLocator.getStorageLocationManager(request);
/* 255 */     InventoryManager itManager = ServiceLocator.getInventoryManager(request);
/* 256 */     BomManager bmanager = ServiceLocator.getBomManager(request);
/* 257 */     String[] ids = request.getParameterValues("cid");
/* 258 */     for (int i = 0; i < ids.length; i++) {
/*     */       
/* 260 */       Integer id = Integer.valueOf(Integer.parseInt(ids[i]));
/* 261 */       ProductGoline pg = manager.getProductGoline(id);
/* 262 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 263 */       conditions.put(BomQueryCondition.PRODUCT_NO_EQ, pg.getHncCode());
/* 264 */       List<Bom> bl = bmanager.getBomList(conditions, 0, -1, null, false);
/*     */       
/* 266 */       String totalNumber = pg.getTotalNumber();
/* 267 */       String hncCode = pg.getHncCode();
/*     */       
/* 269 */       conditions.clear();
/* 270 */       conditions.put(ProductGolineQueryCondition.TOTAL_NUMBER_EQ, totalNumber);
/* 271 */       conditions.put(ProductGolineQueryCondition.STATUS_EQ, Integer.valueOf(1));
/* 272 */       int pgListCount = manager.getProductGolineListCount(conditions);
/* 273 */       BigDecimal number = new BigDecimal(pgListCount);
/*     */       
/* 275 */       insertInventoryDetial(((Bom)bl.get(0)).getProduct_no(), slManager.getStorageLocation("XBK001"), itManager, number);
/*     */       
/* 277 */       String tireCode = pg.getPartTireCode();
/* 278 */       String hubCode = pg.getPartHubCode();
/* 279 */       String valuestemCode = pg.getPartValvestemCode();
/*     */ 
/*     */       
/* 282 */       if (!isNotBiger(boxManager, tireCode, number)) {
/* 283 */         postGlobalMessage("productGoline.partCode.notEnough", tireCode, request.getSession());
/* 284 */         return new ActionForward("listProductGoline.do", true);
/*     */       } 
/* 286 */       if (!isNotBiger(boxManager, hubCode, number)) {
/* 287 */         postGlobalMessage("productGoline.partCode.notEnough", hubCode, request.getSession());
/* 288 */         return new ActionForward("listProductGoline.do", true);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 295 */       Integer nxgrowth = nextGrowth(request);
/*     */       
/* 297 */       buckleMaterial(nxgrowth, boxManager, tireCode, number, hncCode, pbmManager, request);
/* 298 */       buckleMaterial(nxgrowth, boxManager, hubCode, number, hncCode, pbmManager, request);
/*     */ 
/*     */ 
/*     */       
/* 302 */       BomManager bm = ServiceLocator.getBomManager(request);
/* 303 */       Map<Object, Object> conditionsbm = new HashMap<Object, Object>();
/* 304 */       conditionsbm.put(BomQueryCondition.PRODUCT_NO_EQ, hncCode);
/* 305 */       conditionsbm.put(BomQueryCondition.CHILD_PART_EQ, valuestemCode);
/* 306 */       Bom bom = bm.getBomList(conditionsbm, 0, -1, null, false).get(0);
/*     */       
/* 308 */       ProduceBuckleMaterial pbm = new ProduceBuckleMaterial();
/* 309 */       pbm.setAssembly(wpManager.getWmsPart(hncCode));
/* 310 */       pbm.setPart(wpManager.getWmsPart(valuestemCode));
/* 311 */       pbm.setQty(number);
/* 312 */       pbm.setLocation(null);
/* 313 */       pbm.setSite(getCurrentUser(request).getPrimarySite());
/* 314 */       pbm.setDate(new Date());
/* 315 */       pbm.setGrowth(nxgrowth);
/* 316 */       pbm.setBom_id(bom);
/* 317 */       pbmManager.insertProduceBuckleMaterial(pbm);
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
/* 330 */       List<ProductGoline> pgList = manager.getProductGolineList(conditions, 0, -1, null, false);
/* 331 */       for (ProductGoline productGoline : pgList) {
/* 332 */         productGoline.setBuckleMaterialDate(new Date());
/* 333 */         productGoline.setStatus(Integer.valueOf(2));
/* 334 */         manager.updateProductGoline(productGoline);
/*     */       } 
/*     */     } 
/* 337 */     return new ActionForward("listProductGoline.do", true);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isNotBiger(BoxManager boxManager, String partCode, BigDecimal number) {
/* 342 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 343 */     conditions.put(BoxQueryCondition.FREEZE_EQ, Integer.valueOf(1));
/* 344 */     conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(4));
/* 345 */     conditions.put(BoxQueryCondition.PART_ID_EQ, partCode);
/*     */ 
/*     */     
/* 348 */     conditions.put(BoxQueryCondition.LOCATION_EQ, Integer.valueOf(5));
/* 349 */     List<Box> list = boxManager.getBoxList(conditions, 0, -1, null, false);
/* 350 */     BigDecimal qty = new BigDecimal(0);
/* 351 */     for (Box box : list) {
/* 352 */       qty = qty.add(box.getNumber());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 357 */     if (qty.compareTo(number) != -1) {
/* 358 */       return true;
/*     */     }
/* 360 */     return false;
/*     */   }
/*     */   private Integer nextGrowth(HttpServletRequest request) {
/* 363 */     ProduceBuckleMaterialManager pbmm = ServiceLocator.getProduceBuckleMaterialManager(request);
/* 364 */     Integer nxgrowth = pbmm.nextGrowth();
/* 365 */     if (nxgrowth == null || nxgrowth.intValue() == 0) {
/* 366 */       return Integer.valueOf(1);
/*     */     }
/* 368 */     return nxgrowth = Integer.valueOf(nxgrowth.intValue() + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   private void buckleMaterial(Integer nxgrowth, BoxManager boxManager, String partCode, BigDecimal number, String hncCode, ProduceBuckleMaterialManager pbmManager, HttpServletRequest request) {
/* 373 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 374 */     BigDecimal count = number;
/* 375 */     conditions.put(BoxQueryCondition.FREEZE_EQ, Integer.valueOf(1));
/* 376 */     conditions.put(BoxQueryCondition.STATUS_EQ, Integer.valueOf(4));
/* 377 */     conditions.put(BoxQueryCondition.PART_ID_EQ, partCode);
/* 378 */     conditions.put(BoxQueryCondition.ISNOTZERO, null);
/* 379 */     conditions.put(BoxQueryCondition.LOCATION_NOTNULL, null);
/* 380 */     List<Box> list = boxManager.getBoxList(conditions, 0, -1, BoxQueryOrder.INDATE_LINE, false); Box box; Iterator<Box> iterator;
/* 381 */     for (iterator = list.iterator(), box = iterator.next(); iterator.hasNext() && 
/* 382 */       number.compareTo(new BigDecimal(0)) == 1; ) {
/*     */ 
/*     */       
/* 385 */       number = number.subtract(box.getNumber());
/* 386 */       BigDecimal useQty = new BigDecimal(0);
/* 387 */       if (number.compareTo(new BigDecimal(0)) == 1) {
/*     */         
/* 389 */         useQty = box.getNumber();
/* 390 */         box.setNumber(new BigDecimal(0));
/*     */       } else {
/*     */         
/* 393 */         useQty = box.getNumber().add(number);
/* 394 */         BigDecimal bd = new BigDecimal(0);
/* 395 */         box.setNumber(bd.subtract(number));
/*     */       } 
/*     */       
/* 398 */       boxManager.updateBox(box);
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
/*     */ 
/*     */ 
/*     */       
/* 418 */       InventoryManager itManager = ServiceLocator.getInventoryManager(request);
/* 419 */       BigDecimal zero = new BigDecimal(0);
/* 420 */       useQty = zero.subtract(useQty);
/* 421 */       insertInventoryDetial(box.getPart(), box.getLocation(), itManager, useQty);
/*     */     } 
/*     */     
/* 424 */     BomManager bm = ServiceLocator.getBomManager(request);
/* 425 */     Map<Object, Object> conditionsbm = new HashMap<Object, Object>();
/* 426 */     conditionsbm.put(BomQueryCondition.PRODUCT_NO_EQ, hncCode);
/* 427 */     conditionsbm.put(BomQueryCondition.CHILD_PART_EQ, partCode);
/* 428 */     Bom bom = bm.getBomList(conditionsbm, 0, -1, null, false).get(0);
/*     */     
/* 430 */     ProduceBuckleMaterial pbm = new ProduceBuckleMaterial();
/* 431 */     WmsPartManager wpManager = ServiceLocator.getWmsPartManager(request);
/* 432 */     pbm.setAssembly(wpManager.getWmsPart(hncCode));
/* 433 */     pbm.setPart(((Box)list.get(0)).getPart());
/* 434 */     pbm.setQty(count);
/* 435 */     pbm.setLocation(((Box)list.get(0)).getLocation());
/* 436 */     pbm.setSite(getCurrentUser(request).getPrimarySite());
/* 437 */     pbm.setDate(new Date());
/* 438 */     pbm.setGrowth(nxgrowth);
/* 439 */     pbm.setBom_id(bom);
/* 440 */     pbmManager.insertProduceBuckleMaterial(pbm);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertInventoryDetial(WmsPart part, StorageLocation sl, InventoryManager itManager, BigDecimal qty) {
/* 446 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 448 */     conditions.put(InventoryQueryCondition.DETAIL_PART_EQ, part.getId());
/*     */     
/* 450 */     List<InventoryDetial> partQtyList = itManager.getInventoryDetialList(conditions, 0, -1, null, false);
/* 451 */     BigDecimal oldQty = new BigDecimal(0);
/* 452 */     Iterator<InventoryDetial> iterator = partQtyList.iterator(); if (iterator.hasNext()) { InventoryDetial it = iterator.next();
/* 453 */       oldQty = oldQty.add(it.getPart_qty()); }
/*     */ 
/*     */     
/* 456 */     conditions.put(InventoryQueryCondition.DETAIL_STORAGE_EQ, sl.getId());
/*     */     
/* 458 */     if (itManager.getInventoryDetialListCount(conditions) < 1) {
/* 459 */       InventoryDetial detial = new InventoryDetial();
/* 460 */       detial.setLocation(sl);
/* 461 */       detial.setNumber(new BigDecimal(0));
/* 462 */       detial.setPart(part);
/* 463 */       detial.setPart_qty(new BigDecimal(0));
/* 464 */       itManager.insertInventoryDetial(detial);
/*     */     } 
/*     */     
/* 467 */     InventoryDetial indl = itManager.getInventoryDetialList(conditions, 0, 1, null, false).get(0);
/*     */     
/* 469 */     indl.setNumber(indl.getNumber().add(qty));
/* 470 */     itManager.updateInventoryDetial(indl);
/* 471 */     conditions.clear();
/*     */     
/* 473 */     conditions.put(InventoryQueryCondition.DETAIL_PART_EQ, part.getId());
/* 474 */     List<InventoryDetial> detialList = itManager.getInventoryDetialList(conditions, 0, -1, null, false);
/*     */     
/* 476 */     for (InventoryDetial inventoryDetial : detialList) {
/* 477 */       inventoryDetial.setPart_qty(oldQty.add(qty));
/* 478 */       itManager.updateInventoryDetial(inventoryDetial);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward productDownlineList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 489 */     ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
/* 490 */     ProductGoLineQueryForm queryForm = (ProductGoLineQueryForm)form;
/* 491 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 492 */     conditions.put(ProductGolineQueryCondition.STATUS_EQ, Integer.valueOf(2));
/* 493 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 494 */     String exportType = queryForm.getExportType();
/* 495 */     if (StringUtils.isNotEmpty(exportType)) {
/* 496 */       List<ProductGoline> data = manager.getProductGolineList(conditions, 0, -1, 
/* 497 */           ProductGolineQueryOrder.HNC_CODE, false);
/* 498 */       int index = SessionTempFile.createNewTempFile(request);
/* 499 */       String fileName = "ProductDownlineExcel";
/* 500 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 501 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 504 */               MessageResources messages = ProductGolineAction.this.getResources(request);
/* 505 */               row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.hncCode"));
/* 506 */               row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.saiHeCode"));
/* 507 */               row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.hubCode"));
/* 508 */               row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.hubdesc"));
/* 509 */               row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.tireCode"));
/* 510 */               row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.tireDesc"));
/* 511 */               row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.qty"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 515 */               row.add(BeanHelper.getBeanPropertyValue(data, "hncCode"));
/* 516 */               row.add(BeanHelper.getBeanPropertyValue(data, "shCode"));
/* 517 */               row.add(BeanHelper.getBeanPropertyValue(data, "partTireCode"));
/* 518 */               row.add(BeanHelper.getBeanPropertyValue(data, "tireDesc"));
/* 519 */               row.add(BeanHelper.getBeanPropertyValue(data, "partHubCode"));
/* 520 */               row.add(BeanHelper.getBeanPropertyValue(data, "hubDesc"));
/* 521 */               row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
/*     */             }
/*     */           });
/* 524 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, 
/* 525 */           true);
/*     */     } 
/* 527 */     if (queryForm.isFirstInit()) {
/* 528 */       queryForm.init(manager.getProductGolineListCount(conditions));
/*     */     } else {
/* 530 */       queryForm.init();
/*     */     } 
/* 532 */     List<ProductGoline> result = manager.getProductGolineList(conditions, queryForm.getPageNoAsInt(), 
/* 533 */         queryForm.getPageSizeAsInt(), ProductGolineQueryOrder.HNC_CODE, false);
/* 534 */     request.setAttribute("x_selType", Integer.valueOf(107));
/* 535 */     request.setAttribute("X_RESULTLIST", result);
/* 536 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward productInObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 542 */     ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
/* 543 */     FinishedToolPutnumberManager ftpm = ServiceLocator.getFinishedToolPutnumberManager(request);
/*     */     
/* 545 */     String str = request.getParameter("array");
/* 546 */     String[] arrays = str.split(",");
/*     */     
/* 548 */     List<ProductGoline> listPg = new ArrayList<ProductGoline>(); byte b; int i; String[] arrayOfString1;
/* 549 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 550 */       ProductGoline productGoline = manager.getProductGoline(Integer.valueOf(Integer.parseInt(id)));
/* 551 */       if (productGoline != null) {
/* 552 */         listPg.add(productGoline);
/*     */       }
/*     */       b++; }
/*     */     
/* 556 */     ProductGoline pg = listPg.get(0);
/* 557 */     String code = pg.getHncCode();
/* 558 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 559 */     conditions.put(FinishedToolPutnumberQueryCondition.FINISHEDCODE_EQ, code);
/*     */     
/* 561 */     List<FinishedToolPutnumber> finished = ftpm.getFinishedToolPutnumberList(conditions, 0, -1, null, false);
/* 562 */     FinishedToolPutnumber fin = null;
/* 563 */     if (finished != null) {
/* 564 */       fin = finished.get(0);
/*     */     }
/* 566 */     request.setAttribute("X_PRODUCTLIST", listPg);
/* 567 */     request.setAttribute("X_PRODUCTTOOL", fin);
/* 568 */     request.setAttribute("X_PRODUCTQTY", Integer.valueOf(listPg.size()));
/* 569 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward productDownInStorage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 575 */     ProductGolineManager pgm = ServiceLocator.getProductGolineManager(request);
/* 576 */     StorageLocationManager slm = ServiceLocator.getStorageLocationManager(request);
/* 577 */     InventoryManager manager = ServiceLocator.getInventoryManager(request);
/* 578 */     FinishedToolPutnumberManager ftm = ServiceLocator.getFinishedToolPutnumberManager(request);
/* 579 */     InventoryMovingManager movingManager = ServiceLocator.getInventoryMovingManager(request);
/* 580 */     WmsPartManager partManager = ServiceLocator.getWmsPartManager(request);
/* 581 */     InventoryMoving moving = new InventoryMoving();
/* 582 */     String[] idsStrings = request.getParameterValues("ids");
/* 583 */     String toolCode = request.getParameter("tool");
/* 584 */     String location_code = request.getParameter("locationId");
/*     */     
/* 586 */     StorageLocation sl = slm.getStorageLocation(Integer.valueOf(Integer.parseInt(location_code)));
/*     */     
/* 588 */     FinishedToolPutnumber ftl = ftm.getFinishedToolPutnumber(Integer.valueOf(Integer.parseInt(toolCode)));
/*     */     
/* 590 */     List<ProductGoline> golineList = new ArrayList<ProductGoline>();
/*     */     
/* 592 */     int tqty = 0;
/* 593 */     for (int i = 0; i < idsStrings.length; i++) {
/* 594 */       Integer id = Integer.valueOf(Integer.parseInt(idsStrings[i]));
/* 595 */       ProductGoline pg = pgm.getProductGoline(id);
/* 596 */       pg.setLocationCode(sl);
/* 597 */       pg.setTool(ftl);
/* 598 */       pg.setStatus(Integer.valueOf(3));
/* 599 */       pgm.updateProductGoline(pg);
/* 600 */       tqty += pg.getQty().intValue();
/* 601 */       golineList.add(pg);
/*     */     } 
/* 603 */     BigDecimal big = new BigDecimal(tqty);
/* 604 */     StorageLocation xbk = slm.getStorageLocation("XBK001");
/*     */     
/* 606 */     WmsPart part = partManager.getWmsPart(((ProductGoline)golineList.get(0)).getHncCode());
/*     */     
/* 608 */     BigDecimal qty = BigDecimal.valueOf(golineList.size());
/*     */     
/* 610 */     BigDecimal rqty = BigDecimal.valueOf(-tqty);
/* 611 */     moving.setOld_location(xbk);
/* 612 */     moving.setPart(part);
/* 613 */     moving.setNew_location(sl);
/* 614 */     moving.setQty(big);
/* 615 */     moving.setDate(new Date());
/* 616 */     moving.setIs_sync(YesNo.NO);
/* 617 */     moving.setRemark("成品入库：" + xbk.getCode() + "-" + sl.getCode() + ",成品号：" + part.getId());
/* 618 */     movingManager.insertInventoryMoving(moving);
/*     */ 
/*     */ 
/*     */     
/* 622 */     insertInventoryDetial(part, sl, manager, qty);
/*     */ 
/*     */     
/* 625 */     insertInventoryDetial(part, xbk, manager, rqty);
/*     */     
/* 627 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 633 */     ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
/* 634 */     StorageLocationManager slManager = ServiceLocator.getStorageLocationManager(request);
/* 635 */     ProductGoLineQueryForm queryForm = (ProductGoLineQueryForm)form;
/* 636 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/* 638 */     StorageLocation xbk = slManager.getStorageLocation("XBK001");
/* 639 */     conditions.put(ProductGolineQueryCondition.LOCATION_CODE_EQ, xbk.getId());
/* 640 */     conditions.put(ProductGolineQueryCondition.STATUS_NE, new Object[] { Integer.valueOf(1), Integer.valueOf(4) });
/* 641 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 643 */     List hnCode = manager.getHncCodeList();
/* 644 */     List<ProductGoline> resultList = new ArrayList();
/*     */     
/* 646 */     for (Iterator<String> iterator = hnCode.iterator(); iterator.hasNext(); ) {
/* 647 */       String hncCode = iterator.next();
/*     */       
/* 649 */       conditions.put(ProductGolineQueryCondition.HNC_CODE_EQ, hncCode);
/* 650 */       List<ProductGoline> list = manager.getProductGolineList(conditions, 0, -1, null, false);
/* 651 */       if (list.size() > 0) {
/*     */         
/* 653 */         int qty = manager.getProductGolineListCount(conditions);
/* 654 */         ProductGoline pg = list.get(0);
/* 655 */         pg.setQty(Integer.valueOf(qty));
/* 656 */         resultList.add(pg);
/*     */       } 
/*     */     } 
/* 659 */     if (queryForm.isFirstInit()) {
/* 660 */       queryForm.init(resultList.size());
/*     */     } else {
/* 662 */       queryForm.init();
/*     */     } 
/* 664 */     request.setAttribute("X_RESULTLIST", resultList);
/* 665 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward checkhncCodeByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 671 */     response.setContentType("text/json");
/* 672 */     response.setCharacterEncoding("UTF-8");
/* 673 */     JsonConfig cfg = new JsonConfig();
/* 674 */     FinishedToolPutnumberManager manager = ServiceLocator.getFinishedToolPutnumberManager(request);
/*     */     
/* 676 */     String hncCode = request.getParameter("hncCode");
/* 677 */     HashedMap<FinishedToolPutnumberQueryCondition, String> hashedMap = new HashedMap();
/* 678 */     hashedMap.put(FinishedToolPutnumberQueryCondition.FINISHEDCODE_EQ, hncCode);
/*     */     
/* 680 */     int count = manager.getFinishedToolPutnumberListCount((Map)hashedMap);
/*     */     
/* 682 */     if (count > 0) {
/* 683 */       JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 684 */       response.getWriter().print(jo);
/*     */     } else {
/* 686 */       JSONArray jo = JSONArray.fromObject(Boolean.valueOf(false), cfg);
/* 687 */       response.getWriter().print(jo);
/*     */     } 
/* 689 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward checkhncCountByAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 695 */     response.setContentType("text/json");
/* 696 */     response.setCharacterEncoding("UTF-8");
/* 697 */     JsonConfig cfg = new JsonConfig();
/* 698 */     ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
/* 699 */     InventoryManager iMnager = ServiceLocator.getInventoryManager(request);
/*     */     
/* 701 */     String str = request.getParameter("array");
/* 702 */     String[] arrays = str.split(",");
/*     */     
/* 704 */     List<ProductGoline> listPg = new ArrayList<ProductGoline>(); byte b; int i; String[] arrayOfString1;
/* 705 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 706 */       ProductGoline productGoline = manager.getProductGoline(Integer.valueOf(Integer.parseInt(id)));
/* 707 */       if (productGoline != null) {
/* 708 */         listPg.add(productGoline);
/*     */       }
/*     */       b++; }
/*     */     
/* 712 */     ProductGoline pg = listPg.get(0);
/* 713 */     String code = pg.getHncCode();
/* 714 */     BigDecimal count = new BigDecimal(listPg.size());
/*     */     
/* 716 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 717 */     conditions.put(InventoryQueryCondition.DETAIL_STORAGE_ID_EQ, Integer.valueOf(5));
/* 718 */     conditions.put(InventoryQueryCondition.DETAIL_PART_EQ, code);
/* 719 */     List<InventoryDetial> list = iMnager.getInventoryDetialList(conditions, 0, -1, null, false);
/* 720 */     BigDecimal number = ((InventoryDetial)list.get(0)).getNumber();
/*     */     
/* 722 */     if (number.compareTo(count) != -1) {
/* 723 */       JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 724 */       response.getWriter().print(jo);
/*     */     } else {
/* 726 */       JSONArray jo = JSONArray.fromObject(Boolean.valueOf(false), cfg);
/* 727 */       response.getWriter().print(jo);
/*     */     } 
/* 729 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward TransferList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 735 */     ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
/* 736 */     ProductGoLineQueryForm queryForm = (ProductGoLineQueryForm)form;
/*     */     
/* 738 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 739 */     conditions.put(ProductGolineQueryCondition.STATUS_EQ, Integer.valueOf(3));
/*     */ 
/*     */     
/* 742 */     conditions.put(ProductGolineQueryCondition.STOREROOM_TYPE_NE, Integer.valueOf(5));
/*     */     
/* 744 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 745 */     String exportType = queryForm.getExportType();
/* 746 */     if (StringUtils.isNotEmpty(exportType)) {
/* 747 */       List<ProductGoline> data = manager.getProductGolineList(conditions, 0, -1, 
/* 748 */           ProductGolineQueryOrder.HNC_CODE, false);
/* 749 */       int index = SessionTempFile.createNewTempFile(request);
/* 750 */       String fileName = "ProductDownlineExcel";
/* 751 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 752 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 755 */               MessageResources messages = ProductGolineAction.this.getResources(request);
/* 756 */               row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.hncCode"));
/* 757 */               row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.saiHeCode"));
/* 758 */               row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.hubCode"));
/* 759 */               row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.hubdesc"));
/* 760 */               row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.tireCode"));
/* 761 */               row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.tireDesc"));
/* 762 */               row.add(messages.getMessage(ProductGolineAction.this.getLocale(request), "ProductDownline.qty"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 766 */               row.add(BeanHelper.getBeanPropertyValue(data, "hncCode"));
/* 767 */               row.add(BeanHelper.getBeanPropertyValue(data, "shCode"));
/* 768 */               row.add(BeanHelper.getBeanPropertyValue(data, "partTireCode"));
/* 769 */               row.add(BeanHelper.getBeanPropertyValue(data, "tireDesc"));
/* 770 */               row.add(BeanHelper.getBeanPropertyValue(data, "partHubCode"));
/* 771 */               row.add(BeanHelper.getBeanPropertyValue(data, "hubDesc"));
/* 772 */               row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
/*     */             }
/*     */           });
/* 775 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, 
/* 776 */           true);
/*     */     } 
/* 778 */     if (queryForm.isFirstInit()) {
/* 779 */       queryForm.init(manager.getProductGolineListCount(conditions));
/*     */     } else {
/* 781 */       queryForm.init();
/*     */     } 
/* 783 */     List<ProductGoline> result = manager.getProductGolineList(conditions, queryForm.getPageNoAsInt(), 
/* 784 */         queryForm.getPageSizeAsInt(), ProductGolineQueryOrder.HNC_CODE, false);
/* 785 */     request.setAttribute("x_selType", Integer.valueOf(110));
/* 786 */     request.setAttribute("X_RESULTLIST", result);
/* 787 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward TransferNew(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 793 */     ProductGolineManager manager = ServiceLocator.getProductGolineManager(request);
/* 794 */     FinishedToolPutnumberManager ftpm = ServiceLocator.getFinishedToolPutnumberManager(request);
/*     */     
/* 796 */     String str = request.getParameter("array");
/* 797 */     String[] arrays = str.split(",");
/*     */     
/* 799 */     List<ProductGoline> listPg = new ArrayList<ProductGoline>(); byte b; int i; String[] arrayOfString1;
/* 800 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 801 */       ProductGoline pg = manager.getProductGoline(Integer.valueOf(Integer.parseInt(id)));
/* 802 */       if (pg != null) {
/* 803 */         listPg.add(pg);
/*     */       }
/* 805 */       request.setAttribute("X_code", pg.getLocationCode().getCode());
/* 806 */       request.setAttribute("X_codeid", pg.getLocationCode().getId());
/*     */       b++; }
/*     */     
/* 809 */     request.setAttribute("X_PRODUCTLIST", listPg);
/* 810 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward productTransferUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 815 */     ProductGolineManager pgm = ServiceLocator.getProductGolineManager(request);
/* 816 */     StorageLocationManager slm = ServiceLocator.getStorageLocationManager(request);
/* 817 */     InventoryManager manager = ServiceLocator.getInventoryManager(request);
/* 818 */     FinishedToolPutnumberManager ftm = ServiceLocator.getFinishedToolPutnumberManager(request);
/* 819 */     WmsPartManager partManager = ServiceLocator.getWmsPartManager(request);
/* 820 */     InventoryMovingManager movingManager = ServiceLocator.getInventoryMovingManager(request);
/* 821 */     String[] idsStrings = request.getParameterValues("ids");
/* 822 */     String location_code = request.getParameter("locationId");
/*     */     
/* 824 */     StorageLocation sl = slm.getStorageLocation(Integer.valueOf(Integer.parseInt(location_code)));
/*     */     
/* 826 */     List<ProductGoline> golineList = new ArrayList<ProductGoline>();
/* 827 */     ProductGoline pg = null;
/* 828 */     InventoryMoving moving = new InventoryMoving();
/* 829 */     StorageLocation oldLocation = null;
/* 830 */     BigDecimal qty = new BigDecimal(1);
/* 831 */     int count = 0;
/* 832 */     String[] code = new String[idsStrings.length];
/* 833 */     List<String> list = new ArrayList<String>();
/* 834 */     for (int i = 0; i < idsStrings.length; i++) {
/* 835 */       Integer id = Integer.valueOf(Integer.parseInt(idsStrings[i]));
/* 836 */       pg = pgm.getProductGoline(id);
/* 837 */       if (oldLocation == null) {
/* 838 */         oldLocation = pg.getLocationCode();
/*     */       }
/* 840 */       code[i] = pg.getHncCode();
/* 841 */       if (sl.getBasic_storeroom_id().getType() == StoreRoomType.RAWMATERIALSLINE) {
/* 842 */         pg.setStatus(Integer.valueOf(2));
/*     */       }
/* 844 */       pg.setLocationCode(sl);
/* 845 */       pgm.updateProductGoline(pg);
/* 846 */       golineList.add(pg);
/*     */     }  byte b; int j;
/*     */     String[] arrayOfString1;
/* 849 */     for (j = (arrayOfString1 = code).length, b = 0; b < j; ) { String str = arrayOfString1[b];
/* 850 */       if (!list.contains(str))
/* 851 */         list.add(str); 
/*     */       b++; }
/*     */     
/* 854 */     for (String str : list) {
/* 855 */       int num = 0; byte b1; int k; String[] arrayOfString;
/* 856 */       for (k = (arrayOfString = code).length, b1 = 0; b1 < k; ) { String s = arrayOfString[b1];
/* 857 */         if (s.equals(str))
/* 858 */           num++; 
/*     */         b1++; }
/*     */       
/* 861 */       WmsPart wmsPart = partManager.getWmsPart(str);
/* 862 */       moving.setOld_location(oldLocation);
/* 863 */       moving.setPart(wmsPart);
/* 864 */       moving.setNew_location(sl);
/* 865 */       moving.setQty(new BigDecimal(num));
/* 866 */       moving.setDate(new Date());
/* 867 */       moving.setIs_sync(YesNo.NO);
/* 868 */       moving.setRemark("成品移库：" + oldLocation.getCode() + "-" + sl.getCode() + ",成品号：" + wmsPart.getId());
/* 869 */       movingManager.insertInventoryMoving(moving);
/* 870 */       insertInventoryDetial(partManager.getWmsPart(str), oldLocation, manager, new BigDecimal(-num));
/* 871 */       insertInventoryDetial(partManager.getWmsPart(str), sl, manager, new BigDecimal(num));
/*     */     } 
/*     */     
/* 874 */     WmsPart part = partManager.getWmsPart(((ProductGoline)golineList.get(0)).getHncCode());
/* 875 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/product/ProductGolineAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */