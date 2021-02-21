<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.commodity.model.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="com.member.model.*"%>   
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  ComVO comVO = (ComVO) request.getAttribute("comVO"); //CartServlet.java(Controller), 存入req的comVO物件
%>
<%MemVO memVO = (MemVO) session.getAttribute("memVO"); %>

<html>
<head>
<title>商品資料</title>

<style>
#wrapper {
	width:920px;
	margin:50px auto;
}

#wrapper #left{
	width:520px;
	float:left;
    
}

#wrapper #right{
    
}
</style>

</head>
<body bgcolor=#E8FFE8>


<table id="table-1">
	<tr>
		<td>
			 <h3>商品資料</h3>
			 <h4><a href="<%=request.getContextPath() %>/back_end/commodity/comSelectPage.jsp">回首頁</a></h4>
		</td>
	</tr>
	
	<tr>
		<td>
			<font><a href="<%=request.getContextPath() %>/front_end/commodity/comindex.jsp">回商城首頁</a></font>
		</td>
	</tr>
	<%CartService cartSvc = new CartService(); 
	%>
	<tr>
	<%if(memVO==null){ %>
		<td><a href="<%=request.getContextPath()%>/front_end/member/login.jsp">會員登入</a></td><br>
		<%session.setAttribute("comVO", comVO); %>
		<%}else{ %>
		<td>
		<%List list = cartSvc.getAllByMemID(memVO.getMemID());%>
			<font color="red">${memVO.memName}您好! 您的<a href="<%=request.getContextPath() %>/front_end/commodity/listAllCart.jsp">購物車</a>內共有:<%=list.size()%> 筆商品</font>
		</td>
		<br>
		<td>
		<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/member/mem.do">
		<br><input type="submit" value="會員登出">
		<input type="hidden" name="action"	value="logout">
		</FORM>
		</td>
		<%} %>
	</tr>
</table>


<div id="wrapper">
	<div id="left">
		<img src = "<%=request.getContextPath()%>/ComPicReader${comVO.comPicSrc}&pic=1" height="350" width="350"><br>
		<font color="blue" size="7">${comVO.comName}</font><br><br>
		<font size="5">${comVO.comWeight}${comVO.comUnit} /份</font><br>
		<font size="5">售價 $ ${comVO.comPrice}</font>
	</div>

	<div id="right">
    <font size = "6"><b>商品簡介 :</b><br> ${comVO.comContent}</font><br>
    <font size = "6"><b>商品熱量</b> : ${comVO.comCal}</font><br>
    <font size = "6"><b>商品碳水化合物含量</b> : ${comVO.comCarb}</font><br>
    <font size = "6"><b>商品蛋白質含量</b> : ${comVO.comPro}</font><br>
    <font size = "6"><b>商品脂質含量</b> : ${comVO.comFat}</font><br>
    <font size = "6"><b>商品特性</b> : ${comVO.comProp}</font><br>
    <br><br><br><br><br><br><br>
    <form method="post" action="<%=request.getContextPath()%>/cart/comCart.do">
    <img src="<%=request.getContextPath()%>/resource/images/cart.jpg" width="65px" height="45px">
    <input type="submit" class="button" value="放入購物車">
    <input type="text" name="cardCount" size="3" value=1>
      <input type="hidden" name="comID" value="${comVO.comID}">
      <input type="hidden" name="action" value="ADD">
      <input type="hidden" name="memID" value="${memVO.memID}">
      <input type="hidden" name="location" value="<%=request.getServletPath()%>">      
    </form>
	</div>
	
	
	
</div>

</body>
</html>