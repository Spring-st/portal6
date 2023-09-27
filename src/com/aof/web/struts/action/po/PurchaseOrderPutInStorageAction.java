/*     */ package com.aof.web.struts.action.po;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.metadata.PurchaseOrderPutInStorageStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.PurchaseOrderPutInStorage;
/*     */ import com.aof.model.po.query.PurchaseOrderPutInStorageQueryCondition;
/*     */ import com.aof.model.po.query.PurchaseOrderPutInStorageQueryOrder;
/*     */ import com.aof.service.po.PurchaseOrderPutInStorageManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.po.PurchaseOrderPutInStorageQueryForm;
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
/*     */ public class PurchaseOrderPutInStorageAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  66 */     PurchaseOrderPutInStorageQueryForm queryForm = (PurchaseOrderPutInStorageQueryForm)form;
/*  67 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  68 */       queryForm.setOrder(PurchaseOrderPutInStorageQueryOrder.ID.getName());
/*  69 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/*  72 */     PurchaseOrderPutInStorageManager fm = ServiceLocator.getPurchaseOrderPutInStorageManager(request);
/*  73 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  75 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  76 */     String exportType = queryForm.getExportType();
/*  77 */     if (StringUtils.isNotEmpty(exportType)) {
/*  78 */       List data = fm.getPurchaseOrderPutInStorageList(conditions, 0, -1, 
/*  79 */           PurchaseOrderPutInStorageQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  81 */       int index = SessionTempFile.createNewTempFile(request);
/*  82 */       String fileName = "purchaseOrder";
/*  83 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  86 */               MessageResources messages = PurchaseOrderPutInStorageAction.this.getResources(request);
/*  87 */               row.add(messages.getMessage(PurchaseOrderPutInStorageAction.this.getLocale(request), "storageLocation.poNumber"));
/*  88 */               row.add(messages.getMessage(PurchaseOrderPutInStorageAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.row"));
/*  89 */               row.add(messages.getMessage(PurchaseOrderPutInStorageAction.this.getLocale(request), "warehouseInventoryRepor.lotset"));
/*  90 */               row.add(messages.getMessage(PurchaseOrderPutInStorageAction.this.getLocale(request), "Box.part.id"));
/*  91 */               row.add(messages.getMessage(PurchaseOrderPutInStorageAction.this.getLocale(request), "DPI"));
/*  92 */               row.add(messages.getMessage(PurchaseOrderPutInStorageAction.this.getLocale(request), "wmspart.describe1"));
/*  93 */               row.add(messages.getMessage(PurchaseOrderPutInStorageAction.this.getLocale(request), "wmspart.describe2"));
/*  94 */               row.add(messages.getMessage(PurchaseOrderPutInStorageAction.this.getLocale(request), "Box.po_supp"));
/*  95 */               row.add(messages.getMessage(PurchaseOrderPutInStorageAction.this.getLocale(request), "Box.location.code"));
/*  96 */               row.add(messages.getMessage(PurchaseOrderPutInStorageAction.this.getLocale(request), "purchaseOrder.single"));
/*  97 */               row.add(messages.getMessage(PurchaseOrderPutInStorageAction.this.getLocale(request), "Box.in_date"));
/*  98 */               row.add(messages.getMessage(PurchaseOrderPutInStorageAction.this.getLocale(request), "purchaseOrder.receipts_qty"));
/*  99 */               row.add(messages.getMessage(PurchaseOrderPutInStorageAction.this.getLocale(request), "purchaseOrder.delivery_qty"));
/* 100 */               row.add(messages.getMessage(PurchaseOrderPutInStorageAction.this.getLocale(request), "purchaseOrder.putIn_qty"));
/* 101 */               row.add(messages.getMessage(PurchaseOrderPutInStorageAction.this.getLocale(request), "purchaseOrder.is_sync"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 105 */               row.add(BeanHelper.getBeanPropertyValue(data, "po_number"));
/* 106 */               row.add(BeanHelper.getBeanPropertyValue(data, "line"));
/* 107 */               row.add(BeanHelper.getBeanPropertyValue(data, "lotSer.id"));
/* 108 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
/* 109 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.dpiNo"));
/* 110 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
/* 111 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe2"));
/* 112 */               row.add(BeanHelper.getBeanPropertyValue(data, "supper.code"));
/* 113 */               row.add(BeanHelper.getBeanPropertyValue(data, "location.code"));
/* 114 */               if (BeanHelper.getBeanPropertyValue(data, "single") != null) {
/* 115 */                 row.add(BeanHelper.getBeanPropertyValue(data, "single.po_detial_id.capacity"));
/*     */               } else {
/* 117 */                 row.add(BeanHelper.getBeanPropertyValue(data, "poipItem.capacity"));
/*     */               } 
/* 119 */               row.add(BeanHelper.getBeanPropertyValue(data, "date"));
/* 120 */               row.add(BeanHelper.getBeanPropertyValue(data, "receipts_qty"));
/* 121 */               if (BeanHelper.getBeanPropertyValue(data, "poipItem.receiptQty") != null) {
/* 122 */                 row.add(BeanHelper.getBeanPropertyValue(data, "poipItem.receiptQty"));
/*     */               } else {
/* 124 */                 row.add("0");
/*     */               } 
/* 126 */               if (BeanHelper.getBeanPropertyValue(data, "qty") != null) {
/* 127 */                 row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
/*     */               } else {
/* 129 */                 row.add("0");
/*     */               } 
/* 131 */               if (BeanHelper.getBeanPropertyValue(data, "is_sync") == "0") {
/* 132 */                 row.add("已同步");
/*     */               }
/* 134 */               if (BeanHelper.getBeanPropertyValue(data, "is_sync") == "1") {
/* 135 */                 row.add("未同步");
/*     */               }
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */       
/* 142 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 144 */     if (queryForm.isFirstInit()) {
/* 145 */       queryForm.init(fm.getPurchaseOrderPutInStorageListCount(conditions));
/*     */     } else {
/* 147 */       queryForm.init();
/*     */     } 
/* 149 */     List result = fm.getPurchaseOrderPutInStorageList(conditions, 
/* 150 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 151 */         PurchaseOrderPutInStorageQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 153 */     request.setAttribute("X_RESULTLIST", result);
/* 154 */     request.setAttribute("x_selType", Integer.valueOf(98));
/* 155 */     putEnumListToRequest(request);
/* 156 */     return mapping.findForward("page");
/*     */   }
/*     */   private Map constructQueryMap(PurchaseOrderPutInStorageQueryForm queryForm) {
/* 159 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 160 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 161 */     if (id != null) {
/* 162 */       conditions.put(PurchaseOrderPutInStorageQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/* 165 */     return conditions;
/*     */   }
/*     */ 
/*     */   
/*     */   private PurchaseOrderPutInStorage getPurchaseOrderPutInStorage(HttpServletRequest request) throws Exception {
/* 170 */     String id = request.getParameter("id");
/* 171 */     PurchaseOrderPutInStorageManager purchaseOrderManager = ServiceLocator.getPurchaseOrderPutInStorageManager(request);
/* 172 */     PurchaseOrderPutInStorage purchaseOrder = purchaseOrderManager.getPurchaseOrderPutInStorage(Integer.valueOf(Integer.parseInt(id)));
/* 173 */     if (purchaseOrder == null) {
/* 174 */       throw new ActionException("purchaseOrder.notFound", id);
/*     */     }
/* 176 */     return purchaseOrder;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 180 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/* 181 */     request.setAttribute("X_SITELIST", getAndCheckGrantedSiteList(request));
/* 182 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(PurchaseOrderPutInStorageStatus.class));
/* 183 */     request.setAttribute("X_ISWORKREPORT", PersistentEnum.getEnumList(YesNo.class));
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
/* 200 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward editPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 207 */     return mapping.findForward("page");
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
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 223 */     return mapping.findForward("success");
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
/* 239 */     BeanForm purchaseOrderForm = (BeanForm)form;
/* 240 */     PurchaseOrderPutInStorage purchaseOrder = new PurchaseOrderPutInStorage();
/* 241 */     purchaseOrderForm.populate(purchaseOrder, "to_bean");
/* 242 */     PurchaseOrderPutInStorageManager purchaseOrderManager = ServiceLocator.getPurchaseOrderPutInStorageManager(request);
/*     */ 
/*     */ 
/*     */     
/* 246 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private Site getSiteFromRequestAndCheckPower(HttpServletRequest request) throws Exception {
/* 250 */     return getAndCheckSite("site_id", request);
/*     */   }
/*     */   
/*     */   private boolean hasSite(HttpServletRequest request) {
/* 254 */     String s = request.getParameter("site_id");
/* 255 */     return (s != null && !s.equals(""));
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/po/PurchaseOrderPutInStorageAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */