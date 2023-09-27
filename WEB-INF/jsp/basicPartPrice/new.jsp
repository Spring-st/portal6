<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
function selectWmsPart() {
		v = window.showModalDialog(
			'showDialog.do?title=expense.selectTA.title&selectWmsPart.do', 
			null, 'dialogWidth:1000px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) { 
			document.getElementById("name").innerHTML=v['id'];
			document.getElementById("partId").value=v['id'];
		};
	}
function selectBasicCustomer() {
		v = window.showModalDialog(
			'showDialog.do?title=expense.selectTA.title&selectBasicCustomer.do', 
			null, 'dialogWidth:1000px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			document.getElementById("code").innerHTML=v['code'];
			document.getElementById("customer").value=v['code'];
		};
	}
function validateForm(form)
	{
		if(!validateBasicPartPriceForm(form))
		{
			return false;
		}
		return true;
	}
</script>
  <html:javascript formName="basicPartPriceForm" staticJavascript="false" />
<html:form action="/insertBasicPartPriceMaintenanceAction" onsubmit="return validateForm(this)">
	<table width=100% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td class="bluetext"><bean:message key="basic_customer.id" />:</td>
			<td>(<bean:message key="common.id.generateBySystem" />)</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="basicpartprice.customer" />:</td>
			<td><span id="code"></span>
				<html:hidden property="customer" styleId="customer"/><span class="required">*</span>
  				<a href="javascript:selectBasicCustomer();"><img src="images/select.gif" border="0"/></a>
  			</td>
			<td class="bluetext"><bean:message key="basicpartprice.partId" />:</td>
			<td><span id="name"></span><html:hidden property="partId" styleId="partId"/><span class="required">*</span>
  				<a href="javascript:selectWmsPart();"><img src="images/select.gif" border="0"/></a></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="basicpartprice.sotaxc" />:</td>
			<td><html:text property="sotaxc"/></td>
			<td class="bluetext"><bean:message key="basicpartprice.solist" />:</td>
			<td><html:text property="solist"/></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="basicpartprice.pcDesc" />:</td>
			<td><html:text property="pcDesc"/></td>
			<td class="bluetext"><bean:message key="basicpartprice.curr" />:</td>
			<td><html:text property="curr"/></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="basicpartprice.pcUm" />:</td>
			<td><html:text property="pcUm"/></td>
			<td class="bluetext"><bean:message key="basicpartprice.amtType" />:</td>
			<td><html:text property="amtType"/></td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="basicpartprice.startDate" />:</td>
			<td>
				<html:text property="startDate" readonly="true" size="15" />
				<a onclick="event.cancelBubble=true;" href="javascript:showCalendar('dimg1',false,'startDate',null,null,'basicPartPriceForm')"> 
				<img align="absmiddle" border="0" id="dimg1" src="images/datebtn.gif" /> </a>
			</td>
			<td class="bluetext"><bean:message key="basicpartprice.expireDate" />:</td>
			<td>
				<html:text property="expireDate" readonly="true" size="15" />
				<a onclick="event.cancelBubble=true;" href="javascript:showCalendar('dimg2',false,'expireDate',null,null,'basicPartPriceForm')"> 
				<img align="absmiddle" border="0" id="dimg2" src="images/datebtn.gif" /> </a>
			</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="basicpartprice.amt" />:</td>
			<td><html:text property="amt" maxlength="18" onkeyup="this.value=(this.value.match(/\d+(\.\d{0,5})?/)||[''])[0]" /></td>
			<td class="bluetext"><bean:message key="basicpartprice.rmks" />:</td>
			<td><html:text property="rmks"/></td>
		</tr>
		<tr>
			<td colspan="4" align="right" style="border-top: 1px solid black;">
				<html:submit><bean:message key="all.save" /></html:submit>
			</td>
		</tr>
	</table>
</html:form>