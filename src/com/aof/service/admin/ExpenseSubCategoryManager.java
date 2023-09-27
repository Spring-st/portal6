package com.aof.service.admin;

import com.aof.model.admin.ExpenseSubCategory;
import com.aof.model.admin.query.ExpenseSubCategoryQueryOrder;
import java.util.List;
import java.util.Map;

public interface ExpenseSubCategoryManager {
  ExpenseSubCategory getExpenseSubCategory(Integer paramInteger);
  
  ExpenseSubCategory insertExpenseSubCategory(ExpenseSubCategory paramExpenseSubCategory);
  
  ExpenseSubCategory updateExpenseSubCategory(ExpenseSubCategory paramExpenseSubCategory);
  
  int getExpenseSubCategoryListCount(Map paramMap);
  
  List getExpenseSubCategoryList(Map paramMap, int paramInt1, int paramInt2, ExpenseSubCategoryQueryOrder paramExpenseSubCategoryQueryOrder, boolean paramBoolean);
  
  List getChildrenOfExpenseCategory(Integer paramInteger);
  
  List getEnabledChildrenOfExpenseCategory(Integer paramInteger);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/ExpenseSubCategoryManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */