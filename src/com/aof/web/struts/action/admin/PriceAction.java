/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.Price;
/*     */ import com.aof.model.admin.query.PriceQueryCondition;
/*     */ import com.aof.model.admin.query.PriceQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.service.admin.PriceManager;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.PriceQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
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
/*     */ public class PriceAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  51 */     PriceManager fm = ServiceLocator.getPriceManager(request);
/*     */     
/*  53 */     PriceQueryForm queryForm = (PriceQueryForm)form;
/*     */     
/*  55 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  57 */     int pageNo = ActionUtils.parseInt(queryForm.getPageNo()).intValue();
/*  58 */     int pageSize = ActionUtils.parseInt(queryForm.getPageSize()).intValue();
/*  59 */     if (pageSize == 0) {
/*  60 */       pageSize = 15;
/*  61 */       queryForm.setPageSize(String.valueOf(pageSize));
/*     */     } 
/*  63 */     if (ActionUtils.parseInt(queryForm.getCount()) == null) {
/*  64 */       int count = fm.getPriceListCount(conditions);
/*  65 */       queryForm.setCount(String.valueOf(count));
/*     */     } 
/*  67 */     int pageCount = queryForm.getPageCount();
/*  68 */     if (pageNo >= pageCount) {
/*  69 */       pageNo = pageCount - 1;
/*  70 */       queryForm.setPageNo(String.valueOf(pageNo));
/*     */     } 
/*     */     
/*  73 */     List result = fm.getPriceList(
/*  74 */         conditions, pageNo, pageSize, PriceQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  75 */     request.setAttribute("X_RESULTLIST", result);
/*  76 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(PriceQueryForm queryForm) {
/*  80 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/*  82 */     Integer id = 
/*  83 */       ActionUtils.parseInt(queryForm.getId());
/*  84 */     if (id != null)
/*     */     {
/*  86 */       conditions.put(PriceQueryCondition.ID_EQ, 
/*  87 */           id);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     Integer hotel_id = 
/*  96 */       ActionUtils.parseInt(queryForm.getHotel_id());
/*  97 */     if (hotel_id != null)
/*     */     {
/*  99 */       conditions.put(PriceQueryCondition.HOTEL_ID_EQ, 
/* 100 */           hotel_id);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     String room = 
/* 107 */       queryForm.getRoom();
/* 108 */     if (room != null && room.trim().length() != 0)
/*     */     {
/* 110 */       conditions.put(PriceQueryCondition.ROOM_LIKE, 
/* 111 */           room);
/*     */     }
/* 113 */     String price = 
/* 114 */       queryForm.getPrice();
/* 115 */     if (price != null && price.trim().length() != 0)
/*     */     {
/* 117 */       conditions.put(PriceQueryCondition.PRICE_EQ, 
/* 118 */           price);
/*     */     }
/* 120 */     Integer discount = 
/* 121 */       ActionUtils.parseInt(queryForm.getDiscount());
/* 122 */     if (discount != null)
/*     */     {
/* 124 */       conditions.put(PriceQueryCondition.DISCOUNT_EQ, 
/* 125 */           discount);
/*     */     }
/* 127 */     String description = 
/* 128 */       queryForm.getDescription();
/* 129 */     if (description != null && description.trim().length() != 0)
/*     */     {
/* 131 */       conditions.put(PriceQueryCondition.DESCRIPTION_LIKE, 
/* 132 */           description);
/*     */     }
/* 134 */     String enabled = 
/* 135 */       queryForm.getEnabled();
/* 136 */     if (enabled != null && enabled.trim().length() != 0)
/*     */     {
/* 138 */       conditions.put(PriceQueryCondition.ENABLED_EQ, 
/* 139 */           enabled);
/*     */     }
/* 141 */     return conditions;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/* 145 */     request.setAttribute("X_ENABLEDDISABLEDLIST", 
/* 146 */         PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Price getPriceFromRequest(HttpServletRequest request) throws Exception {
/* 152 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 153 */     PriceManager priceManager = ServiceLocator.getPriceManager(request);
/* 154 */     Price price = priceManager.getPrice(id);
/* 155 */     if (price == null)
/* 156 */       throw new ActionException("price.notFound", id); 
/* 157 */     return price;
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
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 171 */     Price price = getPriceFromRequest(request);
/*     */     
/* 173 */     request.setAttribute("x_price", price);
/* 174 */     if (!isBack(request)) {
/* 175 */       BeanForm priceForm = (BeanForm)getForm("/updatePrice", request);
/* 176 */       priceForm.populate(price, "to_form");
/*     */     } 
/* 178 */     putEnumListToRequest(request);
/* 179 */     return mapping.findForward("page");
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
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 194 */     if (!isBack(request)) {
/* 195 */       Price price = new Price();
/*     */       
/* 197 */       BeanForm priceForm = (BeanForm)getForm("/insertPrice", request);
/* 198 */       priceForm.populate(price, "to_form");
/*     */     } 
/* 200 */     putEnumListToRequest(request);
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 240 */     BeanForm priceForm = (BeanForm)form;
/* 241 */     Price price = new Price();
/* 242 */     priceForm.populate(price, "to_bean");
/*     */ 
/*     */     
/* 245 */     PriceManager priceManager = ServiceLocator.getPriceManager(request);
/* 246 */     request.setAttribute("X_OBJECT", priceManager.updatePrice(price));
/* 247 */     request.setAttribute("X_ROWPAGE", "price/row.jsp");
/*     */     
/* 249 */     return mapping.findForward("success");
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
/* 265 */     BeanForm priceForm = (BeanForm)form;
/* 266 */     Price price = new Price();
/* 267 */     priceForm.populate(price, "to_bean");
/*     */ 
/*     */     
/* 270 */     PriceManager priceManager = ServiceLocator.getPriceManager(request);
/* 271 */     request.setAttribute("X_OBJECT", priceManager.insertPrice(price));
/* 272 */     request.setAttribute("X_ROWPAGE", "price/row.jsp");
/*     */     
/* 274 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/PriceAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */