<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<tr id="a${X_OBJECT.part.id}" align="center">
	<td nowrap="nowrap" rowspan="3">${X_OBJECT.part.id }</td>
	<td nowrap="nowrap" rowspan="3">${X_OBJECT.part.describe1 }</td>
	<td nowrap="nowrap" rowspan="3"><fmt:formatNumber>${X_OBJECT.currentQty }</fmt:formatNumber> </td>
	<td nowrap="nowrap" rowspan="3"><fmt:formatNumber>${X_OBJECT.part.highQty }</fmt:formatNumber></td>
	<td nowrap="nowrap" rowspan="3"><fmt:formatNumber>${X_OBJECT.part.lowQty }</fmt:formatNumber></td>
	<td nowrap="nowrap" rowspan="3"><fmt:formatNumber>${X_OBJECT.part.securityQty }</fmt:formatNumber></td>
	<td nowrap="nowrap">需求数量</td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.neddPastQty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no0Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no1Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no2Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no3Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no4Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no5Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no6Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no7Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no8Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no9Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no10Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no11Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no12Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no13Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no14Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no15Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no16Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no17Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no18Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no19Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no20Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no21Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no22Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no23Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no24Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no25Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no26Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no27Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no28Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no29Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no30Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no31Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no32Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no33Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no34Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no35Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no36Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no37Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no38Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no39Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no40Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no41Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no42Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no43Needqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no44Needqty }</fmt:formatNumber></td>
</tr>
<tr id="b${X_OBJECT.part.id}" align="center">
	<td nowrap="nowrap">未结发运单量</td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.transitPastQty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no0Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no1Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no2Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no3Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no4Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no5Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no6Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no7Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no8Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no9Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no10Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no11Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no12Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no13Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no14Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no15Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no16Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no17Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no18Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no19Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no20Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no21Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no22Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no23Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no24Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no25Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no26Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no27Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no28Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no29Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no30Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no31Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no32Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no33Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no34Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no35Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no36Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no37Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no38Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no39Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no40Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no41Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no42Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no43Knotqty }</fmt:formatNumber></td>
	<td nowrap="nowrap"><fmt:formatNumber>${X_OBJECT.no44Knotqty }</fmt:formatNumber></td>
	
</tr>
<tr id="c${X_OBJECT.part.id}" align="center">
	<td nowrap="nowrap">预计库存</td>
	<td nowrap="nowrap" ><fmt:formatNumber>${X_OBJECT.pastQty }</fmt:formatNumber></td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected0Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected0Qty) && (X_OBJECT.projected0Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected0Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected0Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected1Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected1Qty) && (X_OBJECT.projected1Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected1Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected1Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected2Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected2Qty) && (X_OBJECT.projected2Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected2Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected2Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected3Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected3Qty) && (X_OBJECT.projected3Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected3Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected3Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected4Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected4Qty) && (X_OBJECT.projected4Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected4Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected4Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected5Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected5Qty) && (X_OBJECT.projected5Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected5Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected5Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected6Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected6Qty) && (X_OBJECT.projected6Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected6Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected6Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected7Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected7Qty) && (X_OBJECT.projected7Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected7Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected7Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected8Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected8Qty) && (X_OBJECT.projected8Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected8Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected8Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected9Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected9Qty) && (X_OBJECT.projected9Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected9Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected9Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected10Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected10Qty) && (X_OBJECT.projected10Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected10Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected10Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected11Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected11Qty) && (X_OBJECT.projected11Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected11Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected11Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected12Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected12Qty) && (X_OBJECT.projected12Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected12Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected12Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected13Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected13Qty) && (X_OBJECT.projected13Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected13Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected13Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected14Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected14Qty) && (X_OBJECT.projected14Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected14Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected14Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected15Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected15Qty) && (X_OBJECT.projected15Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected15Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected15Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected16Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected16Qty) && (X_OBJECT.projected16Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected16Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected16Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected17Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected17Qty) && (X_OBJECT.projected17Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected17Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected17Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected18Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected18Qty) && (X_OBJECT.projected18Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected18Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected18Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected19Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected19Qty) && (X_OBJECT.projected19Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected19Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected19Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected20Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected20Qty) && (X_OBJECT.projected20Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected20Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected20Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected21Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected21Qty) && (X_OBJECT.projected21Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected21Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected21Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected22Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected22Qty) && (X_OBJECT.projected22Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected22Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected22Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected23Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected23Qty) && (X_OBJECT.projected23Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected23Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected23Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
			<c:if test="${X_OBJECT.projected24Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
			<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected24Qty) && (X_OBJECT.projected24Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
			<c:if test="${X_OBJECT.projected24Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
			<fmt:formatNumber>${X_OBJECT.projected24Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected25Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected25Qty) && (X_OBJECT.projected25Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected25Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected25Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected26Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected26Qty) && (X_OBJECT.projected26Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected26Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected26Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected27Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected27Qty) && (X_OBJECT.projected27Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected27Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected27Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected28Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected28Qty) && (X_OBJECT.projected28Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected28Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected28Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected29Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected29Qty) && (X_OBJECT.projected29Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected29Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected29Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected30Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected30Qty) && (X_OBJECT.projected30Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected30Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected30Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected31Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected31Qty) && (X_OBJECT.projected31Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected31Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected31Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected32Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected32Qty) && (X_OBJECT.projected32Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected32Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected32Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected33Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected33Qty) && (X_OBJECT.projected33Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected33Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected33Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected34Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected34Qty) && (X_OBJECT.projected34Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected34Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected34Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected35Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected35Qty) && (X_OBJECT.projected35Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected35Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected35Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected36Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected36Qty) && (X_OBJECT.projected36Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected36Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected36Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected37Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected37Qty) && (X_OBJECT.projected37Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected37Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected37Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected38Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected38Qty) && (X_OBJECT.projected38Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected38Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected38Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected39Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected39Qty) && (X_OBJECT.projected39Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected39Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected39Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected40Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected40Qty) && (X_OBJECT.projected40Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected40Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected40Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected41Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected41Qty) && (X_OBJECT.projected41Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected41Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected41Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected42Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected42Qty) && (X_OBJECT.projected42Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected42Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected42Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected43Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected43Qty) && (X_OBJECT.projected43Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected43Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected43Qty }</fmt:formatNumber>
	</td>
	<td nowrap="nowrap" 
		<c:if test="${X_OBJECT.projected44Qty < X_OBJECT.part.lowQty}"> bgcolor="red"</c:if>
		<c:if test="${(X_OBJECT.part.lowQty < X_OBJECT.projected44Qty) && (X_OBJECT.projected44Qty < X_OBJECT.part.securityQty)}"> bgcolor="yellow"</c:if>
		<c:if test="${X_OBJECT.projected44Qty > X_OBJECT.part.highQty}"> bgcolor="#E9967A"</c:if>>
		<fmt:formatNumber>${X_OBJECT.projected44Qty }</fmt:formatNumber>
	</td>
</tr>

