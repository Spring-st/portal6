/*    */ package com.aof.utils;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ 
/*    */ public class SessionTempFile
/*    */ {
/*    */   public static final String TEMP_FILE_LIST = "X_SESSIONTEMPFILELIST";
/*    */   
/*    */   public static int createNewTempFile(HttpServletRequest request) throws IOException {
/* 16 */     HttpSession session = request.getSession();
/* 17 */     List<File> tempFileList = (List)session.getAttribute("X_SESSIONTEMPFILELIST");
/* 18 */     if (tempFileList == null) {
/* 19 */       tempFileList = new ArrayList();
/* 20 */       session.setAttribute("X_SESSIONTEMPFILELIST", tempFileList);
/*    */     } 
/* 22 */     File tempFile = File.createTempFile("sessionTempFile", ".tmp");
/* 23 */     tempFileList.add(tempFile);
/* 24 */     return tempFileList.size();
/*    */   }
/*    */   
/*    */   public static File getTempFile(int index, HttpServletRequest request) {
/* 28 */     HttpSession session = request.getSession();
/* 29 */     List<File> tempFileList = (List)session.getAttribute("X_SESSIONTEMPFILELIST");
/* 30 */     if (tempFileList != null && tempFileList.size() >= index) {
/* 31 */       return tempFileList.get(index - 1);
/*    */     }
/* 33 */     return null;
/*    */   }
/*    */   
/*    */   public static void clearTempFile(HttpSession session) {
/* 37 */     List tempFileList = (List)session.getAttribute("X_SESSIONTEMPFILELIST");
/* 38 */     if (tempFileList != null)
/* 39 */       for (Iterator<File> itor = tempFileList.iterator(); itor.hasNext(); ) {
/* 40 */         File tempFile = itor.next();
/* 41 */         tempFile.delete();
/*    */       }  
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/utils/SessionTempFile.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */