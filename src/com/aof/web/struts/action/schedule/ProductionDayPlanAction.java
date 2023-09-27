/*     */ package com.aof.web.struts.action.schedule;
/*     */ 
/*     */ import com.aof.model.schedule.ProductionDayPlan;
/*     */ import com.aof.model.schedule.query.ProductionDayPlanQueryOrder;
/*     */ import com.aof.service.schedule.ProductionDayPlanManager;
/*     */ import com.aof.utils.SessionTempFile;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*     */ import com.aof.web.struts.form.schedule.ProductionDayPlanQueryForm;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProductionDayPlanAction
/*     */   extends BaseAction2
/*     */ {
/*     */   public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/*  40 */     ProductionDayPlanManager planManager = ServiceLocator.getProductionDayPlanManager(request);
/*  41 */     ProductionDayPlanQueryForm queryForm = (ProductionDayPlanQueryForm)form;
/*  42 */     Map conditions = getConditions(queryForm);
/*  43 */     if (queryForm.getOrder() == "") {
/*  44 */       queryForm.setDescend(true);
/*     */     }
/*  46 */     getConditionAndOrder((BaseSessionQueryForm)queryForm, conditions, request);
/*  47 */     String exportType = queryForm.getExportType();
/*  48 */     if (exportType != null && exportType.length() > 0) {
/*  49 */       List datas = planManager.getList(conditions, 0, -1, 
/*  50 */           ProductionDayPlanQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/*  51 */       int index = SessionTempFile.createNewTempFile(request);
/*  52 */       String fileName = "RiJiHua";
/*  53 */       String suffix = ExportUtil.export(exportType, datas, request, 
/*  54 */           new FileOutputStream(SessionTempFile.getTempFile(index, request)), 
/*  55 */           new Exportable()
/*     */           {
/*     */             public void exportRow(List row, Object data, HttpServletRequest request) throws Exception
/*     */             {
/*  59 */               row.add(BeanUtils.getProperty(data, "carkind"));
/*  60 */               row.add(BeanUtils.getProperty(data, "asnNo"));
/*  61 */               row.add(BeanUtils.getProperty(data, "planM1"));
/*  62 */               row.add(BeanUtils.getProperty(data, "planMouth"));
/*  63 */               row.add(BeanUtils.getProperty(data, "planD1"));
/*  64 */               row.add(BeanUtils.getProperty(data, "planPbs"));
/*  65 */               row.add(BeanUtils.getProperty(data, "planPrj"));
/*  66 */               row.add(BeanUtils.getProperty(data, "planWbs"));
/*  67 */               row.add(BeanUtils.getProperty(data, "planSum1"));
/*  68 */               row.add(BeanUtils.getProperty(data, "day1"));
/*  69 */               row.add(BeanUtils.getProperty(data, "day2"));
/*  70 */               row.add(BeanUtils.getProperty(data, "day3"));
/*  71 */               row.add(BeanUtils.getProperty(data, "day4"));
/*  72 */               row.add(BeanUtils.getProperty(data, "day5"));
/*  73 */               row.add(BeanUtils.getProperty(data, "day6"));
/*  74 */               row.add(BeanUtils.getProperty(data, "day7"));
/*  75 */               row.add(BeanUtils.getProperty(data, "day8"));
/*  76 */               row.add(BeanUtils.getProperty(data, "day9"));
/*  77 */               row.add(BeanUtils.getProperty(data, "day10"));
/*  78 */               row.add(BeanUtils.getProperty(data, "day11"));
/*  79 */               row.add(BeanUtils.getProperty(data, "day12"));
/*  80 */               row.add(BeanUtils.getProperty(data, "day13"));
/*  81 */               row.add(BeanUtils.getProperty(data, "day14"));
/*  82 */               row.add(BeanUtils.getProperty(data, "day15"));
/*  83 */               row.add(BeanUtils.getProperty(data, "day16"));
/*  84 */               row.add(BeanUtils.getProperty(data, "day17"));
/*  85 */               row.add(BeanUtils.getProperty(data, "day18"));
/*  86 */               row.add(BeanUtils.getProperty(data, "day19"));
/*  87 */               row.add(BeanUtils.getProperty(data, "day20"));
/*  88 */               row.add(BeanUtils.getProperty(data, "day21"));
/*  89 */               row.add(BeanUtils.getProperty(data, "day22"));
/*  90 */               row.add(BeanUtils.getProperty(data, "day23"));
/*  91 */               row.add(BeanUtils.getProperty(data, "day24"));
/*  92 */               row.add(BeanUtils.getProperty(data, "day25"));
/*  93 */               row.add(BeanUtils.getProperty(data, "day26"));
/*  94 */               row.add(BeanUtils.getProperty(data, "day27"));
/*  95 */               row.add(BeanUtils.getProperty(data, "day28"));
/*  96 */               row.add(BeanUtils.getProperty(data, "day29"));
/*  97 */               row.add(BeanUtils.getProperty(data, "day30"));
/*  98 */               row.add(BeanUtils.getProperty(data, "day31"));
/*  99 */               row.add(BeanUtils.getProperty(data, "day32"));
/* 100 */               row.add(BeanUtils.getProperty(data, "day33"));
/* 101 */               row.add(BeanUtils.getProperty(data, "day34"));
/* 102 */               row.add(BeanUtils.getProperty(data, "day35"));
/* 103 */               row.add(BeanUtils.getProperty(data, "day36"));
/* 104 */               row.add(BeanUtils.getProperty(data, "day37"));
/* 105 */               row.add(BeanUtils.getProperty(data, "day38"));
/* 106 */               row.add(BeanUtils.getProperty(data, "day39"));
/* 107 */               row.add(BeanUtils.getProperty(data, "day40"));
/* 108 */               row.add(BeanUtils.getProperty(data, "day41"));
/* 109 */               row.add(BeanUtils.getProperty(data, "day42"));
/* 110 */               row.add(BeanUtils.getProperty(data, "day43"));
/* 111 */               row.add(BeanUtils.getProperty(data, "day44"));
/* 112 */               row.add(BeanUtils.getProperty(data, "day45"));
/* 113 */               row.add(BeanUtils.getProperty(data, "planRem"));
/* 114 */               row.add(BeanUtils.getProperty(data, "planSum"));
/* 115 */               row.add(BeanUtils.getProperty(data, "planWeitou"));
/* 116 */               row.add(BeanUtils.getProperty(data, "planLco"));
/* 117 */               row.add(BeanUtils.getProperty(data, "planLcx"));
/* 118 */               row.add(BeanUtils.getProperty(data, "planAll"));
/*     */             }
/*     */             
/*     */             public void exportHead(List row, HttpServletRequest request) throws Exception {
/* 122 */               MessageResources message = ProductionDayPlanAction.this.getResources(request);
/* 123 */               List<String> list = new ArrayList<String>();
/* 124 */               Date date = new Date();
/* 125 */               SimpleDateFormat format = new SimpleDateFormat("dd");
/* 126 */               String weekD = "";
/* 127 */               row.add("车种");
/* 128 */               row.add("值");
/* 129 */               row.add("M-1");
/* 130 */               row.add("当月");
/* 131 */               row.add("D-1");
/* 132 */               row.add("PBS");
/* 133 */               row.add("PRJ");
/* 134 */               row.add("WBS");
/* 135 */               row.add(String.valueOf(format.format(date)) + "SUM");
/* 136 */               for (int i = 0; i < 45; i++) {
/* 137 */                 Calendar cal = Calendar.getInstance();
/* 138 */                 cal.setTime(date);
/* 139 */                 cal.add(5, i);
/* 140 */                 int weekDay = cal.get(7);
/* 141 */                 if (weekDay - 1 == 1) {
/* 142 */                   weekD = "一";
/* 143 */                 } else if (weekDay - 1 == 2) {
/* 144 */                   weekD = "二";
/* 145 */                 } else if (weekDay - 1 == 3) {
/* 146 */                   weekD = "三";
/* 147 */                 } else if (weekDay - 1 == 4) {
/* 148 */                   weekD = "四";
/* 149 */                 } else if (weekDay - 1 == 5) {
/* 150 */                   weekD = "五";
/* 151 */                 } else if (weekDay - 1 == 6) {
/* 152 */                   weekD = "六";
/*     */                 } else {
/* 154 */                   weekD = "日";
/*     */                 } 
/* 156 */                 list.add(String.valueOf(format.format(cal.getTime())) + weekD);
/*     */               } 
/* 158 */               for (String head : list) {
/* 159 */                 row.add(head);
/*     */               }
/* 161 */               row.add("REM");
/* 162 */               row.add("合计查询");
/* 163 */               row.add("未投");
/* 164 */               row.add("LC(O)");
/* 165 */               row.add("LC(X)");
/* 166 */               row.add("总计");
/*     */             }
/*     */           });
/* 169 */       return new ActionForward("download/" + index + "/" + URLEncoder.encode(fileName, "UTF-8") + "." + suffix, true);
/*     */     } 
/* 171 */     if (queryForm.isFirstInit()) {
/* 172 */       queryForm.init(planManager.getListCount(conditions).intValue());
/*     */     } else {
/* 174 */       queryForm.init();
/*     */     } 
/* 176 */     calculatedByDate(request);
/* 177 */     List<ProductionDayPlan> planList = planManager.getList(conditions, queryForm.getPageNoAsInt(), queryForm.getPageSizeAsInt(), ProductionDayPlanQueryOrder.getEnum(queryForm.getOrder()), queryForm.isDescend());
/* 178 */     request.setAttribute("X_RESULTLIST", planList);
/* 179 */     request.setAttribute("x_selType", Integer.valueOf(175));
/* 180 */     return mapping.findForward("page");
/*     */   }
/*     */   private Map getConditions(ProductionDayPlanQueryForm formBean) {
/* 183 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 184 */     String str = "";
/*     */     
/* 186 */     return conditions;
/*     */   }
/*     */   private void calculatedByDate(HttpServletRequest request) {
/* 189 */     List<String> list = new ArrayList<String>();
/* 190 */     Date date = new Date();
/* 191 */     SimpleDateFormat format = new SimpleDateFormat("dd");
/* 192 */     String weekD = "";
/* 193 */     for (int i = 0; i < 45; i++) {
/* 194 */       Calendar cal = Calendar.getInstance();
/* 195 */       cal.setTime(date);
/* 196 */       cal.add(5, i);
/* 197 */       int weekDay = cal.get(7);
/* 198 */       if (weekDay - 1 == 1) {
/* 199 */         weekD = "一";
/* 200 */       } else if (weekDay - 1 == 2) {
/* 201 */         weekD = "二";
/* 202 */       } else if (weekDay - 1 == 3) {
/* 203 */         weekD = "三";
/* 204 */       } else if (weekDay - 1 == 4) {
/* 205 */         weekD = "四";
/* 206 */       } else if (weekDay - 1 == 5) {
/* 207 */         weekD = "五";
/* 208 */       } else if (weekDay - 1 == 6) {
/* 209 */         weekD = "六";
/*     */       } else {
/* 211 */         weekD = "日";
/*     */       } 
/* 213 */       list.add(String.valueOf(format.format(cal.getTime())) + weekD);
/*     */     } 
/* 215 */     request.setAttribute("X_DATELIST", list);
/* 216 */     request.setAttribute("X_DD", format.format(date));
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/schedule/ProductionDayPlanAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */