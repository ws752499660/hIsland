package club.quan9.hIsland.service;

import club.quan9.hIsland.domain.entity.Comment;
import club.quan9.hIsland.domain.entity.Topic;
import club.quan9.hIsland.domain.entity.User;

import java.util.List;

public interface CommentService
{
    public List<Comment> getCommentsByTopicId(int topicId);
    public void addComment(Comment comment);
    public Comment initComment(Topic topic, User user,String content);
}