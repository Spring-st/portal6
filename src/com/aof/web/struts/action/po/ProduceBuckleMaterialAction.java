/*     */ package com.aof.web.struts.action.po;
/*     */ 
/*     */ import com.aof.model.po.query.ProduceBuckleMaterialQueryOrder;
/*     */ import com.aof.model.po.query.PurchaseOrderQueryCondition;
/*     */ import com.aof.model.po.query.PurchaseOrderQueryOrder;
/*     */ import com.aof.service.po.ProduceBuckleMaterialManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.po.ProduceBuckleMaterialQueryForm;
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
/*     */ public class ProduceBuckleMaterialAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  56 */     ProduceBuckleMaterialQueryForm queryForm = (ProduceBuckleMaterialQueryForm)form;
/*  57 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  58 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/*  59 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/*  62 */     ProduceBuckleMaterialManager fm = ServiceLocator.getProduceBuckleMaterialManager(request);
/*  63 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  65 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  67 */     String exportType = queryForm.getExportType();
/*  68 */     if (StringUtils.isNotEmpty(exportType)) {
/*  69 */       List data = fm.getProduceBuckleMaterialList(conditions, 0, -1, ProduceBuckleMaterialQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  71 */       int index = SessionTempFile.createNewTempFile(request);
/*  72 */       String fileName = "ProduceBuckleMaterial";
/*  73 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  76 */               MessageResources messages = ProduceBuckleMaterialAction.this.getResources(request);
/*  77 */               row.add(messages.getMessage(ProduceBuckleMaterialAction.this.getLocale(request), "ProduceBuckleMaterial.assembly.id"));
/*  78 */               row.add(messages.getMessage(ProduceBuckleMaterialAction.this.getLocale(request), "ProduceBuckleMaterial.assembly.describe1"));
/*  79 */               row.add(messages.getMessage(ProduceBuckleMaterialAction.this.getLocale(request), "ProduceBuckleMaterial.part.id"));
/*  80 */               row.add(messages.getMessage(ProduceBuckleMaterialAction.this.getLocale(request), "ProduceBuckleMaterial.part.describe1"));
/*  81 */               row.add(messages.getMessage(ProduceBuckleMaterialAction.this.getLocale(request), "ProduceBuckleMaterial.location.code"));
/*  82 */               row.add(messages.getMessage(ProduceBuckleMaterialAction.this.getLocale(request), "ProduceBuckleMaterial.qty"));
/*  83 */               row.add(messages.getMessage(ProduceBuckleMaterialAction.this.getLocale(request), "ProduceBuckleMaterial.date"));
/*  84 */               row.add(messages.getMessage(ProduceBuckleMaterialAction.this.getLocale(request), "ProduceBuckleMaterial.is_sync"));
/*  85 */               row.add(messages.getMessage(ProduceBuckleMaterialAction.this.getLocale(request), "ProduceBuckleMaterial.is_sync_date"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  89 */               row.add(BeanHelper.getBeanPropertyValue(data, "assembly.id"));
/*  90 */               row.add(BeanHelper.getBeanPropertyValue(data, "assembly.describe1"));
/*  91 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.id"));
/*  92 */               row.add(BeanHelper.getBeanPropertyValue(data, "part.describe1"));
/*  93 */               row.add(BeanHelper.getBeanPropertyValue(data, "location.code"));
/*  94 */               row.add(BeanHelper.getBeanPropertyValue(data, "qty"));
/*  95 */               row.add(BeanHelper.getBeanPropertyValue(data, "date"));
/*  96 */               if (ProduceBuckleMaterialAction.this.isEnglish(request)) {
/*  97 */                 row.add(BeanHelper.getBeanPropertyValue(data, "is_sync.engShortDescription"));
/*     */               } else {
/*  99 */                 row.add(BeanHelper.getBeanPropertyValue(data, "is_sync.chnShortDescription"));
/* 100 */               }  row.add(BeanHelper.getBeanPropertyValue(data, "is_sync_date"));
/*     */             }
/*     */           });
/*     */       
/* 104 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 106 */     if (queryForm.isFirstInit()) {
/* 107 */       queryForm.init(fm.getProduceBuckleMaterialListCount(conditions));
/*     */     } else {
/* 109 */       queryForm.init();
/*     */     } 
/* 111 */     List result = fm.getProduceBuckleMaterialList(conditions, 
/* 112 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 113 */         ProduceBuckleMaterialQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 115 */     request.setAttribute("X_RESULTLIST", result);
/* 116 */     request.setAttribute("x_selType", Integer.valueOf(90));
/* 117 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(ProduceBuckleMaterialQueryForm queryForm) {
/* 121 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 122 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 123 */     if (id != null && !id.equals("")) {
/* 124 */       conditions.put(PurchaseOrderQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/* 127 */     return conditions;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/po/ProduceBuckleMaterialAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */