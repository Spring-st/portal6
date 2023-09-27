/*     */ package com.aof.web.struts.action.po;
/*     */ 
/*     */ import com.aof.model.inventory.query.InventoryTransitQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.query.PurchaseOrderQueryCondition;
/*     */ import com.aof.model.po.query.PurchaseOrderQueryOrder;
/*     */ import com.aof.service.inventory.InventoryTransitManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.po.InventoryTransitQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionUtils;
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
/*     */ public class InventoryTransitAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  60 */     InventoryTransitQueryForm queryForm = (InventoryTransitQueryForm)form;
/*  61 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  62 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/*  63 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/*  66 */     InventoryTransitManager manager = ServiceLocator.getInventoryTransitManager(request);
/*  67 */     Map conditions = constructQueryMap(queryForm);
/*  68 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  70 */     String exportType = queryForm.getExportType();
/*  71 */     if (StringUtils.isNotEmpty(exportType)) {
/*  72 */       List data = manager.getInventoryTransitList(conditions, 0, -1, InventoryTransitQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  74 */       int index = SessionTempFile.createNewTempFile(request);
/*  75 */       String fileName = "InventoryTransit";
/*  76 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  79 */               MessageResources messages = InventoryTransitAction.this.getResources(request);
/*  80 */               row.add(messages.getMessage(InventoryTransitAction.this.getLocale(request), "InventoryTransit.part.id"));
/*  81 */               row.add(messages.getMessage(InventoryTransitAction.this.getLocale(request), "InventoryTransit.location.code"));
/*  82 */               row.add(messages.getMessage(InventoryTransitAction.this.getLocale(request), "InventoryTransit.location.code.desc"));
/*  83 */               row.add(messages.getMessage(InventoryTransitAction.this.getLocale(request), "InventoryTransit.part.describe1"));
/*  84 */               row.add(messages.getMessage(InventoryTransitAction.this.getLocale(request), "InventoryTransit.part.describe2"));
/*  85 */               row.add(messages.getMessage(InventoryTransitAction.this.getLocale(request), "InventoryTransit.qty"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  89 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
/*  90 */               row.add(BeanHelper.getBeanPropertyValue(data, "location.code"));
/*  91 */               row.add("中转库");
/*  92 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
/*  93 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe2"));
/*  94 */               row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
/*     */             }
/*     */           });
/*     */       
/*  98 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 100 */     int count = manager.getInventoryTransitListCount(conditions);
/* 101 */     if (queryForm.isFirstInit()) {
/* 102 */       queryForm.init(manager.getInventoryTransitListCount(conditions));
/*     */     } else {
/* 104 */       queryForm.init();
/*     */     } 
/* 106 */     List result = manager.getInventoryTransitList(conditions, 
/* 107 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 108 */         InventoryTransitQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 110 */     putEnumListToRequest(request);
/* 111 */     request.setAttribute("X_RESULTLIST", result);
/* 112 */     request.setAttribute("x_selType", Integer.valueOf(92));
/* 113 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 117 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/* 118 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(InventoryTransitQueryForm queryForm) {
/* 122 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 123 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 124 */     if (id != null && !id.equals("")) {
/* 125 */       conditions.put(PurchaseOrderQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/* 128 */     return conditions;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/po/InventoryTransitAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */