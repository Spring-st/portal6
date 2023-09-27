<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<div>
	<table width=100% border=1 cellpadding=4 cellspacing=0 style="margin: 0px 0px 10px 0px;">
		<tr align="right">
			<td class="bluetext">合计数量:&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${sumaCount}" maxFractionDigits="2" minFractionDigits="2"/></td>
		</tr>
	</table>
</div>
