package club.quan9.hIsland.service.impl;

import club.quan9.hIsland.domain.entity.Comment;
import club.quan9.hIsland.domain.entity.Topic;
import club.quan9.hIsland.domain.entity.User;
import club.quan9.hIsland.repository.CommentRepository;
import club.quan9.hIsland.service.CommentService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService
{
    @Autowired
    SqlSession sqlSession;
    private CommentRepository commentRepository;

    private void getCommentRepository()
    {
        this.commentRepository=sqlSession.getMapper(CommentRepository.class);
    }

    @Override
    public List<Comment> getCommentsByTopicId(int topicId)
    {
        getCommentRepository();
        return commentRepository.getCommentsByTopicId(topicId);
    }

    @Override
    public void addComment(Comment comment)
    {
        getCommentRepository();
        commentRepository.addComment(comment);
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
}
