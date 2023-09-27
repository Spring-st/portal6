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
/*     */ public class ConditionType
/*     */   extends MetadataDetailEnum
/*     */ {
/*  14 */   public static final ConditionType DEPARTMENT = new ConditionType(1, "Department", MetadataType.CONDITION_TYPE);
/*  15 */   public static final ConditionType PURCHASE_CATEGORY = new ConditionType(2, "Purchase Category", MetadataType.CONDITION_TYPE);
/*     */   
/*  17 */   public static final ConditionType CAPEX_REQUEST_AMOUNT = new ConditionType(4, "Capex Request Amount", MetadataType.CONDITION_TYPE);
/*  18 */   public static final ConditionType PR_REQUEST_AMOUNT = new ConditionType(5, "PR Request Amount", MetadataType.CONDITION_TYPE);
/*     */ 
/*     */   
/*  21 */   public static final ConditionType DIFFERENCE_AMOUNT_OF_PR_PURCHASE_AND_APPROVED = new ConditionType(8, "PR Purchase Amount - PR Approved Amount", MetadataType.CONDITION_TYPE);
/*  22 */   public static final ConditionType DIFFERENCE_AMOUNT_PERCENTAGE_OF_PR_PURCHASE_AND_APPROVED = new ConditionType(9, "(PR Purchase Amount - PR Approved Amount) / PR Approved Amount %", MetadataType.CONDITION_TYPE);
/*  23 */   public static final ConditionType PO_PURCHASE_AMOUNT = new ConditionType(10, "PO Purchase Amount", MetadataType.CONDITION_TYPE);
/*  24 */   public static final ConditionType EXPENSE_CATEGORY = new ConditionType(11, "Expense Category", MetadataType.CONDITION_TYPE);
/*  25 */   public static final ConditionType EXPENSE_SUBCATEGORY = new ConditionType(12, "Expense SubCategory", MetadataType.CONDITION_TYPE);
/*  26 */   public static final ConditionType EXPENSE_ENTERED_AMOUNT = new ConditionType(13, "Expense Entered Amount", MetadataType.CONDITION_TYPE);
/*  27 */   public static final ConditionType DIFFERENCE_AMOUNT_OF_EXPENSE_CLAIMED_AND_ENTERED = new ConditionType(14, "Expense Claimed Amount - Expense Entered Amount", MetadataType.CONDITION_TYPE);
/*  28 */   public static final ConditionType DIFFERENCE_AMOUNT_PERCENTAGE_OF_EXPENSE_CLAIMED_AND_ENTERED = new ConditionType(15, "(Expense Claimed Amount - Expense Entered Amount) / Expense Entered Amount %", MetadataType.CONDITION_TYPE);
/*  29 */   public static final ConditionType TRAVEL_FROM = new ConditionType(16, "Travel From", MetadataType.CONDITION_TYPE);
/*  30 */   public static final ConditionType TRAVEL_TO = new ConditionType(17, "Travel To", MetadataType.CONDITION_TYPE);
/*  31 */   public static final ConditionType TRAVEL_MODE = new ConditionType(18, "Travel Mode", MetadataType.CONDITION_TYPE);
/*  32 */   public static final ConditionType OVER_BUDGET = new ConditionType(19, "Over Budget", MetadataType.CONDITION_TYPE);
/*  33 */   public static final ConditionType OVER_BUDGET_PERCENTAGE = new ConditionType(20, "Over Budget (%)", MetadataType.CONDITION_TYPE);
/*     */   
/*  35 */   public static final ConditionType BUDGET_TYPE = new ConditionType(21, "Budget Type", MetadataType.CONDITION_TYPE);
/*  36 */   public static final ConditionType REMAIN_AMOUNT = new ConditionType(22, "Remaining Amount", MetadataType.CONDITION_TYPE);
/*  37 */   public static final ConditionType YEARLY_BUDGET_AMOUNT = new ConditionType(23, "Yearly Budget Amount", MetadataType.CONDITION_TYPE);
/*     */   
/*  39 */   public static final ConditionType WITH_BUDGET = new ConditionType(24, "With Budget", MetadataType.CONDITION_TYPE);
/*     */   
/*  41 */   public static final ConditionType TRAVEL_FEE = new ConditionType(25, "Expense Fee", MetadataType.CONDITION_TYPE);
/*  42 */   public static final ConditionType PR_REQUEST_ITEM_MAX_PRICE = new ConditionType(26, "PR Request Item Max Price", MetadataType.CONDITION_TYPE);
/*     */   private Set supportedCompareTypes;
/*     */   private String typeString;
/*     */   
/*     */   static {
/*  47 */     Set<ConditionCompareType> s = new HashSet();
/*  48 */     s.add(ConditionCompareType.EQUAL);
/*  49 */     DEPARTMENT.supportedCompareTypes = s;
/*  50 */     DEPARTMENT.typeString = "Department";
/*     */     
/*  52 */     s = new HashSet<ConditionCompareType>();
/*  53 */     s.add(ConditionCompareType.EQUAL);
/*  54 */     PURCHASE_CATEGORY.supportedCompareTypes = s;
/*  55 */     PURCHASE_CATEGORY.typeString = "PurchaseCategory";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  62 */     s = new HashSet<ConditionCompareType>();
/*  63 */     s.add(ConditionCompareType.LESS_THAN);
/*  64 */     s.add(ConditionCompareType.LESS_OR_EQUAL);
/*  65 */     s.add(ConditionCompareType.EQUAL);
/*  66 */     s.add(ConditionCompareType.GREATER_OR_EQUAL);
/*  67 */     s.add(ConditionCompareType.GREATER_THAN);
/*  68 */     CAPEX_REQUEST_AMOUNT.supportedCompareTypes = s;
/*  69 */     CAPEX_REQUEST_AMOUNT.typeString = "Number";
/*     */     
/*  71 */     s = new HashSet<ConditionCompareType>();
/*  72 */     s.add(ConditionCompareType.LESS_THAN);
/*  73 */     s.add(ConditionCompareType.LESS_OR_EQUAL);
/*  74 */     s.add(ConditionCompareType.EQUAL);
/*  75 */     s.add(ConditionCompareType.GREATER_OR_EQUAL);
/*  76 */     s.add(ConditionCompareType.GREATER_THAN);
/*  77 */     PR_REQUEST_AMOUNT.supportedCompareTypes = s;
/*  78 */     PR_REQUEST_AMOUNT.typeString = "Number";
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
/* 100 */     s = new HashSet<ConditionCompareType>();
/* 101 */     s.add(ConditionCompareType.LESS_THAN);
/* 102 */     s.add(ConditionCompareType.LESS_OR_EQUAL);
/* 103 */     s.add(ConditionCompareType.EQUAL);
/* 104 */     s.add(ConditionCompareType.GREATER_OR_EQUAL);
/* 105 */     s.add(ConditionCompareType.GREATER_THAN);
/* 106 */     DIFFERENCE_AMOUNT_OF_PR_PURCHASE_AND_APPROVED.supportedCompareTypes = s;
/* 107 */     DIFFERENCE_AMOUNT_OF_PR_PURCHASE_AND_APPROVED.typeString = "Number";
/*     */     
/* 109 */     s = new HashSet<ConditionCompareType>();
/* 110 */     s.add(ConditionCompareType.LESS_THAN);
/* 111 */     s.add(ConditionCompareType.LESS_OR_EQUAL);
/* 112 */     s.add(ConditionCompareType.EQUAL);
/* 113 */     s.add(ConditionCompareType.GREATER_OR_EQUAL);
/* 114 */     s.add(ConditionCompareType.GREATER_THAN);
/* 115 */     DIFFERENCE_AMOUNT_PERCENTAGE_OF_PR_PURCHASE_AND_APPROVED.supportedCompareTypes = s;
/* 116 */     DIFFERENCE_AMOUNT_PERCENTAGE_OF_PR_PURCHASE_AND_APPROVED.typeString = "Number";
/*     */     
/* 118 */     s = new HashSet<ConditionCompareType>();
/* 119 */     s.add(ConditionCompareType.LESS_THAN);
/* 120 */     s.add(ConditionCompareType.LESS_OR_EQUAL);
/* 121 */     s.add(ConditionCompareType.EQUAL);
/* 122 */     s.add(ConditionCompareType.GREATER_OR_EQUAL);
/* 123 */     s.add(ConditionCompareType.GREATER_THAN);
/* 124 */     PO_PURCHASE_AMOUNT.supportedCompareTypes = s;
/* 125 */     PO_PURCHASE_AMOUNT.typeString = "Number";
/*     */     
/* 127 */     s = new HashSet<ConditionCompareType>();
/* 128 */     s.add(ConditionCompareType.EQUAL);
/* 129 */     EXPENSE_CATEGORY.supportedCompareTypes = s;
/* 130 */     EXPENSE_CATEGORY.typeString = "ExpenseCategory";
/*     */     
/* 132 */     s = new HashSet<ConditionCompareType>();
/* 133 */     s.add(ConditionCompareType.EQUAL);
/* 134 */     EXPENSE_SUBCATEGORY.supportedCompareTypes = s;
/* 135 */     EXPENSE_SUBCATEGORY.typeString = "ExpenseSUBCategory";
/*     */     
/* 137 */     s = new HashSet<ConditionCompareType>();
/* 138 */     s.add(ConditionCompareType.LESS_THAN);
/* 139 */     s.add(ConditionCompareType.LESS_OR_EQUAL);
/* 140 */     s.add(ConditionCompareType.EQUAL);
/* 141 */     s.add(ConditionCompareType.GREATER_OR_EQUAL);
/* 142 */     s.add(ConditionCompareType.GREATER_THAN);
/* 143 */     EXPENSE_ENTERED_AMOUNT.supportedCompareTypes = s;
/* 144 */     EXPENSE_ENTERED_AMOUNT.typeString = "Number";
/*     */     
/* 146 */     s = new HashSet<ConditionCompareType>();
/* 147 */     s.add(ConditionCompareType.LESS_THAN);
/* 148 */     s.add(ConditionCompareType.LESS_OR_EQUAL);
/* 149 */     s.add(ConditionCompareType.EQUAL);
/* 150 */     s.add(ConditionCompareType.GREATER_OR_EQUAL);
/* 151 */     s.add(ConditionCompareType.GREATER_THAN);
/* 152 */     DIFFERENCE_AMOUNT_OF_EXPENSE_CLAIMED_AND_ENTERED.supportedCompareTypes = s;
/* 153 */     DIFFERENCE_AMOUNT_OF_EXPENSE_CLAIMED_AND_ENTERED.typeString = "Number";
/*     */     
/* 155 */     s = new HashSet<ConditionCompareType>();
/* 156 */     s.add(ConditionCompareType.LESS_THAN);
/* 157 */     s.add(ConditionCompareType.LESS_OR_EQUAL);
/* 158 */     s.add(ConditionCompareType.EQUAL);
/* 159 */     s.add(ConditionCompareType.GREATER_OR_EQUAL);
/* 160 */     s.add(ConditionCompareType.GREATER_THAN);
/* 161 */     DIFFERENCE_AMOUNT_PERCENTAGE_OF_EXPENSE_CLAIMED_AND_ENTERED.supportedCompareTypes = s;
/* 162 */     DIFFERENCE_AMOUNT_PERCENTAGE_OF_EXPENSE_CLAIMED_AND_ENTERED.typeString = "Number";
/*     */     
/* 164 */     s = new HashSet<ConditionCompareType>();
/* 165 */     s.add(ConditionCompareType.EQUAL);
/* 166 */     TRAVEL_FROM.supportedCompareTypes = s;
/* 167 */     TRAVEL_FROM.typeString = "TravelType";
/*     */     
/* 169 */     s = new HashSet<ConditionCompareType>();
/* 170 */     s.add(ConditionCompareType.EQUAL);
/* 171 */     TRAVEL_TO.supportedCompareTypes = s;
/* 172 */     TRAVEL_TO.typeString = "TravelType";
/*     */     
/* 174 */     s = new HashSet<ConditionCompareType>();
/* 175 */     s.add(ConditionCompareType.EQUAL);
/* 176 */     TRAVEL_MODE.supportedCompareTypes = s;
/* 177 */     TRAVEL_MODE.typeString = "TravellingMode";
/*     */     
/* 179 */     s = new HashSet<ConditionCompareType>();
/* 180 */     s.add(ConditionCompareType.LESS_THAN);
/* 181 */     s.add(ConditionCompareType.LESS_OR_EQUAL);
/* 182 */     s.add(ConditionCompareType.EQUAL);
/* 183 */     s.add(ConditionCompareType.GREATER_OR_EQUAL);
/* 184 */     s.add(ConditionCompareType.GREATER_THAN);
/* 185 */     OVER_BUDGET.supportedCompareTypes = s;
/* 186 */     OVER_BUDGET.typeString = "Number";
/*     */     
/* 188 */     s = new HashSet<ConditionCompareType>();
/* 189 */     s.add(ConditionCompareType.LESS_THAN);
/* 190 */     s.add(ConditionCompareType.LESS_OR_EQUAL);
/* 191 */     s.add(ConditionCompareType.EQUAL);
/* 192 */     s.add(ConditionCompareType.GREATER_OR_EQUAL);
/* 193 */     s.add(ConditionCompareType.GREATER_THAN);
/* 194 */     OVER_BUDGET_PERCENTAGE.supportedCompareTypes = s;
/* 195 */     OVER_BUDGET_PERCENTAGE.typeString = "Number";
/*     */     
/* 197 */     s = new HashSet<ConditionCompareType>();
/* 198 */     s.add(ConditionCompareType.EQUAL);
/* 199 */     BUDGET_TYPE.supportedCompareTypes = s;
/* 200 */     BUDGET_TYPE.typeString = "BudgetType";
/*     */     
/* 202 */     s = new HashSet<ConditionCompareType>();
/* 203 */     s.add(ConditionCompareType.LESS_THAN);
/* 204 */     s.add(ConditionCompareType.LESS_OR_EQUAL);
/* 205 */     s.add(ConditionCompareType.EQUAL);
/* 206 */     s.add(ConditionCompareType.GREATER_OR_EQUAL);
/* 207 */     s.add(ConditionCompareType.GREATER_THAN);
/* 208 */     REMAIN_AMOUNT.supportedCompareTypes = s;
/* 209 */     REMAIN_AMOUNT.typeString = "Number";
/*     */     
/* 211 */     s = new HashSet<ConditionCompareType>();
/* 212 */     s.add(ConditionCompareType.LESS_THAN);
/* 213 */     s.add(ConditionCompareType.LESS_OR_EQUAL);
/* 214 */     s.add(ConditionCompareType.EQUAL);
/* 215 */     s.add(ConditionCompareType.GREATER_OR_EQUAL);
/* 216 */     s.add(ConditionCompareType.GREATER_THAN);
/* 217 */     YEARLY_BUDGET_AMOUNT.supportedCompareTypes = s;
/* 218 */     YEARLY_BUDGET_AMOUNT.typeString = "Number";
/*     */     
/* 220 */     s = new HashSet<ConditionCompareType>();
/* 221 */     s.add(ConditionCompareType.EQUAL);
/* 222 */     WITH_BUDGET.supportedCompareTypes = s;
/* 223 */     WITH_BUDGET.typeString = "YesNo";
/*     */     
/* 225 */     s = new HashSet<ConditionCompareType>();
/* 226 */     s.add(ConditionCompareType.LESS_THAN);
/* 227 */     s.add(ConditionCompareType.LESS_OR_EQUAL);
/* 228 */     s.add(ConditionCompareType.EQUAL);
/* 229 */     s.add(ConditionCompareType.GREATER_OR_EQUAL);
/* 230 */     s.add(ConditionCompareType.GREATER_THAN);
/* 231 */     TRAVEL_FEE.supportedCompareTypes = s;
/* 232 */     TRAVEL_FEE.typeString = "Number";
/*     */     
/* 234 */     s = new HashSet<ConditionCompareType>();
/* 235 */     s.add(ConditionCompareType.LESS_THAN);
/* 236 */     s.add(ConditionCompareType.LESS_OR_EQUAL);
/* 237 */     s.add(ConditionCompareType.EQUAL);
/* 238 */     s.add(ConditionCompareType.GREATER_OR_EQUAL);
/* 239 */     s.add(ConditionCompareType.GREATER_THAN);
/* 240 */     PR_REQUEST_ITEM_MAX_PRICE.supportedCompareTypes = s;
/* 241 */     PR_REQUEST_ITEM_MAX_PRICE.typeString = "Number";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ConditionType() {}
/*     */ 
/*     */ 
/*     */   
/*     */   private ConditionType(int metadataId, String defaultLabel, MetadataType type) {
/* 251 */     super(metadataId, defaultLabel, type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getSupportedCompareTypes() {
/* 258 */     return this.supportedCompareTypes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTypeString() {
/* 265 */     return this.typeString;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/metadata/ConditionType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */