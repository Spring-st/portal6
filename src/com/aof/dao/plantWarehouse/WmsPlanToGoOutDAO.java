package com.aof.dao.plantWarehouse;

import com.aof.dao.DAO;
import com.aof.model.plantWarehouse.WmsPlanToGoOut;
import com.aof.model.plantWarehouse.WmsPlanToGoOutItem;
import com.aof.model.plantWarehouse.WmsPlanToGoOutSub;
import com.aof.model.plantWarehouse.query.WmsPlanToGoOutQueryOrder;
import java.util.List;
import java.util.Map;

public interface WmsPlanToGoOutDAO extends DAO {
  WmsPlanToGoOut getWmsPlanToGoOut(Integer paramInteger);
  
  WmsPlanToGoOut getWmsPlanToGoOutByCode(String paramString);
  
  int getWmsPlanToGoOutListCount(Map paramMap);
  
  List getWmsPlanToGoOutList(Map paramMap, int paramInt1, int paramInt2, WmsPlanToGoOutQueryOrder paramWmsPlanToGoOutQueryOrder, boolean paramBoolean);
  
  WmsPlanToGoOut insertWmsPlanToGoOut(WmsPlanToGoOut paramWmsPlanToGoOut);
  
  WmsPlanToGoOut updateWmsPlanToGoOut(WmsPlanToGoOut paramWmsPlanToGoOut);
  
  String getMaxPoApplicationIdBeginWith(String paramString);
  
  WmsPlanToGoOutItem getWmsPlanToGoOutItem(int paramInt);
  
  WmsPlanToGoOutItem insertWmsPlanToGoOutItem(WmsPlanToGoOutItem paramWmsPlanToGoOutItem);
  
  WmsPlanToGoOutItem updateWmsPlanToGoOutItem(WmsPlanToGoOutItem paramWmsPlanToGoOutItem);
  
  void deleteWmsPlanToGoOutItem(WmsPlanToGoOutItem paramWmsPlanToGoOutItem);
  
  List<WmsPlanToGoOutItem> getWmsPlanToGoOutItemByMain(Integer paramInteger);
  
  List<WmsPlanToGoOutItem> getWmsPlanToGoOutItemByBox(Integer paramInteger);
  
  List<WmsPlanToGoOutItem> getWmsPlanToGoOutItemByBoxLotser(String paramString);
  
  void deleteWmsPlanToGoOut(WmsPlanToGoOut paramWmsPlanToGoOut);
  
  WmsPlanToGoOutSub insertWmsPlanToGoOutSub(WmsPlanToGoOutSub paramWmsPlanToGoOutSub);
  
  List<WmsPlanToGoOutSub> getWmsPlanToGoOutSubByItem(Integer paramInteger);
  
  List<WmsPlanToGoOutSub> getWmsPlanToGoOutSubByMain(Integer paramInteger);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/plantWarehouse/WmsPlanToGoOutDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */