new Vue({
    el: '#app',
    data: {

      registerVisible:false,//注册弹出框
      loginVisible:false,//登录弹出框
      logoutVisible:false,//退出弹出框
      addtopicVisible:false,//发帖弹出框
      addCommentVisible:false,//评论弹出框
      banUserVisible:false,//ban人弹出框

      show3v:false,
      textarea: '',
      activeNames:[],
      id:'',
      name:'',

      topicList:null,
      topicId:null,
      commentList:null,

      loginFlag:{
          flag:false,
      },//判断用户是否登录

      loginStatus:{
        id:'',
        name:'',
        status:'',
        topicNum:'',
        delName:'',

      },

      user:{
          id:'',
          passwd:'',
          name:'',
          sex:'',
          hobby:'',
          phone:'',
          group:'',
          states:''
      },

      userId:{userId:''},
      checkUserIdFlag:null,

      topicReceive:{
        title:'',
        content:''
      },//发帖时接收帖子内容
      topicForAdd:{
          authorId:'',
          title:'',
          content:''
      },
      topicAddFlag:null,

      commentReceive:{
          content:'',
      },
      commentForAdd:{
        authorId:null,
        topicId:null,
        content:null
      },
      commentAddFlag:null,



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
      
      loginObj:{
        id:"",
        passwd:""
    },

      getCommentObjList:[],
      getCommentObj:{
          topicId:null,
          commentList:[]
      }
    },
    methods:{
        show3:function(){
          this.show3v=!this.show3v
        },

        getTopicList:function(){
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
        },

        handleCommentObj:function(){
            this.getCommentObjList=new Array(this.topicList.length);
           

            for(var i=0;i<this.topicList.length;i++){
                    this.getCommentObjList[i]={
                        topicId:this.topicList[i].id,
                        commentList:[]
                    }
                    console.log("topicId",this.topicList[i].id)
            }

            console.log("getCommentObjList",this.getCommentObjList)
        },

        handleChange(val) {
            console.log("val",val);
            if(val.length!=0){
                this.loginStatus.topicNum=this.topicList[val].id;
                this.loginStatus.delName=this.topicList[val].authorId;
            
            // for(var i=0;i<val.length;i++){
            //     var index=val[i];
            //     console.log(this.getCommentObjList[index].topicId);//测试是否能将输出当前序号前的所有topic
            //     var commentList=this.getCommentList(this.getCommentObjList[index].topicId);
            //     this.getCommentObjList[index].commentList=commentList;
            //     console.log("commentlisttest",this.getCommentObjList[index].commentList);
            // } 
                console.log(this.getCommentObjList[val].topicId);//测试是否能将输出当前序号前的所有topic
                var commentList=this.getCommentList(this.getCommentObjList[val].topicId);
                this.getCommentObjList[val].commentList=commentList;
                console.log("commentlisttest",this.getCommentObjList[val].commentList);
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
            this.user.group="N";
            this.user.states="N";
            var text=JSON.stringify(this.user);
            xhr.send(text);
            console.log("用户注册-发送数据：",text);
            recive=JSON.parse(xhr.response);
            console.log("用户注册-接受数据：",recive);
            this.registerVisible=false;
        },
        login:function(){

            var xhr=new XMLHttpRequest();
            var url="http://127.0.0.1:8080/login";
            xhr.open("post",url,false);
            xhr.setRequestHeader("content-type","application/json");
            var text=JSON.stringify(this.loginObj);
            xhr.send(text);
            console.log("登录-发送数据：",text);
            recive=JSON.parse(xhr.response);
            console.log("登录-接受数据：",recive);
            this.loginFlag=recive;
            console.log("登录状态" ,this.loginFlag.flag);
            if( this.loginFlag.flag==true){
                 this.loginStatus.id=this.loginObj.id;
                 this.id=this.loginObj.id;
                 this.getUserName();
                 this.loginStatus.name=this.name;
                 console.log("获取用户id",this.loginStatus.id);
            }
        },
        getUserName:function(){
            var xhr=new XMLHttpRequest();
            var url="http://127.0.0.1:8080/getName";
            xhr.open("post",url,false);
            xhr.setRequestHeader("content-type","application/json");
            var text={
                id:this.id
            };
            text=JSON.stringify(text);
            xhr.send(text);
            console.log("获取用户姓名-发送数据：",text);
            recive=JSON.parse(xhr.response);
            console.log("获取用户姓名-接受数据：",recive);
            this.name=recive.name;
        },

        addTopicClk:function(){
            this.addTopic();
            this.getTopicList();
            this.handleCommentObj();
            this.addtopicVisible=false;
        },

        addTopic:function () {
            
            var xhr=new XMLHttpRequest();
            var url="http://127.0.0.1:8080/addTopic";
            xhr.open("post",url,false);
            xhr.setRequestHeader("content-type","application/json");

            this.topicForAdd.authorId=this.loginStatus.id;
            this.topicForAdd.title=this.topicReceive.title;
            this.topicForAdd.content=this.topicReceive.content;
            var text=JSON.stringify(this.topicForAdd);
            xhr.send(text);
            console.log("发帖-发送数据：",text);
            recive=JSON.parse(xhr.response);
            console.log("发帖-接受数据：",recive);
            this.topicAddFlag=recive;
        },
        /**需要增加topicId */

        addCommentClk:function(){
            this.addComment();
            if(this.commentAddFlag.flag){
                for(var i in this.getCommentObjList){
                    var item=this.getCommentObjList[i];
                    if(item.topicId==this.commentForAdd.topicId)
                    {
                        var newComment={
                            authorId:this.commentForAdd.authorId,
                            authorName:this.loginStatus.name,
                            topicId:this.commentForAdd.topicId,
                            content:this.commentForAdd.content,
                            id:this.commentAddFlag.commentId
                        }
                        item.commentList.push(newComment);
                        console.log("ac",item);
                    }
                }
            }
            this.commentForAdd={
                authorId:null,
                topicId:null,
                content:null
            };
            this.commentAddFlag=null;
            this.addCommentVisible=false;
        },
        
        addComment:function () {
         
            var xhr=new XMLHttpRequest();
            var url="http://127.0.0.1:8080/addComment";
            xhr.open("post",url,false);
            xhr.setRequestHeader("content-type","application/json");

            this.commentForAdd.authorId=this.loginStatus.id;
            this.commentForAdd.topicId=this.loginStatus.topicNum;//这句需要改

            var text=JSON.stringify(this.commentForAdd);
            xhr.send(text);
            console.log("评论-发送数据：",text);
            recive=JSON.parse(xhr.response);
            console.log("评论-接受数据：",recive);
            this.commentAddFlag=recive;
        },
        delTopicClk:function(){
            this.delTopic();
            // for(var i in this.topicList){
            //     if(this.topicList[i].id==this.delTopicObj.topicId){
            //         this.topicList.splice(i);
            //     }
            // }
            this.getTopicList();
            this.handleCommentObj();
            this.activeNames=[];
        },
        /*需要增加topicID*/
        delTopic:function () {
            var xhr=new XMLHttpRequest();
            var url="http://127.0.0.1:8080/delTopic";
            xhr.open("post",url,false);
            xhr.setRequestHeader("content-type","application/json");

            this.delTopicObj.userId=this.loginStatus.id;
            this.delTopicObj.topicId=this.loginStatus.topicNum;
            var text=JSON.stringify(this.delTopicObj);
            xhr.send(text);
            console.log("删主题-发送数据：",text);
            recive=JSON.parse(xhr.response);
            console.log("删主题-接受数据：",recive);
            this.delTopicFlag=recive;
        },

        delCommentClk:function(commentId,key,index){
            console.log("comment",commentId);
            this.delCommentObj.userId=this.loginStatus.id;
            this.delCommentObj.commentId=commentId;
            this.delComment();
            var commentObj=this.getCommentObjList[key];
            console.log("dc1",commentObj);
            commentObj.commentList.splice(index);
            console.log("dc2",commentObj);
            this.activeNames=[];
        },

        delComment:function () {
            var xhr=new XMLHttpRequest();
            var url="http://127.0.0.1:8080/delComment";
            xhr.open("post",url,false);
            xhr.setRequestHeader("content-type","application/json");
            var text=JSON.stringify(this.delCommentObj);
            xhr.send(text);
            console.log("删评论-发送数据：",text);
            recive=JSON.parse(xhr.response);
            console.log("删评论-接受数据：",recive);
            this.delCommentFlag=recive;
        },
        
        banUser:function () {
            this.banUserObj.optUserId=this.loginStatus.id;
            var xhr=new XMLHttpRequest();
            var url="http://127.0.0.1:8080/banUser";
            xhr.open("post",url,false);
            xhr.setRequestHeader("content-type","application/json");
            var text=JSON.stringify(this.banUserObj);
            xhr.send(text);
            console.log("封禁用户-发送数据：",text);
            recive=JSON.parse(xhr.response);
            console.log("封禁用户-接受数据：",recive);
            this.banUserFlag=recive;
        }

    },      
    created:function(){
        this.getTopicList();
        this.handleCommentObj();
    }
})
