
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="./css/style.css">
        <title>Internal Server Error</title>
    </head>
    <body>
        <%@include file="component/header.jsp"%>

        <main class="container main-height">
            <h2>Exception occurred....</h2>
            <p>Oops,Something went wrong. Please try again later.</p>
            <button class="btn btn-info" onclick="handleVisibility()">Exception detail</button>
            <pre class="three-column-summary visibility" id="exceptionMessage">
                ${requestScope.exception.printStackTrace()}
            </pre>
        </main>
        <%@include file="component/footer.jsp"%>

        <script>
            const handleVisibility=()=>{
                let element = document.getElementById("exceptionMessage");
                if (element.classList.contains("visibility")) {
                    element.classList.remove("visibility");
                } else {
                    element.classList.add("visibility");
                }
            }
        </script>
    </body>
</html>
