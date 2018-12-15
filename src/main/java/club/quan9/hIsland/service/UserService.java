package club.quan9.hIsland.service;

import club.quan9.hIsland.domain.entity.User;
import org.springframework.stereotype.Service;

public interface UserService
{
    public User getUserById(String id);
    public void register(User user);
}
