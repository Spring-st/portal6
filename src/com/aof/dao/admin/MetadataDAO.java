package com.aof.dao.admin;

import com.aof.dao.DAO;
import com.aof.model.admin.Metadata;
import com.aof.model.admin.MetadataDetail;
import com.aof.model.metadata.MetadataDetailEnum;
import com.aof.model.metadata.MetadataType;
import java.util.List;

public interface MetadataDAO extends DAO {
  Metadata getMetadata(Integer paramInteger);
  
  Metadata saveMetadata(Metadata paramMetadata);
  
  Metadata saveMetadata(MetadataType paramMetadataType);
  
  MetadataDetail getMetadataDetail(Integer paramInteger, Metadata paramMetadata);
  
  MetadataDetail getMetadataDetail(Integer paramInteger1, Integer paramInteger2);
  
  MetadataDetail saveMetadataDetail(MetadataDetail paramMetadataDetail);
  
  MetadataDetail saveMetadataDetail(MetadataDetailEnum paramMetadataDetailEnum);
  
  List getAllMetadataDetail();
  
  int getMetadataListCount();
  
  List getMetadataList(int paramInt1, int paramInt2);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/MetadataDAO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */