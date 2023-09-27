/*     */ package com.aof.web.struts.action.admin;
/*     */ 
/*     */ import com.aof.model.admin.Site;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.admin.query.WebMonitorQueryOrder;
/*     */ import com.aof.utils.IpAddress;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.domain.SessionList;
/*     */ import com.aof.web.domain.SessionListView;
/*     */ import com.aof.web.struts.action.BaseAction;
/*     */ import com.aof.web.struts.form.admin.WebMonitorQueryForm;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.utils.BeanHelper;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
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
/*     */ public class WebMonitorAction
/*     */   extends BaseAction
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  61 */     WebMonitorQueryForm queryForm = (WebMonitorQueryForm)form;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     List<Site> grantedSiteList = getAndCheckGrantedSiteList(request);
/*  67 */     request.setAttribute("X_SITELIST", grantedSiteList);
/*     */     
/*  69 */     Site site = null;
/*  70 */     boolean global = hasGlobalPower(request);
/*  71 */     request.setAttribute("X_GLOBAL", Boolean.valueOf(global));
/*  72 */     request.setAttribute("x_action", global ? "listGlobalOnlineUser" : "listOnlineUser");
/*  73 */     Integer siteId = ActionUtils.parseInt(queryForm.getSiteId());
/*  74 */     if (siteId == null) {
/*  75 */       if (!global)
/*     */       {
/*     */ 
/*     */         
/*  79 */         site = grantedSiteList.get(0);
/*     */       }
/*     */     } else {
/*  82 */       site = getAndCheckSite(siteId, request);
/*     */     } 
/*     */     
/*  85 */     SessionList.lock.getReadLock();
/*     */     
/*  87 */     try { List orgList = (site == null) ? SessionList.getSessionList() : SessionList.getSessionListBySite(site, request);
/*     */       
/*  89 */       List list = getSessionListView(orgList, 0, 2147483647, queryForm.getOrder(), queryForm.isDescend());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  95 */       String exportType = queryForm.getExportType();
/*  96 */       if (StringUtils.isNotEmpty(exportType)) {
/*     */         
/*  98 */         int index = SessionTempFile.createNewTempFile(request);
/*  99 */         String fileName = "onlineUser";
/* 100 */         String suffix = ExportUtil.export(exportType, list, request, new FileOutputStream(SessionTempFile.getTempFile(index, request)), new Exportable()
/*     */             {
/*     */               public void exportHead(List<String> row, HttpServletRequest request) throws Exception {
/* 103 */                 MessageResources messages = WebMonitorAction.this.getResources(request);
/* 104 */                 if (WebMonitorAction.this.isGlobal(request))
/* 105 */                   row.add(messages.getMessage(WebMonitorAction.this.getLocale(request), "webMonitor.site")); 
/* 106 */                 row.add(messages.getMessage(WebMonitorAction.this.getLocale(request), "webMonitor.userId"));
/* 107 */                 row.add(messages.getMessage(WebMonitorAction.this.getLocale(request), "webMonitor.userName"));
/* 108 */                 row.add(messages.getMessage(WebMonitorAction.this.getLocale(request), "webMonitor.ip"));
/* 109 */                 row.add(messages.getMessage(WebMonitorAction.this.getLocale(request), "webMonitor.loginTime"));
/* 110 */                 row.add(messages.getMessage(WebMonitorAction.this.getLocale(request), "webMonitor.lastAccessTime"));
/* 111 */                 row.add(messages.getMessage(WebMonitorAction.this.getLocale(request), "webMonitor.timeToLive"));
/*     */               }
/*     */               
/*     */               public void exportRow(List<Object> row, Object data, HttpServletRequest request) throws Exception {
/* 115 */                 if (WebMonitorAction.this.isGlobal(request))
/* 116 */                   row.add(BeanHelper.getBeanPropertyValue(data, "user.primarySite.name")); 
/* 117 */                 row.add(BeanHelper.getBeanPropertyValue(data, "user.loginName"));
/* 118 */                 row.add(BeanHelper.getBeanPropertyValue(data, "user.name"));
/* 119 */                 row.add(BeanHelper.getBeanPropertyValue(data, "ip"));
/* 120 */                 row.add(BeanHelper.getBeanPropertyValue(data, "loginTime"));
/* 121 */                 row.add(BeanHelper.getBeanPropertyValue(data, "accessTime"));
/* 122 */                 row.add(BeanHelper.getBeanPropertyValue(data, "liveMinute"));
/* 123 */                 row.add(BeanHelper.getBeanPropertyValue(data, "liveSecond"));
/*     */               }
/*     */             });
/* 126 */         return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + '.' + suffix, true);
/*     */       } 
/*     */       
/* 129 */       if (queryForm.isFirstInit()) {
/* 130 */         queryForm.init(list.size());
/*     */       } else {
/* 132 */         queryForm.init();
/*     */       }
/*     */        }
/*     */     finally
/*     */     
/* 137 */     { SessionList.lock.releaseLock(); }  SessionList.lock.releaseLock();
/*     */ 
/*     */     
/* 140 */     return mapping.findForward("page");
/*     */   }
/*     */ 
/*     */   
/*     */   private List getSessionListView(List sessionList, int pageNo, int pageSize, String order, boolean isDescend) {
/* 145 */     List<HttpSession> sortedList = getSortedList(sessionList, order);
/* 146 */     List<SessionListView> retList = new ArrayList();
/* 147 */     long nowTimeLong = Calendar.getInstance().getTimeInMillis();
/* 148 */     if (!isDescend) {
/* 149 */       for (int index = pageNo * pageSize; index < sortedList.size() && index < (pageNo + 1) * pageSize; index++) {
/*     */         try {
/* 151 */           HttpSession session = sortedList.get(index);
/* 152 */           SessionListView view = new SessionListView();
/* 153 */           view.setSession(session);
/* 154 */           view.setUser((User)session.getAttribute("LOGIN_USER"));
/* 155 */           view.setIp((IpAddress)session.getAttribute("LOGIN_IP"));
/* 156 */           view.setLoginTime((Date)session.getAttribute("LOGIN_TIME"));
/* 157 */           view.setAccessTime(session.getLastAccessedTime());
/* 158 */           long secondLeft = session.getMaxInactiveInterval() - (nowTimeLong - session.getLastAccessedTime()) / 
/* 159 */             1000L;
/* 160 */           if (secondLeft > 0L) {
/* 161 */             view.setLiveMinute(secondLeft / 60L);
/* 162 */             view.setLiveSecond(secondLeft % 60L);
/*     */           } 
/* 164 */           retList.add(view);
/* 165 */         } catch (Exception exception) {}
/*     */       } 
/*     */     } else {
/*     */       
/* 169 */       for (int index = sortedList.size() - 1 - pageNo * pageSize; index >= 0 && 
/* 170 */         index >= sortedList.size() - (pageNo + 1) * pageSize; index--) {
/*     */         try {
/* 172 */           HttpSession session = sortedList.get(index);
/* 173 */           SessionListView view = new SessionListView();
/* 174 */           view.setSession(session);
/* 175 */           view.setUser((User)session.getAttribute("LOGIN_USER"));
/* 176 */           view.setIp((IpAddress)session.getAttribute("LOGIN_IP"));
/* 177 */           view.setLoginTime((Date)session.getAttribute("LOGIN_TIME"));
/* 178 */           view.setAccessTime(session.getLastAccessedTime());
/* 179 */           long secondLeft = session.getMaxInactiveInterval() - (nowTimeLong - session.getLastAccessedTime()) / 
/* 180 */             1000L;
/* 181 */           if (secondLeft > 0L) {
/* 182 */             view.setLiveMinute(secondLeft / 60L);
/* 183 */             view.setLiveSecond(secondLeft % 60L);
/*     */           } 
/* 185 */           retList.add(view);
/* 186 */         } catch (Exception exception) {}
/*     */       } 
/*     */     } 
/*     */     
/* 190 */     return retList;
/*     */   }
/*     */   
/*     */   private List getSortedList(List sessionList, String order) {
/* 194 */     if (order == null) {
/* 195 */       return sessionList;
/*     */     }
/* 197 */     if (order.equals(WebMonitorQueryOrder.SITE.getName())) {
/* 198 */       return getSortedListBySite(sessionList);
/*     */     }
/* 200 */     if (order.equals(WebMonitorQueryOrder.USER_ID.getName())) {
/* 201 */       return getSortedListByUserId(sessionList);
/*     */     }
/* 203 */     if (order.equals(WebMonitorQueryOrder.USER_NAME.getName())) {
/* 204 */       return getSortedListByUserName(sessionList);
/*     */     }
/* 206 */     if (order.equals(WebMonitorQueryOrder.IP.getName())) {
/* 207 */       return getSortedListByIp(sessionList);
/*     */     }
/* 209 */     if (order.equals(WebMonitorQueryOrder.LOGIN_TIME.getName())) {
/* 210 */       return getSortedListByLoginTime(sessionList);
/*     */     }
/* 212 */     if (order.equals(WebMonitorQueryOrder.ACCESS_TIME.getName())) {
/* 213 */       return getSortedListByAccessTime(sessionList);
/*     */     }
/* 215 */     if (order.equals(WebMonitorQueryOrder.LIVE_TIME.getName())) {
/* 216 */       return getSortedListByAccessTime(sessionList);
/*     */     }
/* 218 */     return sessionList;
/*     */   }
/*     */   
/*     */   private List getSortedListBySite(List sessionList) {
/* 222 */     List<HttpSession> retList = new ArrayList();
/* 223 */     Iterator<HttpSession> itor = sessionList.iterator();
/* 224 */     while (itor.hasNext()) {
/*     */       try {
/* 226 */         HttpSession session = itor.next();
/* 227 */         User user = (User)session.getAttribute("LOGIN_USER");
/* 228 */         int insertIndex = getSortedIndexBySite(user.getPrimarySite().getName(), retList);
/* 229 */         retList.add(insertIndex, session);
/* 230 */       } catch (Exception exception) {}
/*     */     } 
/*     */     
/* 233 */     return retList;
/*     */   }
/*     */   
/*     */   private int getSortedIndexBySite(String siteName, List<HttpSession> sessionList) {
/* 237 */     int index = 0;
/* 238 */     while (index < sessionList.size()) {
/* 239 */       String compareContent = ((User)((HttpSession)sessionList.get(index))
/* 240 */         .getAttribute("LOGIN_USER")).getPrimarySite().getName();
/* 241 */       if (compareContent.compareTo(siteName) > 0)
/*     */         break; 
/* 243 */       index++;
/*     */     } 
/* 245 */     return index;
/*     */   }
/*     */   
/*     */   private List getSortedListByUserId(List sessionList) {
/* 249 */     List<HttpSession> retList = new ArrayList();
/* 250 */     Iterator<HttpSession> itor = sessionList.iterator();
/* 251 */     while (itor.hasNext()) {
/*     */       try {
/* 253 */         HttpSession session = itor.next();
/* 254 */         User user = (User)session.getAttribute("LOGIN_USER");
/* 255 */         int insertIndex = getSortedIndexByUserId(user.getLoginName(), retList);
/* 256 */         retList.add(insertIndex, session);
/* 257 */       } catch (Exception exception) {}
/*     */     } 
/*     */     
/* 260 */     return retList;
/*     */   }
/*     */   
/*     */   private int getSortedIndexByUserId(String userId, List<HttpSession> sessionList) {
/* 264 */     int index = 0;
/* 265 */     while (index < sessionList.size()) {
/* 266 */       String compareContent = ((User)((HttpSession)sessionList.get(index))
/* 267 */         .getAttribute("LOGIN_USER")).getLoginName();
/* 268 */       if (compareContent.compareTo(userId) > 0)
/*     */         break; 
/* 270 */       index++;
/*     */     } 
/* 272 */     return index;
/*     */   }
/*     */   
/*     */   private List getSortedListByUserName(List sessionList) {
/* 276 */     List<HttpSession> retList = new ArrayList();
/* 277 */     Iterator<HttpSession> itor = sessionList.iterator();
/* 278 */     while (itor.hasNext()) {
/*     */       try {
/* 280 */         HttpSession session = itor.next();
/* 281 */         User user = (User)session.getAttribute("LOGIN_USER");
/* 282 */         int insertIndex = getSortedIndexByUserName(user.getName(), retList);
/* 283 */         retList.add(insertIndex, session);
/* 284 */       } catch (Exception exception) {}
/*     */     } 
/*     */     
/* 287 */     return retList;
/*     */   }
/*     */   
/*     */   private int getSortedIndexByUserName(String userName, List<HttpSession> sessionList) {
/* 291 */     int index = 0;
/* 292 */     while (index < sessionList.size()) {
/* 293 */       String compareContent = ((User)((HttpSession)sessionList.get(index))
/* 294 */         .getAttribute("LOGIN_USER")).getName();
/* 295 */       if (compareContent.compareTo(userName) > 0)
/*     */         break; 
/* 297 */       index++;
/*     */     } 
/* 299 */     return index;
/*     */   }
/*     */   
/*     */   private List getSortedListByIp(List sessionList) {
/* 303 */     List<HttpSession> retList = new ArrayList();
/* 304 */     Iterator<HttpSession> itor = sessionList.iterator();
/* 305 */     while (itor.hasNext()) {
/*     */       try {
/* 307 */         HttpSession session = itor.next();
/* 308 */         IpAddress ip = (IpAddress)session.getAttribute("LOGIN_IP");
/* 309 */         int insertIndex = getSortedIndexByIp(ip, retList);
/* 310 */         retList.add(insertIndex, session);
/* 311 */       } catch (Exception exception) {}
/*     */     } 
/*     */     
/* 314 */     return retList;
/*     */   }
/*     */   
/*     */   private int getSortedIndexByIp(IpAddress ip, List<HttpSession> sessionList) {
/* 318 */     int index = 0;
/* 319 */     while (index < sessionList.size()) {
/* 320 */       IpAddress compareIp = (IpAddress)((HttpSession)sessionList.get(index))
/* 321 */         .getAttribute("LOGIN_IP");
/* 322 */       if (compareIp.compareTo(ip) > 0)
/*     */         break; 
/* 324 */       index++;
/*     */     } 
/* 326 */     return index;
/*     */   }
/*     */   
/*     */   private List getSortedListByLoginTime(List sessionList) {
/* 330 */     List<HttpSession> retList = new ArrayList();
/* 331 */     Iterator<HttpSession> itor = sessionList.iterator();
/* 332 */     while (itor.hasNext()) {
/*     */       try {
/* 334 */         HttpSession session = itor.next();
/* 335 */         Date loginTime = (Date)session.getAttribute("LOGIN_TIME");
/* 336 */         int insertIndex = getSortedIndexByLoginTime(loginTime, retList);
/* 337 */         retList.add(insertIndex, session);
/* 338 */       } catch (Exception exception) {}
/*     */     } 
/*     */     
/* 341 */     return retList;
/*     */   }
/*     */   
/*     */   private int getSortedIndexByLoginTime(Date loginTime, List<HttpSession> sessionList) {
/* 345 */     int index = 0;
/* 346 */     while (index < sessionList.size()) {
/* 347 */       Date compareDate = (Date)((HttpSession)sessionList.get(index))
/* 348 */         .getAttribute("LOGIN_TIME");
/* 349 */       if (compareDate.compareTo(loginTime) > 0)
/*     */         break; 
/* 351 */       index++;
/*     */     } 
/* 353 */     return index;
/*     */   }
/*     */   
/*     */   private List getSortedListByAccessTime(List sessionList) {
/* 357 */     List<HttpSession> retList = new ArrayList();
/* 358 */     Iterator<HttpSession> itor = sessionList.iterator();
/* 359 */     while (itor.hasNext()) {
/*     */       try {
/* 361 */         HttpSession session = itor.next();
/* 362 */         Date accessTime = new Date(session.getLastAccessedTime());
/* 363 */         int insertIndex = getSortedIndexByAccessTime(accessTime, retList);
/* 364 */         retList.add(insertIndex, session);
/* 365 */       } catch (Exception exception) {}
/*     */     } 
/*     */     
/* 368 */     return retList;
/*     */   }
/*     */   
/*     */   private int getSortedIndexByAccessTime(Date accessTime, List<HttpSession> sessionList) {
/* 372 */     int index = 0;
/* 373 */     while (index < sessionList.size()) {
/* 374 */       Date compareDate = new Date(((HttpSession)sessionList.get(index)).getLastAccessedTime());
/* 375 */       if (compareDate.compareTo(accessTime) > 0)
/*     */         break; 
/* 377 */       index++;
/*     */     } 
/* 379 */     return index;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/admin/WebMonitorAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */