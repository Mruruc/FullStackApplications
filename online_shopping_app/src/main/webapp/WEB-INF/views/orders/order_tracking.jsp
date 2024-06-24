<%@ page import="com.mruruc.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Track Order</title>
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
    <%@include file="../component/header.jsp"%>
    <main class="container mb-4">
        <div class="row mb-4">
            <div class="col-12">
                <div class="card">
                    <div class="card-header bg-info text-white">
                        Track Your Order
                    </div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/orders" method="POST">
                            <div class="input-group mb-3">
                                <input type="search" name="orderId" placeholder="Enter Order Tracking Number" class="form-control">
                                <div class="input-group-append">
                                    <button class="btn btn-outline-secondary" type="submit">Search</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-12">
                <div class="p-3 border bg-light">
                    <h4>Product(s) Ordered:</h4>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                            List<Product> products = (List<Product>) request.getAttribute("products");
                            if (products != null) {
                                for (Product product : products) {
                        %>
                        <tr>
                            <td><%= product.getName() %></td>
                            <td><%= product.getQuantity() %></td>
                            <td><%= product.getPrice() %></td>
                        </tr>
                        <%}}%>
                        </tbody>
                    </table>

                    <h4>Order Status</h4>
                    <p>Order ID:${order.orderId}</p>
                    <p>Order ID:${order.orderDate}</p>
                    <br>
                    <p>Order Status:${order.orderStatus}</p>
                    <br>

                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="p-3 border bg-light">
                    <h3>Customer Support</h3>
                    <p>Have questions? Contact us:</p>
                    <div class="card mb-3">
                        <div class="card-body">
                            <div class="d-flex align-items-center mb-2">
                                <i class="bi bi-envelope-fill mr-2 text-info"></i>
                                <p class="mb-0">Email: <a href="mailto:support@gmail.com" class="text-info">support@gmail.com</a></p>
                            </div>
                            <div class="d-flex align-items-center">
                                <i class="bi bi-telephone-fill mr-2 text-info"></i>
                                <p class="mb-0">Phone: <a href="tel:123-456-789" class="text-info">123-456-789</a></p>
                            </div>
                        </div>
                    </div>
                    <button class="btn btn-info">Contact Support</button>
                </div>
            </div>
        </div>
    </main>
    <%@include file="../component/footer.jsp"%>
</body>
</html>
