package club.quan9.hIsland.service.impl;

import club.quan9.hIsland.domain.entity.User;
import club.quan9.hIsland.repository.UserRepository;
import club.quan9.hIsland.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    SqlSession sqlSession;
    private UserRepository userRepository;

    @Autowired
    private void getUserRepository()
    {
        this.userRepository=sqlSession.getMapper(UserRepository.class);
    }


    public User getUserById(String id)
    {
        getUserRepository();
        return userRepository.getUserById(id);
    }

    @Override
    public JSONObject register(JSONObject receive)
    {
        getUserRepository();
        User user=new User();
        user.setId(UUID.randomUUID().toString().replace(" ","-"));
        user.setName(receive.getString("name"));
        user.setSex(receive.getString("sex").toCharArray()[0]);
        user.setHobby(receive.getString("hobby"));
        user.setPhone(receive.getLong("phone"));
        user.setGroup(receive.getString("group"));
        user.setStates(receive.getString("states"));
        userRepository.addUser(user);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("flag","true");
        jsonObject.put("userId",user.getId());
        return jsonObject;
    }
}
