/*    */ package com.aof.dao.schedule.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.schedule.NjitNpoPlanDao;
/*    */ import com.aof.model.schedule.NjitNpoPlan;
/*    */ import com.aof.model.schedule.query.NjitNpoPlanQueryCondition;
/*    */ import com.aof.model.schedule.query.NjitNpoPlanQueryOrder;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.sf.hibernate.Hibernate;
/*    */ import net.sf.hibernate.type.Type;
/*    */ import org.apache.commons.beanutils.PropertyUtils;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NjitNpoPlanDaoHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements NjitNpoPlanDao
/*    */ {
/* 22 */   private Log log = LogFactory.getLog(NjitNpoPlanDaoHibernate.class); private static final String SQL_COUNT = "select count(*) from NjitNpoPlan entity";
/*    */   public NjitNpoPlan save(NjitNpoPlan entity) {
/* 24 */     getHibernateTemplate().save(entity);
/* 25 */     return entity;
/*    */   }
/*    */   private static final String SQL = "from NjitNpoPlan entity";
/*    */   public NjitNpoPlan getNjitNpoPlan(Integer id) {
/* 29 */     if (id == null && 
/* 30 */       this.log.isDebugEnabled()) {
/* 31 */       this.log.debug("get NjitNpoPlan with null id!");
/* 32 */       return null;
/*    */     } 
/*    */     
/* 35 */     return (NjitNpoPlan)getHibernateTemplate().get(NjitNpoPlan.class, id);
/*    */   }
/*    */   
/*    */   public void delete(NjitNpoPlan entity) {
/* 39 */     getHibernateTemplate().delete(entity);
/*    */   }
/*    */   
/*    */   public NjitNpoPlan update(NjitNpoPlan entity) {
/* 43 */     if (entity.getId() == null) {
/* 44 */       throw new RuntimeException("update NjitNpoPlan with null id!");
/*    */     }
/* 46 */     NjitNpoPlan oldEntity = getNjitNpoPlan(entity.getId());
/* 47 */     if (oldEntity != null) {
/*    */       try {
/* 49 */         PropertyUtils.copyProperties(oldEntity, entity);
/* 50 */       } catch (Exception e) {
/* 51 */         throw new RuntimeException("copy error with MappingCsutomer" + e);
/*    */       } 
/* 53 */       getHibernateTemplate().update(oldEntity);
/* 54 */       return oldEntity;
/*    */     } 
/* 56 */     throw new RuntimeException("MappinCustomer not found");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   private static final Object[][] QUERY_CONDITIONS = new Object[][] {
/* 65 */       { NjitNpoPlanQueryCondition.ID_EQ, "entity.id = ?"
/* 66 */       }, { NjitNpoPlanQueryCondition.PARTID_VEND_EQ, "entity.partId.vend = ?" }
/*    */     };
/*    */   
/* 69 */   private static final Object[][] QUERY_ORDERS = new Object[][] {
/* 70 */       { NjitNpoPlanQueryOrder.ID, "entity.id"
/* 71 */       }, { NjitNpoPlanQueryOrder.PART_ID, "entity.partId.id" }
/*    */     };
/*    */   
/*    */   public Integer getListCount(Map conditions) {
/* 75 */     return Integer.valueOf(getListCount(conditions, "select count(*) from NjitNpoPlan entity", QUERY_CONDITIONS));
/*    */   }
/*    */ 
/*    */   
/*    */   public List<NjitNpoPlan> getList(Map conditions, int pageNum, int pageSize, NjitNpoPlanQueryOrder order, boolean descend) {
/* 80 */     return getList(conditions, pageNum, pageSize, order, descend, "from NjitNpoPlan entity", QUERY_CONDITIONS, QUERY_ORDERS);
/*    */   }
/*    */   
/*    */   public NjitNpoPlan getNjitNpoPlanByPart(String partId) {
/* 84 */     String sql = "from NjitNpoPlan njitNpoPlan where njitNpoPlan.partId.id = ?";
/* 85 */     List<NjitNpoPlan> njitNpoPlanList = getHibernateTemplate().find(sql, partId, (Type)Hibernate.STRING);
/* 86 */     NjitNpoPlan njitNpoPlan = null;
/* 87 */     if (njitNpoPlanList != null && njitNpoPlanList.size() > 0) {
/* 88 */       njitNpoPlan = njitNpoPlanList.get(0);
/*    */     }
/* 90 */     return njitNpoPlan;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/schedule/hibernate/NjitNpoPlanDaoHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */