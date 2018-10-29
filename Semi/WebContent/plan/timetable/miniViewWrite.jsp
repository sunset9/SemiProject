<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.5.1//js/froala_editor.pkgd.min.js"></script>
<style>

</style>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="miniViewWriteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      
      <div class="modal-body">
		<!-- div (팝업으로 띄어줄) 본문 내용 -->	
		<div style="border: 1px solid #9AA3E6; height: auto;" >
			<form>
			<table style="width: 100%;">
			<tr>
				<td rowspan="2" style="padding: 10px 15px; width: 60%;">
				<img class="miniImg" width="280" height="150" alt=""/>
				</td>
				<td class="miniTitle" style="font-weight: bold; width: 40%;"><hr></td>
			</tr>	
			<tr>
				<td>
				추가 정보 란
				</td>
			</tr>
			<tr>
				<td style="padding-right: 15px" colspan="2">
					<font size="2">식비 | 10,000 달러($)</font>
				</td>
			</tr>
				<tr>
					<td style="padding-right: 15px" colspan="2">
						<font size="2">오락 | 10,000 달러($)</font>
					</td>
				</tr>
			<tr>
			<td colspan="2" style="padding: 15px">
				<div class="storyContent" style="height: 230px;"></div>
			</td>
			</tr>

			</table>
			</form>
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

<script>

$(function() {
$.FroalaEditor.COMMANDS.imageAlign.options.justify = 'Center';

$('.storyContent').froalaEditor({
    // Set the image upload URL.
    enter: $.FroalaEditor.ENTER_DIV,
    charCounterCount: false,
    pluginsDisable: ["quickInsert"],
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
 heightMin: 185,
    heightMax: 185,
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
</body>
</html>