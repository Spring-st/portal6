/*     */ package com.aof.model.metadata;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RuleType
/*     */   extends MetadataDetailEnum
/*     */ {
/*  14 */   public static final RuleType CAPEX_APPROVAL_RULES = new RuleType(1, "Capex Approval Rules", MetadataType.RULE_TYPE);
/*  15 */   public static final RuleType PR_APPROVAL_RULES = new RuleType(2, "PR Approval Rules", MetadataType.RULE_TYPE);
/*  16 */   public static final RuleType PURCHASING_RULES = new RuleType(3, "Purchasing Rules", MetadataType.RULE_TYPE);
/*  17 */   public static final RuleType PURCHASING_PRICE_CONTROL_RULES = new RuleType(4, "Purchasing Price Control Rules", MetadataType.RULE_TYPE);
/*  18 */   public static final RuleType PO_APPROVAL_RULES = new RuleType(5, "PO Approval Rules", MetadataType.RULE_TYPE);
/*  19 */   public static final RuleType EXPENSE_APPROVAL_RULES = new RuleType(6, "Expense Approval Rules", MetadataType.RULE_TYPE);
/*  20 */   public static final RuleType EXPENSE_CLAIM_RULES = new RuleType(7, "Expense Claim Rules", MetadataType.RULE_TYPE);
/*  21 */   public static final RuleType TRAVEL_APPROVAL_RULES = new RuleType(8, "Travel Approval Rules", MetadataType.RULE_TYPE);
/*     */   
/*  23 */   public static final RuleType YEARLY_BUDGET_FILTERS = new RuleType(10, "Yearly Budget Filters", MetadataType.RULE_TYPE);
/*  24 */   public static final RuleType CAPEX_FILTERS = new RuleType(11, "Capex Filters", MetadataType.RULE_TYPE);
/*  25 */   public static final RuleType PURCHASE_REQUEST_FILTERS = new RuleType(12, "Purchase Request Filters", MetadataType.RULE_TYPE);
/*  26 */   public static final RuleType PURCHASE_ORDER_FILTERS = new RuleType(13, "Purchase Order Filters", MetadataType.RULE_TYPE);
/*  27 */   public static final RuleType EXPENSE_FILTERS = new RuleType(14, "Expense Filters", MetadataType.RULE_TYPE);
/*  28 */   public static final RuleType TRAVEL_APPLICATION_FILTERS = new RuleType(15, "Travel Application Filters", MetadataType.RULE_TYPE);
/*     */   static {
/*  30 */     Set<ConditionType> s = new HashSet();
/*  31 */     s.add(ConditionType.DEPARTMENT);
/*  32 */     s.add(ConditionType.PURCHASE_CATEGORY);
/*     */     
/*  34 */     s.add(ConditionType.CAPEX_REQUEST_AMOUNT);
/*  35 */     s.add(ConditionType.OVER_BUDGET);
/*  36 */     s.add(ConditionType.OVER_BUDGET_PERCENTAGE);
/*  37 */     s.add(ConditionType.WITH_BUDGET);
/*  38 */     CAPEX_APPROVAL_RULES.supportedConditions = s;
/*  39 */     CAPEX_APPROVAL_RULES.consequenceType = ConsequenceType.APPROVER;
/*  40 */     CAPEX_APPROVAL_RULES.prefixUrl = "CapexApproval";
/*     */     
/*  42 */     s = new HashSet<ConditionType>();
/*  43 */     s.add(ConditionType.DEPARTMENT);
/*  44 */     s.add(ConditionType.PURCHASE_CATEGORY);
/*     */     
/*  46 */     s.add(ConditionType.PR_REQUEST_AMOUNT);
/*  47 */     s.add(ConditionType.OVER_BUDGET);
/*  48 */     s.add(ConditionType.OVER_BUDGET_PERCENTAGE);
/*  49 */     s.add(ConditionType.WITH_BUDGET);
/*  50 */     s.add(ConditionType.PR_REQUEST_ITEM_MAX_PRICE);
/*  51 */     PR_APPROVAL_RULES.supportedConditions = s;
/*  52 */     PR_APPROVAL_RULES.consequenceType = ConsequenceType.APPROVER;
/*  53 */     PR_APPROVAL_RULES.prefixUrl = "PRApproval";
/*     */     
/*  55 */     s = new HashSet<ConditionType>();
/*  56 */     s.add(ConditionType.DEPARTMENT);
/*  57 */     s.add(ConditionType.PURCHASE_CATEGORY);
/*     */     
/*  59 */     PURCHASING_RULES.supportedConditions = s;
/*  60 */     PURCHASING_RULES.consequenceType = ConsequenceType.PURCHASER;
/*  61 */     PURCHASING_RULES.prefixUrl = "Purchasing";
/*     */     
/*  63 */     s = new HashSet<ConditionType>();
/*  64 */     s.add(ConditionType.DEPARTMENT);
/*  65 */     s.add(ConditionType.PURCHASE_CATEGORY);
/*     */     
/*  67 */     s.add(ConditionType.DIFFERENCE_AMOUNT_OF_PR_PURCHASE_AND_APPROVED);
/*  68 */     s.add(ConditionType.DIFFERENCE_AMOUNT_PERCENTAGE_OF_PR_PURCHASE_AND_APPROVED);
/*  69 */     PURCHASING_PRICE_CONTROL_RULES.supportedConditions = s;
/*  70 */     PURCHASING_PRICE_CONTROL_RULES.consequenceType = ConsequenceType.ACCEPTABLE;
/*  71 */     PURCHASING_PRICE_CONTROL_RULES.prefixUrl = "PurchasingPriceControl";
/*     */     
/*  73 */     s = new HashSet<ConditionType>();
/*  74 */     s.add(ConditionType.DEPARTMENT);
/*  75 */     s.add(ConditionType.PURCHASE_CATEGORY);
/*     */     
/*  77 */     s.add(ConditionType.PO_PURCHASE_AMOUNT);
/*  78 */     PO_APPROVAL_RULES.supportedConditions = s;
/*  79 */     PO_APPROVAL_RULES.consequenceType = ConsequenceType.APPROVER;
/*  80 */     PO_APPROVAL_RULES.prefixUrl = "POApproval";
/*     */     
/*  82 */     s = new HashSet<ConditionType>();
/*  83 */     s.add(ConditionType.DEPARTMENT);
/*  84 */     s.add(ConditionType.EXPENSE_CATEGORY);
/*  85 */     s.add(ConditionType.EXPENSE_ENTERED_AMOUNT);
/*  86 */     s.add(ConditionType.EXPENSE_SUBCATEGORY);
/*  87 */     EXPENSE_APPROVAL_RULES.supportedConditions = s;
/*  88 */     EXPENSE_APPROVAL_RULES.consequenceType = ConsequenceType.APPROVER;
/*  89 */     EXPENSE_APPROVAL_RULES.prefixUrl = "ExpenseApproval";
/*     */     
/*  91 */     s = new HashSet<ConditionType>();
/*  92 */     s.add(ConditionType.DEPARTMENT);
/*  93 */     s.add(ConditionType.EXPENSE_CATEGORY);
/*     */     
/*  95 */     s.add(ConditionType.DIFFERENCE_AMOUNT_OF_EXPENSE_CLAIMED_AND_ENTERED);
/*  96 */     s.add(ConditionType.DIFFERENCE_AMOUNT_PERCENTAGE_OF_EXPENSE_CLAIMED_AND_ENTERED);
/*  97 */     EXPENSE_CLAIM_RULES.supportedConditions = s;
/*  98 */     EXPENSE_CLAIM_RULES.consequenceType = ConsequenceType.ACCEPTABLE;
/*  99 */     EXPENSE_CLAIM_RULES.prefixUrl = "ExpenseClaim";
/*     */     
/* 101 */     s = new HashSet<ConditionType>();
/* 102 */     s.add(ConditionType.DEPARTMENT);
/* 103 */     s.add(ConditionType.TRAVEL_MODE);
/* 104 */     s.add(ConditionType.TRAVEL_FROM);
/* 105 */     s.add(ConditionType.TRAVEL_TO);
/* 106 */     s.add(ConditionType.TRAVEL_FEE);
/*     */     
/* 108 */     TRAVEL_APPROVAL_RULES.supportedConditions = s;
/* 109 */     TRAVEL_APPROVAL_RULES.consequenceType = ConsequenceType.APPROVER;
/* 110 */     TRAVEL_APPROVAL_RULES.prefixUrl = "TravelApproval";
/*     */     
/* 112 */     s = new HashSet<ConditionType>();
/* 113 */     s.add(ConditionType.DEPARTMENT);
/*     */     
/* 115 */     s = new HashSet<ConditionType>();
/* 116 */     s.add(ConditionType.DEPARTMENT);
/* 117 */     s.add(ConditionType.PURCHASE_CATEGORY);
/* 118 */     s.add(ConditionType.BUDGET_TYPE);
/* 119 */     s.add(ConditionType.YEARLY_BUDGET_AMOUNT);
/* 120 */     s.add(ConditionType.REMAIN_AMOUNT);
/* 121 */     YEARLY_BUDGET_FILTERS.supportedConditions = s;
/* 122 */     YEARLY_BUDGET_FILTERS.consequenceType = ConsequenceType.NOTIFIER;
/* 123 */     YEARLY_BUDGET_FILTERS.prefixUrl = "YearlyBudgetFilter";
/*     */     
/* 125 */     s = new HashSet<ConditionType>();
/* 126 */     s.add(ConditionType.DEPARTMENT);
/* 127 */     s.add(ConditionType.PURCHASE_CATEGORY);
/* 128 */     s.add(ConditionType.CAPEX_REQUEST_AMOUNT);
/* 129 */     s.add(ConditionType.REMAIN_AMOUNT);
/* 130 */     CAPEX_FILTERS.supportedConditions = s;
/* 131 */     CAPEX_FILTERS.consequenceType = ConsequenceType.NOTIFIER;
/* 132 */     CAPEX_FILTERS.prefixUrl = "CapexFilter";
/*     */     
/* 134 */     s = new HashSet<ConditionType>();
/* 135 */     s.add(ConditionType.DEPARTMENT);
/* 136 */     s.add(ConditionType.PURCHASE_CATEGORY);
/* 137 */     s.add(ConditionType.PR_REQUEST_AMOUNT);
/* 138 */     PURCHASE_REQUEST_FILTERS.supportedConditions = s;
/* 139 */     PURCHASE_REQUEST_FILTERS.consequenceType = ConsequenceType.NOTIFIER;
/* 140 */     PURCHASE_REQUEST_FILTERS.prefixUrl = "PurchaseRequestFilter";
/*     */     
/* 142 */     s = new HashSet<ConditionType>();
/* 143 */     s.add(ConditionType.DEPARTMENT);
/* 144 */     s.add(ConditionType.PURCHASE_CATEGORY);
/* 145 */     s.add(ConditionType.PO_PURCHASE_AMOUNT);
/* 146 */     PURCHASE_ORDER_FILTERS.supportedConditions = s;
/* 147 */     PURCHASE_ORDER_FILTERS.consequenceType = ConsequenceType.NOTIFIER;
/* 148 */     PURCHASE_ORDER_FILTERS.prefixUrl = "PurchaseOrderFilter";
/*     */     
/* 150 */     s = new HashSet<ConditionType>();
/* 151 */     s.add(ConditionType.DEPARTMENT);
/* 152 */     s.add(ConditionType.EXPENSE_CATEGORY);
/* 153 */     s.add(ConditionType.EXPENSE_ENTERED_AMOUNT);
/* 154 */     EXPENSE_FILTERS.supportedConditions = s;
/* 155 */     EXPENSE_FILTERS.consequenceType = ConsequenceType.NOTIFIER;
/* 156 */     EXPENSE_FILTERS.prefixUrl = "ExpenseFilter";
/*     */     
/* 158 */     s = new HashSet<ConditionType>();
/* 159 */     s.add(ConditionType.DEPARTMENT);
/* 160 */     s.add(ConditionType.TRAVEL_MODE);
/* 161 */     s.add(ConditionType.TRAVEL_FROM);
/* 162 */     s.add(ConditionType.TRAVEL_TO);
/* 163 */     TRAVEL_APPLICATION_FILTERS.supportedConditions = s;
/* 164 */     TRAVEL_APPLICATION_FILTERS.consequenceType = ConsequenceType.NOTIFIER;
/* 165 */     TRAVEL_APPLICATION_FILTERS.prefixUrl = "TravelApplicationFilter";
/*     */     
/* 167 */     s = new HashSet<ConditionType>();
/* 168 */     s.add(ConditionType.DEPARTMENT);
/*     */   }
/*     */ 
/*     */   
/*     */   private Set supportedConditions;
/*     */   private ConsequenceType consequenceType;
/*     */   private String prefixUrl;
/*     */   
/*     */   public RuleType() {}
/*     */   
/*     */   private RuleType(int metadataId, String defaultLabel, MetadataType type) {
/* 179 */     super(metadataId, defaultLabel, type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getSupportedConditions() {
/* 186 */     return this.supportedConditions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConsequenceType getConsequenceType() {
/* 193 */     return this.consequenceType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrefixUrl() {
/* 200 */     return this.prefixUrl;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/RuleType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */