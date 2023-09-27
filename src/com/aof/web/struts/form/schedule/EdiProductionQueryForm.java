/*    */ package com.aof.web.struts.form.schedule;
/*    */ 
/*    */ import com.aof.web.struts.form.BaseSessionQueryForm;
/*    */ import org.apache.struts.upload.FormFile;
/*    */ 
/*    */ public class EdiProductionQueryForm
/*    */   extends BaseSessionQueryForm {
/*    */   public FormFile fileContent;
/*    */   
/*    */   public FormFile getFileContent() {
/* 11 */     return this.fileContent;
/*    */   }
/*    */   
/*    */   public void setFileContent(FormFile fileContent) {
/* 15 */     this.fileContent = fileContent;
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/schedule/EdiProductionQueryForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */