<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改 - updateMem.jsp</title>

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
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>會員資料修改 - updateMem.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front_end/member/frontMemSelect.jsp"><img src="<%=request.getContextPath()%>/resource/images/3.jpg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/mem.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMemID()%></td>
	</tr>
	<tr>
		<td>會員姓名:</td>
		<td><input type="TEXT" name="memName" size="45" value="<%=memVO.getMemName()%>" /></td>
	</tr>
	<tr>
		<td>商品圖片</td>
		<td><input type="file" name="memUpfile" id="myFile" /></td>
	</tr>
	<tr>
		<td>會員帳號:</td>
		<td><input type="TEXT" name="memAccount" size="45"	value="<%=memVO.getMemAccount()%>" /></td>
	</tr>

	<tr>
		<td>會員密碼:</td>
		<td><input type="TEXT" name="memPassword" size="45"	value="<%=memVO.getMemPassword()%>" /></td>
	</tr>
	<tr>
		<td>會員電話</td>
		<td><input type="TEXT" name="memPhone" size="45" value="<%=memVO.getMemPhone()%>" /></td>
	</tr>
	<tr>
		<td>會員Email</td>
		<td><input type="TEXT" name="memEmail" size="45" value="<%=memVO.getMemEmail()%>" /></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="memID"  value="<%=memVO.getMemID()%>">
<input type="submit" value="送出修改"></FORM>


</body>

</html>