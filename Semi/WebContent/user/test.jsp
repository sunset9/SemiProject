<!DOCTYPE html>
<html>
<head>
<title>Facebook Login JavaScript Example</title>
<meta charset="UTF-8">
</head>
<body>
<script>
// This is called with the results from from FB.getLoginStatus().
function statusChangeCallback(response) {
console.log('statusChangeCallback');
console.log(response);
// The response object is returned with a status field that lets the
// app know the current login status of the person.
// Full docs on the response object can be found in the documentation
// for FB.getLoginStatus().
if (response.status === 'connected') {
// Logged into your app and Facebook.
testAPI();
} else if (response.status === 'not_authorized') {
// The person is logged into Facebook, but not your app.
document.getElementById('status').innerHTML = 'Please log ' +
'into this app.';
} else {
// The person is not logged into Facebook, so we're not sure if
// they are logged into this app or not.
document.getElementById('status').innerHTML = 'Please log ' +
'into Facebook.';
}
}
// This function is called when someone finishes with the Login
// Button. See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
FB.getLoginStatus(function(response) {
statusChangeCallback(response);
});
}
window.fbAsyncInit = function() {
FB.init({
appId : '1865349853561280',
cookie : true, // enable cookies to allow the server to access 
// the session
xfbml : true, // parse social plugins on this page
version : 'v3.2' // use version 2.0
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

FB.getLoginStatus(function(response) {
statusChangeCallback(response);
});

};

// Load the SDK asynchronously
(function(d, s, id) {
var js, fjs = d.getElementsByTagName(s)[0];
if (d.getElementById(id)) return;
js = d.createElement(s); js.id = id;
js.src = "//connect.facebook.net/en_US/sdk.js";
fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Here we run a very simple test of the Graph API after login is
// successful. See statusChangeCallback() for when this call is made.
function testAPI() {
console.log('Welcome! Fetching your information.... ');
FB.api('/me', function(response) {
console.log('Successful login for: ' + response.name); //response.name == 사용자의 이름
document.getElementById('status').innerHTML =
'Thanks for logging in, ' + response.name + '!';
});
}
</script>

<!--
Below we include the Login Button social plugin. This button uses
the JavaScript SDK to present a graphical Login button that triggers
the FB.login() function when clicked.
-->

<!-- 페이스북 로그인 버튼 -->
<!-- 클릭하면 checkLoginState() 함수 작동 -->
<fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
</fb:login-button>

<div id="status">
</div>

</body>
</html>