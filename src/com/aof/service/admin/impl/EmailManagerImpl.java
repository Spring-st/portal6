/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.EmailDAO;
/*     */ import com.aof.dao.admin.GlobalDAO;
/*     */ import com.aof.dao.admin.ParameterDAO;
/*     */ import com.aof.dao.admin.SiteDAO;
/*     */ import com.aof.dao.admin.SupplierDAO;
/*     */ import com.aof.dao.admin.UserDAO;
/*     */ import com.aof.dao.business.BaseApproveRequestDAO;
/*     */ import com.aof.dao.business.rule.ApproverDelegateDAO;
/*     */ import com.aof.model.admin.Email;
/*     */ import com.aof.model.admin.EmailBatch;
/*     */ import com.aof.model.admin.Function;
/*     */ import com.aof.model.admin.GlobalMailReminder;
/*     */ import com.aof.model.admin.GlobalParameter;
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.SiteMailReminder;
/*     */ import com.aof.model.admin.Supplier;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.query.EmailQueryOrder;
/*     */ import com.aof.model.business.BaseApproveRequest;
/*     */ import com.aof.model.business.query.BaseApproveQueryCondition;
/*     */ import com.aof.model.business.rule.ApproverDelegate;
/*     */ import com.aof.model.business.rule.query.ApproverDelegateQueryCondition;
/*     */ import com.aof.model.metadata.ApproveStatus;
/*     */ import com.aof.model.metadata.ApproverDelegateType;
/*     */ import com.aof.model.metadata.GlobalMailReminderType;
/*     */ import com.aof.model.metadata.SiteMailReminderType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.EmailManager;
/*     */ import com.aof.service.admin.FunctionManager;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.LineNumberReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import javax.activation.DataSource;
/*     */ import javax.mail.internet.MimeMessage;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.velocity.app.VelocityEngine;
/*     */ import org.apache.velocity.exception.VelocityException;
/*     */ import org.apache.velocity.tools.generic.DateTool;
/*     */ import org.apache.velocity.tools.generic.NumberTool;
/*     */ import org.springframework.context.ResourceLoaderAware;
/*     */ import org.springframework.core.io.ResourceLoader;
/*     */ import org.springframework.mail.javamail.JavaMailSenderImpl;
/*     */ import org.springframework.mail.javamail.MimeMessageHelper;
/*     */ import org.springframework.ui.velocity.VelocityEngineUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EmailManagerImpl
/*     */   extends BaseManager
/*     */   implements EmailManager, ResourceLoaderAware
/*     */ {
/*  79 */   private Log log = LogFactory.getLog(EmailManagerImpl.class);
/*     */ 
/*     */   
/*     */   private EmailDAO dao;
/*     */ 
/*     */   
/*     */   private GlobalDAO globalDAO;
/*     */ 
/*     */   
/*     */   private VelocityEngine velocityEngine;
/*     */ 
/*     */   
/*     */   private ParameterDAO parameterDAO;
/*     */ 
/*     */   
/*     */   private UserDAO userDAO;
/*     */ 
/*     */   
/*     */   private FunctionManager functionManager;
/*     */   
/*     */   private SupplierDAO supplierDAO;
/*     */   
/*     */   private ApproverDelegateDAO approverDelegateDAO;
/*     */   
/*     */   private SiteDAO siteDAO;
/*     */   
/*     */   private ResourceLoader resourceLoader;
/*     */   
/*     */   private static Properties emailProperty;
/*     */ 
/*     */   
/*     */   public void setSupplierDAO(SupplierDAO supplierDAO) {
/* 111 */     this.supplierDAO = supplierDAO;
/*     */   }
/*     */   
/*     */   public void setUserDAO(UserDAO userDAO) {
/* 115 */     this.userDAO = userDAO;
/*     */   }
/*     */   
/*     */   public void setVelocityEngine(VelocityEngine velocityEngine) {
/* 119 */     this.velocityEngine = velocityEngine;
/*     */   }
/*     */   
/*     */   public void setEmailDAO(EmailDAO dao) {
/* 123 */     this.dao = dao;
/*     */   }
/*     */   
/*     */   public void setGlobalDAO(GlobalDAO globalDao) {
/* 127 */     this.globalDAO = globalDao;
/*     */   }
/*     */   
/*     */   public void setApproverDelegateDAO(ApproverDelegateDAO approverDelegateDAO) {
/* 131 */     this.approverDelegateDAO = approverDelegateDAO;
/*     */   }
/*     */   
/*     */   public void setSiteDAO(SiteDAO siteDAO) {
/* 135 */     this.siteDAO = siteDAO;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Email getEmail(Integer id) {
/* 144 */     return this.dao.getEmail(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Email updateEmail(Email email) {
/* 153 */     return this.dao.updateEmail(email);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Email insertEmail(Email email, String body) {
/* 162 */     email = this.dao.insertEmail(email);
/* 163 */     this.dao.saveEmailBody(email.getId(), body);
/* 164 */     return email;
/*     */   }
/*     */   
/*     */   public EmailBatch insertEmailBatch(EmailBatch emailBatch, String body) {
/* 168 */     emailBatch = this.dao.insertEmailBatch(emailBatch);
/* 169 */     this.dao.saveEmailBatchBody(emailBatch.getId(), body);
/* 170 */     return emailBatch;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEmailListCount(Map conditions) {
/* 179 */     return this.dao.getEmailListCount(conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEmailList(Map conditions, int pageNo, int pageSize, EmailQueryOrder order, boolean descend) {
/* 188 */     return this.dao.getEmailList(conditions, pageNo, pageSize, order, descend);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertEmail(Site site, String from, String to, String subject, String body) {
/* 197 */     Email email = new Email();
/* 198 */     email.setMailFrom(from);
/* 199 */     email.setMailTo(to);
/* 200 */     email.setSubject(subject);
/* 201 */     email.setSite(site);
/* 202 */     this.dao.insertEmail(email);
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertEmailBatch(Site site, String from, String to, String body, String emailTemplateName, String refNo, User user) {
/* 207 */     EmailBatch emailBatch = new EmailBatch();
/* 208 */     emailBatch.setSite(site);
/* 209 */     emailBatch.setMailToUser(user);
/* 210 */     emailBatch.setTemplateName(emailTemplateName);
/* 211 */     emailBatch.setRefNo(refNo);
/* 212 */     emailBatch.setIsSend(YesNo.NO);
/* 213 */     this.dao.insertEmailBatch(emailBatch);
/* 214 */     this.dao.saveEmailBatchBody(emailBatch.getId(), body);
/*     */   }
/*     */   
/*     */   private void sendEmail(JavaMailSenderImpl sender, Email email) {
/* 218 */     MimeMessage message = sender.createMimeMessage();
/*     */     try {
/* 220 */       MimeMessageHelper helper = new MimeMessageHelper(message, 2, "UTF-8");
/* 221 */       helper.setFrom(email.getMailFrom());
/* 222 */       helper.setTo(email.getMailTo());
/* 223 */       String subject = email.getSubject();
/* 224 */       helper.setSubject(subject);
/* 225 */       Site site = email.getSite();
/* 226 */       Map<Object, Object> context = new HashMap<Object, Object>();
/* 227 */       context.put("subject", subject);
/* 228 */       context.put("x_email_to_site", site);
/* 229 */       context.put("body", this.dao.getEmailBody(email.getId()));
/* 230 */       helper.setText(VelocityEngineUtils.mergeTemplateIntoString(this.velocityEngine, "EMail.vm", context), true);
/* 231 */       final InputStream in = this.siteDAO.getSiteLogo(email.getSite().getId());
/* 232 */       if (in == null) {
/* 233 */         helper.addInline("logo.gif", this.resourceLoader.getResource("/WEB-INF/emailTemplate/resource/logo.gif"));
/*     */       } else {
/* 235 */         in.mark(0);
/* 236 */         DataSource ds = new DataSource()
/*     */           {
/*     */             public String getContentType() {
/* 239 */               return "image/gif";
/*     */             }
/*     */             
/*     */             public InputStream getInputStream() throws IOException {
/* 243 */               in.reset();
/* 244 */               return in;
/*     */             }
/*     */             
/*     */             public String getName() {
/* 248 */               return "logo.gif";
/*     */             }
/*     */             
/*     */             public OutputStream getOutputStream() throws IOException {
/* 252 */               return null;
/*     */             }
/*     */           };
/*     */         
/* 256 */         helper.addInline("logo.gif", ds);
/*     */       }
/*     */     
/* 259 */     } catch (Exception e) {
/* 260 */       throw new RuntimeException(e);
/*     */     } 
/*     */     
/* 263 */     sender.send(message);
/* 264 */     email.setWaitToSend(YesNo.NO);
/* 265 */     email.setSentTime(new Date());
/* 266 */     this.dao.updateEmail(email);
/*     */   }
/*     */   
/*     */   private JavaMailSenderImpl getMailSender() {
/* 270 */     JavaMailSenderImpl sender = new JavaMailSenderImpl();
/* 271 */     GlobalParameter para = this.globalDAO.getParameter();
/* 272 */     sender.setHost(para.getSmtpAddress());
/* 273 */     sender.setUsername(para.getSmtpUsername());
/* 274 */     sender.setPassword(para.getSmtpPassword());
/*     */     
/* 276 */     Properties pt = new Properties();
/* 277 */     pt.setProperty("mail.smtp.auth", "true");
/*     */     
/* 279 */     sender.setJavaMailProperties(pt);
/*     */     
/* 281 */     return sender;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendEmail() {
/* 290 */     JavaMailSenderImpl sender = getMailSender();
/* 291 */     Iterator<Email> mailItor = this.dao.getWaitToSendEmailList().iterator();
/* 292 */     while (mailItor.hasNext()) {
/* 293 */       Email email = mailItor.next();
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
/*     */   public void insertEmail(Site site, String to, String templateLocation, Map<String, DateTool> context) {
/*     */     String[] ss;
/* 308 */     context.putAll(emailProperty);
/* 309 */     context.put("dateTool", dateTool);
/* 310 */     context.put("numberTool", numberTool);
/* 311 */     context.put("signature", site.getName());
/*     */     
/*     */     try {
/* 314 */       ss = splitTitleAndContent(
/* 315 */           VelocityEngineUtils.mergeTemplateIntoString(
/* 316 */             this.velocityEngine, templateLocation, context));
/* 317 */     } catch (VelocityException e) {
/* 318 */       throw new RuntimeException(e);
/*     */     } 
/* 320 */     insertEmail(site, emailProperty.getProperty("from"), to, ss[0], ss[1]);
/*     */   }
/*     */   public void insertEmailBatch(Site site, String to, String templateLocation, String refNo, Map<String, DateTool> context, String batchEmailTemplateName) {
/*     */     String ss;
/* 324 */     context.putAll(emailProperty);
/* 325 */     context.put("dateTool", dateTool);
/* 326 */     context.put("numberTool", numberTool);
/* 327 */     context.put("signature", site.getName());
/*     */     
/*     */     try {
/* 330 */       ss = VelocityEngineUtils.mergeTemplateIntoString(
/* 331 */           this.velocityEngine, batchEmailTemplateName, context);
/* 332 */     } catch (VelocityException e) {
/* 333 */       throw new RuntimeException(e);
/*     */     } 
/* 335 */     insertEmailBatch(site, emailProperty.getProperty("from"), to, ss, templateLocation, refNo, (User)context.get("user"));
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
/*     */   private static String[] splitTitleAndContent(String s) {
/* 348 */     String[] retVal = new String[2];
/* 349 */     LineNumberReader reader = new LineNumberReader(new StringReader(s));
/*     */     
/* 351 */     boolean titleRead = false;
/*     */     
/*     */     try {
/*     */       while (true) {
/* 355 */         String line = reader.readLine();
/* 356 */         if (line == null)
/* 357 */           break;  if (!titleRead && line.trim().equals("Title:")) {
/*     */           
/* 359 */           line = reader.readLine();
/* 360 */           if (line == null)
/* 361 */             break;  retVal[0] = line;
/* 362 */           titleRead = true; continue;
/*     */         } 
/* 364 */         if (titleRead && line.trim().equals("Body:"))
/*     */         {
/* 366 */           retVal[1] = getRemain(reader);
/*     */         }
/*     */       } 
/* 369 */       reader.close();
/* 370 */     } catch (IOException e) {
/* 371 */       throw new RuntimeException(e);
/*     */     } 
/* 373 */     return retVal;
/*     */   }
/*     */   
/*     */   private static String getRemain(LineNumberReader reader) {
/* 377 */     StringBuffer sb = new StringBuffer();
/*     */     try {
/*     */       int c;
/* 380 */       while ((c = reader.read()) != -1)
/*     */       {
/* 382 */         sb.append((char)c);
/*     */       }
/* 384 */     } catch (IOException e) {
/* 385 */       throw new RuntimeException(e);
/*     */     } 
/* 387 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/* 392 */     InputStream is = getClass().getResourceAsStream("/email.properties");
/* 393 */     emailProperty = new Properties();
/*     */     try {
/* 395 */       emailProperty.load(is);
/* 396 */     } catch (IOException e) {
/* 397 */       throw new RuntimeException(e);
/*     */     } finally {
/*     */       
/*     */       try {
/* 401 */         is.close();
/* 402 */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 409 */   private static DateTool dateTool = new DateTool();
/* 410 */   private static NumberTool numberTool = new NumberTool();
/*     */   
/*     */   public void mailReminder() {
/* 413 */     Date now = new Date();
/* 414 */     Set siteSet = this.parameterDAO.getEnabledSiteSetWithMailReminders();
/* 415 */     for (Iterator<Site> iter = siteSet.iterator(); iter.hasNext(); ) {
/* 416 */       Site site = iter.next();
/* 417 */       notifyCapexRequestApprover(now, site);
/* 418 */       notifyHotelMaintainer(now, site);
/* 419 */       notifySupplierMaintainer(now, site);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sendBatchEmail() {
/* 424 */     List<EmailBatch> list = this.dao.getAllUnSendEmailBatch();
/* 425 */     User oldMailToUser = null;
/* 426 */     User newMailToUser = null;
/* 427 */     Site oldSite = null;
/* 428 */     Site newSite = null;
/* 429 */     String oldTemplate = null;
/* 430 */     String newTemplate = null;
/* 431 */     StringBuffer body = new StringBuffer();
/*     */     
/* 433 */     for (int i = 0; i < list.size(); i++) {
/*     */       
/* 435 */       EmailBatch eb = list.get(i);
/* 436 */       newMailToUser = eb.getMailToUser();
/* 437 */       newTemplate = eb.getTemplateName();
/* 438 */       newSite = eb.getSite();
/*     */       
/* 440 */       if (newMailToUser.equals(oldMailToUser) && newTemplate.equals(oldTemplate) && newSite.equals(oldSite)) {
/* 441 */         body.append(this.dao.getEmailBatchBody(eb.getId()));
/*     */       } else {
/* 443 */         if (oldSite != null) {
/* 444 */           Map<Object, Object> context = new HashMap<Object, Object>();
/* 445 */           context.put("batchEmailContext", body.toString());
/* 446 */           context.put("user", oldMailToUser);
/* 447 */           insertEmail(oldSite, oldMailToUser.getEmail(), oldTemplate, context);
/*     */         } 
/*     */         
/* 450 */         oldMailToUser = newMailToUser;
/* 451 */         oldSite = newSite;
/* 452 */         oldTemplate = newTemplate;
/* 453 */         body = new StringBuffer();
/* 454 */         body.append(this.dao.getEmailBatchBody(eb.getId()));
/*     */       } 
/*     */       
/* 457 */       eb.setIsSend(YesNo.YES);
/* 458 */       this.dao.updateEmailBatch(eb);
/*     */     } 
/*     */     
/* 461 */     if (oldSite != null) {
/* 462 */       Map<Object, Object> context = new HashMap<Object, Object>();
/* 463 */       context.put("batchEmailContext", body.toString());
/* 464 */       context.put("user", oldMailToUser);
/* 465 */       insertEmail(oldSite, oldMailToUser.getEmail(), oldTemplate, context);
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
/*     */ 
/*     */   
/*     */   private void notifyHotelMaintainer(Date now, Site site) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void notifySupplierMaintainer(Date now, Site site) {
/* 495 */     GlobalMailReminder gmr = this.parameterDAO.getGlobalMailReminder(
/* 496 */         GlobalMailReminderType.SUPPLIER_MAINTAINER_NOT_RESPOND);
/* 497 */     if (gmr == null)
/* 498 */       return;  if (gmr.getMaxTime() <= 0)
/* 499 */       return;  List supplierList = this.supplierDAO.getSupplierMaintainerNotResponsedList(site, now, gmr);
/*     */     
/* 501 */     if (supplierList.size() > 0) {
/* 502 */       List userList = getUserList(site, "siteSupplierMaintenance");
/* 503 */       for (Iterator<User> iterator = userList.iterator(); iterator.hasNext(); ) {
/* 504 */         User user = iterator.next();
/* 505 */         Map<Object, Object> context = new HashMap<Object, Object>();
/* 506 */         context.put("x_user", user);
/* 507 */         context.put("x_supplierList", supplierList);
/* 508 */         insertEmail(site, user.getEmail(), "SupplierPromoteRemind.vm", context);
/*     */       } 
/* 510 */       for (Iterator<Supplier> itor = supplierList.iterator(); itor.hasNext(); ) {
/* 511 */         Supplier supplier = itor.next();
/* 512 */         supplier.emailed(now);
/* 513 */         this.supplierDAO.updateSupplier(supplier);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void notifyCapexRequestApprover(Date now, Site site) {}
/*     */ 
/*     */   
/*     */   private void notifyApprover(Date now, Site site, SiteMailReminderType reminderType, BaseApproveRequestDAO approveRequestDAO, String template, ApproverDelegateType delegateType) {
/* 524 */     SiteMailReminder smr = (SiteMailReminder)site.getMailReminders().get(reminderType);
/* 525 */     if (smr == null || smr.getMaxTime() <= 0) {
/*     */       return;
/*     */     }
/* 528 */     long t = now.getTime();
/* 529 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 530 */     conditions.put(BaseApproveQueryCondition.SITE_ID_EQ, site.getId());
/* 531 */     conditions.put(BaseApproveQueryCondition.STATUS_EQ, ApproveStatus.WAITING_FOR_APPROVE);
/* 532 */     conditions.put(BaseApproveQueryCondition.YOUR_TURN_DATE_LE, new Date(t - (smr.getDueDay() * 86400000)));
/* 533 */     conditions.put(BaseApproveQueryCondition.LAST_EMAIL_DATE_LE, new Date(t - (smr.getIntervalDay() * 86400000) + 3600000L));
/* 534 */     conditions.put(BaseApproveQueryCondition.SENT_EMAIL_TIMES_LT, new Integer(smr.getMaxTime()));
/* 535 */     Map<Object, Object> cache = new HashMap<Object, Object>();
/* 536 */     for (Iterator<Object[]> itor = approveRequestDAO.getBaseApproveRequestList(conditions).iterator(); itor.hasNext(); ) {
/* 537 */       Object[] o = itor.next();
/* 538 */       BaseApproveRequest bar = (BaseApproveRequest)o[0];
/* 539 */       notifyApprover(cache, delegateType, bar, o[1]);
/* 540 */       bar.setLastEmailDate(now);
/* 541 */       bar.setSentEmailTimes(bar.getSentEmailTimes() + 1);
/* 542 */       this.globalDAO.updateObject(bar);
/*     */     } 
/* 544 */     Map<Object, Object> context = new HashMap<Object, Object>();
/* 545 */     for (Iterator<User> iterator = cache.keySet().iterator(); iterator.hasNext(); ) {
/* 546 */       User approver = iterator.next();
/* 547 */       Object targetList = cache.get(approver);
/* 548 */       context.put("user", approver);
/* 549 */       context.put("targetList", targetList);
/* 550 */       insertEmail(site, approver.getEmail(), template, context);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void notifyApprover(Map cache, ApproverDelegateType type, BaseApproveRequest approveRequest, Object target) {
/* 555 */     User approver = approveRequest.getApprover();
/* 556 */     notifyApprover(cache, approver, target);
/* 557 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 558 */     conditions.put(ApproverDelegateQueryCondition.ORIGINALAPPROVER_ID_EQ, approver.getId());
/* 559 */     conditions.put(ApproverDelegateQueryCondition.TYPE_EQ, type);
/* 560 */     long now = getTodayTimeMillis();
/* 561 */     conditions.put(ApproverDelegateQueryCondition.FROMDATE_LT, new Date(now + 86400000L));
/* 562 */     conditions.put(ApproverDelegateQueryCondition.TODATE_GE, new Date(now));
/* 563 */     for (Iterator<ApproverDelegate> itor = this.approverDelegateDAO.getApproverDelegateList(conditions, 0, -1, null, false).iterator(); itor.hasNext(); ) {
/* 564 */       ApproverDelegate delegate = itor.next();
/* 565 */       notifyApprover(cache, delegate.getDelegateApprover(), target);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void notifyApprover(Map<User, List> cache, User approver, Object target) {
/* 570 */     List<Object> targetList = (List)cache.get(approver);
/* 571 */     if (targetList == null) {
/* 572 */       targetList = new ArrayList();
/* 573 */       cache.put(approver, targetList);
/*     */     } 
/* 575 */     targetList.add(target);
/*     */   }
/*     */ 
/*     */   
/*     */   private List getUserList(Site site, String functionName) {
/* 580 */     if (this.functionManager.isInitiated()) {
/* 581 */       Function f = this.functionManager.getFunction(functionName);
/* 582 */       return this.userDAO.getEnabledUserList(f, site);
/*     */     } 
/* 584 */     return new ArrayList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParameterDAO(ParameterDAO parameterDAO) {
/* 591 */     this.parameterDAO = parameterDAO;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFunctionManager(FunctionManager functionManager) {
/* 596 */     this.functionManager = functionManager;
/*     */   }
/*     */   
/*     */   public void setResourceLoader(ResourceLoader resourceLoader) {
/* 600 */     this.resourceLoader = resourceLoader;
/*     */   }
/*     */   
/*     */   public void deleteEmailBatch(String refNo) {
/* 604 */     this.dao.deleteEmailBatch(refNo);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/EmailManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */