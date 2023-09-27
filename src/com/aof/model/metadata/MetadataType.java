/*     */ package com.aof.model.metadata;
/*     */ 
/*     */ import com.shcnc.hibernate.PersistentIntegerEnum;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MetadataType
/*     */   extends PersistentIntegerEnum
/*     */ {
/*  14 */   public static final MetadataType YES_NO = new MetadataType(100001, "Yes or No");
/*  15 */   public static final MetadataType GENDER = new MetadataType(100002, "Gender");
/*  16 */   public static final MetadataType ENABLED_DISABLED = new MetadataType(100003, "Enabled or Disabled");
/*  17 */   public static final MetadataType ROLE_TYPE = new MetadataType(100101, "Role Type");
/*  18 */   public static final MetadataType ORGANIZATION_TYPE = new MetadataType(100102, "Organization Type");
/*  19 */   public static final MetadataType BUDGET_STATUS = new MetadataType(100103, "Budget Status");
/*  20 */   public static final MetadataType BUDGET_TYPE = new MetadataType(100104, "Budget Type");
/*  21 */   public static final MetadataType EXPENSE_TYPE = new MetadataType(100105, "Expense Type");
/*  22 */   public static final MetadataType EXPORT_STATUS = new MetadataType(100106, "Export Status");
/*  23 */   public static final MetadataType HOTEL_LEVEL = new MetadataType(100107, "Hotel Level");
/*  24 */   public static final MetadataType CUSTOMER_TYPE = new MetadataType(100108, "Customer Type");
/*  25 */   public static final MetadataType EXPORT_FILE_LINE_TYPE = new MetadataType(100109, "Export File Line Type");
/*  26 */   public static final MetadataType RECHARGE_TYPE = new MetadataType(100110, "Recharge Type");
/*  27 */   public static final MetadataType EXPORT_FILE_STATUS = new MetadataType(100111, "Export File Status");
/*  28 */   public static final MetadataType GLOBAL_MAIL_REMINDER_TYPE = new MetadataType(100112, "Global Mail Reminder Type");
/*  29 */   public static final MetadataType SITE_MAIL_REMINDER_TYPE = new MetadataType(100113, "Site Mail Reminder Type");
/*  30 */   public static final MetadataType HOTEL_PROMOTE_STATUS = new MetadataType(100114, "Hotel Promote Status");
/*  31 */   public static final MetadataType SUPPLIER_PROMOTE_STATUS = new MetadataType(100115, "Supplier Promote Status");
/*  32 */   public static final MetadataType CONTRACT_STATUS = new MetadataType(100116, "Contract Status");
/*  33 */   public static final MetadataType CAPEX_REQUEST_TYPE = new MetadataType(100117, "Capex Request Type");
/*  34 */   public static final MetadataType CAPEX_REQUEST_STATUS = new MetadataType(100118, "Capex Request Status");
/*  35 */   public static final MetadataType PURCHASE_REQUEST_STATUS = new MetadataType(100119, "Purchase Request Status");
/*  36 */   public static final MetadataType PURCHASE_ORDER_STATUS = new MetadataType(100120, "Purchase Order Status");
/*  37 */   public static final MetadataType SUPPLIER_CONFIRM_STATUS = new MetadataType(100121, "Supplier Confirm Status");
/*  38 */   public static final MetadataType APPROVE_STATUS = new MetadataType(100122, "Approve Status");
/*  39 */   public static final MetadataType APPROVER_DELEGATE_TYPE = new MetadataType(100123, "Approver Delegate Type");
/*  40 */   public static final MetadataType TRAVEL_APPLICATION_STATUS = new MetadataType(100124, "Travel Application Status");
/*  41 */   public static final MetadataType TRAVELLING_MODE = new MetadataType(100125, "Travelling Mode");
/*  42 */   public static final MetadataType SINGLE_RETURN = new MetadataType(100126, "Single or Return");
/*  43 */   public static final MetadataType FLIGHT_CLASS = new MetadataType(100127, "Flight Class");
/*  44 */   public static final MetadataType AIR_TICKET_STATUS = new MetadataType(100128, "Air Ticket Status");
/*  45 */   public static final MetadataType EXPENSE_STATUS = new MetadataType(100129, "Expense Status");
/*  46 */   public static final MetadataType CONDITION_TYPE = new MetadataType(100130, "Condition Type");
/*  47 */   public static final MetadataType CONDITION_COMPARE_TYPE = new MetadataType(100131, "Condition Compare Type");
/*  48 */   public static final MetadataType RULE_TYPE = new MetadataType(100132, "Rule Type");
/*  49 */   public static final MetadataType TRAVEL_APPLICATION_URGENT = new MetadataType(100133, "Travel Application Urgent");
/*  50 */   public static final MetadataType TRAVEL_APPLICATION_BOOK_STATUS = new MetadataType(100134, "Travel Application Book Status");
/*  51 */   public static final MetadataType TRAVEL_TYPE = new MetadataType(100135, "Travel Type");
/*  52 */   public static final MetadataType CONSEQUENCE_TYPE = new MetadataType(100136, "Consequence Type");
/*  53 */   public static final MetadataType EXPORT_FILE_TYPE = new MetadataType(100137, "Export File Type");
/*  54 */   public static final MetadataType PURCHASE_ORDER_RECEIPTS_TYPE = new MetadataType(100138, "Purchase Order Receipts Type");
/*  55 */   public static final MetadataType PURCHASE_ORDER_TYPE = new MetadataType(100139, "Purchase Order Type");
/*  56 */   public static final MetadataType PURCHASE_ORDER_RECEIPTS_STATUS = new MetadataType(100140, "Purchase Order Receipts Status");
/*  57 */   public static final MetadataType BOX_STATUS = new MetadataType(100141, "Box Status");
/*  58 */   public static final MetadataType BOX_STATUS_RQC = new MetadataType(100142, "Box Status Rqc");
/*  59 */   public static final MetadataType PURCHASEORDERCONDIMENTSINGLE_STATUS = new MetadataType(100143, "PurchaseOrderCondimentSingle Status");
/*  60 */   public static final MetadataType PRUCHASE_ORDER_RQC_STATUS = new MetadataType(100144, "Purchase Order Rqc Status");
/*  61 */   public static final MetadataType PRUCHASE_ORDER_PUTINSTORAGE_STATUS = new MetadataType(100145, "Purchase Order PutInStorage Status");
/*  62 */   public static final MetadataType STOREROOM_TYPE = new MetadataType(100146, "StoreRoom Type");
/*  63 */   public static final MetadataType INVENTORY_RECORD_TYPE = new MetadataType(100147, "Inventory Record Type");
/*  64 */   public static final MetadataType UNPLANNEDOUTBOUND_STATUS = new MetadataType(100148, "UnplannedOutbound Status");
/*  65 */   public static final MetadataType UNPLANNEDWAREHOUSING_STATUS = new MetadataType(100149, "UnplannedWarehousing Status");
/*  66 */   public static final MetadataType BOX_ADJUSTMENT_TYPE = new MetadataType(100150, "Box Adjustment Type");
/*  67 */   public static final MetadataType WMSPART_TYPE = new MetadataType(100151, "WmsPart Type");
/*  68 */   public static final MetadataType CURRENCY_TYPE = new MetadataType(100152, "Currency Type");
/*  69 */   public static final MetadataType PORTALSHIPORDER_STATUS = new MetadataType(100153, "PortalShipOrder Status");
/*  70 */   public static final MetadataType INVENTORYTYPE = new MetadataType(100154, "inventory type");
/*  71 */   public static final MetadataType WMSPLANTOGOOUTSTATUS = new MetadataType(100155, "wmsplan togoout status");
/*  72 */   public static final MetadataType WMSUNPLANNEDWAREHOUSING = new MetadataType(100156, "wmsunplan nedwareousing");
/*  73 */   public static final MetadataType POHIGHLINESTATUS = new MetadataType(100157, "poHighLine Status");
/*  74 */   public static final MetadataType BADREASONSTYPE = new MetadataType(100158, "badReasons type");
/*  75 */   public static final MetadataType BADREASONS_STATUS = new MetadataType(100159, "BadReasons Status");
/*  76 */   public static final MetadataType PRODUCTBELOWLINE_STATUS = new MetadataType(100160, "productBelowLine Status");
/*  77 */   public static final MetadataType PARTTYPE = new MetadataType(100161, "part Type");
/*  78 */   public static final MetadataType SALESPRESHIPORDER_STATUS = new MetadataType(100162, "SalesPreshiporder Status");
/*  79 */   public static final MetadataType SALESPRESHIPORDERBATCH_STATUS = new MetadataType(100163, "SalesPreshiporderBatch Status");
/*     */   
/*     */   static {
/*  82 */     YES_NO.detailClass = YesNo.class;
/*  83 */     GENDER.detailClass = Gender.class;
/*  84 */     ENABLED_DISABLED.detailClass = EnabledDisabled.class;
/*  85 */     ROLE_TYPE.detailClass = RoleType.class;
/*  86 */     ORGANIZATION_TYPE.detailClass = OrganizationType.class;
/*  87 */     BUDGET_STATUS.detailClass = BudgetStatus.class;
/*  88 */     BUDGET_TYPE.detailClass = BudgetType.class;
/*  89 */     EXPENSE_TYPE.detailClass = ExpenseType.class;
/*  90 */     EXPORT_STATUS.detailClass = ExportStatus.class;
/*  91 */     HOTEL_LEVEL.detailClass = HotelLevel.class;
/*  92 */     CUSTOMER_TYPE.detailClass = CustomerType.class;
/*  93 */     EXPORT_FILE_LINE_TYPE.detailClass = ExportFileLineType.class;
/*  94 */     RECHARGE_TYPE.detailClass = RechargeType.class;
/*  95 */     EXPORT_FILE_STATUS.detailClass = ExportFileStatus.class;
/*  96 */     GLOBAL_MAIL_REMINDER_TYPE.detailClass = GlobalMailReminderType.class;
/*  97 */     SITE_MAIL_REMINDER_TYPE.detailClass = SiteMailReminderType.class;
/*  98 */     HOTEL_PROMOTE_STATUS.detailClass = HotelPromoteStatus.class;
/*  99 */     SUPPLIER_PROMOTE_STATUS.detailClass = SupplierPromoteStatus.class;
/* 100 */     CONTRACT_STATUS.detailClass = ContractStatus.class;
/* 101 */     CAPEX_REQUEST_TYPE.detailClass = CapexRequestType.class;
/* 102 */     CAPEX_REQUEST_STATUS.detailClass = CapexRequestStatus.class;
/* 103 */     PURCHASE_REQUEST_STATUS.detailClass = PurchaseRequestStatus.class;
/* 104 */     PURCHASE_ORDER_STATUS.detailClass = PurchaseOrderStatus.class;
/* 105 */     SUPPLIER_CONFIRM_STATUS.detailClass = SupplierConfirmStatus.class;
/* 106 */     APPROVE_STATUS.detailClass = ApproveStatus.class;
/* 107 */     APPROVER_DELEGATE_TYPE.detailClass = ApproverDelegateType.class;
/* 108 */     TRAVEL_APPLICATION_STATUS.detailClass = TravelApplicationStatus.class;
/* 109 */     TRAVELLING_MODE.detailClass = TravellingMode.class;
/* 110 */     SINGLE_RETURN.detailClass = SingleReturn.class;
/* 111 */     FLIGHT_CLASS.detailClass = FlightClass.class;
/* 112 */     AIR_TICKET_STATUS.detailClass = AirTicketStatus.class;
/* 113 */     EXPENSE_STATUS.detailClass = ExpenseStatus.class;
/* 114 */     CONDITION_TYPE.detailClass = ConditionType.class;
/* 115 */     CONDITION_COMPARE_TYPE.detailClass = ConditionCompareType.class;
/* 116 */     RULE_TYPE.detailClass = RuleType.class;
/* 117 */     TRAVEL_APPLICATION_URGENT.detailClass = TravelApplicationUrgent.class;
/* 118 */     TRAVEL_APPLICATION_BOOK_STATUS.detailClass = TravelApplicationBookStatus.class;
/* 119 */     TRAVEL_TYPE.detailClass = TravelType.class;
/* 120 */     CONSEQUENCE_TYPE.detailClass = ConsequenceType.class;
/* 121 */     EXPORT_FILE_TYPE.detailClass = ExportFileType.class;
/* 122 */     PURCHASE_ORDER_RECEIPTS_TYPE.detailClass = PurchaseOrderReceiptsType.class;
/* 123 */     PURCHASE_ORDER_TYPE.detailClass = PurchaseOrderType.class;
/* 124 */     PURCHASE_ORDER_RECEIPTS_STATUS.detailClass = PurchaseOrderReceiptsStatus.class;
/* 125 */     BOX_STATUS.detailClass = BoxStatus.class;
/* 126 */     BOX_STATUS_RQC.detailClass = BoxStatusRqc.class;
/* 127 */     PURCHASEORDERCONDIMENTSINGLE_STATUS.detailClass = PurchaseOrderCondimentSingleStatus.class;
/* 128 */     PRUCHASE_ORDER_RQC_STATUS.detailClass = PurchaseOrderRqcStatus.class;
/* 129 */     PRUCHASE_ORDER_PUTINSTORAGE_STATUS.detailClass = PurchaseOrderPutInStorageStatus.class;
/* 130 */     STOREROOM_TYPE.detailClass = StoreRoomType.class;
/* 131 */     INVENTORY_RECORD_TYPE.detailClass = InventoryRecordType.class;
/* 132 */     UNPLANNEDOUTBOUND_STATUS.detailClass = UnplannedOutboundStatus.class;
/* 133 */     UNPLANNEDWAREHOUSING_STATUS.detailClass = UnplannedWarehousingStatus.class;
/* 134 */     BOX_ADJUSTMENT_TYPE.detailClass = BoxAdjustmentType.class;
/* 135 */     WMSPART_TYPE.detailClass = WmsPartType.class;
/* 136 */     CURRENCY_TYPE.detailClass = CurrencyType.class;
/* 137 */     PORTALSHIPORDER_STATUS.detailClass = PortalShipOrderStatus.class;
/* 138 */     INVENTORYTYPE.detailClass = InventoryType.class;
/* 139 */     WMSPLANTOGOOUTSTATUS.detailClass = WmsPlanToGoOutStatus.class;
/* 140 */     WMSUNPLANNEDWAREHOUSING.detailClass = WmsUnplannedWarehousingStatus.class;
/* 141 */     POHIGHLINESTATUS.detailClass = PoHighLineStatus.class;
/* 142 */     BADREASONSTYPE.detailClass = BadReasonsType.class;
/* 143 */     BADREASONS_STATUS.detailClass = BadReasonsStatus.class;
/* 144 */     PRODUCTBELOWLINE_STATUS.detailClass = ProductBelowLineStatus.class;
/* 145 */     PARTTYPE.detailClass = PartType.class;
/* 146 */     SALESPRESHIPORDER_STATUS.detailClass = SalesPreshiporderStatus.class;
/* 147 */     SALESPRESHIPORDERBATCH_STATUS.detailClass = SalesPreshiporderBatchStatus.class;
/*     */   }
/*     */ 
/*     */   
/*     */   private String defaultLabel;
/*     */   private Class detailClass;
/*     */   
/*     */   public MetadataType() {}
/*     */   
/*     */   private MetadataType(int metadataId, String defaultLabel) {
/* 157 */     super(null, metadataId);
/* 158 */     this.defaultLabel = defaultLabel;
/*     */   }
/*     */   
/*     */   public void setLabel(String label) {
/* 162 */     this.name = label;
/*     */   }
/*     */   
/*     */   public String getDefaultLabel() {
/* 166 */     return this.defaultLabel;
/*     */   }
/*     */   
/*     */   public Class getDetailClass() {
/* 170 */     return this.detailClass;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/MetadataType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */