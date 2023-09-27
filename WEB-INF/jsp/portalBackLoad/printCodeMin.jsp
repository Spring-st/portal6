<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<object id="factory" style="display: none"
	classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
	codebase="${path}/includes/smsx/smsx.cab#Version=6,5,439,12">
</object>
<style>
v\:* {
	behavior: url(#default#VML);
}

o\:* {
	behavior: url(#default#VML);
}

x\:* {
	behavior: url(#default#VML);
}

.shape {
	behavior: url(#default#VML);
}

</style>

<%
	Date date = new Date();
	request.setAttribute("date", date);
%>
<script language="JavaScript" src="includes/jquery-1.3.2.js">
</script>
<script language="javascript">
function window.onload()    {  
		factory.printing.header = "";  //页尾        
		factory.printing.footer = "";  //页首        
		factory.printing.portrait = true;   //portrait是指打印方向，设置为true就是纵向，false就是横向。         
		factory.printing.leftMargin = 0.0;         
		factory.printing.topMargin = 0.0;         
		factory.printing.rightMargin = 0.0;        
		factory.printing.bottomMargin = 0.0;   
		
		//factory.DoPrint(true); //设置为false，直接打印     }    
	}
  	function printTure() //打印函数 
	{ 
	  //	document.all("viewpanel").style.display="none";//隐藏按钮 
		document.all("viewpane2").style.display="none";//隐藏按钮 
		factory.printing.Print(false); //调用控件打印 
		//document.all("viewpanel").style.display="";//显示
		document.all("viewpane2").style.display="";//隐藏按钮
		var ids="<c:forEach items="${X_RESULTLIST}" var="po" >${po.id};</c:forEach>";
		//var url="updatePirntCodePurchaseOrderItem.do?ids="+ids;
		$.ajax({         
                type:"POST", //请求方式        
                url:"updatePirntCodePurchaseOrderItem.do", //请求路径        
                cache: false,           
              	data:{"ids":ids},//传参        
                dataType: 'json',   //返回值类型        
                async:false,
                success:function(json){
              		//if(json.object==true){
              		//	window.location.href="viewPortalShipOrderTwo.do?id="+json.id;
              		//}
               }
			    
            });
	}
	
	function Print(frame) { 
		factory.printing.Print(true, frame) // print with prompt 
		
	}
	function printView(){
		document.all("viewpane2").style.display="none";//隐藏按钮 
		
		factory.printing.Preview();
		document.all("viewpane2").style.display="";//隐藏按钮 
		var ids="<c:forEach items="${X_RESULTLIST}" var="po" >${po.id};</c:forEach>";
		//var url="updatePirntCodePurchaseOrderItem.do?ids="+ids;
		//window.location=url;
		$.ajax({         
                type:"POST", //请求方式        
                url:"updatePirntCodePurchaseOrderItem.do", //请求路径        
                cache: false,           
              	data:{"ids":ids},//传参        
                dataType: 'json',   //返回值类型        
                async:false,
                success:function(json){
              		//if(json.object==true){
              		//	window.location.href="viewPortalShipOrderTwo.do?id="+json.id;
              		//}
               }
			    
            });
	}
	 
	function partBarCodes(){
		var partBarCodes=$(".partBarCode");
		for(var i = 0; i < partBarCodes.length; i++){
			if(partBarCodes[i].style.display=='none'){
				partBarCodes[i].style.display="";
			}else{
				partBarCodes[i].style.display="none";
			}
		}
	}
	
	function  addState(){
	   alert("状态已修改!");
	}
</script>
<style type="text/css" id="style1">
td{border:solid black; border-width:0px 1px 1px 0px;}
table{border:solid black; border-width:1px 0px 0px 1px;}
.tab {
	width: 310px;
	height: 215px;
	border: solid #FFFFFF 2px; <%--边框 白色   #FFFFFF --%> 
	padding: 0px;
	margin-top:3px;
}

 .tab td {
	font-size: 10px;padding-left: 0px;
}
.tabspan{
	font-size: 18px;
}
.transparent_class { 
/* http://www.sharejs.com/codes/ */ 
filter:alpha(opacity=50); 
-moz-opacity:0.5; 
-khtml-opacity: 0.5; 
opacity: 0.5; 
} 
</style>

<div id="viewpane2" align="center">
	<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}">
		<input name="bequery" type="button" value="打印" style="cursor: hand;"
			onclick="printTure();" />
	</c:if>
	<c:if test="${sessionScope.LOGIN_USER.locale=='en'}">
		<input name="bequery" type="button" value="Print"
			style="cursor: hand;" onclick="printTure();">
	</c:if>
	<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}">
		<input name="bequery" type="button" value="打印预览" style="cursor: hand;"
			onclick="printView();" />
	</c:if>
	<c:if test="${sessionScope.LOGIN_USER.locale=='en'}">
		<input name="bequery" type="button" value="Print preview"
			style="cursor: hand;" onclick="printView();" />
	</c:if>
	<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}">
		<input name="bequery" type="button" value="返回"
			onclick="history.go(-1)" />
	</c:if>
	<c:if test="${sessionScope.LOGIN_USER.locale=='en'}">
		<input name="bequery" type="button" value="Return"
			onclick="history.go(-1);" />
	</c:if>
	<input name="bequery" type="button" value="刷新"
		onclick="location=location" />
</div>
<c:forEach var="box" items="${X_RESULTLIST}" varStatus="status">
<br/>
	<%--<table width="321px" height="170px" align="center"  border="0" cellspacing="0" cellpadding="0" style="background-image: url('images/TLC.png');">
		<tr height="21%" >
			<td width="21%"><img width="80" height="44"  src='images/Lear_log_proc.jpg'></td>
			<td width="62%" colspan="2" align="center" style="font-weight: bold;">北京李尔现代坦迪斯汽车系统有限公司<br/>部品识别表</td>
			<td width="17%"><img width="68" height="44"  src='images/Dymos_log_proc.jpg'></td>
		</tr>
		<tr height="10%" align="center">
			<td width="21%"	 rowspan="2">零件号</td>
			<td width="45%"  rowspan="2" style="font-weight: bold; font-size: 15px;">${box.part.id}</td>
			<td width="17%">车型</td>
			<td width="17%" style="font-weight: bold;">TLC</td>
		</tr>
		<tr height="11%" align="center">
			<td width="17%">数量</td>
			<td width="17%" style="font-weight: bold;"><fmt:formatNumber>${box.number}</fmt:formatNumber>${box.part.unit}</td>
		</tr>
		<tr height="14%" align="center">
			<td width="21%">零件名称</td>
			<td width="45%" colspan="3" style="font-weight: bold;">${box.part.name}</td>
		</tr>
		<tr height="14%" align="center">
			<td width="21%">供应商</td>
			<td width="45%" style="font-weight: bold;" >${box.po_supp_name}</td>
			<td width="17%">批次号</td>
			<td width="17%">&nbsp;</td>
		</tr>
		<tr height="11%" align="center">
			<td width="21%">打印日期</td>
			<td width="45%" >${X_DATE}</td>
			<td width="17%">校验</td>
			<td width="17%">&nbsp;</td>
		</tr>
		<tr height="17%">
			<td width="21%">条形码</td>
			<td width="45%" colspan="3"><img  style="width: 356px; height: 34px;" src='${path}/CreateBarCode?msg=${box.lot.id}&barType=Code93&checkCharacter=n&hrp=none&checkCharacterInText=n&height=9&width=160&moduleWidth=300'></td>
		</tr>
	</table>
	--%><!-- 
	<div  align="center" class="transparent_class"  style="margin-left:30%;margin-top:-15%;z-index: 2;position: absolute; width: 453px;height: 265px; padding-top: 0px ">
		<span style="font-size: 120px;font-weight: bold;color: red; " class="xy">TLC</span>
	</div> -->
	<table width="321px" height="170px" align="center"  border="0" cellspacing="0" cellpadding="0" >
		<tr height="21%" >
			<td width="12%">&nbsp;</td>
			<td width="62%" colspan="2" align="center" style="font-weight: bold;">北京李尔现代坦迪斯汽车系统有限公司<br/>部品识别表</td>
			<td width="17%">&nbsp;</td>
		</tr>
		<tr height="10%" align="center">
			<td width="12%"	 rowspan="2">零件号</td>
			<td width="45%"  rowspan="2" style="font-weight: bold; font-size: 15px;">${box.part.id}</td>
			<td width="12%">车型</td>
			<td width="17%" style="font-weight: bold;">${box.part.productSpecifications}</td>
		</tr>
		<tr height="11%" align="center">
			<td width="12%">数量</td>
			<td width="17%" style="font-weight: bold;"><fmt:formatNumber>${box.number}</fmt:formatNumber>${box.part.unit}</td>
		</tr>
		<tr height="14%" align="center">
			<td width="12%">零件名称</td>
			<td width="45%" colspan="3" style="font-weight: bold;">${box.part.name}</td>
		</tr>
		<tr height="14%" align="center">
			<td width="12%">供应商</td>
			<td width="45%" style="font-weight: bold;" >${box.po_supp_name}</td>
			<td width="12%">批次号</td>
			<td width="17%">&nbsp;</td>
		</tr>
		<tr height="11%" align="center">
			<td width="12%">打印日期</td>
			<td width="45%" >${X_DATE}</td>
			<td width="12%">检验</td>
			<td width="17%">&nbsp;</td>
		</tr>
		<tr height="17%" align="center">
			<%--<td width="12%">条形码</td>
			--%><td width="45%" colspan="4"><img  style="width: 360px; height: 23px;" src='${path}/CreateBarCode?msg=${box.lot.id}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=9&width=160&moduleWidth=300'></td>
		</tr>
		
	</table>
	<%--<div>
		<td width="45%" colspan="3"><img  style="width: 240px; height: 23px;" src='${path}/CreateBarCode?msg=${fn:split(box.lot.id,"/")[0]}/${fn:split(box.lot.id,"/")[2]}/${fn:split(box.lot.id,"/")[3]}&barType=Code93&checkCharacter=n&hrp=none&checkCharacterInText=n&height=9&width=160&moduleWidth=300'></td>
		</div>--%>
</c:forEach>