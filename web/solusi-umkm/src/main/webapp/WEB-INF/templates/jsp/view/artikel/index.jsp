<%-- 
    Document   : index
    Created on : Jan 12, 2014, 10:24:05 PM
    Author     : sidratul
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="id_ID" scope="session"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index Artikel</title>
    </head>
    <body>
        <h1>Artikel <small>Index</small></h1>
        <c:forEach items="${listArtikel}" var="la">
            <div class="col-lg-12 index-artikel breadcrumb">
                <h3 class="capital" ><a href="detail?id=${la.id}">${la.judul}</a></h3>
                <div class="row col-lg-12 artikel">
                <c:if test="${!empty la.namaFoto}">
                    <span class="pull-left">
                        <a href="<%= request.getContextPath() %>/upload-file/foto/${la.namaFoto}">
                            <img src="<%= request.getContextPath() %>/upload-file/foto/${la.namaFoto}" class="img-responsive artikel-img" >
                        </a>
                    </span>
                </c:if>
                <c:choose>
                    <c:when test="${fn:length(la.isi) > 500}">
                        ${fn:substring(la.isi,0,500)} <a href="detail?id=${la.id}">...Baca Selengkapnya</a>
                    </c:when>
                    <c:otherwise>
                        ${la.isi}
                    </c:otherwise>
                </c:choose>
                
                
                <c:if test="${!empty la.namaFile}">
                    <div class="row col-lg-12"><a href="<%= request.getContextPath() %>/upload-file/aplikasi/${la.namaFile}" class="btn pull-right"><i class="fa fa-download"></i> download file</a></div>
                </c:if>

                </div>
            </div>
        </c:forEach>    
    </body>
</html>
