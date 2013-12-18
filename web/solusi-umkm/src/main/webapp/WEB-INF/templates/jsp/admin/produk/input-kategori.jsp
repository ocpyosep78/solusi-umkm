<%-- 
    Document   : input-umkm
    Created on : Dec 18, 2013, 9:43:51 PM
    Author     : sidratul
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="col-lg-12">
            <c:choose>
                <c:when test="${empty kategoriProduk.id}">
                    <c:set var="ket" value="Input"/>
                </c:when>
                <c:otherwise>
                    <c:set var="ket" value="Edit"/>
                </c:otherwise>
            </c:choose>
            <h2>UMKM <small> ${ket} Kategori</small>
            </h2>
            <ol class="breadcrumb">
              <li class="active"><i class="fa fa-download"></i> ${ket} kategori Produk</li>
            </ol>
        </div>
        
        <sf:form class="form-horizontal" role="form" modelAttribute="kategoriProduk" action="input-kategori">
            <sf:hidden path="id"/>
            <div class="form-group">
              <label class="col-sm-2 control-label">Jenis Kategori</label>
              <div class="col-xs-4">
                  <sf:input type="text" class="form-control" path="jenisProduk" placeholder="jenis kategori"/>
              </div>
            </div>
            
            <!--button-->
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Proses</button>
                <button type="reset" class="btn btn-danger">Hapus</button>
              </div>
            </div>
        </sf:form>
    </body>
</html>
