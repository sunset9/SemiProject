<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script type="text/javascript">
	
	function checkLoginStatus() { /* 로그인 상태인지 비로그인 상태인지 판별하는 메소드 */
		var loginBtn = document.querySelector('#loginBtn'); /* id가 loginBtn인 요소를 가져와서 loginBtn이란 변수에 담기 */
		var nameTxt = document.querySelector('#name'); /* id가 name인 요소를 가져와서 nameTxt란 변수에 담기 */
		if(gauth.isSignedIn.get()){ /* 만약 로그인한 상태라면 */
			console.log('logined'); /* 콘솔에 로그인드를 찍어줌 */
			loginBtn.value = 'Logout'; /* loginBtn의 글자가 Logout으로 바뀜 */
			window.profile = gauth.currentUser.get().getBasicProfile(); /* gauth객체는 현재 유저의 프로필을 가져올 수 있는 메소드를 가지고 있다. */
			console.log(profile.getEmail()); /* 가져온 프로필 정보 출력 */
			console.log(profile.getImageUrl());
			console.log(profile.getName());
			console.log(profile.getId());
			nameTxt.innerHTML = 'Welcome <strong> '+profile.getName()+'</strong> ';
			
		} else {
			console.log('logouted');
			loginBtn.value = 'Login';
			nameTxt.innerHTML = '';
		}
	}
	<%  %>
	function init() { /* auth객체 생성자 : 바디태그를 다 읽은 후 이 메소드가 제일 먼저 호출된다. */
		console.log('init');
		/* gapi(구글 api) 중 auth2라는 기능을 로드한 후, 다음에 정의한 function을 수행하라는 뜻. */
		gapi.load('auth2', function() { 
			
			/* var gauth가 아니라 window.gauth라고 해주면 전역 변수로 사용 가능하다. 아래 바디 태그에서도 사용가능 */
			window.gauth = gapi.auth2.init({  /* 인증(허가) 객체 초기화 메소드 */
				client_id:'261787449656-7thgl5h7flg6lvb5sa3vb22r1tqfo4fc.apps.googleusercontent.com'
			})
			/* gapi.auth2.init()의 리턴 타입은 gapi.auth2.GoogleAuth이다.
			그래서 아래의 gauth 라는 변수의 타입은 GoogleAuth임.
			그리고 init()의 파라미터로 client_id를 전달해주면 
			gauth는 나의 auth(허가, authorization) 정보를 가진 객체가 된다.
			*/
			
			/* then이라는 함수는 inti() 후 상태를 알려준다. 
			첫번째 인자는 init()이 성공적으로 수행됐을때 호출되고,
			두번째 인자는 init()이 호출되는데 문제가 생겼을때 호출된다.*/
			gauth.then(function(){
				console.log('googleAuth success');
				checkLoginStatus();
			}, function(){
				console.log('googleAuth fails');
			});
		});
	}
	

</script>
<!-- <script type="text/javascript">
function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
	}
</script> -->
</head>
<body>

<form action="/user/login" method="post" >
	<span id="name"></span> <input type="button" id="loginBtn" value="checking..." onclick="
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
	}
" />
</form>
<br>

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