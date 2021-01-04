<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>WEB DRILL</title>
    <!-- favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/images/drill/favicon.png" />" />
    <!-- Basic Styles -->
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/vendor/bootstrap.css" />">
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/vendor/bootstrap-datepicker.css" />">
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/vendor/bootstrap-select.css" />">
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/drill/common.css" />">
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/drill/style.css" />">
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/css/indicator.css" />">
    <!-- vendor js -->
    <script type="text/javascript" src="<c:url value="/js/vendor/polyfill.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/vendor/jquery-3.5.1.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/vendor/popper.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/vendor/bootstrap.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/vendor/bootstrap-datepicker.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/vendor/bootstrap-datepicker.ko.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/vendor/bootstrap-select.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/vendor/jquery.blockUI.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/vendor/underscore.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/vendor/moment.min.js" />"></script>
    <script type="text/javascript">
        //<![CDATA[
        const thisPage = {
            ctxPath : '<c:out value="${pageContext.request.contextPath}" />' + '/'
        };
        console.log("thisPage", thisPage);
        //]]>
    </script>
</head>
<body>
<!-- wrap div -->
<div class="wrap">
    <header id="header" class="main-color">
        <div class="header-top">
            <div class="header-top-inner">
                <p class="user-info float-right">
                    <span class="name">홍길동</span>
                    <span class="team">전자계약 개발팀</span>
                </p>
            </div>
        </div>
        <div class="header-inner">
            <h1 class="logo">
                <a href="<c:url value="/" />"><img src="<c:url value="/images/drill/logo.gif" />" width="100" height="80" alt="전자계약" /></a>
            </h1>
            <div class="gnb-wrap">
                <!-- header -->
                <ul class="main-menu">
                    <!-- li .on 추가시 mouseover와 같은상태 -->
                    <li>
                        <a href="#">
                            <span>탑 메뉴 #1</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span>탑 메뉴 #2</span>
                        </a>
                    </li>
                </ul>
                <div class="sub-menu">
                    <div class="sub-menu-inner">
                        <ul>
                            <!-- a .on 추가시 mouseover와 같은상태 -->
                            <li><a href="#">서브 메뉴 #1</a></li>
                            <li><a href="#">서브 메뉴 #2</a></li>
                        </ul>
                    </div>
                </div>
                <!-- header -->
            </div>
            <!-- Left menu button -->
            <div class="btn-group">
                <!--  personal setting -->
                <div id="personal-setting" class="btn-setting">
                    <span class="icon-area"></span>
                    <a href="#" style="text-decoration: none">개인설정</a>
                </div>
                <!--  admin page -->
                <div class="btn-admin">
                    <span class="icon-area"></span>
                    <a href="#" style="text-decoration: none">관리자</a>
                </div>
            </div>
        </div>
    </header>
    <!--s:container-->
    <div class="container-wrap">
        <div id="contents">
            <br/>
            <div class="row mb-2">
                <!-- card -->
                <div class="col-md-4">
                    <div class="card flex-md-row mb-4 shadow-sm h-md-230">
                        <div class="card-body d-flex flex-column align-items-start">
                            <strong class="d-inline-block mb-2 text-primary">Alert Modal!</strong>
                            <h3 class="mb-0">
                                <span>Alert Modal!</span>
                            </h3>
                            <button type="button" id="alertEvent" class="btn btn-default b-delete">
                                <p>Alert Modal!</p>
                            </button>
                        </div>
                    </div>
                </div>
                <!-- card -->
                <!-- card -->
                <div class="col-md-4">
                    <div class="card flex-md-row mb-4 shadow-sm h-md-230">
                        <div class="card-body d-flex flex-column align-items-start">
                            <strong class="d-inline-block mb-2 text-primary">Error Test #1</strong>
                            <h3 class="mb-0">
                                <span>Error Test #1</span>
                            </h3>
                            <button type="button" id="errorTest1" class="btn btn-default b-delete">
                                <p>Error Test #1</p>
                            </button>
                        </div>
                    </div>
                </div>
                <!-- card -->
                <div class="col-md-4">
                    <div class="card flex-md-row mb-4 shadow-sm h-md-230">
                        <div class="card-body d-flex flex-column align-items-start">
                            <strong class="d-inline-block mb-2 text-primary">Error Test #2</strong>
                            <h3 class="mb-0">
                                <span>Error Test #2</span>
                            </h3>
                            <button type="button" id="errorTest2" class="btn btn-default b-delete">
                                <p>Error Test #2</p>
                            </button>
                        </div>
                    </div>
                </div>
                <!-- card -->
            </div>
        </div>
    </div>
    <!-- end wrap div -->
    <!--e:container-->
<footer>
    <p>Copyright(c) 2020 Cesco Co., Ltd. All Rights Reserved</p>
</footer>
<!-- //footer -->
</div>
<!-- footer -->
<script type="text/javascript" src="<c:url value="/js/common/ajaxCommon.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/common/uiCommon.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/views/web/drill/web-drill.js" />"></script>
</body>
</html>
<!-- ///////// Ajax Success Alert Modal ///////// -->
<div class="modal fade" id="alertPop" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="commonAlert" aria-hidden="true">
    <div class="modal-dialog modal-default modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="commonAlert">알림</h5>
            </div>
            <div id="alertMessage" class="modal-body">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default b-close btn-full" data-dismiss="modal">
                    <p>확인</p>
                </button>
            </div>
        </div>
    </div>
</div>
<!-- ///////// Ajax Success Alert Modal ///////// -->