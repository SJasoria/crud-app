<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Inventory Tracking - Web App</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway:500,900|Quicksand">
        <link rel="stylesheet" href="/css/main.css">
    </head>
    <body>
        <div>
            <nav class="navbar navbar-default navbar-fixed-top">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        </button>
                    </div>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a class="bold" href="${pageContext.request.contextPath}/">Home</a></li>
                        <li><a class="bold" href="${pageContext.request.contextPath}/inventory-list">Inventory Item List</a></li>
                        <li><a class="bold" href="${pageContext.request.contextPath}/add">Add Item</a></li>
                        <li><a class="bold" href="${pageContext.request.contextPath}/edit">Edit Item</a></li>
                        <li><a class="bold" href="${pageContext.request.contextPath}/delete">Delete Item</a></li>
                        <li><a class="bold" href="${pageContext.request.contextPath}/downloadcsv">Download CSV</a></li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="heading">
            <h1>Inventory Tracking Application</h1>
        </div>
        <div>
            <h3>Inventory items:</h3>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Inventory ID #</th>
                        <th scope="col">Item Name</th>
                        <th scope="col">Count</th>
                        <th scope="col">Price</th>
                        <th scope="col">Create Time</th>
                        <th scope="col">Update Time</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${itemList}" var="item">
                        <tr>
                            <td><c:out value="${item.getInventoryId()}"/></td>
                            <td><c:out value="${item.getName()}"/></td>
                            <td><c:out value="${item.getCount()}"/></td>
                            <td><c:out value="${item.getPrice()}"/></td>
                            <td><c:out value="${item.getCreateTime().toString()}"/></td>
                            <td><c:out value="${item.getLastUpdated().toString()}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>