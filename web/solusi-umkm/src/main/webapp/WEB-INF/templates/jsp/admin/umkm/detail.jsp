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
            <h2>UMKM <small>Detail</small></h2>
            <ol class="breadcrumb">
              <li class="active"><i class="fa fa-users"></i> Detail UMKM</li>
            </ol>
        </div>
        <c:choose>
            <c:when test="${empty umkm}">
                <div class="col-lg-12">
                    <ol class="breadcrumb">
                      <li class="active"><i class="fa fa-arrow-circle-o-right"></i> Data Tidak Ditemukan</li>
                    </ol>
                </div>
            </c:when>
            <c:otherwise>
                <div class="table-responsive">
                    <table class="table table-responsive breadcrumb">
                        <tr>
                          <td class="col-lg-2">Kode</td>
                          <td class="col-lg-1">:</td>
                          <td>${umkm.kodeUmkm}</td>
                        </tr>
                        <tr>
                          <td>Nama</td>
                          <td>:</td>
                          <td>${umkm.namaUmkm}</td>
                        </tr>
                        <tr>
                          <td>Pemilik</td>
                          <td>:</td>
                          <td>${umkm.pemilikUmkm}</td>
                        </tr>
                        <tr>
                          <td>Kategori</td>
                          <td>:</td>
                          <td>${umkm.kategoriUmkm.jenisUmkm}</td>
                        </tr>
                        <tr>
                          <td>Keterangan</td>
                          <td>:</td>
                          <td>${umkm.keteranganUmkm}</td>
                        </tr>
                        <tr>
                          <td>Visi</td>
                          <td>:</td>
                          <td>${umkm.visi}</td>
                        </tr>
                        <tr>
                          <td>misi</td>
                          <td>:</td>
                          <td>${umkm.misi}</td>
                        </tr>
                        <tr>
                          <td>alamat</td>
                          <td>:</td>
                          <td>${umkm.alamat}</td>
                        </tr>
                        <tr>
                          <td>Telepon</td>
                          <td>:</td>
                          <td>${umkm.noTelp}</td>
                        </tr>
                        <tr>
                          <td>Email</td>
                          <td>:</td>
                          <td>${umkm.email}</td>
                        </tr>
                    </table>
                        <a title="edit" class="btn btn-primary" alt="edit" href="input-umkm?id=${umkm.id}">Edit <i class="fa fa-edit"></i></a>
                        <a title="hapus" class="btn btn-danger" alt="hapus" href="hapus-umkm?id=${umkm.id}">Hapus <i class="fa fa-trash-o"></i></a>
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
