
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

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
   
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/bootstrap-wysihtml5.css">


    <!-- Page-Level Plugin CSS - Tables -->
    <link href="<%= request.getContextPath() %>/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <!-- SB Admin CSS - Include with every page -->
    <link href="<%= request.getContextPath() %>/css/sb-admin.css" rel="stylesheet">
</head>

<body>

    <div id="wrapper">

        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%= request.getContextPath() %>/admin/umkm/index">Administrator Solusi UMKM</a>
            </div>

            <ul class="nav navbar-top-links navbar-right">
                <li><a href="<%= request.getContextPath() %>/">Solusi UMKM</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
<!--                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a></li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>-->
                        <li class="divider"></li>
                <li><a href="<c:url value='/j_spring_security_logout'/>"><i class="fa fa-power-off"></i> Logout</a></li>
                    </ul>             
                </li>
              
            </ul>

        </nav>

        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li>
                        <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Artikel<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="<%= request.getContextPath() %>/admin/artikel/index">Index Artikel</a></li>
                            <li><a href="<%= request.getContextPath() %>/admin/artikel/input-artikel">Input Artikel</a></li>
                        </ul>                       
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> UMKM<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="<%= request.getContextPath() %>/admin/umkm/index">Index UMKM</a></li>
                            <li><a href="<%= request.getContextPath() %>/admin/umkm/input-umkm">Input UMKM</a></li>
                        </ul>                       
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Produk<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="<%= request.getContextPath() %>/admin/produk/index">Index Produk</a></li>
                            <li><a href="<%= request.getContextPath() %>/admin/produk/kategori">Kategori Produk</a></li>
                            <li><a href="<%= request.getContextPath() %>/admin/produk/input-kategori">Input Kategori Produk</a></li>
                        </ul>                       
                    </li>
                    <li>
                       <a href="<%= request.getContextPath() %>/admin/user-umkm/index?aktif=1">Index User</a>
<!--                        <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> User<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="<%= request.getContextPath() %>/admin/user-umkm/index?aktif=1">Index User</a></li>
                            <li><a href="<%= request.getContextPath() %>/admin/user-umkm/index?aktif=0">Non Aktif User</a></li>
                            <li><a href="<%= request.getContextPath() %>/admin/user-umkm/input-user">Input User</a></li>
                        </ul>                       -->
                    </li>
                </ul>
                <!-- /#side-menu -->
            </div>
            <!-- /.sidebar-collapse -->
        </nav>
        <!-- /.navbar-static-side -->

        <div id="page-wrapper" >
            <sitemesh:write property='body'/>
        </div>
        

    </div>
    <!-- /#wrapper -->

    <!-- Core Scripts - Include with every page -->
    
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/bootstrap.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/aplikasi.js"></script>
        
            
    <script src="<%= request.getContextPath() %>/js/plugins/metisMenu/jquery.metisMenu.js"></script>

    <!-- Page-Level Plugin Scripts - Tables -->
    <script src="<%= request.getContextPath() %>/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="<%= request.getContextPath() %>/js/plugins/dataTables/dataTables.bootstrap.js"></script>

    <!-- SB Admin Scripts - Include with every page -->
    <script src="<%= request.getContextPath() %>/js/sb-admin.js"></script>
    <script src="<%= request.getContextPath() %>/js/bootstrap-wysihtml5"></script>
    

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#dataTables-example').dataTable();
    });
    </script>

</body>

</html>
