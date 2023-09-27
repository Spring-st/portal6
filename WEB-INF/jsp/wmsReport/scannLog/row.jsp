<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<tr align="center">
	<td>${X_OBJECT.userId.name}</td>
	<td>${X_OBJECT.date }</td>
	<td>
		<c:if test="${X_OBJECT.type=='1'}">采购单收货</c:if>
		<c:if test="${X_OBJECT.type=='2'}">品质质检</c:if>
		<c:if test="${X_OBJECT.type=='3'}">仓库入库</c:if>
		<c:if test="${X_OBJECT.type=='4'}">原材料出库</c:if>
		<c:if test="${X_OBJECT.type=='5'}">成品装箱</c:if>
		<c:if test="${X_OBJECT.type=='6'}">成品出库</c:if>
		<c:if test="${X_OBJECT.type=='7'}">移库</c:if>
		<c:if test="${X_OBJECT.type=='8'}">非计划出库</c:if>
		<c:if test="${X_OBJECT.type=='9'}">非计划 入库</c:if>
		<c:if test="${X_OBJECT.type=='10'}">条码拆分</c:if>
		<c:if test="${X_OBJECT.type=='11'}">条码合并</c:if>
		<c:if test="${X_OBJECT.type=='12'}">采购退货</c:if>
		<c:if test="${X_OBJECT.type=='13'}">退货入库</c:if>
		<c:if test="${X_OBJECT.type=='15'}">移库</c:if>
		<c:if test="${X_OBJECT.type=='55'}">库房盘点</c:if>
		<c:if test="${X_OBJECT.type=='56'}">物料盘点</c:if>
		<c:if test="${X_OBJECT.type=='41'}">入库前退货</c:if>
		<c:if test="${X_OBJECT.type=='42'}">入库后退货</c:if>
		<c:if test="${X_OBJECT.type=='43'}">装车撤回</c:if>
		<c:if test="${X_OBJECT.type=='44'}">备货撤回</c:if>
	</td>
	<td>${X_OBJECT.describe }</td>
</tr>

