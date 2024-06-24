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
    <h2>Edit Product:</h2>

    <form  action="./edit-product?productId=${product.productId}" method="post">
        <input type="hidden" name="userId" value="${product.userId}"/>
        <div class="row form-group">
            <label for="p_name" class="col-md-4">Product Name</label>
            <div class="col-md-8">
                <input type="text" name="name" id="p_name" value="${product.name}" class="col-md-8"/>
                <p class="text-danger">${errorMap.get("name")}</p>
            </div>
        </div>
        <div class="row form-group">
            <label for="p_desc" class="col-md-4">Product Description</label>
            <div class="col-md-8">
                <input type="text" name="description" id="p_desc" value="${product.description}" class="col-md-8"/>
                <p class="text-bg-danger">${errorMap.get("description")}</p>
            </div>
        </div>
        <div class="row form-group">
            <label for="p_price" class="col-md-4">Product Price</label>
            <div class="col-md-8">
                <input type="text" name="price" id="p_price" class="col-md-8" value="${product.price}"/>
                <p class="text-bg-danger">${errorMap.get("price")}
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</main>
<%@include file="../component/footer.jsp"%>
<script>
/*
    const handleForm = (event) => {
        event.preventDefault();
        const formData=new FormData(event.target);
        const userId = formData.get("userId");

        let urlEncodedData = new URLSearchParams();
        urlEncodedData.append("name", formData.get("name"));
        urlEncodedData.append("description", formData.get("description"));
        urlEncodedData.append("price", formData.get("price"));
        urlEncodedData.append("userId", userId);

        const url = `./edit-product?productId=${product.productId}`;

        fetch(url, {
            method: "PUT",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: urlEncodedData,
        }).then(response => response.text())
            .then(data => {
                console.log(data);
                setTimeout(() => {
                    window.location.href = "./product-details?productId=" + productId;
                }, 1000);
              //  window.location.href = "./edit-product?productId=" + productId;
            })
            .catch(error => console.error('Error:', error)); // Error handling


    }
    */
</script>
</body>
</html>
