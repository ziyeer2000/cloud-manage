<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DEMO系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">

<link rel="stylesheet" type="text/css" href="/cloud-manage/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="/cloud-manage/css/login.css">

</head>
<body>
	<div class="login-main">
	    <div class="header">登录</div>
	    <p class="title">DEMO系统</p>
	    
	    <div style="display: none;" id="errorMessage">${cloud_interceptor_check_message }</div>
	    <form class="layui-form">
	        <div class="layui-input-inline">
	            <input type="text" name="userName" lay-verify="required" placeholder="用户名" autocomplete="off" class="layui-input">
	        </div>
	        <div class="layui-input-inline">
	            <input type="password" name="password" lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input">
	        </div>
	        <div class="layui-input-inline login-btn">
	            <button lay-submit lay-filter="login" class="layui-btn">登录</button>
	        </div>
	        <hr/>
	        <!-- <p><a href="register.html" class="fl">立即注册</a><a href="javascript:;" class="fr">忘记密码？</a></p> -->
	    </form>
	</div>	
 
	<script type="text/javascript" src="/cloud-manage/js/jquery/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="/cloud-manage/layui/layui.js"></script>
	<script type="text/javascript" src="/cloud-manage/js/common/config.js"></script>
	<script type="text/javascript" src="/cloud-manage/js/common/common.js"></script>
	<script type="text/javascript" src="/cloud-manage/js/common/layutils.js"></script>
	<script type="text/javascript">
	
	if (window.top != window.self) {
		var message = $('#loginError').html();
		if (message) {
			layutils.alert(message, function() {
				window.top.location= config.baseURL + "/login";
			});
		} else {
			window.top.location= config.baseURL + "/login";
		}
	}
	
    layui.use(['form','layer'], function () {
        var form = layui.form;
        form.on('submit(login)',function (data) {
        	ajaxLoading();
        	
            $.ajax({
                url: config.baseURL + 'user/login',
                data: data.field,
                dataType: 'json',
                type: 'post',
                success:function (data) {
                	ajaxLoadingEnd();
                    if(data.result){ 
                        top.location.href = data.redirectURL+"?t="+new Date().getTime();
                    }else{
                    	layutils.error('用户名或密码错误');
                    }
                }
            })
            return false;
        })
    });
	</script>
</body>
</html>