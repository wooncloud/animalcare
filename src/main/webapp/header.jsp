<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- fontawesome -->
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

<!-- common -->
<link rel="stylesheet" href="./css/common.css" />
<script src="./js/common.js"></script>
<title>:: PET CARE ::</title>
</head>
<body class="d-flex flex-column body-height">
	<header class="p-3 mb-3 border-bottom">
		<div class="container">
			<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
				<a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
					<img src="./img/logo.jpg" width="48" height="48">
					<span class="mx-2 fs-3 fw-bold">PET CARE</span>
				</a>
				<ul class="nav col-12 col-lg-auto me-lg-auto mb-2 ms-5 justify-content-center mb-md-0">
					<li><a href="#" class="nav-link px-4 link-dark">메뉴1</a></li>
					<li><a href="#" class="nav-link px-4 link-dark">메뉴2</a></li>
					<li><a href="#" class="nav-link px-4 link-dark">메뉴3</a></li>
				</ul>

				<div class="col-md-3 text-end mx-3">
					<button type="button" class="btn btn-primary me-1"><i class="fas fa-sign-in-alt"></i> 로그인</button>
					<button type="button" class="btn btn-outline-primary"><i class="fas fa-user-plus"></i> 회원가입</button>
				</div>
				<div class="dropdown text-end mx-3">
					<a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
						<img src="./img/logo.jpg" alt="mdo" width="32" height="32" class="rounded-circle">
					</a>
					<ul class="dropdown-menu text-small"
						aria-labelledby="dropdownUser1" style="">
						<li><a class="dropdown-item" href="#"><i class="fas fa-user me-2"></i>내 정보</a></li>
						<li><a class="dropdown-item" href="#"><i class="fas fa-cat me-2"></i>내 애완동물</a></li>
						<li><a class="dropdown-item" href="#"><i class="fas fa-list me-2"></i>예약내역</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item" href="#"><i class="fas fa-sign-out-alt me-2"></i>로그아웃</a></li>
					</ul>
				</div>
			</div>
		</div>
	</header>
	<section>
		<div class="container">