<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>EATIN登入介面</title>
    <style>
        html {
            background-color: #E8FFE8;
        }
        
        .wrapper {
            margin: 140px 0 140px auto;
            width: 1284px;
        }
        
        .loginBox {
            background-color: #F0F4F6;
            /*上divcolor*/
            border: 1px solid #BfD6E1;
            border-radius: 5px;
            color: #444;
            font: 14px 'Microsoft YaHei', '微軟雅黑';
            margin: 0 auto;
            width: 388px
        }
        
        .loginBox .loginBoxCenter {
            border-bottom: 1px solid #DDE0E8;
            padding: 24px;
        }
        
        .loginBox .loginBoxCenter p {
            margin-bottom: 10px
        }
        
        .loginBox .loginBoxButtons {
            /*background-color: #F0F4F6;*/
            /*下divcolor*/
            border-top: 0px solid #FFF;
            border-bottom-left-radius: 5px;
            border-bottom-right-radius: 5px;
            line-height: 28px;
            overflow: hidden;
            padding: 20px 24px;
            vertical-align: center;
            filter: alpha(Opacity=80);
            -moz-opacity: 0.5;
            opacity: 0.5;
        }
        
        .loginBox .loginInput {
            border: 1px solid #D2D9dC;
            border-radius: 2px;
            color: #444;
            font: 12px 'Microsoft YaHei', '微軟雅黑';
            padding: 8px 14px;
            margin-bottom: 8px;
            width: 310px;
        }
        
        .loginBox .loginInput:FOCUS {
            border: 1px solid #B7D4EA;
            box-shadow: 0 0 8px #B7D4EA;
        }
        
        .loginBox .loginBtn {
            background-image: -moz-linear-gradient(to bottom, blue, #85CFEE);
            border: 1px solid #98CCE7;
            border-radius: 20px;
            box-shadow: inset rgba(255, 255, 255, 0.6) 0 1px 1px, rgba(0, 0, 0, 0.1) 0 1px 1px;
            color: #444;
            /*登入*/
            cursor: pointer;
            float: right;
            font: bold 13px Arial;
            padding: 10px 50px;
        }
        
        .loginBox .loginBtn:HOVER {
            background-image: -moz-linear-gradient(to top, blue, #85CFEE);
        }
        
        .loginBox a.forgetLink {
            color: #ABABAB;
            cursor: pointer;
            float: right;
            font: 11px/20px Arial;
            text-decoration: none;
            vertical-align: middle;
            /*忘記密碼*/
        }
        
        .loginBox a.forgetLink:HOVER {
            color: #000000;
            text-decoration: none;
            /*忘記密碼*/
        }
        
        .loginBox input#remember {
            vertical-align: middle;
        }
        
        .loginBox label[for="remember"] {
            font: 11px Arial;
        }
    </style>
</head>
<body>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
    <img src="<%=request.getContextPath() %>/resource/images/food.jpg" height="100" width="100"><font size="+3">EATIN 登入頁面</font>
<hr><p>
<h3>Welcome to EATIN!</h3>
<a href="<%=request.getContextPath() %>/front_end/commodity/comindex.jsp">回商城首頁</a>
    <div class="wrapper">
        <form action="<%=request.getContextPath() %>/member/mem.do" method="post">
            <div class="loginBox">
                <div class="loginBoxCenter">
                    <p><label for="username">使用者名稱：</label></p>
                    <!--autofocus 規定當頁面載入時按鈕應當自動地獲得焦點。 -->
                    <!-- placeholder提供可描述輸入欄位預期值的提示資訊-->
                    <p><input type="text" id="text" name="memAccount" class="loginInput" autofocus="autofocus" required="required" autocomplete="off" placeholder="請輸入EATIN會員帳號" value="" /></p>
                    <!-- required 規定必需在提交之前填寫輸入欄位-->
                    <p><label for="password">密碼：</label></p>
                    <p><input type="password" id="password" name="memPassword" class="loginInput" required="required" placeholder="請輸入EATIN會員密碼" value="" /></p>
                    <p><a class="forgetLink" href="#">忘記密碼?</a></p>
                    <input id="remember" type="checkbox" name="remember" />
                    <label for="remember">記住登入狀態</label>
                </div>
                <div class="loginBoxButtons">

					<input type="submit" value="登入" class="loginBtn">
					<input type="hidden" name="action" value="login">
                   
                    <div> <a href="<%=request.getContextPath() %>/back_end/member/addMem.jsp">新使用者註冊</a></div>
                </div>
            </div>
        </form>
    </div>
</body>


</html>