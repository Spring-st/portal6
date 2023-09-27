/*    */ package com.aof.dao.hibernate;
/*    */ 
/*    */ import com.aof.dao.BaseDAOHibernate;
/*    */ import com.aof.dao.UniversalDAO;
/*    */ import java.io.Serializable;
/*    */ import java.sql.SQLException;
/*    */ import net.sf.hibernate.HibernateException;
/*    */ import net.sf.hibernate.Session;
/*    */ import net.sf.hibernate.SessionFactory;
/*    */ import net.sf.hibernate.metadata.ClassMetadata;
/*    */ import org.springframework.orm.hibernate.HibernateCallback;
/*    */ 
/*    */ 
/*    */ public class UniversalDAOImpl
/*    */   extends BaseDAOHibernate
/*    */   implements UniversalDAO
/*    */ {
/*    */   public Object load(Class clazz, Serializable idValue) {
/* 19 */     return getHibernateTemplate().get(clazz, idValue);
/*    */   }
/*    */   
/*    */   public void refresh(final Object object) {
/* 23 */     getHibernateTemplate().execute(new HibernateCallback()
/*    */         {
/*    */           public Object doInHibernate(Session session) throws HibernateException, SQLException {
/* 26 */             session.refresh(object);
/* 27 */             if (!session.contains(object)) {
/* 28 */               Class<?> clazz = object.getClass();
/* 29 */               SessionFactory sf = session.getSessionFactory();
/* 30 */               ClassMetadata cm = sf.getClassMetadata(clazz);
/* 31 */               Serializable id = null;
/* 32 */               if (cm.hasIdentifierProperty()) {
/* 33 */                 id = cm.getIdentifier(object);
/*    */               } else {
/* 35 */                 id = (Serializable)object;
/*    */               } 
/* 37 */               Object objInSession = session.get(clazz, id);
/* 38 */               session.flush();
/* 39 */               session.evict(objInSession);
/* 40 */               session.load(object, id);
/*    */             } 
/* 42 */             return object;
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/dao/hibernate/UniversalDAOImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */