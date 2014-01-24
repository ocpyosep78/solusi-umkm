<%-- 
    Document   : index
    Created on : Dec 18, 2013, 10:15:21 AM
    Author     : sidratul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Artikel <small>Detail</small></h1>           
            </div>
        </div>
        <c:choose>
            <c:when test="${empty artikel}">
                <div class="row">
                <div class="col-lg-12">
                    <ol class="breadcrumb">
                      <li class="active"><i class="fa fa-arrow-circle-o-right"></i> Data Tidak Ditemukan</li>
                    </ol>
                </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row">
                <div class="col-lg-12">
                    <div class="row">
                        <div class="col-lg-12">
                            <h3 class="capital" align="center"><a href="#"><strong>${artikel.judul}</strong></a></h3>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 isi-artikel">
                            <c:if test="${!empty artikel.namaFoto}">
                                <span class="pull-left">
                                    <a href="<%= request.getContextPath() %>/upload-file/foto/${artikel.namaFoto}"><img src="<%= request.getContextPath() %>/upload-file/foto/${artikel.namaFoto}" class="img-responsive" width="400px" ></a>
                                </span>
                            </c:if>
                            ${artikel.isi}

                        </div>
                    </div>
                </div>
                </div>
                
            </c:otherwise>
        </c:choose>
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.js"></script>
        <script>
            $(document).ready(function() {
                $('.active').removeClass('active');
                
                $("#menu-artikel").addClass("active");
            });
        </script>
    </body>
</html>
