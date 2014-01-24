<%-- 
    Document   : decorator
    Created on : Dec 20, 2013, 3:03:30 PM
    Author     : sidratul
--%>

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
        
        <link href="<%= request.getContextPath() %>/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
        <style>
            body{
                background: url(<%= request.getContextPath() %>/img/billie_holiday.png) repeat fixed;
            }
        </style>
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
            <li id="menu-artikel" class="active"><a href="<%= request.getContextPath() %>/view/artikel/index">Home</a></li>
            <li id="menu-umkm" class=""><a href="<%= request.getContextPath() %>/view/umkm/index">UMKM</a></li>
            <li id="menu-produk" class=""><a href="<%= request.getContextPath() %>/view/produk/index">Produk</a></li>
            <c:catch><c:set var="principal" value="${pageContext.request.userPrincipal.principal}" scope="request"/></c:catch>
            <c:if test="${!empty principal}">
                <% if (request.isUserInRole("ROLE_ADMIN")) { %>
                    <li><a class="pull-right" href="<%= request.getContextPath() %>/view/login/berhasil">Administrator Solusi UMKM </a></li>
                <% }else if (request.isUserInRole("ROLE_UMKM")) { %>
                   <li id="menu-umkm-saya" class=""><a class="pull-right" href="<%= request.getContextPath() %>/user/profil-umkm/detail"><i class="fa fa-users"></i> Profil Usaha Saya</a></li>
                   <li id="menu-produk-saya" class="">
                       <ul class="nav navbar-nav navbar-right navbar-user">
                        <li class="dropdown user-dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gift"></i> Produk Saya<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li id="menu-produk-saya-list" class=""><a href="<%= request.getContextPath() %>/user/produk/daftar-produk">Daftar Produk</a></li>
                                <li class="divider"></li>
                                <li id="menu-produk-saya-input"><a href="<%= request.getContextPath() %>/user/produk/input-produk">Input</a></li>
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
                                <li><a href="<%= request.getContextPath() %>/user/profil-umkm/detail"><i class="fa user"></i> Profile</a></li>
                                <li class="divider"></li>
                                <li><a href="<c:url value='/j_spring_security_logout'/>"><i class="fa fa-power-off"></i> Logout</a></li>
                            </ul>
                        </li>
                  </ul>
                </c:when>
                <c:otherwise>
                    <li id="menu-login" class=""><a class="" id="login" href="<%= request.getContextPath() %>/view/login/berhasil">Login</a></li>
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
        </div>
        
        <div class="col-lg-4">
            <div class="panel panel-default" >
                <div class="panel-heading capital">Waktu & Tangal</div>
                <div class="panel-body">
                    
                </div>            
            </div>
            
            <div class="panel panel-default" >
                <div class="panel-heading capital">Produk1</div>
                <div class="panel-body">
                    
                </div>            
            </div>
            
            <div class="panel panel-default" >
                <div class="panel-heading capital">Produk 2</div>
                <div class="panel-body">
                </div>            
            </div>
            <div class="panel panel-info" >
                <div class="panel-heading capital"><strong>Aplikasi Solusi UMKM Depok</strong></div>
                <div class="panel-body">

                </div>
                <div class="panel-footer">
                    <div  ><a href="#" >download</a></div>
                </div>
            
            </div>
            <c:if test="${empty principal}">
                <div class="panel panel-default" >
                <div class="panel-heading capital">Login</div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="<c:url value='/j_spring_security_check'/>" method="POST">
                        <div class="form-group">
                            <label class="col-lg-4 control-label">Username</label>
                            <div class="col-lg-8">
                              <input type="text" class="form-control" name="j_username" placeholder="Username">
                            </div>
                          </div>
                        <div class="form-group">
                          <label class="col-lg-4 control-label">Password</label>
                          <div class="col-lg-8">
                            <input type="password" class="form-control" name="j_password" placeholder="Password">
                          </div>
                        </div>
                        <div class="form-group">
                          <div class="col-lg-12 ">
                            <button type="submit" class="btn btn-default pull-right">Login</button>
                          </div>
                        </div>
                      </form>
                </div>            
            </div>
            </c:if>
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
    
    <script src="<%= request.getContextPath() %>/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="<%= request.getContextPath() %>/js/plugins/dataTables/dataTables.bootstrap.js"></script>


    <script>
    $(document).ready(function() {
        $('#dataTables-example').dataTable();
    });
    </script>
  </body>
</html>
