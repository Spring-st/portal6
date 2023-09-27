/*     */ package com.aof.web.struts.action.po;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.metadata.PurchaseOrderReceiptsStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.PurchaseOrderReceipts;
/*     */ import com.aof.model.po.PurchaseOrderReceiptsDetial;
/*     */ import com.aof.model.po.query.PurchaseOrderReceiptsQueryCondition;
/*     */ import com.aof.model.po.query.PurchaseOrderReceiptsQueryOrder;
/*     */ import com.aof.service.po.PurchaseOrderReceiptsManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.po.PurchaseOrderReceiptsQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
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
/*     */ public class PurchaseOrderReceiptsAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  63 */     PurchaseOrderReceiptsQueryForm queryForm = (PurchaseOrderReceiptsQueryForm)form;
/*  64 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  65 */       queryForm.setOrder(PurchaseOrderReceiptsQueryOrder.ID.getName());
/*  66 */       queryForm.setDescend(false);
/*     */     } 
/*  68 */     PurchaseOrderReceiptsManager fm = ServiceLocator.getPurchaseOrderReceiptsManager(request);
/*  69 */     Map conditions = constructQueryMap(queryForm);
/*  70 */     String exportType = queryForm.getExportType();
/*  71 */     if (StringUtils.isNotEmpty(exportType)) {
/*  72 */       List data = fm.getPurchaseOrderReceiptsItemList(conditions, 0, -1, PurchaseOrderReceiptsQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  74 */       int index = SessionTempFile.createNewTempFile(request);
/*  75 */       String fileName = "purchaseOrder";
/*  76 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  79 */               MessageResources messages = PurchaseOrderReceiptsAction.this.getResources(request);
/*  80 */               row.add(messages.getMessage(PurchaseOrderReceiptsAction.this.getLocale(request), "purchaseOrder.id"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  84 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*     */             }
/*     */           });
/*  87 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*  89 */     if (queryForm.isFirstInit()) {
/*  90 */       queryForm.init(fm.getPurchaseOrderReceiptsItemListCount(conditions));
/*     */     } else {
/*  92 */       queryForm.init();
/*     */     } 
/*  94 */     List result = fm.getPurchaseOrderReceiptsItemList(conditions, 
/*  95 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  96 */         PurchaseOrderReceiptsQueryOrder.ID, queryForm.isDescend());
/*     */     
/*  98 */     request.setAttribute("X_RESULTLIST", result);
/*  99 */     putEnumListToRequest(request);
/* 100 */     return mapping.findForward("page");
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
/*     */   public ActionForward report(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 117 */     PurchaseOrderReceiptsQueryForm queryForm = (PurchaseOrderReceiptsQueryForm)form;
/* 118 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 119 */       queryForm.setOrder(PurchaseOrderReceiptsQueryOrder.ID.getName());
/* 120 */       queryForm.setDescend(false);
/*     */     } 
/* 122 */     if (queryForm.getStatus() == null) {
/* 123 */       queryForm.setStatus("1");
/*     */     }
/* 125 */     PurchaseOrderReceiptsManager fm = ServiceLocator.getPurchaseOrderReceiptsManager(request);
/* 126 */     Map conditions = constructQueryMap(queryForm);
/* 127 */     String exportType = queryForm.getExportType();
/* 128 */     StringUtils.isNotEmpty(exportType);
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
/* 168 */     if (queryForm.isFirstInit()) {
/* 169 */       queryForm.init(fm.getPurchaseOrderReceiptsListCount(conditions));
/*     */     } else {
/* 171 */       queryForm.init();
/*     */     } 
/* 173 */     List result = fm.getPurchaseOrderReceiptsList(conditions, 
/* 174 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 175 */         PurchaseOrderReceiptsQueryOrder.ID, queryForm.isDescend());
/*     */ 
/*     */     
/* 178 */     request.setAttribute("X_RESULTLIST", result);
/* 179 */     putEnumListToRequest(request);
/* 180 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward listPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 186 */     PurchaseOrderReceiptsQueryForm queryForm = (PurchaseOrderReceiptsQueryForm)form;
/* 187 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 188 */       queryForm.setOrder(PurchaseOrderReceiptsQueryOrder.ID.getName());
/* 189 */       queryForm.setDescend(false);
/*     */     } 
/* 191 */     PurchaseOrderReceiptsManager fm = ServiceLocator.getPurchaseOrderReceiptsManager(request);
/* 192 */     Map conditions = constructQueryMap(queryForm);
/* 193 */     String exportType = queryForm.getExportType();
/* 194 */     if (StringUtils.isNotEmpty(exportType)) {
/* 195 */       List data = fm.getPurchaseOrderReceiptsList(conditions, 0, -1, PurchaseOrderReceiptsQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 197 */       int index = SessionTempFile.createNewTempFile(request);
/* 198 */       String fileName = "purchaseOrderReceipts";
/* 199 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 202 */               MessageResources messages = PurchaseOrderReceiptsAction.this.getResources(request);
/* 203 */               row.add(messages.getMessage(PurchaseOrderReceiptsAction.this.getLocale(request), "purchaseOrderReceipts.id"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 207 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*     */             }
/*     */           });
/*     */       
/* 211 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 213 */     if (queryForm.isFirstInit()) {
/* 214 */       queryForm.init(fm.getPurchaseOrderReceiptsListCount(conditions));
/*     */     } else {
/* 216 */       queryForm.init();
/*     */     } 
/* 218 */     int pageNo = queryForm.getPageNoAsInt();
/* 219 */     int pageSize = queryForm.getPageSizeAsInt();
/* 220 */     List result = fm.getPurchaseOrderReceiptsList(conditions, pageNo, pageSize, PurchaseOrderReceiptsQueryOrder.ID, queryForm.isDescend());
/* 221 */     request.setAttribute("X_RESULTLIST", result);
/* 222 */     putEnumListToRequest(request);
/* 223 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(PurchaseOrderReceiptsQueryForm queryForm) {
/* 227 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 228 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 229 */     if (id != null && !id.equals("")) {
/* 230 */       conditions.put(PurchaseOrderReceiptsQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/* 233 */     return conditions;
/*     */   }
/*     */   
/*     */   private PurchaseOrderReceipts getPurchaseOrderReceipts(HttpServletRequest request) throws Exception {
/* 237 */     String id = request.getParameter("id");
/* 238 */     PurchaseOrderReceiptsManager purchaseOrderManager = ServiceLocator.getPurchaseOrderReceiptsManager(request);
/* 239 */     PurchaseOrderReceipts purchaseOrder = purchaseOrderManager.getPurchaseOrderReceipts(Integer.valueOf(Integer.parseInt(id)));
/* 240 */     if (purchaseOrder == null) {
/* 241 */       throw new ActionException("purchaseOrder.notFound", id);
/*     */     }
/* 243 */     return purchaseOrder;
/*     */   }
/*     */   
/*     */   private PurchaseOrderReceiptsDetial getPurchaseOrderReceiptsDetial(HttpServletRequest request) throws Exception {
/* 247 */     String id = request.getParameter("id");
/* 248 */     PurchaseOrderReceiptsManager purchaseOrderManager = ServiceLocator.getPurchaseOrderReceiptsManager(request);
/* 249 */     PurchaseOrderReceiptsDetial purchaseOrderitem = purchaseOrderManager.getPurchaseOrderReceiptsDetial(Integer.valueOf(Integer.parseInt(id)));
/* 250 */     if (purchaseOrderitem == null) {
/* 251 */       throw new ActionException("purchaseOrder.notFound", id);
/*     */     }
/* 253 */     return purchaseOrderitem;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 257 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/* 258 */     request.setAttribute("X_SITELIST", getAndCheckGrantedSiteList(request));
/* 259 */     request.setAttribute("X_STATUS", PersistentEnum.getEnumList(PurchaseOrderReceiptsStatus.class));
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
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 304 */     return mapping.findForward("page");
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
/*     */   
/*     */   public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 350 */     return mapping.findForward("page");
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
/* 379 */     return mapping.findForward("page");
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
/*     */   public ActionForward alreadyPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 391 */     return null;
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
/* 406 */     PurchaseOrderReceipts purchaseOrder = getPurchaseOrderReceipts(request);
/*     */     
/* 408 */     PurchaseOrderReceiptsManager cm = ServiceLocator.getPurchaseOrderReceiptsManager(request);
/*     */     try {
/* 410 */       cm.deletePurchaseOrderReceipts(purchaseOrder);
/*     */     }
/* 412 */     catch (Throwable t) {
/*     */       
/* 414 */       throw new ActionException("purchaseOrder.delete.fail");
/*     */     } 
/* 416 */     return mapping.findForward("success");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward viewReceiptsBoxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 442 */     return mapping.findForward("page");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward updateViewReceiptsBoxListStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 465 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 469 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 473 */     String s = request.getParameter("site_id");
/* 474 */     return (s != null && !s.equals(""));
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
/*     */   public ActionForward updateItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 490 */     BeanForm purchaseOrderItemForm = (BeanForm)form;
/* 491 */     PurchaseOrderReceiptsDetial purchaseOrderItem = new PurchaseOrderReceiptsDetial();
/* 492 */     purchaseOrderItemForm.populate(purchaseOrderItem, "to_bean");
/*     */     
/* 494 */     PurchaseOrderReceiptsManager purchaseOrderItemManager = ServiceLocator.getPurchaseOrderReceiptsManager(request);
/* 495 */     purchaseOrderItemManager.updatePurchaseOrderReceiptsDetial(purchaseOrderItem);
/*     */     
/* 497 */     request.setAttribute("X_OBJECT", purchaseOrderItemManager.getPurchaseOrderReceiptsDetial(purchaseOrderItem.getId()));
/* 498 */     request.setAttribute("X_ROWPAGE", "wmspo/purchaseOrder/itemRow.jsp");
/* 499 */     return mapping.findForward("success");
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
/*     */   public ActionForward printBox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 514 */     String arrays = request.getParameter("arrays");
/* 515 */     PurchaseOrderReceiptsManager manager = ServiceLocator.getPurchaseOrderReceiptsManager(request);
/* 516 */     List list = manager.getBoxList(arrays);
/*     */     
/* 518 */     request.setAttribute("x_listMap", list);
/* 519 */     request.setAttribute("x_date", new Date());
/* 520 */     request.setAttribute("path", request.getContextPath());
/* 521 */     request.setAttribute("x_user", getCurrentUser(request));
/* 522 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward scanningPurchaseOrderReceipts(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 528 */     PurchaseOrderReceiptsManager manager = ServiceLocator.getPurchaseOrderReceiptsManager(request);
/* 529 */     String arrays = request.getParameter("arrays");
/* 530 */     String tool = request.getParameter("tool");
/*     */ 
/*     */     
/* 533 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/po/PurchaseOrderReceiptsAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */