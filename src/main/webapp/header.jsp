<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- favicon -->
<link rel="apple-touch-icon" sizes="57x57" href="${path}/img/favicon/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="${path}/img/favicon/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="${path}/img/favicon/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="${path}/img/favicon/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="${path}/img/favicon/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="${path}/img/favicon/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="${path}/img/favicon/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="${path}/img/favicon/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="${path}/img/favicon/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"  href="${path}/img/favicon/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="${path}/img/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="${path}/img/favicon/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="${path}/img/favicon/">
<link rel="manifest" href="${path}/img/favicon/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="${path}/img/favicon/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- fontawesome -->
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<!-- SWAL2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.19/dist/sweetalert2.all.min.js"></script>

<!-- common -->
<link rel="stylesheet" href="${path}/css/common.css" />
<script src="${path}/js/common.js" type="text/javascript"></script>
<title>:: PET CARE ::</title>
</head>
<body class="d-flex flex-column body-height">
	<header class="p-3 mb-3 border-bottom">
		<div class="container">
			<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
				<a href="${path}/home.do" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
					<img src="${path}/img/favicon/favicon.png" width="32" height="32">
					<span class="mx-2 fs-3 fw-bold">PET CARE</span>
				</a>
				<ul class="nav col-12 col-lg-auto me-lg-auto mb-2 ms-5 justify-content-center mb-md-0">
					<li>
						<a href="./searchHospital.do" class="nav-link px-4 link-dark">
							<i class="fas fa-search"></i> 병원찾기
						</a>
					</li>
					<c:choose>
						<c:when test="${sessionScope.member.usertype eq 'ROLE_USER'}">
							<li>
								<a href="${path}/" class="nav-link px-4 link-dark">
									<i class="fas fa-cat"></i> 내 애완동물
								</a>
							</li>
							<li>
								<a href="${path}/" class="nav-link px-4 link-dark">
									<i class="fas fa-book"></i> 건강수첩
								</a>
							</li>
							<li>
								<a href="${path}/" class="nav-link px-4 link-dark">
									<i class="far fa-calendar-check"></i> 예약내역
								</a>
							</li>
							<li>
								<a href="${path}/" class="nav-link px-4 link-dark">
									<i class="fas fa-notes-medical"></i> 진료내역
								</a>
							</li>
						</c:when>
						<c:otherwise>
						
						</c:otherwise>
					</c:choose>
				</ul>
				<c:choose>
					<c:when test="${sessionScope.member eq null}">
						<div class="col-md-3 text-end mx-3">
							<a href="${path}/login/loginForm.do" class="btn btn-secondary me-1">
								<i class="fas fa-sign-in-alt"></i> 로그인
							</a>
							<a href="${path}/login/signupAgree.do" class="btn btn-outline-secondary">
								<i class="fas fa-user-plus"></i> 회원가입
							</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="dropdown text-end mx-3">
							<a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
								<img src="${path}/img/logo.jpg" alt="mdo" width="32" height="32" class="rounded-circle">
							</a>
							<ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
								<li class="ms-3">
									<div>이거성님</div>
									<div style="font-size:small">안녕하세요.</div>
								</li>
								<li>
									<hr class="dropdown-divider">
								</li>
								<li>
									<a class="dropdown-item" href="${path}/login/userInfo.do">
										<i class="fas fa-user"></i> 내 정보
									</a>
								</li>
								<li>
									<a class="dropdown-item" href="#">
										<i class="fas fa-headset"></i> 고객의 소리
									</a>
								</li>
								<c:if test="${sessionScope.member.usertype eq 'ROLE_USER'}">
									<li>
										<a class="dropdown-item" href="#">
											<i class="fas fa-star"></i> 관심병원
										</a>
									</li>
								</c:if>
								<li>
									<hr class="dropdown-divider">
								</li>
								<li>
									<a class="dropdown-item" href="${path}/login/logout.do">
										<i class="fas fa-sign-out-alt"></i> 로그아웃
									</a>
								</li>
							</ul>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</header>
	<section>
		<div class="container">