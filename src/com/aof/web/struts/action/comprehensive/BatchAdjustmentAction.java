/*     */ package com.aof.web.struts.action.comprehensive;
/*     */ 
/*     */ import com.aof.model.comprehensive.BoxAdjustment;
/*     */ import com.aof.model.comprehensive.BoxAdjustmentBox;
/*     */ import com.aof.model.comprehensive.query.BoxAdjustmentQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.PurchaseOrderStatus;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.model.po.Box;
/*     */ import com.aof.model.po.PurchaseOrder;
/*     */ import com.aof.model.po.query.PurchaseOrderQueryCondition;
/*     */ import com.aof.model.po.query.PurchaseOrderQueryOrder;
/*     */ import com.aof.service.comprehensive.BoxAdjustmentManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import com.aof.service.po.PurchaseOrderManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.basic.BoxAdjustmentQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.ArrayList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BatchAdjustmentAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 147 */     BoxAdjustmentQueryForm queryForm = (BoxAdjustmentQueryForm)form;
/* 148 */     if (StringUtils.isEmpty(queryForm.getOrder())) {
/* 149 */       queryForm.setOrder(PurchaseOrderQueryOrder.ID.getName());
/* 150 */       queryForm.setDescend(false);
/*     */     } 
/*     */     
/* 153 */     BoxAdjustmentManager fm = ServiceLocator.getBoxAdjustmentManager(request);
/* 154 */     Map conditions = constructQueryMap(queryForm);
/* 155 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*     */     
/* 157 */     String exportType = queryForm.getExportType();
/* 158 */     if (StringUtils.isNotEmpty(exportType)) {
/*     */       
/* 160 */       List data = fm.getBoxAdjustmentBoxList(conditions, 0, -1, BoxAdjustmentQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*     */       
/* 162 */       int index = SessionTempFile.createNewTempFile(request);
/* 163 */       String fileName = "purchaseOrder";
/* 164 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 167 */               MessageResources messages = BatchAdjustmentAction.this.getResources(request);
/* 168 */               row.add(messages.getMessage(BatchAdjustmentAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.id"));
/* 169 */               row.add(messages.getMessage(BatchAdjustmentAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.number"));
/* 170 */               row.add(messages.getMessage(BatchAdjustmentAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.row"));
/* 171 */               row.add(messages.getMessage(BatchAdjustmentAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.code"));
/* 172 */               row.add(messages.getMessage(BatchAdjustmentAction.this.getLocale(request), "DPI"));
/* 173 */               row.add(messages.getMessage(BatchAdjustmentAction.this.getLocale(request), "purchaseOrder.describe1"));
/* 174 */               row.add(messages.getMessage(BatchAdjustmentAction.this.getLocale(request), "purchaseOrder.describe2"));
/* 175 */               row.add(messages.getMessage(BatchAdjustmentAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.num"));
/* 176 */               row.add(messages.getMessage(BatchAdjustmentAction.this.getLocale(request), "purchaseOrderPutInStorageBoxList.location.code"));
/* 177 */               row.add(messages.getMessage(BatchAdjustmentAction.this.getLocale(request), "wmsStocking.type"));
/* 178 */               row.add(messages.getMessage(BatchAdjustmentAction.this.getLocale(request), "operation_user"));
/* 179 */               row.add(messages.getMessage(BatchAdjustmentAction.this.getLocale(request), "operation_date"));
/* 180 */               row.add(messages.getMessage(BatchAdjustmentAction.this.getLocale(request), "WmsUW.remark"));
/*     */             }
/*     */ 
/*     */             
/*     */             public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 185 */               row.add(BeanHelper.getBeanPropertyValue(data, "new_box_id.lot.id"));
/* 186 */               row.add(BeanHelper.getBeanPropertyValue(data, "new_box_id.psoItem.poipItem.poip_number.po_number"));
/* 187 */               row.add(BeanHelper.getBeanPropertyValue(data, "new_box_id.psoItem.poipItem.line"));
/* 188 */               row.add(BeanHelper.getBeanPropertyValue(data, "new_box_id.part.id"));
/* 189 */               row.add(BeanHelper.getBeanPropertyValue(data, "new_box_id.part.dpiNo"));
/* 190 */               row.add(BeanHelper.getBeanPropertyValue(data, "new_box_id.part.describe1"));
/* 191 */               row.add(BeanHelper.getBeanPropertyValue(data, "new_box_id.part.describe2"));
/* 192 */               row.add(BeanHelper.getBeanPropertyValue(data, "new_box_id.number"));
/* 193 */               row.add(BeanHelper.getBeanPropertyValue(data, "old_location.code"));
/* 194 */               if (BeanHelper.getBeanPropertyValue(data, "type").equals("1")) {
/* 195 */                 row.add("拆分");
/*     */               }
/* 197 */               if (BeanHelper.getBeanPropertyValue(data, "type").equals("2")) {
/* 198 */                 row.add("合并");
/*     */               }
/*     */               
/* 201 */               row.add(BeanHelper.getBeanPropertyValue(data, "operation.name"));
/* 202 */               row.add(BeanHelper.getBeanPropertyValue(data, "date"));
/* 203 */               row.add(BeanHelper.getBeanPropertyValue(data, "remark"));
/*     */             }
/*     */           });
/*     */       
/* 207 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/* 209 */     if (queryForm.isFirstInit()) {
/* 210 */       queryForm.init(fm.getBoxAdjustmentBoxListCount(conditions));
/*     */     } else {
/* 212 */       queryForm.init();
/*     */     } 
/* 214 */     List<BoxAdjustmentBox> result = fm.getBoxAdjustmentBoxList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), BoxAdjustmentQueryOrder.ID, queryForm.isDescend());
/* 215 */     request.setAttribute("X_RESULTLIST", result);
/* 216 */     request.setAttribute("x_selType", Integer.valueOf(97));
/* 217 */     putEnumListToRequest(request);
/* 218 */     return mapping.findForward("page");
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
/*     */   private Map constructQueryMap(BoxAdjustmentQueryForm queryForm) {
/* 245 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 246 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/* 247 */     if (id != null && !id.equals("")) {
/* 248 */       conditions.put(PurchaseOrderQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/* 251 */     return conditions;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 255 */     request.setAttribute("X_YESNOLIST", PersistentEnum.getEnumList(YesNo.class));
/* 256 */     request.setAttribute("X_SITELIST", getAndCheckGrantedSiteList(request));
/* 257 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/* 258 */     request.setAttribute("X_WmsPurchaseOrderStatusLIST", PersistentEnum.getEnumList(PurchaseOrderStatus.class));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 264 */     BeanForm purchaseOrderForm = (BeanForm)form;
/* 265 */     PurchaseOrder purchaseOrder = new PurchaseOrder();
/* 266 */     purchaseOrderForm.populate(purchaseOrder, "to_bean");
/*     */     
/* 268 */     PurchaseOrderManager purchaseOrderManager = ServiceLocator.getPurchaseOrderManager(request);
/*     */ 
/*     */ 
/*     */     
/* 272 */     return new ActionForward("editPurchaseOrder.do?id=" + purchaseOrder.getId(), true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 278 */     BoxAdjustmentManager manager = ServiceLocator.getBoxAdjustmentManager(request);
/* 279 */     String id = request.getParameter("id");
/* 280 */     BoxAdjustment adjustment = manager.getBoxAdjustment(Integer.valueOf(Integer.parseInt(id)));
/* 281 */     List<BoxAdjustmentBox> boxs = manager.getBoxAdjustmentBoxByMain(adjustment.getId());
/*     */     
/* 283 */     request.setAttribute("x_adjustment", adjustment);
/* 284 */     request.setAttribute("x_boxs", boxs);
/* 285 */     return mapping.findForward("page");
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
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 301 */     String type = request.getParameter("type");
/* 302 */     String arrays = request.getParameter("array");
/* 303 */     String[] str = arrays.split(",");
/* 304 */     BoxManager manager = ServiceLocator.getBoxManager(request);
/* 305 */     List<Box> list = new ArrayList<Box>(); byte b; int i; String[] arrayOfString1;
/* 306 */     for (i = (arrayOfString1 = str).length, b = 0; b < i; ) { String id = arrayOfString1[b];
/* 307 */       Box box = manager.getBox(Integer.valueOf(Integer.parseInt(id)));
/* 308 */       list.add(box);
/*     */       b++; }
/*     */     
/* 311 */     request.setAttribute("x_type", type);
/* 312 */     request.setAttribute("x_list", list);
/* 313 */     return mapping.findForward("page");
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 329 */     String str = request.getParameter("str");
/* 330 */     String type = request.getParameter("type");
/* 331 */     String[] lot = request.getParameterValues("lot");
/* 332 */     String newLot = ""; byte b; int i; String[] arrayOfString1;
/* 333 */     for (i = (arrayOfString1 = lot).length, b = 0; b < i; ) { String str1 = arrayOfString1[b];
/* 334 */       newLot = String.valueOf(newLot) + str1;
/* 335 */       newLot = String.valueOf(newLot) + ",";
/*     */       b++; }
/*     */     
/* 338 */     BoxAdjustmentManager manager = ServiceLocator.getBoxAdjustmentManager(request);
/* 339 */     manager.insertBoxAdjustmentBox(newLot, str, type, getCurrentUser(request).getId().toString());
/* 340 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/comprehensive/BatchAdjustmentAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */