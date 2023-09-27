package com.aof.dao.comprehensive;

import com.aof.dao.DAO;
import com.aof.model.comprehensive.BoxAdjustment;
import com.aof.model.comprehensive.BoxAdjustmentBox;
import com.aof.model.comprehensive.query.BoxAdjustmentQueryOrder;
import java.util.List;
import java.util.Map;

public interface BoxAdjustmentDAO extends DAO {
  BoxAdjustment getBoxAdjustment(Integer paramInteger);
  
  int getBoxAdjustmentListCount(Map paramMap);
  
  List getBoxAdjustmentList(Map paramMap, int paramInt1, int paramInt2, BoxAdjustmentQueryOrder paramBoxAdjustmentQueryOrder, boolean paramBoolean);
  
  BoxAdjustment insertBoxAdjustment(BoxAdjustment paramBoxAdjustment);
  
  BoxAdjustment updateBoxAdjustment(BoxAdjustment paramBoxAdjustment);
  
  String getMaxBoxAdjustmentIdBeginWith(String paramString);
  
  List<BoxAdjustmentBox> getBoxAdjustmentBoxByMain(Integer paramInteger);
  
  int getBoxAdjustmentBoxListCount(Map paramMap);
  
  List getBoxAdjustmentBoxList(Map paramMap, int paramInt1, int paramInt2, BoxAdjustmentQueryOrder paramBoxAdjustmentQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/comprehensive/BoxAdjustmentDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */