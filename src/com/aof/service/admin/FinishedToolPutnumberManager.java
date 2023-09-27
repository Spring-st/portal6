package com.aof.service.admin;

import com.aof.model.admin.FinishedToolPutnumber;
import com.aof.model.admin.query.FinishedToolPutnumberQueryOrder;
import java.util.List;
import java.util.Map;

public interface FinishedToolPutnumberManager {
  FinishedToolPutnumber getFinishedToolPutnumber(Integer paramInteger);
  
  int getFinishedToolPutnumberListCount(Map paramMap);
  
  List getFinishedToolPutnumberList(Map paramMap, int paramInt1, int paramInt2, FinishedToolPutnumberQueryOrder paramFinishedToolPutnumberQueryOrder, boolean paramBoolean);
  
  FinishedToolPutnumber insertFinishedToolPutnumber(FinishedToolPutnumber paramFinishedToolPutnumber);
  
  FinishedToolPutnumber updateFinishedToolPutnumber(FinishedToolPutnumber paramFinishedToolPutnumber);
  
  void deleteFinishedToolPutnumber(FinishedToolPutnumber paramFinishedToolPutnumber);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/FinishedToolPutnumberManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */