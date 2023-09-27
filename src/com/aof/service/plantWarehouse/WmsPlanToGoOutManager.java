package com.aof.service.plantWarehouse;

import com.aof.model.plantWarehouse.WmsPlanToGoOut;
import com.aof.model.plantWarehouse.WmsPlanToGoOutItem;
import com.aof.model.plantWarehouse.WmsPlanToGoOutSub;
import com.aof.model.plantWarehouse.query.WmsPlanToGoOutQueryOrder;
import java.util.List;
import java.util.Map;
import jxl.Sheet;

public interface WmsPlanToGoOutManager {
  WmsPlanToGoOut getWmsPlanToGoOut(Integer paramInteger);
  
  int getWmsPlanToGoOutListCount(Map paramMap);
  
  List getWmsPlanToGoOutList(Map paramMap, int paramInt1, int paramInt2, WmsPlanToGoOutQueryOrder paramWmsPlanToGoOutQueryOrder, boolean paramBoolean);
  
  WmsPlanToGoOut insertWmsPlanToGoOut(WmsPlanToGoOut paramWmsPlanToGoOut);
  
  WmsPlanToGoOut updateWmsPlanToGoOut(WmsPlanToGoOut paramWmsPlanToGoOut);
  
  void deleteWmsPlanToGoOut(WmsPlanToGoOut paramWmsPlanToGoOut);
  
  String getMaxPoApplicationIdBeginWith(String paramString);
  
  WmsPlanToGoOutItem getWmsPlanToGoOutItem(int paramInt);
  
  List<WmsPlanToGoOutItem> getWmsPlanToGoOutItemByMain(Integer paramInteger);
  
  WmsPlanToGoOutItem insertWmsPlanToGoOutItem(WmsPlanToGoOutItem paramWmsPlanToGoOutItem);
  
  WmsPlanToGoOutItem updateWmsPlanToGoOutItem(WmsPlanToGoOutItem paramWmsPlanToGoOutItem);
  
  void deleteWmsPlanToGoOutItem(WmsPlanToGoOutItem paramWmsPlanToGoOutItem);
  
  WmsPlanToGoOutItem updateWmsPlanToGoOutItemByList(String[] paramArrayOfString, WmsPlanToGoOut paramWmsPlanToGoOut);
  
  String scanningWmsPlanToGoOut(String paramString, Integer paramInteger);
  
  String scanningUnplannedOutbound(String paramString1, String paramString2, String paramString3);
  
  void insertWmsPlanToGoOutItemByMain(WmsPlanToGoOut paramWmsPlanToGoOut, String paramString);
  
  boolean checkWmsPlanToGoOutItemByAmount(String paramString1, String paramString2, String paramString3);
  
  void updateWmsPlanToGoOutByBox(WmsPlanToGoOut paramWmsPlanToGoOut, String[] paramArrayOfString);
  
  Map updateWmsPlanToGoOutByManual(WmsPlanToGoOut paramWmsPlanToGoOut);
  
  WmsPlanToGoOutSub insertWmsPlanToGoOutSub(WmsPlanToGoOutSub paramWmsPlanToGoOutSub);
  
  List<WmsPlanToGoOutSub> getWmsPlanToGoOutSub(List<WmsPlanToGoOutItem> paramList);
  
  List<Map> getRecommendLotset(List<WmsPlanToGoOutItem> paramList);
  
  List<Map> getImportWmsPlanToGoOutList(Sheet[] paramArrayOfSheet);
  
  void updateWmsPlanToGoOutByManualScannery(WmsPlanToGoOut paramWmsPlanToGoOut);
  
  List<WmsPlanToGoOut> updateWmsPlanToGoOutByPlanNum(List<WmsPlanToGoOut> paramList);
  
  List<WmsPlanToGoOutSub> getWmsPlanToGoOutSubByMain(Integer paramInteger);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/plantWarehouse/WmsPlanToGoOutManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */