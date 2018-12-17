package club.quan9.hIsland.repository;

import club.quan9.hIsland.domain.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserRepository
{
    @Select("select * from user where id=#{id}")
    public User getUserById(String id);

    @Insert("insert into user " +
            "values (#{id},#{name},#{sex},#{hobby},#{phone},#{group},#{states})")
    public void addUser(User user);

    @Select("select id from user where id=#{id}")
    public String getUserIdById(String id);

    @Update("update user set ${column}=#{value} where id=#{user.id}")
    public void updateColumnById(@Param("column")String column,@Param("value")String value,@Param("user")User user);
}