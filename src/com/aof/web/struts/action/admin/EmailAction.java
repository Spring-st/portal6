/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.query.EmailQueryCondition;
/*     */ import com.aof.model.admin.query.EmailQueryOrder;
/*     */ import com.aof.service.admin.EmailManager;
/*     */ import com.aof.web.struts.action.ActionUtils;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.admin.EmailQueryForm;
/*     */ import java.util.Date;
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
/*     */ public class EmailAction
/*     */   extends BaseAction
/*     */ {
/*     */   private static final String FAKE_DATE_BEGIN = "1900/01/01";
/*     */   private static final String FAKE_DATE_TO = "2099/01/01";
/*     */   
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  49 */     EmailManager fm = ServiceLocator.getEmailManager(request);
/*     */     
/*  51 */     EmailQueryForm queryForm = (EmailQueryForm)form;
/*     */     
/*  53 */     Map conditions = constructQueryMap(queryForm);
/*     */     
/*  55 */     if (queryForm.isFirstInit()) {
/*     */       
/*  57 */       queryForm.init(fm.getEmailListCount(conditions));
/*     */     }
/*     */     else {
/*     */       
/*  61 */       queryForm.init();
/*     */     } 
/*  63 */     int pageNo = queryForm.getPageNoAsInt();
/*  64 */     int pageSize = queryForm.getPageSizeAsInt();
/*     */     
/*  66 */     List result = fm.getEmailList(
/*  67 */         conditions, pageNo, pageSize, EmailQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  68 */     request.setAttribute("X_RESULTLIST", result);
/*  69 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map constructQueryMap(EmailQueryForm queryForm) {
/*  78 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/*     */     
/*  80 */     Integer id = 
/*  81 */       ActionUtils.parseInt(queryForm.getId());
/*  82 */     if (id != null)
/*     */     {
/*  84 */       conditions.put(EmailQueryCondition.ID_EQ, 
/*  85 */           id);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     String mailFrom = 
/*  98 */       queryForm.getMailFrom();
/*  99 */     if (mailFrom != null && mailFrom.trim().length() != 0)
/*     */     {
/* 101 */       conditions.put(EmailQueryCondition.MAILFROM_LIKE, 
/* 102 */           mailFrom);
/*     */     }
/* 104 */     String mailTo = 
/* 105 */       queryForm.getMailTo();
/* 106 */     if (mailTo != null && mailTo.trim().length() != 0)
/*     */     {
/* 108 */       conditions.put(EmailQueryCondition.MAILTO_LIKE, 
/* 109 */           mailTo);
/*     */     }
/* 111 */     String subject = 
/* 112 */       queryForm.getSubject();
/* 113 */     if (subject != null && subject.trim().length() != 0)
/*     */     {
/* 115 */       conditions.put(EmailQueryCondition.SUBJECT_LIKE, 
/* 116 */           subject);
/*     */     }
/* 118 */     String body = 
/* 119 */       queryForm.getBody();
/* 120 */     if (body != null && body.trim().length() != 0)
/*     */     {
/* 122 */       conditions.put(EmailQueryCondition.BODY_LIKE, 
/* 123 */           body);
/*     */     }
/* 125 */     String createTimeBegin = queryForm.getCreateTimeBegin();
/* 126 */     String createTimeTo = queryForm.getCreateTimeTo();
/*     */     
/* 128 */     if ((createTimeBegin != null && createTimeBegin.trim().length() != 0) || (
/* 129 */       createTimeTo != null && createTimeTo.trim().length() != 0)) {
/*     */       
/* 131 */       if (createTimeBegin == null || createTimeBegin.trim().length() == 0) createTimeBegin = "1900/01/01"; 
/* 132 */       if (createTimeTo == null || createTimeTo.trim().length() == 0) createTimeTo = "2099/01/01"; 
/* 133 */       Date queryCreateTimeBegin = ActionUtils.getQueryBeginDateFromDisplayDate(createTimeBegin);
/* 134 */       Date queryCreateTimeTo = ActionUtils.getQueryToDateFromDisplayDate(createTimeTo);
/* 135 */       conditions.put(EmailQueryCondition.CREATETIME_BT, new Object[] { queryCreateTimeBegin, queryCreateTimeTo });
/*     */     } 
/*     */     
/* 138 */     String sentTimeBegin = queryForm.getSentTimeBegin();
/* 139 */     String sentTimeTo = queryForm.getSentTimeTo();
/*     */     
/* 141 */     if ((sentTimeBegin != null && sentTimeBegin.trim().length() != 0) || (
/* 142 */       sentTimeTo != null && sentTimeTo.trim().length() != 0)) {
/*     */       
/* 144 */       if (sentTimeBegin == null || sentTimeBegin.trim().length() == 0) sentTimeBegin = "1900/01/01"; 
/* 145 */       if (sentTimeTo == null || sentTimeTo.trim().length() == 0) sentTimeTo = "2099/01/01"; 
/* 146 */       Date querySentTimeBegin = ActionUtils.getQueryBeginDateFromDisplayDate(sentTimeBegin);
/* 147 */       Date querySentTimeTo = ActionUtils.getQueryToDateFromDisplayDate(sentTimeTo);
/* 148 */       conditions.put(EmailQueryCondition.SENTTIME_BT, new Object[] { querySentTimeBegin, querySentTimeTo });
/*     */     } 
/*     */     
/* 151 */     Integer failCount = 
/* 152 */       ActionUtils.parseInt(queryForm.getFailCount());
/* 153 */     if (failCount != null)
/*     */     {
/* 155 */       conditions.put(EmailQueryCondition.FAILCOUNT_GE, 
/* 156 */           failCount);
/*     */     }
/* 158 */     Integer waitToSend = 
/* 159 */       ActionUtils.parseInt(queryForm.getWaitToSend());
/* 160 */     if (waitToSend != null)
/*     */     {
/* 162 */       conditions.put(EmailQueryCondition.WAITTOSEND_EQ, 
/* 163 */           waitToSend);
/*     */     }
/* 165 */     return conditions;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/EmailAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */