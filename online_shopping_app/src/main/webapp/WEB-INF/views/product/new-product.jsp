<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="./css/style.css">
        <title>Add Product</title>
    </head>
    <body>
        <%@include file="../component/header.jsp"%>
        <main class="container main-height">
            <h2>Add New Product</h2>

            <form action="./add-product" method="post">
                <div class="row form-group">
                    <label for="p_name" class="col-md-4">Product Name</label>
                    <div class="col-md-8">
                        <input type="text" name="name" id="p_name"  class="col-md-8"/>
                    </div>
                </div>
                <div class="row form-group">
                    <label for="p_desc" class="col-md-4">Product Description</label>
                    <div class="col-md-8">
                        <textarea type="text" name="description" id="p_desc"  class="col-md-8"
                        placeholder="description of the product ..."
                        >
                        </textarea>
                    </div>
                </div>
                <div class="row form-group">
                    <label for="p_price" class="col-md-4">Product Price</label>
                    <div class="col-md-8">
                        <input type="text" name="price" id="p_price" class="col-md-8" />
                    </div>
                </div>

                <div class="row form-group">
                    <label for="quantity" class="col-md-4">Quantity</label>
                    <div class="col-md-8">
                        <input type="number" name="quantity" id="quantity" class="col-md-8" />
                    </div>
                </div>
                <input type="number" name="userId" value="${sessionScope.user.userId}" hidden>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>

        </main>
        <%@include file="../component/footer.jsp"%>

    </body>
</html>
