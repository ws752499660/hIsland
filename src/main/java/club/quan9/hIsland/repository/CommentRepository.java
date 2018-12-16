package club.quan9.hIsland.repository;

import club.quan9.hIsland.domain.entity.Comment;

import java.util.List;

public interface CommentRepository
{
    public List<Comment> getCommentsByTopicId(String topicId);
    public void addComment(Comment comment);
    public void delComment(Comment comment);
    public Comment getCommentById(String id);
}