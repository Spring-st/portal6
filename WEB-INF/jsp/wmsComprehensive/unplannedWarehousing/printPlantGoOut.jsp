<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/page.tld" prefix="page"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="includes/jquery-1.3.2.js"></script>
<style type="text/css" id="style1">
		.bottomSolid {border-bottom:solid #000000 1px;}
		.bottomDouble {border-bottom:double #000000 2px;}
		table{ font-size:8px}
</style>
<object id="factory" style="display:none" classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814" codebase="${path}/includes/smsx/smsx.cab#Version=6,5,439,12"> 
</object>
<script> 
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
  	function printTure() //打印函数 
	{ 
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
	 
</script>
<body>
<center id="printContent">
<input type="hidden" id="haschosen"/>
<html:hidden property="wmsGoOut_id" value="${x_toGoOut.id}"/>
 <table width="100%" style="border: 1px solid;" border="0">
   		<tr>
   			<td colspan="3"> </td>
   		</tr>
   		<tr>
   			<td colspan="7" align="center"><span style="font-size: 24px;"><strong>非计划入库单</strong></span></td>
   		</tr>
   		<tr>
  	 		<td colspan="7" align="center"><img width=320 src='/wmsv5_km/CreateBarCode?msg=${x_toGoOut.code}&barType=CODE128&checkCharacter=n&checkCharacterInText=n&height=9&width=500&moduleWidth=500'></td>
  	 	</tr>
  	 	<tr>
   		<td>&nbsp;</td>
   		<td colspan="2" class="bluetext" style="font-size: 15px;">申请日期：&nbsp;${x_toGoOut.date}</td>
   		<td colspan="2" class="bluetext" style="font-size: 15px;">申请人：&nbsp;${x_toGoOut.applicant.name}</td>
   		<td colspan="2" align="left" class="bluetext" style="font-size: 15px;">非计划入库日期：&nbsp;<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%></td>
   		</tr>
   		<%--<tr>
   		<td>&nbsp;</td>
   		<td class="bluetext" colspan="6" style="font-size: 15px;">非计划入库类别：（非计划入库类别为"试作品入库时"，需提交到品管科）</td>
   		</tr>
   		<tr>
   		<td>&nbsp;</td>
   		<td class="bluetext" > <input type="checkbox" name="checkbox" value="checkbox"  />生产中产品品质异常 </td>
   		<td class="bluetext" ><input type="checkbox" name="checkbox" value="checkbox"  />产品试做</td>
   		<td class="bluetext" ><input type="checkbox" name="checkbox" value="checkbox"  />工艺改进</td>
   		<td class="bluetext" ><input type="checkbox" name="checkbox" value="checkbox"  />设备调用</td>
   		<td class="bluetext" ><input type="checkbox" name="checkbox" value="checkbox" />样品</td>
   		</tr>
   		<tr>
   		<td>&nbsp;</td>
   		<td colspan="1" class="bluetext">费用部门：&nbsp;</td>
   		<td>${x_toGoOut.reason_code.department_cost}</td>
   		<td colspan="1" class="bluetext">费用科目：&nbsp;</td>
   		<td>${x_toGoOut.reason_code.expenses_course}</td>
   		</tr>
   		<tr>
   		<td>&nbsp;</td>
   		<td style="font-size: 15px;" colspan="4" class="bluetext">联络部门：&nbsp;</td>
   		</tr>
   		<tr>
   		<td>&nbsp;</td>
   		<td class="bluetext" ><input type="checkbox" name="checkbox" value="checkbox"  />制造科 </td>
   		<td class="bluetext"><input type="checkbox" name="checkbox" value="checkbox"  />设施科</td>
   		<td class="bluetext"><input type="checkbox" name="checkbox" value="checkbox"  />制造技术科</td>
   		<td class="bluetext"><input type="checkbox" name="checkbox" value="checkbox"  />开发技术科</td>
   		<td class="bluetext"><input type="checkbox" name="checkbox" value="checkbox"  />品管</td>
   		<td class="bluetext"><input type="checkbox" name="checkbox" value="checkbox"  />营业科</td>
   		<td class="bluetext"><input type="checkbox" name="checkbox" value="checkbox"  />总务人事科</td>
   		<td class="bluetext"><input type="checkbox" name="checkbox" value="checkbox"  />财务科</td>
   		</tr>
   	--%></table>
   	
	<table class="data">
	       <tr align="center">
	        <td style="font-size: 15px;" colspan="9">非计划入库申请明细</td>
	       </tr>
   	       <tr bgcolor="#9999ff" align="center">
   	          <td class="bluetext">序号</td>
   	          <td class="bluetext">物料号</td>
   	          <td class="bluetext">描述一</td>
   	          <td class="bluetext">描述二</td>
   	          <td class="bluetext">条码编号</td>
   	          <td class="bluetext">库位</td>
   	          <td class="bluetext">数量</td>
   	          <td class="bluetext">单位</td>
   	       </tr>
   	       <tbody id="item_datatable">
   	        <c:forEach var="X_OBJECT" items="${x_items}" varStatus="status">
   	          <tr align="center">
   	            <td>${status.index+1}</td>
   	            <td>${X_OBJECT.box_id.part.id}</td>
   	            <td>${X_OBJECT.box_id.part.describe1}</td>
   	            <td>${X_OBJECT.box_id.part.describe2}</td>
   	            <td>${X_OBJECT.box_id.lot.id}</td>
   	            <td>${X_OBJECT.box_id.location.code}</td>
   	            <td>${X_OBJECT.box_id.number}</td>
   	            <td>${X_OBJECT.box_id.part.unit}</td>
   	          </tr>
   	        </c:forEach>
   	       </tbody>
   	</table>
   
   	<table width="100%" style="border: 1px solid;" border="0">
   	<tr>
   	  <td class="bluetext" colspan="3" >非计划入库主要原因：</td>
   	</tr>
   	<tr><td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${x_toGoOut.remark}</td></tr>
   	<tr><td colspan="3">&nbsp;</td></tr>
   	<tr>
   	  <td width="50%">&nbsp;</td>
   	  <td class="bluetext" >担当：</td>
   	  <td class="bluetext" >主管（科长/部门）：</td>
   	</tr>
   	<tr>
   	  <td class="bluetext" colspan="3" >备注：</td>
   	</tr>
   	<tr><td colspan="3">&nbsp;</td></tr>
   	<tr><td colspan="3">&nbsp;</td></tr>
   	<tr>
   	  <td width="50%">&nbsp;</td>
   	  <td class="bluetext" > &nbsp;</td>
   	  <td class="bluetext" >&nbsp; </td>
   	</tr>
   	</table>
	</center>
    <div id="viewpanel" align="center"><br/>
    <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input  name="bequery" type="button" value="打印 " style="cursor:hand;" onclick="printTure();"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"> <input name="bequery" type="button" value="Print" style="cursor:hand;" onclick="printTure();"></c:if>
     <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input  name="bequery" type="button" value="打印预览" style="cursor:hand;" onclick="printView();"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input  name="bequery" type="button" value="Print preview" style="cursor:hand;" onclick="printView();"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='zh'}"><input  name="bequery" type="button" value="返回" onclick="history.go(-1);"/></c:if>
    <c:if test="${sessionScope.LOGIN_USER.locale=='en'}"><input  name="bequery" type="button" value="Return" onclick="history.go(-1);"/></c:if>
    </div>  
</body> 

