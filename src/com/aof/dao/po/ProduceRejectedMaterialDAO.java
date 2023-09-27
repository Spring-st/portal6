package com.aof.dao.po;

import com.aof.dao.DAO;
import com.aof.model.po.ProduceRejectedMaterial;
import com.aof.model.po.query.ProduceRejectedMaterialQueryOrder;
import java.util.List;
import java.util.Map;

public interface ProduceRejectedMaterialDAO extends DAO {
  ProduceRejectedMaterial getProduceRejectedMaterial(Integer paramInteger);
  
  int getProduceRejectedMaterialListCount(Map paramMap);
  
  List getProduceRejectedMaterialList(Map paramMap, int paramInt1, int paramInt2, ProduceRejectedMaterialQueryOrder paramProduceRejectedMaterialQueryOrder, boolean paramBoolean);
  
  ProduceRejectedMaterial insertProduceRejectedMaterial(ProduceRejectedMaterial paramProduceRejectedMaterial);
  
  ProduceRejectedMaterial updateProduceRejectedMaterial(ProduceRejectedMaterial paramProduceRejectedMaterial);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/po/ProduceRejectedMaterialDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */