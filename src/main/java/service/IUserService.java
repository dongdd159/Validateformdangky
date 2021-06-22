package service;

import model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    void save(User user);

    User findById(Long id);

    void remove(Long id);
}
