package club.quan9.hIsland.repository;

import club.quan9.hIsland.domain.entity.Topic;

import java.util.List;


public interface TopicRepository
{
    public List<Topic> getAllTopic();
    public void addTopic(Topic topic);
    public Topic getTopicById(String id);
}
