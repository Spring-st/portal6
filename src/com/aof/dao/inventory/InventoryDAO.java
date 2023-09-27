package com.aof.dao.inventory;

import com.aof.dao.DAO;
import com.aof.model.basicDataView.query.LocationPartNumberQueryOrder;
import com.aof.model.inventory.Inventory;
import com.aof.model.inventory.InventoryDetial;
import com.aof.model.inventory.InventoryRecord;
import com.aof.model.inventory.InventoryTotal;
import com.aof.model.inventory.query.InventoryQueryOrder;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface InventoryDAO extends DAO {
  List<Object> getInventoryListsum(Map paramMap);
  
  Inventory getInventory(Integer paramInteger);
  
  List<Inventory> getInventoryByPart(String paramString);
  
  InventoryRecord getInventoryRecordByInventoryId(Integer paramInteger);
  
  int getInventoryListCount(Map paramMap);
  
  List getInventoryList(Map paramMap, int paramInt1, int paramInt2, InventoryQueryOrder paramInventoryQueryOrder, boolean paramBoolean);
  
  Inventory insertInventory(Inventory paramInventory);
  
  InventoryRecord insertInventoryRecord(InventoryRecord paramInventoryRecord);
  
  Inventory updateInventory(Inventory paramInventory);
  
  void deleteInventory(Inventory paramInventory);
  
  InventoryRecord getInventoryRecord(Integer paramInteger);
  
  List<Inventory> getInventoryByPart(String paramString, Integer paramInteger);
  
  List<Inventory> getInventoryByRoomAndPart(Integer paramInteger, String paramString);
  
  List<Inventory> getInventoryByPart(String paramString, Integer paramInteger1, Integer paramInteger2);
  
  BigDecimal getInventoryRecord(Date paramDate1, Date paramDate2, String paramString);
  
  Inventory getInventoryByPartIdAndLocation(String paramString);
  
  List getInventoryRecordReturnPeriodInitialValue(String paramString1, String paramString2);
  
  InventoryRecord getInventoryRecordByLocationAndPart(String paramString1, String paramString2);
  
  InventoryRecord getInventoryRecordByLocationAndPart(String paramString);
  
  InventoryRecord getInventoryRecordByLocationAndPartBalance(String paramString1, String paramString2);
  
  InventoryRecord getInventoryRecordByLocationAndPartBalance(String paramString);
  
  BigDecimal getInventoryBySemiFinished();
  
  BigDecimal getInventoryByPowderStorage();
  
  InventoryDetial getInventoryDetial(Integer paramInteger);
  
  InventoryDetial insertInventoryDetial(InventoryDetial paramInventoryDetial);
  
  InventoryDetial updateInventoryDetial(InventoryDetial paramInventoryDetial);
  
  InventoryTotal getInventoryTotal(Integer paramInteger);
  
  InventoryTotal insertInventoryTotal(InventoryTotal paramInventoryTotal);
  
  InventoryTotal updateInventoryTotal(InventoryTotal paramInventoryTotal);
  
  InventoryDetial getInventoryTotalByPart(String paramString);
  
  int getInventoryReportListCount(Map paramMap);
  
  List getInventoryReportList(Map paramMap, int paramInt1, int paramInt2, InventoryQueryOrder paramInventoryQueryOrder, boolean paramBoolean);
  
  List test();
  
  int getInventoryDetialListCount(Map paramMap);
  
  List getInventoryDetialList(Map paramMap, int paramInt1, int paramInt2, InventoryQueryOrder paramInventoryQueryOrder, boolean paramBoolean);
  
  List getInventoryDetialByPartList(Map paramMap, int paramInt1, int paramInt2, InventoryQueryOrder paramInventoryQueryOrder, boolean paramBoolean);
  
  int getInventoryDetialByPartListCount(Map paramMap);
  
  int getInventoryTotalList(Map paramMap);
  
  List getInventoryTotalList(Map paramMap, int paramInt1, int paramInt2, InventoryQueryOrder paramInventoryQueryOrder, boolean paramBoolean);
  
  InventoryDetial getInventoryDetialByPartAndLocation(String paramString, Integer paramInteger);
  
  List getBoxSumCount(String paramString);
  
  int getLocationPartNumberListCount(Map paramMap);
  
  List getLocationPartNumberList(Map paramMap, int paramInt1, int paramInt2, LocationPartNumberQueryOrder paramLocationPartNumberQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/inventory/InventoryDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */