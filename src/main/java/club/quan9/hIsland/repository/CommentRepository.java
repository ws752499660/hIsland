package club.quan9.hIsland.repository;

import club.quan9.hIsland.domain.entity.Comment;

import java.util.List;

public interface CommentRepository
{
    public List<Comment> getCommentsByTopicId(int topicId);
    public void addComment(Comment comment);
}