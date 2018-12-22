package club.quan9.hIsland.service.impl;

import club.quan9.hIsland.domain.entity.Comment;
import club.quan9.hIsland.domain.entity.Topic;
import club.quan9.hIsland.domain.entity.User;
import club.quan9.hIsland.repository.CommentRepository;
import club.quan9.hIsland.service.CommentService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService
{
    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsByTopicId(String topicId)
    {
        return commentRepository.getCommentsByTopicId(topicId);
    }

    @Override
    public JSONObject addComment(Comment comment)
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("flag",true);
        commentRepository.addComment(comment);
        jsonObject.put("commentId",comment.getId());
        return jsonObject;
    }

    @Override
    public Comment initComment(Topic topic, User user, String content)
    {
        Comment comment=new Comment();
        comment.setId(UUID.randomUUID().toString().replace(" ","-"));
        comment.setAuthorId(user.getId());
        comment.setTopicId(topic.getId());
        comment.setDate(new Date());
        comment.setContent(content);
        comment.setAuthorName(user.getName());
        return comment;
    }

    @Override
    public JSONObject delComment(User user, Comment comment)
    {
        JSONObject jsonObject=new JSONObject();
        if(user.getGroup().equals("N"))
        {
            if(comment.getAuthorId().equals(user.getId()))
            {
                commentRepository.delComment(comment);
                jsonObject.put("flag",true);
                jsonObject.put("reason","删除自己的评论");
                return jsonObject;
            }
            else
            {
                jsonObject.put("flag",false);
                jsonObject.put("reason","普通用户不能删除别人的评论");
                return jsonObject;
            }
        }
        else
        {
            commentRepository.delComment(comment);
            jsonObject.put("flag",true);
            jsonObject.put("reason","管理员删除评论");
            return jsonObject;
        }
    }

    @Override
    public Comment getCommentById(String id)
    {
        return commentRepository.getCommentById(id);
    }
}
