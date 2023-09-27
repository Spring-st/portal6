<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>

    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
    <html><head><title></title>  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
	<style type="text/css" id="style1">
		.bottomSolid {border-bottom:solid #000000 1px;}
		.bottomDouble {border-bottom:double #000000 2px;}
		table{ font-size:8px}
	</style>
	<object id="factory" style="display:none" classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814" 
codebase="${path}/includes/smsx/smsx.cab#Version=6,5,439,12"> 
	</object>
	<style>
v\:* {behavior:url(#default#VML);}
o\:* {behavior:url(#default#VML);}
x\:* {behavior:url(#default#VML);}
.shape {behavior:url(#default#VML);}
</style>
<link rel=Stylesheet href=stylesheet.css>
<style>
th, td {
  padding: 0.2em 0.7em;
} 

<!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
@page
	{margin:.75in .7in .75in .7in;
	mso-header-margin:.3in;
	mso-footer-margin:.3in;
	mso-page-orientation:landscape;}
ruby
	{ruby-align:left;}
rt
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-char-type:none;
	display:none;}
	
.font0
	{color:black;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font7
	{color:black;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:"Lingoes Unicode";
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font9
	{color:black;
	font-size:12.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:"Lingoes Unicode";
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font13
	{color:black;
	font-size:12.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font17
	{color:black;
	font-size:12.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:"Lingoes Unicode";
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font18
	{color:black;
	font-size:24.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font19
	{color:black;
	font-size:24.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:"Lingoes Unicode";
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font20
	{color:black;
	font-size:34.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:"Lingoes Unicode";
	mso-generic-font-family:auto;
	mso-font-charset:134;}
-->
</style>
 
<%
Date date = new Date();
request.setAttribute("date", date);
%>
<script language="JavaScript" src="includes/jquery-1.3.2.js"></script>
<script language="javascript">  
	function window.onload()    {  
		factory.printing.header = "";  //页尾        
		factory.printing.footer = "";  //页首        
		factory.printing.portrait = false;   //portrait是指打印方向，设置为true就是纵向，false就是横向。         
		factory.printing.leftMargin = 0.0;         
		factory.printing.topMargin = 1.0;         
		factory.printing.rightMargin = 0.0;        
		factory.printing.bottomMargin = 0.0;   
		
		//factory.DoPrint(true); //设置为false，直接打印     }    
	}
  	function printTure() //打印函数 
	{ 
		document.all("viewpanel").style.display="none";//隐藏按钮 
		document.all("viewpane2").style.display="none";//隐藏按钮 
		factory.printing.Print(false); //调用控件打印 
		document.all("viewpanel").style.display="";//显示
		document.all("viewpane2").style.display="";//隐藏按钮
	}
	
	function Print(frame) { 
		factory.printing.Print(true, frame) // print with prompt 
		
	}
	function printView(){
		document.all("viewpane2").style.display="none";//隐藏按钮 
		
		factory.printing.Preview();
		document.all("viewpane2").style.display="";//隐藏按钮 
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
</script> 
<style>
table tr td{font-size: 20px;  text-align: center; }
.table{
	border: 1px , solid ,;
	background-color: #ffffff;
}
.bluetext{font-size: ${x_fontSize}px; }
.price{font-weight: bold; color: #FF0000;}
</style>
</head>  
<body topmargin="0px" leftmargin="0px" rightmargin="0px" bottommargin="0px">

<div id="viewpane2" align="center"> 
    <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input  name="bequery" type="button" value="打印" style="cursor:hand;" onclick="printTure();"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"> <input name="bequery" type="button" value="Print" style="cursor:hand;" onclick="printTure();"></c:if>
     <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input  name="bequery" type="button" value="打印预览" style="cursor:hand;" onclick="printView();"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input  name="bequery" type="button" value="Print preview" style="cursor:hand;" onclick="printView();"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input  name="bequery" type="button" value="返回" onclick="history.go(-1)"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input  name="bequery" type="button" value="Return" onclick="history.go(-1);"/></c:if>
    <input  name="bequery" type="button" value="刷新" onclick="location=location"/>
</div> 
 
<bean:define id="woStrs" toScope="request" value="" />
	<c:forEach items="${x_listMap}" var="box">
		<bean:define id="woStrs" toScope="request" value="${woStrs}${box.id};" />
	</c:forEach>
<input type="hidden" id="lots" value="${woStrs}"/>
<center id="printContent"  style="margin: 0px 0px 20px 20px;">
<c:choose>
<c:when test="${fn:length(x_listMap) == 1}">
<c:forEach var="box" items="${x_listMap}" varStatus="status">
<div style="margin: 27px 10px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
<table border="1" style="border-collapse:collapse;" <c:if test="${false==status.last}"> class="pages"</c:if>>
  	  <tr>
    	<td>
    		<img width=182 height=88 src=images/image002.gif >
    	</td>
    	<td colspan="3">
    	<font class="font13">收货方  Goods recipient<span style='mso-spacerun:yes'>&nbsp; </span></font>
    	<font class="font0"><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><br>
    </font><font class="font18">B H A P</font>
    	</td>
    </tr>
    <tr>
    	<td class=xl72 width=282 style='width:212pt'><font class="font17">订单编号<br>Order Number </font><font class="font7"><br>
    </font><font class="font19">${box.po_number}</font></td>
  <td class=xl80 width=232 style='border-left:none;width:174pt'>
  <font class="font17">供应商编号 <br>Supplier Number<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></font>
  <font class="font7">
  <span style='mso-spacerun:yes'></span></font>
  <font class="font19">${box.po_supp}</font></td>
  <td class=xl93 width=226 style='width:170pt'>
  	<font class="font17">供应商名称<br>
    <span style='mso-spacerun:yes'>&nbsp;</span>Supplier Name
    <span style='mso-spacerun:yes'>&nbsp;&nbsp; </span></font><font class="font9">
    <span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></font><br/>
    <font  class="font19">
    <c:if test="${box.single==null}">${box.psoItem.poipItem.poip_number.supplier.name}</c:if>
    <c:if test="${box.single!=null}">${box.single.po_detial_id.poip_number.supplier.name}</c:if> 
    </font></td>
    <td class=xl73 width=291 style='width:218pt;'><font class="font17">发运单日期</font><br>
   <font class="font17">Arrival Date</font>
   <span style='mso-spacerun:yes'>
   </span><br>
    <font class="font19"><fmt:formatDate value="${box.po_date}" pattern="yyyy/MM/dd" /></font></td>
    </tr>
    <tr>
    	<td class="xl83 partBarCode2" colspan=2 width=230 style='width:100pt;height=104' >
	<table border="0">
  	<tr>
  		<td valign="middle"><font class="font17"><nobr>
    		<font class="font17" >零件编号  Material Number<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span></font>
  			<font class="font7"><span style='mso-spacerun:yes'></span></font></nobr></font>
  <br/>
  <nobr><font class="font20">${box.part.id}</font></nobr>
</td>
<td class="xl83 partBarCode" colspan=1 width=230 style='width:150pt;' style="display: none;">
	<img width=230 src='${path}/CreateBarCode?msg=${box.lot.id}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=14&width=200&moduleWidth=200&hrp=none'>&nbsp;
</td>
  	</tr>
  </table>
    </td>
 	 <td colspan=2 class=xl80 width=517 style='width:388pt'><font class="font17">零件名称 Material Description
  	 <span style='mso-spacerun:yes'>&nbsp;&nbsp; </span></font>
 	 <font  class="font7"><span style='mso-spacerun:yes'></span></font><br/>
  	 <font class="font19">&nbsp;${box.part.name}<span style='mso-spacerun:yes'>&nbsp; </span></font>
  	 <font class="font9"><span style='mso-spacerun:yes'></span></font></td>
	</tr>
    <tr>
    	<td rowspan="2" colspan="3">
    		<font class="font17">HU编号 Handling Unit Number
    		<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span></font><br/><br/>
  				<img width=530  src='${path}/CreateBarCode?msg=${box.lot.id}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=9&width=500&moduleWidth=500'>&nbsp;
    	</td>
  			<td class=xl73 width=291 style='width:218pt;height: 70px;'><font class="font17">&nbsp;数量 Quantity</font>
  			<span style='mso-spacerun:yes'>
  </span><br>
    <span style='mso-spacerun:yes'>&nbsp;</span><font class="font19">&nbsp;${box.number}</font></td>
    </tr>
    <tr>
    	 <td class=xl73 width=291 style='border-left:none;width:218pt;height: 70px;' valign="top">
    	 <font class="font9">&nbsp;ZGS/Q-Level</font><br/><font class="font17">
    	 <span style='mso-spacerun:yes'> </span></font><font class="font7">
    	 <span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></font></td>
    </tr>
    <tr>
     <td ><font class="font17">质量状态</font></td>
  	 <td colspan=2 class=xl88 width=748 style='border-right:.5pt solid black;border-left:none;width:561pt' align="center">
  	 <font class="font17">合格</font>
  	 <span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
  	 |<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	 </span><font class="font17">不合格</font></td>
  <td>
  <table border="0">
  	<tr>
  		<td valign="middle"><font class="font17"><div class="bl_show">备料</div><br/>确认</font></td>
  		<td><img width=134 height=104 src=images/image007.gif >
  		</td>
  	</tr>
  </table>
  </td>
    </tr>
    </table>
 
</c:forEach>
</c:when>
  <c:otherwise>
	<c:if test="${sizePage==null}">
  	<bean:define id="sizePage" toScope="request" value="4" />
  </c:if>
<table>
<c:forEach var="box" items="${x_listMap}" varStatus="status">
<c:if test="${status.index==0}">
<div style="margin: 5px 10px;">&nbsp;</div>
</c:if>
<!-- 分页 ${status.index}-->
 <!-- ${status.index%sizePage} |${status.index}%${sizePage}-->
		<c:if test="${status.index%(sizePage)==0&&(status.index)!=0}">
			<!-- 占位符${fn:length(x_listMap)} -->
			</table>
			<p style="page-break-after: always; "></p>
			
<c:if test="${status.index%(sizePage)==0&&status.index!=0}">
<div style="margin: 5px 10px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
</c:if>
<table align="center">
		</c:if>
		<c:if test="${(status.index+1)%2!=0}">
			<tr>
				<td height="23%">
		</c:if>
		<c:if test="${(status.index+1)%2==0&&(status.index+1)!=0}">
				<td height="23%">
		</c:if>
		<table border="1" style="border-collapse:collapse;" <c:if test="${false==status.last}"> class="pages"</c:if>>
  	  <tr>
    	<td>
    		<img width=182 height=88 src=images/image002.gif >
    	</td>
    	<td colspan="3">
    	<font class="font13">收货方  Goods recipient<span style='mso-spacerun:yes'>&nbsp; </span></font>
    	<font class="font0"><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><br>
    </font><font class="font18">B H A P</font>
    	</td>
    </tr>
    <tr>
    	<td class=xl72 width=282 style='width:212pt'><font class="font17">订单编号<br>Order Number </font><font class="font7"><br>
    </font><font class="font19">${box.po_number}</font></td>
  <td class=xl80 width=232 style='border-left:none;width:174pt'>
  <font class="font17">供应商编号 <br>Supplier Number<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></font>
  <font class="font7">
  <span style='mso-spacerun:yes'></span></font>
  <font class="font19">${box.po_supp}</font></td>
  <td class=xl93 width=226 style='width:170pt'>
  	<font class="font17">供应商名称<br>
    <span style='mso-spacerun:yes'>&nbsp;</span>Supplier Name
    <span style='mso-spacerun:yes'>&nbsp;&nbsp; </span></font><font class="font9">
    <span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></font><br/>
    <font  class="font19">
    <c:if test="${box.single==null}">${box.psoItem.poipItem.poip_number.supplier.name}</c:if>
    <c:if test="${box.single!=null}">${box.single.po_detial_id.poip_number.supplier.name}</c:if> 
    </font></td>
    <td class=xl73 width=291 style='width:218pt;'><font class="font17">发运单日期</font><br>
   <font class="font17">Arrival Date</font>
   <span style='mso-spacerun:yes'>
   </span><br>
    <font class="font19"><fmt:formatDate value="${box.po_date}" pattern="yyyy/MM/dd" /></font></td>
    </tr>
    <tr>
    	<td class="xl83 partBarCode2" colspan=2 width=230 style='width:100pt;height=104' >
	<table border="0">
  	<tr>
  		<td valign="middle"><font class="font17"><nobr>
    		<font class="font17" >零件编号  Material Number<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span></font>
  			<font class="font7"><span style='mso-spacerun:yes'></span></font></nobr></font>
  <br/>
  <nobr><font class="font20">${box.part.id}</font></nobr>
</td>
<td class="xl83 partBarCode" colspan=1 width=230 style='width:150pt;' style="display: none;">
	<img width=230 src='${path}/CreateBarCode?msg=${box.lot.id}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=14&width=200&moduleWidth=200&hrp=none'>&nbsp;
</td>
  	</tr>
  </table>
    </td>
 	 <td colspan=2 class=xl80 width=517 style='width:388pt'><font class="font17">零件名称 Material Description
  	 <span style='mso-spacerun:yes'>&nbsp;&nbsp; </span></font>
 	 <font  class="font7"><span style='mso-spacerun:yes'></span></font><br/>
  	 <font class="font19">&nbsp;${box.part.name}<span style='mso-spacerun:yes'>&nbsp; </span></font>
  	 <font class="font9"><span style='mso-spacerun:yes'></span></font></td>
	</tr>
    <tr>
    	<td rowspan="2" colspan="3">
    		<font class="font17">HU编号 Handling Unit Number
    		<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span></font><br/><br/>
  				<img width=530  src='${path}/CreateBarCode?msg=${box.lot.id}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=9&width=500&moduleWidth=500'>&nbsp;
    	</td>
  			<td class=xl73 width=291 style='width:218pt;height: 70px;'><font class="font17">&nbsp;数量 Quantity</font>
  			<span style='mso-spacerun:yes'>
  </span><br>
    <span style='mso-spacerun:yes'>&nbsp;</span><font class="font19">&nbsp;${box.number}</font></td>
    </tr>
    <tr>
    	 <td class=xl73 width=291 style='border-left:none;width:218pt;height: 70px;' valign="top">
    	 <font class="font9">&nbsp;ZGS/Q-Level</font><br/><font class="font17">
    	 <span style='mso-spacerun:yes'> </span></font><font class="font7">
    	 <span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></font></td>
    </tr>
    <tr>
     <td ><font class="font17">质量状态</font></td>
  	 <td colspan=2 class=xl88 width=748 style='border-right:.5pt solid black;border-left:none;width:561pt' align="center">
  	 <font class="font17">合格</font>
  	 <span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
  	 |<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	 </span><font class="font17">不合格</font></td>
  <td>
  <table border="0">
  	<tr>
  		<td valign="middle"><font class="font17"><div class="bl_show">备料</div><br/>确认</font></td>
  		<td><img width=134 height=104 src=images/image007.gif >
  		</td>
  	</tr>
  </table>
  </td>
    </tr>
    </table>
		<div style="margin: 0px 0px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
		<c:if test="${(status.index+1)%2!=0}">
	</td>
		</c:if>
		<c:if test="${(status.index+1)%2==0&&(status.index+1)!=0}">
	</td>
	</tr>
	</c:if>
	</c:forEach>
</table>
 		</c:otherwise>
	</c:choose>
</center>
 
</body>  
<script type="text/javascript">
	document.body.style.zoom = 0.85; 
</script>  
</html> 
  