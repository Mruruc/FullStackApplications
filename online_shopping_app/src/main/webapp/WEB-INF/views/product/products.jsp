<%@ page import="com.mruruc.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="./css/style.css">
        <title>Products</title>
    </head>
    <body>
    <%@include file="../component/header.jsp"%>
    <br>
        <main class="container">
            <h1>Products Details</h1>
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                    <%for(Product product:(List<Product>)request.getAttribute("products")){%>
                <tr>
                    <td><%=product.getProductId()%></td>
                    <td>
                        <a href="./product-details?productId=<%=product.getProductId()%>" class="btn btn-link">
                            <%=product.getName()%>
                        </a>
                    </td>
                    <td><%=product.getDescription()%></td>
                    <td><%=product.getPrice()%></td>
                    <td><%=product.getQuantity()%></td>
                    <td>
                        <form action="shopping-cart" method="post">
                            <input type="number" name="productId" value="<%=product.getProductId()%>" hidden>
                            <input type="number" name="userId" value="${sessionScope.user.userId}" hidden>
                            <button type="submit" class="btn btn-primary">Add Shopping Cart</button>
                        </form>
                    </td>
                </tr>
                    <%}%>
            </table>
        </main>
        <%@include file="../component/footer.jsp"%>
    </body>
</html>
