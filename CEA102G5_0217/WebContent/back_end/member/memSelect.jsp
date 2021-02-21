<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>後台會員管理</title>
</head>
<body bgcolor=#E8FFE8 onload="load()">
<img src="<%=request.getContextPath()%>/resource/images/food.jpg" height="100" width="100"><font size="+3">後台會員管理</font>
<hr><p>
<h3>This is the Home page for Member: Home</h3>
<h4><a href="<%=request.getContextPath() %>/back_end/commodity/comSelectPage.jsp">回後台商品管理</a></h4>


<ul>
  <li><a href='<%=request.getContextPath()%>/back_end/member/listAllMem.jsp'>所有會員列表</a>   <br><br></li>
   <li><a href='<%=request.getContextPath()%>/front_end/member/login.jsp'>會員登入</a>   <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/mem.do" >
        <b>輸入會員編號 (如1):</b>
        <input type="text" name="memID">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="memSvc" scope="page" class="com.member.model.MemService"/>
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/mem.do" >
       <b>選擇會員ID:</b>
       <select size="1" name="memID">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.memID}">${memVO.memID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/mem.do" >
       <b>選擇會員姓名:</b>
       <select size="1" name="memID">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.memID}">${memVO.memName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>



<h4><font>新增會員:</font></h4>
<ul>
  <li><a href='<%=request.getContextPath()%>/back_end/member/addMem.jsp'>ADD Member</a> </li>
</ul>




</body>


</html>