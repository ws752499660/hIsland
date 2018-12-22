package club.quan9.hIsland.service.impl;

import club.quan9.hIsland.domain.entity.User;
import club.quan9.hIsland.repository.UserRepository;
import club.quan9.hIsland.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    UserRepository userRepository;


    public User getUserById(String id)
    {
        return userRepository.getUserById(id);
    }

    public Boolean checkUserId(String id)
    {
        if(userRepository.getUserIdById(id) == null)
            return true;
        else
            return false;
    }

    @Override
    public JSONObject register(JSONObject receive)
    {
        JSONObject jsonObject=new JSONObject();
        User user=new User();
        String id=receive.getString("id");
        if(checkUserId(id))
        {
            user.setId(id);
            user.setName(receive.getString("name"));
            user.setSex(receive.getString("sex").toCharArray()[0]);
            user.setHobby(receive.getString("hobby"));
            user.setPhone(receive.getLong("phone"));
            user.setGroup(receive.getString("group"));
            user.setStates(receive.getString("states"));
            userRepository.addUser(user);
            jsonObject.put("flag",true);
            jsonObject.put("userId",user.getId());
        }
        else
        {
            jsonObject.put("flag", false);
            jsonObject.put("reason", "该ID已经有人注册");
        }
        return jsonObject;
    }

    @Override
    public JSONObject banUser(User optUser, User tarUser)
    {
        JSONObject jsonObject=new JSONObject();
        if(optUser.getGroup().equals("S"))
        {
            userRepository.updateColumnById("states","B",tarUser);
            jsonObject.put("flag",true);
        }
        else
        {
            jsonObject.put("flag",false);
            jsonObject.put("reason","权限不够或其他错误");
        }
        return jsonObject;
    }

    @Override
    public JSONObject changeUserProfile(User originalUser, User tarUser)
    {
        boolean flag=true;
        JSONObject jsonObject=new JSONObject();
        JSONObject oUserJson=JSONObject.parseObject(JSONObject.toJSONString(originalUser));
        JSONObject tUserJson=JSONObject.parseObject(JSONObject.toJSONString(tarUser));
        for (Map.Entry<String, Object> entry : oUserJson.entrySet())
        {
            String key=entry.getKey();
            if( (!(entry.getValue().toString().equals(tUserJson.getString(key)))) && (!key.equals("id")) )
            {
                userRepository.updateColumnById(key,tUserJson.getString(key),originalUser);
            }
        }
        jsonObject.put("flag",flag);
        return jsonObject;
    }

    @Override
    public JSONObject login(JSONObject receive)
    {
        JSONObject jsonObject=new JSONObject();
        String id=receive.getString("id");
        String passwd=receive.getString("passwd");
        if(userRepository.getPasswdById(getUserById(id)).equals(passwd))
            jsonObject.put("flag",true);
        else
            jsonObject.put("flag",false);
        return jsonObject;
    }
}
