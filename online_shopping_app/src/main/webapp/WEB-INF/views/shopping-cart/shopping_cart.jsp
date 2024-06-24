<%@ page import="com.mruruc.model.ShoppingCart" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.mruruc.model.Product" %>
            <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>Shopping Cart</title>
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/style.css">
</head>

<body>
    <%@include file="../component/header.jsp" %>
        <main class="container">
            <h1>Your Shopping Cart</h1>

            <%List<Product> cartItems = (List<Product>) request.getAttribute("cartItems");%>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                                <th>Actions</th>
                            </tr>
                        </thead>

                        <tbody>
                            <%for (Product product : cartItems) {%>
                                <tr>
                                    <td>
                                        <%= product.getName() %>
                                    </td>
                                    <td>
                                        <%= product.getDescription() %>
                                    </td>
                                    <td>
                                        <%= product.getPrice() %>
                                    </td>
                                </tr>
                                <%}%>
                        </tbody>

                        <tfoot>
                            <tr>
                                <td colspan="4">Total Amount</td>
                                <td colspan="2">
                                    <%= 1 %>
                                </td>
                            </tr>
                        </tfoot>

                    </table>
                    <form action="checkout" method="post">
                        <input type="submit" value="Checkout">
                    </form>
                    <a href="products">Continue Shopping</a>

        </main>
        <%@include file="../component/footer.jsp" %>
</body>

</html>