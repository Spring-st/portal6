<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>

<html:form action="/updateStorageLocation" >
	<html:hidden property="id" />
	<div style="font-size: 23px; font-weight: bold; margin: 10px 10px; text-align: center; color: blue;">库位修改</div>
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext"><bean:message key="storageLocation.id" />:</td>
			<td>(<bean:message key="common.id.generateBySystem" />)</td>
			<td class="bluetext"><bean:message key="storageLocation.site" />:</td>
			<td><html:select property="site_id" >
			  <html:options collection="X_SITEID" labelProperty="name" property="id"/>
			  </html:select><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="storageLocation.describe" />:</td>
			<td><html:text property="description" /><span class="required">*</span></td>
			<td class="bluetext"><bean:message key="storageLocation.code" />:</td>
			<td><html:text property="code" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="storageLocation.address" />:</td>
			<td><html:text property="address" /><span class="required">*</span></td>
			<td class="bluetext"><bean:message key="storageLocation.max_inventory" />:</td>
			<td><html:text property="max_inventory" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="storageLocation.storeroom_id" />:</td>
			<td><html:select property="basic_storeroom_id_id" >
				<html:options collection="X_STOREROOMLIST" labelProperty="code" property="id"/>
			</html:select>
			<span class="required">*</span></td>
			<td class="bluetext"><bean:message key="storageLocation.enabled" />:</td>
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
		<tr>
			<td class="bluetext">是否被冻结:</td>
			<td><html:select property="freeae_status">
			<html:option value="">请选择</html:option>
				<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}">
					<html:options collection="X_YESNOLIST"
						property="enumCode" labelProperty="chnShortDescription" />
				</c:if>
				<c:if test="${sessionScope.LOGIN_USER.locale!='zh'}">
					<html:options collection="X_YESNOLIST"
						property="enumCode" labelProperty="engShortDescription" />
				</c:if>
			</html:select></td>
			<td class="bluetext">是否参与推荐:</td>
			<td>
			<html:select property="recommend_status" >
				<html:option value="">请选择</html:option>
				<html:option value="0">参与推荐</html:option>
				<html:option value="1">不参与推荐</html:option>
			</html:select>
			</td>
		</tr>
		<tr>
			<td class="bluetext">先进先出状态:</td>
			<td>
			<html:select property="f_in_f_out_status" >
				<html:option value="">请选择</html:option>
				<html:option value="0">强制限定</html:option>
				<html:option value="1">不限定</html:option>
			</html:select>
			</td>
		</tr>
	</table>
	<hr />
	<div style="margin: 10px 10px;" align="center">
		<html:submit><bean:message key="all.save" /> </html:submit>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</html:form>
