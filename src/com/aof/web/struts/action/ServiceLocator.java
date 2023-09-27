package com.aof.web.struts.action;

import com.aof.service.Product.BasicCustomerManager;
import com.aof.service.Product.CustomerPlanManager;
import com.aof.service.Product.DailyProductPlanManager;
import com.aof.service.Product.ProductBelowLineManager;
import com.aof.service.Product.ProductBelowlineCasadeManager;
import com.aof.service.Product.ProductGolineManager;
import com.aof.service.Product.ProductManager;
import com.aof.service.Product.ProductOutGolineManager;
import com.aof.service.Product.ProductOutStorageManager;
import com.aof.service.Product.SalesOrderItemManager;
import com.aof.service.Product.SalesOrderManager;
import com.aof.service.Product.SalesPreshiporderBatchManager;
import com.aof.service.Product.SalesPreshiporderItemManager;
import com.aof.service.Product.SalesPreshiporderManager;
import com.aof.service.Product.SalesWorkorderManager;
import com.aof.service.Product.WorkOrderBomManager;
import com.aof.service.admin.CityManager;
import com.aof.service.admin.CountryManager;
import com.aof.service.admin.CurrencyManager;
import com.aof.service.admin.CustomerManager;
import com.aof.service.admin.DataTransferManager;
import com.aof.service.admin.DepartmentManager;
import com.aof.service.admin.EmailManager;
import com.aof.service.admin.ExchangeRateManager;
import com.aof.service.admin.ExpenseCategoryManager;
import com.aof.service.admin.ExpenseSubCategoryManager;
import com.aof.service.admin.FinishedSaiheRelationManager;
import com.aof.service.admin.FinishedToolPutnumberManager;
import com.aof.service.admin.FunctionManager;
import com.aof.service.admin.GlobalManager;
import com.aof.service.admin.HotelContractManager;
import com.aof.service.admin.HotelManager;
import com.aof.service.admin.InventoryMovingManager;
import com.aof.service.admin.MenuManager;
import com.aof.service.admin.MetadataManager;
import com.aof.service.admin.ParameterManager;
import com.aof.service.admin.PriceManager;
import com.aof.service.admin.ProvinceManager;
import com.aof.service.admin.PurchaseCategoryManager;
import com.aof.service.admin.PurchaseSubCategoryManager;
import com.aof.service.admin.PurchaseTypeManager;
import com.aof.service.admin.RoleManager;
import com.aof.service.admin.SiteManager;
import com.aof.service.admin.SupplierContractManager;
import com.aof.service.admin.SupplierHistoryManager;
import com.aof.service.admin.SupplierItemManager;
import com.aof.service.admin.SupplierManager;
import com.aof.service.admin.SystemLogManager;
import com.aof.service.admin.UserManager;
import com.aof.service.basic.BadReasonsManager;
import com.aof.service.basic.BasicPartLocationManager;
import com.aof.service.basic.BasicPartPriceManager;
import com.aof.service.basic.CostCenterManager;
import com.aof.service.basic.CustomerReturnItemManager;
import com.aof.service.basic.CustomerreturnsManager;
import com.aof.service.basic.ExpensesCourseManager;
import com.aof.service.basic.PartDecompositionManager;
import com.aof.service.basic.ReportEntersSellsSavesManager;
import com.aof.service.basic.ScanLogManager;
import com.aof.service.basic.StorageLocationManager;
import com.aof.service.basic.StoreRoomManager;
import com.aof.service.basic.SycSleepTimeManager;
import com.aof.service.basic.UnplannedReasonsManager;
import com.aof.service.basic.UselessPartManager;
import com.aof.service.basic.WmsPartManager;
import com.aof.service.basic.WmsToolManager;
import com.aof.service.basicDataView.BasicDataViewManager;
import com.aof.service.business.rule.ApproverDelegateManager;
import com.aof.service.business.rule.FlowManager;
import com.aof.service.business.rule.RuleManager;
import com.aof.service.comprehensive.BomManager;
import com.aof.service.comprehensive.BoxAdjustmentManager;
import com.aof.service.comprehensive.StockingManager;
import com.aof.service.inventory.InventoryManager;
import com.aof.service.inventory.InventoryTransitManager;
import com.aof.service.kpi.KPIManager;
import com.aof.service.plantWarehouse.WmsPlanToGoOutManager;
import com.aof.service.plantWarehouse.WmsUWManager;
import com.aof.service.po.BoxManager;
import com.aof.service.po.PoHighLineManager;
import com.aof.service.po.PortalShipOrderItemManager;
import com.aof.service.po.PortalShipOrderManager;
import com.aof.service.po.ProduceBuckleMaterialManager;
import com.aof.service.po.ProduceRejectedMaterialManager;
import com.aof.service.po.ProductionplanningManager;
import com.aof.service.po.PurchaseManualCreateBarcodeManager;
import com.aof.service.po.PurchaseOrderCondimentSingleManager;
import com.aof.service.po.PurchaseOrderInspectionPendingManager;
import com.aof.service.po.PurchaseOrderManager;
import com.aof.service.po.PurchaseOrderPutInStorageManager;
import com.aof.service.po.PurchaseOrderRQCManager;
import com.aof.service.po.PurchaseOrderReceiptsManager;
import com.aof.service.quartz.job.DeliverMinuteSyncJob;
import com.aof.service.quartz.job.RedMinuteSyncJob;
import com.aof.service.schedule.EdiProductionErrorLogManager;
import com.aof.service.schedule.EdiProductionManager;
import com.aof.service.schedule.JitProductionPlanHistoryManager;
import com.aof.service.schedule.JitProductionPlanManager;
import com.aof.service.schedule.NjitNpoPlanManager;
import com.aof.service.schedule.Portalv6MrpPartPlanViewManager;
import com.aof.service.schedule.Production72HourPlanManager;
import com.aof.service.schedule.ProductionDayPlanManager;
import com.aof.service.schedule.ProjectedInventoryManager;
import com.aof.service.schedule.QadOrEdiManager;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ServiceLocator {
    protected static Object getBean(String name, HttpServletRequest request) {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession()
                .getServletContext());
        return webApplicationContext.getBean(name);
    }

    public static BeanLoader getBeanLoader(HttpServletRequest request) {
        return (BeanLoader)getBean("beanLoader", request);
    }

    public static SiteManager getSiteManager(HttpServletRequest request) {
        return (SiteManager)getBean("siteManager", request);
    }

    public static FunctionManager getFunctionManager(HttpServletRequest request) {
        return (FunctionManager)getBean("functionManager", request);
    }

    public static DepartmentManager getDepartmentManager(HttpServletRequest request) {
        return (DepartmentManager)getBean("departmentManager", request);
    }

    public static UserManager getUserManager(HttpServletRequest request) {
        return (UserManager)getBean("userManager", request);
    }

    public static ExpenseSubCategoryManager getExpenseSubCategoryManager(HttpServletRequest request) {
        return (ExpenseSubCategoryManager)getBean("expenseSubCategoryManager", request);
    }

    public static ExpenseCategoryManager getExpenseCategoryManager(HttpServletRequest request) {
        return (ExpenseCategoryManager)getBean("expenseCategoryManager", request);
    }

    public static CityManager getCityManager(HttpServletRequest request) {
        return (CityManager)getBean("cityManager", request);
    }

    public static ProvinceManager getProvinceManager(HttpServletRequest request) {
        return (ProvinceManager)getBean("provinceManager", request);
    }

    public static CountryManager getCountryManager(HttpServletRequest request) {
        return (CountryManager)getBean("countryManager", request);
    }

    public static CurrencyManager getCurrencyManager(HttpServletRequest request) {
        return (CurrencyManager)getBean("currencyManager", request);
    }

    public static EmailManager getEmailManager(HttpServletRequest request) {
        return (EmailManager)getBean("emailManager", request);
    }

    public static ExchangeRateManager getExchangeRateManager(HttpServletRequest request) {
        return (ExchangeRateManager)getBean("exchangeRateManager", request);
    }

    public static MenuManager getMenuManager(HttpServletRequest request) {
        return (MenuManager)getBean("menuManager", request);
    }

    public static MetadataManager getMetadataManager(HttpServletRequest request) {
        return (MetadataManager)getBean("metadataManager", request);
    }

    public static GlobalManager getGlobalManager(HttpServletRequest request) {
        return (GlobalManager)getBean("globalManager", request);
    }

    public static RoleManager getRoleManager(HttpServletRequest request) {
        return (RoleManager)getBean("roleManager", request);
    }

    public static PurchaseCategoryManager getPurchaseCategoryManager(HttpServletRequest request) {
        return (PurchaseCategoryManager)getBean("purchaseCategoryManager", request);
    }

    public static PurchaseSubCategoryManager getPurchaseSubCategoryManager(HttpServletRequest request) {
        return (PurchaseSubCategoryManager)getBean("purchaseSubCategoryManager", request);
    }

    public static SupplierManager getSupplierManager(HttpServletRequest request) {
        return (SupplierManager)getBean("supplierManager", request);
    }

    public static PriceManager getPriceManager(HttpServletRequest request) {
        return (PriceManager)getBean("priceManager", request);
    }

    public static SupplierHistoryManager getSupplierHistoryManager(HttpServletRequest request) {
        return (SupplierHistoryManager)getBean("supplierHistoryManager", request);
    }

    public static RuleManager getRuleManager(HttpServletRequest request) {
        return (RuleManager)getBean("ruleManager", request);
    }

    public static FlowManager getFlowManager(HttpServletRequest request) {
        return (FlowManager)getBean("flowManager", request);
    }

    public static SupplierContractManager getSupplierContractManager(HttpServletRequest request) {
        return (SupplierContractManager)getBean("supplierContractManager", request);
    }

    public static SupplierItemManager getSupplierItemManager(HttpServletRequest request) {
        return (SupplierItemManager)getBean("supplierItemManager", request);
    }

    public static ApproverDelegateManager getApproverDelegateManager(HttpServletRequest request) {
        return (ApproverDelegateManager)getBean("approverDelegateManager", request);
    }

    public static CustomerManager getCustomerManager(HttpServletRequest request) {
        return (CustomerManager)getBean("customerManager", request);
    }

    public static PurchaseTypeManager getPurchaseTypeManager(HttpServletRequest request) {
        return (PurchaseTypeManager)getBean("purchaseTypeManager", request);
    }

    public static ParameterManager getParameterManager(HttpServletRequest request) {
        return (ParameterManager)getBean("parameterManager", request);
    }

    public static DataTransferManager getDataTransferManager(HttpServletRequest request) {
        return (DataTransferManager)getBean("dataTransferManager", request);
    }

    public static SystemLogManager getSystemLogManager(HttpServletRequest request) {
        return (SystemLogManager)getBean("systemLogManager", request);
    }

    public static KPIManager getKPIManager(HttpServletRequest request) {
        return (KPIManager)getBean("kpiManager", request);
    }

    public static HotelManager getHotelManager(HttpServletRequest request) {
        return (HotelManager)getBean("hotelManager", request);
    }

    public static HotelContractManager getHotelContractManager(HttpServletRequest request) {
        return (HotelContractManager)getBean("hotelContractManager", request);
    }

    public static PurchaseOrderManager getPurchaseOrderManager(HttpServletRequest request) {
        return (PurchaseOrderManager)getBean("purchaseOrderManager", request);
    }

    public static BoxManager getBoxManager(HttpServletRequest request) {
        return (BoxManager)getBean("boxManager", request);
    }

    public static PurchaseOrderReceiptsManager getPurchaseOrderReceiptsManager(HttpServletRequest request) {
        return (PurchaseOrderReceiptsManager)getBean("purchaseOrderReceiptsManager", request);
    }

    public static PurchaseOrderPutInStorageManager getPurchaseOrderPutInStorageManager(HttpServletRequest request) {
        return (PurchaseOrderPutInStorageManager)getBean("purchaseOrderPutInStorageManager", request);
    }

    public static PurchaseOrderRQCManager getPurchaseOrderRQCManager(HttpServletRequest request) {
        return (PurchaseOrderRQCManager)getBean("purchaseOrderRQCManager", request);
    }

    public static WmsPartManager getWmsPartManager(HttpServletRequest request) {
        return (WmsPartManager)getBean("wmsPartManager", request);
    }

    public static StorageLocationManager getStorageLocationManager(HttpServletRequest request) {
        return (StorageLocationManager)getBean("storageLocationManager", request);
    }

    public static StoreRoomManager getStoreRoomManager(HttpServletRequest request) {
        return (StoreRoomManager)getBean("storeRoomManager", request);
    }

    public static PurchaseOrderInspectionPendingManager getPurchaseOrderInspectionPendingManager(HttpServletRequest request) {
        return (PurchaseOrderInspectionPendingManager)getBean("purchaseOrderInspectionPendingManager", request);
    }

    public static PortalShipOrderItemManager getPortalShipOrderItemManager(HttpServletRequest request) {
        return (PortalShipOrderItemManager)getBean("portalShipOrderItemManager", request);
    }

    public static PurchaseOrderCondimentSingleManager getPurchaseOrderCondimentSingleManager(HttpServletRequest request) {
        return (PurchaseOrderCondimentSingleManager)getBean("purchaseOrderCondimentSingleManager", request);
    }

    public static WmsToolManager getWmsToolManager(HttpServletRequest request) {
        return (WmsToolManager)getBean("wmsToolManager", request);
    }

    public static BadReasonsManager getBadReasonsManager(HttpServletRequest request) {
        return (BadReasonsManager)getBean("badReasonsManager", request);
    }

    public static BasicPartLocationManager getBasicPartLocationManager(HttpServletRequest request) {
        return (BasicPartLocationManager)getBean("basicPartLocationManager", request);
    }

    public static InventoryManager getInventoryManager(HttpServletRequest request) {
        return (InventoryManager)getBean("inventoryManager", request);
    }

    public static ProductManager getProductManager(HttpServletRequest request) {
        return (ProductManager)getBean("productManager", request);
    }

    public static BoxAdjustmentManager getBoxAdjustmentManager(HttpServletRequest request) {
        return (BoxAdjustmentManager)getBean("boxAdjustmentManager", request);
    }

    public static WmsUWManager getWmsUWManager(HttpServletRequest request) {
        return (WmsUWManager)getBean("wmsUWManager", request);
    }

    public static WmsPlanToGoOutManager getWmsPlanToGoOutManager(HttpServletRequest request) {
        return (WmsPlanToGoOutManager)getBean("wmsPlanToGoOutManager", request);
    }

    public static InventoryMovingManager getInventoryMovingManager(HttpServletRequest request) {
        return (InventoryMovingManager)getBean("inventoryMovingManager", request);
    }

    public static ScanLogManager getScanLogManager(HttpServletRequest request) {
        return (ScanLogManager)getBean("scanLogManager", request);
    }

    public static ProduceRejectedMaterialManager getProduceRejectedMaterialManager(HttpServletRequest request) {
        return (ProduceRejectedMaterialManager)getBean("produceRejectedMaterialManager", request);
    }

    public static PartDecompositionManager getPartDecompositionManager(HttpServletRequest request) {
        return (PartDecompositionManager)getBean("partDecompositionManager", request);
    }

    public static UselessPartManager getUselessPartManager(HttpServletRequest request) {
        return (UselessPartManager)getBean("uselessPartManager", request);
    }

    public static InventoryTransitManager getInventoryTransitManager(HttpServletRequest request) {
        return (InventoryTransitManager)getBean("inventoryTransitManager", request);
    }

    public static BomManager getBomManager(HttpServletRequest request) {
        return (BomManager)getBean("bomManager", request);
    }

    public static ProduceBuckleMaterialManager getProduceBuckleMaterialManager(HttpServletRequest request) {
        return (ProduceBuckleMaterialManager)getBean("produceBuckleMaterialManager", request);
    }

    public static PoHighLineManager getPoHighLineManager(HttpServletRequest request) {
        return (PoHighLineManager)getBean("poHighLineManager", request);
    }

    public static StockingManager getStockingManager(HttpServletRequest request) {
        return (StockingManager)getBean("stockingManager", request);
    }

    public static ExpensesCourseManager getExpensesCourseManager(HttpServletRequest request) {
        return (ExpensesCourseManager)getBean("expensesCourseManager", request);
    }

    public static CostCenterManager getCostCenterManager(HttpServletRequest request) {
        return (CostCenterManager)getBean("costCenterManager", request);
    }

    public static RedMinuteSyncJob getRedMinuteSyncJobManager(HttpServletRequest request) {
        return (RedMinuteSyncJob)getBean("redMinuteSyncJobManager", request);
    }

    public static DeliverMinuteSyncJob getDeliverMinuteSyncJobManager(HttpServletRequest request) {
        return (DeliverMinuteSyncJob)getBean("deliverMinuteSyncJobManager", request);
    }

    public static UnplannedReasonsManager getUnplannedReasonsManager(HttpServletRequest request) {
        return (UnplannedReasonsManager)getBean("unplannedReasonsManager", request);
    }

    public static ReportEntersSellsSavesManager getReportEntersSellsSavesManager(HttpServletRequest request) {
        return (ReportEntersSellsSavesManager)getBean("reportEntersSellsSavesManager", request);
    }

    public static ProductBelowLineManager getProductBelowLineManager(HttpServletRequest request) {
        return (ProductBelowLineManager)getBean("productBelowLineManager", request);
    }

    public static ProductBelowlineCasadeManager getProductBelowlineCasadeManager(HttpServletRequest request) {
        return (ProductBelowlineCasadeManager)getBean("productBelowlineCasadeManager", request);
    }

    public static FinishedSaiheRelationManager getFinishedSaiheRelationManager(HttpServletRequest request) {
        return (FinishedSaiheRelationManager)getBean("finishedSaiheRelationManager", request);
    }

    public static FinishedToolPutnumberManager getFinishedToolPutnumberManager(HttpServletRequest request) {
        return (FinishedToolPutnumberManager)getBean("finishedToolPutnumberManager", request);
    }

    public static ProductGolineManager getProductGolineManager(HttpServletRequest request) {
        return (ProductGolineManager)getBean("productGolineManager", request);
    }

    public static ProductOutStorageManager getProductOutStorageManager(HttpServletRequest request) {
        return (ProductOutStorageManager)getBean("productOutStorageManager", request);
    }

    public static ProductOutGolineManager getProductOutGolineManager(HttpServletRequest request) {
        return (ProductOutGolineManager)getBean("productOutGolineManager", request);
    }


    public static BasicCustomerManager getBasicCustomerManager(HttpServletRequest request) {
        return (BasicCustomerManager)getBean("basicCustomerManager", request);
    }
    public static CustomerPlanManager getCustomerPlanManager(HttpServletRequest request) {
        return (CustomerPlanManager)getBean("customerPlanManager", request);
    }

    public static DailyProductPlanManager getDailyProductPlanManager(HttpServletRequest request) {
        return (DailyProductPlanManager)getBean("dailyProductPlanManager", request);
    }

    public static SalesOrderManager getSalesOrderManager(HttpServletRequest request) {
        return (SalesOrderManager)getBean("salesOrderManager", request);
    }

    public static WorkOrderBomManager getWorkOrderBomManager(HttpServletRequest request) {
        return (WorkOrderBomManager)getBean("workOrderBomManager", request);
    }
    public static SalesOrderItemManager getSalesOrderItemManager(HttpServletRequest request) {
        return (SalesOrderItemManager)getBean("salesOrderItemManager", request);
    }

    public static SalesPreshiporderManager getSalesPreshiporderManager(HttpServletRequest request) {
        return (SalesPreshiporderManager)getBean("salesPreshiporderManager", request);
    }

    public static SalesPreshiporderItemManager getSalesPreshiporderItemManager(HttpServletRequest request) {
        return (SalesPreshiporderItemManager)getBean("salesPreshiporderItemManager", request);
    }

    public static SalesPreshiporderBatchManager getSalesPreshiporderBatchManager(HttpServletRequest request) {
        return (SalesPreshiporderBatchManager)getBean("salesPreshiporderBatchManager", request);
    }

    public static SalesWorkorderManager getSalesWorkorderManager(HttpServletRequest request) {
        return (SalesWorkorderManager)getBean("salesWorkorderManager", request);
    }

    public static PortalShipOrderManager getPortalShipOrderManager(HttpServletRequest request) {
        return (PortalShipOrderManager)getBean("portalShipOrderManager", request);
    }

    public static CustomerreturnsManager getCustomerreturnsManager(HttpServletRequest request) {
        return (CustomerreturnsManager)getBean("customerreturnsManager", request);
    }

    public static CustomerReturnItemManager getCustomerReturnItemManager(HttpServletRequest request) {
        return (CustomerReturnItemManager)getBean("customerReturnItemManager", request);
    }

    public static BasicPartPriceManager getBasicPartPriceManager(HttpServletRequest request) {
        return (BasicPartPriceManager)getBean("basicPartPriceManager", request);
    }
    public static BasicDataViewManager getBasicDataViewManager(HttpServletRequest request) {
        return (BasicDataViewManager)getBean("basicDataViewManager", request);
    }

    public static EdiProductionManager getEdiProductionManager(HttpServletRequest request) {
        return (EdiProductionManager)getBean("ediProductionManager", request);
    }


    public static QadOrEdiManager getQadOrEdiManager(HttpServletRequest request) {
        return (QadOrEdiManager)getBean("qadOrEdiManager", request);
    }

    public static JitProductionPlanManager getJitProductionPlanManager(HttpServletRequest request) {
        return (JitProductionPlanManager)getBean("jitProductionPlanManager", request);
    }
    public static ProductionplanningManager getProductionplanningManager(HttpServletRequest request) {
        return (ProductionplanningManager)getBean("productionPlanningManager", request);
    }


    public static ProjectedInventoryManager getProjectedInventoryManager(HttpServletRequest request) {
        return (ProjectedInventoryManager)getBean("projectedInventoryManager", request);
    }

    public static NjitNpoPlanManager getNjitNpoPlanManager(HttpServletRequest request) {
        return (NjitNpoPlanManager)getBean("njitNpoPlanManager", request);
    }
    public static Portalv6MrpPartPlanViewManager getPortalv6MrpPartPlanViewManager(HttpServletRequest request) {
        return (Portalv6MrpPartPlanViewManager)getBean("portalv6MrpPartPlanViewManager", request);
    }

    public static JitProductionPlanHistoryManager getJitProductionPlanHistoryManager(HttpServletRequest request) {
        return (JitProductionPlanHistoryManager)getBean("jitProductionPlanHistoryManager", request);
    }
    public static ProductionDayPlanManager getProductionDayPlanManager(HttpServletRequest request) {
        return (ProductionDayPlanManager)getBean("productionDayPlanManager", request);
    }

    public static Production72HourPlanManager getProduction72HourPlanManager(HttpServletRequest request) {
        return (Production72HourPlanManager)getBean("production72HourPlanManager", request);
    }

    public static PurchaseManualCreateBarcodeManager getPurchaseManualCreateBarcodeManager(HttpServletRequest request) {
        return (PurchaseManualCreateBarcodeManager)getBean("purchaseManualCreateBarcodeManager", request);
    }

    public static EdiProductionErrorLogManager getEdiProductionErrorLogManager(HttpServletRequest request) {
        return (EdiProductionErrorLogManager)getBean("ediProductionErrorLogManager", request);
    }


    public static SycSleepTimeManager getSycSleepTimeManager(HttpServletRequest request) {
        return (SycSleepTimeManager)getBean("sycSleepTimeManager", request);
    }
}

