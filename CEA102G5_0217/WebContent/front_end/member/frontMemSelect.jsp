<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<% MemVO memVO = (MemVO)session.getAttribute("memVO"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>個人主頁</title>
</head>
<body bgcolor="#E8FFE8">
<img src="<%=request.getContextPath()%>/resource/images/food.jpg" height="100" width="100"><font size="+3">個人主頁</font>
<hr><p>
<img src="<%=request.getContextPath()%>/commodity/comPic.do${memVO.memPicSrc}&pic=1" height="100" width="100">
<h3>${memVO.memName} 您好! 歡迎回來</h3>
<h4><a href="<%=request.getContextPath() %>/front_end/commodity/comindex.jsp">商城首頁</a></h4>

<ul>
  <li><a href='<%=request.getContextPath()%>/member/mem.do?action=getOne_For_Update&memID=${memVO.memID}'>修改會員資料</a> </li>
</ul>


<ul>
  <li><a href='<%=request.getContextPath()%>/front_end/commodity/listAllCart.jsp'>查看購物車</a> </li>
</ul>

<ul>
  <li><a href='<%=request.getContextPath()%>/front_end/commodity/listOmbyMemID.jsp'>查看訂單列表</a> </li>
</ul>

<ul>
  <li><a href='<%=request.getContextPath()%>/back_end/member/addMem.jsp'>查看商品收藏</a> </li>
</ul>

<ul>
  <li><a href='<%=request.getContextPath()%>/back_end/member/addMem.jsp'>查看食譜收藏</a> </li>
</ul>

<ul>
  <li><a href='<%=request.getContextPath()%>/back_end/member/addMem.jsp'>查看食譜列表</a> </li>
</ul>
</body>


</html>