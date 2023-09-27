<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
	<c:set var="x_hotel_promoteStatus_GLOBAL"
		value="<%=com.aof.model.metadata.HotelPromoteStatus.GLOBAL%>" />
	<tr id="r${X_OBJECT.id}">
		<td>
			${X_OBJECT.hncCode}
		</td>
		<td>
			${X_OBJECT.hncDesc}
		</td>
		<td>
			${X_OBJECT.qty }
		</td>
		<td>
			<a
				href='javascript:select("${X_OBJECT.id}","${X_OBJECT.hncCode}", "${X_OBJECT.qty}","${X_OBJECT.hncDesc }");'><bean:message
					key="all.select" />
			</a>
		</td>
	</tr>