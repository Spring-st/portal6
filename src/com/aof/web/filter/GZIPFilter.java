/*    */ package com.aof.web.filter;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class GZIPFilter
/*    */   implements Filter
/*    */ {
/* 18 */   private static Log log = LogFactory.getLog(GZIPFilter.class);
/*    */ 
/*    */ 
/*    */   
/*    */   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
/* 23 */     if (req instanceof HttpServletRequest) {
/* 24 */       HttpServletRequest request = (HttpServletRequest)req;
/* 25 */       HttpServletResponse response = (HttpServletResponse)res;
/*    */       
/* 27 */       if (isGZIPSupported(request)) {
/* 28 */         if (log.isDebugEnabled()) {
/* 29 */           log.debug("GZIP supported, compressing response");
/*    */         }
/*    */         
/* 32 */         GZIPResponseWrapper wrappedResponse = 
/* 33 */           new GZIPResponseWrapper(response);
/*    */         
/* 35 */         chain.doFilter(req, (ServletResponse)wrappedResponse);
/* 36 */         wrappedResponse.finishResponse();
/*    */         
/*    */         return;
/*    */       } 
/*    */       
/* 41 */       chain.doFilter(req, res);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean isGZIPSupported(HttpServletRequest req) {
/* 51 */     String browserEncodings = req.getHeader("accept-encoding");
/* 52 */     boolean supported = 
/* 53 */       (browserEncodings != null && 
/* 54 */       browserEncodings.indexOf("gzip") != -1);
/*    */     
/* 56 */     String userAgent = req.getHeader("user-agent");
/*    */     
/* 58 */     if (userAgent.startsWith("httpunit")) {
/* 59 */       if (log.isDebugEnabled()) {
/* 60 */         log.debug("httpunit detected, disabling filter...");
/*    */       }
/*    */       
/* 63 */       return false;
/*    */     } 
/* 65 */     return supported;
/*    */   }
/*    */   
/*    */   public void init(FilterConfig filterConfig) {}
/*    */   
/*    */   public void destroy() {}
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/filter/GZIPFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */