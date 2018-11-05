<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<style>
/*���丮 write ��� div*/
#StoryWriteDiv{
	height: auto;
	padding: 20px;
}

/*���丮 ������ div*/
#storyWriteContentDiv{
	border: 1px solid #B6B7FA;
	width: auto;
	height: auto;
}

/*����� ���*/
.st_cost{
	text-align:right;
	width: 270px;
}

.modal-content{
	width: 620px;
}

/*����� �� �̹���*/
.moneyImage{

}

.st_accType{
	width: 85px;
}

.st_currSymbol{
	width: 75px;

}



</style>
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">��</button>
          <h4 class="modal-title"><span class="glyphicon glyphicon-map-marker modalPlaceName" id ="placename" style="font-size: 25px"><font size="5">place_name</font></span></h4>
        </div>
        <div class="modal-body">
		 
		  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.5.1//js/froala_editor.pkgd.min.js"></script>
			<form class = "writeForm" action="/story/write" method="POST">
			
			<!-- ttb_idx �� ���ܵα� -->
			<input type="hidden" value="" name ="st_ttb_idx" class = "st_ttb_idx" />
			<input type="hidden" value="" name ="st_plan_idx" class = "st_plan_idx" />
			<div id ="StoryWriteDiv">
				<table>
				<tr class="storytr">
					<td class="storytd">
						<div id ="storyWriteContentDiv">
							<textarea name="st_content" class = "st_content"></textarea>
						</div>
					</td>
				</tr>
				</table>
				<div id ="accountViewList">
					<div id = "story_accountView" name = "accountViewName">
						<table>
							<tr class="storytr">	
							  <td class="storytd">
							  <span class = "glyphicon glyphicon-usd moneyImage"></span>
							  </td>
								<td class="storytd">
								<select name = "st_accType" class="form-control st_accType">
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
								<select name = "st_currSymbol" class="form-control st_currSymbol">
									<option value = "1">USD</option>
									<option value = "2">KRW</optoin>
									<option value = "3">JPY</option>
<!-- 									<option value = "JPY">JPY</option> -->
								</select>
								</td>
								<td class="storytd">
									<input type="text" size="33" name = "st_cost" class="form-control st_cost" onkeypress="Numberchk()" onkeyup="vComma(this)"/>
								</td>
								<td class="storytd">
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
        </div> <!-- modal �ٵ� -->
        <div class="modal-footer" style="text-align: center">
          <button type="button" class="btn btn-warning storySaveBtn">Save</button>
        </div>
        
      </div>
      
    </div>
  </div>
  
  <script>

//write â�� xǥ�� �߰�

    $(function() {
    $.FroalaEditor.COMMANDS.imageAlign.options.justify = 'Center';
    $('.st_content').froalaEditor({
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
        width: '530',
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