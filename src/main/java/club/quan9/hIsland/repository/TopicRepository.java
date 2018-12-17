package club.quan9.hIsland.repository;

import club.quan9.hIsland.domain.entity.Topic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TopicRepository
{
    @Select("select topic.*,user.name as authorName from topic,user "+
            "where user.id=topic.authorId " +
            "order by date")
    public List<Topic> getAllTopic();

    @Insert("insert into topic\n"+
            "        values (#{id},#{title},#{authorId},#{date},#{content});")
    public void addTopic(Topic topic);

    @Select("select * from topic where id=#{id};")
    public Topic getTopicById(String id);

    @Delete("delete from topic where id=#{id};")
    public void delTopic(Topic topic);
}
