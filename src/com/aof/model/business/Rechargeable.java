package com.aof.model.business;

import com.aof.model.metadata.YesNo;

public interface Rechargeable {
  BaseRecharge createNewRechargeObj();
  
  YesNo getIsRecharge();
  
  void setIsRecharge(YesNo paramYesNo);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/Rechargeable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */