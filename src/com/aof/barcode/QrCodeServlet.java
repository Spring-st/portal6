/*     */ package com.aof.barcode;
/*     */ 
/*     */ import com.swetake.util.Qrcode;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.avalon.framework.configuration.Configuration;
/*     */ import org.apache.avalon.framework.configuration.DefaultConfiguration;
/*     */ import org.apache.avalon.framework.logger.ConsoleLogger;
/*     */ import org.apache.avalon.framework.logger.Logger;
/*     */ import org.krysalis.barcode4j.tools.MimeTypes;
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
/*     */ public class QrCodeServlet
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
/*  53 */   private transient Logger log = (Logger)new ConsoleLogger(1);
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*  58 */     String format = determineFormat(request);
/*  59 */     int orientation = 0;
/*  60 */     Configuration cfg = buildCfg(request);
/*  61 */     String msg = request.getParameter("msg");
/*     */     
/*     */     try {
/*  64 */       Qrcode handler = new Qrcode();
/*  65 */       handler.setQrcodeErrorCorrect('M');
/*  66 */       handler.setQrcodeEncodeMode('B');
/*  67 */       handler.setQrcodeVersion(7);
/*     */       
/*  69 */       byte[] contentBytes = msg.getBytes("UTF-8");
/*     */       
/*  71 */       BufferedImage bufImg = new BufferedImage(140, 140, 1);
/*     */       
/*  73 */       Graphics2D gs = bufImg.createGraphics();
/*     */       
/*  75 */       gs.setBackground(Color.WHITE);
/*  76 */       gs.clearRect(0, 0, 140, 140);
/*     */ 
/*     */       
/*  79 */       gs.setColor(Color.BLACK);
/*     */ 
/*     */       
/*  82 */       int pixoff = 2;
/*     */       
/*  84 */       if (contentBytes.length > 0 && contentBytes.length < 124) {
/*  85 */         boolean[][] codeOut = handler.calQrcode(contentBytes);
/*  86 */         for (int i = 0; i < codeOut.length; i++) {
/*  87 */           for (int j = 0; j < codeOut.length; j++) {
/*  88 */             if (codeOut[j][i]) {
/*  89 */               gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } else {
/*  94 */         System.err.println("QRCode content bytes length = " + contentBytes.length + " not in [ 0,120 ]. ");
/*     */       } 
/*  96 */       gs.dispose();
/*  97 */       bufImg.flush();
/*     */       
/*  99 */       ImageIO.write(bufImg, "jpg", (OutputStream)response.getOutputStream());
/*     */     }
/* 101 */     catch (Throwable t) {
/* 102 */       this.log.error("Error while generating barcode", t);
/* 103 */       throw new ServletException(t);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String determineFormat(HttpServletRequest request) {
/* 113 */     String format = request.getParameter("fmt");
/* 114 */     format = MimeTypes.expandFormat(format);
/* 115 */     if (format == null) format = "image/jpeg"; 
/* 116 */     return format;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Configuration buildCfg(HttpServletRequest request) {
/* 126 */     DefaultConfiguration cfg = new DefaultConfiguration("barcode", null);
/*     */     
/* 128 */     String type = request.getParameter("type");
/* 129 */     if (type == null) type = "code128"; 
/* 130 */     DefaultConfiguration child = new DefaultConfiguration(type, null);
/* 131 */     cfg.addChild((Configuration)child);
/*     */ 
/*     */     
/* 134 */     String height = request.getParameter("height");
/* 135 */     if (height != null) {
/* 136 */       DefaultConfiguration attr = new DefaultConfiguration("height", null);
/* 137 */       attr.setValue(height);
/* 138 */       child.addChild((Configuration)attr);
/*     */     } 
/*     */     
/* 141 */     String width = request.getParameter("width");
/* 142 */     if (width != null) {
/* 143 */       DefaultConfiguration attr = new DefaultConfiguration("width", null);
/* 144 */       attr.setValue(width);
/* 145 */       child.addChild((Configuration)attr);
/*     */     } 
/* 147 */     String moduleWidth = request.getParameter("mw");
/* 148 */     if (moduleWidth != null) {
/* 149 */       DefaultConfiguration attr = new DefaultConfiguration("module-width", null);
/* 150 */       attr.setValue(moduleWidth);
/* 151 */       child.addChild((Configuration)attr);
/*     */     } 
/* 153 */     String wideFactor = request.getParameter("wf");
/* 154 */     if (wideFactor != null) {
/* 155 */       DefaultConfiguration attr = new DefaultConfiguration("wide-factor", null);
/* 156 */       attr.setValue(wideFactor);
/* 157 */       child.addChild((Configuration)attr);
/*     */     } 
/* 159 */     String quietZone = request.getParameter("qz");
/* 160 */     if (quietZone != null) {
/* 161 */       DefaultConfiguration attr = new DefaultConfiguration("quiet-zone", null);
/* 162 */       if (quietZone.startsWith("disable")) {
/* 163 */         attr.setAttribute("enabled", "false");
/*     */       } else {
/* 165 */         attr.setValue(quietZone);
/*     */       } 
/* 167 */       child.addChild((Configuration)attr);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 173 */     String humanReadablePosition = request.getParameter("hrp");
/* 174 */     String pattern = request.getParameter("hrpattern");
/* 175 */     String humanReadableSize = request.getParameter("hrsize");
/* 176 */     String humanReadableFont = request.getParameter("hrfont");
/*     */     
/* 178 */     if (humanReadablePosition != null || 
/* 179 */       pattern != null || 
/* 180 */       humanReadableSize != null || 
/* 181 */       humanReadableFont != null) {
/* 182 */       DefaultConfiguration attr = new DefaultConfiguration("human-readable", null);
/*     */ 
/*     */       
/* 185 */       if (pattern != null) {
/* 186 */         DefaultConfiguration subAttr = new DefaultConfiguration("pattern", null);
/* 187 */         subAttr.setValue(pattern);
/* 188 */         attr.addChild((Configuration)subAttr);
/*     */       } 
/* 190 */       if (humanReadableSize != null) {
/* 191 */         DefaultConfiguration subAttr = new DefaultConfiguration("font-size", null);
/* 192 */         subAttr.setValue(humanReadableSize);
/* 193 */         attr.addChild((Configuration)subAttr);
/*     */       } 
/* 195 */       if (humanReadableFont != null) {
/* 196 */         DefaultConfiguration subAttr = new DefaultConfiguration("font-name", null);
/* 197 */         subAttr.setValue(humanReadableFont);
/* 198 */         attr.addChild((Configuration)subAttr);
/*     */       } 
/* 200 */       if (humanReadablePosition != null) {
/* 201 */         DefaultConfiguration subAttr = new DefaultConfiguration("placement", null);
/* 202 */         subAttr.setValue(humanReadablePosition);
/* 203 */         attr.addChild((Configuration)subAttr);
/*     */       } 
/*     */       
/* 206 */       child.addChild((Configuration)attr);
/*     */     } 
/*     */     
/* 209 */     return (Configuration)cfg;
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/barcode/QrCodeServlet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */