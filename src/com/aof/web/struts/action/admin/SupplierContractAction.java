/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.admin.SupplierContract;
/*     */ import com.aof.model.admin.query.SupplierContractQueryCondition;
/*     */ import com.aof.model.admin.query.SupplierContractQueryOrder;
/*     */ import com.aof.model.metadata.SupplierPromoteStatus;
/*     */ import com.aof.service.admin.SupplierContractManager;
/*     */ import com.aof.service.admin.SupplierManager;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.SupplierContractQueryForm;
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
/*     */ public class SupplierContractAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  54 */     SupplierContractQueryForm queryForm = (SupplierContractQueryForm)form;
/*     */     
/*  56 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  58 */     SupplierContractManager scm = ServiceLocator.getSupplierContractManager(request);
/*  59 */     List result = scm.getSupplierContractList(conditions, 0, -1, SupplierContractQueryOrder.ID, false);
/*  60 */     request.setAttribute("X_RESULTLIST", result);
/*  61 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map constructQueryMap(SupplierContractQueryForm queryForm) {
/*  71 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*  72 */     Integer id = 
/*  73 */       ActionUtils.parseInt(queryForm.getId());
/*  74 */     if (id != null)
/*     */     {
/*  76 */       conditions.put(SupplierContractQueryCondition.ID_EQ, 
/*  77 */           id);
/*     */     }
/*     */     
/*  80 */     String Supplier_id = queryForm.getSupplier_id();
/*  81 */     if (Supplier_id != null && Supplier_id.trim().length() != 0)
/*     */     {
/*  83 */       conditions.put(SupplierContractQueryCondition.SUPPLIER_ID_EQ, Supplier_id);
/*     */     }
/*     */     
/*  86 */     return conditions;
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkSupplier(Supplier supplier, HttpServletRequest request) throws Exception {
/*  91 */     if (isSite(request)) {
/*  92 */       if (supplier.getPromoteStatus().equals(SupplierPromoteStatus.GLOBAL)) {
/*  93 */         throw new ActionException("supplier.error.siteEditGlobal");
/*     */       }
/*  95 */       checkSite(supplier.getSite(), request);
/*     */     } 
/*     */     
/*  98 */     if (isGlobal(request) && 
/*  99 */       !supplier.getPromoteStatus().equals(SupplierPromoteStatus.GLOBAL)) {
/* 100 */       throw new ActionException("supplier.error.globalEditSite");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private SupplierContract getSupplierContractFromRequest(HttpServletRequest request) throws Exception {
/* 106 */     Integer id = ActionUtils.parseInt(request.getParameter("id"));
/* 107 */     SupplierContractManager supplierContractManager = ServiceLocator.getSupplierContractManager(request);
/* 108 */     SupplierContract supplierContract = supplierContractManager.getSupplierContract(id);
/* 109 */     if (supplierContract == null)
/* 110 */       throw new ActionException("supplierContract.notFound", id); 
/* 111 */     return supplierContract;
/*     */   }
/*     */   
/*     */   private Supplier getSupplierFromRequest(HttpServletRequest request) throws Exception {
/* 115 */     Integer id = new Integer(request.getParameter("supplier_id"));
/* 116 */     SupplierManager supplierManager = ServiceLocator.getSupplierManager(request);
/* 117 */     Supplier supplier = supplierManager.getSupplier(id);
/* 118 */     if (supplier == null)
/* 119 */       throw new ActionException("supplier.notFound", id); 
/* 120 */     return supplier;
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
/* 134 */     SupplierContract supplierContract = getSupplierContractFromRequest(request);
/* 135 */     Supplier supplier = supplierContract.getSupplier();
/* 136 */     checkSupplier(supplier, request);
/* 137 */     request.setAttribute("x_supplierContract", supplierContract);
/*     */     
/* 139 */     if (!isBack(request)) {
/* 140 */       BeanForm supplierContractForm = (BeanForm)getForm("/updateSupplierContract", request);
/* 141 */       supplierContractForm.populate(supplierContract, "to_form");
/*     */     } 
/*     */     
/* 144 */     return mapping.findForward("page");
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
/* 158 */     Supplier supplier = getSupplierFromRequest(request);
/*     */     
/* 160 */     checkSupplier(supplier, request);
/*     */     
/* 162 */     if (!isBack(request)) {
/* 163 */       SupplierContract supplierContract = new SupplierContract();
/* 164 */       supplierContract.setSupplier(supplier);
/* 165 */       BeanForm supplierContractForm = (BeanForm)getForm("/insertSupplierContract", request);
/* 166 */       supplierContractForm.populate(supplierContract, "to_form");
/*     */     } 
/*     */     
/* 169 */     return mapping.findForward("page");
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
/* 183 */     Supplier supplier = getSupplierFromRequest(request);
/* 184 */     checkSupplier(supplier, request);
/*     */     
/* 186 */     BeanForm supplierContractForm = (BeanForm)form;
/* 187 */     SupplierContract supplierContract = new SupplierContract();
/* 188 */     supplierContractForm.populate(supplierContract, "to_bean");
/*     */     
/* 190 */     SupplierContractManager supplierContractManager = ServiceLocator.getSupplierContractManager(request);
/* 191 */     request.setAttribute("X_OBJECT", supplierContractManager.updateSupplierContract(supplierContract));
/* 192 */     request.setAttribute("X_ROWPAGE", "supplierContract/row.jsp");
/*     */     
/* 194 */     return mapping.findForward("success");
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
/*     */   public ActionForward download(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 208 */     SupplierContract supplierContract = getSupplierContractFromRequest(request);
/*     */ 
/*     */ 
/*     */     
/* 212 */     InputStream in = ServiceLocator.getSupplierContractManager(request).getSupplierContractContent(supplierContract.getId());
/* 213 */     if (in != null) {
/*     */       try {
/* 215 */         if (supplierContract.getFileSize() == 0) {
/* 216 */           throw new ActionException("supplierContract.error.fileSize.zero");
/*     */         }
/* 218 */         DownloadUploadHelper.download(
/* 219 */             in, 
/* 220 */             supplierContract.getFileName(), 
/* 221 */             DownloadUploadHelper.getMime(supplierContract.getFileName()), supplierContract.getFileSize(), 
/* 222 */             request, 
/* 223 */             response, 
/* 224 */             true);
/*     */       } finally {
/*     */         
/* 227 */         in.close();
/*     */       } 
/*     */     }
/* 230 */     return null;
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
/* 243 */     Supplier supplier = getSupplierFromRequest(request);
/* 244 */     checkSupplier(supplier, request);
/*     */     
/* 246 */     BeanForm supplierContractForm = (BeanForm)form;
/* 247 */     SupplierContract supplierContract = new SupplierContract();
/* 248 */     supplierContractForm.populateToBean(supplierContract, request);
/* 249 */     supplierContract.setSupplier(supplier);
/*     */     
/* 251 */     FormFile file = (FormFile)supplierContractForm.get("fileContent");
/* 252 */     supplierContract.setFileName(file.getFileName());
/*     */     
/* 254 */     SupplierContractManager supplierContractManager = ServiceLocator.getSupplierContractManager(request);
/* 255 */     SupplierContract newHc = null;
/* 256 */     if (file.getFileSize() > 0) {
/* 257 */       supplierContract.setFileSize(file.getFileSize());
/* 258 */       newHc = supplierContractManager.insertSupplierContract(supplierContract, file.getInputStream());
/*     */     } else {
/* 260 */       throw new ActionException("supplierContract.error.fileSize.zero");
/*     */     } 
/* 262 */     request.setAttribute("X_OBJECT", newHc);
/* 263 */     request.setAttribute("X_ROWPAGE", "supplierContract/row.jsp");
/*     */     
/* 265 */     return mapping.findForward("success");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/SupplierContractAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */