package com.aof.service.admin;

import com.aof.model.admin.Metadata;
import com.aof.model.admin.MetadataDetail;
import com.aof.model.metadata.MetadataType;
import java.util.List;

public interface MetadataManager {
  Metadata getMetadata(Integer paramInteger);
  
  Metadata saveMetadata(Metadata paramMetadata);
  
  Metadata saveMetadata(MetadataType paramMetadataType);
  
  MetadataDetail getMetadataDetail(Integer paramInteger, Metadata paramMetadata);
  
  MetadataDetail getMetadataDetail(Integer paramInteger1, Integer paramInteger2);
  
  MetadataDetail saveMetadataDetail(MetadataDetail paramMetadataDetail);
  
  int getMetadataListCount();
  
  List getMetadataList(int paramInt1, int paramInt2);
}


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/MetadataManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */