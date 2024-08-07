<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Detail post</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
<c:if test="${not empty success_mgs}">
<div class="toast" data-delay="2000" style="position:fixed;top: 100PX; right: 10PX;z-index: 2000;width: 300px">
    <script>
        swal({
            title: 'Duyệt thành công!',
            /* text: 'Redirecting...', */
            icon: 'success',
            timer: 3000,
            buttons: true,
            type: 'success'
        })
    </script>
</div>
</c:if>
<div class="hero-wrap hero-wrap-2" style="background-image: url('${pageContext.request.contextPath }/assets/images/bg_1.jpg');" data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end justify-content-start">
            <div class="col-md-12 text-center mb-5">
                <p class="breadcrumbs mb-0"><span class="mr-3"><a href="index.html">Trang chủ <i class="ion-ios-arrow-forward"></i></a></span>Chi tiết <span></span></p>
                <h1 class="mb-3 bread">Chi tiết công việc</h1>
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
                        <img width="100" height="100" src="${pageContext.request.contextPath }/assets/images/${recruitment.company.logo}" alt="Image">
                    </div>
                    <div>
                        <h2>${recruitment.title}</h2>
                        <div>
                            <span class="icon-briefcase mr-2"></span><a class="ml-0 mr-2 mb-2"></a>
                            <span  class="icon-room mr-2"></span ><span class="m-2"></span>
                            <span class="icon-clock-o mr-2"></span><span class="m-2"></span>
                        </div>
                        <input type="hidden">
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="row">
                    <div class="col-6">
                        <a onclick="save(${recruitment.id})" class="btn btn-block btn-light btn-md"><span class="icon-heart-o mr-2 text-danger"></span>Lưu</a>
                    </div>
                    <div class="col-6">
                        <a data-toggle="modal" data-target="#exampleModal${recruitment.id}" class="btn btn-block btn-primary btn-md">Ứng tuyển</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div  class="row">
                    
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8">
                <div class="mb-5">

                    <h3 class="h5 d-flex align-items-center mb-4 text-primary"><span class="icon-align-left mr-3"></span>Mô tả công việc</h3>
                    <p>${recruitment.description}</p>
                </div>

            </div>
            <div class="col-lg-4">
                <div class="bg-light p-3 border rounded mb-4">
                    <h3 class="text-primary  mt-3 h5 pl-3 mb-3 ">Tóm tắt công việc</h3>
                    <ul class="list-unstyled pl-3 mb-0">
                       <li class="mb-2"> <strong class="text-black">Ngày tạo: </strong> <span>${recruitment.createdAt}</span> </li>
                        <li class="mb-2"><strong class="text-black">Kiểu công việc: </strong> <span>${recruitment.category.name}</span></li>
                        <li class="mb-2"><strong class="text-black">Loại công việc: </strong> <span>${recruitment.type}</span></li>
                        <li class="mb-2"><strong class="text-black">Kinh nghiệm: </strong> <span>${recruitment.experience}</span></li>
                        <li class="mb-2"><strong class="text-black">Địa chỉ: </strong> <span> ${recruitment.address}</span></li>
                        <li class="mb-2"><strong class="text-black">Lương: </strong> <span> ${recruitment.salary} </span></li>
                        <li class="mb-2"><strong class="text-black">Số lượng: </strong><span>${recruitment.quantity}</span></li>
                        <li class="mb-2"><strong class="text-black">Hạn nộp cv: </strong><span>${recruitment.deadline}</span></li>
                    </ul>
                </div>

                <div class="bg-light p-3 border rounded">
                    <h3 class="text-primary  mt-3 h5 pl-3 mb-3 ">Share</h3>
                    <div class="px-3">
                        <a href="#" class="pt-3 pb-3 pr-3 pl-0"><span class="icon-facebook"></span></a>
                        <a href="#" class="pt-3 pb-3 pr-3 pl-0"><span class="icon-twitter"></span></a>
                        <a href="#" class="pt-3 pb-3 pr-3 pl-0"><span class="icon-linkedin"></span></a>
                        <a href="#" class="pt-3 pb-3 pr-3 pl-0"><span class="icon-pinterest"></span></a>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>
<!-- Modal -->
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" id="exampleModal${recruitment.id}">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ứng tuyển: <span></span></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form  method="post" enctype="multipart/form-data">
            <input type="hidden" id="userName${recruitment.id }" value="${pageContext.request.userPrincipal.name}">
            <input type="hidden" id="idRe${recruitment.id }" value="${recruitment.id}">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-12">
                            <label for="fileUpload"
                                   class="col-form-label">Chọn cv:</label>
                            <input type="file" class="form-control"
                                   id="fileUpload${recruitment.id}" name="file"   required>
                            <label for="fileUpload"
                                   class="col-form-label">Giới thiệu:</label>
                            <textarea rows="10" cols="3" class="form-control" id="text${recruitment.id}">

                            </textarea>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                        <button type="button" onclick="apply(${recruitment.id})" class="btn btn-primary">Nộp</button>
                    </div>
                </div>
            </form>


        </div>
    </div>
</div>


<section class="site-section" id="next">
    <div class="container">

          <div class="row mb-5 justify-content-center">
            <div class="col-md-7 text-center">
            <c:choose>
            <c:when test="${ totalApplyPosts >0}">
                <h2 class="section-title mb-2">Danh sách ứng viên ứng tuyển</h2>
            </c:when>
            <c:otherwise>
                <h2 class="section-title mb-2">Những công việc liên quan</h2>
            </c:otherwise>
            </c:choose>
            </div> 
        </div>
        <div class="row">
             <div class="col-lg-12 pr-lg-4">
                <div class="row">
              <c:if test="${totalApplyPosts > 0 }">
                  <c:forEach var="applyPost" items="${applyPosts}">
                        <div class="col-md-12" style="box-shadow: rgba(0, 0, 0, 0.4) 0px 0px 10px;margin: 20px auto;">
                            <div class="team d-md-flex p-4 bg-white">
                            <c:choose>
                            <c:when test="${applyPost.user.image != null}">
                            <IMG class="img" src="${pageContext.request.contextPath }/assets/images/${applyPost.user.image}">
                            </c:when>
                            <c:otherwise>
                                <IMG class="img" src="https://st.quantrimang.com/photos/image/072015/22/avatar.jpg"></IMG>
                            </c:otherwise>
                            </c:choose>
                                <div class="text pl-md-4">
                                    <H5 class="location mb-0">${applyPost.user.userName}</H5>
                                    <p style="display: block;color: black">${applyPost.user.address}</p>
                                    <span class="position" style="display: block;color: black">${applyPost.user.email}</span>
                                    <p class="mb-4" style="width: 700px">${applyPost.user.description}</p>
                              <%--      <div class="row">
                                        <p><a href="#" th:if="${applyPost.nameCv != null}" class="btn btn-primary" th:href="${'http://localhost:8080/resources/uploads/'} +${applyPost.nameCv}" >Xem cv</a></p>
                                        <p th:if="${applyPost.status == 0}" style="margin-left: 10px"><a class="btn btn-outline-primary" th:href="${'/user/approve/'} +${applyPost.user.id} +${'/'} +${recruitment.id}" >Duyệt</a></p>
                                        <p th:if="${applyPost.status == 1}" style="margin-left: 10px;margin-top: 15px"><span style="color: #1e7e34;font-weight: bold" >Đã duyệt</span></p>
                                    </div> --%> 
                                </div>
                            </div>
                        </div>
                </c:forEach>
                 </c:if>
               
                <c:if test="${totalApplyPosts==0 }">
                        <p>Chưa có ứng cử viên nào ứng tuyển</p>
                </c:if>


                </div>

            </div>
            <div class="col-lg-4 sidebar">
                <div class="sidebar-box bg-white p-4 ftco-animate">
                    <h3 class="heading-sidebar">Browse Category</h3>
                    <form action="#" class="search-form mb-3">
                        <div class="form-group">
                            <span class="icon icon-search"></span>
                            <input type="text" class="form-control" placeholder="Search...">
                        </div>
                    </form>
                    <form action="#" class="browse-form">
                        <label for="option-job-1"><input type="checkbox" id="option-job-1" name="vehicle" value="" checked> Website &amp; Software</label><br>
                        <label for="option-job-2"><input type="checkbox" id="option-job-2" name="vehicle" value=""> Education &amp; Training</label><br>
                        <label for="option-job-3"><input type="checkbox" id="option-job-3" name="vehicle" value=""> Graphics Design</label><br>
                        <label for="option-job-4"><input type="checkbox" id="option-job-4" name="vehicle" value=""> Accounting &amp; Finance</label><br>
                        <label for="option-job-5"><input type="checkbox" id="option-job-5" name="vehicle" value=""> Restaurant &amp; Food</label><br>
                        <label for="option-job-6"><input type="checkbox" id="option-job-6" name="vehicle" value=""> Health &amp; Hospital</label><br>
                    </form>
                </div>

                <div class="sidebar-box bg-white p-4 ftco-animate">
                    <h3 class="heading-sidebar">Select Location</h3>
                    <form action="#" class="search-form mb-3">
                        <div class="form-group">
                            <span class="icon icon-search"></span>
                            <input type="text" class="form-control" placeholder="Search...">
                        </div>
                    </form>
                    <form action="#" class="browse-form">
                        <label for="option-location-1"><input type="checkbox" id="option-location-1" name="vehicle" value="" checked> Sydney, Australia</label><br>
                        <label for="option-location-2"><input type="checkbox" id="option-location-2" name="vehicle" value=""> New York, United States</label><br>
                        <label for="option-location-3"><input type="checkbox" id="option-location-3" name="vehicle" value=""> Tokyo, Japan</label><br>
                        <label for="option-location-4"><input type="checkbox" id="option-location-4" name="vehicle" value=""> Manila, Philippines</label><br>
                        <label for="option-location-5"><input type="checkbox" id="option-location-5" name="vehicle" value=""> Seoul, South Korea</label><br>
                        <label for="option-location-6"><input type="checkbox" id="option-location-6" name="vehicle" value=""> Western City, UK</label><br>
                    </form>
                </div>

                <div class="sidebar-box bg-white p-4 ftco-animate">
                    <h3 class="heading-sidebar">Job Type</h3>
                    <form action="#" class="browse-form">
                        <label for="option-job-type-1"><input type="checkbox" id="option-job-type-1" name="vehicle" value="" checked> Partime</label><br>
                        <label for="option-job-type-2"><input type="checkbox" id="option-job-type-2" name="vehicle" value=""> Fulltime</label><br>
                        <label for="option-job-type-3"><input type="checkbox" id="option-job-type-3" name="vehicle" value=""> Intership</label><br>
                        <label for="option-job-type-4"><input type="checkbox" id="option-job-type-4" name="vehicle" value=""> Temporary</label><br>
                        <label for="option-job-type-5"><input type="checkbox" id="option-job-type-5" name="vehicle" value=""> Freelance</label><br>
                        <label for="option-job-type-6"><input type="checkbox" id="option-job-type-6" name="vehicle" value=""> Fixed</label><br>
                    </form>
                </div>
            </div> 
        </div>
   <%--     <th:block  th:each="recruitment : ${listRelated}">
            <div th:unless="${applyPosts}" class="col-md-12 ">
                <div class="job-post-item p-4 d-block d-lg-flex align-items-center">
                    <div class="one-third mb-4 mb-md-0">
                        <div class="job-post-item-header align-items-center">
                            <span class="subadge" th:text="${recruitment.type}"></span>
                            <h2 class="mr-3 text-black" ><a th:text="${recruitment.title}" th:href="${'/recruitment/detail/'} +${recruitment.id}"></a></h2>
                        </div>
                        <div class="job-post-item-body d-block d-md-flex">
                            <div class="mr-3"><span class="icon-layers"></span> <a href="#" th:text="${recruitment.company.nameCompany}" ></a></div>
                            <div><span class="icon-my_location"></span> <span th:text="${recruitment.address}"></span></div>
                        </div>
                    </div>

                    <input type="hidden" th:id="${'idRe'}+${recruitment.id}" th:value="${recruitment.id}">
                    <div th:if="${session.user}" class="one-forth ml-auto d-flex align-items-center mt-4 md-md-0">
                        <div th:if="${session.user.role.id == 1}">
                            <a  th:onclick="'save(' +${recruitment.id}+ ')'" class="icon text-center d-flex justify-content-center align-items-center icon mr-2">
                                <span class="icon-heart"></span>
                            </a>
                        </div>
                        <a th:if="${session.user.role.id == 1}" data-toggle="modal" th:data-target="${'#exampleModal'}+${recruitment.id}" class="btn btn-primary py-2">Apply Job</a>
                    </div>
                    <div th:unless="${session.user}" class="one-forth ml-auto d-flex align-items-center mt-4 md-md-0">
                        <div >
                            <a  th:onclick="'save(' +${recruitment.id}+ ')'" class="icon text-center d-flex justify-content-center align-items-center icon mr-2">
                                <span class="icon-heart"></span>
                            </a>
                        </div>
                        <a  data-toggle="modal" th:data-target="${'#exampleModal'}+${recruitment.id}" class="btn btn-primary py-2">Apply Job</a>
                    </div>
                </div>
            </div>
        </th:block> 
--%>
        <script>

            function apply(id){
                var name = "#idRe" +id;
                var nameModal = "#exampleModal" +id;
                var nameFile = "#fileUpload"+id;
                var nameText = "#text" +id;
                var user = "#userName"+id;
                var idRe = $(name).val();
                var textvalue = $(nameText).val();
                var fileUpload = $(nameFile).get(0);
                var files = fileUpload.files;
                var userName = $(user).val();
                var formData = new FormData();
                formData.append('file', files[0]);
                formData.append('idRe', idRe);
                formData.append('text', textvalue);
                formData.append('userName',userName);
                formData.append('useExistingCV',false);
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
                console.log('userName: '+userName);
                    $.ajax(
                        {
                            type: 'POST',
                            url: window.location.origin + '/job-search-site/user/applyJob/',
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
                var user = "#userName"+id;
                var userName = $(user).val();
                var idRe = $(name).val();
                var formData = new FormData();
                formData.append('idRe', idRe);
                formData.append('userName',userName);
                $.ajax(
                    {
                        type: 'POST',
                        url: window.location.origin + '/job-search-site/user/saveJob/',
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
        </script>

    </div>
</section>




<footer th:replace="public/fragments :: footer" class="ftco-footer ftco-bg-dark ftco-section">

</footer>
</body>
</html>