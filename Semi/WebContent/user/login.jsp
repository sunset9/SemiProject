<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
<title>로그인</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript">
	function init() { /* auth객체 생성자 : 바디태그를 다 읽은 후 이 메소드가 제일 먼저 호출된다. */
		console.log('init');
	
		/* gapi(구글 api) 중 auth2라는 기능을 가져온후, 다음에 정의한 function을 수행하라는 뜻. */
		gapi.load('auth2', function() { 
			
			/* var gauth가 아니라 window.gauth라고 해주면 전역 변수로 사용 가능하다. 아래 바디 태그에서도 사용가능 */
			window.gauth = gapi.auth2.init({  /* 인증(허가) 객체 초기화 메소드 */
				client_id:'261787449656-7thgl5h7flg6lvb5sa3vb22r1tqfo4fc.apps.googleusercontent.com'
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
			gauth.then(function(){
				console.log('googleAuth success');
				checkLoginStatus(); 
			}, function(){
				console.log('googleAuth fails');
			});
		});
	}
	
	
	/* 로그인 상태인지 비로그인 상태인지 판별하는 메소드 */
	/* 로그인한 상태면 현재 유저의 프로필 정보를 가져온다. */
	function checkLoginStatus() { 
		var loginBtn = document.querySelector('#loginBtn'); /* id가 loginBtn인 요소를 가져와서 loginBtn이란 변수에 담기 */
		var nameTxt = document.querySelector('#name'); /* id가 name인 요소(span)를 가져와서 nameTxt란 변수에 담기 */
		if(gauth.isSignedIn.get()){ /* 만약 로그인한 상태라면 */
			console.log('logined'); /* 콘솔에 로그인드를 찍어줌 */
			loginBtn.value = 'Logout'; /* loginBtn의 글자가 Logout으로 바뀜 */
			window.profile = gauth.currentUser.get().getBasicProfile(); /* gauth객체는 현재 유저의 프로필을 가져올 수 있는 메소드를 가지고 있다. */
			console.log(profile.getEmail()); /* 가져온 프로필 정보 출력 */
			console.log(profile.getImageUrl());
			console.log(profile.getName());
			console.log(profile.getId());
			console.log(profile);
			nameTxt.innerHTML = 'Welcome <strong> '+profile.getName()+'</strong> ';
			
			
		} else {
			console.log('logouted'); /* 만약 로그아웃한 상태라면 */
			loginBtn.value = 'Login'; /* 버튼의 글자가 Login으로 설정됨 */
			nameTxt.innerHTML = '';
		}
	};
	
</script>

<script>
	$(document).ready(function(){
		Kakao.init("88e34dd24cbb64e2ee9278a3acbfc78d");
		function getKakaotalkUserProfile(){
			Kakao.API.request({
				url: '/v1/user/me',
				success: function(res) {
					console.log(res.properties.nickname);
					console.log(res.properties.profile_image);
					$("id").append(res.properties.id);
					$("#nickname").append(res.properties.nickname);
					$("#profileImage").append($("<img/>",{"src":res.properties.profile_image,"alt":res.properties.nickname+"님의 프로필 사진"}));
					this.document.getElementById("kakaoForm").submit(); 
				},
				fail: function(error) {
					console.log(error);
				}
			});
		}
		function createKakaotalkLogin(){
			$("#kakao-logged-group .kakao-logout-btn,#kakao-logged-group .kakao-login-btn").remove();
			var loginBtn = $("<a/>",{"class":"kakao-login-btn","text":"로그인"});
			loginBtn.click(function(){
				Kakao.Auth.login({
					persistAccessToken: true,
					persistRefreshToken: true,
					success: function(authObj) {
						getKakaotalkUserProfile();
						createKakaotalkLogout();
					},
					fail: function(err) {
						console.log(err);
					}
				});
			});
			$("#kakao-logged-group").prepend(loginBtn)
		}
		function createKakaotalkLogout(){
			$("#kakao-logged-group .kakao-logout-btn,#kakao-logged-group .kakao-login-btn").remove();
			var logoutBtn = $("<a/>",{"class":"kakao-logout-btn","text":"로그아웃"});
			logoutBtn.click(function(){
				Kakao.Auth.logout();
				createKakaotalkLogin();
				$("#kakao-profile").text("");
			});
			$("#kakao-logged-group").prepend(logoutBtn);
		}
		if(Kakao.Auth.getRefreshToken()!=undefined&&Kakao.Auth.getRefreshToken().replace(/ /gi,"")!=""){
			createKakaotalkLogout();
			getKakaotalkUserProfile();
		}else{
			createKakaotalkLogin();
		}
	});
</script>
</head>
<body>

<!-- 페이스북으로 로그인 -->
<!-- <script type="text/javascript">
window.fbAsyncInit = function() {
    FB.init({
      appId      : '529980714074036',
      cookie     : true,
      xfbml      : true,
      version    : 'v3.1'
    });
      
    FB.AppEvents.logPageView();   
      
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "https://connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));

</script> -->

<!-- 구글로 로그인 -->
<!-- 일단 로그인 화면이 뜬 직후엔 버튼을 누르기 전이라서 맨아래 스크립트안에 써있는 init()이 수행된다. -->
<!-- if this.value(버튼의 value)가 Login인 경우는 즉 로그아웃 상태라는 뜻 -->
<form action="/user/login" method="post" name="googleLogin" >
	<span id="name"></span> 
	<input type="button" id="loginBtn" value="checking..." onclick="
	if(this.value === 'Login'){ 
		gauth.signIn().then(function(){
			console.log('gauth.signIn()');
			checkLoginStatus();
		});
	}else {
		gauth.signOut().then(function(){
			console.log('gauth.signOut()');
			checkLoginStatus();
		});
	}" />
	<input type="hidden" name="useremail" value="" />
	<input type="hidden" name="usernickname" value="" />
</form>
<br>

<!-- 카카오톡으로 로그인 -->
<div id="kakao-logged-group"></div>
<div id="kakao-profile"></div>
<form id="kakaoForm" action="/user/socialLogin" method="post">
	<input type="hidden" name="id" value="" />
	<input type="hidden" name="nickname" value="" />
	<input type="hidden" name="profileImage" value="" />
</form>
  
<!-- email로 로그인 -->
<div>
<form action="/user/login" method="post">
	<label for="useremail">이메일</label>
	<input type="text" id="useremail" name="useremail" /><br>
	<label for="userpw">비밀번호</label>
	<input type="text" id="userpw" name="userpw" /><br>
	<input type="submit" value="로그인"/>
	<button onclick='window.open("/user/join");'>회원가입</button>
</form>
</div>


<!-- 바디 태그 로드가 끝나면 init이라는 함수를 호출하라는 뜻 -->
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
</body>
</html>