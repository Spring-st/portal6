<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td><a href='javascript:view("${X_OBJECT.box_adjustment_id.id}")'>${X_OBJECT.box_adjustment_id.new_box_id.lot.id}</a></td>
	<td>${X_OBJECT.box_adjustment_id.new_box_id.number}</td>
	<td>${X_OBJECT.box_id.lot.id}</td>
	<td>${X_OBJECT.box_id.number}</td>
	<td>${X_OBJECT.box_adjustment_id.new_box_id.psoItem.poipItem.poip_number.po_number}</td>
	<td>${X_OBJECT.box_adjustment_id.new_box_id.psoItem.poipItem.line}</td>
	<td>${X_OBJECT.box_adjustment_id.new_box_id.part.id}</td>
	<td>${X_OBJECT.box_adjustment_id.new_box_id.part.dpiNo}</td>
	<td>${X_OBJECT.box_adjustment_id.new_box_id.part.oldCode}</td>
	<td>${X_OBJECT.box_adjustment_id.new_box_id.part.describe1}</td>
	<td>${X_OBJECT.box_adjustment_id.new_box_id.part.describe2}</td>
	<td>${X_OBJECT.box_adjustment_id.old_location.code}</td>
	<td>
		<c:if test="${X_OBJECT.box_adjustment_id.type=='1'}">拆分</c:if>
		<c:if test="${X_OBJECT.box_adjustment_id.type=='2'}">合并</c:if>
	</td>
	<td>${X_OBJECT.box_adjustment_id.operation.name}</td>
	<td>${X_OBJECT.box_adjustment_id.date}</td>
	<td>${X_OBJECT.box_adjustment_id.remark}</td>
</tr>

