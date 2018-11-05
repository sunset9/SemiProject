<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<style>
/*��� �ٵ�*/
#storyUpdateDiv{
	height: auto; 
	width:500px; 
	padding: 20px;
}

/*������Ʈ ������ div*/
#storyUpdateContentDiv{
	border: 1px solid #B6B7FA; 
	width: auto; 
	height: auto;
}

/*����� ���*/
.up_cost{
	text-align:right;
}

/*����� ���� �̹���*/
.moneyImage_up{

}


</style>
    
<div class="modal fade" id="myModal_update" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">��</button>
          <h4 class="modal-title"><span class="glyphicon glyphicon-map-marker up_modalPlaceName" id ="placename" style="font-weight: bold;font-size: 25px"><font size="5">place_name</font></span></h4>
        </div>
        <div class="modal-body">
		 
		  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.5.1//js/froala_editor.pkgd.min.js"></script>
			<form class = "updateForm" action="/story/write" method="POST">
			
			<!-- ttb_idx �� ���ܵα� -->
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
					<td class="storytd">
						<select name = "up_accType" class="up_accType" name = "up_accType">
							<option value="1">�װ���</option>
							<option value="2">����</optoin>
							<option value="3">����</option>
							<option value="4">�����</option>
							<option value="5">����</option>
							<option value="6">����</option>
							<option value="7">����</option>
							<option value="8">��Ÿ</option>
						</select>
					</td>
					<td class="storytd">
						<select name = "up_currSymbol" class="up_currSymbol" name = "up_currSymbol">
							<option value = "1">USD</option>
							<option value = "2">KRW</optoin>
							<option value = "3">JPY</option>
						</select>
					</td>
					<td class="storytd">
					<input type="text" size="40" name = "up_cost" class="up_cost" onkeypress="Numberchk()" onkeyup="vComma(this)"/>
					</td>
					<td class="storytd">
						<span class="glyphicon glyphicon-plus accountPlus" onclick = "UpappendAccount()" onmouseover="mover($(this))" 
							onmouseleave="mleave($(this))" onmousedown="mdown($(this))"></span>
					</td>
					<td class="storytd">
						<span class="glyphicon glyphicon-remove accountRemove" name = "removeAcoountName" onclick = "UpremoveAccount()" style="display: none" 33onmouseover="mover($(this))" 
							onmouseleave="mleave($(this))" onmousedown="mdown($(this))"></span>
					</td>
				</tr>
				</table>
				</div>
			  </div>
			</div>
        </form>
        </div> <!-- modal �ٵ� -->
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
	        //��� ����Ҷ� �߻��ϴ� ���� : image edit menu�� �ȶ�, -> �ذ�� : zIndex�� ������
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