<!DOCTYPE html>
<html>
<head>
<title>Facebook Login JavaScript Example</title>
<style type="text/css">
	.help_btn{cursor:pointer;}
	#help_div_0{display:none; position:absolute; left:0px; top:-210px;}
	#help_div_1{display:none; position:absolute; left:80px; top:-210px;}
	#help_div_2{display:none; position:absolute; left:185px; top:-210px;}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
$(".help_btn").each(function(index) {
    $(this).mouseover(function() {
        $("#help_div_" + index).css("display", "block");
    });
    $(this).mouseout(function() {
        $("#help_div_" + index).css("display", "none");
    });
});
</script>
<meta charset="UTF-8">
</head>
<body>
<div style="position:relative">
    <input type="radio">라디오1 <img src="/resources/img/logo.png" class="help_btn">
    <input type="radio">라디오2 <img src="/resources/img/logo.png" class="help_btn">
    <input type="radio">라디오3 <img src="/resources/img/logo.png" class="help_btn">
    
    <div id="help_div_0">설명 1</div>
    <div id="help_div_1">설명 2</div>
    <div id="help_div_2">설명 3</div>
</div>
</body>
</html>