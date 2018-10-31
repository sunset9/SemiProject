<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<script>
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
    
<div class="modal fade" id="myModal_update" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4 class="modal-title"><span class="glyphicon glyphicon-map-marker up_modalPlaceName" id ="placename" style="font-weight: bold;font-size: 25px"><font size="5">place_name</font></span></h4>
        </div>
        <div class="modal-body">
		 
		  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.5.1//js/froala_editor.pkgd.min.js"></script>
			<form class = "updateForm" action="/story/write" method="POST">
			
			<!-- ttb_idx 값 숨겨두기 -->
			<input type="hidden" value="" name ="up_ttb_idx" class = "up_ttb_idx" />
			<input type="hidden" value="" name ="up_plan_idx" class = "up_plan_idx" />
			<input type ="hidden" value="" name ="up_story_idx" class="up_story_idx"/>
			<div style="height: auto; width:500px; padding: 20px;" id ="StoryWriteDiv">
				<table>
				<tr>
					<td colspan="4">
						<div style="border: 1px solid #B6B7FA; width: auto; height: auto" >
							<textarea id = "up_edit" name="up_content" class = "up_content">
							
							</textarea>
						</div>
					</td>
				</tr>
				</table>
				<div id ="up_accountViewList">
				<div id = "up_accountView" name = "up_accountViewName" class ="up_accountViewClass">
				<table>
				<tr>	
					<td>
						<select name = "up_accType" class="up_accType">
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
						<select name = "up_currSymbol" class="up_currSymbol">
							<option value = "1">USD</option>
							<option value = "2">KRW</optoin>
							<option value = "3">JPY</option>
						</select>
					</td>
					<td>
					<input type="text" size="48" name = "up_cost" class="up_cost" onkeypress="Numberchk()" onkeyup="vComma(this)" style = "text-align:right;"/>
					</td>
					<td>
						<span class="glyphicon glyphicon-plus accountPlus" onclick = "UpappendAccount()" onmouseover="mover($(this))" 
							onmouseleave="mleave($(this))" onmousedown="mdown($(this))"></span>
					</td>
					<td>
						<span class="glyphicon glyphicon-remove accountRemove" name = "removeAcoountName" onclick = "UpremoveAccount()" style="display: none" 33onmouseover="mover($(this))" 
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
          <button class ="storyUpdateBtn" type="button" class="btn btn-warning">Save</button>
        </div>
        
      </div>
      
    </div>
  </div>
  
  <script>
  $(function() {
	    $.FroalaEditor.COMMANDS.imageAlign.options.justify = 'Center';
	    $('.up_content').froalaEditor({
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