/*    */ package com.aof.web.struts.repeatsubmit;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import javax.servlet.ServletException;
/*    */ import org.apache.struts.action.ActionServlet;
/*    */ import org.apache.struts.action.PlugIn;
/*    */ import org.apache.struts.config.ActionConfig;
/*    */ import org.apache.struts.config.ModuleConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PreventRepeatSubmitPlugIn
/*    */   implements PlugIn
/*    */ {
/*    */   public static final String ATTRIBUTE_FROMMAP = "com.shcnc.struts.PreventRepeatSubmitPlugIn.frommap";
/*    */   public static final String ATTRIBUTE_TOMAP = "com.shcnc.struts.PreventRepeatSubmitPlugIn.tomap";
/*    */   public static final String ATTRIBUTE_TOKENMAP = "com.shcnc.struts.PreventRepeatSubmitPlugIn.tokenmap";
/*    */   
/*    */   public void destroy() {}
/*    */   
/*    */   public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {
/* 30 */     Map<Object, Object> fromMap = new HashMap<Object, Object>();
/* 31 */     Map<Object, Object> toMap = new HashMap<Object, Object>();
/*    */     
/* 33 */     Map<Object, Object> pathToActionMap = new HashMap<Object, Object>();
/* 34 */     ActionConfig[] actionConfigs = config.findActionConfigs(); int i;
/* 35 */     for (i = 0; i < actionConfigs.length; i++) {
/* 36 */       ActionConfig actionConfig = actionConfigs[i];
/* 37 */       pathToActionMap.put(processPath(actionConfig.getPath()), actionConfig);
/*    */     } 
/*    */     
/* 40 */     for (i = 0; i < actionConfigs.length; i++) {
/* 41 */       ActionConfig actionConfig = actionConfigs[i];
/* 42 */       if (actionConfig.getInput() != null) {
/*    */         
/* 44 */         String input = processPath(actionConfig.getInput());
/* 45 */         ActionConfig fromActionConfig = (ActionConfig)pathToActionMap.get(input);
/* 46 */         if (fromActionConfig != null) {
/*    */           
/* 48 */           fromMap.put(fromActionConfig.getPath(), input);
/* 49 */           toMap.put(actionConfig.getPath(), input);
/*    */         } 
/*    */       } 
/*    */     } 
/* 53 */     servlet.getServletContext().setAttribute("com.shcnc.struts.PreventRepeatSubmitPlugIn.frommap", fromMap);
/* 54 */     servlet.getServletContext().setAttribute("com.shcnc.struts.PreventRepeatSubmitPlugIn.tomap", toMap);
/*    */   }
/*    */ 
/*    */   
/*    */   private String processPath(String path) {
/* 59 */     if (path.charAt(0) == '/') path = path.substring(1); 
/* 60 */     int index = path.lastIndexOf(".do");
/* 61 */     if (index == -1) return path; 
/* 62 */     return path.substring(0, index);
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/repeatsubmit/PreventRepeatSubmitPlugIn.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */