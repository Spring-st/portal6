package com.aof.service.admin;

import com.aof.model.admin.PurchaseType;
import com.aof.model.admin.Site;
import java.util.List;

public interface PurchaseTypeManager {
  PurchaseType getPurchaseType(String paramString);
  
  List getEnabledPurchaseTypeList(Site paramSite);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/PurchaseTypeManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */