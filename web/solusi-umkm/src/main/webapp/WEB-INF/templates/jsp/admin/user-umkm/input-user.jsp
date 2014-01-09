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
        <title>Input User</title>
    </head>
    <body>
        <div class="col-lg-12">
            <c:choose>
                <c:when test="${empty userUmkm.id}">
                    <c:set var="ket" value="Input"/>
                </c:when>
                <c:otherwise>
                    <c:set var="ket" value="Edit"/>
                </c:otherwise>
            </c:choose>
            <h2>User <small>${ket}</small></h2>
            <ol class="breadcrumb">
              <li class="active"><i class="fa fa-download"></i> ${ket} user berdasarkan asosiasi UMKM</li>
            </ol>
        </div>
        
        <sf:form class="form-horizontal" role="form" modelAttribute="userUmkm" action="input-user">
            <sf:hidden path="id"/>
            <div class="form-group">
              <label class="col-sm-2 control-label">Username</label>
              <div class="col-xs-4">
                  <sf:input type="text" class="form-control" path="username" placeholder="Username" required=""/>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">Password</label>
              <div class="col-xs-4">
                  <sf:input type="password" class="form-control" path="password" placeholder="Password" required=""/>
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
            <c:choose>
                <c:when test="${empty userUmkm.id}">
                    <sf:hidden path="aktif" value="1"/>
                    <sf:hidden path="peran" value="ROLE_UMKM"/>
                </c:when>
                <c:otherwise>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Status Aktif</label>
                        <div class="col-xs-4">
                            <sf:select path="aktif" class="form-control" required="required">
                                <sf:option value="">Status </sf:option>
                                <sf:option value="1">Aktif</sf:option>
                                <sf:option value="0">Non Aktif</sf:option>
                              </sf:select>
                        </div>
                  </div>
                </c:otherwise>
            </c:choose>
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
