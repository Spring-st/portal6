package com.aof.service.po;

import com.aof.model.comprehensive.ProduceBuckleMaterial;
import com.aof.model.po.query.ProduceBuckleMaterialQueryOrder;
import java.util.List;
import java.util.Map;

public interface ProduceBuckleMaterialManager {
  ProduceBuckleMaterial getProduceBuckleMaterial(Integer paramInteger);
  
  int getProduceBuckleMaterialListCount(Map paramMap);
  
  List getProduceBuckleMaterialList(Map paramMap, int paramInt1, int paramInt2, ProduceBuckleMaterialQueryOrder paramProduceBuckleMaterialQueryOrder, boolean paramBoolean);
  
  ProduceBuckleMaterial insertProduceBuckleMaterial(ProduceBuckleMaterial paramProduceBuckleMaterial);
  
  ProduceBuckleMaterial updateProduceBuckleMaterial(ProduceBuckleMaterial paramProduceBuckleMaterial);
  
  Integer nextGrowth();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/ProduceBuckleMaterialManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */