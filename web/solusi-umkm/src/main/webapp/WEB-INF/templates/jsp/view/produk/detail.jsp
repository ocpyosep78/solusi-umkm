
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
        <div class="col-lg-12">
            <h1 class="page-header">Produk <small>Detail</small></h1>
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
                                        <c:when test="${empty lp.keteranganProduk}">
                                            <i><small>(tidak ada keterangan)</small></i>
                                        </c:when >
                                        <c:otherwise>
                                            ${lp.keteranganProduk}
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
                        <ol class="breadcrumb">
                            <c:choose>
                                <c:when test="${fn:length(produk.fotos) > 0}">
                                    <c:forEach items="${produk.fotos}" var="f">
                                        <li class="col-lg-4">
                                            <a href="<%= request.getContextPath() %>/upload-file/foto/${f.namaFile}"><img src="<%= request.getContextPath() %>/upload-file/foto/${f.namaFile}" class="img-thumbnail foto-produk"></a>
                                        </li>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <li class="active"><i class="glyphicon glyphicon-file "></i> Produk Ini Belum Memiliki Foto</li>
                                </c:otherwise>
                            </c:choose>
                          </ol>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.js"></script>
        <script>
            $(document).ready(function() {
                $('.active').removeClass('active');
                
                $("#menu-produk").addClass("active");
            });
        </script>
    </body>
</html>
