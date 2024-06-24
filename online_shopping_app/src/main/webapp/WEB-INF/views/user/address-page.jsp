<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
    <title>Registration</title>

</head>
<body>
<%@include file="../component/header.jsp" %>
<main class="container">
    <div class="row">
        <h1>Address Information:</h1>
        <b></b>
        <div class="col">
            <form action="./register-address" method="post">
                <div class="form-group">
                    <label for="street">Street</label>
                    <input type="text" class="form-control" name="street" id="street">
                </div>
                <div class="form-group">
                    <label for="city">City</label>
                    <input type="text" class="form-control" id="city" name="city">
                </div>
                <div class="form-group">
                    <label for="state">State</label>
                    <input type="text" class="form-control" id="state" name="state">
                </div>
                <div class="form-group">
                    <label for="zip">Zip Code</label>
                    <input type="text" class="form-control" id="zip" name="zip">
                </div>

                <div class="form-group">
                    <label for="country">country</label>
                    <input type="text" class="form-control" id="country" name="country">
                </div>
                <input type="number" name="userId" value="${user.userId}" hidden>
                <button type="submit" class="btn btn-primary mt-4">Submit</button>
            </form>
            <p class="text-danger">${errorMessage}</p>
        </div>
        <div class="col"></div>
    </div>
</main>
<%@include file="../component/footer.jsp" %>
</body>
</html>
