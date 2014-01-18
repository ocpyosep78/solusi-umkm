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
              <li class="active"><i class="fa fa-users"></i> Daftar anggota asosiasi UMKM</li>
            </ol>
        </div>
        <c:choose>
            <c:when test="${empty listUmkm}">
                <div class="col-lg-12">
                    <ol class="breadcrumb">
                      <li class="active"><i class="fa fa-arrow-circle-o-right"></i> Data Kosong</li>
                    </ol>
                </div>
            </c:when>
            <c:otherwise>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>Kode</th>
                          <th>Nama</th>
                          <th>Pemilik</th>
                          <th>Kategori</th>
                          <th>keterangan</th>
                          <th>visi</th>
                          <th>misi</th>
                          <th>alamat</th>
                          <th>Telepon</th>
                          <th>Email</th>
                          <th></th>
                          <th></th>
                          <th></th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${listUmkm}" var="lu" varStatus="i" >
                          <tr>
                              <td>${i.count}</td>
                              <td>${lu.kodeUmkm}</td>
                              <td>${lu.namaUmkm}</td>
                              <td>${lu.pemilikUmkm}</td>
                              <td>${lu.kategoriUmkm.jenisUmkm}</td>
                              <td>${lu.keteranganUmkm}</td>
                              <td>${lu.visi}</td>
                              <td>${lu.misi}</td>
                              <td>${lu.alamat}</td>
                              <td>${lu.noTelp}</td>
                              <td>${lu.email}</td>
                              <td><a title="detail" alt="detail" href="detail?id=${lu.id}"><i class="fa fa-eye"></i></a></td>
                              <td><a title="edit" alt="edit" href="input-umkm?id=${lu.id}"><i class="fa fa-edit"></i></a></td>
                              <td><a title="hapus" alt="hapus" href="hapus-umkm?id=${lu.id}" onclick="return tampil_confirm()"><i class="fa fa-trash-o"></i></a></td>
                          </tr>
                        </c:forEach>
                          
                      </tbody>
                    </table>
                </div>
           </c:otherwise>
        </c:choose>
    </body>
</html>
