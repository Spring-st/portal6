package com.aof.dao.admin;

import com.aof.dao.DAO;
import com.aof.model.admin.Function;
import com.aof.model.admin.query.FunctionQueryOrder;
import java.util.List;
import java.util.Map;

public interface FunctionDAO extends DAO {
  Function getFunction(Integer paramInteger);
  
  Function saveFunction(Function paramFunction);
  
  int getFunctionListCount(Map paramMap);
  
  List getFunctionList(Map paramMap, int paramInt1, int paramInt2, FunctionQueryOrder paramFunctionQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/FunctionDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */