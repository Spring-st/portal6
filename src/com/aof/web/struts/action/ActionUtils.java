/*     */ package com.aof.web.struts.action;
/*     */ 
/*     */ import com.shcnc.struts.action.ActionException;
/*     */ import com.shcnc.struts.action.ActionUtils;
/*     */ import com.shcnc.struts.form.DateFormatter;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ActionUtils
/*     */   extends ActionUtils
/*     */ {
/*  30 */   private static DateFormatter dfDisplayDate = new DateFormatter(
/*  31 */       Date.class, "yyyy/MM/dd");
/*  32 */   private static DateFormatter dfDisplayDateTime = new DateFormatter(
/*  33 */       Date.class, "yyyy/MM/dd HH:mm");
/*  34 */   private static DateFormatter df8CharsDate = new DateFormatter(
/*  35 */       Date.class, "yyyyMMdd");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getTodayAs8Chars() {
/*  42 */     return get8CharsFromDate(new Date());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getTodayAsDisplayDate() {
/*  50 */     return getDisplayDateFromDate(new Date());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDisplayDateFrom8Chars(String date) {
/*  58 */     return String.valueOf(date.substring(0, 4)) + "/" + date.substring(4, 6) + "/" + 
/*  59 */       date.substring(6, 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String get8CharsFromDisplayDate(String date) {
/*  67 */     return String.valueOf(date.substring(0, 4)) + date.substring(5, 7) + 
/*  68 */       date.substring(8, 10);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String get8CharsFromDate(Date date) {
/*  76 */     return df8CharsDate.format(date);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Date getDateFrom8Chars(String s) {
/*  84 */     if (s.length() != 8) {
/*  85 */       throw new RuntimeException("date error");
/*     */     }
/*  87 */     return (Date)df8CharsDate.unformat(s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Date getDateFromDisplayDate(String s) {
/*  95 */     if (s.length() != 10) {
/*  96 */       throw new RuntimeException("date error");
/*     */     }
/*  98 */     return (Date)dfDisplayDate.unformat(s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDisplayDateFromDate(Date date) {
/* 106 */     return dfDisplayDate.format(date);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDisplayDateFromDateTime(Date date) {
/* 113 */     return dfDisplayDateTime.format(date);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Date getQueryBeginDateFromDisplayDate(String s) {
/* 121 */     Date date = getDateFromDisplayDate(s);
/* 122 */     Calendar calendar = Calendar.getInstance();
/* 123 */     calendar.setTime(date);
/* 124 */     calendar.set(11, 0);
/* 125 */     calendar.set(12, 0);
/* 126 */     calendar.set(13, 0);
/* 127 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Date getQueryToDateFromDisplayDate(String s) {
/* 135 */     Date date = getDateFromDisplayDate(s);
/* 136 */     Calendar calendar = Calendar.getInstance();
/* 137 */     calendar.setTime(date);
/* 138 */     calendar.set(11, 23);
/* 139 */     calendar.set(12, 59);
/* 140 */     calendar.set(13, 59);
/* 141 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Date getStartDate(Date date) {
/* 149 */     Calendar calendar = Calendar.getInstance();
/* 150 */     calendar.setTime(date);
/* 151 */     calendar.set(11, 0);
/* 152 */     calendar.set(12, 0);
/* 153 */     calendar.set(13, 0);
/* 154 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Date getExpireDate(Date date) {
/* 162 */     Calendar calendar = Calendar.getInstance();
/* 163 */     calendar.setTime(date);
/* 164 */     calendar.set(11, 23);
/* 165 */     calendar.set(12, 59);
/* 166 */     calendar.set(13, 59);
/* 167 */     return calendar.getTime();
/*     */   }
/*     */   
/*     */   public static BigDecimal parseBigDecimal(String amount) {
/* 171 */     if (amount == null) return null; 
/*     */     try {
/* 173 */       return new BigDecimal(amount);
/*     */     }
/* 175 */     catch (Throwable t) {
/*     */       
/* 177 */       return null;
/*     */     } 
/*     */   }
/*     */   public static String savaFile(InputStream inputStream) {
/* 181 */     DateFormatter format = new DateFormatter(Date.class, 
/* 182 */         "yyyy/MM/dd/");
/* 183 */     String path = format.format(new Date());
/* 184 */     if (emailProperty == null) {
/* 185 */       ActionUtils au = new ActionUtils();
/* 186 */       au.init();
/*     */     } 
/* 188 */     String path2 = emailProperty.getProperty("path");
/* 189 */     File f = new File(String.valueOf(path2) + path);
/* 190 */     if (!f.exists()) {
/* 191 */       f.mkdirs();
/*     */     }
/* 193 */     format = new DateFormatter(Date.class, "HHmmssSS");
/* 194 */     String name = format.format(new Date());
/*     */     try {
/* 196 */       f = new File(String.valueOf(path2) + path + name);
/* 197 */       while (f.exists()) {
/* 198 */         name = format.format(new Date());
/* 199 */         f = new File(String.valueOf(path2) + path + name);
/*     */       } 
/* 201 */       OutputStream os = new FileOutputStream(f);
/* 202 */       int bytesRead = 0;
/* 203 */       byte[] buffer = new byte[8192];
/* 204 */       while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
/* 205 */         os.write(buffer, 0, bytesRead);
/*     */       }
/* 207 */       os.close();
/* 208 */     } catch (Exception e) {
/* 209 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 212 */     return String.valueOf(path) + name;
/*     */   }
/*     */   public static InputStream getFile(String path) {
/* 215 */     if (emailProperty == null) {
/* 216 */       ActionUtils au = new ActionUtils();
/* 217 */       au.init();
/*     */     } 
/* 219 */     String path2 = emailProperty.getProperty("path");
/* 220 */     File f = new File(String.valueOf(path2) + path);
/* 221 */     if (!f.exists()) {
/* 222 */       throw new ActionException(
/* 223 */           "purchaseOrderAttachment.error.fileSize.zero");
/*     */     }
/* 225 */     InputStream in = null;
/*     */     try {
/* 227 */       in = new FileInputStream(f);
/* 228 */     } catch (FileNotFoundException e) {
/* 229 */       e.printStackTrace();
/*     */     } 
/* 231 */     return in;
/*     */   }
/* 233 */   private static Properties emailProperty = null;
/*     */   
/*     */   public void init() {
/* 236 */     InputStream is = getClass().getResourceAsStream(
/* 237 */         "/file.properties");
/* 238 */     emailProperty = new Properties();
/*     */     try {
/* 240 */       emailProperty.load(is);
/* 241 */     } catch (IOException e) {
/* 242 */       throw new RuntimeException(e);
/*     */     } finally {
/*     */       try {
/* 245 */         is.close();
/* 246 */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/ActionUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */