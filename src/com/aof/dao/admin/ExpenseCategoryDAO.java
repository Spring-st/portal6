package com.aof.dao.admin;

import com.aof.model.admin.ExpenseCategory;
import com.aof.model.admin.query.ExpenseCategoryQueryOrder;
import java.util.List;
import java.util.Map;

public interface ExpenseCategoryDAO {
  ExpenseCategory getExpenseCategory(Integer paramInteger);
  
  int getExpenseCategoryListCount(Map paramMap);
  
  List getExpenseCategoryList(Map paramMap, int paramInt1, int paramInt2, ExpenseCategoryQueryOrder paramExpenseCategoryQueryOrder, boolean paramBoolean);
  
  ExpenseCategory insertExpenseCategory(ExpenseCategory paramExpenseCategory);
  
  ExpenseCategory updateExpenseCategory(ExpenseCategory paramExpenseCategory);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/ExpenseCategoryDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */