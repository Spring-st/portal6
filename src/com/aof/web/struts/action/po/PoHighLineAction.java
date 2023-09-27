/*     */ package com.aof.web.struts.action.po;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.PurchaseOrderStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.query.PoHighLineTwoQueryOrder;
/*     */ import com.aof.model.po.query.PurchaseOrderQueryCondition;
/*     */ import com.aof.model.po.query.PurchaseOrderQueryOrder;
/*     */ import com.aof.service.po.PoHighLineManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.po.PoHighLineQueryForm;
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
/*     */ public class PoHighLineAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  60 */     PoHighLineQueryForm queryForm = (PoHighLineQueryForm)form;
/*  61 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  62 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/*  63 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/*  66 */     PoHighLineManager fm = ServiceLocator.getPoHighLineManager(request);
/*  67 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  69 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  71 */     String exportType = queryForm.getExportType();
/*  72 */     if (StringUtils.isNotEmpty(exportType)) {
/*  73 */       List data = fm.getPoHighLineOneList(conditions, 0, -1, PoHighLineTwoQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  75 */       int index = SessionTempFile.createNewTempFile(request);
/*  76 */       String fileName = "purchaseOrder";
/*  77 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  80 */               MessageResources messages = PoHighLineAction.this.getResources(request);
/*  81 */               row.add(messages.getMessage(PoHighLineAction.this.getLocale(request), "purchaseOrder.id"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  85 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*     */             }
/*     */           });
/*     */       
/*  89 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*  91 */     if (queryForm.isFirstInit()) {
/*  92 */       queryForm.init(fm.getPoHighLineOneListCount(conditions));
/*     */     } else {
/*  94 */       queryForm.init();
/*     */     } 
/*  96 */     List result = fm.getPoHighLineOneList(conditions, 
/*  97 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/*  98 */         PoHighLineTwoQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 100 */     putEnumListToRequest(request);
/* 101 */     request.setAttribute("X_RESULTLIST", result);
/* 102 */     request.setAttribute("x_selType", Integer.valueOf(88));
/* 103 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward selectPoHighLine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 109 */     PoHighLineQueryForm queryForm = (PoHighLineQueryForm)form;
/* 110 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 111 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/* 112 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 115 */     PoHighLineManager fm = ServiceLocator.getPoHighLineManager(request);
/* 116 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/* 118 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 120 */     String exportType = queryForm.getExportType();
/* 121 */     if (StringUtils.isNotEmpty(exportType)) {
/* 122 */       List data = fm.getPoHighLineOneList(conditions, 0, -1, PoHighLineTwoQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 124 */       int index = SessionTempFile.createNewTempFile(request);
/* 125 */       String fileName = "purchaseOrder";
/* 126 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 129 */               MessageResources messages = PoHighLineAction.this.getResources(request);
/* 130 */               row.add(messages.getMessage(PoHighLineAction.this.getLocale(request), "purchaseOrder.id"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 134 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*     */             }
/*     */           });
/*     */       
/* 138 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 140 */     if (queryForm.isFirstInit()) {
/* 141 */       queryForm.init(fm.getPoHighLineOneListCount(conditions));
/*     */     } else {
/* 143 */       queryForm.init();
/*     */     } 
/* 145 */     List result = fm.getPoHighLineOneList(conditions, 
/* 146 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 147 */         PoHighLineTwoQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 149 */     putEnumListToRequest(request);
/* 150 */     request.setAttribute("X_RESULTLIST", result);
/* 151 */     request.setAttribute("x_selType", Integer.valueOf(76));
/* 152 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward listPoHighLineTwo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 158 */     PoHighLineQueryForm queryForm = (PoHighLineQueryForm)form;
/* 159 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 160 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/* 161 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 164 */     PoHighLineManager fm = ServiceLocator.getPoHighLineManager(request);
/* 165 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/* 167 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 169 */     String exportType = queryForm.getExportType();
/* 170 */     if (StringUtils.isNotEmpty(exportType)) {
/* 171 */       List data = fm.getPoHighLineTwoList(conditions, 0, -1, PoHighLineTwoQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 173 */       int index = SessionTempFile.createNewTempFile(request);
/* 174 */       String fileName = "purchaseOrder";
/* 175 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 178 */               MessageResources messages = PoHighLineAction.this.getResources(request);
/* 179 */               row.add(messages.getMessage(PoHighLineAction.this.getLocale(request), "purchaseOrder.id"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 183 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*     */             }
/*     */           });
/*     */       
/* 187 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 189 */     if (queryForm.isFirstInit()) {
/* 190 */       queryForm.init(fm.getPoHighLineTwoListCount(conditions));
/*     */     } else {
/* 192 */       queryForm.init();
/*     */     } 
/* 194 */     List result = fm.getPoHighLineTwoList(conditions, 
/* 195 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 196 */         PoHighLineTwoQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 198 */     putEnumListToRequest(request);
/* 199 */     request.setAttribute("X_RESULTLIST", result);
/* 200 */     request.setAttribute("x_selType", Integer.valueOf(87));
/* 201 */     return mapping.findForward("page");
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
/*     */   public ActionForward listBelowLine(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 216 */     PoHighLineQueryForm queryForm = (PoHighLineQueryForm)form;
/* 217 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 218 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/* 219 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 222 */     PoHighLineManager fm = ServiceLocator.getPoHighLineManager(request);
/* 223 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/* 225 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 227 */     String exportType = queryForm.getExportType();
/* 228 */     if (StringUtils.isNotEmpty(exportType)) {
/* 229 */       List data = fm.getPoHighLineBelowList(conditions, 0, -1, PoHighLineTwoQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 231 */       int index = SessionTempFile.createNewTempFile(request);
/* 232 */       String fileName = "purchaseOrder";
/* 233 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 236 */               MessageResources messages = PoHighLineAction.this.getResources(request);
/* 237 */               row.add(messages.getMessage(PoHighLineAction.this.getLocale(request), "purchaseOrder.id"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 241 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*     */             }
/*     */           });
/*     */       
/* 245 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 247 */     if (queryForm.isFirstInit()) {
/* 248 */       queryForm.init(fm.getPoHighLineBelowListCount(conditions));
/*     */     } else {
/* 250 */       queryForm.init();
/*     */     } 
/* 252 */     List result = fm.getPoHighLineBelowList(conditions, 
/* 253 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 254 */         PoHighLineTwoQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 256 */     request.setAttribute("X_RESULTLIST", result);
/* 257 */     putEnumListToRequest(request);
/* 258 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(PoHighLineQueryForm queryForm) {
/* 262 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 263 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 264 */     if (id != null && !id.equals("")) {
/* 265 */       conditions.put(PurchaseOrderQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/* 268 */     return conditions;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 272 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/* 273 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 274 */     request.setAttribute("x_status", PersistentEnum.getEnumList(PurchaseOrderStatus.class));
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/po/PoHighLineAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */