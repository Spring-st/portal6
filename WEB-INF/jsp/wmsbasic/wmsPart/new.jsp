<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script type="text/javascript">
function validateForm(form)
	{
		if(!validateWmsPartForm(form))
		{
			return false;
		}
		return true;
}
</script>
<html:javascript formName="wmsPartForm" staticJavascript="false" />
<html:form action="/insertWmsPart" onsubmit="return validateForm(this)" >
<div style="font-size: 23px; font-weight: bold; margin: 10px 10px; text-align: center; color: blue;">物料新增</div>
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
		 	<td class="bluetext">车型:</td>
			<td><html:text property="productSpecifications" /><span class="id">*</span></td>
			<td class="bluetext"><bean:message key="wmsPart.idPart" />:</td>
			<td><html:text property="id" /><span class="id">*</span></td>
		</tr>
		<tr>
			<td style="display: none;"><html:select property="site_id" >
			
			  </html:select>
			<span class="required">*</span></td>
			<td class="bluetext"><bean:message key="wmsPart.describe1" />:</td>
			<td><html:text property="describe1" /></td>
			<td class="bluetext"><bean:message key="wmsPart.describe2" />:</td>
			<td><html:text property="describe2" /></td>
		</tr>
		<tr>
			<td class="bluetext"> 类型</td>
			<td><html:text property="drwgLoc" /></td>
			<td class="bluetext"><bean:message key="wmsPart.vend" />:</td>
			<td><html:text property="vend" /></td>
		</tr>
		<tr>
		<td class="bluetext"><bean:message key="wmsPart.productClass" />:</td>
			<td><html:text property="productClass" /></td>
		<td class="bluetext"><bean:message key="wmsPart.ord_mult" />:</td>
			<td><html:text property="ord_mult" /><span class="required">*</span></td>
			<td style="display: none;" class="bluetext">物料名称:</td>
			<td style="display: none;"><html:text property="name" />
			<span class="required">*</span></td>	
		</tr>
		<tr>
		<td class="bluetext"><bean:message key="wmsPart.highQty" />:</td>
			<td><html:text property="highQty" onkeyup="this.value=(this.value.match(/\d+(\.\d{0,2})?/)||[''])[0]"/><span class="required">*</span></td>
			<td class="bluetext"><bean:message key="wmsPart.lowQty" />:</td>
			<td><html:text property="lowQty" onkeyup="this.value=(this.value.match(/\d+(\.\d{0,2})?/)||[''])[0]"/><span class="required">*</span></td>
		</tr>
		<tr>
		<td class="bluetext"><bean:message key="wmsPart.securityQty" />:</td>
			<td><html:text property="securityQty" onkeyup="this.value=(this.value.match(/\d+(\.\d{0,2})?/)||[''])[0]"/><span class="required">*</span></td>
			<td class="bluetext"><bean:message key="wmsPart.unit" />:</td>
			<td><html:text property="unit" /><span class="required">*</span></td>
		</tr>
		<tr>
		<td class="bluetext">是否被冻结:</td>
			<td><html:select property="freeze_status">
				<html:option value="">请选择</html:option>
				<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}">
					<html:options collection="X_YESNOLIST" property="enumCode" labelProperty="chnShortDescription" />
				</c:if>
				<c:if test="${sessionScope.LOGIN_USER.locale!='zh'}">
					<html:options collection="X_YESNOLIST"
						property="enumCode" labelProperty="engShortDescription" />
				</c:if>
			</html:select></td>
			<td class="bluetext"><bean:message key="wmsPart.enabled" />:</td>
			<td><html:select property="enabled">
				<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}">
					<html:options collection="X_ENABLEDDISABLEDLIST"
						property="enumCode" labelProperty="chnShortDescription" />
				</c:if>
				<c:if test="${sessionScope.LOGIN_USER.locale!='zh'}">
					<html:options collection="X_ENABLEDDISABLEDLIST"
						property="enumCode" labelProperty="engShortDescription" />
				</c:if>
			</html:select></td>
		</tr>
	</table>
	<hr />
	<div style="margin: 10px 10px;" align="center">
		<html:submit><bean:message key="all.save" /> </html:submit>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</html:form>
