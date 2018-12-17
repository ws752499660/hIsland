package club.quan9.hIsland.service.impl;

import club.quan9.hIsland.domain.entity.Topic;
import club.quan9.hIsland.domain.entity.User;
import club.quan9.hIsland.repository.TopicRepository;
import club.quan9.hIsland.service.TopicService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TopicServiceImpl implements TopicService
{
    @Autowired
    TopicRepository topicRepository;

    @Override
    public List<Topic> getAllTopic()
    {
        return topicRepository.getAllTopic();
    }

    @Override
    public JSONObject addTopic(User user,Topic topic)
    {
        JSONObject jsonObject=new JSONObject();
        if(user.getStates().equals("N"))
        {
            topicRepository.addTopic(topic);
            jsonObject.put("flag",true);
            jsonObject.put("topicId",topic.getId());
        }
        else
        {
            jsonObject.put("flag",false);
            jsonObject.put("reason","用户被封禁");
        }
        return jsonObject;
    }

    @Override
    public Topic initTopic(User user, String title, String content)
    {
        Topic topic=new Topic();
        topic.setId(UUID.randomUUID().toString().replace(" ","-"));
        topic.setTitle(title);
        topic.setAuthorId(user.getId());
        topic.setContent(content);
        topic.setDate(new Date());
        topic.setAuthorName(user.getName());
        return topic;
    }

    @Override
    public Topic getTopicById(String id)
    {
        return topicRepository.getTopicById(id);
    }

    @Override
    public JSONObject delTopic(User user, Topic topic)
    {
        JSONObject jsonObject=new JSONObject();
        if(user.getGroup().equals("N"))
        {
            if(user.getId().equals(topic.getAuthorId()))
            {
                topicRepository.delTopic(topic);
                jsonObject.put("flag",true);
                jsonObject.put("reason","删除自己的主题");
            }
            else
            {
                jsonObject.put("flag",false);
                jsonObject.put("reason","普通用户不能删除别人的主题");
            }
        }
        else
        {
            topicRepository.delTopic(topic);
            jsonObject.put("flag",true);
            jsonObject.put("reason","管理员删除主题");
        }
        return jsonObject;
    }
}
