/*     */ package com.aof.web.struts.action.schedule;
/*     */ 
/*     */ import com.aof.model.schedule.Production72HourPlan;
/*     */ import com.aof.model.schedule.query.Production72HourPlanQueryOrder;
/*     */ import com.aof.service.schedule.Production72HourPlanManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.schedule.Production72HourPlanQueryForm;
/*     */ import com.shcnc.utils.BeanUtils;
/*     */ import com.shcnc.utils.ExportUtil;
/*     */ import com.shcnc.utils.Exportable;
/*     */ import java.io.FileOutputStream;
/*     */ import java.net.URLEncoder;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class Production72HourPlanAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  37 */     Production72HourPlanQueryForm queryForm = (Production72HourPlanQueryForm)form;
/*  38 */     Production72HourPlanManager manager = ServiceLocator.getProduction72HourPlanManager(request);
/*  39 */     Map conditions = getConditions(queryForm);
/*  40 */     if (queryForm.getOrder() == "") {
/*  41 */       queryForm.setDescend(true);
/*     */     }
/*  43 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  44 */     String exportType = queryForm.getExportType();
/*  45 */     if (exportType != null && exportType.length() > 0) {
/*  46 */       List datas = manager.getList(conditions, 0, -1, 
/*  47 */           Production72HourPlanQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  48 */       int index = SessionTempFile.createNewTempFile(request);
/*  49 */       String fileName = "RiJiHua";
/*  50 */       String suffix = ExportUtil.export(exportType, datas, request, 
/*  51 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/*  52 */           new Exportable()
/*     */           {
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception
/*     */             {
/*  56 */               row.add(BeanUtils.getProperty(data, "carkind"));
/*  57 */               row.add(BeanUtils.getProperty(data, "asn"));
/*  58 */               row.add(BeanUtils.getProperty(data, "m1"));
/*  59 */               row.add(BeanUtils.getProperty(data, "mouth"));
/*  60 */               row.add(BeanUtils.getProperty(data, "d1"));
/*  61 */               row.add(BeanUtils.getProperty(data, "pbs"));
/*  62 */               row.add(BeanUtils.getProperty(data, "prj"));
/*  63 */               row.add(BeanUtils.getProperty(data, "wbs"));
/*  64 */               row.add(BeanUtils.getProperty(data, "sum1"));
/*  65 */               row.add(BeanUtils.getProperty(data, "hourplan_day1"));
/*  66 */               row.add(BeanUtils.getProperty(data, "hourplan_day2"));
/*  67 */               row.add(BeanUtils.getProperty(data, "hourplan_day3"));
/*  68 */               row.add(BeanUtils.getProperty(data, "hourplan_day4"));
/*  69 */               row.add(BeanUtils.getProperty(data, "hourplan_day5"));
/*  70 */               row.add(BeanUtils.getProperty(data, "hourplan_day6"));
/*  71 */               row.add(BeanUtils.getProperty(data, "hourplan_day7"));
/*  72 */               row.add(BeanUtils.getProperty(data, "hourplan_day8"));
/*  73 */               row.add(BeanUtils.getProperty(data, "hourplan_day9"));
/*  74 */               row.add(BeanUtils.getProperty(data, "hourplan_day10"));
/*  75 */               row.add(BeanUtils.getProperty(data, "hourplan_day11"));
/*  76 */               row.add(BeanUtils.getProperty(data, "hourplan_day12"));
/*  77 */               row.add(BeanUtils.getProperty(data, "hourplan_day13"));
/*  78 */               row.add(BeanUtils.getProperty(data, "hourplan_day14"));
/*  79 */               row.add(BeanUtils.getProperty(data, "hourplan_day15"));
/*  80 */               row.add(BeanUtils.getProperty(data, "hourplan_day16"));
/*  81 */               row.add(BeanUtils.getProperty(data, "hourplan_day17"));
/*  82 */               row.add(BeanUtils.getProperty(data, "hourplan_day18"));
/*  83 */               row.add(BeanUtils.getProperty(data, "hourplan_day19"));
/*  84 */               row.add(BeanUtils.getProperty(data, "hourplan_day20"));
/*  85 */               row.add(BeanUtils.getProperty(data, "hourplan_day21"));
/*  86 */               row.add(BeanUtils.getProperty(data, "hourplan_day22"));
/*  87 */               row.add(BeanUtils.getProperty(data, "hourplan_day23"));
/*  88 */               row.add(BeanUtils.getProperty(data, "hourplan_day24"));
/*  89 */               row.add(BeanUtils.getProperty(data, "hourplan_day25"));
/*  90 */               row.add(BeanUtils.getProperty(data, "hourplan_day26"));
/*  91 */               row.add(BeanUtils.getProperty(data, "hourplan_day27"));
/*  92 */               row.add(BeanUtils.getProperty(data, "hourplan_day28"));
/*  93 */               row.add(BeanUtils.getProperty(data, "hourplan_day29"));
/*  94 */               row.add(BeanUtils.getProperty(data, "hourplan_day30"));
/*  95 */               row.add(BeanUtils.getProperty(data, "hourplan_day31"));
/*  96 */               row.add(BeanUtils.getProperty(data, "hourplan_day32"));
/*  97 */               row.add(BeanUtils.getProperty(data, "hourplan_day33"));
/*  98 */               row.add(BeanUtils.getProperty(data, "hourplan_day34"));
/*  99 */               row.add(BeanUtils.getProperty(data, "hourplan_day35"));
/* 100 */               row.add(BeanUtils.getProperty(data, "hourplan_day36"));
/* 101 */               row.add(BeanUtils.getProperty(data, "hourplan_day37"));
/* 102 */               row.add(BeanUtils.getProperty(data, "hourplan_day38"));
/* 103 */               row.add(BeanUtils.getProperty(data, "hourplan_day39"));
/* 104 */               row.add(BeanUtils.getProperty(data, "hourplan_day40"));
/* 105 */               row.add(BeanUtils.getProperty(data, "hourplan_day41"));
/* 106 */               row.add(BeanUtils.getProperty(data, "hourplan_day42"));
/* 107 */               row.add(BeanUtils.getProperty(data, "hourplan_day43"));
/* 108 */               row.add(BeanUtils.getProperty(data, "hourplan_day44"));
/* 109 */               row.add(BeanUtils.getProperty(data, "hourplan_day45"));
/* 110 */               row.add(BeanUtils.getProperty(data, "hourplan_day46"));
/* 111 */               row.add(BeanUtils.getProperty(data, "hourplan_day47"));
/* 112 */               row.add(BeanUtils.getProperty(data, "hourplan_day48"));
/* 113 */               row.add(BeanUtils.getProperty(data, "hourplan_day49"));
/* 114 */               row.add(BeanUtils.getProperty(data, "hourplan_day50"));
/* 115 */               row.add(BeanUtils.getProperty(data, "hourplan_day51"));
/* 116 */               row.add(BeanUtils.getProperty(data, "hourplan_day52"));
/* 117 */               row.add(BeanUtils.getProperty(data, "hourplan_day53"));
/* 118 */               row.add(BeanUtils.getProperty(data, "hourplan_day54"));
/* 119 */               row.add(BeanUtils.getProperty(data, "hourplan_day55"));
/* 120 */               row.add(BeanUtils.getProperty(data, "hourplan_day56"));
/* 121 */               row.add(BeanUtils.getProperty(data, "hourplan_day57"));
/* 122 */               row.add(BeanUtils.getProperty(data, "hourplan_day58"));
/* 123 */               row.add(BeanUtils.getProperty(data, "hourplan_day59"));
/* 124 */               row.add(BeanUtils.getProperty(data, "hourplan_day60"));
/* 125 */               row.add(BeanUtils.getProperty(data, "hourplan_day61"));
/* 126 */               row.add(BeanUtils.getProperty(data, "hourplan_day62"));
/* 127 */               row.add(BeanUtils.getProperty(data, "hourplan_day63"));
/* 128 */               row.add(BeanUtils.getProperty(data, "hourplan_day64"));
/* 129 */               row.add(BeanUtils.getProperty(data, "hourplan_day65"));
/* 130 */               row.add(BeanUtils.getProperty(data, "rem"));
/* 131 */               row.add(BeanUtils.getProperty(data, "sum"));
/* 132 */               row.add(BeanUtils.getProperty(data, "weitou"));
/* 133 */               row.add(BeanUtils.getProperty(data, "lco"));
/* 134 */               row.add(BeanUtils.getProperty(data, "lcx"));
/* 135 */               row.add(BeanUtils.getProperty(data, "hourAll"));
/*     */             }
/*     */             
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 139 */               MessageResources message = Production72HourPlanAction.this.getResources(request);
/* 140 */               List<String> list = new ArrayList<String>();
/* 141 */               Date date = new Date();
/* 142 */               SimpleDateFormat format = new SimpleDateFormat("dd");
/* 143 */               SimpleDateFormat HH = new SimpleDateFormat("HH");
/* 144 */               String weekD = "";
/* 145 */               row.add("车种");
/* 146 */               row.add("值");
/* 147 */               row.add("M-1");
/* 148 */               row.add("当月");
/* 149 */               row.add("D-1");
/* 150 */               row.add("PBS");
/* 151 */               row.add("PRJ");
/* 152 */               row.add("WBS");
/* 153 */               row.add(String.valueOf(format.format(date)) + "SUM");
/*     */               
/* 155 */               for (int i = 0; i < 5; i++) {
/* 156 */                 Calendar cal = Calendar.getInstance();
/* 157 */                 cal.setTime(date);
/* 158 */                 cal.add(5, i);
/* 159 */                 int weekDay = cal.get(7);
/* 160 */                 if (weekDay - 1 == 1) {
/* 161 */                   weekD = "一";
/* 162 */                 } else if (weekDay - 1 == 2) {
/* 163 */                   weekD = "二";
/* 164 */                 } else if (weekDay - 1 == 3) {
/* 165 */                   weekD = "三";
/* 166 */                 } else if (weekDay - 1 == 4) {
/* 167 */                   weekD = "四";
/* 168 */                 } else if (weekDay - 1 == 5) {
/* 169 */                   weekD = "五";
/* 170 */                 } else if (weekDay - 1 == 6) {
/* 171 */                   weekD = "六";
/*     */                 } else {
/* 173 */                   weekD = "日";
/*     */                 } 
/* 175 */                 list.add(String.valueOf(format.format(cal.getTime())) + weekD);
/* 176 */                 for (int j = 0; j < 12; j++) {
/* 177 */                   if (j == 0) {
/* 178 */                     list.add("06~07");
/* 179 */                   } else if (j == 1) {
/* 180 */                     list.add("08~09");
/* 181 */                   } else if (j == 2) {
/* 182 */                     list.add("10~11");
/* 183 */                   } else if (j == 3) {
/* 184 */                     list.add("12~13");
/* 185 */                   } else if (j == 4) {
/* 186 */                     list.add("14~15");
/* 187 */                   } else if (j == 5) {
/* 188 */                     list.add("16~17");
/* 189 */                   } else if (j == 6) {
/* 190 */                     list.add("18~19");
/* 191 */                   } else if (j == 7) {
/* 192 */                     list.add("20~21");
/* 193 */                   } else if (j == 8) {
/* 194 */                     list.add("22~23");
/* 195 */                   } else if (j == 9) {
/* 196 */                     list.add("00~01");
/* 197 */                   } else if (j == 10) {
/* 198 */                     list.add("02~03");
/* 199 */                   } else if (j == 11) {
/* 200 */                     list.add("04~05");
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */               
/* 205 */               for (String head : list) {
/* 206 */                 row.add(head);
/*     */               }
/* 208 */               row.add("REM");
/* 209 */               row.add("合计查询");
/* 210 */               row.add("未投");
/* 211 */               row.add("LC(O)");
/* 212 */               row.add("LC(X)");
/* 213 */               row.add("总计");
/*     */             }
/*     */           });
/* 216 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/* 218 */     if (queryForm.isFirstInit()) {
/* 219 */       queryForm.init(manager.getListCount(conditions).intValue());
/*     */     } else {
/* 221 */       queryForm.init();
/*     */     } 
/*     */     try {
/* 224 */       List<Production72HourPlan> planList = manager.getList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), Production72HourPlanQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 225 */       calculatedByDateHH(request);
/* 226 */       request.setAttribute("X_RESULTLIST", planList);
/* 227 */       request.setAttribute("x_selType", Integer.valueOf(176));
/* 228 */       return mapping.findForward("page");
/* 229 */     } catch (Exception e) {
/*     */       
/* 231 */       e.printStackTrace();
/*     */       
/* 233 */       return null;
/*     */     } 
/*     */   } private Map getConditions(Production72HourPlanQueryForm formBean) {
/* 236 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 237 */     String str = "";
/*     */     
/* 239 */     return conditions;
/*     */   }
/*     */   
/*     */   private void calculatedByDateHH(HttpServletRequest request) {
/* 243 */     List<String> list = new ArrayList<String>();
/* 244 */     Date date = new Date();
/* 245 */     SimpleDateFormat format = new SimpleDateFormat("dd");
/* 246 */     SimpleDateFormat HH = new SimpleDateFormat("HH");
/* 247 */     String weekD = "";
/* 248 */     for (int i = 0; i < 5; i++) {
/* 249 */       Calendar cal = Calendar.getInstance();
/* 250 */       cal.setTime(date);
/* 251 */       cal.add(5, i);
/* 252 */       int weekDay = cal.get(7);
/* 253 */       if (weekDay - 1 == 1) {
/* 254 */         weekD = "一";
/* 255 */       } else if (weekDay - 1 == 2) {
/* 256 */         weekD = "二";
/* 257 */       } else if (weekDay - 1 == 3) {
/* 258 */         weekD = "三";
/* 259 */       } else if (weekDay - 1 == 4) {
/* 260 */         weekD = "四";
/* 261 */       } else if (weekDay - 1 == 5) {
/* 262 */         weekD = "五";
/* 263 */       } else if (weekDay - 1 == 6) {
/* 264 */         weekD = "六";
/*     */       } else {
/* 266 */         weekD = "日";
/*     */       } 
/* 268 */       list.add(String.valueOf(format.format(cal.getTime())) + weekD);
/* 269 */       for (int j = 0; j < 12; j++) {
/* 270 */         if (j == 0) {
/* 271 */           list.add("06~07");
/* 272 */         } else if (j == 1) {
/* 273 */           list.add("08~09");
/* 274 */         } else if (j == 2) {
/* 275 */           list.add("10~11");
/* 276 */         } else if (j == 3) {
/* 277 */           list.add("12~13");
/* 278 */         } else if (j == 4) {
/* 279 */           list.add("14~15");
/* 280 */         } else if (j == 5) {
/* 281 */           list.add("16~17");
/* 282 */         } else if (j == 6) {
/* 283 */           list.add("18~19");
/* 284 */         } else if (j == 7) {
/* 285 */           list.add("20~21");
/* 286 */         } else if (j == 8) {
/* 287 */           list.add("22~23");
/* 288 */         } else if (j == 9) {
/* 289 */           list.add("00~01");
/* 290 */         } else if (j == 10) {
/* 291 */           list.add("02~03");
/* 292 */         } else if (j == 11) {
/* 293 */           list.add("04~05");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 298 */     request.setAttribute("X_DATELIST", list);
/* 299 */     request.setAttribute("X_DD", format.format(date));
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/schedule/Production72HourPlanAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */