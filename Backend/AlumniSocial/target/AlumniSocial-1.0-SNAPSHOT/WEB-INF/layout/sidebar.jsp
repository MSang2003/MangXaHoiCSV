<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<nav id="sidebar" class="sidebar js-sidebar">
    <div class="sidebar-content js-simplebar">
        <a class="sidebar-brand" href="index.html">
            <span class="align-middle">Mạng xã hội CSV </span>
        </a>

        <ul class="sidebar-nav">
            <li class="sidebar-header">
                Trang
            </li>

            <li class="sidebar-item active">
                <a class="sidebar-link" href="index.html">
                    <i class="align-middle" data-feather="sliders"></i> <span
                        class="align-middle">Dashboard</span>
                </a>
            </li>
            <li class="sidebar-item">
                <a class="sidebar-link"  href="<c:url value="/users" />" >
                    <i class="align-middle" data-feather="user"></i> <span class="align-middle">Người dùng</span>
                </a>
            </li>

            <li class="sidebar-item">
                <a class="sidebar-link" href="<c:url value="/userEditor"/>" >
                    <i class="align-middle" data-feather="log-in"></i> <span class="align-middle">Editor</span>
                </a>
            </li>

           

            <li class="sidebar-item">
                <a class="sidebar-link" href="<c:url value="/post"/>">
                    <i class="align-middle" data-feather="book"></i> <span class="align-middle">Bài đăng</span>
                </a>
            </li>

            <li class="sidebar-header">
                Tài khoản
            </li>

             <li class="sidebar-item">
                <a class="sidebar-link" href="<c:url value="/login"/>">
                    <i class="align-middle" data-feather="user-plus"></i> <span class="align-middle">Đăng nhập</span>
                </a>
            </li> <li class="sidebar-item">
                <a class="sidebar-link" href="<c:url value="/logout"/>">
                    <i class="align-middle" data-feather="user-plus"></i> <span class="align-middle">Đăng xuẩt</span>
                </a>
            </li>
           
        </ul>
    </div>
</nav>