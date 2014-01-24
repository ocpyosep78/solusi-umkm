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
        <div class="row">
            <div class="col-lg-12 ">
                <h1 class="page-header">Artikel <small>Index</small></h1>
            </div>
        </div>
        <c:forEach items="${listArtikel}" var="la">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default" >
                        <div class="panel-heading capital">
                            <a href="detail?id=${la.id}"><strong>${la.judul}</strong></a></div>
                        <div class="panel-body isi-artikel"> 
                            <c:if test="${!empty la.namaFoto}">
                                <span class="pull-left">
                                    <a href="<%= request.getContextPath() %>/upload-file/foto/${la.namaFoto}">
                                        <img src="<%= request.getContextPath() %>/upload-file/foto/${la.namaFoto}" class="img-responsive img-index-artikel" >
                                    </a>
                                </span>
                            </c:if>
                            <c:choose>
                                <c:when test="${fn:length(la.isi) > 390}">
                                    ${fn:substring(la.isi,0,390)}... <a href="detail?id=${la.id}"> <small><i>(Baca Selengkapnya)</i></small></a>
                                </c:when>
                                <c:otherwise>
                                    ${la.isi}
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>    
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.js"></script>
        <script>
            $(document).ready(function() {
                $('.active').removeClass('active');
                
                $("#menu-artikel").addClass("active");
            });
        </script>
    </body>
    
</html>
