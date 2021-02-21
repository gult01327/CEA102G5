<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ordermaster.model.*"%>
<%@ page import="com.orderdetail.model.*"%>

<%List<OdVO> list = (List<OdVO>) request.getAttribute("list"); %>

<html>
<head><title>訂單明細 - listOd_ByOmID.jsp</title>

<style>
  table#table-2 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-2 h4 {
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
	width: 1400px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
  }
</style>

</head>
<body bgcolor='white'>

</head>
<body bgcolor=#E8FFE8>



	
<img src="<%=request.getContextPath()%>/resource/images/food.jpg" height="100" width="100"><font size="+3">訂單明細列表</font>
<h4><a href="<%=request.getContextPath()%>/back_end/commodity/comSelectPage.jsp"><img src="<%=request.getContextPath()%>/resource/images/3.jpg" width="100" height="100" border="0">回後台首頁</a></h4>


<table>
	<tr>
		<th>訂單主檔ID</th>
		<th>商品ID</th>
		<th>商品名稱</th>
		<th>商品圖片</th>
		<th>訂購數量</th>
		<th>商品單價</th>
		<th>退貨狀態</th>
		<th>退貨原因</th>
		<th>退貨日期</th>
	</tr>
	
	<c:forEach var="odVO" items="${list}">
		<tr>
			<td>${odVO.omID}</td>
			<td>${odVO.comID}</td>
			<td>${odVO.comName}</td>
			<td><img src = "<%=request.getContextPath()%>/ComPicReader${odVO.comPicSrc}&pic=1" height="100" width="100"></td>
			<td>${odVO.odCount}</td>
			<td>$ ${odVO.odPrice}</td>
			<td>${odVO.odReturn}</td>
			<td>${odVO.odReason}</td>
			<td>${odVO.odRtime}</td>
		</tr>
	</c:forEach>
</table>



</body>
</html>