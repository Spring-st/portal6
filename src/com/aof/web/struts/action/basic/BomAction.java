/*     */ package com.aof.web.struts.action.basic;
/*     */ 
/*     */ import com.aof.model.basic.query.WmsPartQueryCondition;
/*     */ import com.aof.model.basic.query.WmsPartQueryOrder;
/*     */ import com.aof.model.comprehensive.query.BomQueryOrder;
/*     */ import com.aof.service.comprehensive.BomManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.BomQueryForm;
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
/*     */ public class BomAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  55 */     BomQueryForm queryForm = (BomQueryForm)form;
/*  56 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/*  57 */       queryForm.setOrder(WmsPartQueryOrder.ID.getName());
/*  58 */       queryForm.setDescend(false);
/*     */     } 
/*  60 */     BomManager fm = ServiceLocator.getBomManager(request);
/*     */     
/*  62 */     Map conditions = constructQueryMap(queryForm);
/*  63 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/*  65 */     String exportType = queryForm.getExportType();
/*  66 */     if (StringUtils.isNotEmpty(exportType)) {
/*  67 */       List data = fm.getBomList(conditions, 0, -1, BomQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/*  69 */       int index = SessionTempFile.createNewTempFile(request);
/*  70 */       String fileName = "bom";
/*  71 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/*  74 */               MessageResources messages = BomAction.this.getResources(request);
/*  75 */               row.add(messages.getMessage(BomAction.this.getLocale(request), "bom.assembly.no"));
/*  76 */               row.add(messages.getMessage(BomAction.this.getLocale(request), "bom.assembly.describe"));
/*  77 */               row.add(messages.getMessage(BomAction.this.getLocale(request), "bom.part.id"));
/*  78 */               row.add(messages.getMessage(BomAction.this.getLocale(request), "bom.part.describe"));
/*  79 */               row.add(messages.getMessage(BomAction.this.getLocale(request), "bom.quantity"));
/*  80 */               row.add(messages.getMessage(BomAction.this.getLocale(request), "bom.date"));
/*     */             }
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/*  84 */               row.add(BeanHelper.getBeanPropertyValue(data, "product_no.id"));
/*  85 */               row.add(BeanHelper.getBeanPropertyValue(data, "father_part.describe1"));
/*  86 */               row.add(BeanHelper.getBeanPropertyValue(data, "child_part.id"));
/*  87 */               row.add(BeanHelper.getBeanPropertyValue(data, "child_part.describe1"));
/*  88 */               row.add(BeanHelper.getBeanPropertyValue(data, "unit_qty"));
/*  89 */               row.add(BeanHelper.getBeanPropertyValue(data, "date"));
/*     */             }
/*     */           });
/*  92 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*  94 */     if (queryForm.isFirstInit()) {
/*  95 */       queryForm.init(fm.getBomListCount(conditions));
/*     */     } else {
/*  97 */       queryForm.init();
/*     */     } 
/*  99 */     List result = fm.getBomList(conditions, queryForm.getPageNoAsInt(), 
/* 100 */         queryForm.getPageSizeAsInt(), BomQueryOrder.ID, 
/* 101 */         queryForm.isDescend());
/*     */     
/* 103 */     request.setAttribute("X_RESULTLIST", result);
/* 104 */     request.setAttribute("x_selType", Integer.valueOf(91));
/* 105 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 112 */     BomQueryForm queryForm = (BomQueryForm)form;
/* 113 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 114 */       queryForm.setOrder(WmsPartQueryOrder.ID.getName());
/* 115 */       queryForm.setDescend(false);
/*     */     } 
/* 117 */     BomManager fm = ServiceLocator.getBomManager(request);
/*     */     
/* 119 */     Map conditions = constructQueryMap(queryForm);
/* 120 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/* 121 */     if (queryForm.isFirstInit()) {
/* 122 */       queryForm.init(fm.getBomListCount(conditions));
/*     */     } else {
/* 124 */       queryForm.init();
/*     */     } 
/* 126 */     List result = fm.getBomList(conditions, queryForm.getPageNoAsInt(), 
/* 127 */         queryForm.getPageSizeAsInt(), BomQueryOrder.ID, 
/* 128 */         queryForm.isDescend());
/*     */     
/* 130 */     request.setAttribute("X_RESULTLIST", result);
/* 131 */     request.setAttribute("x_selType", Integer.valueOf(91));
/* 132 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(BomQueryForm queryForm) {
/* 136 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 137 */     String id = queryForm.getId();
/* 138 */     Date date = queryForm.getDate();
/* 139 */     if (id != null && !id.equals("")) {
/* 140 */       conditions.put(WmsPartQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/* 143 */     return conditions;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/BomAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */