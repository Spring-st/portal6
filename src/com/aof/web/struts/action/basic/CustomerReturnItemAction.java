/*     */ package com.aof.web.struts.action.basic;
/*     */ 
/*     */ import com.aof.model.basic.CustomerReturnItem;
/*     */ import com.aof.model.basic.query.CustomerReturnItemQueryOrder;
/*     */ import com.aof.service.admin.SiteManager;
/*     */ import com.aof.service.basic.CustomerReturnItemManager;
/*     */ import com.aof.service.basic.CustomerreturnsManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.CustomerReturnItemQueryForm;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
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
/*     */ public class CustomerReturnItemAction
/*     */   extends BaseAction
/*     */ {
/*     */   private Map getConditions(CustomerReturnItemQueryForm formBean) {
/*  36 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  37 */     return conditions;
/*     */   }
/*     */   
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  41 */     return null;
/*     */   }
/*     */   
/*     */   public ActionForward reportList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  45 */     CustomerreturnsManager manager = ServiceLocator.getCustomerreturnsManager(request);
/*  46 */     SiteManager siteManager = ServiceLocator.getSiteManager(request);
/*  47 */     CustomerReturnItemManager customerReturnItemManager = ServiceLocator.getCustomerReturnItemManager(request);
/*  48 */     CustomerReturnItemQueryForm formBean = (CustomerReturnItemQueryForm)form;
/*  49 */     if (formBean.getOrder() == "") {
/*  50 */       formBean.setDescend(true);
/*     */     }
/*  52 */     Map conditions = getConditions(formBean);
/*  53 */     getConditionAndOrder((BaseSessionQueryForm)formBean, conditions, request);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     String exportType = formBean.getExportType();
/*  61 */     if (exportType != null && exportType.length() > 0) {
/*  62 */       List datas = customerReturnItemManager.getList(conditions, 0, -1, CustomerReturnItemQueryOrder.getEnum(formBean.getOrder()), formBean.isDescend());
/*  63 */       int index = SessionTempFile.createNewTempFile(request);
/*  64 */       String fileName = "CustomerReturnReport";
/*  65 */       String suffix = ExportUtil.export(exportType, datas, request, 
/*  66 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportRow(List<String> row, Object data, HttpServletRequest request) throws Exception {
/*  69 */               row.add(BeanUtils.getProperty(data, "customerreturns.returnNumber"));
/*  70 */               row.add(BeanUtils.getProperty(data, "batchNumber"));
/*  71 */               row.add(BeanUtils.getProperty(data, "part.id"));
/*  72 */               row.add(BeanUtils.getProperty(data, "part.describe1"));
/*  73 */               row.add(BeanUtils.getProperty(data, "part.oldCode"));
/*  74 */               row.add(BeanUtils.getProperty(data, "returnStorage"));
/*  75 */               row.add(BeanUtils.getProperty(data, "qty"));
/*  76 */               row.add(BeanUtils.getProperty(data, "salesDeliveryDate"));
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  81 */               MessageResources message = CustomerReturnItemAction.this.getResources(request);
/*  82 */               row.add("退货单号");
/*  83 */               row.add("条码编号");
/*  84 */               row.add("物料编号");
/*  85 */               row.add("物料描述");
/*  86 */               row.add("原厂编号");
/*  87 */               row.add("退货接收库位");
/*  88 */               row.add("数量");
/*  89 */               row.add("销售出库时间");
/*     */             }
/*     */           });
/*     */       
/*  93 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/*  95 */     if (formBean.isFirstInit()) {
/*  96 */       formBean.init(manager.getListCount(conditions).intValue());
/*     */     } else {
/*  98 */       formBean.init();
/*     */     } 
/* 100 */     int pageNum = formBean.getPageNoAsInt();
/* 101 */     int pageSize = formBean.getPageSizeAsInt();
/* 102 */     List<CustomerReturnItem> customerReturnItemList = customerReturnItemManager.getList(conditions, pageNum, pageSize, CustomerReturnItemQueryOrder.ID, formBean.isDescend());
/*     */ 
/*     */     
/* 105 */     request.setAttribute("x_selType", Integer.valueOf(152));
/* 106 */     request.setAttribute("X_RESULTLIST", customerReturnItemList);
/* 107 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/CustomerReturnItemAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */