<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script src="includes/jquery-1.3.2.js"></script>
<script type="text/javascript">
 function selectProductGoline() {
		v = window.showModalDialog(
			'showDialog.do?title=assembly2 expense.selectTA.title&selectProductGoline.do', 
			null, 'dialogWidth:1024px;dialogHeight:580px;status:no;help:no;scroll:no');
		if (v) {
			document.getElementById("desc").innerHTML=v['hncdesc'];
			document.getElementById("descText").value=v['hncdesc'];
			document.getElementById("code").value=v['code'];
			document.getElementById("codeSpan").innerHTML=v['code'];
			document.getElementById("num").value=v['qty'];//出库数量
			document.getElementById("num1").value=v['qty'];
		};
	}
 function valudateForm(){
	 var qty=document.getElementById("num").value;
	 var qty1=document.getElementById("num1").value;
	 if(parseInt(qty) > parseInt(qty1)){
		 alert("当前出库数量必须小于库存量！");
		 return;
	 }
	 document.productOutStorageForm.submit();
 }
</script>
<html:form action="/insertProductOutStorage">
	<div style="font-size: 23px; font-weight: bold; margin: 10px 10px; text-align: center; color: blue;">新增成品出库</div>
	<table width=90% border=0 cellpadding=4 cellspacing=0>
		<tr>
			<td></td>
			<td></td>
			<td align="center">ID:</td>
			<td>(<bean:message key="common.id.generateBySystem" />)</td>
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="product.finishedCode" />:</td>
			<td><input type="hidden" id="code" name="hncCode"/>
				<input type="hidden" id="descText" name="description" />
				<span id="codeSpan"></span>
				<a href="javascript:selectProductGoline();"><img src="images/select.gif" border="0"/></a>
			</td>
			
			<td class="bluetext"><bean:message key="product.description" />:</td>
			<td id="desc"></td>
		</tr>
		<tr>
			<td class="bluetext">出库库位:</td>
  			<td><html:select property="storeroom_id" >
  				<c:forEach var="X_STOR" items="${X_STOREROOMLIST}">
					<option value="${X_STOR.id}">${X_STOR.code} ${X_STOR.name}</option>
				</c:forEach>
  				<%--<html:options collection="X_STOREROOMLIST" property="id" labelProperty="code"/>--%>
  			</html:select></td>
			
		</tr>
		<tr>
			<td class="bluetext"><bean:message key="product.outqty" />:</td>
			<td><input type="text" name="qty" id="num" /><span class="required">*</span>
			<input type="hidden" id="num1" />
			</td>
		</tr>
	</table>
	<div style="margin: 10px 10px;" align="center">
		<input type="button" value='<bean:message key="all.save" />' onclick="valudateForm();" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="关闭" onclick="window.close();"/>
	</div>
</html:form>
