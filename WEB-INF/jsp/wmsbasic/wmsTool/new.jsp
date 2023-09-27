<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>

<html:form action="/insertWmsTool">
	<table width=90% border=0 cellpadding=4 cellspacing=0>	
		<tr>
			<td class="bluetext"><bean:message key="workCenter.id" />:</td>
			<td>(<bean:message key="common.id.generateBySystem" />)</td>
		</tr>		
		<tr>
			<td class="bluetext"><bean:message key="wmsTool.code" />:</td>
			<td><html:text property="code" /><span class="required">*</span></td>
		</tr>	
		<tr>
			<td class="bluetext">包装量:</td>
			<td><html:text property="capacity" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext">日期:</td>
			<td><html:text property="date" size="6"/>
			<a onclick="event.cancelBubble=true;"
				href="javascript:showCalendar('dimg1',false,'date',null,null,'wmsToolForm')"><img
				align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /></a>
			<span class="required">*</span></td>
		</tr>	
		<tr>
		<td class="bluetext">状态:</td>
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
		<html:submit><bean:message key="all.save" /></html:submit>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</html:form>
