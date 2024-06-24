<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="./css/style.css">
        <title>Product</title>
    </head>
    <body>
          <%@include file="../component/header.jsp"%>
          <main class="container main-height">
             <div class="row">
                 <h1>Product Details:</h1>

                 <div class="col">
                     <table class="table">
                         <tbody>
                                <tr>
                                    <td>Product ID</td>
                                    <td>${product.productId}</td>
                                </tr>
                                <tr>
                                    <td>Name</td>
                                    <td>${product.name}</td>
                                </tr>
                                <tr>
                                    <td>Description</td>
                                    <td>${product.description}</td>
                                </tr>
                                <tr>
                                    <td>Price</td>
                                    <td>${product.price}</td>
                                </tr>
                                <tr>
                                    <td>Quantity</td>
                                    <td>${product.quantity}</td>
                                </tr>
                               <tr>
                                   <a href="./edit-product?productId=${product.productId}" class="btn btn-info">
                                       Edit
                                   </a>
                               </tr>
                         </tbody>

                     </table>
                 </div>
                 <div class="col"></div>
             </div>
          </main>
          <%@include file="../component/footer.jsp"%>
    </body>
</html>
