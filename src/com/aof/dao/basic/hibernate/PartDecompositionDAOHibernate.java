/*    */ package com.aof.dao.basic.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.basic.PartDecompositionDAO;
/*    */ import com.aof.model.basic.PartDecomposition;
/*    */ import com.aof.model.basic.query.PartDecompositionQueryOrder;
/*    */ import com.aof.model.po.Box;
/*    */ import java.math.BigDecimal;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class PartDecompositionDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements PartDecompositionDAO
/*    */ {
/*    */   public PartDecomposition getPartDecomposition(Integer id) {
/* 17 */     return null;
/*    */   }
/*    */   
/*    */   public int getPartDecompositionCount(Map conditions) {
/* 21 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public List getPartDecompositionList(Map conditions, int pageNo, int pageSize, PartDecompositionQueryOrder order, boolean descend) {
/* 26 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void insertPartDecomposition(PartDecomposition partDecomposition) {
/* 31 */     getHibernateTemplate().save(partDecomposition);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updatePartDecomposition(PartDecomposition partDecomposition) {
/* 36 */     getHibernateTemplate().update(partDecomposition);
/*    */   }
/*    */   
/*    */   public void deletePartDecomposition(PartDecomposition partDecomposition) {
/* 40 */     getHibernateTemplate().delete(partDecomposition);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<PartDecomposition> getPartDecompositionByBox(Box box) {
/* 45 */     String hql = "from PartDecomposition p where p.box.id=?";
/* 46 */     return getHibernateTemplate().find(hql, box.getId());
/*    */   }
/*    */   
/*    */   public BigDecimal getLotPartCount(Box box, String partId) {
/* 50 */     String hql = "select sum(p.qty) from PartDecomposition p where p.box.lot.id=? and p.part.id=?";
/* 51 */     Object[] params = { box.getLot().getId(), partId };
/* 52 */     List<BigDecimal> object = getHibernateTemplate().find(hql, params);
/* 53 */     if (object == null || object.isEmpty()) {
/* 54 */       return new BigDecimal(0);
/*    */     }
/* 56 */     return object.get(0);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/basic/hibernate/PartDecompositionDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */