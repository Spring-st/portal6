package com.aof.service.basic;

import com.aof.model.basic.PartDecomposition;
import com.aof.model.basic.query.PartDecompositionQueryOrder;
import com.aof.model.po.Box;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PartDecompositionManager {
  PartDecomposition getPartDecomposition(Integer paramInteger);
  
  List<PartDecomposition> getPartDecompositionByBox(Box paramBox);
  
  int getPartDecompositionCount(Map paramMap);
  
  List getPartDecompositionList(Map paramMap, int paramInt1, int paramInt2, PartDecompositionQueryOrder paramPartDecompositionQueryOrder, boolean paramBoolean);
  
  void insertPartDecomposition(PartDecomposition paramPartDecomposition);
  
  void updatePartDecomposition(PartDecomposition paramPartDecomposition);
  
  BigDecimal getLotPartCount(Box paramBox, String paramString);
  
  void deletePartDecomposition(PartDecomposition paramPartDecomposition);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/basic/PartDecompositionManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */