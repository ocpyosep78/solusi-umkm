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
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">UMKM <small>Detail</small></h1>
                <ol class="breadcrumb">
                    <li class="active"><i class="fa fa-users"></i> Detail Produk</li>
                </ol>
            </div>
        </div>        
        <c:choose>
            <c:when test="${empty produk}">
                <div class="row">
                    <div class="col-lg-12">
                        <ol class="breadcrumb">
                          <li class="active"><i class="fa fa-arrow-circle-o-right"></i> Data Tidak Ditemukan</li>
                        </ol>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <div class="col-lg-12">

                <div class="table-responsive">
                    <table class="table table-responsive breadcrumb">
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
                            <td>Harga</td>
                            <td> : </td>
                            <td>
                                <fmt:setLocale value="id_ID" scope="session"/>
                                <div><fmt:formatNumber value="${produk.harga}" type="currency"/></div>
                            </td>
                        </tr>
                        <tr>
                          <td>UMKM</td>
                          <td>:</td>
                          <td>
                              <a href="<%= request.getContextPath() %>/view/umkm/detail?id=${produk.umkm.id}">${produk.umkm.namaUmkm}</a>
                          </td>
                        </tr>
                        <tr>
                            <td>Keterangan</td>
                            <td> : </td>
                            <td>
                                <div>
                                    <c:choose>    
                                        <c:when test="${empty produk.keteranganProduk}">
                                            <i><small>(tidak ada keterangan)</small></i>
                                        </c:when >
                                        <c:otherwise>
                                            ${produk.keteranganProduk}
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"><div><i><small>update tanggal <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${produk.tglUpdateProduk}" /></small></i></div></td>
                        </tr>
                    </table>
                    
                    <div class="col-lg-12">
                        <h3>Foto <small>produk</small></h3>
                        <div class="breadcrumb col-lg-12">
                            <c:choose>
                                <c:when test="${fn:length(produk.fotos) > 0}">
                                    <c:forEach items="${produk.fotos}" var="f">
                                        <div class="pull-left lproduk">
                                            <div class="row">
                                                <a href="<%= request.getContextPath() %>/upload-file/foto/${f.namaFile}"><img src="<%= request.getContextPath() %>/upload-file/foto/${f.namaFile}" class="img-responsive img-rounded foto-produk"></a>
                                            </div>
                                            <div class="row">
                                                <a href="<%= request.getContextPath() %>/upload-file/foto/${f.namaFile}" class="btn btn-sm btn-info"><i class="glyphicon glyphicon-zoom-in"> </i>lihat</a>
                                                <a href="<%= request.getContextPath() %>/admin/produk/hapus-foto?id=${f.id}&idProduk=${produk.id}&namaFile=${f.namaFile}" class="btn btn-sm btn-danger" onclick="return tampil_confirm()"><i class="glyphicon glyphicon-remove"> </i>Hapus</a>
                                            </div>
                                        </div>  
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="active"><i class="glyphicon glyphicon-file "></i> Produk Ini Belum Memiliki Foto</div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                        
                    <div class="">
                        <a title="edit" class="btn btn-primary" alt="edit" href="input-produk?id=${produk.id}">Edit <i class="fa fa-edit"></i></a>
                        <a title="hapus" class="btn btn-danger" alt="hapus" href="hapus-produk?id=${produk.id}" onclick="return tampil_confirm()">Hapus <i class="fa fa-trash-o"></i></a>
                    </div>

                </div>
                    </div>
                </div>  
            </c:otherwise>
        </c:choose>
    </body>
</html>
