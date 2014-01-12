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
                    <div class="row col-lg-12">
                        <c:choose>
                            <c:when test="${!empty artikel.namaFoto}">
                                <span class="pull-left col-lg-3">
                                    <a href="<%= request.getContextPath() %>/upload-file/foto/${artikel.namaFoto}"><img src="<%= request.getContextPath() %>/upload-file/foto/${artikel.namaFoto}" class="img-responsive" ></a>
                                </span>
                                <div class="col-lg-9">
                            </c:when>
                            <c:otherwise>
                                <div class="col-lg-12">
                            </c:otherwise>
                        </c:choose>
                        
                            <div class="row">${artikel.isi}</div>
                            <c:if test="${!empty artikel.namaFile}">
                                <div class="row col-lg-12"><a href="<%= request.getContextPath() %>/upload-file/aplikasi/${artikel.namaFile}" class="btn pull-right"><i class="fa fa-download"></i> download file</a></div>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-lg-12 breadcrumb">
                        <div class="pull-right">
                            <a href="input-artikel?id=${artikel.id}" class="btn btn-info btn-sm"> Edit</a>
                            
                            <c:if test="${!empty artikel.namaFoto}">
                                <a href="hapus-foto?id=${artikel.id}" class="btn btn-danger btn-sm"> Hapus Foto</a>
                            </c:if>
                            <c:if test="${!empty artikel.namaFile}">
                                <a href="hapus-file?id=${artikel.id}" class="btn btn-danger btn-sm"> Hapus File</a>
                            </c:if>
                            <a href="hapus-artikel?id=${artikel.id}" class="btn btn-danger btn-sm"> Hapus Artikel</a>
                        </div>
                    </div>    
                            
                </div>
                
            </c:otherwise>
        </c:choose>
    </body>
</html>
