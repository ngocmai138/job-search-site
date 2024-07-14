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
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s"></script>
<script src="${pageContext.request.contextPath}/assets/js/google-map.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<script
	src="https://cdn.ckeditor.com/ckeditor5/29.0.0/classic/ckeditor.js"></script>
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
<c:if test="${success}">
<div class="toast" data-delay="2000" style="position:fixed;top: 100PX; right: 10PX;z-index: 2000;width: 300px">
    <script>
        swal({
            title: 'Xóa thành công!',
            /* text: 'Redirecting...', */
            icon: 'success',
            timer: 3000,
            buttons: true,
            type: 'success'
        })
    </script>
</div>
</c:if>
<!-- END nav -->
<s:authorize access="hasRole('candidate')">
<div class="hero-wrap hero-wrap-2" style="background-image: url('${pageContext.request.contextPath }/user/assets/images/bg_1.jpg');" data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end justify-content-start">
            <div class="col-md-12 text-center mb-5">
                <p class="breadcrumbs mb-0"><span class="mr-3"><a href="${pageContext.request.contextPath }/">Trang chủ <i class="ion-ios-arrow-forward"></i></a></span>Công việc <span></span></p>
                <h1 class="mb-3 bread">Danh sách công việc đã ứng tuyển</h1>
            </div>
        </div>
    </div>
</div>
</s:authorize>
<s:authorize access="hasRole('recruiter')">
<div class="hero-wrap hero-wrap-2" style="background-image: url('${pageContext.request.contextPath }/user/assets/images/bg_1.jpg');" data-stellar-background-ratio="0.5">
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
<s:authorize access="hasRole('candidate')">
<section class="ftco-section bg-light">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 pr-lg-5">
            	<c:if test="${totalApplyPosts >0 }">
                <div class="row">
                	<c:forEach var="applyPost" items="${applyPosts }">
                        <div class="col-md-12 ">
                            <div class="job-post-item p-4 d-block d-lg-flex align-items-center">
                                <div class="one-third mb-4 mb-md-0">
                                    <div class="job-post-item-header align-items-center">
                                        <span class="subadge">${applyPost.recruitment.type}</span>
                                        <h2 class="mr-3 text-black" ><a href="${pageContext.request.contextPath}/recruitment/detail?recruitmentId=${applyPost.recruitment.id}">${applyPost.recruitment.title}</a></h2>
                                    </div>
                                    <div class="job-post-item-body d-block d-md-flex">
                                        <div class="mr-3"><span class="icon-layers"></span> <a href="${pageContext.request.contextPath}/user/showDetailCompany?companyId=${applyPost.recruitment.company.id}">${applyPost.recruitment.company.nameCompany}</a></div>
                                        <div><span class="icon-my_location"></span> <span th:text="${saveJob.recruitment.address}"></span></div>
                                    </div>
                                </div>
                                <input type="hidden" id="idRe${applyPost.id}" value="${applyPost.recruitment.id}">
                                <input type="hidden" id="idAP${applyPost.id}" value="${applyPost.id}">
                                <div class="one-forth ml-auto d-flex align-items-center mt-4 md-md-0">
                                    <c:if test="${applyPost.status == 1}">
                                    <p style="color: #1e7e34;font-weight: bold;margin-top: 10px">Đã duyệt</p>
                                    </c:if>
                                    <c:if test="${applyPost.status == 0}">
                                    <div>
                                        <a onclick="delapply(${applyPost.id})" class="icon text-center d-flex justify-content-center align-items-center icon mr-2">
                                            <span class="icon-delete"></span>
                                        </a>
                                    </div>
                                    <p style="color: red;font-weight: bold;margin-top: 10px">Đợi duyệt</p>
                                    </c:if>
                                </div>
                            </div>
                        </div><!-- end -->
                        <!-- Modal -->
                        <div class="modal fade" id="exampleModal${applyPost.recruitment.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Ứng tuyển: <span>${applyPost.recruitment.title}</span></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <form method="post" action="/user/apply-job">
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="col-12">
                                                    <label for="fileUpload"
                                                           class="col-form-label">Chọn cv:</label>
                                                    <input type="file" class="form-control"
                                                           id="fileUpload${saveJob.recruitment.id}" name="file"   required>
                                                    <label for="fileUpload"
                                                           class="col-form-label">Giới thiệu:</label>
                                                    <textarea rows="10" cols="3" class="form-control"  id="text${saveJob.recruitment.id}" >

                                                    </textarea>
                                                </div>

                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                                                <button type="button" onclick="apply(${saveJob.recruitment.id})" class="btn btn-primary">Nộp</button>
                                            </div>
                                        </div>
                                    </form>


                                </div>
                            </div>
                        </div>

				</c:forEach>
                </div>
                </c:if>
                <c:if test="${totalApplyPosts < 1 }">
                <div style="text-align: center">
                    <p style="color:red;">Danh sách trống</p>
                </div>
                </c:if>
                <div class="row mt-5">
                    <div class="col text-center">
                        <div class="block-27">
                            <ul>
                            	<c:if test="${pagePrev >0 }">
                                	<li><a href="${pageContext.request.contextPath}/user/getListApply?username=${pageContext.request.userPrincipal.name}&pageNumber=${pagePrev}&pageSize=${pageSize}">&lt;</a></li>
                            	</c:if>
                            	<c:forEach var="i" begin="1" end="${ totalPages}">
                                    <li class="${pageNumber == i  ? 'active' : ' ' }">
                                    	<a href="${pageContext.request.contextPath}/user/getListApply?username=${pageContext.request.userPrincipal.name}&pageNumber=${i}&pageSize=${pageSize}">${i }</a>
                                    </li>
                            	</c:forEach>
                                <c:if test="${pageNext <= totalPages}">
                                	<li><a href="${pageContext.request.contextPath}/user/getListApply?username=${pageContext.request.userPrincipal.name}&pageNumber=${pageNext}&pageSize=${pageSize}">&gt;</a></li>
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
    function apply(id){
        var name = "#idRe" +id;
        var nameModal = "#exampleModal" +id;
        var nameFile = "#fileUpload"+id;
        var nameText = "#text" +id;
        var idRe = $(name).val();
        var textvalue = $(nameText).val();
        var fileUpload = $(nameFile).get(0);
        var files = fileUpload.files;
        var formData = new FormData();
        formData.append('file', files[0]);
        formData.append('idRe', idRe);
        formData.append('text', textvalue);
        if(files[0] == null){
            swal({
                title: 'Bạn cần phải chọn cv!',
                /* text: 'Redirecting...', */
                icon: 'error',
                timer: 3000,
                buttons: true,
                type: 'error'
            })
        } else {
            $.ajax(
                {
                    type: 'POST',
                    url: '/user/apply-job/',
                    contentType: false,
                    processData: false,
                    data: formData,
                    success: function (data) {
                        if(data == "false"){
                            swal({
                                title: 'Bạn cần phải đăng nhập!',
                                /* text: 'Redirecting...', */
                                icon: 'error',
                                timer: 3000,
                                buttons: true,
                                type: 'error'
                            })
                        }else if(data == "true"){
                            swal({
                                title: 'Ứng tuyển thành công!',
                                /* text: 'Redirecting...', */
                                icon: 'success',
                                timer: 3000,
                                buttons: true,
                                type: 'success'
                            })
                            $(nameModal).modal('hide');
                            $('#fileUpload').val("");
                        }else{
                            swal({
                                title: 'Bạn đã ứng tuyển công việc này!',
                                /* text: 'Redirecting...', */
                                icon: 'error',
                                timer: 3000,
                                buttons: true,
                                type: 'error'
                            })
                            $(nameModal).modal('hide');
                            $('#fileUpload').val("");
                        }
                    },
                    error: function (err) {
                        alert(err);
                    }
                }
            )
        }

    }
    function save(id){
        var name = "#idRe" +id;
        var idRe = $(name).val();
        var formData = new FormData();
        formData.append('idRe', idRe);
        $.ajax(
            {
                type: 'POST',
                url: '/save-job/save/',
                contentType: false,
                processData: false,
                data: formData,
                success: function (data) {
                    if(data == "false"){
                        swal({
                            title: 'Bạn cần phải đăng nhập!',
                            /* text: 'Redirecting...', */
                            icon: 'error',
                            timer: 3000,
                            buttons: true,
                            type: 'error'
                        })
                    }else if(data == "true"){
                        swal({
                            title: 'Lưu thành công!',
                            /* text: 'Redirecting...', */
                            icon: 'success',
                            timer: 3000,
                            buttons: true,
                            type: 'success'
                        })
                    }else{
                        swal({
                            title: 'Bạn đã lưu bài này rồi!',
                            /* text: 'Redirecting...', */
                            icon: 'error',
                            timer: 3000,
                            buttons: true,
                            type: 'error'
                        })
                    }
                },
                error: function (err) {
                    alert(err);
                }
            }
        )
    }
    function delapply(id){
        var name = "#idAP" +id;
        var idAP = $(name).val();
        var formData = new FormData();
        formData.append('idAP', idAP);
        $.ajax(
            {
                type: 'POST',
                url: window.location.origin + '/job-search-site/user/deleteApplyPost/',
                contentType: false,
                processData: false,
                data: formData,
                success: function (data) {
                    if(data == "false"){
                        swal({
                            title: 'Bạn cần phải đăng nhập!',
                            /* text: 'Redirecting...', */
                            icon: 'error',
                            timer: 3000,
                            buttons: true,
                            type: 'error'
                        })
                    }else if(data == "true"){
                        swal({
                            title: 'Huỷ ứng tuyển thành công!',
                            /* text: 'Redirecting...', */
                            icon: 'success',
                            timer: 3000,
                            buttons: true,
                            type: 'success'
                        }).then(()=>{
                        	location.reload();
                        });
                    }else{
                        swal({
                            title: 'Bạn đã hủy ứng tuyển công ty này rồi!',
                            /* text: 'Redirecting...', */
                            icon: 'error',
                            timer: 3000,
                            buttons: true,
                            type: 'error'
                        })
                    }
                },
                error: function (err) {
                    alert(err);
                }
            }
        )
    }
</script>



<footer th:replace="public/fragments :: footer" class="ftco-footer ftco-bg-dark ftco-section">

</footer>


<!-- loader -->
<!--<div th:replace="public/fragments :: loading" id="ftco-loader" class="show fullscreen"></div>-->

</body>
</html>