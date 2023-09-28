/*     */ package com.aof.web.struts.action.product;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.product.SalesPreshiporderItem;
/*     */ import com.aof.model.product.query.SalesPreshiporderItemQueryCondition;
/*     */ import com.aof.model.product.query.SalesPreshiporderItemQueryOrder;
/*     */ import com.aof.service.Product.SalesPreshiporderItemManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.product.SalesPreshiporderItemQueryForm;
/*     */ import com.aof.web.struts.form.product.SalesPreshiporderQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanUtils;
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
/*     */ public class SalesPreshiporderItemAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  42 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/*  43 */     SalesPreshiporderQueryForm queryForm = (SalesPreshiporderQueryForm)form;
/*  44 */     Map conditions = getQueryConditions(queryForm);
/*  45 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  46 */     String exportType = queryForm.getExportType();
/*  47 */     if (exportType != null && exportType.length() > 0) {
/*  48 */       List data = salesPreshiporderItemManager.getList(conditions, 0, -1, null, false);
/*  49 */       int index = SessionTempFile.createNewTempFile(request);
/*  50 */       String fileName = "SalesPreshiporderItem";
/*  51 */       String suffix = ExportUtil.export(exportType, data, request, 
/*  52 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  55 */               MessageResources messages = SalesPreshiporderItemAction.this.getResources(request);
/*  56 */               row.add(messages.getMessage(SalesPreshiporderItemAction.this.getLocale(request), ""));
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  64 */               row.add(BeanUtils.getProperty(data, ""));
/*     */             }
/*     */           });
/*  67 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/*  70 */     if (queryForm.isFirstInit()) {
/*  71 */       queryForm.init(salesPreshiporderItemManager.getListCount(conditions));
/*     */     } else {
/*  73 */       queryForm.init();
/*     */     } 
/*     */     
/*  76 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/*  77 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/*  78 */     List<SalesPreshiporderItem> resultList = salesPreshiporderItemManager.getList(conditions, pageNo.intValue(), pageSize.intValue(), null, false);
/*     */     
/*  80 */     request.setAttribute("X_RESULTLIST", resultList);
/*  81 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   private Map getQueryConditions(SalesPreshiporderQueryForm queryForm) {
/*  86 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  87 */     String queryStr = "";
/*  88 */     if (queryStr != null) queryStr.trim().length();
/*     */ 
/*     */     
/*  91 */     return conditions;
/*     */   }
/*     */   
/*     */   private void getBasic(HttpServletRequest request) {
/*  95 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 100 */     getBasic(request);
/* 101 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 106 */     SalesPreshiporderItemManager manager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 107 */     BeanForm formBean = (BeanForm)form;
/* 108 */     SalesPreshiporderItem salesPreshiporderItem = new SalesPreshiporderItem();
/* 109 */     formBean.populateToBean(salesPreshiporderItem);
/* 110 */     salesPreshiporderItem = manager.insert(salesPreshiporderItem);
/* 111 */     request.setAttribute("X_OBJECT", salesPreshiporderItem);
/* 112 */     request.setAttribute("X_ROWPAGE", "salesPreshiporderItem/row.jsp");
/* 113 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 118 */     SalesPreshiporderItemManager manager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 119 */     String idStr = request.getParameter("id");
/* 120 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 121 */     SalesPreshiporderItem salesPreshiporderItem = manager.getById(id);
/* 122 */     if (salesPreshiporderItem == null) throw new ActionException("salesPreshiporderItem.notFound", id); 
/* 123 */     request.setAttribute("X_OBJECT", salesPreshiporderItem);
/* 124 */     getBasic(request);
/* 125 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 130 */     SalesPreshiporderItemManager manager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 131 */     String idStr = request.getParameter("id");
/* 132 */     Integer id = Integer.valueOf(Integer.parseInt(idStr));
/* 133 */     BeanForm formBean = (BeanForm)form;
/* 134 */     SalesPreshiporderItem salesPreshiporderItem = manager.getById(id);
/* 135 */     formBean.populateToBean(salesPreshiporderItem, request);
/* 136 */     salesPreshiporderItem = manager.update(salesPreshiporderItem);
/* 137 */     request.setAttribute("X_OBJECT", salesPreshiporderItem);
/* 138 */     request.setAttribute("X_ROWPAGE", "salesPreshiporderItem/row.jsp");
/* 139 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 144 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 145 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 146 */     salesPreshiporderItemManager.remove(salesPreshiporderItemManager.getById(id));
/* 147 */     return new ActionForward("listSalesPreshiporderItem.do", true);
/*     */   }
/*     */   
/*     */   public ActionForward reportList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 151 */     SalesPreshiporderItemManager salesPreshiporderItemManager = ServiceLocator.getSalesPreshiporderItemManager(request);
/* 152 */     SalesPreshiporderItemQueryForm queryForm = (SalesPreshiporderItemQueryForm)form;
/* 153 */     if (queryForm.getOrder() == "") {
/*     */       
/* 155 */       queryForm.setOrder(SalesPreshiporderItemQueryOrder.ID.getName());
/* 156 */       queryForm.setDescend(true);
/*     */     } 
/* 158 */     Map conditions = new HashMap();
/* 159 */     getConditionAndOrder(queryForm, conditions, request);
/* 160 */     conditions.put(SalesPreshiporderItemQueryCondition.SALESSHIPORDER_TYPE_EQ, Integer.valueOf(2));
/*     */     
/* 162 */     String exportType = queryForm.getExportType();
/* 163 */     if (exportType != null && exportType.length() > 0) {
/* 164 */       List data = salesPreshiporderItemManager.getList(conditions, 0, -1, null, false);
/* 165 */       int index = SessionTempFile.createNewTempFile(request);
/* 166 */       String fileName = "SalesPreshiporderItem";
/* 167 */       String suffix = ExportUtil.export(exportType, data, request, 
/* 168 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 171 */               MessageResources messages = SalesPreshiporderItemAction.this.getResources(request);
/* 172 */               row.add(messages.getMessage(SalesPreshiporderItemAction.this.getLocale(request), "salesWorkorder.shipId"));
/* 173 */               row.add("需求计划单号");
/* 174 */               row.add("客户编码");
/* 175 */               row.add("打印日期");
/* 176 */               row.add("物料号");
/* 177 */               row.add("物料描述");
/* 178 */               row.add("发货数量");
/* 179 */               row.add("单价");
/* 180 */               row.add("金额");
/* 181 */               row.add("是否已匹配");
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/* 190 */               row.add(BeanUtils.getProperty(data, "spsoId.code"));
/* 191 */               row.add(BeanUtils.getProperty(data, "customerPlanId.planNumbers"));
/* 192 */               row.add(BeanUtils.getProperty(data, "customerPlanId.customer.code"));
/* 193 */               row.add(BeanUtils.getProperty(data, "spsoId.shPrintDate"));
/* 194 */               row.add(BeanUtils.getProperty(data, "customerPlanId.wmspart.id"));
/* 195 */               row.add(BeanUtils.getProperty(data, "customerPlanId.wmspart.describe1"));
/* 196 */               row.add(BeanUtils.getProperty(data, "shipQty"));
/* 197 */               row.add(BeanUtils.getProperty(data, "customerPlanId.unitPrice"));
/* 198 */               SalesPreshiporderItem item = (SalesPreshiporderItem)data;
/*     */ 
/*     */               
/* 201 */               row.add(item.getShipQty().multiply(item.getCustomerPlanId().getUnitPrice()));
/* 202 */               String matchStatus = BeanUtils.getProperty(data, "spsoId.matchStatus");
/* 203 */               if (matchStatus == null) {
/* 204 */                 row.add("未匹配");
/*     */               }
/* 206 */               else if (matchStatus.equals("0")) {
/* 207 */                 row.add("已匹配");
/*     */               } else {
/* 209 */                 row.add("未匹配");
/*     */               } 
/*     */             }
/*     */           });
/*     */       
/* 214 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */     
/* 217 */     if (queryForm.isFirstInit()) {
/* 218 */       queryForm.init(salesPreshiporderItemManager.getListCount(conditions));
/*     */     } else {
/* 220 */       queryForm.init();
/*     */     } 
/*     */     
/* 223 */     Integer pageNo = Integer.valueOf(queryForm.getPageNoAsInt());
/* 224 */     Integer pageSize = Integer.valueOf(queryForm.getPageSizeAsInt());
/* 225 */     List<SalesPreshiporderItem> resultList = salesPreshiporderItemManager.getList(conditions, pageNo.intValue(), pageSize.intValue(), SalesPreshiporderItemQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 226 */     request.setAttribute("x_selType", Integer.valueOf(150));
/* 227 */     request.setAttribute("X_RESULTLIST", resultList);
/* 228 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/product/SalesPreshiporderItemAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */