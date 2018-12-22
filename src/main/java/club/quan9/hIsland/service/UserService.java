package club.quan9.hIsland.service;

import club.quan9.hIsland.domain.entity.User;
import com.alibaba.fastjson.JSONObject;

public interface UserService
{
    public User getUserById(String id);
    public JSONObject register(JSONObject receive);
    public Boolean checkUserId(String id);
    public JSONObject banUser(User optUser,User tarUser);
    public JSONObject changeUserProfile(User originalUser,User tarUser);
    public JSONObject login(JSONObject receive);
}
