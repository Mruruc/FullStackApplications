
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="./css/style.css">
        <title>Home</title>
    </head>
    <body>
        <%@include file="component/header.jsp"%>
         <main class="container main-height">
             <p> Hello ${sessionScope.user.username} Welcome to the home page!</p>

         </main>
         <%@include file="component/footer.jsp"%>
    </body>
</html>
