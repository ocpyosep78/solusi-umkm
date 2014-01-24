<%-- 
    Document   : form
    Created on : Jan 9, 2014, 1:45:53 AM
    Author     : sidratul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form Login</title>
    </head>
    <body>
        <h1>Login <small>Formulir</small></h1>
        <c:if test="${not empty error}">            
            <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <span><strong>Login gagal !</strong> periksa kembali username dan password anda </span>
            </div>
        </c:if>
        <form class="form-horizontal" role="form" action="<c:url value='/j_spring_security_check'/>" method="POST">
            <div class="form-group">
              <label class="col-sm-2 control-label">Username</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" name="j_username" placeholder="Username">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">Password</label>
              <div class="col-sm-6">
                <input type="password" class="form-control" name="j_password" placeholder="Password">
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Login</button>
              </div>
            </div>
          </form>
        <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.js"></script>
        <script>
            $(document).ready(function() {
                $('.active').removeClass('active');
                
                $("#menu-login").addClass("active");
            });
        </script>
    </body>
</html>
