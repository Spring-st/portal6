<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<tr id="r${X_OBJECT.id}" align="center" >
	<td>
		<c:if test="${X_OBJECT.handStatus eq 1  }">
			<c:if test="${X_OBJECT.status ne 1  }">
				<a href="javascript:edit(${X_OBJECT.id})">${X_OBJECT.asnNo}</a>
			</c:if>
			<c:if test="${X_OBJECT.status eq 1  }">
				${X_OBJECT.asnNo}
			</c:if>
		</c:if>
		<c:if test="${X_OBJECT.handStatus ne 1  }">
			${X_OBJECT.asnNo}
		</c:if>
		
	</td>
	<%--<td>
		${X_OBJECT.models}
	</td>
	--%><td>
		${X_OBJECT.productlinecode}
	</td>
	<td>
		${X_OBJECT.shiftcode}
	</td>
	<td>
		${X_OBJECT.staffcode}
	</td>
	<td>
		${X_OBJECT.number}
	</td>
	<td>
		${X_OBJECT.taskDate}
	</td>
	<td>
		${X_OBJECT.time}
	</td>
	<td>
		${X_OBJECT.qty}
	</td>
	<td>
		${X_OBJECT.syncTime}
	</td>
	<td>
		<c:if test="${X_OBJECT.status=='0'}">未分解</c:if>
		<c:if test="${X_OBJECT.status=='1'}">已分解</c:if>
		<c:if test="${X_OBJECT.status=='2'}">失败</c:if>
	</td>
	<%--<td>
		<input type="button" value="删除" onclick="deleteHand('${X_OBJECT.id}')">
	</td>
--%></tr>

