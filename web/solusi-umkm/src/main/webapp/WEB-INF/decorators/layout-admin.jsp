
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/admin.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font-awesome.min.css">
        
    </head>
    <body>
    <div id="wrapper">

      <!-- Sidebar -->
      <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<%= request.getContextPath() %>/admin/umkm/index">Administrator Solusi UMKM</a>
          <a class="navbar-brand" href="<%= request.getContextPath() %>/">Solusi UMKM</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav side-nav">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-users"></i> UMKM <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="<%= request.getContextPath() %>/admin/umkm/index">Index UMKM</a></li>
                <li><a href="<%= request.getContextPath() %>/admin/umkm/input-umkm">Input UMKM</a></li>
                <li><a href="<%= request.getContextPath() %>/admin/umkm/kategori">Kategori UMKM</a></li>
                <li><a href="<%= request.getContextPath() %>/admin/umkm/input-kategori">Input Kategori</a></li>
              </ul>
            </li>
            
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gift"></i> Produk <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="<%= request.getContextPath() %>/admin/produk/index">Index Produk</a></li>
                <li><a href="<%= request.getContextPath() %>/admin/produk/input-produk">Input Produk</a></li>
                <li><a href="<%= request.getContextPath() %>/admin/produk/kategori">Kategori Produk</a></li>
                <li><a href="<%= request.getContextPath() %>/admin/produk/input-kategori">Input Produk</a></li>
              </ul>
            </li>
            
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user-md"></i> User <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="<%= request.getContextPath() %>/admin/user-umkm/index?aktif=1">Index User</a></li>
                <li><a href="<%= request.getContextPath() %>/admin/user-umkm/index?aktif=0">Non Aktif User</a></li>
                <li><a href="<%= request.getContextPath() %>/admin/user-umkm/input-user">Input User</a></li>
              </ul>
            </li>
          </ul>
            
          <ul class="nav navbar-nav navbar-right navbar-user">
            <li class="dropdown user-dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <%= request.getUserPrincipal().getName() %> <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <!--<li><a href="#"><i class="fa user"></i> Profile</a></li>
                <li><a href="#"><i class="fa fa-gear"></i> Settings</a></li>-->
                <li class="divider"></li>
                <li><a href="<c:url value='/j_spring_security_logout'/>"><i class="fa fa-power-off"></i> Log Out</a></li>
              </ul>
            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </nav>

      <div id="page-wrapper">

        <div class="row">
          <div class="col-lg-12">
              <%--<decorator:body></decorator:body>--%>
              <sitemesh:write property='body'/>
          </div>
        </div><!-- /.row -->
        
      </div><!-- /#page-wrapper -->
      
    </div><!-- /#wrapper -->

        <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/bootstrap.js"></script>
    </body>
</html>
