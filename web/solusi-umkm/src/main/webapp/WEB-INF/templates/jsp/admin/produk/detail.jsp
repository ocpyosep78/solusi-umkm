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
            <h2>Produk <small>Detail</small></h2>
            <ol class="breadcrumb">
              <li class="active"><i class="fa fa-users"></i> Detail Produk</li>
            </ol>
        </div>
        <c:choose>
            <c:when test="${empty produk}">
                <div class="col-lg-12">
                    <ol class="breadcrumb">
                      <li class="active"><i class="fa fa-arrow-circle-o-right"></i> Data Tidak Ditemukan</li>
                    </ol>
                </div>
            </c:when>
            <c:otherwise>
                <div class="table-responsive">
                    <table class="table table-responsive">
                        <tr>
                          <td class="col-lg-2">Kode Produk</td>
                          <td class="col-lg-1">:</td>
                          <td>${produk.kodeProduk}</td>
                        </tr>
                        <tr>
                          <td>Nama Produk</td>
                          <td>:</td>
                          <td>${produk.namaProduk}</td>
                        </tr>
                        <tr>
                          <td>Kategori Produk</td>
                          <td>:</td>
                          <td>${produk.kategoriProduk.jenisProduk}</td>
                        </tr>
                        <tr>
                          <td>Nama UMKM</td>
                          <td>:</td>
                          <td>${produk.umkm.namaUmkm}</td>
                        </tr>
                        <tr>
                          <td>Harga</td>
                          <td>:</td>
                          <td>${produk.harga}</td>
                        </tr>
                        <tr>
                          <td>Keterangan</td>
                          <td>:</td>
                          <td>${produk.tglUpdateProduk}</td>
                        </tr>
                    </table>
                    <div class="well">
                        <a title="edit" class="btn btn-primary" alt="edit" href="input-produk?id=${produk.id}">Edit <i class="fa fa-edit"></i></a>
                        <a title="hapus" class="btn btn-danger" alt="hapus" href="hapus-produk?id=${produk.id}">Hapus <i class="fa fa-trash-o"></i></a>
                    </div>
                        
                    <img src="<%= request.getContextPath() %>/upload-file/foto/${produk.fotos[1].namaFile}" alt="" class="img-thumbnail">
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
