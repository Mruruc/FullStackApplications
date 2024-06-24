<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="./css/style.css">
        <title>Login</title>
    </head>
    <body>
        <%@include file="../component/header.jsp"%>
        <main class="container main-height">
            <div class="row">
                <h3>
                    Do Not you have an account? <a href="./registration">Register</a>
                </h3>
                <div class="col">
                    <form action="./login-credential" method="post">
                        <div class="form-group">
                            <label for="email">Email address</label>
                            <input type="email" class="form-control" name="username" id="email" >
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                        <button type="submit" class="btn btn-primary mt-4">Submit</button>
                    </form>
                    <p class="text-danger">${errorMessage}</p>
                </div>
                <div class="col"></div>
            </div>
        </main>
        <%@include file="../component/footer.jsp"%>
    </body>
</html>
