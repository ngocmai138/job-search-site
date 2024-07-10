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
								<li><a
									href="${pageContext.request.contextPath}/showProfile?username=${pageContext.request.userPrincipal.name}">Hồ
										sơ</a></li>
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
						<li class="nav-item"><a class="nav-link" href="#"> 
						<s:authentication property="principal.username" />
						</a>
							<ul class="dropdown">
								<li><a href="#"
									onclick="document.getElementById('detailCompany').submit();">Hồ
										Sơ</a> <f:form id="detailCompany"
										action="${pageContext.request.contextPath }/showProfile"
										style="display:none;">
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



	<div class="hero-wrap hero-wrap-2"
		style="background-image: url('${pageContext.request.contextPath}/assets/images/bg_1.jpg');"
		data-stellar-background-ratio="0.5">
		<div class="overlay"></div>
		<div class="container">
			<div
				class="row no-gutters slider-text align-items-end justify-content-start">
				<div class="col-md-12 text-center mb-5">
					<h1 class="mb-3 bread">Hồ sơ</h1>
					<div class="form-group">
						<label class="btn btn-primary btn-md btn-file"> Chọn ảnh<input
							type="file" name="file" id="fileUpload" hidden>
						</label>
					</div>
					<div style="margin-left: 0px" id="divImage">
						<c:choose>
							<c:when test="${user.image != null}">
								<img id="avatar" height="100" width="100"
									style="border-radius: 50px"
									src="${pageContext.request.contextPath }/assets/images/${user.image }" />
							</c:when>
							<c:otherwise>
								<img id="avatar" height="100" width="100"
									style="border-radius: 50px"
									src="https://st.quantrimang.com/photos/image/072015/22/avatar.jpg" />
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- HOME -->
	<c:if test="${user.status == 0}">
		<div class="container-fluid" style="text-align: center">
			<p
				style="font-size: 20px; font-weight: bold; color: #aaa; margin-top: 10px">Xác
				thực email đăng nhập</p>
			<div
				style="width: 600px; height: 400px; border-radius: 5px; box-shadow: rgba(0, 0, 0, 0.4) 0px 0px 10px; margin: 20px auto; padding: 15px">
				<p style="line-height: 35px; font-size: 16px">
					Xin chào, <span>${user.userName}</span> và làm theo
					hướng dẫn trong email. Trường hợp không nhận được email, bạn vui
					lòng bấm nút Nhận email xác thực dưới đây.
				</p>
				<div class="row form-group">
					<form action="/user/confirm-account" method="post"
						class="col-md-12">
						<input type="hidden" value="${user.email}" name="email"
							class="btn px-4 btn-primary text-white"> <input
							type="submit" value="Nhận email xác thực"
							class="btn px-4 btn-primary text-white">
					</form>
				</div>
				<p>
					Mọi thắc mắc vui lòng liên hệ bộ phận CSKH của WorkCV:<br>
				</p>
				- Điện thoại:<span style="color: #5f80ec">(024) 6680 5588</span><br>
				- Email: <a href="#" style="color: #5f80ec"> hotro@workcv.vn</a>
			</div>
			<c:if test="${not empty comfirm_await}">
			<div
				style="width: 600px; height: 400px; border-radius: 5px; box-shadow: rgba(0, 0, 0, 0.4) 0px 0px 10px; margin: 20px auto; padding: 15px">
				<p style="line-height: 35px; font-size: 16px">
					Xin chào, <span>${user.userName}</span> .Bạn đã gửi yêu
					cầu xác thực thành công, vui lòng kiểm tra mail để xác thực.Cảm ơn
					bạn!!!
				</p>
				<p>
					Mọi thắc mắc vui lòng liên hệ bộ phận CSKH của WorkCV:<br>
				</p>
				- Điện thoại:<span style="color: #5f80ec">(024) 6680 5588</span><br>
				- Email: <a href="#" style="color: #5f80ec"> hotro@workcv.vn</a>
			</div>
			</c:if>
		</div>
	</c:if>
	<c:if test="${user.status == 1}">
		<s:authorize access="hasRole('candidate')">
			<section class="site-section" style="margin-top: 10px">
				<div class="container">
					<form action="${pageContext.request.contextPath}/user/uploadCv"
						enctype="multipart/form-data" method="post">
						<input type="hidden" name="userId" id="userId" value="${user.id }">
						<div class="row align-items-center mb-5">
							<div class="col-lg-8 ">
								<div class="d-flex align-items-center">
									<div class="form-group" style="margin-top: 15px">
										<label class="btn btn-primary btn-md btn-file"> Chọn
											cv (pdf)<input type="file" name="file" id="fileUpload1"
											required hidden>
										</label>
									</div>
								</div>
								<c:if test="${Cv != null}">
									<p id="cvName">${Cv != null ? Cv.fileName :'Chưa cập nhập'}</p>
									<a id="nameCv"
										href="${pageContext.request.contextPath}/uploads/${Cv.fileName}">Xem
										cv</a>
									<a style="color: red; margin-left: 20px" data-toggle="modal"
										data-target="#exampleModal">Xóa cv</a>
								</c:if>
								<c:if test="${Cv == null}">
									<p id="cvName"></p>
									<a id="nameCv"
										href="${pageContext.request.contextPath}/resources/uploads/"></a>
									<a style="color: red; margin-left: 20px" id="xoa"
										data-toggle="modal" data-target="#exampleModal"></a>
								</c:if>
							</div>

							<div class="col-lg-4">
								<div class="row">
									<!--                        <div class="col-6">-->
									<!--                            <button type="submit" id="myBtn" class="btn btn-block btn-light btn-md"><span class="icon-open_in_new mr-2"></span>Cập nhật cv</button>-->
									<!--                        </div>-->
								</div>
							</div>
						</div>
					</form>

					<f:form modelAttribute="user"
						action="${pageContext.request.contextPath }/user/updateProfile"
						method="post">
						<input type="hidden" name="id" value="${user.id }">
						<input type="hidden" name="image" value="${user.image }">
						<input type="hidden" name="password" value="${user.password }">
						<input type="hidden" name="status" value="${user.status }">
						<input type="hidden" name="isActive" value="${user.isActive }">
						<input type="hidden" name="companyId" value="${company.id }">

						<div class="row align-items-center mb-5">
							<div class="col-lg-8 mb-4 mb-lg-0">
								<div class="d-flex align-items-center">
									<div>
										<h2>Cập nhật thông tin</h2>
										<c:if test="${not empty update_user_message }">
											<div class="alert alert-success" role="alert">
												${update_user_message }</div>
										</c:if>
									</div>
								</div>
							</div>
							<div class="col-lg-4">
								<div class="row">

									<div class="col-6">
										<button type="submit" class="btn btn-block btn-primary btn-md">Lưu
											thông tin</button>
									</div>
								</div>
							</div>
						</div>
						<div class="row mb-5">
							<div class="col-lg-12">
								<div class="p-4 p-md-5 border rounded" method="post">
									<h3 class="text-black mb-5 border-bottom pb-2">Thông tin
										chi tiết</h3>
									<div class="form-group">
										<label for="email">Email</label> <input type="email"
											class="form-control" id="email" name="email"
											value="${user.email != null ? user.email : null}" required
											placeholder="you@yourdomain.com">
									</div>
									<div class="form-group">
										<label for="job-title">User name</label> <input type="text"
											class="form-control" readonly="readonly" name="userName"
											value="${user.userName != null ? user.userName : null}"
											required id="job-title" placeholder="Full name">
									</div>
									<div class="form-group">
										<label for="job-location">Địa chỉ</label> <input type="text"
											name="address"
											value="${user.address != null ? user.address : null}"
											class="form-control" id="job-location" required
											placeholder="e.g. New York">
									</div>
									<div class="form-group">
										<label for="job-location">Số điện thoại</label> <input
											type="text" name="phoneNumber"
											value="${user.phoneNumber != null ? user.phoneNumber : null}"
											class="form-control" id="job-location" required
											placeholder="+ 84">
									</div>
									<div class="form-group">
										<label for="job-location">Mô tả bản thân</label>
										<textarea name="description" class="form-control" id="editor"
											placeholder="Mô tả">${user.description != null ? user.description : null}</textarea>
									</div>

								</div>

							</div>

						</div>
					</f:form>
				</div>
			</section>
		</s:authorize>
	</c:if>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Bạn có chắc
						chắn muốn xóa ?</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					Cv : <span id="cvXoa"></span>
					<c:if test="${Cv != null}">
						<span>${Cv.fileName}</span>
					</c:if>
					<f:form action="${pageContext.request.contextPath }/user/deleteCv"
						method="post">
						<div class="modal-footer mt-1">
							<input type="hidden" name="idCv" value="${Cv.id }">
							<button type="submit" class="btn btn-secondary"
								data-dismiss="modal">Đóng</button>
							<button type="submit" class="btn btn-danger">Xóa</button>
						</div>
					</f:form>
				</div>

			</div>
		</div>
	</div>
	<c:if test="${user.status == 1}">
		<s:authorize access="hasRole('recruiter')">
			<section class="site-section" style="margin-top: 10px">
				<div class="container">
					<div class="row">
						<div class="col-lg-6 mb-5">
							<c:if test="${not empty update_user_message }">
								<div class="alert alert-success" role="alert">
									${update_user_message }</div>
							</c:if>
							<h2 class="mb-4">Thông tin cá nhân</h2>
							<f:form
								action="${pageContext.request.contextPath }/user/updateProfile"
								method="post">
								<input type="hidden" name="id" value="${user.id }">
								<input type="hidden" name="image" value="${user.image }">
								<input type="hidden" name="password" value="${user.password }">
								<input type="hidden" name="status" value="${user.status }">
								<input type="hidden" name="isActive" value="${user.isActive }">
								<div class="row mb-5">
									<div class="col-lg-12">
										<div class="p-4 p-md-5 border rounded" method="post">
											<div class="form-group">
												<label for="email">Email</label> <input type="email"
													class="form-control" id="email" name="email"
													value="${user.email}" required
													placeholder="you@yourdomain.com">
											</div>
											<div class="form-group">
												<label for="job-title">Tài khoản</label> <input type="text"
													class="form-control" readonly="readonly" name="userName"
													value="${user.userName}" id="job-title" required
													placeholder="User name">
											</div>
											<div class="form-group">
												<label for="job-location">Địa chỉ</label> <input type="text"
													name="address"
													value="${user.address != null ? user.address : null}"
													required class="form-control" id="job-location"
													placeholder="e.g. New York">
											</div>
											<div class="form-group">
												<label for="job-location">Số điện thoại</label> <input
													type="text" name="phoneNumber"
													value="${user.phoneNumber != null ? user.phoneNumber : null}"
													required class="form-control" id="job-location"
													placeholder="+ 84">
											</div>
											<div class="form-group">
												<label for="job-location">Mô tả bản thân</label>
												<textarea name="description" class="form-control"
													id="editor" placeholder="Mô tả">
                                    	<c:if
														test="${user.description != null}">${user.description}</c:if>
                                    </textarea>
											</div>
											<div class="row form-group">
												<div class="col-md-12">
													<input type="submit" value="Lưu thông tin"
														class="btn px-4 btn-primary text-white">
												</div>
											</div>
										</div>

									</div>

								</div>
							</f:form>
						</div>
						<div class="col-lg-6">
							<c:if test="${not empty update_company_message }">
								<div class="alert alert-success" role="alert">
									${update_company_message }</div>
							</c:if>
							<h2 class="mb-4">Thông tin công ty</h2>
							<div class="form-group">
								<label for="company-website-tw d-block1">Cập nhật Logo</label> <br>
								<label class="btn btn-primary btn-md btn-file"> Chọn
									logo<input type="file" name="file" id="fileUpload2" required
									hidden>
								</label>
								<div id="divLogo">
									<c:choose>
										<c:when test="${company.logo != null}">
											<img id="avatar1" height="100" width="100"
												style="border-radius: 50px"
												src="${pageContext.request.contextPath }/assets/images/${company.logo}">
										</c:when>
										<c:otherwise>
											<img id="avatar1" height="100" width="100"
												style="border-radius: 50px"
												src="https://st.quantrimang.com/photos/image/072015/22/avatar.jpg">
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<f:form
								action="${pageContext.request.contextPath }/user/updateCompany"
								method="post">
								<input type="hidden" name="userId" value="${company.user.id }">
								<input type="hidden" name="isActive"
									value="${company.isActive }">
								<input type="hidden" name="status" value="${company.status }">
								<input type="hidden" name="numberOfRecruitments"
									value="${company.numberOfRecruitments }">

								<div class="row mb-5">
									<div class="col-lg-12">
										<div class="p-4 p-md-5 border rounded">
											<div class="form-group">
												<label for="email">Email</label> <input type="text"
													class="form-control" required id="email1" name="email"
													value="${company.email != null ? company.email : null }"
													placeholder="you@yourdomain.com">
											</div>
											<div class="form-group">
												<label for="job-title">Tên công ty</label> <input
													type="text" class="form-control" name="nameCompany"
													value="${company.nameCompany != null ? company.nameCompany : null }"
													id="job-title" placeholder="Full name" required> <input
													type="hidden" class="form-control" name="user_id"
													value="${company.user.id != null ? company.user.id : null}"
													id="job-title" placeholder="Full name"> <input
													type="hidden" class="form-control" name="id"
													value="${company.id !=null ? company.id : null}"
													id="job-title" placeholder="Full name">

											</div>
											<div class="form-group">
												<label for="job-location">Địa chỉ</label> <input type="text"
													name="address"
													value="${company.address != null ? company.address : null}"
													required class="form-control" id="job-location"
													placeholder="e.g. New York">
											</div>
											<div class="form-group">
												<label for="job-location">Số điện thoại công ty</label> <input
													type="text" name="phoneNumber"
													value="${company.phoneNumber != null ? company.phoneNumber : null}"
													required class="form-control" id="job-location"
													placeholder="+ 84">
											</div>
											<div class="form-group">
												<label for="job-location">Mô tả công ty</label>
												<textarea name="description" class="form-control"
													id="editorN" placeholder="Mô tả">
                                    <c:if
														test="${company.description != null}">
                                    	${company.description}
                                    </c:if>
                                    </textarea>
											</div>

											<c:if test="${company.logo != null}">
												<div style="margin-left: 0px" id="divImag1">
													<img id="avatar" height="100" width="100"
														style="border-radius: 50px; margin-bottom: 15px"
														src="${pageContext.request.contextPath }/assets/images/${company.logo}">
												</div>
												<input type="hidden" name="logo" value="${company.logo}">
											</c:if>
											<div class="row form-group">
												<div class="col-md-12">
													<input type="submit" value="Lưu thông tin"
														class="btn px-4 btn-primary text-white">
												</div>
											</div>
										</div>
									</div>
								</div>
							</f:form>
						</div>
					</div>
				</div>
			</section>
		</s:authorize>
	</c:if>
	<script>
    ClassicEditor.create(document.querySelector('#editor')).then(eidt => {
        console.log("da" + eidt);
    })
        .catch(error => {
            console.error(error);
        });
    ClassicEditor.create(document.querySelector('#editorN')).then(eidt => {
        console.log("da" + eidt);
    })
        .catch(error => {
            console.error(error);
        });
</script>
	<script>
    $(function () {
        $('#fileUpload').change(function () {
            if (window.FormData !== undefined) {
                var fileUpload = $('#fileUpload').get(0);
                var files = fileUpload.files;
                var userId = $("#userId").val();
                var formData = new FormData();
                formData.append('file', files[0]);
                formData.append('userId', userId);
                if(files[0] == null){
                    // document.getElementById("change").style.backgroundColor = 'red';
                    // $('#text').val(" ❌ Cập nhật ảnh thất bại");
                    $(".toast").toast("show");
                } else {
                    $.ajax(
                        {
                            type: 'POST',
                            url: window.location.origin + '/job-search-site/user/uploadImage',
                            contentType: false,
                            processData: false,
                            data: formData,
                            success: function (response) {
                                console.log("Success: "+response)
                                if(response === "Error"){
                                    document.getElementById("change").style.backgroundColor = 'red';
                                    swal({
                                        title: 'Cập nhật ảnh đại diện thất bại!',
                                        /* text: 'Redirecting...', */
                                        icon: 'error',
                                        timer: 3000,
                                        buttons: true,
                                        type: 'error'
                                    })
                                    $("#divImage").css("display","block")
                                }else{
                                    $('#avatar').attr('src', response)
                                    swal({
                                        title: 'Cập nhật ảnh đại diện thành công!',
                                        /* text: 'Redirecting...', */
                                        icon: 'success',
                                        timer: 3000,
                                        buttons: true,
                                        type: 'success'
                                    })
                                }

                            },
                            error: function (err) {
                                alert(err);
                            }
                        }
                    )
                }

            }
        })
    })
</script>

	<script>
    $(function () {
        $('#fileUpload2').change(function () {
            if (window.FormData !== undefined) {
                var fileUpload = $('#fileUpload2').get(0);
                var files = fileUpload.files;
                var email = $("#email").val();
                var formData = new FormData();
                formData.append('file', files[0]);
                formData.append('email', email);
                if(files[0] == null){
                    // document.getElementById("change").style.backgroundColor = 'red';
                    // $('#text').val(" ❌ Cập nhật ảnh thất bại");
                    $(".toast").toast("show");
                } else {
                    $.ajax(
                        {
                            type: 'POST',
                            url: '/user/upload-company/',
                            contentType: false,
                            processData: false,
                            data: formData,
                            success: function (urlImage) {
                                console.log(urlImage)
                                if(urlImage == "Error"){
                                    document.getElementById("change").style.backgroundColor = 'red';
                                    swal({
                                        title: 'Cập nhật logo thất bại!',
                                        /* text: 'Redirecting...', */
                                        icon: 'error',
                                        timer: 3000,
                                        buttons: true,
                                        type: 'error'
                                    })
                                    $("#divLogo").css("display","block")
                                }else{
                                    $('#avatar1').attr('src', urlImage)
                                    swal({
                                        title: 'Cập nhật logo thành công!',
                                        /* text: 'Redirecting...', */
                                        icon: 'success',
                                        timer: 3000,
                                        buttons: true,
                                        type: 'success'
                                    })
                                }

                            },
                            error: function (err) {
                                alert(err);
                            }
                        }
                    )
                }

            }
        })
    })
</script>

	<script>
    $(function () {
        $('#fileUpload1').change(function () {
        	console.log("File input changed");
            if (window.FormData !== undefined) {
                var fileUpload = $('#fileUpload1').get(0);
                var files = fileUpload.files;
                var formData = new FormData();
                var userId = $('#userId').val();
                formData.append('file', files[0]);
                formData.append('userId',userId);
                if(files[0] == null){
                	console.log("No file selected");
                    // document.getElementById("change").style.backgroundColor = 'red';
                    // $('#text').val(" ❌ Cập nhật ảnh thất bại");
                    $(".toast").toast("show");
                } else {
                    $.ajax(
                        {
                            type: 'POST',
                            url: window.location.origin + '/job-search-site/user/uploadCv',
                            contentType: false,
                            processData: false,
                            data: formData,
                            success: function (response) {
                                console.log("Success: ", response)
                                if(response === "No file selected."){
                                    // document.getElementById("change").style.backgroundColor = 'red';

                                    swal({
                                        title: 'Cần chọn đúng loại file (PDF)!',
                                        /* text: 'Redirecting...', */
                                        icon: 'error',
                                        timer: 3000,
                                        buttons: true,
                                        type: 'error'
                                    })
                                    // $("#divImage").css("display","block")
                                }else{
                                    // $('#avatar').attr('src', urlImage)
                                    if (document.getElementById('nameCv')) {
							            document.getElementById('nameCv').innerHTML = 'Xem cv';
							            document.getElementById('nameCv').href = "/uploads/" + response;
							        }
							        if (document.getElementById('xoa')) {
							            document.getElementById('xoa').innerHTML = 'Xóa cv';
							        }
							        if (document.getElementById('cvName')) {
							            document.getElementById("cvName").innerHTML = response;
							        }
							        if (document.getElementById('cvXoa')) {
							            document.getElementById("cvXoa").innerHTML = response;
							        }

                                    swal({
                                        title: 'Cập nhật CV thành công!',
                                        /* text: 'Redirecting...', */
                                        icon: 'success',
                                        timer: 3000,
                                        buttons: true,
                                        type: 'success'
                                    })
                                }
                                setTimeout(function(){
                                	location.reload();                                	
                                }, 2000);

                            },
                            error: function (err) {
                            	console.error("Error: ",err);
                                alert("Error:" + JSON.stringify(err));
                            }
                        }
                    )
                }

            }
        })
    })
</script>

	<footer class="ftco-footer ftco-bg-dark ftco-section"> </footer>
</body>
</html>