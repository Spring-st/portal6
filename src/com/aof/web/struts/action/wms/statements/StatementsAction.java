/*     */ package com.aof.web.struts.action.wms.statements;
/*     */ 
/*     */ import com.aof.model.po.PurchaseOrderPutInStorage;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.service.po.PurchaseOrderPutInStorageManager;
/*     */ import com.aof.utils.XMLUtil;
/*     */ import com.aof.web.struts.action.BaseAction2;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import java.text.ParseException;
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
/*     */ import org.dom4j.Element;
/*     */ 
/*     */ public class StatementsAction
/*     */   extends BaseAction2
/*     */ {
/*     */   private int getMondayPlus(Date now) {
/*  28 */     Calendar cd = Calendar.getInstance();
/*     */     
/*  30 */     cd.setTime(now);
/*  31 */     int dayOfWeek = cd.get(7);
/*  32 */     if (dayOfWeek == 1) {
/*  33 */       return -6;
/*     */     }
/*  35 */     return 2 - dayOfWeek;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String[] convertWeekByDate(String time) {
/*  43 */     String[] date = new String[2];
/*  44 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
/*  45 */     Date now = null;
/*  46 */     if (time == null || time == "")
/*  47 */       time = sdf.format(Calendar.getInstance().getTime()); 
/*     */     try {
/*  49 */       now = sdf.parse(time);
/*  50 */     } catch (Exception e) {
/*  51 */       e.printStackTrace();
/*     */     } 
/*  53 */     Calendar cal = Calendar.getInstance();
/*  54 */     cal.setTime(now);
/*     */     
/*  56 */     int dayWeek = cal.get(7);
/*  57 */     if (1 == dayWeek) {
/*  58 */       cal.add(5, -1);
/*     */     }
/*  60 */     cal.setFirstDayOfWeek(2);
/*  61 */     int day = cal.get(7);
/*  62 */     cal.add(5, cal.getFirstDayOfWeek() - day);
/*  63 */     String imptimeBegin = sdf.format(cal.getTime());
/*  64 */     date[0] = imptimeBegin;
/*  65 */     cal.add(5, 6);
/*  66 */     String imptimeEnd = sdf.format(cal.getTime());
/*  67 */     date[1] = imptimeEnd;
/*  68 */     return date;
/*     */   }
/*     */   
/*     */   public List<String> getDateList(String start, String end) throws ParseException {
/*  72 */     List<String> rlist = new ArrayList<String>();
/*  73 */     Date date = null;
/*  74 */     Calendar cal = Calendar.getInstance();
/*  75 */     date = (new SimpleDateFormat("yyyy/MM/dd")).parse(start);
/*  76 */     rlist.add((new SimpleDateFormat("yyyy-MM-dd")).format(date));
/*  77 */     cal.setTime(date);
/*  78 */     int i = 1;
/*     */     while (true) {
/*  80 */       cal.add(5, 1);
/*  81 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*  82 */       rlist.add(sdf.format(cal.getTime()));
/*  83 */       if (i == 7) {
/*     */         break;
/*     */       }
/*  86 */       i++;
/*     */     } 
/*  88 */     return rlist;
/*     */   }
/*     */   
/*     */   public List<String> getBeforeDateList() {
/*  92 */     List<String> dateList = new ArrayList<String>();
/*  93 */     Calendar date = Calendar.getInstance();
/*  94 */     for (int i = 0; i < 7; i++) {
/*  95 */       date.setTime(new Date());
/*  96 */       date.add(5, -i);
/*  97 */       dateList.add((new SimpleDateFormat("yyyy/MM/dd")).format(date.getTime()));
/*     */     } 
/*  99 */     return dateList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getLastDayOfMonth(int month) {
/* 108 */     Calendar cal = Calendar.getInstance();
/* 109 */     cal.set(1, cal.get(1));
/* 110 */     cal.set(2, month);
/* 111 */     cal.set(5, 1);
/* 112 */     cal.setTime(cal.getTime());
/* 113 */     cal.set(5, 1);
/* 114 */     cal.roll(5, -1);
/* 115 */     return (new SimpleDateFormat("yyyy/MM/dd")).format(cal.getTime());
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
/*     */   public ActionForward ajaxGetpoInData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 129 */     PurchaseOrderPutInStorageManager inboundManager = ServiceLocator.getPurchaseOrderPutInStorageManager(request);
/* 130 */     request.setCharacterEncoding("utf-8");
/* 131 */     SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
/* 132 */     List<String> dateList = getBeforeDateList();
/* 133 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 134 */     List<Integer> dataList = new ArrayList<Integer>();
/* 135 */     List<PurchaseOrderPutInStorage> endlist = inboundManager.getPurchaseOrderPutInStorageList(conditions, 0, -1, null, false);
/* 136 */     Map<Object, Object> countMap = new HashMap<Object, Object>(); int i;
/* 137 */     for (i = 0; i < endlist.size(); i++) {
/* 138 */       PurchaseOrderPutInStorage inbound = endlist.get(i);
/* 139 */       String time = format.format(inbound.getDate());
/* 140 */       countMap.put(time, Integer.valueOf(1 + intCheng(countMap.get(time))));
/*     */     } 
/* 142 */     for (i = dateList.size() - 1; i > -1; i--) {
/* 143 */       String time = dateList.get(i);
/* 144 */       if (countMap.get(time) != null && !countMap.get(time).equals("")) {
/* 145 */         dataList.add(Integer.valueOf(intCheng(countMap.get(time))));
/*     */       } else {
/* 147 */         dataList.add(Integer.valueOf(0));
/*     */       } 
/*     */     } 
/*     */     
/* 151 */     XMLUtil xml = new XMLUtil();
/* 152 */     Element graph = xml.addRoot("graph");
/* 153 */     xml.addAttribute(graph, "caption", "(入库信息 from " + dateList.get(6) + " to " + dateList.get(0) + ")");
/* 154 */     xml.addAttribute(graph, "showvalues", "0");
/* 155 */     xml.addAttribute(graph, "numDivlines", "4");
/* 156 */     xml.addAttribute(graph, "formatNumberScale", "0");
/* 157 */     xml.addAttribute(graph, "decimalPrecision", "0");
/* 158 */     xml.addAttribute(graph, "anchorSides", "10");
/* 159 */     xml.addAttribute(graph, "anchorRadius", "3");
/* 160 */     xml.addAttribute(graph, "anchorBorderColor", "009900");
/* 161 */     xml.addAttribute(graph, "yaxisminvalue", "0");
/* 162 */     xml.addAttribute(graph, "yaxismaxvalue", "100");
/* 163 */     Element categories = xml.addNode(graph, "categories");
/* 164 */     for (int j = dateList.size() - 1; j > -1; j--) {
/* 165 */       Element category = xml.addNode(categories, "category");
/* 166 */       category.addAttribute("name", dateList.get(j).toString().substring(5));
/*     */     } 
/*     */     
/* 169 */     Element dataset = xml.addNode(graph, "dataset");
/* 170 */     dataset.addAttribute("color", "AFD8F8");
/* 171 */     dataset.addAttribute("showValues", "0");
/* 172 */     for (int k = 0; k < dataList.size(); k++) {
/* 173 */       Element set = xml.addNode(dataset, "set");
/* 174 */       set.addAttribute("value", ((Integer)dataList.get(k)).toString());
/*     */     } 
/* 176 */     Element datasetY = xml.addNode(graph, "dataset");
/* 177 */     datasetY.addAttribute("showValues", "0");
/* 178 */     datasetY.addAttribute("color", "F6BD0F");
/* 179 */     datasetY.addAttribute("parentYAxis", "S");
/* 180 */     for (int m = 0; m < dataList.size(); m++) {
/* 181 */       Element set = xml.addNode(datasetY, "set");
/* 182 */       set.addAttribute("value", ((Integer)dataList.get(m)).toString());
/*     */     } 
///* 184 */     response.setCharacterEncoding("UTF-8");
/* 185 */     response.getWriter().print(xml.getXML());
/* 186 */     return null;
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
/*     */   public ActionForward ajaxGetpoOutData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 199 */     PurchaseOrderPutInStorageManager inboundManager = ServiceLocator.getPurchaseOrderPutInStorageManager(request);
/* 200 */     request.setCharacterEncoding("utf-8");
/* 201 */     SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
/* 202 */     List<String> dateList = getBeforeDateList();
/* 203 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 204 */     List<Integer> dataList = new ArrayList<Integer>();
/* 205 */     List<PurchaseOrderPutInStorage> endlist = inboundManager.getPurchaseOrderPutInStorageList(conditions, 0, -1, null, false);
/* 206 */     Map<Object, Object> countMap = new HashMap<Object, Object>(); int i;
/* 207 */     for (i = 0; i < endlist.size(); i++) {
/* 208 */       PurchaseOrderPutInStorage inbound = endlist.get(i);
/* 209 */       String time = format.format(inbound.getDate());
/* 210 */       countMap.put(time, Integer.valueOf(1 + intCheng(countMap.get(time))));
/*     */     } 
/* 212 */     for (i = dateList.size() - 1; i > -1; i--) {
/* 213 */       String time = dateList.get(i);
/* 214 */       if (countMap.get(time) != null && !countMap.get(time).equals("")) {
/* 215 */         dataList.add(Integer.valueOf(intCheng(countMap.get(time))));
/*     */       } else {
/* 217 */         dataList.add(Integer.valueOf(0));
/*     */       } 
/*     */     } 
/*     */     
/* 221 */     XMLUtil xml = new XMLUtil();
/* 222 */     Element graph = xml.addRoot("graph");
/* 223 */     xml.addAttribute(graph, "caption", "(入库信息 from " + dateList.get(6) + " to " + dateList.get(0) + ")");
/* 224 */     xml.addAttribute(graph, "showvalues", "0");
/* 225 */     xml.addAttribute(graph, "numDivlines", "4");
/* 226 */     xml.addAttribute(graph, "formatNumberScale", "0");
/* 227 */     xml.addAttribute(graph, "decimalPrecision", "0");
/* 228 */     xml.addAttribute(graph, "anchorSides", "10");
/* 229 */     xml.addAttribute(graph, "anchorRadius", "3");
/* 230 */     xml.addAttribute(graph, "anchorBorderColor", "009900");
/* 231 */     xml.addAttribute(graph, "yaxisminvalue", "0");
/* 232 */     xml.addAttribute(graph, "yaxismaxvalue", "100");
/* 233 */     Element categories = xml.addNode(graph, "categories");
/* 234 */     for (int j = dateList.size() - 1; j > -1; j--) {
/* 235 */       Element category = xml.addNode(categories, "category");
/* 236 */       category.addAttribute("name", dateList.get(j).toString().substring(5));
/*     */     } 
/*     */     
/* 239 */     Element dataset = xml.addNode(graph, "dataset");
/* 240 */     dataset.addAttribute("color", "AFD8F8");
/* 241 */     dataset.addAttribute("showValues", "0");
/* 242 */     for (int k = 0; k < dataList.size(); k++) {
/* 243 */       Element set = xml.addNode(dataset, "set");
/* 244 */       set.addAttribute("value", ((Integer)dataList.get(k)).toString());
/*     */     } 
/* 246 */     Element datasetY = xml.addNode(graph, "dataset");
/* 247 */     datasetY.addAttribute("showValues", "0");
/* 248 */     datasetY.addAttribute("color", "F6BD0F");
/* 249 */     datasetY.addAttribute("parentYAxis", "S");
/* 250 */     for (int m = 0; m < dataList.size(); m++) {
/* 251 */       Element set = xml.addNode(datasetY, "set");
/* 252 */       set.addAttribute("value", ((Integer)dataList.get(m)).toString());
/*     */     } 
///* 254 */     response.setCharacterEncoding("UTF-8");
/* 255 */     response.getWriter().print(xml.getXML());
/* 256 */     return null;
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
/*     */   public ActionForward ajaxGetproduceOutData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 270 */     PurchaseOrderPutInStorageManager inboundManager = ServiceLocator.getPurchaseOrderPutInStorageManager(request);
/* 271 */     request.setCharacterEncoding("utf-8");
/* 272 */     SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
/* 273 */     List<String> dateList = getBeforeDateList();
/* 274 */     Map<Object, Object> conditions = new HashMap<Object, Object>();
/* 275 */     List<Integer> dataList = new ArrayList<Integer>();
/* 276 */     List<PurchaseOrderPutInStorage> endlist = inboundManager.getPurchaseOrderPutInStorageList(conditions, 0, -1, null, false);
/* 277 */     Map<Object, Object> countMap = new HashMap<Object, Object>(); int i;
/* 278 */     for (i = 0; i < endlist.size(); i++) {
/* 279 */       PurchaseOrderPutInStorage inbound = endlist.get(i);
/* 280 */       String time = format.format(inbound.getDate());
/* 281 */       countMap.put(time, Integer.valueOf(1 + intCheng(countMap.get(time))));
/*     */     } 
/* 283 */     for (i = dateList.size() - 1; i > -1; i--) {
/* 284 */       String time = dateList.get(i);
/* 285 */       if (countMap.get(time) != null && !countMap.get(time).equals("")) {
/* 286 */         dataList.add(Integer.valueOf(intCheng(countMap.get(time))));
/*     */       } else {
/* 288 */         dataList.add(Integer.valueOf(0));
/*     */       } 
/*     */     } 
/*     */     
/* 292 */     XMLUtil xml = new XMLUtil();
/* 293 */     Element graph = xml.addRoot("graph");
/* 294 */     xml.addAttribute(graph, "caption", "(入库信息 from " + dateList.get(6) + " to " + dateList.get(0) + ")");
/* 295 */     xml.addAttribute(graph, "showvalues", "0");
/* 296 */     xml.addAttribute(graph, "numDivlines", "4");
/* 297 */     xml.addAttribute(graph, "formatNumberScale", "0");
/* 298 */     xml.addAttribute(graph, "decimalPrecision", "0");
/* 299 */     xml.addAttribute(graph, "anchorSides", "10");
/* 300 */     xml.addAttribute(graph, "anchorRadius", "3");
/* 301 */     xml.addAttribute(graph, "anchorBorderColor", "009900");
/* 302 */     xml.addAttribute(graph, "yaxisminvalue", "0");
/* 303 */     xml.addAttribute(graph, "yaxismaxvalue", "100");
/* 304 */     Element categories = xml.addNode(graph, "categories");
/* 305 */     for (int j = dateList.size() - 1; j > -1; j--) {
/* 306 */       Element category = xml.addNode(categories, "category");
/* 307 */       category.addAttribute("name", dateList.get(j).toString().substring(5));
/*     */     } 
/*     */     
/* 310 */     Element dataset = xml.addNode(graph, "dataset");
/* 311 */     dataset.addAttribute("color", "AFD8F8");
/* 312 */     dataset.addAttribute("showValues", "0");
/* 313 */     for (int k = 0; k < dataList.size(); k++) {
/* 314 */       Element set = xml.addNode(dataset, "set");
/* 315 */       set.addAttribute("value", ((Integer)dataList.get(k)).toString());
/*     */     } 
/* 317 */     Element datasetY = xml.addNode(graph, "dataset");
/* 318 */     datasetY.addAttribute("showValues", "0");
/* 319 */     datasetY.addAttribute("color", "F6BD0F");
/* 320 */     datasetY.addAttribute("parentYAxis", "S");
/* 321 */     for (int m = 0; m < dataList.size(); m++) {
/* 322 */       Element set = xml.addNode(datasetY, "set");
/* 323 */       set.addAttribute("value", ((Integer)dataList.get(m)).toString());
/*     */     } 
///* 325 */     response.setCharacterEncoding("UTF-8");
/* 326 */     response.getWriter().print(xml.getXML());
/* 327 */     return null;
/*     */   }
/*     */   
/*     */   public List<Map> getStockListData(HttpServletRequest request) {
/* 331 */     InventoryManager inventoryManager = ServiceLocator.getInventoryManager(request);
/* 332 */     Map<Object, Object> map = new HashMap<Object, Object>();
/* 333 */     String siteString = "";
/* 334 */     List<Map> dataList = new ArrayList<Map>();
/* 335 */     List resultList = inventoryManager.getInventoryList(map, -1, -1, null, false);
/* 336 */     for (int j = 0; j < 12; j++) {
/* 337 */       Map<Object, Object> stockMap = new HashMap<Object, Object>();
/* 338 */       Map<Object, Object> parMap = new HashMap<Object, Object>();
/* 339 */       int stock = 0;
/* 340 */       String endTime = getLastDayOfMonth(j);
/* 341 */       String startTime = String.valueOf(endTime.substring(0, 8)) + "01";
/* 342 */       for (int i = 0; i < resultList.size(); i++) {
/* 343 */         stock = 0;
/*     */       }
/* 345 */       stockMap.put(Integer.valueOf(j), Integer.valueOf(stock));
/* 346 */       dataList.add(stockMap);
/*     */     } 
/* 348 */     return dataList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionForward palletScaleMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
/* 355 */     return mapping.findForward("page");
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/wms/statements/StatementsAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */