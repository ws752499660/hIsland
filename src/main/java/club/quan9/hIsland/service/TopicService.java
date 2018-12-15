package club.quan9.hIsland.service;

import club.quan9.hIsland.domain.entity.Topic;
import club.quan9.hIsland.domain.entity.User;

import java.util.List;

public interface TopicService
{
    public List<Topic> getAllTopic();
    public void addTopic(Topic topic);
    public Topic initTopic(User user,String title,String content);
    public Topic getTopicById(String id);
}
