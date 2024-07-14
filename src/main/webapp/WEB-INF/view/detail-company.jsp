<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Company detail</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700&display=swap"
	rel="stylesheet">

<!-- CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/open-iconic-bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/animate.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/owl.theme.default.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/magnific-popup.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/owl.theme.default.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/aos.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/ionicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap-datepicker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jquery.timepicker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/css/bootstrap-reboot.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/css/mixins/_text-hide.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/flaticon.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/icomoon.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap/bootstrap-grid.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap/bootstrap-reboot.css">

<!-- JS -->
<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/jquery-migrate-3.0.1.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/jquery.easing.1.3.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/jquery.waypoints.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/jquery.stellar.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/owl.carousel.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/jquery.magnific-popup.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/aos.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/jquery.animateNumber.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/scrollax.min.js"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
<script src="${pageContext.request.contextPath}/assets/js/google-map.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<script
	src="https://cdn.ckeditor.com/ckeditor5/29.0.0/classic/ckeditor.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
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
				<!-- 	<li class="nav-item"><a href="${pageContext.request.contextPath}/listJob" class="nav-link">Công
							việc</a></li> -->
					
					<s:authorize access="hasRole('candidate')">
					<li class="nav-item" style="position: relative;"><a class="nav-link" href="#"> Hồ Sơ
						</a>
							<ul class="dropdown" >
								<li><a href="${pageContext.request.contextPath}/showProfile?username=${pageContext.request.userPrincipal.name}">Hồ sơ</a></li>
								<li><a href="${pageContext.request.contextPath}/user/getListSaveJob?username=${pageContext.request.userPrincipal.name}">Công việc đã lưu</a></li>
								<li><a href="${pageContext.request.contextPath}/user/getListApply?username=${pageContext.request.userPrincipal.name}">Công việc đã ứng
										tuyển</a></li>
								<li><a href="${pageContext.request.contextPath}/user/getListCompany?username=${pageContext.request.userPrincipal.name}">Công ty đã theo
										dõi</a></li>
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
								<a href="${pageContext.request.contextPath }/showProfile?username=${pageContext.request.userPrincipal.name }"
									>Hồ Sơ</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/user/showListPost?username=${pageContext.request.userPrincipal.name }">Danh sách
									bài đăng</a> 
									
							</li>
								<li><a href="${pageContext.request.contextPath }/logout">Đăng
										xuất</a> </li>
							</ul>
						</li>
						<li class="nav-item "><a href="${pageContext.request.contextPath }/recruitment/post?username=${pageContext.request.userPrincipal.name}" class="nav-link">Đăng
								tuyển</a></li>
					</s:authorize>
					<s:authorize access="isAnonymous()">
						<li class="nav-item cta cta-colored"><a
							href="${pageContext.request.contextPath }/showLoginPage"
							class="nav-link"> Đăng nhập</a></li>
					</s:authorize>
					<s:authorize access="isAuthenticated()">
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.request.contextPath }/logout">
								Đăng xuất</a>
						</li>
					</s:authorize>
				</ul>
			</div>
		</div>
	</nav>

	<!-- END nav -->



	<div class="hero-wrap hero-wrap-2"
		style="background-image: url('${pageContext.request.contextPath}/assets/images/bg_1.jpg');"
		data-stellar-background-ratio="0.5">
		<div class="overlay"></div>
		<div class="container">
			<div
				class="row no-gutters slider-text align-items-end justify-content-start">
				<div class="col-md-12 text-center mb-5">
					<p class="breadcrumbs mb-0">
						<span class="mr-3"><a href="index.html">Trang chủ <i
								class="ion-ios-arrow-forward"></i></a></span>Chi tiết <span></span>
					</p>
					<h1 class="mb-3 bread">Chi tiết công ty</h1>
				</div>
			</div>
		</div>
	</div>

	<section style="margin-top: 10px" class="site-section">
		<div class="container">
			<div class="row align-items-center mb-5">
				<div class="col-lg-8 mb-4 mb-lg-0">
					<div class="d-flex align-items-center">
						<div class="border p-2 d-inline-block mr-3 rounded">
							<img width="100" height="100"
								src="${pageContext.request.contextPath}/assets/images/${company.logo}"
								alt="Image">
						</div>
						<div>
							<h2>${company.nameCompany}</h2>
							<div>
								<span class="icon-briefcase mr-2"></span><span
									class="ml-0 mr-2 mb-2">${company.email}</span> <span
									class="icon-room mr-2"></span><span class="m-2">${company.address}</span>

							</div>
							<input type="hidden" id="idCompany" value="${company.id }">
							<input type="hidden" id="username" value="${pageContext.request.userPrincipal.name }">
						</div>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="row">
						<s:authorize access="isAuthenticated() && hasRole('candidate')">
						<div class="col-6">
							<a onclick="follow()"
								class="btn btn-block btn-light btn-md"><span
								class="icon-heart-o mr-2 text-danger"></span>Theo dõi</a>
						</div>
						</s:authorize>
						<s:authorize access="isAnonymous()">
						<div class="col-6">
							<a onclick="follow()" class="btn btn-block btn-light btn-md"><span
								class="icon-heart-o mr-2 text-danger"></span>Theo dõi</a>
						</div>
						</s:authorize>
						<div class="col-6"></div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8">
					<div class="mb-5">

						<h3 class="h5 d-flex align-items-center mb-4 text-primary">
							<span class="icon-align-left mr-3"></span>Mô tả công việc
						</h3>
						<p>${company.description}</p>
					</div>

				</div>
				<div class="col-lg-4">
					<div class="bg-light p-3 border rounded mb-4">
						<h3 class="text-primary  mt-3 h5 pl-3 mb-3 ">Tóm tắt công
							việc</h3>
						<ul class="list-unstyled pl-3 mb-0">

							<li class="mb-2"><strong class="text-black">Email
									công ty: </strong> <span>${company.email}</span></li>
							<li class="mb-2"><strong class="text-black">Số điện
									thoại: </strong> <span>${company.phoneNumber}</span></li>
							<li class="mb-2"><strong class="text-black">Địa
									chỉ: </strong> <span>${company.address}</span></li>
						</ul>
					</div>

					<div class="bg-light p-3 border rounded">
						<h3 class="text-primary  mt-3 h5 pl-3 mb-3 ">Share</h3>
						<div class="px-3">
							<a href="#" class="pt-3 pb-3 pr-3 pl-0"><span
								class="icon-facebook"></span></a> <a href="#"
								class="pt-3 pb-3 pr-3 pl-0"><span class="icon-twitter"></span></a>
							<a href="#" class="pt-3 pb-3 pr-3 pl-0"><span
								class="icon-linkedin"></span></a> <a href="#"
								class="pt-3 pb-3 pr-3 pl-0"><span class="icon-pinterest"></span></a>
						</div>
					</div>

				</div>
			</div>
		</div>
		<script>
			function follow() {
				var name = "#idCompany";
				var user = "#username";
				var idCompany = $(name).val();
				var username = $(user).val();
				var formData = new FormData();
				formData.append('idCompany', idCompany);
				formData.append('username', username);
				$.ajax({
					type : 'POST',
					url : window.location.origin + '/job-search-site/user/followCompany/',
					contentType : false,
					processData : false,
					data : formData,
					success : function(data) {
						if (data == "false") {
							swal({
								title : 'Bạn cần phải đăng nhập!',
								/* text: 'Redirecting...', */
								icon : 'error',
								timer : 3000,
								buttons : true,
								type : 'error'
							})
						} else if (data == "true") {
							swal({
								title : 'Theo dõi thành công!',
								/* text: 'Redirecting...', */
								icon : 'success',
								timer : 3000,
								buttons : true,
								type : 'success'
							})
						} else {
							swal({
								title : 'Bạn đã theo dõi công ty này!',
								/* text: 'Redirecting...', */
								icon : 'error',
								timer : 3000,
								buttons : true,
								type : 'error'
							})
						}
					},
					error : function(err) {
						alert(err);
					}
				})
			}
		</script>
	</section>

	<footer class="footer" class="ftco-footer ftco-bg-dark ftco-section">
		<div class="container">

			<div class="row">
				<div class="col-md-12 text-center">

					<p>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						Copyright &copy;
						<script>
							document.write(new Date().getFullYear());
						</script>
						<i class="icon-heart text-danger" aria-hidden="true"></i> by <a
							href="https://colorlib.com" target="_blank">KhuongTM</a>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</p>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>