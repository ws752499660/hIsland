package club.quan9.hIsland.service;

import club.quan9.hIsland.domain.entity.Topic;
import club.quan9.hIsland.domain.entity.User;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface TopicService
{
    public List<Topic> getAllTopic();
    public JSONObject addTopic(User user,Topic topic);
    public Topic initTopic(User user,String title,String content);
    public Topic getTopicById(String id);
    public JSONObject delTopic(User user,Topic topic);
}
