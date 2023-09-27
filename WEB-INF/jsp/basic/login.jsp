<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #1D3647;
}
-->
</style>
<script language="JavaScript">
function correctPNG()
{
    var arVersion = navigator.appVersion.split("MSIE")
    var version = parseFloat(arVersion[1])
    if ((version >= 5.5) && (document.body.filters)) 
    {
       for(var j=0; j<document.images.length; j++)
       {
          var img = document.images[j]
          var imgName = img.src.toUpperCase()
          if (imgName.substring(imgName.length-3, imgName.length) == "PNG")
          {
             var imgID = (img.id) ? "id='" + img.id + "' " : ""
             var imgClass = (img.className) ? "class='" + img.className + "' " : ""
             var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' "
             var imgStyle = "display:inline-block;" + img.style.cssText 
             if (img.align == "left") imgStyle = "float:left;" + imgStyle
             if (img.align == "right") imgStyle = "float:right;" + imgStyle
             if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle
             var strNewHTML = "<span " + imgID + imgClass + imgTitle
             + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";"
             + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
             + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>" 
             img.outerHTML = strNewHTML
             j = j-1
          }
       }
    }    
}
window.attachEvent("onload", correctPNG);

function validatecodes(value)
{
	var timenow = new Date().getTime();
	value.src=value.src+"?d="+timenow;
}
</script>
<link href="images/skin.css" rel="stylesheet" type="text/css">

<html:javascript formName="loginForm" staticJavascript="false"/>
<table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
    <tr>
    <td height="42" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
      <tr>
        <td width="1%" height="21">&nbsp;</td>
        <td height="42">&nbsp;</td>
        <td width="17%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
      <tr>
        <td width="55%" align="right"><table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
            <tr>
              <td height="138" valign="top"><table width="100%" height="427" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="100">&nbsp;</td>
                </tr>
                <tr>
                  <td align="right" valign="top"><img src="images/logo.png"></td>
                </tr>
                
              </table></td>
            </tr>
        </table></td>
        <td width="1%" >&nbsp;</td>
        <td width="50%" valign="bottom">
        <html:form action="login.do" method="post" focus="loginName" onsubmit="return validateLoginForm(this);">
        <table width="110%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
            	<td>&nbsp;</td>
            	<td>
					<div style="font-size:16px;margin-left:60px;font-family:'黑体';color:#009933">
						<html:messages id="x_message" message="true">
							${x_message}<br>
						</html:messages>
					</div>
				</td>
            </tr>
            <tr>
              <td width="4%">&nbsp;</td>
              <td width="96%" height="38"><span class="login_txt_bt">登录管理系统</span>
              </td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td height="21"><table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
            <tr>
              <td height="164" colspan="2" align="middle">
               <table width="100%">
               <tr>
              	<td class="warningMsg"><html:errors/></td>
              </tr>
              </table>
              <form name="myform" action="login.do" method="post" onsubmit="return validateLoginForm(this);">
                  <table cellSpacing="0" cellPadding="0" width="110%" border="0" height="143" id="table212">
                    <tr>
                      <td width="13%" height="38" class="top_hui_text"><span class="login_txt">用户名：&nbsp;&nbsp; </span></td>
                      <td  width="38%" height="38" colspan="2" class="top_hui_text"><input name="loginName" class="editbox4" value="" style="font-size:9pt; width:143px">
                       <img src="images/icon-login-seaver.gif" width="16" height="16"> </td>
                    
                    
                    </tr>
                    <tr>
                      <td width="13%" height="35" class="top_hui_text"><span class="login_txt"> 密&nbsp;&nbsp;&nbsp;&nbsp;码： &nbsp;&nbsp; </span></td>
                      <td height="35" colspan="2" class="top_hui_text"><input class="editbox4" value="" type="password" style="font-size:9pt; width:143px" name="password">
                        <img src="images/luck.gif" width="19" height="18"> </td>
                      <td width="67%" align="left">
                      <!--  
                      	<a href="forgetPassword.do" ><bean:message key="user.forgetPassword"/></a>
                      -->
                      
                      </td>
                    </tr>
                    <tr>
      <td><span class="login_txt">语&nbsp;&nbsp;&nbsp;&nbsp;言:&nbsp;&nbsp; </span></td>
      <td><html:select property="locale"><html:option value=""><bean:message key="user.locale.useDefault"/></html:option><html:option value="en"><bean:message key="user.locale.useEnglish"/></html:option><html:option value="zh"><bean:message key="user.locale.useChinese"/></html:option></html:select></td>
    </tr>
    <tr>
    <td></td>
    <tr>
      <td></td>
      <td align="left">
        <html:submit><bean:message key="all.login"/></html:submit>
        <html:reset><bean:message key="all.reset"/></html:reset>
      </td>
    <td>
        <a href="forgetPassword.do" ><bean:message key="user.forgetPassword"/></a>
      </td>
      <td></td>
    </tr>
                  </table>
                  <input type="hidden" name="locale" value="zh">
                  </form>
                    </td>
                  </tr>
                  <tr>
                    <td width="433" align="center" valign="bottom"><br>
                      <span class="login_txt" style="align:center"> 
                      </span>
                    </td>
                    <td width="57" align="right" valign="bottom">&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="433" height="164" align="right" valign="bottom"><img src="images/login-wel.gif" width="242" height="138"></td>
                    <td width="57" align="right" valign="bottom">&nbsp;</td>
                  </tr>
              </table>
              </td>
            </tr>
          </table>
          </html:form>
          </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
      <tr>
        <td align="center"><span class="login-buttom-txt">Copyright © 2011-2013 北京市闻荫科技有限公司  版权所有</span></td>
      </tr>
    </table></td>
  </tr>
</table>

