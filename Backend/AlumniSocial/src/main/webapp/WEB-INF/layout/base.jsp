<%-- 
    Document   : base
    Created on : Apr 3, 2024, 1:08:22 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title" />
        </title>
        <link href="${pageContext.request.contextPath}/css/sidebar.css" rel="stylesheet" type="text/css"/>
                <link href="${pageContext.request.contextPath}/css/navbar.css" rel="stylesheet" type="text/css"/>
                                <link href="${pageContext.request.contextPath}/css/content.css" rel="stylesheet" type="text/css"/>
                                                                <link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet" type="text/css"/>



        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </head>
    <body>


        <div class="container-fluid position-relative d-flex p-0">

            <tiles:insertAttribute name="sidebar" />
            <div class="content">
                <tiles:insertAttribute name="navbar" />
                <tiles:insertAttribute name="content" />
                <tiles:insertAttribute name="footer" />
            </div>
        </div>

    </body>
</html>
