package com.aof.dao;

import com.aof.model.po.Box;
import java.io.Serializable;
import java.util.List;

public interface DAO {
  Object refreshObject(Object paramObject);
  
  void saveObject(Object paramObject);
  
  void updateObject(Object paramObject);
  
  void removeObject(Object paramObject);
  
  List getObjectList(String paramString);
  
  List<Box> getObjectListBox(String paramString);
  
  Object getObject(Class paramClass, Serializable paramSerializable);
  
  void businessCommit();
  
  void commit();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/DAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */