package com.aof.service.admin;

import com.aof.model.admin.Site;
import com.aof.model.admin.query.SiteQueryOrder;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface SiteManager {
  Site getSite(Integer paramInteger);
  
  Site saveSite(Site paramSite);
  
  Site saveSite(Site paramSite, InputStream paramInputStream);
  
  void removeSite(Integer paramInteger);
  
  int getSiteListCount(Map paramMap);
  
  List getSiteList(Map paramMap, int paramInt1, int paramInt2, SiteQueryOrder paramSiteQueryOrder, boolean paramBoolean);
  
  List getAllEnabledSiteList();
  
  List getAllEnabledSiteListAndIncludeThis(Integer paramInteger);
  
  boolean isSiteHasLogo(Integer paramInteger);
  
  InputStream getSiteLogo(Integer paramInteger);
  
  void saveSiteLogo(Integer paramInteger, InputStream paramInputStream);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/SiteManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */