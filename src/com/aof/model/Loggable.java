package com.aof.model;

import com.aof.model.admin.Site;

public interface Loggable {
  Site getLogSite();
  
  String getLogTargetName();
  
  String getLogTargetId();
  
  String[][] getLogFieldInfo(String paramString);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/model/Loggable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */