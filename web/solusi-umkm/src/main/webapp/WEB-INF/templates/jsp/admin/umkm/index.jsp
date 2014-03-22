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
        
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">UMKM <small>Tampil</small></h1>
                <ol class="breadcrumb">
                    <li class="active"><i class="fa fa-users"></i> Daftar anggota asosiasi UMKM</li>
                </ol>
            </div>
        </div>
        
        <div class="row">
            <div class="col-lg-12">
                <c:if test="${not empty listPesan}">
                    <div class="row">
                        <div class="col-lg-12">
                             <c:forEach items="${listPesan}" var="lp" varStatus="i" >
                                <div class="alert alert-${lp.jenisPesan} alert-dismissable">
                                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                                    <span class="kapital"> ${lp.isiPesan}</span>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
            </div>
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
                <div class="row">
                    <div class="col-lg-12">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                              <thead>
                                <tr>
                                  <th>#</th>
                                  <th>Kode</th>
                                  <th>Nama</th>
                                  <th>Pemilik</th>
                                  <th>Username</th>
                                  <th>Keterangan</th>
                                  <th>Visi</th>
                                  <th>Misi</th>
                                  <th>Alamat</th>
                                  <th>Telepon</th>
                                  <th>Email</th>
                                  <th>Lihat</th>
                                  <th>Edit</th>
                                  <th>Hapus</th>
                                </tr>
                              </thead>
                              <tbody>
                                <c:forEach items="${listUmkm}" var="lu" varStatus="i" >
                                  <tr>
                                      <td>${i.count}</td>
                                      <td>${lu.kodeUmkm}</td>
                                      <td>${lu.namaUmkm}</td>
                                      <td>${lu.pemilikUmkm}</td>
                                      <td>${lu.username}</td>
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
                    </div>
                </div>
           </c:otherwise>
        </c:choose>
    </body>
</html>
