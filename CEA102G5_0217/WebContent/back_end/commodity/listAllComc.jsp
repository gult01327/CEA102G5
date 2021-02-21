<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.commodity_category.model.*"%>

<jsp:useBean id="comcSvc" scope="page" class="com.commodity_category.model.ComcService" />

<html>
<head><title>所有食材分類 - listAllComc.jsp</title>

<style>
  table#table-1 {
	background-color:#E8FFE8;
    border: 2px solid black;
    text-align: ;
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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor=#E8FFE8>

<h4>此頁練習採用 EL 的寫法取值:</h4><br>

	<tr><td>
		 <img src="<%=request.getContextPath()%>/resource/images/food.jpg" height="100" width="100"><font size="+3">所有食材分類列表</font><br>
		 <h4><a href="<%=request.getContextPath()%>/back_end/commodity/comSelectPage.jsp"><img src="<%=request.getContextPath()%>/resource/images/3.jpg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>食材分類ID</th>
		<th>食材分類名稱</th>
		<th>修改</th>
		<th>刪除<font color=red>(關聯測試與交易-小心)</font></th>
		<th>查詢分類下的所有食材</th>
	</tr>
	
	<c:forEach var="comcVO" items="${comcSvc.all}">
		<tr>
			<td>${comcVO.comcID}</td>
			<td>${comcVO.comcName}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/commodity_category/comc.do" style="margin-bottom: 0px;">
			    <input type="submit" value="修改"   disabled="disabled"> 
			    <input type="hidden" name="comcID" value="${comcVO.comcID}">
			    <input type="hidden" name="action" value="getOne_For_Update_Comc"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/commodity_category/comc.do" style="margin-bottom: 0px;">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="comcID" value="${comcVO.comcID}">
			    <input type="hidden" name="action" value="delete_Comc"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/commodity_category/comc.do" style="margin-bottom: 0px;">
			    <input type="submit" value="送出查詢"> 
			    <input type="hidden" name="comcID" value="${comcVO.comcID}">
			    <input type="hidden" name="action" value="listCom_ByComcID_B"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

<%if (request.getAttribute("listCom_ByComcID")!=null){%>
       <jsp:include page="/back_end/commodity/listCom_ByComcID.jsp" />
<%} %>

</body>
</html>