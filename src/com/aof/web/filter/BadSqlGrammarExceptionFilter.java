/*    */ package com.aof.web.filter;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BadSqlGrammarExceptionFilter
/*    */   implements Filter
/*    */ {
/*    */   private List<String> ignoreUrl;
/*    */   private String redirectUrl;
/*    */   private boolean ignore = true;
/*    */   
/*    */   public void destroy() {}
/*    */   
/*    */   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
/* 52 */     HttpServletResponse response = (HttpServletResponse)res;
/* 53 */     HttpServletRequest request = (HttpServletRequest)req;
/*    */     
/*    */     try {
/* 56 */       chain.doFilter(req, res);
/* 57 */     } catch (RuntimeException e) {
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 62 */       request.setAttribute("BadSqlGrammarException", e);
/* 63 */       request.getRequestDispatcher(this.redirectUrl).forward((ServletRequest)request, (ServletResponse)response);
/* 64 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void init(FilterConfig config) throws ServletException {
/* 70 */     String urlsrc = config.getInitParameter("ignoreBadSql");
/* 71 */     if (urlsrc != null && !urlsrc.equals("")) {
/* 72 */       this.ignoreUrl = Arrays.asList(urlsrc.split(","));
/*    */     }
/*    */     
/* 75 */     this.redirectUrl = config.getInitParameter("redirectUrl");
/*    */ 
/*    */     
/* 78 */     String ignoreSrc = config.getInitParameter("ignore");
/* 79 */     if (ignoreSrc != null && ignoreSrc.equals("false"))
/* 80 */       this.ignore = false; 
/*    */   }
/*    */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/filter/BadSqlGrammarExceptionFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */