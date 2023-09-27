package com.aof.service.comprehensive;

import com.aof.model.comprehensive.BoxAdjustment;
import com.aof.model.comprehensive.BoxAdjustmentBox;
import com.aof.model.comprehensive.query.BoxAdjustmentQueryOrder;
import java.util.List;
import java.util.Map;

public interface BoxAdjustmentManager {
  BoxAdjustment getBoxAdjustment(Integer paramInteger);
  
  int getBoxAdjustmentListCount(Map paramMap);
  
  List getBoxAdjustmentList(Map paramMap, int paramInt1, int paramInt2, BoxAdjustmentQueryOrder paramBoxAdjustmentQueryOrder, boolean paramBoolean);
  
  BoxAdjustment insertBoxAdjustment(BoxAdjustment paramBoxAdjustment);
  
  BoxAdjustment updateBoxAdjustment(BoxAdjustment paramBoxAdjustment);
  
  String getMaxBoxAdjustmentIdBeginWith(String paramString);
  
  String insertBoxAdjustmentBox(String paramString1, String paramString2, String paramString3, String paramString4);
  
  String scanningLotBreakUp(String paramString1, String paramString2, String paramString3);
  
  String scanningLotMerge(String paramString1, String paramString2);
  
  List<BoxAdjustmentBox> getBoxAdjustmentBoxByMain(Integer paramInteger);
  
  int getBoxAdjustmentBoxListCount(Map paramMap);
  
  List getBoxAdjustmentBoxList(Map paramMap, int paramInt1, int paramInt2, BoxAdjustmentQueryOrder paramBoxAdjustmentQueryOrder, boolean paramBoolean);
  
  String ListBoxInfoNumber(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/comprehensive/BoxAdjustmentManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */