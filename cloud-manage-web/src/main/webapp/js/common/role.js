var role = {
	
	check : function(id) {
		
		//获取菜单下的按钮
		$.ajax({
	        type: "POST",
	        url: config.baseURL + "button/findMy",
	        dataType: "json",
	        async:true, 
	        data : {
	            "menuId" : utils.getUrlParam("menuId")
	        },
	        success: function(data){
	        	//删除按钮
	        	$("#" + id + " a").each(function() {
	        		var a = $(this);
        			$.each(data.list, function(index, value) {
    	        		if ($.trim(value.name) !== $.trim(a.text())) {
    	        			a.remove();
    	        		}
    	            });
        		});
	        }
	    });
	},
	
	find : function(menuId) {
		
		var ret;
		
		//获取菜单下的按钮
		$.ajax({
	        type: "POST",
	        url: config.baseURL + "button/findMy",
	        dataType: "json",
	        async:false, 
	        data : {
	            "menuId" : menuId
	        },
	        success: function(data){
	        	ret = data.list;
	        }
	    });
		
		return ret;
	}
};
