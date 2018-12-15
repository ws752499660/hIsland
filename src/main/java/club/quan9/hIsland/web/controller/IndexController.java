package club.quan9.hIsland.web.controller;

import club.quan9.hIsland.domain.entity.Comment;
import club.quan9.hIsland.domain.entity.Topic;
import club.quan9.hIsland.domain.entity.User;
import club.quan9.hIsland.repository.test;
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
import java.util.UUID;

@Controller
public class IndexController
{
    @Autowired
    test test;
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
        return commentService.getCommentsByTopicId(receive.getInteger("topicId"));
    }

    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    @ResponseBody
    public String userRegister(@RequestBody JSONObject receive)
    {
        User user=new User();
        user.setId(UUID.randomUUID().toString().replace(" ","-"));
        user.setName(receive.getString("name"));
        user.setSex(receive.getString("sex").toCharArray()[0]);
        user.setHobby(receive.getString("hobby"));
        user.setPhone(receive.getLong("phone"));
        user.setGroup(receive.getString("group"));
        user.setStates(receive.getString("states"));
        userService.register(user);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("flag","true");
        jsonObject.put("userId",user.getId());
        return jsonObject.toString();
    }

    @RequestMapping(value = "/addTopic",method = RequestMethod.POST)
    @ResponseBody
    public String addTopic(@RequestBody JSONObject receive)
    {
        User user=userService.getUserById(receive.getString("authorId"));
        Topic topic=topicService.initTopic(user,receive.getString("title"),receive.getString("content"));
        topicService.addTopic(topic);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("flag","true");
        jsonObject.put("TopicId",topic.getId());
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/addComment",method = RequestMethod.POST)
    @ResponseBody
    public String addComment(@RequestBody JSONObject receive)
    {
        User user=userService.getUserById(receive.getString("authorId"));
        Topic topic=topicService.getTopicById(receive.getString("topicId"));
        Comment comment=commentService.initComment(topic,user,receive.getString("content"));
        commentService.addComment(comment);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("flag","true");
        jsonObject.put("commentId",comment.getId());
        return jsonObject.toString();
    }
}
