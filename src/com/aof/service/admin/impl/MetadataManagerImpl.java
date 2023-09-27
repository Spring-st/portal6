/*     */ package com.aof.service.admin.impl;
/*     */ 
/*     */ import com.aof.dao.admin.MetadataDAO;
/*     */ import com.aof.model.admin.Metadata;
/*     */ import com.aof.model.admin.MetadataDetail;
/*     */ import com.aof.model.metadata.MetadataDetailEnum;
/*     */ import com.aof.model.metadata.MetadataType;
/*     */ import com.aof.service.BaseManager;
/*     */ import com.aof.service.admin.MetadataManager;
/*     */ import com.shcnc.hibernate.PersistentEnum;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MetadataManagerImpl
/*     */   extends BaseManager
/*     */   implements MetadataManager
/*     */ {
/*     */   private MetadataDAO dao;
/*     */   
/*     */   public void setMetadataDAO(MetadataDAO dao) {
/*  37 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  45 */     Map<Object, Object> metadatas = new HashMap<Object, Object>();
/*  46 */     Map<Object, Object> metadataDetails = new HashMap<Object, Object>();
/*  47 */     for (Iterator<MetadataDetail> iterator = this.dao.getAllMetadataDetail().iterator(); iterator.hasNext(); ) {
/*  48 */       MetadataDetail md = iterator.next();
/*  49 */       Metadata m = md.getType();
/*  50 */       Integer metadataId = m.getId();
/*  51 */       Map<Object, Object> mm = (Map)metadataDetails.get(metadataId);
/*  52 */       if (mm == null) {
/*  53 */         metadatas.put(metadataId, m);
/*  54 */         mm = new HashMap<Object, Object>();
/*  55 */         metadataDetails.put(metadataId, mm);
/*     */       } 
/*  57 */       mm.put(md.getId(), md);
/*     */     } 
/*     */     
/*  60 */     Iterator<MetadataType> itor = PersistentEnum.getEnumList(MetadataType.class).iterator();
/*  61 */     while (itor.hasNext()) {
/*  62 */       MetadataType mt = itor.next();
/*  63 */       Metadata m = (Metadata)metadatas.get(mt.getEnumCode());
/*  64 */       Map mm = (Map)metadataDetails.get(mt.getEnumCode());
/*  65 */       if (m == null) {
/*  66 */         mt.setLabel(mt.getDefaultLabel());
/*  67 */         this.dao.saveMetadata(mt);
/*     */       } else {
/*  69 */         mt.setLabel(m.getDescription());
/*     */       } 
/*  71 */       Iterator<MetadataDetailEnum> itor2 = PersistentEnum.getEnumList(mt.getDetailClass()).iterator();
/*  72 */       while (itor2.hasNext()) {
/*  73 */         MetadataDetailEnum mde = itor2.next();
/*  74 */         MetadataDetail md = (mm == null) ? null : (MetadataDetail)mm.get(mde.getEnumCode());
/*  75 */         if (md == null) {
/*  76 */           String desc = mde.getLabel();
/*  77 */           mde.setEngDescription(desc);
/*  78 */           mde.setChnDescription(desc);
/*  79 */           mde.setEngShortDescription(desc);
/*  80 */           mde.setChnShortDescription(desc);
/*  81 */           mde.setColor(null);
/*  82 */           this.dao.saveMetadataDetail(mde); continue;
/*     */         } 
/*  84 */         mde.setEngDescription(md.getEngDescription());
/*  85 */         mde.setChnDescription(md.getChnDescription());
/*  86 */         mde.setEngShortDescription(md.getEngShortDescription());
/*  87 */         mde.setChnShortDescription(md.getChnShortDescription());
/*  88 */         mde.setColor(md.getColor());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Metadata getMetadata(Integer id) {
/*  98 */     return this.dao.getMetadata(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Metadata saveMetadata(Metadata metadata) {
/* 105 */     return this.dao.saveMetadata(metadata);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Metadata saveMetadata(MetadataType metadataType) {
/* 113 */     return this.dao.saveMetadata(metadataType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MetadataDetail getMetadataDetail(Integer id, Metadata type) {
/* 121 */     return this.dao.getMetadataDetail(id, type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MetadataDetail getMetadataDetail(Integer id, Integer typeId) {
/* 129 */     return this.dao.getMetadataDetail(id, typeId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MetadataDetail saveMetadataDetail(MetadataDetail metadataDetail) {
/* 137 */     return this.dao.saveMetadataDetail(metadataDetail);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getMetadataList(int pageNo, int pageSize) {
/* 145 */     return this.dao.getMetadataList(pageNo, pageSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetadataListCount() {
/* 152 */     return this.dao.getMetadataListCount();
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/admin/impl/MetadataManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */