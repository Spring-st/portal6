/*     */ package com.aof.web.struts.action.po;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.metadata.PurchaseOrderRqcStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.PurchaseOrderRqc;
/*     */ import com.aof.model.po.query.PurchaseOrderRqcQueryCondition;
/*     */ import com.aof.model.po.query.PurchaseOrderRqcQueryOrder;
/*     */ import com.aof.service.po.PurchaseOrderRQCManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.po.PurchaseOrderRQCQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JsonConfig;
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
/*     */ public class PurchaseOrderRQCAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  68 */     PurchaseOrderRQCQueryForm queryForm = (PurchaseOrderRQCQueryForm)form;
/*  69 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  70 */       queryForm.setOrder(PurchaseOrderRqcQueryOrder.ID.getName());
/*  71 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/*  74 */     PurchaseOrderRQCManager fm = ServiceLocator.getPurchaseOrderRQCManager(request);
/*  75 */     Map conditions = constructQueryMap(queryForm);
/*  76 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  78 */     String exportType = queryForm.getExportType();
/*  79 */     if (StringUtils.isNotEmpty(exportType)) {
/*  80 */       List data = fm.getPurchaseOrderRqcList(conditions, 0, -1, PurchaseOrderRqcQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  82 */       int index = SessionTempFile.createNewTempFile(request);
/*  83 */       String fileName = "purchaseOrder";
/*  84 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  87 */               MessageResources messages = PurchaseOrderRQCAction.this.getResources(request);
/*  88 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.number"));
/*  89 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.row"));
/*  90 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.id"));
/*  91 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.code"));
/*  92 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "DPI"));
/*  93 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.part.describe1"));
/*  94 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.po_supp"));
/*  95 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.po_supp_name"));
/*  96 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.date"));
/*  97 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "rqc.time"));
/*  98 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "title.shippingOrder.number"));
/*  99 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "need_qty_rqc"));
/* 100 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "actual_qty_rqc"));
/* 101 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "qualified_qty"));
/* 102 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "unqualified_qty"));
/* 103 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "WmsUW.status.chnShortDescription"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 107 */               row.add(BeanHelper.getBeanPropertyValue(data, "po_number"));
/* 108 */               row.add(BeanHelper.getBeanPropertyValue(data, "line"));
/* 109 */               row.add(BeanHelper.getBeanPropertyValue(data, "boxId.lot.id"));
/* 110 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
/* 111 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.dpiNo"));
/* 112 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
/* 113 */               row.add(BeanHelper.getBeanPropertyValue(data, "supper.code"));
/* 114 */               row.add(BeanHelper.getBeanPropertyValue(data, "supper.name"));
/* 115 */               row.add(BeanHelper.getBeanPropertyValue(data, "po_date"));
/* 116 */               row.add(BeanHelper.getBeanPropertyValue(data, "create_date"));
/* 117 */               row.add(BeanHelper.getBeanPropertyValue(data, "po_qty"));
/* 118 */               row.add(BeanHelper.getBeanPropertyValue(data, "need_qty_rqc"));
/* 119 */               row.add(BeanHelper.getBeanPropertyValue(data, "actual_qty_rqc"));
/* 120 */               row.add(BeanHelper.getBeanPropertyValue(data, "qualified_qty"));
/* 121 */               if (BeanHelper.getBeanPropertyValue(data, "unqualified_qty") == null) {
/* 122 */                 row.add("0");
/*     */               } else {
/* 124 */                 row.add(BeanHelper.getBeanPropertyValue(data, "unqualified_qty"));
/*     */               } 
/* 126 */               row.add(BeanHelper.getBeanPropertyValue(data, "boxId.status.chnShortDescription"));
/*     */             }
/*     */           });
/*     */       
/* 130 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 132 */     if (queryForm.isFirstInit()) {
/* 133 */       queryForm.init(fm.getPurchaseOrderRqcListCount(conditions));
/*     */     } else {
/* 135 */       queryForm.init();
/*     */     } 
/* 137 */     List result = fm.getPurchaseOrderRqcList(conditions, 
/* 138 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 139 */         PurchaseOrderRqcQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 141 */     request.setAttribute("X_RESULTLIST", result);
/* 142 */     request.setAttribute("x_selType", Integer.valueOf(99));
/* 143 */     putEnumListToRequest(request);
/* 144 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward listClose(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 150 */     PurchaseOrderRQCQueryForm queryForm = (PurchaseOrderRQCQueryForm)form;
/* 151 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 152 */       queryForm.setOrder(PurchaseOrderRqcQueryOrder.ID.getName());
/* 153 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 156 */     PurchaseOrderRQCManager fm = ServiceLocator.getPurchaseOrderRQCManager(request);
/* 157 */     Map conditions = constructQueryMap(queryForm);
/* 158 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 160 */     String exportType = queryForm.getExportType();
/* 161 */     if (StringUtils.isNotEmpty(exportType)) {
/* 162 */       List data = fm.getPurchaseOrderRqcList(conditions, 0, -1, PurchaseOrderRqcQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 164 */       int index = SessionTempFile.createNewTempFile(request);
/* 165 */       String fileName = "purchaseOrder";
/* 166 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 169 */               MessageResources messages = PurchaseOrderRQCAction.this.getResources(request);
/* 170 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "purchaseOrder.id"));
/* 171 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "customer.line"));
/* 172 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "customer.wmspart.id"));
/* 173 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "DPI"));
/* 174 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "ProduceBuckleMaterial.assembly.describe1"));
/* 175 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "Box.po_supp"));
/* 176 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "Box.po_supp_name"));
/* 177 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.date"));
/* 178 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "purchaseOrder.rqcdate"));
/* 179 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "title.shippingOrder.number"));
/* 180 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "need_qty_rqc"));
/* 181 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "actual_qty_rqc"));
/* 182 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "qualified_qty"));
/* 183 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "unqualified_qty"));
/* 184 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "WmsUW.status.chnShortDescription"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 188 */               row.add(BeanHelper.getBeanPropertyValue(data, "po_number"));
/* 189 */               row.add(BeanHelper.getBeanPropertyValue(data, "line"));
/* 190 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
/* 191 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.dpiNo"));
/* 192 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
/* 193 */               row.add(BeanHelper.getBeanPropertyValue(data, "supper.code"));
/* 194 */               row.add(BeanHelper.getBeanPropertyValue(data, "supper.name"));
/* 195 */               row.add(BeanHelper.getBeanPropertyValue(data, "po_date"));
/* 196 */               row.add(BeanHelper.getBeanPropertyValue(data, "create_date"));
/* 197 */               row.add(BeanHelper.getBeanPropertyValue(data, "po_qty"));
/* 198 */               row.add(BeanHelper.getBeanPropertyValue(data, "need_qty_rqc"));
/* 199 */               row.add(BeanHelper.getBeanPropertyValue(data, "actual_qty_rqc"));
/* 200 */               row.add(BeanHelper.getBeanPropertyValue(data, "qualified_qty"));
/* 201 */               row.add(BeanHelper.getBeanPropertyValue(data, "unqualified_qty"));
/* 202 */               row.add(BeanHelper.getBeanPropertyValue(data, "status.chnShortDescription"));
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */       
/* 208 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 210 */     if (queryForm.isFirstInit()) {
/* 211 */       queryForm.init(fm.getPurchaseOrderRqcListCount(conditions));
/*     */     } else {
/* 213 */       queryForm.init();
/*     */     } 
/* 215 */     List result = fm.getPurchaseOrderRqcList(conditions, 
/* 216 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 217 */         PurchaseOrderRqcQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 219 */     request.setAttribute("X_RESULTLIST", result);
/* 220 */     request.setAttribute("x_selType", Integer.valueOf(33));
/* 221 */     putEnumListToRequest(request);
/* 222 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward close(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 228 */     response.setContentType("text/json");
///* 229 */     response.setCharacterEncoding("UTF-8");
/* 230 */     JsonConfig cfg = new JsonConfig();
/*     */     
/* 232 */     PurchaseOrderRQCManager manager = ServiceLocator.getPurchaseOrderRQCManager(request);
/* 233 */     String array = request.getParameter("arrays");
/* 234 */     String[] arrays = array.split(","); byte b; int i; String[] arrayOfString1;
/* 235 */     for (i = (arrayOfString1 = arrays).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 236 */       PurchaseOrderRqc rqc = manager.getPurchaseOrderRqc(Integer.valueOf(Integer.parseInt(id)));
/* 237 */       rqc.setStatus(PurchaseOrderRqcStatus.CLOSE);
/* 238 */       manager.updatePurchaseOrderRqc(rqc);
/*     */       b++; }
/*     */     
/* 241 */     JSONArray jo = JSONArray.fromObject(Boolean.valueOf(true), cfg);
/* 242 */     response.getWriter().print(jo);
/* 243 */     return null;
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
/*     */   public ActionForward listPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 259 */     PurchaseOrderRQCQueryForm queryForm = (PurchaseOrderRQCQueryForm)form;
/* 260 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 261 */       queryForm.setOrder(PurchaseOrderRqcQueryOrder.ID.getName());
/* 262 */       queryForm.setDescend(false);
/*     */     } 
/* 264 */     if (queryForm.getStatus() == null) {
/* 265 */       queryForm.setStatus("1");
/*     */     }
/* 267 */     PurchaseOrderRQCManager fm = ServiceLocator.getPurchaseOrderRQCManager(request);
/* 268 */     Map conditions = constructQueryMap(queryForm);
/* 269 */     String exportType = queryForm.getExportType();
/* 270 */     if (StringUtils.isNotEmpty(exportType)) {
/* 271 */       List data = fm.getPurchaseOrderRqcList(conditions, 0, -1, PurchaseOrderRqcQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 273 */       int index = SessionTempFile.createNewTempFile(request);
/* 274 */       String fileName = "purchaseOrder";
/* 275 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 278 */               MessageResources messages = PurchaseOrderRQCAction.this.getResources(request);
/* 279 */               row.add(messages.getMessage(PurchaseOrderRQCAction.this.getLocale(request), "purchaseOrder.id"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 283 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*     */             }
/*     */           });
/*     */       
/* 287 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 289 */     if (queryForm.isFirstInit()) {
/* 290 */       queryForm.init(fm.getPurchaseOrderRqcListCount(conditions));
/*     */     } else {
/* 292 */       queryForm.init();
/*     */     } 
/* 294 */     List result = fm.getPurchaseOrderRqcList(conditions, 
/* 295 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 296 */         PurchaseOrderRqcQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 298 */     request.setAttribute("X_RESULTLIST", result);
/* 299 */     putEnumListToRequest(request);
/* 300 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(PurchaseOrderRQCQueryForm queryForm) {
/* 304 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 305 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 306 */     if (id != null && !id.equals("")) {
/* 307 */       conditions.put(PurchaseOrderRqcQueryCondition.ID_EQ, id);
/*     */     }
/*     */ 
/*     */     
/* 311 */     return conditions;
/*     */   }
/*     */   
/*     */   private PurchaseOrderRqc getPurchaseOrderRQC(HttpServletRequest request) throws Exception {
/* 315 */     String id = request.getParameter("id");
/* 316 */     PurchaseOrderRQCManager purchaseOrderManager = ServiceLocator.getPurchaseOrderRQCManager(request);
/* 317 */     PurchaseOrderRqc purchaseOrder = purchaseOrderManager.getPurchaseOrderRqc(Integer.valueOf(Integer.parseInt(id)));
/* 318 */     if (purchaseOrder == null)
/* 319 */       throw new ActionException("purchaseOrder.notFound", id); 
/* 320 */     return purchaseOrder;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 324 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/* 325 */     request.setAttribute("X_SITELIST", getAndCheckGrantedSiteList(request));
/* 326 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(PurchaseOrderRqcStatus.class));
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
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 343 */     return mapping.findForward("page");
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
/*     */ 
/*     */   
/*     */   public ActionForward editPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 361 */     return mapping.findForward("page");
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 377 */     BeanForm purchaseOrderForm = (BeanForm)form;
/* 378 */     PurchaseOrderRqc purchaseOrder = new PurchaseOrderRqc();
/* 379 */     purchaseOrderForm.populate(purchaseOrder, "to_bean");
/*     */     
/* 381 */     PurchaseOrderRQCManager purchaseOrderManager = ServiceLocator.getPurchaseOrderRQCManager(request);
/*     */ 
/*     */ 
/*     */     
/* 385 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 389 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 393 */     String s = request.getParameter("site_id");
/* 394 */     return (s != null && !s.equals(""));
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/po/PurchaseOrderRQCAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */