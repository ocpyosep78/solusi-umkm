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
        <div class="row">
            <div class="col-lg-12">
                <c:choose>
                    <c:when test="${empty artikel.id}">
                        <c:set var="ket" value="Input"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="ket" value="Edit"/>
                    </c:otherwise>
                </c:choose>
                <h1 class="page-header">Artikel <small>${ket}</small></h1>
                <ol class="breadcrumb">
                  <li class="active"><i class="fa fa-download"></i> ${ket} artikel</li>
                </ol>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <c:if test="${not empty listPesan}">
                    <div class="row">
                        <div class="col-lg-12">
                             <c:forEach items="${listPesan}" var="lp" varStatus="i" >
                                <div class="alert alert-${lp.jenisPesan} alert-dismissable">
                                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                                    <span class="kapital"> ${lp.isiPesan}</span>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">              
                <sf:form class="form-horizontal" role="form" modelAttribute="artikel" action="input-artikel" enctype="multipart/form-data">
                    <sf:hidden path="id"/>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">Judul</label>
                      <div class="col-xs-4">
                          <sf:input type="text" class="form-control" path="judul" placeholder="Judul Artikel" required="required"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">Isi</label>
                      <div class="col-lg-8">
                          <sf:textarea type="text" rows="15px" class="form-control" path="isi" placeholder="Isi" required="required"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">Foto</label>
                      <div class="col-xs-4" id="btnfile">
                          <div class="input-group groupbtnfile">
                            <input type="file" class="btn" name="foto" placeholder="foto"  accept="image/x-png,image/jpeg" max-size="5000000"/>
                          </div>
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
            </div>
        </div>
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.js"></script>
    </body>
</html>
