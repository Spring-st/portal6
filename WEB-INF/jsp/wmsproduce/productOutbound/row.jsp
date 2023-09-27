<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<tr id="r${X_OBJECT.id}" align="center">
<td><input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/></td>
<td>${X_OBJECT.box.lot.id}</td>
<td>${X_OBJECT.box.psoItem.poipItem.poip_number.po_number}</td>
<td>${X_OBJECT.box.psoItem.poipItem.line}</td>
<td>${X_OBJECT.box.part.id}</td>
<td>${X_OBJECT.box.part.describe1}</td>
<td>${X_OBJECT.box.part.describe1}</td>
<td>${X_OBJECT.qty}</td>
<td>${X_OBJECT.box.part.unit}</td>
<td>${X_OBJECT.date}</td>
<td>${X_OBJECT.box.status_freeze.chnShortDescription}</td>	
<td>${X_OBJECT.box.status_print.chnShortDescription}</td>	
<td>${X_OBJECT.box.status.chnShortDescription}</td>	
</tr>

