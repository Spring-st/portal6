<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<tr id="r${X_OBJECT.wmsUWItem.id}" align="center">
<c:if test="${x_warehousing.status=='3'}">
	 <td>
	 <c:if test="${X_OBJECT.isPrint.enumCode!=0}">
     <input type="checkbox" name="ids" value="${X_OBJECT.wmsUWItem.id}" onclick="productbox_click();"/>
     </c:if>
     <c:if test="${X_OBJECT.isPrint.enumCode==0}">
     <input type="checkbox" disabled="disabled"  />
     </c:if>
	</td>
	</c:if>
	<td>${X_OBJECT.lotSer.id}</td>
	<td>${X_OBJECT.wmsUWItem.supplierCode}</td>
	<td>${X_OBJECT.wmsUWItem.supplierStandard}</td>
<!-- <td>${X_OBJECT.wmsUWItem.supplierName}</td>-->
<td>${X_OBJECT.wmsUWItem.wmsPart}</td>
<td>${X_OBJECT.wmsUWItem.enterTime}</td>
<td>${X_OBJECT.wmsUWItem.blanket}</td>
<td>${X_OBJECT.count}</td>
<td>${X_OBJECT.wmsUWItem.unit}</td>
<td>
<input type="text" value="${X_OBJECT.manualNo}" id="saveBut_${X_OBJECT.id}" style="display: none;" size="7"/>
<span id="${X_OBJECT.id}_span">${X_OBJECT.manualNo}</span>
</td>
<td>${X_OBJECT.location.code}</td>
<td>
<c:if test="${X_OBJECT.isScanning=='0'}">Yes</c:if>
<c:if test="${X_OBJECT.isScanning=='1'}">No</c:if>
</td>
<td>
<input type="button" value="修改手册号" onclick="editWmsUWItem('${X_OBJECT.id}')" id="edit_${X_OBJECT.id}" />
<input type="button" value="保存" onclick="editSaveWmsUWItem('${X_OBJECT.id}')" style="display: none;" id="save_${X_OBJECT.id}"/>
<%--<a href="#" onclick="deleteWmsUWItem('${X_OBJECT.id}')">删除</a>--%>
</td>                                
</tr>
