<%-- 
    Document   : form
    Created on : Jan 9, 2014, 1:45:53 AM
    Author     : sidratul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form Login</title>
    </head>
    <body>
        <form class="form-horizontal" role="form">
            <div class="form-group">
              <label class="col-sm-2 control-label">Username</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" placeholder="Username">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">Password</label>
              <div class="col-sm-6">
                <input type="password" class="form-control" placeholder="Password">
              </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Login</button>
              </div>
            </div>
          </form>
    </body>
</html>
