<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">

var checkLoginStatus = function(response){
	console.log(response);
	if(response.status == 'connected'){
		/* connected는 1. 페북에도 로그인했고 앱에도 로그인한 상태 */
		document.querySelector("#authBtn").value = 'Logout';
		
		
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
		document.querySelector("#authBtn").value = 'Login';
		document.querySelector('#name').innerHTML = '';

	}
}

function checkFacebookLoginStatus2() {
	document.getElementById("facebookLogin").submit();
}



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
	// 2. 페북은 했고 앱은 안했을때 ('not_authorized')
	// 3. 페북도 앱도 안했을때 ('unknown')
	//
	// These three cases are handled in the callback function.

	//로그인 되었는지 안되었는지 상태를 가져오는 함수
	//인자로 콜백함수를 갖는다.
	//response 안에 status 정보가 담겨있음 -> 개발자 도구에서 확인 가능
	FB.getLoginStatus(checkLoginStatus);

	};


//페이스북 sdk 로드 부분
(function(d, s, id) {
var js, fjs = d.getElementsByTagName(s)[0];
if (d.getElementById(id)) return;
js = d.createElement(s); js.id = id;
js.src = "//connect.facebook.net/en_US/sdk.js";
fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
</script>
</head>
<body>
<form action="/user/socialLogin" method="post" id="facebookLogin">
<input type="button" id="authBtn" value="checking..." onclick="
	if(this.value === 'Login'){
		// now logout
		FB.login(function(res){
			console.log('login => ', res);
			checkLoginStatus(res);
		});
	} else {
		// now login
		FB.logout(function(res){
			console.log('logout => ', res);
			checkLoginStatus(res);
		});
	}
">
	<input type="hidden" id="facebookid" name="id" value="" />
	<input type="hidden" id="facebooknickname" name="nickname" value=""/>
	<input type="hidden" id="facebookprofileImage" name="profileImage" value=""/>
	<input type="hidden" id="facebooksnsIdx" name="snsIdx" value="2" />
</form>

<input type="button" value="" id="test"/>

</body>
</html>