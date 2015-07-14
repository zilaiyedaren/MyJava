
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<script type="text/javascript" src="js/JsOnresize5.js"></script>--%>
<%
    int currentPage = 1;	// 为当前所在的页，默认在第1页
    int lineSize = 3;		// 每次显示的记录数
    int allRecorders = 0;	// 表示全部的记录数
    int pageSize = 1;		// 表示全部的页数（尾页）
    int count = 0;
    int lsData[] = {1, 3, 5, 7, 9, 10, 15, 20, 25, 30, 50, 100};
 

%>
<%
    try {
        currentPage = Integer.parseInt(request.getParameter("cp"));
    } catch (Exception e) {
        System.out.println(e);
    }
    try {
        lineSize = Integer.parseInt(request.getParameter("ls"));
    } catch (Exception e) {
        System.out.println(e);
    }
    try {
        allRecorders = Integer.parseInt(request.getParameter("allRecorders"));
    } catch (Exception e) {
        System.out.println(e);
    }
    pageSize = (allRecorders + lineSize - 1) / lineSize;
    if (pageSize == 0) {
        pageSize = 1;
    }

%>
<script language="javascript">
    function go(num){
        document.getElementById("cp").value = num ;
        document.spform.submit() ;	// 表单提交
    }
</script>
<table id="table1"  align="center">
  <tr>
    <td width="300" align="left">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong> <%=allRecorders%></strong> 条记录，当前第<strong> <%=currentPage%></strong> 页，共 <strong><%=pageSize%></strong> 页</span> </td>
    <td width="500" align="right"><input type="button" class="right-button02" name="button" id="button" value="最前页" onClick="go(1)" <%=currentPage == 1 ? "DISABLED" : ""%>>
      &nbsp;
      <input type="button" class="right-button02" name="button" id="buttonPre" value="上一页" onClick="go(<%=currentPage - 1%>)" <%=currentPage == 1 ? "DISABLED" : ""%>>
      &nbsp;
      <input type="button" class="right-button02" name="button" id="buttonNext" value="下一页" onClick="go(<%=currentPage + 1%>)" <%=currentPage == pageSize ? "DISABLED" : ""%> >
      &nbsp;
      <input type="button" class="right-button02" name="button" id="buttonLast" value="最后页" onClick="go(<%=pageSize%>)" <%=currentPage == pageSize ? "DISABLED" : ""%>>
      &nbsp;
      跳转到第
      <select name="selcp" class="xl" onChange="go(this.value)">
        <%
            for (int x = 1; x <= pageSize; x++) {
        %>
        <option value="<%=x%>" <%=x == currentPage ? "SELECTED" : ""%>><%=x%></option>
        <%
            }
        %>
      </select>
      页&nbsp;&nbsp;&nbsp;
      每页显示
      <select name="ls" class="xl" onChange="go(1)">
        <%
            for (int x = 0; x < lsData.length; x++) {
        %>
        <option value="<%=lsData[x]%>" <%=lsData[x] == lineSize ? "SELECTED" : ""%>><%=lsData[x]%></option>
        <%
            }
        %>
      </select>
      条 </td>
  </tr>
</table>
<input type="hidden" name="cp" value="1" id="cp">
</form>

