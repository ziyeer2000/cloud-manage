var selectTree = {

	/**
	 * 初始化复选控件
	 * @param inputId
	 * 						input Id
	 * @param checkFn
	 * 						处理选择数据函数 (传入id数组, name数组)
	 */
	initMenuCheck : function(inputId, checkFn) {
		selectTree.init(inputId, 1, 'menu/ztree/find/check', checkFn, null, null, null, null);
	},

	/**
	 * 初始化单选控件
	 * @param inputId
	 * 						input Id
	 * @param checkFn
	 * 						处理选择数据函数 (传入id数组, name数组)
	 */
	initMenuRadio : function(inputId, checkFn) {
		selectTree.init(inputId, 0, "menu/ztree/find/radio", checkFn, null, null, null, null);
	},

	/**
	 * 初始化控件
	 * @param inputId
	 * 						input Id
	 * @param type
	 * 						类型 (1:复选 0:单选)
	 * @param url
	 * 						数据访问链接
	 * @param checkFn
	 * 						处理选择数据函数 (传入id数组, name数组)
	 * @param initFun
	 * 						加载完成处理函数
	 * @param pidName
	 * 						pid名称
	 * @param relation
	 * 						checkbox/radio是否关联父节点/子节点
	 * @param dataFilter
	 * 						数据预处理函数
	 */
	init : function(inputId, type, url, checkFn, initFun, pidName, relation, dataFilter) {
		var divId = inputId + "_Div";
		var treeId = inputId + "_selectTree";
		var okId = inputId + "_ok";
		var cleanId = inputId + "_clean";
		var cancelId = inputId + "_cancel";
		
		relation = relation == null ? false : relation;

		var pidName_ = "pid";
		if (pidName) {
			pidName_ = pidName;
		}
		
		var chkboxType = { "Y": "ps", "N": "ps" };
		if (!relation) {
			chkboxType = { "Y": "", "N": "" };
		}
		
		if ($("#" + divId).length > 0) {
			$("#" + divId).show();
			return;
		}
		
		$("#" + inputId).attr("selectTree", "true");

		var _html = '<div selectTree="true" id="'
				+ divId
				+ '" style="z-index:999; zTreeDemoBackground left;position:absolute;border-style:solid;border-width:1px;background-color: white;overflow: auto; max-height: 200px;";><ul id="'
				+ treeId + '" class="ztree"></ul>';
		_html += '<input selectTree="true" type="button" value="确定" id="' + okId + '">';
		_html += '<input selectTree="true" type="button" value="清空" id="' + cleanId + '">';
		_html += '<input selectTree="true" type="button" value="取消" id="' + cancelId + '">';
		_html += '</div>';

		$('body').append(_html);
		
		var radioType = type == 1 ? "level" : "all";
		var chkStyle = type == 1 ? "checkbox" : "radio";
		var setting = {
			async : {
				enable : true,
				url : config.baseURL + url,
				autoParam : [ "id=" + pidName_ ],
				type : "get",
				dataFilter : function(treeId, parentNode, childNodes) {
					if(dataFilter != null) {
						childNodes = dataFilter(childNodes);
					} else {
						childNodes =  childNodes.list;
					}
					
					return selectTree.dataFilter(treeId, parentNode, childNodes);
				}
			},
			check : {
				enable : true,
				radioType : radioType,
				chkStyle : chkStyle,
				chkboxType : chkboxType
			},
			view : {
				dblClickExpand : false,
				selectedMulti : true
			},
			callback : {
				onClick : selectTree.zTreeOnClick,
				onAsyncSuccess : initFun
			}
		};

		//初始化菜单树
		$.fn.zTree.init($("#" + treeId), setting);

		//确定位置
		var obj = $("#" + inputId);
		$("#" + divId).css({
			"top" : (obj.offset().top + obj.outerHeight()) + "px"
		});
		$("#" + divId).css({
			"left" : obj.offset().left
		});
		$("#" + divId).css({
			"min-width" : 140 > obj.outerWidth() - 2 ? 140 : obj.outerWidth() - 2
		});

		//确定按钮处理
		$("#" + okId).click(function() {
			var ids = [];
			var names = [];
			var levels = [];
			$.each($.fn.zTree.getZTreeObj(treeId).getCheckedNodes(true), function(n, val) {
				if (!relation || !this.isParent) {
					ids.push(this.id);
					names.push(this.name);
					levels.push(this.level);
				}
			});
			checkFn(ids, names, levels);
			$("#" + divId).hide();
		});

		//取消按钮处理
		$("#" + cancelId).click(function() {
			$("#" + divId).hide();
		});
		
		//清空按钮
		$("#" + cleanId).click(function() {
			checkFn([], [],[]);
			$("#" + divId).hide();
		});
	},

	dataFilter : function(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for ( var i = 0, l = childNodes.length; i < l; i++) {
			if (childNodes[i].name) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
		}
		return childNodes;
	},

	zTreeOnClick : function(event, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		if (treeNode.isParent) {
			zTree.expandNode(treeNode);
		} else {
			zTree.checkNode(treeNode);
		}
	},
	
	/**
	 * 公用初始化函数
	 * @param ids 
	 * 		被选ID列表
	 * @param event 
	 * 		onAsyncSuccess传入数据
	 * @param treeId 
	 * 		onAsyncSuccess传入数据
	 * @param treeNode 
	 * 		onAsyncSuccess传入数据
	 * @param msg 
	 * 		onAsyncSuccess传入数据
	 * 
	 */
	commonInitFun : function(ids, event, treeId, treeNode, msg) {
		
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		$.each(ids, function(index, value) {
			var node = treeObj.getNodesByParam("id", value, treeNode);
			treeObj.checkNode(node[0], true, true);
	    });
		
	}
		
};