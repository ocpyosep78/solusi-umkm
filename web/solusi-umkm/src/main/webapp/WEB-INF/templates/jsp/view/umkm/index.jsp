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
        <title>JSP Page</title>
    </head>
    <body>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">UMKM <small>Tampil</small></h1>
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
                                  <th>Keterangan</th>
                                  <th>Alamat</th>
                                  <th>Telepon</th>
                                  <th>Lihat</th>
                                </tr>
                              </thead>
                              <tbody>
                                <c:forEach items="${listUmkm}" var="lu" varStatus="i" >
                                  <tr>
                                      <td>${i.count}</td>
                                      <td>${lu.kodeUmkm}</td>
                                      <td>${lu.namaUmkm}</td>
                                      <td>${lu.pemilikUmkm}</td>
                                      <td>${lu.keteranganUmkm}</td>                                      
                                      <td>${lu.alamat}</td>
                                      <td>${lu.noTelp}</td>
                                      <td><a title="detail" alt="detail" href="detail?id=${lu.id}"><i class="fa fa-eye"></i></a></td>
                                  </tr>
                                </c:forEach>

                              </tbody>
                            </table>
                        </div>
                    </div>
                </div>
           </c:otherwise>
        </c:choose>
            <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.js"></script>
         <script>
            $(document).ready(function() {
                $('.active').removeClass('active');
                
                $("#menu-umkm").addClass("active");
            });
            
        </script>
    </body>
</html>
