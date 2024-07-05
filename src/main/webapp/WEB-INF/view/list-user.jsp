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
<body>
	<nav
		class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
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
						<li class="nav-item" style="position: relative;"><a
							class="nav-link" href="#"> Hồ Sơ </a>
							<ul class="dropdown">
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
						<li class="nav-item"><a
							href="${pageContext.request.contextPath }/listApplyPost?username=${pageContext.request.userPrincipal.name}"
							class="nav-link">Ứng cử viên</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <s:authentication
									property="principal.username" />
						</a>
							<ul class="dropdown">
								<li><a href="#"
									onclick="document.getElementById('detailCompany').submit();">Hồ
										Sơ</a> <f:form id="detailCompany"
										action="${pageContext.request.contextPath }/showProfile"
										style="display:none;" method="get">
										<input type="hidden"
											value="${pageContext.request.userPrincipal.name }"
											name="username">
									</f:form></li>
								<li><a href="#"
									onclick="document.getElementById('showListPost').submit();">Danh
										sách bài đăng</a> <f:form id="showListPost"
										action="${pageContext.request.contextPath}/user/showListPost"
										style="display:none;" method="get">
										<input type="hidden"
											value="${pageContext.request.userPrincipal.name }"
											name="username">
									</f:form></li>
								<li><a href="#"
									onclick="document.getElementById('logoutForm').submit();">Đăng
										xuất</a> <f:form id="logoutForm"
										action="${pageContext.request.contextPath }/logout"
										method="post" style="display:none;">
										<input type="hidden" name="_csrf" value="${_csrf.token }" />
									</f:form></li>
							</ul></li>
						<li class="nav-item "><a href="#"
							onclick="document.getElementById('postRecruitment').submit();"
							class="nav-link">Đăng tuyển</a> <f:form id="postRecruitment"
								action="${pageContext.request.contextPath }/recruitment/post"
								method="get">
								<input type="hidden"
									value="${pageContext.request.userPrincipal.name}"
									name="username">
							</f:form></li>
					</s:authorize>
					<s:authorize access="isAnonymous()">
						<li class="nav-item cta cta-colored"><a
							href="${pageContext.request.contextPath }/showLoginPage"
							class="nav-link"> Đăng nhập</a></li>
					</s:authorize>
					<s:authorize access="isAuthenticated()">
						<li class="nav-item"><a class="nav-link" href="#"
							onclick="document.getElementById('logoutForm').submit();">Đăng
								xuất</a> <f:form id="logoutForm"
								action="${pageContext.request.contextPath }/logout"
								method="post" style="display:none;">
								<input type="hidden" name="_csrf" value="${_csrf.token }" />
							</f:form></li>
					</s:authorize>
				</ul>
			</div>
		</div>
	</nav>
	<!-- END nav -->
	<c:if test="${not empty success_msg}">
		<div class="toast" data-delay="2000"
			style="position: fixed; top: 100PX; right: 10PX; z-index: 2000; width: 300px">
			<script>
				swal({
					title : 'Ứng cử viên này chưa cập nhật cv!',
					/* text: 'Redirecting...', */
					icon : 'error',
					timer : 3000,
					buttons : true,
					type : 'error'
				})
			</script>
		</div>
	</c:if>
	<!-- END nav -->
	<div class="hero-wrap hero-wrap-2"
		style="background-image: url('${pageContext.request.contextPath }/assets/images/bg_1.jpg');"
		data-stellar-background-ratio="0.5">
		<div class="overlay"></div>
		<div class="container">
			<div
				class="row no-gutters slider-text align-items-end justify-content-start">
				<div class="col-md-12 text-center mb-5">
					<p class="breadcrumbs mb-0">
						<span class="mr-3"><a
							href="${pageContext.request.contextPath }/">Trang chủ <i
								class="ion-ios-arrow-forward"></i></a></span>Công việc <span></span>
					</p>
					<h1 class="mb-3 bread">Danh sách ứng cử viên</h1>
				</div>
			</div>
		</div>
	</div>
	<section class="ftco-section bg-light">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 pr-lg-5">
					<div class="row">
						<c:set var="isEmpty" value="true" />
						<c:forEach var="applyPost" items="${applyPosts }">
							<c:set value="false" var="iseEmpty" />
							<div class="col-md-12"
								style="box-shadow: rgba(0, 0, 0, 0.4) 0px 0px 10px; margin: 20px auto;">
								<div class="team d-md-flex p-4 bg-white">
									<c:choose>
										<c:when test="${applyPost.user.image == null}">
											<img style="margin-top: 10px" class="img"
												src="https://st.quantrimang.com/photos/image/072015/22/avatar.jpg" />
										</c:when>
										<c:otherwise>
											<img style="margin-top: 10px" class="img"
												src="${pageContext.request.contextPath }/assets/images/${applyPost.user.image}" />
										</c:otherwise>
									</c:choose>
									<div class="text pl-md-4">
										<H5 class="location mb-0">${applyPost.user.userName}</H5>
										<p style="display: block; color: black">${applyPost.user.address}</p>
										<span class="position" style="display: block; color: black">${applyPost.user.email}</span>
										<p class="mb-4" style="width: 700px">${applyPost.user.description}</p>
										<c:if test="${applyPost.nameCv != null}">
											<div style="margin-left: 1px" class="row">
												<p>
													<a
														href="#"
														class="btn btn-primary">Xem cv</a>
												</p>
											</div>
										</c:if>
									</div>
								</div>
							</div>
						</c:forEach>
						<c:if test="${isEmpty}">
							<div style="text-align: center">
								<p style="color: red">Không có kết quả nào</p>
							</div>
						</c:if>
					</div>
					<div class="row mt-5">
						<div class="col text-center">
							<div class="block-27">
								<ul>
								<c:if test="${pagePre>0 }">
									<li><a
										href="${pageContext.request.contextPath }/listApplyPost?username=${pageContext.request.userPrincipal.name }&pageNumber=${pagePre}&pageSize=${pageSize}">&lt;</a></li>
								</c:if>
								<c:forEach var="i" begin="1" end="${totalPages }">
										<li
											class="${pageNumber == i  ? 'active' : ' ' }"><a
											href="${pageContext.request.contextPath }/listApplyPost?username=${pageContext.request.userPrincipal.name }&pageNumber=${i}&pageSize=${pageSize}"
											>${i }</a></li>
								</c:forEach>
									<c:if test="${pageNext<=totalPages }">
									<li> <a
										href="${pageContext.request.contextPath }/listApplyPost?username=${pageContext.request.userPrincipal.name }&pageNumber=${pageNext}&pageSize=${pageSize}">&gt;</a></li>
									</c:if>
								</ul>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</section>
	<script>
		function save(id) {
			var name = "#idRe" + id;
			var idRe = $(name).val();
			var formData = new FormData();
			formData.append('idRe', idRe);
			$.ajax({
				type : 'POST',
				url : '/save-job/save/',
				contentType : false,
				processData : false,
				data : formData,
				success : function(data) {
					console.log(data);
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
							title : 'Lưu thành công!',
							/* text: 'Redirecting...', */
							icon : 'success',
							timer : 3000,
							buttons : true,
							type : 'success'
						})
					} else {
						swal({
							title : 'Bạn đã lưu bài này rồi!',
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

		function apply(id) {
			var name = "#idRe" + id;
			var nameModal = "#exampleModal" + id;
			var nameFile = "#fileUpload" + id;
			var nameText = "#text" + id;
			var idRe = $(name).val();
			var textvalue = $(nameText).val();
			var fileUpload = $(nameFile).get(0);
			var files = fileUpload.files;
			var formData = new FormData();
			formData.append('file', files[0]);
			formData.append('idRe', idRe);
			formData.append('text', textvalue);
			if (files[0] == null) {
				swal({
					title : 'Bạn cần phải chọn cv!',
					/* text: 'Redirecting...', */
					icon : 'error',
					timer : 3000,
					buttons : true,
					type : 'error'
				})
			} else {
				$.ajax({
					type : 'POST',
					url : '/user/apply-job/',
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
								title : 'Ứng tuyển thành công!',
								/* text: 'Redirecting...', */
								icon : 'success',
								timer : 3000,
								buttons : true,
								type : 'success'
							})
							$(nameModal).modal('hide');
							$('#fileUpload').val("");
						} else {
							swal({
								title : 'Bạn đã ứng tuyển công việc này!',
								/* text: 'Redirecting...', */
								icon : 'error',
								timer : 3000,
								buttons : true,
								type : 'error'
							})
							$(nameModal).modal('hide');
							$('#fileUpload').val("");
						}
					},
					error : function(err) {
						alert(err);
					}
				})
			}

		}
	</script>



	<footer th:replace="public/fragments :: footer"
		class="ftco-footer ftco-bg-dark ftco-section"> </footer>


	<!-- loader -->
	<!--<div th:replace="public/fragments :: loading" id="ftco-loader" class="show fullscreen"></div>-->

</body>
</html>