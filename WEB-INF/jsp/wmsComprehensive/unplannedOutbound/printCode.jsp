<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html><head><title></title>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<style type="text/css" id="style1">
.bottomSolid {border-bottom:solid #000000 1px;}
.bottomDouble {border-bottom:double #000000 2px;}
table{ font-size:8px}
</style>
<object id="factory" style="display:none" classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814" codebase="${path}/includes/smsx/smsx.cab#Version=6,5,439,12"> 
</object>
<script src="includes/jquery-1.3.2.js"></script>
<script language="javascript">  
	function window.onload(){
		var type = document.getElementById('type').value;
		factory.printing.header = "";  //页尾        
		factory.printing.footer = "";  //页首        
		factory.printing.portrait = type;   //portrait是指打印方向，设置为true就是纵向，false就是横向。         
		factory.printing.leftMargin = 0.0;         
		factory.printing.topMargin = 1.0;         
		factory.printing.rightMargin = 0.0;        
		factory.printing.bottomMargin = 1.0;        
		//factory.DoPrint(true); //设置为false，直接打印     }    
	}
	
  	function printTure() //打印函数 
	{ 
		document.all("viewpanel").style.display="none";//隐藏按钮 
		document.all("viewpane2").style.display="none";//隐藏按钮 
		factory.printing.Print(false); //调用控件打印 
		document.all("viewpanel").style.display="";//显示 
		document.all("viewpane2").style.display="";//显示 
		var str = "";
		<c:forEach var="box" items="${x_boxList}">
            str = str+${box.id}+",";		
		</c:forEach>
	    
		var url="updateWmsUW.do?id=${x_warehousing.id}"+"&str="+str.toString();
		window.location=url;
	} 
	
	function Print(frame) { 
		factory.printing.Print(true, frame) // print with prompt 
		
	}
	function printView(){
		document.all("viewpanel").style.display="none";//隐藏按钮 
		document.all("viewpane2").style.display="none";//隐藏按钮 
		factory.printing.Preview();
		document.all("viewpanel").style.display="";//显示 
		document.all("viewpane2").style.display="";//显示 
		
		var str = "";
		<c:forEach var="box" items="${x_boxList}">
            str = str+${box.id}+",";		
		</c:forEach>
	    
		var url="updateWmsUW.do?id=${x_warehousing.id}"+"&str="+str.toString();
		window.location=url;
	}
	function window.close(){
		window.location.href="listPurchaseOrderReceipts.do";
	}
</script>
</head>  
<body topmargin="0px" leftmargin="0px" rightmargin="0px" bottommargin="0px">  
<c:if test="${x_type=='1'}">
<input type="hidden" id="type" value="true"/>
<input type="hidden" id="number" value="1.6"/>
</c:if>
<c:if test="${x_type=='2'}">
<input type="hidden" id="type" value="false"/>
<input type="hidden" id="number" value="1.0"/>
</c:if>
 <div id="viewpane2" align="center"> 
    <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input  name="bequery" type="button" value="打印" style="cursor:hand;" onclick="printTure();"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"> <input name="bequery" type="button" value="Print" style="cursor:hand;" onclick="printTure();"></c:if>
     <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input  name="bequery" type="button" value="打印预览" style="cursor:hand;" onclick="printView();"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input  name="bequery" type="button" value="Print preview" style="cursor:hand;" onclick="printView();"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input  name="bequery" type="button" value="返回" onclick="history.go(-1)"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input  name="bequery" type="button" value="Return" onclick="history.go(-1);"/></c:if>
</div>

<c:forEach items="${x_boxList}" var="box" varStatus="status">
<c:if test="${status.index!=0}"><p style="page-break-after: always; "></p></c:if> 
<table width="350px" align="center" border="0" height="150" >
<tr><td>
<table width="350px" align="center" border="1" height="150" >

     	<tr height="30" valign="top">
    		<td align="center" style="font-size: 15;font-weight: bold;">
    		<bean:message key="box.id" />	    			
    		</td>
    		<td align="center" style="font-size: 15;font-weight: bold;">
    		${box.wmsUWItem.wmsPart}
    		</td>
    		<td align="center" style="font-size: 15;font-weight: bold;">
    		<bean:message key="part.count" />	
    		</td>
    		<td align="center" style="font-size: 15;font-weight: bold;">
    			<fmt:formatNumber value="${box.count}" maxFractionDigits="2" minFractionDigits="2"/>
    		</td>
    	</tr>
    	<c:if test="${box.wmsUWItem.blanket!=null&&box.wmsUWItem.blanket!=''}">
    	<tr height="30">
    	    <c:if test="${box.wmsUWItem.blanket!=null}">
    	    <td align="center" style="font-size: 15;font-weight: bold;">
    		<bean:message key="box.blanketMark" />	
    		</td>
    		<td align="center" style="font-size: 15;font-weight: bold;" colspan="3">
    		${box.wmsUWItem.blanket}
    		</td>
    	    </c:if>
    	</tr>
    	</c:if>
    	<tr height="30">
    		<td align="center" style="font-size: 15;font-weight: bold;">
    		<bean:message key="supplier.standard" />	    			
    		</td>
    		<td align="center" style="font-size: 15;font-weight: bold;" colspan="3">
    		${box.wmsUWItem.supplierStandard}
    		</td>
    	</tr>
    	<tr height="60">
    		<td align="center" colspan="4" >
    		<c:if test="${x_type=='1'}">
 				<img width=320 src='${path}/CreateBarCode?msg=${box.lotSer.id}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=12&width=500&moduleWidth=500'>
		    </c:if>
    		<c:if test="${x_type=='2'}">
 				<img width=320 src='${path}/CreateBarCode?msg=${box.lotSer.id}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=9&width=500&moduleWidth=500'>
		    </c:if>
    		</td>
    	</tr>
    	</table>
    	</td>
    	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    	</tr>
    	</table>
    	<br/>
    	</c:forEach>
    <div id="viewpanel" align="center"> 
    <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input  name="bequery" type="button" value="打印" style="cursor:hand;" onclick="printTure();"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"> <input name="bequery" type="button" value="Print" style="cursor:hand;" onclick="printTure();"></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input  name="bequery" type="button" value="打印预览" style="cursor:hand;" onclick="printView();"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input  name="bequery" type="button" value="Print preview" style="cursor:hand;" onclick="printView();"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input  name="bequery" type="button" value="返回" onclick="history.go(-1)"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input  name="bequery" type="button" value="Return" onclick="history.go(-1);"/></c:if>
    </div>  
</body>
<script type="text/javascript">
document.body.style.zoom = $('#number').val();
</script>
</html> 

