/*     */ package com.aof.web.struts.action.product;
/*     */ 
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.product.Product;
/*     */ import com.aof.model.product.query.ProductBelowLineQueryOrder;
/*     */ import com.aof.model.product.query.ProductQueryCondition;
/*     */ import com.aof.model.product.query.ProductQueryOrder;
/*     */ import com.aof.service.Product.ProductBelowLineManager;
/*     */ import com.aof.service.Product.ProductManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.po.ProductBelowLineQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
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
/*     */ public class ProductBelowLineAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  62 */     ProductBelowLineQueryForm queryForm = (ProductBelowLineQueryForm)form;
/*  63 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  64 */       queryForm.setOrder(ProductQueryOrder.ID.getName());
/*  65 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/*  68 */     ProductBelowLineManager fm = ServiceLocator.getProductBelowLineManager(request);
/*  69 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  71 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  73 */     String exportType = queryForm.getExportType();
/*  74 */     if (StringUtils.isNotEmpty(exportType)) {
/*  75 */       List data = fm.getProductBelowLineList(conditions, 0, -1, ProductBelowLineQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  77 */       int index = SessionTempFile.createNewTempFile(request);
/*  78 */       String fileName = "product";
/*  79 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  82 */               MessageResources messages = ProductBelowLineAction.this.getResources(request);
/*  83 */               row.add(messages.getMessage(ProductBelowLineAction.this.getLocale(request), "Product.id"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  87 */               row.add(BeanHelper.getBeanPropertyValue(data, "id"));
/*     */             }
/*     */           });
/*     */       
/*  91 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*  93 */     if (queryForm.isFirstInit()) {
/*  94 */       queryForm.init(fm.getProductBelowLineListCount(conditions));
/*     */     } else {
/*  96 */       queryForm.init();
/*     */     } 
/*     */     
/*  99 */     List result = fm.getProductBelowLineList(conditions, 
/* 100 */         queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), 
/* 101 */         ProductBelowLineQueryOrder.ID, queryForm.isDescend());
/*     */     
/* 103 */     request.setAttribute("X_RESULTLIST", result);
/* 104 */     request.setAttribute("x_selType", Integer.valueOf(4));
/* 105 */     putEnumListToRequest(request);
/* 106 */     return mapping.findForward("page");
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
/*     */   public ActionForward newProductOffline(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 120 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/*     */     
/* 122 */     request.setAttribute("x_listMap", manager.getProduceLineLocationAmunt());
/* 123 */     return mapping.findForward("page");
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
/*     */   public ActionForward insertProductOffline(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 138 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 139 */     String part = request.getParameter("part");
/* 140 */     String tool = request.getParameter("tool");
/* 141 */     String shcode = request.getParameter("shcodeId");
/*     */     
/* 143 */     manager.scanningProductPackingBySystem(part, tool, shcode, getCurrentUser(request).getId().toString());
/* 144 */     return mapping.findForward("success");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(ProductBelowLineQueryForm queryForm) {
/* 148 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 149 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 150 */     if (id != null && !id.equals("")) {
/* 151 */       conditions.put(ProductQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/* 154 */     return conditions;
/*     */   }
/*     */   
/*     */   private Product getProduct(HttpServletRequest request) throws Exception {
/* 158 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 159 */     ProductManager ProductManager = ServiceLocator.getProductManager(request);
/* 160 */     Product Product = ProductManager.getProduct(id);
/* 161 */     if (Product == null)
/* 162 */       throw new ActionException("Product.notFound", id); 
/* 163 */     return Product;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 167 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/product/ProductBelowLineAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */