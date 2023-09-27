<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>

<html:form action="/updateBadReasons" >
	<html:hidden property="id" />
	<div style="font-size: 20px; color: blue; text-align: center; margin: 10px 0px; font-weight: bold;">不合格原因修改</div>
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext">不合格原因:</td>
			<td><html:textarea property="describe" cols="20" rows="3"/><span class="required">*</span></td>
		</tr>
		 <tr>
			<td class="bluetext">物料类型:</td>
			<td>
				<html:select property="type">
					<html:option value="">请选择</html:option>
			      	<html:options collection = "x_type" property = "enumCode" labelProperty = "chnShortDescription"/>
				</html:select>
			<span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext">备注:</td>
			<td><html:textarea property="remark" cols="20" rows="3" /></td>
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
