<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
      <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
        <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
        <sec:authorize access="isAuthenticated()">
          <sec:authentication property="principal" var="principal" />
        </sec:authorize>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="${_csrf.parameterName}" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">
    <title>Milano</title>

    <!-- CSS 적용 경로 -->
    <link href="${path}/css/headerstyles.css" rel="stylesheet" />
    <link href="${path}/css/communityBoard.css" rel="stylesheet" />
    <link href="/css/style_user.css" rel="stylesheet" type="text/css" />
    <link href="/css/style_community_home.css" rel="stylesheet" type="text/css" />
    <link href="/css/style_community_detail.css" rel="stylesheet" type="text/css" />
    <link href="/css/style_community_social.css" rel="stylesheet" type="text/css" />
    <link href="/css/kakao_pay.css" rel="stylesheet" type="text/css" />
    <link href="/css/style_community_my_social.css" rel="stylesheet" type="text/css" />


    <!-- 부트스트랩 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js" integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK" crossorigin="anonymous"></script>

    <!-- include plugin -->
    <script src="[folder where script is located]/[plugin script].js"></script>

    <!-- 폰트 설정 -->
	<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Hahmlet:wght@100;300&display=swap"
      rel="stylesheet">
    <link
      href="https://fonts.googleapis.com/css2?family=Gowun+Batang:wght@400;700&family=Noto+Sans+KR:wght@100;300;400;500;700;900&family=Song+Myung&display=swap"
      rel="stylesheet" />

    <!-- 아이콘 폰트어썸 -->
    <script src="https://kit.fontawesome.com/decc5cea51.js" crossorigin="anonymous"></script>
    
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

    </head>
    
    <body>

        <header>

          <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #453675;">
            <!-- Brand -->
            <img alt="" src="/image/wanggawn-removebg-preview.png" style="width: 75px; height: 50px; margin-left: 15px; margin-bottom: 10px;">
            <a class="navbar-brand" href="/">
              <h1 style="color: white; font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; margin-left: 20px;">Milano</h1>
            </a>

            <!-- Toggler/collapsibe Button -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
              <span class="navbar-toggler-icon"></span>
            </button>

            <!-- Navbar links -->
            <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
              <ul class="navbar-nav mr-5">
                <c:choose>
                  <c:when test="${principal.user eq null}">
                    <li class="nav-item">
                      <a class="nav-link" href="/community/social-main"
                        style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">SOCIAL</a>
                    </li>
                    <li class="nav-item">
                      <div class="dropdown">
                        <button class="nav-link dropbtn"
                          style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">STORE
                          <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content">
                          <a href="/shop/mans_form"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">MAN's</a>
                          <a href="/shop/womans_form"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">WOMAN's</a>
                        </div>
                      </div>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="/security/login_form"
                        style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">LOGIN</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="/security/join_form"
                        style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white; margin-right: 20px;">JOIN</a>
                    </li>
                  </c:when>
                  <c:when test="${principal.user.role eq 'USER'}">
                    <!-- 로그인 성공하면(회원이면) 글 작성 가능 -->
                    <li class="nav-item">
                      <div class="dropdown">
                        <button class="nav-link dropbtn text-white" 
                        style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675;">SOCIAL
                        	<i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content bg-white">
                          <a href="/community/social-main"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">SOCIAL MAIN</a>
                          <a href="/community/user/${principal.user.id}"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">MY SOCIAL</a>
                          <a href="/board/write"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">WRITE</a>
                        </div>
                      </div>
                    </li>
                    <li class="nav-item">
                      <div class="dropdown">
                        <button class="nav-link dropbtn text-white"
                          style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675;">STORE
                          <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content bg-white">
                          <a href="/shop/mans_form"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">MAN's</a>
                          <a href="/shop/womans_form"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">
                            WOMAN's</a>
                        </div>
                      </div>
                    </li>
                    <li class="nav-item">
      
                      <a href="/shop/basket_form/${principal.user.id}" style="text-decoration: none;">
                        <button class="nav-link dropbtn text-white"
                          style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675;">BASKET
                          
                        </button>
                        </a>
                    
                    </li>
                   
                   <li class="nav-item">
                      <div class="dropdown">
                        <button class="nav-link dropbtn"
                          style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">MORE
                          <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content">
                          <a href="/user/update_form"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">MYPAGE</a>
                          <a href="/user/purchase_history/"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">HISTORY</a>
                        </div>
                      </div>
                    </li>
                    <li class="nav-item">
                      <!-- 시큐리티를 적용하면 자동 로그아웃 처리 된다. -->
                      <a class="nav-link text-white" href="/logout"
                        style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; margin-right: 20px;">LOGOUT</a>
                    </li>
                  </c:when>
                  <c:when test="${principal.user.role eq 'ADMIN'}">
                    <!-- 로그인 성공하면(회원이면) 글 작성 가능 -->
                    <li class="nav-item">
                      <a class="nav-link " href="/admin/graph-join"
                        style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">MANAGEMENT</a>
                    </li>
                    <li class="nav-item">
                      <div class="dropdown">
                        <button class="nav-link dropbtn " 
                        style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">SOCIAL
                        	<i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content">
                          <a href="/community/social-main"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">SOCIAL MAIN</a>
                          <a href="/community/user/${principal.user.id}"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">MY SOCIAL</a>
                          <a href="/board/write"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">WRITE</a>
                        </div>
                      </div>
                    </li>
                    <li class="nav-item">
                      <div class="dropdown">
                        <button class="nav-link dropbtn "
                          style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">STORE
                          <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content">
                          <a href="/shop/mans_form"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">MAN's</a>
                          <a href="/shop/womans_form"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">WOMAN's</a>
                          <a href="/shop/save_form"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">ADD
                            ITEM</a>
                        </div>
                      </div>
                    </li>
                    <li class="nav-item">
                      <a href="/shop/basket_form/${principal.user.id}" style="text-decoration: none;">
                        <button class="nav-link dropbtn text-white"
                          style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675;">BASKET
                        </button>
                        </a>
                    </li>
                   
                  <li class="nav-item">
                      <div class="dropdown">
                        <button class="nav-link dropbtn"
                          style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">MORE
                          <i class="fa fa-caret-down"></i>
                        </button>
                        <div class="dropdown-content">
                          <a href="/user/update_form"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">MYPAGE</a>
                          <a href="/user/purchase_history/"
                            style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">HISTORY</a>
                        </div>
                      </div>
                    </li>
                    <li class="nav-item">
                      <!-- 시큐리티를 적용하면 자동 로그아웃 처리 된다. -->
                      <a class="nav-link" href="/logout"
                        style="font-family: 'Black Han Sans', sans-serif; font-family: 'Hahmlet', serif; font-weight: bold; background-color: #453675; color: white;">LOGOUT</a>
                    </li>
                  </c:when>
                </c:choose>
              </ul>
            </div>
          </nav>
        </header>
        <br />
        <!-- 자바 스크립트 추가할 때는 그 페이지의 밑에 작성 -->
