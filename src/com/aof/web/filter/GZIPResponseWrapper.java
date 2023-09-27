/*    */ package com.aof.web.filter;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.io.PrintWriter;
/*    */ import javax.servlet.ServletOutputStream;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpServletResponseWrapper;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class GZIPResponseWrapper
/*    */   extends HttpServletResponseWrapper {
/* 15 */   private static Log log = LogFactory.getLog(GZIPResponseWrapper.class);
/* 16 */   protected HttpServletResponse origResponse = null;
/* 17 */   protected ServletOutputStream stream = null;
/* 18 */   protected PrintWriter writer = null;
/* 19 */   protected int error = 0;
/*    */   
/*    */   public GZIPResponseWrapper(HttpServletResponse response) {
/* 22 */     super(response);
/* 23 */     this.origResponse = response;
/*    */   }
/*    */   
/*    */   public ServletOutputStream createOutputStream() throws IOException {
/* 27 */     return new GZIPResponseStream(this.origResponse);
/*    */   }
/*    */   
/*    */   public void finishResponse() {
/*    */     try {
/* 32 */       if (this.writer != null) {
/* 33 */         this.writer.close();
/*    */       }
/* 35 */       else if (this.stream != null) {
/* 36 */         this.stream.close();
/*    */       }
/*    */     
/* 39 */     } catch (IOException iOException) {}
/*    */   }
/*    */ 
/*    */   
/*    */   public void flushBuffer() throws IOException {
/* 44 */     this.stream.flush();
/*    */   }
/*    */   
/*    */   public ServletOutputStream getOutputStream() throws IOException {
/* 48 */     if (this.writer != null) {
/* 49 */       throw new IllegalStateException("getWriter() has already been called!");
/*    */     }
/*    */     
/* 52 */     if (this.stream == null) {
/* 53 */       this.stream = createOutputStream();
/*    */     }
/*    */     
/* 56 */     return this.stream;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public PrintWriter getWriter() throws IOException {
/* 62 */     if (this.error == 403) {
/* 63 */       return super.getWriter();
/*    */     }
/*    */     
/* 66 */     if (this.writer != null) {
/* 67 */       return this.writer;
/*    */     }
/*    */     
/* 70 */     if (this.stream != null) {
/* 71 */       throw new IllegalStateException("getOutputStream() has already been called!");
/*    */     }
/*    */     
/* 74 */     this.stream = createOutputStream();
/* 75 */     this.writer = 
/* 76 */       new PrintWriter(new OutputStreamWriter((OutputStream)this.stream, 
/* 77 */           this.origResponse.getCharacterEncoding()));
/*    */     
/* 79 */     return this.writer;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setContentLength(int length) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void sendError(int error, String message) throws IOException {
/* 89 */     super.sendError(error, message);
/* 90 */     this.error = error;
/*    */     
/* 92 */     if (log.isDebugEnabled())
/* 93 */       log.debug("sending error: " + error + " [" + message + "]"); 
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/filter/GZIPResponseWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */