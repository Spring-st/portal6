<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">
 function devanning(){
		window.location.href = "listDevanningBox.do";			
	}
 function together(){
		window.location.href = "listDevanningBox.do";			
	}
 function exchange(){
		window.location.href = "pirntPurchaseOrderRQCItem.do";			
	}
 function back(){
		
		window.location.href="listPurchaseOrderBox.do"; 
    }
</script>
<html:form action="/updatePurchaseOrderBox" onsubmit="return validatePurchaseOrderRQCForm(this)">
	<html:hidden property="id" />
	
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
 		 <td width="15%" class="bluetext"><bean:message key="purchaseOrderBox.number"/>:</td>
 		 <td width="35%">${x_purchaseOrderBox.number}</td>
		 <td width="15%"  class="bluetext"><bean:message key="purchaseOrderBox.lotSer"/>:</td>
 		 <td width="35%">${x_purchaseOrderBox.lotSer.id}</td>
		</tr>
		<tr>
		<td class="bluetext"><bean:message key="purchaseOrderBox.count" />:</td>
			<td>${x_purchaseOrderBox.count}</td>
			<td class="bluetext"><bean:message key="purchaseOrderBox.location" />:</td>
			<td>${x_purchaseOrderBox.location.address}</td>
		</tr>
		
	</table>
	<hr width="100%"/>

	<div align="right">
<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="拆箱"  onclick="devanning();"/></c:if>
<c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="Devanning Box"  onclick="devanning();"/></c:if>
<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="拼箱"  onclick="together();"/></c:if>
<c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="Knock Together Box"  onclick="together();"/></c:if>
<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="换箱"  onclick="exchange();"/></c:if>
<c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="Exchange Box"  onclick="exchange();"/></c:if>
<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input type="button" value="返回"  onclick="back();"/></c:if>
<c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input type="button" value="Return"  onclick="back();"/></c:if>
</div>
</html:form>
