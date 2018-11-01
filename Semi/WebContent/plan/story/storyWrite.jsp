<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<script>

// 	//콤마찍기
// 	function comma(str) {
// 	    str = String(str);
// 	    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
// 	}
	
// 	//콤마풀기
// 	function uncomma(str) {
// 	    str = String(str);
// 	    return str.replace(/[^\d]+/g, '');
// 	}
	 
// 	//값 입력시 콤마찍기
// 	function inputNumberFormat(obj) {
// 		// 		console.log(obj.parents());
// // 		console.log(obj.parents().parent());
// // 		console.log(obj.parents().parent().parent());
// 	    obj.value = comma(uncomma(obj.value));
// 	}
	
	function Numberchk() { 
		if (event.keyCode < 46 || event.keyCode > 57) event.returnValue = false; 
	} 

	function vComma(obj) { 
		var str    = "" + obj.value.replace(/,/gi,''); // 콤마 제거 
		var regx    = new RegExp(/(-?\d+)(\d{3})/); 
		var bExists = str.indexOf(".",0); 
		var strArr  = str.split('.'); 
		
		while(regx.test(strArr[0])){ 
			strArr[0] = strArr[0].replace(regx,"$1,$2"); 
		} 
		if (bExists > -1) 
			obj.value = strArr[0] + "." + strArr[1]; 
		else 
			obj.value = strArr[0]; 
	} 

	function trim(str) { 
		return str.replace(/(^\s*)|(\s*$)/g, ""); 
	} 

	function getNumber(str) { 
		str = "" + str.replace(/,/gi,''); // 콤마 제거 
		str = str.replace(/(^\s*)|(\s*$)/g, ""); // trim 
		return (new Number(str)); 
	} 
	
</script>
    
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4 class="modal-title"><span class="glyphicon glyphicon-map-marker modalPlaceName" id ="placename" style="font-weight: bold;font-size: 25px"><font size="5">place_name</font></span></h4>
        </div>
        <div class="modal-body">
		 
		  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.5.1//js/froala_editor.pkgd.min.js"></script>
			<form class = "writeForm" action="/story/write" method="POST">
			
			<!-- ttb_idx 값 숨겨두기 -->
			<input type="hidden" value="" name ="ttb_idx" class = "ttb_idx" />
			<input type="hidden" value="" name ="plan_idx" class = "plan_idx" />
			<div style="height: auto; padding: 20px;" id ="StoryWriteDiv">
				<table>
				<tr>
					<td>
						<div style="border: 1px solid #B6B7FA; width: auto; height: auto" >
							<textarea id = "edit" name="content" class = "content"></textarea>
						</div>
					</td>
				</tr>
				</table>
				<div id ="accountViewList">
				
					<div id = "accountView" name = "accountViewName">
						<table>
							<tr>	
								<td>
								<select name = "accType" class="accType">
									<option value="1">항공료</option>
									<option value="2">교통</optoin>
									<option value="3">숙박</option>
									<option value="4">입장료</option>
									<option value="5">음식</option>
									<option value="6">오락</option>
									<option value="7">쇼핑</option>
									<option value="8">기타</option>
								</select>
								</td>
								<td>
								<select name = "currSymbol" class="currSymbol">
									<option value = "1">USD</option>
									<option value = "2">KRW</optoin>
									<option value = "3">JPY</option>
<!-- 									<option value = "JPY">JPY</option> -->
								</select>
								</td>
								<td>
<!-- 								<input type="text" size="48" name = "cost" class="cost" onkeyup="inputNumberFormat(this)" style = "text-align:right;"/> -->
								<input type="text" size="48" name = "cost" class="cost" onkeypress="Numberchk()" onkeyup="vComma(this)" style = "text-align:right;"/>
								</td>
								<td>
									<span class="glyphicon glyphicon-plus accountPlus" onclick = "appendAccount()" onmouseover="mover($(this))" 
							onmouseleave="mleave($(this))" onmousedown="mdown($(this))"></span>
								</td>
								<td>
									<span class="glyphicon glyphicon-remove accountRemove" name = "removeAcoountName" onclick = "removeAccount()" style="display: none" onmouseover="mover($(this))" 
							onmouseleave="mleave($(this))" onmousedown="mdown($(this))"></span>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
        </form>
        </div> <!-- modal 바디끝 -->
        <div class="modal-footer" style="text-align: center">
          <button class ="storySaveBtn" type="button" class="btn btn-warning">Save</button>
        </div>
        
      </div>
      
    </div>
  </div>
  
  <script>

//write 창에 x표시 추가

    $(function() {
    $.FroalaEditor.COMMANDS.imageAlign.options.justify = 'Center';
    $('.content').froalaEditor({
        // Set the image upload URL.
        enter: $.FroalaEditor.ENTER_DIV,
        //모달 사용할때 발생하는 문제 : image edit menu가 안뜸, -> 해결법 : zIndex를 높여라
        zIndex: 2501,
        toolbarButtons: ['fontFamily','bold', 'italic', 'underline','align','|','insertLink','insertImage','|', 'undo', 'redo'],
        toolbarButtonsXS: ['fontFamily','bold', 'italic', 'underline','align','|','insertLink','insertImage','|', 'undo', 'redo'],
        toolbarButtonsSM: ['fontFamily','bold', 'italic', 'underline','align','|','insertLink','insertImage','|', 'undo', 'redo'],
        toolbarButtonsMD: ['fontFamily','bold', 'italic', 'underline','align','|','insertLink','insertImage','|', 'undo', 'redo'],
        imageUploadURL: '/story/upload_image',
        imageUploadParams: {
          id: 'my_editor'
        },
	    imageEditButtons : ['imageAlign', 'imageRemove', 'imageLink','imageSize','imageDisplay'],
	    heightMin: 300,
        heightMax: 300,
        width: '520',
      }).on('froalaEditor.image.error', function (e, editor, error, response) {
    	  console.log(error);
    	  console.log(response);
    	}).on('froalaEditor.image.removed', function (e, editor, $img) {
            $.ajax({
                // Request method.
                method: "POST",
       
                // Request URL.
                url: "/story/image_delete",
       
                // Request params.
                data: {
                  src: $img.attr('src')
                }
              })
              .done (function (data) {
                console.log ('image was deleted');
                console.log($img.attr('src'));
              })
              .fail (function () {
                console.log ('image delete problem');
              })
            });
    });
 
    
  </script> 