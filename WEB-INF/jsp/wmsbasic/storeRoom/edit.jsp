<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>

<html:form action="/updateStoreRoom.do" >
	<html:hidden property="id" />
	<html:hidden property="site_id"/>
	
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext">库房编码:</td>
			<td><html:text property="code" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext">地址:</td>
			<td><html:text property="address" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext">类型:</td>
			<td><html:select property="type" >
				  <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><html:options collection = "x_STOREROOMTYPELIST" property = "enumCode" labelProperty = "engShortDescription"/></c:if>
	      <c:if test="${sessionScope.LOGIN_USER.locale!='en'}"><html:options collection = "x_STOREROOMTYPELIST" property = "enumCode" labelProperty = "chnShortDescription"/></c:if>
			</html:select>
			<span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext">名称:</td>
			<td><html:text property="name" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext">安全库存:</td>
			<td><html:text property="largest_inventory" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext">状态:</td>
			<td><html:select property="status">
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
	<div align="center">
			<html:submit><bean:message key="all.save" /></html:submit>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</html:form>
