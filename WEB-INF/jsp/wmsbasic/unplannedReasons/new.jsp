<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>

<html:form action="/insertUnplannedReasons.do" >
<div style="font-size: 20px; color: blue; text-align: center; margin: 10px 0px; font-weight: bold;">非计划原因代码新增</div>
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext">原因代码:</td>
			<td><html:text property="instructions" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext">描述:</td>
			<td><html:text property="describe" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext">费用科目:</td>
			<td><html:text property="expenses_course" /><span class="expenses_course">*</span></td>
		</tr>
		<tr>
			<td class="bluetext">费用部门:</td>
			<td><html:text property="department_cost" /><span class="department_cost">*</span></td>
		</tr>
	</table>
	<hr />
	<div align="center">
			<html:submit><bean:message key="all.save" /></html:submit>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</html:form>
