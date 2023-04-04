# 管理项目模板

### 模块功能
- web: 后台管理
- storeage: 上传文件存储
- cloud-manage.sql: DB初始化文件 (库名:cloud-manage)

### 独立storeage的意义
web项目受功能影响需要经常发布,如果上传文件与web项目一起会导致发布前需要备份上传文件,发布完成后再还原,考虑到误操作的可能性,尽量不对上传文件进行操作,故独立一个storage项目

### 组件
- jdk 8
- tomcat 8
- spring 4.3.3.RELEASE
- Mybatis 3.2.4
- 通用mapper 1.0.4
- memcache (使用server session 时可以不用)

### 前端
- layui 2.4.5
- ckeditor 4

### 关闭memcache,改用server session
1. cloud-memcache.properties: cloud.cache.default.use = false
2. cloud-session.properties : cloud.session.type = server

### layui datagrid 工具
DataGrid插件
1.初始化 
```
layutils.datagrid.init({
	id:"dataGrid", //datagrid插入Id
	url : config.baseURL + 'menu/findPage.json', //数据获取地址
	height: 100,高度(选填)
	page: true, 分页(选填,默认true)
	cellMinWidth: 60, 最小列宽(默认60) 
	where: where, 请求参数(选填)
	role: true, 操作权限控制(true:根据menuId对应的button控制操作按钮展示. false: 根据operator参数控制操作按钮展示),
	multi: true, //是否多选(选填,默认单选)
	cols:[[ //列,具体参考layui table cols
		{field: 'id', title: 'ID', hide: true, fixed: 'left'},
	    {field: 'name', title: '菜单名称', width: 70},
	    {field: 'hasChild', title: '是否有子菜单', templet: function(data){
		    	var value = data.hasChild;
		    	if (value == '1') {
				return "是";
			} else {
				return "否";
			}
	    }} ,
      	{field: 'comments', title: '备注'}
	]],
	operatorWidth: 160, //操作列宽(选填)
	operator:{ //操作(选填)
		edit : { //事件名,对应button.name
			name: '修改', //按钮名称(选填,若role=false则必填)
			type: 1, //类型(1.数据级 2.全局. role=false必填, 其他不填)
			op: function(data) { //点击回调(data为该行数据)
				alert("编辑:" + data.id);
			},
			btnClass: 'layui-btn-danger layui-btn-xs' //按钮样式
		},
		add : {
			name: '增加',
			type: 2,
			op : function(data) { //点击回调(全局回调data参数为选中行数据数组)
				var arr = [];
				$.each(data, function(index, value) {
				    arr.push(value.id);
			    });
				alert(arr);
			}
		}
	},
	done: function() { //表格渲染完成
		alert('表格加载完成');
	},
	rowClick: function(data, checked) { //行单击, data:行数据 checked:选中状态
	
	}
});
```

2.搜索 
```
layutils.datagrid.search(
	'dataGrid',  //dataGridId
	{//查询条件JSON
		name: $("#name").val(), 
        url: $("#url").val(), 
		hasChild: $("#hasChild").val(), 
        superId: $("#superId").val() 
	}
);
```
	
3.刷新当前页数据 
```
layutils.datagrid.reload(
	'dataGrid'	 //dataGridId
);
```

4.获取行数据
```
layutils.datagrid.getRowDataByTr(
	'dataGrid',	 //dataGridId
	tr		 //行DOM
);
```

5.获取选中数据
```
//单选
layutils.datagrid.getSelect(
	'dataGrid',	 //dataGridId
);

//多选
layutils.datagrid.getSelects(
	'dataGrid',	 //dataGridId
);
```

6.选择
```
//全选
layutils.datagrid.setAllRowCheck(
	'dataGrid',	 //dataGridId
);

//条件选择
layutils.datagrid.selectRow(
	'dataGrid',	 //dataGridId
	'id', 		 //唯一键名称
	'118'		 //唯一键值
);
```

6.取消选择
```
//全部取消
layutils.datagrid.removeAllRowCheck(
	'dataGrid',	 //dataGridId
);

//条件取消
layutils.datagrid.unselectRow(
	'dataGrid',	 //dataGridId
	'id', 		 //唯一键名称
	'118'		 //唯一键值
);
```

### End