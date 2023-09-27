package com.aof.service.comprehensive;

import com.aof.model.comprehensive.Bom;
import com.aof.model.comprehensive.query.BomQueryOrder;
import java.util.List;
import java.util.Map;

public interface BomManager {
  Bom getBom(Integer paramInteger);
  
  int getBomListCount(Map paramMap);
  
  List getBomList(Map paramMap, int paramInt1, int paramInt2, BomQueryOrder paramBomQueryOrder, boolean paramBoolean);
  
  Bom insertBom(Bom paramBom);
  
  Bom updateBom(Bom paramBom);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/comprehensive/BomManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */