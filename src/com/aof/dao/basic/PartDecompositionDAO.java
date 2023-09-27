package com.aof.dao.basic;

import com.aof.model.basic.PartDecomposition;
import com.aof.model.basic.query.PartDecompositionQueryOrder;
import com.aof.model.po.Box;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PartDecompositionDAO {
  PartDecomposition getPartDecomposition(Integer paramInteger);
  
  List<PartDecomposition> getPartDecompositionByBox(Box paramBox);
  
  int getPartDecompositionCount(Map paramMap);
  
  List getPartDecompositionList(Map paramMap, int paramInt1, int paramInt2, PartDecompositionQueryOrder paramPartDecompositionQueryOrder, boolean paramBoolean);
  
  void insertPartDecomposition(PartDecomposition paramPartDecomposition);
  
  void updatePartDecomposition(PartDecomposition paramPartDecomposition);
  
  void deletePartDecomposition(PartDecomposition paramPartDecomposition);
  
  BigDecimal getLotPartCount(Box paramBox, String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/PartDecompositionDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */