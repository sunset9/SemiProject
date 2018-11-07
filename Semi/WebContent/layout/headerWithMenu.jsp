<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--froala Css -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/codemirror.min.css"> -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/css/froala_editor.pkgd.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/css/froala_style.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/css/plugins/image.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/css/plugins/image_manager.min.css"
	rel="stylesheet" type="text/css" />
<!-- 2.5.1 -->

<!-- jQuery 2.2.4 -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- jquery-confirm 3.3.0 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.js"></script>

<!-- froala JS -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/js/froala_editor.pkgd.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/js/plugins/image.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/js/plugins/image_manager.min.js"></script>

<!-- jQuery ui -->
<script type="text/javascript"
	src='/resources/timetable/jquery-ui.min.js'></script>

<!-- moment.js (시간 포멧 설정용) -->
<script type="text/javascript" src='/resources/timetable/moment.min.js'></script>

<!-- 부트스트랩 3.3.2 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<link
	href='https://cdn.rawgit.com/openhiun/hangul/14c0f6faa2941116bb53001d6a7dcd5e82300c3f/nanumbarungothic.css'
	rel='stylesheet' type='text/css'>

<style type="text/css">
body {
	width: 1160px;
	margin: 0 auto;
	font-family: 'Nanum Barun Gothic', sans-serif;
	font-size: 15px;
	padding-right: 0 !important; /* 모달 오픈 시 padding-right 생김. 해당 작동 막기위함 */
}
.backGRD {
	width: 1160px;
	heigt: 50px;
	border: 1px solid rgba( 227, 228, 229, 0.5 );
	position: absolute;
/* 	background-color: rgba( 227, 228, 229, 0.5 ); */
	background: rgba(243, 243, 243, 1);
	top:79px;
	border-radius: 7px;
}
.wholeHeader>div {
	padding-bottom: 40px;
}

.common {
	display: inline-block;
}

.dropdown {position:relative;}

/* .right {float:left;} */
.dropdown>ul>li {
	display: inline-block;
	position: absolute;
}

.dropdown>ul>li>a {
	font-size:large;
	color: #686868;	
	text-decoration: none;
}
.dropdown>ul>li:hover>a {
	transform:scale(1.2,1.2);
	color: #5cd6b9;	
}
.dropdown>ul>li>ul>li>a {
	font-size:large;
	color: #686868;	
	text-decoration: none;
}
.dropdown>ul>li>ul>li:hover>a {
	transform:scale(1.2,1.2);
	color: #5cd6b9;	
}

.menuLi ul:hover {
/*  	text-decoration: none;  */
/*  	color: black;  */
}




/* 모든콘텐츠 */
.dropdown>ul>li:nth-child(1) {
	top:87px;
	left:70px;
}
/* 마이페이지 */
.dropdown>ul>li:nth-child(2) {
	top:87px;
	left:300px;
}
/* 공지사항 */
.dropdown>ul>li:nth-child(3) {
	top:87px;
	left:560px;
}
/* QnA */
.dropdown>ul>li:nth-child(4) {
	top:87px;
	right: 305px;
}
/* 문의사항 */
.dropdown>ul>li:nth-child(5) {
	top:87px;
	right:80px;
}

.contentsubli:first-child {
	margin-top: 11px;
}

.dropdown>ul>li>ul {
    position: absolute;
    list-style-type: none;
    background: rgba(243, 243, 243, 1);
    display: none;
    height: auto;
    position: absolute;
    width: 135px;
    padding-left: 25px;
    padding-bottom: 18px;
    border-radius: 0px 0px 7px 7px;
    margin-top: 1px;
    margin-left: -26px;
    z-index: 200;
    border-left: 1px solid rgba( 227, 228, 229, 0.5 );
    border-bottom: 1px solid rgba( 227, 228, 229, 0.5 );
    border-right: 1px solid rgba( 227, 228, 229, 0.5 );
}

.dropdown>ul>li:hover>ul {
	display: block; /* 마우스 올리면 서브메뉴 보이는 */
}

/* The Modal (background) */
.newPlanModal, .loginWindowModal, .findPwModal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

.joinModal{
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.8); /* Black w/ opacity */

}

/* Modal Content */
.loginWindowModal-content, .findPwModal-contents{
	position: relative;
	background-color: white;
	margin: auto;
	padding: 10px;
	border: 1px solid #888;
	width: 370px;
	height: 420px;
	border-radius: 7px;
}

.newPlanmodal-content {
	position: relative;
	background-color: white;
	margin: auto;
	padding: 10px;
	border: 1px solid #888;
	width: 370px;
	height: auto;
	border-radius: 7px;
}
.joinModal-contents {
	position: relative;
	background-color: white;
	margin: auto;
	padding: 10px;
	border: 1px solid #888;
	width: 405px;
	height: 350px;
	border-radius: 7px;

}
.joinModal-contents h1{
   display: block;
   position: initial;
   font-size: 40px;
   color: black;  
   margin-top: 9px;
}

/* The Close Button */
.newPlanModalclose, .loginWindowModalclose, .fClose, .jClose {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.newPlanModalclose:hover, .newPlanModalclose:focus,
	.loginWindowModalclose:hover, .loginWindowModalclose:focus, .fClose:hover,
	.fClose:focus, .jClose:hover, .jClose:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

.form-control {
	/* 	border: 1px solid #bfb9bf;
	border-radius: 10px;
	background: radial-gradient( circle, white, #bfb9bf, white );
	width: 180px;
	height: 30px;
	font-size: 15px; */
	margin-bottom: 11px;
	
}
.findPwModal-contents {
	position: relative;
    background-color: white;
    margin: auto;
    padding: 18px 28px;
    border: 1px solid #888;
    width: 435px;
    height: 322px;
    border-radius: 7px;
}
.findPwModal-contents h1{
   display: block;
   position: initial;
   font-size: 40px;
   color: black;  
   margin-top: 3px;
}
.findPwModal-contents hr {
    margin-top: 11px;
}
.findPwModal-contents input[type='submit'] {
    margin-top: 17px;
    width: 100%;
}
.loginWindowModalclose{
	position: absolute;
	top: 10px;
	right: 10px;
}
.googleBtnDesign {
	position: absolute;
	top: 15px;
	left: 40px;
}
.kakaoBtnDesign {
	position: absolute;
	top: 72px;
	left: 40px;
}
.facebookBtnDesign {
	position: absolute;
	top: 132px;
	left: 40px;
}


.emailLabelDesign {
	position: absolute;
	top: 217px;
	left: 60px;
}
.emailInputDesign {
	position: absolute;
	top: 215px;
	left: 120px;
}

.passwordLabelDesign {
	position: absolute;
	top: 257px;
	left: 60px;
}
.passwordInputDesign{
	position: absolute;
	top: 257px;
	left: 120px;
}


.afterLoginCheckDesign {
	position: absolute;
	top: 286px;
	left: 83px;
	width: 260px;
	background-color: rgba(255,255,255,0);
}
.LoginSubmitBtnDesign {
	position: absolute;
	bottom: 70px;
	left: 60px;
	width: 240px;
}
.joinlogo {
	position: absolute;
	bottom: 25px;
	left :60px;
	width: 110px;
}
.findPw {
	position: absolute;
	bottom: 25px;
	right: 69px;
	width: 110px;
}
.contentsubli{
    padding-top: 16px;
}

.jmlabel {
	width:95px;
}

.jmJoinSubBtn {
	width: -webkit-fill-available;
}

.newPlanmodal-content select {
	margin-top: 10px;
    margin-bottom: 10px;
    height: 33px;
    width: -webkit-fill-available;
}

.npmTitle{
    height: 34px;
    width: -webkit-fill-available;
}
</style>

<script type="text/javascript">
	// 쿠기 설정 메소드
	var setCookie = function(name, value, exp) {
		var date = new Date();
		date.setTime(date.getTime() + exp * 24 * 60 * 60 * 1000);
		document.cookie = name + '=' + value + ';expires=' + date.toUTCString()
				+ ';path=/';
	};
	// 쿠키 얻기 메소드
	var getCookie = function(name) {
		var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
		return value ? value[2] : null;
	};
	// 쿠키 삭제 메소드
	var deleteCookie = function(name) {
		document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
	};
</script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<!-- 구글 api  -->
<script type="text/javascript">
	function init() { /* auth객체 생성자 : 바디태그를 다 읽은 후 이 메소드가 제일 먼저 호출된다. */
		console.log('init');
		/* gapi(구글 api) 중 auth2라는 기능을 가져온후, 다음에 정의한 function을 수행하라는 뜻. */
		gapi
				.load(
						'auth2',
						function() {
							/* var gauth가 아니라 window.gauth라고 해주면 전역 변수로 사용 가능하다. 아래 바디 태그에서도 사용가능 */
							window.gauth = gapi.auth2
									.init({ /* 인증(허가) 객체 초기화 메소드 */
										client_id : '261787449656-7thgl5h7flg6lvb5sa3vb22r1tqfo4fc.apps.googleusercontent.com'
									})
							/* 바로 위에 gapi.auth2.init()의 리턴 타입은 gapi.auth2.GoogleAuth이다.
							그래서 아래의 gauth 라는 변수의 데이터 타입은 GoogleAuth.(GoogleAuth gauth)
							그리고 init()의 파라미터로 client_id를 전달해주면 
							gauth는 나의 auth(허가, authorization) 정보를 가진 객체가 된다.
							허가 정보가 있어야 구글이 아닌 다른 웹페이지에서도 구글 정보를 가지고 로그인이 가능
							 */
							/* 밑에 then이라는 함수는 inti() 후 상태를 알려준다. 
							첫번째 인자는 init()이 성공적으로 수행됐을때 호출되고,
							두번째 인자는 init()이 호출되는데 문제가 생겼을때 호출된다.*/
							/* 성공적으로 수행됐다면 로그인 상태를 체크하는 checkLoginStatus()를 호출한다. */
							gauth.then(function() {
								console.log('googleAuth success');
								if (gauth.isSignedIn.get()) {
									console.log('구글 로그인 상태');
								} else {
									console.log('구글 로그아웃 상태');
								}
							}, function() {
								console.log('googleAuth fails');
							});
						});
	}

	/* 로그인창 띄워주고 그 후 처리 */
	function startGoogleLogin() {
		gauth.signIn().then(function() {
			console.log('gauth.signIn()');
			window.profile = gauth.currentUser.get().getBasicProfile();
			/* 구글 로그인폼에 가져온 프로필 넣어주기 */
			$("#googleid").val(profile.getEmail());
			$("#googlenickname").val(profile.getName());
			$("#googleprofileImage").val(profile.getImageUrl());
			submitGoogleLogin();
		})
	}
	function submitGoogleLogin() {
		document.getElementById("googleLogin").submit();
	};
</script>

<!-- 카카오톡 api -->
<script>
	function loginWithKakao() {
		// 로그인 창을 띄웁니다.
		Kakao.Auth.login({
			success : function(authObj) {
				getKakaotalkUserProfile();
				//kakaoInfoSubmit();
				//createKakaotalkLogout();
			},
			fail : function(err) {
				console.log(err);
			}
		});
	}

	function getKakaotalkUserProfile() {
		Kakao.API.request({
			url : '/v1/user/me',
			success : function(res) {
				//바디에서 해당되는 태그의 id를 찾아서 value에 값 지정해주기
				$("#kakaoid").val(res.id);
				$("#kakaonickname").val(res.properties.nickname);
				$("#kakaoprofileImage").val(res.properties.profile_image);

				//value가 지정되면 서브밋해주는 코드 -> 소셜로그인컨트롤러로 서브밋
				document.getElementById("kakaoForm").submit();
			},
			fail : function(error) {
				console.log(error);
			}
		});
	}

	function kakaoInfoSubmit() {
		document.getElementById("kakaoForm").submit();
	}

	$(document).ready(function() {
		//Javascript SDK 초기화 
		Kakao.init("88e34dd24cbb64e2ee9278a3acbfc78d");
	});
</script>

<!-- 페이스북 로그인 api -->
<script type="text/javascript">
	/* 페이스북 sdk 로드하는 즉시실행함수 */
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
		js = d.createElement(s);
		js.id = id;
		js.src = "//connect.facebook.net/en_US/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));

	/* fbAsyncInit 함수 : sdk 로드가 끝난 후 실행 */
	/* FB라는 객체를 인자로 가지는 함수이다. */
	window.fbAsyncInit = function() {
		FB.init({
			appId : '496716117492739',
			cookie : true, // enable cookies to allow the server to access 
			// the session
			xfbml : true, // parse social plugins on this page
			version : 'v3.2' // facebook api change log검색하면 최신버전 나옴
		});
		// 1. 페북에도 로그인했고 앱에도 로그인했을때 ('connected')
		// 2. 페북은 로그인했고 앱은 안했을때 ('not_authorized')
		// 3. 페북도 앱도 로그인 안했을때 ('unknown')
		//로그인 되었는지 안되었는지 상태를 가져오는 함수
		//인자로 checkLoginStatus라는 함수를 전달해서 
		//status
		//response 안에 status 정보가 담겨있음 -> 개발자 도구에서 확인 가능
		//FB.getLoginStatus(checkLoginStatus);
	};

	function startFacebookLogin() {
		FB.login(function(res) {
			checkLoginStatus(res);
		})
	};

	var checkLoginStatus = function(response) {
		console.log(response);
		if (response.status == 'connected') {
			FB.api('/me', 'GET', {
				"fields" : "id,name,picture"
			}, function(response) {
				window.userProfile = response;
				$("#facebookid").val(userProfile.id);
				$("#facebooknickname").val(userProfile.name);
				$("#facebookprofileImage").val(userProfile.picture.data.url);
				//$("#test").val(response.picture.data.url);
				checkFacebookLoginStatus2();
			});
		} else {
			console.log('not connected');
		}
	}

	function checkFacebookLoginStatus2() {
		document.getElementById("facebookLogin").submit();
	}
</script>

<script type="text/javascript">
	/* 회원가입 관련 */
	function sendJoinForm() {
		if (joinForm.userid.value == "") {
			alert("이메일을 입력해주세요.");
			joinForm.userid.focus(); //포커스 안해주면 그냥 서브밋 되어버림
			return false;
		}
		if (joinForm.userpw.value == "") {
			alert("비밀번호를 입력해주세요.");
			joinForm.userpw.focus();
			return false;
		}
		if (joinForm.userpw.value.length < 3) {
			alert("비밀번호는 3자 이상 입력해주세요.");
			joinForm.userpw.focus();
			joinForm.userpw.select(); //드래그로 선택됨
			return false;
		}
		if (joinForm.userpw.value != joinForm.pwCheck.value) {
			alert("비밀번호가 일치하지 않습니다.");
			joinForm.pwCheck.value = "";
			joinForm.pwCheck.focus();
			return false;
		}
		if (joinForm.usernickname.value == "") {
			alert("닉네임을 입력해주세요.");
			joinForm.usernickname.focus();
			return false;
		}
		if (joinForm.usernickname.value.length > 10) {
			alert("닉네임은 10자까지만 가능합니다.");
			joinForm.usernickname.focus();
			return false;
		}
	}

	/* 회원가입시 아이디 중복확인 */
	function checkUserId() {
		var inputUserid = $("#userid").val();
		$.ajax({
			type : 'GET',
			url : '/user/check',
			data : {
				inputUserid : inputUserid
			},
			success : function(result) {
				if (result == 1) {
					$("#afterIdCheck").val("사용 가능한 email입니다.");
					$("#afterIdCheck").css("color", "blue");
					$("#joinBtn").prop("disabled", false);
					$("#joinBtn").css("color", "black");
				} else {
					$("#afterIdCheck").val("사용 불가능한 email입니다.");
					$("#afterIdCheck").css("color", "red");
				}
			}
		});
	}

	function checkLoginInfo() {
		var inputUserId = $("#useridforemail").val();
		var inputUserPw = $("#userpwforemail").val();
		$.ajax({
			type : 'POST',
			url : '/user/check',
			data : {
				inputUserId : inputUserId,
				inputUserPw : inputUserPw
			},
			success : function(result) {
				if (result == 1) {
					/* 아이디 존재하지 않음, 로그인 실패 */
					$("#afterLoginCheck").val("존재하지 않는 email입니다.");
					$("#afterLoginCheck").css("color", "red");
					return false;
				} else if (result == 2) {
					/* 아이디는 존재하지만 비밀번호가 틀릴때 */
					$("#afterLoginCheck").val("비밀번호가 일치하지 않습니다.");
					$("#afterLoginCheck").css("color", "red");
					return false;
				} else if (result == 3) {
					/* 아이디가 존재하며 비밀번호도 맞을때 -> 로그인 성공! */
					/* $("#afterLoginCheck").val("로그인 성공"); */
					$("#mainLoginForm").submit();
				}
			}
		});
	}
	
	function checkField1(){
		if(newPlanFormInHeader.editTitleView.value == ""){
			alert("일정의 title을 입력해주세요.");
			newPlanFormInHeader.editTitleView.focus();
			return false;
		}
		if(newPlanFormInHeader.editStartDate.value == ""){
			alert("출발일을 선택해주세요.");
			newPlanFormInHeader.editStartDate.focus();
			return false;
		}
		if(newPlanFormInHeader.editEndDate.value == ""){
			alert("도착일을 선택해주세요.");
			newPlanFormInHeader.editEndDate.focus();
			return false;
		}
		if(newPlanFormInHeader.editTitleView.value != "" && newPlanFormInHeader.editStartDate.value != "" 
				&& newPlanFormInHeader.editEndDate.value != ""){
			$('#newPlanFormInHeader').submit();
		}
	}

	function checkDate1(){
		if(newPlanFormInHeader.editTraveledInHeader.value == "1"){
			if(newPlanFormInHeader.editStartDate.value < getTodayDate1()){
				alert("[여행전] 일정의 출발일은 오늘 날짜보다 빠를 수 없습니다.");
			}
		}
		
		
	}

	function setDisabledDate1(){
		if(newPlanFormInHeader.editTraveledInHeader.value == "1"){
			$('#editStartDate').attr("min",getTodayDate1());
		} else{
			$('#editStartDate').removeAttr("min");
		}
	}

	function setEndDateDis1(){
		$('#editEndDate').attr("min",newPlanFormInHeader.editStartDate.value);
	}
	
	function getTodayDate1(){
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!
		var yyyy = today.getFullYear();

		if(dd<10) {
		    dd='0'+dd
		} 

		if(mm<10) {
		    mm='0'+mm
		} 

		today = yyyy+'-'+mm+'-'+dd;
		return today;
	}
	
	function press(){ 
		if(window.event.keyCode == 13){ //javascript에서는 13이 enter키를 의미함 
			checkLoginInfo();
		} 
	}

</script>
</head>
<body>
	<div class="wholeHeader" style="position: relative; height: 160px; ">
		<!-- header시작 -->
		<a href="/main"><img src="/image/logo.png"
			style="position:absolute; display: inline; width: 160px; height: 80px; top:3px;" /></a>
		<!-- 로그인 상태일때 -->
		<c:if test="${login}">
			<form action="/contents/all" method="get" style="display: inline;">
				<select name="searchType" style="position:absolute; top:12px; right: 387px; width:70px; height:33px;">
					<option value="1">제목</option>
					<option value="2">닉네임</option>
				</select> 
				<input type="text" name="search" style="position:absolute; top:12px; right: 210px; height:34px; border-radius:6px;"/>
				<button type="submit" class="btn btn-default" style="position:absolute; top:11px; right: 150px;">검색</button>
			</form>
			<button id="newPlan" class="btn btn-default" style="position: absolute; top: 11px; right: 80px;">새일정</button>
			<button onclick='location.href="/user/logout";' class="btn btn-default" style="position: absolute; top: 11px; right: 0px;">로그아웃</button>
		</c:if>
		<!-- 비로그인 상태일때 -->
		<c:if test="${not login}">
			<form action="/contents/all" method="get" style="display: inline;">
				<select name="searchType" style="position:absolute; top:12px; right: 302px; width:70px; height:33px;">
					<option value="1">제목</option>
					<option value="2">닉네임</option>
				</select> 
				<input type="text" name="search" style="position:absolute; top:12px; right: 126px; height:34px; border-radius:6px;"/>
				<button type="submit" class="btn btn-default" style="position:absolute; top:10px; right: 70px;">검색</button>
			</form>
			<button id="MainLoginBtnOnNotLogin" class="btn btn-default"
					onclick='location.href="/user/login";'
					style="position: absolute; top: 11px; right: 0px;">로그인</button>
		</c:if>
		<!-- /header끝 -->

		<div class="backGRD"></div>

		<!-- menubar시작 -->
		<div class="menubar">
			<div class="dropdown">
				<ul>
					<li><a class="menuLi" href="/contents/all">모든 콘텐츠</a>
						<ul>
							<li class ="contentsubli"><a class="menuLi" href="/contents/recommend">추천 콘텐츠</a></li>
							<li class ="contentsubli"><a class="menuLi" href="/contents/newest">최신 콘텐츠</a></li>
						</ul>
					</li>
					<li>
						<!-- 로그인 상태일때 -->
						<c:if test="${login}">
							<a class="menuLi" href="/user/myPage" >마이페이지</a>
						</c:if>

						<!-- 비로그인 상태일때 -->
						<c:if test="${not login}">
							<a class="menuLi" onclick="alert('로그인이 필요합니다.');" >마이페이지</a>
						</c:if>
					</li>
					<li><a class="menuLi" href="/notice/list" >공지사항</a></li>
					<li><a class="menuLi" href="/qna/list" >Q&A</a></li>
					<li><a class="menuLi" href="/inquiry/list">문의사항</a></li>
				</ul>
			</div>
		</div>
		<br>
		<!-- /menubar끝 -->
	</div>

	<!-- 새일정 만들기 The Modal -->
	<div id="newPlanModal" class="newPlanModal">
		<!-- 새일정 만들기 Modal content -->
		<div class="newPlanmodal-content">
			<span class="newPlanModalclose">&times;</span>
			<div>
				<h2 style="margin-top: 0; margin-bottom: 0;">새 일정 만들기</h2>
			</div>
			<hr>
			<form action="/plan/create" method="post" id="newPlanFormInHeader">
				<div>
					<div>
					<input type="text" class="npmTitle" id="editTitleView" name="editTitleView" placeholder="여행 제목" />
					</div>
					<div>
					<select name="editTraveled" id="editTraveledInHeader" style="width: -webkit-fill-available;">
						<option value="1">여행 전</option>
						<option value="0">여행 후</option>
					</select> 
					</div>
					<p>
					<input type="date" id="editStartDate" name="editStartDate" value="" onclick="setDisabledDate1();" onblur="setEndDateDis1();"/> 
					<input type="date" id="editEndDate" name="editEndDate" value="" /> 
					</p>
					<input type="hidden" id="editOpened" name="editOpened" value="0" /><br>
					<!-- <input class="form-control" type="submit" value="새 일정 추가" /> -->
					<!-- <button class="btn btn-default" style="width: -webkit-fill-available;">새 일정 추가</button> -->
					<input type="button" class="btn btn-default" style="width: -webkit-fill-available;" onclick="checkField1();" value="새 일정 추가"/>
				</div>
			</form>
		</div>
	</div>
	<!-- 새일정 만들기 Modal content 끝 -->

	<!-- 로그인 모달 -->
	<div id="loginWindowModal" class="loginWindowModal">
		<div class="loginWindowModal-content">
			<span class="loginWindowModalclose">&times;</span>
			<!-- 구글 로그인 -->
			<input class="googleBtnDesign" type="image" src="/resources/img/user/google_btn.png"
				onclick="startGoogleLogin();" style="width: 280px; height: 65px;" />
			<form action="/user/socialLogin" method="post" id="googleLogin">
				<input type="hidden" id="googleid" name="id" value="" /> <input
					type="hidden" id="googlenickname" name="nickname" value="" /> <input
					type="hidden" id="googleprofileImage" name="profileImage" value="" />
				<input type="hidden" id="googlesnsIdx" name="snsIdx" value="3" />
			</form>
		
			<!-- 카카오톡으로 로그인 -->
			<input class="kakaoBtnDesign" type="image" src="/resources/img/user/kakao_btn.png"
				onclick="loginWithKakao();" style="width: 280px; height: 65px;" />
			<form id="kakaoForm" action="/user/socialLogin" method="post">
				<input type="hidden" id="kakaoid" name="id" value="" /> <input
					type="hidden" id="kakaonickname" name="nickname" value="" /> <input
					type="hidden" id="kakaoprofileImage" name="profileImage" value="" />
				<input type="hidden" id="kakaosnsIdx" name="snsIdx" value="4">
			</form>

			<!-- 페이스북 로그인 -->
			<input class="facebookBtnDesign" type="image" src="/resources/img/user/facebook_btn.png"
				onclick="startFacebookLogin();" style="width: 280px; height: 66px;">
			<form action="/user/socialLogin" method="post" id="facebookLogin">
				<input type="hidden" id="facebookid" name="id" value="" /> <input
					type="hidden" id="facebooknickname" name="nickname" value="" /> <input
					type="hidden" id="facebookprofileImage" name="profileImage"
					value="" /> <input type="hidden" id="facebooksnsIdx" name="snsIdx"
					value="2" />
			</form>
			
			<!-- email로 로그인 -->
			<div>
				<form action="/user/login" method="post" id="mainLoginForm">
					<label class="emailLabelDesign" for="userid">이메일 </label> 
					<input class="emailInputDesign" type="email" id="useridforemail" name="userid" /><br> 
					
					<label class="passwordLabelDesign" for="userpw">비밀번호</label>
					<input class="passwordInputDesign" type="password" id="userpwforemail" name="userpw" onkeyup="press();"/><br>
					
					<input type="hidden" id="snsIdxforemail" name="snsIdx" value="1" />
					
					<input class="afterLoginCheckDesign" type="text" id="afterLoginCheck" style="border: 0;" disabled/><br>
					
					<input class="LoginSubmitBtnDesign btn btn-default" type="button" value="로그인" onclick="checkLoginInfo();" />
				</form>
				<!-- 회원가입 open the modal btn -->
				<button id="joinModalBtn" class="joinlogo btn btn-default">회원가입</button>

				<!-- 비밀번호 찾기 open the modal btn -->
				<button id="findPwModalBtn" class="findPw btn btn-default">비밀번호 찾기</button>
			</div>

			<!-- 비밀번호 찾기 모달 시작 -->
			<!-- 비밀번호 찾기 the modal -->
			<div id="findPwModal" class="findPwModal">

				<!-- 비밀번호 찾기 modal content -->
				<div class="findPwModal-contents">
					<span class="fClose">&times;</span>
					<h1>비밀번호 찾기</h1>
					<hr>
					<form action="/user/findPw" method="post">
						<input type="email" class="form-control" name="email" placeholder="이메일을 입력해주세요." /> 
						<p>임시 비밀번호를 보내드립니다.</p>
						<p>가입시 입력하신 이메일 주소를 입력해주세요.</p>
						<input type="submit" class="btn btn-default" value="이메일로 전송" />
					</form>
				</div>
			</div>
			<!-- 비밀번호 찾기 모달 끝 -->

			<!-- 회원가입 모달 시작 -->
			<div id="joinModal" class="joinModal">
				<!-- 회원가입 modal content  -->
				<div class="joinModal-contents">
					<span class="jClose">&times;</span>
					<!-- <span class="jmTitle">회원가입</span> -->
					<h1>회원가입</h1>
					<hr>
					<form id="joinForm" action="/user/join" method="post" onsubmit="return sendJoinForm();">
						<p>
						<label class="jmlabel" style="width:95px;">이메일  </label> 
						<input class="jmEmailInput" type="email" id="userid" name="userid" placeholder="이메일"> 
						<input class="jmCheckBtn btn btn-default" type="button" value="중복확인" onclick="checkUserId();" />
						</p>
						<p>
						<input class="jmAfterIdCheck" type="text" id="afterIdCheck" style="border: 0;" /> 
						</p>
						<p>
						<label class="jmlabel">비밀번호  </label> 
						<input class="jmPwInput" type="password" id="userpw" name="userpw" placeholder="비밀번호"> 
						</p>
						<p>
						<label class="jmlabel">비밀번호 확인  </label> 
						<input class="jmPwCheckInput" type="password" id="pwCheck" name="pwCheck" placeholder="비밀번호 확인">
						</p>
						<p>
						<label class="jmlabel">닉네임  </label>
						<input class="jmNickInput" type="text" id="usernickname" name="usernickname" placeholder="닉네임"> 
						</p>
						<input type="hidden" id="snsIdx" name="snsIdx" value="1" />
						<button class="jmJoinSubBtn btn btn-default" type="submit" class="signUpBtn" id="joinBtn" disabled="disabled" style="color: gray;">가입하기</button>
					</form>
				</div>
			</div>
			<!-- 회원가입 모달 끝 -->
		</div>
	</div>





	<!-- 새일정 만들기 모달 설정 -->
	<script type="text/javascript">
		//Get the modal
		var newPlanModal = document.getElementById('newPlanModal');

		// Get the button that opens the modal
		var newPlanbtn = document.getElementById("newPlan");

		// Get the <span> element that closes the modal
		var newPlanspan = document.getElementsByClassName("newPlanModalclose")[0];

		if(newPlanbtn != null){
			// When the user clicks the button, open the modal 
			newPlanbtn.onclick = function() {
				if ($("#planSaveBtn").length == 0) {
					// 모달 창 띄우기
					newPlanModal.style.display = "block";
				} else {
					// 저장버튼 활성화 상태이면 탭 안넘어가도록 경고창
					if ($("#planSaveBtn").attr('disabled') == null) {
						// 진행 확인 창 띄움
						$.confirm({
							title : '계속하시겠습니까?',
							content : '저장하시지 않으면 작성 중인 정보를 잃습니다.',
							buttons : {
								cancle : { // 취소 버튼
									text : '취소',
									btnClass : 'btn-blue'
								},
								계속 : function() { // 계속 버튼
									isAlreadyAlert = true;
									// 모달 창 띄우기
									newPlanModal.style.display = "block";
								}
							}
						});
					} else { // 저장버튼 비활성화 상태면 그냥 진행
						// 모달 창 띄우기
						newPlanModal.style.display = "block";
					}
				}
	
			}
		}
		// When the user clicks on <span> (x), close the modal
		newPlanspan.onclick = function() {
			newPlanModal.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == newPlanModal) {
				newPlanModal.style.display = "none";
			}
		}
	</script>
	<!-- login modal script -->
	<script>
		// Get the modal
		var fModal = document.getElementById('findPwModal');

		// Get the button that opens the modal
		var fBtn = document.getElementById("findPwModalBtn");

		// Get the <span> element that closes the modal
		var fSpan = document.getElementsByClassName("fClose")[0];

		// When the user clicks the button, open the modal 
		fBtn.onclick = function() {
			fModal.style.display = "block";
		}

		// When the user clicks on <span> (x), close the modal
		fSpan.onclick = function() {
			fModal.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == fModal) {
				fModal.style.display = "none";
			}
		}

		var jModal = document.getElementById('joinModal');
		var jBtn = document.getElementById("joinModalBtn");
		var jSpan = document.getElementsByClassName("jClose")[0];

		jBtn.onclick = function() {
			jModal.style.display = "block";
		}

		jSpan.onclick = function() {
			jModal.style.display = "none";
		}

		window.onclick = function(event) {
			if (event.target == jModal) {
				jModal.style.display = "none";
			}
		}
	</script>

	<!-- 로그인모달 설정 -->
	<script type="text/javascript">
		//Get the modal
		var loginWindowModal = document.getElementById('loginWindowModal');

		// Get the button that opens the modal
		var MainLoginBtnOnNotLoginBtn = document
				.getElementById("MainLoginBtnOnNotLogin");

		// Get the <span> element that closes the modal
		var loginWindowModalclose = document
				.getElementsByClassName("loginWindowModalclose")[0];
		if(MainLoginBtnOnNotLoginBtn != null){
			// When the user clicks on the button, open the modal 
			MainLoginBtnOnNotLoginBtn.onclick = function() {
				loginWindowModal.style.display = "block";
			}
		}
		
		// When the user clicks on <span> (x), close the modal
		loginWindowModalclose.onclick = function() {
			loginWindowModal.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == loginWindowModal) {
				loginWindowModal.style.display = "none";
			}
		}
	</script>
	<!-- 바디 태그 로드가 끝나면 init이라는 함수를 호출하라는 뜻 -->
	<script src="https://apis.google.com/js/platform.js?onload=init" async
		defer></script>