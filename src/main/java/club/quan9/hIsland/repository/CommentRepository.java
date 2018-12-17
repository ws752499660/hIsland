package club.quan9.hIsland.repository;

import club.quan9.hIsland.domain.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentRepository
{
    @Select("select comment.*,user.name as authorName "+
            "from comment,user "+
            "where comment.topicId = #{topicId} and comment.authorId=user.id "+
            "order by comment.date")
    public List<Comment> getCommentsByTopicId(String topicId);

    @Insert("insert into comment\n"+
            "        values (#{id},#{topicId},#{authorId},#{content},#{date})")
    public void addComment(Comment comment);

    @Delete("delete from comment where id=#{id}")
    public void delComment(Comment comment);

    @Select("select * from comment where id=#{id}")
    public Comment getCommentById(String id);
}