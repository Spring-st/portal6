/*     */ package com.aof.web.struts.action;
/*     */ 
/*     */ import com.aof.service.Product.BasicCustomerManager;
/*     */ import com.aof.service.Product.CustomerPlanManager;
/*     */ import com.aof.service.Product.DailyProductPlanManager;
/*     */ import com.aof.service.Product.ProductBelowLineManager;
/*     */ import com.aof.service.Product.ProductBelowlineCasadeManager;
/*     */ import com.aof.service.Product.ProductGolineManager;
/*     */ import com.aof.service.Product.ProductManager;
/*     */ import com.aof.service.Product.ProductOutGolineManager;
/*     */ import com.aof.service.Product.ProductOutStorageManager;
/*     */ import com.aof.service.Product.SalesOrderItemManager;
/*     */ import com.aof.service.Product.SalesOrderManager;
/*     */ import com.aof.service.Product.SalesPreshiporderBatchManager;
/*     */ import com.aof.service.Product.SalesPreshiporderItemManager;
/*     */ import com.aof.service.Product.SalesPreshiporderManager;
/*     */ import com.aof.service.Product.SalesWorkorderManager;
/*     */ import com.aof.service.Product.WorkOrderBomManager;
/*     */ import com.aof.service.admin.CityManager;
/*     */ import com.aof.service.admin.CountryManager;
/*     */ import com.aof.service.admin.CurrencyManager;
/*     */ import com.aof.service.admin.CustomerManager;
/*     */ import com.aof.service.admin.DataTransferManager;
/*     */ import com.aof.service.admin.DepartmentManager;
/*     */ import com.aof.service.admin.EmailManager;
/*     */ import com.aof.service.admin.ExchangeRateManager;
/*     */ import com.aof.service.admin.ExpenseCategoryManager;
/*     */ import com.aof.service.admin.ExpenseSubCategoryManager;
/*     */ import com.aof.service.admin.FinishedSaiheRelationManager;
/*     */ import com.aof.service.admin.FinishedToolPutnumberManager;
/*     */ import com.aof.service.admin.FunctionManager;
/*     */ import com.aof.service.admin.GlobalManager;
/*     */ import com.aof.service.admin.HotelContractManager;
/*     */ import com.aof.service.admin.HotelManager;
/*     */ import com.aof.service.admin.InventoryMovingManager;
/*     */ import com.aof.service.admin.MenuManager;
/*     */ import com.aof.service.admin.MetadataManager;
/*     */ import com.aof.service.admin.ParameterManager;
/*     */ import com.aof.service.admin.PriceManager;
/*     */ import com.aof.service.admin.ProvinceManager;
/*     */ import com.aof.service.admin.PurchaseCategoryManager;
/*     */ import com.aof.service.admin.PurchaseSubCategoryManager;
/*     */ import com.aof.service.admin.PurchaseTypeManager;
/*     */ import com.aof.service.admin.RoleManager;
/*     */ import com.aof.service.admin.SiteManager;
/*     */ import com.aof.service.admin.SupplierContractManager;
/*     */ import com.aof.service.admin.SupplierHistoryManager;
/*     */ import com.aof.service.admin.SupplierItemManager;
/*     */ import com.aof.service.admin.SupplierManager;
/*     */ import com.aof.service.admin.SystemLogManager;
/*     */ import com.aof.service.admin.UserManager;
/*     */ import com.aof.service.basic.BadReasonsManager;
/*     */ import com.aof.service.basic.BasicPartLocationManager;
/*     */ import com.aof.service.basic.BasicPartPriceManager;
/*     */ import com.aof.service.basic.CostCenterManager;
/*     */ import com.aof.service.basic.CustomerReturnItemManager;
/*     */ import com.aof.service.basic.CustomerreturnsManager;
/*     */ import com.aof.service.basic.ExpensesCourseManager;
/*     */ import com.aof.service.basic.PartDecompositionManager;
/*     */ import com.aof.service.basic.ReportEntersSellsSavesManager;
/*     */ import com.aof.service.basic.ScanLogManager;
/*     */ import com.aof.service.basic.StorageLocationManager;
/*     */ import com.aof.service.basic.StoreRoomManager;
/*     */ import com.aof.service.basic.SycSleepTimeManager;
/*     */ import com.aof.service.basic.UnplannedReasonsManager;
/*     */ import com.aof.service.basic.UselessPartManager;
/*     */ import com.aof.service.basic.WmsPartManager;
/*     */ import com.aof.service.basic.WmsToolManager;
/*     */ import com.aof.service.basicDataView.BasicDataViewManager;
/*     */ import com.aof.service.business.rule.ApproverDelegateManager;
/*     */ import com.aof.service.business.rule.FlowManager;
/*     */ import com.aof.service.business.rule.RuleManager;
/*     */ import com.aof.service.comprehensive.BomManager;
/*     */ import com.aof.service.comprehensive.BoxAdjustmentManager;
/*     */ import com.aof.service.comprehensive.StockingManager;
/*     */ import com.aof.service.inventory.InventoryManager;
/*     */ import com.aof.service.inventory.InventoryTransitManager;
/*     */ import com.aof.service.kpi.KPIManager;
/*     */ import com.aof.service.plantWarehouse.WmsPlanToGoOutManager;
/*     */ import com.aof.service.plantWarehouse.WmsUWManager;
/*     */ import com.aof.service.po.BoxManager;
/*     */ import com.aof.service.po.PoHighLineManager;
/*     */ import com.aof.service.po.PortalShipOrderItemManager;
/*     */ import com.aof.service.po.PortalShipOrderManager;
/*     */ import com.aof.service.po.ProduceBuckleMaterialManager;
/*     */ import com.aof.service.po.ProduceRejectedMaterialManager;
/*     */ import com.aof.service.po.ProductionplanningManager;
/*     */ import com.aof.service.po.PurchaseManualCreateBarcodeManager;
/*     */ import com.aof.service.po.PurchaseOrderCondimentSingleManager;
/*     */ import com.aof.service.po.PurchaseOrderInspectionPendingManager;
/*     */ import com.aof.service.po.PurchaseOrderManager;
/*     */ import com.aof.service.po.PurchaseOrderPutInStorageManager;
/*     */ import com.aof.service.po.PurchaseOrderRQCManager;
/*     */ import com.aof.service.po.PurchaseOrderReceiptsManager;
/*     */ import com.aof.service.quartz.job.DeliverMinuteSyncJob;
/*     */ import com.aof.service.quartz.job.RedMinuteSyncJob;
/*     */ import com.aof.service.schedule.EdiProductionErrorLogManager;
/*     */ import com.aof.service.schedule.EdiProductionManager;
/*     */ import com.aof.service.schedule.JitProductionPlanHistoryManager;
/*     */ import com.aof.service.schedule.JitProductionPlanManager;
/*     */ import com.aof.service.schedule.NjitNpoPlanManager;
/*     */ import com.aof.service.schedule.Portalv6MrpPartPlanViewManager;
/*     */ import com.aof.service.schedule.Production72HourPlanManager;
/*     */ import com.aof.service.schedule.ProductionDayPlanManager;
/*     */ import com.aof.service.schedule.ProjectedInventoryManager;
/*     */ import com.aof.service.schedule.QadOrEdiManager;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.springframework.web.context.WebApplicationContext;
/*     */ import org.springframework.web.context.support.WebApplicationContextUtils;
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
/*     */ public class ServiceLocator
/*     */ {
/*     */   protected static Object getBean(String name, HttpServletRequest request) {
/* 129 */     WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession()
/* 130 */         .getServletContext());
/* 131 */     return webApplicationContext.getBean(name);
/*     */   }
/*     */   
/*     */   public static BeanLoader getBeanLoader(HttpServletRequest request) {
/* 135 */     return (BeanLoader)getBean("beanLoader", request);
/*     */   }
/*     */   
/*     */   public static SiteManager getSiteManager(HttpServletRequest request) {
/* 139 */     return (SiteManager)getBean("siteManager", request);
/*     */   }
/*     */   
/*     */   public static FunctionManager getFunctionManager(HttpServletRequest request) {
/* 143 */     return (FunctionManager)getBean("functionManager", request);
/*     */   }
/*     */   
/*     */   public static DepartmentManager getDepartmentManager(HttpServletRequest request) {
/* 147 */     return (DepartmentManager)getBean("departmentManager", request);
/*     */   }
/*     */   
/*     */   public static UserManager getUserManager(HttpServletRequest request) {
/* 151 */     return (UserManager)getBean("userManager", request);
/*     */   }
/*     */   
/*     */   public static ExpenseSubCategoryManager getExpenseSubCategoryManager(HttpServletRequest request) {
/* 155 */     return (ExpenseSubCategoryManager)getBean("expenseSubCategoryManager", request);
/*     */   }
/*     */   
/*     */   public static ExpenseCategoryManager getExpenseCategoryManager(HttpServletRequest request) {
/* 159 */     return (ExpenseCategoryManager)getBean("expenseCategoryManager", request);
/*     */   }
/*     */   
/*     */   public static CityManager getCityManager(HttpServletRequest request) {
/* 163 */     return (CityManager)getBean("cityManager", request);
/*     */   }
/*     */   
/*     */   public static ProvinceManager getProvinceManager(HttpServletRequest request) {
/* 167 */     return (ProvinceManager)getBean("provinceManager", request);
/*     */   }
/*     */   
/*     */   public static CountryManager getCountryManager(HttpServletRequest request) {
/* 171 */     return (CountryManager)getBean("countryManager", request);
/*     */   }
/*     */   
/*     */   public static CurrencyManager getCurrencyManager(HttpServletRequest request) {
/* 175 */     return (CurrencyManager)getBean("currencyManager", request);
/*     */   }
/*     */   
/*     */   public static EmailManager getEmailManager(HttpServletRequest request) {
/* 179 */     return (EmailManager)getBean("emailManager", request);
/*     */   }
/*     */   
/*     */   public static ExchangeRateManager getExchangeRateManager(HttpServletRequest request) {
/* 183 */     return (ExchangeRateManager)getBean("exchangeRateManager", request);
/*     */   }
/*     */   
/*     */   public static MenuManager getMenuManager(HttpServletRequest request) {
/* 187 */     return (MenuManager)getBean("menuManager", request);
/*     */   }
/*     */   
/*     */   public static MetadataManager getMetadataManager(HttpServletRequest request) {
/* 191 */     return (MetadataManager)getBean("metadataManager", request);
/*     */   }
/*     */   
/*     */   public static GlobalManager getGlobalManager(HttpServletRequest request) {
/* 195 */     return (GlobalManager)getBean("globalManager", request);
/*     */   }
/*     */   
/*     */   public static RoleManager getRoleManager(HttpServletRequest request) {
/* 199 */     return (RoleManager)getBean("roleManager", request);
/*     */   }
/*     */   
/*     */   public static PurchaseCategoryManager getPurchaseCategoryManager(HttpServletRequest request) {
/* 203 */     return (PurchaseCategoryManager)getBean("purchaseCategoryManager", request);
/*     */   }
/*     */   
/*     */   public static PurchaseSubCategoryManager getPurchaseSubCategoryManager(HttpServletRequest request) {
/* 207 */     return (PurchaseSubCategoryManager)getBean("purchaseSubCategoryManager", request);
/*     */   }
/*     */   
/*     */   public static SupplierManager getSupplierManager(HttpServletRequest request) {
/* 211 */     return (SupplierManager)getBean("supplierManager", request);
/*     */   }
/*     */   
/*     */   public static PriceManager getPriceManager(HttpServletRequest request) {
/* 215 */     return (PriceManager)getBean("priceManager", request);
/*     */   }
/*     */   
/*     */   public static SupplierHistoryManager getSupplierHistoryManager(HttpServletRequest request) {
/* 219 */     return (SupplierHistoryManager)getBean("supplierHistoryManager", request);
/*     */   }
/*     */   
/*     */   public static RuleManager getRuleManager(HttpServletRequest request) {
/* 223 */     return (RuleManager)getBean("ruleManager", request);
/*     */   }
/*     */   
/*     */   public static FlowManager getFlowManager(HttpServletRequest request) {
/* 227 */     return (FlowManager)getBean("flowManager", request);
/*     */   }
/*     */   
/*     */   public static SupplierContractManager getSupplierContractManager(HttpServletRequest request) {
/* 231 */     return (SupplierContractManager)getBean("supplierContractManager", request);
/*     */   }
/*     */   
/*     */   public static SupplierItemManager getSupplierItemManager(HttpServletRequest request) {
/* 235 */     return (SupplierItemManager)getBean("supplierItemManager", request);
/*     */   }
/*     */   
/*     */   public static ApproverDelegateManager getApproverDelegateManager(HttpServletRequest request) {
/* 239 */     return (ApproverDelegateManager)getBean("approverDelegateManager", request);
/*     */   }
/*     */   
/*     */   public static CustomerManager getCustomerManager(HttpServletRequest request) {
/* 243 */     return (CustomerManager)getBean("customerManager", request);
/*     */   }
/*     */   
/*     */   public static PurchaseTypeManager getPurchaseTypeManager(HttpServletRequest request) {
/* 247 */     return (PurchaseTypeManager)getBean("purchaseTypeManager", request);
/*     */   }
/*     */   
/*     */   public static ParameterManager getParameterManager(HttpServletRequest request) {
/* 251 */     return (ParameterManager)getBean("parameterManager", request);
/*     */   }
/*     */   
/*     */   public static DataTransferManager getDataTransferManager(HttpServletRequest request) {
/* 255 */     return (DataTransferManager)getBean("dataTransferManager", request);
/*     */   }
/*     */   
/*     */   public static SystemLogManager getSystemLogManager(HttpServletRequest request) {
/* 259 */     return (SystemLogManager)getBean("systemLogManager", request);
/*     */   }
/*     */   
/*     */   public static KPIManager getKPIManager(HttpServletRequest request) {
/* 263 */     return (KPIManager)getBean("kpiManager", request);
/*     */   }
/*     */   
/*     */   public static HotelManager getHotelManager(HttpServletRequest request) {
/* 267 */     return (HotelManager)getBean("hotelManager", request);
/*     */   }
/*     */   
/*     */   public static HotelContractManager getHotelContractManager(HttpServletRequest request) {
/* 271 */     return (HotelContractManager)getBean("hotelContractManager", request);
/*     */   }
/*     */   
/*     */   public static PurchaseOrderManager getPurchaseOrderManager(HttpServletRequest request) {
/* 275 */     return (PurchaseOrderManager)getBean("purchaseOrderManager", request);
/*     */   }
/*     */   
/*     */   public static BoxManager getBoxManager(HttpServletRequest request) {
/* 279 */     return (BoxManager)getBean("boxManager", request);
/*     */   }
/*     */   
/*     */   public static PurchaseOrderReceiptsManager getPurchaseOrderReceiptsManager(HttpServletRequest request) {
/* 283 */     return (PurchaseOrderReceiptsManager)getBean("purchaseOrderReceiptsManager", request);
/*     */   }
/*     */   
/*     */   public static PurchaseOrderPutInStorageManager getPurchaseOrderPutInStorageManager(HttpServletRequest request) {
/* 287 */     return (PurchaseOrderPutInStorageManager)getBean("purchaseOrderPutInStorageManager", request);
/*     */   }
/*     */   
/*     */   public static PurchaseOrderRQCManager getPurchaseOrderRQCManager(HttpServletRequest request) {
/* 291 */     return (PurchaseOrderRQCManager)getBean("purchaseOrderRQCManager", request);
/*     */   }
/*     */   
/*     */   public static WmsPartManager getWmsPartManager(HttpServletRequest request) {
/* 295 */     return (WmsPartManager)getBean("wmsPartManager", request);
/*     */   }
/*     */   
/*     */   public static StorageLocationManager getStorageLocationManager(HttpServletRequest request) {
/* 299 */     return (StorageLocationManager)getBean("storageLocationManager", request);
/*     */   }
/*     */   
/*     */   public static StoreRoomManager getStoreRoomManager(HttpServletRequest request) {
/* 303 */     return (StoreRoomManager)getBean("storeRoomManager", request);
/*     */   }
/*     */   
/*     */   public static PurchaseOrderInspectionPendingManager getPurchaseOrderInspectionPendingManager(HttpServletRequest request) {
/* 307 */     return (PurchaseOrderInspectionPendingManager)getBean("purchaseOrderInspectionPendingManager", request);
/*     */   }
/*     */   
/*     */   public static PortalShipOrderItemManager getPortalShipOrderItemManager(HttpServletRequest request) {
/* 311 */     return (PortalShipOrderItemManager)getBean("portalShipOrderItemManager", request);
/*     */   }
/*     */   
/*     */   public static PurchaseOrderCondimentSingleManager getPurchaseOrderCondimentSingleManager(HttpServletRequest request) {
/* 315 */     return (PurchaseOrderCondimentSingleManager)getBean("purchaseOrderCondimentSingleManager", request);
/*     */   }
/*     */   
/*     */   public static WmsToolManager getWmsToolManager(HttpServletRequest request) {
/* 319 */     return (WmsToolManager)getBean("wmsToolManager", request);
/*     */   }
/*     */   
/*     */   public static BadReasonsManager getBadReasonsManager(HttpServletRequest request) {
/* 323 */     return (BadReasonsManager)getBean("badReasonsManager", request);
/*     */   }
/*     */   
/*     */   public static BasicPartLocationManager getBasicPartLocationManager(HttpServletRequest request) {
/* 327 */     return (BasicPartLocationManager)getBean("basicPartLocationManager", request);
/*     */   }
/*     */   
/*     */   public static InventoryManager getInventoryManager(HttpServletRequest request) {
/* 331 */     return (InventoryManager)getBean("inventoryManager", request);
/*     */   }
/*     */   
/*     */   public static ProductManager getProductManager(HttpServletRequest request) {
/* 335 */     return (ProductManager)getBean("productManager", request);
/*     */   }
/*     */   
/*     */   public static BoxAdjustmentManager getBoxAdjustmentManager(HttpServletRequest request) {
/* 339 */     return (BoxAdjustmentManager)getBean("boxAdjustmentManager", request);
/*     */   }
/*     */   
/*     */   public static WmsUWManager getWmsUWManager(HttpServletRequest request) {
/* 343 */     return (WmsUWManager)getBean("wmsUWManager", request);
/*     */   }
/*     */   
/*     */   public static WmsPlanToGoOutManager getWmsPlanToGoOutManager(HttpServletRequest request) {
/* 347 */     return (WmsPlanToGoOutManager)getBean("wmsPlanToGoOutManager", request);
/*     */   }
/*     */   
/*     */   public static InventoryMovingManager getInventoryMovingManager(HttpServletRequest request) {
/* 351 */     return (InventoryMovingManager)getBean("inventoryMovingManager", request);
/*     */   }
/*     */   
/*     */   public static ScanLogManager getScanLogManager(HttpServletRequest request) {
/* 355 */     return (ScanLogManager)getBean("scanLogManager", request);
/*     */   }
/*     */   
/*     */   public static ProduceRejectedMaterialManager getProduceRejectedMaterialManager(HttpServletRequest request) {
/* 359 */     return (ProduceRejectedMaterialManager)getBean("produceRejectedMaterialManager", request);
/*     */   }
/*     */   
/*     */   public static PartDecompositionManager getPartDecompositionManager(HttpServletRequest request) {
/* 363 */     return (PartDecompositionManager)getBean("partDecompositionManager", request);
/*     */   }
/*     */   
/*     */   public static UselessPartManager getUselessPartManager(HttpServletRequest request) {
/* 367 */     return (UselessPartManager)getBean("uselessPartManager", request);
/*     */   }
/*     */   
/*     */   public static InventoryTransitManager getInventoryTransitManager(HttpServletRequest request) {
/* 371 */     return (InventoryTransitManager)getBean("inventoryTransitManager", request);
/*     */   }
/*     */   
/*     */   public static BomManager getBomManager(HttpServletRequest request) {
/* 375 */     return (BomManager)getBean("bomManager", request);
/*     */   }
/*     */   
/*     */   public static ProduceBuckleMaterialManager getProduceBuckleMaterialManager(HttpServletRequest request) {
/* 379 */     return (ProduceBuckleMaterialManager)getBean("produceBuckleMaterialManager", request);
/*     */   }
/*     */   
/*     */   public static PoHighLineManager getPoHighLineManager(HttpServletRequest request) {
/* 383 */     return (PoHighLineManager)getBean("poHighLineManager", request);
/*     */   }
/*     */   
/*     */   public static StockingManager getStockingManager(HttpServletRequest request) {
/* 387 */     return (StockingManager)getBean("stockingManager", request);
/*     */   }
/*     */   
/*     */   public static ExpensesCourseManager getExpensesCourseManager(HttpServletRequest request) {
/* 391 */     return (ExpensesCourseManager)getBean("expensesCourseManager", request);
/*     */   }
/*     */   
/*     */   public static CostCenterManager getCostCenterManager(HttpServletRequest request) {
/* 395 */     return (CostCenterManager)getBean("costCenterManager", request);
/*     */   }
/*     */   
/*     */   public static RedMinuteSyncJob getRedMinuteSyncJobManager(HttpServletRequest request) {
/* 399 */     return (RedMinuteSyncJob)getBean("redMinuteSyncJobManager", request);
/*     */   }
/*     */   
/*     */   public static DeliverMinuteSyncJob getDeliverMinuteSyncJobManager(HttpServletRequest request) {
/* 403 */     return (DeliverMinuteSyncJob)getBean("deliverMinuteSyncJobManager", request);
/*     */   }
/*     */   
/*     */   public static UnplannedReasonsManager getUnplannedReasonsManager(HttpServletRequest request) {
/* 407 */     return (UnplannedReasonsManager)getBean("unplannedReasonsManager", request);
/*     */   }
/*     */   
/*     */   public static ReportEntersSellsSavesManager getReportEntersSellsSavesManager(HttpServletRequest request) {
/* 411 */     return (ReportEntersSellsSavesManager)getBean("reportEntersSellsSavesManager", request);
/*     */   }
/*     */   
/*     */   public static ProductBelowLineManager getProductBelowLineManager(HttpServletRequest request) {
/* 415 */     return (ProductBelowLineManager)getBean("productBelowLineManager", request);
/*     */   }
/*     */   
/*     */   public static ProductBelowlineCasadeManager getProductBelowlineCasadeManager(HttpServletRequest request) {
/* 419 */     return (ProductBelowlineCasadeManager)getBean("productBelowlineCasadeManager", request);
/*     */   }
/*     */   
/*     */   public static FinishedSaiheRelationManager getFinishedSaiheRelationManager(HttpServletRequest request) {
/* 423 */     return (FinishedSaiheRelationManager)getBean("finishedSaiheRelationManager", request);
/*     */   }
/*     */   
/*     */   public static FinishedToolPutnumberManager getFinishedToolPutnumberManager(HttpServletRequest request) {
/* 427 */     return (FinishedToolPutnumberManager)getBean("finishedToolPutnumberManager", request);
/*     */   }
/*     */   
/*     */   public static ProductGolineManager getProductGolineManager(HttpServletRequest request) {
/* 431 */     return (ProductGolineManager)getBean("productGolineManager", request);
/*     */   }
/*     */   
/*     */   public static ProductOutStorageManager getProductOutStorageManager(HttpServletRequest request) {
/* 435 */     return (ProductOutStorageManager)getBean("productOutStorageManager", request);
/*     */   }
/*     */   
/*     */   public static ProductOutGolineManager getProductOutGolineManager(HttpServletRequest request) {
/* 439 */     return (ProductOutGolineManager)getBean("productOutGolineManager", request);
/*     */   }
/*     */ 
/*     */   
/*     */   public static BasicCustomerManager getBasicCustomerManager(HttpServletRequest request) {
/* 444 */     return (BasicCustomerManager)getBean("basicCustomerManager", request);
/*     */   }
/*     */   public static CustomerPlanManager getCustomerPlanManager(HttpServletRequest request) {
/* 447 */     return (CustomerPlanManager)getBean("customerPlanManager", request);
/*     */   }
/*     */   
/*     */   public static DailyProductPlanManager getDailyProductPlanManager(HttpServletRequest request) {
/* 451 */     return (DailyProductPlanManager)getBean("dailyProductPlanManager", request);
/*     */   }
/*     */   
/*     */   public static SalesOrderManager getSalesOrderManager(HttpServletRequest request) {
/* 455 */     return (SalesOrderManager)getBean("salesOrderManager", request);
/*     */   }
/*     */   
/*     */   public static WorkOrderBomManager getWorkOrderBomManager(HttpServletRequest request) {
/* 459 */     return (WorkOrderBomManager)getBean("workOrderBomManager", request);
/*     */   }
/*     */   public static SalesOrderItemManager getSalesOrderItemManager(HttpServletRequest request) {
/* 462 */     return (SalesOrderItemManager)getBean("salesOrderItemManager", request);
/*     */   }
/*     */   
/*     */   public static SalesPreshiporderManager getSalesPreshiporderManager(HttpServletRequest request) {
/* 466 */     return (SalesPreshiporderManager)getBean("salesPreshiporderManager", request);
/*     */   }
/*     */   
/*     */   public static SalesPreshiporderItemManager getSalesPreshiporderItemManager(HttpServletRequest request) {
/* 470 */     return (SalesPreshiporderItemManager)getBean("salesPreshiporderItemManager", request);
/*     */   }
/*     */   
/*     */   public static SalesPreshiporderBatchManager getSalesPreshiporderBatchManager(HttpServletRequest request) {
/* 474 */     return (SalesPreshiporderBatchManager)getBean("salesPreshiporderBatchManager", request);
/*     */   }
/*     */   
/*     */   public static SalesWorkorderManager getSalesWorkorderManager(HttpServletRequest request) {
/* 478 */     return (SalesWorkorderManager)getBean("salesWorkorderManager", request);
/*     */   }
/*     */   
/*     */   public static PortalShipOrderManager getPortalShipOrderManager(HttpServletRequest request) {
/* 482 */     return (PortalShipOrderManager)getBean("portalShipOrderManager", request);
/*     */   }
/*     */   
/*     */   public static CustomerreturnsManager getCustomerreturnsManager(HttpServletRequest request) {
/* 486 */     return (CustomerreturnsManager)getBean("customerreturnsManager", request);
/*     */   }
/*     */   
/*     */   public static CustomerReturnItemManager getCustomerReturnItemManager(HttpServletRequest request) {
/* 490 */     return (CustomerReturnItemManager)getBean("customerReturnItemManager", request);
/*     */   }
/*     */   
/*     */   public static BasicPartPriceManager getBasicPartPriceManager(HttpServletRequest request) {
/* 494 */     return (BasicPartPriceManager)getBean("basicPartPriceManager", request);
/*     */   }
/*     */   public static BasicDataViewManager getBasicDataViewManager(HttpServletRequest request) {
/* 497 */     return (BasicDataViewManager)getBean("basicDataViewManager", request);
/*     */   }
/*     */   
/*     */   public static EdiProductionManager getEdiProductionManager(HttpServletRequest request) {
/* 501 */     return (EdiProductionManager)getBean("ediProductionManager", request);
/*     */   }
/*     */ 
/*     */   
/*     */   public static QadOrEdiManager getQadOrEdiManager(HttpServletRequest request) {
/* 506 */     return (QadOrEdiManager)getBean("qadOrEdiManager", request);
/*     */   }
/*     */   
/*     */   public static JitProductionPlanManager getJitProductionPlanManager(HttpServletRequest request) {
/* 510 */     return (JitProductionPlanManager)getBean("jitProductionPlanManager", request);
/*     */   }
/*     */   public static ProductionplanningManager getProductionplanningManager(HttpServletRequest request) {
/* 513 */     return (ProductionplanningManager)getBean("productionPlanningManager", request);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ProjectedInventoryManager getProjectedInventoryManager(HttpServletRequest request) {
/* 518 */     return (ProjectedInventoryManager)getBean("projectedInventoryManager", request);
/*     */   }
/*     */   
/*     */   public static NjitNpoPlanManager getNjitNpoPlanManager(HttpServletRequest request) {
/* 522 */     return (NjitNpoPlanManager)getBean("njitNpoPlanManager", request);
/*     */   }
/*     */   public static Portalv6MrpPartPlanViewManager getPortalv6MrpPartPlanViewManager(HttpServletRequest request) {
/* 525 */     return (Portalv6MrpPartPlanViewManager)getBean("portalv6MrpPartPlanViewManager", request);
/*     */   }
/*     */   
/*     */   public static JitProductionPlanHistoryManager getJitProductionPlanHistoryManager(HttpServletRequest request) {
/* 529 */     return (JitProductionPlanHistoryManager)getBean("jitProductionPlanHistoryManager", request);
/*     */   }
/*     */   public static ProductionDayPlanManager getProductionDayPlanManager(HttpServletRequest request) {
/* 532 */     return (ProductionDayPlanManager)getBean("productionDayPlanManager", request);
/*     */   }
/*     */   
/*     */   public static Production72HourPlanManager getProduction72HourPlanManager(HttpServletRequest request) {
/* 536 */     return (Production72HourPlanManager)getBean("production72HourPlanManager", request);
/*     */   }
/*     */   
/*     */   public static PurchaseManualCreateBarcodeManager getPurchaseManualCreateBarcodeManager(HttpServletRequest request) {
/* 540 */     return (PurchaseManualCreateBarcodeManager)getBean("purchaseManualCreateBarcodeManager", request);
/*     */   }
/*     */   
/*     */   public static EdiProductionErrorLogManager getEdiProductionErrorLogManager(HttpServletRequest request) {
/* 544 */     return (EdiProductionErrorLogManager)getBean("ediProductionErrorLogManager", request);
/*     */   }
/*     */ 
/*     */   
/*     */   public static SycSleepTimeManager getSycSleepTimeManager(HttpServletRequest request) {
/* 549 */     return (SycSleepTimeManager)getBean("sycSleepTimeManager", request);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/ServiceLocator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */