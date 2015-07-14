// extend the 'equals' rule
$.extend($.fn.validatebox.defaults.rules, {
    equals: {
        validator: function(value,param){
            return value == $(param[0]).val();
        },  
        message: '输入不一致'
    },
    fixLength: {
    	validator: function(value, param){
    		return value.length == param[0];
    	},
    	message: '该输入项内容长度为{0}个字符'
    },
    minLength: {
        validator: function(value, param){
            return value.length >= param[0];
        },
        message: '最少要输入{0}个字符'
    },
    maxLength: {
        validator: function(value, param){
            return value.length <= param[0];
        },
        message: '最多可以输入{0}个字符'
    },
    integer: {
        validator: function(value) {
            return /^[+]?[1-9]+\d*$/i.test(value);
        },
        message : '请输入整数'
    },
    username: {
        validator: function(value) {
            return /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/i.test(value);
        },
        message: '用户名不合法（字母开头，允许5-16字节，允许字母数字下划线）'
    },
    mobile: {
        validator: function(value) {
            return /^(13|15|18)\d{9}$/i.test(value);
        },
        message: '手机号码格式不正确'
    }
});