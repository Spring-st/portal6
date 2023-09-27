/*    */ package com.aof.web.struts.action;
/*    */ 
/*    */ import com.aof.service.UniversalManager;
/*    */ import com.shcnc.struts.form.beanloader.BeanLoader;
/*    */ import com.shcnc.struts.form.beanloader.UnableToLoadException;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BeanLoader
/*    */   implements BeanLoader {
/* 10 */   private UniversalManager universalManager = null;
/*    */   
/*    */   public Object load(Class clazz, String idKey, Serializable idValue) throws UnableToLoadException {
/* 13 */     return this.universalManager.load(clazz, idValue);
/*    */   }
/*    */   
/*    */   public void setUniversalManager(UniversalManager universalManager) {
/* 17 */     this.universalManager = universalManager;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/action/BeanLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */