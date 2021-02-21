<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.commodity.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>


<jsp:useBean id="comcSvc" scope="page" class="com.commodity_category.model.ComcService" />


<html>
<head><title>複合查詢 - listEmps_ByCompositeQuery.jsp</title>

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
	width: 1600px;
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
<img src="<%=request.getContextPath() %>/resource/images/food.jpg" height="100" width="100"><font size="+3">後台商品管理</font>
<hr><p>

<h4>
☆萬用複合查詢  - 可由客戶端 select_page.jsp 隨意增減任何想查詢的欄位<br>
☆此頁作為複合查詢時之結果練習，<font color=red>已增加分頁、送出修改、刪除之功能</font></h4>
<table id="table-1">
	<tr><td>
		 <h3>複合查詢商品資料 - listAllCom.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/commodity/comSelectPage.jsp"><img src="<%=request.getContextPath() %>/resource/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>商品銷量</th>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>分類名稱</th>
		<th>商品圖片</th>
		<th>商品價格</th>
		<th>商品簡介</th>
		<th>商品狀態</th>
		<th>商品重量</th>
		<th>商品單位</th>
		<th>商品熱量(cal)</th>
		<th>商品碳水(g)</th>
		<th>商品蛋白質(g)</th>
		<th>商品脂肪(g)</th>
		<th>商品特性</th>
		<th>商品創建時間</th>
	</tr>

	<c:forEach var="comVO" items="${listCom_ByCompositeQuery}" >
		<tr align='center' valign='middle' ${(comVO.comID == param.comID) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${comVO.comSales}</td>
			<td>${comVO.comID}</td>
			<td>${comVO.comName}</td>
			<td><c:forEach var="comcVO" items="${comcSvc.all}">
                    <c:if test="${comVO.comcID == comcVO.comcID}">
	                    ${comcVO.comcID}- ${comcVO.comcName}
                    </c:if>
                </c:forEach>
			</td>
			<td><img src="<%=request.getContextPath()%>/ComPicReader${comVO.comPicSrc}&pic=1" height="100" width="100"></td>
			<td>${comVO.comPrice}</td>
			<td>${comVO.comContent}</td>
			<td>${comVO.comStatus}</td>
			<td>${comVO.comWeight}</td>
			<td>${comVO.comUnit}</td>
			<td>${comVO.comCal}</td>
			<td>${comVO.comCarb}</td>
			<td>${comVO.comPro}</td>
			<td>${comVO.comFat}</td>
			<td>${comVO.comProp}</td>
			<td>${comVO.comTime}</td>
					
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/commodity/com.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="comID"      value="${comVO.comID}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			                   <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/commodity/com.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="comID"      value="${comVO.comID}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			                 <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"     value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
</html>