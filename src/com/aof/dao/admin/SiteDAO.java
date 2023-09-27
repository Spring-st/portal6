package com.aof.dao.admin;

import com.aof.dao.DAO;
import com.aof.model.admin.Site;
import com.aof.model.admin.query.SiteQueryOrder;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface SiteDAO extends DAO {
  Site getSite(Integer paramInteger);
  
  Site saveSite(Site paramSite);
  
  void removeSite(Integer paramInteger);
  
  int getSiteListCount(Map paramMap);
  
  List getSiteList(Map paramMap, int paramInt1, int paramInt2, SiteQueryOrder paramSiteQueryOrder, boolean paramBoolean);
  
  boolean isSiteHasLogo(Integer paramInteger);
  
  InputStream getSiteLogo(Integer paramInteger);
  
  void saveSiteLogo(Integer paramInteger, InputStream paramInputStream);
  
  List getEnabledSiteList();
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/SiteDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */