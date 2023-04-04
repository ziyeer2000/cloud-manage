var richeditor = {

	/**初始化富文本控件
	 * 
	 * @param textAreaId
	 * 		textarea ID
	 * @param dataFormat
	 * 		上传返回格式处理. 
	 * 		function(retJson) {
	 * 			return {
	 * 				fileName: retJson.fileList[0].originalFileName,
	 * 				url: "/cloud-manage-storage/" + retJson.fileList[0].fileRelative
	 * 			}
	 * 		}
	 * @param finishCallback
	 * 		完成(失去焦点)回调
	 */
	init : function(textAreaId, dataFormat, finishCallback) {
		
		var editor = CKEDITOR.replace(textAreaId, {
	        language: 'zh-CN',
	        removeButtons: 'Anchor',
	        width: $("#" + textAreaId).width(),
	        enterMode: CKEDITOR.ENTER_BR,
	        shiftEnterMode: CKEDITOR.ENTER_P,
	        filebrowserUploadUrl: richeditorConfig.simpleUploadUrl,
	        removePlugins: 'mathjax',
	        toolbar: [
	        	['Source','Preview','Maximize'],
	        	['PasteFromWord','Undo','Redo'],
	        	['Bold','Italic','Underline','Strike','Subscript','Superscript'],
	        	['Font','FontSize'],
	        	['TextColor','BGColor'],
	        	['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	        	['NumberedList','BulletedList'],
	        	['Indent','Outdent'],
	        	['Link','Unlink','Image','Html5video','Mathjax','Flash','Iframe']
	        ]
	    });
		
		//上传请求处理
		editor.on('fileUploadRequest', function( evt ) {
	    	var fileLoader = evt.data.fileLoader;
	    	var fileName = fileLoader.fileName;
	        var xhr = fileLoader.xhr;
	        var upload = xhr.upload;
	        
	        xhr.withCredentials = true;
	        
	        //上传完成
	        upload.onload = function(evt) {
	        	var processId = richeditor.getProcessId(fileName);
        		$('#' + processId + ' .process_text').text('完成');
        		
        		var canClose = true;
        		$("div .process_text").each(function(index){
        			if ($(this).text() != '完成') {
        				canClose = false;
        				return false;
        			}
        		});
        		
        		if (canClose) {
        			layer.close($("#uploadDiv").attr('layer-index'));
        		} 
	        };
	        
	        //上传进度条
	        upload.onprogress = function(evt) {
	        	var processId = richeditor.getProcessId(fileName);
	        	
	        	if ($('#uploadDiv').length <= 0) {
	        		richeditor.addUploadProcessWin(fileName, processId);
	        	}
	        	
	            var progress = parseInt(evt.loaded / evt.total * 100);
	            
	            layui.element.progress(processId, progress +'%');
	            
	            $('#' + processId + ' .process_text').text('上传中...');
	            if (progress == 100) {
	            	$('#' + processId + ' .process_text').text('处理中...');
	            }
	        };
	    });
		
		//上传返回处理
		editor.on('fileUploadResponse', function( evt ) {
	    	evt.stop();

	    	var fileLoader = evt.data.fileLoader,
	    	xhr = fileLoader.xhr,
	    	data = evt.data;
	    	var responseJson = JSON.parse( xhr.responseText );
	    	
	    	var retData = dataFormat(responseJson)
	    	data.fileName = retData.fileName;
	    	data.url = retData.url;
	    	
	    	return true;
	    });
		
		//失去焦点
		editor.on('blur', function( evt ) {
			var editor_ = evt.editor;
			$("#" + editor_.name).text(editor_.getData());
			
			if (finishCallback) {
				finishCallback();
			}
		});
	},
	
	//增加上传进度条
	addUploadProcessWin : function(fileName, processId) {
		
		var i = layer.open({
		    type: 1,
		  	fixed: false, 
		  	maxmin: false,
		  	area: '400px',
		  	maxHeight: 600,
		  	title: "上传",
		  	content: '<div id="uploadDiv" class="layui-container" style="width: auto;"></div>'
		});
		
		$("#uploadDiv").attr('layer-index', i);
		
    	var _html = '<div id="' + processId + '">';
    	
    	_html += '	<div class="layui-row layui-col-space10" style="margin-top: 0px;margin-bottom: 0px;">';
    	_html += '		<div class="layui-col-md3">';
    	_html += '			<div class="process_file_name">'+fileName+'</div>';
    	_html += '		</div>';
    	_html += '		<div class="layui-col-md7">';
    	_html += '			<div class="layui-progress layui-progress-big process_bar" lay-filter="'+processId+'" >';
    	_html += '				<div class="layui-progress-bar layui-bg-green" lay-percent="0%">';
    	_html += '					<span class="layui-progress-text">0%</span>';
    	_html += '				</div>';
    	_html += '			</div>';
    	_html += '		</div>';
    	_html += '		<div class="layui-col-md2">';
    	_html += '			<div class="process_text">上传中...</div>';
    	_html += '		</div>';
    	_html += '	</div>';
    	
    	_html += '</div>';
    	
    	$("#uploadDiv").append(_html);
    	layui.element.render('progress', processId);
	},
	
	getProcessId : function(fileName) {
		
		return 'progress_'+ fileName.replace(/[^a-zA-Z0-9]/g, "");
	},
	
	/**
	 * 获取内容
	 */
	getData : function(textAreaId) {
		return CKEDITOR.instances[textAreaId].getData();
	},
	
	/**
	 * 设置内容
	 */
	setData : function(textAreaId, data) {
		return CKEDITOR.instances[textAreaId].setData(data);
	}
};