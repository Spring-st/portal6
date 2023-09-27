/*   */ package com.aof.web.struts.form.formatter;
/*   */ 
/*   */ import com.shcnc.struts.form.DateFormatter;
/*   */ import java.util.Date;
/*   */ 
/*   */ public class TimeFormatter
/*   */   extends DateFormatter {
/*   */   public TimeFormatter() {
/* 9 */     super(Date.class, "HH:mm");
/*   */   }
/*   */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/web/struts/form/formatter/TimeFormatter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */