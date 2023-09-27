/*    */ package com.aof.web.struts.form.business.rule;
/*    */ 
/*    */ import com.aof.model.business.rule.query.FlowQueryOrder;
/*    */ import com.aof.model.metadata.EnabledDisabled;
/*    */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*    */ import com.shcnc.struts.form.ComboBox;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FlowQueryForm
/*    */   extends BaseSessionQueryForm
/*    */ {
/*    */   private ComboBox site;
/*    */   private String id;
/*    */   private String description;
/*    */   private String enabled;
/*    */   
/*    */   public String getDescription() {
/* 24 */     return this.description;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDescription(String description) {
/* 30 */     this.description = description;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getEnabled() {
/* 37 */     return this.enabled;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setEnabled(String enabled) {
/* 43 */     this.enabled = enabled;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getId() {
/* 49 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setId(String id) {
/* 55 */     this.id = id;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ComboBox getSite() {
/* 61 */     return this.site;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSite(ComboBox site) {
/* 67 */     this.site = site;
/*    */   }
/*    */ 
/*    */   
/*    */   public FlowQueryForm() {
/* 72 */     setSite(new ComboBox("id", "name"));
/* 73 */     setOrder(FlowQueryOrder.DESCRIPTION.getName());
/* 74 */     setDescend(false);
/* 75 */     setEnabled(EnabledDisabled.ENABLED.getEnumCode().toString());
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/business/rule/FlowQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */