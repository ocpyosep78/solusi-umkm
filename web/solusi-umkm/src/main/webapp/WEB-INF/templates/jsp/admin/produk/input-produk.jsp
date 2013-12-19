<%-- 
    Document   : input-umkm
    Created on : Dec 18, 2013, 9:43:51 PM
    Author     : sidratul
--%>

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
                <c:when test="${empty produk.id}">
                    <c:set var="ket" value="Input"/>
                </c:when>
                <c:otherwise>
                    <c:set var="ket" value="Edit"/>
                </c:otherwise>
            </c:choose>
            <h2>Produk <small>${ket}</small></h2>
            <ol class="breadcrumb">
              <li class="active"><i class="fa fa-download"></i> ${ket} Produk</li>
            </ol>
        </div>
        
        <sf:form class="form-horizontal" role="form" modelAttribute="produk" action="input-produk">
            <sf:hidden path="id"/>
            <div class="form-group">
              <label class="col-sm-2 control-label">Kode Produk</label>
              <div class="col-xs-4">
                  <sf:input type="text" class="form-control" path="kodeProduk" placeholder="kode produk" required="required"/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">Nama Produk</label>
              <div class="col-xs-4">
                  <sf:input type="text" class="form-control" path="namaProduk" placeholder="nama produk" required=""/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">Kategori Produk</label>
              <div class="col-xs-4">
                  <sf:select path="kategoriProduk.id" class="form-control" required="required">
                    <sf:option value="">Pilih Kategori</sf:option>
                    <c:forEach items="${listKategoriProduk}" var="lkp">
                        <sf:option value="${lkp.id}">${lkp.jenisProduk}</sf:option>
                    </c:forEach>
                  </sf:select>
              </div>
            </div>
              <div class="form-group">
              <label class="col-sm-2 control-label">UMKM</label>
              <div class="col-xs-4">
                  <sf:select path="umkm.id" class="form-control" required="required">
                    <sf:option value="">Pilih UMKM</sf:option>
                    <c:forEach items="${listUmkm}" var="lu">
                        <sf:option value="${lu.id}">${lu.namaUmkm}</sf:option>
                    </c:forEach>
                  </sf:select>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">Harga</label>
              <div class="col-xs-4">
                  <div class="input-group">
                    <span class="input-group-addon">Rp</span>
                    <sf:input type="number" class="form-control" path="harga" placeholder="harga" min="100"/>
                  </div>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">Keterangan</label>
              <div class="col-xs-4">
                  <sf:textarea class="form-control" rows="2" path="keteranganProduk" placeholder="keterangan produk"/>
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
