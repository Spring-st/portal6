/*    */ package com.aof.service.dataTransfer.impl;
/*    */ 
/*    */ import com.aof.model.admin.Department;
/*    */ import com.aof.model.admin.Site;
/*    */ import com.aof.model.metadata.EnabledDisabled;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExportDepartmentManagerImpl
/*    */   extends BaseExportManager
/*    */ {
/*    */   public String exportTextFile(Site site) throws Exception {
/* 40 */     List departmentList = this.dao.getDepartmentList(site);
/* 41 */     if (departmentList.size() > 0) {
/* 42 */       File tempFile = new File(getLocalFileName("department", "txt"));
/* 43 */       OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(tempFile), "GB2312");
/* 44 */       for (Iterator<Department> itorExpense = departmentList.iterator(); itorExpense.hasNext(); ) {
/* 45 */         Department department = itorExpense.next();
/* 46 */         writeDepartment(writer, department);
/*    */       } 
/* 48 */       writer.close();
/* 49 */       return tempFile.getPath();
/*    */     } 
/* 51 */     return "";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String exportXmlFile(Site site) throws Exception {
/* 57 */     return null;
/*    */   }
/*    */   
/*    */   private void writeDepartment(OutputStreamWriter writer, Department department) throws IOException {
/* 61 */     writer.write(getFormatString(getValue(department, "id"), 8));
/* 62 */     writer.write(getFormatString(getValue(department, "name"), 50));
/* 63 */     writer.write(getFormatString(getValue(department, "parentDepartment.id"), 8));
/* 64 */     writer.write(department.getEnabled().equals(EnabledDisabled.ENABLED) ? "1" : "0");
/* 65 */     writer.write("\n");
/*    */   }
/*    */   
/*    */   public String getRemoteExportFileName() {
/* 69 */     return "department.txt";
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/service/dataTransfer/impl/ExportDepartmentManagerImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */