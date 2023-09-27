package com.aof.service.schedule;

import com.aof.model.schedule.ProjectedInventory;
import com.aof.model.schedule.query.ProjectedInventoryQueryOrder;
import java.util.List;
import java.util.Map;

public interface ProjectedInventoryManager {
  ProjectedInventory save(ProjectedInventory paramProjectedInventory);
  
  ProjectedInventory getProjectedInventory(Integer paramInteger);
  
  void delete(ProjectedInventory paramProjectedInventory);
  
  ProjectedInventory update(ProjectedInventory paramProjectedInventory);
  
  Integer getListCount(Map paramMap);
  
  List<ProjectedInventory> getList(Map paramMap, int paramInt1, int paramInt2, ProjectedInventoryQueryOrder paramProjectedInventoryQueryOrder, boolean paramBoolean);
  
  List<ProjectedInventory> getJitShipPartNumberList(Map paramMap, int paramInt1, int paramInt2, ProjectedInventoryQueryOrder paramProjectedInventoryQueryOrder, boolean paramBoolean);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/schedule/ProjectedInventoryManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */