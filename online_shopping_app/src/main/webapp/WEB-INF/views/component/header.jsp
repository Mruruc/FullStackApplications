<header class="alert alert-primary">
    <div class="container">
        <h1>#ManageProduct</h1>
    </div>
</header>
<div class="container">
    <a href="./" class="px-4">Home</a>
    <%if (session.getAttribute("user") == null) { %>
    <a href="./registration" class="px-4">Register</a>
    <a href="./login" class="px-4">Login</a>

    <%} else {%>
    <a href="./products" class="px-4">Products</a>
    <a href="./products-price-range" class="px-4"> Filter By Price</a>
    <a href="./new-product" class="px-4">Add Product</a>
    <a href="shopping-cart?userId=${sessionScope.user.userId}">Cart</a>
    <a href="${pageContext.request.contextPath}/orders">Track Your Order</a>
    <a href="./logout" class="px-4">Logout</a>
    <%}%>
</div>
<br><br>