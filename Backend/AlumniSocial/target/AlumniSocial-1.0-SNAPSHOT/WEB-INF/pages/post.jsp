<%-- 
    Document   : users
    Created on : May 17, 2024, 9:33:32?AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container-fluid contentPage vh-100">

    <div class="table-title">
        <div class="row">
            <div>
                <span><b>Quản lý bài đăng</b></span>
                
            </div>
        </div>
    </div>
    <div class="table-responsive" style="height: 500px; overflow-y: auto;">
        <div class="table-wrapper">

            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Content</th>
                        <th>Date Created</th>
                        <th>Post type</th>
                        <th>User's post</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${posts}" var="p">
                        <tr>
                            <td>${p[0]}</td>
                            <td>${p[1]}</td>
                            <td>${p[2]}</td>
                            <td>${p[3]}</td>                        
                            <td>${p[4]}</td>
                            <td>
                                <c:url value="/api/post/${p[0]}" var="url" />
                                <a onclick="deletePost('${url}',${p[0]})" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE5C9;</i></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="clearfix">
                <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                <ul class="pagination">
                    <li class="page-item disabled"><a href="#">Previous</a></li>
                    <li class="page-item"><a href="#" class="page-link">1</a></li>
                    <li class="page-item"><a href="#" class="page-link">2</a></li>
                    <li class="page-item active"><a href="#" class="page-link">3</a></li>
                    <li class="page-item"><a href="#" class="page-link">4</a></li>
                    <li class="page-item"><a href="#" class="page-link">5</a></li>
                    <li class="page-item"><a href="#" class="page-link">Next</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/js/script.js" />"></script>