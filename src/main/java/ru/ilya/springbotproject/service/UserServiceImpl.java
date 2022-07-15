package ru.ilya.springbotproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilya.springbotproject.dao.UsersRepository;
import ru.ilya.springbotproject.entity.User;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    private final UsersRepository userRepository;

    @Autowired
    public UserServiceImpl(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void delByUserId(String userId) {
        userRepository.deleteByUserId(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUsers(User users) {
        userRepository.save(users);
    }

}
