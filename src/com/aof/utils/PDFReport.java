package com.aof.utils;

import com.aof.model.BaseAttachment;
import com.aof.model.admin.Customer;
import com.aof.model.admin.Department;
import com.aof.model.admin.User;
import com.aof.model.business.BaseApproveRequest;
import com.aof.model.business.BaseRecharge;
import com.aof.model.business.BuyForTarget;
import com.aof.model.business.Rechargeable;
import com.aof.model.metadata.ApproveStatus;
import com.aof.model.metadata.MetadataDetailEnum;
import com.aof.model.metadata.RechargeType;
import com.aof.model.metadata.YesNo;
import com.aof.service.admin.SiteManager;
import com.aof.web.struts.action.ActionUtils2;
import com.aof.web.struts.action.ServiceLocator;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.util.MessageResources;

public class PDFReport {
    private static BaseFont baseFont;

    static {
        try {
            baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
        } catch (Exception e) {
            throw new RuntimeException("error create font for pdf", e);
        }
    }

    private static Font defaultTitleFont = new Font(baseFont, 16.0F, 1);

    private static Font defaultFont = new Font(baseFont, 10.0F, 0);

    private static Font boldFont;

    private static Font subTitleFont = getFont(14.0F, 1, new Color(0, 0, 255));

    private static Font itemSubTitleFont = getFont(10.0F, 1, new Color(0, 0, 255));

    private Document document;

    private OutputStream out;

    private MessageResources messages;

    private Locale locale;

    private int tempFileIndex;

    static {
        boldFont = new Font(baseFont, 10.0F, 1);
    }

    public static Font getSubTitleFont() {
        return subTitleFont;
    }

    public static Font getDefaultFont() {
        return defaultFont;
    }

    public static Font getDefaultTitleFont() {
        return defaultTitleFont;
    }

    public static Font getItemSubTitleFont() {
        return itemSubTitleFont;
    }

    public static Font getBoldFont() {
        return boldFont;
    }

    public static Font getFont(String strColor) {
        return getFont(0, strColor);
    }

    public static Font getFont(Color color) {
        return getFont(0, color);
    }

    public static Font getFont(int style, String strColor) {
        return getFont(10.0F, style, strColor);
    }

    public static Font getFont(int style, Color color) {
        return getFont(10.0F, style, color);
    }

    public static Font getFont(float size, int style, String strColor) {
        if (strColor == null)
            return new Font(baseFont, size, style);
        Color color = ColorUtils.getColor(strColor);
        return new Font(baseFont, size, style, color);
    }

    public static Font getFont(float size, int style, Color color) {
        return new Font(baseFont, size, style, color);
    }

    public static PdfPTable createTable(float[] widths) {
        PdfPTable table = new PdfPTable(widths);
        PdfPCell defaultCell = table.getDefaultCell();
        defaultCell.setNoWrap(false);
        defaultCell.setMinimumHeight(16.0F);
        defaultCell.setHorizontalAlignment(0);
        return table;
    }

    public static PdfPTable createTable(float[] widths, int totalWidth) {
        PdfPTable table = createTable(widths);
        table.setWidthPercentage(totalWidth);
        return table;
    }

    public static PdfPTable createTable(float[] widths, int totalWidth, float borderWidth) {
        PdfPTable table = createTable(widths, totalWidth);
        PdfPCell defaultCell = table.getDefaultCell();
        defaultCell.setBorderWidth(borderWidth);
        return table;
    }

    public static PDFReport createReport(Integer siteId, String titleKey, HttpServletRequest request, MessageResources messages, Locale locale) throws DocumentException, MalformedURLException, IOException {
        return createReport(siteId, titleKey, request, messages, locale, PageSize.A4);
    }

    public static PDFReport createReport(Integer siteId, String titleKey, HttpServletRequest request, MessageResources messages, Locale locale, Rectangle pageSize) throws DocumentException, MalformedURLException, IOException {
        PDFReport report = new PDFReport();
        report.tempFileIndex = SessionTempFile.createNewTempFile(request);
        report.document = new Document(pageSize);
        report.out = new FileOutputStream(SessionTempFile.getTempFile(report.tempFileIndex, request));
        report.messages = messages;
        report.locale = locale;
        PdfWriter.getInstance(report.document, report.out);
        report.document.open();
        if (siteId != null) {
            SiteManager sm = ServiceLocator.getSiteManager(request);
            InputStream is = sm.getSiteLogo(siteId);
            if (is != null) {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                byte[] buf = new byte[512];
                while (true) {
                    int i = is.read(buf);
                    if (i > 0) {
                        os.write(buf, 0, i);
                        continue;
                    }
                    break;
                }
                Image logo = Image.getInstance(os.toByteArray());
                logo.scalePercent(80.0F);
                report.document.add((Element)logo);
            }
        }
        Paragraph p = new Paragraph(messages.getMessage(locale, titleKey) + "\n\n", getDefaultTitleFont());
        p.setAlignment(1);
        report.document.add((Element)p);
        return report;
    }

    public Document getDocument() {
        return this.document;
    }

    public void addSpaceLine(int lineHeight) throws DocumentException {
        Paragraph p = new Paragraph(
                "" + "\n",
                getFont(lineHeight, 0, Color.BLACK));
        p.setAlignment(0);
        this.document.add((Element)p);
    }

    public void addCell(PdfPTable table, Object content) {
        addCell(table, content, 1, null, null, false);
    }

    public void addCell(PdfPTable table, Object content, boolean resource) {
        addCell(table, content, 1, null, null, resource);
    }

    public void addCell(PdfPTable table, Object content, int colSpan) {
        addCell(table, content, colSpan, null, null, false);
    }

    public void addCell(PdfPTable table, Object content, int colSpan, boolean resource) {
        addCell(table, content, colSpan, null, null, resource);
    }

    public void addCell(PdfPTable table, Object content, Color color) {
        addCell(table, content, 1, color, null, false);
    }

    public void addCell(PdfPTable table, Object content, Color color, boolean resource) {
        addCell(table, content, 1, color, null, resource);
    }

    public void addCell(PdfPTable table, Object content, Font font) {
        addCell(table, content, 1, null, font, false);
    }

    public void addCell(PdfPTable table, Object content, Font font, boolean resource) {
        addCell(table, content, 1, null, font, resource);
    }

    public void addCell(PdfPTable table, Object content, int colSpan, Color color) {
        addCell(table, content, colSpan, color, null, false);
    }

    public void addCell(PdfPTable table, Object content, int colSpan, Color color, boolean resource) {
        addCell(table, content, colSpan, color, null, resource);
    }

    public void addCell(PdfPTable table, Object content, int colSpan, Font font) {
        addCell(table, content, colSpan, null, font, false);
    }

    public void addCell(PdfPTable table, Object content, int colSpan, Font font, boolean resource) {
        addCell(table, content, colSpan, null, font, resource);
    }

    private void addCell(PdfPTable table, Object content, int colSpan, Color color, Font font, boolean resource) {
        String cellContent = null;
        Font cellFont = null;
        if (content == null) {
            cellContent = "";
        } else if (content instanceof MetadataDetailEnum) {
            MetadataDetailEnum metadata = (MetadataDetailEnum)content;
            cellContent = this.locale.equals(Locale.ENGLISH) ? metadata.getEngShortDescription() : metadata.getChnShortDescription();
            if (font != null) {
                cellFont = getFont(font.size(), font.style(), metadata.getColor());
            } else {
                cellFont = getFont(metadata.getColor());
            }
        } else if (content instanceof Date) {
            cellContent = ActionUtils2.getDisplayDateFromDate((Date)content);
        } else if (content instanceof String) {
            if (resource) {
                cellContent = this.messages.getMessage(this.locale, (String)content);
            } else {
                cellContent = (String)content;
            }
        } else {
            if (content instanceof PdfPCell) {
                table.addCell((PdfPCell)content);
                return;
            }
            if (content instanceof Phrase) {
                table.addCell((Phrase)content);
                return;
            }
            if (content instanceof PdfPTable) {
                table.addCell((PdfPTable)content);
                return;
            }
            if (content instanceof Image) {
                table.addCell((Image)content);
                return;
            }
            cellContent = content.toString();
        }
        if (cellFont == null)
            if (font == null) {
                if (color == null) {
                    cellFont = getDefaultFont();
                } else {
                    cellFont = getFont(color);
                }
            } else {
                cellFont = font;
            }
        table.getDefaultCell().setColspan(colSpan);
        table.addCell(new Phrase(cellContent, cellFont));
        table.getDefaultCell().setColspan(1);
    }

    public void output(String fileName, HttpServletResponse response) throws IOException {
        this.document.close();
        this.out.close();
        response.sendRedirect("download/" + this.tempFileIndex + "/" + URLEncoder.encode(fileName, "UTF8") + ".pdf");
    }

    public void addSplitLine() throws DocumentException {
        PdfPTable table = createTable(new float[] { 1.0F }, 100);
        PdfPCell defaultCell = table.getDefaultCell();
        defaultCell.setBorderWidth(0.0F);
        defaultCell.setBorderWidthBottom(0.1F);
        defaultCell.setFixedHeight(5.0F);
        defaultCell.setBorderColorBottom(new Color(60, 120, 181));
        table.addCell("");
        defaultCell.setBorderWidthBottom(0.0F);
        table.addCell("");
        this.document.add((Element)table);
    }

    public void addAttachmentListTableToTable(PdfPTable parentTable, int tableColumnCount, Collection attachList, String titleKey, String fileTitleKey, String fileNameKey, String uploadDateKey) {
        if (!attachList.isEmpty()) {
            parentTable.getDefaultCell().setHorizontalAlignment(0);
            addCell(parentTable, titleKey, tableColumnCount, itemSubTitleFont, true);
            PdfPTable table = createTable(new float[] { 4.0F, 4.0F, 1.0F }, 100, 0.5F);
            PdfPCell defaultCell = table.getDefaultCell();
            Color defaultBackgroundColor = defaultCell.backgroundColor();
            defaultCell.setBackgroundColor(new Color(221, 221, 255));
            defaultCell.setHorizontalAlignment(1);
            Font headFont = getFont(1, Color.BLACK);
            addCell(table, fileTitleKey, headFont, true);
            addCell(table, fileNameKey, headFont, true);
            addCell(table, uploadDateKey, headFont, true);
            defaultCell.setBackgroundColor(defaultBackgroundColor);
            for (Iterator<BaseAttachment> itor = attachList.iterator(); itor.hasNext(); ) {
                BaseAttachment attach = itor.next();
                addCell(table, attach.getDescription());
                addCell(table, attach.getFileName());
                addCell(table, attach.getUploadDate());
            }
            AddTableToTable(parentTable, table, tableColumnCount);
        }
    }

    public void addApproveListTable(Collection approveList) throws DocumentException {
        Paragraph p = new Paragraph(this.messages.getMessage(this.locale, "approveRequest.approveList.title") + "\n", subTitleFont);
        p.setAlignment(0);
        this.document.add((Element)p);
        addSpaceLine(3);
        PdfPTable table = createTable(new float[] { 1.0F, 2.0F, 2.0F, 2.0F, 6.0F }, 100, 0.5F);
        PdfPCell defaultCell = table.getDefaultCell();
        Color defaultBackgroundColor = defaultCell.backgroundColor();
        defaultCell.setBackgroundColor(new Color(153, 153, 255));
        defaultCell.setHorizontalAlignment(1);
        Font headFont = getFont(1, Color.BLACK);
        addCell(table, "#", headFont);
        addCell(table, "approveRequest.approveList.approver", headFont, true);
        addCell(table, "approveRequest.approveList.status", headFont, true);
        addCell(table, "approveRequest.approveList.date", headFont, true);
        addCell(table, "approveRequest.approveList.comment", headFont, true);
        int i = 1;
        for (Iterator<BaseApproveRequest> itor = approveList.iterator(); itor.hasNext(); i++) {
            BaseApproveRequest approveRequest = itor.next();
            if (ApproveStatus.WAITING_FOR_APPROVE.equals(approveRequest.getStatus())) {
                defaultCell.setBackgroundColor(new Color(255, 255, 153));
            } else {
                defaultCell.setBackgroundColor(defaultBackgroundColor);
            }
            defaultCell.setHorizontalAlignment(1);
            addCell(table, String.valueOf(i));
            User approver = approveRequest.getApprover();
            User actualApprover = approveRequest.getActualApprover();
            defaultCell.setHorizontalAlignment(0);
            if (actualApprover == null || actualApprover.equals(approver)) {
                addCell(table, approver.getName());
            } else {
                addCell(table, (new StringBuffer()).append(approver.getName()).append('/').append(actualApprover.getName()));
            }
            defaultCell.setHorizontalAlignment(1);
            addCell(table, approveRequest.getStatus());
            addCell(table, approveRequest.getApproveDate());
            defaultCell.setHorizontalAlignment(0);
            addCell(table, approveRequest.getComment());
        }
        this.document.add((Element)table);
    }

    public void addRechargeListTable(Rechargeable target, BigDecimal amount, Collection rechargeList) throws DocumentException {
        if (YesNo.YES.equals(target.getIsRecharge())) {
            Paragraph p = new Paragraph(this.messages.getMessage(this.locale, "recharge.buyFor.recharge") + "\n", subTitleFont);
            p.setAlignment(0);
            this.document.add((Element)p);
            addSpaceLine(3);
            PdfPTable table = createTable(new float[] { 1.0F, 4.0F, 2.0F, 2.0F }, 100, 0.5F);
            PdfPCell defaultCell = table.getDefaultCell();
            Color defaultBackgroundColor = defaultCell.backgroundColor();
            defaultCell.setBackgroundColor(new Color(153, 153, 255));
            defaultCell.setHorizontalAlignment(1);
            Font headFont = getFont(1, Color.BLACK);
            addCell(table, "#", headFont);
            addCell(table, "recharge.user", headFont, true);
            addCell(table, "recharge.amount", headFont, true);
            addCell(table, "recharge.percentage", headFont, true);
            defaultCell.setBackgroundColor(defaultBackgroundColor);
            int i = 1;
            for (Iterator<BaseRecharge> itor = rechargeList.iterator(); itor.hasNext(); i++) {
                BaseRecharge recharge = itor.next();
                defaultCell.setHorizontalAlignment(1);
                addCell(table, String.valueOf(i));
                defaultCell.setHorizontalAlignment(0);
                Customer c = recharge.getCustomer();
                if (c != null) {
                    Phrase content = new Phrase();
                    content.add(new Phrase(c.getDescription(), defaultFont));
                    content.add(new Phrase(" (", defaultFont));
                    content.add(getPhrase((MetadataDetailEnum)c.getType().getRechargeType()));
                    content.add(new Phrase(")", defaultFont));
                    addCell(table, content);
                } else {
                    Department d = recharge.getPersonDepartment();
                    User u = recharge.getPerson();
                    Phrase content = new Phrase();
                    content.add(new Phrase(d.getName(), defaultFont));
                    content.add(new Phrase(" \\ ", defaultFont));
                    if (u != null)
                        content.add(new Phrase(u.getName(), defaultFont));
                    content.add(new Phrase(" (", defaultFont));
                    content.add(getPhrase((MetadataDetailEnum)RechargeType.PERSON));
                    content.add(new Phrase(")", defaultFont));
                    addCell(table, content);
                }
                BigDecimal ramount = recharge.getAmount();
                defaultCell.setHorizontalAlignment(2);
                addCell(table, ramount);
                addCell(table, ramount.multiply(new BigDecimal(100)).divide(amount, 2, 6));
            }
            defaultCell.setHorizontalAlignment(2);
            addCell(table, null);
            addCell(table, "recharge.total", boldFont, true);
            addCell(table, amount);
            addCell(table, "100.00");
            this.document.add((Element)table);
        } else if (target instanceof BuyForTarget) {
            BuyForTarget bufForTarget = (BuyForTarget)target;
            Paragraph p = new Paragraph(this.messages.getMessage(this.locale, "recharge.buyFor") + "\n", subTitleFont);
            p.setAlignment(0);
            this.document.add((Element)p);
            Color tColor = new Color(0, 51, 102);
            PdfPTable table = createTable(new float[] { 1.0F, 2.0F, 1.0F, 2.0F }, 100, 0.0F);
            addCell(table, "recharge.buyFor.department", tColor, true);
            Department d = bufForTarget.getBuyForDepartment();
            if (d != null) {
                addCell(table, d.getName());
            } else {
                addCell(table, null);
            }
            addCell(table, "recharge.buyFor.user", tColor, true);
            User u = bufForTarget.getBuyForUser();
            if (u != null) {
                addCell(table, u.getName());
            } else {
                addCell(table, null);
            }
            this.document.add((Element)table);
        }
    }

    public void addRechargeListTableToTable(PdfPTable parentTable, int tableColumnCount, Rechargeable target, BigDecimal amount, Collection rechargeList) {
        if (YesNo.YES.equals(target.getIsRecharge())) {
            parentTable.getDefaultCell().setHorizontalAlignment(0);
            addCell(parentTable, "recharge.buyFor.recharge", tableColumnCount, itemSubTitleFont, true);
            PdfPTable table = createTable(new float[] { 1.0F, 4.0F, 2.0F, 2.0F }, 100, 0.5F);
            PdfPCell defaultCell = table.getDefaultCell();
            Color defaultBackgroundColor = defaultCell.backgroundColor();
            defaultCell.setBackgroundColor(new Color(221, 221, 255));
            defaultCell.setHorizontalAlignment(1);
            Font headFont = getFont(1, Color.BLACK);
            addCell(table, "#", headFont);
            addCell(table, "recharge.user", headFont, true);
            addCell(table, "recharge.amount", headFont, true);
            addCell(table, "recharge.percentage", headFont, true);
            defaultCell.setBackgroundColor(defaultBackgroundColor);
            int i = 1;
            for (Iterator<BaseRecharge> itor = rechargeList.iterator(); itor.hasNext(); i++) {
                BaseRecharge recharge = itor.next();
                defaultCell.setHorizontalAlignment(1);
                addCell(table, String.valueOf(i));
                defaultCell.setHorizontalAlignment(0);
                Customer c = recharge.getCustomer();
                if (c != null) {
                    Phrase content = new Phrase();
                    content.add(new Phrase(c.getDescription(), defaultFont));
                    content.add(new Phrase(" (", defaultFont));
                    content.add(getPhrase((MetadataDetailEnum)c.getType().getRechargeType()));
                    content.add(new Phrase(")", defaultFont));
                    addCell(table, content);
                } else {
                    Department d = recharge.getPersonDepartment();
                    User u = recharge.getPerson();
                    Phrase content = new Phrase();
                    content.add(new Phrase(d.getName(), defaultFont));
                    content.add(new Phrase(" \\ ", defaultFont));
                    if (u != null)
                        content.add(new Phrase(u.getName(), defaultFont));
                    content.add(new Phrase(" (", defaultFont));
                    content.add(getPhrase((MetadataDetailEnum)RechargeType.PERSON));
                    content.add(new Phrase(")", defaultFont));
                    addCell(table, content);
                }
                BigDecimal ramount = recharge.getAmount();
                defaultCell.setHorizontalAlignment(2);
                addCell(table, ramount);
                addCell(table, ramount.multiply(new BigDecimal(100)).divide(amount, 2, 6));
            }
            defaultCell.setHorizontalAlignment(2);
            addCell(table, null);
            addCell(table, "recharge.total", boldFont, true);
            addCell(table, amount);
            addCell(table, "100");
            AddTableToTable(parentTable, table, tableColumnCount);
        } else if (target instanceof BuyForTarget) {
            BuyForTarget bufForTarget = (BuyForTarget)target;
            Color tColor = new Color(0, 51, 102);
            PdfPTable table = createTable(new float[] { 1.0F, 2.0F, 1.0F, 2.0F }, 100, 0.0F);
            addCell(table, "recharge.buyFor", 4, itemSubTitleFont, true);
            addCell(table, "recharge.buyFor.department", tColor, true);
            Department d = bufForTarget.getBuyForDepartment();
            if (d != null) {
                addCell(table, d.getName());
            } else {
                addCell(table, null);
            }
            addCell(table, "recharge.buyFor.user", tColor, true);
            User u = bufForTarget.getBuyForUser();
            if (u != null) {
                addCell(table, u.getName());
            } else {
                addCell(table, null);
            }
            AddTableToTable(parentTable, table, tableColumnCount);
        }
    }

    public static void AddTableToTable(PdfPTable parentTable, PdfPTable table, int tableColumnCount) {
        PdfPCell pCell = parentTable.getDefaultCell();
        int horizontalAlignment = pCell.getHorizontalAlignment();
        int verticalAlignment = pCell.getVerticalAlignment();
        float paddingTop = pCell.getPaddingTop();
        float paddingBottom = pCell.getPaddingBottom();
        float paddingLeft = pCell.getPaddingLeft();
        float paddingRight = pCell.getPaddingRight();
        pCell.setVerticalAlignment(4);
        pCell.setColspan(tableColumnCount);
        pCell.setHorizontalAlignment(1);
        pCell.setPaddingLeft(0.0F);
        pCell.setPaddingRight(0.0F);
        pCell.setPaddingTop(0.0F);
        pCell.setPaddingBottom(0.0F);
        table.setExtendLastRow(true);
        parentTable.addCell(table);
        pCell.setHorizontalAlignment(horizontalAlignment);
        pCell.setVerticalAlignment(verticalAlignment);
        pCell.setColspan(1);
        pCell.setPaddingLeft(paddingLeft);
        pCell.setPaddingRight(paddingRight);
        pCell.setPaddingTop(paddingTop);
        pCell.setPaddingBottom(paddingBottom);
    }

    private Phrase getPhrase(MetadataDetailEnum metadata) {
        String content = this.locale.equals(Locale.ENGLISH) ? metadata.getEngShortDescription() : metadata.getChnShortDescription();
        Font font = getFont(metadata.getColor());
        return new Phrase(content, font);
    }
}
