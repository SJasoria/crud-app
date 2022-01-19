<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a class="bold" href="${pageContext.request.contextPath}/">Home</a></li>
                            <li><a class="bold" href="${pageContext.request.contextPath}/inventory-list">Inventory Item List</a></li>
                            <li><a class="bold" href="${pageContext.request.contextPath}/add">Add Item</a></li>
                            <li><a class="bold" href="${pageContext.request.contextPath}/edit">Edit Item</a></li>
                            <li><a class="bold" href="${pageContext.request.contextPath}/delete">Delete Item</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
        <div class="heading">
            <h1>Inventory Tracking Application</h1>
        </div>
        <div>
            <h4>Add items</h4>
            <div class="item-form">
                <form:form  method="POST" action="/add-to-inventory" modelAttribute="item">
                <div class="form-group">
                    <form:label path="name">Item Name</form:label>
                    <form:input path="name" class="form-control" placeholder="Enter Item Name"/>
                </div>
                <div class="form-group">
                    <form:label path="count">Item Count</form:label>
                    <form:input path="count" class="form-control"  type="number" placeholder="Enter Item Count"/>
                </div>
                <div class="form-group">
                    <form:label path="price">Item Price</form:label>
                    <form:input path="price" class="form-control"  type="number" step="0.01" placeholder="Enter Item Price"/>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
                </form:form>
            </div>
        </div>
    </body>
</html>