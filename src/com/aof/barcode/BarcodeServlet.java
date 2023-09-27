/*     */ package com.aof.barcode;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.xml.transform.Result;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import org.apache.avalon.framework.configuration.Configuration;
/*     */ import org.apache.avalon.framework.configuration.DefaultConfiguration;
/*     */ import org.apache.avalon.framework.logger.ConsoleLogger;
/*     */ import org.apache.avalon.framework.logger.Logger;
/*     */ import org.krysalis.barcode4j.BarcodeGenerator;
/*     */ import org.krysalis.barcode4j.BarcodeUtil;
/*     */ import org.krysalis.barcode4j.output.CanvasProvider;
/*     */ import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
/*     */ import org.krysalis.barcode4j.output.eps.EPSCanvasProvider;
/*     */ import org.krysalis.barcode4j.output.svg.SVGCanvasProvider;
/*     */ import org.krysalis.barcode4j.tools.MimeTypes;
/*     */ import org.w3c.dom.DocumentFragment;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BarcodeServlet
/*     */   extends HttpServlet
/*     */ {
/*     */   public static final String BARCODE_MSG = "msg";
/*     */   public static final String BARCODE_TYPE = "type";
/*     */   public static final String BARCODE_HEIGHT = "height";
/*     */   public static final String BARCODE_WIDTH = "width";
/*     */   public static final String BARCODE_MODULE_WIDTH = "mw";
/*     */   public static final String BARCODE_WIDE_FACTOR = "wf";
/*     */   public static final String BARCODE_QUIET_ZONE = "qz";
/*     */   public static final String BARCODE_HUMAN_READABLE_POS = "hrp";
/*     */   public static final String BARCODE_FORMAT = "fmt";
/*     */   public static final String BARCODE_IMAGE_RESOLUTION = "res";
/*     */   public static final String BARCODE_IMAGE_GRAYSCALE = "gray";
/*     */   public static final String BARCODE_HUMAN_READABLE_SIZE = "hrsize";
/*     */   public static final String BARCODE_HUMAN_READABLE_FONT = "hrfont";
/*     */   public static final String BARCODE_HUMAN_READABLE_PATTERN = "hrpattern";
/*  82 */   private transient Logger log = (Logger)new ConsoleLogger(1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*     */     try {
/*  94 */       String format = determineFormat(request);
/*  95 */       int orientation = 0;
/*     */       
/*  97 */       Configuration cfg = buildCfg(request);
/*     */       
/*  99 */       String msg = request.getParameter("msg");
/* 100 */       if (msg == null) msg = "0123456789";
/*     */       
/* 102 */       BarcodeUtil util = BarcodeUtil.getInstance();
/* 103 */       BarcodeGenerator gen = util.createBarcodeGenerator(cfg);
/*     */       
/* 105 */       ByteArrayOutputStream bout = new ByteArrayOutputStream(4096);
/*     */       try {
/* 107 */         if (format.equals("image/svg+xml")) {
/*     */           
/* 109 */           SVGCanvasProvider svg = new SVGCanvasProvider(false, orientation);
/* 110 */           gen.generateBarcode((CanvasProvider)svg, msg);
/* 111 */           DocumentFragment frag = svg.getDOMFragment();
/*     */ 
/*     */           
/* 114 */           TransformerFactory factory = TransformerFactory.newInstance();
/* 115 */           Transformer trans = factory.newTransformer();
/* 116 */           Source src = new DOMSource(frag);
/* 117 */           Result res = new StreamResult(bout);
/* 118 */           trans.transform(src, res);
/* 119 */         } else if (format.equals("image/x-eps")) {
/* 120 */           EPSCanvasProvider eps = new EPSCanvasProvider(bout, orientation);
/* 121 */           gen.generateBarcode((CanvasProvider)eps, msg);
/* 122 */           eps.finish();
/*     */         } else {
/* 124 */           String resText = request.getParameter("res");
/* 125 */           int resolution = 300;
/* 126 */           if (resText != null) {
/* 127 */             resolution = Integer.parseInt(resText);
/*     */           }
/* 129 */           if (resolution > 2400) {
/* 130 */             throw new IllegalArgumentException(
/* 131 */                 "Resolutions above 2400dpi are not allowed");
/*     */           }
/* 133 */           if (resolution < 10) {
/* 134 */             throw new IllegalArgumentException(
/* 135 */                 "Minimum resolution must be 10dpi");
/*     */           }
/* 137 */           String gray = request.getParameter("gray");
/* 138 */           if ("true".equalsIgnoreCase(gray)) {  } else {  }  BitmapCanvasProvider bitmap = 
/*     */ 
/*     */ 
/*     */             
/* 142 */             new BitmapCanvasProvider(
/* 143 */               bout, format, resolution, 
/* 144 */               12, false, orientation);
/* 145 */           gen.generateBarcode((CanvasProvider)bitmap, msg);
/* 146 */           bitmap.finish();
/*     */         } 
/*     */       } finally {
/* 149 */         bout.close();
/*     */       } 
/* 151 */       response.setContentType(format);
/* 152 */       response.setContentLength(bout.size());
/* 153 */       response.getOutputStream().write(bout.toByteArray());
/*     */     }
/* 155 */     catch (Exception e) {
/* 156 */       this.log.error("Error while generating barcode", e);
/* 157 */       throw new ServletException(e);
/* 158 */     } catch (Throwable t) {
/* 159 */       this.log.error("Error while generating barcode", t);
/* 160 */       throw new ServletException(t);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String determineFormat(HttpServletRequest request) {
/* 170 */     String format = request.getParameter("fmt");
/* 171 */     format = MimeTypes.expandFormat(format);
/* 172 */     if (format == null) format = "image/jpeg"; 
/* 173 */     return format;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Configuration buildCfg(HttpServletRequest request) {
/* 184 */     DefaultConfiguration cfg = new DefaultConfiguration("barcode", null);
/*     */     
/* 186 */     String type = request.getParameter("type");
/* 187 */     if (type == null) type = "code128"; 
/* 188 */     DefaultConfiguration child = new DefaultConfiguration(type, null);
/* 189 */     cfg.addChild((Configuration)child);
/*     */ 
/*     */     
/* 192 */     String height = request.getParameter("height");
/* 193 */     if (height != null) {
/* 194 */       DefaultConfiguration attr = new DefaultConfiguration("height", null);
/* 195 */       attr.setValue(height);
/* 196 */       child.addChild((Configuration)attr);
/*     */     } 
/*     */     
/* 199 */     String width = request.getParameter("width");
/* 200 */     if (width != null) {
/* 201 */       DefaultConfiguration attr = new DefaultConfiguration("width", null);
/* 202 */       attr.setValue(width);
/* 203 */       child.addChild((Configuration)attr);
/*     */     } 
/* 205 */     String moduleWidth = request.getParameter("mw");
/* 206 */     if (moduleWidth != null) {
/* 207 */       DefaultConfiguration attr = new DefaultConfiguration("module-width", null);
/* 208 */       attr.setValue(moduleWidth);
/* 209 */       child.addChild((Configuration)attr);
/*     */     } 
/* 211 */     String wideFactor = request.getParameter("wf");
/* 212 */     if (wideFactor != null) {
/* 213 */       DefaultConfiguration attr = new DefaultConfiguration("wide-factor", null);
/* 214 */       attr.setValue(wideFactor);
/* 215 */       child.addChild((Configuration)attr);
/*     */     } 
/* 217 */     String quietZone = request.getParameter("qz");
/* 218 */     if (quietZone != null) {
/* 219 */       DefaultConfiguration attr = new DefaultConfiguration("quiet-zone", null);
/* 220 */       if (quietZone.startsWith("disable")) {
/* 221 */         attr.setAttribute("enabled", "false");
/*     */       } else {
/* 223 */         attr.setValue(quietZone);
/*     */       } 
/* 225 */       child.addChild((Configuration)attr);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 231 */     String humanReadablePosition = request.getParameter("hrp");
/* 232 */     String pattern = request.getParameter("hrpattern");
/* 233 */     String humanReadableSize = request.getParameter("hrsize");
/* 234 */     String humanReadableFont = request.getParameter("hrfont");
/*     */     
/* 236 */     if (humanReadablePosition != null || 
/* 237 */       pattern != null || 
/* 238 */       humanReadableSize != null || 
/* 239 */       humanReadableFont != null) {
/* 240 */       DefaultConfiguration attr = new DefaultConfiguration("human-readable", null);
/*     */ 
/*     */       
/* 243 */       if (pattern != null) {
/* 244 */         DefaultConfiguration subAttr = new DefaultConfiguration("pattern", null);
/* 245 */         subAttr.setValue(pattern);
/* 246 */         attr.addChild((Configuration)subAttr);
/*     */       } 
/* 248 */       if (humanReadableSize != null) {
/* 249 */         DefaultConfiguration subAttr = new DefaultConfiguration("font-size", null);
/* 250 */         subAttr.setValue(humanReadableSize);
/* 251 */         attr.addChild((Configuration)subAttr);
/*     */       } 
/* 253 */       if (humanReadableFont != null) {
/* 254 */         DefaultConfiguration subAttr = new DefaultConfiguration("font-name", null);
/* 255 */         subAttr.setValue(humanReadableFont);
/* 256 */         attr.addChild((Configuration)subAttr);
/*     */       } 
/* 258 */       if (humanReadablePosition != null) {
/* 259 */         DefaultConfiguration subAttr = new DefaultConfiguration("placement", null);
/* 260 */         subAttr.setValue(humanReadablePosition);
/* 261 */         attr.addChild((Configuration)subAttr);
/*     */       } 
/*     */       
/* 264 */       child.addChild((Configuration)attr);
/*     */     } 
/*     */     
/* 267 */     return (Configuration)cfg;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/barcode/BarcodeServlet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */