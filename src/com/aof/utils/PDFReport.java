/*     */ package com.aof.utils;
/*     */ 
/*     */ import com.aof.model.BaseAttachment;
/*     */ import com.aof.model.admin.Customer;
/*     */ import com.aof.model.admin.Department;
/*     */ import com.aof.model.admin.User;
/*     */ import com.aof.model.business.BaseApproveRequest;
/*     */ import com.aof.model.business.BaseRecharge;
/*     */ import com.aof.model.business.BuyForTarget;
/*     */ import com.aof.model.business.Rechargeable;
/*     */ import com.aof.model.metadata.ApproveStatus;
/*     */ import com.aof.model.metadata.MetadataDetailEnum;
/*     */ import com.aof.model.metadata.RechargeType;
/*     */ import com.aof.model.metadata.YesNo;
/*     */ import com.aof.service.admin.SiteManager;
/*     */ import com.aof.web.struts.action.ActionUtils;
/*     */ import com.aof.web.struts.action.ServiceLocator;
/*     */ import com.lowagie.text.Document;
/*     */ import com.lowagie.text.DocumentException;
/*     */ import com.lowagie.text.Element;
/*     */ import com.lowagie.text.Font;
/*     */ import com.lowagie.text.Image;
/*     */ import com.lowagie.text.PageSize;
/*     */ import com.lowagie.text.Paragraph;
/*     */ import com.lowagie.text.Phrase;
/*     */ import com.lowagie.text.Rectangle;
/*     */ import com.lowagie.text.pdf.BaseFont;
/*     */ import com.lowagie.text.pdf.PdfPCell;
/*     */ import com.lowagie.text.pdf.PdfPTable;
/*     */ import com.lowagie.text.pdf.PdfWriter;
/*     */ import java.awt.Color;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.struts.util.MessageResources;
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
/*     */ public class PDFReport
/*     */ {
/*     */   private static BaseFont baseFont;
/*     */   
/*     */   static {
/*     */     try {
/*  76 */       baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
/*  77 */     } catch (Exception e) {
/*  78 */       throw new RuntimeException("error create font for pdf", e);
/*     */     } 
/*  80 */   } private static Font defaultTitleFont = new Font(baseFont, 16.0F, 1);
/*  81 */   private static Font defaultFont = new Font(baseFont, 10.0F, 0); private static Font boldFont;
/*  82 */   private static Font subTitleFont = getFont(14.0F, 1, new Color(0, 0, 255));
/*  83 */   private static Font itemSubTitleFont = getFont(10.0F, 1, new Color(0, 0, 255)); private Document document; private OutputStream out; static {
/*  84 */     boldFont = new Font(baseFont, 10.0F, 1);
/*     */   }
/*     */   private MessageResources messages; private Locale locale; private int tempFileIndex;
/*     */   public static Font getSubTitleFont() {
/*  88 */     return subTitleFont;
/*     */   }
/*     */   
/*     */   public static Font getDefaultFont() {
/*  92 */     return defaultFont;
/*     */   }
/*     */   
/*     */   public static Font getDefaultTitleFont() {
/*  96 */     return defaultTitleFont;
/*     */   }
/*     */   
/*     */   public static Font getItemSubTitleFont() {
/* 100 */     return itemSubTitleFont;
/*     */   }
/*     */   
/*     */   public static Font getBoldFont() {
/* 104 */     return boldFont;
/*     */   }
/*     */   
/*     */   public static Font getFont(String strColor) {
/* 108 */     return getFont(0, strColor);
/*     */   }
/*     */   
/*     */   public static Font getFont(Color color) {
/* 112 */     return getFont(0, color);
/*     */   }
/*     */   
/*     */   public static Font getFont(int style, String strColor) {
/* 116 */     return getFont(10.0F, style, strColor);
/*     */   }
/*     */   
/*     */   public static Font getFont(int style, Color color) {
/* 120 */     return getFont(10.0F, style, color);
/*     */   }
/*     */   
/*     */   public static Font getFont(float size, int style, String strColor) {
/* 124 */     if (strColor == null) {
/* 125 */       return new Font(baseFont, size, style);
/*     */     }
/* 127 */     Color color = ColorUtils.getColor(strColor);
/* 128 */     return new Font(baseFont, size, style, color);
/*     */   }
/*     */   
/*     */   public static Font getFont(float size, int style, Color color) {
/* 132 */     return new Font(baseFont, size, style, color);
/*     */   }
/*     */   
/*     */   public static PdfPTable createTable(float[] widths) {
/* 136 */     PdfPTable table = new PdfPTable(widths);
/* 137 */     PdfPCell defaultCell = table.getDefaultCell();
/* 138 */     defaultCell.setNoWrap(false);
/* 139 */     defaultCell.setMinimumHeight(16.0F);
/* 140 */     defaultCell.setHorizontalAlignment(0);
/*     */     
/* 142 */     return table;
/*     */   }
/*     */ 
/*     */   
/*     */   public static PdfPTable createTable(float[] widths, int totalWidth) {
/* 147 */     PdfPTable table = createTable(widths);
/* 148 */     table.setWidthPercentage(totalWidth);
/* 149 */     return table;
/*     */   }
/*     */   
/*     */   public static PdfPTable createTable(float[] widths, int totalWidth, float borderWidth) {
/* 153 */     PdfPTable table = createTable(widths, totalWidth);
/* 154 */     PdfPCell defaultCell = table.getDefaultCell();
/* 155 */     defaultCell.setBorderWidth(borderWidth);
/* 156 */     return table;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PDFReport createReport(Integer siteId, String titleKey, HttpServletRequest request, MessageResources messages, Locale locale) throws DocumentException, MalformedURLException, IOException {
/* 166 */     return createReport(siteId, titleKey, request, messages, locale, PageSize.A4);
/*     */   }
/*     */   
/*     */   public static PDFReport createReport(Integer siteId, String titleKey, HttpServletRequest request, MessageResources messages, Locale locale, Rectangle pageSize) throws DocumentException, MalformedURLException, IOException {
/* 170 */     PDFReport report = new PDFReport();
/* 171 */     report.tempFileIndex = SessionTempFile.createNewTempFile(request);
/* 172 */     report.document = new Document(pageSize);
/* 173 */     report.out = new FileOutputStream(SessionTempFile.getTempFile(report.tempFileIndex, request));
/* 174 */     report.messages = messages;
/* 175 */     report.locale = locale;
/*     */     
/* 177 */     PdfWriter.getInstance(report.document, report.out);
/* 178 */     report.document.open();
/*     */     
/* 180 */     if (siteId != null) {
/* 181 */       SiteManager sm = ServiceLocator.getSiteManager(request);
/* 182 */       InputStream is = sm.getSiteLogo(siteId);
/* 183 */       if (is != null) {
/* 184 */         ByteArrayOutputStream os = new ByteArrayOutputStream();
/* 185 */         byte[] buf = new byte[512];
/*     */         while (true) {
/* 187 */           int i = is.read(buf);
/* 188 */           if (i > 0) {
/* 189 */             os.write(buf, 0, i);
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */         } 
/* 194 */         Image logo = Image.getInstance(os.toByteArray());
/* 195 */         logo.scalePercent(80.0F);
/* 196 */         report.document.add((Element)logo);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 201 */     Paragraph p = new Paragraph(messages.getMessage(locale, titleKey) + "\n\n", getDefaultTitleFont());
/* 202 */     p.setAlignment(1);
/*     */     
/* 204 */     report.document.add((Element)p);
/*     */     
/* 206 */     return report;
/*     */   }
/*     */   
/*     */   public Document getDocument() {
/* 210 */     return this.document;
/*     */   }
/*     */   
/*     */   public void addSpaceLine(int lineHeight) throws DocumentException {
/* 214 */     Paragraph p = new Paragraph(
/* 215 */         "" + "\n", 
/* 216 */         getFont(lineHeight, 0, Color.BLACK));
/* 217 */     p.setAlignment(0);
/* 218 */     this.document.add((Element)p);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCell(PdfPTable table, Object content) {
/* 223 */     addCell(table, content, 1, null, null, false);
/*     */   }
/*     */   
/*     */   public void addCell(PdfPTable table, Object content, boolean resource) {
/* 227 */     addCell(table, content, 1, null, null, resource);
/*     */   }
/*     */   
/*     */   public void addCell(PdfPTable table, Object content, int colSpan) {
/* 231 */     addCell(table, content, colSpan, null, null, false);
/*     */   }
/*     */   
/*     */   public void addCell(PdfPTable table, Object content, int colSpan, boolean resource) {
/* 235 */     addCell(table, content, colSpan, null, null, resource);
/*     */   }
/*     */   
/*     */   public void addCell(PdfPTable table, Object content, Color color) {
/* 239 */     addCell(table, content, 1, color, null, false);
/*     */   }
/*     */   
/*     */   public void addCell(PdfPTable table, Object content, Color color, boolean resource) {
/* 243 */     addCell(table, content, 1, color, null, resource);
/*     */   }
/*     */   
/*     */   public void addCell(PdfPTable table, Object content, Font font) {
/* 247 */     addCell(table, content, 1, null, font, false);
/*     */   }
/*     */   
/*     */   public void addCell(PdfPTable table, Object content, Font font, boolean resource) {
/* 251 */     addCell(table, content, 1, null, font, resource);
/*     */   }
/*     */   
/*     */   public void addCell(PdfPTable table, Object content, int colSpan, Color color) {
/* 255 */     addCell(table, content, colSpan, color, null, false);
/*     */   }
/*     */   
/*     */   public void addCell(PdfPTable table, Object content, int colSpan, Color color, boolean resource) {
/* 259 */     addCell(table, content, colSpan, color, null, resource);
/*     */   }
/*     */   
/*     */   public void addCell(PdfPTable table, Object content, int colSpan, Font font) {
/* 263 */     addCell(table, content, colSpan, null, font, false);
/*     */   }
/*     */   
/*     */   public void addCell(PdfPTable table, Object content, int colSpan, Font font, boolean resource) {
/* 267 */     addCell(table, content, colSpan, null, font, resource);
/*     */   }
/*     */   
/*     */   private void addCell(PdfPTable table, Object content, int colSpan, Color color, Font font, boolean resource) {
/* 271 */     String cellContent = null;
/* 272 */     Font cellFont = null;
/* 273 */     if (content == null) {
/* 274 */       cellContent = "";
/* 275 */     } else if (content instanceof MetadataDetailEnum) {
/* 276 */       MetadataDetailEnum metadata = (MetadataDetailEnum)content;
/* 277 */       cellContent = this.locale.equals(Locale.ENGLISH) ? metadata.getEngShortDescription() : metadata.getChnShortDescription();
/* 278 */       if (font != null) {
/* 279 */         cellFont = getFont(font.size(), font.style(), metadata.getColor());
/*     */       } else {
/* 281 */         cellFont = getFont(metadata.getColor());
/*     */       } 
/* 283 */     } else if (content instanceof Date) {
/* 284 */       cellContent = ActionUtils.getDisplayDateFromDate((Date)content);
/* 285 */     } else if (content instanceof String) {
/* 286 */       if (resource) {
/* 287 */         cellContent = this.messages.getMessage(this.locale, (String)content);
/*     */       } else {
/* 289 */         cellContent = (String)content;
/*     */       } 
/*     */     } else {
/* 292 */       if (content instanceof PdfPCell) {
/* 293 */         table.addCell((PdfPCell)content);
/*     */         return;
/*     */       } 
/* 296 */       if (content instanceof Phrase) {
/* 297 */         table.addCell((Phrase)content);
/*     */         return;
/*     */       } 
/* 300 */       if (content instanceof PdfPTable) {
/* 301 */         table.addCell((PdfPTable)content);
/*     */         return;
/*     */       } 
/* 304 */       if (content instanceof Image) {
/* 305 */         table.addCell((Image)content);
/*     */         return;
/*     */       } 
/* 308 */       cellContent = content.toString();
/*     */     } 
/* 310 */     if (cellFont == null) {
/* 311 */       if (font == null) {
/* 312 */         if (color == null) {
/* 313 */           cellFont = getDefaultFont();
/*     */         } else {
/* 315 */           cellFont = getFont(color);
/*     */         } 
/*     */       } else {
/* 318 */         cellFont = font;
/*     */       } 
/*     */     }
/* 321 */     table.getDefaultCell().setColspan(colSpan);
/* 322 */     table.addCell(new Phrase(cellContent, cellFont));
/* 323 */     table.getDefaultCell().setColspan(1);
/*     */   }
/*     */   
/*     */   public void output(String fileName, HttpServletResponse response) throws IOException {
/* 327 */     this.document.close();
/* 328 */     this.out.close();
/* 329 */     response.sendRedirect("download/" + this.tempFileIndex + "/" + URLEncoder.encode(fileName, "UTF8") + ".pdf");
/*     */   }
/*     */   
/*     */   public void addSplitLine() throws DocumentException {
/* 333 */     PdfPTable table = createTable(new float[] { 1.0F }, 100);
/* 334 */     PdfPCell defaultCell = table.getDefaultCell();
/* 335 */     defaultCell.setBorderWidth(0.0F);
/* 336 */     defaultCell.setBorderWidthBottom(0.1F);
/* 337 */     defaultCell.setFixedHeight(5.0F);
/* 338 */     defaultCell.setBorderColorBottom(new Color(60, 120, 181));
/* 339 */     table.addCell("");
/* 340 */     defaultCell.setBorderWidthBottom(0.0F);
/* 341 */     table.addCell("");
/*     */     
/* 343 */     this.document.add((Element)table);
/*     */   }
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
/*     */   public void addAttachmentListTableToTable(PdfPTable parentTable, int tableColumnCount, Collection attachList, String titleKey, String fileTitleKey, String fileNameKey, String uploadDateKey) {
/* 375 */     if (!attachList.isEmpty()) {
/* 376 */       parentTable.getDefaultCell().setHorizontalAlignment(0);
/* 377 */       addCell(parentTable, titleKey, tableColumnCount, itemSubTitleFont, true);
/* 378 */       PdfPTable table = createTable(new float[] { 4.0F, 4.0F, 1.0F }, 100, 0.5F);
/* 379 */       PdfPCell defaultCell = table.getDefaultCell();
/* 380 */       Color defaultBackgroundColor = defaultCell.backgroundColor();
/* 381 */       defaultCell.setBackgroundColor(new Color(221, 221, 255));
/* 382 */       defaultCell.setHorizontalAlignment(1);
/* 383 */       Font headFont = getFont(1, Color.BLACK);
/* 384 */       addCell(table, fileTitleKey, headFont, true);
/* 385 */       addCell(table, fileNameKey, headFont, true);
/* 386 */       addCell(table, uploadDateKey, headFont, true);
/* 387 */       defaultCell.setBackgroundColor(defaultBackgroundColor);
/* 388 */       for (Iterator<BaseAttachment> itor = attachList.iterator(); itor.hasNext(); ) {
/* 389 */         BaseAttachment attach = itor.next();
/* 390 */         addCell(table, attach.getDescription());
/* 391 */         addCell(table, attach.getFileName());
/* 392 */         addCell(table, attach.getUploadDate());
/*     */       } 
/* 394 */       AddTableToTable(parentTable, table, tableColumnCount);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addApproveListTable(Collection approveList) throws DocumentException {
/* 399 */     Paragraph p = new Paragraph(this.messages.getMessage(this.locale, "approveRequest.approveList.title") + "\n", subTitleFont);
/* 400 */     p.setAlignment(0);
/* 401 */     this.document.add((Element)p);
/* 402 */     addSpaceLine(3);
/* 403 */     PdfPTable table = createTable(new float[] { 1.0F, 2.0F, 2.0F, 2.0F, 6.0F }, 100, 0.5F);
/* 404 */     PdfPCell defaultCell = table.getDefaultCell();
/* 405 */     Color defaultBackgroundColor = defaultCell.backgroundColor();
/* 406 */     defaultCell.setBackgroundColor(new Color(153, 153, 255));
/* 407 */     defaultCell.setHorizontalAlignment(1);
/* 408 */     Font headFont = getFont(1, Color.BLACK);
/* 409 */     addCell(table, "#", headFont);
/* 410 */     addCell(table, "approveRequest.approveList.approver", headFont, true);
/* 411 */     addCell(table, "approveRequest.approveList.status", headFont, true);
/* 412 */     addCell(table, "approveRequest.approveList.date", headFont, true);
/* 413 */     addCell(table, "approveRequest.approveList.comment", headFont, true);
/* 414 */     int i = 1;
/* 415 */     for (Iterator<BaseApproveRequest> itor = approveList.iterator(); itor.hasNext(); i++) {
/* 416 */       BaseApproveRequest approveRequest = itor.next();
/* 417 */       if (ApproveStatus.WAITING_FOR_APPROVE.equals(approveRequest.getStatus())) {
/* 418 */         defaultCell.setBackgroundColor(new Color(255, 255, 153));
/*     */       } else {
/* 420 */         defaultCell.setBackgroundColor(defaultBackgroundColor);
/*     */       } 
/* 422 */       defaultCell.setHorizontalAlignment(1);
/* 423 */       addCell(table, String.valueOf(i));
/* 424 */       User approver = approveRequest.getApprover();
/* 425 */       User actualApprover = approveRequest.getActualApprover();
/* 426 */       defaultCell.setHorizontalAlignment(0);
/* 427 */       if (actualApprover == null || actualApprover.equals(approver)) {
/* 428 */         addCell(table, approver.getName());
/*     */       } else {
/* 430 */         addCell(table, (new StringBuffer()).append(approver.getName()).append('/').append(actualApprover.getName()));
/*     */       } 
/* 432 */       defaultCell.setHorizontalAlignment(1);
/* 433 */       addCell(table, approveRequest.getStatus());
/* 434 */       addCell(table, approveRequest.getApproveDate());
/* 435 */       defaultCell.setHorizontalAlignment(0);
/* 436 */       addCell(table, approveRequest.getComment());
/*     */     } 
/* 438 */     this.document.add((Element)table);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRechargeListTable(Rechargeable target, BigDecimal amount, Collection rechargeList) throws DocumentException {
/* 443 */     if (YesNo.YES.equals(target.getIsRecharge())) {
/* 444 */       Paragraph p = new Paragraph(this.messages.getMessage(this.locale, "recharge.buyFor.recharge") + "\n", subTitleFont);
/* 445 */       p.setAlignment(0);
/* 446 */       this.document.add((Element)p);
/* 447 */       addSpaceLine(3);
/* 448 */       PdfPTable table = createTable(new float[] { 1.0F, 4.0F, 2.0F, 2.0F }, 100, 0.5F);
/* 449 */       PdfPCell defaultCell = table.getDefaultCell();
/* 450 */       Color defaultBackgroundColor = defaultCell.backgroundColor();
/* 451 */       defaultCell.setBackgroundColor(new Color(153, 153, 255));
/* 452 */       defaultCell.setHorizontalAlignment(1);
/* 453 */       Font headFont = getFont(1, Color.BLACK);
/* 454 */       addCell(table, "#", headFont);
/* 455 */       addCell(table, "recharge.user", headFont, true);
/* 456 */       addCell(table, "recharge.amount", headFont, true);
/* 457 */       addCell(table, "recharge.percentage", headFont, true);
/* 458 */       defaultCell.setBackgroundColor(defaultBackgroundColor);
/*     */       
/* 460 */       int i = 1;
/* 461 */       for (Iterator<BaseRecharge> itor = rechargeList.iterator(); itor.hasNext(); i++) {
/* 462 */         BaseRecharge recharge = itor.next();
/* 463 */         defaultCell.setHorizontalAlignment(1);
/* 464 */         addCell(table, String.valueOf(i));
/* 465 */         defaultCell.setHorizontalAlignment(0);
/* 466 */         Customer c = recharge.getCustomer();
/* 467 */         if (c != null) {
/* 468 */           Phrase content = new Phrase();
/* 469 */           content.add(new Phrase(c.getDescription(), defaultFont));
/* 470 */           content.add(new Phrase(" (", defaultFont));
/* 471 */           content.add(getPhrase((MetadataDetailEnum)c.getType().getRechargeType()));
/* 472 */           content.add(new Phrase(")", defaultFont));
/* 473 */           addCell(table, content);
/*     */         } else {
/* 475 */           Department d = recharge.getPersonDepartment();
/* 476 */           User u = recharge.getPerson();
/* 477 */           Phrase content = new Phrase();
/* 478 */           content.add(new Phrase(d.getName(), defaultFont));
/* 479 */           content.add(new Phrase(" \\ ", defaultFont));
/* 480 */           if (u != null) {
/* 481 */             content.add(new Phrase(u.getName(), defaultFont));
/*     */           }
/* 483 */           content.add(new Phrase(" (", defaultFont));
/* 484 */           content.add(getPhrase((MetadataDetailEnum)RechargeType.PERSON));
/* 485 */           content.add(new Phrase(")", defaultFont));
/* 486 */           addCell(table, content);
/*     */         } 
/*     */         
/* 489 */         BigDecimal ramount = recharge.getAmount();
/* 490 */         defaultCell.setHorizontalAlignment(2);
/* 491 */         addCell(table, ramount);
/* 492 */         addCell(table, ramount.multiply(new BigDecimal(100)).divide(amount, 2, 6));
/*     */       } 
/*     */       
/* 495 */       defaultCell.setHorizontalAlignment(2);
/* 496 */       addCell(table, null);
/* 497 */       addCell(table, "recharge.total", boldFont, true);
/* 498 */       addCell(table, amount);
/* 499 */       addCell(table, "100.00");
/* 500 */       this.document.add((Element)table);
/*     */     }
/* 502 */     else if (target instanceof BuyForTarget) {
/* 503 */       BuyForTarget bufForTarget = (BuyForTarget)target;
/* 504 */       Paragraph p = new Paragraph(this.messages.getMessage(this.locale, "recharge.buyFor") + "\n", subTitleFont);
/* 505 */       p.setAlignment(0);
/* 506 */       this.document.add((Element)p);
/*     */       
/* 508 */       Color tColor = new Color(0, 51, 102);
/*     */       
/* 510 */       PdfPTable table = createTable(new float[] { 1.0F, 2.0F, 1.0F, 2.0F }, 100, 0.0F);
/* 511 */       addCell(table, "recharge.buyFor.department", tColor, true);
/* 512 */       Department d = bufForTarget.getBuyForDepartment();
/* 513 */       if (d != null) {
/* 514 */         addCell(table, d.getName());
/*     */       } else {
/* 516 */         addCell(table, null);
/*     */       } 
/* 518 */       addCell(table, "recharge.buyFor.user", tColor, true);
/* 519 */       User u = bufForTarget.getBuyForUser();
/* 520 */       if (u != null) {
/* 521 */         addCell(table, u.getName());
/*     */       } else {
/* 523 */         addCell(table, null);
/*     */       } 
/* 525 */       this.document.add((Element)table);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRechargeListTableToTable(PdfPTable parentTable, int tableColumnCount, Rechargeable target, BigDecimal amount, Collection rechargeList) {
/* 531 */     if (YesNo.YES.equals(target.getIsRecharge())) {
/* 532 */       parentTable.getDefaultCell().setHorizontalAlignment(0);
/* 533 */       addCell(parentTable, "recharge.buyFor.recharge", tableColumnCount, itemSubTitleFont, true);
/* 534 */       PdfPTable table = createTable(new float[] { 1.0F, 4.0F, 2.0F, 2.0F }, 100, 0.5F);
/* 535 */       PdfPCell defaultCell = table.getDefaultCell();
/* 536 */       Color defaultBackgroundColor = defaultCell.backgroundColor();
/* 537 */       defaultCell.setBackgroundColor(new Color(221, 221, 255));
/* 538 */       defaultCell.setHorizontalAlignment(1);
/* 539 */       Font headFont = getFont(1, Color.BLACK);
/* 540 */       addCell(table, "#", headFont);
/* 541 */       addCell(table, "recharge.user", headFont, true);
/* 542 */       addCell(table, "recharge.amount", headFont, true);
/* 543 */       addCell(table, "recharge.percentage", headFont, true);
/* 544 */       defaultCell.setBackgroundColor(defaultBackgroundColor);
/*     */       
/* 546 */       int i = 1;
/* 547 */       for (Iterator<BaseRecharge> itor = rechargeList.iterator(); itor.hasNext(); i++) {
/* 548 */         BaseRecharge recharge = itor.next();
/* 549 */         defaultCell.setHorizontalAlignment(1);
/* 550 */         addCell(table, String.valueOf(i));
/* 551 */         defaultCell.setHorizontalAlignment(0);
/* 552 */         Customer c = recharge.getCustomer();
/* 553 */         if (c != null) {
/* 554 */           Phrase content = new Phrase();
/* 555 */           content.add(new Phrase(c.getDescription(), defaultFont));
/* 556 */           content.add(new Phrase(" (", defaultFont));
/* 557 */           content.add(getPhrase((MetadataDetailEnum)c.getType().getRechargeType()));
/* 558 */           content.add(new Phrase(")", defaultFont));
/* 559 */           addCell(table, content);
/*     */         } else {
/* 561 */           Department d = recharge.getPersonDepartment();
/* 562 */           User u = recharge.getPerson();
/* 563 */           Phrase content = new Phrase();
/* 564 */           content.add(new Phrase(d.getName(), defaultFont));
/* 565 */           content.add(new Phrase(" \\ ", defaultFont));
/* 566 */           if (u != null) {
/* 567 */             content.add(new Phrase(u.getName(), defaultFont));
/*     */           }
/* 569 */           content.add(new Phrase(" (", defaultFont));
/* 570 */           content.add(getPhrase((MetadataDetailEnum)RechargeType.PERSON));
/* 571 */           content.add(new Phrase(")", defaultFont));
/* 572 */           addCell(table, content);
/*     */         } 
/* 574 */         BigDecimal ramount = recharge.getAmount();
/* 575 */         defaultCell.setHorizontalAlignment(2);
/* 576 */         addCell(table, ramount);
/* 577 */         addCell(table, ramount.multiply(new BigDecimal(100)).divide(amount, 2, 6));
/*     */       } 
/*     */       
/* 580 */       defaultCell.setHorizontalAlignment(2);
/* 581 */       addCell(table, null);
/* 582 */       addCell(table, "recharge.total", boldFont, true);
/* 583 */       addCell(table, amount);
/* 584 */       addCell(table, "100");
/* 585 */       AddTableToTable(parentTable, table, tableColumnCount);
/*     */     }
/* 587 */     else if (target instanceof BuyForTarget) {
/* 588 */       BuyForTarget bufForTarget = (BuyForTarget)target;
/* 589 */       Color tColor = new Color(0, 51, 102);
/* 590 */       PdfPTable table = createTable(new float[] { 1.0F, 2.0F, 1.0F, 2.0F }, 100, 0.0F);
/* 591 */       addCell(table, "recharge.buyFor", 4, itemSubTitleFont, true);
/* 592 */       addCell(table, "recharge.buyFor.department", tColor, true);
/* 593 */       Department d = bufForTarget.getBuyForDepartment();
/* 594 */       if (d != null) {
/* 595 */         addCell(table, d.getName());
/*     */       } else {
/* 597 */         addCell(table, null);
/*     */       } 
/* 599 */       addCell(table, "recharge.buyFor.user", tColor, true);
/* 600 */       User u = bufForTarget.getBuyForUser();
/* 601 */       if (u != null) {
/* 602 */         addCell(table, u.getName());
/*     */       } else {
/* 604 */         addCell(table, null);
/*     */       } 
/* 606 */       AddTableToTable(parentTable, table, tableColumnCount);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void AddTableToTable(PdfPTable parentTable, PdfPTable table, int tableColumnCount) {
/* 612 */     PdfPCell pCell = parentTable.getDefaultCell();
/* 613 */     int horizontalAlignment = pCell.getHorizontalAlignment();
/* 614 */     int verticalAlignment = pCell.getVerticalAlignment();
/* 615 */     float paddingTop = pCell.getPaddingTop();
/* 616 */     float paddingBottom = pCell.getPaddingBottom();
/* 617 */     float paddingLeft = pCell.getPaddingLeft();
/* 618 */     float paddingRight = pCell.getPaddingRight();
/* 619 */     pCell.setVerticalAlignment(4);
/* 620 */     pCell.setColspan(tableColumnCount);
/* 621 */     pCell.setHorizontalAlignment(1);
/* 622 */     pCell.setPaddingLeft(0.0F);
/* 623 */     pCell.setPaddingRight(0.0F);
/* 624 */     pCell.setPaddingTop(0.0F);
/* 625 */     pCell.setPaddingBottom(0.0F);
/* 626 */     table.setExtendLastRow(true);
/* 627 */     parentTable.addCell(table);
/* 628 */     pCell.setHorizontalAlignment(horizontalAlignment);
/* 629 */     pCell.setVerticalAlignment(verticalAlignment);
/* 630 */     pCell.setColspan(1);
/* 631 */     pCell.setPaddingLeft(paddingLeft);
/* 632 */     pCell.setPaddingRight(paddingRight);
/* 633 */     pCell.setPaddingTop(paddingTop);
/* 634 */     pCell.setPaddingBottom(paddingBottom);
/*     */   }
/*     */   
/*     */   private Phrase getPhrase(MetadataDetailEnum metadata) {
/* 638 */     String content = this.locale.equals(Locale.ENGLISH) ? metadata.getEngShortDescription() : metadata.getChnShortDescription();
/* 639 */     Font font = getFont(metadata.getColor());
/* 640 */     return new Phrase(content, font);
/*     */   }
/*     */ }


/* Location:              /Users/chentao/Desktop/portal-s/portalV6/WEB-INF/classes/!/com/aof/utils/PDFReport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */