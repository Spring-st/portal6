package com.aof.dao;

import java.io.Serializable;

public interface UniversalDAO {
  Object load(Class paramClass, Serializable paramSerializable);
  
  void refresh(Object paramObject);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/UniversalDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */