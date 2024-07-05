<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Work CV</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700&display=swap" rel="stylesheet">

  <!-- CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/open-iconic-bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/animate.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.theme.default.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/magnific-popup.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.theme.default.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/aos.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ionicons.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap-datepicker.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jquery.timepicker.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/css/bootstrap-reboot.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/css/mixins/_text-hide.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/flaticon.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/icomoon.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap/bootstrap-grid.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap/bootstrap-reboot.css">

  <!-- JS -->
  <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/jquery-migrate-3.0.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/jquery.easing.1.3.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/jquery.waypoints.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/jquery.stellar.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/owl.carousel.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/jquery.magnific-popup.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/aos.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/jquery.animateNumber.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="${pageContext.request.contextPath}/assets/js/google-map.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script src="https://cdn.ckeditor.com/ckeditor5/29.0.0/classic/ckeditor.js"></script>
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
<body>
<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
		id="ftco-navbar">
		<div class="container-fluid px-md-4">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/">Work
				CV</a>
			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a
						href="${pageContext.request.contextPath}/" class="nav-link">Trang
							chủ</a></li>
					<li class="nav-item"><a href="/" class="nav-link">Công
							việc</a></li>
					
					<s:authorize access="hasRole('candidate')">
					<li class="nav-item" style="position: relative;"><a class="nav-link" href="#"> Hồ Sơ
						</a>
							<ul class="dropdown" >
								<li><a href="${pageContext.request.contextPath}/showProfile?username=${pageContext.request.userPrincipal.name}">Hồ sơ</a></li>
								<li><a href="/save-job/get-list">Công việc đã lưu</a></li>
								<li><a href="/user/get-list-apply">Công việc đã ứng
										tuyển</a></li>
								<li><a href="/user/get-list-company">Công ty đã theo
										dõi</a></li>
								<li><a href="#"
									onclick="document.getElementById('logoutForm').submit();">Đăng
										xuất</a> <f:form id="logoutForm"
										action="${pageContext.request.contextPath }/logout"
										method="post" style="display:none;">
										<input type="hidden" name="_csrf" value="${_csrf.token }" />
									</f:form></li>
							</ul></li>
					</s:authorize>
					<s:authorize access="hasRole('recruiter')">
					<li class="nav-item"><a href="${pageContext.request.contextPath }/listApplyPost?username=${pageContext.request.userPrincipal.name}" class="nav-link">Ứng cử
							viên</a></li>
						<li class="nav-item">
							<a class="nav-link" href="#"> <s:authentication
									property="principal.username" />
						</a>
						<ul class="dropdown">
							<li>
								<a href="#"
									onclick="document.getElementById('detailCompany').submit();">Hồ
									Sơ</a>
								<f:form id="detailCompany"
									action="${pageContext.request.contextPath }/showProfile"
									style="display:none;" method="get">
									<input type="hidden"
										value="${pageContext.request.userPrincipal.name }"
										name="username">
								</f:form>
							</li>
							<li>
								<a href="#" onclick="document.getElementById('showListPost').submit();">Danh sách
									bài đăng</a> 
									<f:form id="showListPost" action="${pageContext.request.contextPath}/user/showListPost"
										style="display:none;" method="get">
										<input type="hidden" value="${pageContext.request.userPrincipal.name }" name="username">
									</f:form>
							</li>
								<li><a href="#"
									onclick="document.getElementById('logoutForm').submit();">Đăng
										xuất</a> <f:form id="logoutForm"
										action="${pageContext.request.contextPath }/logout"
										method="post" style="display:none;">
										<input type="hidden" name="_csrf" value="${_csrf.token }" />
									</f:form></li>
							</ul>
						</li>
						<li class="nav-item ">
							<a href="#" onclick="document.getElementById('postRecruitment').submit();" class="nav-link">Đăng
								tuyển</a>
							<f:form id="postRecruitment" action="${pageContext.request.contextPath }/recruitment/post" method="get">
								<input type="hidden" value="${pageContext.request.userPrincipal.name}" name="username">
							</f:form>
						</li>
					</s:authorize>
					<s:authorize access="isAnonymous()">
						<li class="nav-item cta cta-colored"><a
							href="${pageContext.request.contextPath }/showLoginPage"
							class="nav-link"> Đăng nhập</a></li>
					</s:authorize>
					<s:authorize access="isAuthenticated()">
						<li class="nav-item">
							<a class="nav-link" href="#"
									onclick="document.getElementById('logoutForm').submit();">Đăng
									xuất</a>
								<f:form id="logoutForm"
									action="${pageContext.request.contextPath }/logout"
									method="post" style="display:none;">
									<input type="hidden" name="_csrf" value="${_csrf.token }" />
								</f:form>
						</li>
					</s:authorize>
				</ul>
			</div>
		</div>
	</nav>

<div class="hero-wrap hero-wrap-2" style="background-image: url('${pageContext.request.contextPath}/assets/images/bg_1.jpg');" data-stellar-background-ratio="0.5" th:if="${session.user.role.id == 2 }">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end justify-content-start">
            <div class="col-md-12 text-center mb-5">
                <p class="breadcrumbs mb-0"><span class="mr-3"><a href="index.html">Trang chủ <i class="ion-ios-arrow-forward"></i></a></span>Danh sách <span></span></p>
                <h1 class="mb-3 bread">Danh sách bài đăng</h1>
            </div>
        </div>
    </div>
</div>
<c:if test="${not empty success_msg}">
<div class="toast" data-delay="2500" style="position:fixed; top: 100PX; left: 10PX;z-index: 2000;width: 300px">
    <script>
        swal({
            title: 'Thành công!',
            /* text: 'Redirecting...', */
            icon: 'success',
            timer: 3000,
            buttons: true,
            type: 'success'
        })
    </script>
</div>
</c:if>
<c:if test="${not empty error_msg}">
<div class="toast" data-delay="2500" style="position:fixed; top: 100PX; left: 10PX;z-index: 2000;width: 300px">
    <script>
        swal({
            title: 'Bài đăng đang có người ứng tuyển!',
            /* text: 'Redirecting...', */
            icon: 'error',
            timer: 3000,
            buttons: true,
            type: 'error'
        })
    </script>
</div>
</c:if>
<s:authorize access="!hasRole('recruiter')">
<div class="hero-wrap hero-wrap-2" style="background-image: url('${pageContext.request.contextPath}/assets/images/bg_1.jpg');" data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end justify-content-start">
            <div class="col-md-12 text-center mb-5">

                <h1 class="mb-3 bread">Không tìm thấy trang yêu cầu</h1>
            </div>
        </div>
    </div>
</div>
</s:authorize>
<s:authorize access="hasRole('recruiter')">
<section class="ftco-section bg-light">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 pr-lg-4">
                <div class="row">
                	<c:if test="${recruitments.size()==0 }">
                        <div class="row form-group" >
                            <label for="company-website-tw d-block">Danh sách bài tuyển dụng </label> <br>
                            <div class="col-md-12">
                                <a href="/recruitment/post" class="btn px-4 btn-primary text-white">Đăng tuyển</a>
                            </div>
                        </div>

                	</c:if>
                	<c:if test="${recruitments.size() > 0 }">
                	<c:forEach var="recruitment" items="${recruitments}">
                        <div class="col-md-12 ">
                            <div class="job-post-item p-4 d-block d-lg-flex align-items-center">
                                <div class="one-third mb-4 mb-md-0">
                                    <div class="job-post-item-header align-items-center">
                                        <span class="subadge">${recruitment.type}</span>
                                        <h2 class="mr-3 text-black" ><a href="${pageContext.request.contextPath}/recruitment/detail?recruitmentId=${recruitment.id}">${recruitment.title}</a></h2>
                                        
                                    </div>
                                    <div class="job-post-item-body d-block d-md-flex">
                                        <div class="mr-3"><span class="icon-layers"></span> 
                                        <a href="#" onclick="document.getElementById('detailCompanyForm').submit();">${recruitment.company.nameCompany}</a></div>
                                        <f:form id="detailCompanyForm" action="${pageContext.request.contextPath}/recruitment/showDetailCompany" method="get">
                                        <input type="hidden" name="companyId" value="${recruitment.company.id }">
                                        </f:form>
                                        <div><span class="icon-my_location"></span> <span>${recruitment.address}</span></div>
                                    </div>
                                </div>

                                <div class="one-forth ml-auto d-flex align-items-center mt-4 md-md-0" style="width: 330px !important;">
<!--                                    <div>-->
<!--                                        <a href="#" class="icon text-center d-flex justify-content-center align-items-center icon mr-2">-->
<!--                                            <span class="icon-heart"></span>-->
<!--                                        </a>-->
<!--                                    </div>-->
                                    <a href="${pageContext.request.contextPath}/recruitment/detail?recruitmentId=${recruitment.id}" class="btn btn-primary py-2 ml-2">Xem chi tiết</a>
                                    <a href="#" onclick="document.getElementById('editpost').submit();"  class="btn btn-warning py-2 ml-2">Cập nhật</a>
                                    <f:form id="editpost" action="${pageContext.request.contextPath}/recruitment/editpost" method="get">
                                    	<input type="hidden" value="${recruitment.id }" name="recruitmentId">
                                    </f:form>
                                    <a class="btn btn-danger py-2 ml-2" href="" data-toggle="modal" data-target="#exampleModal${recruitment.id}">Xóa</a>

                                </div>
                            </div>
                        </div><!-- end -->
                        <!-- Modal -->
                        <div class="modal fade" id="exampleModal${recruitment.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Bạn có chắc chắn muốn xóa ?</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Bài đăng : <span>${recruitment.title}</span>
                                        <f:form action="${pageContext.request.contextPath}/recruitment/delete" method="get">
                                            <input type="hidden" class="form-control" id="id" name="recruitmentId" value="${recruitment.id}">
                                            <div class="modal-footer mt-1">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                                                <button type="submit" class="btn btn-danger">Xóa</button>
                                            </div>
                                        </f:form>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <!-- Modal -->
                    </c:forEach>
                    </c:if>
                </div>
                <div class="row mt-5">
                    <div class="col text-center">
                        <div class="block-27">
                            <ul>
                            	<c:if test="${pagePrev>0}">
                                <li><a href="${pageContext.request.contextPath}/user/showListPost?pageNumber=${pagePrev}&pageSize=${pageSize}&username=${user.userName}">&lt;</a></li>
                            	</c:if>
                            	<c:forEach var="i" begin="1" end="${totalPage }">
<!--                                    <th:block th:if="${numberPage == 0}">-->
<!--                                        <li th:class="${numberPage == 0 ? 'active' : null }"><a th:href="@{/user/list-post(page = ${state.index})}" th:text="${state.index + 1}"></a></li>-->
<!--                                    </th:block>-->
                                        <li class="${pageNumber == i  ? 'active' : ' ' }"><a
												href="#"
												onclick="document.getElementById('showListPost${i}').submit();">${i}</a>
												<f:form id="showListPost${i}"
													action="${pageContext.request.contextPath}/user/showListPost"
													method="get">
													<input type="hidden" name="pageNumber" value="${i}">
													<input type="hidden" name="pageSize" value="${pageSize}">
													<input type="hidden" name="username"
														value="${user.userName }">
												</f:form></li>
                            	
                            	</c:forEach>
                            	<c:if test="${pageNext<=totalPage}">
                                <li><a href="${pageContext.request.contextPath}/user/showListPost?pageNumber=${pageNext}&pageSize=${pageSize}&username=${user.userName}">&gt;</a></li>
                            	</c:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>

</s:authorize>

<script>
    ClassicEditor.create(document.querySelector('#editorN')).then(eidt => {
        console.log("da" + eidt);
    })
        .catch(error => {
            console.error(error);
        });
</script>

<footer class="ftco-footer ftco-bg-dark ftco-section">

</footer>
</body>
</html>