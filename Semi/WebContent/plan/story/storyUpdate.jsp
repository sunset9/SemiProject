<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<style>
/*모달 바디*/
#storyUpdateDiv{
	height: auto; 
	width:500px; 
	padding: 20px;
}

/*업데이트 본문쪽 div*/
#storyUpdateContentDiv{
	border: 1px solid #B6B7FA; 
	width: auto; 
	height: auto;
}

/*가계부 비용*/
.up_cost{
	text-align:right;
	width: 300px;
}

/*가계부 옆에 이미지*/
.moneyImage_up{

}

.up_accType{
	width: 85px;
}

.up_currSymbol{
	width: 75px;

}

/* 닫기 버튼 */
button.close{
    opacity: .7; 
	color: white;
    font-size: 30px;
    margin-top: 6px !important;
}
/* 닫기 버튼 위에 마우스 */
button.close:focus, button.close:hover{
    opacity: .7;
    color: black;
    font-size: 30px;
}

.storyUpdateBtn{
	background-image: linear-gradient(to bottom, #50b69c 0,#429480 100%);
	border-color: #28715e;
}

.storyUpdateBtn:hover{
    background: #429480;
	border-color: #28715e;
}

.accountRemove{
	color:#777777;
}

.accountPlus{
	color:#777777;
}



</style>
    
<div class="modal fade" id="myModal_update" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <span class="glyphicon glyphicon-map-marker"style="font-size: 25px; display: inline"></span><h4 class="modal-title up_modalPlaceName" id ="placename" style="display: inline">place_name</h4>
        </div>
        <div class="modal-body">
		 
		  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.5.1//js/froala_editor.pkgd.min.js"></script>
			<form class = "updateForm" action="/story/write" method="POST">
			
			<!-- ttb_idx 값 숨겨두기 -->
			<input type="hidden" value="" name ="up_ttb_idx" class = "up_ttb_idx" />
			<input type="hidden" value="" name ="up_plan_idx" class = "up_plan_idx" />
			<input type ="hidden" value="" name ="up_story_idx" class="up_story_idx"/>
			<div  id ="storyUpdateDiv">
				<table>
				<tr class="storytr">
					<td colspan="4" class="storytd">
						<div id = storyUpdateContentDiv" >
							<textarea id = "up_edit" name="up_content" class = "up_content">
							</textarea>
						</div>
					</td>
				</tr>
				</table>
				<div id ="up_accountViewList">
				<div id = "up_accountView" name = "up_accountViewName" class ="up_accountViewClass">
				<table>
				<tr class="storytr">
				 <td class="storytd">
					<span class = "glyphicon glyphicon-usd moneyImage_up"></span>
					</td>	
					<td class="storytd" style="padding-left: 0px">
						<select class="form-control up_accType" name = "up_accType" style="width: 85px">
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
					<td class="storytd" style="padding-left: 0px">
						<select class="form-control up_currSymbol" name = "up_currSymbol">
							<option value = "1">USD</option>
							<option value = "2">KRW</optoin>
							<option value = "3">JPY</option>
						</select>
					</td>
					<td class="storytd" style="padding-left: 0px">
					<input type="text" size="33" name = "up_cost" class="form-control up_cost" onkeypress="Numberchk()" onkeyup="vComma(this)" onkeydown="fn_press_han(this)"/>
					</td>
					<td class="storytd" style="padding: inherit;">
						<span class="glyphicon glyphicon-plus accountPlus" onclick = "UpappendAccount()" onmouseover="st_mover($(this))" 
							onmouseleave="st_mleave($(this))" onmousedown="st_mdown($(this))"></span>
					</td>
					<td class="storytd" style="padding: inherit;">
						<span class="glyphicon glyphicon-remove accountRemove" name = "removeAcoountName" onclick = "UpremoveAccount()" style="display: none" onmouseover="st_mover($(this))" 
							onmouseleave="st_mleave($(this))" onmousedown="st_mdown($(this))"></span>
					</td>
				</tr>
				</table>
				</div>
			  </div>
			</div>
        </form>
        </div> <!-- modal 바디끝 -->
        <div class="modal-footer">
       	  <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
          <button class ="btn btn-primary storyUpdateBtn" type="button">저장</button>
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
  
