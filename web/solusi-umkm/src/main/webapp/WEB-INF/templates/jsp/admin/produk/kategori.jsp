<%-- 
    Document   : index
    Created on : Dec 18, 2013, 10:15:21 AM
    Author     : sidratul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="col-lg-12">
            <h2>UMKM <small>Index</small></h2>
            <ol class="breadcrumb">
              <li class="active"><i class="fa fa-qrcode"></i> Daftar anggota asosiasi UMKM</li>
            </ol>
        </div>
        <c:choose>
            <c:when test="${empty listKategoriProduk}">
                <div class="col-lg-12">
                    <ol class="breadcrumb">
                      <li class="active"><i class="fa fa-arrow-circle-o-right"></i> Data Kosong</li>
                    </ol>
                </div>
            </c:when>
            <c:otherwise>
                <div class="table-responsive col-lg-5">
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>Kategori</th>
                          <th colspan="2"></th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${listKategoriProduk}" var="lk" varStatus="i" >
                          <tr>
                              <td>${i.count}</td>
                              <td>${lk.jenisProduk}</td>
                              <td><a title="edit" alt="edit" href="input-kategori?id=${lk.id}"><i class="fa fa-edit"></i></a></td>
                              <td><a title="hapus" alt="hapus" href="hapus-kategori?id=${lk.id}"><i class="fa fa-trash-o"></i></a></td>
                          </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
