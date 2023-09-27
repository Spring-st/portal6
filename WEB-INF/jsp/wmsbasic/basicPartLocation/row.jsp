<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center">
	<td>
				<a href='javascript:edit("${X_OBJECT.id}")'>${X_OBJECT.part.id}</a>
	</td>
	<td> ${X_OBJECT.location.code} </td>
	<td> ${X_OBJECT.location.basic_storeroom_id.code} </td>
	<td> ${X_OBJECT.part.describe1} </td>
	<td> ${X_OBJECT.part.describe2} </td>
	<td>  ${X_OBJECT.part.unit} </td>
	<td> ${X_OBJECT.part.productClass} </td>
	<td> ${X_OBJECT.part.recommend_date}</td>
	 
</tr>

