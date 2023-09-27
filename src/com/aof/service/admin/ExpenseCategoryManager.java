package com.aof.service.admin;

import com.aof.model.admin.ExpenseCategory;
import com.aof.model.admin.Site;
import com.aof.model.admin.query.ExpenseCategoryQueryOrder;
import java.util.List;
import java.util.Map;

public interface ExpenseCategoryManager {
  ExpenseCategory getExpenseCategory(Integer paramInteger);
  
  ExpenseCategory insertExpenseCategory(ExpenseCategory paramExpenseCategory);
  
  ExpenseCategory updateExpenseCategory(ExpenseCategory paramExpenseCategory);
  
  int getExpenseCategoryListCount(Map paramMap);
  
  List getExpenseCategoryList(Map paramMap, int paramInt1, int paramInt2, ExpenseCategoryQueryOrder paramExpenseCategoryQueryOrder, boolean paramBoolean);
  
  ExpenseCategory getEnabledTravelExpenseCategoryOfSite(int paramInt);
  
  List getEnabledNotTravelExpenseCategoryListOfSite(int paramInt);
  
  List getEnabledExpenseCategorySubCategoryOfSite(Site paramSite);
  
  List getEnabledExpenseCategoryOfSite(Site paramSite);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/ExpenseCategoryManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */