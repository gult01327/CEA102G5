
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料新增</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor=#E8FFE8>

<table id="table-1">
	<tr><td>
		 <h3>會員資料新增 - addMem.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath() %>/back_end/member/memSelect.jsp"><img src="<%=request.getContextPath() %>/resource/images/food.jpg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/member/mem.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>會員名稱:</td>
		<td><input type="TEXT" name="memName" size="45" 
			 value="<%=(memVO==null)?"":memVO.getMemName() %>" /></td>
	</tr>
	<tr>
		<td>會員帳號</td>
		<td><input type="TEXT" name="memAccount" size="45"
			 value="<%=(memVO==null)?"":memVO.getMemAccount() %>" /></td>
	</tr>
		<tr>
		<td>會員密碼</td>
		<td><input type="password" name="memPassword" size="45"
			 value="<%=(memVO==null)?"":memVO.getMemPassword() %>" /></td>
	</tr>
	<tr>
		<td>會員圖片</td>
		<td><input type="file" name="memUpfile" id="myFile"></td>
	</tr>
	<tr>
		<td>會員電話</td>
		<td><input type="TEXT" name="memPhone" size="45"
			 value="<%=(memVO==null)?"":memVO.getMemPhone() %>" /></td>
	</tr>
	<tr>
		<td>會員EMAIL</td>
		<td><input type="TEXT" name="memEmail" size="45"
			 value="<%=(memVO==null)?"":memVO.getMemEmail() %>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>