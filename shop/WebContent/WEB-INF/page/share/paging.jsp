<%@ page language="java" pageEncoding="UTF-8"%>
<tr ><td colspan="6"  bgcolor="6f8ac4" align="right">
   	<font color="#FFFFFF"> 总页数:${pageView.totalPage }   |  每页显示:${pageView.maxResult } 条记录  |  总记录数: ${pageView.qr.totalRecord }  | 当前页:${pageView.currentPage }  </font>
    <s:iterator begin="pageView.pageIndex.startindex" end="pageView.pageIndex.endindex" var="wp">
    	<!--Why #wp ? because wp not exist in root object(Action). Other (non-root) objects in the ActionContext can be rendered use the # notation. -->
		<s:if test="pageView.currentPage==#wp "><b>第${wp }页</b></s:if>    
    	<s:if test="pageView.currentPage!=#wp"><a href="javascript:topage('${wp}')" class="a03">第${wp }页</a></s:if>
    </s:iterator>
   </td></tr>