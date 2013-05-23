<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>修改产品品牌</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/vip.css" type="text/css">
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
<script language="JavaScript">
function checkfm(form){
	if (trim(form.name.value)==""){
		alert("品牌名称不能为空！");
		form.name.focus();
		return false;
	}
	return true;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<s:form action="/control/brand/manage!edit" method="post" enctype="multipart/form-data" onsubmit="return checkfm(this)">
<s:hidden name="brand.code" />
<br>
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF">修改品牌：</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <s:textfield label="品牌名称：" name="brand.name" size="50" maxlength="40"/>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">Logo图片：</div></td>
      <td width="78%"> <input type="file" name="logofile" size="50"><br/>
     	<img src="${brand.logopath}" width="50">
     </td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="SYS_SET" value=" 确 定 " class="frm_btn">
        </div></td>
    </tr>
  </table>
</s:form>
<br>
</body>
</html>