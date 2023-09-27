<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>

<html:form action="/insertExpensesCourse" >
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext"><bean:message key="expensesCourse.id" />:</td>
			<td>(<bean:message key="common.id.generateBySystem" />)</td>
		</tr>
		<tr>
			<td class="bluetext">账户:</td>
			<td><html:text property="code" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext">费用科目名称:</td>
			<td><html:text property="description" /><span class="required">*</span></td>
		</tr>
		<tr>
			<td class="bluetext">类型:</td>
			<td><html:select property="type">
			 		<html:option value="A">A</html:option>
			 		<html:option value="E">E</html:option>
			 		<html:option value="I">I</html:option>
			 		<html:option value="L">L</html:option>
                </html:select>
			</td>
		</tr>
		<tr>
			<td class="bluetext">货币:</td>
			<td>
			  <html:select property="currency" >
			     <html:option value="CNY">CNY</html:option>
			     <html:option value="JPY">JPY</html:option>
			     <html:option value="USD">USD</html:option>
			  </html:select>
			</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="expensesCourse.enabled" />:</td>
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
			<td colspan="2" align="right"><html:submit>
				<bean:message key="all.save" />
			</html:submit></td>
		</tr>
	</table>
</html:form>
