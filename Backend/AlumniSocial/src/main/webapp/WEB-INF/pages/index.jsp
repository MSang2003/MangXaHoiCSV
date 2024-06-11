<%-- 
    Document   : index
    Created on : Mar 27, 2024, 1:11:45 PM
    Author     : admin
--%>

<%@ page import="java.util.Date" %>
<jsp:useBean id="now" class="java.util.Date" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container-fluid contentPage">
    <div>
        <h1 class="d-flex justify-content-center text-info">Báo cáo thống kê</h1>

        <div class="container mt-4">
            <div class="border p-4">
                <form class="">
                    <div d-flex flex-row>
                        <div class="form-floating mb-3 mt-3">
                            <input type="text"  value="${param.year}" class="form-control" id="year" placeholder="Năm" name="year">
                            <label for="year">Năm</label>
                        </div>
                        <div class="d-flex flex-row">
                            <div class="form-floating mb-3 mt-3 flex-grow-1 mr-2">
                                <select class="form-select" id="role" name="role">
                                    <option value="Alumni" selected>Cựu sinh viên</option>
                                    <c:choose>
                                        <c:when test="${param.role=='Admin'}">
                                            <option value="Admin" selected>Quản trị viên</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="Admin">Quản trị viên</option>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${param.role=='Teacher'}">
                                            <option value="Teacher" selected>Giảng viên</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="Teacher">Giảng viên</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                                <label for="role" class="form-label">Chọn vai trò:</label>
                            </div>

                            <div class="form-floating mb-3 mt-3 flex-grow-1">
                                <select class="form-select" id="period" name="period">
                                    <option value="MONTH" selected>Theo tháng</option>
                                    <c:choose>
                                        <c:when test="${param.period=='QUATER'}">
                                            <option value="QUATER" selected>Theo quý</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="QUATER">Theo quý</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                                <label for="period" class="form-label">Chọn thời gian:</label>
                            </div> 
                        </div>
                    </div>

                    <div class="d-flex justify-content-center">
                        <button class="btn btn-success">Lọc</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="container pt-5">
            <div class="row">
                <div class="col-4">
                    <div class="table-responsive table-scroll" data-mdb-perfect-scrollbar="true" style="position: relative; height: 700px">
                        <table class="table table-striped mb-0">
                            <thead style="background-color: #002d72;;color: white">
                                <tr>

                                    <c:choose>
                                        <c:when test="${param.period == 'QUATER'}">
                                            <th scope="col">Quý</th>

                                        </c:when>
                                        <c:otherwise>
                                            <th scope="col">Tháng</th>

                                        </c:otherwise>
                                    </c:choose>
                                    <th scope="col">Số lượng</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${statsUser}" var="u">
                                    <tr>  
                                        <td> ${u[1]}</td>
                                        <td>${u[0]}</td></tr>
                                    </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-6">
                    <c:if test="${param.year != null}">
                        <div class="alert alert-info">
                            <p>Thống kê số người dùng</p>
                            <p>Vai trò: ${param.role}</p>
                            <p>Năm: ${param.year}  Thời gian: ${param.period}  </p>
                        </div>
                    </c:if>
                    <canvas id="UserAmoubtChart"></canvas>
                </div>
            </div>
        </div>


        <!--Thống kê bài post-->            

        <div class="container">
            <div class="row">
                <div class="col-4">
                    <div class="table-responsive table-scroll" data-mdb-perfect-scrollbar="true" style="position: relative; height: 700px">
                        <table class="table table-striped mb-0">
                            <thead style="background-color: #002d72;;color: white">
                                <tr>

                                    <c:choose>
                                        <c:when test="${param.period == 'QUATER'}">
                                            <th scope="col">Quý</th>

                                        </c:when>
                                        <c:otherwise>
                                            <th scope="col">Tháng</th>

                                        </c:otherwise>
                                    </c:choose>
                                    <th scope="col">Số lượng</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${statsPost}" var="p">
                                    <tr>  
                                        <td> ${p[1]}</td>
                                        <td>${p[0]}</td></tr>
                                    </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-6">
                    <c:if test="${param.year != null}">
                        <div class="alert alert-info">
                            
                            <p>Thống kê số lượng bài đăng</p>
                            <p>Năm: ${param.year}      Thời gian: ${param.period}</p>
                        </div>
                    </c:if>
                    <canvas id="postChart"></canvas>
                </div>
            </div>
        </div>


        <div class="container">
            <div class="row">
                <div class="col-4">
                    <div class="table-responsive table-scroll" data-mdb-perfect-scrollbar="true" style="position: relative; height: 700px">
                        <table class="table table-striped mb-0">
                            <thead style="background-color: #002d72;;color: white">
                                <tr>
                                    <th scope="col">Khóa</th>
                                    <th scope="col">Số lượng</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${statsAlumni}" var="a">
                                    <tr>  
                                        <td> ${a[0]}</td>
                                        <td>${a[1]}</td></tr>
                                    </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-6">
                    <canvas id="chartAlumni"></canvas>
                </div>
            </div>
        </div>


    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="<c:url value="js/script.js" />"></script>
<script>
    let labels = [];
    let data = [];
    <c:forEach items="${statsAlumni}" var="a">
    labels.push('Niên khóa ${a[0]}');
    data.push(${a[1]});
    </c:forEach>

    let labels2 = [];
    let data2 = [];
    <c:forEach items="${statsUser}" var="u">

        <c:choose>
            <c:when test="${param.period == 'QUATER'}">
    labels2.push('Quý ${u[0]}');
    data2.push(${u[1]});
            </c:when>
            <c:otherwise>
    data2.push(${u[0]});
    labels2.push('Tháng ${u[1]}');
            </c:otherwise>
        </c:choose>
    </c:forEach>


    let labels3 = [];
    let data3 = [];
    <c:forEach items="${statsPost}" var="p">

        <c:choose>
            <c:when test="${param.period == 'QUATER'}">
    data3.push(${p[0]});
    labels3.push('Quý ${p[0]}');
            </c:when>
            <c:otherwise>
    data3.push(${p[0]});
    labels3.push('Tháng ${p[1]}');
            </c:otherwise>
        </c:choose>
    </c:forEach>


    window.onload = function () {
        let ctx3 = document.getElementById("postChart");
        let ctx2 = document.getElementById("UserAmoubtChart");
        let ctx1 = document.getElementById("chartAlumni");
        drawChartRevenue(ctx1, labels, data);
        drawChartRevenue(ctx2, labels2, data2);
        drawChartRevenue(ctx3, labels3, data3);

    };
</script>