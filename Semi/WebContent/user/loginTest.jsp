<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
					console.log('google logined');
				} else {
					console.log('google logouted');
				}
			}, function(){
				console.log('googleAuth fails');
			});
		});
	}
	
	
	/* 로그인 상태인지 비로그인 상태인지 판별하는 메소드 */
	/* 로그인한 상태면 현재 유저의 프로필 정보를 가져온다. */
	function checkGoogleLoginStatus() { 
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
			$("#googleid").val(profile.getEmail());
			$("#googlenickname").val(profile.getName());
			$("#googleprofileImage").val(profile.getImageUrl());
			
			/* nameTxt.innerHTML = 'Welcome <strong> '+profile.getName()+'</strong> '; */
			
			checkLoginStatus2();
			
			
		} else {
			console.log('logouted'); /* 만약 로그아웃한 상태라면 */
			loginBtn.value = 'Login'; /* 버튼의 글자가 Login으로 설정됨 */
			nameTxt.innerHTML = '';
		}
	};
	
	function checkLoginStatus2() {
		document.getElementById("googleLogin").submit();
	};
</script>
</head>
<body>

<button></button>
<form action="/user/socialLogin" method="post" id="googleLogin" >
	<input type="button" id="loginBtn" value="checking..." onclick="
	if(this.value === 'Login'){ 
		gauth.signIn().then(function(){
			console.log('gauth.signIn()');
			checkGoogleLoginStatus();
			checkLoginStatus2();
		});
	}else {
		gauth.signOut().then(function(){
			console.log('gauth.signOut()');
			checkGoogleLoginStatus();
		});
	}" />
	<input type="hidden" id="googleid" name="id" value="" />
	<input type="hidden" id="googlenickname" name="nickname" value="" />
	<input type="hidden" id="googleprofileImage" name="profileImage" value="" />
	<input type="hidden" id="googlesnsIdx" name="snsIdx" value="3" />
</form>


<!-- 
1. 이 페이지가 로드되면 무조건 
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
intit 이 로드되면서 구글 auth객체 생성

2. init에서 로그인 상태인지, 로그아웃 상태인지 판별 
   -> 로그인 상태면 checkLoginStatus2() 으로 서브밋
   -> 로그아웃 상태면 가마니
   
3. 로그아웃 상태에서 구글로 로그인 버튼이 클릭되면 
gauth.signIn().then(function(){
			console.log('gauth.signIn()');
			checkGoogleLoginStatus();
			checkLoginStatus2();
			
			위에 세줄 대신 
			getUserProfile()을 해서 유저의 프로필 정보를 가져오고
			checkLoginStatus2() 로 서브밋
		});

 -->
</body>
</html>