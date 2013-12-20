<%-- 
    Document   : index
    Created on : Dec 18, 2013, 10:15:21 AM
    Author     : sidratul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                    <div class="col-lg-12">
                        <h3>Foto <small>produk</small></h3>
                        <ol class="breadcrumb">
                            <c:choose>
                                <c:when test="${fn:length(produk.fotos) > 0}">
                                    <c:forEach items="${produk.fotos}" var="f">
                                        <li class="col-lg-4">
                                            <a href="<%= request.getContextPath() %>/upload-file/foto/${f.namaFile}"><img src="<%= request.getContextPath() %>/upload-file/foto/${f.namaFile}" alt="sdsdsd" class="img-thumbnail foto-produk"></a>
                                            <div class="col-lg-1">
                                                <a href="<%= request.getContextPath() %>/upload-file/foto/${f.namaFile}" class="btn btn-info"><i class="glyphicon glyphicon-zoom-in"> </i>lihat</a>
                                                <a href="<%= request.getContextPath() %>/admin/produk/hapus-foto?id=${f.id}&idProduk=${produk.id}&namaFile=${f.namaFile}" class="btn btn-danger"><i class="glyphicon glyphicon-remove"> </i>Hapus</a>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <li class="active"><i class="glyphicon glyphicon-file "></i> Produk Ini Belum Memiliki Foto</li>
                                </c:otherwise>
                            </c:choose>
                          </ol>
                    </div>
                    
                    <div class="">
                        <a title="edit" class="btn btn-primary" alt="edit" href="input-produk?id=${produk.id}">Edit <i class="fa fa-edit"></i></a>
                        <a title="hapus" class="btn btn-danger" alt="hapus" href="hapus-produk?id=${produk.id}">Hapus <i class="fa fa-trash-o"></i></a>
                    </div>

                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
