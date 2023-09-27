package com.aof.dao.basic;

import com.aof.dao.DAO;
import com.aof.model.basic.ExpensesCourse;
import com.aof.model.basic.query.ExpensesCourseQueryOrder;
import java.util.List;
import java.util.Map;

public interface ExpensesCourseDAO extends DAO {
  ExpensesCourse getExpensesCourse(Integer paramInteger);
  
  int getExpensesCourseListCount(Map paramMap);
  
  List getExpensesCourseList(Map paramMap, int paramInt1, int paramInt2, ExpensesCourseQueryOrder paramExpensesCourseQueryOrder, boolean paramBoolean);
  
  ExpensesCourse insertExpensesCourse(ExpensesCourse paramExpensesCourse);
  
  ExpensesCourse updateExpensesCourse(ExpensesCourse paramExpensesCourse);
  
  List getEnabledExpensesCourseList();
  
  void deleteExpensesCourse(ExpensesCourse paramExpensesCourse);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/ExpensesCourseDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */