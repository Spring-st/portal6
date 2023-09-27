/*    */ package com.aof.dao.admin.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.admin.PurchaseTypeDAO;
/*    */ import com.aof.model.admin.PurchaseType;
/*    */ import com.aof.model.admin.Site;
/*    */ import com.aof.model.metadata.EnabledDisabled;
/*    */ import com.shcnc.hibernate.CompositeQuery;
/*    */ import java.sql.SQLException;
/*    */ import java.util.List;
/*    */ import net.sf.hibernate.HibernateException;
/*    */ import net.sf.hibernate.Session;
/*    */ import org.springframework.orm.hibernate.HibernateCallback;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PurchaseTypeDAOHibernate
/*    */   extends BaseDAOHibernate
/*    */   implements PurchaseTypeDAO
/*    */ {
/*    */   public PurchaseType getPurchaseType(String code) {
/* 22 */     return (PurchaseType)getHibernateTemplate().get(PurchaseType.class, code);
/*    */   }
/*    */   
/*    */   public List getEnabledPurchaseTypeList(final Site site) {
/* 26 */     return (List)getHibernateTemplate().execute(new HibernateCallback() {
/*    */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 28 */             String sql = "from PurchaseType pt";
/* 29 */             CompositeQuery query = new CompositeQuery(sql, "pt.description", session);
/* 30 */             query.createQueryCondition("pt.enabled=?").appendParameter(EnabledDisabled.ENABLED.getEnumCode());
/* 31 */             query.createQueryCondition("pt.site.id=?").appendParameter(site.getId());
/* 32 */             return query.list();
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/admin/hibernate/PurchaseTypeDAOHibernate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */