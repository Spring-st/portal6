package com.aof.model.business;

import com.aof.model.admin.Department;
import com.aof.model.admin.User;

public interface BuyForTarget extends Rechargeable {
  User getBuyForUser();
  
  void setBuyForUser(User paramUser);
  
  Department getBuyForDepartment();
  
  void setBuyForDepartment(Department paramDepartment);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/business/BuyForTarget.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */