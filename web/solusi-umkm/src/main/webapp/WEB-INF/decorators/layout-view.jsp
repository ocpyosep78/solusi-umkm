<%-- 
    Document   : decorator
    Created on : Dec 20, 2013, 3:03:30 PM
    Author     : sidratul
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="id">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title><sitemesh:write property='title'/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="<%= request.getContextPath() %>/img/logo_kota_depok.jpg"/>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/bootstrap-theme.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/view.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font-awesome.min.css">

  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand " href="<%= request.getContextPath() %>/view/artikel/index">Solusi UMKM</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav">
            <li id="artikel" class=""><a href="<%= request.getContextPath() %>/view/artikel/index">Home</a></li>
            <li id="umkm" class=""><a href="<%= request.getContextPath() %>/view/umkm/index">UMKM</a></li>
            <li id="produk" class=""><a href="<%= request.getContextPath() %>/view/produk/index">Produk</a></li>
            <c:catch><c:set var="principal" value="${pageContext.request.userPrincipal.principal}" scope="request"/></c:catch>
            <c:if test="${!empty principal}">
                <% if (request.isUserInRole("ROLE_ADMIN")) { %>
                    <li><a class="pull-right" href="<%= request.getContextPath() %>/view/login/berhasil">Administrator Solusi UMKM </a></li>
                <% }else if (request.isUserInRole("ROLE_UMKM")) { %>
                   <li><a class="pull-right" href="<%= request.getContextPath() %>/user/profil-umkm/detail"><i class="fa fa-users"></i> Profil Usaha Saya</a></li>
                   <li>
                       <ul class="nav navbar-nav navbar-right navbar-user">
                        <li class="dropdown user-dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gift"></i> Produk Saya<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="<%= request.getContextPath() %>/user/produk/daftar-produk">Daftar Produk</a></li>
                                <li class="divider"></li>
                                <li><a href="<%= request.getContextPath() %>/user/produk/input-produk">Input</a></li>
                            </ul>
                        </li>
                        </ul>
                   </li>
                <% }%>
            </c:if>
          </ul>
          <ul class="nav navbar-nav pull-right">
              <c:choose>
                <c:when test="${!empty principal}">
                  <ul class="nav navbar-nav navbar-right navbar-user">
                        <li class="dropdown user-dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <%= request.getUserPrincipal().getName() %> <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#"><i class="fa user"></i> Profile</a></li>
                                <li class="divider"></li>
                                <li><a href="<c:url value='/j_spring_security_logout'/>"><i class="fa fa-power-off"></i> Logout</a></li>
                            </ul>
                        </li>
                  </ul>
                </c:when>
                <c:otherwise>
                    <li><a class="" id="login" href="<%= request.getContextPath() %>/view/login/berhasil">Login</a></li>
               </c:otherwise>
              </c:choose>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container -->
    </nav>

    <div class="container">

      <div class="row">
        <div class="col-lg-8">
            <sitemesh:write property='body'/>
          <!-- blog entry -->
<!--          <h1><a href="#">A Blog Home Template for Bootstrap 3</a></h1>
          <p class="lead">by <a href="index.php">Start Bootstrap</a></p>
          <hr>
          <p><span class="glyphicon glyphicon-time"></span> Posted on August 28, 2013 at 10:00 PM</p>
          <hr>
          <img src="http://placehold.it/900x300" class="img-responsive">
          <hr>
          <p>This is a very basic starter template for a blog homepage. It makes use of Glyphicons that are built into Bootstrap 3, and it makes use of the Pager at the bottom of the page. Make sure you get the Glyphicons files by downloading the entire <code>/fonts</code> directory that you can download in the source files or at <a href="http://getbootstrap.com">getbootstrap.com</a>. That directory has all of the web fonts in it which makes Glyphicons work.</p>
          <a class="btn btn-primary" href="#">Read More <span class="glyphicon glyphicon-chevron-right"></span></a>
                    
          <hr>--> 
        </div>
        
        <div class="col-lg-4">
<!--          <div class="well">
            <h4>Blog Search</h4>
            <div class="input-group">
              <input type="text" class="form-control">
              <span class="input-group-btn">
                <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
              </span>
            </div> /input-group 
          </div> /well 
          <div class="well">
            <h4>Popular Blog Categories</h4>
              <div class="row">
                <div class="col-lg-6">
                  <ul class="list-unstyled">
                    <li><a href="#dinosaurs">Dinosaurs</a></li>
                    <li><a href="#spaceships">Spaceships</a></li>
                    <li><a href="#fried-foods">Fried Foods</a></li>
                    <li><a href="#wild-animals">Wild Animals</a></li>
                  </ul>
                </div>
                <div class="col-lg-6">
                  <ul class="list-unstyled">
                    <li><a href="#alien-abductions">Alien Abductions</a></li>
                    <li><a href="#business-casual">Business Casual</a></li>
                    <li><a href="#robots">Robots</a></li>
                    <li><a href="#fireworks">Fireworks</a></li>
                  </ul>
                </div>
              </div>
          </div> /well 
          <div class="well">
            <h4>Side Widget Well</h4>
            <p>Bootstrap's default wells work great for side widgets! What is a widget anyways...?</p>
          </div> /well -->
        </div>
      </div>
      
      <hr>
      
      <footer>
        <div class="row">
          <div class="col-lg-12">
            <p>Copyright &copy; Universitas Pancasil 2013</p>
          </div>
        </div>
      </footer>

    </div><!-- /.container -->

    <!-- JavaScript -->
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/bootstrap.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/aplikasi.js"></script>

  </body>
</html>
