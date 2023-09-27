/*    */ package com.aof.web.servlet;
/*    */ 
/*    */ import com.aof.utils.SessionTempFile;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.net.URLEncoder;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletOutputStream;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ 
/*    */ public class DownloadServlet
/*    */   extends HttpServlet
/*    */ {
/*    */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 19 */     String[] pathInfo = request.getPathInfo().split("/");
/* 20 */     if (pathInfo.length != 3) {
/*    */       return;
/*    */     }
/* 23 */     int index = Integer.parseInt(pathInfo[1]);
/* 24 */     File tempFile = SessionTempFile.getTempFile(index, request);
/* 25 */     if (tempFile == null) {
/*    */       return;
/*    */     }
/* 28 */     response.setContentType("application/octet-stream");
/* 29 */     response.addHeader("Content-disposition", "attachment;  filename=" + URLEncoder.encode(pathInfo[2], "UTF8"));
/* 30 */     ServletOutputStream out = response.getOutputStream();
/*    */     try {
/* 32 */       byte[] buffer = new byte[10240];
/* 33 */       FileInputStream in = new FileInputStream(tempFile);
/*    */       try {
/* 35 */         for (int i = in.read(buffer); i != -1; ) {
/* 36 */           out.write(buffer, 0, i);
/* 37 */           i = in.read(buffer);
/*    */         } 
/*    */       } finally {
/* 40 */         in.close();
/*    */       } 
/*    */     } finally {
/* 43 */       out.close();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/servlet/DownloadServlet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */