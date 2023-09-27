/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.model.admin.DataTransferParameter;
/*     */ import com.aof.service.admin.DataTransferManager;
/*     */ import com.aof.service.admin.EmailManager;
/*     */ import com.shcnc.struts.form.DateFormatter;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataTransferThread
/*     */   extends Thread
/*     */ {
/*  28 */   private Log log = LogFactory.getLog(DataTransferThread.class);
/*     */   
/*     */   private DataTransferManager manager;
/*     */   
/*     */   private DataTransferParameter para;
/*     */   
/*     */   private EmailManager emailManager;
/*     */   
/*  36 */   private int todayRunCount = 0;
/*     */ 
/*     */   
/*     */   private Date lastJobTime;
/*     */ 
/*     */   
/*     */   private boolean jobFinish = false;
/*     */ 
/*     */   
/*     */   private boolean manualJob = false;
/*     */   
/*     */   private boolean resetJob = false;
/*     */   
/*     */   private static final long ONE_DAY_MILLISECOND = 86400000L;
/*     */   
/*     */   private static final long ONE_HOUR_MILLISECOND = 3600000L;
/*     */   
/*     */   private static final long ONE_MINUTE_MILLISECOND = 60000L;
/*     */   
/*     */   private static final long ONE_SECOND_MILLISECOND = 1000L;
/*     */ 
/*     */   
/*     */   public void setJobFinish(boolean jobFinish) {
/*  59 */     this.jobFinish = jobFinish;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isJobFinish() {
/*  67 */     return this.jobFinish;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DataTransferThread(EmailManager emailManager, DataTransferManager manager, DataTransferParameter para) {
/*  73 */     this.manager = manager;
/*  74 */     this.para = para;
/*  75 */     this.emailManager = emailManager;
/*  76 */     setName("DataTransfer_" + para.getSite().getId() + '[' + para.getSite().getName() + ']');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isManualJob() {
/*  83 */     return this.manualJob;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setManualJob(boolean manualJob) {
/*  91 */     this.manualJob = manualJob;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isResetJob() {
/*  98 */     return this.resetJob;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResetJob(boolean resetJob) {
/* 106 */     this.resetJob = resetJob;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataTransferParameter getPara() {
/* 113 */     return this.para;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPara(DataTransferParameter para) {
/* 121 */     this.para = para;
/*     */   }
/*     */   
/*     */   public void run() {
/* 125 */     this.log.info("Begin data transfer job.\n\tSite-->" + this.para.getSite().getName());
/* 126 */     long sleepMilliSeconds = 5000L;
/* 127 */     if (sleepMilliSeconds == 0L) {
/* 128 */       this.log.info("Start the job at once!\n\tSite-->" + this.para.getSite().getName());
/*     */     } else {
/* 130 */       this.log.info(String.valueOf(getSleepLogString(sleepMilliSeconds)) + " before first job of today!\n\tSite-->" + this.para.getSite().getName());
/* 131 */     }  this.todayRunCount = 0;
/*     */     while (true) {
/*     */       try {
/* 134 */         sleep(sleepMilliSeconds);
/* 135 */       } catch (InterruptedException e) {
/* 136 */         if (isManualJob()) {
/* 137 */           this.log.info("Start job manually!\n\tSite-->" + this.para.getSite().getName() + "\n\tNow time-->" + new Date());
/* 138 */           doJob();
/* 139 */           sleepMilliSeconds = getSleepAgainMilliSecond();
/* 140 */           this.log.info("Finish job manually!" + getSleepLogString(sleepMilliSeconds) + "!\n\tSite-->" + this.para.getSite().getName());
/*     */         } 
/* 142 */         if (isResetJob()) {
/* 143 */           this.log.info("Parameter has been changed,reset job\n\tSite-->" + this.para.getSite().getName() + "\n\tNow time-->" + new Date());
/* 144 */           this.todayRunCount = 0;
/* 145 */           this.lastJobTime = null;
/* 146 */           sleepMilliSeconds = getSleepMilliSecond();
/* 147 */           if (sleepMilliSeconds == 0L) {
/* 148 */             this.log.info("Start the job at once!\n\tSite-->" + this.para.getSite().getName());
/*     */           } else {
/* 150 */             this.log.info(String.valueOf(getSleepLogString(sleepMilliSeconds)) + " before first job of today!\n\tSite-->" + this.para.getSite().getName());
/*     */           } 
/*     */         } 
/* 153 */       }  if (isJobFinish()) {
/* 154 */         this.log.info("Terminate Job!\n\tSite-->" + this.para.getSite().getName() + "\n\tNow time-->" + new Date());
/*     */         return;
/*     */       } 
/* 157 */       if (!isManualJob() && !isResetJob()) {
/* 158 */         this.todayRunCount++;
/* 159 */         this.lastJobTime = new Date();
/* 160 */         this.log.info("Doing Job!\n\tSite-->" + this.para.getSite().getName() + "\n\tNow time-->" + new Date() + "\n\tToday count-->" + this.todayRunCount);
/*     */         
/* 162 */         doJob();
/*     */         
/* 164 */         sleepMilliSeconds = 5000L;
/* 165 */         if (this.todayRunCount == 0)
/* 166 */           this.log.info("Today's job has been finished!\n\tSite-->" + this.para.getSite().getName()); 
/* 167 */         this.log.info(String.valueOf(getSleepLogString(sleepMilliSeconds)) + "!\n\tSite-->" + this.para.getSite().getName()); continue;
/*     */       } 
/* 169 */       setManualJob(false);
/* 170 */       setResetJob(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void doJob() {
/* 177 */     if (this.para.getServerAddress() == null) {
/* 178 */       this.log.info("FTP Server of site(" + this.para.getSite().getName() + ") is unknown,terminate job!");
/*     */       return;
/*     */     } 
/* 181 */     Map<Object, Object> context = new HashMap<Object, Object>();
/* 182 */     doExportJob(context);
/* 183 */     doImportJob(context);
/* 184 */     if (this.para.getSuccEmail() != null) {
/*     */       try {
/* 186 */         this.emailManager.insertEmail(this.para.getSite(), this.para.getSuccEmail(), "DataTransfer.vm", context);
/* 187 */       } catch (Exception e) {
/* 188 */         this.log.error("Error send data transfer email.", e);
/* 189 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void doExportJob(Map context) {
/* 195 */     boolean successDepartment = exportDepartment(context);
/* 196 */     if (successDepartment) {
/* 197 */       exportExpense(context);
/*     */     }
/* 199 */     if (exportCountry(context) && 
/* 200 */       exportSupplier(context) && 
/* 201 */       successDepartment && 
/* 202 */       exportPurchaseOrder(context)) {
/* 203 */       exportPurchaseOrderReceipt(context);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void doImportJob(Map context) {
/* 210 */     importPurchaseType(context);
/* 211 */     importCustomer(context);
/* 212 */     importExchangeRate(context);
/* 213 */     importExpenseCategory(context);
/*     */   }
/*     */   
/*     */   private boolean exportCountry(Map<String, String> context) {
/*     */     try {
/* 218 */       context.put("startTimeCountry", getNowTime());
/* 219 */       this.manager.exportCountry(this.para);
/* 220 */     } catch (Exception e) {
/* 221 */       this.log.info("Error occurs while exporting country.\nThe reason is:" + e.toString());
/* 222 */       e.printStackTrace();
/* 223 */       context.put("errorMessageCountry", e.toString());
/* 224 */       context.put("errorMessageSupplier", "can't export supplier for failing in exporting country");
/* 225 */       context.put("errorMessagePO", "can't export PO for failing in exporting country");
/* 226 */       context.put("errorMessagePOReceipt", "can't export PO Receipt for failing in exporting country");
/* 227 */       return false;
/*     */     } 
/* 229 */     return true;
/*     */   }
/*     */   
/*     */   private boolean exportDepartment(Map<String, String> context) {
/*     */     try {
/* 234 */       context.put("startTimeDepartment", getNowTime());
/* 235 */       this.manager.exportDepartment(this.para);
/* 236 */     } catch (Exception e) {
/* 237 */       this.log.info("Error occurs while exporting department.\nThe reason is:" + e.toString());
/* 238 */       e.printStackTrace();
/* 239 */       context.put("errorMessageDepartment", e.toString());
/* 240 */       context.put("errorMessageExpense", "can't export expense for failing in exporting department");
/* 241 */       context.put("errorMessagePO", "can't export PO for failing in exporting department");
/* 242 */       context.put("errorMessagePOReceipt", "can't export PO Receipt for failing in exporting department");
/* 243 */       return false;
/*     */     } 
/* 245 */     return true;
/*     */   }
/*     */   
/*     */   private boolean exportExpense(Map<String, String> context) {
/*     */     try {
/* 250 */       context.put("startTimeExpense", getNowTime());
/* 251 */       this.manager.exportExpense(this.para);
/* 252 */     } catch (Exception e) {
/* 253 */       this.log.info("Error occurs while exporting expense.\nThe reason is:" + e.toString());
/* 254 */       e.printStackTrace();
/* 255 */       context.put("errorMessageExpense", e.toString());
/* 256 */       return false;
/*     */     } 
/* 258 */     return true;
/*     */   }
/*     */   
/*     */   private boolean exportPurchaseOrder(Map<String, String> context) {
/*     */     try {
/* 263 */       context.put("startTimePO", getNowTime());
/* 264 */       this.manager.exportPurchaseOrder(this.para);
/* 265 */     } catch (Exception e) {
/* 266 */       this.log.info("Error occurs while exporting purchase order.\nThe reason is:" + e.toString());
/* 267 */       e.printStackTrace();
/* 268 */       context.put("errorMessagePO", e.toString());
/* 269 */       context.put("errorMessagePOReceipt", "can't export PO Receipt for failing in exporting PO");
/* 270 */       return false;
/*     */     } 
/* 272 */     return true;
/*     */   }
/*     */   
/*     */   private boolean exportPurchaseOrderReceipt(Map<String, String> context) {
/*     */     try {
/* 277 */       context.put("startTimePOReceipt", getNowTime());
/* 278 */       this.manager.exportPurchaseOrderReceipt(this.para);
/* 279 */     } catch (Exception e) {
/* 280 */       this.log.info("Error occurs while exporting purchase order receipt.\nThe reason is:" + e.toString());
/* 281 */       e.printStackTrace();
/* 282 */       context.put("errorMessagePOReceipt", e.toString());
/* 283 */       return false;
/*     */     } 
/* 285 */     return true;
/*     */   }
/*     */   
/*     */   private boolean exportSupplier(Map<String, String> context) {
/*     */     try {
/* 290 */       context.put("startTimeSupplier", getNowTime());
/* 291 */       this.manager.exportSupplier(this.para);
/* 292 */     } catch (Exception e) {
/* 293 */       this.log.info("Error occurs while exporting supplier.\nThe reason is:" + e.toString());
/* 294 */       e.printStackTrace();
/* 295 */       context.put("errorMessageSupplier", e.toString());
/* 296 */       context.put("errorMessagePO", "can't export PO for failing in exporting supplier");
/* 297 */       context.put("errorMessagePOReceipt", "can't export PO Receipt for failing in exporting supplier");
/* 298 */       return false;
/*     */     } 
/* 300 */     return true;
/*     */   }
/*     */   
/*     */   private void importPurchaseType(Map<String, String> context) {
/*     */     try {
/* 305 */       context.put("startTimePurchaseType", getNowTime());
/* 306 */       this.manager.importPurchaseType(this.para);
/* 307 */     } catch (Exception e) {
/* 308 */       this.log.info("Error occurs while importing purchase type.\nThe reason is:" + e.toString());
/* 309 */       e.printStackTrace();
/* 310 */       context.put("errorMessagePurchaseType", e.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void importCustomer(Map<String, String> context) {
/*     */     try {
/* 316 */       context.put("startTimeCustomer", getNowTime());
/* 317 */       this.manager.importCustomer(this.para);
/* 318 */     } catch (Exception e) {
/* 319 */       this.log.info("Error occurs while importing customer.\nThe reason is:" + e.toString());
/* 320 */       e.printStackTrace();
/* 321 */       context.put("errorMessageCustomer", e.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void importExchangeRate(Map<String, String> context) {
/*     */     try {
/* 327 */       context.put("startTimeExchangeRate", getNowTime());
/* 328 */       this.manager.importExchangeRate(this.para);
/* 329 */     } catch (Exception e) {
/* 330 */       this.log.info("Error occurs while importing exchangerate.\nThe reason is:" + e.toString());
/* 331 */       e.printStackTrace();
/* 332 */       context.put("errorMessageExchangeRate", e.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void importExpenseCategory(Map<String, String> context) {
/*     */     try {
/* 338 */       context.put("startTimeExpenseCategory", getNowTime());
/* 339 */       this.manager.importExpenseCategory(this.para);
/* 340 */     } catch (Exception e) {
/* 341 */       this.log.info("Error occurs while importing expense category.\nThe reason is:" + e.toString());
/* 342 */       e.printStackTrace();
/* 343 */       context.put("errorMessageExpenseCategory", e.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getNowTime() {
/* 348 */     DateFormatter emailDateFormatter = new DateFormatter(Date.class, "yyyy/MM/dd HH:MM:ss");
/* 349 */     return emailDateFormatter.format(new Date());
/*     */   }
/*     */   
/*     */   private long getSleepMilliSecond() {
/* 353 */     Date nowTime = new Date();
/* 354 */     Date startTime = getTodayStartTime(this.para.getStartTime());
/* 355 */     if (this.todayRunCount < this.para.getTimePerDay().intValue()) {
/* 356 */       if (this.todayRunCount == 0) {
/* 357 */         if (startTime.compareTo(nowTime) <= 0) {
/* 358 */           return 0L;
/*     */         }
/* 360 */         return startTime.getTime() - nowTime.getTime();
/*     */       } 
/*     */       
/* 363 */       if (this.para.getIntervalMin().longValue() * 60000L - nowTime.getTime() - this.lastJobTime.getTime() > 0L) {
/* 364 */         return this.para.getIntervalMin().longValue() * 60000L - nowTime.getTime() - this.lastJobTime.getTime();
/*     */       }
/* 366 */       return 0L;
/*     */     } 
/*     */     
/* 369 */     this.todayRunCount = 0;
/* 370 */     return startTime.getTime() - nowTime.getTime() + 86400000L;
/*     */   }
/*     */ 
/*     */   
/*     */   private long getSleepAgainMilliSecond() {
/* 375 */     Date nowTime = new Date();
/* 376 */     Date startTime = getTodayStartTime(this.para.getStartTime());
/* 377 */     if (this.todayRunCount != 0) {
/*     */       
/* 379 */       if (this.para.getIntervalMin().longValue() * 60000L - nowTime.getTime() - this.lastJobTime.getTime() > 0L) {
/* 380 */         return this.para.getIntervalMin().longValue() * 60000L - nowTime.getTime() - this.lastJobTime.getTime();
/*     */       }
/* 382 */       return 0L;
/*     */     } 
/*     */     
/* 385 */     if (this.lastJobTime == null) {
/*     */       
/* 387 */       if (startTime.compareTo(nowTime) <= 0) {
/* 388 */         return 0L;
/*     */       }
/* 390 */       return startTime.getTime() - nowTime.getTime();
/*     */     } 
/*     */     
/* 393 */     if (getDay(nowTime) == getDay(this.lastJobTime))
/*     */     {
/* 395 */       return startTime.getTime() - nowTime.getTime() + 86400000L;
/*     */     }
/*     */     
/* 398 */     return startTime.getTime() - nowTime.getTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getSleepLogString(long sleepMilliSeconds) {
/* 405 */     long hour = sleepMilliSeconds / 3600000L;
/* 406 */     long minute = (sleepMilliSeconds - hour * 3600000L) / 60000L;
/* 407 */     long second = (sleepMilliSeconds - hour * 3600000L - minute * 60000L) / 1000L;
/* 408 */     return "Sleep " + hour + " hours," + minute + " minutes," + second + " seconds";
/*     */   }
/*     */   
/*     */   private Date getTodayStartTime(Date startTime) {
/* 412 */     Date now = new Date();
/* 413 */     return new Date(now.getTime() / 86400000L * 86400000L + startTime.getTime() % 86400000L);
/*     */   }
/*     */   
/*     */   private long getDay(Date time) {
/* 417 */     return time.getTime() / 86400000L * 86400000L;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/DataTransferThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */