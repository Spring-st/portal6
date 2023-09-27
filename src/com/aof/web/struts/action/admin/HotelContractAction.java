/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.Hotel;
/*     */ import com.aof.model.admin.HotelContract;
/*     */ import com.aof.model.admin.query.HotelContractQueryCondition;
/*     */ import com.aof.model.admin.query.HotelContractQueryOrder;
/*     */ import com.aof.model.metadata.EnabledDisabled;
/*     */ import com.aof.model.metadata.HotelPromoteStatus;
/*     */ import com.aof.service.admin.HotelContractManager;
/*     */ import com.aof.service.admin.HotelManager;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.HotelContractQueryForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.servlet.DownloadUploadHelper;
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.BeanForm;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.struts.action.ActionForm;
/*     */ import org.apache.struts.action.ActionForward;
/*     */ import org.apache.struts.action.ActionMapping;
/*     */ import org.apache.struts.upload.FormFile;
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
/*     */ public class HotelContractAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  57 */     HotelContractQueryForm queryForm = (HotelContractQueryForm)form;
/*  58 */     getHotelFromRequest(request);
/*     */     
/*  60 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  62 */     HotelContractManager fm = ServiceLocator.getHotelContractManager(request);
/*  63 */     List result = fm.getHotelContractList(conditions, 0, -1, HotelContractQueryOrder.ID, false);
/*  64 */     request.setAttribute("X_RESULTLIST", result);
/*  65 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Map constructQueryMap(HotelContractQueryForm queryForm) {
/*  69 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/*  71 */     Integer id = ActionUtils.parseInt(queryForm.getId());
/*  72 */     if (id != null) {
/*  73 */       conditions.put(HotelContractQueryCondition.ID_EQ, id);
/*     */     }
/*     */     
/*  76 */     Integer hotel_id = ActionUtils.parseInt(queryForm.getHotel_id());
/*  77 */     if (hotel_id != null) {
/*  78 */       conditions.put(HotelContractQueryCondition.HOTEL_ID_EQ, hotel_id);
/*     */     }
/*     */     
/*  81 */     String fileName = queryForm.getFileName();
/*  82 */     if (fileName != null && fileName.trim().length() != 0) {
/*  83 */       conditions.put(HotelContractQueryCondition.FILENAME_LIKE, fileName);
/*     */     }
/*     */     
/*  86 */     String description = queryForm.getDescription();
/*  87 */     if (description != null && description.trim().length() != 0) {
/*  88 */       conditions.put(HotelContractQueryCondition.DESCRIPTION_LIKE, description);
/*     */     }
/*     */     
/*  91 */     String uploadDate = queryForm.getUploadDate();
/*  92 */     if (uploadDate != null && uploadDate.trim().length() != 0) {
/*  93 */       conditions.put(HotelContractQueryCondition.UPLOADDATE_EQ, uploadDate);
/*     */     }
/*  95 */     return conditions;
/*     */   }
/*     */   
/*     */   private void putEnumListToRequest(HttpServletRequest request) {
/*  99 */     request.setAttribute("X_ENABLEDDISABLEDLIST", PersistentEnum.getEnumList(EnabledDisabled.class));
/*     */   }
/*     */   
/*     */   private HotelContract getHotelContractFromRequest(HttpServletRequest request) throws Exception {
/* 103 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 104 */     HotelContractManager hotelContractManager = ServiceLocator.getHotelContractManager(request);
/* 105 */     HotelContract hotelContract = hotelContractManager.getHotelContract(id);
/* 106 */     if (hotelContract == null)
/* 107 */       throw new ActionException("hotelContract.notFound", id); 
/* 108 */     return hotelContract;
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
/*     */   public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 121 */     HotelContract hotelContract = getHotelContractFromRequest(request);
/* 122 */     Hotel hotel = hotelContract.getHotel();
/*     */     
/* 124 */     checkHotel(hotel, request);
/*     */     
/* 126 */     request.setAttribute("x_hotelContract", hotelContract);
/*     */     
/* 128 */     if (!isBack(request)) {
/* 129 */       BeanForm hotelContractForm = (BeanForm)getForm("/updateHotelContract", request);
/* 130 */       hotelContractForm.populate(hotelContract, "to_form");
/*     */     } 
/* 132 */     putEnumListToRequest(request);
/* 133 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private void checkHotel(Hotel hotel, HttpServletRequest request) throws Exception {
/* 137 */     if (isSite(request)) {
/* 138 */       if (hotel.getPromoteStatus().equals(HotelPromoteStatus.GLOBAL)) {
/* 139 */         throw new ActionException("hotel.error.siteEditGlobal");
/*     */       }
/* 141 */       checkSite(hotel.getSite(), request);
/*     */     } 
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
/*     */   public ActionForward newObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 156 */     Hotel hotel = getHotelFromRequest(request);
/*     */     
/* 158 */     checkHotel(hotel, request);
/*     */     
/* 160 */     if (!isBack(request)) {
/* 161 */       HotelContract hotelContract = new HotelContract();
/* 162 */       hotelContract.setHotel(hotel);
/* 163 */       BeanForm hotelContractForm = (BeanForm)getForm("/insertHotelContract", request);
/* 164 */       hotelContractForm.populate(hotelContract, "to_form");
/*     */     } 
/* 166 */     putEnumListToRequest(request);
/* 167 */     return mapping.findForward("page");
/*     */   }
/*     */   
/*     */   private Hotel getHotelFromRequest(HttpServletRequest request) throws Exception {
/* 171 */     Integer id = ActionUtils.parseInt(request.getParameter("hotel_id"));
/* 172 */     HotelManager hotelManager = ServiceLocator.getHotelManager(request);
/* 173 */     Hotel hotel = hotelManager.getHotel(id);
/* 174 */     if (hotel == null)
/* 175 */       throw new ActionException("hotel.notFound", id); 
/* 176 */     return hotel;
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 190 */     Hotel hotel = getHotelFromRequest(request);
/* 191 */     checkHotel(hotel, request);
/*     */     
/* 193 */     BeanForm hotelContractForm = (BeanForm)form;
/* 194 */     HotelContract hotelContract = new HotelContract();
/* 195 */     hotelContractForm.populate(hotelContract, "to_bean");
/*     */     
/* 197 */     HotelContractManager hotelContractManager = ServiceLocator.getHotelContractManager(request);
/* 198 */     request.setAttribute("X_OBJECT", hotelContractManager.updateHotelContract(hotelContract));
/* 199 */     request.setAttribute("X_ROWPAGE", "hotelContract/row.jsp");
/*     */     
/* 201 */     return mapping.findForward("success");
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
/*     */   public ActionForward download(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 214 */     HotelContract hotelContract = getHotelContractFromRequest(request);
/* 215 */     Hotel hotel = hotelContract.getHotel();
/* 216 */     checkHotel(hotel, request);
/*     */     
/* 218 */     InputStream in = ServiceLocator.getHotelContractManager(request).getHotelContractContent(hotelContract.getId());
/* 219 */     if (in != null) {
/*     */       try {
/* 221 */         if (hotelContract.getFileSize() == 0) {
/* 222 */           throw new ActionException("hotelContract.error.fileSize.zero");
/*     */         }
/* 224 */         DownloadUploadHelper.download(
/* 225 */             in, 
/* 226 */             hotelContract.getFileName(), 
/* 227 */             DownloadUploadHelper.getMime(hotelContract.getFileName()), hotelContract.getFileSize(), 
/* 228 */             request, 
/* 229 */             response, 
/* 230 */             true);
/*     */       } finally {
/*     */         
/* 233 */         in.close();
/*     */       } 
/*     */     }
/* 236 */     return null;
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
/*     */   public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 249 */     Hotel hotel = getHotelFromRequest(request);
/* 250 */     checkHotel(hotel, request);
/*     */     
/* 252 */     BeanForm hotelContractForm = (BeanForm)form;
/* 253 */     HotelContract hotelContract = new HotelContract();
/* 254 */     hotelContractForm.populate(hotelContract, "to_bean");
/* 255 */     hotelContract.setHotel(hotel);
/*     */     
/* 257 */     FormFile file = (FormFile)hotelContractForm.get("fileContent");
/* 258 */     hotelContract.setFileName(file.getFileName());
/*     */     
/* 260 */     HotelContractManager hotelContractManager = ServiceLocator.getHotelContractManager(request);
/* 261 */     HotelContract newHc = null;
/* 262 */     if (file.getFileSize() > 0) {
/* 263 */       hotelContract.setFileSize(file.getFileSize());
/* 264 */       newHc = hotelContractManager.insertHotelContract(hotelContract, file.getInputStream());
/*     */     } else {
/* 266 */       throw new ActionException("hotelContract.error.fileSize.zero");
/*     */     } 
/* 268 */     request.setAttribute("X_OBJECT", newHc);
/* 269 */     request.setAttribute("X_ROWPAGE", "hotelContract/row.jsp");
/*     */     
/* 271 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/HotelContractAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */