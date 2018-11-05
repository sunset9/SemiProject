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

<!-- 구글 api  -->
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
			if(gauth.isSignedIn.get()){
				console.log('구글 로그인 상태');
			} else {
				console.log('구글 로그아웃 상태');
			}
		}, function(){
			console.log('googleAuth fails');
		});
	});
}

/* 로그인창 띄워주고 그 후 처리 */
function startGoogleLogin() {
	gauth.signIn().then(function(){
		console.log('gauth.signIn()');
		window.profile = gauth.currentUser.get().getBasicProfile();
		
		/* 가져온 프로필 정보 출력 */
		console.log(profile.getEmail()); 
		console.log(profile.getImageUrl());
		console.log(profile.getName());
		console.log(profile.getId());
		console.log(profile);
		
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
	$(document).ready(function(){
		
		//Javascript SDK 초기화 
		Kakao.init("88e34dd24cbb64e2ee9278a3acbfc78d");
		
		//로그인 버튼 만들어주고 로그인 팝업창 띄워주는 함수
		function createKakaotalkLogin(){
			Kakao.Auth.createLoginButton({
				persistAccessToken: true, //세션이 종료된 뒤에도 Access Token을 사용할 수 있도록 로컬 스토리지에 저장
				persistRefreshToken: true, //세션이 종료된 뒤에도 Refresh Token을 사용할 수 있도록 로컬 스토리지에 저장
				
				container: '#kakao-logged-group', //해당 Element 내부에 로그인 버튼이 생성
				
				//로그인이 성공할 경우 사용자 토큰을 받을 콜백 함수
				success: function(authObj) {
					getKakaotalkUserProfile();
					//kakaoInfoSubmit();
					//createKakaotalkLogout();
				},
				
				//로그인이 실패할 경우 에러를 받을 콜백 함수
				fail: function(err) {
					console.log(err);
				}
			});
		};
		
		//유저정보 가져오는 함수
		function getKakaotalkUserProfile(){
			Kakao.API.request({
				url: '/v1/user/me',
				success: function(res) {
					console.log(res.id);
					console.log(res.properties.nickname);
					console.log(res.properties.profile_image);
					
					//바디에서 해당되는 태그의 id를 찾아서 value에 값 지정해주기
					$("#kakaoid").val(res.id);
					$("#kakaonickname").val(res.properties.nickname);
					$("#kakaoprofileImage").val(res.properties.profile_image);
					
					//value가 지정되면 서브밋해주는 코드 -> 소셜로그인컨트롤러로 서브밋
					document.getElementById("kakaoForm").submit();
				},
				fail: function(error) {
					console.log(error);
				}
			});
		}
		
		function kakaoInfoSubmit() {
			document.getElementById("kakaoForm").submit();
		};
		//로그인시 버튼의 텍스트를 '로그아웃'으로 바꿔줌 
		/* function createKakaotalkLogout(){
			$("#kakao-logged-group .kakao-logout-btn,#kakao-logged-group .kakao-login-btn").remove();
			var logoutBtn = $("<a/>",{"class":"kakao-logout-btn","text":"로그아웃"});
			logoutBtn.click(function(){
				Kakao.Auth.logout();
				createKakaotalkLogin();
				$("#kakao-profile").text("");
			});
			$("#kakao-logged-group").prepend(logoutBtn);
		} */
		
		if(Kakao.Auth.getRefreshToken()!=undefined&&Kakao.Auth.getRefreshToken().replace(/ /gi,"")!=""){
			createKakaotalkLogout();
			getKakaotalkUserProfile();
		}else{
			createKakaotalkLogin();
			console.log('여기다');
		}
	});
</script>

<!-- 페이스북 로그인 api -->
<script type="text/javascript">
/* 페이스북 sdk 로드하는 즉시실행함수 */
(function(d, s, id) {
var js, fjs = d.getElementsByTagName(s)[0];
if (d.getElementById(id)) return;
js = d.createElement(s); js.id = id;
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
	// Now that we've initialized the JavaScript SDK, we call 
	// FB.getLoginStatus(). This function gets the state of the
	// person visiting this page and can return one of three states to
	// the callback you provide. They can be:
	//
	// 1. 페북에도 로그인했고 앱에도 로그인했을때 ('connected')
	// 2. 페북은 로그인했고 앱은 안했을때 ('not_authorized')
	// 3. 페북도 앱도 로그인 안했을때 ('unknown')
	//
	// These three cases are handled in the callback function.

	//로그인 되었는지 안되었는지 상태를 가져오는 함수
	//인자로 checkLoginStatus라는 함수를 전달해서 
	//status
	//response 안에 status 정보가 담겨있음 -> 개발자 도구에서 확인 가능
	//FB.getLoginStatus(checkLoginStatus);

	};

	
	function startFacebookLogin(){
		FB.login(function(res){
			checkLoginStatus(res);
		})
	};
	
	var checkLoginStatus = function(response){
		console.log(response);
		
		if(response.status == 'connected'){			
			
			FB.api('/me', 'GET',{"fields":"id,name,picture"},function(response) {
				window.userProfile = response;
				
				console.log(userProfile);
				
				console.log(userProfile.id);
				console.log(userProfile.name);
				console.log(userProfile.picture.data.url);
				
				$("#facebookid").val(userProfile.id);
				$("#facebooknickname").val(userProfile.name);
				$("#facebookprofileImage").val(userProfile.picture.data.url);
				//$("#test").val(response.picture.data.url);
				
				checkFacebookLoginStatus2();
			});
		} else {
			console.log('not connected');
			/* FB.logout(function(res){
				console.log('logouted');
			}) */
		}
		
	}
	
	function checkFacebookLoginStatus2() {
		document.getElementById("facebookLogin").submit();
	}
</script>

<script type="text/javascript">
/* 회원가입 관련 */
function sendJoinForm(){
	if(joinForm.userid.value == ""){
		alert("이메일을 입력해주세요.");
		joinForm.userid.focus(); //포커스 안해주면 그냥 서브밋 되어버림
		return false;
	}
	
	if(joinForm.userpw.value == ""){
		alert("비밀번호를 입력해주세요.");
		joinForm.userpw.focus();
		return false;
	}
	
	if(joinForm.userpw.value.length<10){
		alert("비밀번호는 10자 이상 입력해주세요.");
		joinForm.userpw.focus();
		joinForm.userpw.select(); //드래그로 선택됨
		return false;
	}
	
	if(joinForm.userpw.value != joinForm.pwCheck.value){
		alert("비밀번호가 일치하지 않습니다.");
		joinForm.pwCheck.value = "";
		joinForm.pwCheck.focus();
		return false;
	}
	
	if(joinForm.usernickname.value == ""){
		alert("닉네임을 입력해주세요.");
		joinForm.usernickname.focus();
		return false;
	}
	
	if(joinForm.usernickname.value.length > 10){
		alert("닉네임은 10자까지만 가능합니다.");
		joinForm.usernickname.focus();
		return false;
	}
} 
 
 
</script>

<style type="text/css">
/* The Modal (background) */
.findPwModal, .joinModal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.findPwModal-contents, .joinModal-contents {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 35%;
}

/* The Close Button */
.fClose, .jClose {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.fClose:hover,
.fClose:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

.jClose:hover,
.jClose:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}
</style>
</head>


<body>

<!-- 구글 로그인 -->
<button onclick="startGoogleLogin();"> 로그인 </button>
<form action="/user/socialLogin" method="post" id="googleLogin" >
	<input type="hidden" id="googleid" name="id" value="" />
	<input type="hidden" id="googlenickname" name="nickname" value="" />
	<input type="hidden" id="googleprofileImage" name="profileImage" value="" />
	<input type="hidden" id="googlesnsIdx" name="snsIdx" value="3" />
</form>

<!-- <input type="image" id="loginBtn" src="/upload/user/google-login.png" style="height: 54px; width: 220px;">
 --><br>

<!-- 카카오톡으로 로그인 -->
<br>
<div id="kakao-logged-group"></div>
<div id="kakao-profile"></div>
<form id="kakaoForm" action="/user/socialLogin" method="post">
	<input type="hidden" id="kakaoid" name="id" value="" />
	<input type="hidden" id="kakaonickname" name="nickname" value="" />
	<input type="hidden" id="kakaoprofileImage" name="profileImage" value="" />
	<input type="hidden" id="kakaosnsIdx" name="snsIdx" value="4">
</form>
<br>


<!-- 페이스북 로그인 -->
<input type="button" onclick="startFacebookLogin();" value="페이스북 로그인"/>
<form action="/user/socialLogin" method="post" id="facebookLogin">
	<input type="hidden" id="facebookid" name="id" value="" />
	<input type="hidden" id="facebooknickname" name="nickname" value=""/>
	<input type="hidden" id="facebookprofileImage" name="profileImage" value=""/>
	<input type="hidden" id="facebooksnsIdx" name="snsIdx" value="2" />
</form>
<br>


<!-- email로 로그인 -->
<div>
<form action="/user/login" method="post">
	<label for="userid">이메일 </label>
	<input type="email" id="useridforemail" name="userid" /><br>
	<label for="userpw">비밀번호</label>
	<input type="password" id="userpwforemail" name="userpw" /><br>
	<input type="hidden" id="snsIdxforemail" name="snsIdx" value="1" />
	<input type="submit" value="로그인"/>
</form>
<!-- 회원가입 open the modal btn -->
<button id="joinModalBtn" class="joinlogo">회원가입</button>

<!-- 비밀번호 찾기 open the modal btn -->
<button id="findPwModalBtn" class="findPw">비밀번호 찾기</button>
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
			<input type="email" name="email" placeholder="이메일을 입력해주세요." />
			<input type="submit" value="입력" />
		</form>
		<p>임시 비밀번호를 보내드립니다.</p>
		<p>가입시 입력하신 이메일 주소를 입력해주세요.</p>
	</div>
</div>
<!-- 비밀번호 찾기 모달 끝 -->

<!-- 회원가입 모달 시작 -->
<div id="joinModal" class="joinModal">
	
	<!-- 회원가입 modal content  -->
	<div class="joinModal-contents">
		<span class="jClose">&times;</span>
		<form id="joinForm" action="/user/join" method="post" onsubmit="return sendJoinForm();">
		<div id="sign_up">
			<div class="header">
			<h3>회원가입</h3><br>
			</div>
			<div class="body">
				<div class="signUp">
					<label>이메일 : </label>
					<input type="email" id="userid" name="userid" placeholder="이메일"><br>
					<label>비밀번호 : </label>
					<input type="password" id="userpw" name="userpw" placeholder="비밀번호"><br>
					<label>비밀번호 확인 : </label>
					<input type="password" id="pwCheck" name="pwCheck" placeholder="비밀번호 확인"><br>
					<label>닉네임 : </label>
					<input type="text" id="usernickname" name="usernickname" placeholder="닉네임"><br>
					<input type="hidden" id="snsIdx" name="snsIdx" value="1" />
				</div>
				<div class="signUpBtn">
					<button type="submit" class="signUpBtn" >가입하기</button>
				</div>
			</div>
		</div>
		</form>
	</div>
</div>
<!-- 회원가입 모달 끝 -->


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

<!-- 바디 태그 로드가 끝나면 init이라는 함수를 호출하라는 뜻 -->
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
</body>
</html>