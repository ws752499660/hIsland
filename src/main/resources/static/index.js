new Vue({
    el: '#app',
    data: {
      registerVisible:false,
      loginVisible:false,
      logoutVisible:false,
      show3v:false,
      textarea: '',
      activeNames:[],
      id:'',
      name:'',
      topicList:null,
      topicId:null,
      commentList:null,
      loginStatus:false,
      user:{
          id:'',
          name:'',
          sex:'',
          hobby:'',
          phone:'',
          group:'',
          states:''
      },

      userId:{userId:''},
      checkUserIdFlag:null,

      topicForAdd:{
          authorId:'',
          title:'',
          content:''
      },
      commentForAdd:{
          authorId:null,
          topicId:null,
          content:null
      },

      delCommentObj:{
          userId:"",
          commentId:""
      },
      delCommentFlag:null,

      delTopicObj:{
          userId:"",
          topicId:""
      },
      delTopicFlag:null,

      banUserObj:{
          optUserId:'',
          tarUserId:''
      },
      banUserFlag:null,

      getCommentObjList:[],
      getCommentObj:{
          topicId:null,
          commentList:null
      }
    },
    methods:{
        show3:function(){
          this.show3v=!this.show3v
        },
        handleCommentObj:function(){
            this.getCommentObjList=new Array(this.topicList.length);
           

            for(var i=0;i<this.topicList.length;i++){
                    this.getCommentObjList[i]={
                        topicId:this.topicList[i].id,
                        commentList:null
                    }
                    console.log("topicId",this.topicList[i].id)
            }

            console.log("getCommentObjList",this.getCommentObjList)
        },
        handleChange(val) {
            console.log("val",val)

            for(i in val){
                var index=val[i]
                var commentList=this.getCommentList(this.getCommentObjList[index].topicId)
                this.getCommentObjList[index].commentList=commentList;
            }       
        },
        getCommentList:function (topicId) {
            var xhr=new XMLHttpRequest();
            var url="http://127.0.0.1:8080/getCommentByTopicId";
            xhr.open("post",url,false);
            xhr.setRequestHeader("content-type","application/json");
            var text={
                topicId:topicId
            };
            text=JSON.stringify(text);
            xhr.send(text);
            console.log("通过topicId获取评论列表-发送数据：",text);
            recive=JSON.parse(xhr.response);
            console.log("通过topicId获取评论列表-接受数据：",recive);
            return recive;  //是一个List，里面是Comment对象（见后台Comment实体类）
        },
        register:function () {
            var xhr=new XMLHttpRequest();
            var url="http://127.0.0.1:8080/userRegister";
            xhr.open("post",url,false);
            xhr.setRequestHeader("content-type","application/json");
            var text=JSON.stringify(this.user);
            xhr.send(text);
            console.log("用户注册-发送数据：",text);
            recive=JSON.parse(xhr.response);
            console.log("用户注册-接受数据：",recive);
        },
        login:function(){
            this.loginStatus = true;
            var status = true;
            console.log("登录状态",status);
            return loginStatus;
        }
    },      
    created:function(){
        var xhr=new XMLHttpRequest();
        var url="http://127.0.0.1:8080/getTopicList";
        xhr.open("get",url,false);
        xhr.send();
        console.log("获取topic列表-发送数据：","不需要发送数据，只请求get一下就好");
        recive=JSON.parse(xhr.response);
        console.log("获取topic列表-接受数据：",recive);
        recive.forEach(function (e) {
            e.date=new Date(e.date);
            e.date=e.date.getFullYear()+"-"+e.date.getMonth()+"-"+e.date.getDate()+"  "+e.date.getHours()+":"+e.date.getMinutes();
            console.log("获取topic列表-这里对date进行格式化为Date类型后再次格式化为我们想要的格式",e.date);
        })
        this.topicList=recive; 
        this.handleCommentObj()
    }
})
