/*     */ package com.aof.web.struts.action.product;
/*     */ 
/*     */ import com.aof.model.admin.FinishedSaiheRelation;
/*     */ import com.aof.model.admin.query.FinishedSaiheRelationQueryCondition;
/*     */ import com.aof.model.basic.StorageLocation;
/*     */ import com.aof.model.basic.StoreRoom;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.basic.query.StoreRoomQueryCondition;
/*     */ import com.aof.model.inventory.InventoryDetial;
/*     */ import com.aof.model.inventory.query.InventoryQueryCondition;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.StoreRoomType;
/*     */ import com.aof.model.product.ProductGoline;
/*     */ import com.aof.model.product.ProductOutStorage;
/*     */ import com.aof.model.product.query.ProductGolineQueryCondition;
/*     */ import com.aof.model.product.query.ProductOutStorageQueryOrder;
/*     */ import com.aof.service.Product.ProductGolineManager;
/*     */ import com.aof.service.Product.ProductOutStorageManager;
/*     */ import com.aof.service.admin.FinishedSaiheRelationManager;
/*     */ import com.aof.service.basic.StorageLocationManager;
/*     */ import com.aof.service.basic.StoreRoomManager;
/*     */ import com.aof.service.basic.WmsPartManager;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.po.ProductOutStorageQueryForm;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.json.JSONObject;
/*     */ import net.sf.json.JsonConfig;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProductOutStorageAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  52 */     ProductOutStorageManager manager = ServiceLocator.getProductOutStorageManager(request);
/*  53 */     ProductOutStorageQueryForm queryForm = (ProductOutStorageQueryForm)form;
/*  54 */     Map conditions = new HashMap();
/*  55 */     conditions.put(ProductGolineQueryCondition.STATUS_EQ, Integer.valueOf(2));
/*     */     
/*  57 */     getConditionAndOrder(queryForm, conditions, request);
/*  58 */     if (queryForm.isFirstInit()) {
/*  59 */       queryForm.init(manager.getProductOutStorageListCount(conditions));
/*     */     } else {
/*  61 */       queryForm.init();
/*     */     } 
/*     */     
/*  64 */     List<ProductOutStorage> result = manager.getProductOutStorageList(conditions, queryForm.getPageNoAsInt(), 
/*  65 */         queryForm.getPageSizeAsInt(), ProductOutStorageQueryOrder.ID, queryForm.isDescend());
/*     */     
/*  67 */     request.setAttribute("x_selType", Integer.valueOf(108));
/*  68 */     request.setAttribute("X_RESULTLIST", result);
/*  69 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward productOutObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  74 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  75 */     conditions.put(StoreRoomQueryCondition.TYPE_EQ, StoreRoomType.OUTLOCATION);
/*  76 */     conditions.put(StoreRoomQueryCondition.ENABLED_EQ, EnabledDisabled.ENABLED);
/*  77 */     List<StoreRoom> StoreRoomList = ServiceLocator.getStoreRoomManager(request).getStoreRoomList(conditions, 0, -1, null, false);
/*  78 */     request.setAttribute("X_STOREROOMLIST", StoreRoomList);
/*  79 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   private void insertInventoryDetial(WmsPart part, StorageLocation sl, InventoryManager itManager, BigDecimal qty) {
/*  84 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/*  86 */     conditions.put(InventoryQueryCondition.DETAIL_PART_EQ, part.getId());
/*     */     
/*  88 */     List<InventoryDetial> partQtyList = itManager.getInventoryDetialList(conditions, 0, -1, null, false);
/*  89 */     BigDecimal oldQty = new BigDecimal(0);
/*  90 */     Iterator<InventoryDetial> iterator = partQtyList.iterator(); if (iterator.hasNext()) { InventoryDetial it = iterator.next();
/*  91 */       oldQty = oldQty.add(it.getPart_qty()); }
/*     */ 
/*     */     
/*  94 */     conditions.put(InventoryQueryCondition.DETAIL_STORAGE_EQ, sl.getId());
/*     */     
/*  96 */     if (itManager.getInventoryDetialListCount(conditions) < 1) {
/*  97 */       InventoryDetial detial = new InventoryDetial();
/*  98 */       detial.setLocation(sl);
/*  99 */       detial.setNumber(new BigDecimal(0));
/* 100 */       detial.setPart(part);
/* 101 */       detial.setPart_qty(new BigDecimal(0));
/* 102 */       itManager.insertInventoryDetial(detial);
/*     */     } 
/*     */     
/* 105 */     InventoryDetial indl = (InventoryDetial) itManager.getInventoryDetialList(conditions, 0, 1, null, false).get(0);
/*     */     
/* 107 */     indl.setNumber(indl.getNumber().add(qty));
/* 108 */     itManager.updateInventoryDetial(indl);
/* 109 */     conditions.clear();
/*     */     
/* 111 */     conditions.put(InventoryQueryCondition.DETAIL_PART_EQ, part.getId());
/* 112 */     List<InventoryDetial> detialList = itManager.getInventoryDetialList(conditions, 0, -1, null, false);
/*     */     
/* 114 */     for (InventoryDetial inventoryDetial : detialList) {
/* 115 */       inventoryDetial.setPart_qty(oldQty.add(qty));
/* 116 */       itManager.updateInventoryDetial(inventoryDetial);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 123 */     ProductOutStorageManager posm = ServiceLocator.getProductOutStorageManager(request);
/* 124 */     FinishedSaiheRelationManager fmanager = ServiceLocator.getFinishedSaiheRelationManager(request);
/* 125 */     StoreRoomManager srm = ServiceLocator.getStoreRoomManager(request);
/* 126 */     String code = request.getParameter("hncCode");
/* 127 */     String desc = request.getParameter("description");
/* 128 */     String qty = request.getParameter("qty");
/* 129 */     Integer outqty = Integer.valueOf(Integer.parseInt(qty));
/* 130 */     String storeroom_id = request.getParameter("storeroom_id");
/* 131 */     Integer outstoreroom_id = Integer.valueOf(Integer.parseInt(storeroom_id));
/*     */ 
/*     */     
/* 134 */     ProductOutStorage productOutStorage = new ProductOutStorage();
/* 135 */     productOutStorage.setDescription(desc);
/* 136 */     productOutStorage.setHncCode(code);
/* 137 */     productOutStorage.setQty(outqty);
/* 138 */     productOutStorage.setStoreroom(srm.getStoreRoom(outstoreroom_id));
/*     */     
/* 140 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 141 */     conditions.put(FinishedSaiheRelationQueryCondition.FINISHEDCODE_EQ, code);
/* 142 */     List<FinishedSaiheRelation> fsrList = fmanager.getFinishedSaiheRelationList(conditions, 0, -1, null, false);
/* 143 */     if (fsrList.size() > 0) {
/* 144 */       productOutStorage.setSaiheCode(((FinishedSaiheRelation)fsrList.get(0)).getSaiheCode());
/*     */     }
/*     */     
/* 147 */     posm.insertProductOutStorage(productOutStorage);
/*     */     
/* 149 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward confirmProductOutStorage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
/* 154 */     ProductOutStorageManager posManager = ServiceLocator.getProductOutStorageManager(request);
/* 155 */     ProductGolineManager pgManager = ServiceLocator.getProductGolineManager(request);
/* 156 */     StorageLocationManager slManager = ServiceLocator.getStorageLocationManager(request);
/* 157 */     WmsPartManager partManager = ServiceLocator.getWmsPartManager(request);
/* 158 */     InventoryManager itManager = ServiceLocator.getInventoryManager(request);
/*     */     
/* 160 */     String array = request.getParameter("ids");
/* 161 */     String[] ids = array.split(","); byte b; int i; String[] arrayOfString1;
/* 162 */     for (i = (arrayOfString1 = ids).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 163 */       ProductOutStorage pos = posManager.getProductOutStorage(Integer.valueOf(Integer.parseInt(id)));
/* 164 */       String hncCode = pos.getHncCode();
/* 165 */       int qty = pos.getQty().intValue();
/* 166 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */       
/* 168 */       StorageLocation sl = slManager.getStorageLocation("XBK001");
/*     */       
/* 170 */       conditions.put(ProductGolineQueryCondition.HNC_CODE_EQ, hncCode);
/* 171 */       conditions.put(ProductGolineQueryCondition.LOCATION_CODE_EQ, sl.getId());
/* 172 */       conditions.put(ProductGolineQueryCondition.STATUS_EQ, Integer.valueOf(2));
/* 173 */       List<ProductGoline> list = pgManager.getProductGolineList(conditions, 0, -1, null, false);
/* 174 */       for (int j = 0; j < qty; j++) {
/* 175 */         ProductGoline goline = list.get(j);
/* 176 */         goline.setStatus(Integer.valueOf(4));
/* 177 */         pgManager.updateProductGoline(goline);
/*     */       } 
/*     */       
/* 180 */       WmsPart part = partManager.getWmsPart(hncCode);
/*     */       
/* 182 */       BigDecimal oqty = BigDecimal.valueOf(-qty);
/*     */       
/* 184 */       insertInventoryDetial(part, sl, itManager, oqty);
/*     */       
/* 186 */       pos.setStatus(1);
/* 187 */       pos.setOutDate(new Date());
/* 188 */       posManager.updateProductOutStorage(pos); b++; }
/*     */     
/* 190 */     return new ActionForward("productOutStorageList.do", true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward confirmProductOutStorageAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 196 */     response.setContentType("text/json");
/* 198 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 200 */     ProductOutStorageManager posManager = ServiceLocator.getProductOutStorageManager(request);
/* 201 */     ProductGolineManager pgManager = ServiceLocator.getProductGolineManager(request);
/* 202 */     StorageLocationManager slManager = ServiceLocator.getStorageLocationManager(request);
/* 203 */     WmsPartManager partManager = ServiceLocator.getWmsPartManager(request);
/* 204 */     InventoryManager itManager = ServiceLocator.getInventoryManager(request);
/* 205 */     Map<String, Integer> map = new HashMap<String, Integer>();
/* 206 */     Map<String, Object> maps = new HashMap<String, Object>();
/* 207 */     String array = request.getParameter("ids");
/* 208 */     String[] ids = array.split(","); byte b; int i; String[] arrayOfString1;
/* 209 */     for (i = (arrayOfString1 = ids).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 210 */       ProductOutStorage pos = posManager.getProductOutStorage(Integer.valueOf(Integer.parseInt(id)));
/* 211 */       String hncCode = pos.getHncCode();
/* 212 */       int qty = pos.getQty().intValue();
/* 213 */       if (map.get(hncCode) == null) {
/* 214 */         map.put(hncCode, Integer.valueOf(qty));
/*     */       } else {
/* 216 */         int amount = ((Integer)map.get(hncCode)).intValue();
/* 217 */         map.put(hncCode, Integer.valueOf(qty + amount));
/*     */       }  b++; }
/*     */     
/* 220 */     for (String str : map.keySet()) {
/* 221 */       Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */       
/* 223 */       StorageLocation sl = slManager.getStorageLocation("XBK001");
/*     */       
/* 225 */       conditions.put(ProductGolineQueryCondition.HNC_CODE_EQ, str);
/* 226 */       conditions.put(ProductGolineQueryCondition.LOCATION_CODE_EQ, sl.getId());
/* 227 */       conditions.put(ProductGolineQueryCondition.STATUS_EQ, Integer.valueOf(2));
/* 228 */       int count = pgManager.getProductGolineListCountAjax(conditions);
/* 229 */       int qty = ((Integer)map.get(str)).intValue();
/* 230 */       if (qty > count) {
/* 231 */         maps.put("result", "1");
/* 232 */         maps.put("hnccode", str);
/* 233 */         maps.put("count", Integer.valueOf(count));
/*     */         break;
/*     */       } 
/* 236 */       maps.put("result", "0");
/*     */     } 
/*     */     
/* 239 */     JSONObject jo = JSONObject.fromObject(maps, cfg);
/* 240 */     response.getWriter().print(jo);
/* 241 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/product/ProductOutStorageAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */