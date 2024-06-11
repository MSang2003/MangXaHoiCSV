<%-- 
    Document   : index
    Created on : Mar 27, 2024, 1:11:45 PM
    Author     : admin
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container-fluid contentPage">
    <div class="row vh-100 rounded align-items-center justify-content-center mx-0 box">

        <c:url value="/userEditor" var="action" />
        <form:form method="post" action="${pageContext.request.contextPath}/userEditor" modelAttribute="user" enctype="multipart/form-data">
            <form:errors path="*" element="div" cssClass="alert alert-danger" />

            <div class="form-floating mb-3 mt-3">
                <form:input class="form-control"  id="name"  placeholder="Tên sản phẩm" path="name" />
                <label for="name">Tên người dùng</label>
            </div>
            <div class="form-floating mb-3 mt-3">
                <form:input class="form-control"  id="email"  placeholder="Email" path="email" />
                <label for="email">Email</label>
            </div>
            <div class="form-floating mb-3 mt-3">
                <form:input class="form-control"  id="password"  placeholder="Password" path="password" />
                <label for="password">Password</label>
            </div>

            <div class="form-floating mb-3 mt-3">
                <form:input class="form-control"  id="studentID"  placeholder="Student ID" path="studentID" />
                <label for="studentID">Student ID</label>
            </div>

            <div class="form-floating mb-3 mt-3 d-flex align-items-center">
                <p>Vai trò</p>
                <form:select class="form-selected" id="role" path="role">
                    <option value="Alumni">Cựu sinh viên</option>
                    <option value="Student">Quản trị viên</option>
                    <option value="Faculty">Giảng viên</option>
                </form:select>
            </div>

            <div class="form-floating mb-3 mt-3 d-flex align-items-center">
                <p>Giới tính</p>
                <form:select class="form-selected" id="sex" path="sex">
                    <option value="Male">Name</option>
                    <option value="Female">Nữ</option>
                    <option value="Another">Khác</option>
                </form:select>
            </div>

            <div class="form-floating mb-3 mt-3">
                <form:input type="file" accept=".png,.jpg" class="form-control" id="avatar" path="avatarFile" />
                <label for="avatar">Ảnh đại diện</label>
                <c:if test="${user.userID > 0}">
                    <img src="${user.avatar}" width="200" class="img-fluid" />
                </c:if>

            </div>
            <div class="form-floating mb-3 mt-3 d-flex align-items-center" >
                <label class="form-check-label" for="available">Đã xác thực</label>

                <form:checkbox class="form-check-input" id="isVerified" path="isVerified" />
            </div>

            <div class="form-floating mb-3 mt-3 d-flex align-items-center">
                <label class="form-check-label" for="available">Khóa tài khoản</label>

                <form:checkbox class="form-check-input" id="isLocked" path="isLocked" />
            </div>

            <div class="form-floating">
                <button class="btn btn-info mt-1" type="submit">
                    <c:choose>
                        <c:when test="${user.userID > 0}">Cập nhật</c:when>
                        <c:otherwise>Thêm</c:otherwise>
                    </c:choose>
                </button>
                <form:hidden path="userID" />
            </div>
        </form:form>
    </div>
</div>
