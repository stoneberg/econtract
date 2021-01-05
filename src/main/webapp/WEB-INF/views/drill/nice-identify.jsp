<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NICE평가정보 - CheckPlus 안심본인인증 테스트</title>
</head>
<script type="text/javascript" src="<c:url value="/js/vendor/jquery-3.5.1.min.js" />"></script>
<body>
<text>sRequestNumber&gt;&gt;&gt;BC283_2021010510021533412</text>
<br><br>
업체정보 암호화 데이타 : [AgAFQkMyODPKKwX891PG60DegItlJFrNyLHyezcJaaiRUDnciaGRMpad1cSi2Gb9YSvL3yQ7CLIeGKLWIVfROId/gU7jzKbIlUnQrG1/atudadxEsuBn8IS/RLOUVznOvmibSMa5tt6NPFH+Th0bV5TTNirqFk6rgHGE3ueuJ0cUjF2TXD5IFHl3arWmBz25rDpcE24eHuJB3u9AvAi5NzcBznxzopCh/vt1CNhMy4hwo0RCDzDoqV1pU/1poii5+6Zk0OzGzz6nhqSx/o/bGUn2Wje0tokCbJo+0pWtuMWowpnDY3Ll1D68KlgG/ThcEmyNGQJk0szXtP19tSZn1NfevwXvmwIVGbj6KIkv0k+F4uWsyBR7xdqy22v0bATQ0iPGD8fg41JhiWFsWiRLrzNRKxWYrjMAZJjtjwAJHmWX+SSXV0RU7Vp9BPC5ogo7iIaziCYTz2s+rINL9rGLJSrZWs1t7j+9IjQxRBcY8vQjIpNeRUWvVe3KqzA/hX3a4Z1+/dn6a/g=]<br><br>

<!-- 본인인증 서비스 팝업을 호출하기 위해서는 다음과 같은 form이 필요합니다. -->
<form name="form_chk" method="post">
	<input type="hidden" name="m" value="checkplusSerivce"><!-- 필수 데이타로, 누락하시면 안됩니다. -->
	<input type="hidden" name="EncodeData" value="AgAFQkMyODPKKwX891PG60DegItlJFrNyLHyezcJaaiRUDnciaGRMpad1cSi2Gb9YSvL3yQ7CLIeGKLWIVfROId/gU7jzKbIlUnQrG1/atudadxEsuBn8IS/RLOUVznOvmibSMa5tt6NPFH+Th0bV5TTNirqFk6rgHGE3ueuJ0cUjF2TXD5IFHl3arWmBz25rDpcE24eHuJB3u9AvAi5NzcBznxzopCh/vt1CNhMy4hwo0RCDzDoqV1pU/1poii5+6Zk0OzGzz6nhqSx/o/bGUn2Wje0tokCbJo+0pWtuMWowpnDY3Ll1D68KlgG/ThcEmyNGQJk0szXtP19tSZn1NfevwXvmwIVGbj6KIkv0k+F4uWsyBR7xdqy22v0bATQ0iPGD8fg41JhiWFsWiRLrzNRKxWYrjMAZJjtjwAJHmWX+SSXV0RU7Vp9BPC5ogo7iIaziCYTz2s+rINL9rGLJSrZWs1t7j+9IjQxRBcY8vQjIpNeRUWvVe3KqzA/hX3a4Z1+/dn6a/g="><!-- 위에서 업체정보를 암호화 한 데이타입니다. -->
	<input type="hidden" name="enc_data" value=" AgAFQkMyODPKKwX891PG60DegItlJFrNyLHyezcJaaiRUDnciaGRMpad1cSi2Gb9YSvL3yQ7CLIeGKLWIVfROId/gU7jzKbIlUnQrG1/atudadxEsuBn8IS/RLOUVznOvmibSMa5tt6NPFH+Th0bV5TTNirqFk6rgHGE3ueuJ0cUjF2TXD5IFHl3arWmBz25rDpcE24eHuJB3u9AvAi5NzcBznxzopCh/vt1CNhMy4hwo0RCDzDoqV1pU/1poii5+6Zk0OzGzz6nhqSx/o/bGUn2Wje0tokCbJo+0pWtuMWowpnDY3Ll1D68KlgG/ThcEmyNGQJk0szXtP19tSZn1NfevwXvmwIVGbj6KIkv0k+F4uWsyBR7xdqy22v0bATQ0iPGD8fg41JhiWFsWiRLrzNRKxWYrjMAZJjtjwAJHmWX+SSXV0RU7Vp9BPC5ogo7iIaziCYTz2s+rINL9rGLJSrZWs1t7j+9IjQxRBcY8vQjIpNeRUWvVe3KqzA/hX3a4Z1+/dn6a/g=">
	<button id="identify" type="button">CheckPlus 안심본인인증 Click</button>
</form>
<script type="text/javascript">
	function openIdentifyPop(){
		window.open('', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
		document.form_chk.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
		document.form_chk.target = "popupChk";
		document.form_chk.submit();
	}

	$(document).ready(function() {
		$('#identify').on('click', function(e) {
			e.preventDefault();
			console.log("#identify============================>");
			openIdentifyPop();
			//window.open($(this).attr("href"), "popupWindow", "width=600,height=600,scrollbars=yes");
		});
	});
</script>
</body>
</html>