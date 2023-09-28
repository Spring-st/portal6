/*     */ package com.aof.web.struts.action.basic;
/*     */ 
/*     */ import com.aof.model.basic.BasicPartPrice;
/*     */ import com.aof.model.basic.query.BasicPartPriceQueryOrder;
/*     */ import com.aof.service.basic.BasicPartPriceManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.BasicPartPriceQueryForm;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.struts.form.beanloader.BeanLoader;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.Date;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicPartPriceAction
/*     */   extends BaseAction2
/*     */ {
/*     */   private Map getConditions(BasicPartPriceQueryForm formBean) {
/*  44 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  45 */     String str = "";
/*     */     
/*  47 */     return conditions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  53 */     BasicPartPriceManager manager = ServiceLocator.getBasicPartPriceManager(request);
/*  54 */     BasicPartPriceQueryForm formBean = (BasicPartPriceQueryForm)form;
/*  55 */     Map conditions = getConditions(formBean);
/*  56 */     getConditionAndOrder((BaseSessionQueryForm)formBean, conditions, request);
/*  57 */     String exportType = formBean.getExportType();
/*  58 */     if (exportType != null && exportType.length() > 0) {
/*  59 */       List datas = manager.getList(conditions, 0, -1, 
/*  60 */           BasicPartPriceQueryOrder.getEnum(formBean.getOrder()), formBean.isDescend());
/*  61 */       int index = SessionTempFile.createNewTempFile(request);
/*  62 */       String fileName = "BasicPartPrice";
/*  63 */       String suffix = ExportUtil.export(exportType, datas, request, 
/*  64 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/*  65 */           new Exportable()
/*     */           {
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception
/*     */             {
/*  69 */               row.add(BeanUtils.getProperty(data, "customer"));
/*  70 */               row.add(BeanUtils.getProperty(data, "sotaxc"));
/*  71 */               row.add(BeanUtils.getProperty(data, "partId"));
/*  72 */               row.add(BeanUtils.getProperty(data, "solist"));
/*  73 */               row.add(BeanUtils.getProperty(data, "pcDesc"));
/*  74 */               row.add(BeanUtils.getProperty(data, "curr"));
/*  75 */               row.add(BeanUtils.getProperty(data, "pcUm"));
/*  76 */               row.add(BeanUtils.getProperty(data, "startDate"));
/*  77 */               row.add(BeanUtils.getProperty(data, "expireDate"));
/*  78 */               row.add(BeanUtils.getProperty(data, "amtType"));
/*  79 */               row.add(BeanUtils.getProperty(data, "amt"));
/*  80 */               row.add(BeanUtils.getProperty(data, "rmks"));
/*     */             }
/*     */             
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  84 */               MessageResources message = BasicPartPriceAction.this.getResources(request);
/*  85 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.customer"));
/*  86 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.sotaxc"));
/*  87 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.partId"));
/*  88 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.solist"));
/*  89 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.pcDesc"));
/*  90 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.curr"));
/*  91 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.pcUm"));
/*  92 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.startDate"));
/*  93 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.expireDate"));
/*  94 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.amtType"));
/*  95 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.amt"));
/*  96 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.rmks"));
/*     */             }
/*     */           });
/*  99 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/* 101 */     if (formBean.isFirstInit()) {
/* 102 */       formBean.init(manager.getListCount(conditions).intValue());
/*     */     } else {
/* 104 */       formBean.init();
/*     */     } 
/* 106 */     int pageNum = formBean.getPageNoAsInt();
/* 107 */     int pageSize = formBean.getPageSizeAsInt();
/* 108 */     List entityList = manager.getList(conditions, pageNum, pageSize, 
/* 109 */         BasicPartPriceQueryOrder.getEnum(formBean.getOrder()), 
/* 110 */         formBean.isDescend());
/* 111 */     request.setAttribute("x_selType", Integer.valueOf(145));
/* 112 */     request.setAttribute("X_RESULTLIST", entityList);
/* 113 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward listMaintenance(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 118 */     BasicPartPriceManager manager = ServiceLocator.getBasicPartPriceManager(request);
/* 119 */     BasicPartPriceQueryForm formBean = (BasicPartPriceQueryForm)form;
/* 120 */     Map conditions = getConditions(formBean);
/* 121 */     getConditionAndOrder((BaseSessionQueryForm)formBean, conditions, request);
/* 122 */     String exportType = formBean.getExportType();
/* 123 */     if (exportType != null && exportType.length() > 0) {
/* 124 */       List datas = manager.getList(conditions, 0, -1, 
/* 125 */           BasicPartPriceQueryOrder.getEnum(formBean.getOrder()), formBean.isDescend());
/* 126 */       int index = SessionTempFile.createNewTempFile(request);
/* 127 */       String fileName = "BasicPartPrice";
/* 128 */       String suffix = ExportUtil.export(exportType, datas, request, 
/* 129 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/* 130 */           new Exportable()
/*     */           {
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception
/*     */             {
/* 134 */               row.add(BeanUtils.getProperty(data, "customer"));
/* 135 */               row.add(BeanUtils.getProperty(data, "sotaxc"));
/* 136 */               row.add(BeanUtils.getProperty(data, "partId"));
/* 137 */               row.add(BeanUtils.getProperty(data, "solist"));
/* 138 */               row.add(BeanUtils.getProperty(data, "pcDesc"));
/* 139 */               row.add(BeanUtils.getProperty(data, "curr"));
/* 140 */               row.add(BeanUtils.getProperty(data, "pcUm"));
/* 141 */               row.add(BeanUtils.getProperty(data, "startDate"));
/* 142 */               row.add(BeanUtils.getProperty(data, "expireDate"));
/* 143 */               row.add(BeanUtils.getProperty(data, "amtType"));
/* 144 */               row.add(BeanUtils.getProperty(data, "amt"));
/* 145 */               row.add(BeanUtils.getProperty(data, "rmks"));
/*     */             }
/*     */             
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 149 */               MessageResources message = BasicPartPriceAction.this.getResources(request);
/* 150 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.customer"));
/* 151 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.sotaxc"));
/* 152 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.partId"));
/* 153 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.solist"));
/* 154 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.pcDesc"));
/* 155 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.curr"));
/* 156 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.pcUm"));
/* 157 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.startDate"));
/* 158 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.expireDate"));
/* 159 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.amtType"));
/* 160 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.amt"));
/* 161 */               row.add(message.getMessage(BasicPartPriceAction.this.getLocale(request), "basicpartprice.rmks"));
/*     */             }
/*     */           });
/* 164 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/* 166 */     if (formBean.isFirstInit()) {
/* 167 */       formBean.init(manager.getListCount(conditions).intValue());
/*     */     } else {
/* 169 */       formBean.init();
/*     */     } 
/* 171 */     int pageNum = formBean.getPageNoAsInt();
/* 172 */     int pageSize = formBean.getPageSizeAsInt();
/* 173 */     List entityList = manager.getList(conditions, pageNum, pageSize, 
/* 174 */         BasicPartPriceQueryOrder.getEnum(formBean.getOrder()), 
/* 175 */         formBean.isDescend());
/* 176 */     request.setAttribute("x_selType", Integer.valueOf(147));
/* 177 */     request.setAttribute("X_RESULTLIST", entityList);
/* 178 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 183 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 188 */     BasicPartPriceManager manager = ServiceLocator.getBasicPartPriceManager(request);
/* 189 */     BeanForm formBean = (BeanForm)form;
/* 190 */     BasicPartPrice entity = new BasicPartPrice();
/* 191 */     formBean.populateToBean(entity);
/* 192 */     entity.setCreateDate(new Date());
/* 193 */     entity.setCreateUser(getCurrentUser(request).getName());
/* 194 */     entity.setSite("YA01");
/* 195 */     entity.setDomain("YA01");
/* 196 */     entity.setMtType(getCurrentUser(request).getName());
/* 197 */     request.setAttribute("X_OBJECT", manager.save(entity));
/* 198 */     request.setAttribute("X_ROWPAGE", "basicPartPrice/row.jsp");
/* 199 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 204 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 205 */     BasicPartPriceManager manager = ServiceLocator.getBasicPartPriceManager(request);
/* 206 */     BasicPartPrice entity = manager.getBasicPartPrice(id);
/* 207 */     request.setAttribute("X_OBJECT", entity);
/* 208 */     if (!isBack(request)) {
/*     */       
/* 210 */       BeanForm wmsPartForm = (BeanForm)getForm("/updateBasicPartPriceMaintenanceAction", request);
/* 211 */       wmsPartForm.populate(entity, "to_form");
/*     */     } 
/* 213 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 218 */     BasicPartPriceManager manager = ServiceLocator.getBasicPartPriceManager(request);
/* 219 */     BeanForm formBean = (BeanForm)form;
/* 220 */     BasicPartPrice entity = new BasicPartPrice();
/* 221 */     formBean.setBeanLoader((BeanLoader)ServiceLocator.getBeanLoader(request));
/* 222 */     formBean.populateToBean(entity, request);
/* 223 */     entity.setUpdateDate(new Date());
/* 224 */     entity.setUpdateUser(getCurrentUser(request).getName());
/* 225 */     entity.setMtType(getCurrentUser(request).getName());
/* 226 */     request.setAttribute("X_OBJECT", manager.update(entity));
/* 227 */     request.setAttribute("X_ROWPAGE", "basicPartPrice/row.jsp");
/* 228 */     return mapping.findForward("success");
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 233 */     Integer id = Integer.valueOf(Integer.parseInt(request.getParameter("id")));
/* 234 */     BasicPartPriceManager manager = ServiceLocator.getBasicPartPriceManager(request);
/* 235 */     manager.delete(manager.getBasicPartPrice(id));
/* 236 */     return new ActionForward("listBasicPartPriceAction.do", true);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/basic/BasicPartPriceAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */