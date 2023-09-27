package com.aof.dao.po;

import com.aof.dao.DAO;
import com.aof.model.po.PoHighLineTwo;
import com.aof.model.po.query.PoHighLineTwoQueryOrder;
import java.util.List;
import java.util.Map;

public interface PoHighLineDAO extends DAO {
  PoHighLineTwo getPoHighLineTwo(Integer paramInteger);
  
  int getPoHighLineTwoListCount(Map paramMap);
  
  List getPoHighLineTwoList(Map paramMap, int paramInt1, int paramInt2, PoHighLineTwoQueryOrder paramPoHighLineTwoQueryOrder, boolean paramBoolean);
  
  PoHighLineTwo insertPoHighLineTwo(PoHighLineTwo paramPoHighLineTwo);
  
  PoHighLineTwo updatePoHighLineTwo(PoHighLineTwo paramPoHighLineTwo);
  
  int getPoHighLineOneListCount(Map paramMap);
  
  List getPoHighLineOneList(Map paramMap, int paramInt1, int paramInt2, PoHighLineTwoQueryOrder paramPoHighLineTwoQueryOrder, boolean paramBoolean);
  
  int getPoHighLineBelowListCount(Map paramMap);
  
  List getPoHighLineBelowList(Map paramMap, int paramInt1, int paramInt2, PoHighLineTwoQueryOrder paramPoHighLineTwoQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/PoHighLineDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */