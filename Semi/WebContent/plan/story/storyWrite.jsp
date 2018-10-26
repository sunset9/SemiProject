<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
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
			<form class = "ModalForm" action="/story/write" method="POST">
			
			<!-- ttb_idx 값 숨겨두기 -->
			<input type="hidden" value="" name ="ttb_idx" class = "ttb_idx" />
			<input type="hidden" value="" name ="plan_idx" class = "plan_idx" />
			<div style="height: auto; width:500px; padding: 20px;" id ="StoryWriteDiv">
				<table>
				<tr>
					<td colspan="4">
						<div style="border: 1px solid #B6B7FA; width: auto; height: auto" >
							<textarea id = "edit" name="content" class = "content"></textarea>
						</div>
					</td>
				</tr>
				<tr>	
					<td>
					<select>
						<option>교통</option>
						<option>식비</optoin>
						<option>오락</option>
					</select>
					</td>
					<td>
						<select>
						<option>USD</option>
						<option>KRW</optoin>
						<option>CAD</option>
						<option>CNY</option>
					</select>
					</td>
					<td>
					<input type="text" size="40"/>
					</td>
					<td>
						<span class="glyphicon glyphicon-plus"></span>
					</td>
					<td>
						<span class="glyphicon glyphicon-remove"></span>
					</td>
				</tr>
				</table>
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