String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
};

var utils = {
	
	loadUploadFileJs : false,
	
	/**
	 * 获取url参数
	 * 
	 * @param key
	 * @param customUrl
	 * 		默认空,使用location.href
	 */
	getUrlParam : function(key, customUrl){
		if(key){
			var url = location.href;
			if (customUrl) {
				url = customUrl;
			}
			var paramsStr = url.substring(url.lastIndexOf("?")+1);
			var paramArray = paramsStr.split("&");
			for(var i=0;i<paramArray.length;i++){
				var p = paramArray[i].split("=");
				if(p[0]==key){
					return p[1];
				}
			}
		}
		return;
	},
	
	/**
	 * 根据文件名获取文件类型
	 * 
	 * @param fileName
	 * @returns 文件后缀的大写
	 */
	getFileExtention : function(fileName){
		if(fileName){
			return fileName.split('.')[fileName.split('.').length-1].toUpperCase();
		}
		return;
	},
	
    /**
     * 日期转换
     * yyyy splitStr mm splitStr dd hh:mm:ss
     * @param {} date  splitStr 分隔字符
     * @return {}
     */
    dateFormatSec : function(date,splitStr){
        if (date) {
            if(!splitStr){
                splitStr = "/";
            }
            var month = parseInt(date.getMonth())<9?"0"+(date.getMonth()+1):date.getMonth()+1;
            var day = parseInt(date.getDate())<10?"0"+date.getDate():date.getDate();
            var hours = parseInt(date.getHours())<10?"0"+date.getHours():date.getHours();
            var minutes = parseInt(date.getMinutes())<10?"0"+date.getMinutes():date.getMinutes();
            var seconds = parseInt(date.getSeconds())<10?"0"+date.getSeconds():date.getSeconds();
            return date.getFullYear() + splitStr + month + splitStr + day + ' ' + hours + ':' + minutes + ':' + seconds;
        } else {
            return "未知";
        }
    },
    
    /**
     * 日期转换
     * yyyy splitStr mm splitStr dd 
     * @param {} date  splitStr 分隔字符
     * @return {}
     */
    dateFormatDay : function(date,splitStr){
        if (date) {
            if(!splitStr){
                splitStr = "/";
            }
            var month = parseInt(date.getMonth())<9?"0"+(date.getMonth()+1):date.getMonth()+1;
            var day = parseInt(date.getDate())<10?"0"+date.getDate():date.getDate();
            return date.getFullYear() + splitStr + month + splitStr + day;
        } else {
            return "未知";
        }
    },
    
    loadJS : function(url,callback,charset) {
		var script = document.createElement('script');
		script.onload = script.onreadystatechange = function ()
		{
			if (script && script.readyState && /^(?!(?:loaded|complete)$)/.test(script.readyState)) return;
			script.onload = script.onreadystatechange = null;
			script.src = '';
			script.parentNode.removeChild(script);
			script = null;
			if(callback)callback();
		};
		script.charset=charset || document.charset || document.characterSet;
		script.src = url;
		try {document.getElementsByTagName("head")[0].appendChild(script);} catch (e) {}
	},
	
    /**
     * 分页
     * @param pages
     * 						当前页
     * @param rows
     * 						每页行数
     * @param total
     * 						总记录数
     * @param clickFun
     * 						点击函数
     */
    pagination : function(page, rows, total, clickFun, place) {
    	
    	if (!place) {
    		place = "pagination";
    	}
    	var ret = "";
    	var start = 1;
    	var totalPage = Math.ceil(total / rows);
    	if (page > 3) {
    		start = page - 2;
    	}
    	var end = start + 4 > totalPage ? totalPage : start + 4;
    	ret += '<div class="clearfix">';
    	ret += '<ul class="pagination no-margin">';
    	
    	if (page > 1) {
    		ret += '<li><a href="javascript:void(0);" type="pagination" page="before">上一页</a></li>';
    	}
    	
    	for (var i = start; i <= end; i++) {
    		if (i == page) {
    			ret += '<li class="active"><a href="javascript:void(0);" type="pagination" page="'+i+'">'+i+'</a></li>';
    		} else {
    			ret += '<li><a href="javascript:void(0);" type="pagination" page="'+i+'">'+i+'</a></li>';
    		}
    	}
    	
    	if (page < totalPage) {
    		ret += '<li><a href="javascript:void(0);" type="pagination" page="next">下一页</a></li>';
    	}
    	
    	ret += '</ul>';
    	ret += '<div class="extra">';
    	ret += '<span>共'+totalPage+'页，</span> <span>到第</span> <input class="num" id="paginationToPage" value="1" data-pagesize="96" name="page-num"> <span>页</span>';
    	ret += '<button class="sub" type="button" id="paginationSubmit">确定</button>';
    	ret += '</div>';
    	ret += '</div>';
    	
    	$("#" + place).html(ret);
    	
    	$('a[type="pagination"]').click(function () {
    		var pageNum = $(this).attr("page");
    		
    		if (pageNum == "before") {
    			if ((page - 1) > 0) {
    				clickFun(page - 1);
    			}
    		} else if (pageNum == "next") {
    			if ((page + 1) <= totalPage) {
    				clickFun(page + 1);
    			}
    		} else {
    			clickFun(page);
    		}
    	});
    	
    	$("#paginationSubmit").click(function () {
    		var toPage = Number($("#paginationToPage").val());
    		if (toPage > 0 && toPage <= totalPage) {
    			clickFun(toPage);
    		} else {
    			alert('页数非法');
    		}
    	});
    },
    
    numToChinese : function(num) {
    	var units = ['', '十', '百', '千'];
    	var chineseNums = ['零', '一', '二', '三', '四', '五', '六', '七', '八', '九'];
    	if (num >= 10000) {
    		return num;
    	} else if (num == 10) {
    		return '十';
    	}
    	
    	var numStr = num + "";
    	var ret = "";
    	var unitsIndex = 0;
    	
    	for (var i = numStr.length - 1; i >= 0; i--) {
    		var n = Number(numStr[i]);
    		if (n != 0) {
    			ret = chineseNums[n] + units[unitsIndex] + ret;
    		} else {
    			ret = chineseNums[n] + ret;
    		}
    		
    		unitsIndex++;
    	}
    	
    	ret = ret.replace(/零+/, '零');
    	
    	ret = ret.replace(/零+$/, '');
    	
    	return ret;
    },
    
    numToEnglish : function(num) {
    	
    	var englishNums = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
    	
    	if (num > 26) {
    		return num;
    	} 
    	
    	return englishNums[num - 1];
    }
    
};

$(window).resize(function() {  
	
});  
