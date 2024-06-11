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
                <span>User <b>Management</b></span>
                <a href="<c:url value="/userEditor"/>" class="btn btn-primary">
                    <i class="material-icons">&#xE147;</i> <span>Add New User</span>
                </a>
                <a href="#" class="btn btn-primary"><i class="material-icons">&#xE24D;</i> <span>Export to Excel</span></a>						
            </div>
        </div>
    </div>
    <div class="table-responsive" style="height: 500px; overflow-y: auto;">
        <div class="table-wrapper">

            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>

                        <th>Email</th>	
                                                <th>Username</th>						


                        <th>Date Created</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listUsers}" var="u">
                        <tr>
                            <td>${u.userID}</td>
                            <td><a href="#"> ${u.name}</a></td>

                            <td>${u.email}</td>
                                                        <td>${u.userName}</td>


                            <td>${u.createdAt}</td>                        
                            <td>${u.role}</td>
                            <c:choose>
                                <c:when test="${u.isLocked == true}">   
                                    <td><span class="status text-danger">&bull;</span> Inactive</td>

                                </c:when>
                                <c:otherwise>
                                    <td><span class="status text-success">&bull;</span> Active</td>

                                </c:otherwise>
                            </c:choose>

                            <td>
                                <c:url value="/api/users/${u.userID}" var="url" />
                                <a href="<c:url value="/userEditor/${u.userID}"/>" class="update" title="Update" data-toggle="tooltip"><i class="material-icons">&#xE8B8;</i></a>
                                <a onclick="deleteUser('${url}',${u.userID})" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE5C9;</i></a>
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


<script src="<c:url value="/js/script.js" />"></script>