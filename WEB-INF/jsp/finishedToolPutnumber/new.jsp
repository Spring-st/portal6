<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>

<html:form action="/insertfinishedToolPutnumber.do" >
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
		<td class="bluetext">
			<h4 style="color: blue;" >新增成品条码与赛赫条码对应关系</h4>
		</tr>
		<tr>
			<td>ID:</td>
			<td>系统自动生成</td>
		</tr>
		<tr>
			<td class="bluetext">器具编码:</td>
			<td><html:text property="toolCode" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext">海纳川条码:</td>
			<td><html:text property="finishedCode" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext">可盛放数量:</td>
			<td><html:text property="putNumber" /><span class="required">*</span></td>
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
