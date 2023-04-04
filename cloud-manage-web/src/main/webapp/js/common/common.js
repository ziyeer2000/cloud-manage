var lastLoadingIndex;
var loginCount = 0;

$.ajaxSetup({
	contentType : "application/x-www-form-urlencoded;charset=utf-8",
	cache : false,
	beforeSend : function(xhr) {
		//ajaxLoading();
	},
	complete : function(xhr,status) {
		//ajaxLoadingEnd();
	},
	dataFilter : function(data, type) {
		var isRet=true;
		if (data && type == "json") {
			var jsonData = eval("(" + data + ")");
			if (jsonData.cloud_interceptor_check_success === false) {
				if (jsonData.cloud_interceptor_check_message) {
					alert(jsonData.cloud_interceptor_check_message);
				}
				if (jsonData.cloud_interceptor_check_url) {
					top.location.href = "/cloud-manage" + 
										(jsonData.cloud_interceptor_check_url.indexOf("/") == -1 ? "/" : "") +
										jsonData.cloud_interceptor_check_url + 
										(jsonData.cloud_interceptor_check_url.indexOf("?") == -1 ? "?" : "&") +
										"cloud_interceptor_check_message=" + jsonData.cloud_interceptor_check_message;
				}
				
				throw new InterceptorException("拦截器验证失败:" + jsonData.cloud_interceptor_check_message);
				
			}
			
			//处理标准返回失败
			if(jsonData && jsonData.success === false){
				isRet=false;
				var msg = jsonData.message;
				layutils.error(msg ? msg : "处理失败");
			}else {
				isRet=true;
			}
		}
		
		if(isRet){
			return data;
		} else {
			throw new BusinessException("业务操作失败:" + jsonData.message);
		}
	},
	error: function (XMLHttpRequest, textStatus, errorThrown) {
		if (!(errorThrown instanceof InterceptorException) && !(errorThrown instanceof BusinessException)) {
			layutils.error("操作失败,请联系管理员");
		}
		
		if (lastLoadingIndex) {
			ajaxLoadingEnd(lastLoadingIndex);
		}
	}
});

function InterceptorException() {}  
InterceptorException.prototype = new Error();  
InterceptorException.prototype.constructor = InterceptorException;  
InterceptorException.prototype.toString = function () {  
    return this.message;  
};  

function BusinessException() {}  
BusinessException.prototype = new Error();  
BusinessException.prototype.constructor = BusinessException;  
BusinessException.prototype.toString = function () {  
    return this.message;  
};  

function ajaxLoading(){   
	loginCount++;
	
	if (lastLoadingIndex == undefined) {
		var index = layer.load(1, {
			shade: [0.3,'#000'] 
		}); 
		lastLoadingIndex = index;
	}
	
	return lastLoadingIndex;
} 

function ajaxLoadingEnd(){ 
	loginCount--;
	
	if (loginCount <= 0) {
		layer.close(lastLoadingIndex);
		lastLoadingIndex = undefined;
	}
}