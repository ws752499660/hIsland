new Vue({
    el: '#app',
    data: {
        visible: false,
        loginStatus:false,
        input:'',
    },
    method:{
        visitorGoindex:function(){
            window.location.href='./index.html'
        },
        register:function () {
            var xhr=new XMLHttpRequest();
            var url="http://dev.quan9.eu.org/userRegister";
            xhr.open("post",url,false);
            xhr.setRequestHeader("content-type","application/json");
            var text=JSON.stringify(this.user);
            xhr.send(text);
            console.log("用户注册-发送数据：",text);
            recive=JSON.parse(xhr.response);
            console.log("用户注册-接受数据：",recive);
    }
        }
  })