package club.quan9.hIsland.service.impl;

import club.quan9.hIsland.domain.entity.Topic;
import club.quan9.hIsland.domain.entity.User;
import club.quan9.hIsland.repository.TopicRepository;
import club.quan9.hIsland.service.TopicService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TopicServiceImpl implements TopicService
{
    @Autowired
    SqlSession sqlSession;
    private TopicRepository topicRepository;

    private void getTopicRepository()
    {
        this.topicRepository=sqlSession.getMapper(TopicRepository.class);
    }

    @Override
    public List<Topic> getAllTopic()
    {
        getTopicRepository();
        return topicRepository.getAllTopic();
    }

    @Override
    public void addTopic(Topic topic)
    {
        getTopicRepository();
        topicRepository.addTopic(topic);
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
        getTopicRepository();
        return topicRepository.getTopicById(id);
    }
}
