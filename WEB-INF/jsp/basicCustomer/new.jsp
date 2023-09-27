<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
<!--
  function AJAXCode(){
	var code = document.getElementById("code").value;
	if(code==null||code==""){
		alert("客户编码不能为空");
	}else{
		$.ajax({
			url:"AJAXCode.do",
			cache : false,
			type : "post",
			data : {"code":code},
			dataType : "json",
			success:function(result){
				if(result!=0){
					alert("客户编码已经存在");
				}else if(result==0){
					basicCustomerForm.submit();
				}
			}
		});
	}
  }
//-->
</script>
  <html:javascript formName="basicCustomerForm" staticJavascript="false" />
<html:form action="/insertBasicCustomer" >
	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext"><bean:message key="basic_customer.id" />:</td>
			<td>(<bean:message key="common.id.generateBySystem" />)</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="basic_customer.code" />:</td>
			<td><html:text property="code"/></td>
			<td class="bluetext"><bean:message key="basic_customer.name1" />:</td>
			<td><html:text property="name1"/></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="basic_customer.name2" />:</td>
			<td><html:text property="name2"/></td>
			<td class="bluetext"><bean:message key="basic_customer.address" />:</td>
			<td><html:text property="address"/></td>
		</tr>
		<tr>
			<td class="bluetext">地址2:</td>
			<td><html:text property="address2"/></td>
			<td class="bluetext">地址3:</td>
			<td><html:text property="address3"/></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="basic_customer.contacts" />:</td>
			<td><html:text property="contacts"/></td>
			<td class="bluetext"><bean:message key="basic_customer.phone" />:</td>
			<td><html:text property="phone"/></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="basic_customer.email" />:</td>
			<td><html:text property="email"/></td>
			<td class="bluetext"><bean:message key="basic_customer.product" />:</td>
			<td><html:text property="product"/></td>
		</tr>
		
		<tr>
			<td class="bluetext">国家:</td>
			<td><html:text property="country"/></td>
			<td class="bluetext">城市:</td>
			<td><html:text property="city"/></td>
		</tr>
		<tr>
			<td class="bluetext">传真:</td>
			<td><html:text property="fax"/></td>
			<td class="bluetext">邮编:</td>
			<td><html:text property="postId"/></td>
		</tr>
		<tr>
			<td class="bluetext">货币:</td>
			<td><html:text property="currencyType"/></td>
			<td class="bluetext">工厂:</td>
			<td><html:text property="site"/></td>
		</tr>
		<tr>
			<td class="bluetext">域:</td>
			<td><html:text property="domain"/></td>
			<td class="bluetext">流水号:</td>
			<td><html:text property="seqNo"/></td>
		</tr>
		<tr>
			<td class="bluetext">站点:</td>
			 <td><html:select property="siteId_id">
			 <html:options collection="X_SITELIST" property="id" labelProperty="name"/></html:select></td>
			<td class="bluetext"><bean:message key="basic_customer.enabled" />:</td>
			<td><html:select property="enabled">
				<c:if test="${x_lang eq 'chn'}">
					<html:options collection="X_ENABLEDDISABLEDLIST" property="enumCode" labelProperty="chnShortDescription" />
				</c:if>
				<c:if test="${x_lang eq 'eng'}">
					<html:options collection="X_ENABLEDDISABLEDLIST" property="enumCode" labelProperty="engShortDescription" />
				</c:if>
			</html:select></td>
		</tr>
		<tr>
			<td class="bluetext" ><bean:message key="basic_customer.remarks" />:</td>
			<td><html:textarea property="remarks" cols="25" rows="3"></html:textarea></td>
		</tr>
		<tr>
			<td colspan="4" align="right" style="border-top: 1px solid black;">
				<input type="button" onclick="AJAXCode();" value="保存"/>
			</td>
		</tr>
	</table>
</html:form>