package com.aof.service.po;

import com.aof.model.admin.User;
import com.aof.model.po.ProduceRejectedMaterial;
import com.aof.model.po.query.ProduceRejectedMaterialQueryOrder;
import java.util.List;
import java.util.Map;

public interface ProduceRejectedMaterialManager {
  ProduceRejectedMaterial getProduceRejectedMaterial(Integer paramInteger);
  
  int getProduceRejectedMaterialListCount(Map paramMap);
  
  List getProduceRejectedMaterialList(Map paramMap, int paramInt1, int paramInt2, ProduceRejectedMaterialQueryOrder paramProduceRejectedMaterialQueryOrder, boolean paramBoolean);
  
  ProduceRejectedMaterial insertProduceRejectedMaterial(ProduceRejectedMaterial paramProduceRejectedMaterial);
  
  ProduceRejectedMaterial updateProduceRejectedMaterial(ProduceRejectedMaterial paramProduceRejectedMaterial);
  
  String updateProduceRejectedMaterial(String paramString, User paramUser);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/po/ProduceRejectedMaterialManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */