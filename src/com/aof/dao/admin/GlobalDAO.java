package com.aof.dao.admin;

import com.aof.dao.DAO;
import com.aof.model.admin.GlobalParameter;

public interface GlobalDAO extends DAO {
  GlobalParameter getParameter();
  
  GlobalParameter saveUser(GlobalParameter paramGlobalParameter);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/GlobalDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */