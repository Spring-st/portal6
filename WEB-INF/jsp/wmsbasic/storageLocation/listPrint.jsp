<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<script language="javascript" src="includes/table.js"></script>
<script language="javascript" src="includes/basicJS.js"></script>
<script language="javascript" src="includes/jquery-1.3.2.js"></script>
<style type="text/css" >
.bottomSolid {border-bottom:solid #000000 1px;}
.bottomDouble {border-bottom:double #000000 2px;}
table{ font-size:8px}
</style>
<object id="factory" style="display:none" classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814" codebase="${path}/includes/smsx/smsx.cab#Version=6,5,439,12"> 
</object>
<script type="text/javascript">
<!--
  function window.onload()    {  
		factory.printing.header = "";  //页尾        
		factory.printing.footer = "";  //页首        
		factory.printing.portrait = true;   //portrait是指打印方向，设置为true就是纵向，false就是横向。         
		factory.printing.leftMargin = 1.0;         
		factory.printing.topMargin = 1.0;         
		factory.printing.rightMargin = 1.0;        
		factory.printing.bottomMargin = 1.0;        
		//factory.DoPrint(true); //设置为false，直接打印     }    
	}
	function printTure(){  //打印函数 
		document.all("viewpanel").style.display="none";//隐藏按钮 
		factory.printing.Print(false); //调用控件打印 
		document.all("viewpanel").style.display="";//显示 
	} 
	function Print(frame) { 
		factory.printing.Print(true, frame) // print with prompt 
	}
	function printView(){
		document.all("viewpanel").style.display="none";//隐藏按钮 
		factory.printing.Preview();
		document.all("viewpanel").style.display="";//显示 
	}
	
//-->
</script>
<body>
<div align="center">
<table border=0>
<tr>
	<td valign="top">
		<c:forEach items="${X_RESULTLIST}" var="X_OBJECT" varStatus="status">
		<c:if test="${status.index!=0}"><p style="page-break-after: always; "></p></c:if> 
<table align="center" width="350px" height="150px" valign="top">
	<tr>
		<td align="center">
<span style='font-size:70px'>${X_OBJECT.code}</br>
					<img width=360 src='${path}/CreateBarCode?msg=${X_OBJECT.code}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=9&width=210&moduleWidth=220&hrp=none'>
</span></td>
	</tr>
</table>
		</c:forEach>
	</td>
</tr>
</table>
</div>		
	 <div id="viewpanel" align="center"> 
     <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input name="bequery" type="button" value="打印" style="cursor:hand;" onclick="printTure();"/></c:if>
     <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input name="bequery" type="button" value="Print" style="cursor:hand;" onclick="printTure();"></c:if>
     <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input name="bequery" type="button" value="打印预览" style="cursor:hand;" onclick="printView();"/></c:if>
     <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input name="bequery" type="button" value="Print preview" style="cursor:hand;" onclick="printView();"/></c:if>
     <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input name="bequery" type="button" value="返回"  onclick="history.go(-1)"/></c:if>
     <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input name="bequery" type="button" value="Return"  onclick="history.go(-1)"/></c:if>
     </div> 
</body>
<script type="text/javascript">
document.body.style.zoom = 1.5;
</script>  