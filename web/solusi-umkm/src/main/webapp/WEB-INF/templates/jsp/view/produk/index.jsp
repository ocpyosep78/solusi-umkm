<%-- 
    Document   : index
    Created on : Dec 21, 2013, 11:52:43 AM
    Author     : sidratul
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="id">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UMKM</title>
    </head>
    <body>
        <h1>Produk <small>Index</small></h1>
        <hr>
    <form class="form-horizontal">
        <div class="form-group">
                <div class="col-lg-5">
                  <input type="text" class="form-control" placeholder="Kata Kunci">
                </div>
                <div class="col-lg-5">
                    <select class="form-control">
                        <option value="">Kategori Produk</option>
                        <c:forEach items="${listKategoriProduk}" var="lkp">
                            <option value="${lkp.id}">${lkp.jenisProduk}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-lg-2">
                    <input type="submit" class="btn btn-info" value="Cari">
                </div>
        </div>
      </form>  
        <hr>
        <c:forEach items="${listProduk}" var="lp">
            <div class="col-md-12 p-padd breadcrumb" >
                <div class="col-lg-4">
                    <c:choose>
                        <c:when test="${empty lp.fotos}">
                            <img src="<%= request.getContextPath() %>/upload-file/foto/default-produk.jpg" class="img-thumbnail foto-produk">
                        </c:when>
                        <c:otherwise>
                            <img src="<%= request.getContextPath() %>/upload-file/foto/${lp.fotos[0].namaFile}" class="img-thumbnail foto-produk">
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="">
                    <table class="capital">
                        <tr>
                            <td>Kode Produk</td>
                            <td width="30px" > : </td>
                            <td>${lp.kodeProduk}</td>
                        </tr>
                        <tr>
                            <td>nama produk</td>
                            <td> : </td>
                            <td><div>${lp.namaProduk}</div></td>
                        </tr>
                        <tr>
                            <td>Harga</td>
                            <td> : </td>
                            <td>
                                <fmt:setLocale value="id_ID" scope="session"/>
                                <div><fmt:formatNumber value="${lp.harga}" type="currency"/></div>
                            </td>
                        </tr>
                        <tr>
                            <td>Ketegori Produk</td>
                            <td> : </td>
                            <td>
                                <div>${lp.kategoriProduk.jenisProduk}</div>
                            </td>
                        </tr>
                        <tr>
                            <td>UMKM</td>
                            <td> : </td>
                            <td><div>${lp.umkm.namaUmkm}</div></div></td>
                        </tr>
                        <tr>
                            <td>no telepon</td>
                            <td> : </td>
                            <td>
                                <di>
                                    <c:choose>    
                                        <c:when test="${empty lp.umkm.noTelp}">
                                            <i><small>(nomor telepon tidak tersedia)</small></i>
                                        </c:when >
                                        <c:otherwise>
                                            ${lp.umkm.noTelp}
                                        </c:otherwise>
                                    </c:choose>
                                </di>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <div><i><small>update tanggal <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${lp.tglUpdateProduk}" /></small></i></div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <div>
                                    <a href="detail?id=${lp.id}" class="btn btn-sm btn-info">Lihat Produk</a>
                                    <a href="<%= request.getContextPath() %>/view/umkm/detail?id=${lp.umkm.id}" class="btn btn-sm btn-info">Lihat UMKM</a>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <hr>
        </c:forEach>    

    </body>
</html>
