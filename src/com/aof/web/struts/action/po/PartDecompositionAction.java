/*     */ package com.aof.web.struts.action.po;
/*     */ 
/*     */ import com.aof.model.basic.PartDecomposition;
/*     */ import com.aof.model.basic.UselessPart;
/*     */ import com.aof.model.basic.WmsPart;
/*     */ import com.aof.model.comprehensive.Bom;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.PurchaseOrderStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.po.PurchaseOrder;
/*     */ import com.aof.model.po.PurchaseOrderDetial;
/*     */ import com.aof.model.po.query.BoxQueryOrder;
/*     */ import com.aof.service.admin.SupplierManager;
/*     */ import com.aof.service.basic.PartDecompositionManager;
/*     */ import com.aof.service.basic.UselessPartManager;
/*     */ import com.aof.service.basic.WmsPartManager;
/*     */ import com.aof.service.plantWarehouse.WmsUWManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import com.aof.service.po.PurchaseOrderManager;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.po.BoxQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
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
/*     */ public class PartDecompositionAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  68 */     BoxQueryForm queryForm = (BoxQueryForm)form;
/*  69 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  70 */       queryForm.setOrder(BoxQueryOrder.ID.getName());
/*  71 */       queryForm.setDescend(false);
/*     */     } 
/*  73 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/*  74 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  76 */     if (queryForm.isFirstInit()) {
/*  77 */       queryForm.init(boxManager.getBoxListCount(conditions));
/*     */     } else {
/*  79 */       queryForm.init();
/*     */     } 
/*  81 */     List result = boxManager.getBoxList(conditions, 
/*  82 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  83 */         BoxQueryOrder.ID, queryForm.isDescend());
/*     */     
/*  85 */     request.setAttribute("X_RESULTLIST", result);
/*  86 */     request.setAttribute("x_selType", Integer.valueOf(96));
/*  87 */     putEnumListToRequest(request);
/*  88 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(BoxQueryForm queryForm) {
/*  92 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  93 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/*     */     
/*  95 */     return conditions;
/*     */   }
/*     */   
/*     */   private PurchaseOrderDetial getPurchaseOrderItem(HttpServletRequest request) throws Exception {
/*  99 */     String id = request.getParameter("id");
/* 100 */     SupplierManager supplierManager = ServiceLocator.getSupplierManager(request);
/* 101 */     PurchaseOrderManager manager = ServiceLocator.getPurchaseOrderManager(request);
/* 102 */     PurchaseOrderDetial detial = manager.getPurchaseOrderDetial(Integer.valueOf(Integer.parseInt(id)));
/*     */     
/* 104 */     if (detial == null) {
/* 105 */       throw new ActionException("purchaseOrder.notFound", id);
/*     */     }
/* 107 */     return detial;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 111 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/* 112 */     request.setAttribute("X_SITELIST", getAndCheckGrantedSiteList(request));
/* 113 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 114 */     request.setAttribute("X_WmsPurchaseOrderStatusLIST", PersistentEnum.getEnumList(PurchaseOrderStatus.class));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 120 */     putEnumListToRequest(request);
/* 121 */     return mapping.findForward("page");
/*     */   }
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
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 136 */     return mapping.findForward("success");
/*     */   }
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
/*     */   public ActionForward createPurchaseOrderIP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 152 */     return null;
/*     */   }
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
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 168 */     String id = request.getParameter("id");
/* 169 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 170 */     PartDecompositionManager pd = ServiceLocator.getPartDecompositionManager(request);
/*     */     
/* 172 */     Box box = manager.getBoxBylotSer2(id);
/* 173 */     List<Bom> listBom = manager.getBomByBox(box.getPart().getId());
/* 174 */     if (!isBack(request)) {
/* 175 */       PurchaseOrder purchaseOrder = new PurchaseOrder();
/* 176 */       BeanForm purchaseOrderForm = (BeanForm)getForm("/insertPurchaseOrder", request);
/* 177 */       purchaseOrderForm.populate(purchaseOrder, "to_form");
/*     */     } 
/* 179 */     Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
/*     */     
/* 181 */     for (Bom bom : listBom) {
/* 182 */       String partId = bom.getChild_part().getId();
/* 183 */       BigDecimal count = pd.getLotPartCount(box, partId);
/* 184 */       map.put(partId, count);
/*     */     } 
/*     */     
/* 187 */     request.setAttribute("x_box", box);
/* 188 */     request.setAttribute("x_listBom", listBom);
/* 189 */     request.setAttribute("map", map);
/* 190 */     putEnumListToRequest(request);
/* 191 */     return mapping.findForward("page");
/*     */   }
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 207 */     PurchaseOrderManager cm = ServiceLocator.getPurchaseOrderManager(request);
/*     */     
/* 209 */     BeanForm purchaseOrderForm = (BeanForm)form;
/* 210 */     PurchaseOrder purchaseOrder = new PurchaseOrder();
/* 211 */     purchaseOrderForm.populate(purchaseOrder, "to_bean");
/*     */     
/* 213 */     cm.insertPurchaseOrder(purchaseOrder);
/* 214 */     return new ActionForward("editPurchaseOrder.do?id=" + purchaseOrder.getId(), true);
/*     */   }
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
/*     */   public ActionForward decomposedPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 230 */     String str = request.getParameter("str");
/* 231 */     String[] partIds = str.split(";");
/* 232 */     WmsUWManager wManager = ServiceLocator.getWmsUWManager(request);
/* 233 */     WmsPartManager partManager = ServiceLocator.getWmsPartManager(request);
/* 234 */     PartDecompositionManager partDecompositionManager = ServiceLocator.getPartDecompositionManager(request);
/* 235 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 236 */     String id = request.getParameter("id");
/* 237 */     Box oldBox = manager.getBoxBylotSer2(id);
/* 238 */     for (int i = 0; i < partIds.length; i++) {
/* 239 */       String s = partIds[i];
/* 240 */       String[] arr = s.split(",");
/* 241 */       String partId = arr[0];
/* 242 */       BigDecimal qty = new BigDecimal(arr[1]);
/*     */       
/* 244 */       String supplier = arr[2];
/* 245 */       BigDecimal boxCapacity = new BigDecimal(arr[3]);
/* 246 */       PartDecomposition partDecomposition = new PartDecomposition();
/* 247 */       partDecomposition.setBox(oldBox);
/* 248 */       partDecomposition.setDate(new Date());
/* 249 */       partDecomposition.setOperation(getCurrentUser(request));
/* 250 */       partDecomposition.setPart(partManager.getWmsPart(partId));
/* 251 */       partDecomposition.setQty(qty);
/* 252 */       partDecomposition.setType(YesNo.YES);
/* 253 */       partDecompositionManager.insertPartDecomposition(partDecomposition);
/* 254 */       int qtyInt = qty.intValue();
/* 255 */       int boxCapacityInt = boxCapacity.intValue();
/*     */       
/* 257 */       if (qtyInt <= boxCapacityInt) {
/* 258 */         wManager.insertBox(supplier, partId, qty, new Date());
/*     */       } else {
/* 260 */         int a = qtyInt / boxCapacityInt;
/* 261 */         if (qtyInt % boxCapacityInt == 0) {
/* 262 */           for (int j = 0; j < a; j++) {
/* 263 */             wManager.insertBox(supplier, partId, boxCapacity, new Date());
/*     */           }
/*     */         } else {
/* 266 */           for (int j = 0; j < a; j++) {
/* 267 */             wManager.insertBox(supplier, partId, boxCapacity, new Date());
/*     */           }
/* 269 */           BigDecimal amount = new BigDecimal(boxCapacityInt * a);
/* 270 */           wManager.insertBox(supplier, partId, qty.subtract(amount), new Date());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 275 */     return new ActionForward("listPartDecomposition.do", true);
/*     */   }
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
/*     */   public ActionForward uselessdPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 290 */     String str = request.getParameter("str");
/* 291 */     String[] partIds = str.split(";");
/*     */     
/* 293 */     String parentPart = request.getParameter("parentPart");
/* 294 */     String lot = request.getParameter("lot");
/*     */     
/* 296 */     WmsUWManager wManager = ServiceLocator.getWmsUWManager(request);
/*     */     
/* 298 */     WmsPartManager partManager = ServiceLocator.getWmsPartManager(request);
/* 299 */     PartDecompositionManager partDecompositionManager = ServiceLocator.getPartDecompositionManager(request);
/* 300 */     BoxManager boxManager = ServiceLocator.getBoxManager(request);
/* 301 */     UselessPartManager uselessPartManager = ServiceLocator.getUselessPartManager(request);
/* 302 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 303 */     String id = request.getParameter("id");
/* 304 */     Box oldBox = manager.getBoxBylotSer2(id);
/* 305 */     WmsPart fatherPart = partManager.getWmsPart(parentPart);
/*     */     
/* 307 */     for (int i = 0; i < partIds.length; i++) {
/* 308 */       String s = partIds[i];
/* 309 */       String[] arr = s.split(",");
/* 310 */       String partId = arr[0];
/* 311 */       BigDecimal qty = new BigDecimal(arr[1]);
/*     */       
/* 313 */       String supplier = arr[2];
/* 314 */       BigDecimal boxCapacity = new BigDecimal(arr[3]);
/* 315 */       PartDecomposition partDecomposition = new PartDecomposition();
/* 316 */       partDecomposition.setBox(oldBox);
/* 317 */       partDecomposition.setDate(new Date());
/* 318 */       partDecomposition.setOperation(getCurrentUser(request));
/* 319 */       partDecomposition.setPart(partManager.getWmsPart(partId));
/* 320 */       partDecomposition.setQty(qty);
/* 321 */       partDecomposition.setType(YesNo.NO);
/* 322 */       partDecompositionManager.insertPartDecomposition(partDecomposition);
/*     */       
/* 324 */       UselessPart uselessPart = new UselessPart();
/* 325 */       uselessPart.setLot(oldBox.getLot());
/* 326 */       uselessPart.setAmount(qty);
/* 327 */       uselessPart.setFatherPart(fatherPart);
/* 328 */       uselessPart.setPart(partManager.getWmsPart(partId));
/* 329 */       uselessPart.setPartDecomposition(partDecomposition);
/* 330 */       uselessPartManager.insertUselessPart(uselessPart);
/*     */     } 
/*     */     
/* 333 */     return new ActionForward("listPartDecomposition.do", true);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/po/PartDecompositionAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */