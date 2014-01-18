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
        <div class="col-lg-12">
            <h2>Artikel <small>Detail</small></h2>
            <ol class="breadcrumb">
              <li class="active"><i class="fa fa-users"></i> Detail Artikel</li>
            </ol>
        </div>
        <c:choose>
            <c:when test="${empty artikel}">
                <div class="col-lg-12">
                    <ol class="breadcrumb">
                      <li class="active"><i class="fa fa-arrow-circle-o-right"></i> Data Tidak Ditemukan</li>
                    </ol>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-lg-12">
                    <h3 class="capital" align="center">${artikel.judul}</h3>
                    <div class="row col-lg-12 artikel">
                        <c:if test="${!empty artikel.namaFoto}">
                            <span class="pull-left">
                                <a href="<%= request.getContextPath() %>/upload-file/foto/${artikel.namaFoto}"><img src="<%= request.getContextPath() %>/upload-file/foto/${artikel.namaFoto}" class="img-responsive" width="400px" ></a>
                            </span>
                        </c:if>
                            
                        
                        ${artikel.isi}
                        <c:if test="${!empty artikel.namaFile}">
                            <div class="row col-lg-12"><a href="<%= request.getContextPath() %>/upload-file/aplikasi/${artikel.namaFile}" class="btn pull-right"><i class="fa fa-download"></i> download file</a></div>
                        </c:if>
                        
                    </div>
                </div>
                
            </c:otherwise>
        </c:choose>
    </body>
</html>
