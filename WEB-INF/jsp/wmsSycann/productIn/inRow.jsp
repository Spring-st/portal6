<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
<td>
<input type="checkbox" name="ids" value="${X_OBJECT.id}" onclick="productbox_click();"/>
<input type="checkbox" name="hnccodes" value="${X_OBJECT.hncCode}" style="display: none;" />
</td>
<td>${X_OBJECT.hncCode}</td>
<td>${X_OBJECT.shCode}</td>
<td>${X_OBJECT.partHubCode}</td>
<td>${X_OBJECT.hubDesc}</td>
<td>${X_OBJECT.partTireCode}</td>
<td>${X_OBJECT.tireDesc}</td>
<td>${X_OBJECT.partValvestemCode}</td>
<td>${X_OBJECT.valvestemDesc}</td>
<td>${X_OBJECT.qty}</td>
</tr>

