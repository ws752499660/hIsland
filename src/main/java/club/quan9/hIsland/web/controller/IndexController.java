package club.quan9.hIsland.web.controller;

import club.quan9.hIsland.domain.entity.Comment;
import club.quan9.hIsland.domain.entity.Topic;
import club.quan9.hIsland.domain.entity.User;
import club.quan9.hIsland.service.CommentService;
import club.quan9.hIsland.service.TopicService;
import club.quan9.hIsland.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController
{
    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;
    @Autowired
    CommentService commentService;

    @RequestMapping(value = {"/","index"},method = RequestMethod.GET)
    public String index()
    {
        return "index.html";
    }

    @RequestMapping(value = {"/test"},method =RequestMethod.GET)
    public String test()
    {
        return "test.html";
    }

    @RequestMapping(value = "/getTopicList",method = RequestMethod.GET)
    @ResponseBody
    public List<Topic> getAllTopic()
    {
        return topicService.getAllTopic();
    }


    @RequestMapping(value = "/getName",method = RequestMethod.POST)
    @ResponseBody
    public User getUserById(@RequestBody JSONObject receive)
    {
        User user=userService.getUserById(receive.getString("id"));
        return user;
    }

    @RequestMapping(value = "/getCommentByTopicId",method = RequestMethod.POST)
    @ResponseBody
    public List<Comment> getCommentByTopicId(@RequestBody JSONObject receive)
    {
        return commentService.getCommentsByTopicId(receive.getString("topicId"));
    }

    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    @ResponseBody
    public String userRegister(@RequestBody JSONObject receive)
    {
        JSONObject jsonObject=userService.register(receive);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody JSONObject receive)
    {
        JSONObject jsonObject=userService.login(receive);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/checkUserId",method = RequestMethod.POST)
    @ResponseBody
    public String checkUserId(@RequestBody JSONObject receive)
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("flag",userService.checkUserId(receive.getString("userId")));
        return jsonObject.toString();
    }

    @RequestMapping(value = "/addTopic",method = RequestMethod.POST)
    @ResponseBody
    public String addTopic(@RequestBody JSONObject receive)
    {
        User user=userService.getUserById(receive.getString("authorId"));
        Topic topic=topicService.initTopic(user,receive.getString("title"),receive.getString("content"));
        JSONObject jsonObject=topicService.addTopic(user,topic);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/addComment",method = RequestMethod.POST)
    @ResponseBody
    public String addComment(@RequestBody JSONObject receive)
    {
        User user=userService.getUserById(receive.getString("authorId"));
        Topic topic=topicService.getTopicById(receive.getString("topicId"));
        Comment comment=commentService.initComment(topic,user,receive.getString("content"));
        JSONObject jsonObject=commentService.addComment(comment);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/delComment",method = RequestMethod.POST)
    @ResponseBody
    public String delComment(@RequestBody JSONObject receive)
    {
        User user=userService.getUserById(receive.getString("userId"));
        Comment comment=commentService.getCommentById(receive.getString("commentId"));
        JSONObject jsonObject=commentService.delComment(user,comment);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/delTopic",method = RequestMethod.POST)
    @ResponseBody
    public String delTopic(@RequestBody JSONObject receive)
    {
        User user=userService.getUserById(receive.getString("userId"));
        Topic topic=topicService.getTopicById(receive.getString("topicId"));
        JSONObject jsonObject=topicService.delTopic(user,topic);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/banUser",method = RequestMethod.POST)
    @ResponseBody
    public String banUser(@RequestBody JSONObject receive)
    {
        User optUser=userService.getUserById(receive.getString("optUserId"));
        User tarUser=userService.getUserById(receive.getString("tarUserId"));
        JSONObject jsonObject=userService.banUser(optUser,tarUser);
        return jsonObject.toString();
    }
}
