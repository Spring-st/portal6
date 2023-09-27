<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center" >
	<td>
		${X_OBJECT.fatherPart.id}
	</td>
	<td>
		${X_OBJECT.childPart.id}
	</td>
	<td>${X_OBJECT.childPart.productClass}</td>
	<td>
		${X_OBJECT.childPart.describe1}
	</td>
	<td>
		${X_OBJECT.qty}
	</td>
	<td>
		${X_OBJECT.childPart.unit}
	</td>
	<%--<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	<td>
		
	</td>
	
--%></tr>

