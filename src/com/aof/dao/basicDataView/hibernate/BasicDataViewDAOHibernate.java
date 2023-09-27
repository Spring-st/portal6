/*     */ package com.aof.dao.basicDataView.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.basicDataView.BasicDataViewDAO;
/*     */ import com.aof.model.basicDataView.PartForecastNeedReport;
/*     */ import com.aof.model.basicDataView.SkPartSumNumber;
/*     */ import com.aof.model.basicDataView.query.JitShipPartQueryCondition;
/*     */ import com.aof.model.basicDataView.query.JitShipPartQueryOrder;
/*     */ import com.aof.model.basicDataView.query.PartForecastNeedReportQueryCondition;
/*     */ import com.aof.model.basicDataView.query.PartForecastNeedReportQueryOrder;
/*     */ import com.aof.model.basicDataView.query.SkPartSumNumberQueryOrder;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BasicDataViewDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements BasicDataViewDAO
/*     */ {
/*  42 */   private Log log = LogFactory.getLog(BasicDataViewDAOHibernate.class);
/*     */   
/*     */   public SkPartSumNumber getSkPartSumNumber(String id) {
/*  45 */     if (id == null) {
/*  46 */       if (this.log.isDebugEnabled())
/*  47 */         this.log.debug("try to get WmsPart with null id"); 
/*  48 */       return null;
/*     */     } 
/*  50 */     return (SkPartSumNumber)getHibernateTemplate().get(SkPartSumNumber.class, id);
/*     */   }
/*     */ 
/*     */   
/*     */   private static final String SQL_COUNT_SkPartSumNumber = "select count(*) from SkPartSumNumber spsn";
/*     */   private static final String SQL_SkPartSumNumber = "from SkPartSumNumber spsn";
/*  56 */   private static final Object[][] QUERY_CONDITIONS_SkPartSumNumber = new Object[0][];
/*     */ 
/*     */ 
/*     */   
/*  60 */   private static final Object[][] QUERY_ORDERS_SkPartSumNumber = new Object[][] {
/*  61 */       { SkPartSumNumberQueryOrder.PART_ID, "spsn.part.id"
/*  62 */       }, { SkPartSumNumberQueryOrder.SUMNUMBER, "spsn.sumNumber" } };
/*     */   private static final String SQL_COUNT_JitShipPart = "select count(*) from JitShipPart jspt";
/*     */   
/*     */   public int getSkPartSumNumberListCount(Map conditions) {
/*  66 */     return getListCount(conditions, "select count(*) from SkPartSumNumber spsn", QUERY_CONDITIONS_SkPartSumNumber);
/*     */   }
/*     */   private static final String SQL_JitShipPart = "from JitShipPart jspt";
/*     */   
/*     */   public List getSkPartSumNumberList(Map conditions, int pageNo, int pageSize, SkPartSumNumberQueryOrder order, boolean descend) {
/*  71 */     return getList(conditions, pageNo, pageSize, order, descend, "from SkPartSumNumber spsn", QUERY_CONDITIONS_SkPartSumNumber, QUERY_ORDERS_SkPartSumNumber);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   private static final Object[][] QUERY_CONDITIONS_JitShipPart = new Object[][] {
/*  78 */       { JitShipPartQueryCondition.PART_ID_EQ, "jspt.part.id = ?"
/*  79 */       }, { JitShipPartQueryCondition.PART_FREEZE_STATUS_EQ, "jspt.part.freeze_status = ?"
/*  80 */       }, { JitShipPartQueryCondition.PART_PRODUCTCLASS_EQ, "jspt.part.productClass = ?"
/*  81 */       }, { JitShipPartQueryCondition.PART_ENABLED_EQ, "jspt.part.enabled = ?"
/*  82 */       }, { JitShipPartQueryCondition.QTY_GT, "jspt.qty > ?"
/*  83 */       }, { JitShipPartQueryCondition.PART_VEND_EQ, "jspt.part.vend = ?"
/*  84 */       }, { JitShipPartQueryCondition.PART_VEND_EQ, "jspt.part.vend = ?" }
/*     */     };
/*     */   
/*  87 */   private static final Object[][] QUERY_ORDERS_JitShipPart = new Object[][] {
/*  88 */       { JitShipPartQueryOrder.PART_ID, "jspt.part.id"
/*  89 */       }, { JitShipPartQueryOrder.PART_CARTYPE, "jspt.part.productSpecifications"
/*  90 */       }, { JitShipPartQueryOrder.QTY, "jspt.qty"
/*  91 */       }, { JitShipPartQueryOrder.part_drwgLoc, " jspt.part.drwgLoc"
/*  92 */       }, { JitShipPartQueryOrder.highQty, " jspt.part.highQty"
/*     */       },
/*  94 */       { JitShipPartQueryOrder.lowQty, " jspt.part.lowQty"
/*  95 */       }, { JitShipPartQueryOrder.securityQty, "jspt.part.securityQty"
/*  96 */       }, { JitShipPartQueryOrder.currentQty, "jspt.currentQty" } }; private static final String SQL_COUNT_PartForecastNeedReport = "select count(*) from PartForecastNeedReport pfnr";
/*     */   private static final String SQL_PartForecastNeedReport = "from PartForecastNeedReport pfnr";
/*     */   
/*     */   public int getJitShipPartListCount(Map conditions) {
/* 100 */     return getListCount(conditions, "select count(*) from JitShipPart jspt", QUERY_CONDITIONS_JitShipPart);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getJitShipPartNumberList(Map conditions, int pageNo, int pageSize, JitShipPartQueryOrder order, boolean descend) {
/* 105 */     return getList(conditions, pageNo, pageSize, order, descend, "from JitShipPart jspt", QUERY_CONDITIONS_JitShipPart, QUERY_ORDERS_JitShipPart);
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
/* 135 */   private static final Object[][] QUERY_CONDITIONS_PartForecastNeedReport = new Object[][] { 
/* 136 */       { PartForecastNeedReportQueryCondition.PART_ID_EQ, "pfnr.part.id = ?"
/* 137 */       }, { PartForecastNeedReportQueryCondition.PART_ENABLED_EQ, "pfnr.part.enabled = ?"
/* 138 */       }, { PartForecastNeedReportQueryCondition.PART_FREEZE_STATUS_EQ, "pfnr.part.freeze_status = ?"
/* 139 */       }, { PartForecastNeedReportQueryCondition.PART_PRODUCTCLASS_EQ, "pfnr.part.productClass = ?"
/* 140 */       }, { PartForecastNeedReportQueryCondition.PART_SUPPLIER_EQ, "pfnr.part.vend = ?"
/* 141 */       }, { PartForecastNeedReportQueryCondition.NO9NEEDQTY_GT, " pfnr.no9Needqty > ?"
/* 142 */       }, { PartForecastNeedReportQueryCondition.NO10NEEDQTY_GT, " pfnr.no10Needqty > ?"
/* 143 */       }, { PartForecastNeedReportQueryCondition.NO11NEEDQTY_GT, " pfnr.no11Needqty > ?"
/* 144 */       }, { PartForecastNeedReportQueryCondition.NO12NEEDQTY_GT, " pfnr.no12Needqty > ?"
/* 145 */       }, { PartForecastNeedReportQueryCondition.NO13NEEDQTY_GT, " pfnr.no13Needqty > ?" }, 
/* 146 */       { PartForecastNeedReportQueryCondition.NO14NEEDQTY_GT, " pfnr.no14Needqty > ?"
/* 147 */       }, { PartForecastNeedReportQueryCondition.NO15NEEDQTY_GT, " pfnr.no15Needqty > ?"
/* 148 */       }, { PartForecastNeedReportQueryCondition.NO16NEEDQTY_GT, " pfnr.no16Needqty > ?"
/* 149 */       }, { PartForecastNeedReportQueryCondition.NO17NEEDQTY_GT, " pfnr.no17Needqty > ?"
/* 150 */       }, { PartForecastNeedReportQueryCondition.NO18NEEDQTY_GT, " pfnr.no18Needqty > ?"
/* 151 */       }, { PartForecastNeedReportQueryCondition.NO19NEEDQTY_GT, " pfnr.no19Needqty > ?"
/* 152 */       }, { PartForecastNeedReportQueryCondition.NO20NEEDQTY_GT, " pfnr.no20Needqty > ?"
/* 153 */       }, { PartForecastNeedReportQueryCondition.NO21NEEDQTY_GT, " pfnr.no21Needqty > ?"
/* 154 */       }, { PartForecastNeedReportQueryCondition.NO22NEEDQTY_GT, " pfnr.no22Needqty > ?"
/* 155 */       }, { PartForecastNeedReportQueryCondition.NO23NEEDQTY_GT, " pfnr.no23Needqty > ?" }, 
/* 156 */       { PartForecastNeedReportQueryCondition.NO24NEEDQTY_GT, " pfnr.no24Needqty > ?"
/* 157 */       }, { PartForecastNeedReportQueryCondition.NO25NEEDQTY_GT, " pfnr.no25Needqty > ?"
/* 158 */       }, { PartForecastNeedReportQueryCondition.NO26NEEDQTY_GT, " pfnr.no26Needqty > ?"
/* 159 */       }, { PartForecastNeedReportQueryCondition.NO27NEEDQTY_GT, " pfnr.no27Needqty > ?"
/* 160 */       }, { PartForecastNeedReportQueryCondition.NO28NEEDQTY_GT, " pfnr.no28Needqty > ?"
/* 161 */       }, { PartForecastNeedReportQueryCondition.NO29NEEDQTY_GT, " pfnr.no29Needqty > ?"
/* 162 */       }, { PartForecastNeedReportQueryCondition.NO30NEEDQTY_GT, " pfnr.no30Needqty > ?"
/* 163 */       }, { PartForecastNeedReportQueryCondition.NO31NEEDQTY_GT, " pfnr.no31Needqty > ?"
/* 164 */       }, { PartForecastNeedReportQueryCondition.NO32NEEDQTY_GT, " pfnr.no32Needqty > ?"
/* 165 */       }, { PartForecastNeedReportQueryCondition.NO33NEEDQTY_GT, " pfnr.no33Needqty > ?" }, 
/* 166 */       { PartForecastNeedReportQueryCondition.NO34NEEDQTY_GT, " pfnr.no34Needqty > ?"
/* 167 */       }, { PartForecastNeedReportQueryCondition.NO35NEEDQTY_GT, " pfnr.no35Needqty > ?"
/* 168 */       }, { PartForecastNeedReportQueryCondition.NO36NEEDQTY_GT, " pfnr.no36Needqty > ?"
/* 169 */       }, { PartForecastNeedReportQueryCondition.NO37NEEDQTY_GT, " pfnr.no37Needqty > ?"
/* 170 */       }, { PartForecastNeedReportQueryCondition.NO38NEEDQTY_GT, " pfnr.no38Needqty > ?"
/* 171 */       }, { PartForecastNeedReportQueryCondition.NO39NEEDQTY_GT, " pfnr.no39Needqty > ?"
/* 172 */       }, { PartForecastNeedReportQueryCondition.NO40NEEDQTY_GT, " pfnr.no40Needqty > ?"
/* 173 */       }, { PartForecastNeedReportQueryCondition.NO41NEEDQTY_GT, " pfnr.no41Needqty > ?"
/* 174 */       }, { PartForecastNeedReportQueryCondition.NO42NEEDQTY_GT, " pfnr.no42Needqty > ?"
/* 175 */       }, { PartForecastNeedReportQueryCondition.NO43NEEDQTY_GT, " pfnr.no43Needqty > ?" }, 
/* 176 */       { PartForecastNeedReportQueryCondition.NO44NEEDQTY_GT, " pfnr.no44Needqty > ?" } };
/*     */ 
/*     */   
/* 179 */   private static final Object[][] QUERY_ORDERS_PartForecastNeedReport = new Object[][] {
/* 180 */       { PartForecastNeedReportQueryOrder.PART_ID, "pfnr.part.id"
/* 181 */       }, { PartForecastNeedReportQueryOrder.PART_productspecifications, "pfnr.part.productSpecifications"
/* 182 */       }, { PartForecastNeedReportQueryOrder.PART_drwgLoc, " pfnr.part.drwgLoc" }
/*     */     };
/*     */ 
/*     */   
/*     */   public Integer getPartForecastNeedReportListCount(Map conditions) {
/* 187 */     return Integer.valueOf(getListCount(conditions, "select count(*) from PartForecastNeedReport pfnr", QUERY_CONDITIONS_PartForecastNeedReport));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PartForecastNeedReport> getPartForecastNeedReportList(Map conditions, int pageNo, int pageSize, PartForecastNeedReportQueryOrder order, boolean descend) {
/* 193 */     return getList(conditions, pageNo, pageSize, order, descend, "from PartForecastNeedReport pfnr", QUERY_CONDITIONS_PartForecastNeedReport, QUERY_ORDERS_PartForecastNeedReport);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basicDataView/hibernate/BasicDataViewDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */