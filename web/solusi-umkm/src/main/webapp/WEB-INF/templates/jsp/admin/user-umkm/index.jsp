<%-- 
    Document   : index
    Created on : Jan 9, 2014, 8:50:30 PM
    Author     : sidratul
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="id_ID" scope="session"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User UMKM</title>
    </head>
    <body>
        <div class="col-lg-12">
            <h2>USER UMKM 
                <small>
                    <c:choose>
                        <c:when test="${aktif==1}">Aktif</c:when>
                        <c:otherwise>Non Aktif</c:otherwise>
                    </c:choose>
                </small>
            </h2>
            <ol class="breadcrumb">
              <li class="active"><i class="fa fa-users"></i> Daftar User Berdasarkan Anggota Asosiasi UMKM</li>
            </ol>
        </div>
        <c:choose>
            <c:when test="${empty listUserUmkm}">
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
                          <th>username</th>
                          <th>Password</th>
                          <th>Umkm</th>
                          <th>Terakhir Login</th>
                          <!--<th></th>-->
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach items="${listUserUmkm}" var="luu" varStatus="i" >
                          <tr>
                              <td>${i.count}</td>
                              <td>${luu.username}</td>
                              <td>${luu.password}</td>
                              <td>${luu.umkm.namaUmkm}</td>
                              <td>
                                <c:choose>
                                    <c:when test="${empty luu.terakhirLogin}">
                                        <i><small>(user belum pernah login)</small></i>
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${luu.terakhirLogin}" />
                                    </c:otherwise>
                                </c:choose>
                              </td>
                              <!--<td><a title="edit" alt="edit" href="input-user?id=${luu.id}"><i class="fa fa-edit"></i></a></td>-->
                              <!--<td><a title="hapus" alt="hapus" href="hapus-user?id=${luu.id}" onclick="tampil_confirm()"><i class="fa fa-trash-o"></i></a></td>-->
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
