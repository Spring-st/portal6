/*     */ package com.aof.dao.admin.hibernate;
/*     */ 
/*     */ import com.aof.dao.BaseDAOHibernate;
/*     */ import com.aof.dao.admin.MetadataDAO;
/*     */ import com.aof.model.admin.Metadata;
/*     */ import com.aof.model.admin.MetadataDetail;
/*     */ import com.aof.model.admin.query.MetadataQueryOrder;
/*     */ import com.aof.model.metadata.MetadataDetailEnum;
/*     */ import com.aof.model.metadata.MetadataType;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MetadataDAOHibernate
/*     */   extends BaseDAOHibernate
/*     */   implements MetadataDAO
/*     */ {
/*  30 */   private Log log = LogFactory.getLog(MetadataDAOHibernate.class);
/*     */   
/*     */   private static final String SQL_COUNT = "select count(*) from Metadata metadata";
/*     */   
/*     */   private static final String SQL = "from Metadata metadata";
/*     */   
/*     */   public Metadata getMetadata(Integer id) {
/*  37 */     if (id == null) {
/*  38 */       if (this.log.isDebugEnabled()) this.log.debug("try to get Metadata with null id"); 
/*  39 */       return null;
/*     */     } 
/*  41 */     return (Metadata)getHibernateTemplate().get(Metadata.class, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Metadata saveMetadata(Metadata metadata) {
/*  48 */     if (metadata == null) {
/*  49 */       throw new RuntimeException("try to save null metadata, it's not allowed.");
/*     */     }
/*  51 */     Integer id = metadata.getId();
/*  52 */     MetadataType mt = (MetadataType)PersistentEnum.fromEnumCode(MetadataType.class, id);
/*  53 */     if (mt == null) {
/*  54 */       throw new RuntimeException("try to create new metadata with id '" + id + "', it's not allowed.");
/*     */     }
/*  56 */     mt.setLabel(metadata.getDescription());
/*  57 */     return saveMetadata(mt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Metadata saveMetadata(MetadataType mt) {
/*  64 */     if (mt == null) {
/*  65 */       if (this.log.isDebugEnabled()) this.log.debug("try to save/update null MetadataType"); 
/*  66 */       return null;
/*     */     } 
/*  68 */     Metadata m = getMetadata((Integer)mt.getEnumCode());
/*  69 */     if (m == null) {
/*  70 */       m = new Metadata();
/*  71 */       m.setId((Integer)mt.getEnumCode());
/*  72 */       m.setDescription(mt.getLabel());
/*  73 */       getHibernateTemplate().save(m);
/*  74 */       if (this.log.isDebugEnabled()) this.log.debug("init data, insert Metadata '" + m.getId() + "' into table"); 
/*  75 */       return m;
/*     */     } 
/*  77 */     m.setDescription(mt.getLabel());
/*  78 */     getHibernateTemplate().update(m);
/*  79 */     return m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MetadataDetail getMetadataDetail(Integer id, Metadata type) {
/*  86 */     if (id == null) {
/*  87 */       if (this.log.isDebugEnabled()) this.log.debug("try to get MetadataDetail with null id"); 
/*  88 */       return null;
/*     */     } 
/*  90 */     if (type == null && type.getId() == null) {
/*  91 */       if (this.log.isDebugEnabled()) this.log.debug("try to get MetadataDetail with null type"); 
/*  92 */       return null;
/*     */     } 
/*  94 */     MetadataDetail md = new MetadataDetail(id, type);
/*  95 */     return (MetadataDetail)getHibernateTemplate().get(MetadataDetail.class, (Serializable)md);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MetadataDetail getMetadataDetail(Integer id, Integer typeId) {
/* 102 */     return getMetadataDetail(id, new Metadata(typeId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MetadataDetail saveMetadataDetail(MetadataDetail metadataDetail) {
/* 109 */     if (metadataDetail == null) {
/* 110 */       throw new RuntimeException("try to save null metadataDetail, it's not allowed.");
/*     */     }
/* 112 */     Integer id = metadataDetail.getId();
/* 113 */     Metadata type = metadataDetail.getType();
/* 114 */     if (type == null) {
/* 115 */       throw new RuntimeException("try to save metadataDetail with null type, it's not allowed.");
/*     */     }
/* 117 */     MetadataType mt = (MetadataType)PersistentEnum.fromEnumCode(MetadataType.class, type.getId());
/* 118 */     if (mt == null) {
/* 119 */       throw new RuntimeException("try to save metadataDetail with a unknow type id '" + type.getId() + "', it's not allowed.");
/*     */     }
/* 121 */     MetadataDetailEnum mde = (MetadataDetailEnum)PersistentEnum.fromEnumCode(mt.getDetailClass(), id);
/* 122 */     if (mde == null) {
/* 123 */       throw new RuntimeException("try to create new metadataDetail with id '" + id + "' and type id '" + type.getId() + "', it's not allowed.");
/*     */     }
/* 125 */     mde.setEngDescription(metadataDetail.getEngDescription());
/* 126 */     mde.setChnDescription(metadataDetail.getChnDescription());
/* 127 */     mde.setEngShortDescription(metadataDetail.getEngShortDescription());
/* 128 */     mde.setChnShortDescription(metadataDetail.getChnShortDescription());
/* 129 */     mde.setColor(metadataDetail.getColor().trim());
/* 130 */     return saveMetadataDetail(mde);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MetadataDetail saveMetadataDetail(MetadataDetailEnum mde) {
/* 137 */     if (mde == null) {
/* 138 */       if (this.log.isDebugEnabled()) this.log.debug("try to save/update null MetadataDetailEnum"); 
/* 139 */       return null;
/*     */     } 
/* 141 */     Integer id = (Integer)mde.getEnumCode();
/* 142 */     MetadataType type = mde.getType();
/* 143 */     Metadata m = getMetadata((Integer)type.getEnumCode());
/* 144 */     if (m == null) m = saveMetadata(type); 
/* 145 */     MetadataDetail md = getMetadataDetail(id, m);
/* 146 */     String color = mde.getColor();
/* 147 */     if (md == null) {
/* 148 */       md = new MetadataDetail();
/* 149 */       md.setId(id);
/* 150 */       md.setType(m);
/* 151 */       md.setEngDescription(mde.getEngDescription());
/* 152 */       md.setChnDescription(mde.getChnDescription());
/* 153 */       md.setEngShortDescription(mde.getEngShortDescription());
/* 154 */       md.setChnShortDescription(mde.getChnShortDescription());
/* 155 */       md.setColor((color == null) ? "" : color.trim());
/* 156 */       getHibernateTemplate().save(md);
/* 157 */       if (this.log.isDebugEnabled()) this.log.debug("init data, insert MetadataDetail '" + md.getId() + ", " + m.getId() + "' into table"); 
/* 158 */       return md;
/*     */     } 
/* 160 */     md.setEngDescription(mde.getEngDescription());
/* 161 */     md.setChnDescription(mde.getChnDescription());
/* 162 */     md.setEngShortDescription(mde.getEngShortDescription());
/* 163 */     md.setChnShortDescription(mde.getChnShortDescription());
/* 164 */     md.setColor((color == null) ? "" : color.trim());
/* 165 */     getHibernateTemplate().update(md);
/* 166 */     return md;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getAllMetadataDetail() {
/* 173 */     return getHibernateTemplate().find("from MetadataDetail md");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 181 */       { MetadataQueryOrder.ID, "metadata.id" }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getMetadataList(int pageNo, int pageSize) {
/* 188 */     return getList(null, pageNo, pageSize, MetadataQueryOrder.ID, false, "from Metadata metadata", null, QUERY_ORDERS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetadataListCount() {
/* 195 */     return getListCount(null, "select count(*) from Metadata metadata", null);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/MetadataDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */