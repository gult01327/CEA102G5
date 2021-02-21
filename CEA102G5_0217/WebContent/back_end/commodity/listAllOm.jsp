<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.commodity_category.model.*"%>
<%@ page import="com.ordermaster.model.*"%>

<%
	OmService omSvc = new OmService();
	List<OmVO> list = omSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<html>
<head><title>所有訂單主檔 - listAllOm.jsp</title>

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
    text-align: center;
  }
</style>

</head>
<body bgcolor=#E8FFE8>



	<tr><td>
		 <img src="<%=request.getContextPath() %>/resource/images/food.jpg" height="100" width="100"><font size="+3">所有訂單主檔列表</font><br>
		 <h4><a href="<%=request.getContextPath()%>/back_end/commodity/comSelectPage.jsp"><img src="<%=request.getContextPath() %>/resource/images/3.jpg" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
	
<table>
	<tr>
		<th>訂單主檔ID</th>
		<th>會員ID</th>
		<th>訂單總金額</th>
		<th>訂單狀態</th>
		<th>出貨狀態</th>
		<th>使用積分狀態</th>
		<th>收貨人</th>
		<th>收貨人電話</th>
		<th>收貨人地址</th>
		<th>訂單創立時間</th>
		<th>修改出貨狀態</th>
		<th>取消訂單</th>
		<th>查看訂單明細</th>
	</tr>
	<%@ include file="page1.file" %>  
	<c:forEach var="omVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr ${(omVO.omID == param.omID)?'bgcolor=#CCCCFF':''}>
			<td>${omVO.omID}</td>
			<td>${omVO.memID}</td>
			<td>$ ${omVO.omPrice}</td>
			<td>${omVO.omPay==1?"已付款":"已取消"}</td>
			<td>${omVO.omShip==1?"已出貨":"未出貨"}</td>
			<td>${omVO.omDiscount==1?"未使用":"已使用"}</td>
			<td>${omVO.omrName}</td>
			<td>${omVO.omrPhone}</td>
			<td>${omVO.omrAddress}</td>
			<td>${omVO.omTime}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ordermaster/om.do" style="margin-bottom: 0px;">
			    <input type="submit" value="修改出貨狀態"> 
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			    <input type="hidden" name="omID" value="${omVO.omID}">
			    <input type="hidden" name="check" value="${omVO.omShip}">
			    <input type="hidden" name="action" value="Update_omShip"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ordermaster/om.do" style="margin-bottom: 0px;">
			    <input type="submit" value="取消訂單">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">
			    <input type="hidden" name="omID" value="${omVO.omID}">
			    <input type="hidden" name="check" value="${omVO.omPay}">
			    <input type="hidden" name="action" value="Cancel_om"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ordermaster/om.do" style="margin-bottom: 0px;">
			    <input type="submit" value="查看訂單明細">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">  
			    <input type="hidden" name="omID" value="${omVO.omID}">
			    <input type="hidden" name="action" value="ListOd_ByOmID"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

<%if (request.getAttribute("list")!=null){%>
       <jsp:include page="listOd_ByOmID.jsp" />
<%} %>

</body>
</html>