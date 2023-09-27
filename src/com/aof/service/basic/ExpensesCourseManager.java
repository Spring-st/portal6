package com.aof.service.basic;

import com.aof.model.basic.ExpensesCourse;
import com.aof.model.basic.query.ExpensesCourseQueryOrder;
import java.util.List;
import java.util.Map;

public interface ExpensesCourseManager {
  ExpensesCourse getExpensesCourse(Integer paramInteger);
  
  ExpensesCourse insertExpensesCourse(ExpensesCourse paramExpensesCourse);
  
  ExpensesCourse updateExpensesCourse(ExpensesCourse paramExpensesCourse);
  
  int getExpensesCourseListCount(Map paramMap);
  
  List getExpensesCourseList(Map paramMap, int paramInt1, int paramInt2, ExpensesCourseQueryOrder paramExpensesCourseQueryOrder, boolean paramBoolean);
  
  void promoteExpensesCourse(ExpensesCourse paramExpensesCourse);
  
  boolean deleteExpensesCourse(ExpensesCourse paramExpensesCourse);
  
  List getExpensesCourseListEnabledAll();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/ExpensesCourseManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */