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
                <h1 class="page-header">Profil UMKM <small>Edit</small></h1>
            </div>
        </div>
        
        <div class="row">
            <div class="col-lg-12">
                <small>
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
                </small>
            </div>
        </div> 
        <div class="row">
            <div class="col-lg-12">
                <sf:form class="form-horizontal" role="form" modelAttribute="umkm" action="edit" >
                    <sf:hidden path="id"/>
                    <sf:hidden path="kodeUmkm"/>
                    
                    <div class="form-group">
                      <label class="col-sm-2 control-label">Nama UMKM</label>
                      <div class="col-xs-4">
                          <sf:input type="text" class="form-control" path="namaUmkm" placeholder="nama UMKM" required="required"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">Pemilik UMKM</label>
                      <div class="col-xs-4">
                          <sf:input type="text" class="form-control" path="pemilikUmkm" placeholder="pemilik UMKM" required="required"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">Username</label>
                      <div class="col-xs-4">
                          <sf:input type="text" class="form-control" path="username" placeholder="username" required="required"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">Password Baru</label>
                      <div class="col-xs-4">
                          <sf:input type="password" class="form-control" path="password" placeholder="password" required="required"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">Tulis Ulang Password</label>
                      <div class="col-xs-4">
                          <sf:input type="password" class="form-control" path="passwordCek" placeholder="Tulis Ulang Password" required="required"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">Password Lama</label>
                      <div class="col-xs-4">
                          <sf:input type="password" class="form-control" path="passwordLama" placeholder="password" required="required"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">Keterangan UMKM</label>
                      <div class="col-xs-4">
                          <sf:textarea class="form-control" rows="2" path="keteranganUmkm" placeholder="keterangan UMKM"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">Visi</label>
                      <div class="col-xs-4">
                          <sf:textarea class="form-control" rows="2" path="visi" placeholder="visi"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">misi</label>
                      <div class="col-xs-4">
                          <sf:textarea class="form-control" rows="2" path="misi" placeholder="misi"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">alamat</label>
                      <div class="col-xs-4">
                          <sf:textarea class="form-control" rows="2" path="alamat" placeholder="alamat"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">Telepon</label>
                      <div class="col-xs-4">
                          <sf:input type="tel" class="form-control" path="noTelp" placeholder="telephon"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="col-sm-2 control-label">Email</label>
                      <div class="col-xs-4">
                          <sf:input type="email" class="form-control" path="email" placeholder="email"/>
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
        <script>
            $(document).ready(function() {
                $('.active').removeClass('active');
                
                $("#menu-umkm-saya").addClass("active");
            });
        </script>
    </body>
</html>
