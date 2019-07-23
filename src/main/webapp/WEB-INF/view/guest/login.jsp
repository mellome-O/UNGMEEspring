<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="/resource/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/guest/login.css">
</head>
<body>
    <!-- header -------------------------------------------------- -->
    <section id="header">

    </section>



    
    <!-- body----------------------------------------------------- -->
    <section id="main">
        <section class="login-form">
            <h1 class="d-none">로그인 폼</h1>
            <div class="api-login">
                <ul>
                    <li><a href="kakao"><img src="">카카오</a></li>
                    <li><a href="facebook"><img src="">페이스북</a></li>
                <li><a href="naver"><img src="">네이버</a></li>
                </ul>
            </div>
            <div class="login-id">
                <form action="index" method="GET">
                    <input type="email" placeholder="이메일주소" >
                    <input type="password" placeholder="비밀번호">
                    <input type="submit" value="로그인">
                </form>
            </div>
        </section>
        
        <div class="logo">
           <a href="index"><img src="resource/images/ummo/logo.jpg"></a>
        </div>
    </section>
    <!-- footer----------------------------------------------------- -->
	 <jsp:include page="../inc/footer.jsp"></jsp:include>
    </body>
</html>