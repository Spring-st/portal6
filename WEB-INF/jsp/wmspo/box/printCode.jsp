<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
	<object id="factory" style="display:none" classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814" 
codebase="${path}/includes/smsx/smsx.cab#Version=6,5,439,12"> 
	</object>
	<style>
v\:* {behavior:url(#default#VML);}
o\:* {behavior:url(#default#VML);}
x\:* {behavior:url(#default#VML);}
.shape {behavior:url(#default#VML);}
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
		factory.printing.portrait = true;   //portrait是指打印方向，设置为true就是纵向，false就是横向。         
		factory.printing.leftMargin = 0.0;         
		factory.printing.topMargin = 1.0;         
		factory.printing.rightMargin = 0.0;        
		factory.printing.bottomMargin = 0.0;   
		
		//factory.DoPrint(true); //设置为false，直接打印     }    
	}
  	function printTure() //打印函数 
	{ 
  		var array=document.getElementById("lots").value;
  		 $.ajax({         
                type:"POST", //请求方式        
                url:"updateBoxIsPrint.do", //请求路径        
                cache: false,           
                data : "ids=" + array,  //传参        
                dataType: 'json',   //返回值类型        
                success:function(date){
                	document.all("viewpane2").style.display="none";//隐藏按钮 
					factory.printing.Print(false); //调用控件打印 
					document.all("viewpane2").style.display="";//显示按钮         
                }
               })
	}
	
	function Print(frame) { 
		factory.printing.Print(true, frame) // print with prompt 
		
	}
	function printView(){
		var array=document.getElementById("lots").value;
  		 $.ajax({         
                type:"POST", //请求方式        
                url:"updateBoxIsPrint.do", //请求路径        
                cache: false,           
                data : "ids=" + array,  //传参        
                dataType: 'json',   //返回值类型        
                success:function(date){
					//document.all("buhi").style.display="none";//隐藏按钮 
					document.all("viewpane2").style.display="none";//隐藏按钮 
					factory.printing.Preview();//调用控件打印 
					document.all("viewpane2").style.display="";//显示按钮         
                }
               })
		
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
	function backToList()
	{
		var url="listPurchaseOrderLotPrint.do";
		window.location.href=url;
	}
</script> 
<style type="text/css" id="style1">

.tab {
	width: 310px;
	height: 215px;
	border: solid  #FFFFFF 2px; <%--边框 白色   #FFFFFF --%> 
	padding: 0px;
	margin-top:3px;
}

 .tab td {
	font-size: 13px;padding-left: 0px;
}

</style>

<body topmargin="0px" leftmargin="0px" rightmargin="0px" bottommargin="0px">

<div id="viewpane2" align="center"> 
	<span id="buhi">
    <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input  name="bequery" type="button" value="打印" style="cursor:hand;" onclick="printTure();"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"> <input name="bequery" type="button" value="Print" style="cursor:hand;" onclick="printTure();"></c:if>
     <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input  name="bequery" type="button" value="打印预览" style="cursor:hand;" onclick="printView();"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input  name="bequery" type="button" value="Print preview" style="cursor:hand;" onclick="printView();"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input  name="bequery" type="button" value="Return" onclick="history.go(-1);"/></c:if>
    <input  name="bequery" type="button" value="刷新" onclick="location=location"/>
    </span>
	<c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input  name="bequery" type="button" value="返回" onclick="backToList()"/></c:if>
</div> 

	
<bean:define id="woStrs" toScope="request" value="" />
	<c:forEach items="${x_listMap}" var="box">
		<bean:define id="woStrs" toScope="request" value="${woStrs}${box.id};" />
	</c:forEach>
<input type="hidden" id="lots" value="${woStrs}"/> 
<c:forEach var="box" items="${x_listMap}" varStatus="status">
	<table class="tab" align="center" border="0" cellspacing="0" cellpadding="0" >
		<tr>
			<td style="line-height:40px;" align="right">
				<font style="font-family: Arial; font-weight: bold; font-size: 22px;">${box.part.id}</font>
			</td>
		</tr>
		<tr height="40px;">
			<td style="border-top: 2px solid #000;padding-left: 8px;" >
				<table>
					<tr>
						<td style="font-family: Arial; padding-left: 0px; font-size: 13px;">适用车型:</td>
						<td style="font-family: Arial; font-size: 13px;padding-left: 0px;">
							${box.part.carBrand1}&nbsp;${box.part.domesticCar1}&nbsp;
							<c:if test="${box.part.yearFrom1 ne null && box.part.yearFrom1 ne ''}">'</c:if>${box.part.yearFrom1}<c:if test="${(box.part.yearFrom1 ne null && box.part.yearFrom1 ne '') or (box.part.yearTo1 ne null && box.part.yearTo1 ne '')}">-'</c:if>${box.part.yearTo1}
						</td>
					</tr>
					<tr>
						<td></td>
						<td style="font-family: Arial; font-size: 13px;padding-left: 0px;">
							${box.part.carBrand2}&nbsp;${box.part.domesticCar2}&nbsp;
							<c:if test="${box.part.yearFrom2 ne null && box.part.yearFrom2 ne ''}">'</c:if>${box.part.yearFrom2}<c:if test="${(box.part.yearFrom2 ne null && box.part.yearFrom2 ne '') or (box.part.yearTo2 ne null && box.part.yearTo2 ne '')}">-'</c:if>${box.part.yearTo2}
						</td>
					</tr>
					<%--<tr>
						<td></td>
						<td style="font-family: Arial; font-size: 13px;padding-left: 0px;">
							${box.part.carBrand3}&nbsp;${box.part.domesticCar3}&nbsp;
							<c:if test="${box.part.yearFrom3 ne null && box.part.yearFrom3 ne ''}">'</c:if>${box.part.yearFrom3}<c:if test="${(box.part.yearFrom3 ne null && box.part.yearFrom3 ne '') or (box.part.yearTo3 ne null && box.part.yearTo3 ne '')}">-'</c:if>${box.part.yearTo3}
						</td>
					</tr>
				--%></table>
			</td>
		</tr>
		
		<tr height="12px">
			<td style="font-family: Arial; padding-left: 10px; font-size: 13px;">
				<%--${box.part.describe1}&nbsp;${box.part.describe2}&nbsp;${box.part.vend}--%>
				${box.part.describe1}&nbsp;${box.part.vend}
			</td>
		</tr>
		<tr height="10px">
			<td style="font-family: Arial; padding-left: 10px; font-size: 13px; word-wrap:break-word; word-break:break-all;">
				${box.part.productSpecifications}
			</td>
		</tr>
		<tr>
			<td>
				<table border="0px;">
					<tr>
						<td  style="padding-left: 0px; padding-top: 2px;font-size: 13px; padding-bottom: 0px;">
							<img style="width: 220px; height: 55px;"
								src='${path}/CreateBarCode?msg=${box.lot.id}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=9&width=500&moduleWidth=500'>
						</td>
						<td valign="bottom" style="padding-bottom: 5px;font-size: 13px;">
							<fmt:formatNumber pattern="###" value="${box.number}" var="number" />QTY:${number}PC	
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</c:forEach>		