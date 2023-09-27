package com.aof.service.admin;

import com.aof.model.admin.Function;
import com.aof.model.admin.query.FunctionQueryOrder;
import java.util.List;
import java.util.Map;
import org.apache.struts.config.ModuleConfig;

public interface FunctionManager {
  boolean isInitiated();
  
  void initiate(ModuleConfig paramModuleConfig);
  
  Function getFunction(String paramString);
  
  Function getFunction(Integer paramInteger);
  
  Function saveFunction(Function paramFunction);
  
  int getFunctionListCount(Map paramMap);
  
  List getFunctionList(Map paramMap, int paramInt1, int paramInt2, FunctionQueryOrder paramFunctionQueryOrder, boolean paramBoolean);
  
  List getAllFunction();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/FunctionManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */