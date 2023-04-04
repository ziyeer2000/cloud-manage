var selectCascade = {
	selectNameRoot : "select",
	
	keyParentId : 'parentId',
	
	keyVal: 'value',
	
	searchName: 'keyword',
	
	/**
	 * 初始化
	 * @param:
	 * 	config: 配置
	 *  	placeId:	位置ID
	 *  	title:		标题
	 *  	url:		数据获取URL
	 *  	keyVal:		返回数据中value的key, 默认 value
	 *  	finish:		当前是否子节点(id, val)
	 *  	dataParse:	数据解析函数, 用于将远程数据解析成数组(data)
	 *  	width:		宽度
	 *  	searchName: 搜索内容的key值, 默认keyword
	 */
	init : function(config) {
		
		var i = 0;
		selectCascade.addSub(i++, config);
	},
	
	/**
	 * 获取数据
	 * @param:
	 * 	placeId: 位置ID
	 */
	getValue: function(placeId) {
		
		var ret = [];
		$.each($("#" + placeId + " select"), function(index, value) {
			var id = $(value).attr("id");
			ret.push(formSelects.value(id, 'valStr'));
	    });
		
		return ret;
	},
	
	addSub : function(i, config, parentVal) {
		var placeId = config.id;
		var title = config.title;
		var url = config.url;
		var keyVal = config.keyVal ? config.keyVal : selectCascade.keyVal;
		var finish = config.finish;
		var dataParse = config.dataParse;
		var width = config.width;
		var searchName = config.searchName ? config.searchName : selectCascade.searchName;
		
		var selectName = placeId + "-" +selectCascade.selectNameRoot + i;
		
		var realUrl = url;
		if (parentVal) {
			if (url.indexOf('?') != -1) {
				realUrl = url + '&' + selectCascade.keyParentId + '=' + parentVal;
			} else {
				realUrl = url + '?' + selectCascade.keyParentId + '=' + parentVal;
			}
		}
		
		if (!finish) {
			$.ajax({
		        type: "GET",
		        url: realUrl,
		        dataType: "json",
		        async:true,
		        success: function(data){
		        	if (dataParse(data).length != 0) {
		        		selectCascade.addSubCore(placeId, i, width, title, selectName, searchName, realUrl, keyVal, finish, dataParse, config);
		        	}
		        }
		    });
		} else {
			selectCascade.addSubCore(placeId, i, width, title, selectName, searchName, realUrl, keyVal, finish, dataParse, config);
		}
	},
	
	addSubCore: function(placeId, i, width, title, selectName, searchName, realUrl, keyVal, finish, dataParse, config) {
		
		var widthHtml = '';
		if (width) {
			widthHtml = ' style="width:' + width + 'px"';
		}
		
		var _html = '';
		_html += '<div class="layui-inline">';
		if (i == 0) {
			_html += '	<label class="layui-form-label">' + title + '：</label>';
		}
		_html += '	<div class="layui-input-inline" '+widthHtml+'>';
		_html += '		<select id="'+selectName+'" name="'+selectName+'" style="width:50px;" xm-select="'+selectName+'" lay-verify="required" xm-select-search="'+realUrl+'" xm-select-radio>';
		_html += '			<option value="">请输入</option>';
		_html += '		</select>';
		_html += '	</div>';
		_html += '</div>';
		
        $('#' + placeId).append(_html);
        formSelects.config(selectName, {
        	"keyVal": "id",
        	"searchUrl": realUrl,
        	"searchName": searchName,
        	beforeSuccess: function(id, url, searchVal, result) {
        		if (dataParse) {
        			return dataParse(result);
        		} else {
        			return result;
        		}
        	}
        }, false);
        
        formSelects.on(selectName, function(id, vals, val, isAdd, isDisabled) {
        	if (isAdd) {
        		if (vals.length != 0) {
        			$('#' + id).parent().parent().nextAll().remove();
        		}
        		
        		if (!finish || (finish && !finish(id, val))) {
        			selectCascade.addSub(++i, config, val[keyVal]);
    			}
        	} else {
        		$('#' + id).parent().parent().nextAll().remove();
		    }
        }, false);
	}
	
};