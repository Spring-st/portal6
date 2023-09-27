/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.MetadataDetail;
/*     */ import com.aof.model.metadata.MetadataType;
/*     */ import com.aof.service.admin.MetadataManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.admin.MetadataForm;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MetadataAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  53 */     MetadataManager mm = ServiceLocator.getMetadataManager(request);
/*  54 */     BaseSessionQueryForm queryForm = (BaseSessionQueryForm)form;
/*     */ 
/*     */     
/*  57 */     String exportType = queryForm.getExportType();
/*  58 */     if (exportType != null && exportType.length() > 0) {
/*  59 */       List data = mm.getMetadataList(0, -1);
/*  60 */       int index = SessionTempFile.createNewTempFile(request);
/*  61 */       String fileName = "metadata";
/*  62 */       String suffix = ExportUtil.export(exportType, data, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */           {
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/*  65 */               MessageResources messages = MetadataAction.this.getResources(request);
/*  66 */               row.add(messages.getMessage(MetadataAction.this.getLocale(request), "metadata.code"));
/*  67 */               row.add(messages.getMessage(MetadataAction.this.getLocale(request), "metadata.description"));
/*     */             }
/*     */             
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception {
/*  71 */               row.add(BeanUtils.getProperty(data, "id"));
/*  72 */               row.add(BeanUtils.getProperty(data, "description"));
/*     */             }
/*     */           });
/*  75 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */     } 
/*     */ 
/*     */     
/*  79 */     if (queryForm.isFirstInit()) {
/*     */       
/*  81 */       queryForm.init(mm.getMetadataListCount());
/*     */     }
/*     */     else {
/*     */       
/*  85 */       queryForm.init();
/*     */     } 
/*  87 */     int pageNo = queryForm.getPageNoAsInt();
/*  88 */     int pageSize = queryForm.getPageSizeAsInt();
/*     */     
/*  90 */     List result = mm.getMetadataList(pageNo, pageSize);
/*     */     
/*  92 */     request.setAttribute("X_RESULTLIST", result);
/*  93 */     return mapping.findForward("page");
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
/* 106 */     MetadataForm mForm = (MetadataForm)form;
/* 107 */     MetadataType mt = (MetadataType)PersistentEnum.fromEnumCode(MetadataType.class, new Integer(mForm.getId()));
/* 108 */     mForm.setDescription(mt.getLabel());
/* 109 */     request.setAttribute("X_METADATADETAILLIST", PersistentEnum.getEnumList(mt.getDetailClass()));
/* 110 */     return mapping.findForward("page");
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
/*     */   public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 122 */     MetadataForm mForm = (MetadataForm)form;
/* 123 */     MetadataType mt = (MetadataType)PersistentEnum.fromEnumCode(MetadataType.class, new Integer(mForm.getId()));
/* 124 */     mt.setLabel(mForm.getDescription());
/* 125 */     MetadataManager mm = ServiceLocator.getMetadataManager(request);
/* 126 */     mm.saveMetadata(mt);
/*     */     
/* 128 */     String[] detailId = request.getParameterValues("detailId");
/* 129 */     String[] engFull = request.getParameterValues("engFull");
/* 130 */     String[] chnFull = request.getParameterValues("chnFull");
/* 131 */     String[] engShort = request.getParameterValues("engShort");
/* 132 */     String[] chnShort = request.getParameterValues("chnShort");
/* 133 */     String[] color = request.getParameterValues("color");
/* 134 */     for (int index = 0; index < detailId.length; index++) {
/* 135 */       MetadataDetail md = mm.getMetadataDetail(new Integer(detailId[index]), new Integer(mForm.getId()));
/* 136 */       md.setEngDescription(engFull[index]);
/* 137 */       md.setEngShortDescription(engShort[index]);
/* 138 */       md.setChnDescription(chnFull[index]);
/* 139 */       md.setChnShortDescription(chnShort[index]);
/* 140 */       md.setColor(color[index]);
/* 141 */       mm.saveMetadataDetail(md);
/*     */     } 
/* 143 */     ActionForward forward = new ActionForward();
/* 144 */     forward.setRedirect(true);
/* 145 */     forward.setPath("editMetadata.do?id=" + mt.getEnumCode().toString());
/* 146 */     return forward;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/MetadataAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */