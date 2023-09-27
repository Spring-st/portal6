<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<link href="images/skin.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="includes/FusionChartsFree/FusionCharts.js"></script>
<script language="JavaScript" src="includes/jquery-1.3.2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
-->
</style>
<script type="text/javascript">
<!--
	function viewNews(id) {
		var title="RecentNews.view";
		var url="viewRecentNews.do?id="+id;
		var v=dialogAction(url,title,400,500);
		if (v) {
		};
	}
	function download(status){
		var url="download.do?status="+status;
		var title="download.info";
		dialogAction(url,title,400,500);
	}
//-->
</script><%--

<script type="text/javascript">
<!--
 $(document).ready(function() {
	 var time = new Date().getSeconds()
     $.ajax({
       type:"POST", 
       url:"poInData.do?time="+time,   
       success: function(responseText){
	       var chart = new FusionCharts("includes/FusionChartsFree/FCF_MSColumn3DLineDY.swf", "ChartId", "600", "350");
	       chart.setDataXML(responseText);
	       chart.render("poInData");
      }
     });
     $.ajax({
       type:"POST", 
       url:"poOutData.do?time="+time,   
       success: function(responseText){
	       var chart = new FusionCharts("includes/FusionChartsFree/FCF_MSColumn3DLineDY.swf", "ChartId", "600", "350");
	       chart.setDataXML(responseText);
	       chart.render("poOutData");
      }
     });
     $.ajax({
       type:"POST", 
       url:"produceOutData.do?time="+time,   
       success: function(responseText){
	       var chart = new FusionCharts("includes/FusionChartsFree/FCF_MSColumn3DLineDY.swf", "ChartId", "600", "350");
	       chart.setDataXML(responseText);
	       chart.render("produceOutData");
      }
     });
 });
//-->
</script>
--%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="images/mail_leftbg.gif"><img src="images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">欢迎界面</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="images/mail_rightbg.gif"><img src="images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
    <td valign="middle" background="images/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td colspan="2" valign="top">&nbsp;</td>
        <td>&nbsp;</td>
        <td valign="top">&nbsp;</td>
      </tr>
      <tr>
        <%--<td colspan="2" valign="top"><span class="left_bt">WIN-IN条码仓库管理系统</span><br>--%>
        <td colspan="2" valign="top"><span class="left_bt">供应商管理门户系统</span><br>
        <br>
            <span class="left_txt">&nbsp;<img src="images/ts.gif" width="16" height="16"> 提示：<br>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您已经登陆系统</span><span class="left_txt">！<br>
          <br>
		</span></td>
        <td width="7%">&nbsp;</td>
        <td width="40%" valign="top">
        <table width="100%" height="144" border="0" cellpadding="0" cellspacing="0" class="line_table">
          <tr>
            <td width="7%" height="27" background="images/news-title-bg.gif"><img src="images/news-title-bg.gif" width="2" height="27"></td>
            <td width="93%" background="images/news-title-bg.gif" class="left_bt2">最新动态</td>
          </tr>

        </table></td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2" valign="top"><!--JavaScript部分--><%--
              <SCRIPT language=javascript>
		function secBoard(n)
		{
			for(i=0;i<secTable.cells.length;i++)
			secTable.cells[i].className="sec1";
			secTable.cells[n].className="sec2";
			for(i=0;i<mainTable.tBodies.length;i++)
			mainTable.tBodies[i].style.display="none";
			mainTable.tBodies[n].style.display="block";
		}
          </SCRIPT>
              <!--HTML部分-->
              <TABLE width=95% border=0 cellPadding=0 cellSpacing=0 id=secTable>
                <TBODY>
                  <TR align=middle height=20>
                    <TD align="center" class=sec2 onclick=secBoard(0)>入库信息</TD>
                    <TD align="center" class=sec2 onclick=secBoard(1)>原材料出库信息</TD>
                    <TD align="center" class=sec2 onclick=secBoard(2)>成品出库信息</TD>
                  </TR>
                </TBODY>
              </TABLE>
          <TABLE class=main_tab id=mainTable cellSpacing=0 cellPadding=0 width=100% border=0>
                <!--关于TBODY标记-->
                <TBODY style="DISPLAY: block">
                  <TR>
                    <TD vAlign=top align=middle><TABLE width=98% height="63" border=0 align="center" cellPadding=0 cellSpacing=0>
                        <TBODY>
                          <TR>
                            <TD bgcolor="#FAFBFC" colspan="3"> 
                            	<div id="poInData" align="center">入库信息图形报表 </div>
        					</TD>
                          </TR>
                        </TBODY>
                    </TABLE></TD>
                  </TR>
                </TBODY>
                <!--关于cells集合-->
                <TBODY style="DISPLAY: none">
                  <TR>
                    <TD vAlign=top align=middle><TABLE width=98% height="63" border=0 align="center" cellPadding=0 cellSpacing=0>
                        <TBODY>
                          <TR>
                            <TD bgcolor="#FAFBFC" colspan="3">
                            	<div id="poOutData" align="center">原材料出库信息图形报表 </div>
                            </TD>
                          </TR>
                        </TBODY>
                    </TABLE></TD>
                  </TR>
                </TBODY> 
                <!--关于tBodies集合-->
                <TBODY style="DISPLAY: none">
                  <TR>
                    <TD vAlign=top align=middle><TABLE width=98% height="63" border=0 align="center" cellPadding=0 cellSpacing=0>
                        <TBODY>
                          <TR>
                            <TD bgcolor="#FAFBFC" colspan="3">
                            	<div id="produceOutData" align="center">成品出库信息图形报表 </div>
                            </TD>
                          </TR>
                        </TBODY>
                    </TABLE></TD>
                  </TR>
                </TBODY> 
            </TABLE></td>--%>
        <td>&nbsp;</td>
        <td valign="top"><table width="100%" height="144" border="0" cellpadding="0" cellspacing="0" class="line_table">
          <tr>
            <td width="7%" height="27" background="images/news-title-bg.gif"><img src="images/news-title-bg.gif" width="2" height="27"></td>
            <td width="93%" background="images/news-title-bg.gif" class="left_bt2">系统说明</td>
          </tr>
          <tr>
            <td height="102" valign="top">&nbsp;</td>
           	<td align="center" valign="middle"><br/>
           		<span><a href="javascript:download(1)">请点击这里下载最新版本的用户手册(中文)</a></span><br/>
           		<span><a href="javascript:download(2)">请点击这里下载最新版本的用户手册(英文)</a></span><br/>
           		<span><a href="javascript:download(3)">请点击这里下载最新版本的管理员手册</a></span><br/>
  			</td>
          <tr>
            <td height="5" colspan="2">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="40" colspan="4"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
          <tr>
            <td></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td width="2%">&nbsp;</td>
        <td width="51%" class="left_txt"><img src="images/icon-phone.gif" width="17" height="14"> 技术支持：010-88400396</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
    <td background="images/mail_rightbg.gif">&nbsp;</td>
  </tr>
  <tr>
    <td valign="bottom" background="images/mail_leftbg.gif"><img src="images/buttom_left2.gif" width="17" height="17" /></td>
    <td background="images/buttom_bgs.gif"><img src="images/buttom_bgs.gif" width="17" height="17"></td>
    <td valign="bottom" background="images/mail_rightbg.gif"><img src="images/buttom_right2.gif" width="16" height="17" /></td>
  </tr>
</table>
