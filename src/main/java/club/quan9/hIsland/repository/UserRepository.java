package club.quan9.hIsland.repository;

import club.quan9.hIsland.domain.entity.User;

public interface UserRepository
{
    public User getUserById(String id);
    public void addUser(User user);
}
