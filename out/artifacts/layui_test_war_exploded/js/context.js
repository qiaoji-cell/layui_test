layui.define(function (exports) {
    let obj={
        projectPath:function () {
            let path = window.location.pathname;
            let slash = path.lastIndexOf("/");
            path = path.substring(0,slash);
            return path
        }
    }
    exports('context',obj);
})

// layui.define(function(exports){ //提示：模块也可以依赖其它模块，如：layui.define('mod1', callback);
//     var obj = {
//         hello: function(str){
//             alert('Hello '+ (str||'mymod'));
//         }
//     };
//
//     //输出 mymod 接口
//     exports('mymod', obj);
// });
