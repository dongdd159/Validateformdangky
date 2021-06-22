package service;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import repository.IUserRepository;

import java.util.List;

public class UserService implements IUserService{

    @Autowired(required = true)
    private IUserRepository userRepository;

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void remove(Long id) {
        userRepository.delete(id);
    }
}
