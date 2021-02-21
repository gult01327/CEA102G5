<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.commodity_category.model.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="com.commodity.model.*"%>
<%@ page import="com.commodity.controller.*"%>
<%@ page import="com.member.model.*"%>

<html>
<head><title>購物車列表 - listAllCart.jsp</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
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
  
  #form1{
  	display:inline;
  }
</style>

</head>
<body bgcolor=#E8FFE8>

<h4>此頁練習採用 EL 的寫法取值:</h4><br>

	<tr>
		<td>
			 <img src="<%=request.getContextPath() %>/resource/images/food.jpg" height="100" width="100"><font size="+3">購物車列表</font><br>
			 <h4><a href="<%=request.getContextPath()%>/front_end/commodity/comindex.jsp"><img src="<%=request.getContextPath() %>/resource/images/3.jpg" width="100" height="100" border="0">回商城首頁</a></h4>
		</td>
	</tr><br><br>
	<%MemVO memVO = (MemVO)session.getAttribute("memVO"); %>
	<%CartService cartSvc = new CartService(); %>
	<%ComService comSvc = new ComService(); %>
	<%List list = cartSvc.getAllByMemID(memVO.getMemID()); %>
	<tr>
		<td>
			<font color="red">購物車內共有:<%=list.size()%> 筆商品</font>
		</td>
	</tr>


<table>
	<tr>
		<th>勾選欲結帳商品</th>
		<th>會員ID</th>
		<th>食材ID</th>
		<th>食材名稱</th>
		<th>食材圖片</th>
		<th>數量(份)</th>
		<th>單價</th>
		<th>新增時間</th>
		<th>刪除</th>
	</tr>

	<%Iterator it = list.iterator(); %>
	<%while(it.hasNext()){ %>
		<% CartVO cartVO = (CartVO)it.next(); %>	
		<% ComVO comVO = comSvc.getOneCom(cartVO.getComID()); %>
		<tr>
			<td><input type="checkbox"  name="checkComID" value="<%=cartVO.getComID()%>"></td>
			<td><%=cartVO.getMemID() %></td>
			<td><%=cartVO.getComID() %></td>
			<td><%=comVO.getComName() %></td>
			<td><img src = "<%=request.getContextPath()%>/ComPicReader<%=comVO.getComPicSrc()%>&pic=1" height="100" width="100"></td>
			
			<td>
			 
			<button id ="minus">-</button>
			<input type="hidden" name="action" id="action" value="minusCount">
			<input type="hidden" name="comID" id="comID" value="<%=cartVO.getComID()%>">
			<input type="hidden" name="cardCount" id="cardCount" value="<%=cartVO.getCardCount()%>">
			
			<%=cartVO.getCardCount() %>
			
			<button id = "plus">+</button>
			<input type="hidden" name="action" value="plusCount">
			<input type="hidden" name="comID" id="comID" value="<%=cartVO.getComID()%>">
			<input type="hidden" name="cardCount" id="cardCount" value="<%=cartVO.getCardCount()%>">
			
			</td>
			
			<td>$<%=comVO.getComPrice()%></td>
			<td><%=cartVO.getCardTime() %></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cart/comCart.do" style="margin-bottom: 0px;">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="comID" value="<%=cartVO.getComID()%>">
			    <input type="hidden" name="action" value="delete_Cart">
			  </FORM>
			</td>
		</tr>
<%} %>
</table>

 <form name="checkoutForm" action="<%=request.getContextPath()%>/cart/comCart.do" method="POST">
              <input type="hidden" name="action"  value="CHECKOUT">
              <input type="submit" value="確認訂單" class="button">
          </form>


<script>
	$("#minus").click(function(){
		$.ajax({
			url: "<%=request.getContextPath()%>/cart/comCart.do",
			type: "POST",
			data:{
				action: $("#action").val(),
				comID: $("#comID").val(),
				cardCount: $("#cardCount").val()
				
			},
			
		});
	});
</script>

</body>
</html>