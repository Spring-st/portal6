package com.aof.dao.admin;

import com.aof.model.admin.ExpenseSubCategory;
import com.aof.model.admin.query.ExpenseSubCategoryQueryOrder;
import java.util.List;
import java.util.Map;

public interface ExpenseSubCategoryDAO {
  ExpenseSubCategory getExpenseSubCategory(Integer paramInteger);
  
  int getExpenseSubCategoryListCount(Map paramMap);
  
  List getExpenseSubCategoryList(Map paramMap, int paramInt1, int paramInt2, ExpenseSubCategoryQueryOrder paramExpenseSubCategoryQueryOrder, boolean paramBoolean);
  
  ExpenseSubCategory insertExpenseSubCategory(ExpenseSubCategory paramExpenseSubCategory);
  
  ExpenseSubCategory updateExpenseSubCategory(ExpenseSubCategory paramExpenseSubCategory);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/ExpenseSubCategoryDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */