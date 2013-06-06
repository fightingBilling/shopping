<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>产品品牌显示</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/vip.css" type="text/css">
<script language="JavaScript">
<!--
	//到指定的分页页面
	function topage(page){
		var form = document.forms[0];
		form.page.value = page;
		form.submit();
	}
//-->
</script>
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">
<s:form action="/control/product/brand/list" method="post">
<s:hidden name="page"/>
<s:hidden name="brand.name"/>
  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="4" bgcolor="6f8ac4" align="right">
    	<%@ include file="/WEB-INF/page/share/paging.jsp" %>
   </td></tr>
    <tr>
      <td width="30%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">代号</font></div></td>
      <td width="5%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">修改</font></div></td>
      <td width="35%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">名称</font></div></td>
	  <td width="30%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">Logo</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<s:iterator value="pageView.qr.resultList" var="entry">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center">${entry.code}</div></td>
      <td bgcolor="f5f5f5"> <div align="center"><a href="<s:url action="/control/brand/manage!editUI"/>?brand.code=${entry.code}">
	  <img src="/images/edit.gif" width="15" height="16" border="0"></a></div></td>
      <td bgcolor="f5f5f5"> <div align="center">${entry.name}</div></td>
	  <td bgcolor="f5f5f5"> <div align="center"><s:if test="entry.logopath">没有logo</s:if>
	  <img src="${entry.logopath}" width="50"></div></td>
	</tr>
</s:iterator>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="4" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="5%"></td>
              <td width="85%">
              <input type="button" class="frm_btn" onClick="javascript:window.location.href='<s:url action="/control/brand/manage!addUI"/>'" value="添加品牌"> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='<s:url action="/control/brand/manage!queryUI"/>'" value=" 查 询 "> &nbsp;&nbsp;
            </td>
          </tr>
        </table></td>
    </tr>
  </table>
</s:form>
</body>
</html>