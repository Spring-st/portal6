<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js">
</script>
<script language="javascript" src="includes/jquery-1.3.2.js">
</script>
<style>
<!--
.hjd {
	height: 35px;
	width: 100%;
	border: solid #3c78b5 1px;
	font: bold 18px;
	line-height: 35px;
	text-align: right;
	padding-right: 30px;
}
-->
</style>
<script type="text/javascript">
<!--
	//添加成品信息
	function doInsert(){	
		var shCode = document.getElementById("shCode").value;
		if(shCode.length<21){
			window.setTimeout("doInsert()",450);
			return;
		}
		window.location.href="insertProductGoline.do?shCode=" + shCode;
	}
	//文本框内容修改时触发，0.5秒之后运行doInsert()方法
	function doCheck(shCode){
		if(shCode.value.length==1){//当文本框输入第一个字符时，触发定时器，0.5秒之后进入新增方法
			window.setTimeout("doInsert()",550);
			shCode.onpropertychange=null;//清除文本框改变时触发的事件
		}
	}
	//进入页面时，鼠标焦点聚焦到文本框中
	window.onload = function(){
		<%--
		var message = document.getElementById("errorMessage").innerHTML;
		if(message==""){
		}
		--%>
		document.getElementById("shCode").focus();
		window.setTimeout("startInterval()",600000);
	}
	//防止页面连接超时，每10分钟与服务器同步
	function startInterval(){
		window.setInterval("doRefresh()",600000);
	}
	function doRefresh(){
		$.ajax({
			type : "POST", //请求方式        
			url : "connectSys.do", //请求路径        
			dataType : 'json', //返回值类型        
			success : function(data) {
				document.getElementById("reTime").innerHTML = data['refreshTime'];
			}
		});	
	}
	//点击标题栏复选框时
	function idsCheck(ids){
		var idArray = document.getElementsByName("cid");
		var hjdn = 0;
		var qty;
		if(ids.checked){
			for(var i=0;i<idArray.length;i++){
				idArray[i].checked=true;
				qty = document.getElementById('qty'+idArray[i].value).value;
				hjdn += parseInt(qty);
			}
		}else{
			for(var i=0;i<idArray.length;i++){
				idArray[i].checked=false;
			}
		}	
		document.getElementById('hjdn').innerHTML = hjdn;
	}
	//点击列表中复选框时
	function cidCheck(cid){
		var hjdn = document.getElementById('hjdn');
		var qty = document.getElementById('qty'+cid.value).value;
		if(cid.checked){
			hjdn.innerHTML = parseInt(hjdn.innerHTML)+parseInt(qty);
		}else{
			hjdn.innerHTML = parseInt(hjdn.innerHTML)-parseInt(qty);
		}
	}
	//扣料
	function doReduction(bu){
		var idArray = document.getElementsByName("cid");
		var count = 0;
		for(var i=0;i<idArray.length;i++){
			if(idArray[i].checked){
				count = 1;
				break;
			}
		}
		if(count==1){
			document.reductionForm.submit();
			bu.disabled = true;
		}else{
			alert("请至少选择一项！");
		}
	}
	function qadCheck(){
		window.location.href="productGolineQAD.do";
	}
	function qadQueryCheck(){
		window.location.href="productGolineNotQAD.do";
	}
	
//-->
</script>
<div id="reTime" style="display: none;"></div>
<div class="message" id="errorMessage">
	<html:messages id="x_message" message="true">
		${x_message}
	</html:messages>
</div>
<table width=100% border=0 cellpadding=4 cellspacing=0>
	<tr>
		<td width="100px" align="left">
			<bean:message key="productGoline.shCode" />
			:
		</td>
		<td align="left">
			<input type="text" id="shCode" size="40" onpropertychange="doCheck(this)" />
		</td>
		<td>
			<%--<input type="button" value="同步赛赫条码" onclick="qadCheck()"/>
		--%>
		<input type="button" value="<bean:message key="qad.productGoline.shCode" />" onclick="qadCheck()"/>
		</td>
		<td>
			<input type="button" value="<bean:message key="all.query.QAD" />" onclick="qadQueryCheck()"/>
		</td>
	</tr>
</table>
<br />
<form name="reductionForm" action="productGolineMaterialReduction.do"
	method="post">
	<table class="data">
		<thead>
			<tr bgcolor="#9999ff">
				<th width="2%">
					<input type="checkbox" id="ids" onclick="idsCheck(this)" />
				</th>
				<th width="10%">
					<bean:message key="productGoline.totalNumber" />
				</th>
				<th width="10%">
					<bean:message key="productGoline.hncCode" />
				</th>
				<th width="10%">
					<bean:message key="productGoline.hubCode" />
				</th>
				<th width="10%">
					<bean:message key="productGoline.tireCode" />
				</th>
				<th width="10%">
					<bean:message key="productGoline.valvestemCode" />
				</th>
				<th width="10%">
					<bean:message key="productGoline.qty" />
				</th>
				<%--<th width="5%">
					<bean:message key="productGoline.operation" />
				</th>
			--%></tr>
		</thead>

		<tbody id="datatable">
			<logic:iterate id="X_OBJECT" name="X_RESULTLIST">
				<bean:define id="X_OBJECT" toScope="request" name="X_OBJECT" />
				<jsp:include page="row.jsp" />
			</logic:iterate>
		</tbody>
	</table>
	<div class="hjd">
		<bean:message key="productGoline.sum" />
		：
		<span id="hjdn" style="color: red;">0</span>
	</div>
	<div style="text-align: right; padding: 10px 30px;">
		<input type="button"
			value="<bean:message key="productGoline.material.reduction" />"
			onclick="doReduction(this)" />
	</div>
</form>
<script type="text/javascript">
applyRowStyle(document.all('datatable'));
</script>

