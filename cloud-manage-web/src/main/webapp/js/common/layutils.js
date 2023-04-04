var layutils = {
	
	message : function(message) {
		layer.msg(message);
	},
	
	messageTop : function(message) {
		top.layer.msg(message);
	},
	
	error : function(message) {
		layer.msg(message, function() {});
	},
	
	alert : function(message, fun) {
		
		layer.alert(message, function(index){
			if (fun) {
				fun();
			}
		  	layer.close(index);
		});
	},
	
	window : function(width, height, title, url, done) {
		layer.open({
		    type: 2,
		  	area: [width, height],
		  	fixed: false, 
		  	maxmin: true,
		  	title: title,
		  	content: url,
		  	success : function(layero, index) {
		  		if (done) {
		  			done(index);
		  		}
		  	}
		});
	},
	
	windowMax : function(title, url, done, customLayer) {
		
		if (!customLayer) {
			customLayer = layer;
		}
		
		var index = customLayer.open({
		    type: 2,
		  	fixed: true, 
		  	title: title,
		  	content: url,
		  	resize: false,
		  	move: false,
		  	success : function(layero, index) {
		  		if (done) {
		  			done(index);
		  		}
		  	}
		});
		
		customLayer.full(index);
	},
	
	closeWindow : function() {
		parent.layer.close(parent.layer.getFrameIndex(window.name));
	},
	
	confirm : function(message, yesFun, noFun) {
		layer.confirm(message, function(index) {
			if (yesFun) {
				yesFun();
			}
			layer.close(index);
		}, function(index) {
			if (noFun) {
				noFun();
			}
			layer.close(index);
		});
	},
	
	initDatePicker : function(laydate, id, type) {
		
		var format;
    	
    	if (type === 'year') {
    		format = 'yyyy';
    	} else if (type === 'date') {
    		format = 'yyyy-MM-dd';
    	} else if (type === 'time') {
    		format = 'HH:mm:ss';
    	} else {
    		type = 'datetime';
    		format = 'yyyy-MM-dd HH:mm:ss';
    	}
    	
    	var startTime=laydate.render({
		    elem: '#' + id,
		    type: type,
		    format: format
		});
	},
	
	initDatePickerRange : function(laydate, startId, endId, type) {
    	var format;
    	
    	if (type === 'year') {
    		format = 'yyyy';
    	} else if (type === 'date') {
    		format = 'yyyy-MM-dd';
    	} else if (type === 'time') {
    		format = 'HH:mm:ss';
    	} else {
    		type = 'datetime';
    		format = 'yyyy-MM-dd HH:mm:ss';
    	}
    	
    	var startTime=laydate.render({
		    elem: '#' + startId,
		    type: type,
		    format: format,
		    done:function(value,date){
	    	    endTime.config.min={    	    		
	    	    	year:date.year,
	    	    	month:date.month-1,
	                date:date.date,
	                hours:date.hours,
	                minutes:date.minutes,
	                seconds:date.seconds
	    	    };
		    }
		});
		
		var endTime=laydate.render({
		    elem: '#' + endId,
		    type: type,
		    format: format,
		    done:function(value,date){
		    	startTime.config.max={    	    		
	    	    	year:date.year,
	    	    	month:date.month-1,
	                date:date.date,
	                hours:date.hours,
	                minutes:date.minutes,
	                seconds:date.seconds
	    	    };
		    }
		});
    },
    
    /**
     * 初始化Form Select选中
     * @param formSelects
     * 		formSelects组件
     * @param url
     * 		URL
     * @param place
     * 		插入位置(Jquery对象)
     * @param filter
     * 		formSelects filter(xm-select)
     * @param data
     * 		post参数
     * @param resultName
     * 		返回中表示结果的字段名
     * @param valueName
     * 		结果中value字段名,默认为id
     * @param textName
     * 		结果中text字段名,默认为name
     * @param done
     * 		完成后执行函数
     */
    initFormSelectAll : function(initData) {
    	
    	var formSelects = initData.formSelects;
    	var place = initData.place;
    	var filter = initData.filter;
    	var url = initData.url;
    	var data = initData.data;
    	var resultName = initData.resultName;
    	var valueName = initData.valueName;
    	var textName = initData.textName;
    	var done = initData.done;
    	
    	
    	if (!valueName) {
    		valueName = "id";
    	}
    	
    	if (!textName) {
    		textName = "name";
    	}
    	
    	$.ajax({
	        type: "POST",
	        url: url,
	        data: data,
	        dataType: "json",
	        async:true,
	        success: function(data){
	        	var _html = '';
	        	var initValue = [];
	        	var ret = data[resultName];
	        	if (Array.isArray(ret)) {
	        		$.each(ret, function(index, value) {
	        			_html += "<option value='"+value[valueName]+"'>"+value[textName]+"</option>";
	        			initValue.push(value[valueName]);
	        	    });
	        		
	        	} else {
	        		_html += "<option value='"+ret[valueName]+"'>"+ret[textName]+"</option>";
	        		initValue.push(ret[valueName]);
	        	}
	        	
	        	place.append(_html);
	        	
	        	formSelects.render(filter, {
	        		init: initValue
	        	});
	        	
	        	if (done) {
	        		done();
	        	}
	        }
	    });
    },
    
    datagrid : {
    	
		init : function(config) {
			
			var id = config.id;
			var filter = id;
			var url = config.url;
			var cols = config.cols;
			var operator = config.operator;
			var operatorWidth = config.operatorWidth;
			var height = config.height;
			var page = config.page == undefined  ? true : config.page;
			var request = {};
			var where = config.where ? config.where : {};
			var needRole = config.role == undefined ? true : config.role;
			var done = config.done;
			var rowClick = config.rowClick;
			var multi = config.multi;
			var cellMinWidth = config.cellMinWidth == undefined ? 60 : config.cellMinWidth;
			
			if (page) {
				request.pageName = "page";
				request.limitName = "rows";
			}
			
			$("#"+id).attr("multi", multi).attr("lay-filter", filter);
			
			//权限
			var operatorHtml;
			var globalHtml;
			var ops;
			if (needRole) {
				ops = role.find(utils.getUrlParam("menuId"));
			} else {
				ops = [];
				for(var key in operator) {
					var obj = operator[key];
					ops.push({
						event: key,
						name: obj.name,
						type: obj.type
					});
				}
			}
			
			if (ops && ops.length > 0) {
				operatorHtml = '';
				globalHtml = '';
				
				operatorHtml += '<div>';
				globalHtml += '<div>';
				
				$.each(ops, function(index, bean) {
					var event = bean.event;
					var name = bean.name;
					var type = bean.type;
					var config = operator[event];
					var btnClassGlobal = 'layui-btn-normal layui-btn-sm';
					var btnClassRow = 'layui-btn-primary layui-btn-xs';
					
					if (config) {
						if (config.btnClass) {
							btnClassGlobal = config.btnClass;
							btnClassRow = config.btnClass;
						} 
						
						if (config.name) {
							name = config.name;
						}
					}
					
					if (type == 1) {
						operatorHtml += '<a class="layui-btn '+btnClassRow+'" data-flag="datagridOperator" data-event="'+event+'">'+name+'</a>';
					} else if (type == 2) {
						globalHtml += '<a class="layui-btn '+btnClassGlobal+'" lay-event="'+event+'">'+name+'</a>';
					}
			    });
				
				operatorHtml += '</div>';
				globalHtml += '</div>';
			}
			
			if (operatorHtml && operatorHtml !== '<div></div>') {
				cols[0].push({
				  	fixed: 'right', 
				  	title: '操作', 
				  	toolbar:operatorHtml, 
				    rowspan:cols.length,
				    width: operatorWidth
				});
			}
			
			if (multi) {
				cols[0].unshift({
					title: '<input data-flag="datagridAllCheckBox" type="checkbox" lay-filter="datagridAllCheckBox" lay-skin="primary">', 
					fixed: 'left',
					width: 60,
					templet: function(data){
				    	return '<input data-flag="datagridCheckBox" type="checkbox" lay-skin="primary">';
				    }
				});
			}
			
			//渲染
			layui.table.render({
			    elem: '#' + id,
			    id: id,
			    url: config.url,
			    method: 'post',
			    page: page,
			    height: height ? height : 'full-98',
			    request: request,
			    where: where,
			    response: {
			        statusName: 'success',
			        statusCode: true, 
			        msgName: 'message',
			        countName: 'total', 
			        dataName: 'rows'
			    },
			    cols: cols,
			    cellMinWidth: cellMinWidth,
			    toolbar: globalHtml ? globalHtml : '<div />',
			    defaultToolbar: ['filter'],
			    done : function() {
			    	
			    	//单击行事件
			    	$("#" + id).next().find("tr[data-index]").click(function() {
			    		var dataIndex = $(this).attr("data-index");
			        	var checked = true;
			        	
			        	if (layutils.datagrid.isRowChecked(id, dataIndex)) {
			        		if (multi === true) {
			        			layutils.datagrid.removeRowCheck(id, dataIndex);
			        			checked = false;
			        		}
			        	} else {
			        		layutils.datagrid.setRowCheck(id, dataIndex);
			        	}
						
						if (rowClick) {
							rowClick(layutils.datagrid.getRowData(id, dataIndex), checked);
						}
			    	});
				    
				    //全选事件
				    layui.form.on('checkbox(datagridAllCheckBox)', function(data){
				    	if (data.elem.checked) {
				    		layutils.datagrid.setAllRowCheck(id);
				    	} else {
				    		layutils.datagrid.removeAllRowCheck(id);
				    	}
				    });
				    
				    //操作
					if (operator) {
						$.each($('a[data-flag="datagridOperator"]'), function(index, value) {
							var event = $(this).attr("data-event");
						    $(this).click(function(e) {
								var config = operator[event];
								if (config) {
									var dataIndex = $(this).parents("tr:first").attr("data-index");
									config.op(layutils.datagrid.getRowData(id, dataIndex));
								}
								e.stopPropagation();
						    });
					    });
					}
				    
				    //完成事件
			    	if (done) {
			    		done();
			    	}
			    }
			});
			
			//操作
			if (operator) {
				
				layui.table.on('toolbar(' + filter + ')', function(obj){
					var checkStatus = layui.table.checkStatus(obj.config.id);
					var data = checkStatus.data;
					
					var config = operator[obj.event];
					if (config) {
						config.op(data);
					}
					
				});
			}
			
		},
		
		search : function(id, jsonCondition) {
			
			layui.table.reloadExt(id, {
				where: jsonCondition,
			  	page: {
			    	curr: 1 
			  	}
			});
		},
		
		reload : function(id) {
			
			layui.table.refresh(id);
		},
		
		getSelect : function(id) {
			var checkStatus = layui.table.checkStatus(id);
			var ret = checkStatus.data;
			if (ret && ret.length == 0) {
				return null;
			} else {
				return ret[0];
			}
		},
		
		getSelects : function(id) {
			var checkStatus = layui.table.checkStatus(id);
			var ret = checkStatus.data;
			if (ret && ret.length == 0) {
				return null;
			} else {
				return ret;
			}
		},
		
		getRowDataByTr : function(id, tr) {
			
			var index = $(tr).attr("data-index");
			var data = layui.table.cache[id];
			for (var i = 0; i < data.length; i++) {
				var bean = data[i];
				if (bean.LAY_TABLE_INDEX == index) {
					return bean;
				}
			}
			
			return null;
		},
		
		getRowData : function(id, index) {
			
			var data = layui.table.cache[id];
			for (var i = 0; i < data.length; i++) {
				var bean = data[i];
				if (bean.LAY_TABLE_INDEX == index) {
					return bean;
				}
			}
			
			return null;
		},
		
		setRowCheck : function(id, index) {
			if (!layutils.datagrid.isRowChecked(id, index)) {
				var rowDate = layutils.datagrid.getRowData(id, index);
				var tr = $("div[lay-id='"+id+"'] tr[data-index='"+index+"']");
				
				if ($("#"+id).attr("multi") === "true") {
					rowDate.LAY_CHECKED = true;
					$("div[lay-id='"+id+"'] tr[data-index='"+index+"'] input[data-flag='datagridCheckBox']").prop("checked",true);
					if (layutils.datagrid.isAllRowChecked(id)) {
						$("div[lay-id='"+id+"'] input[data-flag='datagridAllCheckBox']").prop("checked",true);
					}
					
					layui.form.render('checkbox');
				} else {
					layutils.datagrid.removeAllRowCheck(id);
					rowDate.LAY_CHECKED = true;
				}
				
				$(tr).addClass("layui-table-click");
			}
		},
		
		removeRowCheck : function(id, index) {
			if (layutils.datagrid.isRowChecked(id, index)) {
				//数据处理
				var rowDate = layutils.datagrid.getRowData(id, index);
				delete rowDate.LAY_CHECKED;
				
				//样式处理
				if ($("#"+id).attr("multi") === "true") {
					$("div[lay-id='"+id+"'] tr[data-index='"+index+"'] input[data-flag='datagridCheckBox']").removeAttr("checked");
					if (!layutils.datagrid.isAllRowChecked(id)) {
						$("div[lay-id='"+id+"'] input[data-flag='datagridAllCheckBox']").prop("checked",false);
					}
					layui.form.render('checkbox');
				}
				var tr = $("div[lay-id='"+id+"'] tr[data-index='"+index+"']");
				$(tr).removeClass("layui-table-click");
			}
		},
		
		removeAllRowCheck : function(id) {
			var data = layui.table.cache[id];
			for (var i = 0; i < data.length; i++) {
				delete data[i].LAY_CHECKED;
			}
			
			if ($("#"+id).attr("multi") === "true") {
				$("div[lay-id='"+id+"'] input[data-flag='datagridCheckBox']").prop("checked", false);
				$("div[lay-id='"+id+"'] input[data-flag='datagridAllCheckBox']").prop("checked", false);
				layui.form.render('checkbox');
			}
			
			$("div[lay-id='"+id+"'] tr").removeClass("layui-table-click");
		},
		
		setAllRowCheck : function(id) {
			
			var data = layui.table.cache[id];
			for (var i = 0; i < data.length; i++) {
				data[i].LAY_CHECKED = true;
			}
			
			$("div[lay-id='"+id+"'] input[data-flag='datagridCheckBox']").prop("checked", true);
			$("div[lay-id='"+id+"'] input[data-flag='datagridAllCheckBox']").prop("checked", true);
			layui.form.render('checkbox');
			
			$("div[lay-id='"+id+"'] tr").addClass("layui-table-click");
			
		},
		
		isRowChecked : function(id, index) {
			var rowDate = layutils.datagrid.getRowData(id, index);
			if (rowDate.LAY_CHECKED === true) {
				return true;
			} else {
				return false;
			}
		},
		
		isAllRowChecked : function(id) {
			var data = layui.table.cache[id];
			for (var i = 0; i < data.length; i++) {
				if (data[i].LAY_CHECKED !== true) {
					return false;
				}
			}
			
			return true;
		}
	}
}