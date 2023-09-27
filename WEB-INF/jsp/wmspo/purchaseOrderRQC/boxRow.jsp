<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">

<td><input type="checkbox" name="ids" value="${X_OBJECT.lot.id}" onclick="productbox_click();"/></td>
<%-- 条码编号--%>
<td>${X_OBJECT.lot.id}</td>
<%-- 采购单号--%>
<td>${X_OBJECT.po_number}</td>
<%-- 行号--%>
<td>${X_OBJECT.po_line}</td>
<%-- 发货单号--%>
<td>
<c:if test="${X_OBJECT.psoItem!=null}">${X_OBJECT.psoItem.portalShipOrder.code}</c:if>
<c:if test="${X_OBJECT.single!=null}">${X_OBJECT.single.code}</c:if>
</td>
<%-- 物料号--%>
<td>${X_OBJECT.part.id}</td>
<%-- DPI--%>
<td>${X_OBJECT.part.dpiNo}</td>
<td>${X_OBJECT.part.oldCode}</td>
<%-- 供应商编号--%>
<td>${X_OBJECT.po_supp}</td>
<%-- 描述一--%>
<td>${X_OBJECT.part.describe1}</td>
<%-- 库位--%>
<td>${X_OBJECT.location.code}</td>
<%-- 数量--%>
<td>${X_OBJECT.number}</td>
<%-- 单位--%>
<td>${X_OBJECT.part.unit}</td>
<%-- 时间--%>
<td>${X_OBJECT.po_date}</td>
<%-- 质检状态--%>
<td>
<c:if test="${X_OBJECT.status_rqc==null}">未质检</c:if>
<c:if test="${X_OBJECT.status_rqc!=null}">${X_OBJECT.status_rqc.chnShortDescription}</c:if>
</td>
<%-- 冻结状态--%>
<td>
<c:if test="${X_OBJECT.status_freeze == '0'}"><span style="color: red;">已冻结</span></c:if>
<c:if test="${X_OBJECT.status_freeze == '1'}"><span style="color: green;">未冻结</span></c:if>
</td>	
<%-- 条码状态--%>
<td>${X_OBJECT.status.chnShortDescription}
<input type="hidden" value="${X_OBJECT.status}" id="${X_OBJECT.id}_status"/>
</td>
</tr>

