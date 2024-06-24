
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="./css/style.css">
        <title>Price Range</title>
    </head>
    <body>
        <%@include file="../component/header.jsp"%>
        <main class="container">
            <h1>Enter Price Range</h1>

            <form action="./products-by-price-range" method="GET">
                <div class="row form-group">
                    <label for="min" class="col-md-4">Minimum Price</label>
                   <div class="col-md-8">
                       <input type="text" name="minPrice" id="min" class="col-md-8"/>
                   </div>
                </div>
                <div class="row form-group">
                    <label for="max" class="col-md-4">Maximum Price</label>
                    <div class="col-md-8">
                        <input type="text" name="maxPrice" id="max" class="col-md-8"/>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>

        </main>
        <%@include file="../component/footer.jsp"%>
    </body>
</html>
