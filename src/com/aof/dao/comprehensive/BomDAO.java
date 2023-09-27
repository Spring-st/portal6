package com.aof.dao.comprehensive;

import com.aof.dao.DAO;
import com.aof.model.comprehensive.Bom;
import com.aof.model.comprehensive.query.BomQueryOrder;
import java.util.List;
import java.util.Map;

public interface BomDAO extends DAO {
  Bom getBom(Integer paramInteger);
  
  int getBomListCount(Map paramMap);
  
  List getBomList(Map paramMap, int paramInt1, int paramInt2, BomQueryOrder paramBomQueryOrder, boolean paramBoolean);
  
  Bom insertBom(Bom paramBom);
  
  Bom updateBom(Bom paramBom);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/comprehensive/BomDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */