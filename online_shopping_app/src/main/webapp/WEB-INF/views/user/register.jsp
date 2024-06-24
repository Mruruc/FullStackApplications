<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="./css/style.css">
        <title>Register</title>
    </head>
    <body>
     <%@include file="../component/header.jsp"%>
     <main class="container main-height">
         <div class="row">

             <h3>Welcome Registration:</h3>

             <div class="col">
                 <form action="./register" method="post">
                     <div class="form-group">
                         <label for="email">Email address</label>
                         <input type="email" class="form-control" name="username" id="email">
                         <div class="error-message">${errorsMap.get("username")}</div>
                     </div>
                     <div class="form-group">
                         <label for="password">Password</label>
                         <input type="password" class="form-control" id="password" name="password" >
                         <div class="error-message">${errorsMap.get("password")}</div>
                     </div>
                     <div class="form-group">
                         <label for="passwordConfirm">Confirm Password</label>
                         <input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" >
                         <div class="error-message">${errorsMap.get("passwordConfirm")}</div>
                     </div>
                     <div class="form-group">
                         <label for="f_name">First Name</label>
                         <input type="text" class="form-control" id="f_name" name="firstName" >
                     </div>

                     <div class="form-group">
                         <label for="l_name">Last Name</label>
                         <input type="text" class="form-control" id="l_name" name="lastName" >
                     </div>

                     <div class="form-group">
                         <label for="phone">Phone Number</label>
                         <input type="text" class="form-control" id="phone" name="phoneNumber" >
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
