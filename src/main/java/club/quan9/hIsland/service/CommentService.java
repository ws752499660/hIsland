package club.quan9.hIsland.service;

import club.quan9.hIsland.domain.entity.Comment;
import club.quan9.hIsland.domain.entity.Topic;
import club.quan9.hIsland.domain.entity.User;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface CommentService
{
    public List<Comment> getCommentsByTopicId(String topicId);
    public JSONObject addComment(Comment comment);
    public Comment initComment(Topic topic, User user,String content);
    public JSONObject delComment(User user,Comment comment);
    public Comment getCommentById(String id);
}