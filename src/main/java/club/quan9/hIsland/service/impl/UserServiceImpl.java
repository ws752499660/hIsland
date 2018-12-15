package club.quan9.hIsland.service.impl;

import club.quan9.hIsland.domain.entity.User;
import club.quan9.hIsland.repository.UserRepository;
import club.quan9.hIsland.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void register(User user)
    {
        getUserRepository();
        userRepository.addUser(user);
    }
}
